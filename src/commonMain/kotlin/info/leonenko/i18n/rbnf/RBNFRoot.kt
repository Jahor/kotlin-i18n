package info.leonenko.i18n.rbnf

import kotlin.math.roundToLong
import info.leonenko.i18n.DecimalNumberFormatter
import info.leonenko.i18n.Language
import info.leonenko.i18n.RBNF
import info.leonenko.i18n.NumberFormatter
import info.leonenko.i18n.Case
import info.leonenko.i18n.Gender
import info.leonenko.i18n.languages.root
import info.leonenko.i18n.formatFractionalPartByDigit
import info.leonenko.i18n.bestDenominatorOrNull
import info.leonenko.i18n.leadingFractionalZeroesCount
import info.leonenko.i18n.Plurals
import info.leonenko.i18n.until
import info.leonenko.i18n.contains


open class RBNFRoot(val language: Language = Language.root) : RBNF {
    private inner class NumberingSystemRules {
        val armenianLower = ArmenianLower()

        val armenianUpper = ArmenianUpper()

        val cyrillicLower110 = CyrillicLower110()

        val cyrillicLowerFinal = CyrillicLowerFinal()

        val cyrillicLowerPost = CyrillicLowerPost()

        val cyrillicLowerThousands = CyrillicLowerThousands()

        val cyrillicLower = CyrillicLower()

        val ethiopicP = EthiopicP()

        val ethiopicP1 = EthiopicP1()

        val ethiopicP2 = EthiopicP2()

        val ethiopicP3 = EthiopicP3()

        val ethiopic = Ethiopic()

        val georgian = Georgian()

        val greekLower = GreekLower()

        val greekNumeralMinuscules = GreekNumeralMinuscules()

        val greekUpper = GreekUpper()

        val greekNumeralMajuscules = GreekNumeralMajuscules()

        val hebrewThousands = HebrewThousands()

        val hebrew = Hebrew()

        val hebrew099 = Hebrew099()

        val hebrewItemHundreds = HebrewItemHundreds()

        val hebrewItem = HebrewItem()

        val romanLower = RomanLower()

        val romanUpper = RomanUpper()

        val tamil = Tamil()

        val tamilThousands = TamilThousands()

        val zzDefault = ZzDefault()

        private inner class ArmenianLower : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "0"
                    1L -> "ա"
                    2L -> "բ"
                    3L -> "գ"
                    4L -> "դ"
                    5L -> "ե"
                    6L -> "զ"
                    7L -> "է"
                    8L -> "ը"
                    9L -> "թ"
                    in 10L..19L -> "ժ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 20L..29L -> "ի" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 30L..39L -> "լ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 40L..49L -> "խ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 50L..59L -> "ծ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 60L..69L -> "կ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 70L..79L -> "հ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 80L..89L -> "ձ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 90L..99L -> "ղ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 100L..199L -> "ճ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 200L..299L -> "մ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 300L..399L -> "յ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 400L..499L -> "ն" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 500L..599L -> "շ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 600L..699L -> "ո" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 700L..799L -> "չ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 800L..899L -> "պ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 900L..999L -> "ջ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1000L..1999L -> "ռ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 2000L..2999L -> "ս" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 3000L..3999L -> "վ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 4000L..4999L -> "տ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 5000L..5999L -> "ր" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 6000L..6999L -> "ց" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 7000L..7999L -> "ւ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 8000L..8999L -> "փ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 9000L..9999L -> "ք" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    else -> {
                        if (value >= 10000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("armenianLower only supports numbers >= 0")
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
                    3000,
                    4000,
                    5000,
                    6000,
                    7000,
                    8000,
                    9000,
                    10000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "0"
                    1L -> "ա"
                    2L -> "բ"
                    3L -> "գ"
                    4L -> "դ"
                    5L -> "ե"
                    6L -> "զ"
                    7L -> "է"
                    8L -> "ը"
                    9L -> "թ"
                    10L -> "ժ" + (if (value * 10 == 1.0) "" else format(value % 10))
                    20L -> "ի" + (if (value * 20 == 1.0) "" else format(value % 10))
                    30L -> "լ" + (if (value * 30 == 1.0) "" else format(value % 10))
                    40L -> "խ" + (if (value * 40 == 1.0) "" else format(value % 10))
                    50L -> "ծ" + (if (value * 50 == 1.0) "" else format(value % 10))
                    60L -> "կ" + (if (value * 60 == 1.0) "" else format(value % 10))
                    70L -> "հ" + (if (value * 70 == 1.0) "" else format(value % 10))
                    80L -> "ձ" + (if (value * 80 == 1.0) "" else format(value % 10))
                    90L -> "ղ" + (if (value * 90 == 1.0) "" else format(value % 10))
                    100L -> "ճ" + (if (value * 100 == 1.0) "" else format(value % 100))
                    200L -> "մ" + (if (value * 200 == 1.0) "" else format(value % 100))
                    300L -> "յ" + (if (value * 300 == 1.0) "" else format(value % 100))
                    400L -> "ն" + (if (value * 400 == 1.0) "" else format(value % 100))
                    500L -> "շ" + (if (value * 500 == 1.0) "" else format(value % 100))
                    600L -> "ո" + (if (value * 600 == 1.0) "" else format(value % 100))
                    700L -> "չ" + (if (value * 700 == 1.0) "" else format(value % 100))
                    800L -> "պ" + (if (value * 800 == 1.0) "" else format(value % 100))
                    900L -> "ջ" + (if (value * 900 == 1.0) "" else format(value % 100))
                    1000L -> "ռ" + (if (value * 1000 == 1.0) "" else format(value % 1000))
                    2000L -> "ս" + (if (value * 2000 == 1.0) "" else format(value % 1000))
                    3000L -> "վ" + (if (value * 3000 == 1.0) "" else format(value % 1000))
                    4000L -> "տ" + (if (value * 4000 == 1.0) "" else format(value % 1000))
                    5000L -> "ր" + (if (value * 5000 == 1.0) "" else format(value % 1000))
                    6000L -> "ց" + (if (value * 6000 == 1.0) "" else format(value % 1000))
                    7000L -> "ւ" + (if (value * 7000 == 1.0) "" else format(value % 1000))
                    8000L -> "փ" + (if (value * 8000 == 1.0) "" else format(value % 1000))
                    9000L -> "ք" + (if (value * 9000 == 1.0) "" else format(value % 1000))
                    10000L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 2, max_decimal = 2, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class ArmenianUpper : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "0"
                    1L -> "Ա"
                    2L -> "Բ"
                    3L -> "Գ"
                    4L -> "Դ"
                    5L -> "Ե"
                    6L -> "Զ"
                    7L -> "Է"
                    8L -> "Ը"
                    9L -> "Թ"
                    in 10L..19L -> "Ժ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 20L..29L -> "Ի" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 30L..39L -> "Լ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 40L..49L -> "Խ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 50L..59L -> "Ծ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 60L..69L -> "Կ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 70L..79L -> "Հ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 80L..89L -> "Ձ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 90L..99L -> "Ղ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 100L..199L -> "Ճ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 200L..299L -> "Մ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 300L..399L -> "Յ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 400L..499L -> "Ն" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 500L..599L -> "Շ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 600L..699L -> "Ո" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 700L..799L -> "Չ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 800L..899L -> "Պ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 900L..999L -> "Ջ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1000L..1999L -> "Ռ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 2000L..2999L -> "Ս" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 3000L..3999L -> "Վ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 4000L..4999L -> "Տ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 5000L..5999L -> "Ր" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 6000L..6999L -> "Ց" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 7000L..7999L -> "Ւ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 8000L..8999L -> "Փ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 9000L..9999L -> "Ք" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    else -> {
                        if (value >= 10000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("armenianUpper only supports numbers >= 0")
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
                    3000,
                    4000,
                    5000,
                    6000,
                    7000,
                    8000,
                    9000,
                    10000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "0"
                    1L -> "Ա"
                    2L -> "Բ"
                    3L -> "Գ"
                    4L -> "Դ"
                    5L -> "Ե"
                    6L -> "Զ"
                    7L -> "Է"
                    8L -> "Ը"
                    9L -> "Թ"
                    10L -> "Ժ" + (if (value * 10 == 1.0) "" else format(value % 10))
                    20L -> "Ի" + (if (value * 20 == 1.0) "" else format(value % 10))
                    30L -> "Լ" + (if (value * 30 == 1.0) "" else format(value % 10))
                    40L -> "Խ" + (if (value * 40 == 1.0) "" else format(value % 10))
                    50L -> "Ծ" + (if (value * 50 == 1.0) "" else format(value % 10))
                    60L -> "Կ" + (if (value * 60 == 1.0) "" else format(value % 10))
                    70L -> "Հ" + (if (value * 70 == 1.0) "" else format(value % 10))
                    80L -> "Ձ" + (if (value * 80 == 1.0) "" else format(value % 10))
                    90L -> "Ղ" + (if (value * 90 == 1.0) "" else format(value % 10))
                    100L -> "Ճ" + (if (value * 100 == 1.0) "" else format(value % 100))
                    200L -> "Մ" + (if (value * 200 == 1.0) "" else format(value % 100))
                    300L -> "Յ" + (if (value * 300 == 1.0) "" else format(value % 100))
                    400L -> "Ն" + (if (value * 400 == 1.0) "" else format(value % 100))
                    500L -> "Շ" + (if (value * 500 == 1.0) "" else format(value % 100))
                    600L -> "Ո" + (if (value * 600 == 1.0) "" else format(value % 100))
                    700L -> "Չ" + (if (value * 700 == 1.0) "" else format(value % 100))
                    800L -> "Պ" + (if (value * 800 == 1.0) "" else format(value % 100))
                    900L -> "Ջ" + (if (value * 900 == 1.0) "" else format(value % 100))
                    1000L -> "Ռ" + (if (value * 1000 == 1.0) "" else format(value % 1000))
                    2000L -> "Ս" + (if (value * 2000 == 1.0) "" else format(value % 1000))
                    3000L -> "Վ" + (if (value * 3000 == 1.0) "" else format(value % 1000))
                    4000L -> "Տ" + (if (value * 4000 == 1.0) "" else format(value % 1000))
                    5000L -> "Ր" + (if (value * 5000 == 1.0) "" else format(value % 1000))
                    6000L -> "Ց" + (if (value * 6000 == 1.0) "" else format(value % 1000))
                    7000L -> "Ւ" + (if (value * 7000 == 1.0) "" else format(value % 1000))
                    8000L -> "Փ" + (if (value * 8000 == 1.0) "" else format(value % 1000))
                    9000L -> "Ք" + (if (value * 9000 == 1.0) "" else format(value % 1000))
                    10000L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 2, max_decimal = 2, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class CyrillicLower110 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    1L -> "а"
                    2L -> "в"
                    3L -> "г"
                    4L -> "д"
                    5L -> "є"
                    6L -> "ѕ"
                    7L -> "з"
                    8L -> "и"
                    9L -> "ѳ"
                    else -> {
                        if (value >= 10.0) "і" else {
                            throw UnsupportedOperationException("cyrillicLower110 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    1L -> "а"
                    2L -> "в"
                    3L -> "г"
                    4L -> "д"
                    5L -> "є"
                    6L -> "ѕ"
                    7L -> "з"
                    8L -> "и"
                    9L -> "ѳ"
                    else -> {
                        if (value >= 10L) "і" else {
                            throw UnsupportedOperationException("cyrillicLower110 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).bestDenominatorOrNull(value)!!) {
                    1L -> "а"
                    2L -> "в"
                    3L -> "г"
                    4L -> "д"
                    5L -> "є"
                    6L -> "ѕ"
                    7L -> "з"
                    8L -> "и"
                    9L -> "ѳ"
                    10L -> "і"
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class CyrillicLowerFinal : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "҃"
                    in 1L..10L -> "҃" + cyrillicLower110.format(value)
                    11L -> "а҃і"
                    12L -> "в҃і"
                    13L -> "г҃і"
                    14L -> "д҃і"
                    15L -> "є҃і"
                    16L -> "ѕ҃і"
                    17L -> "з҃і"
                    18L -> "и҃і"
                    19L -> "ѳ҃і"
                    20L -> "҃к"
                    in 21L..29L -> "к" + format(value % 10)
                    30L -> "҃л"
                    in 31L..39L -> "л" + format(value % 10)
                    40L -> "҃м"
                    in 41L..49L -> "м" + format(value % 10)
                    50L -> "҃н"
                    in 51L..59L -> "н" + format(value % 10)
                    60L -> "҃ѯ"
                    in 61L..69L -> "ѯ" + format(value % 10)
                    70L -> "҃ѻ"
                    in 71L..79L -> "ѻ" + format(value % 10)
                    80L -> "҃п"
                    in 81L..89L -> "п" + format(value % 10)
                    90L -> "҃ч"
                    else -> {
                        if (value >= 91.0) "ч" + format(value % 10) else {
                            throw UnsupportedOperationException("cyrillicLowerFinal only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "҃"
                    in 1L..10L -> "҃" + cyrillicLower110.format(value)
                    11L -> "а҃і"
                    12L -> "в҃і"
                    13L -> "г҃і"
                    14L -> "д҃і"
                    15L -> "є҃і"
                    16L -> "ѕ҃і"
                    17L -> "з҃і"
                    18L -> "и҃і"
                    19L -> "ѳ҃і"
                    20L -> "҃к"
                    in 21L..29L -> "к" + format(value % 10)
                    30L -> "҃л"
                    in 31L..39L -> "л" + format(value % 10)
                    40L -> "҃м"
                    in 41L..49L -> "м" + format(value % 10)
                    50L -> "҃н"
                    in 51L..59L -> "н" + format(value % 10)
                    60L -> "҃ѯ"
                    in 61L..69L -> "ѯ" + format(value % 10)
                    70L -> "҃ѻ"
                    in 71L..79L -> "ѻ" + format(value % 10)
                    80L -> "҃п"
                    in 81L..89L -> "п" + format(value % 10)
                    90L -> "҃ч"
                    else -> {
                        if (value >= 91L) "ч" + format(value % 10) else {
                            throw UnsupportedOperationException("cyrillicLowerFinal only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 30, 31, 40, 41, 50, 51, 60, 61, 70, 71, 80, 81, 90, 91).bestDenominatorOrNull(value)!!) {
                    0L -> "҃"
                    1L -> "҃" + cyrillicLower110.format(value)
                    11L -> "а҃і"
                    12L -> "в҃і"
                    13L -> "г҃і"
                    14L -> "д҃і"
                    15L -> "є҃і"
                    16L -> "ѕ҃і"
                    17L -> "з҃і"
                    18L -> "и҃і"
                    19L -> "ѳ҃і"
                    20L -> "҃к"
                    21L -> "к" + format(value % 10)
                    30L -> "҃л"
                    31L -> "л" + format(value % 10)
                    40L -> "҃м"
                    41L -> "м" + format(value % 10)
                    50L -> "҃н"
                    51L -> "н" + format(value % 10)
                    60L -> "҃ѯ"
                    61L -> "ѯ" + format(value % 10)
                    70L -> "҃ѻ"
                    71L -> "ѻ" + format(value % 10)
                    80L -> "҃п"
                    81L -> "п" + format(value % 10)
                    90L -> "҃ч"
                    91L -> "ч" + format(value % 10)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class CyrillicLowerPost : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "҃"
                    else -> {
                        if (value >= 1.0) cyrillicLower.format(value) else {
                            throw UnsupportedOperationException("cyrillicLowerPost only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "҃"
                    else -> {
                        if (value >= 1L) cyrillicLower.format(value) else {
                            throw UnsupportedOperationException("cyrillicLowerPost only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1).bestDenominatorOrNull(value)!!) {
                    0L -> "҃"
                    1L -> cyrillicLower.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class CyrillicLowerThousands : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "҃"
                    1L -> "҃҂а"
                    2L -> "҃҂в"
                    3L -> "҃҂г"
                    4L -> "҃҂д"
                    5L -> "҃҂є"
                    6L -> "҃҂ѕ"
                    7L -> "҃҂з"
                    8L -> "҃҂и"
                    9L -> "҃҂ѳ"
                    10L -> "҃҂і"
                    11L -> "҂а҃҂і"
                    12L -> "҂в҃҂і"
                    13L -> "҂г҃҂і"
                    14L -> "҂д҃҂і"
                    15L -> "҂є҃҂і"
                    16L -> "҂ѕ҃҂і"
                    17L -> "҂з҃҂і"
                    18L -> "҂и҃҂і"
                    19L -> "҂ѳ҃҂і"
                    in 20L..29L -> "҂к" + format(value % 10)
                    in 30L..39L -> "҂л" + format(value % 10)
                    in 40L..49L -> "҂м" + format(value % 10)
                    in 50L..59L -> "҂н" + format(value % 10)
                    in 60L..69L -> "҂ѯ" + format(value % 10)
                    in 70L..79L -> "҂ѻ" + format(value % 10)
                    in 80L..89L -> "҂п" + format(value % 10)
                    in 90L..99L -> "҂ч" + format(value % 10)
                    in 100L..199L -> "҂р" + format(value % 100)
                    in 200L..299L -> "҂с" + format(value % 100)
                    in 300L..399L -> "҂т" + format(value % 100)
                    in 400L..499L -> "҂у" + format(value % 100)
                    in 500L..599L -> "҂ф" + format(value % 100)
                    in 600L..699L -> "҂х" + format(value % 100)
                    in 700L..799L -> "҂ѱ" + format(value % 100)
                    in 800L..899L -> "҂ѿ" + format(value % 100)
                    else -> {
                        if (value >= 900.0) "҂ц" + format(value % 100) else {
                            throw UnsupportedOperationException("cyrillicLowerThousands only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "҃"
                    1L -> "҃҂а"
                    2L -> "҃҂в"
                    3L -> "҃҂г"
                    4L -> "҃҂д"
                    5L -> "҃҂є"
                    6L -> "҃҂ѕ"
                    7L -> "҃҂з"
                    8L -> "҃҂и"
                    9L -> "҃҂ѳ"
                    10L -> "҃҂і"
                    11L -> "҂а҃҂і"
                    12L -> "҂в҃҂і"
                    13L -> "҂г҃҂і"
                    14L -> "҂д҃҂і"
                    15L -> "҂є҃҂і"
                    16L -> "҂ѕ҃҂і"
                    17L -> "҂з҃҂і"
                    18L -> "҂и҃҂і"
                    19L -> "҂ѳ҃҂і"
                    in 20L..29L -> "҂к" + format(value % 10)
                    in 30L..39L -> "҂л" + format(value % 10)
                    in 40L..49L -> "҂м" + format(value % 10)
                    in 50L..59L -> "҂н" + format(value % 10)
                    in 60L..69L -> "҂ѯ" + format(value % 10)
                    in 70L..79L -> "҂ѻ" + format(value % 10)
                    in 80L..89L -> "҂п" + format(value % 10)
                    in 90L..99L -> "҂ч" + format(value % 10)
                    in 100L..199L -> "҂р" + format(value % 100)
                    in 200L..299L -> "҂с" + format(value % 100)
                    in 300L..399L -> "҂т" + format(value % 100)
                    in 400L..499L -> "҂у" + format(value % 100)
                    in 500L..599L -> "҂ф" + format(value % 100)
                    in 600L..699L -> "҂х" + format(value % 100)
                    in 700L..799L -> "҂ѱ" + format(value % 100)
                    in 800L..899L -> "҂ѿ" + format(value % 100)
                    else -> {
                        if (value >= 900L) "҂ц" + format(value % 100) else {
                            throw UnsupportedOperationException("cyrillicLowerThousands only supports numbers >= 0")
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
                    900
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "҃"
                    1L -> "҃҂а"
                    2L -> "҃҂в"
                    3L -> "҃҂г"
                    4L -> "҃҂д"
                    5L -> "҃҂є"
                    6L -> "҃҂ѕ"
                    7L -> "҃҂з"
                    8L -> "҃҂и"
                    9L -> "҃҂ѳ"
                    10L -> "҃҂і"
                    11L -> "҂а҃҂і"
                    12L -> "҂в҃҂і"
                    13L -> "҂г҃҂і"
                    14L -> "҂д҃҂і"
                    15L -> "҂є҃҂і"
                    16L -> "҂ѕ҃҂і"
                    17L -> "҂з҃҂і"
                    18L -> "҂и҃҂і"
                    19L -> "҂ѳ҃҂і"
                    20L -> "҂к" + format(value % 10)
                    30L -> "҂л" + format(value % 10)
                    40L -> "҂м" + format(value % 10)
                    50L -> "҂н" + format(value % 10)
                    60L -> "҂ѯ" + format(value % 10)
                    70L -> "҂ѻ" + format(value % 10)
                    80L -> "҂п" + format(value % 10)
                    90L -> "҂ч" + format(value % 10)
                    100L -> "҂р" + format(value % 100)
                    200L -> "҂с" + format(value % 100)
                    300L -> "҂т" + format(value % 100)
                    400L -> "҂у" + format(value % 100)
                    500L -> "҂ф" + format(value % 100)
                    600L -> "҂х" + format(value % 100)
                    700L -> "҂ѱ" + format(value % 100)
                    800L -> "҂ѿ" + format(value % 100)
                    900L -> "҂ц" + format(value % 100)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class CyrillicLower : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "0҃"
                    in 1L..10L -> cyrillicLower110.format(value) + "҃"
                    11L -> "а҃і"
                    12L -> "в҃і"
                    13L -> "г҃і"
                    14L -> "д҃і"
                    15L -> "є҃і"
                    16L -> "ѕ҃і"
                    17L -> "з҃і"
                    18L -> "и҃і"
                    19L -> "ѳ҃і"
                    in 20L..29L -> "к" + cyrillicLowerFinal.format(value % 10)
                    in 30L..39L -> "л" + cyrillicLowerFinal.format(value % 10)
                    in 40L..49L -> "м" + cyrillicLowerFinal.format(value % 10)
                    in 50L..59L -> "н" + cyrillicLowerFinal.format(value % 10)
                    in 60L..69L -> "ѯ" + cyrillicLowerFinal.format(value % 10)
                    in 70L..79L -> "ѻ" + cyrillicLowerFinal.format(value % 10)
                    in 80L..89L -> "п" + cyrillicLowerFinal.format(value % 10)
                    in 90L..99L -> "ч" + cyrillicLowerFinal.format(value % 10)
                    in 100L..199L -> "р" + cyrillicLowerFinal.format(value % 100)
                    in 200L..299L -> "с" + cyrillicLowerFinal.format(value % 100)
                    in 300L..399L -> "т" + cyrillicLowerFinal.format(value % 100)
                    in 400L..499L -> "у" + cyrillicLowerFinal.format(value % 100)
                    in 500L..599L -> "ф" + cyrillicLowerFinal.format(value % 100)
                    in 600L..699L -> "х" + cyrillicLowerFinal.format(value % 100)
                    in 700L..799L -> "ѱ" + cyrillicLowerFinal.format(value % 100)
                    800L -> "ѿ҃"
                    in 801L..899L -> "ѿ" + format(value % 100)
                    in 900L..999L -> "ц" + cyrillicLowerFinal.format(value % 100)
                    in 1000L..9999L -> "҂" + cyrillicLower110.format((value) / 1000) + cyrillicLowerPost.format(value % 1000)
                    in 10000L..10999L -> {
                        var result = "҂"
                        result += format((value) / 1000)
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 11000L..999999L -> {
                        var result = cyrillicLowerThousands.format((value) / 1000)
                        result += (if ((value) % 1000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000)
                        })
                        result
                    }

                    in 1000000L..999999999L -> {
                        var result = "҂҂"
                        result += format((value) / 1000000)
                        result += (if ((value) % 1000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000)
                        })
                        result
                    }

                    in 1000000000L..999999999999L -> {
                        var result = "҂҂҂"
                        result += format((value) / 1000000000)
                        result += (if ((value) % 1000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000)
                        })
                        result
                    }

                    in 1000000000000L..999999999999999L -> {
                        var result = "҂҂҂҂"
                        result += format((value) / 1000000000000)
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 1000000000000000L..999999999999999999L -> {
                        var result = "҂҂҂҂҂"
                        result += format((value) / 1000000000000000)
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
                            throw UnsupportedOperationException("cyrillicLower only supports numbers >= 0")
                        }
                    }
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + "." + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), "")
            }
        }

        private inner class EthiopicP : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9999L -> ethiopic.format(value)
                    in 10000L..99999999L -> format((value).toLong() / 10000) + "፼" + (if ((value).toLong() % 10000 == 0L) "" else format(value % 10000))
                    in 100000000L..999999999999L -> format((value).toLong() / 100000000) + "፼" + ethiopicP1.format(value % 100000000)
                    in 1000000000000L..9999999999999999L -> format((value).toLong() / 1000000000000) + "፼" + ethiopicP2.format(value % 1000000000000)
                    else -> {
                        if (value >= 10000000000000000.0) format((value).toLong() / 10000000000000000) + "፼" + ethiopicP3.format(value % 10000000000000000) else {
                            throw UnsupportedOperationException("ethiopicP only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9999L -> ethiopic.format(value)
                    in 10000L..99999999L -> format((value) / 10000) + "፼" + (if ((value) % 10000 == 0L) "" else format(value % 10000))
                    in 100000000L..999999999999L -> format((value) / 100000000) + "፼" + ethiopicP1.format(value % 100000000)
                    in 1000000000000L..9999999999999999L -> format((value) / 1000000000000) + "፼" + ethiopicP2.format(value % 1000000000000)
                    else -> {
                        if (value >= 10000000000000000L) format((value) / 10000000000000000) + "፼" + ethiopicP3.format(value % 10000000000000000) else {
                            throw UnsupportedOperationException("ethiopicP only supports numbers >= 1")
                        }
                    }
                }
            }
        }

        private inner class EthiopicP1 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "፼"
                    in 1L..9999L -> "፼" + ethiopicP.format(value)
                    else -> {
                        if (value >= 10000.0) ethiopic.format((value).toLong() / 10000) + "፼" + (if ((value).toLong() % 10000 == 0L) "" else ethiopic.format(value % 10000)) else {
                            throw UnsupportedOperationException("ethiopicP1 only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "፼"
                    in 1L..9999L -> "፼" + ethiopicP.format(value)
                    else -> {
                        if (value >= 10000L) ethiopic.format((value) / 10000) + "፼" + (if ((value) % 10000 == 0L) "" else ethiopic.format(value % 10000)) else {
                            throw UnsupportedOperationException("ethiopicP1 only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 10000).bestDenominatorOrNull(value)!!) {
                    0L -> "፼"
                    1L -> "፼" + ethiopicP.format(value)
                    10000L -> ethiopic.format((value * 10000).toLong()) + "፼" + (if (value * 10000 == 1.0) "" else ethiopic.format(value % 10000))
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class EthiopicP2 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "፼፼"
                    in 1L..99999999L -> "፼፼" + ethiopicP.format(value)
                    else -> {
                        if (value >= 100000000.0) ethiopic.format((value).toLong() / 100000000) + "፼" + ethiopicP1.format(value % 100000000) else {
                            throw UnsupportedOperationException("ethiopicP2 only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "፼፼"
                    in 1L..99999999L -> "፼፼" + ethiopicP.format(value)
                    else -> {
                        if (value >= 100000000L) ethiopic.format((value) / 100000000) + "፼" + ethiopicP1.format(value % 100000000) else {
                            throw UnsupportedOperationException("ethiopicP2 only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 100000000).bestDenominatorOrNull(value)!!) {
                    0L -> "፼፼"
                    1L -> "፼፼" + ethiopicP.format(value)
                    100000000L -> ethiopic.format((value * 100000000).toLong()) + "፼" + ethiopicP1.format(value % 100000000)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class EthiopicP3 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "፼፼፼"
                    in 1L..999999999999L -> "፼፼፼" + ethiopicP.format(value)
                    else -> {
                        if (value >= 1000000000000.0) ethiopic.format((value).toLong() / 1000000000000) + "፼" + ethiopicP2.format(value % 1000000000000) else {
                            throw UnsupportedOperationException("ethiopicP3 only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "፼፼፼"
                    in 1L..999999999999L -> "፼፼፼" + ethiopicP.format(value)
                    else -> {
                        if (value >= 1000000000000L) ethiopic.format((value) / 1000000000000) + "፼" + ethiopicP2.format(value % 1000000000000) else {
                            throw UnsupportedOperationException("ethiopicP3 only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 1000000000000).bestDenominatorOrNull(value)!!) {
                    0L -> "፼፼፼"
                    1L -> "፼፼፼" + ethiopicP.format(value)
                    1000000000000L -> ethiopic.format((value * 1000000000000).toLong()) + "፼" + ethiopicP2.format(value % 1000000000000)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Ethiopic : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "ባዶ"
                    1L -> "፩"
                    2L -> "፪"
                    3L -> "፫"
                    4L -> "፬"
                    5L -> "፭"
                    6L -> "፮"
                    7L -> "፯"
                    8L -> "፰"
                    9L -> "፱"
                    in 10L..19L -> "፲" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 20L..29L -> "፳" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 30L..39L -> "፴" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 40L..49L -> "፵" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 50L..59L -> "፶" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 60L..69L -> "፷" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 70L..79L -> "፸" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 80L..89L -> "፹" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 90L..99L -> "፺" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 100L..199L -> "፻" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 200L..9999L -> format((value) / 100) + "፻" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 10000L..19999L -> "፼" + (if ((value) % 10000 == 0L) "" else format(value % 10000))
                    in 20000L..99999999L -> format((value) / 10000) + "፼" + (if ((value) % 10000 == 0L) "" else format(value % 10000))
                    in 100000000L..199999999L -> "፼" + ethiopicP1.format(value % 100000000)
                    in 200000000L..999999999999L -> format((value) / 100000000) + "፼" + ethiopicP1.format(value % 100000000)
                    in 1000000000000L..1999999999999L -> "፼" + ethiopicP2.format(value % 1000000000000)
                    in 2000000000000L..9999999999999999L -> format((value) / 1000000000000) + "፼" + ethiopicP2.format(value % 1000000000000)
                    in 10000000000000000L..19999999999999999L -> "፼" + ethiopicP3.format(value % 10000000000000000)
                    in 20000000000000000L..999999999999999999L -> format((value) / 10000000000000000) + "፼" + ethiopicP3.format(value % 10000000000000000)
                    else -> {
                        if (value >= 1000000000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("ethiopic only supports numbers >= 0")
                        }
                    }
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + "፡" + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), " ")
            }
        }

        private inner class Georgian : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
                    1L -> "ა"
                    2L -> "ბ"
                    3L -> "გ"
                    4L -> "დ"
                    5L -> "ე"
                    6L -> "ვ"
                    7L -> "ზ"
                    8L -> "ჱ"
                    9L -> "თ"
                    in 10L..19L -> "ი" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 20L..29L -> "კ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 30L..39L -> "ლ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 40L..49L -> "მ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 50L..59L -> "ნ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 60L..69L -> "ჲ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 70L..79L -> "ო" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 80L..89L -> "პ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 90L..99L -> "ჟ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 100L..199L -> "რ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 200L..299L -> "ს" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 300L..399L -> "ტ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 400L..499L -> "უ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 500L..599L -> "ჳ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 600L..699L -> "ფ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 700L..799L -> "ქ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 800L..899L -> "ღ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 900L..999L -> "ყ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1000L..1999L -> "შ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 2000L..2999L -> "ჩ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 3000L..3999L -> "ც" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 4000L..4999L -> "ძ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 5000L..5999L -> "წ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 6000L..6999L -> "ჭ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 7000L..7999L -> "ხ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 8000L..8999L -> "ჴ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 9000L..9999L -> "ჵ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 10000L..19999L -> "ჯ" + (if ((value) % 10000 == 0L) "" else format(value % 10000))
                    else -> {
                        if (value >= 20000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("georgian only supports numbers >= 0")
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
                    3000,
                    4000,
                    5000,
                    6000,
                    7000,
                    8000,
                    9000,
                    10000,
                    20000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
                    1L -> "ა"
                    2L -> "ბ"
                    3L -> "გ"
                    4L -> "დ"
                    5L -> "ე"
                    6L -> "ვ"
                    7L -> "ზ"
                    8L -> "ჱ"
                    9L -> "თ"
                    10L -> "ი" + (if (value * 10 == 1.0) "" else format(value % 10))
                    20L -> "კ" + (if (value * 20 == 1.0) "" else format(value % 10))
                    30L -> "ლ" + (if (value * 30 == 1.0) "" else format(value % 10))
                    40L -> "მ" + (if (value * 40 == 1.0) "" else format(value % 10))
                    50L -> "ნ" + (if (value * 50 == 1.0) "" else format(value % 10))
                    60L -> "ჲ" + (if (value * 60 == 1.0) "" else format(value % 10))
                    70L -> "ო" + (if (value * 70 == 1.0) "" else format(value % 10))
                    80L -> "პ" + (if (value * 80 == 1.0) "" else format(value % 10))
                    90L -> "ჟ" + (if (value * 90 == 1.0) "" else format(value % 10))
                    100L -> "რ" + (if (value * 100 == 1.0) "" else format(value % 100))
                    200L -> "ს" + (if (value * 200 == 1.0) "" else format(value % 100))
                    300L -> "ტ" + (if (value * 300 == 1.0) "" else format(value % 100))
                    400L -> "უ" + (if (value * 400 == 1.0) "" else format(value % 100))
                    500L -> "ჳ" + (if (value * 500 == 1.0) "" else format(value % 100))
                    600L -> "ფ" + (if (value * 600 == 1.0) "" else format(value % 100))
                    700L -> "ქ" + (if (value * 700 == 1.0) "" else format(value % 100))
                    800L -> "ღ" + (if (value * 800 == 1.0) "" else format(value % 100))
                    900L -> "ყ" + (if (value * 900 == 1.0) "" else format(value % 100))
                    1000L -> "შ" + (if (value * 1000 == 1.0) "" else format(value % 1000))
                    2000L -> "ჩ" + (if (value * 2000 == 1.0) "" else format(value % 1000))
                    3000L -> "ც" + (if (value * 3000 == 1.0) "" else format(value % 1000))
                    4000L -> "ძ" + (if (value * 4000 == 1.0) "" else format(value % 1000))
                    5000L -> "წ" + (if (value * 5000 == 1.0) "" else format(value % 1000))
                    6000L -> "ჭ" + (if (value * 6000 == 1.0) "" else format(value % 1000))
                    7000L -> "ხ" + (if (value * 7000 == 1.0) "" else format(value % 1000))
                    8000L -> "ჴ" + (if (value * 8000 == 1.0) "" else format(value % 1000))
                    9000L -> "ჵ" + (if (value * 9000 == 1.0) "" else format(value % 1000))
                    10000L -> "ჯ" + (if (value * 10000 == 1.0) "" else format(value % 10000))
                    20000L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 2, max_decimal = 2, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class GreekLower : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return if (value >= 0L) greekNumeralMinuscules.format(value) + "´" else {
                    throw UnsupportedOperationException("greekLower only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> greekNumeralMinuscules.format(value) + "´"
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + "." + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), "")
            }
        }

        private inner class GreekNumeralMinuscules : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "𐆊"
                    1L -> "α"
                    2L -> "β"
                    3L -> "γ"
                    4L -> "δ"
                    5L -> "ε"
                    6L -> "ϝ"
                    7L -> "ζ"
                    8L -> "η"
                    9L -> "θ"
                    in 10L..19L -> "ι" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 20L..29L -> "κ" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 30L..39L -> "λ" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 40L..49L -> "μ" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 50L..59L -> "ν" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 60L..69L -> "ξ" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 70L..79L -> "ο" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 80L..89L -> "π" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 90L..99L -> "ϟ" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 100L..199L -> "ρ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 200L..299L -> "σ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 300L..399L -> "τ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 400L..499L -> "υ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 500L..599L -> "φ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 600L..699L -> "χ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 700L..799L -> "ψ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 800L..899L -> "ω" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 900L..999L -> "ϡ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 1000L..9999L -> "͵" + format((value).toLong() / 1000) + (if ((value).toLong() % 1000 == 0L) "" else format(value % 1000))
                    in 10000L..99999999L -> {
                        var result = format((value).toLong() / 10000)
                        result += "μ"
                        result += (if ((value).toLong() % 10000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10000)
                        })
                        result
                    }

                    in 100000000L..999999999999L -> {
                        var result = format((value).toLong() / 100000000)
                        result += "μμ"
                        result += (if ((value).toLong() % 100000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100000000)
                        })
                        result
                    }

                    in 1000000000000L..9999999999999999L -> {
                        var result = format((value).toLong() / 1000000000000)
                        result += "μμμ"
                        result += (if ((value).toLong() % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 10000000000000000L..999999999999999999L -> {
                        var result = format((value).toLong() / 10000000000000000)
                        result += "μμμμ"
                        result += (if ((value).toLong() % 10000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10000000000000000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 1000000000000000000.0) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("greekNumeralMinuscules only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "𐆊"
                    1L -> "α"
                    2L -> "β"
                    3L -> "γ"
                    4L -> "δ"
                    5L -> "ε"
                    6L -> "ϝ"
                    7L -> "ζ"
                    8L -> "η"
                    9L -> "θ"
                    in 10L..19L -> "ι" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 20L..29L -> "κ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 30L..39L -> "λ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 40L..49L -> "μ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 50L..59L -> "ν" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 60L..69L -> "ξ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 70L..79L -> "ο" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 80L..89L -> "π" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 90L..99L -> "ϟ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 100L..199L -> "ρ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 200L..299L -> "σ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 300L..399L -> "τ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 400L..499L -> "υ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 500L..599L -> "φ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 600L..699L -> "χ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 700L..799L -> "ψ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 800L..899L -> "ω" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 900L..999L -> "ϡ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1000L..9999L -> "͵" + format((value) / 1000) + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 10000L..99999999L -> {
                        var result = format((value) / 10000)
                        result += "μ"
                        result += (if ((value) % 10000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10000)
                        })
                        result
                    }

                    in 100000000L..999999999999L -> {
                        var result = format((value) / 100000000)
                        result += "μμ"
                        result += (if ((value) % 100000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100000000)
                        })
                        result
                    }

                    in 1000000000000L..9999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += "μμμ"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 10000000000000000L..999999999999999999L -> {
                        var result = format((value) / 10000000000000000)
                        result += "μμμμ"
                        result += (if ((value) % 10000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10000000000000000)
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
                            throw UnsupportedOperationException("greekNumeralMinuscules only supports numbers >= 0")
                        }
                    }
                }
            }
        }

        private inner class GreekUpper : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return if (value >= 0L) greekNumeralMajuscules.format(value) + "´" else {
                    throw UnsupportedOperationException("greekUpper only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> greekNumeralMajuscules.format(value) + "´"
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + "." + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), "")
            }
        }

        private inner class GreekNumeralMajuscules : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "𐆊"
                    1L -> "Α"
                    2L -> "Β"
                    3L -> "Γ"
                    4L -> "Δ"
                    5L -> "Ε"
                    6L -> "Ϝ"
                    7L -> "Ζ"
                    8L -> "Η"
                    9L -> "Θ"
                    in 10L..19L -> "Ι" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 20L..29L -> "Κ" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 30L..39L -> "Λ" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 40L..49L -> "Μ" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 50L..59L -> "Ν" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 60L..69L -> "Ξ" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 70L..79L -> "Ο" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 80L..89L -> "Π" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 90L..99L -> "Ϟ" + (if ((value).toLong() % 10 == 0L) "" else format(value % 10))
                    in 100L..199L -> "Ρ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 200L..299L -> "Σ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 300L..399L -> "Τ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 400L..499L -> "Υ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 500L..599L -> "Φ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 600L..699L -> "Χ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 700L..799L -> "Ψ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 800L..899L -> "Ω" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 900L..999L -> "Ϡ" + (if ((value).toLong() % 100 == 0L) "" else format(value % 100))
                    in 1000L..9999L -> "͵" + format((value).toLong() / 1000) + (if ((value).toLong() % 1000 == 0L) "" else format(value % 1000))
                    in 10000L..99999999L -> {
                        var result = format((value).toLong() / 10000)
                        result += "Μ"
                        result += (if ((value).toLong() % 10000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10000)
                        })
                        result
                    }

                    in 100000000L..999999999999L -> {
                        var result = format((value).toLong() / 100000000)
                        result += "ΜΜ"
                        result += (if ((value).toLong() % 100000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100000000)
                        })
                        result
                    }

                    in 1000000000000L..9999999999999999L -> {
                        var result = format((value).toLong() / 1000000000000)
                        result += "ΜΜΜ"
                        result += (if ((value).toLong() % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 10000000000000000L..999999999999999999L -> {
                        var result = format((value).toLong() / 10000000000000000)
                        result += "ΜΜΜΜ"
                        result += (if ((value).toLong() % 10000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10000000000000000)
                        })
                        result
                    }

                    else -> {
                        if (value >= 1000000000000000000.0) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("greekNumeralMajuscules only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "𐆊"
                    1L -> "Α"
                    2L -> "Β"
                    3L -> "Γ"
                    4L -> "Δ"
                    5L -> "Ε"
                    6L -> "Ϝ"
                    7L -> "Ζ"
                    8L -> "Η"
                    9L -> "Θ"
                    in 10L..19L -> "Ι" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 20L..29L -> "Κ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 30L..39L -> "Λ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 40L..49L -> "Μ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 50L..59L -> "Ν" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 60L..69L -> "Ξ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 70L..79L -> "Ο" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 80L..89L -> "Π" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 90L..99L -> "Ϟ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 100L..199L -> "Ρ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 200L..299L -> "Σ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 300L..399L -> "Τ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 400L..499L -> "Υ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 500L..599L -> "Φ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 600L..699L -> "Χ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 700L..799L -> "Ψ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 800L..899L -> "Ω" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 900L..999L -> "Ϡ" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1000L..9999L -> "͵" + format((value) / 1000) + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 10000L..99999999L -> {
                        var result = format((value) / 10000)
                        result += "Μ"
                        result += (if ((value) % 10000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10000)
                        })
                        result
                    }

                    in 100000000L..999999999999L -> {
                        var result = format((value) / 100000000)
                        result += "ΜΜ"
                        result += (if ((value) % 100000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 100000000)
                        })
                        result
                    }

                    in 1000000000000L..9999999999999999L -> {
                        var result = format((value) / 1000000000000)
                        result += "ΜΜΜ"
                        result += (if ((value) % 1000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 1000000000000)
                        })
                        result
                    }

                    in 10000000000000000L..999999999999999999L -> {
                        var result = format((value) / 10000000000000000)
                        result += "ΜΜΜΜ"
                        result += (if ((value) % 10000000000000000 == 0L) {
                            ""
                        } else {
                            " " + format(value % 10000000000000000)
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
                            throw UnsupportedOperationException("greekNumeralMajuscules only supports numbers >= 0")
                        }
                    }
                }
            }
        }

        private inner class HebrewThousands : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 0L..9L -> hebrew.format(value)
                    in 10L..99L -> hebrew.format(value) + (if ((value).toLong() % 10 == 0L) "" else "׳")
                    in 100L..400L -> hebrew.format(value) + (if ((value).toLong() % 100 == 0L) "" else "׳")
                    else -> {
                        if (value >= 401.0) hebrew.format(value) + "׳" else {
                            throw UnsupportedOperationException("hebrewThousands only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 0L..9L -> hebrew.format(value)
                    in 10L..99L -> hebrew.format(value) + (if ((value) % 10 == 0L) "" else "׳")
                    in 100L..400L -> hebrew.format(value) + (if ((value) % 100 == 0L) "" else "׳")
                    else -> {
                        if (value >= 401L) hebrew.format(value) + "׳" else {
                            throw UnsupportedOperationException("hebrewThousands only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 10, 100, 401).bestDenominatorOrNull(value)!!) {
                    0L -> hebrew.format(value)
                    10L -> hebrew.format(value) + (if (value * 10 == 1.0) "" else "׳")
                    100L -> hebrew.format(value) + (if (value * 100 == 1.0) "" else "׳")
                    401L -> hebrew.format(value) + "׳"
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Hebrew : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    in 0L..10L -> hebrewItem.format(value) + "׳"
                    in 11L..14L -> "י״" + hebrewItem.format(value % 10)
                    15L -> "ט״ו"
                    16L -> "ט״ז"
                    in 17L..19L -> "י״" + hebrewItem.format(value % 10)
                    20L -> "כ׳"
                    in 21L..29L -> "כ״" + hebrewItem.format(value % 10)
                    30L -> "ל׳"
                    in 31L..39L -> "ל״" + hebrewItem.format(value % 10)
                    40L -> "מ׳"
                    in 41L..49L -> "מ״" + hebrewItem.format(value % 10)
                    50L -> "נ׳"
                    in 51L..59L -> "נ״" + hebrewItem.format(value % 10)
                    60L -> "ס׳"
                    in 61L..69L -> "ס״" + hebrewItem.format(value % 10)
                    70L -> "ע׳"
                    in 71L..79L -> "ע״" + hebrewItem.format(value % 10)
                    80L -> "פ׳"
                    in 81L..89L -> "פ״" + hebrewItem.format(value % 10)
                    90L -> "צ׳"
                    in 91L..99L -> "צ״" + hebrewItem.format(value % 10)
                    in 100L..199L -> "ק" + hebrew099.format(value % 100)
                    in 200L..297L -> "ר" + hebrew099.format(value % 100)
                    298L -> "רח״צ"
                    299L -> "ר" + hebrew099.format(value % 100)
                    in 300L..303L -> "ש" + hebrew099.format(value % 100)
                    304L -> "ד״ש"
                    in 305L..343L -> "ש" + hebrew099.format(value % 100)
                    344L -> "שד״מ"
                    in 345L..399L -> "ש" + hebrew099.format(value % 100)
                    in 400L..499L -> "ת" + hebrew099.format(value % 100)
                    500L -> "ת״ק"
                    in 501L..599L -> "תק" + hebrew099.format(value % 100)
                    600L -> "ת״ר"
                    in 601L..697L -> "תר" + hebrew099.format(value % 100)
                    698L -> "תרח״צ"
                    699L -> "תר" + hebrew099.format(value % 100)
                    700L -> "ת״ש"
                    in 701L..743L -> "תש" + hebrew099.format(value % 100)
                    744L -> "תשד״מ"
                    in 745L..799L -> "תש" + hebrew099.format(value % 100)
                    800L -> "ת״ת"
                    in 801L..899L -> "תת" + hebrew099.format(value % 100)
                    900L -> "תת״ק"
                    in 901L..999L -> "תתק" + hebrew099.format(value % 100)
                    1000L -> "אלף"
                    in 1001L..1999L -> if ((value) % 1000 == 0L) {
                        "אלף"
                    } else
                        hebrewThousands.format((value) / 1000) + (if ((value) % 1000 == 0L) "" else format(value % 1000))

                    2000L -> "אלפיים"
                    in 2001L..2999L -> if ((value) % 1000 == 0L) {
                        "אלפיים"
                    } else
                        hebrewThousands.format((value) / 1000) + (if ((value) % 1000 == 0L) "" else format(value % 1000))

                    3000L -> format((value) / 1000) + " אלפים"
                    in 3001L..999999L -> if ((value) % 1000 == 0L) {
                        format((value) / 1000) + " אלפים"
                    } else
                        hebrewThousands.format((value) / 1000) + (if ((value) % 1000 == 0L) "" else format(value % 1000))

                    1000000L -> "אלף אלפים"
                    else -> {
                        if (value >= 1000001L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("hebrew only supports numbers >= 0")
                        }
                    }
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 2, max_decimal = 2, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class Hebrew099 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "׳"
                    in 1L..10L -> "״" + hebrewItem.format(value)
                    in 11L..14L -> "י״" + hebrewItem.format(value % 10)
                    15L -> "ט״ו"
                    16L -> "ט״ז"
                    in 17L..19L -> "י״" + hebrewItem.format(value % 10)
                    20L -> "״כ"
                    in 21L..29L -> "כ״" + hebrewItem.format(value % 10)
                    30L -> "״ל"
                    in 31L..39L -> "ל״" + hebrewItem.format(value % 10)
                    40L -> "״מ"
                    in 41L..49L -> "מ״" + hebrewItem.format(value % 10)
                    50L -> "״נ"
                    in 51L..59L -> "נ״" + hebrewItem.format(value % 10)
                    60L -> "״ס"
                    in 61L..69L -> "ס״" + hebrewItem.format(value % 10)
                    70L -> "״ע"
                    in 71L..79L -> "ע״" + hebrewItem.format(value % 10)
                    80L -> "״ף"
                    in 81L..89L -> "פ״" + hebrewItem.format(value % 10)
                    90L -> "״צ"
                    else -> {
                        if (value >= 91.0) "צ״" + hebrewItem.format(value % 10) else {
                            throw UnsupportedOperationException("hebrew099 only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "׳"
                    in 1L..10L -> "״" + hebrewItem.format(value)
                    in 11L..14L -> "י״" + hebrewItem.format(value % 10)
                    15L -> "ט״ו"
                    16L -> "ט״ז"
                    in 17L..19L -> "י״" + hebrewItem.format(value % 10)
                    20L -> "״כ"
                    in 21L..29L -> "כ״" + hebrewItem.format(value % 10)
                    30L -> "״ל"
                    in 31L..39L -> "ל״" + hebrewItem.format(value % 10)
                    40L -> "״מ"
                    in 41L..49L -> "מ״" + hebrewItem.format(value % 10)
                    50L -> "״נ"
                    in 51L..59L -> "נ״" + hebrewItem.format(value % 10)
                    60L -> "״ס"
                    in 61L..69L -> "ס״" + hebrewItem.format(value % 10)
                    70L -> "״ע"
                    in 71L..79L -> "ע״" + hebrewItem.format(value % 10)
                    80L -> "״ף"
                    in 81L..89L -> "פ״" + hebrewItem.format(value % 10)
                    90L -> "״צ"
                    else -> {
                        if (value >= 91L) "צ״" + hebrewItem.format(value % 10) else {
                            throw UnsupportedOperationException("hebrew099 only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 11, 15, 16, 17, 20, 21, 30, 31, 40, 41, 50, 51, 60, 61, 70, 71, 80, 81, 90, 91).bestDenominatorOrNull(value)!!) {
                    0L -> "׳"
                    1L -> "״" + hebrewItem.format(value)
                    11L -> "י״" + hebrewItem.format(value % 10)
                    15L -> "ט״ו"
                    16L -> "ט״ז"
                    17L -> "י״" + hebrewItem.format(value % 10)
                    20L -> "״כ"
                    21L -> "כ״" + hebrewItem.format(value % 10)
                    30L -> "״ל"
                    31L -> "ל״" + hebrewItem.format(value % 10)
                    40L -> "״מ"
                    41L -> "מ״" + hebrewItem.format(value % 10)
                    50L -> "״נ"
                    51L -> "נ״" + hebrewItem.format(value % 10)
                    60L -> "״ס"
                    61L -> "ס״" + hebrewItem.format(value % 10)
                    70L -> "״ע"
                    71L -> "ע״" + hebrewItem.format(value % 10)
                    80L -> "״ף"
                    81L -> "פ״" + hebrewItem.format(value % 10)
                    90L -> "״צ"
                    91L -> "צ״" + hebrewItem.format(value % 10)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class HebrewItemHundreds : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "״"
                    1L -> "א"
                    2L -> "ב"
                    3L -> "ג"
                    4L -> "ד"
                    5L -> "ה"
                    6L -> "ו"
                    7L -> "ז"
                    8L -> "ח"
                    9L -> "ט"
                    in 10L..14L -> "י" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    15L -> "טו"
                    16L -> "טז"
                    in 17L..19L -> "י" + format(value % 10)
                    in 20L..29L -> "כ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 30L..39L -> "ל" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 40L..49L -> "מ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 50L..59L -> "נ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 60L..69L -> "ס" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 70L..79L -> "ע" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    80L -> "ף"
                    in 81L..89L -> "פ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 90L..99L -> "צ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 100L..199L -> "ק" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 200L..297L -> "ר" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    298L -> "רחצ"
                    299L -> "ר" + format(value % 100)
                    in 300L..303L -> "ש" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    304L -> "דש"
                    in 305L..343L -> "ש" + format(value % 100)
                    344L -> "שדמ"
                    in 345L..399L -> "ש" + format(value % 100)
                    in 400L..499L -> "ת" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 500L..599L -> "תק" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 600L..697L -> "תר" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    698L -> "תרחצ"
                    699L -> "תר" + format(value % 100)
                    in 700L..743L -> "תש" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    744L -> "תשדמ"
                    in 745L..799L -> "תש" + format(value % 100)
                    in 800L..899L -> "תת" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 900L..999L -> "תתק" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1000L..1099L -> "תתר" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1100L..1199L -> "תתש" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1200L..1299L -> "תתת" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1300L..1399L -> "תתתק" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1400L..1499L -> "תתתר" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1500L..1599L -> "תתתש" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1600L..1699L -> "תתתת" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1700L..1799L -> "תתתתק" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1800L..1899L -> "תתתתר" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1900L..1999L -> "תתתתש" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 2000L..2099L -> "תתתתת" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    else -> {
                        if (value >= 2100L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("hebrewItemHundreds only supports numbers >= 0")
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
                    15,
                    16,
                    17,
                    20,
                    30,
                    40,
                    50,
                    60,
                    70,
                    80,
                    81,
                    90,
                    100,
                    200,
                    298,
                    299,
                    300,
                    304,
                    305,
                    344,
                    345,
                    400,
                    500,
                    600,
                    698,
                    699,
                    700,
                    744,
                    745,
                    800,
                    900,
                    1000,
                    1100,
                    1200,
                    1300,
                    1400,
                    1500,
                    1600,
                    1700,
                    1800,
                    1900,
                    2000,
                    2100
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "״"
                    1L -> "א"
                    2L -> "ב"
                    3L -> "ג"
                    4L -> "ד"
                    5L -> "ה"
                    6L -> "ו"
                    7L -> "ז"
                    8L -> "ח"
                    9L -> "ט"
                    10L -> "י" + (if (value * 10 == 1.0) "" else format(value % 10))
                    15L -> "טו"
                    16L -> "טז"
                    17L -> "י" + format(value % 10)
                    20L -> "כ" + (if (value * 20 == 1.0) "" else format(value % 10))
                    30L -> "ל" + (if (value * 30 == 1.0) "" else format(value % 10))
                    40L -> "מ" + (if (value * 40 == 1.0) "" else format(value % 10))
                    50L -> "נ" + (if (value * 50 == 1.0) "" else format(value % 10))
                    60L -> "ס" + (if (value * 60 == 1.0) "" else format(value % 10))
                    70L -> "ע" + (if (value * 70 == 1.0) "" else format(value % 10))
                    80L -> "ף"
                    81L -> "פ" + (if (value * 81 == 1.0) "" else format(value % 10))
                    90L -> "צ" + (if (value * 90 == 1.0) "" else format(value % 10))
                    100L -> "ק" + (if (value * 100 == 1.0) "" else format(value % 100))
                    200L -> "ר" + (if (value * 200 == 1.0) "" else format(value % 100))
                    298L -> "רחצ"
                    299L -> "ר" + format(value % 100)
                    300L -> "ש" + (if (value * 300 == 1.0) "" else format(value % 100))
                    304L -> "דש"
                    305L -> "ש" + format(value % 100)
                    344L -> "שדמ"
                    345L -> "ש" + format(value % 100)
                    400L -> "ת" + (if (value * 400 == 1.0) "" else format(value % 100))
                    500L -> "תק" + (if (value * 500 == 1.0) "" else format(value % 100))
                    600L -> "תר" + (if (value * 600 == 1.0) "" else format(value % 100))
                    698L -> "תרחצ"
                    699L -> "תר" + format(value % 100)
                    700L -> "תש" + (if (value * 700 == 1.0) "" else format(value % 100))
                    744L -> "תשדמ"
                    745L -> "תש" + format(value % 100)
                    800L -> "תת" + (if (value * 800 == 1.0) "" else format(value % 100))
                    900L -> "תתק" + (if (value * 900 == 1.0) "" else format(value % 100))
                    1000L -> "תתר" + (if (value * 1000 == 1.0) "" else format(value % 100))
                    1100L -> "תתש" + (if (value * 1100 == 1.0) "" else format(value % 100))
                    1200L -> "תתת" + (if (value * 1200 == 1.0) "" else format(value % 100))
                    1300L -> "תתתק" + (if (value * 1300 == 1.0) "" else format(value % 100))
                    1400L -> "תתתר" + (if (value * 1400 == 1.0) "" else format(value % 100))
                    1500L -> "תתתש" + (if (value * 1500 == 1.0) "" else format(value % 100))
                    1600L -> "תתתת" + (if (value * 1600 == 1.0) "" else format(value % 100))
                    1700L -> "תתתתק" + (if (value * 1700 == 1.0) "" else format(value % 100))
                    1800L -> "תתתתר" + (if (value * 1800 == 1.0) "" else format(value % 100))
                    1900L -> "תתתתש" + (if (value * 1900 == 1.0) "" else format(value % 100))
                    2000L -> "תתתתת" + (if (value * 2000 == 1.0) "" else format(value % 100))
                    2100L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 2, max_decimal = 2, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class HebrewItem : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "״"
                    1L -> "א"
                    2L -> "ב"
                    3L -> "ג"
                    4L -> "ד"
                    5L -> "ה"
                    6L -> "ו"
                    7L -> "ז"
                    8L -> "ח"
                    9L -> "ט"
                    in 10L..14L -> "י" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    15L -> "טו"
                    16L -> "טז"
                    in 17L..19L -> "י" + format(value % 10)
                    in 20L..29L -> "כ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 30L..39L -> "ל" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 40L..49L -> "מ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 50L..59L -> "נ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 60L..69L -> "ס" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 70L..79L -> "ע" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 80L..89L -> "פ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 90L..99L -> "צ" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    else -> {
                        if (value >= 100L) hebrewItemHundreds.format(value) else {
                            throw UnsupportedOperationException("hebrewItem only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 16, 17, 20, 30, 40, 50, 60, 70, 80, 90, 100).bestDenominatorOrNull(value)!!) {
                    0L -> "״"
                    1L -> "א"
                    2L -> "ב"
                    3L -> "ג"
                    4L -> "ד"
                    5L -> "ה"
                    6L -> "ו"
                    7L -> "ז"
                    8L -> "ח"
                    9L -> "ט"
                    10L -> "י" + (if (value * 10 == 1.0) "" else format(value % 10))
                    15L -> "טו"
                    16L -> "טז"
                    17L -> "י" + format(value % 10)
                    20L -> "כ" + (if (value * 20 == 1.0) "" else format(value % 10))
                    30L -> "ל" + (if (value * 30 == 1.0) "" else format(value % 10))
                    40L -> "מ" + (if (value * 40 == 1.0) "" else format(value % 10))
                    50L -> "נ" + (if (value * 50 == 1.0) "" else format(value % 10))
                    60L -> "ס" + (if (value * 60 == 1.0) "" else format(value % 10))
                    70L -> "ע" + (if (value * 70 == 1.0) "" else format(value % 10))
                    80L -> "פ" + (if (value * 80 == 1.0) "" else format(value % 10))
                    90L -> "צ" + (if (value * 90 == 1.0) "" else format(value % 10))
                    100L -> hebrewItemHundreds.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 2, max_decimal = 2, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class RomanLower : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "n"
                    1L -> "i"
                    2L -> "ii"
                    3L -> "iii"
                    4L -> "iv"
                    5L -> "v"
                    6L -> "vi"
                    7L -> "vii"
                    8L -> "viii"
                    9L -> "ix"
                    in 10L..19L -> "x" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 20L..29L -> "xx" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 30L..39L -> "xxx" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 40L..49L -> "xl" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 50L..59L -> "l" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 60L..69L -> "lx" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 70L..79L -> "lxx" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 80L..89L -> "lxxx" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 90L..99L -> "xc" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 100L..199L -> "c" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 200L..299L -> "cc" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 300L..399L -> "ccc" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 400L..499L -> "cd" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 500L..599L -> "d" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 600L..699L -> "dc" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 700L..799L -> "dcc" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 800L..899L -> "dccc" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 900L..999L -> "cm" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1000L..1999L -> "m" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 2000L..2999L -> "mm" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 3000L..3999L -> "mmm" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 4000L..4999L -> "mmmm" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    else -> {
                        if (value >= 5000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("romanLower only supports numbers >= 0")
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
                    3000,
                    4000,
                    5000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "n"
                    1L -> "i"
                    2L -> "ii"
                    3L -> "iii"
                    4L -> "iv"
                    5L -> "v"
                    6L -> "vi"
                    7L -> "vii"
                    8L -> "viii"
                    9L -> "ix"
                    10L -> "x" + (if (value * 10 == 1.0) "" else format(value % 10))
                    20L -> "xx" + (if (value * 20 == 1.0) "" else format(value % 10))
                    30L -> "xxx" + (if (value * 30 == 1.0) "" else format(value % 10))
                    40L -> "xl" + (if (value * 40 == 1.0) "" else format(value % 10))
                    50L -> "l" + (if (value * 50 == 1.0) "" else format(value % 10))
                    60L -> "lx" + (if (value * 60 == 1.0) "" else format(value % 10))
                    70L -> "lxx" + (if (value * 70 == 1.0) "" else format(value % 10))
                    80L -> "lxxx" + (if (value * 80 == 1.0) "" else format(value % 10))
                    90L -> "xc" + (if (value * 90 == 1.0) "" else format(value % 10))
                    100L -> "c" + (if (value * 100 == 1.0) "" else format(value % 100))
                    200L -> "cc" + (if (value * 200 == 1.0) "" else format(value % 100))
                    300L -> "ccc" + (if (value * 300 == 1.0) "" else format(value % 100))
                    400L -> "cd" + (if (value * 400 == 1.0) "" else format(value % 100))
                    500L -> "d" + (if (value * 500 == 1.0) "" else format(value % 100))
                    600L -> "dc" + (if (value * 600 == 1.0) "" else format(value % 100))
                    700L -> "dcc" + (if (value * 700 == 1.0) "" else format(value % 100))
                    800L -> "dccc" + (if (value * 800 == 1.0) "" else format(value % 100))
                    900L -> "cm" + (if (value * 900 == 1.0) "" else format(value % 100))
                    1000L -> "m" + (if (value * 1000 == 1.0) "" else format(value % 1000))
                    2000L -> "mm" + (if (value * 2000 == 1.0) "" else format(value % 1000))
                    3000L -> "mmm" + (if (value * 3000 == 1.0) "" else format(value % 1000))
                    4000L -> "mmmm" + (if (value * 4000 == 1.0) "" else format(value % 1000))
                    5000L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 2, max_decimal = 2, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class RomanUpper : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "N"
                    1L -> "I"
                    2L -> "II"
                    3L -> "III"
                    4L -> "IV"
                    5L -> "V"
                    6L -> "VI"
                    7L -> "VII"
                    8L -> "VIII"
                    9L -> "IX"
                    in 10L..19L -> "X" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 20L..29L -> "XX" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 30L..39L -> "XXX" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 40L..49L -> "XL" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 50L..59L -> "L" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 60L..69L -> "LX" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 70L..79L -> "LXX" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 80L..89L -> "LXXX" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 90L..99L -> "XC" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 100L..199L -> "C" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 200L..299L -> "CC" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 300L..399L -> "CCC" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 400L..499L -> "CD" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 500L..599L -> "D" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 600L..699L -> "DC" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 700L..799L -> "DCC" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 800L..899L -> "DCCC" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 900L..999L -> "CM" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1000L..1999L -> "M" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 2000L..2999L -> "MM" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 3000L..3999L -> "MMM" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 4000L..4999L -> "Mↁ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 5000L..5999L -> "ↁ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 6000L..6999L -> "ↁM" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 7000L..7999L -> "ↁMM" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 8000L..8999L -> "ↁMMM" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 9000L..9999L -> "Mↂ" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 10000L..19999L -> "ↂ" + (if ((value) % 10000 == 0L) "" else format(value % 10000))
                    in 20000L..29999L -> "ↂↂ" + (if ((value) % 10000 == 0L) "" else format(value % 10000))
                    in 30000L..39999L -> "ↂↂↂ" + (if ((value) % 10000 == 0L) "" else format(value % 10000))
                    in 40000L..49999L -> "ↂↇ" + (if ((value) % 10000 == 0L) "" else format(value % 10000))
                    in 50000L..59999L -> "ↇ" + (if ((value) % 10000 == 0L) "" else format(value % 10000))
                    in 60000L..69999L -> "ↇↂ" + (if ((value) % 10000 == 0L) "" else format(value % 10000))
                    in 70000L..79999L -> "ↇↂↂ" + (if ((value) % 10000 == 0L) "" else format(value % 10000))
                    in 80000L..89999L -> "ↇↂↂↂ" + (if ((value) % 10000 == 0L) "" else format(value % 10000))
                    in 90000L..99999L -> "ↂↈ" + (if ((value) % 10000 == 0L) "" else format(value % 10000))
                    in 100000L..199999L -> "ↈ" + (if ((value) % 100000 == 0L) "" else format(value % 100000))
                    in 200000L..299999L -> "ↈↈ" + (if ((value) % 100000 == 0L) "" else format(value % 100000))
                    in 300000L..399999L -> "ↈↈↈ" + (if ((value) % 100000 == 0L) "" else format(value % 100000))
                    else -> {
                        if (value >= 400000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("romanUpper only supports numbers >= 0")
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
                    3000,
                    4000,
                    5000,
                    6000,
                    7000,
                    8000,
                    9000,
                    10000,
                    20000,
                    30000,
                    40000,
                    50000,
                    60000,
                    70000,
                    80000,
                    90000,
                    100000,
                    200000,
                    300000,
                    400000
                ).bestDenominatorOrNull(value)!!) {
                    0L -> "N"
                    1L -> "I"
                    2L -> "II"
                    3L -> "III"
                    4L -> "IV"
                    5L -> "V"
                    6L -> "VI"
                    7L -> "VII"
                    8L -> "VIII"
                    9L -> "IX"
                    10L -> "X" + (if (value * 10 == 1.0) "" else format(value % 10))
                    20L -> "XX" + (if (value * 20 == 1.0) "" else format(value % 10))
                    30L -> "XXX" + (if (value * 30 == 1.0) "" else format(value % 10))
                    40L -> "XL" + (if (value * 40 == 1.0) "" else format(value % 10))
                    50L -> "L" + (if (value * 50 == 1.0) "" else format(value % 10))
                    60L -> "LX" + (if (value * 60 == 1.0) "" else format(value % 10))
                    70L -> "LXX" + (if (value * 70 == 1.0) "" else format(value % 10))
                    80L -> "LXXX" + (if (value * 80 == 1.0) "" else format(value % 10))
                    90L -> "XC" + (if (value * 90 == 1.0) "" else format(value % 10))
                    100L -> "C" + (if (value * 100 == 1.0) "" else format(value % 100))
                    200L -> "CC" + (if (value * 200 == 1.0) "" else format(value % 100))
                    300L -> "CCC" + (if (value * 300 == 1.0) "" else format(value % 100))
                    400L -> "CD" + (if (value * 400 == 1.0) "" else format(value % 100))
                    500L -> "D" + (if (value * 500 == 1.0) "" else format(value % 100))
                    600L -> "DC" + (if (value * 600 == 1.0) "" else format(value % 100))
                    700L -> "DCC" + (if (value * 700 == 1.0) "" else format(value % 100))
                    800L -> "DCCC" + (if (value * 800 == 1.0) "" else format(value % 100))
                    900L -> "CM" + (if (value * 900 == 1.0) "" else format(value % 100))
                    1000L -> "M" + (if (value * 1000 == 1.0) "" else format(value % 1000))
                    2000L -> "MM" + (if (value * 2000 == 1.0) "" else format(value % 1000))
                    3000L -> "MMM" + (if (value * 3000 == 1.0) "" else format(value % 1000))
                    4000L -> "Mↁ" + (if (value * 4000 == 1.0) "" else format(value % 1000))
                    5000L -> "ↁ" + (if (value * 5000 == 1.0) "" else format(value % 1000))
                    6000L -> "ↁM" + (if (value * 6000 == 1.0) "" else format(value % 1000))
                    7000L -> "ↁMM" + (if (value * 7000 == 1.0) "" else format(value % 1000))
                    8000L -> "ↁMMM" + (if (value * 8000 == 1.0) "" else format(value % 1000))
                    9000L -> "Mↂ" + (if (value * 9000 == 1.0) "" else format(value % 1000))
                    10000L -> "ↂ" + (if (value * 10000 == 1.0) "" else format(value % 10000))
                    20000L -> "ↂↂ" + (if (value * 20000 == 1.0) "" else format(value % 10000))
                    30000L -> "ↂↂↂ" + (if (value * 30000 == 1.0) "" else format(value % 10000))
                    40000L -> "ↂↇ" + (if (value * 40000 == 1.0) "" else format(value % 10000))
                    50000L -> "ↇ" + (if (value * 50000 == 1.0) "" else format(value % 10000))
                    60000L -> "ↇↂ" + (if (value * 60000 == 1.0) "" else format(value % 10000))
                    70000L -> "ↇↂↂ" + (if (value * 70000 == 1.0) "" else format(value % 10000))
                    80000L -> "ↇↂↂↂ" + (if (value * 80000 == 1.0) "" else format(value % 10000))
                    90000L -> "ↂↈ" + (if (value * 90000 == 1.0) "" else format(value % 10000))
                    100000L -> "ↈ" + (if (value * 100000 == 1.0) "" else format(value % 100000))
                    200000L -> "ↈↈ" + (if (value * 200000 == 1.0) "" else format(value % 100000))
                    300000L -> "ↈↈↈ" + (if (value * 300000 == 1.0) "" else format(value % 100000))
                    400000L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 2, max_decimal = 2, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class Tamil : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "௦"
                    1L -> "௧"
                    2L -> "௨"
                    3L -> "௩"
                    4L -> "௪"
                    5L -> "௫"
                    6L -> "௬"
                    7L -> "௭"
                    8L -> "௮"
                    9L -> "௯"
                    in 10L..19L -> "௰" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 20L..99L -> format((value) / 10) + "௰" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 100L..199L -> "௱" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 200L..999L -> format((value) / 100) + "௱" + (if ((value) % 100 == 0L) "" else format(value % 100))
                    in 1000L..1999L -> "௲" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 2000L..999999L -> format((value) / 1000) + "௲" + (if ((value) % 1000 == 0L) "" else format(value % 1000))
                    in 1000000L..99999999L -> format((value) / 100000) + "௱௲" + (if ((value) % 100000 == 0L) "" else tamilThousands.format(value % 100000))
                    else -> {
                        if (value >= 100000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 2,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("tamil only supports numbers >= 0")
                        }
                    }
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 2, max_decimal = 2, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class TamilThousands : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 0L..999L -> tamil.format(value)
                    else -> {
                        if (value >= 1000.0) format((value).toLong() / 1000) + "௲" + (if ((value).toLong() % 1000 == 0L) "" else format(value % 1000)) else {
                            throw UnsupportedOperationException("tamilThousands only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 0L..999L -> tamil.format(value)
                    else -> {
                        if (value >= 1000L) format((value) / 1000) + "௲" + (if ((value) % 1000 == 0L) "" else format(value % 1000)) else {
                            throw UnsupportedOperationException("tamilThousands only supports numbers >= 0")
                        }
                    }
                }
            }
        }

        private inner class ZzDefault : NumberFormatter {
            override fun format(value: Double): String {
                return if (value >= 0.0) DecimalNumberFormatter(
                    min_integer = 1,
                    min_decimal = 0,
                    max_decimal = 0,
                    primary_grouping = 3,
                    secondary_grouping = 3,
                    symbols = language.numbers.symbols
                ).format(value) else {
                    throw UnsupportedOperationException("zzDefault only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                return if (value >= 0L) DecimalNumberFormatter(
                    min_integer = 1,
                    min_decimal = 0,
                    max_decimal = 0,
                    primary_grouping = 3,
                    secondary_grouping = 3,
                    symbols = language.numbers.symbols
                ).format(value) else {
                    throw UnsupportedOperationException("zzDefault only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

    }

    private val numberingSystemRules = NumberingSystemRules()

    private inner class OrdinalRules {
        val digitsOrdinal = DigitsOrdinal()

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
                ).format(value) + "." else {
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
                ).format(value) + "." else {
                    throw UnsupportedOperationException("digitsOrdinal only supports numbers >= 0")
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
                    ).format(value) + "."

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

    }

    private val ordinalRules = OrdinalRules()

    private inner class SpelloutRules {
        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumbering = SpelloutNumbering()

        val spelloutCardinal = SpelloutCardinal()

        val spelloutOrdinal = SpelloutOrdinal()

        private inner class SpelloutNumberingYear : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return if (value >= 0L) DecimalNumberFormatter(
                    min_integer = 1,
                    min_decimal = 0,
                    max_decimal = 0,
                    primary_grouping = 0,
                    secondary_grouping = 0,
                    symbols = language.numbers.symbols
                ).format(value) else {
                    throw UnsupportedOperationException("spelloutNumberingYear only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
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
                    return "−" + format(kotlin.math.abs(value))
                }
                return if (value >= 0.0) DecimalNumberFormatter(
                    min_integer = 1,
                    min_decimal = 0,
                    max_decimal = 1,
                    primary_grouping = 3,
                    secondary_grouping = 3,
                    symbols = language.numbers.symbols
                ).format(value) else {
                    throw UnsupportedOperationException("spelloutNumbering only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return if (value >= 0L) DecimalNumberFormatter(
                    min_integer = 1,
                    min_decimal = 0,
                    max_decimal = 1,
                    primary_grouping = 3,
                    secondary_grouping = 3,
                    symbols = language.numbers.symbols
                ).format(value) else {
                    throw UnsupportedOperationException("spelloutNumbering only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinal : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return if (value >= 0.0) DecimalNumberFormatter(
                    min_integer = 1,
                    min_decimal = 0,
                    max_decimal = 1,
                    primary_grouping = 3,
                    secondary_grouping = 3,
                    symbols = language.numbers.symbols
                ).format(value) else {
                    throw UnsupportedOperationException("spelloutCardinal only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return if (value >= 0L) DecimalNumberFormatter(
                    min_integer = 1,
                    min_decimal = 0,
                    max_decimal = 1,
                    primary_grouping = 3,
                    secondary_grouping = 3,
                    symbols = language.numbers.symbols
                ).format(value) else {
                    throw UnsupportedOperationException("spelloutCardinal only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinal : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return if (value >= 0.0) DecimalNumberFormatter(
                    min_integer = 1,
                    min_decimal = 0,
                    max_decimal = 1,
                    primary_grouping = 3,
                    secondary_grouping = 3,
                    symbols = language.numbers.symbols
                ).format(value) + "." else {
                    throw UnsupportedOperationException("spelloutOrdinal only supports numbers >= 0")
                }
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "−" + format(kotlin.math.abs(value))
                }
                return if (value >= 0L) DecimalNumberFormatter(
                    min_integer = 1,
                    min_decimal = 0,
                    max_decimal = 1,
                    primary_grouping = 3,
                    secondary_grouping = 3,
                    symbols = language.numbers.symbols
                ).format(value) + "." else {
                    throw UnsupportedOperationException("spelloutOrdinal only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> DecimalNumberFormatter(
                        min_integer = 1,
                        min_decimal = 0,
                        max_decimal = 1,
                        primary_grouping = 3,
                        secondary_grouping = 3,
                        symbols = language.numbers.symbols
                    ).format(value) + "."

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

    }

    private val spelloutRules = SpelloutRules()
    open val armenianLower: NumberFormatter
        get() = numberingSystemRules.armenianLower
    open val armenianUpper: NumberFormatter
        get() = numberingSystemRules.armenianUpper
    open val cyrillicLower: NumberFormatter
        get() = numberingSystemRules.cyrillicLower
    open val ethiopic: NumberFormatter
        get() = numberingSystemRules.ethiopic
    open val georgian: NumberFormatter
        get() = numberingSystemRules.georgian
    open val greekLower: NumberFormatter
        get() = numberingSystemRules.greekLower
    open val greekUpper: NumberFormatter
        get() = numberingSystemRules.greekUpper
    open val hebrew: NumberFormatter
        get() = numberingSystemRules.hebrew
    open val hebrewItem: NumberFormatter
        get() = numberingSystemRules.hebrewItem
    open val romanLower: NumberFormatter
        get() = numberingSystemRules.romanLower
    open val romanUpper: NumberFormatter
        get() = numberingSystemRules.romanUpper
    open val tamil: NumberFormatter
        get() = numberingSystemRules.tamil
    open val zzDefault: NumberFormatter
        get() = numberingSystemRules.zzDefault
    open val digitsOrdinal: NumberFormatter
        get() = ordinalRules.digitsOrdinal
    override val spelloutNumberingYear: NumberFormatter
        get() = spelloutRules.spelloutNumberingYear
    override val spelloutNumbering: NumberFormatter
        get() = spelloutRules.spelloutNumbering
    override val spelloutCardinal: NumberFormatter
        get() = spelloutRules.spelloutCardinal
    override val spelloutOrdinal: NumberFormatter
        get() = spelloutRules.spelloutOrdinal
}
