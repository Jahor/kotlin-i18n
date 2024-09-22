package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.fr_MF
    get() = languageFr_MF
private val languageFr_MF = LanguageFr_MF()

class LanguageFr_MF internal constructor() : LanguageFr() {
    override val code: String = "fr_MF"
    override val script: String? = null
    override val territory: String = "MF"

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
