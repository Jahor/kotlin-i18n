package info.leonenko.i18n.rbnf

import info.leonenko.i18n.*
import info.leonenko.i18n.languages.be


open class RBNFBe(val language: Language = Language.be) : RBNF {
    private inner class SpelloutRules {
        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumbering = SpelloutNumbering()

        val spelloutCardinalMasculine = SpelloutCardinalMasculine()

        val spelloutCardinalNeuter = SpelloutCardinalNeuter()

        val spelloutCardinalFeminine = SpelloutCardinalFeminine()

        val spelloutOrdinalMasculine = SpelloutOrdinalMasculine()

        val spelloutOrdinalFeminine = SpelloutOrdinalFeminine()

        val spelloutOrdinalNeuter = SpelloutOrdinalNeuter()

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
                    return "мінус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "мінус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нуль"
                    1L -> "адзiн"
                    2L -> "два"
                    3L -> "тры"
                    4L -> "чатыры"
                    5L -> "пяць"
                    6L -> "шэсць"
                    7L -> "сем"
                    8L -> "восем"
                    9L -> "дзевяць"
                    10L -> "дзесяць"
                    11L -> "адзінаццаць"
                    12L -> "дванаццаць"
                    13L -> "трынаццаць"
                    14L -> "чатырнаццаць"
                    15L -> "пятнаццаць"
                    16L -> "шаснаццаць"
                    17L -> "сямнаццаць"
                    18L -> "васямнаццаць"
                    19L -> "дзевятнаццаць"
                    in 20L..29L -> {
                        var result = "дваццаць"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "трыццаць"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорак"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пяцьдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шэсцьдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восемдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "дзевяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "сто"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "дзвесце"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "трыста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "чатырыста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "пяцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "шэсцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "семсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "восемсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "дзевяцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысяча", few = "тысячы", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "мільён", few = "мільёны", other = "мільёнаў").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "мільярд", few = "мільярды", other = "мільярдаў").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " "
                        result += Plurals(one = "трыльён", few = "трыльёны", other = "трылёнаў").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(one = "квадрыльён", few = "квадрыльёны", other = "квадрыльёнаў").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нуль"
                    1L -> "адзiн"
                    2L -> "два"
                    3L -> "тры"
                    4L -> "чатыры"
                    5L -> "пяць"
                    6L -> "шэсць"
                    7L -> "сем"
                    8L -> "восем"
                    9L -> "дзевяць"
                    10L -> "дзесяць"
                    11L -> "адзінаццаць"
                    12L -> "дванаццаць"
                    13L -> "трынаццаць"
                    14L -> "чатырнаццаць"
                    15L -> "пятнаццаць"
                    16L -> "шаснаццаць"
                    17L -> "сямнаццаць"
                    18L -> "васямнаццаць"
                    19L -> "дзевятнаццаць"
                    20L -> {
                        var result = "дваццаць"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "трыццаць"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорак"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пяцьдзесят"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шэсцьдзесят"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семдзесят"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восемдзесят"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "дзевяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "сто"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = "дзвесце"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = "трыста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> {
                        var result = "чатырыста"
                        result += (if (value * 400 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = "пяцьсот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> {
                        var result = "шэсцьсот"
                        result += (if (value * 600 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> {
                        var result = "семсот"
                        result += (if (value * 700 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> {
                        var result = "восемсот"
                        result += (if (value * 800 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> {
                        var result = "дзевяцьсот"
                        result += (if (value * 900 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminine.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысяча", few = "тысячы", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "мільён", few = "мільёны", other = "мільёнаў").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "мільярд", few = "мільярды", other = "мільярдаў").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = format((value * 1000000000000).toLong())
                        result += " "
                        result += Plurals(one = "трыльён", few = "трыльёны", other = "трылёнаў").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = format((value * 1000000000000000).toLong())
                        result += " "
                        result += Plurals(one = "квадрыльён", few = "квадрыльёны", other = "квадрыльёнаў").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000000 == 1.0) {
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
                return format(kotlin.math.truncate(value).toLong()) + " коска " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalNeuter : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "мінус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "мінус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нуль"
                    1L -> "адно"
                    2L -> "два"
                    in 3L..19L -> spelloutCardinalMasculine.format(value)
                    in 20L..29L -> {
                        var result = "дваццаць"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "трыццаць"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорак"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пяцьдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шэсцьдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восемдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "дзевяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "сто"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "дзвесце"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "трыста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "чатырыста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "пяцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "шэсцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "сямсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "васямсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "дзевяцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысяча", few = "тысячы", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "мільён", few = "мільёны", other = "мільёнаў").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "мільярд", few = "мільярды", other = "мільярдаў").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " "
                        result += Plurals(one = "трыльён", few = "трыльёны", other = "трылёнаў").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(one = "квадрыльён", few = "квадрыльёны", other = "квадрыльёнаў").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нуль"
                    1L -> "адно"
                    2L -> "два"
                    3L -> spelloutCardinalMasculine.format(value)
                    20L -> {
                        var result = "дваццаць"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "трыццаць"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорак"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пяцьдзесят"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шэсцьдзесят"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семдзесят"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восемдзесят"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "дзевяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "сто"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = "дзвесце"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = "трыста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> {
                        var result = "чатырыста"
                        result += (if (value * 400 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = "пяцьсот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> {
                        var result = "шэсцьсот"
                        result += (if (value * 600 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> {
                        var result = "сямсот"
                        result += (if (value * 700 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> {
                        var result = "васямсот"
                        result += (if (value * 800 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> {
                        var result = "дзевяцьсот"
                        result += (if (value * 900 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminine.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысяча", few = "тысячы", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "мільён", few = "мільёны", other = "мільёнаў").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "мільярд", few = "мільярды", other = "мільярдаў").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000).toLong())
                        result += " "
                        result += Plurals(one = "трыльён", few = "трыльёны", other = "трылёнаў").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000000).toLong())
                        result += " "
                        result += Plurals(one = "квадрыльён", few = "квадрыльёны", other = "квадрыльёнаў").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000000 == 1.0) {
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
                return format(kotlin.math.truncate(value).toLong()) + " коска " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalFeminine : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "мінус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "мінус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нуль"
                    1L -> "адна"
                    2L -> "дзве"
                    in 3L..19L -> spelloutCardinalMasculine.format(value)
                    in 20L..29L -> {
                        var result = "дваццаць"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "трыццаць"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорак"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пяцьдзясят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шэсцьдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восемдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "дзевяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "сто"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "дзвесце"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "трыста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "чатырыста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "пяцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "шэсцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "семсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "васямсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "дзевяцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысяча", few = "тысячы", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "мільён", few = "мільёны", other = "мільёнаў").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "мільярд", few = "мільярды", other = "мільярдаў").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " "
                        result += Plurals(one = "трыльён", few = "трыльёны", other = "трылёнаў").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(one = "квадрыльён", few = "квадрыльёны", other = "квадрыльёнаў").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нуль"
                    1L -> "адна"
                    2L -> "дзве"
                    3L -> spelloutCardinalMasculine.format(value)
                    20L -> {
                        var result = "дваццаць"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "трыццаць"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорак"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пяцьдзясят"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шэсцьдзесят"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семдзесят"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восемдзесят"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "дзевяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "сто"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = "дзвесце"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = "трыста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> {
                        var result = "чатырыста"
                        result += (if (value * 400 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = "пяцьсот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> {
                        var result = "шэсцьсот"
                        result += (if (value * 600 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> {
                        var result = "семсот"
                        result += (if (value * 700 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> {
                        var result = "васямсот"
                        result += (if (value * 800 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> {
                        var result = "дзевяцьсот"
                        result += (if (value * 900 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысяча", few = "тысячы", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "мільён", few = "мільёны", other = "мільёнаў").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "мільярд", few = "мільярды", other = "мільярдаў").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000).toLong())
                        result += " "
                        result += Plurals(one = "трыльён", few = "трыльёны", other = "трылёнаў").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000000).toLong())
                        result += " "
                        result += Plurals(one = "квадрыльён", few = "квадрыльёны", other = "квадрыльёнаў").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000000 == 1.0) {
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
                return format(kotlin.math.truncate(value).toLong()) + " коска " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutOrdinalMasculine : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "мінус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "мінус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулявы"
                    1L -> "першы"
                    2L -> "другі"
                    3L -> "трэйці"
                    4L -> "чацьверты"
                    5L -> "пяты"
                    6L -> "шосты"
                    7L -> "сёмы"
                    8L -> "восьмы"
                    9L -> "дзявяты"
                    10L -> "дзясяты"
                    11L -> "адзінаццаты"
                    12L -> "дванаццаты"
                    13L -> "трынаццаты"
                    14L -> "чатырнаццаты"
                    15L -> "пятнаццаты"
                    16L -> "шаснаццаты"
                    17L -> "сямнаццаты"
                    18L -> "васямнаццаты"
                    19L -> "дзевятнаццаты"
                    20L -> "дваццаты"
                    in 21L..29L -> {
                        var result = "дваццаць"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> "трыццаты"
                    in 31L..39L -> {
                        var result = "трыццаць"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> "саракавы"
                    in 41L..49L -> {
                        var result = "сорак"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> "пяцідзясяты"
                    in 51L..59L -> {
                        var result = "пяцідзясят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> "шэсцьдзесяты"
                    in 61L..69L -> {
                        var result = "шэсцьдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> "семдзесяты"
                    in 71L..79L -> {
                        var result = "семдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> "васьмідзясяты"
                    in 81L..89L -> {
                        var result = "восемдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> "дзевяносты"
                    in 91L..99L -> {
                        var result = "дзевяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> "соты"
                    in 101L..199L -> {
                        var result = "сто"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> "дзвухсоты"
                    in 201L..299L -> {
                        var result = "дзвесце"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> "трохсоты"
                    in 301L..399L -> {
                        var result = "трыста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> "чатырохсоты"
                    in 401L..499L -> {
                        var result = "чатырыста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> "пяцісоты"
                    in 501L..599L -> {
                        var result = "пяцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> "шасьцісоты"
                    in 601L..699L -> {
                        var result = "шэсцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> "сямісоты"
                    in 701L..799L -> {
                        var result = "семсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> "васьмісоты"
                    in 801L..899L -> {
                        var result = "васямсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> "дзевяцісоты"
                    in 901L..999L -> {
                        var result = "дзевяцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> spelloutCardinalFeminine.format((value) / 1000) + " тысячны"
                    in 1001L..1999L -> if ((value) % 1000 == 0L) {
                        spelloutCardinalFeminine.format((value) / 1000) + " тысячны"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысяча"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> "дзвух тысячны"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        "дзвух тысячны"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысячы"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> spelloutCardinalFeminine.format((value) / 1000) + " тысячны"
                    in 5001L..9999L -> if ((value) % 1000 == 0L) {
                        spelloutCardinalFeminine.format((value) / 1000) + " тысячны"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    10000L -> "дзесяці тысячны"
                    in 10001L..10999L -> {
                        var result = "дзесяць тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 11000L..19999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    20000L -> "дваццаці тысячны"
                    in 20001L..20999L -> if ((value) % 1000 == 0L) {
                        "дваццаці тысячны"
                    } else {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 21000L..99999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысяча"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    100000L -> "сто тысячны"
                    in 100001L..109999L -> if ((value) % 1000 == 0L) {
                        "сто тысячны"
                    } else {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 110000L..199999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " тысячны"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    200000L -> "дзвухсот тысячны"
                    in 200001L..299999L -> if ((value) % 1000 == 0L) {
                        "дзвухсот тысячны"
                    } else {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " тысячны"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    300000L -> "трохсот тысячны"
                    in 300001L..399999L -> if ((value) % 1000 == 0L) {
                        "трохсот тысячны"
                    } else {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " тысячны"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    400000L -> "чатырохсот тысячны"
                    in 400001L..499999L -> if ((value) % 1000 == 0L) {
                        "чатырохсот тысячны"
                    } else {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " тысячны"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 500000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " тысячнае"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " мільён"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..4999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " мільёны"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 5000000L..999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " мільёнаў"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " мільярд"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..4999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " мільярды"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 5000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " мільярдаў"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " трыльён"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..4999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " трыльёны"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 5000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " трылёнаў"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " квадрыльён"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..4999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " квадрыльёны"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 5000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " квадрыльёнаў"
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
                    12,
                    13,
                    14,
                    15,
                    16,
                    17,
                    18,
                    19,
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
                    70,
                    71,
                    80,
                    81,
                    90,
                    91,
                    100,
                    101,
                    200,
                    201,
                    300,
                    301,
                    400,
                    401,
                    500,
                    501,
                    600,
                    601,
                    700,
                    701,
                    800,
                    801,
                    900,
                    901,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    10000,
                    10001,
                    11000,
                    20000,
                    20001,
                    21000,
                    100000,
                    100001,
                    110000,
                    200000,
                    200001,
                    300000,
                    300001,
                    400000,
                    400001,
                    500000,
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
                    0L -> "нулявы"
                    1L -> "першы"
                    2L -> "другі"
                    3L -> "трэйці"
                    4L -> "чацьверты"
                    5L -> "пяты"
                    6L -> "шосты"
                    7L -> "сёмы"
                    8L -> "восьмы"
                    9L -> "дзявяты"
                    10L -> "дзясяты"
                    11L -> "адзінаццаты"
                    12L -> "дванаццаты"
                    13L -> "трынаццаты"
                    14L -> "чатырнаццаты"
                    15L -> "пятнаццаты"
                    16L -> "шаснаццаты"
                    17L -> "сямнаццаты"
                    18L -> "васямнаццаты"
                    19L -> "дзевятнаццаты"
                    20L -> "дваццаты"
                    21L -> {
                        var result = "дваццаць"
                        result += (if (value * 21 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> "трыццаты"
                    31L -> {
                        var result = "трыццаць"
                        result += (if (value * 31 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> "саракавы"
                    41L -> {
                        var result = "сорак"
                        result += (if (value * 41 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> "пяцідзясяты"
                    51L -> {
                        var result = "пяцідзясят"
                        result += (if (value * 51 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> "шэсцьдзесяты"
                    61L -> {
                        var result = "шэсцьдзесят"
                        result += (if (value * 61 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> "семдзесяты"
                    71L -> {
                        var result = "семдзесят"
                        result += (if (value * 71 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> "васьмідзясяты"
                    81L -> {
                        var result = "восемдзесят"
                        result += (if (value * 81 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> "дзевяносты"
                    91L -> {
                        var result = "дзевяноста"
                        result += (if (value * 91 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> "соты"
                    101L -> {
                        var result = "сто"
                        result += (if (value * 101 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> "дзвухсоты"
                    201L -> {
                        var result = "дзвесце"
                        result += (if (value * 201 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> "трохсоты"
                    301L -> {
                        var result = "трыста"
                        result += (if (value * 301 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> "чатырохсоты"
                    401L -> {
                        var result = "чатырыста"
                        result += (if (value * 401 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> "пяцісоты"
                    501L -> {
                        var result = "пяцьсот"
                        result += (if (value * 501 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> "шасьцісоты"
                    601L -> {
                        var result = "шэсцьсот"
                        result += (if (value * 601 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> "сямісоты"
                    701L -> {
                        var result = "семсот"
                        result += (if (value * 701 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> "васьмісоты"
                    801L -> {
                        var result = "васямсот"
                        result += (if (value * 801 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> "дзевяцісоты"
                    901L -> {
                        var result = "дзевяцьсот"
                        result += (if (value * 901 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> spelloutCardinalFeminine.format((value * 1000).toLong()) + " тысячны"
                    1001L -> {
                        var result = spelloutCardinalFeminine.format((value * 1001).toLong())
                        result += " тысяча"
                        result += (if (value * 1001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> "дзвух тысячны"
                    2001L -> {
                        var result = spelloutCardinalFeminine.format((value * 2001).toLong())
                        result += " тысячы"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> spelloutCardinalFeminine.format((value * 5000).toLong()) + " тысячны"
                    5001L -> {
                        var result = spelloutCardinalFeminine.format((value * 5001).toLong())
                        result += " тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    10000L -> "дзесяці тысячны"
                    10001L -> {
                        var result = "дзесяць тысяч"
                        result += (if (value * 10001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    11000L -> {
                        var result = spelloutCardinalMasculine.format((value * 11000).toLong())
                        result += " тысяч"
                        result += (if (value * 11000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    20000L -> "дваццаці тысячны"
                    20001L -> {
                        var result = spelloutCardinalMasculine.format((value * 20001).toLong())
                        result += " тысяч"
                        result += (if (value * 20001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21000L -> {
                        var result = spelloutCardinalFeminine.format((value * 21000).toLong())
                        result += " тысяча"
                        result += (if (value * 21000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    100000L -> "сто тысячны"
                    100001L -> {
                        var result = spelloutCardinalMasculine.format((value * 100001).toLong())
                        result += " тысяч"
                        result += (if (value * 100001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    110000L -> {
                        var result = spelloutCardinalMasculine.format((value * 110000).toLong())
                        result += " тысячны"
                        result += (if (value * 110000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    200000L -> "дзвухсот тысячны"
                    200001L -> {
                        var result = spelloutCardinalMasculine.format((value * 200001).toLong())
                        result += " тысячны"
                        result += (if (value * 200001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    300000L -> "трохсот тысячны"
                    300001L -> {
                        var result = spelloutCardinalMasculine.format((value * 300001).toLong())
                        result += " тысячны"
                        result += (if (value * 300001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    400000L -> "чатырохсот тысячны"
                    400001L -> {
                        var result = spelloutCardinalMasculine.format((value * 400001).toLong())
                        result += " тысячны"
                        result += (if (value * 400001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    500000L -> {
                        var result = spelloutCardinalMasculine.format((value * 500000).toLong())
                        result += " тысячнае"
                        result += (if (value * 500000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000).toLong())
                        result += " мільён"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000).toLong())
                        result += " мільёны"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    5000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000).toLong())
                        result += " мільёнаў"
                        result += (if (value * 5000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000).toLong())
                        result += " мільярд"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000).toLong())
                        result += " мільярды"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    5000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000000).toLong())
                        result += " мільярдаў"
                        result += (if (value * 5000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000).toLong())
                        result += " трыльён"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000).toLong())
                        result += " трыльёны"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    5000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000000000).toLong())
                        result += " трылёнаў"
                        result += (if (value * 5000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000000).toLong())
                        result += " квадрыльён"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000000).toLong())
                        result += " квадрыльёны"
                        result += (if (value * 2000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    5000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000000000000).toLong())
                        result += " квадрыльёнаў"
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

        private inner class SpelloutOrdinalFeminine : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "мінус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "мінус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулявая"
                    1L -> "першая"
                    2L -> "другая"
                    3L -> "трэццяя"
                    4L -> "чацьвертая"
                    5L -> "пятая"
                    6L -> "шостая"
                    7L -> "сёмая"
                    8L -> "восьмая"
                    9L -> "дзявятая"
                    10L -> "дзясятая"
                    11L -> "адзінаццатая"
                    12L -> "дванаццатая"
                    13L -> "трынаццатая"
                    14L -> "чатырнаццатая"
                    15L -> "пятнаццатая"
                    16L -> "шаснаццатая"
                    17L -> "сямнаццатая"
                    18L -> "васямнаццатая"
                    19L -> "дзевятнаццатая"
                    20L -> "дваццатая"
                    in 21L..29L -> {
                        var result = "дваццаць"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> "трыццатая"
                    in 31L..39L -> {
                        var result = "трыццаць"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> "саракавая"
                    in 41L..49L -> {
                        var result = "сорак"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> "пяцідзесятая"
                    in 51L..59L -> {
                        var result = "пяцідзясят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> "шэсцідзесятая"
                    in 61L..69L -> {
                        var result = "шэсцьдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> "семдзесятая"
                    in 71L..79L -> {
                        var result = "семдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> "васьмідзясятая"
                    in 81L..89L -> {
                        var result = "восемдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> "дзевяностая"
                    in 91L..99L -> {
                        var result = "дзевяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> "сотая"
                    in 101L..199L -> {
                        var result = "сто"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> "дзвухсотая"
                    in 201L..299L -> {
                        var result = "дзвесце"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> "трохсотая"
                    in 301L..399L -> {
                        var result = "трыста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> "чатырохсотая"
                    in 401L..499L -> {
                        var result = "чатырыста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> "пяцісотая"
                    in 501L..599L -> {
                        var result = "пяцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> "шасьцісотая"
                    in 601L..699L -> {
                        var result = "шэсцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> "сямісотая"
                    in 701L..799L -> {
                        var result = "семсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> "васьмісотая"
                    in 801L..899L -> {
                        var result = "васямсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> "дзевяцісотая"
                    in 901L..999L -> {
                        var result = "дзевяцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> spelloutCardinalFeminine.format((value) / 1000) + " тысячны"
                    in 1001L..1999L -> if ((value) % 1000 == 0L) {
                        spelloutCardinalFeminine.format((value) / 1000) + " тысячны"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысяча"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> "дзвух тысячная"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        "дзвух тысячная"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысячы"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> spelloutCardinalFeminine.format((value) / 1000) + " тысячная"
                    in 5001L..9999L -> if ((value) % 1000 == 0L) {
                        spelloutCardinalFeminine.format((value) / 1000) + " тысячная"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    10000L -> "дзесяці тысячная"
                    in 10001L..10999L -> {
                        var result = "дзесяць тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 11000L..19999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    20000L -> "дваццаці тысячная"
                    in 20001L..20999L -> if ((value) % 1000 == 0L) {
                        "дваццаці тысячная"
                    } else {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 21000L..99999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысяча"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    100000L -> "сто тысячная"
                    in 100001L..109999L -> if ((value) % 1000 == 0L) {
                        "сто тысячная"
                    } else {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 110000L..199999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысячная"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    200000L -> "дзвухсот тысячная"
                    in 200001L..299999L -> if ((value) % 1000 == 0L) {
                        "дзвухсот тысячная"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысячная"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    300000L -> "трохсот тысячная"
                    in 300001L..399999L -> if ((value) % 1000 == 0L) {
                        "трохсот тысячная"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысячная"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    400000L -> "чатырохсот тысячная"
                    in 400001L..499999L -> if ((value) % 1000 == 0L) {
                        "чатырохсот тысячная"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысячная"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 500000L..999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысячнае"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " мільён"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..4999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " мільёны"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 5000000L..999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " мільёнаў"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " мільярд"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..4999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " мільярды"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 5000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " мільярдаў"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " трыльён"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..4999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " трыльёны"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 5000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " трылёнаў"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " квадрыльён"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..4999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " квадрыльёны"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 5000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " квадрыльёнаў"
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
                    12,
                    13,
                    14,
                    15,
                    16,
                    17,
                    18,
                    19,
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
                    70,
                    71,
                    80,
                    81,
                    90,
                    91,
                    100,
                    101,
                    200,
                    201,
                    300,
                    301,
                    400,
                    401,
                    500,
                    501,
                    600,
                    601,
                    700,
                    701,
                    800,
                    801,
                    900,
                    901,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    10000,
                    10001,
                    11000,
                    20000,
                    20001,
                    21000,
                    100000,
                    100001,
                    110000,
                    200000,
                    200001,
                    300000,
                    300001,
                    400000,
                    400001,
                    500000,
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
                    0L -> "нулявая"
                    1L -> "першая"
                    2L -> "другая"
                    3L -> "трэццяя"
                    4L -> "чацьвертая"
                    5L -> "пятая"
                    6L -> "шостая"
                    7L -> "сёмая"
                    8L -> "восьмая"
                    9L -> "дзявятая"
                    10L -> "дзясятая"
                    11L -> "адзінаццатая"
                    12L -> "дванаццатая"
                    13L -> "трынаццатая"
                    14L -> "чатырнаццатая"
                    15L -> "пятнаццатая"
                    16L -> "шаснаццатая"
                    17L -> "сямнаццатая"
                    18L -> "васямнаццатая"
                    19L -> "дзевятнаццатая"
                    20L -> "дваццатая"
                    21L -> {
                        var result = "дваццаць"
                        result += (if (value * 21 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> "трыццатая"
                    31L -> {
                        var result = "трыццаць"
                        result += (if (value * 31 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> "саракавая"
                    41L -> {
                        var result = "сорак"
                        result += (if (value * 41 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> "пяцідзесятая"
                    51L -> {
                        var result = "пяцідзясят"
                        result += (if (value * 51 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> "шэсцідзесятая"
                    61L -> {
                        var result = "шэсцьдзесят"
                        result += (if (value * 61 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> "семдзесятая"
                    71L -> {
                        var result = "семдзесят"
                        result += (if (value * 71 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> "васьмідзясятая"
                    81L -> {
                        var result = "восемдзесят"
                        result += (if (value * 81 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> "дзевяностая"
                    91L -> {
                        var result = "дзевяноста"
                        result += (if (value * 91 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> "сотая"
                    101L -> {
                        var result = "сто"
                        result += (if (value * 101 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> "дзвухсотая"
                    201L -> {
                        var result = "дзвесце"
                        result += (if (value * 201 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> "трохсотая"
                    301L -> {
                        var result = "трыста"
                        result += (if (value * 301 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> "чатырохсотая"
                    401L -> {
                        var result = "чатырыста"
                        result += (if (value * 401 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> "пяцісотая"
                    501L -> {
                        var result = "пяцьсот"
                        result += (if (value * 501 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> "шасьцісотая"
                    601L -> {
                        var result = "шэсцьсот"
                        result += (if (value * 601 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> "сямісотая"
                    701L -> {
                        var result = "семсот"
                        result += (if (value * 701 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> "васьмісотая"
                    801L -> {
                        var result = "васямсот"
                        result += (if (value * 801 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> "дзевяцісотая"
                    901L -> {
                        var result = "дзевяцьсот"
                        result += (if (value * 901 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> spelloutCardinalFeminine.format((value * 1000).toLong()) + " тысячны"
                    1001L -> {
                        var result = spelloutCardinalFeminine.format((value * 1001).toLong())
                        result += " тысяча"
                        result += (if (value * 1001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> "дзвух тысячная"
                    2001L -> {
                        var result = spelloutCardinalFeminine.format((value * 2001).toLong())
                        result += " тысячы"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> spelloutCardinalFeminine.format((value * 5000).toLong()) + " тысячная"
                    5001L -> {
                        var result = spelloutCardinalFeminine.format((value * 5001).toLong())
                        result += " тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    10000L -> "дзесяці тысячная"
                    10001L -> {
                        var result = "дзесяць тысяч"
                        result += (if (value * 10001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    11000L -> {
                        var result = spelloutCardinalMasculine.format((value * 11000).toLong())
                        result += " тысяч"
                        result += (if (value * 11000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    20000L -> "дваццаці тысячная"
                    20001L -> {
                        var result = spelloutCardinalMasculine.format((value * 20001).toLong())
                        result += " тысяч"
                        result += (if (value * 20001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21000L -> {
                        var result = spelloutCardinalFeminine.format((value * 21000).toLong())
                        result += " тысяча"
                        result += (if (value * 21000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    100000L -> "сто тысячная"
                    100001L -> {
                        var result = spelloutCardinalMasculine.format((value * 100001).toLong())
                        result += " тысяч"
                        result += (if (value * 100001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    110000L -> {
                        var result = spelloutCardinalFeminine.format((value * 110000).toLong())
                        result += " тысячная"
                        result += (if (value * 110000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    200000L -> "дзвухсот тысячная"
                    200001L -> {
                        var result = spelloutCardinalFeminine.format((value * 200001).toLong())
                        result += " тысячная"
                        result += (if (value * 200001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    300000L -> "трохсот тысячная"
                    300001L -> {
                        var result = spelloutCardinalFeminine.format((value * 300001).toLong())
                        result += " тысячная"
                        result += (if (value * 300001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    400000L -> "чатырохсот тысячная"
                    400001L -> {
                        var result = spelloutCardinalFeminine.format((value * 400001).toLong())
                        result += " тысячная"
                        result += (if (value * 400001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    500000L -> {
                        var result = spelloutCardinalFeminine.format((value * 500000).toLong())
                        result += " тысячнае"
                        result += (if (value * 500000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000).toLong())
                        result += " мільён"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000).toLong())
                        result += " мільёны"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    5000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000).toLong())
                        result += " мільёнаў"
                        result += (if (value * 5000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000).toLong())
                        result += " мільярд"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000).toLong())
                        result += " мільярды"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    5000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000000).toLong())
                        result += " мільярдаў"
                        result += (if (value * 5000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000).toLong())
                        result += " трыльён"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000).toLong())
                        result += " трыльёны"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    5000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000000000).toLong())
                        result += " трылёнаў"
                        result += (if (value * 5000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000000).toLong())
                        result += " квадрыльён"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000000).toLong())
                        result += " квадрыльёны"
                        result += (if (value * 2000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    5000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000000000000).toLong())
                        result += " квадрыльёнаў"
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

        private inner class SpelloutOrdinalNeuter : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "мінус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "мінус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулявое"
                    1L -> "першае"
                    2L -> "другое"
                    3L -> "трэццяе"
                    4L -> "чацьвертае"
                    5L -> "пятае"
                    6L -> "шостае"
                    7L -> "сёмае"
                    8L -> "восьмае"
                    9L -> "дзявятае"
                    10L -> "дзясятае"
                    11L -> "адзінаццатае"
                    12L -> "дванаццатае"
                    13L -> "трынаццатае"
                    14L -> "чатырнаццатае"
                    15L -> "пятнаццатае"
                    16L -> "шаснаццатае"
                    17L -> "сямнаццатае"
                    18L -> "васямнаццатае"
                    19L -> "дзевятнаццатае"
                    20L -> "дваццатае"
                    in 21L..29L -> {
                        var result = "дваццаць"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> "трыццатае"
                    in 31L..39L -> {
                        var result = "трыццаць"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> "саракавое"
                    in 41L..49L -> {
                        var result = "сорак"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> "пяцьдзесятае"
                    in 51L..59L -> {
                        var result = "пяцідзясят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> "шэсцідзясятае"
                    in 61L..69L -> {
                        var result = "шэсцьдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> "сямдзясятае"
                    in 71L..79L -> {
                        var result = "семдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> "васьмідзясятае"
                    in 81L..89L -> {
                        var result = "восемдзесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> "дзевяностае"
                    in 91L..99L -> {
                        var result = "дзевяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> "сотае"
                    in 101L..199L -> {
                        var result = "сто"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> "дзвухсотае"
                    in 201L..299L -> {
                        var result = "дзвесце"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> "трохсотае"
                    in 301L..399L -> {
                        var result = "трыста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> "чатырохсотае"
                    in 401L..499L -> {
                        var result = "чатырыста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> "пяцісотае"
                    in 501L..599L -> {
                        var result = "пяцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> "шасьцісотае"
                    in 601L..699L -> {
                        var result = "шэсцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> "сямісотае"
                    in 701L..799L -> {
                        var result = "семсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> "васьмісотае"
                    in 801L..899L -> {
                        var result = "васямсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> "дзевяцісотае"
                    in 901L..999L -> {
                        var result = "дзевяцьсот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> spelloutCardinalFeminine.format((value) / 1000) + " тысячны"
                    in 1001L..1999L -> if ((value) % 1000 == 0L) {
                        spelloutCardinalFeminine.format((value) / 1000) + " тысячны"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысяча"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> "дзвух тысячнае"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        "дзвух тысячнае"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысячы"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> spelloutCardinalFeminine.format((value) / 1000) + " тысячнае"
                    in 5001L..9999L -> if ((value) % 1000 == 0L) {
                        spelloutCardinalFeminine.format((value) / 1000) + " тысячнае"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    10000L -> "дзесяці тысячнае"
                    in 10001L..10999L -> {
                        var result = "дзесяць тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 11000L..19999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    20000L -> "дваццаці тысячнае"
                    in 20001L..20999L -> if ((value) % 1000 == 0L) {
                        "дваццаці тысячнае"
                    } else {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 21000L..99999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысяча"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    100000L -> "сто тысячнае"
                    in 100001L..109999L -> if ((value) % 1000 == 0L) {
                        "сто тысячнае"
                    } else {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 110000L..199999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысячнае"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    200000L -> "дзвухсот тысячнае"
                    in 200001L..299999L -> if ((value) % 1000 == 0L) {
                        "дзвухсот тысячнае"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысячнае"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    300000L -> "трохсот тысячнае"
                    in 300001L..399999L -> if ((value) % 1000 == 0L) {
                        "трохсот тысячнае"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысячнае"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    400000L -> "чатырохсот тысячнае"
                    in 400001L..499999L -> if ((value) % 1000 == 0L) {
                        "чатырохсот тысячнае"
                    } else {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысячнае"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 500000L..999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " тысячнае"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " мільён"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..4999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " мільёны"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 5000000L..999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " мільёнаў"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " мільярд"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..4999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " мільярды"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 5000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " мільярдаў"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " трыльён"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..4999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " трыльёны"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 5000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " трылёнаў"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " квадрыльён"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..4999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " квадрыльёны"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 5000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " квадрыльёнаў"
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
                            throw UnsupportedOperationException("spelloutOrdinalNeuter only supports numbers >= 0")
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
                    21,
                    30,
                    31,
                    40,
                    41,
                    50,
                    51,
                    60,
                    61,
                    70,
                    71,
                    80,
                    81,
                    90,
                    91,
                    100,
                    101,
                    200,
                    201,
                    300,
                    301,
                    400,
                    401,
                    500,
                    501,
                    600,
                    601,
                    700,
                    701,
                    800,
                    801,
                    900,
                    901,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    10000,
                    10001,
                    11000,
                    20000,
                    20001,
                    21000,
                    100000,
                    100001,
                    110000,
                    200000,
                    200001,
                    300000,
                    300001,
                    400000,
                    400001,
                    500000,
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
                    0L -> "нулявое"
                    1L -> "першае"
                    2L -> "другое"
                    3L -> "трэццяе"
                    4L -> "чацьвертае"
                    5L -> "пятае"
                    6L -> "шостае"
                    7L -> "сёмае"
                    8L -> "восьмае"
                    9L -> "дзявятае"
                    10L -> "дзясятае"
                    11L -> "адзінаццатае"
                    12L -> "дванаццатае"
                    13L -> "трынаццатае"
                    14L -> "чатырнаццатае"
                    15L -> "пятнаццатае"
                    16L -> "шаснаццатае"
                    17L -> "сямнаццатае"
                    18L -> "васямнаццатае"
                    19L -> "дзевятнаццатае"
                    20L -> "дваццатае"
                    21L -> {
                        var result = "дваццаць"
                        result += (if (value * 21 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> "трыццатае"
                    31L -> {
                        var result = "трыццаць"
                        result += (if (value * 31 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> "саракавое"
                    41L -> {
                        var result = "сорак"
                        result += (if (value * 41 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> "пяцьдзесятае"
                    51L -> {
                        var result = "пяцідзясят"
                        result += (if (value * 51 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> "шэсцідзясятае"
                    61L -> {
                        var result = "шэсцьдзесят"
                        result += (if (value * 61 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> "сямдзясятае"
                    71L -> {
                        var result = "семдзесят"
                        result += (if (value * 71 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> "васьмідзясятае"
                    81L -> {
                        var result = "восемдзесят"
                        result += (if (value * 81 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> "дзевяностае"
                    91L -> {
                        var result = "дзевяноста"
                        result += (if (value * 91 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> "сотае"
                    101L -> {
                        var result = "сто"
                        result += (if (value * 101 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> "дзвухсотае"
                    201L -> {
                        var result = "дзвесце"
                        result += (if (value * 201 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> "трохсотае"
                    301L -> {
                        var result = "трыста"
                        result += (if (value * 301 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    400L -> "чатырохсотае"
                    401L -> {
                        var result = "чатырыста"
                        result += (if (value * 401 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> "пяцісотае"
                    501L -> {
                        var result = "пяцьсот"
                        result += (if (value * 501 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    600L -> "шасьцісотае"
                    601L -> {
                        var result = "шэсцьсот"
                        result += (if (value * 601 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    700L -> "сямісотае"
                    701L -> {
                        var result = "семсот"
                        result += (if (value * 701 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    800L -> "васьмісотае"
                    801L -> {
                        var result = "васямсот"
                        result += (if (value * 801 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    900L -> "дзевяцісотае"
                    901L -> {
                        var result = "дзевяцьсот"
                        result += (if (value * 901 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> spelloutCardinalFeminine.format((value * 1000).toLong()) + " тысячны"
                    1001L -> {
                        var result = spelloutCardinalFeminine.format((value * 1001).toLong())
                        result += " тысяча"
                        result += (if (value * 1001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    2000L -> "дзвух тысячнае"
                    2001L -> {
                        var result = spelloutCardinalFeminine.format((value * 2001).toLong())
                        result += " тысячы"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> spelloutCardinalFeminine.format((value * 5000).toLong()) + " тысячнае"
                    5001L -> {
                        var result = spelloutCardinalFeminine.format((value * 5001).toLong())
                        result += " тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    10000L -> "дзесяці тысячнае"
                    10001L -> {
                        var result = "дзесяць тысяч"
                        result += (if (value * 10001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    11000L -> {
                        var result = spelloutCardinalMasculine.format((value * 11000).toLong())
                        result += " тысяч"
                        result += (if (value * 11000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    20000L -> "дваццаці тысячнае"
                    20001L -> {
                        var result = spelloutCardinalMasculine.format((value * 20001).toLong())
                        result += " тысяч"
                        result += (if (value * 20001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21000L -> {
                        var result = spelloutCardinalFeminine.format((value * 21000).toLong())
                        result += " тысяча"
                        result += (if (value * 21000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    100000L -> "сто тысячнае"
                    100001L -> {
                        var result = spelloutCardinalMasculine.format((value * 100001).toLong())
                        result += " тысяч"
                        result += (if (value * 100001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    110000L -> {
                        var result = spelloutCardinalFeminine.format((value * 110000).toLong())
                        result += " тысячнае"
                        result += (if (value * 110000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    200000L -> "дзвухсот тысячнае"
                    200001L -> {
                        var result = spelloutCardinalFeminine.format((value * 200001).toLong())
                        result += " тысячнае"
                        result += (if (value * 200001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    300000L -> "трохсот тысячнае"
                    300001L -> {
                        var result = spelloutCardinalFeminine.format((value * 300001).toLong())
                        result += " тысячнае"
                        result += (if (value * 300001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    400000L -> "чатырохсот тысячнае"
                    400001L -> {
                        var result = spelloutCardinalFeminine.format((value * 400001).toLong())
                        result += " тысячнае"
                        result += (if (value * 400001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    500000L -> {
                        var result = spelloutCardinalFeminine.format((value * 500000).toLong())
                        result += " тысячнае"
                        result += (if (value * 500000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000).toLong())
                        result += " мільён"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000).toLong())
                        result += " мільёны"
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    5000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000).toLong())
                        result += " мільёнаў"
                        result += (if (value * 5000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000).toLong())
                        result += " мільярд"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000).toLong())
                        result += " мільярды"
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    5000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000000).toLong())
                        result += " мільярдаў"
                        result += (if (value * 5000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000).toLong())
                        result += " трыльён"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000).toLong())
                        result += " трыльёны"
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    5000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000000000).toLong())
                        result += " трылёнаў"
                        result += (if (value * 5000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000000).toLong())
                        result += " квадрыльён"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000000).toLong())
                        result += " квадрыльёны"
                        result += (if (value * 2000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    5000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 5000000000000000).toLong())
                        result += " квадрыльёнаў"
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
    open val spelloutOrdinalFeminine: NumberFormatter
        get() = spelloutRules.spelloutOrdinalFeminine
    open val spelloutOrdinalNeuter: NumberFormatter
        get() = spelloutRules.spelloutOrdinalNeuter
    override val spelloutOrdinal: NumberFormatter
        get() = TODO("spelloutOrdinal is not available for be")
    override val spelloutCardinal: NumberFormatter
        get() = TODO("spelloutCardinal is not available for be")

    fun spelloutCardinalFor(gender: Gender): NumberFormatter {
        return when (gender) {
            Gender.Neuter -> spelloutCardinalNeuter
            Gender.Masculine -> spelloutCardinalMasculine
            Gender.Feminine -> spelloutCardinalFeminine
            else -> TODO("be does not support $gender gender")
        }

    }

    fun spelloutOrdinalFor(gender: Gender): NumberFormatter {
        return when (gender) {
            Gender.Neuter -> spelloutOrdinalNeuter
            Gender.Masculine -> spelloutOrdinalMasculine
            Gender.Feminine -> spelloutOrdinalFeminine
            else -> TODO("be does not support $gender gender")
        }

    }
}
