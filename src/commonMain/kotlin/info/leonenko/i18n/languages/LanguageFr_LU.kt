package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.fr_LU
    get() = languageFr_LU
private val languageFr_LU = LanguageFr_LU()

class LanguageFr_LU internal constructor() : LanguageFr() {
    override val code: String = "fr_LU"
    override val script: String? = null
    override val territory: String = "LU"

    class EllipsisLanguage internal constructor() : LanguageFr.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageFr.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageFr.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageFr.NumberLanguage() {
        override val symbols = LanguageFr.NumberLanguage().symbols.copy(
            groupSymbol = ".",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
