package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.be_BY
    get() = languageBe_BY
private val languageBe_BY = LanguageBe_BY()

class LanguageBe_BY internal constructor() : LanguageBe() {
    override val code: String = "be_BY"
    override val script: String? = null
    override val territory: String = "BY"

    class EllipsisLanguage internal constructor() : LanguageBe.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageBe.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageBe.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageBe.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
