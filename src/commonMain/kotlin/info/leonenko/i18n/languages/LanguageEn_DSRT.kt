package info.leonenko.i18n.languages

import info.leonenko.i18n.*

val Language.Companion.en_DSRT
    get() = languageEn_DSRT
private val languageEn_DSRT = LanguageEn_DSRT()

open class LanguageEn_DSRT internal constructor() : LanguageEn() {
    override val code: String = "en#Dsrt"
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
