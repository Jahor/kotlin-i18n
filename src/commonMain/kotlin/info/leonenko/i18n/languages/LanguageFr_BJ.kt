package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.fr_BJ
    get() = languageFr_BJ
private val languageFr_BJ = LanguageFr_BJ()

class LanguageFr_BJ internal constructor() : LanguageFr() {
    override val code: String = "fr_BJ"
    override val script: String? = null
    override val territory: String = "BJ"

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
