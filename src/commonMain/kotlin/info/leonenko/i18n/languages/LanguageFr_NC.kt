package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.fr_NC
    get() = languageFr_NC
private val languageFr_NC = LanguageFr_NC()
class LanguageFr_NC internal constructor() : LanguageFr() {
    override val code: String = "fr_NC"
    override val script: String? = null
    override val territory: String = "NC"

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
