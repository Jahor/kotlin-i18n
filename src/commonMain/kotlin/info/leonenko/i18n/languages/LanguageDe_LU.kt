package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.de_LU
    get() = languageDe_LU
private val languageDe_LU = LanguageDe_LU()
class LanguageDe_LU internal constructor() : LanguageDe() {
    override val code: String = "de_LU"
    override val script: String? = null
    override val territory: String = "LU"

    class EllipsisLanguage internal constructor() : LanguageDe.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageDe.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageDe.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageDe.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
