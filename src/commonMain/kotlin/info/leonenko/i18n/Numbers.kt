package info.leonenko.i18n

import info.leonenko.i18n.languages.root
import kotlin.math.abs
import kotlin.math.round
import kotlin.math.truncate

interface NumberFormatter {
    fun format(value: Double): String
    fun format(value: Float): String = format(value.toDouble())
    fun format(value: Int): String = format(value.toLong())
    fun format(value: Short): String = format(value.toLong())
    fun format(value: Byte): String = format(value.toLong())
    fun format(value: Long): String
}

// Partial implementation of https://unicode-org.github.io/icu-docs/apidoc/released/icu4c/classicu_1_1DecimalFormat.html
data class DecimalNumberFormatter(
    val min_integer: Int,
    val min_decimal: Int,
    val max_decimal: Int,
    val primary_grouping: Int,
    val secondary_grouping: Int,
    val symbols: NumberSymbols = NumberSymbols.root
) : NumberFormatter {

    private fun format(integer: Long, fractional: Double, negative: Boolean): String {
        var i = integer
        val iDigits = mutableListOf<String>()
        var k = 0
        while (i > 0 || k < min_integer) {
            iDigits.add((i % 10).toString())
            i /= 10
            k += 1
            if (primary_grouping != 0 && (k == primary_grouping || (k > primary_grouping && (k - primary_grouping) % secondary_grouping == 0))) {
                iDigits.add(symbols.groupSymbol)
            }
        }
        val iS = iDigits.reversed().joinToString("").removePrefix(symbols.groupSymbol)
//        var f = fractional
        val dDigits = mutableListOf<String>()
//        val multipler = 10
//        while ((f > 0 || dDigits.size < min_decimal) && dDigits.size < max_decimal) {
//            f *= 10
//            val d = if (dDigits.size == max_decimal - 1) round(f) else truncate(f)
//            dDigits.add(d.toLong().toString())
//            f -= d
//        }

        var multiplier = 10
        var left = fractional
        while ((left > 0 || dDigits.size < min_decimal) && dDigits.size < max_decimal) {
            val v = fractional * multiplier
            val integerPart = truncate(v)
            val d = (if (dDigits.size == max_decimal - 1) round(v) else truncate(v)).toLong() % 10
            dDigits.add(d.toString())
            multiplier *= 10
            left = v - integerPart
        }

        while (dDigits.lastOrNull() == "0" && dDigits.size > min_decimal) {
            dDigits.removeAt(dDigits.size - 1)
        }
        val dS = if (dDigits.isEmpty()) {
            ""
        } else {
            symbols.decimalSymbol + dDigits.joinToString("")
        }
        return (if (negative) symbols.minusSignSymbol else "") + iS + dS
    }

    override fun format(value: Double): String {
        val neg = value < 0
        val v = abs(value)
        val f = v - truncate(v)
        val i = (v - f).toLong()
        return format(i, f, neg)
    }

    override fun format(value: Long): String {
        return format(abs(value), 0.0, value < 0)
    }

    override fun toString(): String {
        var i = "0".repeat(min_integer)
        if (primary_grouping != 0) {
            i = "#," + "#".repeat(primary_grouping - min_integer) + i
        }
        if (secondary_grouping != 0 && secondary_grouping != primary_grouping) {
            i = "#," + "#".repeat(secondary_grouping - 1) + i
        }
        val f = "0".repeat(min_decimal) + "#".repeat(max_decimal - min_decimal)
        return if (f.isNotEmpty()) "$i.$f" else i
    }
}

data class NumberSymbols(
    val decimalSymbol: String = ".",
    val groupSymbol: String = "",
    val listSymbol: String = ",",
    val percentSignSymbol: String = "%",
    val plusSignSymbol: String = "+",
    val minusSignSymbol: String = "-",
    val exponentialSymbol: String = "e",
    val superscriptingExponentSymbol: String = "",
    val perMilleSymbol: String = "",
    val infinitySymbol: String = "",
    val nanSymbol: String = "",
    val approximatelySignSymbol: String = "",
    val timeSeparatorSymbol: String = "",
    val currencyDecimalSymbol: String = decimalSymbol,
    val currencyGroupSymbol: String = groupSymbol,
) {
    companion object {
        val root = Language.root.numbers.symbols
    }
}

interface NumberLanguage {
    val symbols: NumberSymbols
}

const val MinimumValue = 1e-8

fun Double.fractionalDigits(): List<Int> {
    val digits = mutableListOf<Int>()
    var multiplier = 1
    while ((this * multiplier).fractional() > MinimumValue) {
        multiplier *= 10
        val v = this * multiplier
        val integer = truncate(v)
        val digit = integer.toLong() % 10
        digits.add(digit.toInt())
    }
    return digits
}

fun NumberFormatter.formatFractionalPartByDigit(value: Double, separator: String): String =
    value.fractionalDigits().joinToString(separator) { format(it) }

// fun NumberFormatter.formatFractionalPartByDigit(value: Double, separator: String): String {
//    val digits = mutableListOf<String>()
//    var multiplier = 10
//    while (true) {
//        val v = value * multiplier
//        val integer = truncate(v)
//        val digit = integer.toLong() % 10
//        digits.add(format(digit))
//        multiplier *= 10
//        if (v - integer < MinimumValue) {
//            break
//        }
//    }
//    return digits.joinToString(separator)
// }

fun Double.leadingFractionalZeroesCount(): Int {
    var v = this
    var c = 0
    while (truncate(v) == 0.0 && v > MinimumValue) {
        v *= 10
        c += 1
    }
    return c - 1
}

fun Double.fractional(): Double = this - truncate(this)

fun Double.distanceToInteger(): Double = abs(this - round(this))

fun List<Long>.bestDenominatorOrNull(value: Double): Long? {
    val distances = map { it to (it * value).distanceToInteger() }
    return distances.withIndex().minWithOrNull { a, b ->
        if (a.value.second == b.value.second) {
            a.index.compareTo(b.index)
        } else {
            a.value.second.compareTo(b.value.second)
        }
    }?.value?.first
}

internal data class OpenEndRange<T>(val from: T, val to: T)
internal data class OpenStartRange<T>(val from: T, val to: T)
internal data class OpenRange<T>(val from: T, val to: T)

internal infix fun Double.until(to: Double) = OpenEndRange(this, to)
internal infix fun Float.until(to: Float) = OpenEndRange(this, to)

internal operator fun OpenEndRange<Double>.contains(f: Double) = from <= f && f < to
internal operator fun OpenEndRange<Float>.contains(f: Float) = from <= f && f < to
internal operator fun OpenStartRange<Double>.contains(f: Double) = from < f && f <= to
internal operator fun OpenStartRange<Float>.contains(f: Float) = from < f && f <= to
internal operator fun OpenRange<Double>.contains(f: Double) = from < f && f < to
internal operator fun OpenRange<Float>.contains(f: Float) = from < f && f < to