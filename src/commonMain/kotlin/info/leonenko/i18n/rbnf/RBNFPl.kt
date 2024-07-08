package info.leonenko.i18n.rbnf

import info.leonenko.i18n.*
import info.leonenko.i18n.languages.pl
import kotlin.math.roundToLong


open class RBNFPl(val language: Language = Language.pl) : RBNF {
    private inner class SpelloutRules {
        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumbering = SpelloutNumbering()

        val spelloutCardinalMasculine = SpelloutCardinalMasculine()

        val spelloutCardinalMasculinePersonal = SpelloutCardinalMasculinePersonal()

        val spelloutCardinalFeminine = SpelloutCardinalFeminine()

        val spelloutCardinalNeuter = SpelloutCardinalNeuter()

        val spelloutCardinalMasculineGenitive = SpelloutCardinalMasculineGenitive()

        val spelloutCardinalFeminineGenitive = SpelloutCardinalFeminineGenitive()

        val spelloutCardinalNeuterGenitive = SpelloutCardinalNeuterGenitive()

        val spelloutCardinalMasculineDative = SpelloutCardinalMasculineDative()

        val spelloutCardinalFeminineDative = SpelloutCardinalFeminineDative()

        val spelloutCardinalNeuterDative = SpelloutCardinalNeuterDative()

        val spelloutCardinalMasculineAccusative = SpelloutCardinalMasculineAccusative()

        val spelloutCardinalMasculineAccusativeAnimate = SpelloutCardinalMasculineAccusativeAnimate()

        val spelloutCardinalMasculineAccusativePersonal = SpelloutCardinalMasculineAccusativePersonal()

        val spelloutCardinalFeminineAccusative = SpelloutCardinalFeminineAccusative()

        val spelloutCardinalNeuterAccusative = SpelloutCardinalNeuterAccusative()

        val spelloutCardinalMasculineInstrumental = SpelloutCardinalMasculineInstrumental()

        val spelloutCardinalFeminineInstrumental = SpelloutCardinalFeminineInstrumental()

        val spelloutCardinalNeuterInstrumental = SpelloutCardinalNeuterInstrumental()

        val spelloutCardinalMasculineLocative = SpelloutCardinalMasculineLocative()

        val spelloutCardinalFeminineLocative = SpelloutCardinalFeminineLocative()

        val spelloutCardinalMasculineGenitiveOnes = SpelloutCardinalMasculineGenitiveOnes()

        val spelloutCardinalMasculineDativeOnes = SpelloutCardinalMasculineDativeOnes()

        val spelloutCardinalMasculineInstrumentalOnes = SpelloutCardinalMasculineInstrumentalOnes()

        val spelloutCardinalMasculineLocativeOnes = SpelloutCardinalMasculineLocativeOnes()

        val spelloutCardinalFeminineOnes = SpelloutCardinalFeminineOnes()

        val spelloutCardinalNeuterLocative = SpelloutCardinalNeuterLocative()

        val spelloutCardinalTens = SpelloutCardinalTens()

        val spelloutCardinalGenitiveTens = SpelloutCardinalGenitiveTens()

        val spelloutFraction = SpelloutFraction()

        val spelloutFractionDigits = SpelloutFractionDigits()

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
                    0L -> "zero"
                    1L -> "jeden"
                    2L -> "dwa"
                    3L -> "trzy"
                    4L -> "cztery"
                    5L -> "pięć"
                    6L -> "sześć"
                    7L -> "siedem"
                    8L -> "osiem"
                    9L -> "dziewięć"
                    10L -> "dziesięć"
                    11L -> "jedenaście"
                    12L -> "dwanaście"
                    13L -> "trzynaście"
                    14L -> "czternaście"
                    15L -> "piętnaście"
                    16L -> "szesnaście"
                    17L -> "siedemnaście"
                    18L -> "osiemnaście"
                    19L -> "dziewiętnaście"
                    in 20L..99L -> {
                        var result = spelloutCardinalTens.format((value) / 10)
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
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
                        var result = "dwieście"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "sta"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "set"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "tysiąc"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = format((value) / 1000)
                        result += " "
                        result += Plurals(few = "tysiące", other = "tysięcy").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "milion"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = format((value) / 1000000)
                        result += " "
                        result += Plurals(few = "miliony", other = "milionów").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "miliard"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = format((value) / 1000000000)
                        result += " "
                        result += Plurals(few = "miliardy", other = "miliardów").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "bilion"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " "
                        result += Plurals(few = "biliony", other = "bilionów").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "biliard"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(few = "biliardy", other = "biliardów").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + spelloutFraction.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
            }
        }

        private inner class SpelloutCardinalMasculinePersonal : NumberFormatter {
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
                    0L -> "zero"
                    1L -> "jeden"
                    2L -> "dwaj"
                    3L -> "trzej"
                    4L -> "czterej"
                    in 5L..999L -> spelloutCardinalMasculineGenitive.format(value)
                    in 1000L..1999L -> {
                        var result = "tysiąc"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " "
                        result += Plurals(few = "tysiące", other = "tysięcy").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "milion"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " "
                        result += Plurals(few = "miliony", other = "milionów").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "miliard"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " "
                        result += Plurals(few = "miliardy", other = "miliardów").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "bilion"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " "
                        result += Plurals(few = "biliony", other = "bilionów").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "biliard"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(few = "biliardy", other = "biliardów").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalMasculinePersonal only supports numbers >= 0")
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
                    0L -> "zero"
                    1L -> "jeden"
                    2L -> "dwaj"
                    3L -> "trzej"
                    4L -> "czterej"
                    5L -> spelloutCardinalMasculineGenitive.format(value)
                    1000L -> {
                        var result = "tysiąc"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000).toLong())
                        result += " "
                        result += Plurals(few = "tysiące", other = "tysięcy").get(language.pluralFormChooser.pluralForm(((value * 2000).toLong()).toString()))
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "milion"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000).toLong())
                        result += " "
                        result += Plurals(few = "miliony", other = "milionów").get(language.pluralFormChooser.pluralForm(((value * 2000000).toLong()).toString()))
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = "miliard"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000).toLong())
                        result += " "
                        result += Plurals(few = "miliardy", other = "miliardów").get(language.pluralFormChooser.pluralForm(((value * 2000000000).toLong()).toString()))
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "bilion"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000).toLong())
                        result += " "
                        result += Plurals(few = "biliony", other = "bilionów").get(language.pluralFormChooser.pluralForm(((value * 2000000000000).toLong()).toString()))
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = "biliard"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000000).toLong())
                        result += " "
                        result += Plurals(few = "biliardy", other = "biliardów").get(language.pluralFormChooser.pluralForm(((value * 2000000000000000).toLong()).toString()))
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
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
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
                    0L -> "zero"
                    1L -> "jedna"
                    2L -> "dwie"
                    3L -> "trzy"
                    4L -> "cztery"
                    5L -> "pięć"
                    6L -> "sześć"
                    7L -> "siedem"
                    8L -> "osiem"
                    9L -> "dziewięć"
                    10L -> "dziesięć"
                    11L -> "jedenaście"
                    12L -> "dwanaście"
                    13L -> "trzynaście"
                    14L -> "czternaście"
                    15L -> "piętnaście"
                    16L -> "szesnaście"
                    17L -> "siedemnaście"
                    18L -> "osiemnaście"
                    19L -> "dziewiętnaście"
                    in 20L..99L -> {
                        var result = spelloutCardinalTens.format((value) / 10)
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "sto"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "dwieście"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = format((value) / 100)
                        result += "sta"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = format((value) / 100)
                        result += "set"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "tysiąc"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " "
                        result += Plurals(few = "tysiące", other = "tysięcy").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "milion"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " "
                        result += Plurals(few = "miliony", other = "milionów").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "miliard"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " "
                        result += Plurals(few = "miliardy", other = "miliardów").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "bilion"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " "
                        result += Plurals(few = "biliony", other = "bilionów").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "biliard"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(few = "biliardy", other = "biliardów").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 1000000000000000)
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
                    100,
                    200,
                    300,
                    500,
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
                    0L -> "zero"
                    1L -> "jedna"
                    2L -> "dwie"
                    3L -> "trzy"
                    4L -> "cztery"
                    5L -> "pięć"
                    6L -> "sześć"
                    7L -> "siedem"
                    8L -> "osiem"
                    9L -> "dziewięć"
                    10L -> "dziesięć"
                    11L -> "jedenaście"
                    12L -> "dwanaście"
                    13L -> "trzynaście"
                    14L -> "czternaście"
                    15L -> "piętnaście"
                    16L -> "szesnaście"
                    17L -> "siedemnaście"
                    18L -> "osiemnaście"
                    19L -> "dziewiętnaście"
                    20L -> {
                        var result = spelloutCardinalTens.format((value * 20).toLong())
                        result += (if (value * 20 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 10)
                        })
                        result
                    }

                    100L -> {
                        var result = "sto"
                        result += (if (value * 100 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 100)
                        })
                        result
                    }

                    200L -> {
                        var result = "dwieście"
                        result += (if (value * 200 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 100)
                        })
                        result
                    }

                    300L -> {
                        var result = format((value * 300).toLong())
                        result += "sta"
                        result += (if (value * 300 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 100)
                        })
                        result
                    }

                    500L -> {
                        var result = format((value * 500).toLong())
                        result += "set"
                        result += (if (value * 500 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 100)
                        })
                        result
                    }

                    1000L -> {
                        var result = "tysiąc"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000).toLong())
                        result += " "
                        result += Plurals(few = "tysiące", other = "tysięcy").get(language.pluralFormChooser.pluralForm(((value * 2000).toLong()).toString()))
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "milion"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000).toLong())
                        result += " "
                        result += Plurals(few = "miliony", other = "milionów").get(language.pluralFormChooser.pluralForm(((value * 2000000).toLong()).toString()))
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = "miliard"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000).toLong())
                        result += " "
                        result += Plurals(few = "miliardy", other = "miliardów").get(language.pluralFormChooser.pluralForm(((value * 2000000000).toLong()).toString()))
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "bilion"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000).toLong())
                        result += " "
                        result += Plurals(few = "biliony", other = "bilionów").get(language.pluralFormChooser.pluralForm(((value * 2000000000000).toLong()).toString()))
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = "biliard"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000000).toLong())
                        result += " "
                        result += Plurals(few = "biliardy", other = "biliardów").get(language.pluralFormChooser.pluralForm(((value * 2000000000000000).toLong()).toString()))
                        result += (if (value * 2000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalFeminineOnes.format(value % 1000000000000000)
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
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + spelloutFraction.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
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
                    0L -> "zero"
                    1L -> "jedno"
                    else -> {
                        if (value >= 2L) spelloutCardinalMasculine.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalNeuter only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2).bestDenominatorOrNull(value)!!) {
                    0L -> "zero"
                    1L -> "jedno"
                    2L -> spelloutCardinalMasculine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + spelloutFraction.formatF(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)))
            }
        }

        private inner class SpelloutCardinalMasculineGenitive : NumberFormatter {
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
                    0L -> "zera"
                    1L -> "jednego"
                    2L -> "dwóch"
                    3L -> "trzech"
                    4L -> "czterech"
                    5L -> "pięciu"
                    6L -> "sześciu"
                    7L -> "siedmiu"
                    8L -> "ośmiu"
                    9L -> "dziewięciu"
                    10L -> "dziesięciu"
                    11L -> "jedenastu"
                    12L -> "dwunastu"
                    13L -> "trzynastu"
                    14L -> "czternastu"
                    15L -> "piętnastu"
                    16L -> "szesnastu"
                    17L -> "siedemnastu"
                    18L -> "osiemnastu"
                    19L -> "dziewiętnastu"
                    in 20L..99L -> {
                        var result = spelloutCardinalGenitiveTens.format((value) / 10)
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "stu"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "dwustu"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "stu"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminineGenitive.format((value) / 100)
                        result += "set"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "tysiąca"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = format((value) / 1000)
                        result += " tysięcy"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "miliona"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = format((value) / 1000000)
                        result += " milionów"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "miliarda"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = format((value) / 1000000000)
                        result += " miliardów"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "biliona"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " bilionów"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "biliarda"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += " biliardów"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000000000000000)
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

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalFeminineGenitive : NumberFormatter {
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
                    0L -> "zera"
                    1L -> "jednej"
                    else -> {
                        if (value >= 2L) spelloutCardinalMasculineGenitive.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalFeminineGenitive only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2).bestDenominatorOrNull(value)!!) {
                    0L -> "zera"
                    1L -> "jednej"
                    2L -> spelloutCardinalMasculineGenitive.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
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

        private inner class SpelloutCardinalMasculineDative : NumberFormatter {
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
                    0L -> "zeru"
                    1L -> "jednemu"
                    2L -> "dwóm"
                    3L -> "trzem"
                    4L -> "czterem"
                    5L -> "pięciu"
                    6L -> "sześciu"
                    7L -> "siedmiu"
                    8L -> "ośmiu"
                    9L -> "dziewięciu"
                    10L -> "dziesięciu"
                    11L -> "jedenastu"
                    12L -> "dwunastu"
                    13L -> "trzynastu"
                    14L -> "czternastu"
                    15L -> "piętnastu"
                    16L -> "szesnastu"
                    17L -> "siedemnastu"
                    18L -> "osiemnastu"
                    19L -> "dziewiętnastu"
                    in 20L..99L -> {
                        var result = spelloutCardinalGenitiveTens.format((value) / 10)
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "stu"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "dwustu"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "stu"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminineGenitive.format((value) / 100)
                        result += "set"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "tysiącowi"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = format((value) / 1000)
                        result += " tysiącom"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "milionowi"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = format((value) / 1000000)
                        result += " milionom"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "miliardowi"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = format((value) / 1000000000)
                        result += " miliardom"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "bilionowi"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " bilionom"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "biliardowi"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += " biliardom"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineDativeOnes.format(value % 1000000000000000)
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

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalFeminineDative : NumberFormatter {
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
                    0L -> "zeru"
                    1L -> "jednej"
                    else -> {
                        if (value >= 2L) spelloutCardinalMasculineDative.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalFeminineDative only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2).bestDenominatorOrNull(value)!!) {
                    0L -> "zeru"
                    1L -> "jednej"
                    2L -> spelloutCardinalMasculineDative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
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

        private inner class SpelloutCardinalMasculineAccusative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutCardinalMasculine.format(value) else {
                    throw UnsupportedOperationException("spelloutCardinalMasculineAccusative only supports numbers >= 0")
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

        private inner class SpelloutCardinalMasculineAccusativeAnimate : NumberFormatter {
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
                    0L -> "zero"
                    1L -> "jednego"
                    else -> {
                        if (value >= 2L) spelloutCardinalMasculine.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalMasculineAccusativeAnimate only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2).bestDenominatorOrNull(value)!!) {
                    0L -> "zero"
                    1L -> "jednego"
                    2L -> spelloutCardinalMasculine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalMasculineAccusativePersonal : NumberFormatter {
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
                    in 0L..999L -> spelloutCardinalMasculineGenitive.format(value)
                    in 1000L..1999L -> {
                        var result = "tysiąc"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000)
                        result += " "
                        result += Plurals(few = "tysiące", other = "tysięcy").get(language.pluralFormChooser.pluralForm(((value) / 1000).toString()))
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "milion"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " "
                        result += Plurals(few = "miliony", other = "milionów").get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString()))
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "miliard"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " "
                        result += Plurals(few = "miliardy", other = "miliardów").get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString()))
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "bilion"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " "
                        result += Plurals(few = "biliony", other = "bilionów").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString()))
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "biliard"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " "
                        result += Plurals(few = "biliardy", other = "biliardów").get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString()))
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
                            throw UnsupportedOperationException("spelloutCardinalMasculineAccusativePersonal only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(
                    0,
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
                    0L -> spelloutCardinalMasculineGenitive.format(value)
                    1000L -> {
                        var result = "tysiąc"
                        result += (if (value * 1000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000)
                        })
                        result
                    }

                    2000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000).toLong())
                        result += " "
                        result += Plurals(few = "tysiące", other = "tysięcy").get(language.pluralFormChooser.pluralForm(((value * 2000).toLong()).toString()))
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineGenitiveOnes.format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = "milion"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    2000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000).toLong())
                        result += " "
                        result += Plurals(few = "miliony", other = "milionów").get(language.pluralFormChooser.pluralForm(((value * 2000000).toLong()).toString()))
                        result += (if (value * 2000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = "miliard"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    2000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000).toLong())
                        result += " "
                        result += Plurals(few = "miliardy", other = "miliardów").get(language.pluralFormChooser.pluralForm(((value * 2000000000).toLong()).toString()))
                        result += (if (value * 2000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = "bilion"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    2000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000).toLong())
                        result += " "
                        result += Plurals(few = "biliony", other = "bilionów").get(language.pluralFormChooser.pluralForm(((value * 2000000000000).toLong()).toString()))
                        result += (if (value * 2000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = "biliard"
                        result += (if (value * 1000000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000000)
                        })
                        result
                    }

                    2000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 2000000000000000).toLong())
                        result += " "
                        result += Plurals(few = "biliardy", other = "biliardów").get(language.pluralFormChooser.pluralForm(((value * 2000000000000000).toLong()).toString()))
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
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalFeminineAccusative : NumberFormatter {
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
                    0L -> "zero"
                    1L -> "jedną"
                    else -> {
                        if (value >= 2L) spelloutCardinalFeminine.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalFeminineAccusative only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2).bestDenominatorOrNull(value)!!) {
                    0L -> "zero"
                    1L -> "jedną"
                    2L -> spelloutCardinalFeminine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalNeuterAccusative : NumberFormatter {
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
                    0L -> "zero"
                    1L -> "jedno"
                    else -> {
                        if (value >= 2L) spelloutCardinalMasculine.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalNeuterAccusative only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2).bestDenominatorOrNull(value)!!) {
                    0L -> "zero"
                    1L -> "jedno"
                    2L -> spelloutCardinalMasculine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalMasculineInstrumental : NumberFormatter {
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
                    0L -> "zerem"
                    1L -> "jednym"
                    2L -> "dwoma"
                    3L -> "trzema"
                    4L -> "czterema"
                    5L -> "pięcioma"
                    6L -> "sześcioma"
                    7L -> "siedmioma"
                    8L -> "ośmioma"
                    9L -> "dziewięcioma"
                    10L -> "dziesięcioma"
                    11L -> "jedenastoma"
                    12L -> "dwunastoma"
                    13L -> "trzynastoma"
                    14L -> "czternastoma"
                    15L -> "piętnastoma"
                    16L -> "szesnastoma"
                    17L -> "siedemnastoma"
                    18L -> "osiemnastoma"
                    19L -> "dziewiętnastoma"
                    in 20L..29L -> {
                        var result = "dwudziestoma"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "trzydziestoma"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "czterdziestoma"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "pięćdziesięcioma"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "sześćdziesięcioma"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "siedemdziesięcioma"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "osiemdziesięcioma"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "dziewięćdziesięcioma"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "stu"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "dwustu"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "stu"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminineGenitive.format((value) / 100)
                        result += "set"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "tysiącem"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = format((value) / 1000)
                        result += " tysiącami"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "milionem"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = format((value) / 1000000)
                        result += " milionami"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "miliardem"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = format((value) / 1000000000)
                        result += " miliardami"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "bilionem"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " bilionami"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "biliardem"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += " biliardami"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineInstrumentalOnes.format(value % 1000000000000000)
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
                            throw UnsupportedOperationException("spelloutCardinalMasculineInstrumental only supports numbers >= 0")
                        }
                    }
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalFeminineInstrumental : NumberFormatter {
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
                    0L -> "zerem"
                    1L -> "jedną"
                    2L -> "dwiema"
                    else -> {
                        if (value >= 3L) spelloutCardinalMasculineInstrumental.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalFeminineInstrumental only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 3).bestDenominatorOrNull(value)!!) {
                    0L -> "zerem"
                    1L -> "jedną"
                    2L -> "dwiema"
                    3L -> spelloutCardinalMasculineInstrumental.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalNeuterInstrumental : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutCardinalMasculineInstrumental.format(value) else {
                    throw UnsupportedOperationException("spelloutCardinalNeuterInstrumental only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutCardinalMasculineInstrumental.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutCardinalMasculineInstrumental.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalMasculineLocative : NumberFormatter {
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
                    0L -> "zerze"
                    1L -> "jednym"
                    2L -> "dwóch"
                    3L -> "trzech"
                    4L -> "czterech"
                    5L -> "pięciu"
                    6L -> "sześciu"
                    7L -> "siedmiu"
                    8L -> "ośmiu"
                    9L -> "dziewięciu"
                    10L -> "dziesięciu"
                    11L -> "jedenastu"
                    12L -> "dwunastu"
                    13L -> "trzynastu"
                    14L -> "czternastu"
                    15L -> "piętnastu"
                    16L -> "szesnastu"
                    17L -> "siedemnastu"
                    18L -> "osiemnastu"
                    19L -> "dziewiętnastu"
                    in 20L..99L -> {
                        var result = spelloutCardinalGenitiveTens.format((value) / 10)
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 10)
                        })
                        result
                    }

                    in 100L..199L -> {
                        var result = "stu"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 100)
                        })
                        result
                    }

                    in 200L..299L -> {
                        var result = "dwustu"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 100)
                        })
                        result
                    }

                    in 300L..499L -> {
                        var result = spelloutCardinalFeminine.format((value) / 100)
                        result += "stu"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 100)
                        })
                        result
                    }

                    in 500L..999L -> {
                        var result = spelloutCardinalFeminineGenitive.format((value) / 100)
                        result += "set"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> {
                        var result = "tysiącu"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 1000)
                        })
                        result
                    }

                    in 2000L..999999L -> {
                        var result = format((value) / 1000)
                        result += " tysiącach"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..1999999L -> {
                        var result = "milionie"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 1000000)
                        })
                        result
                    }

                    in 2000000L..999999999L -> {
                        var result = format((value) / 1000000)
                        result += " milionach"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..1999999999L -> {
                        var result = "miliardzie"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 1000000000)
                        })
                        result
                    }

                    in 2000000000L..999999999999L -> {
                        var result = format((value) / 1000000000)
                        result += " miliardach"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..1999999999999L -> {
                        var result = "bilionie"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 1000000000000)
                        })
                        result
                    }

                    in 2000000000000L..999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += " bilionach"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..1999999999999999L -> {
                        var result = "biliardzie"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 1000000000000000)
                        })
                        result
                    }

                    in 2000000000000000L..999999999999999999L -> {
                        var result = format((value) / 1000000000000000)
                        result += " biliardach"
                        result += (if ((value) % 1000000000000000 == 0L) {
                            ""
                        } else {
                            " " + spelloutCardinalMasculineLocativeOnes.format(value % 1000000000000000)
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

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalFeminineLocative : NumberFormatter {
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
                    0L -> "zerze"
                    1L -> "jednej"
                    else -> {
                        if (value >= 2L) spelloutCardinalMasculineLocative.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalFeminineLocative only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2).bestDenominatorOrNull(value)!!) {
                    0L -> "zerze"
                    1L -> "jednej"
                    2L -> spelloutCardinalMasculineLocative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " przecinek " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalMasculineGenitiveOnes : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    1L -> "jeden"
                    else -> {
                        if (value >= 2.0) spelloutCardinalMasculineGenitive.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalMasculineGenitiveOnes only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    1L -> "jeden"
                    else -> {
                        if (value >= 2L) spelloutCardinalMasculineGenitive.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalMasculineGenitiveOnes only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 2).bestDenominatorOrNull(value)!!) {
                    1L -> "jeden"
                    2L -> spelloutCardinalMasculineGenitive.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalMasculineDativeOnes : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    1L -> "jeden"
                    else -> {
                        if (value >= 2.0) spelloutCardinalMasculineDative.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalMasculineDativeOnes only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    1L -> "jeden"
                    else -> {
                        if (value >= 2L) spelloutCardinalMasculineDative.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalMasculineDativeOnes only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 2).bestDenominatorOrNull(value)!!) {
                    1L -> "jeden"
                    2L -> spelloutCardinalMasculineDative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalMasculineInstrumentalOnes : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    1L -> "jeden"
                    else -> {
                        if (value >= 2.0) spelloutCardinalMasculineInstrumental.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalMasculineInstrumentalOnes only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    1L -> "jeden"
                    else -> {
                        if (value >= 2L) spelloutCardinalMasculineInstrumental.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalMasculineInstrumentalOnes only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 2).bestDenominatorOrNull(value)!!) {
                    1L -> "jeden"
                    2L -> spelloutCardinalMasculineInstrumental.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalMasculineLocativeOnes : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    1L -> "jeden"
                    else -> {
                        if (value >= 2.0) spelloutCardinalMasculineLocative.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalMasculineLocativeOnes only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    1L -> "jeden"
                    else -> {
                        if (value >= 2L) spelloutCardinalMasculineLocative.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalMasculineLocativeOnes only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 2).bestDenominatorOrNull(value)!!) {
                    1L -> "jeden"
                    2L -> spelloutCardinalMasculineLocative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalFeminineOnes : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    1L -> "jeden"
                    else -> {
                        if (value >= 2.0) spelloutCardinalFeminine.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalFeminineOnes only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    1L -> "jeden"
                    else -> {
                        if (value >= 2L) spelloutCardinalFeminine.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalFeminineOnes only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 2).bestDenominatorOrNull(value)!!) {
                    1L -> "jeden"
                    2L -> spelloutCardinalFeminine.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalNeuterLocative : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) spelloutCardinalMasculineLocative.format(value) else {
                    throw UnsupportedOperationException("spelloutCardinalNeuterLocative only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return spelloutCardinalMasculineLocative.format(value)
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutCardinalMasculineLocative.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalTens : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    1L -> "dziesięć"
                    2L -> "dwadzieścia"
                    3L -> "trzydzieści"
                    4L -> "czterdzieści"
                    5L -> "pięćdziesiąt"
                    6L -> "sześćdziesiąt"
                    7L -> "siedemdziesiąt"
                    8L -> "osiemdziesiąt"
                    else -> {
                        if (value >= 9.0) "dziewięćdziesiąt" else {
                            throw UnsupportedOperationException("spelloutCardinalTens only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    1L -> "dziesięć"
                    2L -> "dwadzieścia"
                    3L -> "trzydzieści"
                    4L -> "czterdzieści"
                    5L -> "pięćdziesiąt"
                    6L -> "sześćdziesiąt"
                    7L -> "siedemdziesiąt"
                    8L -> "osiemdziesiąt"
                    else -> {
                        if (value >= 9L) "dziewięćdziesiąt" else {
                            throw UnsupportedOperationException("spelloutCardinalTens only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 2, 3, 4, 5, 6, 7, 8, 9).bestDenominatorOrNull(value)!!) {
                    1L -> "dziesięć"
                    2L -> "dwadzieścia"
                    3L -> "trzydzieści"
                    4L -> "czterdzieści"
                    5L -> "pięćdziesiąt"
                    6L -> "sześćdziesiąt"
                    7L -> "siedemdziesiąt"
                    8L -> "osiemdziesiąt"
                    9L -> "dziewięćdziesiąt"
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalGenitiveTens : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    1L -> "dziesięciu"
                    2L -> "dwudziestu"
                    3L -> "trzydziestu"
                    4L -> "czterdziestu"
                    5L -> "pięćdziesięciu"
                    6L -> "sześćdziesięciu"
                    7L -> "siedemdziesięciu"
                    8L -> "osiemdziesięciu"
                    else -> {
                        if (value >= 9.0) "dziewięćdziesięciu" else {
                            throw UnsupportedOperationException("spelloutCardinalGenitiveTens only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    1L -> "dziesięciu"
                    2L -> "dwudziestu"
                    3L -> "trzydziestu"
                    4L -> "czterdziestu"
                    5L -> "pięćdziesięciu"
                    6L -> "sześćdziesięciu"
                    7L -> "siedemdziesięciu"
                    8L -> "osiemdziesięciu"
                    else -> {
                        if (value >= 9L) "dziewięćdziesięciu" else {
                            throw UnsupportedOperationException("spelloutCardinalGenitiveTens only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 2, 3, 4, 5, 6, 7, 8, 9).bestDenominatorOrNull(value)!!) {
                    1L -> "dziesięciu"
                    2L -> "dwudziestu"
                    3L -> "trzydziestu"
                    4L -> "czterdziestu"
                    5L -> "pięćdziesięciu"
                    6L -> "sześćdziesięciu"
                    7L -> "siedemdziesięciu"
                    8L -> "osiemdziesięciu"
                    9L -> "dziewięćdziesięciu"
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutFraction : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 10L..99L -> (List(value.leadingFractionalZeroesCount()) { spelloutCardinalMasculine.format(0) } + listOf(spelloutCardinalMasculine.format((value * 10).toLong()))).joinToString(" ")
                    in 100L..999L -> (List(value.leadingFractionalZeroesCount()) { spelloutCardinalMasculine.format(0) } + listOf(spelloutCardinalMasculine.format((value * 100).toLong()))).joinToString(
                        " "
                    )

                    in 1000L..9999L -> (List(value.leadingFractionalZeroesCount()) { spelloutCardinalMasculine.format(0) } + listOf(spelloutCardinalMasculine.format((value * 1000).toLong()))).joinToString(
                        " "
                    )

                    in 10000L..99999L -> (List(value.leadingFractionalZeroesCount()) { spelloutFractionDigits.format(0) } + listOf(spelloutFractionDigits.format((value * 10000).toLong()))).joinToString(
                        " "
                    )

                    in 100000L..999999L -> (List(value.leadingFractionalZeroesCount()) { spelloutFractionDigits.format(0) } + listOf(spelloutFractionDigits.format((value * 100000).toLong()))).joinToString(
                        " "
                    )

                    in 1000000L..9999999L -> (List(value.leadingFractionalZeroesCount()) { spelloutFractionDigits.format(0) } + listOf(spelloutFractionDigits.format((value * 1000000).toLong()))).joinToString(
                        " "
                    )

                    in 10000000L..99999999L -> (List(value.leadingFractionalZeroesCount()) { spelloutFractionDigits.format(0) } + listOf(spelloutFractionDigits.format((value * 10000000).toLong()))).joinToString(
                        " "
                    )

                    in 100000000L..999999999L -> (List(value.leadingFractionalZeroesCount()) { spelloutFractionDigits.format(0) } + listOf(spelloutFractionDigits.format((value * 100000000).toLong()))).joinToString(
                        " "
                    )

                    in 1000000000L..9999999999L -> (List(value.leadingFractionalZeroesCount()) { spelloutFractionDigits.format(0) } + listOf(spelloutFractionDigits.format((value * 1000000000).toLong()))).joinToString(
                        " "
                    )

                    else -> {
                        if (value >= 10000000000.0) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format((value).toLong() / 10000000000) else {
                            throw UnsupportedOperationException("spelloutFraction only supports numbers >= 10")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 10L..99L -> ""
                    in 100L..999L -> ""
                    in 1000L..9999L -> ""
                    in 10000L..99999L -> ""
                    in 100000L..999999L -> ""
                    in 1000000L..9999999L -> ""
                    in 10000000L..99999999L -> ""
                    in 100000000L..999999999L -> ""
                    in 1000000000L..9999999999L -> ""
                    else -> {
                        if (value >= 10000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 0,
                            secondary_grouping = 0,
                            symbols = language.numbers.symbols
                        ).format((value) / 10000000000) else {
                            throw UnsupportedOperationException("spelloutFraction only supports numbers >= 10")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000).bestDenominatorOrNull(value)!!) {
                    10L -> (List(value.leadingFractionalZeroesCount()) { spelloutCardinalMasculine.format(0) } + listOf(spelloutCardinalMasculine.format((value * 10).toLong()))).joinToString(" ")
                    100L -> (List(value.leadingFractionalZeroesCount()) { spelloutCardinalMasculine.format(0) } + listOf(spelloutCardinalMasculine.format((value * 100).toLong()))).joinToString(" ")
                    1000L -> (List(value.leadingFractionalZeroesCount()) { spelloutCardinalMasculine.format(0) } + listOf(spelloutCardinalMasculine.format((value * 1000).toLong()))).joinToString(" ")
                    10000L -> (List(value.leadingFractionalZeroesCount()) { spelloutFractionDigits.format(0) } + listOf(spelloutFractionDigits.format((value * 10000).toLong()))).joinToString(" ")
                    100000L -> (List(value.leadingFractionalZeroesCount()) { spelloutFractionDigits.format(0) } + listOf(spelloutFractionDigits.format((value * 100000).toLong()))).joinToString(" ")
                    1000000L -> (List(value.leadingFractionalZeroesCount()) { spelloutFractionDigits.format(0) } + listOf(spelloutFractionDigits.format((value * 1000000).toLong()))).joinToString(" ")
                    10000000L -> (List(value.leadingFractionalZeroesCount()) { spelloutFractionDigits.format(0) } + listOf(spelloutFractionDigits.format((value * 10000000).toLong()))).joinToString(" ")
                    100000000L -> (List(value.leadingFractionalZeroesCount()) { spelloutFractionDigits.format(0) } + listOf(spelloutFractionDigits.format((value * 100000000).toLong()))).joinToString(" ")
                    1000000000L -> (List(value.leadingFractionalZeroesCount()) { spelloutFractionDigits.format(0) } + listOf(spelloutFractionDigits.format((value * 1000000000).toLong()))).joinToString(
                        " "
                    )

                    10000000000L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 0,
                        primary_grouping = 0,
                        secondary_grouping = 0,
                        symbols = language.numbers.symbols
                    ).format((value * 10000000000).toLong())

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutFractionDigits : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 0L..9L -> spelloutCardinalMasculine.format(value)
                    else -> {
                        if (value >= 10.0) format((value).toLong() / 10) + " " + format(value % 10) else {
                            throw UnsupportedOperationException("spelloutFractionDigits only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 0L..9L -> spelloutCardinalMasculine.format(value)
                    else -> {
                        if (value >= 10L) format((value) / 10) + " " + format(value % 10) else {
                            throw UnsupportedOperationException("spelloutFractionDigits only supports numbers >= 0")
                        }
                    }
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
    open val spelloutCardinalMasculinePersonal: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculinePersonal
    open val spelloutCardinalFeminine: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminine
    open val spelloutCardinalNeuter: NumberFormatter
        get() = spelloutRules.spelloutCardinalNeuter
    open val spelloutCardinalMasculineGenitive: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculineGenitive
    open val spelloutCardinalFeminineGenitive: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminineGenitive
    open val spelloutCardinalNeuterGenitive: NumberFormatter
        get() = spelloutRules.spelloutCardinalNeuterGenitive
    open val spelloutCardinalMasculineDative: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculineDative
    open val spelloutCardinalFeminineDative: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminineDative
    open val spelloutCardinalNeuterDative: NumberFormatter
        get() = spelloutRules.spelloutCardinalNeuterDative
    open val spelloutCardinalMasculineAccusative: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculineAccusative
    open val spelloutCardinalMasculineAccusativeAnimate: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculineAccusativeAnimate
    open val spelloutCardinalMasculineAccusativePersonal: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculineAccusativePersonal
    open val spelloutCardinalFeminineAccusative: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminineAccusative
    open val spelloutCardinalNeuterAccusative: NumberFormatter
        get() = spelloutRules.spelloutCardinalNeuterAccusative
    open val spelloutCardinalMasculineInstrumental: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculineInstrumental
    open val spelloutCardinalFeminineInstrumental: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminineInstrumental
    open val spelloutCardinalNeuterInstrumental: NumberFormatter
        get() = spelloutRules.spelloutCardinalNeuterInstrumental
    open val spelloutCardinalMasculineLocative: NumberFormatter
        get() = spelloutRules.spelloutCardinalMasculineLocative
    open val spelloutCardinalFeminineLocative: NumberFormatter
        get() = spelloutRules.spelloutCardinalFeminineLocative
    open val spelloutCardinalNeuterLocative: NumberFormatter
        get() = spelloutRules.spelloutCardinalNeuterLocative
    override val spelloutOrdinal: NumberFormatter
        get() = TODO("spelloutOrdinal is not available for pl")
    override val spelloutCardinal: NumberFormatter
        get() = TODO("spelloutCardinal is not available for pl")

    fun spelloutCardinalFor(gender: Gender, case: Case): NumberFormatter {
        return when (gender) {
            Gender.Neuter -> when (case) {
                Case.Nominative -> spelloutCardinalNeuter
                Case.Locative -> spelloutCardinalNeuterLocative
                Case.Genitive -> spelloutCardinalNeuterGenitive
                Case.Dative -> spelloutCardinalNeuterDative
                Case.Instrumental -> spelloutCardinalNeuterInstrumental
                Case.Accusative -> spelloutCardinalNeuterAccusative
                else -> TODO("pl does not support $case case")
            }

            Gender.Masculine -> when (case) {
                Case.Nominative -> spelloutCardinalMasculine
                Case.Locative -> spelloutCardinalMasculineLocative
                Case.Genitive -> spelloutCardinalMasculineGenitive
                Case.Dative -> spelloutCardinalMasculineDative
                Case.Instrumental -> spelloutCardinalMasculineInstrumental
                Case.Accusative -> spelloutCardinalMasculineAccusative
                else -> TODO("pl does not support $case case")
            }

            Gender.Feminine -> when (case) {
                Case.Nominative -> spelloutCardinalFeminine
                Case.Locative -> spelloutCardinalFeminineLocative
                Case.Genitive -> spelloutCardinalFeminineGenitive
                Case.Dative -> spelloutCardinalFeminineDative
                Case.Instrumental -> spelloutCardinalFeminineInstrumental
                Case.Accusative -> spelloutCardinalFeminineAccusative
                else -> TODO("pl does not support $case case")
            }

            else -> TODO("pl does not support $gender gender")
        }

    }
}
