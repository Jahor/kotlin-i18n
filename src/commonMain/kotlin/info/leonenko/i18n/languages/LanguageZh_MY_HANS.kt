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
val Language.Companion.zh_MY_HANS
   get() = languageZh_MY_HANS
private val languageZh_MY_HANS = LanguageZh_MY_HANS()
class LanguageZh_MY_HANS internal constructor() : LanguageZh_HANS() {
 override val code : String = "zh_MY_#Hans"
 override val script : String = "Hans"
 override val territory : String = "MY"
class EllipsisLanguage internal constructor() : LanguageZh_HANS.EllipsisLanguage() {
}
override val ellipsis : EllipsisLanguage
 get() = EllipsisLanguage()
class ListLanguage internal constructor(ellipsis : EllipsisLanguage) : LanguageZh_HANS.ListLanguage(ellipsis) {
}
override val list : ListLanguage
 get() = ListLanguage(ellipsis) 
 class MeasurementUnitLanguage internal constructor() : LanguageZh_HANS.MeasurementUnitLanguage() {
  }
override val measurementUnits : MeasurementUnitLanguage 
 get() = MeasurementUnitLanguage()
class NumberLanguage internal constructor() : LanguageZh_HANS.NumberLanguage() {
}
override val numbers : NumberLanguage
 get() = NumberLanguage() 
}
