package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.es_CR
    get() = languageEs_CR
private val languageEs_CR = LanguageEs_CR()

class LanguageEs_CR internal constructor() : LanguageEs() {
    override val code: String = "es_CR"
    override val script: String? = null
    override val territory: String = "CR"

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
        override val symbols = LanguageEs.NumberLanguage().symbols.copy(
            decimalSymbol = ",",
            groupSymbol = " ",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
