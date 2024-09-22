package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.en_FI
    get() = languageEn_FI
private val languageEn_FI = LanguageEn_FI()

class LanguageEn_FI internal constructor() : LanguageEn() {
    override val code: String = "en_FI"
    override val script: String? = null
    override val territory: String = "FI"

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
            timeSeparatorSymbol = ".",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
