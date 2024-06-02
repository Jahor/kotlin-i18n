package info.leonenko.i18n.units

import info.leonenko.i18n.*
import info.leonenko.i18n.languages.root

typealias MeasurementUnitValue = Double
typealias MeasurementUnitValueFormatter = (Gender?, Case, MeasurementUnitValue) -> String
typealias MeasurementUnitFormatters = MultiLengthGenderedPattern

fun Number.toMeasurementUnitValue(): MeasurementUnitValue = toDouble()

interface MeasurementUnit : Formattable {
    val value: MeasurementUnitValue
    fun formatters(language: MeasurementUnitLanguage): MeasurementUnitFormatters

    fun getBaseUnitValue(): MeasurementUnitValue

    val measurement: String
    val unitName: String

    fun format(
        language: Language,
        case: Case,
        length: FormatLength,
        format: MeasurementUnitValueFormatter = { _, _, v -> v.toString() }
    ): String {
        val formatters = formatters(language.measurementUnits)
        val formatted = format(formatters.gender, case, value)
        return formatters.formatter(case, length)
            .format(PluralString(formatted, language.pluralFormChooser.pluralForm(value)))
    }

    override fun format(language: Language, case: Case, length: FormatLength): String {
        return format(language, case, length) { _, _, v -> v.toString() }
    }

    fun toDebugString(): String {
        return try {
            format(Language.root, Case.Nominative, FormatLength.Long)
        } catch (e: NotImplementedError) {
            value.toString()
        }
    }
}
