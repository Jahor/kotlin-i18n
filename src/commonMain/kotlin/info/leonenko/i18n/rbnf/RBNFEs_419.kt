package info.leonenko.i18n.rbnf

import kotlin.math.roundToLong
import info.leonenko.i18n.DecimalNumberFormatter
import info.leonenko.i18n.Language
import info.leonenko.i18n.RBNF
import info.leonenko.i18n.NumberFormatter
import info.leonenko.i18n.Case
import info.leonenko.i18n.Gender
import info.leonenko.i18n.languages.es_419
import info.leonenko.i18n.formatFractionalPartByDigit
import info.leonenko.i18n.bestDenominatorOrNull
import info.leonenko.i18n.leadingFractionalZeroesCount
import info.leonenko.i18n.Plurals
import info.leonenko.i18n.until
import info.leonenko.i18n.contains


class RBNFEs_419(language: Language = Language.es_419) : RBNFEs(language) {
    private inner class OrdinalRules {
        val dordMascabbrev = DordMascabbrev()

        val digitsOrdinalMasculineAdjective = DigitsOrdinalMasculineAdjective()

        val digitsOrdinalMasculine = DigitsOrdinalMasculine()

        val digitsOrdinalFeminine = DigitsOrdinalFeminine()

        val digitsOrdinalMasculinePlural = DigitsOrdinalMasculinePlural()

        val digitsOrdinalFemininePlural = DigitsOrdinalFemininePlural()

        val digitsOrdinal = DigitsOrdinal()

        private inner class DordMascabbrev : NumberFormatter {
            override fun format(value: Double): String {
                return when (value.roundToLong()) {
                    0L -> "º"
                    1L -> "ᵉʳ"
                    2L -> "º"
                    3L -> "ᵉʳ"
                    in 4L..19L -> "º"
                    in 20L..99L -> format(value % 10)
                    else -> {
                        if (value >= 100.0) format(value % 100) else {
                            throw UnsupportedOperationException("dordMascabbrev only supports numbers >= 0")
                        }
                    }
                }
            }

            override fun format(value: Long): String {
                return when (value) {
                    0L -> "º"
                    1L -> "ᵉʳ"
                    2L -> "º"
                    3L -> "ᵉʳ"
                    in 4L..19L -> "º"
                    in 20L..99L -> format(value % 10)
                    else -> {
                        if (value >= 100L) format(value % 100) else {
                            throw UnsupportedOperationException("dordMascabbrev only supports numbers >= 0")
                        }
                    }
                }
            }

            fun formatF(value: Double): String {
                return when (listOf<Long>(0, 1, 2, 3, 4, 20, 100).bestDenominatorOrNull(value)!!) {
                    0L -> "º"
                    1L -> "ᵉʳ"
                    2L -> "º"
                    3L -> "ᵉʳ"
                    4L -> "º"
                    20L -> format(value % 10)
                    100L -> format(value % 100)
                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalMasculineAdjective : NumberFormatter {
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
                ).format(value) + dordMascabbrev.format(value) + "." else {
                    throw UnsupportedOperationException("digitsOrdinalMasculineAdjective only supports numbers >= 0")
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
                ).format(value) + dordMascabbrev.format(value) + "." else {
                    throw UnsupportedOperationException("digitsOrdinalMasculineAdjective only supports numbers >= 0")
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
                    ).format(value) + dordMascabbrev.format(value) + "."

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
                ).format(value) + "º." else {
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
                ).format(value) + "º." else {
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
                    ).format(value) + "º."

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
                ).format(value) + "ª." else {
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
                ).format(value) + "ª." else {
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
                    ).format(value) + "ª."

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalMasculinePlural : NumberFormatter {
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
                ).format(value) + "ᵒˢ." else {
                    throw UnsupportedOperationException("digitsOrdinalMasculinePlural only supports numbers >= 0")
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
                ).format(value) + "ᵒˢ." else {
                    throw UnsupportedOperationException("digitsOrdinalMasculinePlural only supports numbers >= 0")
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
                    ).format(value) + "ᵒˢ."

                    else -> throw IllegalStateException("It should not happen")
                }
            }
        }

        private inner class DigitsOrdinalFemininePlural : NumberFormatter {
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
                ).format(value) + "ᵃˢ." else {
                    throw UnsupportedOperationException("digitsOrdinalFemininePlural only supports numbers >= 0")
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
                ).format(value) + "ᵃˢ." else {
                    throw UnsupportedOperationException("digitsOrdinalFemininePlural only supports numbers >= 0")
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
                    ).format(value) + "ᵃˢ."

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
    override val digitsOrdinalMasculineAdjective: NumberFormatter
        get() = ordinalRules.digitsOrdinalMasculineAdjective
    override val digitsOrdinalMasculine: NumberFormatter
        get() = ordinalRules.digitsOrdinalMasculine
    override val digitsOrdinalFeminine: NumberFormatter
        get() = ordinalRules.digitsOrdinalFeminine
    override val digitsOrdinalMasculinePlural: NumberFormatter
        get() = ordinalRules.digitsOrdinalMasculinePlural
    override val digitsOrdinalFemininePlural: NumberFormatter
        get() = ordinalRules.digitsOrdinalFemininePlural
    override val digitsOrdinal: NumberFormatter
        get() = ordinalRules.digitsOrdinal
}
