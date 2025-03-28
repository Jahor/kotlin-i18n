package info.leonenko.i18n.rbnf

import info.leonenko.i18n.*
import info.leonenko.i18n.languages.pt
import kotlin.math.roundToLong


open class RBNFPt(val language: Language = Language.pt) : RBNF {
    private inner class SpelloutRules {
        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumbering = SpelloutNumbering()

        val optionalE = OptionalE()

        val spelloutCardinalMasculineWithE = SpelloutCardinalMasculineWithE()

        val spelloutCardinalMasculine = SpelloutCardinalMasculine()

        val spelloutCardinalFeminineWithE = SpelloutCardinalFeminineWithE()

        val spelloutCardinalFeminine = SpelloutCardinalFeminine()

        val spelloutOrdinalMasculine = SpelloutOrdinalMasculine()

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

        private inner class OptionalE : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> " e "
                    else -> {
                        if (value >= 1.0) " " else {
                            throw UnsupportedOperationException("optionalE only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> " e "
                    else -> {
                        if (value >= 1L) " " else {
                            throw UnsupportedOperationException("optionalE only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> " e "
                    1L -> " "
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalMasculineWithE : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 0L..99L -> " e " + spelloutCardinalMasculine.format(value)
                    else -> {
                        if (value >= 100.0) optionalE.format(value % 100) + spelloutCardinalMasculine.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalMasculineWithE only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 0L..99L -> " e " + spelloutCardinalMasculine.format(value)
                    else -> {
                        if (value >= 100L) optionalE.format(value % 100) + spelloutCardinalMasculine.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalMasculineWithE only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 100).bestDenominatorOrNull(value)!!) {
                    0L -> " e " + spelloutCardinalMasculine.format(value)
                    100L -> optionalE.format(value % 100) + spelloutCardinalMasculine.format(value)
                    else -> throw IllegalStateException("It should not happen")
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
                    0L -> "zero"
                    1L -> "um"
                    2L -> "dois"
                    3L -> "três"
                    4L -> "quatro"
                    5L -> "cinco"
                    6L -> "seis"
                    7L -> "sete"
                    8L -> "oito"
                    9L -> "nove"
                    10L -> "dez"
                    11L -> "onze"
                    12L -> "doze"
                    13L -> "treze"
                    14L -> "catorze"
                    15L -> "quinze"
                    16L -> "dezesseis"
                    17L -> "dezessete"
                    18L -> "dezoito"
                    19L -> "dezenove"
                    in 20L..29L -> {
                        var result = "vinte"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "trinta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "quarenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "cinquenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "sessenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "setenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "oitenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "noventa"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    100L -> "cem"
                    in 101L..199L -> "cento e " + format(value % 100)
                    in 200L..299L -> {
                        var result = "duzentos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "trezentos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "quatrocentos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "quinhentos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "seiscentos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "setecentos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "oitocentos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "novecentos"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> "mil" + (if ((value) % 1000 == 0L) "" else spelloutCardinalMasculineWithE.format(value % 1000))
                    in 2000L..999999L -> format((value) / 1000) + " mil" + (if ((value) % 1000 == 0L) "" else spelloutCardinalMasculineWithE.format(value % 1000))
                    in 1000000L..999999999L -> format((value) / 1000000) + " " + Plurals(
                        one = "milhão",
                        other = "milhões"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString())) + (if ((value) % 1000000 == 0L) "" else spelloutCardinalMasculineWithE.format(value % 1000000))

                    in 1000000000L..999999999999L -> format((value) / 1000000000) + " " + Plurals(
                        one = "bilhão",
                        other = "bilhões"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString())) + (if ((value) % 1000000000 == 0L) "" else spelloutCardinalMasculineWithE.format(value % 1000000000))

                    in 1000000000000L..999999999999999L -> format((value) / 1000000000000) + " " + Plurals(
                        one = "trilhão",
                        other = "trilhões"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString())) + (if ((value) % 1000000000000 == 0L) "" else spelloutCardinalMasculineWithE.format(value % 1000000000000))

                    in 1000000000000000L..999999999999999999L -> format((value) / 1000000000000000) + " " + Plurals(
                        one = "quatrilhão",
                        other = "quatrilhões"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString())) + (if ((value) % 1000000000000000 == 0L) "" else spelloutCardinalMasculineWithE.format(value % 1000000000000000))

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
                return format(kotlin.math.truncate(value).toLong()) + " vírgula " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class SpelloutCardinalFeminineWithE : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 0L..99L -> " e " + spelloutCardinalFeminine.format(value)
                    else -> {
                        if (value >= 100.0) optionalE.format(value % 100) + spelloutCardinalFeminine.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalFeminineWithE only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 0L..99L -> " e " + spelloutCardinalFeminine.format(value)
                    else -> {
                        if (value >= 100L) optionalE.format(value % 100) + spelloutCardinalFeminine.format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalFeminineWithE only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 100).bestDenominatorOrNull(value)!!) {
                    0L -> " e " + spelloutCardinalFeminine.format(value)
                    100L -> optionalE.format(value % 100) + spelloutCardinalFeminine.format(value)
                    else -> throw IllegalStateException("It should not happen")
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
                    0L -> "zero"
                    1L -> "uma"
                    2L -> "duas"
                    in 3L..19L -> spelloutCardinalMasculine.format(value)
                    in 20L..29L -> {
                        var result = "vinte"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    in 30L..39L -> {
                        var result = "trinta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    in 40L..49L -> {
                        var result = "quarenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "cinquenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    in 60L..69L -> {
                        var result = "sessenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    in 70L..79L -> {
                        var result = "setenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    in 80L..89L -> {
                        var result = "oitenta"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    in 90L..99L -> {
                        var result = "noventa"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 10)
                        })
                        result
                    }

                    100L -> "cem"
                    in 101L..199L -> "cento e " + format(value % 100)
                    in 200L..299L -> {
                        var result = "duzentas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 300L..399L -> {
                        var result = "trezentas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 400L..499L -> {
                        var result = "quatrocentas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 500L..599L -> {
                        var result = "quinhentas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 600L..699L -> {
                        var result = "seiscentas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 700L..799L -> {
                        var result = "setecentas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 800L..899L -> {
                        var result = "oitocentas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 900L..999L -> {
                        var result = "novecentas"
                        result += (if ((value) % 100 == 0L) {
                            ""
                        } else {
                            " e " + format(value % 100)
                        })
                        result
                    }

                    in 1000L..1999L -> "mil" + (if ((value) % 1000 == 0L) "" else spelloutCardinalFeminineWithE.format(value % 1000))
                    in 2000L..999999L -> format((value) / 1000) + " mil" + (if ((value) % 1000 == 0L) "" else spelloutCardinalFeminineWithE.format(value % 1000))
                    in 1000000L..999999999L -> spelloutCardinalMasculine.format((value) / 1000000) + " " + Plurals(
                        one = "milhão",
                        other = "milhões"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000).toString())) + (if ((value) % 1000000 == 0L) "" else spelloutCardinalFeminineWithE.format(value % 1000000))

                    in 1000000000L..999999999999L -> spelloutCardinalMasculine.format((value) / 1000000000) + " " + Plurals(
                        one = "bilhão",
                        other = "bilhões"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000000).toString())) + (if ((value) % 1000000000 == 0L) "" else spelloutCardinalFeminineWithE.format(value % 1000000000))

                    in 1000000000000L..999999999999999L -> spelloutCardinalMasculine.format((value) / 1000000000000) + " " + Plurals(
                        one = "trilhão",
                        other = "trilhões"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000000000).toString())) + (if ((value) % 1000000000000 == 0L) "" else spelloutCardinalFeminineWithE.format(value % 1000000000000))

                    in 1000000000000000L..999999999999999999L -> spelloutCardinalMasculine.format((value) / 1000000000000000) + " " + Plurals(
                        one = "quatrilhão",
                        other = "quatrilhões"
                    ).get(language.pluralFormChooser.pluralForm(((value) / 1000000000000000).toString())) + (if ((value) % 1000000000000000 == 0L) "" else spelloutCardinalFeminineWithE.format(value % 1000000000000000))

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

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + " vírgula " + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
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
                    0L -> "zero"
                    1L -> "primeiro"
                    2L -> "segundo"
                    3L -> "terceiro"
                    4L -> "quarto"
                    5L -> "quinto"
                    6L -> "sexto"
                    7L -> "sétimo"
                    8L -> "oitavo"
                    9L -> "nono"
                    in 10L..19L -> {
                        var result = "décimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

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
                        var result = "quadragésimo"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "quinquagésimo"
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
                        var result = "quadringentésimo"
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
                        var result = "octingentésimo"
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

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000)
                        result += " milionésimo"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000)
                        result += " bilionésimo"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000)
                        result += " trilionésimo"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalMasculine.format((value) / 1000000000000000)
                        result += " quadrilionésimo"
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
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "zero"
                    1L -> "primeiro"
                    2L -> "segundo"
                    3L -> "terceiro"
                    4L -> "quarto"
                    5L -> "quinto"
                    6L -> "sexto"
                    7L -> "sétimo"
                    8L -> "oitavo"
                    9L -> "nono"
                    10L -> {
                        var result = "décimo"
                        result += (if (value * 10 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

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
                        var result = "quadragésimo"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "quinquagésimo"
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
                        var result = "quadringentésimo"
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
                        var result = "octingentésimo"
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
                        var result = spelloutCardinalMasculine.format((value * 1000000).toLong())
                        result += " milionésimo"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000).toLong())
                        result += " bilionésimo"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000).toLong())
                        result += " trilionésimo"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalMasculine.format((value * 1000000000000000).toLong())
                        result += " quadrilionésimo"
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
                    ).format(value) + "º"

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
                    0L -> "zero"
                    1L -> "primeira"
                    2L -> "segunda"
                    3L -> "terceira"
                    4L -> "quarta"
                    5L -> "quinta"
                    6L -> "sexta"
                    7L -> "sétima"
                    8L -> "oitava"
                    9L -> "nona"
                    in 10L..19L -> {
                        var result = "décima"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

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
                        var result = "quadragésima"
                        result += (if ((value) % 10 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    in 50L..59L -> {
                        var result = "quinquagésima"
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
                        var result = "quadringentésima"
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
                        var result = "octingentésima"
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
                        var result = spelloutCardinalFeminine.format((value) / 1000)
                        result += " milésima"
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000)
                        result += " milionésima"
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000)
                        result += " bilionésima"
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000)
                        result += " trilionésima"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = spelloutCardinalFeminine.format((value) / 1000000000000000)
                        result += " quadrilionésima"
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
                    1000000000,
                    1000000000000,
                    1000000000000000,
                    1000000000000000000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "zero"
                    1L -> "primeira"
                    2L -> "segunda"
                    3L -> "terceira"
                    4L -> "quarta"
                    5L -> "quinta"
                    6L -> "sexta"
                    7L -> "sétima"
                    8L -> "oitava"
                    9L -> "nona"
                    10L -> {
                        var result = "décima"
                        result += (if (value * 10 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

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
                        var result = "quadragésima"
                        result += (if (value * 40 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 10)
                        })
                        result
                    }

                    50L -> {
                        var result = "quinquagésima"
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
                        var result = "quadringentésima"
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
                        var result = "octingentésima"
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
                        var result = spelloutCardinalFeminine.format((value * 2000).toLong())
                        result += " milésima"
                        result += (if (value * 2000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    1000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 1000000).toLong())
                        result += " milionésima"
                        result += (if (value * 1000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    1000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 1000000000).toLong())
                        result += " bilionésima"
                        result += (if (value * 1000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    1000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 1000000000000).toLong())
                        result += " trilionésima"
                        result += (if (value * 1000000000000 == 1.0) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    1000000000000000L -> {
                        var result = spelloutCardinalFeminine.format((value * 1000000000000000).toLong())
                        result += " quadrilionésima"
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
        val digitsOrdinalMasculine = DigitsOrdinalMasculine()

        val digitsOrdinalFeminine = DigitsOrdinalFeminine()

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
                ).format(value) + "º" else {
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
                ).format(value) + "º" else {
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
                    ).format(value) + "º"

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
                ).format(value) + "ª" else {
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
                ).format(value) + "ª" else {
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
                    ).format(value) + "ª"

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
    open val spelloutOrdinalMasculine: NumberFormatter
        get() = spelloutRules.spelloutOrdinalMasculine
    open val spelloutOrdinalFeminine: NumberFormatter
        get() = spelloutRules.spelloutOrdinalFeminine
    open val digitsOrdinalMasculine: NumberFormatter
        get() = ordinalRules.digitsOrdinalMasculine
    open val digitsOrdinalFeminine: NumberFormatter
        get() = ordinalRules.digitsOrdinalFeminine
    open val digitsOrdinal: NumberFormatter
        get() = ordinalRules.digitsOrdinal
    override val spelloutCardinal: NumberFormatter
        get() = TODO("spelloutCardinal is not available for pt")
    override val spelloutOrdinal: NumberFormatter
        get() = TODO("spelloutOrdinal is not available for pt")

    override fun spelloutNumberingFor(gender: Gender, case: Case): NumberFormatter {
        return spelloutNumbering

    }

    fun spelloutNumberingFor(): NumberFormatter {
        return spelloutNumberingFor(Gender.Neuter, Case.Nominative)
    }

    override fun spelloutCardinalFor(gender: Gender, case: Case): NumberFormatter {
        return when (gender) {
            Gender.Feminine -> spelloutCardinalFeminine
            Gender.Masculine -> spelloutCardinalMasculine
            else -> TODO("pt does not support $gender gender")
        }

    }

    fun spelloutCardinalFor(gender: Gender): NumberFormatter {
        return spelloutCardinalFor(gender, Case.Nominative)
    }

    override fun spelloutOrdinalFor(gender: Gender, case: Case): NumberFormatter {
        return when (gender) {
            Gender.Feminine -> spelloutOrdinalFeminine
            Gender.Masculine -> spelloutOrdinalMasculine
            else -> TODO("pt does not support $gender gender")
        }

    }

    fun spelloutOrdinalFor(gender: Gender): NumberFormatter {
        return spelloutOrdinalFor(gender, Case.Nominative)
    }

    fun digitsOrdinalFor(gender: Gender, case: Case): NumberFormatter {
        return when (gender) {
            Gender.Feminine -> digitsOrdinalFeminine
            Gender.Masculine -> digitsOrdinalMasculine
            else -> TODO("pt does not support $gender gender")
        }

    }

    fun digitsOrdinalFor(gender: Gender): NumberFormatter {
        return digitsOrdinalFor(gender, Case.Nominative)
    }

    override fun spelloutNumberingYearFor(gender: Gender, case: Case): NumberFormatter {
        TODO("spelloutNumberingYear is not available for pt")
    }
}
