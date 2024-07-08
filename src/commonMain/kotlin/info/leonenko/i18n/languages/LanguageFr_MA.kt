package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.fr_MA
    get() = languageFr_MA
private val languageFr_MA = LanguageFr_MA()
class LanguageFr_MA internal constructor() : LanguageFr() {
    override val code: String = "fr_MA"
    override val script: String? = null
    override val territory: String = "MA"

    class EllipsisLanguage internal constructor() : LanguageFr.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageFr.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageFr.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageFr.NumberLanguage() {
        override val symbols = LanguageFr.NumberLanguage().symbols.copy(
            groupSymbol = ".",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
