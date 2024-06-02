package info.leonenko.i18n.rbnf

import kotlin.math.roundToLong
import info.leonenko.i18n.DecimalNumberFormatter
import info.leonenko.i18n.Language
import info.leonenko.i18n.RBNF
import info.leonenko.i18n.NumberFormatter
import info.leonenko.i18n.Case
import info.leonenko.i18n.Gender
import info.leonenko.i18n.languages.es
import info.leonenko.i18n.formatFractionalPartByDigit
import info.leonenko.i18n.bestDenominatorOrNull
import info.leonenko.i18n.leadingFractionalZeroesCount
import info.leonenko.i18n.Plurals
import info.leonenko.i18n.until
import info.leonenko.i18n.contains


open class RBNFEs(val language: Language = Language.es) : RBNF {
    private inner class SpelloutRules {
        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumbering = SpelloutNumbering()

        val spelloutCardinalMasculine = SpelloutCardinalMasculine()

        val spelloutCardinalFeminine = SpelloutCardinalFeminine()

        val spelloutOrdinalMasculineAdjective = SpelloutOrdinalMasculineAdjective()

        val spelloutOrdinalMasculinePlural = SpelloutOrdinalMasculinePlural()

        val spelloutOrdinalMasculine = SpelloutOrdinalMasculine()

        val spelloutOrdinalFemininePlural = SpelloutOrdinalFemininePlural()

        val spelloutOrdinalFeminine = SpelloutOrdinalFeminine()

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
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "cero"
                    1L -> "uno"
                    2L -> "dos"
                    3L -> "tres"
                    4L -> "cuatro"
                    5L -> "cinco"
                    6L -> "seis"
                    7L -> "siete"
                    8L -> "ocho"
                    9L -> "nueve"
                    10L -> "diez"
                    11L -> "once"
                    12L -> "doce"
                    13L -> "trece"
                    14L -> "catorce"
                    15L -> "quince"
                    16L -> "dieciséis"
                    in 17L..19L -> "dieci" + format(value % 10)
                    20L -> "veinte"
                    21L -> "veintiuno"
                    22L -> "veintidós"
                    23L -> "veintitrés"
                    24L -> "veinticuatro"
                    25L -> "veinticinco"
                    26L -> "veintiséis"
                    in 27L..29L -> "veinti" + format(value % 10)
                    in 30L..39L -> {
                        var result = "treinta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "cuarenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "cincuenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "sesenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "setenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "ochenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "noventa"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    100L -> "cien"
                    in 101L..199L -> "ciento " + format(value % 100)
                    in 200L..299L -> {
                        var result = "doscientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "trescientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "cuatrocientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "quinientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "seiscientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "setecientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "ochocientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "novecientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "mil"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " mil"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "un millón"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " millones"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "un billón"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " billones"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
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
                            throw UnsupportedOperationException("spelloutNumbering only supports numbers >= 0")
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
                    14,
                    15,
                    16,
                    17,
                    20,
                    21,
                    22,
                    23,
                    24,
                    25,
                    26,
                    27,
                    30,
                    40,
                    50,
                    60,
                    70,
                    80,
                    90,
                    100,
                    101,
                    200,
                    300,
                    400,
                    500,
                    600,
                    700,
                    800,
                    900,
                    1000,
                    2000,
                    1000000,
                    2000000,
                    1000000000000,
                    2000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "cero"
                    1L -> "uno"
                    2L -> "dos"
                    3L -> "tres"
                    4L -> "cuatro"
                    5L -> "cinco"
                    6L -> "seis"
                    7L -> "siete"
                    8L -> "ocho"
                    9L -> "nueve"
                    10L -> "diez"
                    11L -> "once"
                    12L -> "doce"
                    13L -> "trece"
                    14L -> "catorce"
                    15L -> "quince"
                    16L -> "dieciséis"
                    17L -> "dieci" + format(value % 10)
                    20L -> "veinte"
                    21L -> "veintiuno"
                    22L -> "veintidós"
                    23L -> "veintitrés"
                    24L -> "veinticuatro"
                    25L -> "veinticinco"
                    26L -> "veintiséis"
                    27L -> "veinti" + format(value % 10)
                    30L -> {
                        var result = "treinta"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "cuarenta"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "cincuenta"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "sesenta"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "setenta"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "ochenta"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "noventa"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    100L -> "cien"
                    101L -> "ciento " + format(value % 100)
                    200L -> {
                        var result = "doscientos"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = "trescientos"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> {
                        var result = "cuatrocientos"
                        result += (if (value * 400 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = "quinientos"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> {
                        var result = "seiscientos"
                        result += (if (value * 600 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> {
                        var result = "setecientos"
                        result += (if (value * 700 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> {
                        var result = "ochocientos"
                        result += (if (value * 800 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> {
                        var result = "novecientos"
                        result += (if (value * 900 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = "mil"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000).toLong())
                        result += " mil"
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "un millón"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000).toLong())
                        result += " millones"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "un billón"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000).toLong())
                        result += " billones"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
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
                return when (language.numbers.symbols.decimalSymbol) {
                    "," -> format(kotlin.math.truncate(value).toLong()) + " coma " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
                    else -> format(kotlin.math.truncate(value).toLong()) + " punto " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
                }
            }
        }

        private inner class SpelloutCardinalMasculine : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "cero"
                    1L -> "un"
                    in 2L..20L -> spelloutNumbering.format(value)
                    21L -> "veintiún"
                    in 22L..29L -> spelloutNumbering.format(value)
                    in 30L..39L -> {
                        var result = "treinta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "cuarenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "cincuenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "sesenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "setenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "ochenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "noventa"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    100L -> "cien"
                    in 101L..199L -> "ciento " + format(value % 100)
                    in 200L..299L -> {
                        var result = "doscientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "trescientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "cuatrocientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "quinientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "seis­cientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "sete­cientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "ocho­cientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "nove­cientos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "mil"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = format((value) / 1000)
                        result += " mil"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "un millón"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999999L -> {
                        var result = format((value) / 1000000)
                        result += " millones"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "un billón"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " billones"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
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

            fun formatF(value: Double): String {
                return when (listOf<Long>(
                    0,
                    1,
                    2,
                    21,
                    22,
                    30,
                    40,
                    50,
                    60,
                    70,
                    80,
                    90,
                    100,
                    101,
                    200,
                    300,
                    400,
                    500,
                    600,
                    700,
                    800,
                    900,
                    1000,
                    2000,
                    1000000,
                    2000000,
                    1000000000000,
                    2000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "cero"
                    1L -> "un"
                    2L -> spelloutNumbering.format(value)
                    21L -> "veintiún"
                    22L -> spelloutNumbering.format(value)
                    30L -> {
                        var result = "treinta"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "cuarenta"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "cincuenta"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "sesenta"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "setenta"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "ochenta"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "noventa"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    100L -> "cien"
                    101L -> "ciento " + format(value % 100)
                    200L -> {
                        var result = "doscientos"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = "trescientos"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> {
                        var result = "cuatrocientos"
                        result += (if (value * 400 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = "quinientos"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> {
                        var result = "seis­cientos"
                        result += (if (value * 600 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> {
                        var result = "sete­cientos"
                        result += (if (value * 700 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> {
                        var result = "ocho­cientos"
                        result += (if (value * 800 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> {
                        var result = "nove­cientos"
                        result += (if (value * 900 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = "mil"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = format((value * 2000).toLong())
                        result += " mil"
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "un millón"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = format((value * 2000000).toLong())
                        result += " millones"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "un billón"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = format((value * 2000000000000).toLong())
                        result += " billones"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
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
                return when (language.numbers.symbols.decimalSymbol) {
                    "," -> format(kotlin.math.truncate(value).toLong()) + " coma " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
                    else -> format(kotlin.math.truncate(value).toLong()) + " punto " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
                }
            }
        }

        private inner class SpelloutCardinalFeminine : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "cero"
                    1L -> "una"
                    in 2L..20L -> spelloutNumbering.format(value)
                    21L -> "veintiuna"
                    in 22L..29L -> spelloutNumbering.format(value)
                    in 30L..39L -> {
                        var result = "treinta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "cuarenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "cincuenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "sesenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "setenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "ochenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "noventa"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    100L -> "cien"
                    in 101L..199L -> "ciento " + format(value % 100)
                    in 200L..299L -> {
                        var result = "dos­cientas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "tres­cientas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "cuatro­cientas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "quinientas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "seis­cientas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "sete­cientas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "ocho­cientas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "nove­cientas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "mil"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " mil"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "un millón"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " millones"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "un billón"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " billones"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
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
                    21,
                    22,
                    30,
                    40,
                    50,
                    60,
                    70,
                    80,
                    90,
                    100,
                    101,
                    200,
                    300,
                    400,
                    500,
                    600,
                    700,
                    800,
                    900,
                    1000,
                    2000,
                    1000000,
                    2000000,
                    1000000000000,
                    2000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "cero"
                    1L -> "una"
                    2L -> spelloutNumbering.format(value)
                    21L -> "veintiuna"
                    22L -> spelloutNumbering.format(value)
                    30L -> {
                        var result = "treinta"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "cuarenta"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "cincuenta"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "sesenta"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "setenta"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "ochenta"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "noventa"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " y " + format(value % 10)
                        })
                        result
                    }

                    100L -> "cien"
                    101L -> "ciento " + format(value % 100)
                    200L -> {
                        var result = "dos­cientas"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = "tres­cientas"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> {
                        var result = "cuatro­cientas"
                        result += (if (value * 400 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = "quinientas"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> {
                        var result = "seis­cientas"
                        result += (if (value * 600 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> {
                        var result = "sete­cientas"
                        result += (if (value * 700 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> {
                        var result = "ocho­cientas"
                        result += (if (value * 800 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> {
                        var result = "nove­cientas"
                        result += (if (value * 900 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = "mil"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000).toLong())
                        result += " mil"
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "un millón"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000).toLong())
                        result += " millones"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "un billón"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000).toLong())
                        result += " billones"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
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
                return when (language.numbers.symbols.decimalSymbol) {
                    "," -> format(kotlin.math.truncate(value).toLong()) + " coma " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
                    else -> format(kotlin.math.truncate(value).toLong()) + " punto " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
                }
            }
        }

        private inner class SpelloutOrdinalMasculineAdjective : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "cero"
                    1L -> "primer"
                    2L -> "segundo"
                    3L -> "tercer"
                    4L -> "cuarto"
                    5L -> "quinto"
                    6L -> "sexto"
                    7L -> "séptimo"
                    8L -> "octavo"
                    9L -> "noveno"
                    10L -> "décimo"
                    11L -> "undécimo"
                    12L -> "duodécimo"
                    in 13L..17L -> "decimo" + format(value % 10)
                    18L -> "decim" + format(value % 10)
                    19L -> "decimo" + format(value % 10)
                    in 20L..29L -> {
                        var result = "vigésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "trigésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "cuadragésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "quincuagésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "sexagésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "septuagésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "octogésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "nonagésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "centésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "ducentésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "tricentésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "cuadringentésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "quingentésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "sexcentésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "septingentésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "octingésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "noningentésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "milésimo"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " milésimo"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "un millonésimo"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " millonésimo"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "un billonésimo"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " billonésimo"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
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
                        ).format(value) + "º" else {
                            throw UnsupportedOperationException("spelloutOrdinalMasculineAdjective only supports numbers >= 0")
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
                    18,
                    19,
                    20,
                    30,
                    40,
                    50,
                    60,
                    70,
                    80,
                    90,
                    100,
                    200,
                    300,
                    400,
                    500,
                    600,
                    700,
                    800,
                    900,
                    1000,
                    2000,
                    1000000,
                    2000000,
                    1000000000000,
                    2000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "cero"
                    1L -> "primer"
                    2L -> "segundo"
                    3L -> "tercer"
                    4L -> "cuarto"
                    5L -> "quinto"
                    6L -> "sexto"
                    7L -> "séptimo"
                    8L -> "octavo"
                    9L -> "noveno"
                    10L -> "décimo"
                    11L -> "undécimo"
                    12L -> "duodécimo"
                    13L -> "decimo" + format(value % 10)
                    18L -> "decim" + format(value % 10)
                    19L -> "decimo" + format(value % 10)
                    20L -> {
                        var result = "vigésimo"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "trigésimo"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "cuadragésimo"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "quincuagésimo"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "sexagésimo"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "septuagésimo"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "octogésimo"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "nonagésimo"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "centésimo"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = "ducentésimo"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = "tricentésimo"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> {
                        var result = "cuadringentésimo"
                        result += (if (value * 400 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = "quingentésimo"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> {
                        var result = "sexcentésimo"
                        result += (if (value * 600 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> {
                        var result = "septingentésimo"
                        result += (if (value * 700 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> {
                        var result = "octingésimo"
                        result += (if (value * 800 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> {
                        var result = "noningentésimo"
                        result += (if (value * 900 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = "milésimo"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000).toLong())
                        result += " milésimo"
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "un millonésimo"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000).toLong())
                        result += " millonésimo"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "un billonésimo"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000).toLong())
                        result += " billonésimo"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
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
                    ).format(value) + "º"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalMasculinePlural : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> spelloutOrdinalMasculine.format(value)
                    in 1L..999999999999999999L -> spelloutOrdinalMasculine.format(value) + "s"
                    else -> {
                        if (value >= 1000000000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) + "º" else {
                            throw UnsupportedOperationException("spelloutOrdinalMasculinePlural only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 1000000000000000000).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalMasculine.format(value)
                    1L -> spelloutOrdinalMasculine.format(value) + "s"
                    1000000000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 3,
                        secondary_grouping = 3,
                        symbols = language.numbers.symbols
                    ).format(value) + "º"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalMasculine : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "cero"
                    1L -> "primero"
                    2L -> "segundo"
                    3L -> "tercero"
                    4L -> "cuarto"
                    5L -> "quinto"
                    6L -> "sexto"
                    7L -> "séptimo"
                    8L -> "octavo"
                    9L -> "noveno"
                    10L -> "décimo"
                    in 11L..17L -> "decimo" + format(value % 10)
                    18L -> "decim" + format(value % 10)
                    19L -> "decimo" + format(value % 10)
                    in 20L..29L -> {
                        var result = "vigésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "trigésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "cuadragésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "quincuagésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "sexagésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "septuagésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "octogésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "nonagésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "centésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "ducentésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "tricentésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "cuadringentésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "quingentésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "sexcentésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "septingentésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "octingésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "noningentésimo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "milésimo"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " milésimo"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "un millonésimo"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " millonésimo"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "un billonésimo"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " billonésimo"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
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
                        ).format(value) + "º" else {
                            throw UnsupportedOperationException("spelloutOrdinalMasculine only supports numbers >= 0")
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
                    18,
                    19,
                    20,
                    30,
                    40,
                    50,
                    60,
                    70,
                    80,
                    90,
                    100,
                    200,
                    300,
                    400,
                    500,
                    600,
                    700,
                    800,
                    900,
                    1000,
                    2000,
                    1000000,
                    2000000,
                    1000000000000,
                    2000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "cero"
                    1L -> "primero"
                    2L -> "segundo"
                    3L -> "tercero"
                    4L -> "cuarto"
                    5L -> "quinto"
                    6L -> "sexto"
                    7L -> "séptimo"
                    8L -> "octavo"
                    9L -> "noveno"
                    10L -> "décimo"
                    11L -> "decimo" + format(value % 10)
                    18L -> "decim" + format(value % 10)
                    19L -> "decimo" + format(value % 10)
                    20L -> {
                        var result = "vigésimo"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "trigésimo"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "cuadragésimo"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "quincuagésimo"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "sexagésimo"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "septuagésimo"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "octogésimo"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "nonagésimo"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "centésimo"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = "ducentésimo"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = "tricentésimo"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> {
                        var result = "cuadringentésimo"
                        result += (if (value * 400 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = "quingentésimo"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> {
                        var result = "sexcentésimo"
                        result += (if (value * 600 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> {
                        var result = "septingentésimo"
                        result += (if (value * 700 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> {
                        var result = "octingésimo"
                        result += (if (value * 800 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> {
                        var result = "noningentésimo"
                        result += (if (value * 900 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = "milésimo"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000).toLong())
                        result += " milésimo"
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "un millonésimo"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000).toLong())
                        result += " millonésimo"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "un billonésimo"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000).toLong())
                        result += " billonésimo"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
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
                    ).format(value) + "º"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalFemininePlural : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> spelloutOrdinalFeminine.format(value)
                    in 1L..999999999999999999L -> spelloutOrdinalFeminine.format(value) + "s"
                    else -> {
                        if (value >= 1000000000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) + "ª" else {
                            throw UnsupportedOperationException("spelloutOrdinalFemininePlural only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 1000000000000000000).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalFeminine.format(value)
                    1L -> spelloutOrdinalFeminine.format(value) + "s"
                    1000000000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 3,
                        secondary_grouping = 3,
                        symbols = language.numbers.symbols
                    ).format(value) + "ª"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalFeminine : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "menos " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "cero"
                    1L -> "primera"
                    2L -> "segunda"
                    3L -> "tercera"
                    4L -> "cuarta"
                    5L -> "quinta"
                    6L -> "sexta"
                    7L -> "séptima"
                    8L -> "octava"
                    9L -> "novena"
                    10L -> "décima"
                    in 11L..17L -> "decimo" + format(value % 10)
                    18L -> "decim" + format(value % 10)
                    19L -> "decimo" + format(value % 10)
                    in 20L..29L -> {
                        var result = "vigésima"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "trigésima"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "cuadragésima"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "quincuagésima"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "sexagésima"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "septuagésima"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "octogésima"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "nonagésima"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "centésima"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "ducentésima"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "tricentésima"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "cuadringentésima"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "quingentésima"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "sexcentésima"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "septingentésima"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "octingésima"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "noningentésima"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "milésima"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " milésima"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "un millonésima"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " millonésima"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "un billonésima"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " billonésima"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
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
                        ).format(value) + "ª" else {
                            throw UnsupportedOperationException("spelloutOrdinalFeminine only supports numbers >= 0")
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
                    18,
                    19,
                    20,
                    30,
                    40,
                    50,
                    60,
                    70,
                    80,
                    90,
                    100,
                    200,
                    300,
                    400,
                    500,
                    600,
                    700,
                    800,
                    900,
                    1000,
                    2000,
                    1000000,
                    2000000,
                    1000000000000,
                    2000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "cero"
                    1L -> "primera"
                    2L -> "segunda"
                    3L -> "tercera"
                    4L -> "cuarta"
                    5L -> "quinta"
                    6L -> "sexta"
                    7L -> "séptima"
                    8L -> "octava"
                    9L -> "novena"
                    10L -> "décima"
                    11L -> "decimo" + format(value % 10)
                    18L -> "decim" + format(value % 10)
                    19L -> "decimo" + format(value % 10)
                    20L -> {
                        var result = "vigésima"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "trigésima"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "cuadragésima"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "quincuagésima"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "sexagésima"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "septuagésima"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "octogésima"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "nonagésima"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "centésima"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = "ducentésima"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = "tricentésima"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> {
                        var result = "cuadringentésima"
                        result += (if (value * 400 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = "quingentésima"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> {
                        var result = "sexcentésima"
                        result += (if (value * 600 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> {
                        var result = "septingentésima"
                        result += (if (value * 700 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> {
                        var result = "octingésima"
                        result += (if (value * 800 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> {
                        var result = "noningentésima"
                        result += (if (value * 900 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = "milésima"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000).toLong())
                        result += " milésima"
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "un millonésima"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000).toLong())
                        result += " millonésima"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "un billonésima"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000).toLong())
                        result += " billonésima"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
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
                    ).format(value) + "ª"

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
        val dordMascabbrev = DordMascabbrev()

        val digitsOrdinalMasculineAdjective = DigitsOrdinalMasculineAdjective()

        val digitsOrdinalMasculine = DigitsOrdinalMasculine()

        val digitsOrdinalFeminine = DigitsOrdinalFeminine()

        val digitsOrdinalMasculinePlural = DigitsOrdinalMasculinePlural()

        val digitsOrdinalFemininePlural = DigitsOrdinalFemininePlural()

        val digitsOrdinal = DigitsOrdinal()

        private inner class DordMascabbrev : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "º"
                    1L -> "ᵉʳ"
                    2L -> "º"
                    3L -> "ᵉʳ"
                    in 4L..19L -> "º"
                    in 20L..99L -> format(value % 10)
                    else -> {
                        if (value >= 100.0) format(value % 100) else {
                            throw UnsupportedOperationException("dordMascabbrev only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "º"
                    1L -> "ᵉʳ"
                    2L -> "º"
                    3L -> "ᵉʳ"
                    in 4L..19L -> "º"
                    in 20L..99L -> format(value % 10)
                    else -> {
                        if (value >= 100L) format(value % 100) else {
                            throw UnsupportedOperationException("dordMascabbrev only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 3, 4, 20, 100).bestDenominatorOrNull(value)!!) {
                    0L -> "º"
                    1L -> "ᵉʳ"
                    2L -> "º"
                    3L -> "ᵉʳ"
                    4L -> "º"
                    20L -> format(value % 10)
                    100L -> format(value % 100)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalMasculineAdjective : NumberFormatter {
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
                ).format(value) + "." + dordMascabbrev.format(value) else {
                    throw UnsupportedOperationException("digitsOrdinalMasculineAdjective only supports numbers >= 0")
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
                ).format(value) + "." + dordMascabbrev.format(value) else {
                    throw UnsupportedOperationException("digitsOrdinalMasculineAdjective only supports numbers >= 0")
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
                    ).format(value) + "." + dordMascabbrev.format(value)

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

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
                ).format(value) + ".º" else {
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
                ).format(value) + ".º" else {
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
                    ).format(value) + ".º"

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
                ).format(value) + ".ª" else {
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
                ).format(value) + ".ª" else {
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
                    ).format(value) + ".ª"

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
                ).format(value) + ".ᵒˢ" else {
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
                ).format(value) + ".ᵒˢ" else {
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
                    ).format(value) + ".ᵒˢ"

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
                ).format(value) + ".ᵃˢ" else {
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
                ).format(value) + ".ᵃˢ" else {
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
                    ).format(value) + ".ᵃˢ"

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
    open val spelloutOrdinalMasculineAdjective: NumberFormatter
        get() = spelloutRules.spelloutOrdinalMasculineAdjective
    open val spelloutOrdinalMasculinePlural: NumberFormatter
        get() = spelloutRules.spelloutOrdinalMasculinePlural
    open val spelloutOrdinalMasculine: NumberFormatter
        get() = spelloutRules.spelloutOrdinalMasculine
    open val spelloutOrdinalFemininePlural: NumberFormatter
        get() = spelloutRules.spelloutOrdinalFemininePlural
    open val spelloutOrdinalFeminine: NumberFormatter
        get() = spelloutRules.spelloutOrdinalFeminine
    open val digitsOrdinalMasculineAdjective: NumberFormatter
        get() = ordinalRules.digitsOrdinalMasculineAdjective
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
        get() = TODO("spelloutCardinal is not available for es")
    override val spelloutOrdinal: NumberFormatter
        get() = TODO("spelloutOrdinal is not available for es")

    fun spelloutCardinalFor(gender: Gender): NumberFormatter {
        return when (gender) {
            Gender.Feminine -> spelloutCardinalFeminine
            Gender.Masculine -> spelloutCardinalMasculine
            else -> TODO("es does not support $gender gender")
        }

    }

    fun spelloutOrdinalFor(gender: Gender): NumberFormatter {
        return when (gender) {
            Gender.Feminine -> spelloutOrdinalFeminine
            Gender.Masculine -> spelloutOrdinalMasculine
            else -> TODO("es does not support $gender gender")
        }

    }

    fun digitsOrdinalFor(gender: Gender): NumberFormatter {
        return when (gender) {
            Gender.Feminine -> digitsOrdinalFeminine
            Gender.Masculine -> digitsOrdinalMasculine
            else -> TODO("es does not support $gender gender")
        }

    }
}
