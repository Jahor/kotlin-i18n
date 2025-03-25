package info.leonenko.i18n.languages

import info.leonenko.i18n.*
import info.leonenko.i18n.rbnf.RBNFDe

val Language.Companion.de
    get() = languageDe
private val languageDe = LanguageDe()
open class LanguageDe internal constructor() : Language {
    override val code: String = "de"
    override val language: String = "de"
    override val script: String? = null
    override val territory: String? = null
    override val rbnf: RBNFDe
        get() = RBNFDe(this)
    override val pluralFormChooser = PluralFormChooser.Expression(
        one = { (i == 1L) && (v == 0) },
    )

    open class EllipsisLanguage internal constructor() : info.leonenko.i18n.EllipsisLanguage {
        override val formatter = EllipsisFormatter.Default
        override val wordFormatter = EllipsisFormatter.WordDefault
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    open class ListLanguage internal constructor(val ellipsis: EllipsisLanguage) : info.leonenko.i18n.ListLanguage {
        override val formatter: ListFormatter = MultiLengthPatternedListFormatter(
            long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} und {1}", two = "{0} und {1}", ellipsis = ellipsis.wordFormatter),
        )
        override val orFormatter: ListFormatter =
            MultiLengthPatternedListFormatter(long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} oder {1}", two = "{0} oder {1}", ellipsis = ellipsis.wordFormatter))
        override val unitFormatter: ListFormatter = formatter
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    open class MeasurementUnitLanguage internal constructor() : info.leonenko.i18n.units.MeasurementUnitLanguage {
        override val accelerationGForce
            get() = MultiLengthGenderedPattern(
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}G",
                                other = "{0}G",
                            )
                        ),
                    )
                ),
            )
        override val accelerationMeterPerSquareSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Meter pro Quadratsekunde",
                                other = "{0} Meter pro Quadratsekunde",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Meter pro Quadratsekunde",
                                other = "{0} Meter pro Quadratsekunde",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Meter pro Quadratsekunde",
                                other = "{0} Metern pro Quadratsekunde",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Meters pro Quadratsekunde",
                                other = "{0} Meter pro Quadratsekunde",
                            )
                        ),
                    )
                ),
            )
        override val angleRevolution
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Umdrehung",
                                other = "{0} Umdrehungen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Umdrehung",
                                other = "{0} Umdrehungen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Umdrehung",
                                other = "{0} Umdrehungen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Umdrehung",
                                other = "{0} Umdrehungen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Umdr.",
                                other = "{0} Umdr.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} U",
                                other = "{0} U",
                            )
                        ),
                    )
                ),
            )
        override val angleRadian
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Radiant",
                                other = "{0} Radiant",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Radiant",
                                other = "{0} Radiant",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Radiant",
                                other = "{0} Radiant",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Radiant",
                                other = "{0} Radiant",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}rad",
                                other = "{0}rad",
                            )
                        ),
                    )
                ),
            )
        override val angleDegree
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Grad",
                                other = "{0} Grad",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Grad",
                                other = "{0} Grad",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Grad",
                                other = "{0} Grad",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Grads",
                                other = "{0} Grad",
                            )
                        ),
                    )
                ),
            )
        override val angleArcMinute
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Winkelminute",
                                other = "{0} Winkelminuten",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Winkelminute",
                                other = "{0} Winkelminuten",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Winkelminute",
                                other = "{0} Winkelminuten",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Winkelminute",
                                other = "{0} Winkelminuten",
                            )
                        ),
                    )
                ),
            )
        override val angleArcSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Winkelsekunde",
                                other = "{0} Winkelsekunden",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Winkelsekunde",
                                other = "{0} Winkelsekunden",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Winkelsekunde",
                                other = "{0} Winkelsekunden",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Winkelsekunde",
                                other = "{0} Winkelsekunden",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratkilometer",
                                other = "{0} Quadratkilometer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratkilometer",
                                other = "{0} Quadratkilometer",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratkilometer",
                                other = "{0} Quadratkilometern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Quadratkilometers",
                                other = "{0} Quadratkilometer",
                            )
                        ),
                    )
                ),
            )
        override val areaHectare
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Hektar",
                                other = "{0} Hektar",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Hektar",
                                other = "{0} Hektar",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Hektar",
                                other = "{0} Hektar",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Hektars",
                                other = "{0} Hektar",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratmeter",
                                other = "{0} Quadratmeter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratmeter",
                                other = "{0} Quadratmeter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratmeter",
                                other = "{0} Quadratmetern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Quadratmeters",
                                other = "{0} Quadratmeter",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratzentimeter",
                                other = "{0} Quadratzentimeter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratzentimeter",
                                other = "{0} Quadratzentimeter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratzentimeter",
                                other = "{0} Quadratzentimetern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Quadratzentimeters",
                                other = "{0} Quadratzentimeter",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareMile
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratmeile",
                                other = "{0} Quadratmeilen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratmeile",
                                other = "{0} Quadratmeilen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratmeile",
                                other = "{0} Quadratmeilen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Quadratmeile",
                                other = "{0} Quadratmeilen",
                            )
                        ),
                    )
                ),
            )
        override val areaAcre
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Acre",
                                other = "{0} Acres",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Acre",
                                other = "{0} Acre",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Acre",
                                other = "{0} Acre",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Acres",
                                other = "{0} Acre",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareYard
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratyard",
                                other = "{0} Quadratyards",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratfuß",
                                other = "{0} Quadratfuß",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratfuß",
                                other = "{0} Quadratfuß",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratfuß",
                                other = "{0} Quadratfuß",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Quadratfußes",
                                other = "{0} Quadratfuß",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareInch
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Quadratzoll",
                                other = "{0} Quadratzoll",
                            )
                        ),
                    )
                ),
            )
        override val areaDunam
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Dunam",
                                other = "{0} Dunams",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Dunam",
                                other = "{0} Dunam",
                            )
                        ),
                    )
                ),
            )
        override val concentrationKarat
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Karat",
                                other = "{0} Karat",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Karat",
                                other = "{0} Karat",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Karat",
                                other = "{0} Karat",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Karats",
                                other = "{0} Karat",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}kt",
                                other = "{0}kt",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermillion
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Millionstel",
                                other = "{0} Millionstel",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Millionstel",
                                other = "{0} Millionstel",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Millionstel",
                                other = "{0} Millionsteln",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Millionstels",
                                other = "{0} Millionstel",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPercent
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Prozent",
                                other = "{0} Prozent",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Prozent",
                                other = "{0} Prozent",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Prozent",
                                other = "{0} Prozent",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Prozents",
                                other = "{0} Prozent",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} %",
                                other = "{0} %",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermille
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Promille",
                                other = "{0} Promille",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Promille",
                                other = "{0} Promille",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Promille",
                                other = "{0} Promille",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Promille",
                                other = "{0} Promille",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ‰",
                                other = "{0} ‰",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}‰",
                                other = "{0}‰",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermyriad
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pro Zehntausend",
                                other = "{0} pro Zehntausend",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} pro Zehntausend",
                                other = "{0} pro Zehntausend",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} pro Zehntausend",
                                other = "{0} pro Zehntausend",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} pro Zehntausend",
                                other = "{0} pro Zehntausend",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ‱",
                                other = "{0} ‱",
                            )
                        ),
                    )
                ),
            )
        override val concentrationAPortion: MultiLengthGenderedPattern
            get() = TODO("APortion formatter is not implemented yet for de")
        override val concentrationMilligramOfglucosePerDeciliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Milligramm pro Deziliter",
                                other = "{0} Milligramm pro Deziliter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Milligramm pro Deziliter",
                                other = "{0} Milligramm pro Deziliter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Milligramm pro Deziliter",
                                other = "{0} Milligramm pro Deziliter",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Milligramm pro Deziliter",
                                other = "{0} Milligramm pro Deziliter",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mg/dl",
                                other = "{0} mg/dl",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}mg/dl",
                                other = "{0}mg/dl",
                            )
                        ),
                    )
                ),
            )
        override val concentrationMillimolePerLiter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Millimol pro Liter",
                                other = "{0} Millimol pro Liter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Millimol pro Liter",
                                other = "{0} Millimol pro Liter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Millimol pro Liter",
                                other = "{0} Millimol pro Liter",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Millimol pro Liter",
                                other = "{0} Millimol pro Liter",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mmol/l",
                                other = "{0} mmol/l",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}mmol/l",
                                other = "{0}mmol/l",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPortionPer1e9
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Milliardstel",
                                other = "{0} Milliardstel",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Milliardstel",
                                other = "{0} Milliardstel",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Milliardstel",
                                other = "{0} Milliardsteln",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Milliardstels",
                                other = "{0} Milliardstel",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Milliardstel",
                                other = "{0} Milliardstel",
                            )
                        ),
                    )
                ),
            )
        override val concentrationItem
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Element",
                                other = "{0} Elemente",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Element",
                                other = "{0} Elemente",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Element",
                                other = "{0} Elementen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Elements",
                                other = "{0} Elemente",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Element",
                                other = "{0} Elemente",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Elem.",
                                other = "{0} Elem.",
                            )
                        ),
                    )
                ),
            )
        override val concentrationMole
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Mol",
                                other = "{0} Mol",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Mol",
                                other = "{0} Mol",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Mol",
                                other = "{0} Mol",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Mols",
                                other = "{0} Mol",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}mol",
                                other = "{0}mol",
                            )
                        ),
                    )
                ),
            )
        override val consumptionLiterPerKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Liter pro Kilometer",
                                other = "{0} Liter pro Kilometer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Liter pro Kilometer",
                                other = "{0} Liter pro Kilometer",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Liter pro Kilometer",
                                other = "{0} Litern pro Kilometer",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Liters pro Kilometer",
                                other = "{0} Liter pro Kilometer",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} l/km",
                                other = "{0} l/km",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}l/km",
                                other = "{0}l/km",
                            )
                        ),
                    )
                ),
            )
        override val consumptionLiterPer100Kilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Liter pro 100 Kilometer",
                                other = "{0} Liter pro 100 Kilometer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Liter pro 100 Kilometer",
                                other = "{0} Liter pro 100 Kilometer",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Liter pro 100 Kilometer",
                                other = "{0} Litern pro 100 Kilometer",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Liters pro 100 Kilometer",
                                other = "{0} Liter pro 100 Kilometer",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} L/100 km",
                                other = "{0} L/100 km",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}L/100km",
                                other = "{0}L/100km",
                            )
                        ),
                    )
                ),
            )
        override val consumptionMilePerGallon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Meile pro Gallone",
                                other = "{0} Meilen pro Gallone",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Meile pro Gallone",
                                other = "{0} Meilen pro Gallone",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Meile pro Gallone",
                                other = "{0} Meilen pro Gallone",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Meile pro Gallone",
                                other = "{0} Meilen pro Gallone",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mpg",
                                other = "{0} mpg",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}mpg",
                                other = "{0}mpg",
                            )
                        ),
                    )
                ),
            )
        override val consumptionMilePerGallonImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Meile pro Imp. Gallone",
                                other = "{0} Meilen pro Imp. Gallone",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Meile pro Imp. Gallone",
                                other = "{0} Meilen pro Imp. Gallone",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Meile pro Imp. Gallone",
                                other = "{0} Meilen pro Imp. Gallone",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Meile pro Imp. Gallone",
                                other = "{0} Meilen pro Imp. Gallone",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mpg UK",
                                other = "{0} mpg UK",
                            )
                        ),
                    )
                ),
            )
        override val digitalPetabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Petabyte",
                                other = "{0} Petabyte",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Petabyte",
                                other = "{0} Petabyte",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Petabyte",
                                other = "{0} Petabyte",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Petabyte",
                                other = "{0} Petabyte",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Terabyte",
                                other = "{0} Terabyte",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Terabyte",
                                other = "{0} Terabyte",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Terabyte",
                                other = "{0} Terabyte",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Terabyte",
                                other = "{0} Terabyte",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Terabit",
                                other = "{0} Terabit",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Terabit",
                                other = "{0} Terabit",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Terabit",
                                other = "{0} Terabit",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Terabit",
                                other = "{0} Terabit",
                            )
                        ),
                    )
                ),
            )
        override val digitalGigabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Gigabyte",
                                other = "{0} Gigabyte",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Gigabyte",
                                other = "{0} Gigabyte",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Gigabyte",
                                other = "{0} Gigabyte",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Gigabyte",
                                other = "{0} Gigabyte",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} GB",
                                other = "{0} GB",
                            )
                        ),
                    )
                ),
            )
        override val digitalGigabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Gigabit",
                                other = "{0} Gigabit",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Gigabit",
                                other = "{0} Gigabit",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Gigabit",
                                other = "{0} Gigabit",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Gigabit",
                                other = "{0} Gigabit",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Gb",
                                other = "{0} Gb",
                            )
                        ),
                    )
                ),
            )
        override val digitalMegabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Megabyte",
                                other = "{0} Megabyte",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Megabyte",
                                other = "{0} Megabyte",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Megabyte",
                                other = "{0} Megabyte",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Megabyte",
                                other = "{0} Megabyte",
                            )
                        ),
                    )
                ),
            )
        override val digitalMegabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Megabit",
                                other = "{0} Megabit",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Megabit",
                                other = "{0} Megabit",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Megabit",
                                other = "{0} Megabit",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Megabit",
                                other = "{0} Megabit",
                            )
                        ),
                    )
                ),
            )
        override val digitalKilobyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kilobyte",
                                other = "{0} Kilobyte",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kilobyte",
                                other = "{0} Kilobyte",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kilobyte",
                                other = "{0} Kilobyte",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kilobyte",
                                other = "{0} Kilobyte",
                            )
                        ),
                    )
                ),
            )
        override val digitalKilobit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kilobit",
                                other = "{0} Kilobit",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kilobit",
                                other = "{0} Kilobit",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kilobit",
                                other = "{0} Kilobit",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kilobit",
                                other = "{0} Kilobit",
                            )
                        ),
                    )
                ),
            )
        override val digitalByteUnit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Byte",
                                other = "{0} Byte",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Byte",
                                other = "{0} Byte",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Byte",
                                other = "{0} Byte",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Byte",
                                other = "{0} Byte",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Byte",
                                other = "{0} Byte",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} B",
                                other = "{0} B",
                            )
                        ),
                    )
                ),
            )
        override val digitalBit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Bit",
                                other = "{0} Bit",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Bit",
                                other = "{0} Bit",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Bit",
                                other = "{0} Bit",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Bit",
                                other = "{0} Bit",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Bit",
                                other = "{0} Bit",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} b",
                                other = "{0} b",
                            )
                        ),
                    )
                ),
            )
        override val durationCentury
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Jahrhundert",
                                other = "{0} Jahrhunderte",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Jahrhundert",
                                other = "{0} Jahrhunderte",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Jahrhundert",
                                other = "{0} Jahrhunderten",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Jahrhunderts",
                                other = "{0} Jahrhunderte",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Jh.",
                                other = "{0} Jh.",
                            )
                        ),
                    )
                ),
            )
        override val durationDecade
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Jahrzehnt",
                                other = "{0} Jahrzehnte",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Jahrzehnt",
                                other = "{0} Jahrzehnte",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Jahrzehnt",
                                other = "{0} Jahrzehnten",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Jahrzehnts",
                                other = "{0} Jahrzehnte",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Jz.",
                                other = "{0} Jz.",
                            )
                        ),
                    )
                ),
            )
        override val durationYear
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Jahr",
                                other = "{0} Jahre",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Jahr",
                                other = "{0} Jahre",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Jahr",
                                other = "{0} Jahren",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Jahres",
                                other = "{0} Jahre",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} J",
                                other = "{0} J",
                            )
                        ),
                    )
                ),
            )
        override val durationQuarter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Quartal",
                                other = "{0} Quartale",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Quartal",
                                other = "{0} Quartale",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Quartal",
                                other = "{0} Quartalen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Quartals",
                                other = "{0} Quartale",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Quart.",
                                other = "{0} Quart.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Q",
                                other = "{0} Q",
                            )
                        ),
                    )
                ),
            )
        override val durationMonth
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Monat",
                                other = "{0} Monate",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Monat",
                                other = "{0} Monate",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Monat",
                                other = "{0} Monaten",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Monats",
                                other = "{0} Monate",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Mon.",
                                other = "{0} Mon.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} M",
                                other = "{0} M",
                            )
                        ),
                    )
                ),
            )
        override val durationWeek
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Woche",
                                other = "{0} Wochen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Woche",
                                other = "{0} Wochen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Woche",
                                other = "{0} Wochen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Woche",
                                other = "{0} Wochen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Wo.",
                                other = "{0} Wo.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} W",
                                other = "{0} W",
                            )
                        ),
                    )
                ),
            )
        override val durationDay
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Tag",
                                other = "{0} Tage",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Tag",
                                other = "{0} Tage",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Tag",
                                other = "{0} Tagen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Tages",
                                other = "{0} Tage",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Tg.",
                                other = "{0} Tg.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} T",
                                other = "{0} T",
                            )
                        ),
                    )
                ),
            )
        override val durationHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Stunde",
                                other = "{0} Stunden",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Stunde",
                                other = "{0} Stunden",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Stunde",
                                other = "{0} Stunden",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Stunde",
                                other = "{0} Stunden",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Std.",
                                other = "{0} Std.",
                            )
                        ),
                    )
                ),
            )
        override val durationMinute
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Minute",
                                other = "{0} Minuten",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Minute",
                                other = "{0} Minuten",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Minute",
                                other = "{0} Minuten",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Minute",
                                other = "{0} Minuten",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Min.",
                                other = "{0} Min.",
                            )
                        ),
                    )
                ),
            )
        override val durationSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Sekunde",
                                other = "{0} Sekunden",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Sekunde",
                                other = "{0} Sekunden",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Sekunde",
                                other = "{0} Sekunden",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Sekunde",
                                other = "{0} Sekunden",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Sek.",
                                other = "{0} Sek.",
                            )
                        ),
                    )
                ),
            )
        override val durationMillisecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Millisekunde",
                                other = "{0} Millisekunden",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Millisekunde",
                                other = "{0} Millisekunden",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Millisekunde",
                                other = "{0} Millisekunden",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Millisekunde",
                                other = "{0} Millisekunden",
                            )
                        ),
                    )
                ),
            )
        override val durationMicrosecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Mikrosekunde",
                                other = "{0} Mikrosekunden",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Mikrosekunde",
                                other = "{0} Mikrosekunden",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Mikrosekunde",
                                other = "{0} Mikrosekunden",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Mikrosekunde",
                                other = "{0} Mikrosekunden",
                            )
                        ),
                    )
                ),
            )
        override val durationNanosecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Nanosekunde",
                                other = "{0} Nanosekunden",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Nanosekunde",
                                other = "{0} Nanosekunden",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Nanosekunde",
                                other = "{0} Nanosekunden",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Nanosekunde",
                                other = "{0} Nanosekunden",
                            )
                        ),
                    )
                ),
            )
        override val durationNight
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Übernachtung",
                                other = "{0} Übernachtungen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Übernachtung",
                                other = "{0} Übernachtungen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Übernachtung",
                                other = "{0} Übernachtungen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Übernachtung",
                                other = "{0} Übernachtungen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Nacht",
                                other = "{0} Nächte",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Nacht",
                                other = "{0}Nächte",
                            )
                        ),
                    )
                ),
            )
        override val electricAmpere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Ampere",
                                other = "{0} Ampere",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Ampere",
                                other = "{0} Ampere",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Ampere",
                                other = "{0} Ampere",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Ampere",
                                other = "{0} Ampere",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}A",
                                other = "{0}A",
                            )
                        ),
                    )
                ),
            )
        override val electricMilliampere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Milliampere",
                                other = "{0} Milliampere",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Milliampere",
                                other = "{0} Milliampere",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Milliampere",
                                other = "{0} Milliampere",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Milliampere",
                                other = "{0} Milliampere",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}mA",
                                other = "{0}mA",
                            )
                        ),
                    )
                ),
            )
        override val electricOhm
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Ohm",
                                other = "{0} Ohm",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Ohm",
                                other = "{0} Ohm",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Ohm",
                                other = "{0} Ohm",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Ohms",
                                other = "{0} Ohm",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Ω",
                                other = "{0}Ω",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondSquareAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondSquareAmpere formatter is not implemented yet for de")
        override val electricVolt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Volt",
                                other = "{0} Volt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Volt",
                                other = "{0} Volt",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Volt",
                                other = "{0} Volt",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Volts",
                                other = "{0} Volt",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}V",
                                other = "{0}V",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondAmpere formatter is not implemented yet for de")
        override val energyKilocalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kilokalorie",
                                other = "{0} Kilokalorien",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kilokalorie",
                                other = "{0} Kilokalorien",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kilokalorie",
                                other = "{0} Kilokalorien",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kilokalorie",
                                other = "{0} Kilokalorien",
                            )
                        ),
                    )
                ),
            )
        override val energyCalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kalorie",
                                other = "{0} Kalorien",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kalorie",
                                other = "{0} Kalorien",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kalorie",
                                other = "{0} Kalorien",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kalorie",
                                other = "{0} Kalorien",
                            )
                        ),
                    )
                ),
            )
        override val energyFoodcalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kilokalorie",
                                other = "{0} Kilokalorien",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kilokalorie",
                                other = "{0} Kilokalorien",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kilokalorie",
                                other = "{0} Kilokalorien",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kilokalorie",
                                other = "{0} Kilokalorien",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kcal",
                                other = "{0} kcal",
                            )
                        ),
                    )
                ),
            )
        override val energyKilojoule
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kilojoule",
                                other = "{0} Kilojoule",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kilojoule",
                                other = "{0} Kilojoule",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kilojoule",
                                other = "{0} Kilojoule",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kilojoule",
                                other = "{0} Kilojoule",
                            )
                        ),
                    )
                ),
            )
        override val energyJoule
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Joule",
                                other = "{0} Joule",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Joule",
                                other = "{0} Joule",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Joule",
                                other = "{0} Joule",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Joule",
                                other = "{0} Joule",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} J",
                                other = "{0} J",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}J",
                                other = "{0}J",
                            )
                        ),
                    )
                ),
            )
        override val energyKilowattHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kilowattstunde",
                                other = "{0} Kilowattstunden",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kilowattstunde",
                                other = "{0} Kilowattstunden",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kilowattstunde",
                                other = "{0} Kilowattstunden",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kilowattstunde",
                                other = "{0} Kilowattstunden",
                            )
                        ),
                    )
                ),
            )
        override val energyElectronvolt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Elektronenvolt",
                                other = "{0} Elektronenvolt",
                            )
                        ),
                    )
                ),
            )
        override val energyBritishThermalUnit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} British thermal unit",
                                other = "{0} British thermal units",
                            )
                        ),
                    )
                ),
            )
        override val energyThermUs
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} US thermal unit",
                                other = "{0} US thermal units",
                            )
                        ),
                    )
                ),
            )
        override val energyKilogramSquareMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerSquareSecond formatter is not implemented yet for de")
        override val forcePoundForce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Pound-force",
                                other = "{0} Pound-force",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}lbf",
                                other = "{0}lbf",
                            )
                        ),
                    )
                ),
            )
        override val forceNewton
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Newton",
                                other = "{0} Newton",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Newton",
                                other = "{0} Newton",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Newton",
                                other = "{0} Newton",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Newtons",
                                other = "{0} Newton",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}N",
                                other = "{0}N",
                            )
                        ),
                    )
                ),
            )
        override val forceKilowattHourPer100Kilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kilowattstunde pro 100 Kilometer",
                                other = "{0} Kilowattstunden pro 100 Kilometer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kilowattstunde pro 100 Kilometer",
                                other = "{0} Kilowattstunden pro 100 Kilometer",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kilowattstunde pro 100 Kilometer",
                                other = "{0} Kilowattstunden pro 100 Kilometer",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kilowattstunde pro 100 Kilometer",
                                other = "{0} Kilowattstunden pro 100 Kilometer",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kWh/100 km",
                                other = "{0} kWh/100 km",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kWh/100km",
                                other = "{0} kWh/100km",
                            )
                        ),
                    )
                ),
            )
        override val forceKilogramMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramMeterPerSquareSecond formatter is not implemented yet for de")
        override val frequencyGigahertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Gigahertz",
                                other = "{0} Gigahertz",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Gigahertz",
                                other = "{0} Gigahertz",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Gigahertz",
                                other = "{0} Gigahertz",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Gigahertz",
                                other = "{0} Gigahertz",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}GHz",
                                other = "{0}GHz",
                            )
                        ),
                    )
                ),
            )
        override val frequencyMegahertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Megahertz",
                                other = "{0} Megahertz",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Megahertz",
                                other = "{0} Megahertz",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Megahertz",
                                other = "{0} Megahertz",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Megahertz",
                                other = "{0} Megahertz",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}MHz",
                                other = "{0}MHz",
                            )
                        ),
                    )
                ),
            )
        override val frequencyKilohertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kilohertz",
                                other = "{0} Kilohertz",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kilohertz",
                                other = "{0} Kilohertz",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kilohertz",
                                other = "{0} Kilohertz",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kilohertz",
                                other = "{0} Kilohertz",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}kHz",
                                other = "{0}kHz",
                            )
                        ),
                    )
                ),
            )
        override val frequencyHertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Hertz",
                                other = "{0} Hertz",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Hertz",
                                other = "{0} Hertz",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Hertz",
                                other = "{0} Hertz",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Hertz",
                                other = "{0} Hertz",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Hz",
                                other = "{0}Hz",
                            )
                        ),
                    )
                ),
            )
        override val frequencyRevolutionPerSecond: MultiLengthGenderedPattern
            get() = TODO("RevolutionPerSecond formatter is not implemented yet for de")
        override val graphicsEm: MultiLengthGenderedPattern
            get() = TODO("Em formatter is not implemented yet for de")
        override val graphicsPixel
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Pixel",
                                other = "{0} Pixel",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Pixel",
                                other = "{0} Pixel",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Pixel",
                                other = "{0} Pixeln",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Pixels",
                                other = "{0} Pixel",
                            )
                        ),
                    )
                ),
            )
        override val graphicsMegapixel
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Megapixel",
                                other = "{0} Megapixel",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Megapixel",
                                other = "{0} Megapixel",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Megapixel",
                                other = "{0} Megapixeln",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Megapixels",
                                other = "{0} Megapixel",
                            )
                        ),
                    )
                ),
            )
        override val graphicsDot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Dot",
                                other = "{0} Dots",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} d",
                                other = "{0} d",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixelPerCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Pixel pro Zentimeter",
                                other = "{0} Pixel pro Zentimeter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Pixel pro Zentimeter",
                                other = "{0} Pixel pro Zentimeter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Pixel pro Zentimeter",
                                other = "{0} Pixeln pro Zentimeter",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Pixels pro Zentimeter",
                                other = "{0} Pixel pro Zentimeter",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixelPerInch
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Pixel pro Inch",
                                other = "{0} Pixel pro Inch",
                            )
                        ),
                    )
                ),
            )
        override val graphicsDotPerCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Dot pro Zentimeter",
                                other = "{0} Dots pro Zentimeter",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dpcm",
                                other = "{0} dpcm",
                            )
                        ),
                    )
                ),
            )
        override val graphicsDotPerInch
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Dot pro Inch",
                                other = "{0} Dots pro Inch",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dpi",
                                other = "{0} dpi",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixelPerMeter: MultiLengthGenderedPattern
            get() = TODO("PixelPerMeter formatter is not implemented yet for de")
        override val lengthEarthRadius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Erdradius",
                                other = "{0} Erdradien",
                            )
                        ),
                    )
                ),
            )
        override val lengthKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kilometer",
                                other = "{0} Kilometer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kilometer",
                                other = "{0} Kilometer",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kilometer",
                                other = "{0} Kilometern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kilometers",
                                other = "{0} Kilometer",
                            )
                        ),
                    )
                ),
            )
        override val lengthMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Meter",
                                other = "{0} Meter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Meter",
                                other = "{0} Meter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Meter",
                                other = "{0} Metern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Meters",
                                other = "{0} Meter",
                            )
                        ),
                    )
                ),
            )
        override val lengthDecimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Dezimeter",
                                other = "{0} Dezimeter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Dezimeter",
                                other = "{0} Dezimeter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Dezimeter",
                                other = "{0} Dezimetern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Dezimeters",
                                other = "{0} Dezimeter",
                            )
                        ),
                    )
                ),
            )
        override val lengthCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Zentimeter",
                                other = "{0} Zentimeter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Zentimeter",
                                other = "{0} Zentimeter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Zentimeter",
                                other = "{0} Zentimetern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Zentimeters",
                                other = "{0} Zentimeter",
                            )
                        ),
                    )
                ),
            )
        override val lengthMillimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Millimeter",
                                other = "{0} Millimeter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Millimeter",
                                other = "{0} Millimeter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Millimeter",
                                other = "{0} Millimetern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Millimeters",
                                other = "{0} Millimeter",
                            )
                        ),
                    )
                ),
            )
        override val lengthMicrometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Mikrometer",
                                other = "{0} Mikrometer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Mikrometer",
                                other = "{0} Mikrometer",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Mikrometer",
                                other = "{0} Mikrometern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Mikrometers",
                                other = "{0} Mikrometer",
                            )
                        ),
                    )
                ),
            )
        override val lengthNanometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Nanometer",
                                other = "{0} Nanometer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Nanometer",
                                other = "{0} Nanometer",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Nanometer",
                                other = "{0} Nanometern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Nanometers",
                                other = "{0} Nanometer",
                            )
                        ),
                    )
                ),
            )
        override val lengthPicometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Pikometer",
                                other = "{0} Pikometer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Pikometer",
                                other = "{0} Pikometer",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Pikometer",
                                other = "{0} Pikometern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Pikometers",
                                other = "{0} Pikometer",
                            )
                        ),
                    )
                ),
            )
        override val lengthMile
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Meile",
                                other = "{0} Meilen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Meile",
                                other = "{0} Meilen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Meile",
                                other = "{0} Meilen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Meile",
                                other = "{0} Meilen",
                            )
                        ),
                    )
                ),
            )
        override val lengthYard
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Yard",
                                other = "{0} Yards",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Yard",
                                other = "{0} Yards",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Yard",
                                other = "{0} Yards",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Yards",
                                other = "{0} Yards",
                            )
                        ),
                    )
                ),
            )
        override val lengthFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Fuß",
                                other = "{0} Fuß",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Fuß",
                                other = "{0} Fuß",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Fuß",
                                other = "{0} Fuß",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Fußes",
                                other = "{0} Fuß",
                            )
                        ),
                    )
                ),
            )
        override val lengthInch
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Zoll",
                                other = "{0} Zoll",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Zoll",
                                other = "{0} Zoll",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Zoll",
                                other = "{0} Zoll",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Zolls",
                                other = "{0} Zoll",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} in",
                                other = "{0} in",
                            )
                        ),
                    )
                ),
            )
        override val lengthParsec
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Parsec",
                                other = "{0} Parsec",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Parsec",
                                other = "{0} Parsec",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Parsec",
                                other = "{0} Parsec",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Parsec",
                                other = "{0} Parsec",
                            )
                        ),
                    )
                ),
            )
        override val lengthLightYear
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Lichtjahr",
                                other = "{0} Lichtjahre",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Lj",
                                other = "{0} Lj",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Lj",
                                other = "{0}Lj",
                            )
                        ),
                    )
                ),
            )
        override val lengthAstronomicalUnit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Astronomische Einheit",
                                other = "{0} Astronomische Einheiten",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} AE",
                                other = "{0} AE",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}AE",
                                other = "{0}AE",
                            )
                        ),
                    )
                ),
            )
        override val lengthFurlong
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Furlong",
                                other = "{0} Furlong",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}fur",
                                other = "{0}fur",
                            )
                        ),
                    )
                ),
            )
        override val lengthFathom
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Faden",
                                other = "{0} Faden",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fm",
                                other = "{0} fm",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}fm",
                                other = "{0}fm",
                            )
                        ),
                    )
                ),
            )
        override val lengthNauticalMile
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Seemeile",
                                other = "{0} Seemeilen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} sm",
                                other = "{0} sm",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}sm",
                                other = "{0}sm",
                            )
                        ),
                    )
                ),
            )
        override val lengthMileScandinavian
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} skandinavische Meile",
                                other = "{0} skandinavische Meilen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} skandinavische Meile",
                                other = "{0} skandinavische Meilen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} skandinavischen Meile",
                                other = "{0} skandinavischen Meilen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} skandinavischen Meile",
                                other = "{0} skandinavischen Meilen",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}smi",
                                other = "{0}smi",
                            )
                        ),
                    )
                ),
            )
        override val lengthPoint
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} DTP-Punkt",
                                other = "{0} DTP-Punkte",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} DTP-Punkt",
                                other = "{0} DTP-Punkte",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} DTP-Punkt",
                                other = "{0} DTP-Punkten",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} DTP-Punkts",
                                other = "{0} DTP-Punkte",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} p",
                                other = "{0} p",
                            )
                        ),
                    )
                ),
            )
        override val lengthSolarRadius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Sonnenradius",
                                other = "{0} Sonnenradien",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Sonnenradius",
                                other = "{0} Sonnenradien",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Sonnenradius",
                                other = "{0} Sonnenradien",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Sonnenradius",
                                other = "{0} Sonnenradien",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}R☉",
                                other = "{0}R☉",
                            )
                        ),
                    )
                ),
            )
        override val lightLux
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Lux",
                                other = "{0} Lux",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Lux",
                                other = "{0} Lux",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Lux",
                                other = "{0} Lux",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Lux",
                                other = "{0} Lux",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}lx",
                                other = "{0}lx",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaPerSquareMeter formatter is not implemented yet for de")
        override val lightCandela
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Candela",
                                other = "{0} Candela",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Candela",
                                other = "{0} Candela",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Candela",
                                other = "{0} Candela",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Candela",
                                other = "{0} Candela",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}cd",
                                other = "{0}cd",
                            )
                        ),
                    )
                ),
            )
        override val lightLumen
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Lumen",
                                other = "{0} Lumen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Lumen",
                                other = "{0} Lumen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Lumen",
                                other = "{0} Lumen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Lumens",
                                other = "{0} Lumen",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}lm",
                                other = "{0}lm",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaSquareMeterPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaSquareMeterPerSquareMeter formatter is not implemented yet for de")
        override val lightSolarLuminosity
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Sonnenleuchtkraft",
                                other = "{0} Sonnenleuchtkräfte",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Sonnenleuchtkraft",
                                other = "{0} Sonnenleuchtkräfte",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Sonnenleuchtkraft",
                                other = "{0} Sonnenleuchtkräften",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Sonnenleuchtkraft",
                                other = "{0} Sonnenleuchtkräfte",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}L☉",
                                other = "{0}L☉",
                            )
                        ),
                    )
                ),
            )
        override val massTonne
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Tonne",
                                other = "{0} Tonnen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Tonne",
                                other = "{0} Tonnen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Tonne",
                                other = "{0} Tonnen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Tonne",
                                other = "{0} Tonnen",
                            )
                        ),
                    )
                ),
            )
        override val massKilogram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kilogramm",
                                other = "{0} Kilogramm",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kilogramm",
                                other = "{0} Kilogramm",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kilogramm",
                                other = "{0} Kilogramm",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kilogramms",
                                other = "{0} Kilogramm",
                            )
                        ),
                    )
                ),
            )
        override val massGram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Gramm",
                                other = "{0} Gramm",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Gramm",
                                other = "{0} Gramm",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Gramm",
                                other = "{0} Gramm",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Gramms",
                                other = "{0} Gramm",
                            )
                        ),
                    )
                ),
            )
        override val massMilligram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Milligramm",
                                other = "{0} Milligramm",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Milligramm",
                                other = "{0} Milligramm",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Milligramm",
                                other = "{0} Milligramm",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Milligramms",
                                other = "{0} Milligramm",
                            )
                        ),
                    )
                ),
            )
        override val massMicrogram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Mikrogramm",
                                other = "{0} Mikrogramm",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Mikrogramm",
                                other = "{0} Mikrogramm",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Mikrogramm",
                                other = "{0} Mikrogramm",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Mikrogramms",
                                other = "{0} Mikrogramm",
                            )
                        ),
                    )
                ),
            )
        override val massTon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Short Ton",
                                other = "{0} Short Tons",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} tn. sh.",
                                other = "{0} tn. sh.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} tn",
                                other = "{0} tn",
                            )
                        ),
                    )
                ),
            )
        override val massStone
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Stone",
                                other = "{0} Stones",
                            )
                        ),
                    )
                ),
            )
        override val massPound
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Pfund",
                                other = "{0} Pfund",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Pfund",
                                other = "{0} Pfund",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Pfund",
                                other = "{0} Pfund",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Pfunds",
                                other = "{0} Pfund",
                            )
                        ),
                    )
                ),
            )
        override val massOunce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Unze",
                                other = "{0} Unzen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Unze",
                                other = "{0} Unzen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Unze",
                                other = "{0} Unzen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Unze",
                                other = "{0} Unzen",
                            )
                        ),
                    )
                ),
            )
        override val massOunceTroy
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Feinunze",
                                other = "{0} Feinunzen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} oz.tr.",
                                other = "{0} oz.tr.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} oz.tr.",
                                other = "{0} oz.tr.",
                            )
                        ),
                    )
                ),
            )
        override val massCarat
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Karat",
                                other = "{0} Karat",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Karat",
                                other = "{0} Karat",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Karat",
                                other = "{0} Karat",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Karats",
                                other = "{0} Karat",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kt",
                                other = "{0} Kt",
                            )
                        ),
                    )
                ),
            )
        override val massDalton
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Dalton",
                                other = "{0} Dalton",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Dalton",
                                other = "{0} Dalton",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Dalton",
                                other = "{0} Dalton",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Dalton",
                                other = "{0} Dalton",
                            )
                        ),
                    )
                ),
            )
        override val massEarthMass
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Erdmasse",
                                other = "{0} Erdmassen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Erdmasse",
                                other = "{0} Erdmassen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Erdmasse",
                                other = "{0} Erdmassen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Erdmasse",
                                other = "{0} Erdmassen",
                            )
                        ),
                    )
                ),
            )
        override val massSolarMass
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Sonnenmasse",
                                other = "{0} Sonnenmassen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Sonnenmasse",
                                other = "{0} Sonnenmassen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Sonnenmasse",
                                other = "{0} Sonnenmassen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Sonnenmasse",
                                other = "{0} Sonnenmassen",
                            )
                        ),
                    )
                ),
            )
        override val massGrain
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Gran",
                                other = "{0} Gran",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Gran",
                                other = "{0} Gran",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Gran",
                                other = "{0} Gran",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Grans",
                                other = "{0} Gran",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gr",
                                other = "{0} gr",
                            )
                        ),
                    )
                ),
            )
        override val powerGigawatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Gigawatt",
                                other = "{0} Gigawatt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Gigawatt",
                                other = "{0} Gigawatt",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Gigawatt",
                                other = "{0} Gigawatt",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Gigawatts",
                                other = "{0} Gigawatt",
                            )
                        ),
                    )
                ),
            )
        override val powerMegawatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Megawatt",
                                other = "{0} Megawatt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Megawatt",
                                other = "{0} Megawatt",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Megawatt",
                                other = "{0} Megawatt",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Megawatts",
                                other = "{0} Megawatt",
                            )
                        ),
                    )
                ),
            )
        override val powerKilowatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kilowatt",
                                other = "{0} Kilowatt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kilowatt",
                                other = "{0} Kilowatt",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kilowatt",
                                other = "{0} Kilowatt",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kilowatts",
                                other = "{0} Kilowatt",
                            )
                        ),
                    )
                ),
            )
        override val powerWatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Watt",
                                other = "{0} Watt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Watt",
                                other = "{0} Watt",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Watt",
                                other = "{0} Watt",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Watts",
                                other = "{0} Watt",
                            )
                        ),
                    )
                ),
            )
        override val powerMilliwatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Milliwatt",
                                other = "{0} Milliwatt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Milliwatt",
                                other = "{0} Milliwatt",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Milliwatt",
                                other = "{0} Milliwatt",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Milliwatts",
                                other = "{0} Milliwatt",
                            )
                        ),
                    )
                ),
            )
        override val powerHorsepower
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Pferdestärke",
                                other = "{0} Pferdestärken",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} PS",
                                other = "{0} PS",
                            )
                        ),
                    )
                ),
            )
        override val powerKilogramSquareMeterPerCubicSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecond formatter is not implemented yet for de")
        override val pressureMillimeterOfhg
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Millimeter Quecksilbersäule",
                                other = "{0} Millimeter Quecksilbersäule",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Millimeter Quecksilbersäule",
                                other = "{0} Millimeter Quecksilbersäule",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Millimeter Quecksilbersäule",
                                other = "{0} Millimeter Quecksilbersäule",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Millimeter Quecksilbersäule",
                                other = "{0} Millimeter Quecksilbersäule",
                            )
                        ),
                    )
                ),
            )
        override val pressurePoundForcePerSquareInch
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Pfund pro Quadratzoll",
                                other = "{0} Pfund pro Quadratzoll",
                            )
                        ),
                    )
                ),
            )
        override val pressureInchOfhg
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Zoll Quecksilbersäule",
                                other = "{0} Zoll Quecksilbersäule",
                            )
                        ),
                    )
                ),
            )
        override val pressureBar
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Bar",
                                other = "{0} Bar",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Bar",
                                other = "{0} Bar",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Bar",
                                other = "{0} Bar",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Bars",
                                other = "{0} Bar",
                            )
                        ),
                    )
                ),
            )
        override val pressureMillibar
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Millibar",
                                other = "{0} Millibar",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Millibar",
                                other = "{0} Millibar",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Millibar",
                                other = "{0} Millibar",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Millibars",
                                other = "{0} Millibar",
                            )
                        ),
                    )
                ),
            )
        override val pressureAtmosphere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Atmosphäre",
                                other = "{0} Atmosphären",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Atmosphäre",
                                other = "{0} Atmosphären",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Atmosphäre",
                                other = "{0} Atmosphären",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Atmosphäre",
                                other = "{0} Atmosphären",
                            )
                        ),
                    )
                ),
            )
        override val pressurePascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Pascal",
                                other = "{0} Pascal",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Pascal",
                                other = "{0} Pascal",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Pascal",
                                other = "{0} Pascal",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Pascals",
                                other = "{0} Pascal",
                            )
                        ),
                    )
                ),
            )
        override val pressureHectopascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Hektopascal",
                                other = "{0} Hektopascal",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Hektopascal",
                                other = "{0} Hektopascal",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Hektopascal",
                                other = "{0} Hektopascal",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Hektopascals",
                                other = "{0} Hektopascal",
                            )
                        ),
                    )
                ),
            )
        override val pressureKilopascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kilopascal",
                                other = "{0} Kilopascal",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kilopascal",
                                other = "{0} Kilopascal",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kilopascal",
                                other = "{0} Kilopascal",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kilopascals",
                                other = "{0} Kilopascal",
                            )
                        ),
                    )
                ),
            )
        override val pressureMegapascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Megapascal",
                                other = "{0} Megapascal",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Megapascal",
                                other = "{0} Megapascal",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Megapascal",
                                other = "{0} Megapascal",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Megapascals",
                                other = "{0} Megapascal",
                            )
                        ),
                    )
                ),
            )
        override val pressureGasolineEnergyDensity: MultiLengthGenderedPattern
            get() = TODO("GasolineEnergyDensity formatter is not implemented yet for de")
        override val pressureKilogramPerMeterSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramPerMeterSquareSecond formatter is not implemented yet for de")
        override val speedKilometerPerHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kilometer pro Stunde",
                                other = "{0} Kilometer pro Stunde",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kilometer pro Stunde",
                                other = "{0} Kilometer pro Stunde",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kilometer pro Stunde",
                                other = "{0} Kilometern pro Stunde",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kilometers pro Stunde",
                                other = "{0} Kilometer pro Stunde",
                            )
                        ),
                    )
                ),
            )
        override val speedMeterPerSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Meter pro Sekunde",
                                other = "{0} Meter pro Sekunde",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Meter pro Sekunde",
                                other = "{0} Meter pro Sekunde",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Meter pro Sekunde",
                                other = "{0} Metern pro Sekunde",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Meters pro Sekunde",
                                other = "{0} Meter pro Sekunde",
                            )
                        ),
                    )
                ),
            )
        override val speedMilePerHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Meile pro Stunde",
                                other = "{0} Meilen pro Stunde",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Meile pro Stunde",
                                other = "{0} Meilen pro Stunde",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Meile pro Stunde",
                                other = "{0} Meilen pro Stunde",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Meile pro Stunde",
                                other = "{0} Meilen pro Stunde",
                            )
                        ),
                    )
                ),
            )
        override val speedKnot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Knoten",
                                other = "{0} Knoten",
                            )
                        ),
                    )
                ),
            )
        override val speedBeaufort
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "Beaufort {0}",
                                other = "Beaufort {0}",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "Beaufort {0}",
                                other = "Beaufort {0}",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "Beaufort {0}",
                                other = "Beaufort {0}",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "Beaufort {0}",
                                other = "Beaufort {0}",
                            )
                        ),
                    )
                ),
            )
        override val speedLightSpeed: MultiLengthGenderedPattern
            get() = TODO("LightSpeed formatter is not implemented yet for de")
        override val temperatureGeneric
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Grad",
                                other = "{0} Grad",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Grad",
                                other = "{0} Grad",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Grad",
                                other = "{0} Grad",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Grads",
                                other = "{0} Grad",
                            )
                        ),
                    )
                ),
            )
        override val temperatureCelsius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Grad Celsius",
                                other = "{0} Grad Celsius",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Grad Celsius",
                                other = "{0} Grad Celsius",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Grad Celsius",
                                other = "{0} Grad Celsius",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Grads Celsius",
                                other = "{0} Grad Celsius",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} °C",
                                other = "{0} °C",
                            )
                        ),
                    )
                ),
            )
        override val temperatureFahrenheit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Grad Fahrenheit",
                                other = "{0} Grad Fahrenheit",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Grad Fahrenheit",
                                other = "{0} Grad Fahrenheit",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Grad Fahrenheit",
                                other = "{0} Grad Fahrenheit",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Grads Fahrenheit",
                                other = "{0} Grad Fahrenheit",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} °F",
                                other = "{0} °F",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}°F",
                                other = "{0}°F",
                            )
                        ),
                    )
                ),
            )
        override val temperatureKelvin
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kelvin",
                                other = "{0} Kelvin",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kelvin",
                                other = "{0} Kelvin",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kelvin",
                                other = "{0} Kelvin",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kelvins",
                                other = "{0} Kelvin",
                            )
                        ),
                    )
                ),
            )
        override val torquePoundForceFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Foot-pound",
                                other = "{0} Foot-pound",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}lbf⋅ft",
                                other = "{0}lbf⋅ft",
                            )
                        ),
                    )
                ),
            )
        override val torqueNewtonMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Newtonmeter",
                                other = "{0} Newtonmeter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Newtonmeter",
                                other = "{0} Newtonmeter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Newtonmeter",
                                other = "{0} Newtonmetern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Newtonmeters",
                                other = "{0} Newtonmeter",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}N⋅m",
                                other = "{0}N⋅m",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikkilometer",
                                other = "{0} Kubikkilometer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikkilometer",
                                other = "{0} Kubikkilometer",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikkilometer",
                                other = "{0} Kubikkilometern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kubikkilometers",
                                other = "{0} Kubikkilometer",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikmeter",
                                other = "{0} Kubikmeter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikmeter",
                                other = "{0} Kubikmeter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikmeter",
                                other = "{0} Kubikmetern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kubikmeters",
                                other = "{0} Kubikmeter",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikzentimeter",
                                other = "{0} Kubikzentimeter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikzentimeter",
                                other = "{0} Kubikzentimeter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikzentimeter",
                                other = "{0} Kubikzentimetern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kubikzentimeters",
                                other = "{0} Kubikzentimeter",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicMile
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikmeile",
                                other = "{0} Kubikmeilen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikmeile",
                                other = "{0} Kubikmeilen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikmeile",
                                other = "{0} Kubikmeilen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kubikmeile",
                                other = "{0} Kubikmeilen",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicYard
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikyard",
                                other = "{0} Kubikyards",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikfuß",
                                other = "{0} Kubikfuß",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikfuß",
                                other = "{0} Kubikfuß",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikfuß",
                                other = "{0} Kubikfuß",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Kubikfußes",
                                other = "{0} Kubikfuß",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicInch
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kubikzoll",
                                other = "{0} Kubikzoll",
                            )
                        ),
                    )
                ),
            )
        override val volumeMegaliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Megaliter",
                                other = "{0} Megaliter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Megaliter",
                                other = "{0} Megaliter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Megaliter",
                                other = "{0} Megalitern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Megaliters",
                                other = "{0} Megaliter",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Ml",
                                other = "{0} Ml",
                            )
                        ),
                    )
                ),
            )
        override val volumeHectoliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Hektoliter",
                                other = "{0} Hektoliter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Hektoliter",
                                other = "{0} Hektoliter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Hektoliter",
                                other = "{0} Hektolitern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Hektoliters",
                                other = "{0} Hektoliter",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hl",
                                other = "{0} hl",
                            )
                        ),
                    )
                ),
            )
        override val volumeLiter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Liter",
                                other = "{0} Liter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Liter",
                                other = "{0} Liter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Liter",
                                other = "{0} Litern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Liters",
                                other = "{0} Liter",
                            )
                        ),
                    )
                ),
            )
        override val volumeDeciliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Deziliter",
                                other = "{0} Deziliter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Deziliter",
                                other = "{0} Deziliter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Deziliter",
                                other = "{0} Dezilitern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Deziliters",
                                other = "{0} Deziliter",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dl",
                                other = "{0} dl",
                            )
                        ),
                    )
                ),
            )
        override val volumeCentiliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Zentiliter",
                                other = "{0} Zentiliter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Zentiliter",
                                other = "{0} Zentiliter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Zentiliter",
                                other = "{0} Zentilitern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Zentiliters",
                                other = "{0} Zentiliter",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cl",
                                other = "{0} cl",
                            )
                        ),
                    )
                ),
            )
        override val volumeMilliliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Milliliter",
                                other = "{0} Milliliter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Milliliter",
                                other = "{0} Milliliter",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Milliliter",
                                other = "{0} Millilitern",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Milliliters",
                                other = "{0} Milliliter",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ml",
                                other = "{0} ml",
                            )
                        ),
                    )
                ),
            )
        override val volumePintMetric
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} metrisches Pint",
                                other = "{0} metrische Pints",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} metrische Pint",
                                other = "{0} metrischen Pints",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} metrischen Pint",
                                other = "{0} metrischen Pints",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} metrischen Pints",
                                other = "{0} metrischen Pints",
                            )
                        ),
                    )
                ),
            )
        override val volumeCupMetric
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} metrische Tasse",
                                other = "{0} metrische Tassen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} metrische Tasse",
                                other = "{0} metrischen Tassen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} metrischen Tasse",
                                other = "{0} metrischen Tassen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} metrischen Tasse",
                                other = "{0} metrischen Tassen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Ta",
                                other = "{0} Ta",
                            )
                        ),
                    )
                ),
            )
        override val volumeAcreFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Acre-Foot",
                                other = "{0} Acre-Feet",
                            )
                        ),
                    )
                ),
            )
        override val volumeBushel
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Bushel",
                                other = "{0} Bushel",
                            )
                        ),
                    )
                ),
            )
        override val volumeGallon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Gallone",
                                other = "{0} Gallonen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Gallone",
                                other = "{0} Gallonen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Gallone",
                                other = "{0} Gallonen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Gallone",
                                other = "{0} Gallonen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gal",
                                other = "{0} gal",
                            )
                        ),
                    )
                ),
            )
        override val volumeGallonImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Gallone",
                                other = "{0} Imp. Gallonen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Gallone",
                                other = "{0} Imp. Gallonen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Gallone",
                                other = "{0} Imp. Gallonen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Gallone",
                                other = "{0} Imp. Gallonen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. gal",
                                other = "{0} Imp. gal",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Imp.gal",
                                other = "{0} Imp.gal",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuart
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Quart",
                                other = "{0} Quart",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Quart",
                                other = "{0} Quart",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Quart",
                                other = "{0} Quart",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Quart",
                                other = "{0} Quart",
                            )
                        ),
                    )
                ),
            )
        override val volumePint
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Pint",
                                other = "{0} Pints",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Pint",
                                other = "{0} Pints",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Pint",
                                other = "{0} Pints",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Pints",
                                other = "{0} Pints",
                            )
                        ),
                    )
                ),
            )
        override val volumeCup
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Tasse",
                                other = "{0} Tassen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Tasse",
                                other = "{0} Tassen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Tasse",
                                other = "{0} Tassen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Tasse",
                                other = "{0} Tassen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Cup",
                                other = "{0} Cups",
                            )
                        ),
                    )
                ),
            )
        override val volumeFluidOunce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Flüssigunze",
                                other = "{0} Flüssigunzen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Flüssigunze",
                                other = "{0} Flüssigunzen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Flüssigunze",
                                other = "{0} Flüssigunzen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Flüssigunze",
                                other = "{0} Flüssigunzen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fl oz",
                                other = "{0} fl oz",
                            )
                        ),
                    )
                ),
            )
        override val volumeFluidOunceImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Flüssigunze",
                                other = "{0} Imp. Flüssigunzen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Flüssigunze",
                                other = "{0} Imp. Flüssigunzen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Flüssigunze",
                                other = "{0} Imp. Flüssigunzen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Flüssigunze",
                                other = "{0} Imp. Flüssigunzen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Imp.fl.oz.",
                                other = "{0} Imp.fl.oz.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Im.fl.oz",
                                other = "{0} Im.fl.oz",
                            )
                        ),
                    )
                ),
            )
        override val volumeTablespoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Esslöffel",
                                other = "{0} Esslöffel",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Esslöffel",
                                other = "{0} Esslöffel",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Esslöffel",
                                other = "{0} Esslöffeln",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Esslöffels",
                                other = "{0} Esslöffel",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} EL",
                                other = "{0} EL",
                            )
                        ),
                    )
                ),
            )
        override val volumeTeaspoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Teelöffel",
                                other = "{0} Teelöffel",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Teelöffel",
                                other = "{0} Teelöffel",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Teelöffel",
                                other = "{0} Teelöffeln",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Teelöffels",
                                other = "{0} Teelöffel",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} TL",
                                other = "{0} TL",
                            )
                        ),
                    )
                ),
            )
        override val volumeBarrel
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Barrel",
                                other = "{0} Barrel",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}bbl",
                                other = "{0}bbl",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Dessertlöffel",
                                other = "{0} Dessertlöffel",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Dessertlöffel",
                                other = "{0} Dessertlöffel",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Dessertlöffel",
                                other = "{0} Dessertlöffeln",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Dessertlöffels",
                                other = "{0} Dessertlöffel",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} DL",
                                other = "{0} DL",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoonImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Dessertlöffel",
                                other = "{0} Imp. Dessertlöffel",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Dessertlöffel",
                                other = "{0} Imp. Dessertlöffel",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Dessertlöffel",
                                other = "{0} Imp. Dessertlöffeln",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Dessertlöffels",
                                other = "{0} Imp. Dessertlöffel",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. DL",
                                other = "{0} Imp. DL",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Imp.DL",
                                other = "{0} Imp.DL",
                            )
                        ),
                    )
                ),
            )
        override val volumeDrop
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Tropfen",
                                other = "{0} Tropfen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Tropfen",
                                other = "{0} Tropfen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Tropfen",
                                other = "{0} Tropfen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Tropfens",
                                other = "{0} Tropfen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Trpf.",
                                other = "{0} Trpf.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Tr.",
                                other = "{0} Tr.",
                            )
                        ),
                    )
                ),
            )
        override val volumeDram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Dram",
                                other = "{0} Dram",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Dram",
                                other = "{0} Dram",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Dram",
                                other = "{0} Dram",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Dram",
                                other = "{0} Dram",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Fl.-Dram",
                                other = "{0} Fl.-Dram",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fl.dr.",
                                other = "{0} fl.dr.",
                            )
                        ),
                    )
                ),
            )
        override val volumeJigger
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Jigger",
                                other = "{0} Jigger",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Jigger",
                                other = "{0} Jigger",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Jigger",
                                other = "{0} Jigger",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Jiggers",
                                other = "{0} Jigger",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Jigger",
                                other = "{0} Jigger",
                            )
                        ),
                    )
                ),
            )
        override val volumePinch
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Prise",
                                other = "{0} Prisen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Prise",
                                other = "{0} Prisen",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Prise",
                                other = "{0} Prisen",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Prise",
                                other = "{0} Prisen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Pr.",
                                other = "{0} Pr.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Pr",
                                other = "{0} Pr",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuartImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Quart",
                                other = "{0} Imp. Quart",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Quart",
                                other = "{0} Imp. Quart",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Quart",
                                other = "{0} Imp. Quart",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Imp. Quart",
                                other = "{0} Imp. Quart",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Imp.qt.",
                                other = "{0} Imp.qt.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Imp.qt",
                                other = "{0} Imp.qt",
                            )
                        ),
                    )
                ),
            )
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    open class NumberLanguage internal constructor() : info.leonenko.i18n.NumberLanguage {
        override val symbols = NumberSymbols(
            decimalSymbol = ",",
            groupSymbol = ".",
            approximatelySignSymbol = "≈",
            superscriptingExponentSymbol = "·",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
