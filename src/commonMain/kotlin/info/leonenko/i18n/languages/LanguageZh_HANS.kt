package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.zh_HANS
    get() = languageZh_HANS
private val languageZh_HANS = LanguageZh_HANS()

open class LanguageZh_HANS internal constructor() : LanguageZh() {
    override val code: String = "zh#Hans"
    override val script: String = "Hans"
    override val territory: String? = null

    open class EllipsisLanguage internal constructor() : LanguageZh.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    open class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageZh.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    open class MeasurementUnitLanguage internal constructor() : LanguageZh.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    open class NumberLanguage internal constructor() : LanguageZh.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
