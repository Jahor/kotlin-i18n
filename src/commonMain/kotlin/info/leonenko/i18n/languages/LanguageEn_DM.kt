package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.en_DM
    get() = languageEn_DM
private val languageEn_DM = LanguageEn_DM()
class LanguageEn_DM internal constructor() : LanguageEn() {
    override val code: String = "en_DM"
    override val script: String? = null
    override val territory: String = "DM"

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
