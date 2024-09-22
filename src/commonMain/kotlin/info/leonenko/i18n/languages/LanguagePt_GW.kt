package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.pt_GW
    get() = languagePt_GW
private val languagePt_GW = LanguagePt_GW()

class LanguagePt_GW internal constructor() : LanguagePt() {
    override val code: String = "pt_GW"
    override val script: String? = null
    override val territory: String = "GW"

    class EllipsisLanguage internal constructor() : LanguagePt.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguagePt.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguagePt.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguagePt.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
