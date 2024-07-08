package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.zh_TW_HANT
    get() = languageZh_TW_HANT
private val languageZh_TW_HANT = LanguageZh_TW_HANT()

class LanguageZh_TW_HANT internal constructor() : LanguageZh_HANT() {
    override val code: String = "zh_TW_#Hant"
    override val script: String = "Hant"
    override val territory: String = "TW"

    class EllipsisLanguage internal constructor() : LanguageZh_HANT.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageZh_HANT.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageZh_HANT.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageZh_HANT.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
