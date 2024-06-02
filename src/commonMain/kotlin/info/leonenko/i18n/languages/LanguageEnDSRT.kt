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

val Language.Companion.enDSRT
    get() = languageEnDSRT
private val languageEnDSRT = LanguageEnDSRT()

open class LanguageEnDSRT internal constructor() : LanguageEn() {
    override val code: String = "en-Dsrt"
    override val script: String = "Dsrt"
    override val territory: String? = null

    open class EllipsisLanguage internal constructor() : LanguageEn.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    open class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEn.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    open class MeasurementUnitLanguage internal constructor() : LanguageEn.MeasurementUnitLanguage() {
        override val durationYear
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} 𐐷𐐮𐑉",
                                other = "{0} 𐐷𐐮𐑉𐑆",
                            )
                        ),
                    )
                ),
            )
        override val durationMonth
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} 𐑋𐐲𐑌𐑃𐑅",
                                other = "{0} 𐑋𐐲𐑌𐑃",
                            )
                        ),
                    )
                ),
            )
        override val durationWeek
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} 𐐶𐐨𐐿",
                                other = "{0} 𐐶𐐨𐐿𐑅",
                            )
                        ),
                    )
                ),
            )
        override val durationDay
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} 𐐼𐐩",
                                other = "{0} 𐐼𐐩𐑆",
                            )
                        ),
                    )
                ),
            )
        override val durationHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} 𐐵𐑉",
                                other = "{0} 𐐵𐑉𐑆",
                            )
                        ),
                    )
                ),
            )
        override val durationMinute
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} 𐑋𐐮𐑌𐐲𐐻",
                                other = "{0} 𐑋𐐮𐑌𐐲𐐻𐑅",
                            )
                        ),
                    )
                ),
            )
        override val durationSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} 𐑅𐐯𐐿𐐲𐑌𐐼",
                                other = "{0} 𐑅𐐯𐐿𐐲𐑌𐐼𐑆",
                            )
                        ),
                    )
                ),
            )
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    open class NumberLanguage internal constructor() : LanguageEn.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
