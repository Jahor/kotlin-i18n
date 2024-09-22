package info.leonenko.i18n.languages

import info.leonenko.i18n.*
import info.leonenko.i18n.rbnf.RBNFNl

val Language.Companion.nl
    get() = languageNl
private val languageNl = LanguageNl()

open class LanguageNl internal constructor() : Language {
    override val code: String = "nl"
    override val language: String = "nl"
    override val script: String? = null
    override val territory: String? = null
    override val rbnf: RBNFNl
        get() = RBNFNl(this)
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
            long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} en {1}", two = "{0} en {1}", ellipsis = ellipsis.wordFormatter),
            narrow = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, {1}", two = "{0}, {1}", ellipsis = ellipsis.wordFormatter),
            short = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} & {1}", two = "{0} & {1}", ellipsis = ellipsis.wordFormatter),
        )
        override val orFormatter: ListFormatter =
            MultiLengthPatternedListFormatter(long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} of {1}", two = "{0} of {1}", ellipsis = ellipsis.wordFormatter))
        override val unitFormatter: ListFormatter = MultiLengthPatternedListFormatter(
            long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} en {1}", two = "{0} en {1}", ellipsis = ellipsis.wordFormatter),
            short = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, {1}", two = "{0}, {1}", ellipsis = ellipsis.wordFormatter),
        )
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    open class MeasurementUnitLanguage internal constructor() : info.leonenko.i18n.units.MeasurementUnitLanguage {
        override val accelerationGForce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} G-kracht",
                                other = "{0} G-krachten",
                            )
                        ),
                    )
                ),
            )
        override val accelerationMeterPerSquareSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} meter per seconde kwadraat",
                                other = "{0} meter per seconde kwadraat",
                            )
                        ),
                    )
                ),
            )
        override val angleRevolution
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} toer",
                                other = "{0} toeren",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} tr",
                                other = "{0} tr",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} t",
                                other = "{0} t",
                            )
                        ),
                    )
                ),
            )
        override val angleRadian
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} radiaal",
                                other = "{0} radialen",
                            )
                        ),
                    )
                ),
            )
        override val angleDegree
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} booggraad",
                                other = "{0} booggraden",
                            )
                        ),
                    )
                ),
            )
        override val angleArcMinute
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} boogminuut",
                                other = "{0} boogminuten",
                            )
                        ),
                    )
                ),
            )
        override val angleArcSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} boogseconde",
                                other = "{0} boogseconden",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} vierkante kilometer",
                                other = "{0} vierkante kilometer",
                            )
                        ),
                    )
                ),
            )
        override val areaHectare
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hectare",
                                other = "{0} hectare",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} vierkante meter",
                                other = "{0} vierkante meter",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} vierkante centimeter",
                                other = "{0} vierkante centimeter",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareMile
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} vierkante mijl",
                                other = "{0} vierkante mijl",
                            )
                        ),
                    )
                ),
            )
        override val areaAcre
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} acre",
                                other = "{0} acres",
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
                                one = "{0} vierkante yard",
                                other = "{0} vierkante yard",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} vierkante voet",
                                other = "{0} vierkante voet",
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
                                one = "{0} vierkante inch",
                                other = "{0} vierkante inch",
                            )
                        ),
                    )
                ),
            )
        override val areaDunam: MultiLengthGenderedPattern
            get() = TODO("Dunam formatter is not implemented yet for nl")
        override val concentrationKarat
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} karaat",
                                other = "{0} karaat",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} K",
                                other = "{0} K",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermillion: MultiLengthGenderedPattern
            get() = TODO("Permillion formatter is not implemented yet for nl")
        override val concentrationPercent
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} procent",
                                other = "{0} procent",
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
                                one = "{0} promille",
                                other = "{0} promille",
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
                                one = "{0} basispunt",
                                other = "{0} basispunten",
                            )
                        ),
                    )
                ),
            )
        override val concentrationAPortion: MultiLengthGenderedPattern
            get() = TODO("APortion formatter is not implemented yet for nl")
        override val concentrationMilligramOfglucosePerDeciliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milligram per deciliter",
                                other = "{0} milligram per deciliter",
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
            )
        override val concentrationMillimolePerLiter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} millimol per liter",
                                other = "{0} millimol per liter",
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
            )
        override val concentrationItemPerCubicMeter: MultiLengthGenderedPattern
            get() = TODO("ItemPerCubicMeter formatter is not implemented yet for nl")
        override val concentrationItem
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} onderdeel",
                                other = "{0} onderdelen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} onderdeel",
                                other = "{0} ond.",
                            )
                        ),
                    )
                ),
            )
        override val concentrationMole: MultiLengthGenderedPattern
            get() = TODO("Mole formatter is not implemented yet for nl")
        override val consumptionLiterPerKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} liter per kilometer",
                                other = "{0} liter per kilometer",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} l/km",
                                other = "{0} l/km",
                            )
                        ),
                    )
                ),
            )
        override val consumptionLiterPer100Kilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} liter per 100 kilometer",
                                other = "{0} liter per 100 kilometer",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} l/100km",
                                other = "{0} l/100km",
                            )
                        ),
                    )
                ),
            )
        override val consumptionMilePerGallon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mijl per gallon",
                                other = "{0} mijl per gallon",
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
            )
        override val consumptionMilePerGallonImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mijl per imp. gallon",
                                other = "{0} mijl per imp. gallon",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mpg imp.",
                                other = "{0} mpg imp.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} m/gUK",
                                other = "{0} m/gUK",
                            )
                        ),
                    )
                ),
            )
        override val digitalPetabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} petabyte",
                                other = "{0} petabyte",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} terabyte",
                                other = "{0} terabyte",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} terabit",
                                other = "{0} terabits",
                            )
                        ),
                    )
                ),
            )
        override val digitalGigabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigabyte",
                                other = "{0} gigabyte",
                            )
                        ),
                    )
                ),
            )
        override val digitalGigabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigabit",
                                other = "{0} gigabits",
                            )
                        ),
                    )
                ),
            )
        override val digitalMegabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megabyte",
                                other = "{0} megabyte",
                            )
                        ),
                    )
                ),
            )
        override val digitalMegabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megabit",
                                other = "{0} megabits",
                            )
                        ),
                    )
                ),
            )
        override val digitalKilobyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilobyte",
                                other = "{0} kilobyte",
                            )
                        ),
                    )
                ),
            )
        override val digitalKilobit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilobit",
                                other = "{0} kilobits",
                            )
                        ),
                    )
                ),
            )
        override val digitalByte: MultiLengthGenderedPattern
            get() = TODO("Byte formatter is not implemented yet for nl")
        override val digitalBit
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bit",
                                other = "{0} bits",
                            )
                        ),
                    )
                ),
            )
        override val durationCentury
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} eeuw",
                                other = "{0} eeuwen",
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
                                one = "{0} decennium",
                                other = "{0} decennia",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dec.",
                                other = "{0} dec.",
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
                                one = "{0} jaar",
                                other = "{0} jaar",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} jr",
                                other = "{0} jr",
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
                                one = "{0} kwartaal",
                                other = "{0} kwartalen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kwart.",
                                other = "{0} kwart.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kw.",
                                other = "{0} kw.",
                            )
                        ),
                    )
                ),
            )
        override val durationMonth
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} maand",
                                other = "{0} maanden",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mnd",
                                other = "{0} mnd",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} m",
                                other = "{0} m",
                            )
                        ),
                    )
                ),
            )
        override val durationWeek
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} week",
                                other = "{0} weken",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} wk",
                                other = "{0} wkn",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} w",
                                other = "{0} w",
                            )
                        ),
                    )
                ),
            )
        override val durationDay
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dag",
                                other = "{0} dagen",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
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
        override val durationHour
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} uur",
                                other = "{0} uur",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} u",
                                other = "{0} u",
                            )
                        ),
                    )
                ),
            )
        override val durationMinute
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} minuut",
                                other = "{0} minuten",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} m",
                                other = "{0} m",
                            )
                        ),
                    )
                ),
            )
        override val durationSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} seconde",
                                other = "{0} seconden",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} sec",
                                other = "{0} sec",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} s",
                                other = "{0} s",
                            )
                        ),
                    )
                ),
            )
        override val durationMillisecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milliseconde",
                                other = "{0} milliseconden",
                            )
                        ),
                    )
                ),
            )
        override val durationMicrosecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} microseconde",
                                other = "{0} microseconden",
                            )
                        ),
                    )
                ),
            )
        override val durationNanosecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} nanoseconde",
                                other = "{0} nanoseconden",
                            )
                        ),
                    )
                ),
            )
        override val electricAmpere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ampère",
                                other = "{0} ampère",
                            )
                        ),
                    )
                ),
            )
        override val electricMilliampere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milliampère",
                                other = "{0} milliampère",
                            )
                        ),
                    )
                ),
            )
        override val electricOhm
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ohm",
                                other = "{0} ohm",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondSquareAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondSquareAmpere formatter is not implemented yet for nl")
        override val electricVolt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} volt",
                                other = "{0} volt",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondAmpere formatter is not implemented yet for nl")
        override val energyKilocalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilocalorie",
                                other = "{0} kilocalorieën",
                            )
                        ),
                    )
                ),
            )
        override val energyCalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} calorie",
                                other = "{0} calorieën",
                            )
                        ),
                    )
                ),
            )
        override val energyFoodcalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilocalorie",
                                other = "{0} kilocalorieën",
                            )
                        ),
                    )
                ),
            )
        override val energyKilojoule
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilojoule",
                                other = "{0} kilojoules",
                            )
                        ),
                    )
                ),
            )
        override val energyJoule
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} joule",
                                other = "{0} joules",
                            )
                        ),
                    )
                ),
            )
        override val energyKilowattHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilowattuur",
                                other = "{0} kilowattuur",
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
                                other = "{0} elektronvolt",
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
                                other = "{0} British thermal unit",
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
                                one = "{0} US therm",
                                other = "{0} US therms",
                            )
                        ),
                    )
                ),
            )
        override val energyKilogramSquareMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerSquareSecond formatter is not implemented yet for nl")
        override val forcePoundForce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pound of force",
                                other = "{0} pound of force",
                            )
                        ),
                    )
                ),
            )
        override val forceNewton
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} newton",
                                other = "{0} newton",
                            )
                        ),
                    )
                ),
            )
        override val forceKilowattHourPer100Kilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ki­lo­wattuur per 100 kilometer",
                                other = "{0} ki­lo­wattuur per 100 kilometer",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}kWh/100km",
                                other = "{0}kWh/100km",
                            )
                        ),
                    )
                ),
            )
        override val forceKilogramMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramMeterPerSquareSecond formatter is not implemented yet for nl")
        override val frequencyGigahertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigahertz",
                                other = "{0} gigahertz",
                            )
                        ),
                    )
                ),
            )
        override val frequencyMegahertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megahertz",
                                other = "{0} megahertz",
                            )
                        ),
                    )
                ),
            )
        override val frequencyKilohertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilohertz",
                                other = "{0} kilohertz",
                            )
                        ),
                    )
                ),
            )
        override val frequencyHertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hertz",
                                other = "{0} hertz",
                            )
                        ),
                    )
                ),
            )
        override val frequencyRevolutionPerSecond: MultiLengthGenderedPattern
            get() = TODO("RevolutionPerSecond formatter is not implemented yet for nl")
        override val graphicsEm
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} em",
                                other = "{0} ems",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixel
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pixel",
                                other = "{0} pixels",
                            )
                        ),
                    )
                ),
            )
        override val graphicsMegapixel
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megapixel",
                                other = "{0} megapixels",
                            )
                        ),
                    )
                ),
            )
        override val graphicsDot
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dot",
                                other = "{0} dots",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dot",
                                other = "{0} dot",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixelPerCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pixel per centimeter",
                                other = "{0} pixels per centimeter",
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
                                one = "{0} pixel per inch",
                                other = "{0} pixels per inch",
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
                                one = "{0} dot per centimeter",
                                other = "{0} dots per centimeter",
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
                                one = "{0} dot per inch",
                                other = "{0} dots per inch",
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
            get() = TODO("PixelPerMeter formatter is not implemented yet for nl")
        override val lengthEarthRadius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} aardstraal",
                                other = "{0} aardstralen",
                            )
                        ),
                    )
                ),
            )
        override val lengthKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilometer",
                                other = "{0} kilometer",
                            )
                        ),
                    )
                ),
            )
        override val lengthMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} meter",
                                other = "{0} meter",
                            )
                        ),
                    )
                ),
            )
        override val lengthDecimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} decimeter",
                                other = "{0} decimeter",
                            )
                        ),
                    )
                ),
            )
        override val lengthCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} centimeter",
                                other = "{0} centimeter",
                            )
                        ),
                    )
                ),
            )
        override val lengthMillimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} millimeter",
                                other = "{0} millimeter",
                            )
                        ),
                    )
                ),
            )
        override val lengthMicrometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} micrometer",
                                other = "{0} micrometer",
                            )
                        ),
                    )
                ),
            )
        override val lengthNanometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} nanometer",
                                other = "{0} nanometer",
                            )
                        ),
                    )
                ),
            )
        override val lengthPicometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} picometer",
                                other = "{0} picometer",
                            )
                        ),
                    )
                ),
            )
        override val lengthMile
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mijl",
                                other = "{0} mijl",
                            )
                        ),
                    )
                ),
            )
        override val lengthYard
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} yard",
                                other = "{0} yards",
                            )
                        ),
                    )
                ),
            )
        override val lengthFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} voet",
                                other = "{0} voet",
                            )
                        ),
                    )
                ),
            )
        override val lengthInch
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} inch",
                                other = "{0} inches",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}″",
                                other = "{0}″",
                            )
                        ),
                    )
                ),
            )
        override val lengthParsec
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} parsec",
                                other = "{0} parsecs",
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
                                one = "{0} lichtjaar",
                                other = "{0} lichtjaar",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} lj",
                                other = "{0} lj",
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
                                one = "{0} astronomische eenheid",
                                other = "{0} astronomische eenheden",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} AE",
                                other = "{0} AE",
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
                                other = "{0} furlong",
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
                                one = "{0} vadem",
                                other = "{0} vadems",
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
                                one = "{0} zeemijl",
                                other = "{0} zeemijlen",
                            )
                        ),
                    )
                ),
            )
        override val lengthMileScandinavian
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Scandinavische mijl",
                                other = "{0} Scandinavische mijl",
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
                                one = "{0} punt",
                                other = "{0} punten",
                            )
                        ),
                    )
                ),
            )
        override val lengthSolarRadius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} solar radius",
                                other = "{0} solar radii",
                            )
                        ),
                    )
                ),
            )
        override val lightLux
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} lux",
                                other = "{0} lux",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaPerSquareMeter formatter is not implemented yet for nl")
        override val lightCandela
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} candela",
                                other = "{0} candela",
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
                                one = "{0} lumen",
                                other = "{0} lumen",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaSquareMeterPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaSquareMeterPerSquareMeter formatter is not implemented yet for nl")
        override val lightSolarLuminosity
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} solar luminosity",
                                other = "{0} solar luminosity",
                            )
                        ),
                    )
                ),
            )
        override val massTonne
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} metrische ton",
                                other = "{0} metrische ton",
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
                                one = "{0} kilogram",
                                other = "{0} kilogram",
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
                                one = "{0} gram",
                                other = "{0} gram",
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
                                one = "{0} milligram",
                                other = "{0} milligram",
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
                                one = "{0} microgram",
                                other = "{0} microgram",
                            )
                        ),
                    )
                ),
            )
        override val massTon
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ton",
                                other = "{0} ton",
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
                                one = "{0} stone",
                                other = "{0} stone",
                            )
                        ),
                    )
                ),
            )
        override val massPound
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pound",
                                other = "{0} pound",
                            )
                        ),
                    )
                ),
            )
        override val massOunce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ounce",
                                other = "{0} ounce",
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
                                one = "{0} troy ounce",
                                other = "{0} troy ounce",
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
                                one = "{0} karaat",
                                other = "{0} karaat",
                            )
                        ),
                    )
                ),
            )
        override val massDalton
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dalton",
                                other = "{0} dalton",
                            )
                        ),
                    )
                ),
            )
        override val massEarthMass
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} aardmassa",
                                other = "{0} aardmassa",
                            )
                        ),
                    )
                ),
            )
        override val massSolarMass
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} zonnemassa",
                                other = "{0} zonnemassa",
                            )
                        ),
                    )
                ),
            )
        override val massGrain
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} grein",
                                other = "{0} grein",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
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
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigawatt",
                                other = "{0} gigawatt",
                            )
                        ),
                    )
                ),
            )
        override val powerMegawatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megawatt",
                                other = "{0} megawatt",
                            )
                        ),
                    )
                ),
            )
        override val powerKilowatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilowatt",
                                other = "{0} kilowatt",
                            )
                        ),
                    )
                ),
            )
        override val powerWatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} watt",
                                other = "{0} watt",
                            )
                        ),
                    )
                ),
            )
        override val powerMilliwatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milliwatt",
                                other = "{0} milliwatt",
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
                                one = "{0} paardenkracht",
                                other = "{0} paardenkrachten",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pk",
                                other = "{0} pk",
                            )
                        ),
                    )
                ),
            )
        override val powerKilogramSquareMeterPerCubicSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecond formatter is not implemented yet for nl")
        override val pressureMillimeterOfhg
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} millimeter-kwikdruk",
                                other = "{0} millimeter-kwikdruk",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mmHg",
                                other = "{0} mmHg",
                            )
                        ),
                    )
                ),
            )
        override val pressurePoundForcePerSquareInch: MultiLengthGenderedPattern
            get() = TODO("PoundForcePerSquareInch formatter is not implemented yet for nl")
        override val pressureInchOfhg
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} inch-kwikdruk",
                                other = "{0} inch-kwikdruk",
                            )
                        ),
                    )
                ),
            )
        override val pressureBar: MultiLengthGenderedPattern
            get() = TODO("Bar formatter is not implemented yet for nl")
        override val pressureMillibar
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} millibar",
                                other = "{0} millibar",
                            )
                        ),
                    )
                ),
            )
        override val pressureAtmosphere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} atmosfeer",
                                other = "{0} atmosfeer",
                            )
                        ),
                    )
                ),
            )
        override val pressurePascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pascal",
                                other = "{0} pascal",
                            )
                        ),
                    )
                ),
            )
        override val pressureHectopascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hectopascal",
                                other = "{0} hectopascal",
                            )
                        ),
                    )
                ),
            )
        override val pressureKilopascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilopascal",
                                other = "{0} kilopascal",
                            )
                        ),
                    )
                ),
            )
        override val pressureMegapascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megapascal",
                                other = "{0} megapascal",
                            )
                        ),
                    )
                ),
            )
        override val pressureGasolineEnergyDensity: MultiLengthGenderedPattern
            get() = TODO("GasolineEnergyDensity formatter is not implemented yet for nl")
        override val pressureKilogramPerMeterSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramPerMeterSquareSecond formatter is not implemented yet for nl")
        override val speedKilometerPerHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilometer per uur",
                                other = "{0} kilometer per uur",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} km/u",
                                other = "{0} km/u",
                            )
                        ),
                    )
                ),
            )
        override val speedMeterPerSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} meter per seconde",
                                other = "{0} meter per seconde",
                            )
                        ),
                    )
                ),
            )
        override val speedMilePerHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mijl per uur",
                                other = "{0} mijl per uur",
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
                                one = "{0} knoop",
                                other = "{0} knopen",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kt",
                                other = "{0} kt",
                            )
                        ),
                    )
                ),
            )
        override val speedBeaufort
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}",
                                other = "{0}",
                            )
                        ),
                    )
                ),
            )
        override val temperatureGeneric: MultiLengthGenderedPattern
            get() = TODO("Generic formatter is not implemented yet for nl")
        override val temperatureCelsius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} graad Celsius",
                                other = "{0} graden Celsius",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
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
        override val temperatureFahrenheit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} graad Fahrenheit",
                                other = "{0} graden Fahrenheit",
                            )
                        ),
                    )
                ),
            )
        override val temperatureKelvin
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kelvin",
                                other = "{0} kelvin",
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
                                one = "{0} pound-force-foot",
                                other = "{0} pound-force-feet",
                            )
                        ),
                    )
                ),
            )
        override val torqueNewtonMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} newtonmeter",
                                other = "{0} newtonmeter",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Nm",
                                other = "{0} Nm",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kubieke kilometer",
                                other = "{0} kubieke kilometer",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kubieke meter",
                                other = "{0} kubieke meter",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kubieke centimeter",
                                other = "{0} kubieke centimeter",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicMile
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kubieke mijl",
                                other = "{0} kubieke mijl",
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
                                one = "{0} kubieke yard",
                                other = "{0} kubieke yard",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kubieke voet",
                                other = "{0} kubieke voet",
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
                                one = "{0} kubieke inch",
                                other = "{0} kubieke inch",
                            )
                        ),
                    )
                ),
            )
        override val volumeMegaliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megaliter",
                                other = "{0} megaliter",
                            )
                        ),
                    )
                ),
            )
        override val volumeHectoliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hectoliter",
                                other = "{0} hectoliter",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hl",
                                other = "{0} hl",
                            )
                        ),
                    )
                ),
            )
        override val volumeLiter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} liter",
                                other = "{0} liter",
                            )
                        ),
                    )
                ),
            )
        override val volumeDeciliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} deciliter",
                                other = "{0} deciliter",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dl",
                                other = "{0} dl",
                            )
                        ),
                    )
                ),
            )
        override val volumeCentiliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} centiliter",
                                other = "{0} centiliter",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cl",
                                other = "{0} cl",
                            )
                        ),
                    )
                ),
            )
        override val volumeMilliliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milliliter",
                                other = "{0} milliliter",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ml",
                                other = "{0} ml",
                            )
                        ),
                    )
                ),
            )
        override val volumePintMetric
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} metrische pint",
                                other = "{0} metrische pint",
                            )
                        ),
                    )
                ),
            )
        override val volumeCupMetric
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} metrische cup",
                                other = "{0} metrische cup",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}mc",
                                other = "{0}mc",
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
                                one = "{0} acre-foot",
                                other = "{0} acre-feet",
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
                                one = "{0} bushel",
                                other = "{0} bushels",
                            )
                        ),
                    )
                ),
            )
        override val volumeGallon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gallon",
                                other = "{0} gallon",
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
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} imp. gallon",
                                other = "{0} imp. gallon",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} imp. gal",
                                other = "{0} imp. gal",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}galIm",
                                other = "{0}galIm",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuart
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quart",
                                other = "{0} quart",
                            )
                        ),
                    )
                ),
            )
        override val volumePint
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pint",
                                other = "{0} pint",
                            )
                        ),
                    )
                ),
            )
        override val volumeCup
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cup",
                                other = "{0} cup",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} c",
                                other = "{0} c",
                            )
                        ),
                    )
                ),
            )
        override val volumeFluidOunce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fluid ounce",
                                other = "{0} fluid ounce",
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
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Imp. fluid ounce",
                                other = "{0} Imp. fluid ounce",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fl ozIm",
                                other = "{0} fl ozIm",
                            )
                        ),
                    )
                ),
            )
        override val volumeTablespoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} eetlepel",
                                other = "{0} eetlepels",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} el",
                                other = "{0} el",
                            )
                        ),
                    )
                ),
            )
        override val volumeTeaspoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} theelepel",
                                other = "{0} theelepels",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} tl",
                                other = "{0} tl",
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
                                one = "{0} barrel",
                                other = "{0} barrels",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dessertlepel",
                                other = "{0} dessertlepels",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} des l",
                                other = "{0} des l",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoonImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} imp. dessertlepel",
                                other = "{0} imp. dessertlepels",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} imp. des l",
                                other = "{0} imp. des lpls",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} imp. d l",
                                other = "{0} imp. d l",
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
                                one = "{0} druppel",
                                other = "{0} druppels",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dr",
                                other = "{0} drs",
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
                                one = "{0} drachme",
                                other = "{0} drachme",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fl dr",
                                other = "{0} fl dr",
                            )
                        ),
                    )
                ),
            )
        override val volumeJigger
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} jigger",
                                other = "{0} jiggers",
                            )
                        ),
                    )
                ),
            )
        override val volumePinch
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} snufje",
                                other = "{0} snufjes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} snufje",
                                other = "{0} snufje",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} sn",
                                other = "{0} sn",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuartImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Common,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} imp. quart",
                                other = "{0} imp. quarts",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} imp. qt",
                                other = "{0} imp. qt",
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
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
