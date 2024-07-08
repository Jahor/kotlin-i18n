package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.nl_NL
    get() = languageNl_NL
private val languageNl_NL = LanguageNl_NL()
class LanguageNl_NL internal constructor() : LanguageNl() {
    override val code: String = "nl_NL"
    override val script: String? = null
    override val territory: String = "NL"

    class EllipsisLanguage internal constructor() : LanguageNl.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageNl.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageNl.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageNl.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
