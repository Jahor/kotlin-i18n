package info.leonenko.i18n.languages
import info.leonenko.i18n.Language
import info.leonenko.i18n.PluralFormChooser
import info.leonenko.i18n.EllipsisFormatter
import info.leonenko.i18n.PatternedListFormatter
import info.leonenko.i18n.ListFormatter
import info.leonenko.i18n.MultiLengthPatternedListFormatter
import info.leonenko.i18n.MultiLengthGenderedPattern
import info.leonenko.i18n.NumberSymbols
import info.leonenko.i18n.GenderedPattern
import info.leonenko.i18n.PluralPattern
import info.leonenko.i18n.Plurals
import info.leonenko.i18n.Gender
import info.leonenko.i18n.Cases
import info.leonenko.i18n.RBNF
val Language.Companion.en_FR
   get() = languageEn_FR
private val languageEn_FR = LanguageEn_FR()
class LanguageEn_FR internal constructor() : LanguageEn() {
 override val code : String = "en_FR"
 override val script : String? = null
 override val territory : String = "FR"
class EllipsisLanguage internal constructor() : LanguageEn.EllipsisLanguage() {
}
override val ellipsis : EllipsisLanguage
 get() = EllipsisLanguage()
class ListLanguage internal constructor(ellipsis : EllipsisLanguage) : LanguageEn.ListLanguage(ellipsis) {
}
override val list : ListLanguage
 get() = ListLanguage(ellipsis) 
 class MeasurementUnitLanguage internal constructor() : LanguageEn.MeasurementUnitLanguage() {
  }
override val measurementUnits : MeasurementUnitLanguage 
 get() = MeasurementUnitLanguage()
class NumberLanguage internal constructor() : LanguageEn.NumberLanguage() {
  override val symbols = LanguageEn.NumberLanguage().symbols.copy(
    decimalSymbol = ",",
    groupSymbol = " ",
    )
}
override val numbers : NumberLanguage
 get() = NumberLanguage() 
}
