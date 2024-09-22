package info.leonenko.i18n.rbnf

import info.leonenko.i18n.*
import info.leonenko.i18n.languages.hu
import kotlin.math.roundToLong


open class RBNFHu(val language: Language = Language.hu) : RBNF {
    private inner class SpelloutRules {
        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumbering = SpelloutNumbering()

        val spelloutCardinal = SpelloutCardinal()

        val spelloutCardinalVerbose = SpelloutCardinalVerbose()

        val spelloutCardinalInitial = SpelloutCardinalInitial()

        val spelloutOrdinal = SpelloutOrdinal()

        val spelloutOrdinalAdik = SpelloutOrdinalAdik()

        val spelloutOrdinalOdik = SpelloutOrdinalOdik()

        val spelloutOrdinalLarger = SpelloutOrdinalLarger()

        val spelloutOrdinalVerbose = SpelloutOrdinalVerbose()

        val spelloutOrdinalVerboseAdik = SpelloutOrdinalVerboseAdik()

        val spelloutOrdinalVerboseOdik = SpelloutOrdinalVerboseOdik()

        val spelloutOrdinalVerboseLarger = SpelloutOrdinalVerboseLarger()

        private inner class SpelloutNumberingYear : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "mínusz " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "mínusz " + format(kotlin.math.abs(value))
                }
                return if (value >= 0L) spelloutNumbering.format(value) else {
                    throw UnsupportedOperationException("spelloutNumberingYear only supports numbers >= 0")
                }
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
                    return "mínusz " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "mínusz " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "nulla"
                    1L -> "egy"
                    2L -> "kettő"
                    3L -> "három"
                    4L -> "négy"
                    5L -> "öt"
                    6L -> "hat"
                    7L -> "hét"
                    8L -> "nyolc"
                    9L -> "kilenc"
                    10L -> "tíz"
                    in 11L..19L -> "tizen­" + format(value % 10)
                    20L -> "húsz"
                    in 21L..29L -> "huszon­" + format(value % 10)
                    in 30L..39L -> {
                        var result = "harminc"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "negyven"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "ötven"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "hatvan"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "hetven"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "nyolcvan"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "kilencven"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "száz"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = spelloutCardinalInitial.format((value) / 100)
                        result += "­száz"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "ezer"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = spelloutCardinalInitial.format((value) / 1000)
                        result += "­ezer"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalInitial.format((value) / 1000000)
                        result += "­millió"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalInitial.format((value) / 1000000000)
                        result += "­milliárd"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = spelloutCardinalInitial.format((value) / 1000000000000)
                        result += "­billió"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalInitial.format((value) / 1000000000000000)
                        result += "­billiárd"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000000000000000)
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
                    21,
                    30,
                    40,
                    50,
                    60,
                    70,
                    80,
                    90,
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
                    0L -> "nulla"
                    1L -> "egy"
                    2L -> "kettő"
                    3L -> "három"
                    4L -> "négy"
                    5L -> "öt"
                    6L -> "hat"
                    7L -> "hét"
                    8L -> "nyolc"
                    9L -> "kilenc"
                    10L -> "tíz"
                    11L -> "tizen­" + format(value % 10)
                    20L -> "húsz"
                    21L -> "huszon­" + format(value % 10)
                    30L -> {
                        var result = "harminc"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "negyven"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "ötven"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "hatvan"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "hetven"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "nyolcvan"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "kilencven"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "száz"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = spelloutCardinalInitial.format((value * 200).toLong())
                        result += "­száz"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = "ezer"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = spelloutCardinalInitial.format((value * 2000).toLong())
                        result += "­ezer"
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalInitial.format((value * 1000000).toLong())
                        result += "­millió"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalInitial.format((value * 1000000000).toLong())
                        result += "­milliárd"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalInitial.format((value * 1000000000000).toLong())
                        result += "­billió"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalInitial.format((value * 1000000000000000).toLong())
                        result += "­billiárd"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            "­" + format(value % 1000000000000000)
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
                return format(kotlin.math.truncate(value).toLong()) + " egész " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalVerbose : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "mínusz " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "mínusz " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    in 0L..99L -> spelloutCardinal.format(value)
                    in 100L..999L -> {
                        var result = format((value) / 100)
                        result += "­száz"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = format((value) / 1000)
                        result += "­ezer"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = format((value) / 1000000)
                        result += "­millió"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = format((value) / 1000000000)
                        result += "­milliárd"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += "­billió"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += "­billiárd"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            "­" + format(value % 1000000000000000)
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
                            throw UnsupportedOperationException("spelloutCardinalVerbose only supports numbers >= 0")
                        }
                    }
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " egész " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalInitial : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    1L -> "egy"
                    2L -> "két"
                    else -> {
                        if (value >= 3.0) spelloutCardinal.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalInitial only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    1L -> "egy"
                    2L -> "két"
                    else -> {
                        if (value >= 3L) spelloutCardinal.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalInitial only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 2, 3).bestDenominatorOrNull(value)!!) {
                    1L -> "egy"
                    2L -> "két"
                    3L -> spelloutCardinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinal : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "mínusz " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "mínusz " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "nulla"
                    1L -> "első"
                    2L -> "második"
                    else -> {
                        if (value >= 3L) spelloutOrdinalLarger.format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinal only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 3).bestDenominatorOrNull(value)!!) {
                    0L -> "nulla"
                    1L -> "első"
                    2L -> "második"
                    3L -> spelloutOrdinalLarger.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalAdik : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "adik"
                    else -> {
                        if (value >= 1.0) spelloutOrdinalLarger.format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinalAdik only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "adik"
                    else -> {
                        if (value >= 1L) spelloutOrdinalLarger.format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinalAdik only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "adik"
                    1L -> spelloutOrdinalLarger.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalOdik : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "odik"
                    else -> {
                        if (value >= 1.0) spelloutOrdinalLarger.format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinalOdik only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "odik"
                    else -> {
                        if (value >= 1L) spelloutOrdinalLarger.format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinalOdik only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "odik"
                    1L -> spelloutOrdinalLarger.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalLarger : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "edik"
                    1L -> "egyedik"
                    2L -> "kettedik"
                    3L -> "harmadik"
                    4L -> "negyedik"
                    5L -> "ötödik"
                    6L -> "hatodik"
                    7L -> "hetedik"
                    8L -> "nyolcadik"
                    9L -> "kilencedik"
                    10L -> "tizedik"
                    in 11L..19L -> "tizen" + format(value % 10)
                    20L -> "huszadik"
                    in 21L..29L -> "huszon" + format(value % 10)
                    in 30L..39L -> "harminc" + spelloutOrdinalAdik.format(value % 10)
                    in 40L..49L -> "negyven" + format(value % 10)
                    in 50L..59L -> "ötven" + format(value % 10)
                    in 60L..69L -> "hatvan" + spelloutOrdinalAdik.format(value % 10)
                    in 70L..79L -> "hetven" + format(value % 10)
                    in 80L..89L -> "nyolcvan" + spelloutOrdinalAdik.format(value % 10)
                    in 90L..99L -> "kilencven" + format(value % 10)
                    in 100L..199L -> "száz" + spelloutOrdinalAdik.format(value % 100)
                    in 200L..999L -> spelloutCardinalInitial.format((value).toLong() / 100) + "száz" + spelloutOrdinalAdik.format(value % 100)
                    1000L -> "ezr" + format(value % 1000)
                    in 1001L..1999L -> "ezer­" + format(value % 1000)
                    2000L -> spelloutCardinalInitial.format((value).toLong() / 1000) + "­ezr" + format(value % 1000)
                    in 2001L..999999L -> if ((value).toLong() % 1000 == 0L) {
                        spelloutCardinalInitial.format((value).toLong() / 1000) + "­ezr" + format(value % 1000)
                    } else
                        spelloutCardinalInitial.format((value).toLong() / 1000) + "­ezer­" + format(value % 1000)

                    in 1000000L..999999999L -> spelloutCardinalInitial.format((value).toLong() / 1000000) + "­milliom­" + spelloutOrdinalOdik.format(value % 1000000)
                    else -> {
                        if (value >= 1000000000.0) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) + "." else {
                            throw UnsupportedOperationException("spelloutOrdinalLarger only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "edik"
                    1L -> "egyedik"
                    2L -> "kettedik"
                    3L -> "harmadik"
                    4L -> "negyedik"
                    5L -> "ötödik"
                    6L -> "hatodik"
                    7L -> "hetedik"
                    8L -> "nyolcadik"
                    9L -> "kilencedik"
                    10L -> "tizedik"
                    in 11L..19L -> "tizen" + format(value % 10)
                    20L -> "huszadik"
                    in 21L..29L -> "huszon" + format(value % 10)
                    in 30L..39L -> "harminc" + spelloutOrdinalAdik.format(value % 10)
                    in 40L..49L -> "negyven" + format(value % 10)
                    in 50L..59L -> "ötven" + format(value % 10)
                    in 60L..69L -> "hatvan" + spelloutOrdinalAdik.format(value % 10)
                    in 70L..79L -> "hetven" + format(value % 10)
                    in 80L..89L -> "nyolcvan" + spelloutOrdinalAdik.format(value % 10)
                    in 90L..99L -> "kilencven" + format(value % 10)
                    in 100L..199L -> "száz" + spelloutOrdinalAdik.format(value % 100)
                    in 200L..999L -> spelloutCardinalInitial.format((value) / 100) + "száz" + spelloutOrdinalAdik.format(value % 100)
                    1000L -> "ezr" + format(value % 1000)
                    in 1001L..1999L -> "ezer­" + format(value % 1000)
                    2000L -> spelloutCardinalInitial.format((value) / 1000) + "­ezr" + format(value % 1000)
                    in 2001L..999999L -> if ((value) % 1000 == 0L) {
                        spelloutCardinalInitial.format((value) / 1000) + "­ezr" + format(value % 1000)
                    } else
                        spelloutCardinalInitial.format((value) / 1000) + "­ezer­" + format(value % 1000)

                    in 1000000L..999999999L -> spelloutCardinalInitial.format((value) / 1000000) + "­milliom­" + spelloutOrdinalOdik.format(value % 1000000)
                    else -> {
                        if (value >= 1000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) + "." else {
                            throw UnsupportedOperationException("spelloutOrdinalLarger only supports numbers >= 0")
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
                    21,
                    30,
                    40,
                    50,
                    60,
                    70,
                    80,
                    90,
                    100,
                    200,
                    1000,
                    1001,
                    2000,
                    2001,
                    1000000,
                    1000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "edik"
                    1L -> "egyedik"
                    2L -> "kettedik"
                    3L -> "harmadik"
                    4L -> "negyedik"
                    5L -> "ötödik"
                    6L -> "hatodik"
                    7L -> "hetedik"
                    8L -> "nyolcadik"
                    9L -> "kilencedik"
                    10L -> "tizedik"
                    11L -> "tizen" + format(value % 10)
                    20L -> "huszadik"
                    21L -> "huszon" + format(value % 10)
                    30L -> "harminc" + spelloutOrdinalAdik.format(value % 10)
                    40L -> "negyven" + format(value % 10)
                    50L -> "ötven" + format(value % 10)
                    60L -> "hatvan" + spelloutOrdinalAdik.format(value % 10)
                    70L -> "hetven" + format(value % 10)
                    80L -> "nyolcvan" + spelloutOrdinalAdik.format(value % 10)
                    90L -> "kilencven" + format(value % 10)
                    100L -> "száz" + spelloutOrdinalAdik.format(value % 100)
                    200L -> spelloutCardinalInitial.format((value * 200).toLong()) + "száz" + spelloutOrdinalAdik.format(value % 100)
                    1000L -> "ezr" + format(value % 1000)
                    1001L -> "ezer­" + format(value % 1000)
                    2000L -> spelloutCardinalInitial.format((value * 2000).toLong()) + "­ezr" + format(value % 1000)
                    2001L -> spelloutCardinalInitial.format((value * 2001).toLong()) + "­ezer­" + format(value % 1000)
                    1000000L -> spelloutCardinalInitial.format((value * 1000000).toLong()) + "­milliom­" + spelloutOrdinalOdik.format(value % 1000000)
                    1000000000L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(
                        value
                    ) + "."

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalVerbose : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "mínusz " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "mínusz " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "nulladik"
                    1L -> "első"
                    2L -> "második"
                    else -> {
                        if (value >= 3L) spelloutOrdinalVerboseLarger.format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinalVerbose only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 3).bestDenominatorOrNull(value)!!) {
                    0L -> "nulladik"
                    1L -> "első"
                    2L -> "második"
                    3L -> spelloutOrdinalVerboseLarger.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalVerboseAdik : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "adik"
                    else -> {
                        if (value >= 1.0) spelloutOrdinalVerboseLarger.format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinalVerboseAdik only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "adik"
                    else -> {
                        if (value >= 1L) spelloutOrdinalVerboseLarger.format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinalVerboseAdik only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "adik"
                    1L -> spelloutOrdinalVerboseLarger.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalVerboseOdik : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "odik"
                    else -> {
                        if (value >= 1.0) spelloutOrdinalVerboseLarger.format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinalVerboseOdik only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "odik"
                    else -> {
                        if (value >= 1L) spelloutOrdinalVerboseLarger.format(value) else {
                            throw UnsupportedOperationException("spelloutOrdinalVerboseOdik only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "odik"
                    1L -> spelloutOrdinalVerboseLarger.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalVerboseLarger : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 0L..99L -> spelloutOrdinalLarger.format(value)
                    in 100L..999L -> spelloutCardinalVerbose.format((value).toLong() / 100) + "száz" + spelloutOrdinalVerboseAdik.format(value % 100)
                    1000L -> spelloutCardinalVerbose.format((value).toLong() / 1000) + "­ezr" + format(value % 1000)
                    in 1001L..999999L -> if ((value).toLong() % 1000 == 0L) {
                        spelloutCardinalVerbose.format((value).toLong() / 1000) + "­ezr" + format(value % 1000)
                    } else
                        spelloutCardinalVerbose.format((value).toLong() / 1000) + "­ezer­" + format(value % 1000)

                    in 1000000L..999999999L -> spelloutCardinalVerbose.format((value).toLong() / 1000000) + "­milliom­" + spelloutOrdinalVerboseOdik.format(value % 1000000)
                    else -> {
                        if (value >= 1000000000.0) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) + "." else {
                            throw UnsupportedOperationException("spelloutOrdinalVerboseLarger only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 0L..99L -> spelloutOrdinalLarger.format(value)
                    in 100L..999L -> spelloutCardinalVerbose.format((value) / 100) + "száz" + spelloutOrdinalVerboseAdik.format(value % 100)
                    1000L -> spelloutCardinalVerbose.format((value) / 1000) + "­ezr" + format(value % 1000)
                    in 1001L..999999L -> if ((value) % 1000 == 0L) {
                        spelloutCardinalVerbose.format((value) / 1000) + "­ezr" + format(value % 1000)
                    } else
                        spelloutCardinalVerbose.format((value) / 1000) + "­ezer­" + format(value % 1000)

                    in 1000000L..999999999L -> spelloutCardinalVerbose.format((value) / 1000000) + "­milliom­" + spelloutOrdinalVerboseOdik.format(value % 1000000)
                    else -> {
                        if (value >= 1000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) + "." else {
                            throw UnsupportedOperationException("spelloutOrdinalVerboseLarger only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 100, 1000, 1001, 1000000, 1000000000).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalLarger.format(value)
                    100L -> spelloutCardinalVerbose.format((value * 100).toLong()) + "száz" + spelloutOrdinalVerboseAdik.format(value % 100)
                    1000L -> spelloutCardinalVerbose.format((value * 1000).toLong()) + "­ezr" + format(value % 1000)
                    1001L -> spelloutCardinalVerbose.format((value * 1001).toLong()) + "­ezer­" + format(value % 1000)
                    1000000L -> spelloutCardinalVerbose.format((value * 1000000).toLong()) + "­milliom­" + spelloutOrdinalVerboseOdik.format(value % 1000000)
                    1000000000L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(
                        value
                    ) + "."

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
    override val spelloutCardinal: NumberFormatter
        get() = spelloutRules.spelloutCardinal
    open val spelloutCardinalVerbose: NumberFormatter
        get() = spelloutRules.spelloutCardinalVerbose
    override val spelloutOrdinal: NumberFormatter
        get() = spelloutRules.spelloutOrdinal
    open val spelloutOrdinalVerbose: NumberFormatter
        get() = spelloutRules.spelloutOrdinalVerbose
}
