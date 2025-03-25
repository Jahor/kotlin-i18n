package info.leonenko.i18n.rbnf

import info.leonenko.i18n.*
import info.leonenko.i18n.languages.hr
import kotlin.math.roundToLong


open class RBNFHr(val language: Language = Language.hr) : RBNF {
    private inner class SpelloutRules {
        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumbering = SpelloutNumbering()

        val spelloutCardinalMasculine = SpelloutCardinalMasculine()

        val spelloutCardinalNeuter = SpelloutCardinalNeuter()

        val spelloutCardinalFeminine = SpelloutCardinalFeminine()

        val spelloutOrdinalBase = SpelloutOrdinalBase()

        val spelloutOrdinalMasculine = SpelloutOrdinalMasculine()

        val spelloutOrdinalNeuter = SpelloutOrdinalNeuter()

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
                    0L -> "nula"
                    1L -> "jedan"
                    2L -> "dva"
                    3L -> "tri"
                    4L -> "četiri"
                    5L -> "pet"
                    6L -> "šest"
                    7L -> "sedam"
                    8L -> "osam"
                    9L -> "devet"
                    10L -> "deset"
                    11L -> "jedanaest"
                    12L -> "dvanaest"
                    13L -> "trinaest"
                    14L -> "četrnaest"
                    15L -> "petnaest"
                    16L -> "šesnaest"
                    17L -> "sedamnaest"
                    18L -> "osamnaest"
                    19L -> "devetnaest"
                    in 20L..29L -> {
                        var result = "dvadeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "trideset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "četrdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "pedeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "šezdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "sedamdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "osamdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "devedeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "sto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "dvjesto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "tristo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "četiristo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "petsto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "šeststo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "sedamsto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "osamsto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "devetsto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "tisuću"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..4999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " tisuće"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 5000L..999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " tisuća"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = format((value) / 1000000)
                        result += " milijun"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..4999999L -> {
                        var result = format((value) / 1000000)
                        result += " milijuna"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 5000000L..999999999L -> {
                        var result = format((value) / 1000000)
                        result += " milijuna"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000)
                        result += " milijarda"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..4999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000)
                        result += " milijarde"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 5000000000L..999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000)
                        result += " milijardi"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " bilijun"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..4999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " bilijuna"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 5000000000000L..999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " bilijuna"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000000)
                        result += " bilijarda"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..4999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000000)
                        result += " bilijarde"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 5000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000000)
                        result += " bilijardi"
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
                    5000,
                    1000000,
                    2000000,
                    5000000,
                    1000000000,
                    2000000000,
                    5000000000,
                    1000000000000,
                    2000000000000,
                    5000000000000,
                    1000000000000000,
                    2000000000000000,
                    5000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "nula"
                    1L -> "jedan"
                    2L -> "dva"
                    3L -> "tri"
                    4L -> "četiri"
                    5L -> "pet"
                    6L -> "šest"
                    7L -> "sedam"
                    8L -> "osam"
                    9L -> "devet"
                    10L -> "deset"
                    11L -> "jedanaest"
                    12L -> "dvanaest"
                    13L -> "trinaest"
                    14L -> "četrnaest"
                    15L -> "petnaest"
                    16L -> "šesnaest"
                    17L -> "sedamnaest"
                    18L -> "osamnaest"
                    19L -> "devetnaest"
                    20L -> {
                        var result = "dvadeset"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "trideset"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "četrdeset"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "pedeset"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "šezdeset"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "sedamdeset"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "osamdeset"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "devedeset"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "sto"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = "dvjesto"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = "tristo"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> {
                        var result = "četiristo"
                        result += (if (value * 400 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = "petsto"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> {
                        var result = "šeststo"
                        result += (if (value * 600 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> {
                        var result = "sedamsto"
                        result += (if (value * 700 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> {
                        var result = "osamsto"
                        result += (if (value * 800 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> {
                        var result = "devetsto"
                        result += (if (value * 900 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = "tisuću"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000).toLong())
                        result += " tisuće"
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> {
                        var result = spelloutCardinalFeminine.format((value * 5000).toLong())
                        result += " tisuća"
                        result += (if (value * 5000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = format((value * 1000000).toLong())
                        result += " milijun"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = format((value * 2000000).toLong())
                        result += " milijuna"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    5000000L -> {
                        var result = format((value * 5000000).toLong())
                        result += " milijuna"
                        result += (if (value * 5000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 1000000000).toLong())
                        result += " milijarda"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000).toLong())
                        result += " milijarde"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    5000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 5000000000).toLong())
                        result += " milijardi"
                        result += (if (value * 5000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = format((value * 1000000000000).toLong())
                        result += " bilijun"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = format((value * 2000000000000).toLong())
                        result += " bilijuna"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    5000000000000L -> {
                        var result = format((value * 5000000000000).toLong())
                        result += " bilijuna"
                        result += (if (value * 5000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 1000000000000000).toLong())
                        result += " bilijarda"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000000000).toLong())
                        result += " bilijarde"
                        result += (if (value * 2000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    5000000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 5000000000000000).toLong())
                        result += " bilijardi"
                        result += (if (value * 5000000000000000 == 1.0) {
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
                return format(kotlin.math.truncate(value).toLong()) + " zarez " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalNeuter : NumberFormatter {
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
                    0L -> "nula"
                    1L -> "jedno"
                    2L -> "dva"
                    in 3L..19L -> spelloutCardinalMasculine.format(value)
                    in 20L..29L -> {
                        var result = "dvadeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "trideset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "četrdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "pedeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "šezdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "sedamdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "osamdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "devedeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "sto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "dvjesto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "tristo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "četiristo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "petsto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "šeststo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "sedamsto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "osamsto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "devetsto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "tisuću"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..4999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " tisuće"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 5000L..999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " tisuća"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " milijun"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..4999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " milijuna"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 5000000L..999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " milijuna"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000)
                        result += " milijarda"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..4999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000)
                        result += " milijarde"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 5000000000L..999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000)
                        result += " milijardi"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " bilijun"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..4999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " bilijuna"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 5000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " bilijuna"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000000)
                        result += " bilijarda"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..4999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000000)
                        result += " bilijarde"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 5000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000000)
                        result += " bilijardi"
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
                            throw UnsupportedOperationException("spelloutCardinalNeuter only supports numbers >= 0")
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
                    5000,
                    1000000,
                    2000000,
                    5000000,
                    1000000000,
                    2000000000,
                    5000000000,
                    1000000000000,
                    2000000000000,
                    5000000000000,
                    1000000000000000,
                    2000000000000000,
                    5000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "nula"
                    1L -> "jedno"
                    2L -> "dva"
                    3L -> spelloutCardinalMasculine.format(value)
                    20L -> {
                        var result = "dvadeset"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "trideset"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "četrdeset"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "pedeset"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "šezdeset"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "sedamdeset"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "osamdeset"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "devedeset"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "sto"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = "dvjesto"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = "tristo"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> {
                        var result = "četiristo"
                        result += (if (value * 400 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = "petsto"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> {
                        var result = "šeststo"
                        result += (if (value * 600 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> {
                        var result = "sedamsto"
                        result += (if (value * 700 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> {
                        var result = "osamsto"
                        result += (if (value * 800 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> {
                        var result = "devetsto"
                        result += (if (value * 900 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = "tisuću"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000).toLong())
                        result += " tisuće"
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> {
                        var result = spelloutCardinalFeminine.format((value * 5000).toLong())
                        result += " tisuća"
                        result += (if (value * 5000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000).toLong())
                        result += " milijun"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000).toLong())
                        result += " milijuna"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    5000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000).toLong())
                        result += " milijuna"
                        result += (if (value * 5000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 1000000000).toLong())
                        result += " milijarda"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000).toLong())
                        result += " milijarde"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    5000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 5000000000).toLong())
                        result += " milijardi"
                        result += (if (value * 5000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000).toLong())
                        result += " bilijun"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000).toLong())
                        result += " bilijuna"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    5000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000000000).toLong())
                        result += " bilijuna"
                        result += (if (value * 5000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 1000000000000000).toLong())
                        result += " bilijarda"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000000000000000).toLong())
                        result += " bilijarde"
                        result += (if (value * 2000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    5000000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 5000000000000000).toLong())
                        result += " bilijardi"
                        result += (if (value * 5000000000000000 == 1.0) {
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
                return format(kotlin.math.truncate(value).toLong()) + " zarez " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
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
                    0L -> "nula"
                    1L -> "jedna"
                    2L -> "dvije"
                    in 3L..19L -> spelloutCardinalMasculine.format(value)
                    in 20L..29L -> {
                        var result = "dvadeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "trideset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "četrdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "pedeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "šezdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "sedamdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "osamdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "devedeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "sto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "dvjesto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "tristo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "četiristo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "petsto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "šeststo"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "sedamsto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "osamsto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "devetsto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "tisuću"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..4999L -> {
                        var result = format((value) / 1000)
                        result += " tisuće"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 5000L..999999L -> {
                        var result = format((value) / 1000)
                        result += " tisuća"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " milijun"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..4999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " milijuna"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 5000000L..999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " milijuna"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = format((value) / 1000000000)
                        result += " milijarda"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..4999999999L -> {
                        var result = format((value) / 1000000000)
                        result += " milijarde"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 5000000000L..999999999999L -> {
                        var result = format((value) / 1000000000)
                        result += " milijardi"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " bilijun"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..4999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " bilijuna"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 5000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " bilijuna"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += " bilijarda"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..4999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += " bilijarde"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 5000000000000000L..999999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += " bilijardi"
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
                    3,
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
                    5000,
                    1000000,
                    2000000,
                    5000000,
                    1000000000,
                    2000000000,
                    5000000000,
                    1000000000000,
                    2000000000000,
                    5000000000000,
                    1000000000000000,
                    2000000000000000,
                    5000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "nula"
                    1L -> "jedna"
                    2L -> "dvije"
                    3L -> spelloutCardinalMasculine.format(value)
                    20L -> {
                        var result = "dvadeset"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "trideset"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "četrdeset"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "pedeset"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "šezdeset"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "sedamdeset"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "osamdeset"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "devedeset"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " i " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "sto"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = "dvjesto"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = "tristo"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> {
                        var result = "četiristo"
                        result += (if (value * 400 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = "petsto"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> {
                        var result = "šeststo"
                        result += (if (value * 600 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> {
                        var result = "sedamsto"
                        result += (if (value * 700 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> {
                        var result = "osamsto"
                        result += (if (value * 800 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> {
                        var result = "devetsto"
                        result += (if (value * 900 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = "tisuću"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = format((value * 2000).toLong())
                        result += " tisuće"
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> {
                        var result = format((value * 5000).toLong())
                        result += " tisuća"
                        result += (if (value * 5000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000).toLong())
                        result += " milijun"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000).toLong())
                        result += " milijuna"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    5000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000).toLong())
                        result += " milijuna"
                        result += (if (value * 5000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = format((value * 1000000000).toLong())
                        result += " milijarda"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = format((value * 2000000000).toLong())
                        result += " milijarde"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    5000000000L -> {
                        var result = format((value * 5000000000).toLong())
                        result += " milijardi"
                        result += (if (value * 5000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000).toLong())
                        result += " bilijun"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000).toLong())
                        result += " bilijuna"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    5000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000000000).toLong())
                        result += " bilijuna"
                        result += (if (value * 5000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = format((value * 1000000000000000).toLong())
                        result += " bilijarda"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = format((value * 2000000000000000).toLong())
                        result += " bilijarde"
                        result += (if (value * 2000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    5000000000000000L -> {
                        var result = format((value * 5000000000000000).toLong())
                        result += " bilijardi"
                        result += (if (value * 5000000000000000 == 1.0) {
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
                return format(kotlin.math.truncate(value).toLong()) + " zarez " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutOrdinalBase : NumberFormatter {
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
                    0L -> "nula"
                    1L -> "prv"
                    2L -> "drug"
                    3L -> "treć"
                    4L -> "četvrt"
                    5L -> "pet"
                    6L -> "šest"
                    7L -> "sedm"
                    8L -> "osm"
                    9L -> "devet"
                    10L -> "deset"
                    in 11L..19L -> spelloutNumbering.format(value)
                    in 20L..29L -> {
                        var result = "dvadeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "trideset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "četrdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "pedeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "šezdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "sedamdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "osamdeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "devedeset"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "st"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "dvest"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "trist"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "četrist"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "petst"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "šest"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "sedamst"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "osamst"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "devetst"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "tisuću"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..4999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " tisuće"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 5000L..999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " tisuću"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " milijun"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..4999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " milijuny"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 5000000L..999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " milijun"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " miliarda"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..4999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " miliardy"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 5000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " miliarda"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " bilijun"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..4999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " bilijuny"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 5000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " bilijun"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " biliarda"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..4999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " biliardy"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 5000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " biliarda"
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
                            throw UnsupportedOperationException("spelloutOrdinalBase only supports numbers >= 0")
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
                    5000,
                    1000000,
                    2000000,
                    5000000,
                    1000000000,
                    2000000000,
                    5000000000,
                    1000000000000,
                    2000000000000,
                    5000000000000,
                    1000000000000000,
                    2000000000000000,
                    5000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "nula"
                    1L -> "prv"
                    2L -> "drug"
                    3L -> "treć"
                    4L -> "četvrt"
                    5L -> "pet"
                    6L -> "šest"
                    7L -> "sedm"
                    8L -> "osm"
                    9L -> "devet"
                    10L -> "deset"
                    11L -> spelloutNumbering.format(value)
                    20L -> {
                        var result = "dvadeset"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "trideset"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "četrdeset"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "pedeset"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "šezdeset"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "sedamdeset"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "osamdeset"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "devedeset"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "st"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = "dvest"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = "trist"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> {
                        var result = "četrist"
                        result += (if (value * 400 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = "petst"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> {
                        var result = "šest"
                        result += (if (value * 600 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> {
                        var result = "sedamst"
                        result += (if (value * 700 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> {
                        var result = "osamst"
                        result += (if (value * 800 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> {
                        var result = "devetst"
                        result += (if (value * 900 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = "tisuću"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = spelloutCardinalFeminine.format((value * 2000).toLong())
                        result += " tisuće"
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> {
                        var result = spelloutCardinalFeminine.format((value * 5000).toLong())
                        result += " tisuću"
                        result += (if (value * 5000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000).toLong())
                        result += " milijun"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000).toLong())
                        result += " milijuny"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    5000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000).toLong())
                        result += " milijun"
                        result += (if (value * 5000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000).toLong())
                        result += " miliarda"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000).toLong())
                        result += " miliardy"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    5000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000000).toLong())
                        result += " miliarda"
                        result += (if (value * 5000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000).toLong())
                        result += " bilijun"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000).toLong())
                        result += " bilijuny"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    5000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000000000).toLong())
                        result += " bilijun"
                        result += (if (value * 5000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000000).toLong())
                        result += " biliarda"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000000).toLong())
                        result += " biliardy"
                        result += (if (value * 2000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    5000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000000000000).toLong())
                        result += " biliarda"
                        result += (if (value * 5000000000000000 == 1.0) {
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
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalMasculine : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalBase.format(value) + "i" else {
                    throw UnsupportedOperationException("spelloutOrdinalMasculine only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return if (value >= 0L) spelloutOrdinalBase.format(value) + "i" else {
                    throw UnsupportedOperationException("spelloutOrdinalMasculine only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalBase.format(value) + "i"
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalNeuter : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 0L..2L -> spelloutOrdinalBase.format(value) + "o"
                    3L -> spelloutOrdinalBase.format(value) + "e"
                    else -> {
                        if (value >= 4.0) spelloutOrdinalBase.format(value) + "o" else {
                            throw UnsupportedOperationException("spelloutOrdinalNeuter only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 0L..2L -> spelloutOrdinalBase.format(value) + "o"
                    3L -> spelloutOrdinalBase.format(value) + "e"
                    else -> {
                        if (value >= 4L) spelloutOrdinalBase.format(value) + "o" else {
                            throw UnsupportedOperationException("spelloutOrdinalNeuter only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 3, 4).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalBase.format(value) + "o"
                    3L -> spelloutOrdinalBase.format(value) + "e"
                    4L -> spelloutOrdinalBase.format(value) + "o"
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalFeminine : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalBase.format(value) + "a" else {
                    throw UnsupportedOperationException("spelloutOrdinalFeminine only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return if (value >= 0L) spelloutOrdinalBase.format(value) + "a" else {
                    throw UnsupportedOperationException("spelloutOrdinalFeminine only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalBase.format(value) + "a"
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

    }

    private val spelloutRules = SpelloutRules()
    override val spelloutNumberingYear: NumberFormatter
        get() = spelloutRules.spelloutNumberingYear
    override val spelloutNumbering: NumberFormatter
        get() = spelloutRules.spelloutNumbering
    open val spelloutCardinalMasculine: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculine
    open val spelloutCardinalNeuter: NumberFormatter
        get() = spelloutRules.spelloutCardinalNeuter
    open val spelloutCardinalFeminine: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminine
    open val spelloutOrdinalMasculine: NumberFormatter
        get() = spelloutRules.spelloutOrdinalMasculine
    open val spelloutOrdinalNeuter: NumberFormatter
        get() = spelloutRules.spelloutOrdinalNeuter
    open val spelloutOrdinalFeminine: NumberFormatter
        get() = spelloutRules.spelloutOrdinalFeminine
    override val spelloutCardinal: NumberFormatter
        get() = TODO("spelloutCardinal is not available for hr")
    override val spelloutOrdinal: NumberFormatter
        get() = TODO("spelloutOrdinal is not available for hr")

    override fun spelloutNumberingFor(gender: Gender, case: Case): NumberFormatter {
        return spelloutNumbering

    }

    fun spelloutNumberingFor(): NumberFormatter {
        return spelloutNumberingFor(Gender.Neuter, Case.Nominative)
    }

    override fun spelloutCardinalFor(gender: Gender, case: Case): NumberFormatter {
        return when (gender) {
            Gender.Neuter -> spelloutCardinalNeuter
            Gender.Masculine -> spelloutCardinalMasculine
            Gender.Feminine -> spelloutCardinalFeminine
            else -> TODO("hr does not support $gender gender")
        }

    }

    fun spelloutCardinalFor(gender: Gender): NumberFormatter {
        return spelloutCardinalFor(gender, Case.Nominative)
    }

    override fun spelloutOrdinalFor(gender: Gender, case: Case): NumberFormatter {
        return when (gender) {
            Gender.Neuter -> spelloutOrdinalNeuter
            Gender.Masculine -> spelloutOrdinalMasculine
            Gender.Feminine -> spelloutOrdinalFeminine
            else -> TODO("hr does not support $gender gender")
        }

    }

    fun spelloutOrdinalFor(gender: Gender): NumberFormatter {
        return spelloutOrdinalFor(gender, Case.Nominative)
    }

    override fun spelloutNumberingYearFor(gender: Gender, case: Case): NumberFormatter {
        TODO("spelloutNumberingYear is not available for hr")
    }
}
