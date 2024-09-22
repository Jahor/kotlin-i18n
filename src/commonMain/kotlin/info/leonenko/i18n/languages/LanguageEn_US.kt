package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.en_US
    get() = languageEn_US
private val languageEn_US = LanguageEn_US()

class LanguageEn_US internal constructor() : LanguageEn() {
    override val code: String = "en_US"
    override val script: String? = null
    override val territory: String = "US"

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
        override val symbols = LanguageEn.NumberLanguage().symbols.copy(
            perMilleSymbol = "0/00",
            infinitySymbol = "INF",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
