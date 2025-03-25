package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.ru_BY
    get() = languageRu_BY
private val languageRu_BY = LanguageRu_BY()
class LanguageRu_BY internal constructor() : LanguageRu() {
    override val code: String = "ru_BY"
    override val script: String? = null
    override val territory: String = "BY"

    class EllipsisLanguage internal constructor() : LanguageRu.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageRu.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageRu.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageRu.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
