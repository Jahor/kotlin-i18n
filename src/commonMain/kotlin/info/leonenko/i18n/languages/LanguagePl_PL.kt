package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.pl_PL
    get() = languagePl_PL
private val languagePl_PL = LanguagePl_PL()

class LanguagePl_PL internal constructor() : LanguagePl() {
    override val code: String = "pl_PL"
    override val script: String? = null
    override val territory: String = "PL"

    class EllipsisLanguage internal constructor() : LanguagePl.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguagePl.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguagePl.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguagePl.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
