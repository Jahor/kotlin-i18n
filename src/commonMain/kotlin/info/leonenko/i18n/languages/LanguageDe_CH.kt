package info.leonenko.i18n.languages

import info.leonenko.i18n.*
import info.leonenko.i18n.rbnf.RBNFDe_ch

val Language.Companion.de_CH
    get() = languageDe_CH
private val languageDe_CH = LanguageDe_CH()

class LanguageDe_CH internal constructor() : LanguageDe() {
    override val code: String = "de_CH"
    override val script: String? = null
    override val territory: String = "CH"
    override val rbnf: RBNFDe_ch
        get() = RBNFDe_ch(this)

    class EllipsisLanguage internal constructor() : LanguageDe.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageDe.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageDe.MeasurementUnitLanguage() {
        override val areaSquareFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratfuss",
                                other = "{0} Quadratfuss",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Quadratfusses",
                                other = "{0} Quadratfuss",
                            )
                        ),
                    )
                ),
            )
        override val lengthFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Fuss",
                                other = "{0} Fuss",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Fusses",
                                other = "{0} Fuss",
                            )
                        ),
                    )
                ),
            )
        override val speedBeaufort
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Beaufort",
                                other = "{0} Beaufort",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Bft",
                                other = "{0} Bft",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikfuss",
                                other = "{0} Kubikfuss",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikfuss",
                                other = "{0} Kubikfuss",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikfuss",
                                other = "{0} Kubikfuss",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kubikfusses",
                                other = "{0} Kubikfuss",
                            )
                        ),
                    )
                ),
            )
        override val volumeDram
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dr.",
                                other = "{0} dr.",
                            )
                        ),
                    )
                ),
            )
        override val volumeJigger
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "Schuss",
                                other = "{0} Schuss",
                            )
                        ),
                    )
                ),
            )
        override val volumePinch
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Prise",
                                other = "{0} Prise",
                            )
                        ),
                    )
                ),
            )
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageDe.NumberLanguage() {
        override val symbols = LanguageDe.NumberLanguage().symbols.copy(
            decimalSymbol = ".",
            groupSymbol = "’",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
