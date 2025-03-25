package info.leonenko.i18n.languages

import info.leonenko.i18n.*

val Language.Companion.es_PR
    get() = languageEs_PR
private val languageEs_PR = LanguageEs_PR()
class LanguageEs_PR internal constructor() : LanguageEs() {
    override val code: String = "es_PR"
    override val script: String? = null
    override val territory: String = "PR"

    class EllipsisLanguage internal constructor() : LanguageEs.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEs.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEs.MeasurementUnitLanguage() {
        override val temperatureFahrenheit
            get() = MultiLengthGenderedPattern(
                base = super.temperatureFahrenheit,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}°",
                                other = "{0}°",
                            )
                        ),
                    )
                ),
            )
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageEs.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
