package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.fr_YT
    get() = languageFr_YT
private val languageFr_YT = LanguageFr_YT()
class LanguageFr_YT internal constructor() : LanguageFr() {
    override val code: String = "fr_YT"
    override val script: String? = null
    override val territory: String = "YT"

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
