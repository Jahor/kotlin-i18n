package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.en_MV
    get() = languageEn_MV
private val languageEn_MV = LanguageEn_MV()
class LanguageEn_MV internal constructor() : LanguageEn() {
    override val code: String = "en_MV"
    override val script: String? = null
    override val territory: String = "MV"

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
