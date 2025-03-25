package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.uk_UA
    get() = languageUk_UA
private val languageUk_UA = LanguageUk_UA()
class LanguageUk_UA internal constructor() : LanguageUk() {
    override val code: String = "uk_UA"
    override val script: String? = null
    override val territory: String = "UA"

    class EllipsisLanguage internal constructor() : LanguageUk.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageUk.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageUk.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageUk.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
