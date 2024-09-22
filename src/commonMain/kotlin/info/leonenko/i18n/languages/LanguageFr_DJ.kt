package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.fr_DJ
    get() = languageFr_DJ
private val languageFr_DJ = LanguageFr_DJ()

class LanguageFr_DJ internal constructor() : LanguageFr() {
    override val code: String = "fr_DJ"
    override val script: String? = null
    override val territory: String = "DJ"

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
