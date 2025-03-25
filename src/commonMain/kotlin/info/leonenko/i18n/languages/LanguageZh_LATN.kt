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
val Language.Companion.zh_LATN
   get() = languageZh_LATN
private val languageZh_LATN = LanguageZh_LATN()
open class LanguageZh_LATN internal constructor() : LanguageZh() {
 override val code : String = "zh#Latn"
 override val script : String = "Latn"
 override val territory : String? = null
open class EllipsisLanguage internal constructor() : LanguageZh.EllipsisLanguage() {
}
override val ellipsis : EllipsisLanguage
 get() = EllipsisLanguage()
open class ListLanguage internal constructor(ellipsis : EllipsisLanguage) : LanguageZh.ListLanguage(ellipsis) {
}
override val list : ListLanguage
 get() = ListLanguage(ellipsis) 
open  class MeasurementUnitLanguage internal constructor() : LanguageZh.MeasurementUnitLanguage() {
  }
override val measurementUnits : MeasurementUnitLanguage 
 get() = MeasurementUnitLanguage()
open class NumberLanguage internal constructor() : LanguageZh.NumberLanguage() {
}
override val numbers : NumberLanguage
 get() = NumberLanguage() 
}
