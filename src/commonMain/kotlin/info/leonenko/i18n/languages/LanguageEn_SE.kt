package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.en_SE
    get() = languageEn_SE
private val languageEn_SE = LanguageEn_SE()

class LanguageEn_SE internal constructor() : LanguageEn() {
    override val code: String = "en_SE"
    override val script: String? = null
    override val territory: String = "SE"

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
            decimalSymbol = ",",
            groupSymbol = " ",
            exponentialSymbol = "×10^",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
