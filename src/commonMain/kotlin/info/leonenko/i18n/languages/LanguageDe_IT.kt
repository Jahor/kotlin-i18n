package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.de_IT
    get() = languageDe_IT
private val languageDe_IT = LanguageDe_IT()
class LanguageDe_IT internal constructor() : LanguageDe() {
    override val code: String = "de_IT"
    override val script: String? = null
    override val territory: String = "IT"

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
