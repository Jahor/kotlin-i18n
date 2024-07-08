package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.es_ES
    get() = languageEs_ES
private val languageEs_ES = LanguageEs_ES()
class LanguageEs_ES internal constructor() : LanguageEs() {
    override val code: String = "es_ES"
    override val script: String? = null
    override val territory: String = "ES"

    class EllipsisLanguage internal constructor() : LanguageEs.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEs.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEs.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageEs.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
