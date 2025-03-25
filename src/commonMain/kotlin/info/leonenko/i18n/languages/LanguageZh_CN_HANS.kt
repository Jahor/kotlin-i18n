package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.zh_CN_HANS
    get() = languageZh_CN_HANS
private val languageZh_CN_HANS = LanguageZh_CN_HANS()
class LanguageZh_CN_HANS internal constructor() : LanguageZh_HANS() {
    override val code: String = "zh_CN_#Hans"
    override val script: String = "Hans"
    override val territory: String = "CN"

    class EllipsisLanguage internal constructor() : LanguageZh_HANS.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageZh_HANS.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageZh_HANS.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageZh_HANS.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
