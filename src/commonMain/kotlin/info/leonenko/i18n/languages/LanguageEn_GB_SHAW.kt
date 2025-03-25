package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.en_GB_SHAW
    get() = languageEn_GB_SHAW
private val languageEn_GB_SHAW = LanguageEn_GB_SHAW()
class LanguageEn_GB_SHAW internal constructor() : LanguageEn_SHAW() {
    override val code: String = "en_GB_#Shaw"
    override val script: String = "Shaw"
    override val territory: String = "GB"

    class EllipsisLanguage internal constructor() : LanguageEn_SHAW.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEn_SHAW.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEn_SHAW.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageEn_SHAW.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
