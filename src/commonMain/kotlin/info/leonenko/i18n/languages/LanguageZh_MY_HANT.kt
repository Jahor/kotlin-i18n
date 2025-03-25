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
val Language.Companion.zh_MY_HANT
   get() = languageZh_MY_HANT
private val languageZh_MY_HANT = LanguageZh_MY_HANT()
class LanguageZh_MY_HANT internal constructor() : LanguageZh_HANT() {
 override val code : String = "zh_MY_#Hant"
 override val script : String = "Hant"
 override val territory : String = "MY"
class EllipsisLanguage internal constructor() : LanguageZh_HANT.EllipsisLanguage() {
}
override val ellipsis : EllipsisLanguage
 get() = EllipsisLanguage()
class ListLanguage internal constructor(ellipsis : EllipsisLanguage) : LanguageZh_HANT.ListLanguage(ellipsis) {
}
override val list : ListLanguage
 get() = ListLanguage(ellipsis) 
 class MeasurementUnitLanguage internal constructor() : LanguageZh_HANT.MeasurementUnitLanguage() {
  }
override val measurementUnits : MeasurementUnitLanguage 
 get() = MeasurementUnitLanguage()
class NumberLanguage internal constructor() : LanguageZh_HANT.NumberLanguage() {
}
override val numbers : NumberLanguage
 get() = NumberLanguage() 
}
