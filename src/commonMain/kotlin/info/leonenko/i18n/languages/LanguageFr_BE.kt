package info.leonenko.i18n.languages

import info.leonenko.i18n.Language
import info.leonenko.i18n.rbnf.RBNFFr_be

val Language.Companion.fr_BE
    get() = languageFr_BE
private val languageFr_BE = LanguageFr_BE()

class LanguageFr_BE internal constructor() : LanguageFr() {
    override val code: String = "fr_BE"
    override val script: String? = null
    override val territory: String = "BE"
    override val rbnf: RBNFFr_be
        get() = RBNFFr_be(this)

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
