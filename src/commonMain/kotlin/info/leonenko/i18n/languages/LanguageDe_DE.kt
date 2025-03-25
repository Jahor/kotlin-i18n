package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.de_DE
    get() = languageDe_DE
private val languageDe_DE = LanguageDe_DE()
class LanguageDe_DE internal constructor() : LanguageDe() {
    override val code: String = "de_DE"
    override val script: String? = null
    override val territory: String = "DE"

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
