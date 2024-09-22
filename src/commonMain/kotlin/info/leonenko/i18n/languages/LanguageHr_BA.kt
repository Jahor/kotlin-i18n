package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.hr_BA
    get() = languageHr_BA
private val languageHr_BA = LanguageHr_BA()

class LanguageHr_BA internal constructor() : LanguageHr() {
    override val code: String = "hr_BA"
    override val script: String? = null
    override val territory: String = "BA"

    class EllipsisLanguage internal constructor() : LanguageHr.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageHr.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageHr.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageHr.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
