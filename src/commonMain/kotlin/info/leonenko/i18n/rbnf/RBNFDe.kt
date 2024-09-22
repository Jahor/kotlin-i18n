package info.leonenko.i18n.rbnf

import info.leonenko.i18n.*
import info.leonenko.i18n.languages.de
import kotlin.math.roundToLong


open class RBNFDe(val language: Language = Language.de) : RBNF {
    private inner class SpelloutRules {
        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumbering = SpelloutNumbering()

        val spelloutCardinalNeuter = SpelloutCardinalNeuter()

        val spelloutCardinalMasculine = SpelloutCardinalMasculine()

        val spelloutCardinalFeminine = SpelloutCardinalFeminine()

        val spelloutCardinalN = SpelloutCardinalN()

        val spelloutCardinalR = SpelloutCardinalR()

        val spelloutCardinalS = SpelloutCardinalS()

        val spelloutCardinalM = SpelloutCardinalM()

        val ste = Ste()

        val ste2 = Ste2()

        val spelloutOrdinal = SpelloutOrdinal()

        val spelloutOrdinalN = SpelloutOrdinalN()

        val spelloutOrdinalR = SpelloutOrdinalR()

        val spelloutOrdinalS = SpelloutOrdinalS()

        val spelloutOrdinalM = SpelloutOrdinalM()

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
                    in 0L..1099L -> spelloutNumbering.format(value)
                    in 1100L..1999L -> {
                        var result = format((value) / 100)
                        result += "­hundert"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    else -> {
                        if (value >= 2000L) spelloutNumbering.format(value) else {
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
                    0L -> "null"
                    1L -> "eins"
                    2L -> "zwei"
                    3L -> "drei"
                    4L -> "vier"
                    5L -> "fünf"
                    6L -> "sechs"
                    7L -> "sieben"
                    8L -> "acht"
                    9L -> "neun"
                    10L -> "zehn"
                    11L -> "elf"
                    12L -> "zwölf"
                    in 13L..15L -> format(value % 10) + "zehn"
                    16L -> "sechzehn"
                    17L -> "siebzehn"
                    in 18L..19L -> format(value % 10) + "zehn"
                    in 20L..29L -> {
                        var result = (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "zwanzig"
                        result
                    }

                    in 30L..39L -> {
                        var result = (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "dreißig"
                        result
                    }

                    in 40L..49L -> {
                        var result = (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "vierzig"
                        result
                    }

                    in 50L..59L -> {
                        var result = (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "fünfzig"
                        result
                    }

                    in 60L..69L -> {
                        var result = (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "sechzig"
                        result
                    }

                    in 70L..79L -> {
                        var result = (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "siebzig"
                        result
                    }

                    in 80L..89L -> {
                        var result = (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "achtzig"
                        result
                    }

                    in 90L..99L -> {
                        var result = (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "neunzig"
                        result
                    }

                    in 100L..999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 100)
                        result += "­hundert"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += "­tausend"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "eine Million"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000)
                        result += " Millionen"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "eine Milliarde"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000)
                        result += " Milliarden"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "eine Billion"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000)
                        result += " Billionen"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "eine Billiarde"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000000)
                        result += " Billiarden"
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
                    16,
                    17,
                    18,
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
                    2000000,
                    1000000000,
                    2000000000,
                    1000000000000,
                    2000000000000,
                    1000000000000000,
                    2000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "null"
                    1L -> "eins"
                    2L -> "zwei"
                    3L -> "drei"
                    4L -> "vier"
                    5L -> "fünf"
                    6L -> "sechs"
                    7L -> "sieben"
                    8L -> "acht"
                    9L -> "neun"
                    10L -> "zehn"
                    11L -> "elf"
                    12L -> "zwölf"
                    13L -> format(value % 10) + "zehn"
                    16L -> "sechzehn"
                    17L -> "siebzehn"
                    18L -> format(value % 10) + "zehn"
                    20L -> {
                        var result = (if (value * 20 == 1.0) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "zwanzig"
                        result
                    }

                    30L -> {
                        var result = (if (value * 30 == 1.0) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "dreißig"
                        result
                    }

                    40L -> {
                        var result = (if (value * 40 == 1.0) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "vierzig"
                        result
                    }

                    50L -> {
                        var result = (if (value * 50 == 1.0) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "fünfzig"
                        result
                    }

                    60L -> {
                        var result = (if (value * 60 == 1.0) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "sechzig"
                        result
                    }

                    70L -> {
                        var result = (if (value * 70 == 1.0) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "siebzig"
                        result
                    }

                    80L -> {
                        var result = (if (value * 80 == 1.0) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "achtzig"
                        result
                    }

                    90L -> {
                        var result = (if (value * 90 == 1.0) {
                            ""
                        } else {
                            spelloutCardinalMasculine.format(value % 10) + "­und­"
                        })
                        result += "neunzig"
                        result
                    }

                    100L -> {
                        var result = spelloutCardinalMasculine.format((value * 100).toLong())
                        result += "­hundert"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000).toLong())
                        result += "­tausend"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "eine Million"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000).toLong())
                        result += " Millionen"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = "eine Milliarde"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000).toLong())
                        result += " Milliarden"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "eine Billion"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000000).toLong())
                        result += " Billionen"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = "eine Billiarde"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000000000).toLong())
                        result += " Billiarden"
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
                return format(kotlin.math.truncate(value).toLong()) + " Komma " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalNeuter : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutCardinalMasculine.format(value) else {
                    throw UnsupportedOperationException("spelloutCardinalNeuter only supports numbers >= 0")
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

        private inner class SpelloutCardinalMasculine : NumberFormatter {
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
                    0L -> "null"
                    1L -> "ein"
                    in 2L..99L -> spelloutNumbering.format(value)
                    in 100L..999L -> {
                        var result = format((value) / 100)
                        result += "­hundert"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = format((value) / 1000)
                        result += "­tausend"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "eine Million"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000)
                        result += " Millionen"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "eine Milliarde"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000)
                        result += " Milliarden"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "eine Billion"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000)
                        result += " Billionen"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "eine Billiarde"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000000)
                        result += " Billiarden"
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

            fun formatF(value: Double): String {
                return when (listOf<Long>(
                    0,
                    1,
                    2,
                    100,
                    1000,
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
                    0L -> "null"
                    1L -> "ein"
                    2L -> spelloutNumbering.format(value)
                    100L -> {
                        var result = format((value * 100).toLong())
                        result += "­hundert"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = format((value * 1000).toLong())
                        result += "­tausend"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "eine Million"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000).toLong())
                        result += " Millionen"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = "eine Milliarde"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000).toLong())
                        result += " Milliarden"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "eine Billion"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000000).toLong())
                        result += " Billionen"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = "eine Billiarde"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000000000).toLong())
                        result += " Billiarden"
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
                return format(kotlin.math.truncate(value).toLong()) + " Komma " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalFeminine : NumberFormatter {
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
                    0L -> "null"
                    1L -> "eine"
                    in 2L..99L -> spelloutNumbering.format(value)
                    in 100L..999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 100)
                        result += "­hundert"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += "­tausend"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "eine Million"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = format((value) / 1000000)
                        result += " Millionen"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "eine Milliarde"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = format((value) / 1000000000)
                        result += " Milliarden"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "eine Billion"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " Billionen"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "eine Billiarde"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += " Billiarden"
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
                    100,
                    1000,
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
                    0L -> "null"
                    1L -> "eine"
                    2L -> spelloutNumbering.format(value)
                    100L -> {
                        var result = spelloutCardinalMasculine.format((value * 100).toLong())
                        result += "­hundert"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000).toLong())
                        result += "­tausend"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "eine Million"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = format((value * 2000000).toLong())
                        result += " Millionen"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = "eine Milliarde"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = format((value * 2000000000).toLong())
                        result += " Milliarden"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "eine Billion"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = format((value * 2000000000000).toLong())
                        result += " Billionen"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = "eine Billiarde"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = format((value * 2000000000000000).toLong())
                        result += " Billiarden"
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
                return format(kotlin.math.truncate(value).toLong()) + " Komma " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalN : NumberFormatter {
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
                    0L -> "null"
                    1L -> "einen"
                    in 2L..99L -> spelloutNumbering.format(value)
                    in 100L..999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 100)
                        result += "­hundert"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += "­tausend"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "eine Million"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000)
                        result += " Millionen"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "eine Milliarde"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000)
                        result += " Milliarden"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "eine Billion"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000)
                        result += " Billionen"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "eine Billiarde"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000000)
                        result += " Billiarden"
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
                            throw UnsupportedOperationException("spelloutCardinalN only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(
                    0,
                    1,
                    2,
                    100,
                    1000,
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
                    0L -> "null"
                    1L -> "einen"
                    2L -> spelloutNumbering.format(value)
                    100L -> {
                        var result = spelloutCardinalMasculine.format((value * 100).toLong())
                        result += "­hundert"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000).toLong())
                        result += "­tausend"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "eine Million"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000).toLong())
                        result += " Millionen"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = "eine Milliarde"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000).toLong())
                        result += " Milliarden"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "eine Billion"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000000).toLong())
                        result += " Billionen"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = "eine Billiarde"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000000000).toLong())
                        result += " Billiarden"
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
                return format(kotlin.math.truncate(value).toLong()) + " Komma " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalR : NumberFormatter {
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
                    0L -> "null"
                    1L -> "einer"
                    in 2L..99L -> spelloutNumbering.format(value)
                    in 100L..999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 100)
                        result += "­hundert"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += "­tausend"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "eine Million"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000)
                        result += " Millionen"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "eine Milliarde"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000)
                        result += " Milliarden"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "eine Billion"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000)
                        result += " Billionen"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "eine Billiarde"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000000)
                        result += " Billiarden"
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
                            throw UnsupportedOperationException("spelloutCardinalR only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(
                    0,
                    1,
                    2,
                    100,
                    1000,
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
                    0L -> "null"
                    1L -> "einer"
                    2L -> spelloutNumbering.format(value)
                    100L -> {
                        var result = spelloutCardinalMasculine.format((value * 100).toLong())
                        result += "­hundert"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000).toLong())
                        result += "­tausend"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "eine Million"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000).toLong())
                        result += " Millionen"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = "eine Milliarde"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000).toLong())
                        result += " Milliarden"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "eine Billion"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000000).toLong())
                        result += " Billionen"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = "eine Billiarde"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000000000).toLong())
                        result += " Billiarden"
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
                return format(kotlin.math.truncate(value).toLong()) + " Komma " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalS : NumberFormatter {
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
                    0L -> "null"
                    1L -> "eines"
                    in 2L..99L -> spelloutNumbering.format(value)
                    in 100L..999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 100)
                        result += "­hundert"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += "­tausend"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "eine Million"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000)
                        result += " Millionen"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "eine Milliarde"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000)
                        result += " Milliarden"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "eine Billion"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000)
                        result += " Billionen"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "eine Billiarde"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000000)
                        result += " Billiarden"
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
                            throw UnsupportedOperationException("spelloutCardinalS only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(
                    0,
                    1,
                    2,
                    100,
                    1000,
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
                    0L -> "null"
                    1L -> "eines"
                    2L -> spelloutNumbering.format(value)
                    100L -> {
                        var result = spelloutCardinalMasculine.format((value * 100).toLong())
                        result += "­hundert"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000).toLong())
                        result += "­tausend"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "eine Million"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000).toLong())
                        result += " Millionen"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = "eine Milliarde"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000).toLong())
                        result += " Milliarden"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "eine Billion"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000000).toLong())
                        result += " Billionen"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = "eine Billiarde"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000000000).toLong())
                        result += " Billiarden"
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
                return format(kotlin.math.truncate(value).toLong()) + " Komma " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalM : NumberFormatter {
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
                    0L -> "null"
                    1L -> "einem"
                    in 2L..99L -> spelloutNumbering.format(value)
                    in 100L..999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 100)
                        result += "­hundert"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += "­tausend"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "eine Million"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000)
                        result += " Millionen"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "eine Milliarde"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000)
                        result += " Milliarden"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "eine Billion"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000)
                        result += " Billionen"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "eine Billiarde"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000000)
                        result += " Billiarden"
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
                            throw UnsupportedOperationException("spelloutCardinalM only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(
                    0,
                    1,
                    2,
                    100,
                    1000,
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
                    0L -> "null"
                    1L -> "einem"
                    2L -> spelloutNumbering.format(value)
                    100L -> {
                        var result = spelloutCardinalMasculine.format((value * 100).toLong())
                        result += "­hundert"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000).toLong())
                        result += "­tausend"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "eine Million"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000).toLong())
                        result += " Millionen"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = "eine Milliarde"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000).toLong())
                        result += " Milliarden"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "eine Billion"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000000).toLong())
                        result += " Billionen"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = "eine Billiarde"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000000000).toLong())
                        result += " Billiarden"
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
                return format(kotlin.math.truncate(value).toLong()) + " Komma " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class Ste : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ste"
                    else -> {
                        if (value >= 1.0) "­" + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("ste only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ste"
                    else -> {
                        if (value >= 1L) "­" + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("ste only supports numbers >= 0")
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

        private inner class Ste2 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ste"
                    else -> {
                        if (value >= 1.0) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("ste2 only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ste"
                    else -> {
                        if (value >= 1L) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("ste2 only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ste"
                    1L -> " " + spelloutOrdinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinal : NumberFormatter {
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
                    0L -> "nullte"
                    1L -> "erste"
                    2L -> "zweite"
                    3L -> "dritte"
                    4L -> "vierte"
                    5L -> "fünfte"
                    6L -> "sechste"
                    7L -> "siebte"
                    8L -> "achte"
                    in 9L..19L -> spelloutNumbering.format(value) + "te"
                    in 20L..99L -> spelloutNumbering.format(value) + "ste"
                    in 100L..999L -> spelloutCardinalMasculine.format((value) / 100) + "­hundert" + ste.format(value % 100)
                    in 1000L..999999L -> spelloutCardinalMasculine.format((value) / 1000) + "­tausend" + ste.format(value % 1000)
                    in 1000000L..1999999L -> "eine Million" + ste2.format(value % 1000000)
                    in 2000000L..999999999L -> spelloutCardinalFeminine.format((value) / 1000000) + " Millionen" + ste2.format(value % 1000000)
                    in 1000000000L..1999999999L -> "eine Milliarde" + ste2.format(value % 1000000000)
                    in 2000000000L..999999999999L -> spelloutCardinalFeminine.format((value) / 1000000000) + " Milliarden" + ste2.format(value % 1000000000)
                    in 1000000000000L..1999999999999L -> "eine Billion" + ste.format(value % 1000000000000)
                    in 2000000000000L..999999999999999L -> spelloutCardinalFeminine.format((value) / 1000000000000) + " Billionen" + ste2.format(value % 1000000000000)
                    in 1000000000000000L..1999999999999999L -> "eine Billiarde" + ste2.format(value % 1000000000000000)
                    in 2000000000000000L..999999999999999999L -> spelloutCardinalFeminine.format((value) / 1000000000000000) + " Billiarden" + ste2.format(value % 1000000000000000)
                    else -> {
                        if (value >= 1000000000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) + "." else {
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
                    20,
                    100,
                    1000,
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
                    0L -> "nullte"
                    1L -> "erste"
                    2L -> "zweite"
                    3L -> "dritte"
                    4L -> "vierte"
                    5L -> "fünfte"
                    6L -> "sechste"
                    7L -> "siebte"
                    8L -> "achte"
                    9L -> spelloutNumbering.format(value) + "te"
                    20L -> spelloutNumbering.format(value) + "ste"
                    100L -> spelloutCardinalMasculine.format((value * 100).toLong()) + "­hundert" + ste.format(value % 100)
                    1000L -> spelloutCardinalMasculine.format((value * 1000).toLong()) + "­tausend" + ste.format(value % 1000)
                    1000000L -> "eine Million" + ste2.format(value % 1000000)
                    2000000L -> spelloutCardinalFeminine.format((value * 2000000).toLong()) + " Millionen" + ste2.format(value % 1000000)
                    1000000000L -> "eine Milliarde" + ste2.format(value % 1000000000)
                    2000000000L -> spelloutCardinalFeminine.format((value * 2000000000).toLong()) + " Milliarden" + ste2.format(value % 1000000000)
                    1000000000000L -> "eine Billion" + ste.format(value % 1000000000000)
                    2000000000000L -> spelloutCardinalFeminine.format((value * 2000000000000).toLong()) + " Billionen" + ste2.format(value % 1000000000000)
                    1000000000000000L -> "eine Billiarde" + ste2.format(value % 1000000000000000)
                    2000000000000000L -> spelloutCardinalFeminine.format((value * 2000000000000000).toLong()) + " Billiarden" + ste2.format(value % 1000000000000000)
                    1000000000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 3,
                        secondary_grouping = 3,
                        symbols = language.numbers.symbols
                    ).format(value) + "."

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalN : NumberFormatter {
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
                return if (value >= 0L) spelloutOrdinal.format(value) + "n" else {
                    throw UnsupportedOperationException("spelloutOrdinalN only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinal.format(value) + "n"
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalR : NumberFormatter {
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
                return if (value >= 0L) spelloutOrdinal.format(value) + "r" else {
                    throw UnsupportedOperationException("spelloutOrdinalR only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinal.format(value) + "r"
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalS : NumberFormatter {
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
                return if (value >= 0L) spelloutOrdinal.format(value) + "s" else {
                    throw UnsupportedOperationException("spelloutOrdinalS only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinal.format(value) + "s"
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalM : NumberFormatter {
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
                return if (value >= 0L) spelloutOrdinal.format(value) + "m" else {
                    throw UnsupportedOperationException("spelloutOrdinalM only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinal.format(value) + "m"
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

    }

    private val spelloutRules = SpelloutRules()
    override val spelloutNumberingYear: NumberFormatter
        get() = spelloutRules.spelloutNumberingYear
    override val spelloutNumbering: NumberFormatter
        get() = spelloutRules.spelloutNumbering
    open val spelloutCardinalNeuter: NumberFormatter
        get() = spelloutRules.spelloutCardinalNeuter
    open val spelloutCardinalMasculine: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculine
    open val spelloutCardinalFeminine: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminine
    open val spelloutCardinalN: NumberFormatter
        get() = spelloutRules.spelloutCardinalN
    open val spelloutCardinalR: NumberFormatter
        get() = spelloutRules.spelloutCardinalR
    open val spelloutCardinalS: NumberFormatter
        get() = spelloutRules.spelloutCardinalS
    open val spelloutCardinalM: NumberFormatter
        get() = spelloutRules.spelloutCardinalM
    override val spelloutOrdinal: NumberFormatter
        get() = spelloutRules.spelloutOrdinal
    open val spelloutOrdinalN: NumberFormatter
        get() = spelloutRules.spelloutOrdinalN
    open val spelloutOrdinalR: NumberFormatter
        get() = spelloutRules.spelloutOrdinalR
    open val spelloutOrdinalS: NumberFormatter
        get() = spelloutRules.spelloutOrdinalS
    open val spelloutOrdinalM: NumberFormatter
        get() = spelloutRules.spelloutOrdinalM
    override val spelloutCardinal: NumberFormatter
        get() = TODO("spelloutCardinal is not available for de")

    fun spelloutCardinalFor(gender: Gender): NumberFormatter {
        return when (gender) {
            Gender.Neuter -> spelloutCardinalNeuter
            Gender.Masculine -> spelloutCardinalMasculine
            Gender.Feminine -> spelloutCardinalFeminine
            else -> TODO("de does not support $gender gender")
        }

    }
}
