package info.leonenko.i18n.rbnf

import info.leonenko.i18n.*
import info.leonenko.i18n.languages.en
import kotlin.math.roundToLong


open class RBNFEn(val language: Language = Language.en) : RBNF {
    private inner class SpelloutRules {
        val _2dYear = _2dYearC()

        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumbering = SpelloutNumbering()

        val spelloutNumberingVerbose = SpelloutNumberingVerbose()

        val spelloutCardinal = SpelloutCardinal()

        val and = And()

        val commas = Commas()

        val spelloutCardinalVerbose = SpelloutCardinalVerbose()

        val tieth = Tieth()

        val th = Th()

        val spelloutOrdinal = SpelloutOrdinal()

        val andO = AndO()

        val commasO = CommasO()

        val spelloutOrdinalVerbose = SpelloutOrdinalVerbose()

        private inner class _2dYearC : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "hundred"
                    in 1L..9L -> "oh-" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 10.0) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("_2dYear only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "hundred"
                    in 1L..9L -> "oh-" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 10L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("_2dYear only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 10).bestDenominatorOrNull(value)!!) {
                    0L -> "hundred"
                    1L -> "oh-" + spelloutNumbering.format(value)
                    10L -> spelloutNumbering.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutNumberingYear : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "minus " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "minus " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    in 0L..1009L -> spelloutNumbering.format(value)
                    in 1010L..1099L -> if ((value) % 100 == 0L) {
                        spelloutNumbering.format(value)
                    } else
                    format((value) / 100) + " " + _2dYear.format(value % 100)

                    in 1100L..1999L -> format((value) / 100) + " " + _2dYear.format(value % 100)
                    in 2000L..2009L -> spelloutNumbering.format(value)
                    in 2010L..2099L -> if ((value) % 100 == 0L) {
                        spelloutNumbering.format(value)
                    } else
                    format((value) / 100) + " " + _2dYear.format(value % 100)

                    in 2100L..2999L -> format((value) / 100) + " " + _2dYear.format(value % 100)
                    in 3000L..3009L -> spelloutNumbering.format(value)
                    in 3010L..3099L -> if ((value) % 100 == 0L) {
                        spelloutNumbering.format(value)
                    } else
                    format((value) / 100) + " " + _2dYear.format(value % 100)

                    in 3100L..3999L -> format((value) / 100) + " " + _2dYear.format(value % 100)
                    in 4000L..4009L -> spelloutNumbering.format(value)
                    in 4010L..4099L -> if ((value) % 100 == 0L) {
                        spelloutNumbering.format(value)
                    } else
                    format((value) / 100) + " " + _2dYear.format(value % 100)

                    in 4100L..4999L -> format((value) / 100) + " " + _2dYear.format(value % 100)
                    in 5000L..5009L -> spelloutNumbering.format(value)
                    in 5010L..5099L -> if ((value) % 100 == 0L) {
                        spelloutNumbering.format(value)
                    } else
                    format((value) / 100) + " " + _2dYear.format(value % 100)

                    in 5100L..5999L -> format((value) / 100) + " " + _2dYear.format(value % 100)
                    in 6000L..6009L -> spelloutNumbering.format(value)
                    in 6010L..6099L -> if ((value) % 100 == 0L) {
                        spelloutNumbering.format(value)
                    } else
                    format((value) / 100) + " " + _2dYear.format(value % 100)

                    in 6100L..6999L -> format((value) / 100) + " " + _2dYear.format(value % 100)
                    in 7000L..7009L -> spelloutNumbering.format(value)
                    in 7010L..7099L -> if ((value) % 100 == 0L) {
                        spelloutNumbering.format(value)
                    } else
                    format((value) / 100) + " " + _2dYear.format(value % 100)

                    in 7100L..7999L -> format((value) / 100) + " " + _2dYear.format(value % 100)
                    in 8000L..8009L -> spelloutNumbering.format(value)
                    in 8010L..8099L -> if ((value) % 100 == 0L) {
                        spelloutNumbering.format(value)
                    } else
                    format((value) / 100) + " " + _2dYear.format(value % 100)

                    in 8100L..8999L -> format((value) / 100) + " " + _2dYear.format(value % 100)
                    in 9000L..9009L -> spelloutNumbering.format(value)
                    in 9010L..9099L -> if ((value) % 100 == 0L) {
                        spelloutNumbering.format(value)
                    } else
                    format((value) / 100) + " " + _2dYear.format(value % 100)

                    in 9100L..9999L -> format((value) / 100) + " " + _2dYear.format(value % 100)
                    else -> {
                        if (value >= 10000L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("spelloutNumberingYear only supports numbers >= 0")
                        }
                    }
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutNumbering : NumberFormatter {
            override fun format(value: Double): String {
                if (value.isNaN()) {
                    return "not a number"
                }
                if (value.isInfinite()) {
                    return "infinity"
                }
                if (value < 0L) {
                    return "minus " + format(kotlin.math.abs(value))
                }
                return if (value >= 0.0) spelloutCardinal.format(value) else {
                    throw UnsupportedOperationException("spelloutNumbering only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "minus " + format(kotlin.math.abs(value))
                }
                return if (value >= 0L) spelloutCardinal.format(value) else {
                    throw UnsupportedOperationException("spelloutNumbering only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutCardinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutNumberingVerbose : NumberFormatter {
            override fun format(value: Double): String {
                if (value.isNaN()) {
                    return "not a number"
                }
                if (value.isInfinite()) {
                    return "infinity"
                }
                if (value < 0L) {
                    return "minus " + format(kotlin.math.abs(value))
                }
                return if (value >= 0.0) spelloutCardinalVerbose.format(value) else {
                    throw UnsupportedOperationException("spelloutNumberingVerbose only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "minus " + format(kotlin.math.abs(value))
                }
                return if (value >= 0L) spelloutCardinalVerbose.format(value) else {
                    throw UnsupportedOperationException("spelloutNumberingVerbose only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutCardinalVerbose.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinal : NumberFormatter {
            override fun format(value: Double): String {
                if (value.isNaN()) {
                    return "not a number"
                }
                if (value.isInfinite()) {
                    return "infinite"
                }
                if (value < 0L) {
                    return "minus " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "minus " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "zero"
                    1L -> "one"
                    2L -> "two"
                    3L -> "three"
                    4L -> "four"
                    5L -> "five"
                    6L -> "six"
                    7L -> "seven"
                    8L -> "eight"
                    9L -> "nine"
                    10L -> "ten"
                    11L -> "eleven"
                    12L -> "twelve"
                    13L -> "thirteen"
                    14L -> "fourteen"
                    15L -> "fifteen"
                    16L -> "sixteen"
                    17L -> "seventeen"
                    18L -> "eighteen"
                    19L -> "nineteen"
                    in 20L..29L -> {
                        var result = "twenty"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "thirty"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "forty"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "fifty"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "sixty"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "seventy"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "eighty"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "ninety"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + format(value % 10)
                        })
                        result
                    }

                    in 100L..999L -> {
                        var result = format((value) / 100)
                        result += " hundred"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = format((value) / 1000)
                        result += " thousand"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = format((value) / 1000000)
                        result += " million"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = format((value) / 1000000000)
                        result += " billion"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " trillion"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += " quadrillion"
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
                return format(kotlin.math.truncate(value).toLong()) + " point " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class And : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..99L -> " and " + spelloutCardinalVerbose.format(value)
                    else -> {
                        if (value >= 100.0) " " + spelloutCardinalVerbose.format(value) else {
                            throw UnsupportedOperationException("and only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..99L -> " and " + spelloutCardinalVerbose.format(value)
                    else -> {
                        if (value >= 100L) " " + spelloutCardinalVerbose.format(value) else {
                            throw UnsupportedOperationException("and only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 100).bestDenominatorOrNull(value)!!) {
                    1L -> " and " + spelloutCardinalVerbose.format(value)
                    100L -> " " + spelloutCardinalVerbose.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Commas : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..99L -> " and " + spelloutCardinalVerbose.format(value)
                    in 100L..999L -> ", " + spelloutCardinalVerbose.format(value)
                    in 1000L..999999L -> ", " + spelloutCardinalVerbose.format((value).toLong() / 1000) + " thousand" + (if ((value).toLong() % 1000 == 0L) "" else format(value % 1000))
                    else -> {
                        if (value >= 1000000.0) ", " + spelloutCardinalVerbose.format(value) else {
                            throw UnsupportedOperationException("commas only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..99L -> " and " + spelloutCardinalVerbose.format(value)
                    in 100L..999L -> ", " + spelloutCardinalVerbose.format(value)
                    in 1000L..999999L -> ", " + spelloutCardinalVerbose.format((value) / 1000) + " thousand" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    else -> {
                        if (value >= 1000000L) ", " + spelloutCardinalVerbose.format(value) else {
                            throw UnsupportedOperationException("commas only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 100, 1000, 1000000).bestDenominatorOrNull(value)!!) {
                    1L -> " and " + spelloutCardinalVerbose.format(value)
                    100L -> ", " + spelloutCardinalVerbose.format(value)
                    1000L -> ", " + spelloutCardinalVerbose.format((value * 1000).toLong()) + " thousand" + (if (value * 1000 == 1.0) "" else format(value % 1000))
                    1000000L -> ", " + spelloutCardinalVerbose.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalVerbose : NumberFormatter {
            override fun format(value: Double): String {
                if (value.isNaN()) {
                    return "not a number"
                }
                if (value.isInfinite()) {
                    return "infinite"
                }
                if (value < 0L) {
                    return "minus " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "minus " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    in 0L..99L -> spelloutNumbering.format(value)
                    in 100L..999L -> format((value) / 100) + " hundred" + (if ((value) % 100 == 0L) "" else and.format(value % 100))
                    in 1000L..99999L -> format((value) / 1000) + " thousand" + (if ((value) % 1000 == 0L) "" else and.format(value % 1000))
                    in 100000L..999999L -> format((value) / 1000) + " thousand" + (if ((value) % 1000 == 0L) "" else commas.format(value % 1000))
                    in 1000000L..999999999L -> format((value) / 1000000) + " million" + (if ((value) % 1000000 == 0L) "" else commas.format(value % 1000000))
                    in 1000000000L..999999999999L -> format((value) / 1000000000) + " billion" + (if ((value) % 1000000000 == 0L) "" else commas.format(value % 1000000000))
                    in 1000000000000L..999999999999999L -> format((value) / 1000000000000) + " trillion" + (if ((value) % 1000000000000 == 0L) "" else commas.format(value % 1000000000000))
                    in 1000000000000000L..999999999999999999L -> format((value) / 1000000000000000) + " quadrillion" + (if ((value) % 1000000000000000 == 0L) "" else commas.format(value % 1000000000000000))
                    else -> {
                        if (value >= 1000000000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalVerbose only supports numbers >= 0")
                        }
                    }
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " point " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class Tieth : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "tieth"
                    else -> {
                        if (value >= 1.0) "ty-" + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("tieth only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "tieth"
                    else -> {
                        if (value >= 1L) "ty-" + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("tieth only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "tieth"
                    1L -> "ty-" + spelloutOrdinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Th : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "th"
                    else -> {
                        if (value >= 1.0) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("th only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "th"
                    else -> {
                        if (value >= 1L) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("th only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "th"
                    1L -> " " + spelloutOrdinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinal : NumberFormatter {
            override fun format(value: Double): String {
                if (value.isInfinite()) {
                    return "infinitieth"
                }
                if (value < 0L) {
                    return "minus " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "minus " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "zeroth"
                    1L -> "first"
                    2L -> "second"
                    3L -> "third"
                    4L -> "fourth"
                    5L -> "fifth"
                    6L -> "sixth"
                    7L -> "seventh"
                    8L -> "eighth"
                    9L -> "ninth"
                    10L -> "tenth"
                    11L -> "eleventh"
                    12L -> "twelfth"
                    in 13L..19L -> spelloutNumbering.format(value) + "th"
                    in 20L..29L -> "twen" + tieth.format(value % 10)
                    in 30L..39L -> "thir" + tieth.format(value % 10)
                    in 40L..49L -> "for" + tieth.format(value % 10)
                    in 50L..59L -> "fif" + tieth.format(value % 10)
                    in 60L..69L -> "six" + tieth.format(value % 10)
                    in 70L..79L -> "seven" + tieth.format(value % 10)
                    in 80L..89L -> "eigh" + tieth.format(value % 10)
                    in 90L..99L -> "nine" + tieth.format(value % 10)
                    in 100L..999L -> spelloutNumbering.format((value) / 100) + " hundred" + th.format(value % 100)
                    in 1000L..999999L -> spelloutNumbering.format((value) / 1000) + " thousand" + th.format(value % 1000)
                    in 1000000L..999999999L -> spelloutNumbering.format((value) / 1000000) + " million" + th.format(value % 1000000)
                    in 1000000000L..999999999999L -> spelloutNumbering.format((value) / 1000000000) + " billion" + th.format(value % 1000000000)
                    in 1000000000000L..999999999999999L -> spelloutNumbering.format((value) / 1000000000000) + " trillion" + th.format(value % 1000000000000)
                    in 1000000000000000L..999999999999999999L -> spelloutNumbering.format((value) / 1000000000000000) + " quadrillion" + th.format(value % 1000000000000000)
                    else -> {
                        if (value >= 1000000000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) + Plurals(one = "st", two = "nd", few = "rd", other = "th").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000000).toString())) else {
                            throw UnsupportedOperationException("spelloutOrdinal only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(
                    0,
                    1,
                    2,
                    3,
                    4,
                    5,
                    6,
                    7,
                    8,
                    9,
                    10,
                    11,
                    12,
                    13,
                    20,
                    30,
                    40,
                    50,
                    60,
                    70,
                    80,
                    90,
                    100,
                    1000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "zeroth"
                    1L -> "first"
                    2L -> "second"
                    3L -> "third"
                    4L -> "fourth"
                    5L -> "fifth"
                    6L -> "sixth"
                    7L -> "seventh"
                    8L -> "eighth"
                    9L -> "ninth"
                    10L -> "tenth"
                    11L -> "eleventh"
                    12L -> "twelfth"
                    13L -> spelloutNumbering.format(value) + "th"
                    20L -> "twen" + tieth.format(value % 10)
                    30L -> "thir" + tieth.format(value % 10)
                    40L -> "for" + tieth.format(value % 10)
                    50L -> "fif" + tieth.format(value % 10)
                    60L -> "six" + tieth.format(value % 10)
                    70L -> "seven" + tieth.format(value % 10)
                    80L -> "eigh" + tieth.format(value % 10)
                    90L -> "nine" + tieth.format(value % 10)
                    100L -> spelloutNumbering.format((value * 100).toLong()) + " hundred" + th.format(value % 100)
                    1000L -> spelloutNumbering.format((value * 1000).toLong()) + " thousand" + th.format(value % 1000)
                    1000000L -> spelloutNumbering.format((value * 1000000).toLong()) + " million" + th.format(value % 1000000)
                    1000000000L -> spelloutNumbering.format((value * 1000000000).toLong()) + " billion" + th.format(value % 1000000000)
                    1000000000000L -> spelloutNumbering.format((value * 1000000000000).toLong()) + " trillion" + th.format(value % 1000000000000)
                    1000000000000000L -> spelloutNumbering.format((value * 1000000000000000).toLong()) + " quadrillion" + th.format(value % 1000000000000000)
                    1000000000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 3,
                        secondary_grouping = 3,
                        symbols = language.numbers.symbols
                    ).format(value) + Plurals(one = "st", two = "nd", few = "rd", other = "th").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000000).toLong()).toString()))

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class AndO : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "th"
                    in 1L..99L -> " and " + spelloutOrdinalVerbose.format(value)
                    else -> {
                        if (value >= 100.0) " " + spelloutOrdinalVerbose.format(value) else {
                            throw UnsupportedOperationException("andO only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "th"
                    in 1L..99L -> " and " + spelloutOrdinalVerbose.format(value)
                    else -> {
                        if (value >= 100L) " " + spelloutOrdinalVerbose.format(value) else {
                            throw UnsupportedOperationException("andO only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 100).bestDenominatorOrNull(value)!!) {
                    0L -> "th"
                    1L -> " and " + spelloutOrdinalVerbose.format(value)
                    100L -> " " + spelloutOrdinalVerbose.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class CommasO : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "th"
                    in 1L..99L -> " and " + spelloutOrdinalVerbose.format(value)
                    in 100L..999L -> ", " + spelloutOrdinalVerbose.format(value)
                    in 1000L..999999L -> ", " + spelloutCardinalVerbose.format((value).toLong() / 1000) + " thousand" + format(value % 1000)
                    else -> {
                        if (value >= 1000000.0) ", " + spelloutOrdinalVerbose.format(value) else {
                            throw UnsupportedOperationException("commasO only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "th"
                    in 1L..99L -> " and " + spelloutOrdinalVerbose.format(value)
                    in 100L..999L -> ", " + spelloutOrdinalVerbose.format(value)
                    in 1000L..999999L -> ", " + spelloutCardinalVerbose.format((value) / 1000) + " thousand" + format(value % 1000)
                    else -> {
                        if (value >= 1000000L) ", " + spelloutOrdinalVerbose.format(value) else {
                            throw UnsupportedOperationException("commasO only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 100, 1000, 1000000).bestDenominatorOrNull(value)!!) {
                    0L -> "th"
                    1L -> " and " + spelloutOrdinalVerbose.format(value)
                    100L -> ", " + spelloutOrdinalVerbose.format(value)
                    1000L -> ", " + spelloutCardinalVerbose.format((value * 1000).toLong()) + " thousand" + format(value % 1000)
                    1000000L -> ", " + spelloutOrdinalVerbose.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalVerbose : NumberFormatter {
            override fun format(value: Double): String {
                if (value.isInfinite()) {
                    return "infinitieth"
                }
                if (value < 0L) {
                    return "minus " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "minus " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    in 0L..99L -> spelloutOrdinal.format(value)
                    in 100L..999L -> spelloutNumberingVerbose.format((value) / 100) + " hundred" + andO.format(value % 100)
                    in 1000L..99999L -> spelloutNumberingVerbose.format((value) / 1000) + " thousand" + andO.format(value % 1000)
                    in 100000L..999999L -> spelloutNumberingVerbose.format((value) / 1000) + " thousand" + commasO.format(value % 1000)
                    in 1000000L..999999999L -> spelloutNumberingVerbose.format((value) / 1000000) + " million" + commasO.format(value % 1000000)
                    in 1000000000L..999999999999L -> spelloutNumberingVerbose.format((value) / 1000000000) + " billion" + commasO.format(value % 1000000000)
                    in 1000000000000L..999999999999999L -> spelloutNumberingVerbose.format((value) / 1000000000000) + " trillion" + commasO.format(value % 1000000000000)
                    in 1000000000000000L..999999999999999999L -> spelloutNumberingVerbose.format((value) / 1000000000000000) + " quadrillion" + commasO.format(value % 1000000000000000)
                    else -> {
                        if (value >= 1000000000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) + Plurals(one = "st", two = "nd", few = "rd", other = "th").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000000).toString())) else {
                            throw UnsupportedOperationException("spelloutOrdinalVerbose only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 100, 1000, 100000, 1000000, 1000000000, 1000000000000, 1000000000000000, 1000000000000000000).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinal.format(value)
                    100L -> spelloutNumberingVerbose.format((value * 100).toLong()) + " hundred" + andO.format(value % 100)
                    1000L -> spelloutNumberingVerbose.format((value * 1000).toLong()) + " thousand" + andO.format(value % 1000)
                    100000L -> spelloutNumberingVerbose.format((value * 100000).toLong()) + " thousand" + commasO.format(value % 1000)
                    1000000L -> spelloutNumberingVerbose.format((value * 1000000).toLong()) + " million" + commasO.format(value % 1000000)
                    1000000000L -> spelloutNumberingVerbose.format((value * 1000000000).toLong()) + " billion" + commasO.format(value % 1000000000)
                    1000000000000L -> spelloutNumberingVerbose.format((value * 1000000000000).toLong()) + " trillion" + commasO.format(value % 1000000000000)
                    1000000000000000L -> spelloutNumberingVerbose.format((value * 1000000000000000).toLong()) + " quadrillion" + commasO.format(value % 1000000000000000)
                    1000000000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 3,
                        secondary_grouping = 3,
                        symbols = language.numbers.symbols
                    ).format(value) + Plurals(one = "st", two = "nd", few = "rd", other = "th").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000000).toLong()).toString()))

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
                ).format(value) + Plurals(one = "st", two = "nd", few = "rd", other = "th").get(language.pluralFormChooser.pluralForm(((value).toLong() / 1).toString())) else {
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
                ).format(value) + Plurals(one = "st", two = "nd", few = "rd", other = "th").get(language.pluralFormChooser.pluralForm(((value) / 1).toString())) else {
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
                    ).format(value) + Plurals(one = "st", two = "nd", few = "rd", other = "th").get(language.pluralFormChooser.pluralForm(((value * 0).toLong()).toString()))

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
    open val spelloutNumberingVerbose: NumberFormatter
        get() = spelloutRules.spelloutNumberingVerbose
    override val spelloutCardinal: NumberFormatter
        get() = spelloutRules.spelloutCardinal
    open val spelloutCardinalVerbose: NumberFormatter
        get() = spelloutRules.spelloutCardinalVerbose
    override val spelloutOrdinal: NumberFormatter
        get() = spelloutRules.spelloutOrdinal
    open val spelloutOrdinalVerbose: NumberFormatter
        get() = spelloutRules.spelloutOrdinalVerbose
    open val digitsOrdinal: NumberFormatter
        get() = ordinalRules.digitsOrdinal
}
