package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.zh_MO_HANT
    get() = languageZh_MO_HANT
private val languageZh_MO_HANT = LanguageZh_MO_HANT()

class LanguageZh_MO_HANT internal constructor() : LanguageZh_HANT() {
    override val code: String = "zh_MO_#Hant"
    override val script: String = "Hant"
    override val territory: String = "MO"

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
