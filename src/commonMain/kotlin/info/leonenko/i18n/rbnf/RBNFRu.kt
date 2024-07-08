package info.leonenko.i18n.rbnf

import info.leonenko.i18n.*
import info.leonenko.i18n.languages.ru
import kotlin.math.roundToLong


open class RBNFRu(val language: Language = Language.ru) : RBNF {
    private inner class SpelloutRules {
        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumbering = SpelloutNumbering()

        val spelloutCardinalMasculine = SpelloutCardinalMasculine()

        val spelloutCardinalNeuter = SpelloutCardinalNeuter()

        val spelloutCardinalFeminine = SpelloutCardinalFeminine()

        val spelloutCardinalPlural = SpelloutCardinalPlural()

        val fractionsFeminine = FractionsFeminine()

        val spelloutCardinalMasculineGenitive = SpelloutCardinalMasculineGenitive()

        val spelloutCardinalNeuterGenitive = SpelloutCardinalNeuterGenitive()

        val spelloutCardinalFeminineGenitive = SpelloutCardinalFeminineGenitive()

        val spelloutCardinalPluralGenitive = SpelloutCardinalPluralGenitive()

        val fractionsFeminineGenitive = FractionsFeminineGenitive()

        val spelloutCardinalMasculineDative = SpelloutCardinalMasculineDative()

        val spelloutCardinalNeuterDative = SpelloutCardinalNeuterDative()

        val spelloutCardinalFeminineDative = SpelloutCardinalFeminineDative()

        val spelloutCardinalPluralDative = SpelloutCardinalPluralDative()

        val fractionsFeminineDative = FractionsFeminineDative()

        val spelloutCardinalMasculineAccusative = SpelloutCardinalMasculineAccusative()

        val spelloutCardinalNeuterAccusative = SpelloutCardinalNeuterAccusative()

        val spelloutCardinalFeminineAccusative = SpelloutCardinalFeminineAccusative()

        val spelloutCardinalPluralAccusative = SpelloutCardinalPluralAccusative()

        val fractionsFeminineAccusative = FractionsFeminineAccusative()

        val spelloutCardinalMasculineLocative = SpelloutCardinalMasculineLocative()

        val spelloutCardinalNeuterLocative = SpelloutCardinalNeuterLocative()

        val spelloutCardinalFeminineLocative = SpelloutCardinalFeminineLocative()

        val spelloutCardinalPluralLocative = SpelloutCardinalPluralLocative()

        val fractionsFeminineLocative = FractionsFeminineLocative()

        val spelloutCardinalMasculineAblative = SpelloutCardinalMasculineAblative()

        val spelloutCardinalNeuterAblative = SpelloutCardinalNeuterAblative()

        val spelloutCardinalFeminineAblative = SpelloutCardinalFeminineAblative()

        val spelloutCardinalPluralAblative = SpelloutCardinalPluralAblative()

        val fractionsFeminineAblative = FractionsFeminineAblative()

        val yj = Yj()

        val oe = Oe()

        val aja = Aja()

        val omu = Omu()

        val oj = Oj()

        val ogo = Ogo()

        val uju = Uju()

        val ym = Ym()

        val om = Om()

        val ye = Ye()

        val yh = Yh()

        val ymi = Ymi()

        val thousandsprefixconjoined = Thousandsprefixconjoined()

        val thousandsprefixseparate = Thousandsprefixseparate()

        val spelloutOrdinalMasculine = SpelloutOrdinalMasculine()

        val spelloutOrdinalNeuter = SpelloutOrdinalNeuter()

        val spelloutOrdinalFeminine = SpelloutOrdinalFeminine()

        val spelloutOrdinalPlural = SpelloutOrdinalPlural()

        val spelloutOrdinalMasculineGenitive = SpelloutOrdinalMasculineGenitive()

        val spelloutOrdinalNeuterGenitive = SpelloutOrdinalNeuterGenitive()

        val spelloutOrdinalFeminineGenitive = SpelloutOrdinalFeminineGenitive()

        val spelloutOrdinalPluralGenitive = SpelloutOrdinalPluralGenitive()

        val spelloutOrdinalMasculineDative = SpelloutOrdinalMasculineDative()

        val spelloutOrdinalNeuterDative = SpelloutOrdinalNeuterDative()

        val spelloutOrdinalFeminineDative = SpelloutOrdinalFeminineDative()

        val spelloutOrdinalPluralDative = SpelloutOrdinalPluralDative()

        val spelloutOrdinalMasculineAccusative = SpelloutOrdinalMasculineAccusative()

        val spelloutOrdinalNeuterAccusative = SpelloutOrdinalNeuterAccusative()

        val spelloutOrdinalFeminineAccusative = SpelloutOrdinalFeminineAccusative()

        val spelloutOrdinalPluralAccusative = SpelloutOrdinalPluralAccusative()

        val spelloutOrdinalMasculineLocative = SpelloutOrdinalMasculineLocative()

        val spelloutOrdinalNeuterLocative = SpelloutOrdinalNeuterLocative()

        val spelloutOrdinalFeminineLocative = SpelloutOrdinalFeminineLocative()

        val spelloutOrdinalPluralLocative = SpelloutOrdinalPluralLocative()

        val spelloutOrdinalMasculineAblative = SpelloutOrdinalMasculineAblative()

        val spelloutOrdinalNeuterAblative = SpelloutOrdinalNeuterAblative()

        val spelloutOrdinalFeminineAblative = SpelloutOrdinalFeminineAblative()

        val spelloutOrdinalPluralAblative = SpelloutOrdinalPluralAblative()

        private inner class SpelloutNumberingYear : NumberFormatter {
            override fun format(value: Double): String {
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                return spelloutOrdinalMasculineGenitive.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalMasculineGenitive.format(value)
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
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "ноль"
                    1L -> "один"
                    2L -> "два"
                    3L -> "три"
                    4L -> "четыре"
                    5L -> "пять"
                    6L -> "шесть"
                    7L -> "семь"
                    8L -> "восемь"
                    9L -> "девять"
                    10L -> "десять"
                    11L -> "одиннадцать"
                    12L -> "двенадцать"
                    13L -> "тринадцать"
                    14L -> "четырнадцать"
                    15L -> "пятнадцать"
                    16L -> "шестнадцать"
                    17L -> "семнадцать"
                    18L -> "восемнадцать"
                    19L -> "девятнадцать"
                    in 20L..29L -> {
                        var result = "двадцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорок"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восемьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяносто"
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
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
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
                        result += Plurals(one = "тысяча", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
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
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
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
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
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
                        result += Plurals(one = "квадриллион", few = "квадриллиона", other = "квадриллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                    500,
                    1000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "ноль"
                    1L -> "один"
                    2L -> "два"
                    3L -> "три"
                    4L -> "четыре"
                    5L -> "пять"
                    6L -> "шесть"
                    7L -> "семь"
                    8L -> "восемь"
                    9L -> "девять"
                    10L -> "десять"
                    11L -> "одиннадцать"
                    12L -> "двенадцать"
                    13L -> "тринадцать"
                    14L -> "четырнадцать"
                    15L -> "пятнадцать"
                    16L -> "шестнадцать"
                    17L -> "семнадцать"
                    18L -> "восемнадцать"
                    19L -> "девятнадцать"
                    20L -> {
                        var result = "двадцать"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцать"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорок"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятьдесят"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестьдесят"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семьдесят"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восемьдесят"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяносто"
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
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminine.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысяча", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
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
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
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
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
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
                        result += Plurals(
                            one = "квадриллион",
                            few = "квадриллиона",
                            other = "квадриллионов"
                        ).get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    spelloutCardinalFeminine.format(kotlin.math.truncate(value).toLong()) + " " + Plurals(
                        one = "целый",
                        other = "целых"
                    ).get(language.pluralFormChooser.pluralForm((kotlin.math.truncate(value).toLong()).toString())) + " "
                })
                result += fractionsFeminine.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalNeuter : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "ноль"
                    1L -> "одно"
                    in 2L..19L -> spelloutCardinalMasculine.format(value)
                    in 20L..29L -> {
                        var result = "двадцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорок"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восемьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяносто"
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
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
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
                        result += Plurals(one = "тысяча", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
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
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
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
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
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
                        result += Plurals(one = "квадриллион", few = "квадриллиона", other = "квадриллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                    500,
                    1000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "ноль"
                    1L -> "одно"
                    2L -> spelloutCardinalMasculine.format(value)
                    20L -> {
                        var result = "двадцать"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцать"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорок"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятьдесят"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестьдесят"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семьдесят"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восемьдесят"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяносто"
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
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminine.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысяча", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
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
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
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
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
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
                        result += Plurals(
                            one = "квадриллион",
                            few = "квадриллиона",
                            other = "квадриллионов"
                        ).get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    spelloutCardinalFeminine.format(kotlin.math.truncate(value).toLong()) + " " + Plurals(
                        one = "целая",
                        other = "целых"
                    ).get(language.pluralFormChooser.pluralForm((kotlin.math.truncate(value).toLong()).toString())) + " "
                })
                result += fractionsFeminine.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalFeminine : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "ноль"
                    1L -> "одна"
                    2L -> "две"
                    in 3L..19L -> spelloutCardinalMasculine.format(value)
                    in 20L..29L -> {
                        var result = "двадцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорок"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восемьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяносто"
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
                        var result = format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = format((value) / 100)
                        result += "сот"
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
                        result += Plurals(one = "тысяча", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
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
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
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
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
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
                        result += Plurals(one = "квадриллион", few = "квадриллиона", other = "квадриллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                    500,
                    1000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "ноль"
                    1L -> "одна"
                    2L -> "две"
                    3L -> spelloutCardinalMasculine.format(value)
                    20L -> {
                        var result = "двадцать"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцать"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорок"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятьдесят"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестьдесят"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семьдесят"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восемьдесят"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяносто"
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
                        var result = format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысяча", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
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
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
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
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
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
                        result += Plurals(
                            one = "квадриллион",
                            few = "квадриллиона",
                            other = "квадриллионов"
                        ).get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    format(kotlin.math.truncate(value).toLong()) + " " + Plurals(one = "целая", other = "целых").get(
                        language.pluralFormChooser.pluralForm(
                            (kotlin.math.truncate(value).toLong()).toString()
                        )
                    ) + " "
                })
                result += fractionsFeminine.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalPlural : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "ноль"
                    1L -> "одни"
                    2L -> "две"
                    in 3L..19L -> spelloutCardinalMasculine.format(value)
                    in 20L..29L -> {
                        var result = "двадцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорок"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восемьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяносто"
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
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
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
                        result += Plurals(one = "тысяча", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
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
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
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
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
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
                        result += Plurals(one = "квадриллион", few = "квадриллиона", other = "квадриллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalPlural only supports numbers >= 0")
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
                    500,
                    1000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "ноль"
                    1L -> "одни"
                    2L -> "две"
                    3L -> spelloutCardinalMasculine.format(value)
                    20L -> {
                        var result = "двадцать"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцать"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорок"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятьдесят"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестьдесят"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семьдесят"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восемьдесят"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяносто"
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
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminine.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысяча", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
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
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
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
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
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
                        result += Plurals(
                            one = "квадриллион",
                            few = "квадриллиона",
                            other = "квадриллионов"
                        ).get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                return format(kotlin.math.truncate(value).toLong()) + " запятая " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class FractionsFeminine : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 10L..99L -> spelloutCardinalFeminine.format((value).toLong() / 10) + " " + Plurals(
                        one = "десятая",
                        other = "десятых"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10).toString()))

                    in 100L..999L -> spelloutCardinalFeminine.format((value).toLong() / 100) + " " + Plurals(
                        one = "сотая",
                        other = "сотых"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100).toString()))

                    in 1000L..9999L -> spelloutCardinalFeminine.format((value).toLong() / 1000) + " " + Plurals(
                        one = "тысячная",
                        other = "тысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000).toString()))

                    in 10000L..99999L -> spelloutCardinalFeminine.format((value).toLong() / 10000) + " " + Plurals(
                        one = "десятитысячная",
                        other = "десятитысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000).toString()))

                    in 100000L..999999L -> spelloutCardinalFeminine.format((value).toLong() / 100000) + " " + Plurals(
                        one = "стотысячная",
                        other = "стотысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100000).toString()))

                    in 1000000L..9999999L -> spelloutCardinalFeminine.format((value).toLong() / 1000000) + " " + Plurals(
                        one = "миллионная",
                        other = "миллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000000).toString()))

                    in 10000000L..99999999L -> spelloutCardinalFeminine.format((value).toLong() / 10000000) + " " + Plurals(
                        one = "десятимиллионная",
                        other = "десятимиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000000).toString()))

                    in 100000000L..999999999L -> spelloutCardinalFeminine.format((value).toLong() / 100000000) + " " + Plurals(
                        one = "стомиллионная",
                        other = "стомиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100000000).toString()))

                    in 1000000000L..9999999999L -> spelloutCardinalFeminine.format((value).toLong() / 1000000000) + " " + Plurals(
                        one = "миллиардная",
                        other = "миллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000000000).toString()))

                    in 10000000000L..99999999999L -> spelloutCardinalFeminine.format((value).toLong() / 10000000000) + " " + Plurals(one = "десятимиллиардная", other = "десятимиллиардных").get(
                        language.pluralFormChooser.pluralForm(((value).toLong() / 10000000000).toString())
                    )

                    in 100000000000L..999999999999L -> spelloutCardinalFeminine.format((value).toLong() / 100000000000) + " " + Plurals(
                        one = "стомиллиардная",
                        other = "стомиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100000000000).toString()))

                    else -> {
                        if (value >= 1000000000000.0) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format((value).toLong() / 1000000000000) else {
                            throw UnsupportedOperationException("fractionsFeminine only supports numbers >= 10")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 10L..99L -> spelloutCardinalFeminine.format((value) / 10) + " " + Plurals(
                        one = "десятая",
                        other = "десятых"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10).toString()))

                    in 100L..999L -> spelloutCardinalFeminine.format((value) / 100) + " " + Plurals(
                        one = "сотая",
                        other = "сотых"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100).toString()))

                    in 1000L..9999L -> spelloutCardinalFeminine.format((value) / 1000) + " " + Plurals(
                        one = "тысячная",
                        other = "тысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))

                    in 10000L..99999L -> spelloutCardinalFeminine.format((value) / 10000) + " " + Plurals(
                        one = "десятитысячная",
                        other = "десятитысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000).toString()))

                    in 100000L..999999L -> spelloutCardinalFeminine.format((value) / 100000) + " " + Plurals(
                        one = "стотысячная",
                        other = "стотысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000).toString()))

                    in 1000000L..9999999L -> spelloutCardinalFeminine.format((value) / 1000000) + " " + Plurals(
                        one = "миллионная",
                        other = "миллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))

                    in 10000000L..99999999L -> spelloutCardinalFeminine.format((value) / 10000000) + " " + Plurals(
                        one = "десятимиллионная",
                        other = "десятимиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000000).toString()))

                    in 100000000L..999999999L -> spelloutCardinalFeminine.format((value) / 100000000) + " " + Plurals(
                        one = "стомиллионная",
                        other = "стомиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000000).toString()))

                    in 1000000000L..9999999999L -> spelloutCardinalFeminine.format((value) / 1000000000) + " " + Plurals(
                        one = "миллиардная",
                        other = "миллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))

                    in 10000000000L..99999999999L -> spelloutCardinalFeminine.format((value) / 10000000000) + " " + Plurals(
                        one = "десятимиллиардная",
                        other = "десятимиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000000000).toString()))

                    in 100000000000L..999999999999L -> spelloutCardinalFeminine.format((value) / 100000000000) + " " + Plurals(
                        one = "стомиллиардная",
                        other = "стомиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000000000).toString()))

                    else -> {
                        if (value >= 1000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format((value) / 1000000000000) else {
                            throw UnsupportedOperationException("fractionsFeminine only supports numbers >= 10")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000, 100000000000, 1000000000000).bestDenominatorOrNull(value)!!) {
                    10L -> spelloutCardinalFeminine.format((value * 10).toLong()) + " " + Plurals(
                        one = "десятая",
                        other = "десятых"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10).toLong()).toString()))

                    100L -> spelloutCardinalFeminine.format((value * 100).toLong()) + " " + Plurals(
                        one = "сотая",
                        other = "сотых"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100).toLong()).toString()))

                    1000L -> spelloutCardinalFeminine.format((value * 1000).toLong()) + " " + Plurals(
                        one = "тысячная",
                        other = "тысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))

                    10000L -> spelloutCardinalFeminine.format((value * 10000).toLong()) + " " + Plurals(
                        one = "десятитысячная",
                        other = "десятитысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000).toLong()).toString()))

                    100000L -> spelloutCardinalFeminine.format((value * 100000).toLong()) + " " + Plurals(
                        one = "стотысячная",
                        other = "стотысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000).toLong()).toString()))

                    1000000L -> spelloutCardinalFeminine.format((value * 1000000).toLong()) + " " + Plurals(
                        one = "миллионная",
                        other = "миллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))

                    10000000L -> spelloutCardinalFeminine.format((value * 10000000).toLong()) + " " + Plurals(
                        one = "десятимиллионная",
                        other = "десятимиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000000).toLong()).toString()))

                    100000000L -> spelloutCardinalFeminine.format((value * 100000000).toLong()) + " " + Plurals(
                        one = "стомиллионная",
                        other = "стомиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000000).toLong()).toString()))

                    1000000000L -> spelloutCardinalFeminine.format((value * 1000000000).toLong()) + " " + Plurals(one = "миллиардная", other = "миллиардных").get(
                        language.pluralFormChooser.pluralForm(
                            ((value * 1000000000).toLong()).toString()
                        )
                    )

                    10000000000L -> spelloutCardinalFeminine.format((value * 10000000000).toLong()) + " " + Plurals(
                        one = "десятимиллиардная",
                        other = "десятимиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000000000).toLong()).toString()))

                    100000000000L -> spelloutCardinalFeminine.format((value * 100000000000).toLong()) + " " + Plurals(
                        one = "стомиллиардная",
                        other = "стомиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000000000).toLong()).toString()))

                    1000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format((value * 1000000000000).toLong())

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalMasculineGenitive : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "ноля"
                    1L -> "одного"
                    2L -> "двух"
                    3L -> "трех"
                    4L -> "четырех"
                    5L -> "пяти"
                    6L -> "шести"
                    7L -> "семи"
                    8L -> "восьми"
                    9L -> "девяти"
                    10L -> "десяти"
                    11L -> "одиннадцати"
                    12L -> "двенадцати"
                    13L -> "тринадцати"
                    14L -> "четырнадцати"
                    15L -> "пятнадцати"
                    16L -> "шестнадцати"
                    17L -> "семнадцати"
                    18L -> "восемнадцати"
                    19L -> "девятнадцати"
                    in 20L..29L -> {
                        var result = "двадцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорока"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восьмидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = spelloutCardinalFeminineGenitive.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalFeminineGenitive.format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
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
                        result += Plurals(one = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
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
                        result += Plurals(one = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
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
                        result += Plurals(one = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
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
                        result += Plurals(one = "квадриллиона", other = "квадриллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalMasculineGenitive only supports numbers >= 0")
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
                    1000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "ноля"
                    1L -> "одного"
                    2L -> "двух"
                    3L -> "трех"
                    4L -> "четырех"
                    5L -> "пяти"
                    6L -> "шести"
                    7L -> "семи"
                    8L -> "восьми"
                    9L -> "девяти"
                    10L -> "десяти"
                    11L -> "одиннадцати"
                    12L -> "двенадцати"
                    13L -> "тринадцати"
                    14L -> "четырнадцати"
                    15L -> "пятнадцати"
                    16L -> "шестнадцати"
                    17L -> "семнадцати"
                    18L -> "восемнадцати"
                    19L -> "девятнадцати"
                    20L -> {
                        var result = "двадцати"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцати"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорока"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятидесяти"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестидесяти"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семидесяти"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восьмидесяти"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "ста"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = spelloutCardinalFeminineGenitive.format((value * 200).toLong())
                        result += "сот"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminineGenitive.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
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
                        result += Plurals(one = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
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
                        result += Plurals(one = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
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
                        result += Plurals(one = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
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
                        result += Plurals(one = "квадриллиона", other = "квадриллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    spelloutCardinalFeminineGenitive.format(kotlin.math.truncate(value).toLong()) + " " + Plurals(
                        one = "целой",
                        other = "целых"
                    ).get(language.pluralFormChooser.pluralForm((kotlin.math.truncate(value).toLong()).toString())) + " "
                })
                result += fractionsFeminineGenitive.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalNeuterGenitive : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutCardinalMasculineGenitive.format(value) else {
                    throw UnsupportedOperationException("spelloutCardinalNeuterGenitive only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutCardinalMasculineGenitive.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutCardinalMasculineGenitive.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalFeminineGenitive : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "ноля"
                    1L -> "одной"
                    in 2L..19L -> spelloutCardinalMasculineGenitive.format(value)
                    in 20L..29L -> {
                        var result = "двадцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорока"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восьмидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = format((value) / 100)
                        result += "сот"
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
                        result += Plurals(one = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value) / 1000000000000)
                        result += " "
                        result += Plurals(one = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(one = "квадриллиона", other = "квадриллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalFeminineGenitive only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 1000, 1000000, 1000000000, 1000000000000, 1000000000000000, 1000000000000000000).bestDenominatorOrNull(
                    value
                )!!) {
                    0L -> "ноля"
                    1L -> "одной"
                    2L -> spelloutCardinalMasculineGenitive.format(value)
                    20L -> {
                        var result = "двадцати"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцати"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорока"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятидесяти"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестидесяти"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семидесяти"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восьмидесяти"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "ста"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = format((value * 200).toLong())
                        result += "сот"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value * 1000000000000).toLong())
                        result += " "
                        result += Plurals(one = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value * 1000000000000000).toLong())
                        result += " "
                        result += Plurals(one = "квадриллиона", other = "квадриллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    format(kotlin.math.truncate(value).toLong()) + " " + Plurals(one = "целой", other = "целых").get(
                        language.pluralFormChooser.pluralForm(
                            (kotlin.math.truncate(value).toLong()).toString()
                        )
                    ) + " "
                })
                result += fractionsFeminineGenitive.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalPluralGenitive : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "ноля"
                    1L -> "одних"
                    in 2L..19L -> spelloutCardinalMasculineGenitive.format(value)
                    in 20L..29L -> {
                        var result = "двадцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорока"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восьмидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = spelloutCardinalFeminineGenitive.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalFeminineGenitive.format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value) / 1000000000000)
                        result += " "
                        result += Plurals(one = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(one = "квадриллиона", other = "квадриллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalPluralGenitive only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 1000, 1000000, 1000000000, 1000000000000, 1000000000000000, 1000000000000000000).bestDenominatorOrNull(
                    value
                )!!) {
                    0L -> "ноля"
                    1L -> "одних"
                    2L -> spelloutCardinalMasculineGenitive.format(value)
                    20L -> {
                        var result = "двадцати"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцати"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорока"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятидесяти"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестидесяти"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семидесяти"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восьмидесяти"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "ста"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = spelloutCardinalFeminineGenitive.format((value * 200).toLong())
                        result += "сот"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminineGenitive.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value * 1000000000000).toLong())
                        result += " "
                        result += Plurals(one = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculineGenitive.format((value * 1000000000000000).toLong())
                        result += " "
                        result += Plurals(one = "квадриллиона", other = "квадриллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                return format(kotlin.math.truncate(value).toLong()) + " запятая " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class FractionsFeminineGenitive : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 10L..99L -> spelloutCardinalFeminineGenitive.format((value).toLong() / 10) + " " + Plurals(
                        one = "десятой",
                        other = "десятых"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10).toString()))

                    in 100L..999L -> spelloutCardinalFeminineGenitive.format((value).toLong() / 100) + " " + Plurals(
                        one = "сотой",
                        other = "сотых"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100).toString()))

                    in 1000L..9999L -> spelloutCardinalFeminineGenitive.format((value).toLong() / 1000) + " " + Plurals(one = "тысячной", other = "тысячных").get(
                        language.pluralFormChooser.pluralForm(
                            ((value).toLong() / 1000).toString()
                        )
                    )

                    in 10000L..99999L -> spelloutCardinalFeminineGenitive.format((value).toLong() / 10000) + " " + Plurals(
                        one = "десятитысячной",
                        other = "десятитысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000).toString()))

                    in 100000L..999999L -> spelloutCardinalFeminineGenitive.format((value).toLong() / 100000) + " " + Plurals(
                        one = "стотысячной",
                        other = "стотысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100000).toString()))

                    in 1000000L..9999999L -> spelloutCardinalFeminineGenitive.format((value).toLong() / 1000000) + " " + Plurals(
                        one = "миллионной",
                        other = "миллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000000).toString()))

                    in 10000000L..99999999L -> spelloutCardinalFeminineGenitive.format((value).toLong() / 10000000) + " " + Plurals(
                        one = "десятимиллионной",
                        other = "десятимиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000000).toString()))

                    in 100000000L..999999999L -> spelloutCardinalFeminineGenitive.format((value).toLong() / 100000000) + " " + Plurals(
                        one = "стомиллионной",
                        other = "стомиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100000000).toString()))

                    in 1000000000L..9999999999L -> spelloutCardinalFeminineGenitive.format((value).toLong() / 1000000000) + " " + Plurals(
                        one = "миллиардной",
                        other = "миллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000000000).toString()))

                    in 10000000000L..99999999999L -> spelloutCardinalFeminineGenitive.format((value).toLong() / 10000000000) + " " + Plurals(
                        one = "десятимиллиардной",
                        other = "десятимиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000000000).toString()))

                    in 100000000000L..999999999999L -> spelloutCardinalFeminineGenitive.format((value).toLong() / 100000000000) + " " + Plurals(one = "стомиллиардной", other = "стомиллиардных").get(
                        language.pluralFormChooser.pluralForm(((value).toLong() / 100000000000).toString())
                    )

                    else -> {
                        if (value >= 1000000000000.0) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format((value).toLong() / 1000000000000) else {
                            throw UnsupportedOperationException("fractionsFeminineGenitive only supports numbers >= 10")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 10L..99L -> spelloutCardinalFeminineGenitive.format((value) / 10) + " " + Plurals(
                        one = "десятой",
                        other = "десятых"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10).toString()))

                    in 100L..999L -> spelloutCardinalFeminineGenitive.format((value) / 100) + " " + Plurals(
                        one = "сотой",
                        other = "сотых"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100).toString()))

                    in 1000L..9999L -> spelloutCardinalFeminineGenitive.format((value) / 1000) + " " + Plurals(
                        one = "тысячной",
                        other = "тысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))

                    in 10000L..99999L -> spelloutCardinalFeminineGenitive.format((value) / 10000) + " " + Plurals(
                        one = "десятитысячной",
                        other = "десятитысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000).toString()))

                    in 100000L..999999L -> spelloutCardinalFeminineGenitive.format((value) / 100000) + " " + Plurals(
                        one = "стотысячной",
                        other = "стотысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000).toString()))

                    in 1000000L..9999999L -> spelloutCardinalFeminineGenitive.format((value) / 1000000) + " " + Plurals(
                        one = "миллионной",
                        other = "миллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))

                    in 10000000L..99999999L -> spelloutCardinalFeminineGenitive.format((value) / 10000000) + " " + Plurals(
                        one = "десятимиллионной",
                        other = "десятимиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000000).toString()))

                    in 100000000L..999999999L -> spelloutCardinalFeminineGenitive.format((value) / 100000000) + " " + Plurals(
                        one = "стомиллионной",
                        other = "стомиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000000).toString()))

                    in 1000000000L..9999999999L -> spelloutCardinalFeminineGenitive.format((value) / 1000000000) + " " + Plurals(
                        one = "миллиардной",
                        other = "миллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))

                    in 10000000000L..99999999999L -> spelloutCardinalFeminineGenitive.format((value) / 10000000000) + " " + Plurals(
                        one = "десятимиллиардной",
                        other = "десятимиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000000000).toString()))

                    in 100000000000L..999999999999L -> spelloutCardinalFeminineGenitive.format((value) / 100000000000) + " " + Plurals(
                        one = "стомиллиардной",
                        other = "стомиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000000000).toString()))

                    else -> {
                        if (value >= 1000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format((value) / 1000000000000) else {
                            throw UnsupportedOperationException("fractionsFeminineGenitive only supports numbers >= 10")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000, 100000000000, 1000000000000).bestDenominatorOrNull(value)!!) {
                    10L -> spelloutCardinalFeminineGenitive.format((value * 10).toLong()) + " " + Plurals(
                        one = "десятой",
                        other = "десятых"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10).toLong()).toString()))

                    100L -> spelloutCardinalFeminineGenitive.format((value * 100).toLong()) + " " + Plurals(
                        one = "сотой",
                        other = "сотых"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100).toLong()).toString()))

                    1000L -> spelloutCardinalFeminineGenitive.format((value * 1000).toLong()) + " " + Plurals(
                        one = "тысячной",
                        other = "тысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))

                    10000L -> spelloutCardinalFeminineGenitive.format((value * 10000).toLong()) + " " + Plurals(
                        one = "десятитысячной",
                        other = "десятитысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000).toLong()).toString()))

                    100000L -> spelloutCardinalFeminineGenitive.format((value * 100000).toLong()) + " " + Plurals(one = "стотысячной", other = "стотысячных").get(
                        language.pluralFormChooser.pluralForm(
                            ((value * 100000).toLong()).toString()
                        )
                    )

                    1000000L -> spelloutCardinalFeminineGenitive.format((value * 1000000).toLong()) + " " + Plurals(one = "миллионной", other = "миллионных").get(
                        language.pluralFormChooser.pluralForm(
                            ((value * 1000000).toLong()).toString()
                        )
                    )

                    10000000L -> spelloutCardinalFeminineGenitive.format((value * 10000000).toLong()) + " " + Plurals(
                        one = "десятимиллионной",
                        other = "десятимиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000000).toLong()).toString()))

                    100000000L -> spelloutCardinalFeminineGenitive.format((value * 100000000).toLong()) + " " + Plurals(
                        one = "стомиллионной",
                        other = "стомиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000000).toLong()).toString()))

                    1000000000L -> spelloutCardinalFeminineGenitive.format((value * 1000000000).toLong()) + " " + Plurals(
                        one = "миллиардной",
                        other = "миллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))

                    10000000000L -> spelloutCardinalFeminineGenitive.format((value * 10000000000).toLong()) + " " + Plurals(
                        one = "десятимиллиардной",
                        other = "десятимиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000000000).toLong()).toString()))

                    100000000000L -> spelloutCardinalFeminineGenitive.format((value * 100000000000).toLong()) + " " + Plurals(
                        one = "стомиллиардной",
                        other = "стомиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000000000).toLong()).toString()))

                    1000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format((value * 1000000000000).toLong())

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalMasculineDative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нолю"
                    1L -> "одному"
                    2L -> "двум"
                    3L -> "трем"
                    4L -> "четырем"
                    5L -> "пяти"
                    6L -> "шести"
                    7L -> "семи"
                    8L -> "восьми"
                    9L -> "девяти"
                    10L -> "десяти"
                    11L -> "одиннадцати"
                    12L -> "двенадцати"
                    13L -> "тринадцати"
                    14L -> "четырнадцати"
                    15L -> "пятнадцати"
                    16L -> "шестнадцати"
                    17L -> "семнадцати"
                    18L -> "восемнадцати"
                    19L -> "девятнадцати"
                    in 20L..29L -> {
                        var result = "двадцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорока"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восьмидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = spelloutCardinalFeminineDative.format((value) / 100)
                        result += "стам"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalFeminineDative.format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысяче", other = "тысячам").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
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
                        result += Plurals(one = "миллиону", other = "миллионам").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
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
                        result += Plurals(one = "миллиарду", other = "миллиардам").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
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
                        result += Plurals(one = "триллиону", other = "триллионам").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
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
                        result += Plurals(one = "квадриллиону", other = "квадриллионам").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalMasculineDative only supports numbers >= 0")
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
                    1000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нолю"
                    1L -> "одному"
                    2L -> "двум"
                    3L -> "трем"
                    4L -> "четырем"
                    5L -> "пяти"
                    6L -> "шести"
                    7L -> "семи"
                    8L -> "восьми"
                    9L -> "девяти"
                    10L -> "десяти"
                    11L -> "одиннадцати"
                    12L -> "двенадцати"
                    13L -> "тринадцати"
                    14L -> "четырнадцати"
                    15L -> "пятнадцати"
                    16L -> "шестнадцати"
                    17L -> "семнадцати"
                    18L -> "восемнадцати"
                    19L -> "девятнадцати"
                    20L -> {
                        var result = "двадцати"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцати"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорока"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятидесяти"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестидесяти"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семидесяти"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восьмидесяти"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "ста"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = spelloutCardinalFeminineDative.format((value * 200).toLong())
                        result += "стам"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminineDative.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысяче", other = "тысячам").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
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
                        result += Plurals(one = "миллиону", other = "миллионам").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
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
                        result += Plurals(one = "миллиарду", other = "миллиардам").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
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
                        result += Plurals(one = "триллиону", other = "триллионам").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
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
                        result += Plurals(one = "квадриллиону", other = "квадриллионам").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    spelloutCardinalFeminineDative.format(kotlin.math.truncate(value).toLong()) + " " + Plurals(
                        one = "целой",
                        other = "целым"
                    ).get(language.pluralFormChooser.pluralForm((kotlin.math.truncate(value).toLong()).toString())) + " "
                })
                result += fractionsFeminineDative.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalNeuterDative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutCardinalMasculineDative.format(value) else {
                    throw UnsupportedOperationException("spelloutCardinalNeuterDative only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutCardinalMasculineDative.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutCardinalMasculineDative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalFeminineDative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нолю"
                    1L -> "одной"
                    in 2L..19L -> spelloutCardinalMasculineDative.format(value)
                    in 20L..29L -> {
                        var result = "двадцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорока"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восьмидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = format((value) / 100)
                        result += "стам"
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
                        result += Plurals(one = "тысяче", other = "тысячам").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculineDative.format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "миллиону", other = "миллионам").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculineDative.format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "миллиарду", other = "миллиардам").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculineDative.format((value) / 1000000000000)
                        result += " "
                        result += Plurals(one = "триллиону", other = "триллионам").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculineDative.format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(one = "квадриллиону", other = "квадриллионам").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalFeminineDative only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 1000, 1000000, 1000000000, 1000000000000, 1000000000000000, 1000000000000000000).bestDenominatorOrNull(
                    value
                )!!) {
                    0L -> "нолю"
                    1L -> "одной"
                    2L -> spelloutCardinalMasculineDative.format(value)
                    20L -> {
                        var result = "двадцати"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцати"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорока"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятидесяти"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестидесяти"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семидесяти"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восьмидесяти"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "ста"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = format((value * 200).toLong())
                        result += "стам"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысяче", other = "тысячам").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculineDative.format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиону", other = "миллионам").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculineDative.format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиарду", other = "миллиардам").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculineDative.format((value * 1000000000000).toLong())
                        result += " "
                        result += Plurals(one = "триллиону", other = "триллионам").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculineDative.format((value * 1000000000000000).toLong())
                        result += " "
                        result += Plurals(one = "квадриллиону", other = "квадриллионам").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    format(kotlin.math.truncate(value).toLong()) + " " + Plurals(one = "целой", other = "целым").get(
                        language.pluralFormChooser.pluralForm(
                            (kotlin.math.truncate(value).toLong()).toString()
                        )
                    ) + " "
                })
                result += fractionsFeminineDative.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalPluralDative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нолю"
                    1L -> "одним"
                    in 2L..19L -> spelloutCardinalMasculineDative.format(value)
                    in 20L..29L -> {
                        var result = "двадцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорока"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восьмидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = spelloutCardinalFeminineDative.format((value) / 100)
                        result += "стам"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalFeminineDative.format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысяче", other = "тысячам").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculineDative.format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "миллиону", other = "миллионам").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculineDative.format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "миллиарду", other = "миллиардам").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculineDative.format((value) / 1000000000000)
                        result += " "
                        result += Plurals(one = "триллиону", other = "триллионам").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculineDative.format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(one = "квадриллиону", other = "квадриллионам").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalPluralDative only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 1000, 1000000, 1000000000, 1000000000000, 1000000000000000, 1000000000000000000).bestDenominatorOrNull(
                    value
                )!!) {
                    0L -> "нолю"
                    1L -> "одним"
                    2L -> spelloutCardinalMasculineDative.format(value)
                    20L -> {
                        var result = "двадцати"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцати"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорока"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятидесяти"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестидесяти"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семидесяти"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восьмидесяти"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "ста"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = spelloutCardinalFeminineDative.format((value * 200).toLong())
                        result += "стам"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminineDative.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысяче", other = "тысячам").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculineDative.format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиону", other = "миллионам").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculineDative.format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиарду", other = "миллиардам").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculineDative.format((value * 1000000000000).toLong())
                        result += " "
                        result += Plurals(one = "триллиону", other = "триллионам").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculineDative.format((value * 1000000000000000).toLong())
                        result += " "
                        result += Plurals(one = "квадриллиону", other = "квадриллионам").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                return format(kotlin.math.truncate(value).toLong()) + " запятая " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class FractionsFeminineDative : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 10L..99L -> spelloutCardinalFeminineDative.format((value).toLong() / 10) + " " + Plurals(
                        one = "десятой",
                        other = "десятым"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10).toString()))

                    in 100L..999L -> spelloutCardinalFeminineDative.format((value).toLong() / 100) + " " + Plurals(
                        one = "сотой",
                        other = "сотым"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100).toString()))

                    in 1000L..9999L -> spelloutCardinalFeminineDative.format((value).toLong() / 1000) + " " + Plurals(
                        one = "тысячной",
                        other = "тысячным"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000).toString()))

                    in 10000L..99999L -> spelloutCardinalFeminineDative.format((value).toLong() / 10000) + " " + Plurals(
                        one = "десятитысячной",
                        other = "десятитысячным"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000).toString()))

                    in 100000L..999999L -> spelloutCardinalFeminineDative.format((value).toLong() / 100000) + " " + Plurals(
                        one = "стотысячной",
                        other = "стотысячным"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100000).toString()))

                    in 1000000L..9999999L -> spelloutCardinalFeminineDative.format((value).toLong() / 1000000) + " " + Plurals(
                        one = "миллионной",
                        other = "миллионным"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000000).toString()))

                    in 10000000L..99999999L -> spelloutCardinalFeminineDative.format((value).toLong() / 10000000) + " " + Plurals(
                        one = "десятимиллионной",
                        other = "десятимиллионным"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000000).toString()))

                    in 100000000L..999999999L -> spelloutCardinalFeminineDative.format((value).toLong() / 100000000) + " " + Plurals(
                        one = "стомиллионной",
                        other = "стомиллионным"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100000000).toString()))

                    in 1000000000L..9999999999L -> spelloutCardinalFeminineDative.format((value).toLong() / 1000000000) + " " + Plurals(
                        one = "миллиардной",
                        other = "миллиардным"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000000000).toString()))

                    in 10000000000L..99999999999L -> spelloutCardinalFeminineDative.format((value).toLong() / 10000000000) + " " + Plurals(one = "десятимиллиардной", other = "десятимиллиардным").get(
                        language.pluralFormChooser.pluralForm(((value).toLong() / 10000000000).toString())
                    )

                    in 100000000000L..999999999999L -> spelloutCardinalFeminineDative.format((value).toLong() / 100000000000) + " " + Plurals(one = "стомиллиардной", other = "стомиллиардным").get(
                        language.pluralFormChooser.pluralForm(((value).toLong() / 100000000000).toString())
                    )

                    else -> {
                        if (value >= 1000000000000.0) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format((value).toLong() / 1000000000000) else {
                            throw UnsupportedOperationException("fractionsFeminineDative only supports numbers >= 10")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 10L..99L -> spelloutCardinalFeminineDative.format((value) / 10) + " " + Plurals(
                        one = "десятой",
                        other = "десятым"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10).toString()))

                    in 100L..999L -> spelloutCardinalFeminineDative.format((value) / 100) + " " + Plurals(
                        one = "сотой",
                        other = "сотым"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100).toString()))

                    in 1000L..9999L -> spelloutCardinalFeminineDative.format((value) / 1000) + " " + Plurals(
                        one = "тысячной",
                        other = "тысячным"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))

                    in 10000L..99999L -> spelloutCardinalFeminineDative.format((value) / 10000) + " " + Plurals(
                        one = "десятитысячной",
                        other = "десятитысячным"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000).toString()))

                    in 100000L..999999L -> spelloutCardinalFeminineDative.format((value) / 100000) + " " + Plurals(
                        one = "стотысячной",
                        other = "стотысячным"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000).toString()))

                    in 1000000L..9999999L -> spelloutCardinalFeminineDative.format((value) / 1000000) + " " + Plurals(
                        one = "миллионной",
                        other = "миллионным"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))

                    in 10000000L..99999999L -> spelloutCardinalFeminineDative.format((value) / 10000000) + " " + Plurals(
                        one = "десятимиллионной",
                        other = "десятимиллионным"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000000).toString()))

                    in 100000000L..999999999L -> spelloutCardinalFeminineDative.format((value) / 100000000) + " " + Plurals(
                        one = "стомиллионной",
                        other = "стомиллионным"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000000).toString()))

                    in 1000000000L..9999999999L -> spelloutCardinalFeminineDative.format((value) / 1000000000) + " " + Plurals(
                        one = "миллиардной",
                        other = "миллиардным"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))

                    in 10000000000L..99999999999L -> spelloutCardinalFeminineDative.format((value) / 10000000000) + " " + Plurals(
                        one = "десятимиллиардной",
                        other = "десятимиллиардным"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000000000).toString()))

                    in 100000000000L..999999999999L -> spelloutCardinalFeminineDative.format((value) / 100000000000) + " " + Plurals(
                        one = "стомиллиардной",
                        other = "стомиллиардным"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000000000).toString()))

                    else -> {
                        if (value >= 1000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format((value) / 1000000000000) else {
                            throw UnsupportedOperationException("fractionsFeminineDative only supports numbers >= 10")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000, 100000000000, 1000000000000).bestDenominatorOrNull(value)!!) {
                    10L -> spelloutCardinalFeminineDative.format((value * 10).toLong()) + " " + Plurals(
                        one = "десятой",
                        other = "десятым"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10).toLong()).toString()))

                    100L -> spelloutCardinalFeminineDative.format((value * 100).toLong()) + " " + Plurals(
                        one = "сотой",
                        other = "сотым"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100).toLong()).toString()))

                    1000L -> spelloutCardinalFeminineDative.format((value * 1000).toLong()) + " " + Plurals(
                        one = "тысячной",
                        other = "тысячным"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))

                    10000L -> spelloutCardinalFeminineDative.format((value * 10000).toLong()) + " " + Plurals(
                        one = "десятитысячной",
                        other = "десятитысячным"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000).toLong()).toString()))

                    100000L -> spelloutCardinalFeminineDative.format((value * 100000).toLong()) + " " + Plurals(
                        one = "стотысячной",
                        other = "стотысячным"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000).toLong()).toString()))

                    1000000L -> spelloutCardinalFeminineDative.format((value * 1000000).toLong()) + " " + Plurals(
                        one = "миллионной",
                        other = "миллионным"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))

                    10000000L -> spelloutCardinalFeminineDative.format((value * 10000000).toLong()) + " " + Plurals(
                        one = "десятимиллионной",
                        other = "десятимиллионным"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000000).toLong()).toString()))

                    100000000L -> spelloutCardinalFeminineDative.format((value * 100000000).toLong()) + " " + Plurals(
                        one = "стомиллионной",
                        other = "стомиллионным"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000000).toLong()).toString()))

                    1000000000L -> spelloutCardinalFeminineDative.format((value * 1000000000).toLong()) + " " + Plurals(
                        one = "миллиардной",
                        other = "миллиардным"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))

                    10000000000L -> spelloutCardinalFeminineDative.format((value * 10000000000).toLong()) + " " + Plurals(
                        one = "десятимиллиардной",
                        other = "десятимиллиардным"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000000000).toLong()).toString()))

                    100000000000L -> spelloutCardinalFeminineDative.format((value * 100000000000).toLong()) + " " + Plurals(
                        one = "стомиллиардной",
                        other = "стомиллиардным"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000000000).toLong()).toString()))

                    1000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format((value * 1000000000000).toLong())

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalMasculineAccusative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "ноль"
                    1L -> "один"
                    2L -> "два"
                    3L -> "три"
                    4L -> "четыре"
                    5L -> "пять"
                    6L -> "шесть"
                    7L -> "семь"
                    8L -> "восемь"
                    9L -> "девять"
                    10L -> "десять"
                    11L -> "одиннадцать"
                    12L -> "двенадцать"
                    13L -> "тринадцать"
                    14L -> "четырнадцать"
                    15L -> "пятнадцать"
                    16L -> "шестнадцать"
                    17L -> "семнадцать"
                    18L -> "восемнадцать"
                    19L -> "девятнадцать"
                    in 20L..29L -> {
                        var result = "двадцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорок"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восемьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяносто"
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
                        var result = spelloutCardinalFeminineAccusative.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысячу", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
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
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
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
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
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
                        result += Plurals(one = "квадриллион", few = "квадриллиона", other = "квадриллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalMasculineAccusative only supports numbers >= 0")
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
                    500,
                    1000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "ноль"
                    1L -> "один"
                    2L -> "два"
                    3L -> "три"
                    4L -> "четыре"
                    5L -> "пять"
                    6L -> "шесть"
                    7L -> "семь"
                    8L -> "восемь"
                    9L -> "девять"
                    10L -> "десять"
                    11L -> "одиннадцать"
                    12L -> "двенадцать"
                    13L -> "тринадцать"
                    14L -> "четырнадцать"
                    15L -> "пятнадцать"
                    16L -> "шестнадцать"
                    17L -> "семнадцать"
                    18L -> "восемнадцать"
                    19L -> "девятнадцать"
                    20L -> {
                        var result = "двадцать"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцать"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорок"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятьдесят"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестьдесят"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семьдесят"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восемьдесят"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяносто"
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
                        var result = spelloutCardinalFeminineAccusative.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысячу", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
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
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
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
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
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
                        result += Plurals(
                            one = "квадриллион",
                            few = "квадриллиона",
                            other = "квадриллионов"
                        ).get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    spelloutCardinalFeminineAccusative.format(kotlin.math.truncate(value).toLong()) + " " + Plurals(
                        one = "целую",
                        other = "целых"
                    ).get(language.pluralFormChooser.pluralForm((kotlin.math.truncate(value).toLong()).toString())) + " "
                })
                result += fractionsFeminineAccusative.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalNeuterAccusative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "ноль"
                    1L -> "одно"
                    in 2L..19L -> spelloutCardinalMasculineAccusative.format(value)
                    in 20L..29L -> {
                        var result = "двадцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорок"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восемьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяносто"
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
                        var result = spelloutCardinalFeminineAccusative.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысячу", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculineAccusative.format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculineAccusative.format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
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
                        result += Plurals(one = "квадриллион", few = "квадриллиона", other = "квадриллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalNeuterAccusative only supports numbers >= 0")
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
                    70,
                    80,
                    90,
                    100,
                    200,
                    300,
                    500,
                    1000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "ноль"
                    1L -> "одно"
                    2L -> spelloutCardinalMasculineAccusative.format(value)
                    20L -> {
                        var result = "двадцать"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцать"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорок"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятьдесят"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестьдесят"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семьдесят"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восемьдесят"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяносто"
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
                        var result = spelloutCardinalFeminineAccusative.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысячу", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculineAccusative.format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculineAccusative.format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
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
                        result += Plurals(
                            one = "квадриллион",
                            few = "квадриллиона",
                            other = "квадриллионов"
                        ).get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    spelloutCardinalFeminineAccusative.format(kotlin.math.truncate(value).toLong()) + " " + Plurals(
                        one = "целую",
                        other = "целых"
                    ).get(language.pluralFormChooser.pluralForm((kotlin.math.truncate(value).toLong()).toString())) + " "
                })
                result += fractionsFeminineAccusative.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalFeminineAccusative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "ноль"
                    1L -> "одну"
                    2L -> "две"
                    in 3L..19L -> spelloutCardinalMasculineAccusative.format(value)
                    in 20L..29L -> {
                        var result = "двадцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорок"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восемьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяносто"
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
                        var result = format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = format((value) / 100)
                        result += "сот"
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
                        result += Plurals(one = "тысячу", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculineAccusative.format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculineAccusative.format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
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
                        result += Plurals(one = "квадриллион", few = "квадриллиона", other = "квадриллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalFeminineAccusative only supports numbers >= 0")
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
                    500,
                    1000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "ноль"
                    1L -> "одну"
                    2L -> "две"
                    3L -> spelloutCardinalMasculineAccusative.format(value)
                    20L -> {
                        var result = "двадцать"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцать"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорок"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятьдесят"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестьдесят"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семьдесят"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восемьдесят"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяносто"
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
                        var result = format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысячу", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculineAccusative.format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculineAccusative.format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
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
                        result += Plurals(
                            one = "квадриллион",
                            few = "квадриллиона",
                            other = "квадриллионов"
                        ).get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    format(kotlin.math.truncate(value).toLong()) + " " + Plurals(one = "целую", other = "целых").get(
                        language.pluralFormChooser.pluralForm(
                            (kotlin.math.truncate(value).toLong()).toString()
                        )
                    ) + " "
                })
                result += fractionsFeminineAccusative.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalPluralAccusative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "ноль"
                    1L -> "одни"
                    2L -> "две"
                    in 3L..19L -> spelloutCardinalMasculineAccusative.format(value)
                    in 20L..29L -> {
                        var result = "двадцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорок"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восемьдесят"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяносто"
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
                        var result = spelloutCardinalFeminineAccusative.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысячу", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculineAccusative.format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculineAccusative.format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
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
                        result += Plurals(one = "квадриллион", few = "квадриллиона", other = "квадриллионов").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalPluralAccusative only supports numbers >= 0")
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
                    500,
                    1000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "ноль"
                    1L -> "одни"
                    2L -> "две"
                    3L -> spelloutCardinalMasculineAccusative.format(value)
                    20L -> {
                        var result = "двадцать"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцать"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорок"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятьдесят"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестьдесят"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семьдесят"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восемьдесят"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяносто"
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
                        var result = spelloutCardinalFeminineAccusative.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminineAccusative.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысячу", few = "тысячи", other = "тысяч").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculineAccusative.format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "миллион", few = "миллиона", other = "миллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculineAccusative.format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиард", few = "миллиарда", other = "миллиардов").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
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
                        result += Plurals(one = "триллион", few = "триллиона", other = "триллионов").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
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
                        result += Plurals(
                            one = "квадриллион",
                            few = "квадриллиона",
                            other = "квадриллионов"
                        ).get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                return format(kotlin.math.truncate(value).toLong()) + " запятая " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class FractionsFeminineAccusative : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 10L..99L -> spelloutCardinalFeminineAccusative.format((value).toLong() / 10) + " " + Plurals(
                        one = "десятую",
                        other = "десятых"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10).toString()))

                    in 100L..999L -> spelloutCardinalFeminineAccusative.format((value).toLong() / 100) + " " + Plurals(
                        one = "сотую",
                        other = "сотых"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100).toString()))

                    in 1000L..9999L -> spelloutCardinalFeminineAccusative.format((value).toLong() / 1000) + " " + Plurals(
                        one = "тысячную",
                        other = "тысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000).toString()))

                    in 10000L..99999L -> spelloutCardinalFeminineAccusative.format((value).toLong() / 10000) + " " + Plurals(
                        one = "десятитысячную",
                        other = "десятитысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000).toString()))

                    in 100000L..999999L -> spelloutCardinalFeminineAccusative.format((value).toLong() / 100000) + " " + Plurals(
                        one = "стотысячную",
                        other = "стотысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100000).toString()))

                    in 1000000L..9999999L -> spelloutCardinalFeminineAccusative.format((value).toLong() / 1000000) + " " + Plurals(
                        one = "миллионную",
                        other = "миллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000000).toString()))

                    in 10000000L..99999999L -> spelloutCardinalFeminineAccusative.format((value).toLong() / 10000000) + " " + Plurals(
                        one = "десятимиллионную",
                        other = "десятимиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000000).toString()))

                    in 100000000L..999999999L -> spelloutCardinalFeminineAccusative.format((value).toLong() / 100000000) + " " + Plurals(
                        one = "стомиллионную",
                        other = "стомиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100000000).toString()))

                    in 1000000000L..9999999999L -> spelloutCardinalFeminineAccusative.format((value).toLong() / 1000000000) + " " + Plurals(
                        one = "миллиардную",
                        other = "миллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000000000).toString()))

                    in 10000000000L..99999999999L -> spelloutCardinalFeminineAccusative.format((value).toLong() / 10000000000) + " " + Plurals(
                        one = "десятимиллиардную",
                        other = "десятимиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000000000).toString()))

                    in 100000000000L..999999999999L -> spelloutCardinalFeminineAccusative.format((value).toLong() / 100000000000) + " " + Plurals(one = "стомиллиардную", other = "стомиллиардных").get(
                        language.pluralFormChooser.pluralForm(((value).toLong() / 100000000000).toString())
                    )

                    else -> {
                        if (value >= 1000000000000.0) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format((value).toLong() / 1000000000000) else {
                            throw UnsupportedOperationException("fractionsFeminineAccusative only supports numbers >= 10")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 10L..99L -> spelloutCardinalFeminineAccusative.format((value) / 10) + " " + Plurals(
                        one = "десятую",
                        other = "десятых"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10).toString()))

                    in 100L..999L -> spelloutCardinalFeminineAccusative.format((value) / 100) + " " + Plurals(
                        one = "сотую",
                        other = "сотых"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100).toString()))

                    in 1000L..9999L -> spelloutCardinalFeminineAccusative.format((value) / 1000) + " " + Plurals(
                        one = "тысячную",
                        other = "тысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))

                    in 10000L..99999L -> spelloutCardinalFeminineAccusative.format((value) / 10000) + " " + Plurals(
                        one = "десятитысячную",
                        other = "десятитысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000).toString()))

                    in 100000L..999999L -> spelloutCardinalFeminineAccusative.format((value) / 100000) + " " + Plurals(
                        one = "стотысячную",
                        other = "стотысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000).toString()))

                    in 1000000L..9999999L -> spelloutCardinalFeminineAccusative.format((value) / 1000000) + " " + Plurals(
                        one = "миллионную",
                        other = "миллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))

                    in 10000000L..99999999L -> spelloutCardinalFeminineAccusative.format((value) / 10000000) + " " + Plurals(
                        one = "десятимиллионную",
                        other = "десятимиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000000).toString()))

                    in 100000000L..999999999L -> spelloutCardinalFeminineAccusative.format((value) / 100000000) + " " + Plurals(
                        one = "стомиллионную",
                        other = "стомиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000000).toString()))

                    in 1000000000L..9999999999L -> spelloutCardinalFeminineAccusative.format((value) / 1000000000) + " " + Plurals(
                        one = "миллиардную",
                        other = "миллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))

                    in 10000000000L..99999999999L -> spelloutCardinalFeminineAccusative.format((value) / 10000000000) + " " + Plurals(one = "десятимиллиардную", other = "десятимиллиардных").get(
                        language.pluralFormChooser.pluralForm(((value) / 10000000000).toString())
                    )

                    in 100000000000L..999999999999L -> spelloutCardinalFeminineAccusative.format((value) / 100000000000) + " " + Plurals(
                        one = "стомиллиардную",
                        other = "стомиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000000000).toString()))

                    else -> {
                        if (value >= 1000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format((value) / 1000000000000) else {
                            throw UnsupportedOperationException("fractionsFeminineAccusative only supports numbers >= 10")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000, 100000000000, 1000000000000).bestDenominatorOrNull(value)!!) {
                    10L -> spelloutCardinalFeminineAccusative.format((value * 10).toLong()) + " " + Plurals(
                        one = "десятую",
                        other = "десятых"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10).toLong()).toString()))

                    100L -> spelloutCardinalFeminineAccusative.format((value * 100).toLong()) + " " + Plurals(
                        one = "сотую",
                        other = "сотых"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100).toLong()).toString()))

                    1000L -> spelloutCardinalFeminineAccusative.format((value * 1000).toLong()) + " " + Plurals(
                        one = "тысячную",
                        other = "тысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))

                    10000L -> spelloutCardinalFeminineAccusative.format((value * 10000).toLong()) + " " + Plurals(
                        one = "десятитысячную",
                        other = "десятитысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000).toLong()).toString()))

                    100000L -> spelloutCardinalFeminineAccusative.format((value * 100000).toLong()) + " " + Plurals(
                        one = "стотысячную",
                        other = "стотысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000).toLong()).toString()))

                    1000000L -> spelloutCardinalFeminineAccusative.format((value * 1000000).toLong()) + " " + Plurals(
                        one = "миллионную",
                        other = "миллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))

                    10000000L -> spelloutCardinalFeminineAccusative.format((value * 10000000).toLong()) + " " + Plurals(
                        one = "десятимиллионную",
                        other = "десятимиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000000).toLong()).toString()))

                    100000000L -> spelloutCardinalFeminineAccusative.format((value * 100000000).toLong()) + " " + Plurals(
                        one = "стомиллионную",
                        other = "стомиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000000).toLong()).toString()))

                    1000000000L -> spelloutCardinalFeminineAccusative.format((value * 1000000000).toLong()) + " " + Plurals(
                        one = "миллиардную",
                        other = "миллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))

                    10000000000L -> spelloutCardinalFeminineAccusative.format((value * 10000000000).toLong()) + " " + Plurals(
                        one = "десятимиллиардную",
                        other = "десятимиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000000000).toLong()).toString()))

                    100000000000L -> spelloutCardinalFeminineAccusative.format((value * 100000000000).toLong()) + " " + Plurals(
                        one = "стомиллиардную",
                        other = "стомиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000000000).toLong()).toString()))

                    1000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format((value * 1000000000000).toLong())

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalMasculineLocative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нуле"
                    1L -> "одном"
                    2L -> "двух"
                    3L -> "трех"
                    4L -> "четырех"
                    5L -> "пяти"
                    6L -> "шести"
                    7L -> "семи"
                    8L -> "восьми"
                    9L -> "девяти"
                    10L -> "десяти"
                    11L -> "одиннадцати"
                    12L -> "двенадцати"
                    13L -> "тринадцати"
                    14L -> "четырнадцати"
                    15L -> "пятнадцати"
                    16L -> "шестнадцати"
                    17L -> "семнадцати"
                    18L -> "восемнадцати"
                    19L -> "девятнадцати"
                    in 20L..29L -> {
                        var result = "двадцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцать"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорока"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восьмидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = spelloutCardinalFeminineLocative.format((value) / 100)
                        result += "стах"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalFeminineLocative.format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысяче", other = "тысячах").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
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
                        result += Plurals(one = "миллионе", other = "миллионах").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
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
                        result += Plurals(one = "миллиарде", other = "миллиардах").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
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
                        result += Plurals(one = "триллионе", other = "триллионах").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
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
                        result += Plurals(one = "квадриллионе", other = "квадриллионах").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalMasculineLocative only supports numbers >= 0")
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
                    1000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нуле"
                    1L -> "одном"
                    2L -> "двух"
                    3L -> "трех"
                    4L -> "четырех"
                    5L -> "пяти"
                    6L -> "шести"
                    7L -> "семи"
                    8L -> "восьми"
                    9L -> "девяти"
                    10L -> "десяти"
                    11L -> "одиннадцати"
                    12L -> "двенадцати"
                    13L -> "тринадцати"
                    14L -> "четырнадцати"
                    15L -> "пятнадцати"
                    16L -> "шестнадцати"
                    17L -> "семнадцати"
                    18L -> "восемнадцати"
                    19L -> "девятнадцати"
                    20L -> {
                        var result = "двадцать"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцать"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорока"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятидесяти"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестидесяти"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семидесяти"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восьмидесяти"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "ста"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = spelloutCardinalFeminineLocative.format((value * 200).toLong())
                        result += "стах"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminineLocative.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысяче", other = "тысячах").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
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
                        result += Plurals(one = "миллионе", other = "миллионах").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
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
                        result += Plurals(one = "миллиарде", other = "миллиардах").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
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
                        result += Plurals(one = "триллионе", other = "триллионах").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
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
                        result += Plurals(one = "квадриллионе", other = "квадриллионах").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    spelloutCardinalFeminineLocative.format(kotlin.math.truncate(value).toLong()) + " " + Plurals(
                        one = "целой",
                        other = "целых"
                    ).get(language.pluralFormChooser.pluralForm((kotlin.math.truncate(value).toLong()).toString())) + " "
                })
                result += fractionsFeminineLocative.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalNeuterLocative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нуле"
                    1L -> "одном"
                    in 2L..19L -> spelloutCardinalMasculineLocative.format(value)
                    in 20L..29L -> {
                        var result = "двадцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорока"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восьмидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = spelloutCardinalFeminineLocative.format((value) / 100)
                        result += "стах"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalFeminineLocative.format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысяче", other = "тысячах").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculineLocative.format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "миллионе", other = "миллионах").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculineLocative.format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "миллиарде", other = "миллиардах").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculineLocative.format((value) / 1000000000000)
                        result += " "
                        result += Plurals(one = "триллионе", other = "триллионах").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculineLocative.format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(one = "квадриллионе", other = "квадриллионах").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalNeuterLocative only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 1000, 1000000, 1000000000, 1000000000000, 1000000000000000, 1000000000000000000).bestDenominatorOrNull(
                    value
                )!!) {
                    0L -> "нуле"
                    1L -> "одном"
                    2L -> spelloutCardinalMasculineLocative.format(value)
                    20L -> {
                        var result = "двадцати"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцати"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорока"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятидесяти"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестидесяти"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семидесяти"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восьмидесяти"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "ста"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = spelloutCardinalFeminineLocative.format((value * 200).toLong())
                        result += "стах"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminineLocative.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысяче", other = "тысячах").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculineLocative.format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "миллионе", other = "миллионах").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculineLocative.format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиарде", other = "миллиардах").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculineLocative.format((value * 1000000000000).toLong())
                        result += " "
                        result += Plurals(one = "триллионе", other = "триллионах").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculineLocative.format((value * 1000000000000000).toLong())
                        result += " "
                        result += Plurals(one = "квадриллионе", other = "квадриллионах").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    spelloutCardinalFeminineLocative.format(kotlin.math.truncate(value).toLong()) + " " + Plurals(
                        one = "целой",
                        other = "целых"
                    ).get(language.pluralFormChooser.pluralForm((kotlin.math.truncate(value).toLong()).toString())) + " "
                })
                result += fractionsFeminineLocative.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalFeminineLocative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нуле"
                    1L -> "одной"
                    in 2L..19L -> spelloutCardinalMasculineLocative.format(value)
                    in 20L..29L -> {
                        var result = "двадцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорока"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восьмидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = format((value) / 100)
                        result += "стах"
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
                        result += Plurals(one = "тысяче", other = "тысячах").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculineLocative.format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "миллионе", other = "миллионах").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculineLocative.format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "миллиарде", other = "миллиардах").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculineLocative.format((value) / 1000000000000)
                        result += " "
                        result += Plurals(one = "триллионе", other = "триллионах").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculineLocative.format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(one = "квадриллионе", other = "квадриллионах").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalFeminineLocative only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 1000, 1000000, 1000000000, 1000000000000, 1000000000000000, 1000000000000000000).bestDenominatorOrNull(
                    value
                )!!) {
                    0L -> "нуле"
                    1L -> "одной"
                    2L -> spelloutCardinalMasculineLocative.format(value)
                    20L -> {
                        var result = "двадцати"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцати"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорока"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятидесяти"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестидесяти"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семидесяти"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восьмидесяти"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "ста"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = format((value * 200).toLong())
                        result += "стах"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысяче", other = "тысячах").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculineLocative.format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "миллионе", other = "миллионах").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculineLocative.format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиарде", other = "миллиардах").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculineLocative.format((value * 1000000000000).toLong())
                        result += " "
                        result += Plurals(one = "триллионе", other = "триллионах").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculineLocative.format((value * 1000000000000000).toLong())
                        result += " "
                        result += Plurals(one = "квадриллионе", other = "квадриллионах").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    format(kotlin.math.truncate(value).toLong()) + " " + Plurals(one = "целой", other = "целых").get(
                        language.pluralFormChooser.pluralForm(
                            (kotlin.math.truncate(value).toLong()).toString()
                        )
                    ) + " "
                })
                result += fractionsFeminineLocative.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalPluralLocative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нуле"
                    1L -> "одних"
                    in 2L..19L -> spelloutCardinalMasculineLocative.format(value)
                    in 20L..29L -> {
                        var result = "двадцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцати"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорока"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восьмидесяти"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = spelloutCardinalFeminineLocative.format((value) / 100)
                        result += "стах"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalFeminineLocative.format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысяче", other = "тысячах").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculineLocative.format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "миллионе", other = "миллионах").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculineLocative.format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "миллиарде", other = "миллиардах").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculineLocative.format((value) / 1000000000000)
                        result += " "
                        result += Plurals(one = "триллионе", other = "триллионах").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculineLocative.format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(one = "квадриллионе", other = "квадриллионах").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalPluralLocative only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 1000, 1000000, 1000000000, 1000000000000, 1000000000000000, 1000000000000000000).bestDenominatorOrNull(
                    value
                )!!) {
                    0L -> "нуле"
                    1L -> "одних"
                    2L -> spelloutCardinalMasculineLocative.format(value)
                    20L -> {
                        var result = "двадцати"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцати"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорока"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятидесяти"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестидесяти"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семидесяти"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восьмидесяти"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "ста"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = spelloutCardinalFeminineLocative.format((value * 200).toLong())
                        result += "стах"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminineLocative.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысяче", other = "тысячах").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculineLocative.format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "миллионе", other = "миллионах").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculineLocative.format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиарде", other = "миллиардах").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculineLocative.format((value * 1000000000000).toLong())
                        result += " "
                        result += Plurals(one = "триллионе", other = "триллионах").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculineLocative.format((value * 1000000000000000).toLong())
                        result += " "
                        result += Plurals(one = "квадриллионе", other = "квадриллионах").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                return format(kotlin.math.truncate(value).toLong()) + " запятая " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class FractionsFeminineLocative : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 10L..99L -> spelloutCardinalFeminineLocative.format((value).toLong() / 10) + " " + Plurals(
                        one = "десятой",
                        other = "десятых"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10).toString()))

                    in 100L..999L -> spelloutCardinalFeminineLocative.format((value).toLong() / 100) + " " + Plurals(
                        one = "сотой",
                        other = "сотых"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100).toString()))

                    in 1000L..9999L -> spelloutCardinalFeminineLocative.format((value).toLong() / 1000) + " " + Plurals(one = "тысячной", other = "тысячных").get(
                        language.pluralFormChooser.pluralForm(
                            ((value).toLong() / 1000).toString()
                        )
                    )

                    in 10000L..99999L -> spelloutCardinalFeminineLocative.format((value).toLong() / 10000) + " " + Plurals(
                        one = "десятитысячной",
                        other = "десятитысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000).toString()))

                    in 100000L..999999L -> spelloutCardinalFeminineLocative.format((value).toLong() / 100000) + " " + Plurals(
                        one = "стотысячной",
                        other = "стотысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100000).toString()))

                    in 1000000L..9999999L -> spelloutCardinalFeminineLocative.format((value).toLong() / 1000000) + " " + Plurals(
                        one = "миллионной",
                        other = "миллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000000).toString()))

                    in 10000000L..99999999L -> spelloutCardinalFeminineLocative.format((value).toLong() / 10000000) + " " + Plurals(
                        one = "десятимиллионной",
                        other = "десятимиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000000).toString()))

                    in 100000000L..999999999L -> spelloutCardinalFeminineLocative.format((value).toLong() / 100000000) + " " + Plurals(
                        one = "стомиллионной",
                        other = "стомиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100000000).toString()))

                    in 1000000000L..9999999999L -> spelloutCardinalFeminineLocative.format((value).toLong() / 1000000000) + " " + Plurals(
                        one = "миллиардной",
                        other = "миллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000000000).toString()))

                    in 10000000000L..99999999999L -> spelloutCardinalFeminineLocative.format((value).toLong() / 10000000000) + " " + Plurals(
                        one = "десятимиллиардной",
                        other = "десятимиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000000000).toString()))

                    in 100000000000L..999999999999L -> spelloutCardinalFeminineLocative.format((value).toLong() / 100000000000) + " " + Plurals(one = "стомиллиардной", other = "стомиллиардных").get(
                        language.pluralFormChooser.pluralForm(((value).toLong() / 100000000000).toString())
                    )

                    else -> {
                        if (value >= 1000000000000.0) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format((value).toLong() / 1000000000000) else {
                            throw UnsupportedOperationException("fractionsFeminineLocative only supports numbers >= 10")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 10L..99L -> spelloutCardinalFeminineLocative.format((value) / 10) + " " + Plurals(
                        one = "десятой",
                        other = "десятых"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10).toString()))

                    in 100L..999L -> spelloutCardinalFeminineLocative.format((value) / 100) + " " + Plurals(
                        one = "сотой",
                        other = "сотых"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100).toString()))

                    in 1000L..9999L -> spelloutCardinalFeminineLocative.format((value) / 1000) + " " + Plurals(
                        one = "тысячной",
                        other = "тысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))

                    in 10000L..99999L -> spelloutCardinalFeminineLocative.format((value) / 10000) + " " + Plurals(
                        one = "десятитысячной",
                        other = "десятитысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000).toString()))

                    in 100000L..999999L -> spelloutCardinalFeminineLocative.format((value) / 100000) + " " + Plurals(
                        one = "стотысячной",
                        other = "стотысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000).toString()))

                    in 1000000L..9999999L -> spelloutCardinalFeminineLocative.format((value) / 1000000) + " " + Plurals(
                        one = "миллионной",
                        other = "миллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))

                    in 10000000L..99999999L -> spelloutCardinalFeminineLocative.format((value) / 10000000) + " " + Plurals(
                        one = "десятимиллионной",
                        other = "десятимиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000000).toString()))

                    in 100000000L..999999999L -> spelloutCardinalFeminineLocative.format((value) / 100000000) + " " + Plurals(
                        one = "стомиллионной",
                        other = "стомиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000000).toString()))

                    in 1000000000L..9999999999L -> spelloutCardinalFeminineLocative.format((value) / 1000000000) + " " + Plurals(
                        one = "миллиардной",
                        other = "миллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))

                    in 10000000000L..99999999999L -> spelloutCardinalFeminineLocative.format((value) / 10000000000) + " " + Plurals(
                        one = "десятимиллиардной",
                        other = "десятимиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000000000).toString()))

                    in 100000000000L..999999999999L -> spelloutCardinalFeminineLocative.format((value) / 100000000000) + " " + Plurals(
                        one = "стомиллиардной",
                        other = "стомиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000000000).toString()))

                    else -> {
                        if (value >= 1000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format((value) / 1000000000000) else {
                            throw UnsupportedOperationException("fractionsFeminineLocative only supports numbers >= 10")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000, 100000000000, 1000000000000).bestDenominatorOrNull(value)!!) {
                    10L -> spelloutCardinalFeminineLocative.format((value * 10).toLong()) + " " + Plurals(
                        one = "десятой",
                        other = "десятых"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10).toLong()).toString()))

                    100L -> spelloutCardinalFeminineLocative.format((value * 100).toLong()) + " " + Plurals(
                        one = "сотой",
                        other = "сотых"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100).toLong()).toString()))

                    1000L -> spelloutCardinalFeminineLocative.format((value * 1000).toLong()) + " " + Plurals(
                        one = "тысячной",
                        other = "тысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))

                    10000L -> spelloutCardinalFeminineLocative.format((value * 10000).toLong()) + " " + Plurals(
                        one = "десятитысячной",
                        other = "десятитысячных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000).toLong()).toString()))

                    100000L -> spelloutCardinalFeminineLocative.format((value * 100000).toLong()) + " " + Plurals(one = "стотысячной", other = "стотысячных").get(
                        language.pluralFormChooser.pluralForm(
                            ((value * 100000).toLong()).toString()
                        )
                    )

                    1000000L -> spelloutCardinalFeminineLocative.format((value * 1000000).toLong()) + " " + Plurals(one = "миллионной", other = "миллионных").get(
                        language.pluralFormChooser.pluralForm(
                            ((value * 1000000).toLong()).toString()
                        )
                    )

                    10000000L -> spelloutCardinalFeminineLocative.format((value * 10000000).toLong()) + " " + Plurals(
                        one = "десятимиллионной",
                        other = "десятимиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000000).toLong()).toString()))

                    100000000L -> spelloutCardinalFeminineLocative.format((value * 100000000).toLong()) + " " + Plurals(
                        one = "стомиллионной",
                        other = "стомиллионных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000000).toLong()).toString()))

                    1000000000L -> spelloutCardinalFeminineLocative.format((value * 1000000000).toLong()) + " " + Plurals(
                        one = "миллиардной",
                        other = "миллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))

                    10000000000L -> spelloutCardinalFeminineLocative.format((value * 10000000000).toLong()) + " " + Plurals(
                        one = "десятимиллиардной",
                        other = "десятимиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000000000).toLong()).toString()))

                    100000000000L -> spelloutCardinalFeminineLocative.format((value * 100000000000).toLong()) + " " + Plurals(
                        one = "стомиллиардной",
                        other = "стомиллиардных"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000000000).toLong()).toString()))

                    1000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format((value * 1000000000000).toLong())

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalMasculineAblative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулем"
                    1L -> "одним"
                    2L -> "двумя"
                    3L -> "тремя"
                    4L -> "четырьмя"
                    5L -> "пятью"
                    6L -> "шестью"
                    7L -> "семью"
                    8L -> "восемью"
                    9L -> "девятью"
                    10L -> "десятью"
                    11L -> "одиннадцатью"
                    12L -> "двенадцатью"
                    13L -> "тринадцатью"
                    14L -> "четырнадцатью"
                    15L -> "пятнадцатью"
                    16L -> "шестнадцатью"
                    17L -> "семнадцатью"
                    18L -> "восемнадцатью"
                    19L -> "девятнадцатью"
                    in 20L..29L -> {
                        var result = "двадцатью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцатью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорока"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятьюдесятью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестьюдесятью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семьюдесятью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восемьюдесятью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = spelloutCardinalFeminineAblative.format((value) / 100)
                        result += "стами"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalFeminineAblative.format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысячей", other = "тысячами").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
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
                        result += Plurals(one = "миллионом", other = "миллионами").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
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
                        result += Plurals(one = "миллиардом", other = "миллиардами").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
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
                        result += Plurals(one = "триллионом", other = "триллионами").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
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
                        result += Plurals(one = "квадриллионом", other = "квадриллионами").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalMasculineAblative only supports numbers >= 0")
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
                    1000,
                    1000000,
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нулем"
                    1L -> "одним"
                    2L -> "двумя"
                    3L -> "тремя"
                    4L -> "четырьмя"
                    5L -> "пятью"
                    6L -> "шестью"
                    7L -> "семью"
                    8L -> "восемью"
                    9L -> "девятью"
                    10L -> "десятью"
                    11L -> "одиннадцатью"
                    12L -> "двенадцатью"
                    13L -> "тринадцатью"
                    14L -> "четырнадцатью"
                    15L -> "пятнадцатью"
                    16L -> "шестнадцатью"
                    17L -> "семнадцатью"
                    18L -> "восемнадцатью"
                    19L -> "девятнадцатью"
                    20L -> {
                        var result = "двадцатью"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцатью"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорока"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятьюдесятью"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестьюдесятью"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семьюдесятью"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восемьюдесятью"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "ста"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = spelloutCardinalFeminineAblative.format((value * 200).toLong())
                        result += "стами"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminineAblative.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысячей", other = "тысячами").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
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
                        result += Plurals(one = "миллионом", other = "миллионами").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
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
                        result += Plurals(one = "миллиардом", other = "миллиардами").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
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
                        result += Plurals(one = "триллионом", other = "триллионами").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
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
                        result += Plurals(one = "квадриллионом", other = "квадриллионами").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    spelloutCardinalFeminineAblative.format(kotlin.math.truncate(value).toLong()) + " " + Plurals(
                        one = "целой",
                        other = "целыми"
                    ).get(language.pluralFormChooser.pluralForm((kotlin.math.truncate(value).toLong()).toString())) + " "
                })
                result += fractionsFeminineAblative.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalNeuterAblative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutCardinalMasculineAblative.format(value) else {
                    throw UnsupportedOperationException("spelloutCardinalNeuterAblative only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutCardinalMasculineAblative.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutCardinalMasculineAblative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalFeminineAblative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулем"
                    1L -> "одной"
                    in 2L..19L -> spelloutCardinalMasculineAblative.format(value)
                    in 20L..29L -> {
                        var result = "двадцатью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцатью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорока"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятьюдесятью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестьюдесятью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семьюдесятью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восемьюдесятью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = format((value) / 100)
                        result += "стами"
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
                        result += Plurals(one = "тысячей", other = "тысячами").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculineAblative.format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "миллионом", other = "миллионами").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculineAblative.format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "миллиардом", other = "миллиардами").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculineAblative.format((value) / 1000000000000)
                        result += " "
                        result += Plurals(one = "триллионом", other = "триллионами").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculineAblative.format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(one = "квадриллионом", other = "квадриллионами").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalFeminineAblative only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 1000, 1000000, 1000000000, 1000000000000, 1000000000000000, 1000000000000000000).bestDenominatorOrNull(
                    value
                )!!) {
                    0L -> "нулем"
                    1L -> "одной"
                    2L -> spelloutCardinalMasculineAblative.format(value)
                    20L -> {
                        var result = "двадцатью"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцатью"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорока"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятьюдесятью"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестьюдесятью"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семьюдесятью"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восемьюдесятью"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "ста"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = format((value * 200).toLong())
                        result += "стами"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысячей", other = "тысячами").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculineAblative.format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "миллионом", other = "миллионами").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculineAblative.format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиардом", other = "миллиардами").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculineAblative.format((value * 1000000000000).toLong())
                        result += " "
                        result += Plurals(one = "триллионом", other = "триллионами").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculineAblative.format((value * 1000000000000000).toLong())
                        result += " "
                        result += Plurals(one = "квадриллионом", other = "квадриллионами").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                var result = (if (value in 0.0..1.0) {
                    ""
                } else {
                    format(kotlin.math.truncate(value).toLong()) + " " + Plurals(one = "целой", other = "целыми").get(
                        language.pluralFormChooser.pluralForm(
                            (kotlin.math.truncate(value).toLong()).toString()
                        )
                    ) + " "
                })
                result += fractionsFeminineAblative.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
                return result
            }
        }

        private inner class SpelloutCardinalPluralAblative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулем"
                    1L -> "одними"
                    in 2L..19L -> spelloutCardinalMasculineAblative.format(value)
                    in 20L..29L -> {
                        var result = "двадцатью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "тридцатью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "сорока"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "пятьюдесятью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "шестьюдесятью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "семьюдесятью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "восемьюдесятью"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "девяноста"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 200L..999L -> {
                        var result = spelloutCardinalFeminineAblative.format((value) / 100)
                        result += "стами"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..999999L -> {
                        var result = spelloutCardinalFeminineAblative.format((value) / 1000)
                        result += " "
                        result += Plurals(one = "тысячей", other = "тысячами").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculineAblative.format((value) / 1000000)
                        result += " "
                        result += Plurals(one = "миллионом", other = "миллионами").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculineAblative.format((value) / 1000000000)
                        result += " "
                        result += Plurals(one = "миллиардом", other = "миллиардами").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculineAblative.format((value) / 1000000000000)
                        result += " "
                        result += Plurals(one = "триллионом", other = "триллионами").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculineAblative.format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(one = "квадриллионом", other = "квадриллионами").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalPluralAblative only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 1000, 1000000, 1000000000, 1000000000000, 1000000000000000, 1000000000000000000).bestDenominatorOrNull(
                    value
                )!!) {
                    0L -> "нулем"
                    1L -> "одними"
                    2L -> spelloutCardinalMasculineAblative.format(value)
                    20L -> {
                        var result = "двадцатью"
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    30L -> {
                        var result = "тридцатью"
                        result += (if (value * 30 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    40L -> {
                        var result = "сорока"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "пятьюдесятью"
                        result += (if (value * 50 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    60L -> {
                        var result = "шестьюдесятью"
                        result += (if (value * 60 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    70L -> {
                        var result = "семьюдесятью"
                        result += (if (value * 70 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    80L -> {
                        var result = "восемьюдесятью"
                        result += (if (value * 80 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    90L -> {
                        var result = "девяноста"
                        result += (if (value * 90 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "ста"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = spelloutCardinalFeminineAblative.format((value * 200).toLong())
                        result += "стами"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = spelloutCardinalFeminineAblative.format((value * 1000).toLong())
                        result += " "
                        result += Plurals(one = "тысячей", other = "тысячами").get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalMasculineAblative.format((value * 1000000).toLong())
                        result += " "
                        result += Plurals(one = "миллионом", other = "миллионами").get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculineAblative.format((value * 1000000000).toLong())
                        result += " "
                        result += Plurals(one = "миллиардом", other = "миллиардами").get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculineAblative.format((value * 1000000000000).toLong())
                        result += " "
                        result += Plurals(one = "триллионом", other = "триллионами").get(language.pluralFormChooser.pluralForm(((value * 1000000000000).toLong()).toString()))
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculineAblative.format((value * 1000000000000000).toLong())
                        result += " "
                        result += Plurals(one = "квадриллионом", other = "квадриллионами").get(language.pluralFormChooser.pluralForm(((value * 1000000000000000).toLong()).toString()))
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
                return format(kotlin.math.truncate(value).toLong()) + " запятая " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class FractionsFeminineAblative : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 10L..99L -> spelloutCardinalFeminineAblative.format((value).toLong() / 10) + " " + Plurals(
                        one = "десятой",
                        other = "десятыми"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10).toString()))

                    in 100L..999L -> spelloutCardinalFeminineAblative.format((value).toLong() / 100) + " " + Plurals(
                        one = "сотой",
                        other = "сотыми"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100).toString()))

                    in 1000L..9999L -> spelloutCardinalFeminineAblative.format((value).toLong() / 1000) + " " + Plurals(
                        one = "тысячной",
                        other = "тысячными"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000).toString()))

                    in 10000L..99999L -> spelloutCardinalFeminineAblative.format((value).toLong() / 10000) + " " + Plurals(
                        one = "десятитысячной",
                        other = "десятитысячными"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000).toString()))

                    in 100000L..999999L -> spelloutCardinalFeminineAblative.format((value).toLong() / 100000) + " " + Plurals(
                        one = "стотысячной",
                        other = "стотысячными"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100000).toString()))

                    in 1000000L..9999999L -> spelloutCardinalFeminineAblative.format((value).toLong() / 1000000) + " " + Plurals(
                        one = "миллионной",
                        other = "миллионными"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000000).toString()))

                    in 10000000L..99999999L -> spelloutCardinalFeminineAblative.format((value).toLong() / 10000000) + " " + Plurals(
                        one = "десятимиллионной",
                        other = "десятимиллионными"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000000).toString()))

                    in 100000000L..999999999L -> spelloutCardinalFeminineAblative.format((value).toLong() / 100000000) + " " + Plurals(
                        one = "стомиллионной",
                        other = "стомиллионными"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 100000000).toString()))

                    in 1000000000L..9999999999L -> spelloutCardinalFeminineAblative.format((value).toLong() / 1000000000) + " " + Plurals(
                        one = "миллиардной",
                        other = "миллиардными"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 1000000000).toString()))

                    in 10000000000L..99999999999L -> spelloutCardinalFeminineAblative.format((value).toLong() / 10000000000) + " " + Plurals(
                        one = "десятимиллиардной",
                        other = "десятимиллиардными"
                    ).get(language.pluralFormChooser.pluralForm(((value).toLong() / 10000000000).toString()))

                    in 100000000000L..999999999999L -> spelloutCardinalFeminineAblative.format((value).toLong() / 100000000000) + " " + Plurals(one = "стомиллиардной", other = "стомиллиардными").get(
                        language.pluralFormChooser.pluralForm(((value).toLong() / 100000000000).toString())
                    )

                    else -> {
                        if (value >= 1000000000000.0) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format((value).toLong() / 1000000000000) else {
                            throw UnsupportedOperationException("fractionsFeminineAblative only supports numbers >= 10")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 10L..99L -> spelloutCardinalFeminineAblative.format((value) / 10) + " " + Plurals(
                        one = "десятой",
                        other = "десятыми"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10).toString()))

                    in 100L..999L -> spelloutCardinalFeminineAblative.format((value) / 100) + " " + Plurals(
                        one = "сотой",
                        other = "сотыми"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100).toString()))

                    in 1000L..9999L -> spelloutCardinalFeminineAblative.format((value) / 1000) + " " + Plurals(
                        one = "тысячной",
                        other = "тысячными"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))

                    in 10000L..99999L -> spelloutCardinalFeminineAblative.format((value) / 10000) + " " + Plurals(
                        one = "десятитысячной",
                        other = "десятитысячными"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000).toString()))

                    in 100000L..999999L -> spelloutCardinalFeminineAblative.format((value) / 100000) + " " + Plurals(
                        one = "стотысячной",
                        other = "стотысячными"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000).toString()))

                    in 1000000L..9999999L -> spelloutCardinalFeminineAblative.format((value) / 1000000) + " " + Plurals(
                        one = "миллионной",
                        other = "миллионными"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))

                    in 10000000L..99999999L -> spelloutCardinalFeminineAblative.format((value) / 10000000) + " " + Plurals(
                        one = "десятимиллионной",
                        other = "десятимиллионными"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 10000000).toString()))

                    in 100000000L..999999999L -> spelloutCardinalFeminineAblative.format((value) / 100000000) + " " + Plurals(
                        one = "стомиллионной",
                        other = "стомиллионными"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000000).toString()))

                    in 1000000000L..9999999999L -> spelloutCardinalFeminineAblative.format((value) / 1000000000) + " " + Plurals(
                        one = "миллиардной",
                        other = "миллиардными"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))

                    in 10000000000L..99999999999L -> spelloutCardinalFeminineAblative.format((value) / 10000000000) + " " + Plurals(one = "десятимиллиардной", other = "десятимиллиардными").get(
                        language.pluralFormChooser.pluralForm(((value) / 10000000000).toString())
                    )

                    in 100000000000L..999999999999L -> spelloutCardinalFeminineAblative.format((value) / 100000000000) + " " + Plurals(
                        one = "стомиллиардной",
                        other = "стомиллиардными"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 100000000000).toString()))

                    else -> {
                        if (value >= 1000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format((value) / 1000000000000) else {
                            throw UnsupportedOperationException("fractionsFeminineAblative only supports numbers >= 10")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000, 100000000000, 1000000000000).bestDenominatorOrNull(value)!!) {
                    10L -> spelloutCardinalFeminineAblative.format((value * 10).toLong()) + " " + Plurals(
                        one = "десятой",
                        other = "десятыми"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10).toLong()).toString()))

                    100L -> spelloutCardinalFeminineAblative.format((value * 100).toLong()) + " " + Plurals(
                        one = "сотой",
                        other = "сотыми"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100).toLong()).toString()))

                    1000L -> spelloutCardinalFeminineAblative.format((value * 1000).toLong()) + " " + Plurals(
                        one = "тысячной",
                        other = "тысячными"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000).toLong()).toString()))

                    10000L -> spelloutCardinalFeminineAblative.format((value * 10000).toLong()) + " " + Plurals(
                        one = "десятитысячной",
                        other = "десятитысячными"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000).toLong()).toString()))

                    100000L -> spelloutCardinalFeminineAblative.format((value * 100000).toLong()) + " " + Plurals(
                        one = "стотысячной",
                        other = "стотысячными"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000).toLong()).toString()))

                    1000000L -> spelloutCardinalFeminineAblative.format((value * 1000000).toLong()) + " " + Plurals(
                        one = "миллионной",
                        other = "миллионными"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000000).toLong()).toString()))

                    10000000L -> spelloutCardinalFeminineAblative.format((value * 10000000).toLong()) + " " + Plurals(
                        one = "десятимиллионной",
                        other = "десятимиллионными"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000000).toLong()).toString()))

                    100000000L -> spelloutCardinalFeminineAblative.format((value * 100000000).toLong()) + " " + Plurals(
                        one = "стомиллионной",
                        other = "стомиллионными"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000000).toLong()).toString()))

                    1000000000L -> spelloutCardinalFeminineAblative.format((value * 1000000000).toLong()) + " " + Plurals(
                        one = "миллиардной",
                        other = "миллиардными"
                    ).get(language.pluralFormChooser.pluralForm(((value * 1000000000).toLong()).toString()))

                    10000000000L -> spelloutCardinalFeminineAblative.format((value * 10000000000).toLong()) + " " + Plurals(
                        one = "десятимиллиардной",
                        other = "десятимиллиардными"
                    ).get(language.pluralFormChooser.pluralForm(((value * 10000000000).toLong()).toString()))

                    100000000000L -> spelloutCardinalFeminineAblative.format((value * 100000000000).toLong()) + " " + Plurals(
                        one = "стомиллиардной",
                        other = "стомиллиардными"
                    ).get(language.pluralFormChooser.pluralForm(((value * 100000000000).toLong()).toString()))

                    1000000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format((value * 1000000000000).toLong())

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Yj : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ный"
                    else -> {
                        if (value >= 1.0) "а " + spelloutOrdinalMasculine.format(value) else {
                            throw UnsupportedOperationException("yj only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ный"
                    else -> {
                        if (value >= 1L) "а " + spelloutOrdinalMasculine.format(value) else {
                            throw UnsupportedOperationException("yj only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ный"
                    1L -> "а " + spelloutOrdinalMasculine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Oe : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ное"
                    else -> {
                        if (value >= 1.0) "а " + spelloutOrdinalNeuter.format(value) else {
                            throw UnsupportedOperationException("oe only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ное"
                    else -> {
                        if (value >= 1L) "а " + spelloutOrdinalNeuter.format(value) else {
                            throw UnsupportedOperationException("oe only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ное"
                    1L -> "а " + spelloutOrdinalNeuter.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Aja : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ная"
                    else -> {
                        if (value >= 1.0) "а " + spelloutOrdinalFeminine.format(value) else {
                            throw UnsupportedOperationException("aja only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ная"
                    else -> {
                        if (value >= 1L) "а " + spelloutOrdinalFeminine.format(value) else {
                            throw UnsupportedOperationException("aja only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ная"
                    1L -> "а " + spelloutOrdinalFeminine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Omu : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ному"
                    else -> {
                        if (value >= 1.0) "а " + spelloutOrdinalMasculineDative.format(value) else {
                            throw UnsupportedOperationException("omu only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ному"
                    else -> {
                        if (value >= 1L) "а " + spelloutOrdinalMasculineDative.format(value) else {
                            throw UnsupportedOperationException("omu only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ному"
                    1L -> "а " + spelloutOrdinalMasculineDative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Oj : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ной"
                    else -> {
                        if (value >= 1.0) "а " + spelloutOrdinalFeminineGenitive.format(value) else {
                            throw UnsupportedOperationException("oj only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ной"
                    else -> {
                        if (value >= 1L) "а " + spelloutOrdinalFeminineGenitive.format(value) else {
                            throw UnsupportedOperationException("oj only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ной"
                    1L -> "а " + spelloutOrdinalFeminineGenitive.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Ogo : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ного"
                    else -> {
                        if (value >= 1.0) "а " + spelloutOrdinalMasculineGenitive.format(value) else {
                            throw UnsupportedOperationException("ogo only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ного"
                    else -> {
                        if (value >= 1L) "а " + spelloutOrdinalMasculineGenitive.format(value) else {
                            throw UnsupportedOperationException("ogo only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ного"
                    1L -> "а " + spelloutOrdinalMasculineGenitive.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Uju : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ную"
                    else -> {
                        if (value >= 1.0) "а " + spelloutOrdinalFeminineAccusative.format(value) else {
                            throw UnsupportedOperationException("uju only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ную"
                    else -> {
                        if (value >= 1L) "а " + spelloutOrdinalFeminineAccusative.format(value) else {
                            throw UnsupportedOperationException("uju only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ную"
                    1L -> "а " + spelloutOrdinalFeminineAccusative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Ym : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ным"
                    else -> {
                        if (value >= 1.0) "а " + spelloutOrdinalMasculineAblative.format(value) else {
                            throw UnsupportedOperationException("ym only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ным"
                    else -> {
                        if (value >= 1L) "а " + spelloutOrdinalMasculineAblative.format(value) else {
                            throw UnsupportedOperationException("ym only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ным"
                    1L -> "а " + spelloutOrdinalMasculineAblative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Om : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ном"
                    else -> {
                        if (value >= 1.0) "а " + spelloutOrdinalMasculineLocative.format(value) else {
                            throw UnsupportedOperationException("om only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ном"
                    else -> {
                        if (value >= 1L) "а " + spelloutOrdinalMasculineLocative.format(value) else {
                            throw UnsupportedOperationException("om only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ном"
                    1L -> "а " + spelloutOrdinalMasculineLocative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Ye : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ные"
                    else -> {
                        if (value >= 1.0) "а " + spelloutOrdinalPlural.format(value) else {
                            throw UnsupportedOperationException("ye only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ные"
                    else -> {
                        if (value >= 1L) "а " + spelloutOrdinalPlural.format(value) else {
                            throw UnsupportedOperationException("ye only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ные"
                    1L -> "а " + spelloutOrdinalPlural.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Yh : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ных"
                    else -> {
                        if (value >= 1.0) "а " + spelloutOrdinalPluralGenitive.format(value) else {
                            throw UnsupportedOperationException("yh only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ных"
                    else -> {
                        if (value >= 1L) "а " + spelloutOrdinalPluralGenitive.format(value) else {
                            throw UnsupportedOperationException("yh only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ных"
                    1L -> "а " + spelloutOrdinalPluralGenitive.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Ymi : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "ными"
                    else -> {
                        if (value >= 1.0) "а " + spelloutOrdinalPluralAblative.format(value) else {
                            throw UnsupportedOperationException("ymi only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "ными"
                    else -> {
                        if (value >= 1L) "а " + spelloutOrdinalPluralAblative.format(value) else {
                            throw UnsupportedOperationException("ymi only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "ными"
                    1L -> "а " + spelloutOrdinalPluralAblative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Thousandsprefixconjoined : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> spelloutCardinalFeminineGenitive.format(value)
                    1L -> "одно"
                    in 2L..20L -> spelloutCardinalFeminineGenitive.format(value)
                    21L -> "двадцатиодно"
                    in 22L..29L -> "двадцати" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    30L -> "тридцати"
                    31L -> "тридцатиодно"
                    in 32L..39L -> "тридцати" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    40L -> "сорока"
                    41L -> "сорокаодно"
                    in 42L..49L -> "сорока" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    50L -> "пятидесяти"
                    51L -> "пятидесятиодно"
                    in 52L..59L -> "пятидесяти" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    60L -> "шестидесяти"
                    61L -> "шестидесятиодно"
                    in 62L..69L -> "шестидесяти" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    70L -> "семидесяти"
                    71L -> "семидесятиодно"
                    in 72L..79L -> "семидесяти" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    80L -> "восьмидесяти"
                    81L -> "восьмидесятиодно"
                    in 82L..89L -> "восьмидесяти" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    90L -> spelloutCardinalFeminine.format(value)
                    91L -> "девяностоодно"
                    in 92L..99L -> "девяносто" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 100L..199L -> "сто" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    else -> {
                        if (value >= 200.0) spelloutCardinalFeminineGenitive.format((value).toLong() / 100) + "сот" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100)) else {
                            throw UnsupportedOperationException("thousandsprefixconjoined only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> spelloutCardinalFeminineGenitive.format(value)
                    1L -> "одно"
                    in 2L..20L -> spelloutCardinalFeminineGenitive.format(value)
                    21L -> "двадцатиодно"
                    in 22L..29L -> "двадцати" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    30L -> "тридцати"
                    31L -> "тридцатиодно"
                    in 32L..39L -> "тридцати" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    40L -> "сорока"
                    41L -> "сорокаодно"
                    in 42L..49L -> "сорока" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    50L -> "пятидесяти"
                    51L -> "пятидесятиодно"
                    in 52L..59L -> "пятидесяти" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    60L -> "шестидесяти"
                    61L -> "шестидесятиодно"
                    in 62L..69L -> "шестидесяти" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    70L -> "семидесяти"
                    71L -> "семидесятиодно"
                    in 72L..79L -> "семидесяти" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    80L -> "восьмидесяти"
                    81L -> "восьмидесятиодно"
                    in 82L..89L -> "восьмидесяти" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    90L -> spelloutCardinalFeminine.format(value)
                    91L -> "девяностоодно"
                    in 92L..99L -> "девяносто" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 100L..199L -> "сто" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    else -> {
                        if (value >= 200L) spelloutCardinalFeminineGenitive.format((value) / 100) + "сот" + (if ((value) % 100 == 0L) "" else format(value % 100)) else {
                            throw UnsupportedOperationException("thousandsprefixconjoined only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 21, 22, 30, 31, 32, 40, 41, 42, 50, 51, 52, 60, 61, 62, 70, 71, 72, 80, 81, 82, 90, 91, 92, 100, 200).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutCardinalFeminineGenitive.format(value)
                    1L -> "одно"
                    2L -> spelloutCardinalFeminineGenitive.format(value)
                    21L -> "двадцатиодно"
                    22L -> "двадцати" + (if (value * 22 == 1.0) "" else format(value % 10))
                    30L -> "тридцати"
                    31L -> "тридцатиодно"
                    32L -> "тридцати" + (if (value * 32 == 1.0) "" else format(value % 10))
                    40L -> "сорока"
                    41L -> "сорокаодно"
                    42L -> "сорока" + (if (value * 42 == 1.0) "" else format(value % 10))
                    50L -> "пятидесяти"
                    51L -> "пятидесятиодно"
                    52L -> "пятидесяти" + (if (value * 52 == 1.0) "" else format(value % 10))
                    60L -> "шестидесяти"
                    61L -> "шестидесятиодно"
                    62L -> "шестидесяти" + (if (value * 62 == 1.0) "" else format(value % 10))
                    70L -> "семидесяти"
                    71L -> "семидесятиодно"
                    72L -> "семидесяти" + (if (value * 72 == 1.0) "" else format(value % 10))
                    80L -> "восьмидесяти"
                    81L -> "восьмидесятиодно"
                    82L -> "восьмидесяти" + (if (value * 82 == 1.0) "" else format(value % 10))
                    90L -> spelloutCardinalFeminine.format(value)
                    91L -> "девяностоодно"
                    92L -> "девяносто" + (if (value * 92 == 1.0) "" else format(value % 10))
                    100L -> "сто" + (if (value * 100 == 1.0) "" else format(value % 100))
                    200L -> spelloutCardinalFeminineGenitive.format((value * 200).toLong()) + "сот" + (if (value * 200 == 1.0) "" else format(value % 100))
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Thousandsprefixseparate : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutCardinalFeminine.format(value) + " " else {
                    throw UnsupportedOperationException("thousandsprefixseparate only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return if (value >= 0L) spelloutCardinalFeminine.format(value) + " " else {
                    throw UnsupportedOperationException("thousandsprefixseparate only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutCardinalFeminine.format(value) + " "
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalMasculine : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулевой"
                    1L -> "первый"
                    2L -> "второй"
                    3L -> "третий"
                    4L -> "четвертый"
                    5L -> "пятый"
                    6L -> "шестой"
                    7L -> "седьмой"
                    8L -> "восьмой"
                    9L -> "девятый"
                    10L -> "десятый"
                    11L -> "одиннадцатый"
                    12L -> "двенадцатый"
                    13L -> "тринадцатый"
                    14L -> "четырнадцатый"
                    15L -> "пятнадцатый"
                    16L -> "шестнадцатый"
                    17L -> "семнадцатый"
                    18L -> "восемнадцатый"
                    19L -> "девятнадцатый"
                    20L -> "двадцатый"
                    in 21L..29L -> "двадцать " + format(value % 10)
                    30L -> "тридцатый"
                    in 31L..39L -> "тридцать " + format(value % 10)
                    40L -> "сороковой"
                    in 41L..49L -> "сорок " + format(value % 10)
                    50L -> "пятидесятый"
                    in 51L..59L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятый"
                    in 61L..69L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятый"
                    in 71L..79L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятый"
                    in 81L..89L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностый"
                    in 91L..99L -> "девяносто " + format(value % 10)
                    100L -> "сотый"
                    in 101L..199L -> "сто " + format(value % 100)
                    in 200L..299L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + yj.format(value % 1000)
                    in 1001L..1999L -> "одна тысяч" + yj.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячный"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячный"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысячи"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячный"
                    in 5001L..21000L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячный"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 21001L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format(value) + "-й" else {
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
                    300,
                    500,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    21001
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нулевой"
                    1L -> "первый"
                    2L -> "второй"
                    3L -> "третий"
                    4L -> "четвертый"
                    5L -> "пятый"
                    6L -> "шестой"
                    7L -> "седьмой"
                    8L -> "восьмой"
                    9L -> "девятый"
                    10L -> "десятый"
                    11L -> "одиннадцатый"
                    12L -> "двенадцатый"
                    13L -> "тринадцатый"
                    14L -> "четырнадцатый"
                    15L -> "пятнадцатый"
                    16L -> "шестнадцатый"
                    17L -> "семнадцатый"
                    18L -> "восемнадцатый"
                    19L -> "девятнадцатый"
                    20L -> "двадцатый"
                    21L -> "двадцать " + format(value % 10)
                    30L -> "тридцатый"
                    31L -> "тридцать " + format(value % 10)
                    40L -> "сороковой"
                    41L -> "сорок " + format(value % 10)
                    50L -> "пятидесятый"
                    51L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятый"
                    61L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятый"
                    71L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятый"
                    81L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностый"
                    91L -> "девяносто " + format(value % 10)
                    100L -> "сотый"
                    101L -> "сто " + format(value % 100)
                    200L -> {
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + yj.format(value % 1000)
                    1001L -> "одна тысяч" + yj.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value * 2000).toLong()) + "тысячный"
                    2001L -> {
                        var result = thousandsprefixseparate.format((value * 2001).toLong())
                        result += "тысячи"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value * 5000).toLong()) + "тысячный"
                    5001L -> {
                        var result = thousandsprefixseparate.format((value * 5001).toLong())
                        result += "тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21001L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format(value) + "-й"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalNeuter : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулевое"
                    1L -> "первое"
                    2L -> "второе"
                    3L -> "третье"
                    4L -> "четвертое"
                    5L -> "пятое"
                    6L -> "шестое"
                    7L -> "седьмое"
                    8L -> "восьмое"
                    9L -> "девятое"
                    10L -> "десятое"
                    11L -> "одиннадцатое"
                    12L -> "двенадцатое"
                    13L -> "тринадцатое"
                    14L -> "четырнадцатое"
                    15L -> "пятнадцатое"
                    16L -> "шестнадцатое"
                    17L -> "семнадцатое"
                    18L -> "восемнадцатое"
                    19L -> "девятнадцатое"
                    20L -> "двадцатое"
                    in 21L..29L -> "двадцать " + format(value % 10)
                    30L -> "тридцатое"
                    in 31L..39L -> "тридцать " + format(value % 10)
                    40L -> "сороковое"
                    in 41L..49L -> "сорок " + format(value % 10)
                    50L -> "пятидесятое"
                    in 51L..59L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятое"
                    in 61L..69L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятое"
                    in 71L..79L -> "семьдесят " + format(value % 10)
                    80L -> "восемьдесятое"
                    in 81L..89L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностое"
                    in 91L..99L -> "девяносто " + format(value % 10)
                    100L -> "сотое"
                    in 101L..199L -> "сто " + format(value % 100)
                    in 200L..299L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + oe.format(value % 1000)
                    in 1001L..1999L -> "одна тысяч" + oe.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячное"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячное"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысячи"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячное"
                    in 5001L..21000L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячное"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 21001L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format(value) + "-е" else {
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
                    300,
                    500,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    21001
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нулевое"
                    1L -> "первое"
                    2L -> "второе"
                    3L -> "третье"
                    4L -> "четвертое"
                    5L -> "пятое"
                    6L -> "шестое"
                    7L -> "седьмое"
                    8L -> "восьмое"
                    9L -> "девятое"
                    10L -> "десятое"
                    11L -> "одиннадцатое"
                    12L -> "двенадцатое"
                    13L -> "тринадцатое"
                    14L -> "четырнадцатое"
                    15L -> "пятнадцатое"
                    16L -> "шестнадцатое"
                    17L -> "семнадцатое"
                    18L -> "восемнадцатое"
                    19L -> "девятнадцатое"
                    20L -> "двадцатое"
                    21L -> "двадцать " + format(value % 10)
                    30L -> "тридцатое"
                    31L -> "тридцать " + format(value % 10)
                    40L -> "сороковое"
                    41L -> "сорок " + format(value % 10)
                    50L -> "пятидесятое"
                    51L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятое"
                    61L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятое"
                    71L -> "семьдесят " + format(value % 10)
                    80L -> "восемьдесятое"
                    81L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностое"
                    91L -> "девяносто " + format(value % 10)
                    100L -> "сотое"
                    101L -> "сто " + format(value % 100)
                    200L -> {
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + oe.format(value % 1000)
                    1001L -> "одна тысяч" + oe.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value * 2000).toLong()) + "тысячное"
                    2001L -> {
                        var result = thousandsprefixseparate.format((value * 2001).toLong())
                        result += "тысячи"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value * 5000).toLong()) + "тысячное"
                    5001L -> {
                        var result = thousandsprefixseparate.format((value * 5001).toLong())
                        result += "тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21001L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format(value) + "-е"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalFeminine : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "ноль"
                    1L -> "первая"
                    2L -> "вторая"
                    3L -> "третья"
                    4L -> "четвертая"
                    5L -> "пятая"
                    6L -> "шестая"
                    7L -> "седьмая"
                    8L -> "восьмая"
                    9L -> "девятая"
                    10L -> "десятая"
                    11L -> "одиннадцатая"
                    12L -> "двенадцатая"
                    13L -> "тринадцатая"
                    14L -> "четырнадцатая"
                    15L -> "пятнадцатая"
                    16L -> "шестнадцатая"
                    17L -> "семнадцатая"
                    18L -> "восемнадцатая"
                    19L -> "девятнадцатая"
                    20L -> "двадцатая"
                    in 21L..29L -> "двадцать " + format(value % 10)
                    30L -> "тридцатая"
                    in 31L..39L -> "тридцать " + format(value % 10)
                    40L -> "сороковая"
                    in 41L..49L -> "сорок " + format(value % 10)
                    50L -> "пятидесятая"
                    in 51L..59L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятая"
                    in 61L..69L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятая"
                    in 71L..79L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятая"
                    in 81L..89L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностая"
                    in 91L..99L -> "девяносто " + format(value % 10)
                    100L -> "сотая"
                    in 101L..199L -> "сто " + format(value % 100)
                    in 200L..299L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + aja.format(value % 1000)
                    in 1001L..1999L -> "одна тысяч" + aja.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячная"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячная"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысячи"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячная"
                    in 5001L..21000L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячная"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 21001L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format(value) + "-я" else {
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
                    300,
                    500,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    21001
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "ноль"
                    1L -> "первая"
                    2L -> "вторая"
                    3L -> "третья"
                    4L -> "четвертая"
                    5L -> "пятая"
                    6L -> "шестая"
                    7L -> "седьмая"
                    8L -> "восьмая"
                    9L -> "девятая"
                    10L -> "десятая"
                    11L -> "одиннадцатая"
                    12L -> "двенадцатая"
                    13L -> "тринадцатая"
                    14L -> "четырнадцатая"
                    15L -> "пятнадцатая"
                    16L -> "шестнадцатая"
                    17L -> "семнадцатая"
                    18L -> "восемнадцатая"
                    19L -> "девятнадцатая"
                    20L -> "двадцатая"
                    21L -> "двадцать " + format(value % 10)
                    30L -> "тридцатая"
                    31L -> "тридцать " + format(value % 10)
                    40L -> "сороковая"
                    41L -> "сорок " + format(value % 10)
                    50L -> "пятидесятая"
                    51L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятая"
                    61L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятая"
                    71L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятая"
                    81L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностая"
                    91L -> "девяносто " + format(value % 10)
                    100L -> "сотая"
                    101L -> "сто " + format(value % 100)
                    200L -> {
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + aja.format(value % 1000)
                    1001L -> "одна тысяч" + aja.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value * 2000).toLong()) + "тысячная"
                    2001L -> {
                        var result = thousandsprefixseparate.format((value * 2001).toLong())
                        result += "тысячи"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value * 5000).toLong()) + "тысячная"
                    5001L -> {
                        var result = thousandsprefixseparate.format((value * 5001).toLong())
                        result += "тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21001L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format(value) + "-я"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalPlural : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулевые"
                    1L -> "первые"
                    2L -> "вторые"
                    3L -> "третьи"
                    4L -> "четвертые"
                    5L -> "пятые"
                    6L -> "шестые"
                    7L -> "седьмые"
                    8L -> "восьмые"
                    9L -> "девятые"
                    10L -> "десятые"
                    11L -> "одиннадцатые"
                    12L -> "двенадцатые"
                    13L -> "тринадцатые"
                    14L -> "четырнадцатые"
                    15L -> "пятнадцатые"
                    16L -> "шестнадцатые"
                    17L -> "семнадцатые"
                    18L -> "восемнадцатые"
                    19L -> "девятнадцатые"
                    20L -> "двадцатые"
                    in 21L..29L -> "двадцать " + format(value % 10)
                    30L -> "тридцатые"
                    in 31L..39L -> "тридцать " + format(value % 10)
                    40L -> "сороковые"
                    in 41L..49L -> "сорок " + format(value % 10)
                    50L -> "пятидесятые"
                    in 51L..59L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятые"
                    in 61L..69L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятые"
                    in 71L..79L -> "семьдесят " + format(value % 10)
                    80L -> "восемьдесятые"
                    in 81L..89L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностые"
                    in 91L..99L -> "девяносто " + format(value % 10)
                    100L -> "сотые"
                    in 101L..199L -> "сто " + format(value % 100)
                    in 200L..299L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + ye.format(value % 1000)
                    in 1001L..1999L -> "одна тысяч" + ye.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячные"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячные"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысячи"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячные"
                    in 5001L..21000L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячные"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 21001L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format(value) + "-e" else {
                            throw UnsupportedOperationException("spelloutOrdinalPlural only supports numbers >= 0")
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
                    300,
                    500,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    21001
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нулевые"
                    1L -> "первые"
                    2L -> "вторые"
                    3L -> "третьи"
                    4L -> "четвертые"
                    5L -> "пятые"
                    6L -> "шестые"
                    7L -> "седьмые"
                    8L -> "восьмые"
                    9L -> "девятые"
                    10L -> "десятые"
                    11L -> "одиннадцатые"
                    12L -> "двенадцатые"
                    13L -> "тринадцатые"
                    14L -> "четырнадцатые"
                    15L -> "пятнадцатые"
                    16L -> "шестнадцатые"
                    17L -> "семнадцатые"
                    18L -> "восемнадцатые"
                    19L -> "девятнадцатые"
                    20L -> "двадцатые"
                    21L -> "двадцать " + format(value % 10)
                    30L -> "тридцатые"
                    31L -> "тридцать " + format(value % 10)
                    40L -> "сороковые"
                    41L -> "сорок " + format(value % 10)
                    50L -> "пятидесятые"
                    51L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятые"
                    61L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятые"
                    71L -> "семьдесят " + format(value % 10)
                    80L -> "восемьдесятые"
                    81L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностые"
                    91L -> "девяносто " + format(value % 10)
                    100L -> "сотые"
                    101L -> "сто " + format(value % 100)
                    200L -> {
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + ye.format(value % 1000)
                    1001L -> "одна тысяч" + ye.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value * 2000).toLong()) + "тысячные"
                    2001L -> {
                        var result = thousandsprefixseparate.format((value * 2001).toLong())
                        result += "тысячи"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value * 5000).toLong()) + "тысячные"
                    5001L -> {
                        var result = thousandsprefixseparate.format((value * 5001).toLong())
                        result += "тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21001L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format(value) + "-e"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalMasculineGenitive : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулевого"
                    1L -> "первого"
                    2L -> "второго"
                    3L -> "третьего"
                    4L -> "четвертого"
                    5L -> "пятого"
                    6L -> "шестого"
                    7L -> "седьмого"
                    8L -> "восьмого"
                    9L -> "девятого"
                    10L -> "десятого"
                    11L -> "одиннадцатого"
                    12L -> "двенадцатого"
                    13L -> "тринадцатого"
                    14L -> "четырнадцатого"
                    15L -> "пятнадцатого"
                    16L -> "шестнадцатого"
                    17L -> "семнадцатого"
                    18L -> "восемнадцатого"
                    19L -> "девятнадцатого"
                    20L -> "двадцатого"
                    in 21L..29L -> "двадцать " + format(value % 10)
                    30L -> "тридцатого"
                    in 31L..39L -> "тридцать " + format(value % 10)
                    40L -> "сорокового"
                    in 41L..49L -> "сорок " + format(value % 10)
                    50L -> "пятидесятого"
                    in 51L..59L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятого"
                    in 61L..69L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятого"
                    in 71L..79L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятого"
                    in 81L..89L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностого"
                    in 91L..99L -> "девяносто " + format(value % 10)
                    100L -> "сотого"
                    in 101L..199L -> "сто " + format(value % 100)
                    in 200L..299L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + ogo.format(value % 1000)
                    in 1001L..1999L -> "одна тысяч" + ogo.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячного"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячного"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысячи"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячного"
                    in 5001L..21000L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячного"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 21001L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format(value) + "-го" else {
                            throw UnsupportedOperationException("spelloutOrdinalMasculineGenitive only supports numbers >= 0")
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
                    300,
                    500,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    21001
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нулевого"
                    1L -> "первого"
                    2L -> "второго"
                    3L -> "третьего"
                    4L -> "четвертого"
                    5L -> "пятого"
                    6L -> "шестого"
                    7L -> "седьмого"
                    8L -> "восьмого"
                    9L -> "девятого"
                    10L -> "десятого"
                    11L -> "одиннадцатого"
                    12L -> "двенадцатого"
                    13L -> "тринадцатого"
                    14L -> "четырнадцатого"
                    15L -> "пятнадцатого"
                    16L -> "шестнадцатого"
                    17L -> "семнадцатого"
                    18L -> "восемнадцатого"
                    19L -> "девятнадцатого"
                    20L -> "двадцатого"
                    21L -> "двадцать " + format(value % 10)
                    30L -> "тридцатого"
                    31L -> "тридцать " + format(value % 10)
                    40L -> "сорокового"
                    41L -> "сорок " + format(value % 10)
                    50L -> "пятидесятого"
                    51L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятого"
                    61L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятого"
                    71L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятого"
                    81L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностого"
                    91L -> "девяносто " + format(value % 10)
                    100L -> "сотого"
                    101L -> "сто " + format(value % 100)
                    200L -> {
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + ogo.format(value % 1000)
                    1001L -> "одна тысяч" + ogo.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value * 2000).toLong()) + "тысячного"
                    2001L -> {
                        var result = thousandsprefixseparate.format((value * 2001).toLong())
                        result += "тысячи"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value * 5000).toLong()) + "тысячного"
                    5001L -> {
                        var result = thousandsprefixseparate.format((value * 5001).toLong())
                        result += "тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21001L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format(value) + "-го"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalNeuterGenitive : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalMasculineGenitive.format(value) else {
                    throw UnsupportedOperationException("spelloutOrdinalNeuterGenitive only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutOrdinalMasculineGenitive.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalMasculineGenitive.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalFeminineGenitive : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулевой"
                    1L -> "первой"
                    2L -> "второй"
                    3L -> "третьей"
                    4L -> "четвертой"
                    5L -> "пятой"
                    6L -> "шестой"
                    7L -> "седьмой"
                    8L -> "восьмой"
                    9L -> "девятой"
                    10L -> "десятой"
                    11L -> "одиннадцатой"
                    12L -> "двенадцатой"
                    13L -> "тринадцатой"
                    14L -> "четырнадцатой"
                    15L -> "пятнадцатой"
                    16L -> "шестнадцатой"
                    17L -> "семнадцатой"
                    18L -> "восемнадцатой"
                    19L -> "девятнадцатой"
                    20L -> "двадцатой"
                    in 21L..29L -> "двадцать " + format(value % 10)
                    30L -> "тридцатой"
                    in 31L..39L -> "тридцать " + format(value % 10)
                    40L -> "сороковой"
                    in 41L..49L -> "сорок " + format(value % 10)
                    50L -> "пятидесятой"
                    in 51L..59L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятой"
                    in 61L..69L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятой"
                    in 71L..79L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятой"
                    in 81L..89L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностой"
                    in 91L..99L -> "девяносто " + format(value % 10)
                    100L -> "сотой"
                    in 101L..199L -> "сто " + format(value % 100)
                    in 200L..299L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + oj.format(value % 1000)
                    in 1001L..1999L -> "одна тысяч" + oj.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячной"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячной"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысячи"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячной"
                    in 5001L..21000L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячной"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 21001L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format(value) + "-й" else {
                            throw UnsupportedOperationException("spelloutOrdinalFeminineGenitive only supports numbers >= 0")
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
                    300,
                    500,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    21001
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нулевой"
                    1L -> "первой"
                    2L -> "второй"
                    3L -> "третьей"
                    4L -> "четвертой"
                    5L -> "пятой"
                    6L -> "шестой"
                    7L -> "седьмой"
                    8L -> "восьмой"
                    9L -> "девятой"
                    10L -> "десятой"
                    11L -> "одиннадцатой"
                    12L -> "двенадцатой"
                    13L -> "тринадцатой"
                    14L -> "четырнадцатой"
                    15L -> "пятнадцатой"
                    16L -> "шестнадцатой"
                    17L -> "семнадцатой"
                    18L -> "восемнадцатой"
                    19L -> "девятнадцатой"
                    20L -> "двадцатой"
                    21L -> "двадцать " + format(value % 10)
                    30L -> "тридцатой"
                    31L -> "тридцать " + format(value % 10)
                    40L -> "сороковой"
                    41L -> "сорок " + format(value % 10)
                    50L -> "пятидесятой"
                    51L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятой"
                    61L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятой"
                    71L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятой"
                    81L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностой"
                    91L -> "девяносто " + format(value % 10)
                    100L -> "сотой"
                    101L -> "сто " + format(value % 100)
                    200L -> {
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + oj.format(value % 1000)
                    1001L -> "одна тысяч" + oj.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value * 2000).toLong()) + "тысячной"
                    2001L -> {
                        var result = thousandsprefixseparate.format((value * 2001).toLong())
                        result += "тысячи"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value * 5000).toLong()) + "тысячной"
                    5001L -> {
                        var result = thousandsprefixseparate.format((value * 5001).toLong())
                        result += "тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21001L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format(value) + "-й"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalPluralGenitive : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулевых"
                    1L -> "первых"
                    2L -> "вторых"
                    3L -> "третьих"
                    4L -> "четвертых"
                    5L -> "пятых"
                    6L -> "шестых"
                    7L -> "седьмых"
                    8L -> "восьмых"
                    9L -> "девятых"
                    10L -> "десятых"
                    11L -> "одиннадцатых"
                    12L -> "двенадцатых"
                    13L -> "тринадцатых"
                    14L -> "четырнадцатых"
                    15L -> "пятнадцатых"
                    16L -> "шестнадцатых"
                    17L -> "семнадцатых"
                    18L -> "восемнадцатых"
                    19L -> "девятнадцатых"
                    20L -> "двадцатых"
                    in 21L..29L -> "двадцать " + format(value % 10)
                    30L -> "тридцатых"
                    in 31L..39L -> "тридцать " + format(value % 10)
                    40L -> "сороковых"
                    in 41L..49L -> "сорок " + format(value % 10)
                    50L -> "пятидесятых"
                    in 51L..59L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятых"
                    in 61L..69L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятых"
                    in 71L..79L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятых"
                    in 81L..89L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностых"
                    in 91L..99L -> "девяносто " + format(value % 10)
                    100L -> "сотых"
                    in 101L..199L -> "сто " + format(value % 100)
                    in 200L..299L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + yh.format(value % 1000)
                    in 1001L..1999L -> "одна тысяч" + yh.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячных"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячных"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысячи"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячных"
                    in 5001L..21000L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячных"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 21001L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format(value) + "-х" else {
                            throw UnsupportedOperationException("spelloutOrdinalPluralGenitive only supports numbers >= 0")
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
                    300,
                    500,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    21001
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нулевых"
                    1L -> "первых"
                    2L -> "вторых"
                    3L -> "третьих"
                    4L -> "четвертых"
                    5L -> "пятых"
                    6L -> "шестых"
                    7L -> "седьмых"
                    8L -> "восьмых"
                    9L -> "девятых"
                    10L -> "десятых"
                    11L -> "одиннадцатых"
                    12L -> "двенадцатых"
                    13L -> "тринадцатых"
                    14L -> "четырнадцатых"
                    15L -> "пятнадцатых"
                    16L -> "шестнадцатых"
                    17L -> "семнадцатых"
                    18L -> "восемнадцатых"
                    19L -> "девятнадцатых"
                    20L -> "двадцатых"
                    21L -> "двадцать " + format(value % 10)
                    30L -> "тридцатых"
                    31L -> "тридцать " + format(value % 10)
                    40L -> "сороковых"
                    41L -> "сорок " + format(value % 10)
                    50L -> "пятидесятых"
                    51L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятых"
                    61L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятых"
                    71L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятых"
                    81L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностых"
                    91L -> "девяносто " + format(value % 10)
                    100L -> "сотых"
                    101L -> "сто " + format(value % 100)
                    200L -> {
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + yh.format(value % 1000)
                    1001L -> "одна тысяч" + yh.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value * 2000).toLong()) + "тысячных"
                    2001L -> {
                        var result = thousandsprefixseparate.format((value * 2001).toLong())
                        result += "тысячи"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value * 5000).toLong()) + "тысячных"
                    5001L -> {
                        var result = thousandsprefixseparate.format((value * 5001).toLong())
                        result += "тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21001L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format(value) + "-х"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalMasculineDative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулевому"
                    1L -> "первому"
                    2L -> "второму"
                    3L -> "третьому"
                    4L -> "четвертому"
                    5L -> "пятому"
                    6L -> "шестому"
                    7L -> "седьмому"
                    8L -> "восьмому"
                    9L -> "девятому"
                    10L -> "десятому"
                    11L -> "одиннадцатому"
                    12L -> "двенадцатому"
                    13L -> "тринадцатому"
                    14L -> "четырнадцатому"
                    15L -> "пятнадцатому"
                    16L -> "шестнадцатому"
                    17L -> "семнадцатому"
                    18L -> "восемнадцатому"
                    19L -> "девятнадцатому"
                    20L -> "двадцатому"
                    in 21L..29L -> "двадцать " + format(value % 10)
                    30L -> "тридцатому"
                    in 31L..39L -> "тридцать " + format(value % 10)
                    40L -> "сороковому"
                    in 41L..49L -> "сорок " + format(value % 10)
                    50L -> "пятидесятому"
                    in 51L..59L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятому"
                    in 61L..69L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятому"
                    in 71L..79L -> "семьдесят " + format(value % 10)
                    80L -> "восемьдесятому"
                    in 81L..89L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностому"
                    in 91L..99L -> "девяносто " + format(value % 10)
                    100L -> "сотому"
                    in 101L..199L -> "сто " + format(value % 100)
                    in 200L..299L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + omu.format(value % 1000)
                    in 1001L..1999L -> "одна тысяч" + omu.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячному"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячному"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысячи"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячному"
                    in 5001L..21000L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячному"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 21001L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format(value) + "-му" else {
                            throw UnsupportedOperationException("spelloutOrdinalMasculineDative only supports numbers >= 0")
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
                    300,
                    500,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    21001
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нулевому"
                    1L -> "первому"
                    2L -> "второму"
                    3L -> "третьому"
                    4L -> "четвертому"
                    5L -> "пятому"
                    6L -> "шестому"
                    7L -> "седьмому"
                    8L -> "восьмому"
                    9L -> "девятому"
                    10L -> "десятому"
                    11L -> "одиннадцатому"
                    12L -> "двенадцатому"
                    13L -> "тринадцатому"
                    14L -> "четырнадцатому"
                    15L -> "пятнадцатому"
                    16L -> "шестнадцатому"
                    17L -> "семнадцатому"
                    18L -> "восемнадцатому"
                    19L -> "девятнадцатому"
                    20L -> "двадцатому"
                    21L -> "двадцать " + format(value % 10)
                    30L -> "тридцатому"
                    31L -> "тридцать " + format(value % 10)
                    40L -> "сороковому"
                    41L -> "сорок " + format(value % 10)
                    50L -> "пятидесятому"
                    51L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятому"
                    61L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятому"
                    71L -> "семьдесят " + format(value % 10)
                    80L -> "восемьдесятому"
                    81L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностому"
                    91L -> "девяносто " + format(value % 10)
                    100L -> "сотому"
                    101L -> "сто " + format(value % 100)
                    200L -> {
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + omu.format(value % 1000)
                    1001L -> "одна тысяч" + omu.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value * 2000).toLong()) + "тысячному"
                    2001L -> {
                        var result = thousandsprefixseparate.format((value * 2001).toLong())
                        result += "тысячи"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value * 5000).toLong()) + "тысячному"
                    5001L -> {
                        var result = thousandsprefixseparate.format((value * 5001).toLong())
                        result += "тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21001L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format(value) + "-му"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalNeuterDative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalMasculineDative.format(value) else {
                    throw UnsupportedOperationException("spelloutOrdinalNeuterDative only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutOrdinalMasculineDative.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalMasculineDative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalFeminineDative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalFeminineGenitive.format(value) else {
                    throw UnsupportedOperationException("spelloutOrdinalFeminineDative only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutOrdinalFeminineGenitive.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalFeminineGenitive.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalPluralDative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalMasculineAblative.format(value) else {
                    throw UnsupportedOperationException("spelloutOrdinalPluralDative only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutOrdinalMasculineAblative.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalMasculineAblative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalMasculineAccusative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalMasculine.format(value) else {
                    throw UnsupportedOperationException("spelloutOrdinalMasculineAccusative only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutOrdinalMasculine.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalMasculine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalNeuterAccusative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalNeuter.format(value) else {
                    throw UnsupportedOperationException("spelloutOrdinalNeuterAccusative only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutOrdinalNeuter.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalNeuter.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalFeminineAccusative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулевую"
                    1L -> "первую"
                    2L -> "вторую"
                    3L -> "третью"
                    4L -> "четвертую"
                    5L -> "пятую"
                    6L -> "шестую"
                    7L -> "седьмую"
                    8L -> "восьмую"
                    9L -> "девятую"
                    10L -> "десятую"
                    11L -> "одиннадцатую"
                    12L -> "двенадцатую"
                    13L -> "тринадцатую"
                    14L -> "четырнадцатую"
                    15L -> "пятнадцатую"
                    16L -> "шестнадцатую"
                    17L -> "семнадцатую"
                    18L -> "восемнадцатую"
                    19L -> "девятнадцатую"
                    20L -> "двадцатую"
                    in 21L..29L -> "двадцать " + format(value % 10)
                    30L -> "тридцатую"
                    in 31L..39L -> "тридцать " + format(value % 10)
                    40L -> "сороковую"
                    in 41L..49L -> "сорок " + format(value % 10)
                    50L -> "пятидесятую"
                    in 51L..59L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятую"
                    in 61L..69L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятую"
                    in 71L..79L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятую"
                    in 81L..89L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностую"
                    in 91L..99L -> "девяносто " + format(value % 10)
                    100L -> "сотую"
                    in 101L..199L -> "сто " + format(value % 100)
                    in 200L..299L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + uju.format(value % 1000)
                    in 1001L..1999L -> "одна тысяч" + uju.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячную"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячную"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысячи"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячную"
                    in 5001L..21000L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячную"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 21001L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format(value) + "-ю" else {
                            throw UnsupportedOperationException("spelloutOrdinalFeminineAccusative only supports numbers >= 0")
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
                    300,
                    500,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    21001
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нулевую"
                    1L -> "первую"
                    2L -> "вторую"
                    3L -> "третью"
                    4L -> "четвертую"
                    5L -> "пятую"
                    6L -> "шестую"
                    7L -> "седьмую"
                    8L -> "восьмую"
                    9L -> "девятую"
                    10L -> "десятую"
                    11L -> "одиннадцатую"
                    12L -> "двенадцатую"
                    13L -> "тринадцатую"
                    14L -> "четырнадцатую"
                    15L -> "пятнадцатую"
                    16L -> "шестнадцатую"
                    17L -> "семнадцатую"
                    18L -> "восемнадцатую"
                    19L -> "девятнадцатую"
                    20L -> "двадцатую"
                    21L -> "двадцать " + format(value % 10)
                    30L -> "тридцатую"
                    31L -> "тридцать " + format(value % 10)
                    40L -> "сороковую"
                    41L -> "сорок " + format(value % 10)
                    50L -> "пятидесятую"
                    51L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятую"
                    61L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятую"
                    71L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятую"
                    81L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностую"
                    91L -> "девяносто " + format(value % 10)
                    100L -> "сотую"
                    101L -> "сто " + format(value % 100)
                    200L -> {
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + uju.format(value % 1000)
                    1001L -> "одна тысяч" + uju.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value * 2000).toLong()) + "тысячную"
                    2001L -> {
                        var result = thousandsprefixseparate.format((value * 2001).toLong())
                        result += "тысячи"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value * 5000).toLong()) + "тысячную"
                    5001L -> {
                        var result = thousandsprefixseparate.format((value * 5001).toLong())
                        result += "тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21001L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format(value) + "-ю"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalPluralAccusative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalPlural.format(value) else {
                    throw UnsupportedOperationException("spelloutOrdinalPluralAccusative only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutOrdinalPlural.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalPlural.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalMasculineLocative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулевом"
                    1L -> "первом"
                    2L -> "втором"
                    3L -> "третьем"
                    4L -> "четвертом"
                    5L -> "пятом"
                    6L -> "шестом"
                    7L -> "седьмом"
                    8L -> "восьмом"
                    9L -> "девятом"
                    10L -> "десятом"
                    11L -> "одиннадцатом"
                    12L -> "двенадцатом"
                    13L -> "тринадцатом"
                    14L -> "четырнадцатом"
                    15L -> "пятнадцатом"
                    16L -> "шестнадцатом"
                    17L -> "семнадцатом"
                    18L -> "восемнадцатом"
                    19L -> "девятнадцатом"
                    20L -> "двадцатом"
                    in 21L..29L -> "двадцать " + format(value % 10)
                    30L -> "тридцатом"
                    in 31L..39L -> "тридцать " + format(value % 10)
                    40L -> "сороковой"
                    in 41L..49L -> "сорок " + format(value % 10)
                    50L -> "пятидесятом"
                    in 51L..59L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятом"
                    in 61L..69L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятом"
                    in 71L..79L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятом"
                    in 81L..89L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностом"
                    in 91L..99L -> "девяносто " + format(value % 10)
                    100L -> "сотом"
                    in 101L..199L -> "сто " + format(value % 100)
                    in 200L..299L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + om.format(value % 1000)
                    in 1001L..1999L -> "одна тысяч" + om.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячном"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячном"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысячи"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячном"
                    in 5001L..21000L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячном"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 21001L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format(value) + "-м" else {
                            throw UnsupportedOperationException("spelloutOrdinalMasculineLocative only supports numbers >= 0")
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
                    300,
                    500,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    21001
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нулевом"
                    1L -> "первом"
                    2L -> "втором"
                    3L -> "третьем"
                    4L -> "четвертом"
                    5L -> "пятом"
                    6L -> "шестом"
                    7L -> "седьмом"
                    8L -> "восьмом"
                    9L -> "девятом"
                    10L -> "десятом"
                    11L -> "одиннадцатом"
                    12L -> "двенадцатом"
                    13L -> "тринадцатом"
                    14L -> "четырнадцатом"
                    15L -> "пятнадцатом"
                    16L -> "шестнадцатом"
                    17L -> "семнадцатом"
                    18L -> "восемнадцатом"
                    19L -> "девятнадцатом"
                    20L -> "двадцатом"
                    21L -> "двадцать " + format(value % 10)
                    30L -> "тридцатом"
                    31L -> "тридцать " + format(value % 10)
                    40L -> "сороковой"
                    41L -> "сорок " + format(value % 10)
                    50L -> "пятидесятом"
                    51L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятом"
                    61L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятом"
                    71L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятом"
                    81L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностом"
                    91L -> "девяносто " + format(value % 10)
                    100L -> "сотом"
                    101L -> "сто " + format(value % 100)
                    200L -> {
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + om.format(value % 1000)
                    1001L -> "одна тысяч" + om.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value * 2000).toLong()) + "тысячном"
                    2001L -> {
                        var result = thousandsprefixseparate.format((value * 2001).toLong())
                        result += "тысячи"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value * 5000).toLong()) + "тысячном"
                    5001L -> {
                        var result = thousandsprefixseparate.format((value * 5001).toLong())
                        result += "тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21001L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format(value) + "-м"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalNeuterLocative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalMasculineLocative.format(value) else {
                    throw UnsupportedOperationException("spelloutOrdinalNeuterLocative only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutOrdinalMasculineLocative.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalMasculineLocative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalFeminineLocative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalFeminineGenitive.format(value) else {
                    throw UnsupportedOperationException("spelloutOrdinalFeminineLocative only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutOrdinalFeminineGenitive.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalFeminineGenitive.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalPluralLocative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalPluralGenitive.format(value) else {
                    throw UnsupportedOperationException("spelloutOrdinalPluralLocative only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutOrdinalPluralGenitive.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalPluralGenitive.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalMasculineAblative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулевым"
                    1L -> "первым"
                    2L -> "вторым"
                    3L -> "третьим"
                    4L -> "четвертым"
                    5L -> "пятым"
                    6L -> "шестым"
                    7L -> "седьмым"
                    8L -> "восьмым"
                    9L -> "девятым"
                    10L -> "десятым"
                    11L -> "одиннадцатым"
                    12L -> "двенадцатым"
                    13L -> "тринадцатым"
                    14L -> "четырнадцатым"
                    15L -> "пятнадцатым"
                    16L -> "шестнадцатым"
                    17L -> "семнадцатым"
                    18L -> "восемнадцатым"
                    19L -> "девятнадцатым"
                    20L -> "двадцатым"
                    in 21L..29L -> "двадцать " + format(value % 10)
                    30L -> "тридцатым"
                    in 31L..39L -> "тридцать " + format(value % 10)
                    40L -> "сороковым"
                    in 41L..49L -> "сорок " + format(value % 10)
                    50L -> "пятидесятым"
                    in 51L..59L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятым"
                    in 61L..69L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятым"
                    in 71L..79L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятым"
                    in 81L..89L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностым"
                    in 91L..99L -> "девяносто " + format(value % 10)
                    100L -> "сотым"
                    in 101L..199L -> "сто " + format(value % 100)
                    in 200L..299L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + ym.format(value % 1000)
                    in 1001L..1999L -> "одна тысяч" + ym.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячным"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячным"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысячи"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячным"
                    in 5001L..21000L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячным"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 21001L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format(value) + "-м" else {
                            throw UnsupportedOperationException("spelloutOrdinalMasculineAblative only supports numbers >= 0")
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
                    300,
                    500,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    21001
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нулевым"
                    1L -> "первым"
                    2L -> "вторым"
                    3L -> "третьим"
                    4L -> "четвертым"
                    5L -> "пятым"
                    6L -> "шестым"
                    7L -> "седьмым"
                    8L -> "восьмым"
                    9L -> "девятым"
                    10L -> "десятым"
                    11L -> "одиннадцатым"
                    12L -> "двенадцатым"
                    13L -> "тринадцатым"
                    14L -> "четырнадцатым"
                    15L -> "пятнадцатым"
                    16L -> "шестнадцатым"
                    17L -> "семнадцатым"
                    18L -> "восемнадцатым"
                    19L -> "девятнадцатым"
                    20L -> "двадцатым"
                    21L -> "двадцать " + format(value % 10)
                    30L -> "тридцатым"
                    31L -> "тридцать " + format(value % 10)
                    40L -> "сороковым"
                    41L -> "сорок " + format(value % 10)
                    50L -> "пятидесятым"
                    51L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятым"
                    61L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятым"
                    71L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятым"
                    81L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностым"
                    91L -> "девяносто " + format(value % 10)
                    100L -> "сотым"
                    101L -> "сто " + format(value % 100)
                    200L -> {
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + ym.format(value % 1000)
                    1001L -> "одна тысяч" + ym.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value * 2000).toLong()) + "тысячным"
                    2001L -> {
                        var result = thousandsprefixseparate.format((value * 2001).toLong())
                        result += "тысячи"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value * 5000).toLong()) + "тысячным"
                    5001L -> {
                        var result = thousandsprefixseparate.format((value * 5001).toLong())
                        result += "тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21001L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format(value) + "-м"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutOrdinalNeuterAblative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalMasculineAblative.format(value) else {
                    throw UnsupportedOperationException("spelloutOrdinalNeuterAblative only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutOrdinalMasculineAblative.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalMasculineAblative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalFeminineAblative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutOrdinalFeminineGenitive.format(value) else {
                    throw UnsupportedOperationException("spelloutOrdinalFeminineAblative only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutOrdinalFeminineGenitive.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutOrdinalFeminineGenitive.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinalPluralAblative : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "минус " + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "нулевыми"
                    1L -> "первыми"
                    2L -> "вторыми"
                    3L -> "третьими"
                    4L -> "четвертыми"
                    5L -> "пятыми"
                    6L -> "шестыми"
                    7L -> "седьмыми"
                    8L -> "восьмыми"
                    9L -> "девятыми"
                    10L -> "десятыми"
                    11L -> "одиннадцатыми"
                    12L -> "двенадцатыми"
                    13L -> "тринадцатыми"
                    14L -> "четырнадцатыми"
                    15L -> "пятнадцатыми"
                    16L -> "шестнадцатыми"
                    17L -> "семнадцатыми"
                    18L -> "восемнадцатыми"
                    19L -> "девятнадцатыми"
                    20L -> "двадцатыми"
                    in 21L..29L -> "двадцать " + format(value % 10)
                    30L -> "тридцатыми"
                    in 31L..39L -> "тридцать " + format(value % 10)
                    40L -> "сороковой"
                    in 41L..49L -> "сорок " + format(value % 10)
                    50L -> "пятидесятыми"
                    in 51L..59L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятыми"
                    in 61L..69L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятыми"
                    in 71L..79L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятыми"
                    in 81L..89L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностыми"
                    in 91L..99L -> "девяносто " + format(value % 10)
                    100L -> "сотыми"
                    in 101L..199L -> "сто " + format(value % 100)
                    in 200L..299L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сти"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "ста"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "сот"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + ymi.format(value % 1000)
                    in 1001L..1999L -> "одна тысяч" + ymi.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячными"
                    in 2001L..4999L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячными"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысячи"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value) / 1000) + "тысячными"
                    in 5001L..21000L -> if ((value) % 1000 == 0L) {
                        thousandsprefixconjoined.format((value) / 1000) + "тысячными"
                    } else {
                        var result = thousandsprefixseparate.format((value) / 1000)
                        result += "тысяч"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 21001L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format(value) + "-ми" else {
                            throw UnsupportedOperationException("spelloutOrdinalPluralAblative only supports numbers >= 0")
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
                    300,
                    500,
                    1000,
                    1001,
                    2000,
                    2001,
                    5000,
                    5001,
                    21001
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "нулевыми"
                    1L -> "первыми"
                    2L -> "вторыми"
                    3L -> "третьими"
                    4L -> "четвертыми"
                    5L -> "пятыми"
                    6L -> "шестыми"
                    7L -> "седьмыми"
                    8L -> "восьмыми"
                    9L -> "девятыми"
                    10L -> "десятыми"
                    11L -> "одиннадцатыми"
                    12L -> "двенадцатыми"
                    13L -> "тринадцатыми"
                    14L -> "четырнадцатыми"
                    15L -> "пятнадцатыми"
                    16L -> "шестнадцатыми"
                    17L -> "семнадцатыми"
                    18L -> "восемнадцатыми"
                    19L -> "девятнадцатыми"
                    20L -> "двадцатыми"
                    21L -> "двадцать " + format(value % 10)
                    30L -> "тридцатыми"
                    31L -> "тридцать " + format(value % 10)
                    40L -> "сороковой"
                    41L -> "сорок " + format(value % 10)
                    50L -> "пятидесятыми"
                    51L -> "пятьдесят " + format(value % 10)
                    60L -> "шестидесятыми"
                    61L -> "шестьдесят " + format(value % 10)
                    70L -> "семидесятыми"
                    71L -> "семьдесят " + format(value % 10)
                    80L -> "восьмидесятыми"
                    81L -> "восемьдесят " + format(value % 10)
                    90L -> "девяностыми"
                    91L -> "девяносто " + format(value % 10)
                    100L -> "сотыми"
                    101L -> "сто " + format(value % 100)
                    200L -> {
                        var result = spelloutCardinalFeminine.format((value * 200).toLong())
                        result += "сти"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = spelloutCardinalFeminine.format((value * 300).toLong())
                        result += "ста"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = spelloutCardinalFeminine.format((value * 500).toLong())
                        result += "сот"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    1000L -> "тысяч" + ymi.format(value % 1000)
                    1001L -> "одна тысяч" + ymi.format(value % 1000)
                    2000L -> thousandsprefixconjoined.format((value * 2000).toLong()) + "тысячными"
                    2001L -> {
                        var result = thousandsprefixseparate.format((value * 2001).toLong())
                        result += "тысячи"
                        result += (if (value * 2001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    5000L -> thousandsprefixconjoined.format((value * 5000).toLong()) + "тысячными"
                    5001L -> {
                        var result = thousandsprefixseparate.format((value * 5001).toLong())
                        result += "тысяч"
                        result += (if (value * 5001 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    21001L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format(value) + "-ми"

                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

    }

    private val spelloutRules = SpelloutRules()

    private inner class OrdinalRules {
        val digitsOrdinal = DigitsOrdinal()

        val digitsOrdinalMasculine = DigitsOrdinalMasculine()

        val digitsOrdinalNeuter = DigitsOrdinalNeuter()

        val digitsOrdinalFeminine = DigitsOrdinalFeminine()

        val digitsOrdinalPlural = DigitsOrdinalPlural()

        val digitsOrdinalMasculineGenitive = DigitsOrdinalMasculineGenitive()

        val digitsOrdinalNeuterGenitive = DigitsOrdinalNeuterGenitive()

        val digitsOrdinalFeminineGenitive = DigitsOrdinalFeminineGenitive()

        val digitsOrdinalPluralGenitive = DigitsOrdinalPluralGenitive()

        val digitsOrdinalMasculineDative = DigitsOrdinalMasculineDative()

        val digitsOrdinalNeuterDative = DigitsOrdinalNeuterDative()

        val digitsOrdinalFeminineDative = DigitsOrdinalFeminineDative()

        val digitsOrdinalPluralDative = DigitsOrdinalPluralDative()

        val digitsOrdinalMasculineAccusative = DigitsOrdinalMasculineAccusative()

        val digitsOrdinalNeuterAccusative = DigitsOrdinalNeuterAccusative()

        val digitsOrdinalFeminineAccusative = DigitsOrdinalFeminineAccusative()

        val digitsOrdinalPluralAccusative = DigitsOrdinalPluralAccusative()

        val digitsOrdinalMasculineLocative = DigitsOrdinalMasculineLocative()

        val digitsOrdinalNeuterLocative = DigitsOrdinalNeuterLocative()

        val digitsOrdinalFeminineLocative = DigitsOrdinalFeminineLocative()

        val digitsOrdinalPluralLocative = DigitsOrdinalPluralLocative()

        val digitsOrdinalMasculineAblative = DigitsOrdinalMasculineAblative()

        val digitsOrdinalNeuterAblative = DigitsOrdinalNeuterAblative()

        val digitsOrdinalFeminineAblative = DigitsOrdinalFeminineAblative()

        val digitsOrdinalPluralAblative = DigitsOrdinalPluralAblative()

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
                ).format(value) else {
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
                ).format(value) else {
                    throw UnsupportedOperationException("digitsOrdinal only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
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
                ).format(value) + "-й" else {
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
                ).format(value) + "-й" else {
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
                    ).format(value) + "-й"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalNeuter : NumberFormatter {
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
                ).format(value) + "-е" else {
                    throw UnsupportedOperationException("digitsOrdinalNeuter only supports numbers >= 0")
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
                ).format(value) + "-е" else {
                    throw UnsupportedOperationException("digitsOrdinalNeuter only supports numbers >= 0")
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
                    ).format(value) + "-е"

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
                ).format(value) + "-я" else {
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
                ).format(value) + "-я" else {
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
                    ).format(value) + "-я"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalPlural : NumberFormatter {
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
                ).format(value) + "-e" else {
                    throw UnsupportedOperationException("digitsOrdinalPlural only supports numbers >= 0")
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
                ).format(value) + "-e" else {
                    throw UnsupportedOperationException("digitsOrdinalPlural only supports numbers >= 0")
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
                    ).format(value) + "-e"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalMasculineGenitive : NumberFormatter {
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
                ).format(value) + "-го" else {
                    throw UnsupportedOperationException("digitsOrdinalMasculineGenitive only supports numbers >= 0")
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
                ).format(value) + "-го" else {
                    throw UnsupportedOperationException("digitsOrdinalMasculineGenitive only supports numbers >= 0")
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
                    ).format(value) + "-го"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalNeuterGenitive : NumberFormatter {
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
                ).format(value) + "-го" else {
                    throw UnsupportedOperationException("digitsOrdinalNeuterGenitive only supports numbers >= 0")
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
                ).format(value) + "-го" else {
                    throw UnsupportedOperationException("digitsOrdinalNeuterGenitive only supports numbers >= 0")
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
                    ).format(value) + "-го"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalFeminineGenitive : NumberFormatter {
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
                ).format(value) + "-й" else {
                    throw UnsupportedOperationException("digitsOrdinalFeminineGenitive only supports numbers >= 0")
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
                ).format(value) + "-й" else {
                    throw UnsupportedOperationException("digitsOrdinalFeminineGenitive only supports numbers >= 0")
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
                    ).format(value) + "-й"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalPluralGenitive : NumberFormatter {
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
                ).format(value) + "-х" else {
                    throw UnsupportedOperationException("digitsOrdinalPluralGenitive only supports numbers >= 0")
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
                ).format(value) + "-х" else {
                    throw UnsupportedOperationException("digitsOrdinalPluralGenitive only supports numbers >= 0")
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
                    ).format(value) + "-х"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalMasculineDative : NumberFormatter {
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
                ).format(value) + "-му" else {
                    throw UnsupportedOperationException("digitsOrdinalMasculineDative only supports numbers >= 0")
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
                ).format(value) + "-му" else {
                    throw UnsupportedOperationException("digitsOrdinalMasculineDative only supports numbers >= 0")
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
                    ).format(value) + "-му"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalNeuterDative : NumberFormatter {
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
                ).format(value) + "-му" else {
                    throw UnsupportedOperationException("digitsOrdinalNeuterDative only supports numbers >= 0")
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
                ).format(value) + "-му" else {
                    throw UnsupportedOperationException("digitsOrdinalNeuterDative only supports numbers >= 0")
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
                    ).format(value) + "-му"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalFeminineDative : NumberFormatter {
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
                ).format(value) + "-й" else {
                    throw UnsupportedOperationException("digitsOrdinalFeminineDative only supports numbers >= 0")
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
                ).format(value) + "-й" else {
                    throw UnsupportedOperationException("digitsOrdinalFeminineDative only supports numbers >= 0")
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
                    ).format(value) + "-й"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalPluralDative : NumberFormatter {
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
                ).format(value) + "-м" else {
                    throw UnsupportedOperationException("digitsOrdinalPluralDative only supports numbers >= 0")
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
                ).format(value) + "-м" else {
                    throw UnsupportedOperationException("digitsOrdinalPluralDative only supports numbers >= 0")
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
                    ).format(value) + "-м"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalMasculineAccusative : NumberFormatter {
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
                ).format(value) + "-й" else {
                    throw UnsupportedOperationException("digitsOrdinalMasculineAccusative only supports numbers >= 0")
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
                ).format(value) + "-й" else {
                    throw UnsupportedOperationException("digitsOrdinalMasculineAccusative only supports numbers >= 0")
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
                    ).format(value) + "-й"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalNeuterAccusative : NumberFormatter {
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
                ).format(value) + "-е" else {
                    throw UnsupportedOperationException("digitsOrdinalNeuterAccusative only supports numbers >= 0")
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
                ).format(value) + "-е" else {
                    throw UnsupportedOperationException("digitsOrdinalNeuterAccusative only supports numbers >= 0")
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
                    ).format(value) + "-е"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalFeminineAccusative : NumberFormatter {
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
                ).format(value) + "-ю" else {
                    throw UnsupportedOperationException("digitsOrdinalFeminineAccusative only supports numbers >= 0")
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
                ).format(value) + "-ю" else {
                    throw UnsupportedOperationException("digitsOrdinalFeminineAccusative only supports numbers >= 0")
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
                    ).format(value) + "-ю"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalPluralAccusative : NumberFormatter {
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
                ).format(value) + "-e" else {
                    throw UnsupportedOperationException("digitsOrdinalPluralAccusative only supports numbers >= 0")
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
                ).format(value) + "-e" else {
                    throw UnsupportedOperationException("digitsOrdinalPluralAccusative only supports numbers >= 0")
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
                    ).format(value) + "-e"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalMasculineLocative : NumberFormatter {
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
                ).format(value) + "-м" else {
                    throw UnsupportedOperationException("digitsOrdinalMasculineLocative only supports numbers >= 0")
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
                ).format(value) + "-м" else {
                    throw UnsupportedOperationException("digitsOrdinalMasculineLocative only supports numbers >= 0")
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
                    ).format(value) + "-м"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalNeuterLocative : NumberFormatter {
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
                ).format(value) + "-м" else {
                    throw UnsupportedOperationException("digitsOrdinalNeuterLocative only supports numbers >= 0")
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
                ).format(value) + "-м" else {
                    throw UnsupportedOperationException("digitsOrdinalNeuterLocative only supports numbers >= 0")
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
                    ).format(value) + "-м"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalFeminineLocative : NumberFormatter {
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
                ).format(value) + "-й" else {
                    throw UnsupportedOperationException("digitsOrdinalFeminineLocative only supports numbers >= 0")
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
                ).format(value) + "-й" else {
                    throw UnsupportedOperationException("digitsOrdinalFeminineLocative only supports numbers >= 0")
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
                    ).format(value) + "-й"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalPluralLocative : NumberFormatter {
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
                ).format(value) + "-х" else {
                    throw UnsupportedOperationException("digitsOrdinalPluralLocative only supports numbers >= 0")
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
                ).format(value) + "-х" else {
                    throw UnsupportedOperationException("digitsOrdinalPluralLocative only supports numbers >= 0")
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
                    ).format(value) + "-х"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalMasculineAblative : NumberFormatter {
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
                ).format(value) + "-м" else {
                    throw UnsupportedOperationException("digitsOrdinalMasculineAblative only supports numbers >= 0")
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
                ).format(value) + "-м" else {
                    throw UnsupportedOperationException("digitsOrdinalMasculineAblative only supports numbers >= 0")
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
                    ).format(value) + "-м"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalNeuterAblative : NumberFormatter {
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
                ).format(value) + "-м" else {
                    throw UnsupportedOperationException("digitsOrdinalNeuterAblative only supports numbers >= 0")
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
                ).format(value) + "-м" else {
                    throw UnsupportedOperationException("digitsOrdinalNeuterAblative only supports numbers >= 0")
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
                    ).format(value) + "-м"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalFeminineAblative : NumberFormatter {
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
                ).format(value) + "-й" else {
                    throw UnsupportedOperationException("digitsOrdinalFeminineAblative only supports numbers >= 0")
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
                ).format(value) + "-й" else {
                    throw UnsupportedOperationException("digitsOrdinalFeminineAblative only supports numbers >= 0")
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
                    ).format(value) + "-й"

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalPluralAblative : NumberFormatter {
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
                ).format(value) + "-ми" else {
                    throw UnsupportedOperationException("digitsOrdinalPluralAblative only supports numbers >= 0")
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
                ).format(value) + "-ми" else {
                    throw UnsupportedOperationException("digitsOrdinalPluralAblative only supports numbers >= 0")
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
                    ).format(value) + "-ми"

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
    open val spelloutCardinalNeuter: NumberFormatter
        get() = spelloutRules.spelloutCardinalNeuter
    open val spelloutCardinalFeminine: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminine
    open val spelloutCardinalPlural: NumberFormatter
        get() = spelloutRules.spelloutCardinalPlural
    open val spelloutCardinalMasculineGenitive: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculineGenitive
    open val spelloutCardinalNeuterGenitive: NumberFormatter
        get() = spelloutRules.spelloutCardinalNeuterGenitive
    open val spelloutCardinalFeminineGenitive: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminineGenitive
    open val spelloutCardinalPluralGenitive: NumberFormatter
        get() = spelloutRules.spelloutCardinalPluralGenitive
    open val spelloutCardinalMasculineDative: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculineDative
    open val spelloutCardinalNeuterDative: NumberFormatter
        get() = spelloutRules.spelloutCardinalNeuterDative
    open val spelloutCardinalFeminineDative: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminineDative
    open val spelloutCardinalPluralDative: NumberFormatter
        get() = spelloutRules.spelloutCardinalPluralDative
    open val spelloutCardinalMasculineAccusative: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculineAccusative
    open val spelloutCardinalNeuterAccusative: NumberFormatter
        get() = spelloutRules.spelloutCardinalNeuterAccusative
    open val spelloutCardinalFeminineAccusative: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminineAccusative
    open val spelloutCardinalPluralAccusative: NumberFormatter
        get() = spelloutRules.spelloutCardinalPluralAccusative
    open val spelloutCardinalMasculineLocative: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculineLocative
    open val spelloutCardinalNeuterLocative: NumberFormatter
        get() = spelloutRules.spelloutCardinalNeuterLocative
    open val spelloutCardinalFeminineLocative: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminineLocative
    open val spelloutCardinalPluralLocative: NumberFormatter
        get() = spelloutRules.spelloutCardinalPluralLocative
    open val spelloutCardinalMasculineAblative: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculineAblative
    open val spelloutCardinalNeuterAblative: NumberFormatter
        get() = spelloutRules.spelloutCardinalNeuterAblative
    open val spelloutCardinalFeminineAblative: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminineAblative
    open val spelloutCardinalPluralAblative: NumberFormatter
        get() = spelloutRules.spelloutCardinalPluralAblative
    open val spelloutOrdinalMasculine: NumberFormatter
        get() = spelloutRules.spelloutOrdinalMasculine
    open val spelloutOrdinalNeuter: NumberFormatter
        get() = spelloutRules.spelloutOrdinalNeuter
    open val spelloutOrdinalFeminine: NumberFormatter
        get() = spelloutRules.spelloutOrdinalFeminine
    open val spelloutOrdinalPlural: NumberFormatter
        get() = spelloutRules.spelloutOrdinalPlural
    open val spelloutOrdinalMasculineGenitive: NumberFormatter
        get() = spelloutRules.spelloutOrdinalMasculineGenitive
    open val spelloutOrdinalNeuterGenitive: NumberFormatter
        get() = spelloutRules.spelloutOrdinalNeuterGenitive
    open val spelloutOrdinalFeminineGenitive: NumberFormatter
        get() = spelloutRules.spelloutOrdinalFeminineGenitive
    open val spelloutOrdinalPluralGenitive: NumberFormatter
        get() = spelloutRules.spelloutOrdinalPluralGenitive
    open val spelloutOrdinalMasculineDative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalMasculineDative
    open val spelloutOrdinalNeuterDative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalNeuterDative
    open val spelloutOrdinalFeminineDative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalFeminineDative
    open val spelloutOrdinalPluralDative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalPluralDative
    open val spelloutOrdinalMasculineAccusative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalMasculineAccusative
    open val spelloutOrdinalNeuterAccusative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalNeuterAccusative
    open val spelloutOrdinalFeminineAccusative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalFeminineAccusative
    open val spelloutOrdinalPluralAccusative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalPluralAccusative
    open val spelloutOrdinalMasculineLocative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalMasculineLocative
    open val spelloutOrdinalNeuterLocative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalNeuterLocative
    open val spelloutOrdinalFeminineLocative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalFeminineLocative
    open val spelloutOrdinalPluralLocative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalPluralLocative
    open val spelloutOrdinalMasculineAblative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalMasculineAblative
    open val spelloutOrdinalNeuterAblative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalNeuterAblative
    open val spelloutOrdinalFeminineAblative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalFeminineAblative
    open val spelloutOrdinalPluralAblative: NumberFormatter
        get() = spelloutRules.spelloutOrdinalPluralAblative
    open val digitsOrdinal: NumberFormatter
        get() = ordinalRules.digitsOrdinal
    open val digitsOrdinalMasculine: NumberFormatter
        get() = ordinalRules.digitsOrdinalMasculine
    open val digitsOrdinalNeuter: NumberFormatter
        get() = ordinalRules.digitsOrdinalNeuter
    open val digitsOrdinalFeminine: NumberFormatter
        get() = ordinalRules.digitsOrdinalFeminine
    open val digitsOrdinalPlural: NumberFormatter
        get() = ordinalRules.digitsOrdinalPlural
    open val digitsOrdinalMasculineGenitive: NumberFormatter
        get() = ordinalRules.digitsOrdinalMasculineGenitive
    open val digitsOrdinalNeuterGenitive: NumberFormatter
        get() = ordinalRules.digitsOrdinalNeuterGenitive
    open val digitsOrdinalFeminineGenitive: NumberFormatter
        get() = ordinalRules.digitsOrdinalFeminineGenitive
    open val digitsOrdinalPluralGenitive: NumberFormatter
        get() = ordinalRules.digitsOrdinalPluralGenitive
    open val digitsOrdinalMasculineDative: NumberFormatter
        get() = ordinalRules.digitsOrdinalMasculineDative
    open val digitsOrdinalNeuterDative: NumberFormatter
        get() = ordinalRules.digitsOrdinalNeuterDative
    open val digitsOrdinalFeminineDative: NumberFormatter
        get() = ordinalRules.digitsOrdinalFeminineDative
    open val digitsOrdinalPluralDative: NumberFormatter
        get() = ordinalRules.digitsOrdinalPluralDative
    open val digitsOrdinalMasculineAccusative: NumberFormatter
        get() = ordinalRules.digitsOrdinalMasculineAccusative
    open val digitsOrdinalNeuterAccusative: NumberFormatter
        get() = ordinalRules.digitsOrdinalNeuterAccusative
    open val digitsOrdinalFeminineAccusative: NumberFormatter
        get() = ordinalRules.digitsOrdinalFeminineAccusative
    open val digitsOrdinalPluralAccusative: NumberFormatter
        get() = ordinalRules.digitsOrdinalPluralAccusative
    open val digitsOrdinalMasculineLocative: NumberFormatter
        get() = ordinalRules.digitsOrdinalMasculineLocative
    open val digitsOrdinalNeuterLocative: NumberFormatter
        get() = ordinalRules.digitsOrdinalNeuterLocative
    open val digitsOrdinalFeminineLocative: NumberFormatter
        get() = ordinalRules.digitsOrdinalFeminineLocative
    open val digitsOrdinalPluralLocative: NumberFormatter
        get() = ordinalRules.digitsOrdinalPluralLocative
    open val digitsOrdinalMasculineAblative: NumberFormatter
        get() = ordinalRules.digitsOrdinalMasculineAblative
    open val digitsOrdinalNeuterAblative: NumberFormatter
        get() = ordinalRules.digitsOrdinalNeuterAblative
    open val digitsOrdinalFeminineAblative: NumberFormatter
        get() = ordinalRules.digitsOrdinalFeminineAblative
    open val digitsOrdinalPluralAblative: NumberFormatter
        get() = ordinalRules.digitsOrdinalPluralAblative
    override val spelloutOrdinal: NumberFormatter
        get() = TODO("spelloutOrdinal is not available for ru")
    override val spelloutCardinal: NumberFormatter
        get() = TODO("spelloutCardinal is not available for ru")

    fun spelloutCardinalFor(gender: Gender, case: Case): NumberFormatter {
        return when (gender) {
            Gender.Neuter -> when (case) {
                Case.Nominative -> spelloutCardinalNeuter
                Case.Locative -> spelloutCardinalNeuterLocative
                Case.Ablative -> spelloutCardinalNeuterAblative
                Case.Genitive -> spelloutCardinalNeuterGenitive
                Case.Dative -> spelloutCardinalNeuterDative
                Case.Accusative -> spelloutCardinalNeuterAccusative
                else -> TODO("ru does not support $case case")
            }

            Gender.Masculine -> when (case) {
                Case.Nominative -> spelloutCardinalMasculine
                Case.Locative -> spelloutCardinalMasculineLocative
                Case.Ablative -> spelloutCardinalMasculineAblative
                Case.Genitive -> spelloutCardinalMasculineGenitive
                Case.Dative -> spelloutCardinalMasculineDative
                Case.Accusative -> spelloutCardinalMasculineAccusative
                else -> TODO("ru does not support $case case")
            }

            Gender.Feminine -> when (case) {
                Case.Nominative -> spelloutCardinalFeminine
                Case.Locative -> spelloutCardinalFeminineLocative
                Case.Ablative -> spelloutCardinalFeminineAblative
                Case.Genitive -> spelloutCardinalFeminineGenitive
                Case.Dative -> spelloutCardinalFeminineDative
                Case.Accusative -> spelloutCardinalFeminineAccusative
                else -> TODO("ru does not support $case case")
            }

            else -> TODO("ru does not support $gender gender")
        }

    }

    fun spelloutOrdinalFor(gender: Gender, case: Case): NumberFormatter {
        return when (gender) {
            Gender.Neuter -> when (case) {
                Case.Nominative -> spelloutOrdinalNeuter
                Case.Locative -> spelloutOrdinalNeuterLocative
                Case.Ablative -> spelloutOrdinalNeuterAblative
                Case.Genitive -> spelloutOrdinalNeuterGenitive
                Case.Dative -> spelloutOrdinalNeuterDative
                Case.Accusative -> spelloutOrdinalNeuterAccusative
                else -> TODO("ru does not support $case case")
            }

            Gender.Masculine -> when (case) {
                Case.Nominative -> spelloutOrdinalMasculine
                Case.Locative -> spelloutOrdinalMasculineLocative
                Case.Ablative -> spelloutOrdinalMasculineAblative
                Case.Genitive -> spelloutOrdinalMasculineGenitive
                Case.Dative -> spelloutOrdinalMasculineDative
                Case.Accusative -> spelloutOrdinalMasculineAccusative
                else -> TODO("ru does not support $case case")
            }

            Gender.Feminine -> when (case) {
                Case.Nominative -> spelloutOrdinalFeminine
                Case.Locative -> spelloutOrdinalFeminineLocative
                Case.Ablative -> spelloutOrdinalFeminineAblative
                Case.Genitive -> spelloutOrdinalFeminineGenitive
                Case.Dative -> spelloutOrdinalFeminineDative
                Case.Accusative -> spelloutOrdinalFeminineAccusative
                else -> TODO("ru does not support $case case")
            }

            else -> TODO("ru does not support $gender gender")
        }

    }

    fun digitsOrdinalFor(gender: Gender, case: Case): NumberFormatter {
        return when (gender) {
            Gender.Neuter -> when (case) {
                Case.Nominative -> digitsOrdinalNeuter
                Case.Locative -> digitsOrdinalNeuterLocative
                Case.Ablative -> digitsOrdinalNeuterAblative
                Case.Genitive -> digitsOrdinalNeuterGenitive
                Case.Dative -> digitsOrdinalNeuterDative
                Case.Accusative -> digitsOrdinalNeuterAccusative
                else -> TODO("ru does not support $case case")
            }

            Gender.Feminine -> when (case) {
                Case.Nominative -> digitsOrdinalFeminine
                Case.Locative -> digitsOrdinalFeminineLocative
                Case.Ablative -> digitsOrdinalFeminineAblative
                Case.Genitive -> digitsOrdinalFeminineGenitive
                Case.Dative -> digitsOrdinalFeminineDative
                Case.Accusative -> digitsOrdinalFeminineAccusative
                else -> TODO("ru does not support $case case")
            }

            Gender.Masculine -> when (case) {
                Case.Nominative -> digitsOrdinalMasculine
                Case.Locative -> digitsOrdinalMasculineLocative
                Case.Ablative -> digitsOrdinalMasculineAblative
                Case.Genitive -> digitsOrdinalMasculineGenitive
                Case.Dative -> digitsOrdinalMasculineDative
                Case.Accusative -> digitsOrdinalMasculineAccusative
                else -> TODO("ru does not support $case case")
            }

            else -> TODO("ru does not support $gender gender")
        }

    }
}
