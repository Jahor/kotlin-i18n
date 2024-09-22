import os
import re
import shutil
import subprocess
import sys
import xml.etree.ElementTree as ET

import rbnf
from cldr_utils import ucamel, safe, lcamel, language_identifier, quote_str

CASES = ["nominative", "genitive", "dative", "accusative", "instrumental", "prepositional",
         "vocative", "locative", "terminative", "translative"]
PLURALS = ["one", 'few', 'many', 'other']
REFERENCE_LANGUAGE = 'en'
LANGUAGES = {'root', 'en', 'hu', 'ru', 'nl', 'es', 'fr', 'hr', 'pt', 'zh', 'id', 'tr', 'de', 'jp', 'be', 'uk', 'pl'}
LANGUAGES_EXCLUDED = {'ml'}
CLDR_VERSION = "45.0"
CLDR_PATH = f'/Users/egorleonenko/Downloads/cldr-common-{CLDR_VERSION}'
BASE_PATH = f'{CLDR_PATH}/common/main/'
UNITS_PATH = f'{CLDR_PATH}/common/supplemental/units.xml'
PLURALS_PATH = f'{CLDR_PATH}/common/supplemental/plurals.xml'
METADATA_PATH = f'{CLDR_PATH}/common/supplemental/supplementalMetadata.xml'
TARGET_PATH = '../src/commonMain/kotlin/info/leonenko/i18n'
PACKAGE = "info.leonenko.i18n"

GENERATED = {}


def download_clrd():
    simple_version = CLDR_VERSION.remove_suffix(".0")
    url = f"https://unicode.org/Public/cldr/{simple_version}/cld-common-{CLDR_VERSION}"


def cldr_units(cldr, plurals):
    units = cldr.find('units')
    if units is None:
        return {}, {}
    by_section = {}
    compound_by_pattern = {}
    for unitLength in units.findall('unitLength'):
        unit_length = unitLength.attrib['type']
        for unit in unitLength.findall('unit'):
            # if unit.attrib['type'].startswith('duration-'):
            unit_section, unit_name = unit.attrib['type'].split('-', 1)
            unit_section = {'concentr': 'concentration'}.get(unit_section, unit_section)

            gender = unit.find('gender')
            if gender is not None:
                gender = gender.text
            by_case = {}
            for pattern in unit.findall('unitPattern'):
                by_case.setdefault(pattern.attrib.get('case') or CASES[0], {})[pattern.attrib['count']] = pattern.text
                plurals.add(pattern.attrib['count'])
            for c, forms in by_case.items():
                if 'other' not in forms:
                    forms['other'] = forms.get('many', forms.get('few', forms.get('one')))
            if len(by_case) > 0:
                by_section.setdefault(unit_section, {}).setdefault(unit_name, {})[unit_length] = (gender, by_case)
        for compoundUnit in unitLength.findall('compoundUnit'):
            unit_prefix_pattern = compoundUnit.find("unitPrefixPattern")
            if unit_prefix_pattern is not None:
                compound_by_pattern[unit_prefix_pattern.text] = compoundUnit.attrib['type']

    return by_section, compound_by_pattern


def language_def(cldr, all_units, plurals):
    # TODO merge with parent
    identity = cldr.find('identity')
    lang, script, territory, lang_code, lang_name = language_identifier(identity)
    lang_name_capitalized = lang_name[0].capitalize() + lang_name[1:]
    language_units_imports(cldr, all_units)
    # print(f"import {PACKAGE}.units.MeasurementUnitLanguage")
    print(f"import {PACKAGE}.Language")
    print(f"import {PACKAGE}.PluralFormChooser")
    # print(f"import {PACKAGE}.EllipsisLanguage")
    print(f"import {PACKAGE}.EllipsisFormatter")
    # print(f"import {PACKAGE}.ListLanguage")
    print(f"import {PACKAGE}.PatternedListFormatter")
    print(f"import {PACKAGE}.ListFormatter")
    print(f"import {PACKAGE}.MultiLengthPatternedListFormatter")
    print(f"import {PACKAGE}.MultiLengthGenderedPattern")
    # print(f"import {PACKAGE}.NumberLanguage")
    print(f"import {PACKAGE}.NumberSymbols")
    print(f"import {PACKAGE}.GenderedPattern")
    print(f"import {PACKAGE}.PluralPattern")
    print(f"import {PACKAGE}.Plurals")
    print(f"import {PACKAGE}.Gender")
    print(f"import {PACKAGE}.Cases")
    print(f"import {PACKAGE}.RBNF")

    language_rbnf = rbnf.generate_full(lang_code)
    if language_rbnf is not None:
        print(f"import {PACKAGE}.rbnf.{language_rbnf}")

    if script is not None or territory is not None:
        if script is not None and territory is not None:
            langs = GENERATED[lang]
            if script in langs:
                if None in langs[script]:
                    parent_lang_name = langs[script][None]
                else:
                    parent_lang_name = langs[None][None]
            else:
                parent_lang_name = langs[None][None]
        else:
            parent_lang_name = GENERATED[lang][None][None]
        parent_lang_name_capitalized = parent_lang_name[0].capitalize() + parent_lang_name[1:]
        print(f"val Language.Companion.{safe(lang_name)}")
        print(f"   get() = language{lang_name_capitalized}")
        print(f"private val language{lang_name_capitalized} = Language{lang_name_capitalized}()")
        print(f"{'open ' if territory is None else ''}class Language{lang_name_capitalized} internal constructor() : Language{parent_lang_name_capitalized}() {{")
        print(f" override val code : String = \"{lang_code}\"")
        print(f" override val script : String{'?' if script is None else ''} = {quote_str(script) if script is not None else 'null'}")
        print(f" override val territory : String{'?' if territory is None else ''} = {quote_str(territory) if territory is not None else 'null'}")
        if language_rbnf is not None:
            print(f" override val rbnf : {language_rbnf}\n get() = {language_rbnf}(this)")

        language_plurals(plurals, lang_code, optional=True)

        language_ellipsis(cldr, territory is None, base=f'Language{parent_lang_name_capitalized}.EllipsisLanguage()')
        language_list(cldr, lang_name, territory is None, base=f'Language{parent_lang_name_capitalized}.ListLanguage(ellipsis)')
        language_units(cldr, all_units, set(), lang_name, territory is None, base=f'Language{parent_lang_name_capitalized}.MeasurementUnitLanguage()')
        language_numbers(cldr, territory is None, base=f'Language{parent_lang_name_capitalized}.NumberLanguage()')
        print("}")
        GENERATED.setdefault(lang, {}).setdefault(script, {})[territory] = lang_name_capitalized
        return lang_code, lang_name
    else:
        unit_plurals = set()
        cldr_units(cldr, unit_plurals)
        print(f"val Language.Companion.{safe(lang_name)}")
        print(f"   get() = language{lang_name_capitalized}")
        print(f"private val language{lang_name_capitalized} = Language{lang_name_capitalized}()")
        print(f"open class Language{lang_name_capitalized} internal constructor() : Language {{")
        print(f" override val code : String = \"{lang_code}\"")
        print(f" override val language : String = \"{lang}\"")
        print(f" override val script : String? = {quote_str(script) if script is not None else 'null'}")
        print(f" override val territory : String? = {quote_str(territory) if territory is not None else 'null'}")
        if language_rbnf is not None:
            print(f" override val rbnf : {language_rbnf}\n get() = {language_rbnf}(this)")
        else:
            print(f" override val rbnf : RBNF\n get() = TODO(\"Spell Out is not available for {lang_name}\")")

        if not language_plurals(plurals, lang, optional=False):
            if unit_plurals == {'other'} or len(unit_plurals) == 0:
                print("override val pluralFormChooser = PluralFormChooser.None")
            elif unit_plurals == {'other', 'one'}:
                print("override val pluralFormChooser = PluralFormChooser.Simple")
            else:
                print("override val pluralFormChooser : PluralFormChooser")
                print(f"     get() = TODO(\"PluralFormChooser is not implemented yet for {lang_name}\")  // {','.join(unit_plurals)}")

        language_ellipsis(cldr, True)
        language_list(cldr, lang_name, True)
        language_units(cldr, all_units, set(), lang_name, True)
        language_numbers(cldr, True)
        print("}")
        GENERATED.setdefault(lang, {}).setdefault(script, {})[territory] = lang_name_capitalized
        return lang_code, lang_name


def language_units_imports(cldr, all_units):
    for section, measurements in all_units.items():
        # print(f"import info.leonenko.starterkit.phrases.localization.{section}.*")
        for measurement, units in measurements.items():
            for unit in units.keys():
                if unit == measurement:
                    unit = 'a-' + unit
                # print(f"import info.leonenko.starterkit.phrases.localization.{section}.{ucamel(unit)}")


def language_units(cldr, all_units, plurals, lang_name, open, base=None):
    by_section, _ = cldr_units(cldr, plurals)

    # if len(by_section) > 0 or base is None: # Prevents inheriting in 3-step
    print(f"{'open ' if open else ''} class MeasurementUnitLanguage internal constructor() : {base or f'{PACKAGE}.units.MeasurementUnitLanguage'} {{")
    for section, measurements in all_units.items():
        for measurement, units in measurements.items():
            for unit in units.keys():
                if unit == measurement:
                    unit = 'a-' + unit
                # unit_class = f"{ucamel(unit)}" #f"{ucamel(section)}.{ucamel(unit)}"
                lengths = by_section.get(section, {}).get(unit)
                if lengths is None:
                    if base is None:
                        print(f"""override val {lcamel(section + '-' + unit)}: MultiLengthGenderedPattern  
                            get() = TODO(\"{ucamel(unit)} formatter is not implemented yet for {lang_name}\")""")
                else:
                    print(f"override val {lcamel(section + '-' + unit)} get() = MultiLengthGenderedPattern(")
                    if base is not None:
                        print(f"base = super.{lcamel(section + '-' + unit)},")
                    for length, (gender, counts) in lengths.items():
                        print(f"      {lcamel(length)} = GenderedPattern(")
                        if gender is not None:
                            print(f"       gender = Gender.{gender.capitalize()},")
                        print(f"         cases = Cases(")
                        for count, cases in counts.items():
                            print(f"        {lcamel(count)} = PluralPattern(Plurals(")
                            for case, pattern in cases.items():
                                print(f"          {lcamel(case)} = {quote_str(pattern)},")
                            print(f"        )),")
                        print(f"      )),")
                    print(f"    )")

    print("  }")
    print(f"override val measurementUnits : MeasurementUnitLanguage \n get() = MeasurementUnitLanguage()")
    return plurals


def generic_units(units_info, constants):
    os.makedirs(os.path.join(TARGET_PATH, 'units'), exist_ok=True)
    with open(os.path.join(TARGET_PATH, 'units', 'MeasurementUnitLanguage.kt'), 'w') as uf:
        print("package " + PACKAGE + ".units", file=uf)
        print("import " + PACKAGE + ".MultiLengthGenderedPattern", file=uf)

        print("interface MeasurementUnitLanguage {", file=uf)
        for section, measurements in units_info.items():
            for measurement, units in measurements.items():
                for unit in units.keys():
                    if unit == measurement:
                        unit = 'a-' + unit
                    print(f"val {lcamel(section + '-' + unit)}: MultiLengthGenderedPattern", file=uf)
        print("}", file=uf)

    os.makedirs(os.path.join(TARGET_PATH, 'units', 'constants'), exist_ok=True)
    with open(os.path.join(TARGET_PATH, 'units', 'constants', 'Constants.kt'), 'w') as uf:
        print("package " + PACKAGE + ".units.constants", file=uf)
        for c, v in constants.items():
            if isinstance(v, int):
                print(f"internal const val {c} = {v}L", file=uf)
            else:
                print(f"internal const val {c} = {v}", file=uf)

    for section, measurements in units_info.items():
        os.makedirs(os.path.join(TARGET_PATH, 'units', section), exist_ok=True)
        for measurement, units in measurements.items():
            with open(os.path.join(TARGET_PATH, 'units', section, ucamel(measurement) + '.kt'), 'w') as uf:
                print("package " + PACKAGE + ".units." + section, file=uf)
                # print("import kotlin.jvm.JvmInline", file=uf)
                print("import kotlin.math.truncate", file=uf)
                print("import kotlin.math.round", file=uf)
                print("import kotlin.math.ceil", file=uf)
                print("import kotlin.math.floor", file=uf)
                print("import kotlin.math.abs", file=uf)
                print("import kotlinx.serialization.ExperimentalSerializationApi", file=uf)
                print("import kotlinx.serialization.KSerializer", file=uf)
                print("import kotlinx.serialization.Serializable", file=uf)
                print("import kotlinx.serialization.SerializationException", file=uf)
                print("import kotlinx.serialization.builtins.MapSerializer", file=uf)
                print("import kotlinx.serialization.builtins.serializer", file=uf)
                print("import kotlinx.serialization.descriptors.SerialDescriptor", file=uf)
                print("import kotlinx.serialization.encoding.Decoder", file=uf)
                print("import kotlinx.serialization.encoding.Encoder", file=uf)
                print(f"import {PACKAGE}.units.MeasurementUnit", file=uf)
                print(f"import {PACKAGE}.units.MeasurementUnitValue", file=uf)
                print(f"import {PACKAGE}.units.MeasurementUnitLanguage", file=uf)
                print(f"import {PACKAGE}.units.toMeasurementUnitValue", file=uf)
                # for c in constants.keys():
                #     print(f"import {PACKAGE}.units.constants." + c, file=uf)

                base_unit = None
                base_unit_found = None
                for unit, unit_info in units.items():
                    if unit_info[0] is None:
                        base_unit_found = True
                        base_unit = unit
                    elif unit_info[0] != 'nothing':
                        base_unit = unit_info[0]

                if not base_unit_found:
                    print(f"import {PACKAGE}.units.{base_unit[0]}.{ucamel(measurement)}", file=uf)
                    print(f"import {PACKAGE}.units.{base_unit[0]}.{ucamel(base_unit[-1])}", file=uf)
                    base_unit = base_unit[-1]

                if base_unit == measurement:
                    original_base_unit = base_unit
                    base_unit = 'a-' + base_unit
                else:
                    original_base_unit = None

                # for c, v in constants.items():
                #     print(f"private const val {c} = {v}", file=uf)

                if base_unit_found:

                    print(f"  @Serializable({ucamel(measurement)}.Serializer::class)", file=uf)
                    print(f"  interface {ucamel(measurement)} : MeasurementUnit, Comparable<{ucamel(measurement)}> {{", file=uf)
                    print(f"    override val measurement: String get() = \"{measurement}\"", file=uf)


                    decoders = "\n".join([f"\"{unit}\" -> {ucamel(('a-' if unit == measurement else '') + unit)}(value)" for unit in units.keys()])
                    print(f"""
                    class Serializer: CustomSerializer() 
                    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> {ucamel(measurement)}> = emptyMap()): KSerializer<{ucamel(measurement)}> {{
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("{ucamel(measurement)}", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): {ucamel(measurement)} {{
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected {ucamel(measurement)} unit as a key. None found")
            val value = map[key]!!
            return when(key) {{
                {decoders}
                else -> decoders[key]?.let {{ it(value) }} ?: throw SerializationException("Unknown {ucamel(measurement)} unit $key")
            }}
        }}

        override fun serialize(encoder: Encoder, value: {ucamel(measurement)}) {{
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }}

    }}
                    """, file=uf)

                    for unit, unit_info in units.items():
                        if unit_info[0] is not None:
                            if unit_info[2] is None and unit_info[1] is None:
                                # Works for Generic temperature, replace with TODO("Not supported") for others
                                print(f"fun to{ucamel(unit)}() : {ucamel(unit)} = {ucamel(unit)}(value)", file=uf)
                            else:
                                print(f"fun to{ucamel(unit)}() : {ucamel(unit)} {{", file=uf)
                                print(f"   return {ucamel(unit)}.from{ucamel(base_unit)}(to{ucamel(base_unit)}())", file=uf)
                                # print(f"   return {ucamel(unit)}((to{ucamel(base_unit)}().value - {unit_info[2]}) / ({unit_info[1]}))", file=uf)
                                print("}", file=uf)
                        else:
                            print(f"fun to{ucamel(base_unit)}() : {ucamel(base_unit)}", file=uf)
                            print(f"override fun getBaseUnitValue() = to{ucamel(base_unit)}().value", file=uf)

                    print(f"""
                    companion object {{
                                val ZERO: {ucamel(measurement)} = {ucamel(base_unit)}(0.0)
                                
                            }}
                    override operator fun compareTo(other: {ucamel(measurement)}): Int = this.to{ucamel(base_unit)}().value.compareTo(other.to{ucamel(base_unit)}().value)
                    
                    operator fun plus(other: {ucamel(measurement)}): {ucamel(base_unit)} = this.to{ucamel(base_unit)}() + other.to{ucamel(base_unit)}()                    
                    operator fun minus(other: {ucamel(measurement)}): {ucamel(base_unit)} = this.to{ucamel(base_unit)}() - other.to{ucamel(base_unit)}()
                    operator fun div(other: {ucamel(measurement)}): Double = this.to{ucamel(base_unit)}().value / other.to{ucamel(base_unit)}().value
                    operator fun unaryMinus(): {ucamel(measurement)}
                    operator fun unaryPlus(): {ucamel(measurement)}
                    operator fun inc(): {ucamel(measurement)}
                    operator fun dec(): {ucamel(measurement)} 
                    
                    fun round() : {ucamel(measurement)}
                    fun truncate() : {ucamel(measurement)}
                    fun floor() : {ucamel(measurement)}
                    fun ceil() : {ucamel(measurement)}
                    fun abs() : {ucamel(measurement)}
                    """, file=uf)

                    for t in ["Byte", "Short", "Int", "Long", "Float", "Double"]:
                        print(f"""                               
                               operator fun times(other: kotlin.{t}): {ucamel(measurement)}
                               operator fun div(other: kotlin.{t}): {ucamel(measurement)}
                               operator fun rem(other: kotlin.{t}): {ucamel(measurement)}
                            """, file=uf)

                    print("  }", file=uf)
                else:
                    print(f"fun {ucamel(measurement)}.to{ucamel(unit)}() : {ucamel(unit)} {{", file=uf)
                    print(f"   return {ucamel(unit)}((to{ucamel(unit_info[0][-1])}().value - {unit_info[2]}) / ({unit_info[1]}))", file=uf)
                    print("}", file=uf)

                for unit, unit_info in units.items():
                    original_unit = unit
                    if unit == original_base_unit:
                        unit = base_unit
                    print(f"""
                        data class {ucamel(unit)}(override val value:MeasurementUnitValue): {ucamel(measurement)} {{
                        
                        override val unitName: String = "{original_unit}"
                        
                        override fun formatters(language: MeasurementUnitLanguage) = language.{lcamel(section + '-' + unit)}""", file=uf)
                    if unit_info[0] is None:
                        print(f"override fun to{ucamel(unit)}() = this", file=uf)
                        from_base_unit = f"fun from{ucamel(unit)}(value: {ucamel(unit)}) = value"
                    else:
                        if unit_info[2] is None and unit_info[1] is None:
                            print(f"override fun to{ucamel(base_unit)}(): {ucamel(base_unit)} = TODO(\"Conversion of {ucamel(unit)} to {ucamel(base_unit)} is not supported\")", file=uf)
                            from_base_unit = f"fun from{ucamel(base_unit)}(value: {ucamel(base_unit)}): {ucamel(unit)} = TODO(\"Conversion of {ucamel(base_unit)} to {ucamel(unit)} is not supported\")"
                        else:
                            print(f"override fun to{ucamel(base_unit)}() = {ucamel(base_unit)}(value * ({unit_info[1]}) + {unit_info[2]})", file=uf)
                            from_base_unit = f"fun from{ucamel(base_unit)}(value: {ucamel(base_unit)}) = {ucamel(unit)}((value.value - {unit_info[2]}) / ({unit_info[1]}))"

                    print(f"override fun toString() : String = toDebugString()", file=uf)
                    print(f"""
                            companion object {{
                                val ZERO = {ucamel(unit)}(0.0)
                                {from_base_unit}
                            }}
                           operator fun plus(other: {ucamel(unit)}): {ucamel(unit)} = {ucamel(unit)}(this.value + other.value)                    
                           operator fun minus(other: {ucamel(unit)}): {ucamel(unit)} = {ucamel(unit)}(this.value - other.value)
                           """, file=uf)
                    for t in ["Byte", "Short", "Int", "Long", "Float", "Double"]:
                        if t != "Double":
                            print(f"constructor(value: kotlin.{t}) : this(value.toMeasurementUnitValue())", file=uf)
                        print(f"""                               
                               override operator fun times(other: kotlin.{t}): {ucamel(unit)} = {ucamel(unit)}(this.value.times(other))
                               override operator fun div(other: kotlin.{t}): {ucamel(unit)} = {ucamel(unit)}(this.value.div(other))
                               override operator fun rem(other: kotlin.{t}): {ucamel(unit)} = {ucamel(unit)}(this.value.rem(other))
                            """, file=uf)
                    print(f"""
                           override operator fun unaryMinus(): {ucamel(unit)} = {ucamel(unit)}(-value)
                           override operator fun unaryPlus(): {ucamel(unit)} = this
                           override operator fun inc(): {ucamel(unit)} = {ucamel(unit)}(this.value + 1)  
                           override operator fun dec(): {ucamel(unit)} = {ucamel(unit)}(this.value - 1)  
                           
                           override fun round() : {ucamel(unit)} = {ucamel(unit)}(round(this.value))
                           override fun truncate() : {ucamel(unit)} = {ucamel(unit)}(truncate(this.value))
                           override fun floor() : {ucamel(unit)} = {ucamel(unit)}(floor(this.value))
                           override fun ceil() : {ucamel(unit)} = {ucamel(unit)}(ceil(this.value))
                           override fun abs() : {ucamel(unit)} = {ucamel(unit)}(abs(this.value))
                           
                           override fun equals(other: Any?): Boolean = when (other) {{
                               is {ucamel(unit)} -> this.value == other.value
                               is {ucamel(measurement)} -> this.to{ucamel(base_unit)}().value == other.to{ucamel(base_unit)}().value
                               else -> false
                           }}    

                           override fun hashCode(): Int = this.to{ucamel(base_unit)}().value.hashCode()
                           
                           """, file=uf)
                    print("}", file=uf)

                    for t in ["Byte", "Short", "Int", "Long", "Float", "Double"]:
                        print(f"""
                               val kotlin.{t}.{lcamel(unit)}
                                   get() = {ucamel(unit)}(this)                               
                            """, file=uf)


def language_list(cldr, lang_name, open, base=None):
    patterns = cldr.find('listPatterns')
    by_type = {}
    if patterns is not None:
        for pattern in patterns.findall('listPattern'):
            t = pattern.attrib.get('type', 'standard-long')
            if t == 'unit':
                t = 'unit-long'
            parts = {'start': '{0}, {1}', 'middle': '{0}, {1}'}  # TODO take default values from root
            for part in pattern.findall('listPatternPart'):
                pt = part.attrib['type']
                if pt == '2':
                    pt = 'two'
                parts[pt] = part.text
            if 'start' in parts and 'end' in parts:  # TODO merge with previous pattern in hierarchy?
                by_type[t] = parts

    def plf(options):
        return "PatternedListFormatter(" + ", ".join([f"{k} = {quote_str(v)}" for k, v in options.items()]) + ", ellipsis = ellipsis.wordFormatter)"

    unit = {k.split('-')[1]: v for k, v in by_type.items() if k.startswith('unit-')}
    standard = {k.split('-')[1]: v for k, v in by_type.items() if k.startswith('standard-')}
    # if len(standard) > 0 or 'or' in by_type or len(unit) > 0 or base is None:  # Prevents 3-tier inheritance if middle does not override
    print(f"{'open ' if open else ''}class ListLanguage internal constructor({'val ' if base is None else ''}ellipsis : EllipsisLanguage) : {base or f'{PACKAGE}.ListLanguage'} {{")
    if len(standard) > 0:
        print("  override val formatter: ListFormatter = MultiLengthPatternedListFormatter(")
        for t, s in standard.items():
            print(f"   {t} = {plf(s)},")
        print(")")
    elif base is None:
        print(f"""  override val formatter: ListFormatter 
                        get() = TODO(\"ListFormatter is not yet implemented for {lang_name}\")""")

    if 'or' in by_type:
        print(f"  override val orFormatter: ListFormatter = MultiLengthPatternedListFormatter(long = {plf(by_type['or'])})")
    elif base is None:
        print(f"""  override val orFormatter: ListFormatter  
                        get() = TODO(\"Or ListFormatter is not yet implemented for {lang_name}\")""")

    if len(unit) > 0:
        print("  override val unitFormatter: ListFormatter = MultiLengthPatternedListFormatter(")
        for t, u in unit.items():
            print(f"   {t} = {plf(u)},")
        print(")")
    elif base is None and len(standard) > 0:
        print("  override val unitFormatter: ListFormatter = formatter")
    elif base is None:
        print(f"""  override val unitFormatter: ListFormatter  
                        get() = TODO(\"Unit ListFormatter is not yet implemented for {lang_name}\")""")
    print("}")

    print("override val list : ListLanguage\n get() = ListLanguage(ellipsis) ")


def language_ellipsis(cldr, open, base=None):
    characters = cldr.find('characters')
    by_type = {}
    if characters is not None:
        for pattern in characters.findall('ellipsis'):
            by_type[pattern.attrib['type']] = pattern.text
    # elif base is not None:
    #     return

    word = {k.split('-')[1]: v for k, v in by_type.items() if k.startswith('word-')}
    standard = {k: v for k, v in by_type.items() if not k.startswith('word-')}

    print(f"{'open ' if open else ''}class EllipsisLanguage internal constructor() : {base or f'{PACKAGE}.EllipsisLanguage'} {{")
    if len(standard) == 3:
        print("  override val formatter: EllipsisFormatter = EllipsisFormatter(")
        for t, s in standard.items():
            print(f"   {t} = {quote_str(s)},")
        print(")")
    elif base is None:
        print("""  override val formatter = EllipsisFormatter.Default """)

    if len(word) == 3:
        print("  override val wordFormatter: EllipsisFormatter = EllipsisFormatter(")
        for t, u in word.items():
            print(f"   {t} = {quote_str(u)},")
        print(")")
    elif base is None:
        print("""  override val wordFormatter = EllipsisFormatter.WordDefault""")
    print("}")

    print("override val ellipsis : EllipsisLanguage\n get() = EllipsisLanguage()")


def language_plurals(cldr, language, optional):
    plurals = cldr.find('plurals')
    for pluralRules in plurals.findall("pluralRules"):
        locales = pluralRules.attrib['locales'].split(' ')
        if language in locales:
            rules = {}

            for pluralRule in pluralRules.findall('pluralRule'):
                if pluralRule.attrib['count'] != 'other':
                    rules[pluralRule.attrib['count']] = convert_plural_rule(pluralRule.text)

            if len(rules) > 0:
                print("override val pluralFormChooser = PluralFormChooser.Expression(")
                for c, rule in rules.items():
                    print(f"{c} = {{{rule}}},")
                print(")")
                return True

            return optional

    return optional


def find_number_symbols(cldr):
    numbers = cldr.find("numbers")
    if numbers is None:
        return None
    latn = [x for x in numbers.findall('symbols') if x.attrib.get('numberSystem') == 'latn']
    if len(latn) > 0:
        return latn[0]
    return None


def language_numbers(cldr, open, base=None):
    symbols = find_number_symbols(cldr)
    # if symbols is not None or base is None:  # Prevents 3 -tier iheritance if middle does not override
    print(f"{'open ' if open else ''}class NumberLanguage internal constructor() : {base or f'{PACKAGE}.NumberLanguage'} {{")
    if symbols is not None:
        if base is None:
            print("""  override val symbols = NumberSymbols(""")
        else:
            print(f"  override val symbols = {base}.symbols.copy(")

        for el in symbols:
            if el.attrib.get("alt") is None:
                print(f"    {el.tag}Symbol = \"{el.text}\",")
        print("    )")

    elif base is None:
        print("override val symbols : NumberSymbols")
        print("   get() = LanguageRoot().numbers.symbols")  # TODO(\"Symbols are not supported\")

    print("}")
    print("override val numbers : NumberLanguage\n get() = NumberLanguage() ")


def _include(language):
    if LANGUAGES is None:
        for l in LANGUAGES_EXCLUDED:
            if l == language or language.startswith(l + "_"):
                return False
        return True
    for l in LANGUAGES:
        if l == language or language.startswith(l + "_"):
            return True
    return False


def main():
    with open(os.path.join(BASE_PATH, f'{REFERENCE_LANGUAGE}.xml'), 'r') as f:
        d = ET.fromstring(f.read())
    all_units, compound_units = cldr_units(d, set())

    with open(PLURALS_PATH, 'r') as f:
        plurals = ET.fromstring(f.read())

    with open(UNITS_PATH, 'r') as f:
        d = ET.fromstring(f.read())

    with open(METADATA_PATH, 'r') as f:
        metadata = ET.fromstring(f.read())

    units_info, constants = cldr_units2(d, all_units, compound_units)

    # pprint(units_info, width=100)
    generic_units(units_info, constants)

    os.makedirs(os.path.join(TARGET_PATH, 'languages'), exist_ok=True)

    orig_stdout = sys.stdout
    completed_languages = []
    for fn in sorted(os.listdir(BASE_PATH)):
        language, ext = os.path.splitext(fn)
        if not _include(language):
            print(language, "Skip", file=sys.stderr)
            continue
        print(language, end="  ", file=sys.stderr)
        if ext == '.xml':
            with open(os.path.join(BASE_PATH, fn), 'r') as f:
                d = ET.fromstring(f.read())
            if d.tag != 'ldml':
                continue

            with open(os.path.join(TARGET_PATH, 'languages', f'{language}.kt'), 'w') as f:
                sys.stdout = f
                print("package " + PACKAGE + ".languages")
                lang = language_def(d, units_info, plurals)
            sys.stdout = orig_stdout
            if lang is None:
                print("FAILED", file=sys.stderr)
                os.remove(os.path.join(TARGET_PATH, 'languages', f'{language}.kt'))
            else:
                shutil.move(os.path.join(TARGET_PATH, 'languages', f'{language}.kt'), os.path.join(TARGET_PATH, 'languages', f'Language{lang[1][0].capitalize()}{lang[1][1:]}.kt'))
                print("OK ", file=sys.stderr)

                completed_languages.append(lang)

    with open(os.path.join(TARGET_PATH, 'languages', f'Supported.kt'), 'w') as f:
        print(f"package {PACKAGE}.languages", file=f)
        print(f'import {PACKAGE}.Language', file=f)
        print("""val Language.Companion.supported  : Map<String, Language>
  get() = supportedLanguages""", file=f)
        print("private val supportedLanguages = mapOf(", file=f)
        for code, name in completed_languages:
            print(f"  \"{code}\" to Language.{safe(name)}, ", file=f)
        print(")", file=f)

    aliases = metadata.find('metadata').find('alias')
    language_aliases = aliases.findall('languageAlias')
    script_aliases = aliases.findall('scriptAlias')
    territory_aliases = aliases.findall('territoryAlias')

    with open(os.path.join(TARGET_PATH, 'languages', f'Aliases.kt'), 'w') as f:
        print(f"package {PACKAGE}.languages", file=f)
        print(f'import {PACKAGE}.Language', file=f)
        print("""val Language.Companion.languageAliases  : Map<String, String>
  get() = allLanguageAliases""", file=f)
        print("private val allLanguageAliases = mapOf(", file=f)
        for alias in language_aliases:
            print(f"  \"{alias.attrib['type']}\" to \"{alias.attrib['replacement']}\", ", file=f)
        print(")", file=f)

        print("""val Language.Companion.scriptAliases  : Map<String, String>
  get() = scriptAliases""", file=f)
        print("private val allScriptAliases = mapOf(", file=f)
        for alias in script_aliases:
            print(f"  \"{alias.attrib['type']}\" to \"{alias.attrib['replacement']}\", ", file=f)
        print(")", file=f)

        print("""val Language.Companion.territoryAliases  : Map<String, String>
  get() = allTerritoryAliases""", file=f)
        print("private val allTerritoryAliases = mapOf(", file=f)
        for alias in territory_aliases:
            print(f"  \"{alias.attrib['type']}\" to \"{alias.attrib['replacement']}\", ", file=f)
        print(")", file=f)

    sys.stdout = orig_stdout

    rbnf.print_all_cases()

    all_targets = set(target_paths()) | set(rbnf.target_paths())
    # for t in all_targets:
    #     subprocess.call(['/usr/local/bin/ktlint', '-F'], cwd=t)


def _get_multiprefix(name, compound_units):
    for pattern, factor in compound_units.items():
        m = re.match('^' + pattern.replace("{0}", "(.*)") + '$', name)
        if m is not None:
            fm = re.match(r'^10p(-?\d+)$', factor)
            if fm is not None:
                factor = "1e" + fm.group(1)
            else:
                fm = re.match(r'^1024p(-?\d+)$', factor)
                if fm is not None:
                    factor = pow(1024, fm.group(1))
            return m[1], '*(' + factor + ')'
    return None, ''


def _replace_separated(name1, name2, replacement):
    if f"-{name2}-" in name1:
        return name1.replace(f"-{name2}-", "-" + replacement + "-").replace('--', '-').strip('-'), True
    if name1.startswith(f"{name2}-"):
        return name1.replace(f"{name2}-", replacement + "-").replace('--', '-').strip('-'), True
    if name1.endswith(f"-{name2}"):
        return name1.replace(f"-{name2}", "-" + replacement).replace('--', '-').strip('-'), True
    if name1 == name2:
        return replacement, True
    return name1, False


def __multiply_names(name1, name2):
    if '-per-' in name1:
        n11, n12 = name1.split('-per-')
        if '-per-' in name2:
            n21, n22 = name1.split('-per-')
            r11, r12 = __divide_names(n11, n22)
            r21, r22 = __divide_names(n21, n12)
            return _multiply_names(r11, r21), _multiply_names(r12, r22)
        else:
            r1, r2 = __divide_names(name2, n12)
            return _multiply_names(r1, n11), r2
    elif '-per-' in name2:
        n21, n22 = name2.split('-per-')
        r1, r2 = __divide_names(name1, n22)
        return _multiply_names(r1, n21), r2

    name1, replaced = _replace_separated(name1, f"square-{name2}", f"cubic-{name2}")
    if replaced:
        return name1, ""
    name1, replaced = _replace_separated(name1, f"{name2}", f"square-{name2}")
    if replaced:
        return name1, ""

    name2, replaced = _replace_separated(name2, f"square-{name1}", f"cubic-{name1}")
    if replaced:
        return name2, ""
    name2, replaced = _replace_separated(name2, f"{name1}", f"square-{name1}")
    if replaced:
        return name2, ""

    return f'{name1}-{name2}', ""


def _multiply_names(name1, name2):
    if name1 == 'item' or name1 == '':
        return name2
    if name2 == 'item' or name2 == '':
        return name1
    n1, n2 = __multiply_names(name1, name2)
    if n1 == "":
        n1 = 'item'
    if n2 == 'item':
        n2 = ''
    if n2 == "":
        r = n1
    else:
        r = n1 + '-per-' + n2
    # print(f"MUL {name1} * {name2} -> {r}", file=sys.stderr)
    return r


def __divide_names(name1, name2):
    if '-per-' in name1:
        n11, n12 = name1.split('-per-')
        if '-per-' in name2:
            n21, n22 = name1.split('-per-')
            r11, r12 = __divide_names(n11, n12)
            r21, r22 = __divide_names(n22, n12)
            return _multiply_names(r11, r21), _multiply_names(r12, r22)
        else:
            r1, r2 = __divide_names(n11, name2)
            return r1, _multiply_names(r2, n12)
    elif '-per-' in name2:
        n21, n22 = name2.split('-per-')
        r1, r2 = __divide_names(name1, n21)
        return _multiply_names(r1, n22), r2

    name1, replaced = _replace_separated(name1, f"cubic-{name2}", f"square-{name2}")
    if replaced:
        return name1, ''
    name1, replaced = _replace_separated(name1, f"square-{name2}", f"{name2}")
    if replaced:
        return name1, ''
    name1, replaced = _replace_separated(name1, name2, "")
    if replaced:
        return name1, ''
    if name2.startswith('cubic-'):
        n2 = name2[6:]
        if n2 == name1:
            return 'item', 'square-' + n2
        name1, replaced = _replace_separated(name1, f"square-{n2}", f"{n2}")
        if replaced:
            return name1, n2
        name1, replaced = _replace_separated(name1, n2, f"")
        if replaced:
            return name1, 'square-' + n2

    if name2.startswith('square-'):
        n2 = name2[7:]
        if n2 == name1:
            return 'item', n2
        name1, replaced = _replace_separated(name1, n2, f"")
        if replaced:
            return name1, n2

    name2, replaced = _replace_separated(name2, f"cubic-{name1}", f"square-{name1}")
    if replaced:
        return '', name2
    name2, replaced = _replace_separated(name2, f"square-{name1}", f"{name1}")
    if replaced:
        return '', name2
    name2, replaced = _replace_separated(name2, name1, "")
    if replaced:
        return '', name2

    return name1, name2


def _divide_names(name1, name2):
    if name2 == 'item':
        return name1
    n1, n2 = __divide_names(name1, name2)
    if n1 == "":
        n1 = 'item'
    if n2 == 'item':
        n2 = ''
    if n2 == "":
        r = n1
    else:
        r = n1 + '-per-' + n2
    # print(f"DIV {name1} / {name2} -> {r}", file=sys.stderr)
    return r


def cldr_units2(cldr, all_units, compound_units):
    quantities = cldr.find('unitQuantities')
    by_measurement = {}
    by_base_unit = {}
    by_name = {}
    for unitLength in quantities.findall('unitQuantity'):
        quantity = unitLength.attrib['quantity']
        baseUnit = unitLength.attrib['baseUnit']
        by_measurement.setdefault(quantity, []).append(baseUnit)
        by_base_unit[baseUnit] = quantity
        by_name[baseUnit] = (quantity, None, 1, 0)

    unitConstants = cldr.find('unitConstants')
    constants = {}
    def feval(exp):
        if exp.startswith("1/"):
            exp = f"1/({exp[2:]})"
        return eval(exp, dict(constants))

    for unitConstant in unitConstants.findall('unitConstant'):
        name = unitConstant.attrib['constant']
        value = unitConstant.attrib['value']
        try:
            constants[name] = int(value)
        except ValueError:
            try:
                constants[name] = float(value)
            except ValueError:
                constants[name] = feval(value)

    convertUnits = cldr.find('convertUnits')
    for convertUnit in convertUnits.findall('convertUnit'):
        factor = feval(convertUnit.attrib.get('factor', "1"))
        offset = feval(convertUnit.attrib.get('offset', "0"))
        by_measurement[by_base_unit[convertUnit.attrib['baseUnit']]].append(
            (convertUnit.attrib['source'], factor, offset))
        by_name[convertUnit.attrib['source']] = (
            by_base_unit[convertUnit.attrib['baseUnit']], convertUnit.attrib['baseUnit'],  factor, offset)

    def _define_unit(name):
        if name in by_name:
            return by_name[name]

        if name.startswith('square-'):
            basic_unit = name.split('-', 1)[1]
            basic_info = _define_unit(basic_unit)
            if basic_info is not None:
                squared_base = 'square-' + (basic_info[1] or basic_unit)
                if squared_base in by_name:
                    squared_basic_info = by_name[squared_base]
                    return squared_basic_info[0], squared_base, f"({basic_info[2]})*({basic_info[2]})", "0"
        elif name.startswith('cubic-'):
            basic_unit = name.split('-', 1)[1]
            basic_info = _define_unit(basic_unit)
            if basic_info is not None:
                cubic_base = 'cubic-' + (basic_info[1] or basic_unit)
                if cubic_base in by_name:
                    squared_basic_info = by_name[cubic_base]
                    return squared_basic_info[0], cubic_base, f"({basic_info[2]})*({basic_info[2]})*({basic_info[2]})", "0"

        elif '-per-' in name:
            basic_unit1, basic_unit2 = name.split('-per-')
            basic_info1 = _define_unit(basic_unit1)
            basic_info2 = _define_unit(basic_unit2)
            if basic_info1 is not None and basic_info2 is not None:
                per_basic_unit = _divide_names((basic_info1[1] or basic_unit1), (basic_info2[1] or basic_unit2))
                per_basic_unit_inv = _divide_names((basic_info2[1] or basic_unit2), (basic_info1[1] or basic_unit1))
                if per_basic_unit in by_name:
                    per_basic_info = by_name[per_basic_unit]
                    return per_basic_info[0], per_basic_unit, f"({basic_info1[2]})/({basic_info2[2]})", "0"
                elif per_basic_unit_inv in by_name:
                    per_basic_info = by_name[per_basic_unit_inv]
                    return per_basic_info[0], per_basic_unit_inv, f"({basic_info2[2]})/({basic_info1[2]})", "0"
                else:
                    print(f"No {per_basic_unit} or {per_basic_unit_inv}")
            else:
                print(f"{name} -> {basic_unit1} / {basic_unit2}", file=sys.stderr)
                print(f"{basic_unit1} -> {basic_info1}", file=sys.stderr)
                print(f"{basic_unit2} -> {basic_info2}", file=sys.stderr)
        else:
            basic_unit, factor = _get_multiprefix(name, compound_units)
            if basic_unit is not None and basic_unit in by_name:
                basic_info = by_name[basic_unit]
                return basic_info[0], basic_info[1] or basic_unit, f"{basic_info[2]}{factor}", "0"
            else:
                unit_parts = name.split('-')
                for i in range(len(unit_parts) - 1):
                    basic_unit1 = "-".join(unit_parts[:i + 1])
                    basic_unit2 = "-".join(unit_parts[i + 1:])
                    basic_info1 = _define_unit(basic_unit1)
                    basic_info2 = _define_unit(basic_unit2)
                    if basic_info1 is not None and basic_info2 is not None:
                        mul_basic_unit = _multiply_names((basic_info1[1] or basic_unit1), (basic_info2[1] or basic_unit2))
                        if mul_basic_unit in by_name:
                            per_basic_info = by_name[mul_basic_unit]
                            return per_basic_info[0], mul_basic_unit, f"({basic_info1[2]})*({basic_info2[2]})", "0"
                        else:
                            print(f"No {mul_basic_unit}")
                    else:
                        print(f"{name} -> {basic_unit1} * {basic_unit2}", file=sys.stderr)
                        print(f"{basic_unit1} -> {basic_info1}", file=sys.stderr)
                        print(f"{basic_unit2} -> {basic_info2}", file=sys.stderr)

        return None

    info = {}
    for section, units in all_units.items():
        for unit in units.keys():
            new_unit = _define_unit(unit)
            if new_unit is not None:
                info.setdefault(section, {}).setdefault(new_unit[0], {})[unit] = new_unit[1:]
            else:
                info.setdefault(section, {}).setdefault(section, {})[unit] = ('nothing', None, None)
                print("Not Found: ", section, unit, file=sys.stderr)

    missing = []

    for section, measurements in info.items():
        for measurement, units in measurements.items():
            base_unit_found = False
            base_unit = None
            for unit, unit_info in units.items():
                if unit_info[0] == unit:
                    units[unit] = (None, 1, 0)
                    base_unit_found = True
                elif unit_info[0] is None:
                    base_unit_found = True
                else:
                    base_unit = unit_info[0]

            if not base_unit_found:
                if section == by_name[base_unit][0]:
                    units[base_unit] = (None, 1, 0)
                else:
                    missing.append((section, measurement, base_unit))

    for msection, mmeasurement, base_unit in missing:
        found = False
        for section, measurements in info.items():
            for measurement, units in measurements.items():
                for unit, unit_info in units.items():
                    if unit == base_unit:
                        info[msection][mmeasurement] = {u: ((section, base_unit),) + ui[1:] for u, ui in info[msection][mmeasurement].items()}
                        found = True
                        break
        if not found:
            info[msection][mmeasurement][base_unit] = (None, 1, 0)

    return info, constants


digit = '[0-9]'
digitPos = '[1-9]'
value = f'\\s*(?:{digit}+)\\s*'
value_range = f'\\s*(?:{value}\\.\\.{value})\\s*'
range_list = f'\\s*(?:{value_range}|{value})\\s*(?:,(?:{value_range}|{value}))*'
range_list_ = f'\\s*(?:{value_range}|{value})\\s*(?:,(?:{value_range}|{value}))*'
operand = '\\s*(?:[niftvwce])\\s*'
expr = f'(?P<opr>{operand})(?:\\s+%\\s+(?P<dv>{value}))?'
expr_ = f'{operand}(?:\\s+%\\s+{value})?'
relation = f'(?P<expr>{expr_})\\s+(?P<op>=|!=)\\s+(?P<val>{range_list_})\\s*'
relation_ = f'{expr_}\\s+(?:(?:not)?\\s+in|=|!=)\\s+{range_list}'
and_condition_ = f'{relation_}\\s*(?:\\s+and\\s+{relation_})*'
and_condition = f'({relation_})' + (f'(?:\\s+and\\s+({relation_}))?' * 20)
or_condition = f'({and_condition_})' + (f'(?:\\s+or\\s+({and_condition_}))?' * 20)

or_condition_re = re.compile(or_condition)
and_condition_re = re.compile(and_condition)
relation_re = re.compile(relation)


def _brace(v):
    if (v.startswith("(") and v.endswith(")")) or len(v) == 1:
        return v
    else:
        return f"({v})"


def transform_range_check(expr, op, val):
    real = expr == 'n' or expr.startswith('n ')
    long = expr == 'i' or expr.startswith('i ')
    if real:
        suffix = '.0'
    elif long:
        suffix = 'L'
    else:
        suffix = None
    if '..' in val:
        op = '!in' if op == '!=' else 'in'
        if suffix is not None:
            start, end = val.split('..')
            if '.' not in start:
                start += suffix
            if '.' not in end:
                end += suffix
            val = f'{start}..{end}'
    else:
        if suffix is not None:
            if '.' not in val:
                val += suffix
        if op == '=':
            op = '=='
    return f"{_brace(expr)} {op} {val}"


def transform_relation(rel):
    m_rel0 = relation_re.fullmatch(rel)
    grps = m_rel0.groupdict()
    expr = grps['expr']
    op = grps['op']
    vals = grps['val'].split(',')

    return " || ".join([transform_range_check(expr, op, v) for v in vals])


def transform_and(ands):
    return " && ".join([f"{_brace(transform_relation(r))}" for r in and_condition_re.fullmatch(ands).groups() if r is not None])


def transform_or(ors):
    m = or_condition_re.fullmatch(ors)
    if m is None:
        return "true"
    return " || ".join([f"{_brace(transform_and(r))}" for r in m.groups() if r is not None])


def convert_plural_rule(cond):
    if "@integer" in cond:
        cond = cond[:cond.index("@integer")]
    if "@decimal" in cond:
        cond = cond[:cond.index("@decimal")]
    return transform_or(cond.strip())


def target_paths():
    return [TARGET_PATH + "/languages", TARGET_PATH + "/units"]


if __name__ == '__main__':
    main()

    # with open(PLURALS_PATH, 'r') as f:
    #     cldr = ET.fromstring(f.read())
    # plurals = cldr.find('plurals')
    # for pluralRules in plurals.findall("pluralRules"):
    #     locales = pluralRules.attrib['locales'].split(' ')
    #     if "ru" in locales:
    #         print(locales, ":")
    #         for pluralRule in pluralRules.findall('pluralRule'):
    #             form = pluralRule.attrib['count']
    #             cond = pluralRule.text
    #             if "@integer" in cond:
    #                 cond = cond[:cond.index("@integer")]
    #             if "@decimal" in cond:
    #                 cond = cond[:cond.index("@decimal")]
    #             # matches = list(plural_re.finditer(cond))
    #             print(form, cond)
    #             print(convert_plural_rule(cond.strip()))
