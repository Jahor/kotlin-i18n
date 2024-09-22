package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.es_PH
    get() = languageEs_PH
private val languageEs_PH = LanguageEs_PH()

class LanguageEs_PH internal constructor() : LanguageEs() {
    override val code: String = "es_PH"
    override val script: String? = null
    override val territory: String = "PH"

    class EllipsisLanguage internal constructor() : LanguageEs.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEs.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEs.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageEs.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
