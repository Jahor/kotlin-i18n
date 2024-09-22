package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.fr_TN
    get() = languageFr_TN
private val languageFr_TN = LanguageFr_TN()

class LanguageFr_TN internal constructor() : LanguageFr() {
    override val code: String = "fr_TN"
    override val script: String? = null
    override val territory: String = "TN"

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
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
