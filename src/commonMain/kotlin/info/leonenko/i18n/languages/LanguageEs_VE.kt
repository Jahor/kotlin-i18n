package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.es_VE
    get() = languageEs_VE
private val languageEs_VE = LanguageEs_VE()
class LanguageEs_VE internal constructor() : LanguageEs() {
    override val code: String = "es_VE"
    override val script: String? = null
    override val territory: String = "VE"

    class EllipsisLanguage internal constructor() : LanguageEs.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEs.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEs.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageEs.NumberLanguage() {
        override val symbols = LanguageEs.NumberLanguage().symbols.copy(
            decimalSymbol = ",",
            groupSymbol = ".",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
