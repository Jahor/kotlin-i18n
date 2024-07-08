package info.leonenko.i18n.languages
import info.leonenko.i18n.*
import info.leonenko.i18n.rbnf.RBNFPt_pt

val Language.Companion.pt_PT
    get() = languagePt_PT
private val languagePt_PT = LanguagePt_PT()
class LanguagePt_PT internal constructor() : LanguagePt() {
    override val code: String = "pt_PT"
    override val script: String? = null
    override val territory: String = "PT"
    override val rbnf: RBNFPt_pt
        get() = RBNFPt_pt(this)
    override val pluralFormChooser = PluralFormChooser.Expression(
        one = { (i == 1L) && (v == 0) },
        many = { (e == 0) && (i != 0L) && ((i % 1000000) == 0L) && (v == 0) || (e !in 0..5) },
    )

    class EllipsisLanguage internal constructor() : LanguagePt.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguagePt.ListLanguage(ellipsis) {
        override val unitFormatter: ListFormatter = MultiLengthPatternedListFormatter(
            narrow = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} e {1}", two = "{0} e {1}", ellipsis = ellipsis.wordFormatter),
        )
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguagePt.MeasurementUnitLanguage() {
        override val accelerationGForce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} força G",
                                other = "{0} força G",
                            )
                        ),
                    )
                ),
            )
        override val accelerationMeterPerSquareSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} metro por segundo quadrado",
                                other = "{0} metros por segundo quadrado",
                            )
                        ),
                    )
                ),
            )
        override val angleDegree
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}°",
                                other = "{0} °",
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
        override val areaSquareKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilómetro quadrado",
                                other = "{0} quilómetros quadrados",
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
        override val areaSquareInch
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} in²",
                                other = "{0} in²",
                            )
                        ),
                    )
                ),
            )
        override val concentrationKarat
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilate",
                                other = "{0} quilates",
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
        override val concentrationMillimolePerLiter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milimole por litro",
                                other = "{0} milimoles por litro",
                            )
                        ),
                    )
                ),
            )
        override val consumptionLiterPerKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} litro por quilómetro",
                                other = "{0} litros por quilómetro",
                            )
                        ),
                    )
                ),
            )
        override val consumptionLiterPer100Kilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} litro por 100 quilómetros",
                                other = "{0} litros por 100 quilómetros",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}l/100km",
                                other = "{0}l/100km",
                            )
                        ),
                    )
                ),
            )
        override val digitalByte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} byte",
                                other = "{0} bytes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} byte",
                                other = "{0} byte",
                            )
                        ),
                    )
                ),
            )
        override val digitalBit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bit",
                                other = "{0} bits",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bit",
                                other = "{0} bit",
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
                                one = "{0} quilocaloria",
                                other = "{0} quilocalorias",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kcal",
                                other = "{0} kcal",
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
                                one = "{0} eletrão-volt",
                                other = "{0} eletrões-volts",
                            )
                        ),
                    )
                ),
            )
        override val forceKilowattHourPer100Kilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilowatt-hora por 100 quilómetros",
                                other = "{0} quilowatts-hora por 100 quilómetros",
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
                                one = "{0}kWh/100 km",
                                other = "{0}kWh/100 km",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixel
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} píxel",
                                other = "{0} píxeis",
                            )
                        ),
                    )
                ),
            )
        override val graphicsMegapixel
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megapíxel",
                                other = "{0} megapíxeis",
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
                                one = "{0} ponto",
                                other = "{0} pontos",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pt",
                                other = "{0} pts",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixelPerCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} píxel por centímetro",
                                other = "{0} píxeis por centímetro",
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
                                one = "{0} píxel por polegada",
                                other = "{0} píxeis por polegada",
                            )
                        ),
                    )
                ),
            )
        override val graphicsDotPerInch
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ppp",
                                other = "{0} ppp",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ppp",
                                other = "{0} ppp",
                            )
                        ),
                    )
                ),
            )
        override val lengthKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilómetro",
                                other = "{0} quilómetros",
                            )
                        ),
                    )
                ),
            )
        override val lengthMicrometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} micrómetro",
                                other = "{0} micrómetros",
                            )
                        ),
                    )
                ),
            )
        override val lengthNanometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} nanómetro",
                                other = "{0} nanómetros",
                            )
                        ),
                    )
                ),
            )
        override val lengthPicometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} picómetro",
                                other = "{0} picómetros",
                            )
                        ),
                    )
                ),
            )
        override val lengthMile
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milha",
                                other = "{0} milhas",
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
                                one = "{0} unidade astronómica",
                                other = "{0} unidades astronómicas",
                            )
                        ),
                    )
                ),
            )
        override val lengthFathom
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fth",
                                other = "{0} fth",
                            )
                        ),
                    )
                ),
            )
        override val lengthNauticalMile
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} nmi",
                                other = "{0} nmi",
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
                                one = "{0} ponto tipográfico",
                                other = "{0} pontos tipográficos",
                            )
                        ),
                    )
                ),
            )
        override val lightLumen
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} lúmen",
                                other = "{0} lúmenes",
                            )
                        ),
                    )
                ),
            )
        override val massKilogram
            get() = MultiLengthGenderedPattern(
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kg",
                                other = "{0} kg",
                            )
                        ),
                    )
                ),
            )
        override val massGram
            get() = MultiLengthGenderedPattern(
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} g",
                                other = "{0} g",
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
        override val massCarat
            get() = MultiLengthGenderedPattern(
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ct",
                                other = "{0} ct",
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
                                one = "{0} massa da Terra",
                                other = "{0} massas da Terra",
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
                                one = "{0} psi",
                                other = "{0} psi",
                            )
                        ),
                    )
                ),
            )
        override val pressureInchOfhg
            get() = MultiLengthGenderedPattern(
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}″ Hg",
                                other = "{0}″ Hg",
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
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilómetro por hora",
                                other = "{0} quilómetros por hora",
                            )
                        ),
                    )
                ),
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
        override val speedMilePerHour
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mi/h",
                                other = "{0} mi/h",
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
                                one = "{0} B",
                                other = "{0} B",
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
        override val volumeCubicKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilómetro cúbico",
                                other = "{0} quilómetros cúbicos",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicInch
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} in³",
                                other = "{0} in³",
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
            )
        override val volumeLiter
            get() = MultiLengthGenderedPattern(
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} l",
                                other = "{0} l",
                            )
                        ),
                    )
                ),
            )
        override val volumeCupMetric
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} chávena métrica",
                                other = "{0} chávenas métricas",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} chám",
                                other = "{0} chám",
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
                                one = "{0} acre-pé",
                                other = "{0} acre-pés",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ac ft",
                                other = "{0} ac ft",
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
                                one = "{0} gal imp.",
                                other = "{0} gal imp.",
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
                                one = "{0} chávena",
                                other = "{0} chávenas",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cháv.",
                                other = "{0} cháv.",
                            )
                        ),
                    )
                ),
            )
        override val volumeFluidOunce
            get() = MultiLengthGenderedPattern(
                narrow = GenderedPattern(
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
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} onça fluida imp.",
                                other = "{0} onças fluidas imp.",
                            )
                        ),
                    )
                ),
            )
        override val volumeTablespoon
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cs",
                                other = "{0} cs",
                            )
                        ),
                    )
                ),
            )
        override val volumeTeaspoon
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cc",
                                other = "{0} cc",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoonImperial
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} csb imp.",
                                other = "{0} csb imp.",
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
                                one = "{0} dracma",
                                other = "{0} dracmas",
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
                                one = "{0} doseador",
                                other = "{0} doseadores",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuartImperial
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quarto imp.",
                                other = "{0} quartos imp.",
                            )
                        ),
                    )
                ),
            )
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguagePt.NumberLanguage() {
        override val symbols = LanguagePt.NumberLanguage().symbols.copy(
            groupSymbol = " ",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
