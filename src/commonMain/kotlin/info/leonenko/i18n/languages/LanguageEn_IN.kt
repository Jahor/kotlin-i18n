package info.leonenko.i18n.languages

import info.leonenko.i18n.*
import info.leonenko.i18n.rbnf.RBNFEn_in

val Language.Companion.en_IN
    get() = languageEn_IN
private val languageEn_IN = LanguageEn_IN()

class LanguageEn_IN internal constructor() : LanguageEn() {
    override val code: String = "en_IN"
    override val script: String? = null
    override val territory: String = "IN"
    override val rbnf: RBNFEn_in
        get() = RBNFEn_in(this)

    class EllipsisLanguage internal constructor() : LanguageEn.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEn.ListLanguage(ellipsis) {
        override val formatter: ListFormatter = MultiLengthPatternedListFormatter(
            narrow = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, and {1}", ellipsis = ellipsis.wordFormatter),
        )
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEn.MeasurementUnitLanguage() {
        override val lengthEarthRadius
            get() = MultiLengthGenderedPattern(
                base = super.lengthEarthRadius,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} earth radius",
                                other = "{0} earth radius",
                            )
                        ),
                    )
                ),
            )
        override val speedKilometerPerHour
            get() = MultiLengthGenderedPattern(
                base = super.speedKilometerPerHour,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kph",
                                other = "{0} kph",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}kph",
                                other = "{0}kph",
                            )
                        ),
                    )
                ),
            )
        override val temperatureGeneric
            get() = MultiLengthGenderedPattern(
                base = super.temperatureGeneric,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}°",
                                other = "{0}°",
                            )
                        ),
                    )
                ),
            )
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageEn.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
