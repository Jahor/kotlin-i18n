package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.nl_SX
    get() = languageNl_SX
private val languageNl_SX = LanguageNl_SX()
class LanguageNl_SX internal constructor() : LanguageNl() {
    override val code: String = "nl_SX"
    override val script: String? = null
    override val territory: String = "SX"

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
