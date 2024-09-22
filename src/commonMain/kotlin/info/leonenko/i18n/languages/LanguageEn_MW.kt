package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.en_MW
    get() = languageEn_MW
private val languageEn_MW = LanguageEn_MW()

class LanguageEn_MW internal constructor() : LanguageEn() {
    override val code: String = "en_MW"
    override val script: String? = null
    override val territory: String = "MW"

    class EllipsisLanguage internal constructor() : LanguageEn.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEn.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEn.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageEn.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
