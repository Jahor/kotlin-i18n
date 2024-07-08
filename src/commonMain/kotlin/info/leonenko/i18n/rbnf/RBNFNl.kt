package info.leonenko.i18n.rbnf

import info.leonenko.i18n.*
import info.leonenko.i18n.languages.nl
import kotlin.math.roundToLong


open class RBNFNl(val language: Language = Language.nl) : RBNF {
    private inner class SpelloutRules {
        val _2dYear = _2dYearC()

        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumbering = SpelloutNumbering()

        val numberEn = NumberEn()

        val spelloutCardinal = SpelloutCardinal()

        val ordSte = OrdSte()

        val spelloutOrdinal = SpelloutOrdinal()

        private inner class _2dYearC : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "honderd"
                    else -> {
                        if (value >= 1.0) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("_2dYear only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "honderd"
                    else -> {
                        if (value >= 1L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("_2dYear only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "honderd"
                    1L -> spelloutNumbering.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutNumberingYear : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "min " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "min " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    in 0L..1099L -> spelloutNumbering.format(value)
                    in 1100L..1999L -> format((value) / 100) + _2dYear.format(value % 100)
                    in 2000L..2099L -> spelloutNumbering.format(value)
                    in 2100L..2999L -> format((value) / 100) + _2dYear.format(value % 100)
                    in 3000L..3099L -> spelloutNumbering.format(value)
                    in 3100L..3999L -> format((value) / 100) + _2dYear.format(value % 100)
                    in 4000L..4099L -> spelloutNumbering.format(value)
                    in 4100L..4999L -> format((value) / 100) + _2dYear.format(value % 100)
                    in 5000L..5099L -> spelloutNumbering.format(value)
                    in 5100L..5999L -> format((value) / 100) + _2dYear.format(value % 100)
                    in 6000L..6099L -> spelloutNumbering.format(value)
                    in 6100L..6999L -> format((value) / 100) + _2dYear.format(value % 100)
                    in 7000L..7099L -> spelloutNumbering.format(value)
                    in 7100L..7999L -> format((value) / 100) + _2dYear.format(value % 100)
                    in 8000L..8099L -> spelloutNumbering.format(value)
                    in 8100L..8999L -> format((value) / 100) + _2dYear.format(value % 100)
                    in 9000L..9099L -> spelloutNumbering.format(value)
                    in 9100L..9999L -> format((value) / 100) + _2dYear.format(value % 100)
                    else -> {
                        if (value >= 10000L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("spelloutNumberingYear only supports numbers >= 0")
                        }
                    }
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

        private inner class NumberEn : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    1L -> "een­en­"
                    2L -> "twee­ën­"
                    3L -> "drie­ën­"
                    else -> {
                        if (value >= 4.0) spelloutCardinal.format(value) + "­en­" else {
                            throw UnsupportedOperationException("numberEn only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    1L -> "een­en­"
                    2L -> "twee­ën­"
                    3L -> "drie­ën­"
                    else -> {
                        if (value >= 4L) spelloutCardinal.format(value) + "­en­" else {
                            throw UnsupportedOperationException("numberEn only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 2, 3, 4).bestDenominatorOrNull(value)!!) {
                    1L -> "een­en­"
                    2L -> "twee­ën­"
                    3L -> "drie­ën­"
                    4L -> spelloutCardinal.format(value) + "­en­"
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinal : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "min " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "min " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "nul"
                    1L -> "een"
                    2L -> "twee"
                    3L -> "drie"
                    4L -> "vier"
                    5L -> "vijf"
                    6L -> "zes"
                    7L -> "zeven"
                    8L -> "acht"
                    9L -> "negen"
                    10L -> "tien"
                    11L -> "elf"
                    12L -> "twaalf"
                    13L -> "dertien"
                    14L -> "veertien"
                    15L -> "vijftien"
                    16L -> "zestien"
                    17L -> "zeventien"
                    18L -> "achttien"
                    19L -> "negentien"
                    in 20L..29L -> (if ((value) % 10 == 0L) "" else numberEn.format(value % 10)) + "twintig"
                    in 30L..39L -> (if ((value) % 10 == 0L) "" else numberEn.format(value % 10)) + "dertig"
                    in 40L..49L -> (if ((value) % 10 == 0L) "" else numberEn.format(value % 10)) + "veertig"
                    in 50L..59L -> (if ((value) % 10 == 0L) "" else numberEn.format(value % 10)) + "vijftig"
                    in 60L..69L -> (if ((value) % 10 == 0L) "" else numberEn.format(value % 10)) + "zestig"
                    in 70L..79L -> (if ((value) % 10 == 0L) "" else numberEn.format(value % 10)) + "zeventig"
                    in 80L..89L -> (if ((value) % 10 == 0L) "" else numberEn.format(value % 10)) + "tachtig"
                    in 90L..99L -> (if ((value) % 10 == 0L) "" else numberEn.format(value % 10)) + "negentig"
                    in 100L..199L -> "honderd" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 200L..999L -> {
                        var result = format((value) / 100)
                        result += "­honderd"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "duizend"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..99999L -> {
                        var result = format((value) / 1000)
                        result += "­duizend"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    in 100000L..999999L -> {
                        var result = format((value) / 1000)
                        result += "­duizend"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = format((value) / 1000000)
                        result += " miljoen"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = format((value) / 1000000000)
                        result += " miljard"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " biljoen"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += " biljard"
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
                            max_decimal = 0,
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
                return format(kotlin.math.truncate(value).toLong()) + " komma " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class OrdSte : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ste"
                    else -> {
                        if (value >= 1.0) "­" + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("ordSte only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ste"
                    else -> {
                        if (value >= 1L) "­" + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("ordSte only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ste"
                    1L -> "­" + spelloutOrdinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinal : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "min " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "min " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "nulste"
                    1L -> "eerste"
                    2L -> "tweede"
                    3L -> "derde"
                    in 4L..7L -> spelloutCardinal.format(value) + "de"
                    8L -> spelloutCardinal.format(value) + "ste"
                    in 9L..19L -> spelloutCardinal.format(value) + "de"
                    in 20L..99L -> spelloutCardinal.format(value) + "ste"
                    in 100L..199L -> "honderd" + ordSte.format(value % 100)
                    in 200L..999L -> spelloutCardinal.format((value) / 100) + "­honderd" + ordSte.format(value % 100)
                    in 1000L..1999L -> "duizend" + ordSte.format(value % 1000)
                    in 2000L..999999L -> spelloutCardinal.format((value) / 1000) + "­duizend" + ordSte.format(value % 1000)
                    in 1000000L..999999999L -> spelloutCardinal.format((value) / 1000000) + "­miljoen" + ordSte.format(value % 1000000)
                    in 1000000000L..999999999999L -> spelloutCardinal.format((value) / 1000000000) + "­miljard" + ordSte.format(value % 1000000000)
                    in 1000000000000L..999999999999999L -> spelloutCardinal.format((value) / 1000000000000) + "­biljoen" + ordSte.format(value % 1000000000000)
                    in 1000000000000000L..999999999999999999L -> spelloutCardinal.format((value) / 1000000000000000) + "­biljard" + ordSte.format(value % 1000000000000000)
                    else -> {
                        if (value >= 1000000000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) + "e" else {
                            throw UnsupportedOperationException("spelloutOrdinal only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 3, 4, 8, 9, 20, 100, 200, 1000, 2000, 1000000, 1000000000, 1000000000000, 1000000000000000, 1000000000000000000).bestDenominatorOrNull(value)!!) {
                    0L -> "nulste"
                    1L -> "eerste"
                    2L -> "tweede"
                    3L -> "derde"
                    4L -> spelloutCardinal.format(value) + "de"
                    8L -> spelloutCardinal.format(value) + "ste"
                    9L -> spelloutCardinal.format(value) + "de"
                    20L -> spelloutCardinal.format(value) + "ste"
                    100L -> "honderd" + ordSte.format(value % 100)
                    200L -> spelloutCardinal.format((value * 200).toLong()) + "­honderd" + ordSte.format(value % 100)
                    1000L -> "duizend" + ordSte.format(value % 1000)
                    2000L -> spelloutCardinal.format((value * 2000).toLong()) + "­duizend" + ordSte.format(value % 1000)
                    1000000L -> spelloutCardinal.format((value * 1000000).toLong()) + "­miljoen" + ordSte.format(value % 1000000)
                    1000000000L -> spelloutCardinal.format((value * 1000000000).toLong()) + "­miljard" + ordSte.format(value % 1000000000)
                    1000000000000L -> spelloutCardinal.format((value * 1000000000000).toLong()) + "­biljoen" + ordSte.format(value % 1000000000000)
                    1000000000000000L -> spelloutCardinal.format((value * 1000000000000000).toLong()) + "­biljard" + ordSte.format(value % 1000000000000000)
                    1000000000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 3,
                        secondary_grouping = 3,
                        symbols = language.numbers.symbols
                    ).format(value) + "e"

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
                    return "−" + format(kotlin.math.abs(value))
                }
                return if (value >= 0.0) DecimalNumberFormatter(
                    min_integer = 1,
                    min_decimal = 0,
                    max_decimal = 0,
                    primary_grouping = 3,
                    secondary_grouping = 3,
                    symbols = language.numbers.symbols
                ).format(value) + "e" else {
                    throw UnsupportedOperationException("digitsOrdinal only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return if (value >= 0L) DecimalNumberFormatter(
                    min_integer = 1,
                    min_decimal = 0,
                    max_decimal = 0,
                    primary_grouping = 3,
                    secondary_grouping = 3,
                    symbols = language.numbers.symbols
                ).format(value) + "e" else {
                    throw UnsupportedOperationException("digitsOrdinal only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 3,
                        secondary_grouping = 3,
                        symbols = language.numbers.symbols
                    ).format(value) + "e"

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
}
