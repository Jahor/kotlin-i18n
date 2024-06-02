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

val Language.Companion.enSHAW_GB
    get() = languageEnSHAW_GB
private val languageEnSHAW_GB = LanguageEnSHAW_GB()

class LanguageEnSHAW_GB internal constructor() : LanguageEnSHAW() {
    override val code: String = "en-Shaw_GB"
    override val script: String = "Shaw"
    override val territory: String = "GB"

    class EllipsisLanguage internal constructor() : LanguageEnSHAW.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEnSHAW.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEnSHAW.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageEnSHAW.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
