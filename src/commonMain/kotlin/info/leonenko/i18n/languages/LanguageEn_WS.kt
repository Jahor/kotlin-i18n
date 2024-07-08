package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.en_WS
    get() = languageEn_WS
private val languageEn_WS = LanguageEn_WS()
class LanguageEn_WS internal constructor() : LanguageEn() {
    override val code: String = "en_WS"
    override val script: String? = null
    override val territory: String = "WS"

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
