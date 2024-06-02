package info.leonenko.i18n

import info.leonenko.i18n.units.MeasurementUnit
import info.leonenko.i18n.units.MeasurementUnitValueFormatter

interface ListLanguage {
    val formatter: ListFormatter
    val orFormatter: ListFormatter
    val unitFormatter: ListFormatter
}

interface ListFormatter {
    fun <T, F : Formatter<T>> format(
        collection: Collection<T>,
        formatter: F,
        length: FormatLength,
        lengthLimit: Int = Int.MAX_VALUE
    ): String

    fun <T> format(
        collection: Collection<T>,
        length: FormatLength,
        lengthLimit: Int = Int.MAX_VALUE,
        transform: (T) -> String
    ): String {
        return format(collection, formatter = object : Formatter<T> {
            override fun format(value: T): String {
                return transform(value)
            }
        }, length, lengthLimit)
    }
}

data class PatternedListFormatter(
    val start: String,
    val middle: String = start,
    val end: String,
    val two: String = end,
    val ellipsis: EllipsisFormatter
) {
    private fun String.fmt(v0: String, v1: String): String = replace("{0}", v0).replace("{1}", v1)

    private fun format(
        formatted: List<String>,
        lengthLimit: Int = Int.MAX_VALUE
    ): String {
        // TODO implement limiting

        return when (formatted.size) {
            0 -> ""
            1 -> formatted.first()
            2 -> two.fmt(formatted[0], formatted[1])
            else -> {
                var result = start.fmt(formatted[0], formatted[1])
                for (i in 2..formatted.size - 2) {
                    val updated = middle.fmt(result, formatted[i])
                    if (lengthLimit < updated.length) {
                        return ellipsis.formatFinal(result)
                    } else {
                        result = updated
                    }
                }
                val updated = end.fmt(result, formatted.last())
                if (lengthLimit < updated.length) {
                    ellipsis.formatFinal(result)
                } else {
                    updated
                }
            }
        }
    }

    fun <T, F : Formatter<T>> format(
        collection: Collection<T>,
        formatter: F,
        lengthLimit: Int = Int.MAX_VALUE
    ): String = format(collection.map { formatter.format(it) }.filter { it != "" }, lengthLimit)
}

data class MultiLengthPatternedListFormatter(
    val long: PatternedListFormatter? = null,
    val short: PatternedListFormatter? = null,
    val narrow: PatternedListFormatter? = null,
) : ListFormatter {
    private fun listFormatter(length: FormatLength): PatternedListFormatter {
        return when (length) {
            FormatLength.Long -> long ?: (short ?: narrow)
            FormatLength.Short -> short ?: (long ?: narrow)
            FormatLength.Narrow -> short ?: (narrow ?: long)
        }!!
    }

    override fun <T, F : Formatter<T>> format(
        collection: Collection<T>,
        formatter: F,
        length: FormatLength,
        lengthLimit: Int
    ): String {
        return listFormatter(length).format(collection, formatter)
    }
}

fun <T : MeasurementUnit> Collection<T>.joinToStringLocalized(
    language: Language,
    case: Case = Case.Nominative,
    length: FormatLength = FormatLength.Long,
    lengthLimit: Int = Int.MAX_VALUE,
    transform: MeasurementUnitValueFormatter = { _, _, v -> v.toString() }
): String {
    return language.list.unitFormatter.format(
        this,
        object : Formatter<T> {
            override fun format(value: T): String =
                value.format(language, case, length, transform)
        },
        length, lengthLimit
    )
}

fun <T : MeasurementUnit> Language.joinToStringLocalized(
    collection: Collection<T>,
    case: Case = Case.Nominative,
    length: FormatLength = FormatLength.Long,
    lengthLimit: Int = Int.MAX_VALUE,
    transform: MeasurementUnitValueFormatter = { _, _, v -> v.toString() }
) = collection.joinToStringLocalized(
    this, case, length, lengthLimit, transform
)

fun <T> Collection<T>.joinToStringLocalized(
    language: Language,
    length: FormatLength = FormatLength.Long,
    lengthLimit: Int = Int.MAX_VALUE,
    transform: ((T) -> String) = { it.toString() }
): String {
    return language.list.formatter.format(
        this,
        object : Formatter<T> {
            override fun format(value: T): String = transform(value)
        },
        length, lengthLimit
    )
}

fun <T> Language.joinToStringLocalized(
    collection: Collection<T>,
    length: FormatLength = FormatLength.Long,
    lengthLimit: Int = Int.MAX_VALUE,
    transform: ((T) -> String) = { it.toString() }
) = collection.joinToStringLocalized(
    this, length, lengthLimit, transform
)

