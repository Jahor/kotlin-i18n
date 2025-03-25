package info.leonenko.i18n.rbnf

import info.leonenko.i18n.*
import info.leonenko.i18n.languages.id


open class RBNFId(val language: Language = Language.id) : RBNF {
    private inner class SpelloutRules {
        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumbering = SpelloutNumbering()

        val spelloutCardinal = SpelloutCardinal()

        val spelloutOrdinal = SpelloutOrdinal()

        private inner class SpelloutNumberingYear : NumberFormatter {
            override fun format(value: Double): String {
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                return spelloutNumbering.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutNumbering.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 1, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutNumbering : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutCardinal.format(value) else {
                    throw UnsupportedOperationException("spelloutNumbering only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutCardinal.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutCardinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinal : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "negatif " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "negatif " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "nol"
                    1L -> "satu"
                    2L -> "dua"
                    3L -> "tiga"
                    4L -> "empat"
                    5L -> "lima"
                    6L -> "enam"
                    7L -> "tujuh"
                    8L -> "delapan"
                    9L -> "sembilan"
                    10L -> "sepuluh"
                    11L -> "sebelas"
                    in 12L..19L -> format(value % 10) + " belas"
                    in 20L..99L -> {
                        var result = format((value) / 10)
                        result += " puluh"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "seratus"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = format((value) / 100)
                        result += " ratus"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "seribu"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = format((value) / 1000)
                        result += " ribu"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = format((value) / 1000000)
                        result += " juta"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = format((value) / 1000000000)
                        result += " miliar"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " triliun"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += " kuadriliun"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 1000000000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 1,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("spelloutCardinal only supports numbers >= 0")
                        }
                    }
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return when (language.numbers.symbols.decimalSymbol) {
                    "," -> format(kotlin.math.truncate(value).toLong()) + " koma " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
                    else -> format(kotlin.math.truncate(value).toLong()) + " titik " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
                }
            }
        }

        private inner class SpelloutOrdinal : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "negatif " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "negatif " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "ke" + spelloutCardinal.format(value)
                    1L -> "pertama"
                    else -> {
                        if (value >= 2L) "ke" + spelloutCardinal.format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinal only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2).bestDenominatorOrNull(value)!!) {
                    0L -> "ke" + spelloutCardinal.format(value)
                    1L -> "pertama"
                    2L -> "ke" + spelloutCardinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

    }

    private val spelloutRules = SpelloutRules()

    private inner class OrdinalRules {
        val digitsOrdinal = DigitsOrdinal()

        private inner class DigitsOrdinal : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−ke-" + DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(
                        kotlin.math.abs(value)
                    )
                }
                return if (value >= 0.0) "ke-" + DecimalNumberFormatter(
                    min_integer = 1,
                    min_decimal = 0,
                    max_decimal = 0,
                    primary_grouping = 3,
                    secondary_grouping = 3,
                    symbols = language.numbers.symbols
                ).format(value) else {
                    throw UnsupportedOperationException("digitsOrdinal only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−ke-" + DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(
                        kotlin.math.abs(value)
                    )
                }
                return if (value >= 0L) "ke-" + DecimalNumberFormatter(
                    min_integer = 1,
                    min_decimal = 0,
                    max_decimal = 0,
                    primary_grouping = 3,
                    secondary_grouping = 3,
                    symbols = language.numbers.symbols
                ).format(value) else {
                    throw UnsupportedOperationException("digitsOrdinal only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> "ke-" + DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(
                        value
                    )

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

    }

    private val ordinalRules = OrdinalRules()
    override val spelloutNumberingYear: NumberFormatter
        get() = spelloutRules.spelloutNumberingYear
    override val spelloutNumbering: NumberFormatter
        get() = spelloutRules.spelloutNumbering
    override val spelloutCardinal: NumberFormatter
        get() = spelloutRules.spelloutCardinal
    override val spelloutOrdinal: NumberFormatter
        get() = spelloutRules.spelloutOrdinal
    open val digitsOrdinal: NumberFormatter
        get() = ordinalRules.digitsOrdinal

    override fun spelloutNumberingFor(gender: Gender, case: Case): NumberFormatter {
        return spelloutNumbering

    }

    fun spelloutNumberingFor(): NumberFormatter {
        return spelloutNumberingFor(Gender.Neuter, Case.Nominative)
    }

    override fun spelloutCardinalFor(gender: Gender, case: Case): NumberFormatter {
        return spelloutCardinal

    }

    fun spelloutCardinalFor(): NumberFormatter {
        return spelloutCardinalFor(Gender.Neuter, Case.Nominative)
    }

    override fun spelloutOrdinalFor(gender: Gender, case: Case): NumberFormatter {
        return spelloutOrdinal

    }

    fun spelloutOrdinalFor(): NumberFormatter {
        return spelloutOrdinalFor(Gender.Neuter, Case.Nominative)
    }

    fun digitsOrdinalFor(gender: Gender, case: Case): NumberFormatter {
        return digitsOrdinal

    }

    fun digitsOrdinalFor(): NumberFormatter {
        return digitsOrdinalFor(Gender.Neuter, Case.Nominative)
    }

    override fun spelloutNumberingYearFor(gender: Gender, case: Case): NumberFormatter {
        TODO("spelloutNumberingYear is not available for id")
    }
}
