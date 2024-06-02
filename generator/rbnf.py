import bisect
import logging
import math
import subprocess
import sys
import os
from io import StringIO
from pprint import pprint

import urllib3.request
import xml.etree.ElementTree as elementTree
from typing import Dict, List
from cldr_utils import ucamel, safe, lcamel, language_identifier, quote_str

GENDERS = {'feminine', 'masculine', 'neuter', 'common', 'inanimate'}

CASES = {'nominative', 'genitive', 'dative', 'accusative', 'instrumental', 'prepositional',
     'vocative', 'locative', 'oblique', 'terminative', 'translative',
     'illative', 'inessive', 'ablative', 'adessive', 'elative', 'essive', 'partitive', 'sociative', ''}

# https://unicode-org.github.io/icu-docs/apidoc/released/icu4c/classicu_1_1RuleBasedNumberFormat.html

logging.basicConfig()
logging.getLogger("urllib3").setLevel(logging.DEBUG)
CLDR_PATH = '/Users/egorleonenko/Downloads/cldr-common-43.0'
BASE_PATH = f"{CLDR_PATH}/common/rbnf"
SOURCE_PATH = "/Volumes/Projects/My/SailingApps/kotlin-i18n/src/commonMain/kotlin/info/leonenko/i18n"
TEST_SOURCE_PATH = "/Volumes/Projects/My/SailingApps/kotlin-i18n/src/commonTest/kotlin/info/leonenko/i18n"
PACKAGE = 'info.leonenko.i18n'
NL = '\n'
STANDARD_SPELLERS = {'%spellout-numbering-year', '%spellout-numbering', '%spellout-cardinal', '%spellout-ordinal'}
GENERATED = {}


class RBNFRuleComponent:
    def __init__(self):
        self._rule = None

    @property
    def rule(self):
        return self._rule

    @rule.setter
    def rule(self, value):
        self._rule = value

    def process(self, value, fractional):
        raise NotImplementedError(f"RBNFRuleComponent.process(...) is not handled in {type(self)}")

    def kotlin_code(self, value, fractional, integer):
        raise NotImplementedError(f"RBNFRuleComponent.kotlin_code(...) is not handled in {type(self)}")


class RBNFSubstitutionDescriptor:
    def __init__(self):
        self.token = None

    @classmethod
    def parse(cls, s):
        if s.startswith("%"):
            return RBNFSubstitutionDescriptorRule(s)
        elif s == "":
            return RBNFSubstitutionDescriptor()
        else:
            return RBNFSubstitutionDescriptorFormat(s)

    def process(self, value, fractional):
        if isinstance(self.token.rule, RBNFFractionRule) and (isinstance(self.token, RBNFRuleRRToken) or isinstance(self.token, RBNFRuleRRRToken)):
            digits = []
            while value > 1e-10:
                value *= 10
                digit = math.trunc(value)
                value -= digit
                digits.append(self.token.rule.ruleset.process(digit, False))
            return ("" if isinstance(self.token, RBNFRuleRRRToken) else " ").join(digits)

        # raise NotImplementedError("Empty substitution descriptor must be handled differently")
        return self.token.rule.ruleset.process(value, fractional)

    def __repr__(self):
        return ""

    def kotlin_code(self, value, fractional, integer):
        if isinstance(self.token.rule, RBNFFractionRule) and (isinstance(self.token, RBNFRuleRRToken) or isinstance(self.token, RBNFRuleRRRToken)):
            if integer:
                return '""'
            else:
                return f'formatFractionalPartByDigit({value}, "{"" if isinstance(self.token, RBNFRuleRRRToken) else " "}")'
        return f"format{'F' if fractional else ''}({value})"


def _ruleset_code_name(name):
    return safe(lcamel(name.lstrip("%")))


class RBNFSubstitutionDescriptorRule(RBNFSubstitutionDescriptor):
    def __init__(self, rule):
        super().__init__()
        self.rule = rule

    def __repr__(self):
        return f"CALL({self.rule})"

    def process(self, value, fractional):
        return self.token.rule.ruleset.group.rulesets[self.rule].process(value, fractional)

    def kotlin_code(self, value, fractional, integer):
        ruleset_name = _ruleset_code_name(self.rule)
        if ruleset_name == self.token.rule.ruleset.code_name:
            return f"format({value})"
        else:
            return f"{ruleset_name}.format{'F' if fractional else ''}({value})"


ALL_FORMATS = set()


class ICUDecimalFormat:
    def __init__(self, min_integer, min_decimal, max_decimal, primary_grouping, secondary_grouping):
        self.min_integer = min_integer
        self.secondary_grouping = secondary_grouping
        self.primary_grouping = primary_grouping
        self.max_decimal = max_decimal
        self.min_decimal = min_decimal

    def format(self, v):
        neg = v < 0
        v = abs(v)
        f = _fractional_part(v)
        i = int(v - f)
        i_digits = []
        k = 0
        while i > 0 or k < self.min_integer:
            i_digits.append(str(i % 10))
            i //= 10
            k += 1
            if self.primary_grouping != 0 and (k == self.primary_grouping or (k > self.primary_grouping and (k - self.primary_grouping) % self.secondary_grouping == 0)):
                i_digits.append(",")  # TODO localize ,

        i_s = "".join(reversed(i_digits)).lstrip(",")
        d_digits = []
        while (f > 0 or len(d_digits) < self.min_decimal) and len(d_digits) < self.max_decimal:
            f *= 10
            d = math.trunc(f)
            d_digits.append(str(d))
            f -= d

        if len(d_digits) > 0:
            d_s = "." + "".join(d_digits)  # TODO localize .
        else:
            d_s = ""

        return ("-" if neg else "") + i_s + d_s  # TODO localize -

    def __repr__(self):
        i = "0" * self.min_integer
        if self.primary_grouping != 0:
            i = "#," + "#" * (self.primary_grouping - self.min_integer) + i
        if self.secondary_grouping != 0 and self.secondary_grouping != self.primary_grouping:
            i = "#," + "#" * (self.secondary_grouping - 1) + i
        f = "0" * self.min_decimal + '#' * (self.max_decimal - self.min_decimal)
        if len(f) > 0:
            return i + "." + f
        else:
            return i

    @staticmethod
    def parse(s):
        number_parts = s.split('.')
        groups = number_parts[0].split(',')
        if len(groups) == 1:
            primary_grouping = 0
            secondary_grouping = primary_grouping
        elif len(groups) == 2:
            primary_grouping = len(groups[-1])
            secondary_grouping = primary_grouping
        else:
            primary_grouping = len(groups[-1])
            secondary_grouping = len(groups[-2])
        min_integer = len(groups[-1].lstrip('#'))
        if len(number_parts) == 2:
            max_decimal = len(number_parts[1])
            min_decimal = len(number_parts[1].rstrip("#"))
        else:
            min_decimal = 0
            max_decimal = 0

        return ICUDecimalFormat(min_integer, min_decimal, max_decimal, primary_grouping, secondary_grouping)


class RBNFSubstitutionDescriptorFormat(RBNFSubstitutionDescriptor):
    def __init__(self, fmt):
        super().__init__()
        self.fmt = ICUDecimalFormat.parse(fmt)
        ALL_FORMATS.add(fmt)

    def __repr__(self):
        return f"FMT({self.fmt})"

    def process(self, value, fractional):
        return self.fmt.format(value)

    def kotlin_code(self, value, fractional, integer):
        return f"DecimalNumberFormatter(min_integer = {self.fmt.min_integer}, min_decimal = {self.fmt.min_decimal}, max_decimal = {self.fmt.max_decimal}, " \
               f"primary_grouping = {self.fmt.primary_grouping}, secondary_grouping = {self.fmt.secondary_grouping}, symbols = language.numbers.symbols).format({value})"


class RBNFRuleToken(RBNFRuleComponent):
    def __init__(self, substitution: RBNFSubstitutionDescriptor):
        super().__init__()
        self.substitution = substitution
        self.substitution.token = self

    def new_value(self, value, fractional):
        raise NotImplementedError(f"new_value is not implemented in {type(self)}")

    def is_fractional(self, fractional):
        return False

    def process(self, value, fractional):
        r = self.substitution.process(self.new_value(value, fractional), self.is_fractional(fractional))
        # print(f"{self} {value} -> {r}", file=sys.stderr)
        return r

    def new_value_code(self, value, fractional, integer):
        raise NotImplementedError(f"new_value_code is not implemented in {type(self)}")

    def kotlin_code(self, value, fractional, integer):
        return self.substitution.kotlin_code(self.new_value_code(value, fractional, integer), self.is_fractional(fractional), integer)


def _fractional_part(v):
    return v - math.trunc(v)


class RBNFRuleRRToken(RBNFRuleToken):
    def __repr__(self) -> str:
        return f"RR({self.substitution})"

    def is_fractional(self, fractional):
        return isinstance(self.rule, RBNFFractionRule) or super().is_fractional(fractional)

    def new_value(self, value, fractional):
        if isinstance(self.rule, RBNFBaseValueRule):
            return value % self.rule.divisor
        if isinstance(self.rule, RBNFNegativeRule):
            return abs(value)
        if isinstance(self.rule, RBNFFractionRule):
            return _fractional_part(abs(value))
        raise NotImplementedError(f"RR does not support {type(self.rule)}")

    def new_value_code(self, value, fractional, integer):
        if isinstance(self.rule, RBNFBaseValueRule):
            return f"{value} % {self.rule.divisor}"
        if isinstance(self.rule, RBNFNegativeRule):
            return f"kotlin.math.abs({value})"
        if isinstance(self.rule, RBNFFractionRule):
            if integer:
                return "0"
            else:
                return f"kotlin.math.abs({value}) - kotlin.math.truncate(kotlin.math.abs({value}))"
        raise NotImplementedError(f"RR does not support {type(self.rule)}")


class RBNFRuleRRRToken(RBNFRuleRRToken):
    def process(self, value, fractional):
        if type(self.substitution) == RBNFSubstitutionDescriptor and isinstance(self.rule, RBNFFractionRule):
            return super(RBNFRuleRRRToken, self).process(value, fractional)
        return self.rule.ruleset.previous_rule(self.rule).process(self.new_value(value, fractional), fractional)

    def kotlin_code(self, value, fractional, integer):
        if type(self.substitution) == RBNFSubstitutionDescriptor and isinstance(self.rule, RBNFFractionRule):
            return super(RBNFRuleRRRToken, self).kotlin_code(value, fractional, integer)
        previous_rule = self.rule.ruleset.previous_rule(self.rule)
        if isinstance(previous_rule, RBNFBaseValueRule):
            return previous_rule.inner_kotlin_code(self.new_value_code(value, fractional, integer), fractional, integer, True)
        else:
            return f'"{previous_rule.code_name}({self.new_value_code(value, fractional, integer)})"'

    def __repr__(self) -> str:
        return f"RRR({self.substitution})"


class RBNFRuleLLToken(RBNFRuleToken):
    def __repr__(self) -> str:
        return f"LL({self.substitution})"

    def new_value(self, value, fractional):
        if fractional:
            return int(value * self.rule.base_value)
        if isinstance(self.rule, RBNFBaseValueRule):
            return value // self.rule.divisor
        if isinstance(self.rule, RBNFFractionRule):
            return int(math.trunc(value))
        raise NotImplementedError(f"LL does not support {self.rule.code_name}")

    def new_value_code(self, value, fractional, integer):
        if fractional:
            return f"({value} * {self.rule.base_value}){'.toLong()' if not integer else ''}"
        if isinstance(self.rule, RBNFBaseValueRule):
            return f"({value}){'.toLong()' if not integer else ''} / {self.rule.divisor}"
        if isinstance(self.rule, RBNFFractionRule):
            if integer:
                return value
            else:
                return f"kotlin.math.truncate({value}).toLong()"
        raise NotImplementedError(f"LL does not support {self.rule.code_name}")

    def process(self, value, fractional):
        if type(self.substitution) == RBNFSubstitutionDescriptor and fractional:
            # TODO If you omit the substitution descriptor in a << substitution in a rule in a fraction rule set, format the result using the default rule set for this formatter.
            raise NotImplementedError("Not implemented yet")
        else:
            return super(RBNFRuleLLToken, self).process(value, fractional)

    def kotlin_code(self, value, fractional, integer):
        if type(self.substitution) == RBNFSubstitutionDescriptor and fractional:
            # TODO If you omit the substitution descriptor in a << substitution in a rule in a fraction rule set, format the result using the default rule set for this formatter.
            return f'formatDefault({value})'
        else:
            return super(RBNFRuleLLToken, self).kotlin_code(value, fractional, integer)


class RBNFRuleLLLToken(RBNFRuleToken):
    def new_value_code(self, value, fractional, integer):
        if fractional:
            return f"kotlin.math.abs({value}) - kotlin.math.truncate(kotlin.math.abs({value}))"
        raise NotImplementedError(f"LLL does not support non-fractional")

    def new_value(self, value, fractional):
        if fractional:
            return _fractional_part(abs(value))
        raise NotImplementedError(f"LLL does not support non-fractional")

    def kotlin_code(self, value, fractional, integer):
        if integer:
            return '""'
        else:
            return f"(List({value}.leadingFractionalZeroesCount()) {{ {self.substitution.kotlin_code('0', False, integer)} }} " \
                   f"+ listOf({self.substitution.kotlin_code(f'({value} * {self.rule.base_value}).toLong()', False, integer)})).joinToString(\" \")"

    def process(self, value, fractional):
        v = self.new_value(value, fractional)
        digits = []
        while v > 1e-10:
            v *= 10
            digit = math.trunc(v)
            if digit == 0:
                digits.append(self.substitution.process(0, False))
            else:
                break
        return " ".join(digits + [self.substitution.process(int(value * self.rule.base_value), False)])

    def __repr__(self) -> str:
        return f"LLL({self.substitution})"


class RBNFRuleEEToken(RBNFRuleToken):
    def __repr__(self) -> str:
        return f"EE({self.substitution})"

    def new_value(self, value, fractional):
        return value

    def new_value_code(self, value, fractional, integer):
        return value


class RBNFRulePluralToken(RBNFRuleLLToken):
    def __init__(self, rules):
        super().__init__(RBNFSubstitutionDescriptor())
        self.rules = rules

    @classmethod
    def parse(cls, s):
        rules = {}
        while len(s) > 0:
            v_start = s.index('{')
            v_end = s.index('}', v_start)
            rule_name = s[0:v_start]
            v = s[v_start + 1:v_end]
            s = s[v_end + 1:]
            rules[rule_name] = v
        return cls(rules)

    # TODO external rule
    def rule_type(self, i):
        if ((i % 10) == 1) and ((i % 100) != 11):
            return 'one'
        if (2 <= (i % 10) <= 4) and not (11 <= (i % 100) <= 14):
            return 'few'
        if ((i % 10) == 0) or (5 <= (i % 10) <= 9) or (11 <= (i % 100) <= 14):
            return 'many'
        return 'other'

    def process(self, value, fractional):
        return self.rules.get(self.rule_type(self.new_value(value, fractional)), self.rules['other'])

    def kotlin_code(self, value, fractional, integer):
        return f'Plurals({", ".join([n + " = " + quote_str(v) for n, v in self.rules.items()])}).get(language.pluralFormChooser.pluralForm(({self.new_value_code(value, fractional, integer)}).toString()))'


class RBNFRuleCardinalToken(RBNFRulePluralToken):
    def __repr__(self) -> str:
        return f"Cardinal({self.rules})"


class RBNFRuleOrdinalToken(RBNFRulePluralToken):
    def __repr__(self) -> str:
        return f"Ordinal({self.rules})"


class RBNFRuleOptional(RBNFRuleComponent):
    def __init__(self, components: List[RBNFRuleComponent]):
        super().__init__()
        self.components = components

    @property
    def rule(self):
        return super().rule

    @rule.setter
    def rule(self, value):
        self._rule = value
        for c in self.components:
            c.rule = value

    def __repr__(self) -> str:
        return f"({self.components})?"

    def process(self, value, fractional):
        hide = False
        if fractional:
            hide = value * self.rule.base_value == 1
        else:
            if isinstance(self.rule, RBNFBaseValueRule):
                hide = value % self.rule.divisor == 0
            elif isinstance(self.rule, RBNFNegativeRule):
                raise NotImplementedError("Optional is not allowed in negative rule")
            elif isinstance(self.rule, RBNFImproperFractionRule):
                hide = 0 < value < 1
            elif isinstance(self.rule, RBNFProperFractionRule):
                raise NotImplementedError("Optional is not allowed in proper fraction rule")
            elif isinstance(self.rule, RBNFDefaultRule):
                hide = isinstance(value, int)
        # print(f"? {value} in {type(rule)} -> {hide}")

        return "" if hide else ("".join([c.process(value, fractional) for c in self.components]))

    def kotlin_code(self, value, fractional, integer):
        hide = None
        if fractional:
            hide = f"{value} * {self.rule.base_value} == 1.0"
        else:
            if isinstance(self.rule, RBNFBaseValueRule):
                hide = f"({value}){'.toLong()' if not integer else ''} % {self.rule.divisor} == 0L"
            elif isinstance(self.rule, RBNFNegativeRule):
                raise NotImplementedError("Optional is not allowed in negative rule")
            elif isinstance(self.rule, RBNFImproperFractionRule):
                hide = f"{value} in 0.0..1.0"
            elif isinstance(self.rule, RBNFProperFractionRule):
                raise NotImplementedError("Optional is not allowed in proper fraction rule")
            elif isinstance(self.rule, RBNFDefaultRule):
                if integer:
                    return ""
        if hide is None:
            raise NotImplementedError(f"Optional is not supported in {self.rule.code_name} rule")
        code_pieces = [c.kotlin_code(value, fractional, integer) for c in self.components]
        if len(code_pieces) == 1:
            return f"(if ({hide}) \"\" else {code_pieces[0]})"
        else:
            if any(['\n' in cp for cp in code_pieces]):
                code = f"if ({hide}) {{\n\"\"\n}} \n else {{\n var r = {code_pieces[0]}\n"
                for cp in code_pieces[1:]:
                    code += f"r += {cp}\n"
                code += "r\n}"
                return code
            else:
                return f"(if ({hide}) {{\n\"\"\n}} \n else {{\n{' + '.join(code_pieces)}\n}})"


class RBNFRuleText(RBNFRuleComponent):
    def __init__(self, value):
        super().__init__()
        self.value = value

    def __repr__(self) -> str:
        return f'"{self.value}"'

    def process(self, value, fractional):
        return self.value

    def kotlin_code(self, value, fractional, integer):
        return f"\"{self.value}\""


class RBNFRule:
    def __init__(self, components: List[RBNFRuleComponent]):
        self.components = components
        for c in self.components:
            c.rule = self
        self._ruleset = None

    @property
    def ruleset(self):
        return self._ruleset

    @ruleset.setter
    def ruleset(self, value):
        self._ruleset = value

    def process(self, value, fractional):
        return "".join([c.process(value, fractional) for c in self.components])

    @staticmethod
    def __parse(s: str):
        if s.startswith('←'):
            end2 = s.find('←←', 1)
            end = s.index('←', 1)
            if end2 != -1 and end == end2:
                return RBNFRuleLLLToken(RBNFSubstitutionDescriptor.parse(s[1:end2])), s[end2 + 2:]
            return RBNFRuleLLToken(RBNFSubstitutionDescriptor.parse(s[1:end])), s[end + 1:]
        if s.startswith('['):
            end = s.index(']', 1)
            return RBNFRuleOptional(RBNFRule.parse(s[1:end], inner=True)), s[end + 1:]
        if s.startswith('='):
            end = s.index('=', 1)
            return RBNFRuleEEToken(RBNFSubstitutionDescriptor.parse(s[1:end])), s[end + 1:]
        if s.startswith('→→→'):
            return RBNFRuleRRRToken(RBNFSubstitutionDescriptor()), s[3:]
        if s.startswith('→'):
            end = s.index('→', 1)
            return RBNFRuleRRToken(RBNFSubstitutionDescriptor.parse(s[1:end])), s[end + 1:]
        if s.startswith("$(cardinal,"):
            end = s.index(')$', 10)
            return RBNFRuleCardinalToken.parse(s[11:end]), s[end + 2:]
        if s.startswith("$(ordinal,"):
            end = s.index(')$', 10)
            return RBNFRuleOrdinalToken.parse(s[10:end]), s[end + 2:]
        if s.startswith(";"):
            return None, ""

        return RBNFRuleText(s[0:1]), s[1:]

    @staticmethod
    def parse(s, inner=False):
        s = s.replace("<", "←").replace(">", "→")
        if not inner:
            s = s.strip()
            if s.startswith("'"):
                s = s[1:]
        components = []
        while len(s) > 0:
            nc, s = RBNFRule.__parse(s)
            if nc is not None:
                if len(components) > 0 and isinstance(components[-1], RBNFRuleText) and isinstance(nc, RBNFRuleText):
                    components[-1] = RBNFRuleText(components[-1].value + nc.value)
                else:
                    components.append(nc)
        if len(components) == 0:
            components.append(RBNFRuleText(""))
        return components

    @staticmethod
    def make(name, radix, definition, previous=None):
        try:
            components = RBNFRule.parse(definition)
            if name == "-x":
                return RBNFNegativeRule(components)
            if name.startswith('x') and name.endswith('x'):
                return RBNFImproperFractionRule(components, name[1:-1])
            if name.startswith('0') and name.endswith('x'):
                return RBNFProperFractionRule(components, name[1:-1])
            if name.startswith('x') and name.endswith('0'):
                return RBNFDefaultRule(components, name[1:-1])
            if name == "Inf":
                return RBNFInfinityRule(components)
            if name == "NaN":
                return RBNFNaNRule(components)

            if len(name) == 0:
                return RBNFBaseValueRule(previous.base_value + 1 if previous is not None else 0,
                                         int((radix or (str(previous.radix) if previous is not None else None) or "10").replace(" ", "").replace(",", "").replace(".", "")), 0,
                                         components)

            return RBNFBaseValueRule(int(name.replace(" ", "").replace(",", "").replace(".", "")), int((radix or "10").replace(" ", "").replace(",", "").replace(".", "")), 0,
                                     components)
        except Exception as e:
            print(f"{name}: {definition} => {e}", file=sys.stderr)
            # raise

    @staticmethod
    def parse_name(name):
        name = name.strip()

        if name in {"-x", "x.x", "x,x", "0.x", "x.0", "Inf", "NaN"}:
            return name, None
        dec = 0
        while name.endswith('>'):
            name = name[:-1]
            dec += 1
        if '|' in name:
            return name.split('|')
        else:
            return name, None

    def descriptor(self):
        raise NotImplementedError()

    def __repr__(self):
        return f"{self.descriptor()}: {self.components}"

    @property
    def code_name(self):
        return f"format{self.rule_type()}"

    def kotlin_function(self, fractional, integer):
        code = f"private fun {self.code_name}(value:{'Long' if integer else 'Double'}) : String {{\n"
        code += self.inner_kotlin_code("value", fractional, integer) + "\n"
        code += "}"
        return code

    def supports_real(self):
        return False

    def supports_integer(self):
        return True

    def kotlin_code(self):
        code = ""
        if self.supports_integer():
            code += self.kotlin_function(False, True)
        if self.supports_real():
            code += self.kotlin_function(False, False)
        return code

    def rule_type(self):
        return type(self).__name__[4:-4]

    def inner_kotlin_code(self, value, fractional, integer, expression=False):
        code_pieces = [c.kotlin_code(value, fractional, integer) for c in self.components]
        if len(code_pieces) == 1:
            return ("return " if not expression else "") + code_pieces[0]
        else:
            if any(['\n' in cp for cp in code_pieces]):
                code = f"{('{' + NL) if expression else ''}var result = {self.components[0].kotlin_code(value, fractional, integer)}\n"
                for c in self.components[1:]:
                    code += f"result += {c.kotlin_code(value, fractional, integer)}\n"
                code += ("return " if not expression else "") + "result"
                if expression:
                    code += "\n}"
                return code
            else:
                return ("return " if not expression else "") + ' + '.join(code_pieces)


class RBNFBaseValueRule(RBNFRule):
    def __init__(self, base_value, radix, dec, components):
        super(RBNFBaseValueRule, self).__init__(components)
        self.radix = radix
        self.dec = dec
        self.base_value = base_value
        self.divisor = 1
        while self.divisor * radix <= base_value:
            self.divisor *= radix
        # TODO verify logic below:
        for _ in range(self.dec):
            self.divisor /= radix

    def rule_type(self):
        return str(self.base_value)

    def descriptor(self):
        return f"{self.base_value}" + (f"|{self.radix}" if self.radix != 10 else "") + (">" * self.dec)

    def may_need_to_use_previous(self):
        return self.base_value % self.divisor != 0 and sum([sum([1 for oc in c.components if not isinstance(oc, RBNFRuleText)]) if isinstance(c, RBNFRuleOptional) else 1
                                                            for c in self.components if not isinstance(c, RBNFRuleText)]) == 2


class RBNFNegativeRule(RBNFRule):
    def descriptor(self):
        return "-x"

    def supports_real(self):
        return True


class RBNFFractionRule(RBNFRule):
    def __init__(self, components: List[RBNFRuleComponent], separator):
        super().__init__(components)
        self.separator = separator

    def descriptor(self):
        raise NotImplementedError("ABC")

    def supports_real(self):
        return True


class RBNFFractionMultiRule(RBNFRule):
    def descriptor(self):
        return "|".join([sr.descriptor for sr in self.variants])

    def __init__(self, variants: [RBNFRule]):
        super().__init__([])
        self.variants = variants

    @property
    def ruleset(self):
        return super().ruleset

    @ruleset.setter
    def ruleset(self, value):
        self._ruleset = value
        for c in self.variants:
            c.ruleset = value

    def inner_kotlin_code(self, value, fractional, integer, expression=False):
        if len(self.variants) == 1:
            return self.variants.inner_kotlin_code(value, fractional, integer, expression)
        else:
            dot_variant = [v for v in self.variants if v.separator == '.'][0]
            other_variants = [v for v in self.variants if v.separator != '.']
            code = "return " if not expression else ""
            code += f"when (language.numbers.symbols.decimalSymbol) {{\n"
            for v in other_variants:
                code += f"\"{v.separator}\" -> {v.inner_kotlin_code(value, fractional, integer, True)}\n"
            code += f"else -> {dot_variant.inner_kotlin_code(value, fractional, integer, True)}\n"
            code += "}"
            if expression:
                code += "\n}"
            return code

    def supports_real(self):
        return self.variants[0].supports_real()

    def supports_integer(self):
        return self.variants[0].supports_integer()

    @property
    def code_name(self):
        return self.variants[0].code_name

    def process(self, value, fractional):
        # TODO select based on language
        dot_variants = [v for v in self.variants if v.separator == '.']
        if len(dot_variants) > 0:
            return dot_variants[0].process(value, fractional)
        else:
            other_variants = [v for v in self.variants if v.separator != '.']
            return other_variants[0].process(value, fractional)


class RBNFImproperFractionRule(RBNFFractionRule):
    def descriptor(self):
        return f"x{self.separator}x"

    def supports_integer(self):
        return False


class RBNFProperFractionRule(RBNFFractionRule):
    def descriptor(self):
        return f"0{self.separator}x"

    def supports_integer(self):
        return False


class RBNFDefaultRule(RBNFFractionRule):
    def descriptor(self):
        return f"x{self.separator}0"


class RBNFInfinityRule(RBNFRule):
    def descriptor(self):
        return "Inf"

    def supports_integer(self):
        return False

    def supports_real(self):
        return True


class RBNFNaNRule(RBNFRule):
    def descriptor(self):
        return "NaN"

    def supports_integer(self):
        return False

    def supports_real(self):
        return True


class RBNFRuleSet:
    def __init__(self, name, rules: [RBNFRule]):
        self.name = name
        self.rules = rules
        for r in self.rules:
            r.ruleset = self
        self.group = None
        self.default_index = None
        self.negative_index = None
        self.improper_index = None
        self.proper_index = None
        self.nan_index = None
        self.infinity_index = None
        self.proper_index = None
        base_value_rule_index = []
        for ri, rule in enumerate(rules):
            if isinstance(rule, RBNFNegativeRule):
                self.negative_index = ri
            elif isinstance(rule, RBNFDefaultRule) or (isinstance(rule, RBNFFractionMultiRule) and isinstance(rule.variants[0], RBNFDefaultRule)):
                self.default_index = ri
            elif isinstance(rule, RBNFProperFractionRule) or (isinstance(rule, RBNFFractionMultiRule) and isinstance(rule.variants[0], RBNFProperFractionRule)):
                self.proper_index = ri
            elif isinstance(rule, RBNFImproperFractionRule) or (isinstance(rule, RBNFFractionMultiRule) and isinstance(rule.variants[0], RBNFImproperFractionRule)):
                self.improper_index = ri
            elif isinstance(rule, RBNFNaNRule):
                self.nan_index = ri
            elif isinstance(rule, RBNFInfinityRule):
                self.infinity_index = ri
            elif isinstance(rule, RBNFBaseValueRule):
                base_value_rule_index.append((rule.base_value, ri))
        unsorted = base_value_rule_index
        base_value_rule_index = sorted(base_value_rule_index, key=lambda v: v[0])

        if unsorted != base_value_rule_index:
            print(f"{name}: Unsorted base value rules", file=sys.stderr)

        self.base_value_rule_index = ([v[0] for v in base_value_rule_index], [v[1] for v in base_value_rule_index])

    def __repr__(self):
        return f"{self.name}: \n" + "\n".join([repr(r) for r in self.rules])

    def previous_rule(self, r):
        return self.rules[self.rules.index(r) - 1]

    def process(self, value, fractional):
        if fractional:
            return self.process_fractional(value)
        else:
            return self.process_regular(value)

    def process_regular(self, value):
        rule_index = None
        if isinstance(value, float) and self.default_index is not None:
            rule_index = self.default_index
        elif value < 0:
            rule_index = self.negative_index
        elif isinstance(value, float):
            if abs(value) < 1 and self.proper_index is not None:
                rule_index = self.proper_index
            else:
                rule_index = self.improper_index

        if rule_index is None:
            index_pos = bisect.bisect_right(self.base_value_rule_index[0], value) - 1
            if index_pos == -1:
                rule_index = self.default_index if self.default_index is not None else self.improper_index
            else:
                rule_index = self.base_value_rule_index[1][index_pos]

        rule = self.rules[rule_index]
        # print(f"Start {self.name} {value} with {rule}", file=sys.stderr)

        return rule.process(value, False)

    def process_fractional(self, value):
        # best_dv = None
        # best_rule = -1
        def _dti(v):
            return abs(v - round(v))

        rule_rating = sorted([(_dti(value * rule.base_value), ri, rule) for ri, rule in enumerate(self.rules) if isinstance(rule, RBNFBaseValueRule)], key=lambda rr: rr[:2])
        best_rule = rule_rating[0][1]
        # for ri, rule in enumerate(self.rules):
        #     if isinstance(rule, RBNFBaseValueRule):
        #         v = value * rule.divisor - rule.base_value
        #         dv = abs(v - round(v))
        #         if best_dv is None or dv < best_dv:
        #             best_dv = dv
        #             best_rule = ri
        #         elif dv == best_dv:
        #             break

        rule = self.rules[best_rule]
        # print(f"FStart {self.name} {value} with {rule}", file=sys.stderr)

        return rule.process(value, True)

    @property
    def code_name(self):
        return _ruleset_code_name(self.name)

    @property
    def class_name(self):
        option1 = safe(ucamel(self.name.lstrip("%")))
        if option1 == self.code_name:
            return safe(ucamel(self.name.lstrip("%") + "-c"))
        else:
            return option1

    def _only_delegating_0_base_rule(self):
        return (len(self.base_value_rule_index[0]) == 1
                and self.base_value_rule_index[0][0] == 0
                and len(self.rules[self.base_value_rule_index[1][0]].components) == 1
                and isinstance(self.rules[self.base_value_rule_index[1][0]].components[0], RBNFRuleEEToken)
                and isinstance(self.rules[self.base_value_rule_index[1][0]].components[0].substitution, RBNFSubstitutionDescriptorRule))

    def _kotlin_integer_function(self):
        code = f"override fun format(value:Long):String {{"
        if self._only_delegating_0_base_rule() and self.negative_index is None and self.default_index is None:
            code += f"return {self.rules[self.base_value_rule_index[1][-1]].inner_kotlin_code('value', False, True, True)}\n"
        else:
            if self.negative_index is not None:
                code += f"""if (value < 0L) {{
                            return {self.rules[self.negative_index].inner_kotlin_code('value', False, True, True)}
                        }}
                        """
            # if len(self.base_value_rule_index[0]) > 0:
            #     if len(self.base_value_rule_index[1]) == 1:
            #         code += f'return {self.rules[self.base_value_rule_index[1][-1]].inner_kotlin_code("value", False, True, True)}'
            #     else:
            code += self._kotlin_base_value_select("value", True)
            # else:
            #     code += f"    throw UnsupportedOperationException(\"{self.code_name} does not have base-value rules\")\n"

        code += "}\n"
        return code

    def _kotlin_base_value_select(self, value, integer):
        suffix = 'L' if integer else '.0'
        code = "return "
        if len(self.base_value_rule_index[1]) > 1:
            code += f"when ({value}{'' if integer else '.roundToLong()'}) {{\n"
            for i0, i1 in zip(self.base_value_rule_index[1][0:-1],
                              self.base_value_rule_index[1][1:]):
                r0: RBNFBaseValueRule = self.rules[i0]
                r1: RBNFBaseValueRule = self.rules[i1]
                if r0.base_value == r1.base_value - 1:
                    selector = f"{r0.base_value}L"
                else:
                    selector = f'in {r0.base_value}L..{r1.base_value - 1}L'
                # if r0.base_value == r1.base_value - 1 and integer:
                #     selector = f"{r0.base_value}{suffix}"
                # elif integer:
                #     selector = f'in {r0.base_value}{suffix}..{r1.base_value - 1}{suffix}'
                # else:
                #     selector = f'in {r0.base_value}{suffix} until {r1.base_value}{suffix}'
                normal_code = r0.inner_kotlin_code(value, False, integer, True)
                entry_code = normal_code
                if r0.may_need_to_use_previous():
                    previous_rule = self.rules[self.base_value_rule_index[1][self.base_value_rule_index[1].index(i0) - 1]]
                    entry_code = f""" if (({value}){'' if integer else '.toLong()'} % {r0.divisor} == 0L) {{
                        {previous_rule.inner_kotlin_code(value, False, integer, True)}
                    }} else 
                        {normal_code}
                    """
                code += f'{selector} -> {entry_code}\n'
            code += "else -> {\n"
        if len(self.base_value_rule_index[0]) > 0:
            code += f"  if ({value} >= {self.base_value_rule_index[0][-1]}{suffix}) "
            code += f"    {self.rules[self.base_value_rule_index[1][-1]].inner_kotlin_code(value, False, integer, True)}"
            code += " else {\n"
        if self.default_index is not None:
            code += f"    {self.rules[self.default_index].code_name}({value})\n"
        else:
            if len(self.base_value_rule_index[0]) > 0:
                code += f"    throw UnsupportedOperationException(\"{self.code_name} only supports numbers >= {self.base_value_rule_index[0][0]}\")\n"
            else:
                code += f"    throw UnsupportedOperationException(\"{self.code_name} has no base-value rules or default rule\")\n"
        if len(self.base_value_rule_index[0]) > 0:
            code += "   }\n"
        if len(self.base_value_rule_index[1]) > 1:
            code += "}\n"
            code += "}\n"
        return code

    def _kotlin_real_function(self):
        code = f"override fun format(value:Double):String {{"
        if self.nan_index is not None:
            code += f"""if (value.isNaN()) {{
                            return {self.rules[self.nan_index].inner_kotlin_code('value', False, False, True)}
                        }}
                            """
        if self.infinity_index is not None:
            code += f"""if (value.isInfinite()) {{
                            return {self.rules[self.infinity_index].inner_kotlin_code('value', False, False, True)}
                        }}
                            """
        if self.default_index is not None:
            code += f"return {self.rules[self.default_index].code_name}(value)"
        else:
            if self.negative_index is not None:
                code += f"""if (value < 0L) {{
                               return {self.rules[self.negative_index].inner_kotlin_code('value', False, False, True)}
                       }}
                """
            if self.proper_index is not None and self.improper_index is not None:
                code += f"""return if (value in -1.0..1.0) {{
                    {self.rules[self.proper_index].code_name}(value)
                }} else {{
                    {self.rules[self.improper_index].code_name}(value)
                }}           
                """
            elif self.improper_index is not None:
                code += f"return {self.rules[self.improper_index].code_name}(value)"
            else:
                code += self._kotlin_base_value_select("value", False)
                # code += f" throw UnsupportedOperationException(\"{self.code_name} only supports integer numbers\") "
        code += "}\n"
        return code

    def _kotlin_fractional_function(self, private_class):
        base_value_rules = [rule for rule in self.rules if isinstance(rule, RBNFBaseValueRule)]
        has_empty_ll = any([any([type(c) == RBNFRuleLLToken and type(c.substitution) == RBNFSubstitutionDescriptor for c in r.components]) for r in base_value_rules])
        # TODO check references
        # has_rr_in_fraction_rule = any([any([isinstance(c, RBNFRuleRRToken) and type(c.substitution) != RBNFSubstitutionDescriptor for c in r.components]) for r in self.rules if isinstance(r, RBNFFractionRule)])
        if len(base_value_rules) > 0 and (not has_empty_ll or self.default_index is not None):
            code = f"fun formatF(value:Double):String {{\n"
            code += f"return when (listOf<Long>({', '.join([str(rule.base_value) for rule in base_value_rules])}).bestDenominatorOrNull(value)!!) {{\n"
            for rule in base_value_rules:
                code += f"{rule.base_value}L -> {rule.inner_kotlin_code('value', True, False, True)}\n"
            code += "else -> throw IllegalStateException(\"It should not happen\")\n"
            code += "}\n"
            code += "}\n"
            return code
        else:
            return ""

    def kotlin_code(self, private, override):
        code = f"private inner class {self.class_name} : NumberFormatter {{\n"
        code += self._kotlin_real_function()
        code += self._kotlin_integer_function()
        code += self._kotlin_fractional_function(private)

        for rule in self.rules:
            if not isinstance(rule, RBNFBaseValueRule) and not isinstance(rule, RBNFInfinityRule) and not isinstance(rule, RBNFNaNRule) and not isinstance(rule, RBNFNegativeRule):
                code += rule.kotlin_code() + "\n"
        code += "}\n"

        code2 = f"val {self.code_name} = {self.class_name}()\n"
        # if not private:
        #     code2 += f"{'override ' if override else 'open '}val {self.public_code_name} : NumberFormatter \n get() = {self.code_name}"
        return code2, code


class RBNFRuleSetGrouping:
    def __init__(self, kind, rulesets: Dict[str, RBNFRuleSet]):
        self.kind = kind
        self.rulesets = rulesets
        for rs in rulesets.values():
            rs.group = self
        public = [rs for rs in rulesets.keys() if not rs.startswith('%%')]
        self.default_rule = None if len(public) != 1 else public[0]

    def __repr__(self):
        return self.kind + "\n" + "\n".join([repr(v) for v in self.rulesets.values()])

    @staticmethod
    def parse(s):
        groups = []
        rules = []
        ruleset_name = None
        for line in s.splitlines():
            if line.startswith('%'):
                if len(rules) > 0:
                    groups.append(RBNFRuleSet(ruleset_name, rules))
                    rules = []
                name_end = line.index(':')
                ruleset_name = line[:name_end]
                line = line[name_end + 1:].strip()

            for rule_definition in line.split(";"):
                rule_definition = rule_definition.strip()
                if len(rule_definition) > 0:
                    name_end = rule_definition.index(':')
                    rule_name = rule_definition[:name_end].strip()
                    rule = rule_definition[name_end + 1:].strip()
                    new_rule = RBNFRule.make(*RBNFRule.parse_name(rule_name), definition=rule, previous=rules[-1] if len(rules) > 0 else None)
                    if isinstance(new_rule, RBNFFractionRule) and len(rules) > 0 and isinstance(new_rule, type(rules[-1])):
                        rules[-1] = RBNFFractionMultiRule([rules[-1], new_rule])
                    elif isinstance(new_rule, RBNFFractionRule) and len(rules) > 0 and isinstance(rules[-1], RBNFFractionMultiRule) and isinstance(new_rule,
                                                                                                                                                   type(rules[-1].variants)):
                        rules[-1].variants.append(new_rule)
                    else:
                        rules.append(new_rule)

        if len(rules) > 0:
            groups.append(RBNFRuleSet(ruleset_name, rules))

        return RBNFRuleSetGrouping("", {g.name: g for g in groups})

    @property
    def code_name(self):
        return self.kind

    def kotlin_code(self):
        code = f"private inner class {self.code_name} {{\n"
        code_late = ""
        for name, rs in self.rulesets.items():
            code1, code2 = rs.kotlin_code(name.startswith('%%'), override=False)
            code += code1 + "\n"
            code_late += code2 + "\n"

        code += code_late
        code += "}\n"
        return self.code_name, code


def load(filename):
    if not os.path.exists(filename):
        return None, None, None
    with open(filename, 'r') as f:
        ldml = elementTree.fromstring(f.read())

    identity = ldml.find('identity')
    lang, script, territory, _, lang_name = language_identifier(identity)
    rbnf = ldml.find('rbnf')
    if rbnf is not None:
        groups = []
        for rulesetGrouping in rbnf.findall('rulesetGrouping'):
            t = rulesetGrouping.attrib['type']
            rulesets = {}
            for ruleset in rulesetGrouping.findall('ruleset'):
                name = ruleset.attrib['type']
                if name == 'lenient-parse':
                    continue
                private = ruleset.attrib.get('access', 'public') == 'private'
                rules = []
                for rbnfrule in ruleset.findall('rbnfrule'):
                    if len(set(rbnfrule.attrib.keys()).difference({'value', 'radix'})) > 0:
                        print(rbnfrule.attrib, file=sys.stderr)
                    new_rule = RBNFRule.make(rbnfrule.attrib['value'], rbnfrule.attrib.get('radix'), rbnfrule.text, rules[-1] if len(rules) > 0 else None)
                    if isinstance(new_rule, RBNFFractionRule) and len(rules) > 0 and isinstance(new_rule, type(rules[-1])):
                        rules[-1] = RBNFFractionMultiRule([rules[-1], new_rule])
                    elif isinstance(new_rule, RBNFFractionRule) and len(rules) > 0 and isinstance(rules[-1], RBNFFractionMultiRule) and isinstance(new_rule,
                                                                                                                                                   type(rules[-1].variants)):
                        rules[-1].variants.append(new_rule)
                    else:
                        rules.append(new_rule)

                rulesets[('%' if private else '') + '%' + name] = RBNFRuleSet(name, rules)
            groups.append(RBNFRuleSetGrouping(t, rulesets))
        return (None if script is None and territory is None else lang), lang_name, groups

    return None, None, None


def run(group, rule, value):
    print(rule, value, "=>", group.rulesets[rule].process(value, False))


def run_test(rules, expected, value, base_rule=None):
    group = RBNFRuleSetGrouping.parse(rules)
    actual = group.rulesets[base_rule or group.default_rule].process(value, False)
    print(f"{value} => {actual}")
    assert (expected == actual)


DRY_RUN = False


def generate(lang):
    base_language, default_language, groups = load(f'{BASE_PATH}/{lang}.xml')
    if groups is None:
        return None
    os.makedirs(f'{SOURCE_PATH}/rbnf', exist_ok=True)
    print(f"Generating {lang} ({base_language})", file=sys.stderr)
    with open('/dev/null' if DRY_RUN else f'{SOURCE_PATH}/rbnf/RBNF{ucamel(lang)}.kt', 'w') as f:
        print(f"package {PACKAGE}.rbnf", file=f)
        print(f"""
import kotlin.math.roundToLong
import {PACKAGE}.DecimalNumberFormatter
import {PACKAGE}.Language
import {PACKAGE}.RBNF
import {PACKAGE}.NumberFormatter
import {PACKAGE}.Case
import {PACKAGE}.Gender
import {PACKAGE}.languages.{safe(default_language)}
import {PACKAGE}.formatFractionalPartByDigit
import {PACKAGE}.bestDenominatorOrNull
import {PACKAGE}.leadingFractionalZeroesCount
import {PACKAGE}.Plurals
import {PACKAGE}.until
import {PACKAGE}.contains

""", file=f)

        print("", file=f)
        print(f"{'open ' if base_language is None else ''}class RBNF{ucamel(lang)}({'val ' if base_language is None else ''}language: Language{(' = Language.' + safe(default_language)) if default_language is not None else ''}) : {'RBNF' if base_language is None else 'RBNF' + ucamel(base_language) + '(language)'} {{", file=f)
        existing_spellers = list(STANDARD_SPELLERS) + (GENERATED[base_language] if base_language is not None else [])
        public = []
        for g in groups:
            class_name, code = g.kotlin_code()
            print(code, file=f)

            field_name = g.code_name[0].lower() + g.code_name[1:]
            public_rulesets = [rsn for rsn in g.rulesets.keys() if not rsn.startswith("%%")]
            if lang in GENERATED:
                GENERATED[lang] += public_rulesets
            else:
                GENERATED[lang] = public_rulesets
            public += [(field_name, rsn) for rsn in public_rulesets]

            print(f"private val {field_name} = {g.code_name}()", file=f)

        rulesets = set()
        for speller, ruleset in public:
            override = ruleset in existing_spellers
            rulesets.add(ruleset)
            print(f"{'override ' if override else ('open ' if base_language is None else '')}val {_ruleset_code_name(ruleset)} : NumberFormatter \n get() = {speller}.{_ruleset_code_name(ruleset)}", file=f)

        if base_language is None:
            for sp in STANDARD_SPELLERS:
                if sp not in rulesets:
                    print(f"override val {_ruleset_code_name(sp)} : NumberFormatter \n get() = TODO(\"{_ruleset_code_name(sp)} is not available for {lang}\")", file=f)

        tree = {}
        for ruleset in GENERATED[lang]:
            parts = ruleset.rstrip('%').split('-')
            t = tree
            for pi, p in enumerate(parts):
                t = t.setdefault(p, {})
                if pi == len(parts) - 1:
                    t[''] = ruleset

        print(lang, file=sys.stderr)
        pprint(GENERATED[lang], stream=sys.stderr)
        pprint(tree, stream=sys.stderr)

        def cases_when(prefix, cases, cases_tree, ff):
            print(f"{prefix}when(case) {{", file=ff)
            has_plurals = False
            for case in cases:
                ruleset = cases_tree[case]
                if isinstance(ruleset, dict):
                    if set(ruleset.keys()) == {'', 'plural'}:
                        print(f"  Case.{case.capitalize()} -> if (plural) {_ruleset_code_name(ruleset['plural'][''])} else {_ruleset_code_name(ruleset[''])}", file=ff)
                        has_plurals = True
                    elif '' in ruleset:
                        if len(ruleset) > 1:
                            print(f"Unknown subcases {ruleset}", file=sys.stderr)
                        ruleset = ruleset['']
                        print(f"  Case.{case.capitalize()} -> {_ruleset_code_name(ruleset)}", file=ff)
                    else:
                        raise ValueError(ruleset)
                else:
                    if case == '':
                        case = 'nominative'
                    print(f"  Case.{case.capitalize()} -> {_ruleset_code_name(ruleset)}", file=ff)

            print(f"  else -> TODO(\"{lang} does not support $case case\")", file=ff)
            print(f"  }}", file=ff)
            return has_plurals

        if lang not in ('root',) and base_language is None:

            for t, kind_tree in tree.items():
                for kind, variants_tree in kind_tree.items():
                    code_buf = StringIO()
                    vars = []
                    with_plurals = False
                    genders = set(variants_tree.keys()) & GENDERS
                    if len(genders) <= 1:
                        cases = set(variants_tree.keys()) & CASES
                        if len(cases) <= 1:
                            pass  # TODO?
                        else:
                            vars.append(('case', 'Case'))
                            with_plurals |= cases_when("  return ", cases, variants_tree, code_buf)
                    else:
                        vars.append(('gender', 'Gender'))
                        print(f"  return when(gender) {{", file=code_buf)
                        for gender in genders:
                            gender_tree = variants_tree[gender]
                            cases = set(gender_tree) & CASES
                            if len(cases) <= 1:
                                ruleset = gender_tree['']
                                print(f"  Gender.{gender.capitalize()} -> {_ruleset_code_name(ruleset)}", file=code_buf)
                            else:
                                if ('case', 'Case') not in vars:
                                    vars.append(('case', 'Case'))
                                with_plurals |= cases_when(f"  Gender.{gender.capitalize()} -> ", cases, variants_tree[gender], code_buf)
                        print(f"  else -> TODO(\"{lang} does not support $gender gender\")", file=code_buf)
                        print(f"  }}", file=code_buf)
                    if with_plurals:
                        vars.append(('plural', 'Boolean'))
                    code = code_buf.getvalue()
                    if len(code) > 0:
                        vars_str = ','.join([f'{n}: {t}' for n, t in vars])
                        print(f"fun {_ruleset_code_name(f'{t}-{kind}')}For({vars_str}) : NumberFormatter {{", file=f)
                        print(code, file=f)
                        print(f"}}", file=f)

        print("}", file=f)
    return f"RBNF{ucamel(lang)}"


http = urllib3.PoolManager()


def load_test(lang, numbers):
    response = http.request('POST', 'https://st.unicode.org/cldr-apps/numbers.jsp',
                            headers={'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
                                     'Accept-Language': 'en-au',
                                     'Origin': 'https://st.unicode.org',
                                     'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.1.1 Safari/605.1.15',
                                     'Referer': 'https://st.unicode.org/cldr-apps/numbers.jsp',
                                     'Accept-Encoding': 'gzip, deflate, br'},
                            fields={'locale': lang,
                                    'load': 'Load+Rules',
                                    'type': 'SpelloutRules',
                                    'numbers': "".join([(f'{n[0]}-{n[1]}' if isinstance(n, tuple) else str(n)) + '\r\n' for n in numbers]),
                                    'rules': ''},
                            encode_multipart=False)
    html = elementTree.fromstring(response.data)
    table = [t for t in html.find('{http://www.w3.org/1999/xhtml}body').findall('{http://www.w3.org/1999/xhtml}table') if t.attrib['class'] == 'results'][0]
    rows = table.findall('{http://www.w3.org/1999/xhtml}tr')
    name_cell = rows[0].findall('{http://www.w3.org/1999/xhtml}th')[1]
    prefix = name_cell.text + '-'  # + name_cell.find('{http://www.w3.org/1999/xhtml}span').text.split('=')[1].strip()
    header = [h.find('{http://www.w3.org/1999/xhtml}b').text for h in rows[1]]
    results_by_number = {r[0].text: {prefix + header[ci]: v.text if 'nonsense' not in (v.attrib.get('class') or '').split(' ') else (v.text, 'nonsense') for ci, v in enumerate(r[1:])} for r in rows[2:]
                         if r.find('{http://www.w3.org/1999/xhtml}td').text != '...'}
    results_by_ruleset = {prefix + h: {n: rs[prefix + h] for n, rs in results_by_number.items()} for h in header}

    return results_by_ruleset


# Reference data https://st.unicode.org/cldr-apps/numbers.jsp
def generate_tests(lang, numbers):
    if lang == 'root':
        return
    os.makedirs(f'{TEST_SOURCE_PATH}/rbnf', exist_ok=True)
    with open('/dev/null' if DRY_RUN else f'{TEST_SOURCE_PATH}/rbnf/RBNF{ucamel(lang)}Test.kt', 'w') as file:
        results_by_ruleset = load_test(lang, numbers)
        print(f"""package {PACKAGE}.rbnf
    
    import kotlin.test.Test
    import kotlin.test.assertEquals
    """, file=file)
        print(f"class RBNF{ucamel(lang)}Test {{", file=file)
        for ruleset, testcases in results_by_ruleset.items():
            print("  @Test", file=file)
            print(f"  fun {lcamel(ruleset)}() {{", file=file)
            print(f" val fmt = RBNF{ucamel(lang)}().{lcamel(ruleset)}", file=file)
            for number, expected in testcases.items():
                if isinstance(expected, tuple):
                    print(f"// {expected[1]}", end=" ", file=file)
                    expected = expected[0]
                print(f'assertEquals("{expected}", fmt.format({number}), "{number}")', file=file)
            print("  }", file=file)
        print("}", file=file)


TEST_CASES = [(-1, 0),
              0.2,
              0.2345,
              0.02,
              0.0213,
              4.751,
              12.051,
              13.0251,
              14.02251,
              (1, 31),
              (98, 102),
              (998, 1002),
              (1998, 2002),
              (9998, 10002),
              (100000, 100002),
              (200000, 200002),
              (1000000, 1000002),
              (2000000, 2000002),
              (10000000, 10000002),
              (20000000, 20000002),
              (100000000, 100000002),
              (200000000, 200000002),
              (1000000000, 1000000002),
              (2000000000, 2000000002),
              ]


def generate_full(lang, tests=True):
    produced = generate(lang)
    if produced is not None:
        if tests:
            generate_tests(lang, TEST_CASES)
        return produced
    return None


FRAC_RULES = """
%main:
    x.0: <#,##0<[ >%%frac>];
    0.x: >%%frac>;
%%frac:
    2: 1/2;
    3: <0</3;
    4: <0</4;
    5: <0</5;
    6: <0</6;
    7: <0</7;
    8: <0</8;
    9: <0</9;
   10: <0</10;
    """


def other_checks():
    print(RBNFRule.parse("<< thousand >>"))
    print(RBNFRule.parse("=0=-ю"))
    print(RBNFRule.parse("=#,##0="))
    print(RBNFRule.parse("=#,##0==%%digits-ordinal-indicator=;"))
    print(RBNFRule.parse("<< trillion[ >>]"))
    print(RBNFRule.parse("←%spellout-cardinal-feminine← $(cardinal,one{тысяча}few{тысячи}other{тысяч})$[ →→];"))
    print(RBNFRule.parse("[←%spellout-cardinal-feminine-genitive← $(cardinal,one{целой}other{целых})$ ]→%%fractions-feminine-genitive→;"))

    # en = load(f'{CLDR_PATH}/common/rbnf/en.xml')
    #
    # # print(ru)
    # group = en[0]
    # rule = '%spellout-cardinal'
    # print(group.rulesets['%spellout-cardinal'])
    # tests = [
    #     (1, "one"),
    #     (15, "fifteen"),
    #     (20, "twenty"),
    #     (23, "twenty-three"),
    #     (73, "seventy-three"),
    #     (88, "eighty-eight"),
    #     (100, "one hundred"),
    #     (106, "one hundred six"),
    #     (127, "one hundred twenty-seven"),
    #     (200, "two hundred"),
    #     (579, "five hundred seventy-nine"),
    #     (1000, "one thousand"),
    #     (2000, "two thousand"),
    #     (3004, "three thousand four"),
    #     (4567, "four thousand five hundred sixty-seven"),
    #     (15943, "fifteen thousand nine hundred forty-three"),
    #     (2345678, "two million three hundred forty-five thousand six hundred seventy-eight"),
    #     (-36, "minus thirty-six"),
    #     (0.2, "zero point two"),
    #     (234.567, "two hundred thirty-four point five six seven"),
    # ]
    #
    # for value, expected in tests:
    #     actual = group.rulesets[rule].process(value, False)
    #     if actual != expected:
    #         print(f"{value} => {actual} != {expected}")

    ru = load(f'{CLDR_PATH}/common/rbnf/ru.xml')
    base_language, default_language, groups = ru
    group = groups[0]
    rule = '%spellout-ordinal-feminine-accusative'
    run(group, rule, 1)
    run(group, rule, 12)
    run(group, rule, 75)
    run(group, rule, 1000000)
    run(group, rule, 5116)
    run(group, rule, 0.5)
    run(group, rule, 0.5123)
    run(group, rule, 1.005123)

    exit(0)

    rule = '%spellout-numbering'
    for fn in sorted(os.listdir(BASE_PATH)):
        if fn.endswith('.xml'):

            lang = load(os.path.join(BASE_PATH, fn))
            if lang is not None:

                group = lang[0]
                if rule in group.rulesets:
                    print(os.path.splitext(fn)[0], end=" ")
                    run(group, rule, 3.14)

    # run_test(FRAC_RULES, "123", 123)
    # run_test(FRAC_RULES, "123", .000123)
    pl = load(f'{CLDR_PATH}/common/rbnf/pl.xml')
    group = pl[0]
    rule = '%spellout-cardinal-masculine'
    run(group, rule, 234.567)  # dwieście trzydzieści cztery przecinek pięćset sześćdziesiąt siedem
    run(group, rule, 234.056)  # dwieście trzydzieści cztery przecinek pięćset sześćdziesiąt siedem

    root = load(f'{CLDR_PATH}/common/rbnf/root.xml')
    group = root[0]
    rule = '%roman-upper'
    run(group, rule, 1)
    run(group, rule, 8)
    run(group, rule, 108)

    fi = load(f'{CLDR_PATH}/common/rbnf/fi.xml')
    print("\n".join([rs[1:] for rs in fi[0].rulesets.keys() if not rs.startswith("%%")]))

    ta = load(f'{CLDR_PATH}/common/rbnf/ta.xml')
    group = ta[0]
    rule = '%spellout-numbering'
    run(group, rule, 1)
    run(group, rule, 8)
    run(group, rule, 234.0567)
    in_ = load(f'{CLDR_PATH}/common/rbnf/en_IN.xml')
    group = in_[0]
    rule = '%spellout-cardinal-verbose'
    run(group, rule, 1)
    run(group, rule, 8)
    run(group, rule, 234.0567)

    for f in ALL_FORMATS:
        fmt = ICUDecimalFormat.parse(f)
        print(f"{f} => {fmt}: {fmt.format(123456789.13123123)}, {fmt.format(0.1)}, {fmt.format(2.12)}, {fmt.format(0)}")


def print_all_cases():
    public = set()
    for lang, gens in GENERATED.items():
        public |= set(gens)

    tree = {}

    for ruleset in public:
        parts = ruleset.rstrip('%').split('-')
        t = tree
        for pi, p in enumerate(parts):
            t = t.setdefault(p, {})
            if pi == len(parts) - 1:
                t[''] = True

    pprint(tree)


def target_paths():
    return [SOURCE_PATH + "/rbnf", TEST_SOURCE_PATH + "/rbnf"]


def main():
    # generate('es')
    # exit(0)
    for fn in sorted(os.listdir(BASE_PATH)):
        if fn.endswith('.xml'):
            lang, ext = os.path.splitext(fn)
            generate_full(lang, tests=False)
    if not DRY_RUN:
        subprocess.call(['/usr/local/bin/ktlint', '-F'], cwd=SOURCE_PATH)
        subprocess.call(['/usr/local/bin/ktlint', '-F'], cwd=TEST_SOURCE_PATH)
    print_all_cases()
    exit(0)


if __name__ == '__main__':
    other_checks()
