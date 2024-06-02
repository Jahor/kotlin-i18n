package info.leonenko.i18n.rbnf

import kotlin.math.roundToLong
import info.leonenko.i18n.DecimalNumberFormatter
import info.leonenko.i18n.Language
import info.leonenko.i18n.RBNF
import info.leonenko.i18n.NumberFormatter
import info.leonenko.i18n.Case
import info.leonenko.i18n.Gender
import info.leonenko.i18n.languages.fr
import info.leonenko.i18n.formatFractionalPartByDigit
import info.leonenko.i18n.bestDenominatorOrNull
import info.leonenko.i18n.leadingFractionalZeroesCount
import info.leonenko.i18n.Plurals
import info.leonenko.i18n.until
import info.leonenko.i18n.contains


open class RBNFFr(val language: Language = Language.fr) : RBNF {
    private inner class SpelloutRules {
        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumbering = SpelloutNumbering()

        val etUn = EtUn()

        val centsM = CentsM()

        val subcentsM = SubcentsM()

        val spelloutLeading = SpelloutLeading()

        val spelloutCardinalMasculine = SpelloutCardinalMasculine()

        val etUne = EtUne()

        val centsF = CentsF()

        val subcentsF = SubcentsF()

        val spelloutCardinalFeminine = SpelloutCardinalFeminine()

        val etUnieme = EtUnieme()

        val centsO = CentsO()

        val subcentsO = SubcentsO()

        val milleO = MilleO()

        val spelloutOrdinal = SpelloutOrdinal()

        val spelloutOrdinalMasculinePlural = SpelloutOrdinalMasculinePlural()

        val spelloutOrdinalMasculine = SpelloutOrdinalMasculine()

        val spelloutOrdinalFemininePlural = SpelloutOrdinalFemininePlural()

        val spelloutOrdinalFeminine = SpelloutOrdinalFeminine()

        private inner class SpelloutNumberingYear : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "moins " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "moins " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    in 0L..1099L -> spelloutNumbering.format(value)
                    in 1100L..1999L -> spelloutCardinalMasculine.format((value) / 100) + "-cent" + centsM.format(value % 100)
                    else -> {
                        if (value >= 2000L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("spelloutNumberingYear only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1100, 2000).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutNumbering.format(value)
                    1100L -> spelloutCardinalMasculine.format((value * 1100).toLong()) + "-cent" + centsM.format(value % 100)
                    2000L -> spelloutNumbering.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 1, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutNumbering : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutCardinalMasculine.format(value) else {
                    throw UnsupportedOperationException("spelloutNumbering only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutCardinalMasculine.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutCardinalMasculine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class EtUn : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    1L -> "et-un"
                    in 2L..10L -> spelloutCardinalMasculine.format(value)
                    11L -> "et-onze"
                    else -> {
                        if (value >= 12.0) spelloutCardinalMasculine.format(value) else {
                            throw UnsupportedOperationException("etUn only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    1L -> "et-un"
                    in 2L..10L -> spelloutCardinalMasculine.format(value)
                    11L -> "et-onze"
                    else -> {
                        if (value >= 12L) spelloutCardinalMasculine.format(value) else {
                            throw UnsupportedOperationException("etUn only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 2, 11, 12).bestDenominatorOrNull(value)!!) {
                    1L -> "et-un"
                    2L -> spelloutCardinalMasculine.format(value)
                    11L -> "et-onze"
                    12L -> spelloutCardinalMasculine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class CentsM : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "s"
                    else -> {
                        if (value >= 1.0) " " + spelloutCardinalMasculine.format(value) else {
                            throw UnsupportedOperationException("centsM only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "s"
                    else -> {
                        if (value >= 1L) " " + spelloutCardinalMasculine.format(value) else {
                            throw UnsupportedOperationException("centsM only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "s"
                    1L -> " " + spelloutCardinalMasculine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SubcentsM : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "s"
                    else -> {
                        if (value >= 1.0) "-" + spelloutCardinalMasculine.format(value) else {
                            throw UnsupportedOperationException("subcentsM only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "s"
                    else -> {
                        if (value >= 1L) "-" + spelloutCardinalMasculine.format(value) else {
                            throw UnsupportedOperationException("subcentsM only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "s"
                    1L -> "-" + spelloutCardinalMasculine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutLeading : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 0L..79L -> spelloutCardinalMasculine.format(value)
                    in 80L..99L -> {
                        var result = "quatre-vingt"
                        result += (if ((value).toLong() % 20 == 0L) {
                            ""
                        } else {
                            "-" + format(value % 20)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "cent"
                        result += (if ((value).toLong() % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = format((value).toLong() / 100)
                        result += " cent"
                        result += (if ((value).toLong() % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    else -> {
                        if (value >= 1000.0) spelloutCardinalMasculine.format(value) else {
                            throw UnsupportedOperationException("spelloutLeading only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 0L..79L -> spelloutCardinalMasculine.format(value)
                    in 80L..99L -> {
                        var result = "quatre-vingt"
                        result += (if ((value) % 20 == 0L) {
                            ""
                        } else {
                            "-" + format(value % 20)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "cent"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = format((value) / 100)
                        result += " cent"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    else -> {
                        if (value >= 1000L) spelloutCardinalMasculine.format(value) else {
                            throw UnsupportedOperationException("spelloutLeading only supports numbers >= 0")
                        }
                    }
                }
            }
        }

        private inner class SpelloutCardinalMasculine : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "moins " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "moins " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "zéro"
                    1L -> "un"
                    2L -> "deux"
                    3L -> "trois"
                    4L -> "quatre"
                    5L -> "cinq"
                    6L -> "six"
                    7L -> "sept"
                    8L -> "huit"
                    9L -> "neuf"
                    10L -> "dix"
                    11L -> "onze"
                    12L -> "douze"
                    13L -> "treize"
                    14L -> "quatorze"
                    15L -> "quinze"
                    16L -> "seize"
                    in 17L..19L -> "dix-" + format(value % 10)
                    in 20L..29L -> {
                        var result = "vingt"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + etUn.format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "trente"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + etUn.format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "quarante"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + etUn.format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "cinquante"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + etUn.format(value % 10)
                        })
                        result
                    }

                    in 60L..79L -> {
                        var result = "soixante"
                        result += (if ((value) % 20 == 0L) {
                            ""
                        } else {
                            "-" + etUn.format(value % 20)
                        })
                        result
                    }

                    in 80L..99L -> "quatre-vingt" + subcentsM.format(value % 20)
                    in 100L..199L -> {
                        var result = "cent"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> format((value) / 100) + " cent" + centsM.format(value % 100)
                    in 1000L..1999L -> {
                        var result = "mille"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = spelloutLeading.format((value) / 1000)
                        result += " mille"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "un million"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = spelloutLeading.format((value) / 1000000)
                        result += " millions"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "un milliard"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = spelloutLeading.format((value) / 1000000000)
                        result += " milliards"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "un billion"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = spelloutLeading.format((value) / 1000000000000)
                        result += " billions"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "un billiard"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = spelloutLeading.format((value) / 1000000000000000)
                        result += " billiards"
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
                            throw UnsupportedOperationException("spelloutCardinalMasculine only supports numbers >= 0")
                        }
                    }
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " virgule " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class EtUne : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    1L -> "et-une"
                    in 2L..10L -> spelloutCardinalFeminine.format(value)
                    11L -> "et-onze"
                    else -> {
                        if (value >= 12.0) spelloutCardinalFeminine.format(value) else {
                            throw UnsupportedOperationException("etUne only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    1L -> "et-une"
                    in 2L..10L -> spelloutCardinalFeminine.format(value)
                    11L -> "et-onze"
                    else -> {
                        if (value >= 12L) spelloutCardinalFeminine.format(value) else {
                            throw UnsupportedOperationException("etUne only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 2, 11, 12).bestDenominatorOrNull(value)!!) {
                    1L -> "et-une"
                    2L -> spelloutCardinalFeminine.format(value)
                    11L -> "et-onze"
                    12L -> spelloutCardinalFeminine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class CentsF : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "s"
                    else -> {
                        if (value >= 1.0) " " + spelloutCardinalFeminine.format(value) else {
                            throw UnsupportedOperationException("centsF only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "s"
                    else -> {
                        if (value >= 1L) " " + spelloutCardinalFeminine.format(value) else {
                            throw UnsupportedOperationException("centsF only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "s"
                    1L -> " " + spelloutCardinalFeminine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SubcentsF : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "s"
                    else -> {
                        if (value >= 1.0) "-" + spelloutCardinalFeminine.format(value) else {
                            throw UnsupportedOperationException("subcentsF only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "s"
                    else -> {
                        if (value >= 1L) "-" + spelloutCardinalFeminine.format(value) else {
                            throw UnsupportedOperationException("subcentsF only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "s"
                    1L -> "-" + spelloutCardinalFeminine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalFeminine : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "moins " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "moins " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "zéro"
                    1L -> "une"
                    in 2L..19L -> spelloutCardinalMasculine.format(value)
                    in 20L..29L -> {
                        var result = "vingt"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + etUne.format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "trente"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + etUne.format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "quarante"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + etUne.format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "cinquante"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "-" + etUne.format(value % 10)
                        })
                        result
                    }

                    in 60L..79L -> {
                        var result = "soixante"
                        result += (if ((value) % 20 == 0L) {
                            ""
                        } else {
                            "-" + etUne.format(value % 20)
                        })
                        result
                    }

                    in 80L..99L -> "quatre-vingt" + subcentsF.format(value % 20)
                    in 100L..199L -> {
                        var result = "cent"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> spelloutCardinalMasculine.format((value) / 100) + " cent" + centsF.format(value % 100)
                    in 1000L..1999L -> {
                        var result = "mille"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = spelloutLeading.format((value) / 1000)
                        result += " mille"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "un million"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = spelloutLeading.format((value) / 1000000)
                        result += " millions"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "un milliard"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = spelloutLeading.format((value) / 1000000000)
                        result += " milliards"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "un billion"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = spelloutLeading.format((value) / 1000000000000)
                        result += " billions"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "un billiard"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = spelloutLeading.format((value) / 1000000000000000)
                        result += " billiards"
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
                            throw UnsupportedOperationException("spelloutCardinalFeminine only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(
                    0,
                    1,
                    2,
                    20,
                    30,
                    40,
                    50,
                    60,
                    80,
                    100,
                    200,
                    1000,
                    2000,
                    1000000,
                    2000000,
                    1000000000,
                    2000000000,
                    1000000000000,
                    2000000000000,
                    1000000000000000,
                    2000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "zéro"
                    1L -> "une"
                    2L -> spelloutCardinalMasculine.format(value)
                    20L -> {
                        var result = "vingt"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            "-" + etUne.format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "trente"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            "-" + etUne.format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "quarante"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            "-" + etUne.format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "cinquante"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            "-" + etUne.format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "soixante"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            "-" + etUne.format(value % 20)
                        })
                        result
                    }

                    80L -> "quatre-vingt" + subcentsF.format(value % 20)
                    100L -> {
                        var result = "cent"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> spelloutCardinalMasculine.format((value * 200).toLong()) + " cent" + centsF.format(value % 100)
                    1000L -> {
                        var result = "mille"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = spelloutLeading.format((value * 2000).toLong())
                        result += " mille"
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "un million"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutLeading.format((value * 2000000).toLong())
                        result += " millions"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = "un milliard"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutLeading.format((value * 2000000000).toLong())
                        result += " milliards"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "un billion"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutLeading.format((value * 2000000000000).toLong())
                        result += " billions"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = "un billiard"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutLeading.format((value * 2000000000000000).toLong())
                        result += " billiards"
                        result += (if (value * 2000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    1000000000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 3,
                        secondary_grouping = 3,
                        symbols = language.numbers.symbols
                    ).format(value)

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " virgule " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class EtUnieme : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    1L -> "et-unième"
                    in 2L..10L -> spelloutOrdinal.format(value)
                    11L -> "et-onzième"
                    else -> {
                        if (value >= 12.0) spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("etUnieme only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    1L -> "et-unième"
                    in 2L..10L -> spelloutOrdinal.format(value)
                    11L -> "et-onzième"
                    else -> {
                        if (value >= 12L) spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("etUnieme only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 2, 11, 12).bestDenominatorOrNull(value)!!) {
                    1L -> "et-unième"
                    2L -> spelloutOrdinal.format(value)
                    11L -> "et-onzième"
                    12L -> spelloutOrdinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class CentsO : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ième"
                    1L -> "-" + etUnieme.format(value)
                    in 2L..10L -> " " + spelloutOrdinal.format(value)
                    11L -> "-et-onzième"
                    else -> {
                        if (value >= 12.0) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("centsO only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ième"
                    1L -> "-" + etUnieme.format(value)
                    in 2L..10L -> " " + spelloutOrdinal.format(value)
                    11L -> "-et-onzième"
                    else -> {
                        if (value >= 12L) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("centsO only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 11, 12).bestDenominatorOrNull(value)!!) {
                    0L -> "ième"
                    1L -> "-" + etUnieme.format(value)
                    2L -> " " + spelloutOrdinal.format(value)
                    11L -> "-et-onzième"
                    12L -> " " + spelloutOrdinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SubcentsO : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ième"
                    1L -> "-" + etUnieme.format(value)
                    in 2L..10L -> "-" + spelloutOrdinal.format(value)
                    11L -> "-et-onzième"
                    else -> {
                        if (value >= 12.0) "-" + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("subcentsO only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ième"
                    1L -> "-" + etUnieme.format(value)
                    in 2L..10L -> "-" + spelloutOrdinal.format(value)
                    11L -> "-et-onzième"
                    else -> {
                        if (value >= 12L) "-" + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("subcentsO only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 11, 12).bestDenominatorOrNull(value)!!) {
                    0L -> "ième"
                    1L -> "-" + etUnieme.format(value)
                    2L -> "-" + spelloutOrdinal.format(value)
                    11L -> "-et-onzième"
                    12L -> "-" + spelloutOrdinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class MilleO : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ième"
                    1L -> "e-" + etUnieme.format(value)
                    in 2L..10L -> "e " + spelloutOrdinal.format(value)
                    11L -> "e-et-onzième"
                    else -> {
                        if (value >= 12.0) "e " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("milleO only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ième"
                    1L -> "e-" + etUnieme.format(value)
                    in 2L..10L -> "e " + spelloutOrdinal.format(value)
                    11L -> "e-et-onzième"
                    else -> {
                        if (value >= 12L) "e " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("milleO only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 11, 12).bestDenominatorOrNull(value)!!) {
                    0L -> "ième"
                    1L -> "e-" + etUnieme.format(value)
                    2L -> "e " + spelloutOrdinal.format(value)
                    11L -> "e-et-onzième"
                    12L -> "e " + spelloutOrdinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinal : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    1L -> "unième"
                    2L -> "deuxième"
                    3L -> "troisième"
                    4L -> "quatrième"
                    5L -> "cinquième"
                    6L -> "sixième"
                    7L -> "septième"
                    8L -> "huitième"
                    9L -> "neuvième"
                    10L -> "dixième"
                    11L -> "onzième"
                    12L -> "douzième"
                    13L -> "treizième"
                    14L -> "quatorzième"
                    15L -> "quinzième"
                    16L -> "seizième"
                    in 17L..19L -> "dix-" + format(value % 10)
                    20L -> "vingtième"
                    in 21L..29L -> "vingt-" + etUnieme.format(value % 10)
                    30L -> "trentième"
                    in 31L..39L -> "trente-" + etUnieme.format(value % 10)
                    40L -> "quarantième"
                    in 41L..49L -> "quarante-" + etUnieme.format(value % 10)
                    50L -> "cinquantième"
                    in 51L..59L -> "cinquante-" + etUnieme.format(value % 10)
                    60L -> "soixantième"
                    in 61L..79L -> "soixante-" + etUnieme.format(value % 20)
                    in 80L..99L -> "quatre-vingt" + subcentsO.format(value % 20)
                    in 100L..199L -> "cent" + centsO.format(value % 100)
                    in 200L..999L -> spelloutCardinalMasculine.format((value).toLong() / 100) + " cent" + centsO.format(value % 100)
                    in 1000L..1999L -> "mill" + milleO.format(value % 1000)
                    in 2000L..999999L -> spelloutLeading.format((value).toLong() / 1000) + " mill" + milleO.format(value % 1000)
                    in 1000000L..999999999L -> spelloutLeading.format((value).toLong() / 1000000) + " million" + centsO.format(value % 1000000)
                    in 1000000000L..999999999999L -> spelloutLeading.format((value).toLong() / 1000000000) + " milliard" + centsO.format(value % 1000000000)
                    in 1000000000000L..999999999999999L -> spelloutLeading.format((value).toLong() / 1000000000000) + " billion" + centsO.format(value % 1000000000000)
                    in 1000000000000000L..999999999999999999L -> spelloutLeading.format((value).toLong() / 1000000000000000) + " billiard" + centsO.format(value % 1000000000000000)
                    else -> {
                        if (value >= 1000000000000000000.0) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinal only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    1L -> "unième"
                    2L -> "deuxième"
                    3L -> "troisième"
                    4L -> "quatrième"
                    5L -> "cinquième"
                    6L -> "sixième"
                    7L -> "septième"
                    8L -> "huitième"
                    9L -> "neuvième"
                    10L -> "dixième"
                    11L -> "onzième"
                    12L -> "douzième"
                    13L -> "treizième"
                    14L -> "quatorzième"
                    15L -> "quinzième"
                    16L -> "seizième"
                    in 17L..19L -> "dix-" + format(value % 10)
                    20L -> "vingtième"
                    in 21L..29L -> "vingt-" + etUnieme.format(value % 10)
                    30L -> "trentième"
                    in 31L..39L -> "trente-" + etUnieme.format(value % 10)
                    40L -> "quarantième"
                    in 41L..49L -> "quarante-" + etUnieme.format(value % 10)
                    50L -> "cinquantième"
                    in 51L..59L -> "cinquante-" + etUnieme.format(value % 10)
                    60L -> "soixantième"
                    in 61L..79L -> "soixante-" + etUnieme.format(value % 20)
                    in 80L..99L -> "quatre-vingt" + subcentsO.format(value % 20)
                    in 100L..199L -> "cent" + centsO.format(value % 100)
                    in 200L..999L -> spelloutCardinalMasculine.format((value) / 100) + " cent" + centsO.format(value % 100)
                    in 1000L..1999L -> "mill" + milleO.format(value % 1000)
                    in 2000L..999999L -> spelloutLeading.format((value) / 1000) + " mill" + milleO.format(value % 1000)
                    in 1000000L..999999999L -> spelloutLeading.format((value) / 1000000) + " million" + centsO.format(value % 1000000)
                    in 1000000000L..999999999999L -> spelloutLeading.format((value) / 1000000000) + " milliard" + centsO.format(value % 1000000000)
                    in 1000000000000L..999999999999999L -> spelloutLeading.format((value) / 1000000000000) + " billion" + centsO.format(value % 1000000000000)
                    in 1000000000000000L..999999999999999999L -> spelloutLeading.format((value) / 1000000000000000) + " billiard" + centsO.format(value % 1000000000000000)
                    else -> {
                        if (value >= 1000000000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinal only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(
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
                    14,
                    15,
                    16,
                    17,
                    20,
                    21,
                    30,
                    31,
                    40,
                    41,
                    50,
                    51,
                    60,
                    61,
                    80,
                    100,
                    200,
                    1000,
                    2000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    1L -> "unième"
                    2L -> "deuxième"
                    3L -> "troisième"
                    4L -> "quatrième"
                    5L -> "cinquième"
                    6L -> "sixième"
                    7L -> "septième"
                    8L -> "huitième"
                    9L -> "neuvième"
                    10L -> "dixième"
                    11L -> "onzième"
                    12L -> "douzième"
                    13L -> "treizième"
                    14L -> "quatorzième"
                    15L -> "quinzième"
                    16L -> "seizième"
                    17L -> "dix-" + format(value % 10)
                    20L -> "vingtième"
                    21L -> "vingt-" + etUnieme.format(value % 10)
                    30L -> "trentième"
                    31L -> "trente-" + etUnieme.format(value % 10)
                    40L -> "quarantième"
                    41L -> "quarante-" + etUnieme.format(value % 10)
                    50L -> "cinquantième"
                    51L -> "cinquante-" + etUnieme.format(value % 10)
                    60L -> "soixantième"
                    61L -> "soixante-" + etUnieme.format(value % 20)
                    80L -> "quatre-vingt" + subcentsO.format(value % 20)
                    100L -> "cent" + centsO.format(value % 100)
                    200L -> spelloutCardinalMasculine.format((value * 200).toLong()) + " cent" + centsO.format(value % 100)
                    1000L -> "mill" + milleO.format(value % 1000)
                    2000L -> spelloutLeading.format((value * 2000).toLong()) + " mill" + milleO.format(value % 1000)
                    1000000L -> spelloutLeading.format((value * 1000000).toLong()) + " million" + centsO.format(value % 1000000)
                    1000000000L -> spelloutLeading.format((value * 1000000000).toLong()) + " milliard" + centsO.format(value % 1000000000)
                    1000000000000L -> spelloutLeading.format((value * 1000000000000).toLong()) + " billion" + centsO.format(value % 1000000000000)
                    1000000000000000L -> spelloutLeading.format((value * 1000000000000000).toLong()) + " billiard" + centsO.format(value % 1000000000000000)
                    1000000000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 3,
                        secondary_grouping = 3,
                        symbols = language.numbers.symbols
                    ).format(value)

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalMasculinePlural : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalMasculine.format(value) + "s" else {
                    throw UnsupportedOperationException("spelloutOrdinalMasculinePlural only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return if (value >= 0L) spelloutOrdinalMasculine.format(value) + "s" else {
                    throw UnsupportedOperationException("spelloutOrdinalMasculinePlural only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalMasculine.format(value) + "s"
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalMasculine : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "moins " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "moins " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "zéroième"
                    1L -> "premier"
                    else -> {
                        if (value >= 2L) spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinalMasculine only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2).bestDenominatorOrNull(value)!!) {
                    0L -> "zéroième"
                    1L -> "premier"
                    2L -> spelloutOrdinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalFemininePlural : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalFeminine.format(value) + "s" else {
                    throw UnsupportedOperationException("spelloutOrdinalFemininePlural only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return if (value >= 0L) spelloutOrdinalFeminine.format(value) + "s" else {
                    throw UnsupportedOperationException("spelloutOrdinalFemininePlural only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalFeminine.format(value) + "s"
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalFeminine : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "moins " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "moins " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "zéroième"
                    1L -> "première"
                    else -> {
                        if (value >= 2L) spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinalFeminine only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2).bestDenominatorOrNull(value)!!) {
                    0L -> "zéroième"
                    1L -> "première"
                    2L -> spelloutOrdinal.format(value)
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
        val digitsOrdinalMasculine = DigitsOrdinalMasculine()

        val digitsOrdinalFeminine = DigitsOrdinalFeminine()

        val digitsOrdinalMasculinePlural = DigitsOrdinalMasculinePlural()

        val digitsOrdinalFemininePlural = DigitsOrdinalFemininePlural()

        val digitsOrdinal = DigitsOrdinal()

        private inner class DigitsOrdinalMasculine : NumberFormatter {
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
                ).format(value) + Plurals(one = "er", other = "e").get(language.pluralFormChooser.pluralForm(((value).toLong() / 1).toString())) else {
                    throw UnsupportedOperationException("digitsOrdinalMasculine only supports numbers >= 0")
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
                ).format(value) + Plurals(one = "er", other = "e").get(language.pluralFormChooser.pluralForm(((value) / 1).toString())) else {
                    throw UnsupportedOperationException("digitsOrdinalMasculine only supports numbers >= 0")
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
                    ).format(value) + Plurals(one = "er", other = "e").get(language.pluralFormChooser.pluralForm(((value * 0).toLong()).toString()))

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalFeminine : NumberFormatter {
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
                ).format(value) + Plurals(one = "re", other = "e").get(language.pluralFormChooser.pluralForm(((value).toLong() / 1).toString())) else {
                    throw UnsupportedOperationException("digitsOrdinalFeminine only supports numbers >= 0")
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
                ).format(value) + Plurals(one = "re", other = "e").get(language.pluralFormChooser.pluralForm(((value) / 1).toString())) else {
                    throw UnsupportedOperationException("digitsOrdinalFeminine only supports numbers >= 0")
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
                    ).format(value) + Plurals(one = "re", other = "e").get(language.pluralFormChooser.pluralForm(((value * 0).toLong()).toString()))

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalMasculinePlural : NumberFormatter {
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
                ).format(value) + Plurals(one = "ers", other = "es").get(language.pluralFormChooser.pluralForm(((value).toLong() / 1).toString())) else {
                    throw UnsupportedOperationException("digitsOrdinalMasculinePlural only supports numbers >= 0")
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
                ).format(value) + Plurals(one = "ers", other = "es").get(language.pluralFormChooser.pluralForm(((value) / 1).toString())) else {
                    throw UnsupportedOperationException("digitsOrdinalMasculinePlural only supports numbers >= 0")
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
                    ).format(value) + Plurals(one = "ers", other = "es").get(language.pluralFormChooser.pluralForm(((value * 0).toLong()).toString()))

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalFemininePlural : NumberFormatter {
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
                ).format(value) + Plurals(one = "res", other = "es").get(language.pluralFormChooser.pluralForm(((value).toLong() / 1).toString())) else {
                    throw UnsupportedOperationException("digitsOrdinalFemininePlural only supports numbers >= 0")
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
                ).format(value) + Plurals(one = "res", other = "es").get(language.pluralFormChooser.pluralForm(((value) / 1).toString())) else {
                    throw UnsupportedOperationException("digitsOrdinalFemininePlural only supports numbers >= 0")
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
                    ).format(value) + Plurals(one = "res", other = "es").get(language.pluralFormChooser.pluralForm(((value * 0).toLong()).toString()))

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinal : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) digitsOrdinalMasculine.format(value) else {
                    throw UnsupportedOperationException("digitsOrdinal only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return digitsOrdinalMasculine.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> digitsOrdinalMasculine.format(value)
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
    open val spelloutCardinalMasculine: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculine
    open val spelloutCardinalFeminine: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminine
    open val spelloutOrdinalMasculinePlural: NumberFormatter
        get() = spelloutRules.spelloutOrdinalMasculinePlural
    open val spelloutOrdinalMasculine: NumberFormatter
        get() = spelloutRules.spelloutOrdinalMasculine
    open val spelloutOrdinalFemininePlural: NumberFormatter
        get() = spelloutRules.spelloutOrdinalFemininePlural
    open val spelloutOrdinalFeminine: NumberFormatter
        get() = spelloutRules.spelloutOrdinalFeminine
    open val digitsOrdinalMasculine: NumberFormatter
        get() = ordinalRules.digitsOrdinalMasculine
    open val digitsOrdinalFeminine: NumberFormatter
        get() = ordinalRules.digitsOrdinalFeminine
    open val digitsOrdinalMasculinePlural: NumberFormatter
        get() = ordinalRules.digitsOrdinalMasculinePlural
    open val digitsOrdinalFemininePlural: NumberFormatter
        get() = ordinalRules.digitsOrdinalFemininePlural
    open val digitsOrdinal: NumberFormatter
        get() = ordinalRules.digitsOrdinal
    override val spelloutCardinal: NumberFormatter
        get() = TODO("spelloutCardinal is not available for fr")
    override val spelloutOrdinal: NumberFormatter
        get() = TODO("spelloutOrdinal is not available for fr")

    fun spelloutCardinalFor(gender: Gender): NumberFormatter {
        return when (gender) {
            Gender.Feminine -> spelloutCardinalFeminine
            Gender.Masculine -> spelloutCardinalMasculine
            else -> TODO("fr does not support $gender gender")
        }

    }

    fun spelloutOrdinalFor(gender: Gender): NumberFormatter {
        return when (gender) {
            Gender.Feminine -> spelloutOrdinalFeminine
            Gender.Masculine -> spelloutOrdinalMasculine
            else -> TODO("fr does not support $gender gender")
        }

    }

    fun digitsOrdinalFor(gender: Gender): NumberFormatter {
        return when (gender) {
            Gender.Feminine -> digitsOrdinalFeminine
            Gender.Masculine -> digitsOrdinalMasculine
            else -> TODO("fr does not support $gender gender")
        }

    }
}
