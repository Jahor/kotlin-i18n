package info.leonenko.i18n.languages

import info.leonenko.i18n.*

val Language.Companion.en_BS
    get() = languageEn_BS
private val languageEn_BS = LanguageEn_BS()
class LanguageEn_BS internal constructor() : LanguageEn() {
    override val code: String = "en_BS"
    override val script: String? = null
    override val territory: String = "BS"

    class EllipsisLanguage internal constructor() : LanguageEn.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEn.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEn.MeasurementUnitLanguage() {
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

    class NumberLanguage internal constructor() : LanguageEn.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
