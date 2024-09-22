package info.leonenko.i18n.languages

import info.leonenko.i18n.*
import info.leonenko.i18n.rbnf.RBNFHr

val Language.Companion.hr
    get() = languageHr
private val languageHr = LanguageHr()

open class LanguageHr internal constructor() : Language {
    override val code: String = "hr"
    override val language: String = "hr"
    override val script: String? = null
    override val territory: String? = null
    override val rbnf: RBNFHr
        get() = RBNFHr(this)
    override val pluralFormChooser = PluralFormChooser.Expression(
        one = { (v == 0) && ((i % 10) == 1L) && ((i % 100) != 11L) || ((f % 10) == 1) && ((f % 100) != 11) },
        few = { (v == 0) && ((i % 10) in 2L..4L) && ((i % 100) !in 12L..14L) || ((f % 10) in 2..4) && ((f % 100) !in 12..14) },
    )

    open class EllipsisLanguage internal constructor() : info.leonenko.i18n.EllipsisLanguage {
        override val formatter = EllipsisFormatter.Default
        override val wordFormatter = EllipsisFormatter.WordDefault
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    open class ListLanguage internal constructor(val ellipsis: EllipsisLanguage) : info.leonenko.i18n.ListLanguage {
        override val formatter: ListFormatter = MultiLengthPatternedListFormatter(
            long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} i {1}", two = "{0} i {1}", ellipsis = ellipsis.wordFormatter),
        )
        override val orFormatter: ListFormatter =
            MultiLengthPatternedListFormatter(long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ili {1}", two = "{0} ili {1}", ellipsis = ellipsis.wordFormatter))
        override val unitFormatter: ListFormatter = MultiLengthPatternedListFormatter(
            narrow = PatternedListFormatter(start = "{0} {1}", middle = "{0} {1}", end = "{0} {1}", two = "{0} {1}", ellipsis = ellipsis.wordFormatter),
        )
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    open class MeasurementUnitLanguage internal constructor() : info.leonenko.i18n.units.MeasurementUnitLanguage {
        override val accelerationGForce: MultiLengthGenderedPattern
            get() = TODO("GForce formatter is not implemented yet for hr")
        override val accelerationMeterPerSquareSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} metar u sekundi na kvadrat",
                                few = "{0} metra u sekundi na kvadrat",
                                other = "{0} metara u sekundi na kvadrat",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} metar u sekundi na kvadrat",
                                few = "{0} metra u sekundi na kvadrat",
                                other = "{0} metara u sekundi na kvadrat",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} metra u sekundi na kvadrat",
                                few = "{0} metra u sekundi na kvadrat",
                                other = "{0} metara u sekundi na kvadrat",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} metrom u sekundi na kvadrat",
                                few = "{0} metra u sekundi na kvadrat",
                                other = "{0} metara u sekundi na kvadrat",
                            )
                        ),
                    )
                ),
            )
        override val angleRevolution
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} okretaj",
                                few = "{0} okretaja",
                                other = "{0} okretaja",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} okretaj",
                                few = "{0} okretaja",
                                other = "{0} okretaja",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} okretaja",
                                few = "{0} okretaja",
                                other = "{0} okretaja",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} okretajem",
                                few = "{0} okretaja",
                                other = "{0} okretaja",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} okr.",
                                few = "{0} okr.",
                                other = "{0} okr.",
                            )
                        ),
                    )
                ),
            )
        override val angleRadian
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} radijan",
                                few = "{0} radijana",
                                other = "{0} radijana",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} radijan",
                                few = "{0} radijana",
                                other = "{0} radijana",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} radijana",
                                few = "{0} radijana",
                                other = "{0} radijana",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} radijanom",
                                few = "{0} radijana",
                                other = "{0} radijana",
                            )
                        ),
                    )
                ),
            )
        override val angleDegree
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} stupanj",
                                few = "{0} stupnja",
                                other = "{0} stupnjeva",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} stupanj",
                                few = "{0} stupnja",
                                other = "{0} stupnjeva",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} stupnja",
                                few = "{0} stupnja",
                                other = "{0} stupnjeva",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} stupnjem",
                                few = "{0} stupnja",
                                other = "{0} stupnjeva",
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
                                one = "{0} kutna minuta",
                                few = "{0} kutne minute",
                                other = "{0} kutnih minuta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kutnu minutu",
                                few = "{0} kutne minute",
                                other = "{0} kutnih minuta",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kutne minute",
                                few = "{0} kutne minute",
                                other = "{0} kutnih minuta",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kutnom minutom",
                                few = "{0} kutne minute",
                                other = "{0} kutnih minuta",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} min",
                                few = "{0} min",
                                other = "{0} min",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}′",
                                few = "{0}′",
                                other = "{0}′",
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
                                one = "{0} kutna sekunda",
                                few = "{0} kutne sekunde",
                                other = "{0} kutnih sekundi",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kutnu sekundu",
                                few = "{0} kutne sekunde",
                                other = "{0} kutnih sekundi",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kutne sekunde",
                                few = "{0} kutne sekunde",
                                other = "{0} kutnih sekundi",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kutnom sekundom",
                                few = "{0} kutne sekunde",
                                other = "{0} kutnih sekundi",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} s",
                                few = "{0} s",
                                other = "{0} s",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kvadratni kilometar",
                                few = "{0} kvadratna kilometra",
                                other = "{0} kvadratnih kilometara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kvadratni kilometar",
                                few = "{0} kvadratna kilometra",
                                other = "{0} kvadratnih kilometara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kvadratnog kilometra",
                                few = "{0} kvadratna kilometra",
                                other = "{0} kvadratnih kilometara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kvadratnim kilometrom",
                                few = "{0} kvadratna kilometra",
                                other = "{0} kvadratnih kilometara",
                            )
                        ),
                    )
                ),
            )
        override val areaHectare
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hektar",
                                few = "{0} hektra",
                                other = "{0} hektara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} hektar",
                                few = "{0} hektra",
                                other = "{0} hektara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} hektra",
                                few = "{0} hektra",
                                other = "{0} hektara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} hektrom",
                                few = "{0} hektra",
                                other = "{0} hektara",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kvadratni metar",
                                few = "{0} kvadratna metra",
                                other = "{0} kvadratnih metara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kvadratni metar",
                                few = "{0} kvadratna metra",
                                other = "{0} kvadratnih metara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kvadratnog metra",
                                few = "{0} kvadratna metra",
                                other = "{0} kvadratnih metara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kvadratnim metrom",
                                few = "{0} kvadratna metra",
                                other = "{0} kvadratnih metara",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kvadratni centimetar",
                                few = "{0} kvadratna centimetra",
                                other = "{0} kvadratnih centimetara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kvadratni centimetar",
                                few = "{0} kvadratna centimetra",
                                other = "{0} kvadratnih centimetara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kvadratnog centimetra",
                                few = "{0} kvadratna centimetra",
                                other = "{0} kvadratnih centimetara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kvadratnim centimetrom",
                                few = "{0} kvadratna centimetra",
                                other = "{0} kvadratnih centimetara",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareMile
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kvadratna milja",
                                few = "{0} kvadratne milje",
                                other = "{0} kvadratnih milja",
                            )
                        ),
                    )
                ),
            )
        override val areaAcre
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} katastarsko jutro",
                                few = "{0} katastarska jutra",
                                other = "{0} katastarskih jutara",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kj",
                                few = "{0} kj",
                                other = "{0} kj",
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
                                one = "{0} kvadratni jard",
                                few = "{0} kvadratna jarda",
                                other = "{0} kvadratnih jardi",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kvadratna stopa",
                                few = "{0} kvadratne stope",
                                other = "{0} kvadratnih stopa",
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
                                one = "{0} kvadratni inč",
                                few = "{0} kvadratna inča",
                                other = "{0} kvadratnih inča",
                            )
                        ),
                    )
                ),
            )
        override val areaDunam
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dunam",
                                few = "{0} dunama",
                                other = "{0} dunama",
                            )
                        ),
                    )
                ),
            )
        override val concentrationKarat
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} karat",
                                few = "{0} karata",
                                other = "{0} karata",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} karat",
                                few = "{0} karata",
                                other = "{0} karata",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} karata",
                                few = "{0} karata",
                                other = "{0} karata",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} karatom",
                                few = "{0} karata",
                                other = "{0} karata",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermillion
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dio na milijun",
                                few = "{0} dijela na milijun",
                                other = "{0} dijelova na milijun",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} dio na milijun",
                                few = "{0} dijela na milijun",
                                other = "{0} dijelova na milijun",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} dijela na milijun",
                                few = "{0} dijela na milijun",
                                other = "{0} dijelova na milijun",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} dijelom na milijun",
                                few = "{0} dijela na milijun",
                                other = "{0} dijelova na milijun",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPercent
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} posto",
                                few = "{0} posto",
                                other = "{0} posto",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} posto",
                                few = "{0} posto",
                                other = "{0} posto",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} posto",
                                few = "{0} posto",
                                other = "{0} posto",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} posto",
                                few = "{0} posto",
                                other = "{0} posto",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} %",
                                few = "{0} %",
                                other = "{0} %",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermille
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} promil",
                                few = "{0} promila",
                                other = "{0} promila",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} promil",
                                few = "{0} promila",
                                other = "{0} promila",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} promila",
                                few = "{0} promila",
                                other = "{0} promila",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} promilom",
                                few = "{0} promila",
                                other = "{0} promila",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ‰",
                                few = "{0} ‰",
                                other = "{0} ‰",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermyriad
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} permyriad",
                                few = "{0} permyriada",
                                other = "{0} permyriada",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} permyriad",
                                few = "{0} permyriada",
                                other = "{0} permyriada",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} permyriada",
                                few = "{0} permyriada",
                                other = "{0} permyriada",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} permyriadom",
                                few = "{0} permyriada",
                                other = "{0} permyriada",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ‱",
                                few = "{0} ‱",
                                other = "{0} ‱",
                            )
                        ),
                    )
                ),
            )
        override val concentrationAPortion: MultiLengthGenderedPattern
            get() = TODO("APortion formatter is not implemented yet for hr")
        override val concentrationMilligramOfglucosePerDeciliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} miligram po decilitru",
                                few = "{0} miligrama po decilitru",
                                other = "{0} miligrama po decilitru",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mg/dl",
                                few = "{0} mg/dl",
                                other = "{0} mg/dl",
                            )
                        ),
                    )
                ),
            )
        override val concentrationMillimolePerLiter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milimol po litri",
                                few = "{0} milimola po litri",
                                other = "{0} milimola po litri",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milimol po litri",
                                few = "{0} milimola po litri",
                                other = "{0} milimola po litri",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} milimola po litri",
                                few = "{0} milimola po litri",
                                other = "{0} milimola po litri",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milimolom po litri",
                                few = "{0} milimola po litri",
                                other = "{0} milimola po litri",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mmol/l",
                                few = "{0} mmol/l",
                                other = "{0} mmol/l",
                            )
                        ),
                    )
                ),
            )
        override val concentrationItemPerCubicMeter: MultiLengthGenderedPattern
            get() = TODO("ItemPerCubicMeter formatter is not implemented yet for hr")
        override val concentrationItem
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} stavka",
                                few = "{0} stavke",
                                other = "{0} stavki",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} stavku",
                                few = "{0} stavke",
                                other = "{0} stavki",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} stavke",
                                few = "{0} stavke",
                                other = "{0} stavki",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} stavkom",
                                few = "{0} stavke",
                                other = "{0} stavki",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} stavka",
                                few = "{0} stavke",
                                other = "{0} stavki",
                            )
                        ),
                    )
                ),
            )
        override val concentrationMole
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mola",
                                few = "{0} mola",
                                other = "{0} mola",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mol",
                                few = "{0} mola",
                                other = "{0} mola",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mola",
                                few = "{0} mola",
                                other = "{0} mola",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} molom",
                                few = "{0} mola",
                                other = "{0} mola",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mola",
                                few = "{0} mola",
                                other = "{0} mola",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mol",
                                few = "{0} mola",
                                other = "{0} mola",
                            )
                        ),
                    )
                ),
            )
        override val consumptionLiterPerKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} litra po kilometru",
                                few = "{0} litre po kilometru",
                                other = "{0} litara po kilometru",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} litru po kilometru",
                                few = "{0} litre po kilometru",
                                other = "{0} litara po kilometru",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} litre po kilometru",
                                few = "{0} litre po kilometru",
                                other = "{0} litara po kilometru",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} litrom po kilometru",
                                few = "{0} litre po kilometru",
                                other = "{0} litara po kilometru",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} l/km",
                                few = "{0} l/km",
                                other = "{0} l/km",
                            )
                        ),
                    )
                ),
            )
        override val consumptionLiterPer100Kilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} litra na 100 kilometara",
                                few = "{0} litre na 100 kilometara",
                                other = "{0} litara na 100 kilometara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} litru na 100 kilometara",
                                few = "{0} litre na 100 kilometara",
                                other = "{0} litara na 100 kilometara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} litre na 100 kilometara",
                                few = "{0} litre na 100 kilometara",
                                other = "{0} litara na 100 kilometara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} litrom na 100 kilometara",
                                few = "{0} litre na 100 kilometara",
                                other = "{0} litara na 100 kilometara",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} l/100 km",
                                few = "{0} l/100 km",
                                other = "{0} l/100 km",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}l/100km",
                                few = "{0}l/100km",
                                other = "{0}l/100km",
                            )
                        ),
                    )
                ),
            )
        override val consumptionMilePerGallon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milja po galonu",
                                few = "{0} milje po galonu",
                                other = "{0} milja po galonu",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mpg",
                                few = "{0} mpg",
                                other = "{0} mpg",
                            )
                        ),
                    )
                ),
            )
        override val consumptionMilePerGallonImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milja po imp. galonu",
                                few = "{0} milje po imp. galonu",
                                other = "{0} milja po imp. galonu",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mpg imp.",
                                few = "{0} mpg imp.",
                                other = "{0} mpg imp.",
                            )
                        ),
                    )
                ),
            )
        override val digitalPetabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} petabajt",
                                few = "{0} petabajta",
                                other = "{0} petabajta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} petabajt",
                                few = "{0} petabajta",
                                other = "{0} petabajta",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} petabajta",
                                few = "{0} petabajta",
                                other = "{0} petabajta",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} petabajtom",
                                few = "{0} petabajta",
                                other = "{0} petabajta",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} terabajt",
                                few = "{0} terabajta",
                                other = "{0} terabajta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} terabajt",
                                few = "{0} terabajta",
                                other = "{0} terabajta",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} terabajta",
                                few = "{0} terabajta",
                                other = "{0} terabajta",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} terabajtom",
                                few = "{0} terabajta",
                                other = "{0} terabajta",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} terabit",
                                few = "{0} terabita",
                                other = "{0} terabita",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} terabit",
                                few = "{0} terabita",
                                other = "{0} terabita",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} terabita",
                                few = "{0} terabita",
                                other = "{0} terabita",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} terabitom",
                                few = "{0} terabita",
                                other = "{0} terabita",
                            )
                        ),
                    )
                ),
            )
        override val digitalGigabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigabajt",
                                few = "{0} gigabajta",
                                other = "{0} gigabajta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gigabajt",
                                few = "{0} gigabajta",
                                other = "{0} gigabajta",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} gigabajta",
                                few = "{0} gigabajta",
                                other = "{0} gigabajta",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} gigabajtom",
                                few = "{0} gigabajta",
                                other = "{0} gigabajta",
                            )
                        ),
                    )
                ),
            )
        override val digitalGigabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigabit",
                                few = "{0} gigabita",
                                other = "{0} gigabita",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gigabit",
                                few = "{0} gigabita",
                                other = "{0} gigabita",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} gigabita",
                                few = "{0} gigabita",
                                other = "{0} gigabita",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} gigabitom",
                                few = "{0} gigabita",
                                other = "{0} gigabita",
                            )
                        ),
                    )
                ),
            )
        override val digitalMegabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megabajt",
                                few = "{0} megabajta",
                                other = "{0} megabajta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megabajt",
                                few = "{0} megabajta",
                                other = "{0} megabajta",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} megabajta",
                                few = "{0} megabajta",
                                other = "{0} megabajta",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} megabajtom",
                                few = "{0} megabajta",
                                other = "{0} megabajta",
                            )
                        ),
                    )
                ),
            )
        override val digitalMegabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megabit",
                                few = "{0} megabita",
                                other = "{0} megabita",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megabit",
                                few = "{0} megabita",
                                other = "{0} megabita",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} megabita",
                                few = "{0} megabita",
                                other = "{0} megabita",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} megabitom",
                                few = "{0} megabita",
                                other = "{0} megabita",
                            )
                        ),
                    )
                ),
            )
        override val digitalKilobyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilobajt",
                                few = "{0} kilobajta",
                                other = "{0} kilobajta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilobajt",
                                few = "{0} kilobajta",
                                other = "{0} kilobajta",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilobajta",
                                few = "{0} kilobajta",
                                other = "{0} kilobajta",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilobajtom",
                                few = "{0} kilobajta",
                                other = "{0} kilobajta",
                            )
                        ),
                    )
                ),
            )
        override val digitalKilobit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilobit",
                                few = "{0} kilobita",
                                other = "{0} kilobita",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilobit",
                                few = "{0} kilobita",
                                other = "{0} kilobita",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilobita",
                                few = "{0} kilobita",
                                other = "{0} kilobita",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilobitom",
                                few = "{0} kilobita",
                                other = "{0} kilobita",
                            )
                        ),
                    )
                ),
            )
        override val digitalByte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bajt",
                                few = "{0} bajta",
                                other = "{0} bajtova",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} bajt",
                                few = "{0} bajta",
                                other = "{0} bajtova",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} bajta",
                                few = "{0} bajta",
                                other = "{0} bajtova",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} bajtom",
                                few = "{0} bajta",
                                other = "{0} bajtova",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bajt",
                                few = "{0} bajta",
                                other = "{0} bajtova",
                            )
                        ),
                    )
                ),
            )
        override val digitalBit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bit",
                                few = "{0} bita",
                                other = "{0} bitova",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} bit",
                                few = "{0} bita",
                                other = "{0} bitova",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} bita",
                                few = "{0} bita",
                                other = "{0} bitova",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} bitom",
                                few = "{0} bita",
                                other = "{0} bitova",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bit",
                                few = "{0} bita",
                                other = "{0} bitova",
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
                                one = "{0} stoljeće",
                                few = "{0} stoljeća",
                                other = "{0} stoljeća",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} stoljeće",
                                few = "{0} stoljeća",
                                other = "{0} stoljeća",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} stoljeća",
                                few = "{0} stoljeća",
                                other = "{0} stoljeća",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} stoljećem",
                                few = "{0} stoljeća",
                                other = "{0} stoljeća",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} st.",
                                few = "{0} st.",
                                other = "{0} st.",
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
                                one = "{0} desetljeće",
                                few = "{0} desetljeća",
                                other = "{0} desetljeća",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} desetljeće",
                                few = "{0} desetljeća",
                                other = "{0} desetljeća",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} desetljeća",
                                few = "{0} desetljeća",
                                other = "{0} desetljeća",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} desetljećem",
                                few = "{0} desetljeća",
                                other = "{0} desetljeća",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} des.",
                                few = "{0} des.",
                                other = "{0} des.",
                            )
                        ),
                    )
                ),
            )
        override val durationYear
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} godina",
                                few = "{0} godine",
                                other = "{0} godina",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} godinu",
                                few = "{0} godine",
                                other = "{0} godina",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} godine",
                                few = "{0} godine",
                                other = "{0} godina",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} godinom",
                                few = "{0} godine",
                                other = "{0} godina",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} g.",
                                few = "{0} g.",
                                other = "{0} g.",
                            )
                        ),
                    )
                ),
            )
        override val durationQuarter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} četvrtina",
                                few = "{0} četvrtine",
                                other = "{0} četvrtina",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} četvrtinu",
                                few = "{0} četvrtine",
                                other = "{0} četvrtina",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} četvrtine",
                                few = "{0} četvrtina",
                                other = "{0} četvrtina",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} četvrtinom",
                                few = "{0} četvrtine",
                                other = "{0} četvrtina",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} čet.",
                                few = "{0} čet.",
                                other = "{0} čet.",
                            )
                        ),
                    )
                ),
            )
        override val durationMonth
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mjesec",
                                few = "{0} mjeseca",
                                other = "{0} mjeseci",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mjesec",
                                few = "{0} mjeseca",
                                other = "{0} mjeseci",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mjeseca",
                                few = "{0} mjeseca",
                                other = "{0} mjeseci",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} mjesecom",
                                few = "{0} mjeseca",
                                other = "{0} mjeseci",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mj.",
                                few = "{0} mj.",
                                other = "{0} mj.",
                            )
                        ),
                    )
                ),
            )
        override val durationWeek
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} tjedan",
                                few = "{0} tjedna",
                                other = "{0} tjedana",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} tjedan",
                                few = "{0} tjedna",
                                other = "{0} tjedana",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} tjedna",
                                few = "{0} tjedna",
                                other = "{0} tjedana",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} tjednom",
                                few = "{0} tjedna",
                                other = "{0} tjedana",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} tj.",
                                few = "{0} tj.",
                                other = "{0} tj.",
                            )
                        ),
                    )
                ),
            )
        override val durationDay
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dan",
                                few = "{0} dana",
                                other = "{0} dana",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} dan",
                                few = "{0} dana",
                                other = "{0} dana",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} dana",
                                few = "{0} dana",
                                other = "{0} dana",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} danom",
                                few = "{0} dana",
                                other = "{0} dana",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dan",
                                few = "{0} dana",
                                other = "{0} dana",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} d.",
                                few = "{0} d.",
                                other = "{0} d.",
                            )
                        ),
                    )
                ),
            )
        override val durationHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} sat",
                                few = "{0} sata",
                                other = "{0} sati",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} sat",
                                few = "{0} sata",
                                other = "{0} sati",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} sata",
                                few = "{0} sata",
                                other = "{0} sati",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} satom",
                                few = "{0} sata",
                                other = "{0} sati",
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
                                one = "{0} minuta",
                                few = "{0} minute",
                                other = "{0} minuta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} minutu",
                                few = "{0} minute",
                                other = "{0} minuta",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} minute",
                                few = "{0} minute",
                                other = "{0} minuta",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} minutom",
                                few = "{0} minute",
                                other = "{0} minuta",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} m",
                                few = "{0} m",
                                other = "{0} m",
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
                                one = "{0} sekunda",
                                few = "{0} sekunde",
                                other = "{0} sekundi",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} sekundu",
                                few = "{0} sekunde",
                                other = "{0} sekundi",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} sekunde",
                                few = "{0} sekunde",
                                other = "{0} sekundi",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} sekundom",
                                few = "{0} sekunde",
                                other = "{0} sekundi",
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
                                one = "{0} milisekunda",
                                few = "{0} milisekunde",
                                other = "{0} milisekundi",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milisekundu",
                                few = "{0} milisekunde",
                                other = "{0} milisekundi",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} milisekunde",
                                few = "{0} milisekunde",
                                other = "{0} milisekundi",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milisekundom",
                                few = "{0} milisekunde",
                                other = "{0} milisekundi",
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
                                one = "{0} mikrosekunda",
                                few = "{0} mikrosekunde",
                                other = "{0} mikrosekundi",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mikrosekundu",
                                few = "{0} mikrosekunde",
                                other = "{0} mikrosekundi",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mikrosekunde",
                                few = "{0} mikrosekunde",
                                other = "{0} mikrosekundi",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} mikrosekundom",
                                few = "{0} mikrosekunde",
                                other = "{0} mikrosekundi",
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
                                one = "{0} nanosekunda",
                                few = "{0} nanosekunde",
                                other = "{0} nanosekundi",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} nanosekundu",
                                few = "{0} nanosekunde",
                                other = "{0} nanosekundi",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} nanosekunde",
                                few = "{0} nanosekunde",
                                other = "{0} nanosekundi",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} nanosekundom",
                                few = "{0} nanosekunde",
                                other = "{0} nanosekundi",
                            )
                        ),
                    )
                ),
            )
        override val electricAmpere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} amper",
                                few = "{0} ampera",
                                other = "{0} ampera",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} amper",
                                few = "{0} ampera",
                                other = "{0} ampera",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} ampera",
                                few = "{0} ampera",
                                other = "{0} ampera",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} amperom",
                                few = "{0} ampera",
                                other = "{0} ampera",
                            )
                        ),
                    )
                ),
            )
        override val electricMilliampere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} miliamper",
                                few = "{0} miliampera",
                                other = "{0} miliampera",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} miliamper",
                                few = "{0} miliampera",
                                other = "{0} miliampera",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} miliampera",
                                few = "{0} miliampera",
                                other = "{0} miliampera",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} miliamperom",
                                few = "{0} miliampera",
                                other = "{0} miliampera",
                            )
                        ),
                    )
                ),
            )
        override val electricOhm
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} om",
                                few = "{0} oma",
                                other = "{0} oma",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} om",
                                few = "{0} oma",
                                other = "{0} oma",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} oma",
                                few = "{0} oma",
                                other = "{0} oma",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} omom",
                                few = "{0} oma",
                                other = "{0} oma",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondSquareAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondSquareAmpere formatter is not implemented yet for hr")
        override val electricVolt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} volt",
                                few = "{0} volta",
                                other = "{0} volti",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} volt",
                                few = "{0} volta",
                                other = "{0} volti",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} volta",
                                few = "{0} volta",
                                other = "{0} volta",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} voltom",
                                few = "{0} volta",
                                other = "{0} volta",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondAmpere formatter is not implemented yet for hr")
        override val energyKilocalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilokalorija",
                                few = "{0} kilokalorije",
                                other = "{0} kilokalorija",
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
                                one = "{0} kalorija",
                                few = "{0} kalorije",
                                other = "{0} kalorija",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kaloriju",
                                few = "{0} kalorije",
                                other = "{0} kalorija",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kalorije",
                                few = "{0} kalorije",
                                other = "{0} kalorija",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kalorijom",
                                few = "{0} kalorije",
                                other = "{0} kalorija",
                            )
                        ),
                    )
                ),
            )
        override val energyFoodcalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilokalorija",
                                few = "{0} kilokalorije",
                                other = "{0} kilokalorija",
                            )
                        ),
                    )
                ),
            )
        override val energyKilojoule
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilodžul",
                                few = "{0} kilodžula",
                                other = "{0} kilodžula",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilodžul",
                                few = "{0} kilodžula",
                                other = "{0} kilodžula",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilodžula",
                                few = "{0} kilodžula",
                                other = "{0} kilodžula",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilodžulom",
                                few = "{0} kilodžula",
                                other = "{0} kilodžula",
                            )
                        ),
                    )
                ),
            )
        override val energyJoule
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} džul",
                                few = "{0} džula",
                                other = "{0} džula",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} džul",
                                few = "{0} džula",
                                other = "{0} džula",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} džula",
                                few = "{0} džula",
                                other = "{0} džula",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} džulom",
                                few = "{0} džula",
                                other = "{0} džula",
                            )
                        ),
                    )
                ),
            )
        override val energyKilowattHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilovatsat",
                                few = "{0} kilovatsata",
                                other = "{0} kilovatsati",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilovatsat",
                                few = "{0} kilovatsata",
                                other = "{0} kilovatsati",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilovatsata",
                                few = "{0} kilovatsata",
                                other = "{0} kilovatsati",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilovatsatom",
                                few = "{0} kilovatsata",
                                other = "{0} kilovatsati",
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
                                one = "{0} elektronvolt",
                                few = "{0} elektronvolta",
                                other = "{0} elektronvolta",
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
                                one = "{0} britanska termalna jedinica",
                                few = "{0} britanske termalne jedinice",
                                other = "{0} britanskih termalnih jedinica",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} BTU",
                                few = "{0} BTU",
                                other = "{0} BTU",
                            )
                        ),
                    )
                ),
            )
        override val energyThermUs
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} SAD therm",
                                few = "{0} SAD therma",
                                other = "{0} SAD therma",
                            )
                        ),
                    )
                ),
            )
        override val energyKilogramSquareMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerSquareSecond formatter is not implemented yet for hr")
        override val forcePoundForce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} funta sile",
                                few = "{0} funte sile",
                                other = "{0} funti sile",
                            )
                        ),
                    )
                ),
            )
        override val forceNewton
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} njutn",
                                few = "{0} njutna",
                                other = "{0} njutna",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} njutn",
                                few = "{0} njutna",
                                other = "{0} njutna",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} njutna",
                                few = "{0} njutna",
                                other = "{0} njutna",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} njutnom",
                                few = "{0} njutna",
                                other = "{0} njutna",
                            )
                        ),
                    )
                ),
            )
        override val forceKilowattHourPer100Kilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilovatsat na 100 kilometara",
                                few = "{0} kilovatsata na 100 kilometara",
                                other = "{0} kilovatsati na 100 kilometara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilovatsat na 100 kilometara",
                                few = "{0} kilovatsata na 100 kilometara",
                                other = "{0} kilovatsati na 100 kilometara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilovatsata na 100 kilometara",
                                few = "{0} kilovatsata na 100 kilometara",
                                other = "{0} kilovatsati na 100 kilometara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilovatsatom na 100 kilometara",
                                few = "{0} kilovatsata na 100 kilometara",
                                other = "{0} kilovatsati na 100 kilometara",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kWh/100 km",
                                few = "{0} kWh/100 km",
                                other = "{0} kWh/100 km",
                            )
                        ),
                    )
                ),
            )
        override val forceKilogramMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramMeterPerSquareSecond formatter is not implemented yet for hr")
        override val frequencyGigahertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigaherc",
                                few = "{0} gigaherca",
                                other = "{0} gigaherca",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gigaherc",
                                few = "{0} gigaherca",
                                other = "{0} gigaherca",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} gigaherca",
                                few = "{0} gigaherca",
                                other = "{0} gigaherca",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} gigahercom",
                                few = "{0} gigaherca",
                                other = "{0} gigaherca",
                            )
                        ),
                    )
                ),
            )
        override val frequencyMegahertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megaherc",
                                few = "{0} megaherca",
                                other = "{0} megaherca",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megaherc",
                                few = "{0} megaherca",
                                other = "{0} megaherca",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} megaherca",
                                few = "{0} megaherca",
                                other = "{0} megaherca",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} megahercom",
                                few = "{0} megaherca",
                                other = "{0} megaherca",
                            )
                        ),
                    )
                ),
            )
        override val frequencyKilohertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kiloherc",
                                few = "{0} kiloherca",
                                other = "{0} kiloherca",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kiloherc",
                                few = "{0} kiloherca",
                                other = "{0} kiloherca",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kiloherca",
                                few = "{0} kiloherca",
                                other = "{0} kiloherca",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilohercom",
                                few = "{0} kiloherca",
                                other = "{0} kiloherca",
                            )
                        ),
                    )
                ),
            )
        override val frequencyHertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} herc",
                                few = "{0} herca",
                                other = "{0} herca",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} herc",
                                few = "{0} herca",
                                other = "{0} herca",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} herca",
                                few = "{0} herca",
                                other = "{0} herca",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} hercom",
                                few = "{0} herca",
                                other = "{0} herca",
                            )
                        ),
                    )
                ),
            )
        override val frequencyRevolutionPerSecond: MultiLengthGenderedPattern
            get() = TODO("RevolutionPerSecond formatter is not implemented yet for hr")
        override val graphicsEm
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} tipografski em",
                                few = "{0} tipografska ema",
                                other = "{0} tipografskih ema",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} tipografski em",
                                few = "{0} tipografska ema",
                                other = "{0} tipografskih ema",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} tipografskog ema",
                                few = "{0} tipografska ema",
                                other = "{0} tipografskih ema",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} tipografskim emom",
                                few = "{0} tipografska ema",
                                other = "{0} tipografskih ema",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} em",
                                few = "{0} ema",
                                other = "{0} emova",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixel
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} piksel",
                                few = "{0} piksela",
                                other = "{0} piksela",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} piksel",
                                few = "{0} piksela",
                                other = "{0} piksela",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} piksela",
                                few = "{0} piksela",
                                other = "{0} piksela",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} pikselom",
                                few = "{0} piksela",
                                other = "{0} piksela",
                            )
                        ),
                    )
                ),
            )
        override val graphicsMegapixel
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megapiksel",
                                few = "{0} megapiksela",
                                other = "{0} megapiksela",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megapiksel",
                                few = "{0} megapiksela",
                                other = "{0} megapiksela",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} megapiksela",
                                few = "{0} megapiksela",
                                other = "{0} megapiksela",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} megapikselom",
                                few = "{0} megapiksela",
                                other = "{0} megapiksela",
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
                                one = "{0} točka",
                                few = "{0} točke",
                                other = "{0} točaka",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} p",
                                few = "{0} p",
                                other = "{0} p",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixelPerCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} piksel po centimetru",
                                few = "{0} piksela po centimetru",
                                other = "{0} piksela po centimetru",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} piksel po centimetru",
                                few = "{0} piksela po centimetru",
                                other = "{0} piksela po centimetru",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} piksela po centimetru",
                                few = "{0} piksela po centimetru",
                                other = "{0} piksela po centimetru",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} pikselom po centimetru",
                                few = "{0} piksela po centimetru",
                                other = "{0} piksela po centimetru",
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
                                one = "{0} piksel po inču",
                                few = "{0} piksela po inču",
                                other = "{0} piksela po inču",
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
                                one = "{0} točka po centimetru",
                                few = "{0} točke po centimetru",
                                other = "{0} točaka po centimetru",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dpcm",
                                few = "{0} dpcm",
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
                                one = "{0} točka po inču",
                                few = "{0} točke po inču",
                                other = "{0} točaka po inču",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dpi",
                                few = "{0} dpi",
                                other = "{0} dpi",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixelPerMeter: MultiLengthGenderedPattern
            get() = TODO("PixelPerMeter formatter is not implemented yet for hr")
        override val lengthEarthRadius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} srednji polumjer Zemlje",
                                few = "{0} srednja polumjera Zemlje",
                                other = "{0} srednjih polumjera Zemlje",
                            )
                        ),
                    )
                ),
            )
        override val lengthKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilometar",
                                few = "{0} kilometra",
                                other = "{0} kilometara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilometar",
                                few = "{0} kilometra",
                                other = "{0} kilometara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilometra",
                                few = "{0} kilometra",
                                other = "{0} kilometara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilometrom",
                                few = "{0} kilometra",
                                other = "{0} kilometara",
                            )
                        ),
                    )
                ),
            )
        override val lengthMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} metar",
                                few = "{0} metra",
                                other = "{0} metara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} metar",
                                few = "{0} metra",
                                other = "{0} metara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} metra",
                                few = "{0} metra",
                                other = "{0} metara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} metrom",
                                few = "{0} metra",
                                other = "{0} metara",
                            )
                        ),
                    )
                ),
            )
        override val lengthDecimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} decimetar",
                                few = "{0} decimetra",
                                other = "{0} decimetara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} decimetar",
                                few = "{0} decimetra",
                                other = "{0} decimetara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} decimetra",
                                few = "{0} decimetra",
                                other = "{0} decimetara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} decimetrom",
                                few = "{0} decimetra",
                                other = "{0} decimetara",
                            )
                        ),
                    )
                ),
            )
        override val lengthCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} centimetar",
                                few = "{0} centimetra",
                                other = "{0} centimetara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} centimetar",
                                few = "{0} centimetra",
                                other = "{0} centimetara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} centimetra",
                                few = "{0} centimetra",
                                other = "{0} centimetara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} centimetrom",
                                few = "{0} centimetra",
                                other = "{0} centimetara",
                            )
                        ),
                    )
                ),
            )
        override val lengthMillimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milimetar",
                                few = "{0} milimetra",
                                other = "{0} milimetara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milimetar",
                                few = "{0} milimetra",
                                other = "{0} milimetara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} milimetra",
                                few = "{0} milimetra",
                                other = "{0} milimetara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milimetrom",
                                few = "{0} milimetra",
                                other = "{0} milimetara",
                            )
                        ),
                    )
                ),
            )
        override val lengthMicrometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mikrometar",
                                few = "{0} mikrometra",
                                other = "{0} mikrometara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mikrometar",
                                few = "{0} mikrometra",
                                other = "{0} mikrometara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mikrometra",
                                few = "{0} mikrometra",
                                other = "{0} mikrometara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} mikrometrom",
                                few = "{0} mikrometra",
                                other = "{0} mikrometara",
                            )
                        ),
                    )
                ),
            )
        override val lengthNanometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} nanometar",
                                few = "{0} nanometra",
                                other = "{0} nanometara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} nanometar",
                                few = "{0} nanometra",
                                other = "{0} nanometara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} nanometra",
                                few = "{0} nanometra",
                                other = "{0} nanometara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} nanometrom",
                                few = "{0} nanometra",
                                other = "{0} nanometara",
                            )
                        ),
                    )
                ),
            )
        override val lengthPicometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pikometar",
                                few = "{0} pikometra",
                                other = "{0} pikometara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} pikometar",
                                few = "{0} pikometra",
                                other = "{0} pikometara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} pikometra",
                                few = "{0} pikometra",
                                other = "{0} pikometara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} pikometrom",
                                few = "{0} pikometra",
                                other = "{0} pikometara",
                            )
                        ),
                    )
                ),
            )
        override val lengthMile
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milja",
                                few = "{0} milje",
                                other = "{0} milja",
                            )
                        ),
                    )
                ),
            )
        override val lengthYard
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} jard",
                                few = "{0} jarda",
                                other = "{0} jardi",
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
                                one = "{0} stopa",
                                few = "{0} stope",
                                other = "{0} stopa",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}′",
                                few = "{0}′",
                                other = "{0}′",
                            )
                        ),
                    )
                ),
            )
        override val lengthInch
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} inč",
                                few = "{0} inča",
                                other = "{0} inča",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}″",
                                few = "{0}″",
                                other = "{0}″",
                            )
                        ),
                    )
                ),
            )
        override val lengthParsec
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} parsek",
                                few = "{0} parseka",
                                other = "{0} parseka",
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
                                one = "{0} svjetlosna godina",
                                few = "{0} svjetlosne godine",
                                other = "{0} svjetlosnih godina",
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
                                one = "{0} astronomska jedinica",
                                few = "{0} astronomske jedinice",
                                other = "{0} astronomskih jedinica",
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
                                one = "{0} furlong",
                                few = "{0} furlonga",
                                other = "{0} furlonga",
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
                                one = "{0} hvat",
                                few = "{0} hvata",
                                other = "{0} hvati",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hv",
                                few = "{0} hv",
                                other = "{0} hv",
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
                                one = "{0} nautička milja",
                                few = "{0} nautičke milje",
                                other = "{0} nautičkih milja",
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
                                one = "{0} skandinavska milja",
                                few = "{0} skandinavske milje",
                                other = "{0} skandinavskih milja",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} skandinavsku milju",
                                few = "{0} skandinavske milje",
                                other = "{0} skandinavskih milja",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} skandinavske milje",
                                few = "{0} skandinavske milje",
                                other = "{0} skandinavskih milja",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} skandinavskom miljom",
                                few = "{0} skandinavske milje",
                                other = "{0} skandinavskih milja",
                            )
                        ),
                    )
                ),
            )
        override val lengthPoint
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} tipografska točka",
                                few = "{0} tipografske točke",
                                other = "{0} tipografskih točaka",
                            )
                        ),
                    )
                ),
            )
        override val lengthSolarRadius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Sunčev polumjer",
                                few = "{0} Sunčeva polumjera",
                                other = "{0} Sunčevih polumjera",
                            )
                        ),
                    )
                ),
            )
        override val lightLux
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} luks",
                                few = "{0} luksa",
                                other = "{0} luksa",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} luks",
                                few = "{0} luksa",
                                other = "{0} luksa",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} luksa",
                                few = "{0} luksa",
                                other = "{0} luksa",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} luksom",
                                few = "{0} luksa",
                                other = "{0} luksa",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaPerSquareMeter formatter is not implemented yet for hr")
        override val lightCandela
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kandela",
                                few = "{0} kandele",
                                other = "{0} kandela",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kandelu",
                                few = "{0} kandele",
                                other = "{0} kandela",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kandele",
                                few = "{0} kandele",
                                other = "{0} kandela",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kandelom",
                                few = "{0} kandele",
                                other = "{0} kandela",
                            )
                        ),
                    )
                ),
            )
        override val lightLumen
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} lumen",
                                few = "{0} lumena",
                                other = "{0} lumena",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} lumen",
                                few = "{0} lumena",
                                other = "{0} lumena",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} lumena",
                                few = "{0} lumena",
                                other = "{0} lumena",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} lumenom",
                                few = "{0} lumena",
                                other = "{0} lumena",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaSquareMeterPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaSquareMeterPerSquareMeter formatter is not implemented yet for hr")
        override val lightSolarLuminosity
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} sjaj Sunca",
                                few = "{0} sjaja Sunca",
                                other = "{0} sjaja Sunca",
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
                                one = "{0} tona",
                                few = "{0} tone",
                                other = "{0} tona",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} tonu",
                                few = "{0} tone",
                                other = "{0} tona",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} tone",
                                few = "{0} tone",
                                other = "{0} tona",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} tonom",
                                few = "{0} tone",
                                other = "{0} tona",
                            )
                        ),
                    )
                ),
            )
        override val massKilogram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilogram",
                                few = "{0} kilograma",
                                other = "{0} kilograma",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilogram",
                                few = "{0} kilograma",
                                other = "{0} kilograma",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilograma",
                                few = "{0} kilograma",
                                other = "{0} kilograma",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilogramom",
                                few = "{0} kilograma",
                                other = "{0} kilograma",
                            )
                        ),
                    )
                ),
            )
        override val massGram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gram",
                                few = "{0} grama",
                                other = "{0} grama",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gram",
                                few = "{0} grama",
                                other = "{0} grama",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} grama",
                                few = "{0} grama",
                                other = "{0} grama",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} gramom",
                                few = "{0} grama",
                                other = "{0} grama",
                            )
                        ),
                    )
                ),
            )
        override val massMilligram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} miligram",
                                few = "{0} miligrama",
                                other = "{0} miligrama",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} miligram",
                                few = "{0} miligrama",
                                other = "{0} miligrama",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} miligrama",
                                few = "{0} miligrama",
                                other = "{0} miligrama",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} miligramom",
                                few = "{0} miligrama",
                                other = "{0} miligrama",
                            )
                        ),
                    )
                ),
            )
        override val massMicrogram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mikrogram",
                                few = "{0} mikrograma",
                                other = "{0} mikrograma",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mikrogram",
                                few = "{0} mikrograma",
                                other = "{0} mikrograma",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mikrograma",
                                few = "{0} mikrograma",
                                other = "{0} mikrograma",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} mikrogramom",
                                few = "{0} mikrograma",
                                other = "{0} mikrograma",
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
                                one = "{0} kratka tona",
                                few = "{0} kratke tone",
                                other = "{0} kratkih tona",
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
                                one = "{0} kamen",
                                few = "{0} kamena",
                                other = "{0} kamena",
                            )
                        ),
                    )
                ),
            )
        override val massPound
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} funta",
                                few = "{0} funte",
                                other = "{0} funti",
                            )
                        ),
                    )
                ),
            )
        override val massOunce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} unca",
                                few = "{0} unce",
                                other = "{0} unci",
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
                                one = "{0} troy unca",
                                few = "{0} troy unce",
                                other = "{0} troy unci",
                            )
                        ),
                    )
                ),
            )
        override val massCarat
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} karat",
                                few = "{0} karata",
                                other = "{0} karata",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} karat",
                                few = "{0} karata",
                                other = "{0} karata",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} karata",
                                few = "{0} karata",
                                other = "{0} karata",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} karatom",
                                few = "{0} karata",
                                other = "{0} karata",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ct",
                                few = "{0} ct",
                                other = "{0} ct",
                            )
                        ),
                    )
                ),
            )
        override val massDalton
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dalton",
                                few = "{0} daltona",
                                other = "{0} daltona",
                            )
                        ),
                    )
                ),
            )
        override val massEarthMass
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Zemljina masa",
                                few = "{0} Zemljine mase",
                                other = "{0} Zemljinih masa",
                            )
                        ),
                    )
                ),
            )
        override val massSolarMass
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Sunčeva masa",
                                few = "{0} Sunčeve mase",
                                other = "{0} Sunčevih masa",
                            )
                        ),
                    )
                ),
            )
        override val massGrain
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} grain",
                                few = "{0} graina",
                                other = "{0} graina",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gr",
                                few = "{0} gr",
                                other = "{0} gr",
                            )
                        ),
                    )
                ),
            )
        override val powerGigawatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigavat",
                                few = "{0} gigavata",
                                other = "{0} gigavata",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gigavat",
                                few = "{0} gigavata",
                                other = "{0} gigavata",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} gigavata",
                                few = "{0} gigavata",
                                other = "{0} gigavata",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} gigavatom",
                                few = "{0} gigavata",
                                other = "{0} gigavata",
                            )
                        ),
                    )
                ),
            )
        override val powerMegawatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megavat",
                                few = "{0} megavata",
                                other = "{0} megavata",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megavat",
                                few = "{0} megavata",
                                other = "{0} megavata",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} megavata",
                                few = "{0} megavata",
                                other = "{0} megavata",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} megavatom",
                                few = "{0} megavata",
                                other = "{0} megavata",
                            )
                        ),
                    )
                ),
            )
        override val powerKilowatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilovat",
                                few = "{0} kilovata",
                                other = "{0} kilovata",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilovat",
                                few = "{0} kilovata",
                                other = "{0} kilovata",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilovata",
                                few = "{0} kilovata",
                                other = "{0} kilovata",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilovatom",
                                few = "{0} kilovata",
                                other = "{0} kilovata",
                            )
                        ),
                    )
                ),
            )
        override val powerWatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} vat",
                                few = "{0} vata",
                                other = "{0} vati",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} vat",
                                few = "{0} vata",
                                other = "{0} vati",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} vata",
                                few = "{0} vata",
                                other = "{0} vati",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} vatom",
                                few = "{0} vata",
                                other = "{0} vati",
                            )
                        ),
                    )
                ),
            )
        override val powerMilliwatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milivat",
                                few = "{0} milivata",
                                other = "{0} milivata",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milivat",
                                few = "{0} milivata",
                                other = "{0} milivata",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} milivata",
                                few = "{0} milivata",
                                other = "{0} milivata",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milivatom",
                                few = "{0} milivata",
                                other = "{0} milivata",
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
                                one = "{0} konjska snaga",
                                few = "{0} konjske snage",
                                other = "{0} konjskih snaga",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} KS",
                                few = "{0} KS",
                                other = "{0} KS",
                            )
                        ),
                    )
                ),
            )
        override val powerKilogramSquareMeterPerCubicSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecond formatter is not implemented yet for hr")
        override val pressureMillimeterOfhg
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milimetar živina stupca",
                                few = "{0} milimetra živina stupca",
                                other = "{0} milimetara živina stupca",
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
                                one = "{0} funta po kvadratnom inču",
                                few = "{0} funte po kvadratnom inču",
                                other = "{0} funti po kvadratnom inču",
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
                                one = "{0} inč žive",
                                few = "{0} inča žive",
                                other = "{0} inča žive",
                            )
                        ),
                    )
                ),
            )
        override val pressureBar
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bar",
                                few = "{0} bara",
                                other = "{0} bara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} bar",
                                few = "{0} bara",
                                other = "{0} bara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} bara",
                                few = "{0} bara",
                                other = "{0} bara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} barom",
                                few = "{0} bara",
                                other = "{0} bara",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bar",
                                few = "{0} bara",
                                other = "{0} bara",
                            )
                        ),
                    )
                ),
            )
        override val pressureMillibar
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milibar",
                                few = "{0} milibara",
                                other = "{0} milibara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milibar",
                                few = "{0} milibara",
                                other = "{0} milibara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} milibara",
                                few = "{0} milibara",
                                other = "{0} milibara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milibarom",
                                few = "{0} milibara",
                                other = "{0} milibara",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mb",
                                few = "{0} mb",
                                other = "{0} mb",
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
                                one = "{0} atmosfera",
                                few = "{0} atmosfere",
                                other = "{0} atmosfera",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} atmosferu",
                                few = "{0} atmosfere",
                                other = "{0} atmosfera",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} atmosfere",
                                few = "{0} atmosfere",
                                other = "{0} atmosfera",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} atmosferom",
                                few = "{0} atmosfere",
                                other = "{0} atmosfera",
                            )
                        ),
                    )
                ),
            )
        override val pressurePascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} paskal",
                                few = "{0} paskala",
                                other = "{0} paskala",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} paskal",
                                few = "{0} paskala",
                                other = "{0} paskala",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} paskala",
                                few = "{0} paskala",
                                other = "{0} paskala",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} paskalom",
                                few = "{0} paskala",
                                other = "{0} paskala",
                            )
                        ),
                    )
                ),
            )
        override val pressureHectopascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hektopaskal",
                                few = "{0} hektopaskala",
                                other = "{0} hektopaskala",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} hektopaskal",
                                few = "{0} hektopaskala",
                                other = "{0} hektopaskala",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} hektopaskala",
                                few = "{0} hektopaskala",
                                other = "{0} hektopaskala",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} hektopaskalom",
                                few = "{0} hektopaskala",
                                other = "{0} hektopaskala",
                            )
                        ),
                    )
                ),
            )
        override val pressureKilopascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilopaskal",
                                few = "{0} kilopaskala",
                                other = "{0} kilopaskala",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilopaskal",
                                few = "{0} kilopaskala",
                                other = "{0} kilopaskala",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilopaskala",
                                few = "{0} kilopaskala",
                                other = "{0} kilopaskala",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilopaskalom",
                                few = "{0} kilopaskala",
                                other = "{0} kilopaskala",
                            )
                        ),
                    )
                ),
            )
        override val pressureMegapascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megapaskal",
                                few = "{0} megapaskala",
                                other = "{0} megapaskala",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megapaskal",
                                few = "{0} megapaskala",
                                other = "{0} megapaskala",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} megapaskala",
                                few = "{0} megapaskala",
                                other = "{0} megapaskala",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} megapaskalom",
                                few = "{0} megapaskala",
                                other = "{0} megapaskala",
                            )
                        ),
                    )
                ),
            )
        override val pressureGasolineEnergyDensity: MultiLengthGenderedPattern
            get() = TODO("GasolineEnergyDensity formatter is not implemented yet for hr")
        override val pressureKilogramPerMeterSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramPerMeterSquareSecond formatter is not implemented yet for hr")
        override val speedKilometerPerHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilometar na sat",
                                few = "{0} kilometra na sat",
                                other = "{0} kilometara na sat",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilometar na sat",
                                few = "{0} kilometra na sat",
                                other = "{0} kilometara na sat",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilometra na sat",
                                few = "{0} kilometra na sat",
                                other = "{0} kilometara na sat",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilometrom na sat",
                                few = "{0} kilometra na sat",
                                other = "{0} kilometara na sat",
                            )
                        ),
                    )
                ),
            )
        override val speedMeterPerSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} metar u sekundi",
                                few = "{0} metra u sekundi",
                                other = "{0} metara u sekundi",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} metar u sekundi",
                                few = "{0} metra u sekundi",
                                other = "{0} metara u sekundi",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} metra u sekundi",
                                few = "{0} metra u sekundi",
                                other = "{0} metara u sekundi",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} metrom u sekundi",
                                few = "{0} metra u sekundi",
                                other = "{0} metara u sekundi",
                            )
                        ),
                    )
                ),
            )
        override val speedMilePerHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milja na sat",
                                few = "{0} milje na sat",
                                other = "{0} milja na sat",
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
                                one = "{0} čvor",
                                few = "{0} čvora",
                                other = "{0} čvorova",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} čv",
                                few = "{0} čv",
                                other = "{0} čv",
                            )
                        ),
                    )
                ),
            )
        override val speedBeaufort: MultiLengthGenderedPattern
            get() = TODO("Beaufort formatter is not implemented yet for hr")
        override val temperatureGeneric: MultiLengthGenderedPattern
            get() = TODO("Generic formatter is not implemented yet for hr")
        override val temperatureCelsius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Celzijev stupanj",
                                few = "{0} Celzijeva stupnja",
                                other = "{0} Celzijevih stupnjeva",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Celzijev stupanj",
                                few = "{0} Celzijeva stupnja",
                                other = "{0} Celzijevih stupnjeva",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} Celzijevog stupnja",
                                few = "{0} Celzijeva stupnja",
                                other = "{0} Celzijevih stupnjeva",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} Celzijevim stupnjem",
                                few = "{0} Celzijeva stupnja",
                                other = "{0} Celzijevih stupnjeva",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} °C",
                                few = "{0} °C",
                                other = "{0} °C",
                            )
                        ),
                    )
                ),
            )
        override val temperatureFahrenheit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Fahrenheitov stupanj",
                                few = "{0} Fahrenheitova stupnja",
                                other = "{0} Fahrenheitovih stupnjeva",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} °F",
                                few = "{0} °F",
                                other = "{0} °F",
                            )
                        ),
                    )
                ),
            )
        override val temperatureKelvin
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kelvin",
                                few = "{0} kelvina",
                                other = "{0} kelvina",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kelvin",
                                few = "{0} kelvina",
                                other = "{0} kelvina",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kelvina",
                                few = "{0} kelvina",
                                other = "{0} kelvina",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kelvinom",
                                few = "{0} kelvina",
                                other = "{0} kelvina",
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
                                one = "{0} funta sile po stopi",
                                few = "{0} funte sile po stopi",
                                other = "{0} funti sile po stopi",
                            )
                        ),
                    )
                ),
            )
        override val torqueNewtonMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} njutnmetar",
                                few = "{0} njutnmetra",
                                other = "{0} njutnmetara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} njutnmetar",
                                few = "{0} njutnmetra",
                                other = "{0} njutnmetara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} njutnmetra",
                                few = "{0} njutnmetra",
                                other = "{0} njutnmetara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} njutnmetrom",
                                few = "{0} njutnmetra",
                                other = "{0} njutnmetara",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Nm",
                                few = "{0} Nm",
                                other = "{0} Nm",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kubni kilometar",
                                few = "{0} kubna kilometra",
                                other = "{0} kubnih kilometara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kubni kilometar",
                                few = "{0} kubna kilometra",
                                other = "{0} kubnih kilometara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kubnog kilometra",
                                few = "{0} kubna kilometra",
                                other = "{0} kubnih kilometara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kubnim kilometrom",
                                few = "{0} kubna kilometra",
                                other = "{0} kubnih kilometara",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kubni metar",
                                few = "{0} kubna metra",
                                other = "{0} kubnih metara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kubni metar",
                                few = "{0} kubna metra",
                                other = "{0} kubnih metara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kubnog metra",
                                few = "{0} kubna metra",
                                other = "{0} kubnih metara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kubnim metrom",
                                few = "{0} kubna metra",
                                other = "{0} kubnih metara",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kubni centimetar",
                                few = "{0} kubna centimetra",
                                other = "{0} kubnih centimetara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kubni centimetar",
                                few = "{0} kubna centimetra",
                                other = "{0} kubnih centimetara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kubnog centimetra",
                                few = "{0} kubna centimetra",
                                other = "{0} kubnih centimetara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kubnim centimetrom",
                                few = "{0} kubna centimetra",
                                other = "{0} kubnih centimetara",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicMile
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kubna milja",
                                few = "{0} kubne milje",
                                other = "{0} kubnih milja",
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
                                one = "{0} kubni jard",
                                few = "{0} kubna jarda",
                                other = "{0} kubnih jardi",
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
                                one = "{0} kubna stopa",
                                few = "{0} kubne stope",
                                other = "{0} kubnih stopa",
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
                                one = "{0} kubni inč",
                                few = "{0} kubna inča",
                                other = "{0} kubnih inča",
                            )
                        ),
                    )
                ),
            )
        override val volumeMegaliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megalitar",
                                few = "{0} megalitra",
                                other = "{0} megalitara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megalitar",
                                few = "{0} megalitra",
                                other = "{0} megalitara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} megalitra",
                                few = "{0} megalitra",
                                other = "{0} megalitara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} megalitrom",
                                few = "{0} megalitra",
                                other = "{0} megalitara",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Ml",
                                few = "{0} Ml",
                                other = "{0} Ml",
                            )
                        ),
                    )
                ),
            )
        override val volumeHectoliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hektolitar",
                                few = "{0} hektolitra",
                                other = "{0} hektolitara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} hektolitar",
                                few = "{0} hektolitra",
                                other = "{0} hektolitara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} hektolitra",
                                few = "{0} hektolitra",
                                other = "{0} hektolitara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} hektolitrom",
                                few = "{0} hektolitra",
                                other = "{0} hektolitara",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hl",
                                few = "{0} hl",
                                other = "{0} hl",
                            )
                        ),
                    )
                ),
            )
        override val volumeLiter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} litra",
                                few = "{0} litre",
                                other = "{0} litara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} litru",
                                few = "{0} litre",
                                other = "{0} litara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} litre",
                                few = "{0} litre",
                                other = "{0} litara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} litrom",
                                few = "{0} litre",
                                other = "{0} litara",
                            )
                        ),
                    )
                ),
            )
        override val volumeDeciliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} decilitar",
                                few = "{0} decilitra",
                                other = "{0} decilitara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} decilitar",
                                few = "{0} decilitra",
                                other = "{0} decilitara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} decilitra",
                                few = "{0} decilitra",
                                other = "{0} decilitara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} decilitrom",
                                few = "{0} decilitra",
                                other = "{0} decilitara",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dl",
                                few = "{0} dl",
                                other = "{0} dl",
                            )
                        ),
                    )
                ),
            )
        override val volumeCentiliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} centilitar",
                                few = "{0} centilitra",
                                other = "{0} centilitara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} centilitar",
                                few = "{0} centilitra",
                                other = "{0} centilitara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} centilitra",
                                few = "{0} centilitra",
                                other = "{0} centilitara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} centilitrom",
                                few = "{0} centilitra",
                                other = "{0} centilitara",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cl",
                                few = "{0} cl",
                                other = "{0} cl",
                            )
                        ),
                    )
                ),
            )
        override val volumeMilliliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mililitar",
                                few = "{0} mililitra",
                                other = "{0} mililitara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mililitar",
                                few = "{0} mililitra",
                                other = "{0} mililitara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mililitra",
                                few = "{0} mililitra",
                                other = "{0} mililitara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} mililitrom",
                                few = "{0} mililitra",
                                other = "{0} mililitara",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ml",
                                few = "{0} ml",
                                other = "{0} ml",
                            )
                        ),
                    )
                ),
            )
        override val volumePintMetric
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} metrička pinta",
                                few = "{0} metričke pinte",
                                other = "{0} metričkih pinti",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} metričku pintu",
                                few = "{0} metričke pinte",
                                other = "{0} metričkih pinti",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} metričke pinte",
                                few = "{0} metričke pinte",
                                other = "{0} metričkih pinti",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} metričkom pintom",
                                few = "{0} metričke pinte",
                                other = "{0} metričkih pinti",
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
                                one = "{0} metrička šalica",
                                few = "{0} metričke šalice",
                                other = "{0} metričkih šalica",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} metričku šalicu",
                                few = "{0} metričke šalice",
                                other = "{0} metričkih šalica",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} metričke šalice",
                                few = "{0} metričke šalice",
                                other = "{0} metričkih šalica",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} metričkom šalicom",
                                few = "{0} metričke šalice",
                                other = "{0} metričkih šalica",
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
                                one = "{0} aker-stopa",
                                few = "{0} aker-stope",
                                other = "{0} aker-stopi",
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
                                one = "{0} bušel",
                                few = "{0} bušela",
                                other = "{0} bušela",
                            )
                        ),
                    )
                ),
            )
        override val volumeGallon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} galon",
                                few = "{0} galona",
                                other = "{0} galona",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gal",
                                few = "{0} gal",
                                other = "{0} gal",
                            )
                        ),
                    )
                ),
            )
        override val volumeGallonImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} imp. galon",
                                few = "{0} imp. galona",
                                other = "{0} imp. galona",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} imp. gal.",
                                few = "{0} imp. gal.",
                                other = "{0} imp. gal.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} i. gal.",
                                few = "{0} i. gal.",
                                other = "{0} i. gal.",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuart
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} četvrtina",
                                few = "{0} četvrtine",
                                other = "{0} četvrtina",
                            )
                        ),
                    )
                ),
            )
        override val volumePint
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pinta",
                                few = "{0} pinte",
                                other = "{0} pinti",
                            )
                        ),
                    )
                ),
            )
        override val volumeCup
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} šalica",
                                few = "{0} šalice",
                                other = "{0} šalica",
                            )
                        ),
                    )
                ),
            )
        override val volumeFluidOunce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} tekuća unca",
                                few = "{0} tekuće unce",
                                other = "{0} tekućih unci",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fl oz",
                                few = "{0} fl oz",
                                other = "{0} fl oz",
                            )
                        ),
                    )
                ),
            )
        override val volumeFluidOunceImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} imp. tekuća unca",
                                few = "{0} imp. tekuće unce",
                                other = "{0} imp. tekućih unci",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} imp. fl oz",
                                few = "{0} imp. fl oz",
                                other = "{0} imp. fl oz",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} i. fl oz",
                                few = "{0} i. fl oz",
                                other = "{0} i. fl oz",
                            )
                        ),
                    )
                ),
            )
        override val volumeTablespoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} žlica",
                                few = "{0} žlice",
                                other = "{0} žlica",
                            )
                        ),
                    )
                ),
            )
        override val volumeTeaspoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} žličica",
                                few = "{0} žličice",
                                other = "{0} žličica",
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
                                one = "{0} barel",
                                few = "{0} barela",
                                other = "{0} barela",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} desertna žličica",
                                few = "{0} desertne žličice",
                                other = "{0} desertnih žličica",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} des. žličica",
                                few = "{0} des. žličice",
                                other = "{0} des. žličica",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} žličica",
                                few = "{0} žličice",
                                other = "{0} žličica",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoonImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} imperijalna desertna žličica",
                                few = "{0} imperijalne desertne žličice",
                                other = "{0} imperijalnih desertnih žličica",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} imp. žličica",
                                few = "{0} imp. žličice",
                                other = "{0} imp. žličica",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} i. žličica",
                                few = "{0} i. žličice",
                                other = "{0} i. žličica",
                            )
                        ),
                    )
                ),
            )
        override val volumeDrop
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kap",
                                few = "{0} kapi",
                                other = "{0} kapi",
                            )
                        ),
                    )
                ),
            )
        override val volumeDram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dram tekućine",
                                few = "{0} drama tekućine",
                                other = "{0} drama tekućine",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fl dr",
                                few = "{0} fl dr",
                                other = "{0} fl dr",
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
                                one = "{0} jiggera",
                                few = "{0} jiggera",
                                other = "{0} jiggera",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} jigger",
                                few = "{0} jiggera",
                                other = "{0} jiggera",
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
                                one = "{0} prstohvat",
                                few = "{0} prstohvata",
                                other = "{0} prstohvata",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuartImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} imperijalna četvrtina",
                                few = "{0} imperijalne četvrtine",
                                other = "{0} imperijalne četvrtine",
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
            minusSignSymbol = "−",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
