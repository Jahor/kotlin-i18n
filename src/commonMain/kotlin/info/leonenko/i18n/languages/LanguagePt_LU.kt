package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.pt_LU
    get() = languagePt_LU
private val languagePt_LU = LanguagePt_LU()
class LanguagePt_LU internal constructor() : LanguagePt() {
    override val code: String = "pt_LU"
    override val script: String? = null
    override val territory: String = "LU"

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
