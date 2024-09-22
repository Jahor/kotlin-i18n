package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.fr_FR
    get() = languageFr_FR
private val languageFr_FR = LanguageFr_FR()

class LanguageFr_FR internal constructor() : LanguageFr() {
    override val code: String = "fr_FR"
    override val script: String? = null
    override val territory: String = "FR"

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
