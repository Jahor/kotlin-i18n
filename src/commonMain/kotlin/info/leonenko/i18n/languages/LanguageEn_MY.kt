package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.en_MY
    get() = languageEn_MY
private val languageEn_MY = LanguageEn_MY()

class LanguageEn_MY internal constructor() : LanguageEn() {
    override val code: String = "en_MY"
    override val script: String? = null
    override val territory: String = "MY"

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
