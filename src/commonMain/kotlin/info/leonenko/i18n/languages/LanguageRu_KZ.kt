package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.ru_KZ
    get() = languageRu_KZ
private val languageRu_KZ = LanguageRu_KZ()
class LanguageRu_KZ internal constructor() : LanguageRu() {
    override val code: String = "ru_KZ"
    override val script: String? = null
    override val territory: String = "KZ"

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
