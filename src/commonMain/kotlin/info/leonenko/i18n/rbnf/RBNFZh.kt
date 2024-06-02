package info.leonenko.i18n.rbnf

import kotlin.math.roundToLong
import info.leonenko.i18n.DecimalNumberFormatter
import info.leonenko.i18n.Language
import info.leonenko.i18n.RBNF
import info.leonenko.i18n.NumberFormatter
import info.leonenko.i18n.Case
import info.leonenko.i18n.Gender
import info.leonenko.i18n.languages.zh
import info.leonenko.i18n.formatFractionalPartByDigit
import info.leonenko.i18n.bestDenominatorOrNull
import info.leonenko.i18n.leadingFractionalZeroesCount
import info.leonenko.i18n.Plurals
import info.leonenko.i18n.until
import info.leonenko.i18n.contains


open class RBNFZh(val language: Language = Language.zh) : RBNF {
    private inner class SpelloutRules {
        val spelloutNumberingYear = SpelloutNumberingYear()

        val spelloutNumberingYearDigits = SpelloutNumberingYearDigits()

        val spelloutNumberingDays = SpelloutNumberingDays()

        val numberingDays = NumberingDays()

        val spelloutNumbering = SpelloutNumbering()

        val number2 = Number2()

        val number3 = Number3()

        val number4 = Number4()

        val number5 = Number5()

        val number8 = Number8()

        val number13 = Number13()

        val spelloutCardinalFinancial = SpelloutCardinalFinancial()

        val financialnumber2 = Financialnumber2()

        val financialnumber3 = Financialnumber3()

        val financialnumber4 = Financialnumber4()

        val financialnumber5 = Financialnumber5()

        val financialnumber8 = Financialnumber8()

        val financialnumber13 = Financialnumber13()

        val spelloutCardinal = SpelloutCardinal()

        val cardinal2 = Cardinal2()

        val cardinal3 = Cardinal3()

        val cardinal4 = Cardinal4()

        val cardinal5 = Cardinal5()

        val cardinal8 = Cardinal8()

        val cardinal13 = Cardinal13()

        val spelloutCardinalAlternate2 = SpelloutCardinalAlternate2()

        val cardinalAlternate22 = CardinalAlternate22()

        val cardinalAlternate23 = CardinalAlternate23()

        val cardinalAlternate24 = CardinalAlternate24()

        val cardinalAlternate25 = CardinalAlternate25()

        val cardinalAlternate28 = CardinalAlternate28()

        val cardinalAlternate213 = CardinalAlternate213()

        val spelloutOrdinal = SpelloutOrdinal()

        private inner class SpelloutNumberingYear : NumberFormatter {
            override fun format(value: Double): String {
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 0L..999L -> spelloutNumbering.format(value)
                    in 1000L..9999L -> spelloutNumberingYearDigits.format(value)
                    else -> {
                        if (value >= 10000L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("spelloutNumberingYear only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1000, 10000).bestDenominatorOrNull(value)!!) {
                    0L -> spelloutNumbering.format(value)
                    1000L -> spelloutNumberingYearDigits.format(value)
                    10000L -> spelloutNumbering.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 1, max_decimal = 1, primary_grouping = 0, secondary_grouping = 0, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class SpelloutNumberingYearDigits : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 0L..9L -> spelloutNumbering.format(value)
                    in 10L..99L -> format((value).toLong() / 10) + spelloutNumbering.format(value % 10)
                    in 100L..999L -> format((value).toLong() / 100) + format((value % 100).toLong() / 10) + spelloutNumbering.format(value % 100 % 10)
                    else -> {
                        if (value >= 1000.0) format((value).toLong() / 1000) + format((value % 1000).toLong() / 100) + format((value % 1000 % 100).toLong() / 10) + spelloutNumbering.format(value % 1000 % 100 % 10) else {
                            throw UnsupportedOperationException("spelloutNumberingYearDigits only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 0L..9L -> spelloutNumbering.format(value)
                    in 10L..99L -> format((value) / 10) + spelloutNumbering.format(value % 10)
                    in 100L..999L -> format((value) / 100) + format((value % 100) / 10) + spelloutNumbering.format(value % 100 % 10)
                    else -> {
                        if (value >= 1000L) format((value) / 1000) + format((value % 1000) / 100) + format((value % 1000 % 100) / 10) + spelloutNumbering.format(value % 1000 % 100 % 10) else {
                            throw UnsupportedOperationException("spelloutNumberingYearDigits only supports numbers >= 0")
                        }
                    }
                }
            }
        }

        private inner class SpelloutNumberingDays : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "负" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "负" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "〇"
                    in 1L..10L -> "初" + spelloutNumbering.format(value)
                    in 11L..20L -> spelloutNumbering.format(value)
                    else -> {
                        if (value >= 21L) numberingDays.format(value) else {
                            throw UnsupportedOperationException("spelloutNumberingDays only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 11, 21).bestDenominatorOrNull(value)!!) {
                    0L -> "〇"
                    1L -> "初" + spelloutNumbering.format(value)
                    11L -> spelloutNumbering.format(value)
                    21L -> numberingDays.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 1, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(value)
            }
        }

        private inner class NumberingDays : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 0L..20L -> spelloutNumbering.format(value)
                    in 21L..29L -> "廿" + format(value % 10)
                    30L -> format((value).toLong() / 10) + "十"
                    in 31L..39L -> "丗" + format(value % 10)
                    40L -> format((value).toLong() / 10) + "十"
                    in 41L..49L -> "卌" + format(value % 10)
                    else -> {
                        if (value >= 50.0) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("numberingDays only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 0L..20L -> spelloutNumbering.format(value)
                    in 21L..29L -> "廿" + format(value % 10)
                    30L -> format((value) / 10) + "十"
                    in 31L..39L -> "丗" + format(value % 10)
                    40L -> format((value) / 10) + "十"
                    in 41L..49L -> "卌" + format(value % 10)
                    else -> {
                        if (value >= 50L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("numberingDays only supports numbers >= 0")
                        }
                    }
                }
            }
        }

        private inner class SpelloutNumbering : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "负" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "负" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "〇"
                    1L -> "一"
                    2L -> "二"
                    3L -> "三"
                    4L -> "四"
                    5L -> "五"
                    6L -> "六"
                    7L -> "七"
                    8L -> "八"
                    9L -> "九"
                    in 10L..19L -> "十" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 20L..99L -> format((value) / 10) + "十" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 100L..999L -> format((value) / 100) + "百" + (if ((value) % 100 == 0L) "" else number2.format(value % 100))
                    in 1000L..9999L -> format((value) / 1000) + "千" + (if ((value) % 1000 == 0L) "" else number3.format(value % 1000))
                    in 10000L..99999999L -> format((value) / 10000) + "万" + (if ((value) % 10000 == 0L) "" else number4.format(value % 10000))
                    in 100000000L..999999999999L -> format((value) / 100000000) + "亿" + (if ((value) % 100000000 == 0L) "" else number5.format(value % 100000000))
                    in 1000000000000L..9999999999999999L -> format((value) / 1000000000000) + "兆" + (if ((value) % 1000000000000 == 0L) "" else number8.format(value % 1000000000000))
                    in 10000000000000000L..999999999999999999L -> format((value) / 10000000000000000) + "京" + (if ((value) % 10000000000000000 == 0L) "" else number13.format(value % 10000000000000000))
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

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + "点" + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), "")
            }
        }

        private inner class Number2 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "〇" + spelloutNumbering.format(value)
                    in 10L..19L -> "一" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 20.0) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("number2 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "〇" + spelloutNumbering.format(value)
                    in 10L..19L -> "一" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 20L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("number2 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20).bestDenominatorOrNull(value)!!) {
                    1L -> "〇" + spelloutNumbering.format(value)
                    10L -> "一" + spelloutNumbering.format(value)
                    20L -> spelloutNumbering.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Number3 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "〇" + spelloutNumbering.format(value)
                    in 10L..19L -> "〇一" + spelloutNumbering.format(value)
                    in 20L..99L -> "〇" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 100.0) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("number3 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "〇" + spelloutNumbering.format(value)
                    in 10L..19L -> "〇一" + spelloutNumbering.format(value)
                    in 20L..99L -> "〇" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 100L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("number3 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 100).bestDenominatorOrNull(value)!!) {
                    1L -> "〇" + spelloutNumbering.format(value)
                    10L -> "〇一" + spelloutNumbering.format(value)
                    20L -> "〇" + spelloutNumbering.format(value)
                    100L -> spelloutNumbering.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Number4 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "〇" + spelloutNumbering.format(value)
                    in 10L..19L -> "〇一" + spelloutNumbering.format(value)
                    in 20L..999L -> "〇" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 1000.0) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("number4 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "〇" + spelloutNumbering.format(value)
                    in 10L..19L -> "〇一" + spelloutNumbering.format(value)
                    in 20L..999L -> "〇" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 1000L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("number4 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 1000).bestDenominatorOrNull(value)!!) {
                    1L -> "〇" + spelloutNumbering.format(value)
                    10L -> "〇一" + spelloutNumbering.format(value)
                    20L -> "〇" + spelloutNumbering.format(value)
                    1000L -> spelloutNumbering.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Number5 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "〇" + spelloutNumbering.format(value)
                    in 10L..19L -> "〇一" + spelloutNumbering.format(value)
                    in 20L..9999L -> "〇" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 10000.0) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("number5 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "〇" + spelloutNumbering.format(value)
                    in 10L..19L -> "〇一" + spelloutNumbering.format(value)
                    in 20L..9999L -> "〇" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 10000L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("number5 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 10000).bestDenominatorOrNull(value)!!) {
                    1L -> "〇" + spelloutNumbering.format(value)
                    10L -> "〇一" + spelloutNumbering.format(value)
                    20L -> "〇" + spelloutNumbering.format(value)
                    10000L -> spelloutNumbering.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Number8 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "〇" + spelloutNumbering.format(value)
                    in 10L..19L -> "〇一" + spelloutNumbering.format(value)
                    in 20L..9999999L -> "〇" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 10000000.0) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("number8 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "〇" + spelloutNumbering.format(value)
                    in 10L..19L -> "〇一" + spelloutNumbering.format(value)
                    in 20L..9999999L -> "〇" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 10000000L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("number8 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 10000000).bestDenominatorOrNull(value)!!) {
                    1L -> "〇" + spelloutNumbering.format(value)
                    10L -> "〇一" + spelloutNumbering.format(value)
                    20L -> "〇" + spelloutNumbering.format(value)
                    10000000L -> spelloutNumbering.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Number13 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "〇" + spelloutNumbering.format(value)
                    in 10L..19L -> "〇一" + spelloutNumbering.format(value)
                    in 20L..999999999999L -> "〇" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 1000000000000.0) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("number13 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "〇" + spelloutNumbering.format(value)
                    in 10L..19L -> "〇一" + spelloutNumbering.format(value)
                    in 20L..999999999999L -> "〇" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 1000000000000L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("number13 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 1000000000000).bestDenominatorOrNull(value)!!) {
                    1L -> "〇" + spelloutNumbering.format(value)
                    10L -> "〇一" + spelloutNumbering.format(value)
                    20L -> "〇" + spelloutNumbering.format(value)
                    1000000000000L -> spelloutNumbering.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalFinancial : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "负" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "负" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "零"
                    1L -> "壹"
                    2L -> "贰"
                    3L -> "叁"
                    4L -> "肆"
                    5L -> "伍"
                    6L -> "陆"
                    7L -> "柒"
                    8L -> "捌"
                    9L -> "玖"
                    in 10L..19L -> "拾" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 20L..99L -> format((value) / 10) + "拾" + (if ((value) % 10 == 0L) "" else format(value % 10))
                    in 100L..999L -> format((value) / 100) + "佰" + (if ((value) % 100 == 0L) "" else financialnumber2.format(value % 100))
                    in 1000L..9999L -> format((value) / 1000) + "仟" + (if ((value) % 1000 == 0L) "" else financialnumber3.format(value % 1000))
                    in 10000L..99999999L -> format((value) / 10000) + "万" + (if ((value) % 10000 == 0L) "" else financialnumber4.format(value % 10000))
                    in 100000000L..999999999999L -> format((value) / 100000000) + "亿" + (if ((value) % 100000000 == 0L) "" else financialnumber5.format(value % 100000000))
                    in 1000000000000L..9999999999999999L -> format((value) / 1000000000000) + "兆" + (if ((value) % 1000000000000 == 0L) "" else financialnumber8.format(value % 1000000000000))
                    in 10000000000000000L..999999999999999999L -> format((value) / 10000000000000000) + "京" + (if ((value) % 10000000000000000 == 0L) "" else financialnumber13.format(value % 10000000000000000))
                    else -> {
                        if (value >= 1000000000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalFinancial only supports numbers >= 0")
                        }
                    }
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return format(kotlin.math.truncate(value).toLong()) + "点" + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), "")
            }
        }

        private inner class Financialnumber2 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutCardinalFinancial.format(value)
                    in 10L..19L -> "壹" + spelloutCardinalFinancial.format(value)
                    else -> {
                        if (value >= 20.0) spelloutCardinalFinancial.format(value) else {
                            throw UnsupportedOperationException("financialnumber2 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutCardinalFinancial.format(value)
                    in 10L..19L -> "壹" + spelloutCardinalFinancial.format(value)
                    else -> {
                        if (value >= 20L) spelloutCardinalFinancial.format(value) else {
                            throw UnsupportedOperationException("financialnumber2 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutCardinalFinancial.format(value)
                    10L -> "壹" + spelloutCardinalFinancial.format(value)
                    20L -> spelloutCardinalFinancial.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Financialnumber3 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutCardinalFinancial.format(value)
                    in 10L..19L -> "零壹" + spelloutCardinalFinancial.format(value)
                    in 20L..99L -> "零" + spelloutCardinalFinancial.format(value)
                    else -> {
                        if (value >= 100.0) spelloutCardinalFinancial.format(value) else {
                            throw UnsupportedOperationException("financialnumber3 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutCardinalFinancial.format(value)
                    in 10L..19L -> "零壹" + spelloutCardinalFinancial.format(value)
                    in 20L..99L -> "零" + spelloutCardinalFinancial.format(value)
                    else -> {
                        if (value >= 100L) spelloutCardinalFinancial.format(value) else {
                            throw UnsupportedOperationException("financialnumber3 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 100).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutCardinalFinancial.format(value)
                    10L -> "零壹" + spelloutCardinalFinancial.format(value)
                    20L -> "零" + spelloutCardinalFinancial.format(value)
                    100L -> spelloutCardinalFinancial.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Financialnumber4 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutCardinalFinancial.format(value)
                    in 10L..19L -> "零壹" + spelloutCardinalFinancial.format(value)
                    in 20L..999L -> "零" + spelloutCardinalFinancial.format(value)
                    else -> {
                        if (value >= 1000.0) spelloutCardinalFinancial.format(value) else {
                            throw UnsupportedOperationException("financialnumber4 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutCardinalFinancial.format(value)
                    in 10L..19L -> "零壹" + spelloutCardinalFinancial.format(value)
                    in 20L..999L -> "零" + spelloutCardinalFinancial.format(value)
                    else -> {
                        if (value >= 1000L) spelloutCardinalFinancial.format(value) else {
                            throw UnsupportedOperationException("financialnumber4 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 1000).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutCardinalFinancial.format(value)
                    10L -> "零壹" + spelloutCardinalFinancial.format(value)
                    20L -> "零" + spelloutCardinalFinancial.format(value)
                    1000L -> spelloutCardinalFinancial.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Financialnumber5 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutCardinalFinancial.format(value)
                    in 10L..19L -> "零壹" + spelloutCardinalFinancial.format(value)
                    in 20L..9999L -> "零" + spelloutCardinalFinancial.format(value)
                    else -> {
                        if (value >= 10000.0) spelloutCardinalFinancial.format(value) else {
                            throw UnsupportedOperationException("financialnumber5 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutCardinalFinancial.format(value)
                    in 10L..19L -> "零壹" + spelloutCardinalFinancial.format(value)
                    in 20L..9999L -> "零" + spelloutCardinalFinancial.format(value)
                    else -> {
                        if (value >= 10000L) spelloutCardinalFinancial.format(value) else {
                            throw UnsupportedOperationException("financialnumber5 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 10000).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutCardinalFinancial.format(value)
                    10L -> "零壹" + spelloutCardinalFinancial.format(value)
                    20L -> "零" + spelloutCardinalFinancial.format(value)
                    10000L -> spelloutCardinalFinancial.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Financialnumber8 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutCardinalFinancial.format(value)
                    in 10L..19L -> "零壹" + spelloutCardinalFinancial.format(value)
                    in 20L..9999999L -> "零" + spelloutCardinalFinancial.format(value)
                    else -> {
                        if (value >= 10000000.0) spelloutCardinalFinancial.format(value) else {
                            throw UnsupportedOperationException("financialnumber8 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutCardinalFinancial.format(value)
                    in 10L..19L -> "零壹" + spelloutCardinalFinancial.format(value)
                    in 20L..9999999L -> "零" + spelloutCardinalFinancial.format(value)
                    else -> {
                        if (value >= 10000000L) spelloutCardinalFinancial.format(value) else {
                            throw UnsupportedOperationException("financialnumber8 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 10000000).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutCardinalFinancial.format(value)
                    10L -> "零壹" + spelloutCardinalFinancial.format(value)
                    20L -> "零" + spelloutCardinalFinancial.format(value)
                    10000000L -> spelloutCardinalFinancial.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Financialnumber13 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutCardinalFinancial.format(value)
                    in 10L..19L -> "零壹" + spelloutCardinalFinancial.format(value)
                    in 20L..999999999999L -> "零" + spelloutCardinalFinancial.format(value)
                    else -> {
                        if (value >= 1000000000000.0) spelloutCardinalFinancial.format(value) else {
                            throw UnsupportedOperationException("financialnumber13 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutCardinalFinancial.format(value)
                    in 10L..19L -> "零壹" + spelloutCardinalFinancial.format(value)
                    in 20L..999999999999L -> "零" + spelloutCardinalFinancial.format(value)
                    else -> {
                        if (value >= 1000000000000L) spelloutCardinalFinancial.format(value) else {
                            throw UnsupportedOperationException("financialnumber13 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 1000000000000).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutCardinalFinancial.format(value)
                    10L -> "零壹" + spelloutCardinalFinancial.format(value)
                    20L -> "零" + spelloutCardinalFinancial.format(value)
                    1000000000000L -> spelloutCardinalFinancial.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinal : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "负" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "负" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "零"
                    1L -> "一"
                    2L -> "二"
                    3L -> "三"
                    4L -> "四"
                    5L -> "五"
                    6L -> "六"
                    7L -> "七"
                    8L -> "八"
                    9L -> "九"
                    in 10L..99L -> spelloutNumbering.format(value)
                    in 100L..999L -> format((value) / 100) + "百" + (if ((value) % 100 == 0L) "" else cardinal2.format(value % 100))
                    in 1000L..9999L -> format((value) / 1000) + "千" + (if ((value) % 1000 == 0L) "" else cardinal3.format(value % 1000))
                    in 10000L..99999999L -> format((value) / 10000) + "万" + (if ((value) % 10000 == 0L) "" else cardinal4.format(value % 10000))
                    in 100000000L..999999999999L -> format((value) / 100000000) + "亿" + (if ((value) % 100000000 == 0L) "" else cardinal5.format(value % 100000000))
                    in 1000000000000L..9999999999999999L -> format((value) / 1000000000000) + "兆" + (if ((value) % 1000000000000 == 0L) "" else cardinal8.format(value % 1000000000000))
                    in 10000000000000000L..999999999999999999L -> format((value) / 10000000000000000) + "京" + (if ((value) % 10000000000000000 == 0L) "" else cardinal13.format(value % 10000000000000000))
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
                return format(kotlin.math.truncate(value).toLong()) + "点" + formatFractionalPartByDigit(kotlin.math.abs(value) - kotlin.math.truncate(kotlin.math.abs(value)), "")
            }
        }

        private inner class Cardinal2 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "一" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 20.0) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("cardinal2 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "一" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 20L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("cardinal2 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutNumbering.format(value)
                    10L -> "一" + spelloutNumbering.format(value)
                    20L -> spelloutNumbering.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Cardinal3 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinal.format(value)
                    in 20L..99L -> "零" + spelloutCardinal.format(value)
                    else -> {
                        if (value >= 100.0) spelloutCardinal.format(value) else {
                            throw UnsupportedOperationException("cardinal3 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinal.format(value)
                    in 20L..99L -> "零" + spelloutCardinal.format(value)
                    else -> {
                        if (value >= 100L) spelloutCardinal.format(value) else {
                            throw UnsupportedOperationException("cardinal3 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 100).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutNumbering.format(value)
                    10L -> "零一" + spelloutCardinal.format(value)
                    20L -> "零" + spelloutCardinal.format(value)
                    100L -> spelloutCardinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Cardinal4 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinal.format(value)
                    in 20L..999L -> "零" + spelloutCardinal.format(value)
                    else -> {
                        if (value >= 1000.0) spelloutCardinal.format(value) else {
                            throw UnsupportedOperationException("cardinal4 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinal.format(value)
                    in 20L..999L -> "零" + spelloutCardinal.format(value)
                    else -> {
                        if (value >= 1000L) spelloutCardinal.format(value) else {
                            throw UnsupportedOperationException("cardinal4 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 1000).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutNumbering.format(value)
                    10L -> "零一" + spelloutCardinal.format(value)
                    20L -> "零" + spelloutCardinal.format(value)
                    1000L -> spelloutCardinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Cardinal5 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinal.format(value)
                    in 20L..9999L -> "零" + spelloutCardinal.format(value)
                    else -> {
                        if (value >= 10000.0) spelloutCardinal.format(value) else {
                            throw UnsupportedOperationException("cardinal5 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinal.format(value)
                    in 20L..9999L -> "零" + spelloutCardinal.format(value)
                    else -> {
                        if (value >= 10000L) spelloutCardinal.format(value) else {
                            throw UnsupportedOperationException("cardinal5 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 10000).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutNumbering.format(value)
                    10L -> "零一" + spelloutCardinal.format(value)
                    20L -> "零" + spelloutCardinal.format(value)
                    10000L -> spelloutCardinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Cardinal8 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinal.format(value)
                    in 20L..9999999L -> "零" + spelloutCardinal.format(value)
                    else -> {
                        if (value >= 10000000.0) spelloutCardinal.format(value) else {
                            throw UnsupportedOperationException("cardinal8 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinal.format(value)
                    in 20L..9999999L -> "零" + spelloutCardinal.format(value)
                    else -> {
                        if (value >= 10000000L) spelloutCardinal.format(value) else {
                            throw UnsupportedOperationException("cardinal8 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 10000000).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutNumbering.format(value)
                    10L -> "零一" + spelloutCardinal.format(value)
                    20L -> "零" + spelloutCardinal.format(value)
                    10000000L -> spelloutCardinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class Cardinal13 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinal.format(value)
                    in 20L..999999999999L -> "零" + spelloutCardinal.format(value)
                    else -> {
                        if (value >= 1000000000000.0) spelloutCardinal.format(value) else {
                            throw UnsupportedOperationException("cardinal13 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinal.format(value)
                    in 20L..999999999999L -> "零" + spelloutCardinal.format(value)
                    else -> {
                        if (value >= 1000000000000L) spelloutCardinal.format(value) else {
                            throw UnsupportedOperationException("cardinal13 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 1000000000000).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutNumbering.format(value)
                    10L -> "零一" + spelloutCardinal.format(value)
                    20L -> "零" + spelloutCardinal.format(value)
                    1000000000000L -> spelloutCardinal.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutCardinalAlternate2 : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "负" + format(kotlin.math.abs(value))
                }
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                if (value < 0L) {
                    return "负" + format(kotlin.math.abs(value))
                }
                return when (value) {
                    0L -> "零"
                    1L -> "一"
                    2L -> "两"
                    3L -> "三"
                    4L -> "四"
                    5L -> "五"
                    6L -> "六"
                    7L -> "七"
                    8L -> "八"
                    9L -> "九"
                    in 10L..99L -> spelloutNumbering.format(value)
                    in 100L..999L -> format((value) / 100) + "百" + (if ((value) % 100 == 0L) "" else cardinalAlternate22.format(value % 100))
                    in 1000L..9999L -> format((value) / 1000) + "千" + (if ((value) % 1000 == 0L) "" else cardinalAlternate23.format(value % 1000))
                    in 10000L..99999999L -> format((value) / 10000) + "万" + (if ((value) % 10000 == 0L) "" else cardinalAlternate24.format(value % 10000))
                    in 100000000L..999999999999L -> format((value) / 100000000) + "亿" + (if ((value) % 100000000 == 0L) "" else cardinalAlternate25.format(value % 100000000))
                    in 1000000000000L..9999999999999999L -> format((value) / 1000000000000) + "兆" + (if ((value) % 1000000000000 == 0L) "" else cardinalAlternate28.format(value % 1000000000000))
                    in 10000000000000000L..999999999999999999L -> format((value) / 10000000000000000) + "京" + (if ((value) % 10000000000000000 == 0L) "" else cardinalAlternate213.format(value % 10000000000000000))
                    else -> {
                        if (value >= 1000000000000000000L) DecimalNumberFormatter(
                            min_integer = 1,
                            min_decimal = 0,
                            max_decimal = 0,
                            primary_grouping = 3,
                            secondary_grouping = 3,
                            symbols = language.numbers.symbols
                        ).format(value) else {
                            throw UnsupportedOperationException("spelloutCardinalAlternate2 only supports numbers >= 0")
                        }
                    }
                }
            }

            private fun formatImproperFraction(value: Double): String {
                return spelloutCardinal.format(value)
            }
        }

        private inner class CardinalAlternate22 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "一" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 20.0) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("cardinalAlternate22 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "一" + spelloutNumbering.format(value)
                    else -> {
                        if (value >= 20L) spelloutNumbering.format(value) else {
                            throw UnsupportedOperationException("cardinalAlternate22 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutNumbering.format(value)
                    10L -> "一" + spelloutNumbering.format(value)
                    20L -> spelloutNumbering.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class CardinalAlternate23 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinalAlternate2.format(value)
                    in 20L..99L -> "零" + spelloutCardinalAlternate2.format(value)
                    else -> {
                        if (value >= 100.0) spelloutCardinalAlternate2.format(value) else {
                            throw UnsupportedOperationException("cardinalAlternate23 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinalAlternate2.format(value)
                    in 20L..99L -> "零" + spelloutCardinalAlternate2.format(value)
                    else -> {
                        if (value >= 100L) spelloutCardinalAlternate2.format(value) else {
                            throw UnsupportedOperationException("cardinalAlternate23 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 100).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutNumbering.format(value)
                    10L -> "零一" + spelloutCardinalAlternate2.format(value)
                    20L -> "零" + spelloutCardinalAlternate2.format(value)
                    100L -> spelloutCardinalAlternate2.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class CardinalAlternate24 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinalAlternate2.format(value)
                    in 20L..999L -> "零" + spelloutCardinalAlternate2.format(value)
                    else -> {
                        if (value >= 1000.0) spelloutCardinalAlternate2.format(value) else {
                            throw UnsupportedOperationException("cardinalAlternate24 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinalAlternate2.format(value)
                    in 20L..999L -> "零" + spelloutCardinalAlternate2.format(value)
                    else -> {
                        if (value >= 1000L) spelloutCardinalAlternate2.format(value) else {
                            throw UnsupportedOperationException("cardinalAlternate24 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 1000).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutNumbering.format(value)
                    10L -> "零一" + spelloutCardinalAlternate2.format(value)
                    20L -> "零" + spelloutCardinalAlternate2.format(value)
                    1000L -> spelloutCardinalAlternate2.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class CardinalAlternate25 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinalAlternate2.format(value)
                    in 20L..9999L -> "零" + spelloutCardinalAlternate2.format(value)
                    else -> {
                        if (value >= 10000.0) spelloutCardinalAlternate2.format(value) else {
                            throw UnsupportedOperationException("cardinalAlternate25 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinalAlternate2.format(value)
                    in 20L..9999L -> "零" + spelloutCardinalAlternate2.format(value)
                    else -> {
                        if (value >= 10000L) spelloutCardinalAlternate2.format(value) else {
                            throw UnsupportedOperationException("cardinalAlternate25 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 10000).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutNumbering.format(value)
                    10L -> "零一" + spelloutCardinalAlternate2.format(value)
                    20L -> "零" + spelloutCardinalAlternate2.format(value)
                    10000L -> spelloutCardinalAlternate2.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class CardinalAlternate28 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinalAlternate2.format(value)
                    in 20L..9999999L -> "零" + spelloutCardinalAlternate2.format(value)
                    else -> {
                        if (value >= 10000000.0) spelloutCardinalAlternate2.format(value) else {
                            throw UnsupportedOperationException("cardinalAlternate28 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinalAlternate2.format(value)
                    in 20L..9999999L -> "零" + spelloutCardinalAlternate2.format(value)
                    else -> {
                        if (value >= 10000000L) spelloutCardinalAlternate2.format(value) else {
                            throw UnsupportedOperationException("cardinalAlternate28 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 10000000).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutNumbering.format(value)
                    10L -> "零一" + spelloutCardinalAlternate2.format(value)
                    20L -> "零" + spelloutCardinalAlternate2.format(value)
                    10000000L -> spelloutCardinalAlternate2.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class CardinalAlternate213 : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinalAlternate2.format(value)
                    in 20L..999999999999L -> "零" + spelloutCardinalAlternate2.format(value)
                    else -> {
                        if (value >= 1000000000000.0) spelloutCardinalAlternate2.format(value) else {
                            throw UnsupportedOperationException("cardinalAlternate213 only supports numbers >= 1")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    in 1L..9L -> "零" + spelloutNumbering.format(value)
                    in 10L..19L -> "零一" + spelloutCardinalAlternate2.format(value)
                    in 20L..999999999999L -> "零" + spelloutCardinalAlternate2.format(value)
                    else -> {
                        if (value >= 1000000000000L) spelloutCardinalAlternate2.format(value) else {
                            throw UnsupportedOperationException("cardinalAlternate213 only supports numbers >= 1")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(1, 10, 20, 1000000000000).bestDenominatorOrNull(value)!!) {
                    1L -> "零" + spelloutNumbering.format(value)
                    10L -> "零一" + spelloutCardinalAlternate2.format(value)
                    20L -> "零" + spelloutCardinalAlternate2.format(value)
                    1000000000000L -> spelloutCardinalAlternate2.format(value)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class SpelloutOrdinal : NumberFormatter {
            override fun format(value: Double): String {
                return formatImproperFraction(value)
            }

            override fun format(value: Long): String {
                return if (value >= 0L) "第" + spelloutNumbering.format(value) else {
                    throw UnsupportedOperationException("spelloutOrdinal only supports numbers >= 0")
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0).bestDenominatorOrNull(value)!!) {
                    0L -> "第" + spelloutNumbering.format(value)
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
        val digitsOrdinal = DigitsOrdinal()

        private inner class DigitsOrdinal : NumberFormatter {
            override fun format(value: Double): String {
                if (value < 0L) {
                    return "第−" + DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(
                        kotlin.math.abs(value)
                    )
                }
                return if (value >= 0.0) "第" + DecimalNumberFormatter(
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
                    return "第−" + DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(
                        kotlin.math.abs(value)
                    )
                }
                return if (value >= 0L) "第" + DecimalNumberFormatter(
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
                    0L -> "第" + DecimalNumberFormatter(min_integer = 1, min_decimal = 0, max_decimal = 0, primary_grouping = 3, secondary_grouping = 3, symbols = language.numbers.symbols).format(
                        value
                    )

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

    }

    private val ordinalRules = OrdinalRules()
    override val spelloutNumberingYear: NumberFormatter
        get() = spelloutRules.spelloutNumberingYear
    open val spelloutNumberingDays: NumberFormatter
        get() = spelloutRules.spelloutNumberingDays
    override val spelloutNumbering: NumberFormatter
        get() = spelloutRules.spelloutNumbering
    open val spelloutCardinalFinancial: NumberFormatter
        get() = spelloutRules.spelloutCardinalFinancial
    override val spelloutCardinal: NumberFormatter
        get() = spelloutRules.spelloutCardinal
    open val spelloutCardinalAlternate2: NumberFormatter
        get() = spelloutRules.spelloutCardinalAlternate2
    override val spelloutOrdinal: NumberFormatter
        get() = spelloutRules.spelloutOrdinal
    open val digitsOrdinal: NumberFormatter
        get() = ordinalRules.digitsOrdinal
}
