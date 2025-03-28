package info.leonenko.i18n.languages
import info.leonenko.i18n.Language
import info.leonenko.i18n.rbnf.RBNFFr_ch

val Language.Companion.fr_CH
    get() = languageFr_CH
private val languageFr_CH = LanguageFr_CH()
class LanguageFr_CH internal constructor() : LanguageFr() {
    override val code: String = "fr_CH"
    override val script: String? = null
    override val territory: String = "CH"
    override val rbnf: RBNFFr_ch
        get() = RBNFFr_ch(this)

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
            currencyDecimalSymbol = ".",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
