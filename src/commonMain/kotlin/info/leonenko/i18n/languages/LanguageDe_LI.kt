package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.de_LI
    get() = languageDe_LI
private val languageDe_LI = LanguageDe_LI()

class LanguageDe_LI internal constructor() : LanguageDe() {
    override val code: String = "de_LI"
    override val script: String? = null
    override val territory: String = "LI"

    class EllipsisLanguage internal constructor() : LanguageDe.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageDe.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageDe.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageDe.NumberLanguage() {
        override val symbols = LanguageDe.NumberLanguage().symbols.copy(
            decimalSymbol = ".",
            groupSymbol = "’",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
