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

val Language.Companion.zhHANT_MO
    get() = languageZhHANT_MO
private val languageZhHANT_MO = LanguageZhHANT_MO()

class LanguageZhHANT_MO internal constructor() : LanguageZhHANT() {
    override val code: String = "zh-Hant_MO"
    override val script: String = "Hant"
    override val territory: String = "MO"

    class EllipsisLanguage internal constructor() : LanguageZhHANT.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageZhHANT.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageZhHANT.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageZhHANT.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
