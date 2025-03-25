package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.en_MG
    get() = languageEn_MG
private val languageEn_MG = LanguageEn_MG()
class LanguageEn_MG internal constructor() : LanguageEn() {
    override val code: String = "en_MG"
    override val script: String? = null
    override val territory: String = "MG"

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
