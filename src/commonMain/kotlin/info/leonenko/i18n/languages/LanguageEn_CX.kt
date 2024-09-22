package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.en_CX
    get() = languageEn_CX
private val languageEn_CX = LanguageEn_CX()

class LanguageEn_CX internal constructor() : LanguageEn() {
    override val code: String = "en_CX"
    override val script: String? = null
    override val territory: String = "CX"

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
