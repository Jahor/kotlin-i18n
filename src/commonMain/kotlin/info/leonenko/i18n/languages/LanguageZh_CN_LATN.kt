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
val Language.Companion.zh_CN_LATN
   get() = languageZh_CN_LATN
private val languageZh_CN_LATN = LanguageZh_CN_LATN()
class LanguageZh_CN_LATN internal constructor() : LanguageZh_LATN() {
 override val code : String = "zh_CN_#Latn"
 override val script : String = "Latn"
 override val territory : String = "CN"
class EllipsisLanguage internal constructor() : LanguageZh_LATN.EllipsisLanguage() {
}
override val ellipsis : EllipsisLanguage
 get() = EllipsisLanguage()
class ListLanguage internal constructor(ellipsis : EllipsisLanguage) : LanguageZh_LATN.ListLanguage(ellipsis) {
}
override val list : ListLanguage
 get() = ListLanguage(ellipsis) 
 class MeasurementUnitLanguage internal constructor() : LanguageZh_LATN.MeasurementUnitLanguage() {
  }
override val measurementUnits : MeasurementUnitLanguage 
 get() = MeasurementUnitLanguage()
class NumberLanguage internal constructor() : LanguageZh_LATN.NumberLanguage() {
}
override val numbers : NumberLanguage
 get() = NumberLanguage() 
}
