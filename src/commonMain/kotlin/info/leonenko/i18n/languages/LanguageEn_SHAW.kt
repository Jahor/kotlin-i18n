package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.en_SHAW
    get() = languageEn_SHAW
private val languageEn_SHAW = LanguageEn_SHAW()
open class LanguageEn_SHAW internal constructor() : LanguageEn() {
    override val code: String = "en#Shaw"
    override val script: String = "Shaw"
    override val territory: String? = null

    open class EllipsisLanguage internal constructor() : LanguageEn.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    open class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEn.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    open class MeasurementUnitLanguage internal constructor() : LanguageEn.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    open class NumberLanguage internal constructor() : LanguageEn.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
