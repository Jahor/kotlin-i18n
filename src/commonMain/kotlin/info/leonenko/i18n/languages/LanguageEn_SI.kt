package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.en_SI
    get() = languageEn_SI
private val languageEn_SI = LanguageEn_SI()
class LanguageEn_SI internal constructor() : LanguageEn() {
    override val code: String = "en_SI"
    override val script: String? = null
    override val territory: String = "SI"

    class EllipsisLanguage internal constructor() : LanguageEn.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEn.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEn.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageEn.NumberLanguage() {
        override val symbols = LanguageEn.NumberLanguage().symbols.copy(
            decimalSymbol = ",",
            groupSymbol = ".",
            exponentialSymbol = "e",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
