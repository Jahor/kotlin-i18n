package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.fr_NE
    get() = languageFr_NE
private val languageFr_NE = LanguageFr_NE()
class LanguageFr_NE internal constructor() : LanguageFr() {
    override val code: String = "fr_NE"
    override val script: String? = null
    override val territory: String = "NE"

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
