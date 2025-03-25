package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.en_KI
    get() = languageEn_KI
private val languageEn_KI = LanguageEn_KI()
class LanguageEn_KI internal constructor() : LanguageEn() {
    override val code: String = "en_KI"
    override val script: String? = null
    override val territory: String = "KI"

    class EllipsisLanguage internal constructor() : LanguageEn.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEn.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEn.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageEn.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
