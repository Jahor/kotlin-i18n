package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.hr_HR
    get() = languageHr_HR
private val languageHr_HR = LanguageHr_HR()

class LanguageHr_HR internal constructor() : LanguageHr() {
    override val code: String = "hr_HR"
    override val script: String? = null
    override val territory: String = "HR"

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
