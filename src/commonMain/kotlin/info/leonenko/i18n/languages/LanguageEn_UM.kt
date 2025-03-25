package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.en_UM
    get() = languageEn_UM
private val languageEn_UM = LanguageEn_UM()
class LanguageEn_UM internal constructor() : LanguageEn() {
    override val code: String = "en_UM"
    override val script: String? = null
    override val territory: String = "UM"

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
