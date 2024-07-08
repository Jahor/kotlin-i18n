package info.leonenko.i18n.rbnf

import info.leonenko.i18n.*
import info.leonenko.i18n.languages.tr
import kotlin.math.roundToLong


open class RBNFTr(val language: Language = Language.tr) : RBNF {
    private inner class SpelloutRules {
        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumbering = SpelloutNumbering()

        val spelloutCardinal = SpelloutCardinal()

        val inci = Inci()

        val nci = Nci()

        val inci2 = Inci2()

        val uncu2 = Uncu2()

        val uncu = Uncu()

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
                    return "eksi " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "eksi " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "sıfır"
                    1L -> "bir"
                    2L -> "iki"
                    3L -> "üç"
                    4L -> "dört"
                    5L -> "beş"
                    6L -> "altı"
                    7L -> "yedi"
                    8L -> "sekiz"
                    9L -> "dokuz"
                    in 10L..19L -> {
                        var result = "on"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 20L..29L -> {
                        var result = "yirmi"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "otuz"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "kırk"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "elli"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "altmış"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "yetmiş"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "seksen"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "doksan"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "yüz"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = format((value) / 100)
                        result += " yüz"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "bin"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = format((value) / 1000)
                        result += " bin"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = format((value) / 1000000)
                        result += " milyon"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = format((value) / 1000000000)
                        result += " milyar"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " trilyon"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += " katrilyon"
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
                return format(kotlin.math.truncate(value).toLong()) + " virgül " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class Inci : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "inci"
                    else -> {
                        if (value >= 1.0) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("inci only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "inci"
                    else -> {
                        if (value >= 1L) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("inci only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "inci"
                    1L -> " " + spelloutOrdinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Nci : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "nci"
                    else -> {
                        if (value >= 1.0) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("nci only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "nci"
                    else -> {
                        if (value >= 1L) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("nci only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "nci"
                    1L -> " " + spelloutOrdinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Inci2 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ıncı"
                    else -> {
                        if (value >= 1.0) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("inci2 only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ıncı"
                    else -> {
                        if (value >= 1L) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("inci2 only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ıncı"
                    1L -> " " + spelloutOrdinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Uncu2 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "üncü"
                    else -> {
                        if (value >= 1.0) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("uncu2 only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "üncü"
                    else -> {
                        if (value >= 1L) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("uncu2 only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "üncü"
                    1L -> " " + spelloutOrdinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Uncu : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "uncu"
                    else -> {
                        if (value >= 1.0) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("uncu only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "uncu"
                    else -> {
                        if (value >= 1L) " " + spelloutOrdinal.format(value) else {
                            throw UnsupportedOperationException("uncu only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "uncu"
                    1L -> " " + spelloutOrdinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinal : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "eksi " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "eksi " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "sıfırıncı"
                    1L -> "birinci"
                    2L -> "ikinci"
                    3L -> "üçüncü"
                    4L -> "dördüncü"
                    5L -> "beşinci"
                    6L -> "altıncı"
                    7L -> "yedinci"
                    8L -> "sekizinci"
                    9L -> "dokuzuncu"
                    in 10L..19L -> "on" + uncu.format(value % 10)
                    in 20L..29L -> "yirmi" + nci.format(value % 10)
                    in 30L..39L -> "otuz" + uncu.format(value % 10)
                    in 40L..49L -> "kırk" + inci2.format(value % 10)
                    in 50L..59L -> "elli" + nci.format(value % 10)
                    in 60L..69L -> "altmış" + inci2.format(value % 10)
                    in 70L..79L -> "yetmiş" + inci.format(value % 10)
                    in 80L..89L -> "seksen" + inci.format(value % 10)
                    in 90L..99L -> "doksan" + inci2.format(value % 10)
                    in 100L..199L -> "yüz" + uncu2.format(value % 100)
                    in 200L..999L -> spelloutNumbering.format((value) / 100) + " yüz" + uncu2.format(value % 100)
                    in 1000L..1999L -> "bin" + inci.format(value % 1000)
                    in 2000L..999999L -> spelloutNumbering.format((value) / 1000) + " bin" + inci.format(value % 1000)
                    in 1000000L..999999999L -> spelloutNumbering.format((value) / 1000000) + " milyon" + uncu.format(value % 1000000)
                    in 1000000000L..999999999999L -> spelloutNumbering.format((value) / 1000000000) + " milyar" + inci2.format(value % 1000000000)
                    in 1000000000000L..999999999999999L -> spelloutNumbering.format((value) / 1000000000000) + " trilyon" + uncu.format(value % 1000000000000)
                    in 1000000000000000L..999999999999999999L -> spelloutNumbering.format((value) / 1000000000000000) + " katrilyon" + uncu.format(value % 1000000000000000)
                    else -> {
                        if (value >= 1000000000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) + "'inci" else {
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
                    1000,
                    2000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "sıfırıncı"
                    1L -> "birinci"
                    2L -> "ikinci"
                    3L -> "üçüncü"
                    4L -> "dördüncü"
                    5L -> "beşinci"
                    6L -> "altıncı"
                    7L -> "yedinci"
                    8L -> "sekizinci"
                    9L -> "dokuzuncu"
                    10L -> "on" + uncu.format(value % 10)
                    20L -> "yirmi" + nci.format(value % 10)
                    30L -> "otuz" + uncu.format(value % 10)
                    40L -> "kırk" + inci2.format(value % 10)
                    50L -> "elli" + nci.format(value % 10)
                    60L -> "altmış" + inci2.format(value % 10)
                    70L -> "yetmiş" + inci.format(value % 10)
                    80L -> "seksen" + inci.format(value % 10)
                    90L -> "doksan" + inci2.format(value % 10)
                    100L -> "yüz" + uncu2.format(value % 100)
                    200L -> spelloutNumbering.format((value * 200).toLong()) + " yüz" + uncu2.format(value % 100)
                    1000L -> "bin" + inci.format(value % 1000)
                    2000L -> spelloutNumbering.format((value * 2000).toLong()) + " bin" + inci.format(value % 1000)
                    1000000L -> spelloutNumbering.format((value * 1000000).toLong()) + " milyon" + uncu.format(value % 1000000)
                    1000000000L -> spelloutNumbering.format((value * 1000000000).toLong()) + " milyar" + inci2.format(value % 1000000000)
                    1000000000000L -> spelloutNumbering.format((value * 1000000000000).toLong()) + " trilyon" + uncu.format(value % 1000000000000)
                    1000000000000000L -> spelloutNumbering.format((value * 1000000000000000).toLong()) + " katrilyon" + uncu.format(value % 1000000000000000)
                    1000000000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 3,
                        secondary_grouping = 3,
                        symbols = language.numbers.symbols
                    ).format(value) + "'inci"

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
    override val spelloutCardinal: NumberFormatter
        get() = spelloutRules.spelloutCardinal
    override val spelloutOrdinal: NumberFormatter
        get() = spelloutRules.spelloutOrdinal
}
