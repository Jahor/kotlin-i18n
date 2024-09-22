package info.leonenko.i18n

import info.leonenko.i18n.units.MeasurementUnitLanguage
import kotlin.math.abs
import kotlin.math.truncate

enum class Case {
    Nominative, Genitive, Dative, Accusative, Instrumental, Prepositional,
    Vocative, Locative, Oblique, Terminative, Translative,
    Illative, Inessive, Ablative, Adessive, Elative, Essive, Partitive, Sociative, Ergative
}

enum class Gender {
    Feminine, Masculine, Neuter, Common, Inanimate
}

enum class PluralForm {
    Zero, One, Two, Few, Many, Other
}

enum class FormatLength {
    Long, Short, Narrow
}

data class Plurals<T>(
    val zero: T? = null,
    val one: T? = null,
    val two: T? = null,
    val few: T? = null,
    val many: T? = null,
    val other: T
) {
    fun get(form: PluralForm): T {
        return when (form) {
            PluralForm.Zero -> zero ?: other
            PluralForm.One -> one ?: other
            PluralForm.Two -> two ?: few ?: other
            PluralForm.Few -> few ?: other
            PluralForm.Many -> many ?: other
            PluralForm.Other -> other
        }
    }
}

data class Cases<T>(
    val nominative: T,
    val genitive: T? = null,
    val dative: T? = null,
    val accusative: T? = null,
    val instrumental: T? = null,
    val prepositional: T? = null,
    val vocative: T? = null,
    val locative: T? = null,
    val oblique: T? = null,
    val terminative: T? = null,
    val translative: T? = null,
    val illative: T? = null,
    val inessive: T? = null,
    val ablative: T? = null,
    val adessive: T? = null,
    val elative: T? = null,
    val essive: T? = null,
    val partitive: T? = null,
    val sociative: T? = null,
    val ergative: T? = null,
) {
    fun get(case: Case): T? {
        return when (case) {
            Case.Nominative -> nominative
            Case.Genitive -> genitive
            Case.Dative -> dative
            Case.Accusative -> accusative
            Case.Instrumental -> instrumental
            Case.Prepositional -> prepositional
            Case.Vocative -> vocative
            Case.Locative -> locative
            Case.Oblique -> oblique
            Case.Terminative -> terminative
            Case.Translative -> translative
            Case.Illative -> illative
            Case.Inessive -> inessive
            Case.Ablative -> ablative
            Case.Adessive -> adessive
            Case.Elative -> elative
            Case.Essive -> essive
            Case.Partitive -> partitive
            Case.Sociative -> sociative
            Case.Ergative -> ergative
        }
    }

    constructor(cases: Map<Case, T>) : this(
        nominative = cases[Case.Nominative]!!,
        genitive = cases[Case.Genitive],
        dative = cases[Case.Dative],
        accusative = cases[Case.Accusative],
        instrumental = cases[Case.Instrumental],
        prepositional = cases[Case.Prepositional],
        vocative = cases[Case.Vocative],
        locative = cases[Case.Locative],
        oblique = cases[Case.Oblique],
        terminative = cases[Case.Terminative],
        translative = cases[Case.Translative],
        illative = cases[Case.Illative],
        inessive = cases[Case.Inessive],
        ablative = cases[Case.Ablative],
        adessive = cases[Case.Adessive],
        elative = cases[Case.Elative],
        essive = cases[Case.Essive],
        partitive = cases[Case.Partitive],
        sociative = cases[Case.Sociative],
        ergative = cases[Case.Ergative],
    )

    fun <R> map(transform: ((T, Case) -> R)): Cases<R> {
        return Cases(
            nominative = transform(nominative, Case.Nominative),
            genitive = genitive?.let { transform(it, Case.Genitive) },
            dative = dative?.let { transform(it, Case.Dative) },
            accusative = accusative?.let { transform(it, Case.Accusative) },
            instrumental = instrumental?.let { transform(it, Case.Instrumental) },
            prepositional = prepositional?.let { transform(it, Case.Prepositional) },
            vocative = vocative?.let { transform(it, Case.Vocative) },
            locative = locative?.let { transform(it, Case.Locative) },
            oblique = oblique?.let { transform(it, Case.Oblique) },
            terminative = terminative?.let { transform(it, Case.Terminative) },
            translative = translative?.let { transform(it, Case.Translative) },
            illative = illative?.let { transform(it, Case.Illative) },
            inessive = inessive?.let { transform(it, Case.Inessive) },
            ablative = ablative?.let { transform(it, Case.Ablative) },
            adessive = adessive?.let { transform(it, Case.Adessive) },
            elative = elative?.let { transform(it, Case.Elative) },
            essive = essive?.let { transform(it, Case.Essive) },
            partitive = partitive?.let { transform(it, Case.Partitive) },
            sociative = sociative?.let { transform(it, Case.Sociative) },
            ergative = ergative?.let { transform(it, Case.Ergative) },
        )
    }
}

interface PluralFormChooser {
    fun pluralForm(value: String): PluralForm
    fun pluralForm(value: Long): PluralForm
    fun pluralForm(value: Int): PluralForm = pluralForm(value.toLong())
    fun pluralForm(value: Short): PluralForm = pluralForm(value.toShort())
    fun pluralForm(value: Double): PluralForm
    fun pluralForm(value: Float): PluralForm = pluralForm(value.toDouble())

    object None : PluralFormChooser {
        override fun pluralForm(value: String): PluralForm = PluralForm.Other
        override fun pluralForm(value: Long): PluralForm = PluralForm.Other
        override fun pluralForm(value: Double): PluralForm = PluralForm.Other
    }

    object Simple : PluralFormChooser {
        override fun pluralForm(value: String): PluralForm =
            if (abs(value.toDouble()) == 1.0) PluralForm.One else PluralForm.Other

        override fun pluralForm(value: Long): PluralForm =
            if (abs(value) == 1L) PluralForm.One else PluralForm.Other

        override fun pluralForm(value: Double): PluralForm =
            if (abs(value) == 1.0) PluralForm.One else PluralForm.Other
    }

    // http://unicode.org/reports/tr35/tr35-numbers.html#Language_Plural_Rules
    data class PluralOperand(
        val source: String,
        val n: Double,
        val i: Long, // TODO Make Long
        val v: Int,
        val w: Int,
        val f: Int,
        val t: Int,
        val c: Int,
        val e: Int = c
    ) {

        companion object {
            fun from(value: Long): PluralOperand {
                return PluralOperand(value.toString(), abs(value).toDouble(), abs(value), 0, 0, 0, 0, 0)
            }

            fun from(value: Double): PluralOperand {
                val n = abs(value)
                val digits = n.fractional().fractionalDigits()
                val v = digits.size
                val f = digits.reduceOrNull { acc, i -> acc * 10 + i } ?: 0
                return PluralOperand(value.toString(), n, truncate(n).toLong(), v, v, f, f, 0)
            }

            fun from(
                source: String
            ): PluralOperand {
                val n: Double
                val c: Int
                val f: String
                var conventionalSource = source
                for (exponential in listOf("E", "×۱۰^", "e", "·10^", "اس", "Е", "×10^")) {
                    conventionalSource = conventionalSource.replace(exponential, "c")
                }
                for (decimal in listOf(",", "٫", ".")) {
                    conventionalSource = conventionalSource.replace(decimal, ".")
                }
                // TODO remove group separators

                var s = conventionalSource.toMutableList()
                val cPos = s.indexOf('c')
                if (cPos != -1) {
                    c = if (cPos == s.size - 1) {
                        0
                    } else {
                        s.subList(cPos + 1, s.size).toCharArray().concatToString().toInt()
                    }
                    s = s.subList(0, cPos)
                } else {
                    c = 0
                }
                val dPos = s.indexOf('.')
                if (dPos != -1) {
                    val ds = s[dPos]
                    if (c > 0) {
                        if (s.size - dPos > c) {
                            for (k in dPos until dPos + c) {
                                s[k] = s[k + 1]
                                s[k + 1] = ds
                            }
                            f = s.subList(dPos + c + 1, s.size).toCharArray().concatToString()
                        } else {
                            s.removeAt(dPos)
                            s.addAll(List(c - (s.size - dPos)) { '0' })
                            f = ""
                        }
                    } else {
                        f = s.subList(dPos + 1, s.size).toCharArray().concatToString()
                    }
                } else {
                    s.addAll(List(c) { '0' })
                    f = ""
                }
                val finalS = s.toCharArray().concatToString()

                val t = f.trimEnd('0')
                n = abs(finalS.toDouble())
                val i = truncate(n).toLong()
                return PluralOperand(source, n, i, f.length, t.length, f.toIntOrNull() ?: 0, t.toIntOrNull() ?: 0, c)
            }
        }
    }

    class Expression(
        private val zero: PluralOperand.() -> Boolean = { false },
        private val one: PluralOperand.() -> Boolean = { false },
        private val two: PluralOperand.() -> Boolean = { false },
        private val few: PluralOperand.() -> Boolean = { false },
        private val many: PluralOperand.() -> Boolean = { false },
    ) : PluralFormChooser {
        override fun pluralForm(value: String): PluralForm {
            return pluralForm(PluralOperand.from(value))
        }

        override fun pluralForm(value: Long): PluralForm {
            return pluralForm(PluralOperand.from(value))
        }

        override fun pluralForm(value: Double): PluralForm {
            return pluralForm(PluralOperand.from(value))
        }

        fun pluralForm(operand: PluralOperand): PluralForm {
            return when {
                operand.zero() -> PluralForm.Zero
                operand.one() -> PluralForm.One
                operand.two() -> PluralForm.Two
                operand.few() -> PluralForm.Few
                operand.many() -> PluralForm.Many
                else -> PluralForm.Other
            }
        }
    }
}

interface Language {
    companion object

    val code: String
    val language: String
    val script: String?
    val territory: String?
    val pluralFormChooser: PluralFormChooser
    val measurementUnits: MeasurementUnitLanguage
    val list: ListLanguage
    val ellipsis: EllipsisLanguage
    val rbnf: RBNF

    val numbers: NumberLanguage
}

interface Formattable {
    fun format(language: Language, case: Case, length: FormatLength): String
}

interface Formatter<in U> {
    fun format(value: U): String
}

interface Formatters<U> {
    fun formatter(case: Case, length: FormatLength): Formatter<U>
}

data class PluralString(var value: String, var form: PluralForm)

data class PluralPattern(
    val plurals: Plurals<String>
) : Formatter<PluralString> {
    override fun format(value: PluralString): String {
        return plurals.get(value.form).replace("{0}", value.value)
    }
}

data class GenderedPattern(
    val gender: Gender? = null,
    val cases: Cases<PluralPattern>
)

fun MultiLengthGenderedPattern(
    base: MultiLengthGenderedPattern,
    long: GenderedPattern? = null,
    short: GenderedPattern? = null,
    narrow: GenderedPattern? = null
): MultiLengthGenderedPattern {
    return MultiLengthGenderedPattern(
        long ?: base.long,
        short ?: base.short,
        narrow ?: base.narrow
    )
}

data class MultiLengthGenderedPattern(
    val long: GenderedPattern? = null,
    val short: GenderedPattern? = null,
    val narrow: GenderedPattern? = null,
) : Formatters<PluralString> {

    val gender: Gender? = (long ?: short ?: narrow)?.gender

    override fun formatter(
        case: Case,
        length: FormatLength
    ): Formatter<PluralString> {
        val cases = when (length) {
            FormatLength.Long -> long ?: (short ?: narrow)
            FormatLength.Short -> short ?: (long ?: narrow)
            FormatLength.Narrow -> short ?: (narrow ?: long)
        }!!.cases
        return cases.get(case) ?: cases.nominative
    }
}

interface RBNF {
    val spelloutNumberingYear: NumberFormatter
    val spelloutNumbering: NumberFormatter
    val spelloutCardinal: NumberFormatter
    val spelloutOrdinal: NumberFormatter
}
