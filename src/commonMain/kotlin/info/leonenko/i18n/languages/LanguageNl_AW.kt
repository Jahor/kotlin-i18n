package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.nl_AW
    get() = languageNl_AW
private val languageNl_AW = LanguageNl_AW()
class LanguageNl_AW internal constructor() : LanguageNl() {
    override val code: String = "nl_AW"
    override val script: String? = null
    override val territory: String = "AW"

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
