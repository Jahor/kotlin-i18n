package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.nl_BE
    get() = languageNl_BE
private val languageNl_BE = LanguageNl_BE()
class LanguageNl_BE internal constructor() : LanguageNl() {
    override val code: String = "nl_BE"
    override val script: String? = null
    override val territory: String = "BE"

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
