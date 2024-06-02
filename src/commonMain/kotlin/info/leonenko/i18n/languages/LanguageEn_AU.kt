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

val Language.Companion.en_AU
    get() = languageEn_AU
private val languageEn_AU = LanguageEn_AU()

class LanguageEn_AU internal constructor() : LanguageEn() {
    override val code: String = "en_AU"
    override val script: String? = null
    override val territory: String = "AU"

    class EllipsisLanguage internal constructor() : LanguageEn.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEn.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEn.MeasurementUnitLanguage() {
        override val angleArcMinute
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} arcmin.",
                                other = "{0} arcmin.",
                            )
                        ),
                    )
                ),
            )
        override val angleArcSecond
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} arcsec.",
                                other = "{0} arcsec.",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermille
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} per mill",
                                other = "{0} per mill",
                            )
                        ),
                    )
                ),
            )
        override val concentrationMilligramOfglucosePerDeciliter
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mg/dL",
                                other = "{0} mg/dL",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}mg/dL",
                                other = "{0}mg/dL",
                            )
                        ),
                    )
                ),
            )
        override val concentrationMillimolePerLiter
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mmol/L",
                                other = "{0} mmol/L",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}mmol/L",
                                other = "{0}mmol/L",
                            )
                        ),
                    )
                ),
            )
        override val consumptionLiterPerKilometer
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} L/km",
                                other = "{0} L/km",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}L/km",
                                other = "{0}L/km",
                            )
                        ),
                    )
                ),
            )
        override val consumptionLiterPer100Kilometer
            get() = MultiLengthGenderedPattern(
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
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} m.p.g. US",
                                other = "{0} m.p.g. US",
                            )
                        ),
                    )
                ),
            )
        override val consumptionMilePerGallonImperial
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} m.p.g.",
                                other = "{0} m.p.g.",
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
                                one = "{0} C.",
                                other = "{0} C.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}C.",
                                other = "{0}C.",
                            )
                        ),
                    )
                ),
            )
        override val durationSecond
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} sec.",
                                other = "{0} secs",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}s.",
                                other = "{0}s.",
                            )
                        ),
                    )
                ),
            )
        override val energyKilocalorie
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Cal",
                                other = "{0} Cal",
                            )
                        ),
                    )
                ),
            )
        override val energyKilowattHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilowatt hour",
                                other = "{0} kilowatt hours",
                            )
                        ),
                    )
                ),
            )
        override val lengthAstronomicalUnit
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} AU",
                                other = "{0} AU",
                            )
                        ),
                    )
                ),
            )
        override val massCarat
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} CM",
                                other = "{0} CM",
                            )
                        ),
                    )
                ),
            )
        override val pressureMillimeterOfhg
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mm Hg",
                                other = "{0} mm Hg",
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
                                one = "{0} pound per square inch",
                                other = "{0} pounds per square inch",
                            )
                        ),
                    )
                ),
            )
        override val pressureMillibar
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mb",
                                other = "{0} mb",
                            )
                        ),
                    )
                ),
            )
        override val speedKilometerPerHour
            get() = MultiLengthGenderedPattern(
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} km/h",
                                other = "{0} km/h",
                            )
                        ),
                    )
                ),
            )
        override val speedMeterPerSecond
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} m/s.",
                                other = "{0} m/s.",
                            )
                        ),
                    )
                ),
            )
        override val temperatureGeneric
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} degree",
                                other = "{0} degrees",
                            )
                        ),
                    )
                ),
            )
        override val temperatureCelsius
            get() = MultiLengthGenderedPattern(
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}°C",
                                other = "{0}°C",
                            )
                        ),
                    )
                ),
            )
        override val volumeMegaliter
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ML",
                                other = "{0} ML",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ML",
                                other = "{0}ML",
                            )
                        ),
                    )
                ),
            )
        override val volumeHectoliter
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hL",
                                other = "{0} hL",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}hL",
                                other = "{0}hL",
                            )
                        ),
                    )
                ),
            )
        override val volumeLiter
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} L",
                                other = "{0} L",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}L",
                                other = "{0}L",
                            )
                        ),
                    )
                ),
            )
        override val volumeDeciliter
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dL",
                                other = "{0} dL",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}dL",
                                other = "{0}dL",
                            )
                        ),
                    )
                ),
            )
        override val volumeCentiliter
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cL",
                                other = "{0} cL",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}cL",
                                other = "{0}cL",
                            )
                        ),
                    )
                ),
            )
        override val volumeMilliliter
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mL",
                                other = "{0} mL",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}mL",
                                other = "{0}mL",
                            )
                        ),
                    )
                ),
            )
        override val volumeBushel
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bus.",
                                other = "{0} bus.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bus.",
                                other = "{0} bus.",
                            )
                        ),
                    )
                ),
            )
        override val volumeGallon
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gal US",
                                other = "{0} gal US",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gal US",
                                other = "{0} gal US",
                            )
                        ),
                    )
                ),
            )
        override val volumeGallonImperial
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gal.",
                                other = "{0} gal.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}gal.",
                                other = "{0}gal.",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuart
            get() = MultiLengthGenderedPattern(
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}qt US",
                                other = "{0}qt US",
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
                                one = "{0} Imp. fluid ounce",
                                other = "{0} Imp. fluid ounces",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fl oz Imp.",
                                other = "{0} fl oz Imp.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}fl oz Im",
                                other = "{0}fl oz Im",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoon
            get() = MultiLengthGenderedPattern(
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}dsp US",
                                other = "{0}dsp US",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoonImperial
            get() = MultiLengthGenderedPattern(
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}dsp-Imp",
                                other = "{0}dsp-Imp",
                            )
                        ),
                    )
                ),
            )
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageEn.NumberLanguage() {
        override val symbols = LanguageEn.NumberLanguage().symbols.copy(
            exponentialSymbol = "e",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
