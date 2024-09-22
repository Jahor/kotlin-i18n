package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.nl_SR
    get() = languageNl_SR
private val languageNl_SR = LanguageNl_SR()

class LanguageNl_SR internal constructor() : LanguageNl() {
    override val code: String = "nl_SR"
    override val script: String? = null
    override val territory: String = "SR"

    class EllipsisLanguage internal constructor() : LanguageNl.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageNl.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageNl.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageNl.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
