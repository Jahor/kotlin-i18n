package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.tr_CY
    get() = languageTr_CY
private val languageTr_CY = LanguageTr_CY()

class LanguageTr_CY internal constructor() : LanguageTr() {
    override val code: String = "tr_CY"
    override val script: String? = null
    override val territory: String = "CY"

    class EllipsisLanguage internal constructor() : LanguageTr.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageTr.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageTr.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageTr.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
