package info.leonenko.i18n.languages

import info.leonenko.i18n.*
import info.leonenko.i18n.rbnf.RBNFPt

val Language.Companion.pt
    get() = languagePt
private val languagePt = LanguagePt()

open class LanguagePt internal constructor() : Language {
    override val code: String = "pt"
    override val language: String = "pt"
    override val script: String? = null
    override val territory: String? = null
    override val rbnf: RBNFPt
        get() = RBNFPt(this)
    override val pluralFormChooser = PluralFormChooser.Expression(
        one = { (i in 0L..1L) },
        many = { (e == 0) && (i != 0L) && ((i % 1000000) == 0L) && (v == 0) || (e !in 0..5) },
    )

    open class EllipsisLanguage internal constructor() : info.leonenko.i18n.EllipsisLanguage {
        override val formatter = EllipsisFormatter.Default
        override val wordFormatter = EllipsisFormatter.WordDefault
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    open class ListLanguage internal constructor(val ellipsis: EllipsisLanguage) : info.leonenko.i18n.ListLanguage {
        override val formatter: ListFormatter = MultiLengthPatternedListFormatter(
            long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} e {1}", two = "{0} e {1}", ellipsis = ellipsis.wordFormatter),
            narrow = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, {1}", two = "{0}, {1}", ellipsis = ellipsis.wordFormatter),
        )
        override val orFormatter: ListFormatter =
            MultiLengthPatternedListFormatter(long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ou {1}", two = "{0} ou {1}", ellipsis = ellipsis.wordFormatter))
        override val unitFormatter: ListFormatter = MultiLengthPatternedListFormatter(
            narrow = PatternedListFormatter(start = "{0} {1}", middle = "{0} {1}", end = "{0} {1}", two = "{0} {1}", ellipsis = ellipsis.wordFormatter),
        )
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    open class MeasurementUnitLanguage internal constructor() : info.leonenko.i18n.units.MeasurementUnitLanguage {
        override val accelerationGForce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} força g",
                                other = "{0} força g",
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
                                one = "{0} metro por segundo ao quadrado",
                                other = "{0} metros por segundo ao quadrado",
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
                                one = "{0} revolução",
                                other = "{0} revoluções",
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
                                one = "{0} radiano",
                                other = "{0} radianos",
                            )
                        ),
                    )
                ),
            )
        override val angleDegree
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} grau",
                                other = "{0} graus",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} °",
                                other = "{0} °",
                            )
                        ),
                    )
                ),
            )
        override val angleArcMinute
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} minuto de arco",
                                other = "{0} minutos de arco",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} arcmin",
                                other = "{0} arcmins",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}′",
                                other = "{0}′",
                            )
                        ),
                    )
                ),
            )
        override val angleArcSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} segundo de arco",
                                other = "{0} segundos de arco",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} arcseg",
                                other = "{0} arcsegs",
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
        override val areaSquareKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilômetro quadrado",
                                other = "{0} quilômetros quadrados",
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
                                one = "{0} hectare",
                                other = "{0} hectares",
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
                                one = "{0} metro quadrado",
                                other = "{0} metros quadrados",
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
                                one = "{0} centímetro quadrado",
                                other = "{0} centímetros quadrados",
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
                                one = "{0} milha quadrada",
                                other = "{0} milhas quadradas",
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
                                one = "{0} acre",
                                other = "{0} acres",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
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
                                one = "{0} jarda quadrada",
                                other = "{0} jardas quadradas",
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
                                one = "{0} pé quadrado",
                                other = "{0} pés quadrados",
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
                                one = "{0} polegada quadrada",
                                other = "{0} polegadas quadradas",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pol²",
                                other = "{0} pol²",
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
                                other = "{0} dunans",
                            )
                        ),
                    )
                ),
            )
        override val concentrationKarat
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilate",
                                other = "{0} kilates",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} k",
                                other = "{0} k",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermillion
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} parte por milhão",
                                other = "{0} partes por milhão",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPercent
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} por cento",
                                other = "{0} por cento",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermille
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} por mil",
                                other = "{0} por mil",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermyriad
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ponto base",
                                other = "{0} pontos base",
                            )
                        ),
                    )
                ),
            )
        override val concentrationAPortion: MultiLengthGenderedPattern
            get() = TODO("APortion formatter is not implemented yet for pt")
        override val concentrationMilligramOfglucosePerDeciliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} miligrama por decilitro",
                                other = "{0} miligramas por decilitro",
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milimol por litro",
                                other = "{0} milimols por litro",
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
            get() = TODO("ItemPerCubicMeter formatter is not implemented yet for pt")
        override val concentrationItem
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} item",
                                other = "{0} itens",
                            )
                        ),
                    )
                ),
            )
        override val concentrationMole
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mol",
                                other = "{0} mols",
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
                                one = "{0} litro por quilômetro",
                                other = "{0} litros por quilômetro",
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} litro por 100 quilômetros",
                                other = "{0} litros por 100 quilômetros",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} l/100 km",
                                other = "{0} l/100 km",
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
                                one = "{0} milha por galão",
                                other = "{0} milhas por galão",
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
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milha por galão imperial",
                                other = "{0} milhas por galão imperial",
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
            )
        override val digitalPetabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} petabyte",
                                other = "{0} petabytes",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} terabyte",
                                other = "{0} terabytes",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigabyte",
                                other = "{0} gigabytes",
                            )
                        ),
                    )
                ),
            )
        override val digitalGigabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megabyte",
                                other = "{0} megabytes",
                            )
                        ),
                    )
                ),
            )
        override val digitalMegabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilobyte",
                                other = "{0} kilobytes",
                            )
                        ),
                    )
                ),
            )
        override val digitalKilobit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
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
        override val digitalByte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
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
                                one = "{0} bytes",
                                other = "{0} bytes",
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
                    gender = Gender.Masculine,
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
                                one = "{0} bits",
                                other = "{0} bits",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
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
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} século",
                                other = "{0} séculos",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} séc.",
                                other = "{0} sécs.",
                            )
                        ),
                    )
                ),
            )
        override val durationDecade
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} década",
                                other = "{0} décadas",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} déc.",
                                other = "{0} déc.",
                            )
                        ),
                    )
                ),
            )
        override val durationYear
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ano",
                                other = "{0} anos",
                            )
                        ),
                    )
                ),
            )
        override val durationQuarter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} trimestre",
                                other = "{0} trimestres",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} trim.",
                                other = "{0} trim.",
                            )
                        ),
                    )
                ),
            )
        override val durationMonth
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mês",
                                other = "{0} meses",
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
                                one = "{0} semana",
                                other = "{0} semanas",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} sem.",
                                other = "{0} sem.",
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
                                one = "{0} dia",
                                other = "{0} dias",
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
                                one = "{0} hora",
                                other = "{0} horas",
                            )
                        ),
                    )
                ),
            )
        override val durationMinute
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} minuto",
                                other = "{0} minutos",
                            )
                        ),
                    )
                ),
            )
        override val durationSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} segundo",
                                other = "{0} segundos",
                            )
                        ),
                    )
                ),
            )
        override val durationMillisecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milissegundo",
                                other = "{0} milissegundos",
                            )
                        ),
                    )
                ),
            )
        override val durationMicrosecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} microssegundo",
                                other = "{0} microssegundos",
                            )
                        ),
                    )
                ),
            )
        override val durationNanosecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} nanossegundo",
                                other = "{0} nanossegundos",
                            )
                        ),
                    )
                ),
            )
        override val electricAmpere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ampere",
                                other = "{0} amperes",
                            )
                        ),
                    )
                ),
            )
        override val electricMilliampere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} miliampere",
                                other = "{0} miliamperes",
                            )
                        ),
                    )
                ),
            )
        override val electricOhm
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ohm",
                                other = "{0} ohms",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondSquareAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondSquareAmpere formatter is not implemented yet for pt")
        override val electricVolt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} volt",
                                other = "{0} volts",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondAmpere formatter is not implemented yet for pt")
        override val energyKilocalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilocaloria",
                                other = "{0} quilocalorias",
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
                                one = "{0} caloria",
                                other = "{0} calorias",
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
                                one = "{0} caloria",
                                other = "{0} calorias",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cal",
                                other = "{0} cal",
                            )
                        ),
                    )
                ),
            )
        override val energyKilojoule
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilojoule",
                                other = "{0} quilojoules",
                            )
                        ),
                    )
                ),
            )
        override val energyJoule
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilowatt-hora",
                                other = "{0} quilowatts-hora",
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
                                one = "{0} elétron-volt",
                                other = "{0} elétrons-volt",
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
                                one = "{0} unidade térmica britânica",
                                other = "{0} unidades térmicas britânicas",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} BTU",
                                other = "{0} BTU",
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
                                one = "{0} unidade térmica norte-americana",
                                other = "{0} unidades térmicas norte-americanas",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} thm EUA",
                                other = "{0} thm EUA",
                            )
                        ),
                    )
                ),
            )
        override val energyKilogramSquareMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerSquareSecond formatter is not implemented yet for pt")
        override val forcePoundForce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} libra de força",
                                other = "{0} libras de força",
                            )
                        ),
                    )
                ),
            )
        override val forceNewton
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} newton",
                                other = "{0} newtons",
                            )
                        ),
                    )
                ),
            )
        override val forceKilowattHourPer100Kilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilowatt-hora por 100 quilômetros",
                                other = "{0} quilowatts-hora por 100 quilômetros",
                            )
                        ),
                    )
                ),
            )
        override val forceKilogramMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramMeterPerSquareSecond formatter is not implemented yet for pt")
        override val frequencyGigahertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
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
                    gender = Gender.Masculine,
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
                    gender = Gender.Masculine,
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
                    gender = Gender.Masculine,
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
            get() = TODO("RevolutionPerSecond formatter is not implemented yet for pt")
        override val graphicsEm
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
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
                    gender = Gender.Masculine,
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
                    gender = Gender.Masculine,
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
                                one = "{0} ponto",
                                other = "{0} pts",
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pixel por centímetro",
                                other = "{0} pixels por centímetro",
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
                                one = "{0} pixel por polegada",
                                other = "{0} pixels por polegada",
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
                                one = "{0} ponto por centímetro",
                                other = "{0} pontos por centímetro",
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
                                one = "{0} ponto por polegada",
                                other = "{0} pontos por polegada",
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
            get() = TODO("PixelPerMeter formatter is not implemented yet for pt")
        override val lengthEarthRadius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} raio terrestre",
                                other = "{0} raios terrestres",
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
                                one = "{0} quilômetro",
                                other = "{0} quilômetros",
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
                                one = "{0} metro",
                                other = "{0} metros",
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
                                one = "{0} decímetro",
                                other = "{0} decímetros",
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
                                one = "{0} centímetro",
                                other = "{0} centímetros",
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
                                one = "{0} milímetro",
                                other = "{0} milímetros",
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
                                one = "{0} micrômetro",
                                other = "{0} micrômetros",
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
                                one = "{0} nanômetro",
                                other = "{0} nanômetros",
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
                                one = "{0} picômetro",
                                other = "{0} picômetros",
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
                                one = "{0} milha",
                                other = "{0} milhas",
                            )
                        ),
                    )
                ),
            )
        override val lengthYard
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} jarda",
                                other = "{0} jardas",
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
                                one = "{0} pé",
                                other = "{0} pés",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}′",
                                other = "{0}′",
                            )
                        ),
                    )
                ),
            )
        override val lengthInch
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} polegada",
                                other = "{0} polegadas",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pol.",
                                other = "{0} pol.",
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
                    gender = Gender.Masculine,
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
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ano-luz",
                                other = "{0} anos-luz",
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
                                one = "{0} unidade astronômica",
                                other = "{0} unidades astronômicas",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ua",
                                other = "{0} ua",
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
                                other = "{0} furlongs",
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
                                one = "{0} braça",
                                other = "{0} braças",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bça.",
                                other = "{0} bça.",
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
                                one = "{0} milha náutica",
                                other = "{0} milhas náuticas",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mn",
                                other = "{0} mn",
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
                                one = "{0} milha escandinava",
                                other = "{0} milhas escandinavas",
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
                                other = "{0} pontos",
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
                                one = "{0} raio solar",
                                other = "{0} raios solares",
                            )
                        ),
                    )
                ),
            )
        override val lightLux
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
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
            get() = TODO("CandelaPerSquareMeter formatter is not implemented yet for pt")
        override val lightCandela
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} candela",
                                other = "{0} candelas",
                            )
                        ),
                    )
                ),
            )
        override val lightLumen
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} lúmen",
                                other = "{0} lúmens",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaSquareMeterPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaSquareMeterPerSquareMeter formatter is not implemented yet for pt")
        override val lightSolarLuminosity
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} luminosidade solar",
                                other = "{0} luminosidades solares",
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
                                one = "{0} tonelada métrica",
                                other = "{0} toneladas métricas",
                            )
                        ),
                    )
                ),
            )
        override val massKilogram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilograma",
                                other = "{0} quilogramas",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}kg",
                                other = "{0}kg",
                            )
                        ),
                    )
                ),
            )
        override val massGram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} grama",
                                other = "{0} gramas",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}g",
                                other = "{0}g",
                            )
                        ),
                    )
                ),
            )
        override val massMilligram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} miligrama",
                                other = "{0} miligramas",
                            )
                        ),
                    )
                ),
            )
        override val massMicrogram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} micrograma",
                                other = "{0} microgramas",
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
                                one = "{0} tonelada americana",
                                other = "{0} toneladas americanas",
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
                                other = "{0} stones",
                            )
                        ),
                    )
                ),
            )
        override val massPound
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} libra",
                                other = "{0} libras",
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
                                one = "{0} onça",
                                other = "{0} onças",
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
                                one = "{0} onça troy",
                                other = "{0} onças troy",
                            )
                        ),
                    )
                ),
            )
        override val massCarat
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
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
                                one = "{0} ct",
                                other = "{0} ct",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ql",
                                other = "{0} ql",
                            )
                        ),
                    )
                ),
            )
        override val massDalton
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dalton",
                                other = "{0} daltons",
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
                                one = "{0} massa terrestre",
                                other = "{0} massas terrestres",
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
                                one = "{0} massa solar",
                                other = "{0} massas solares",
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
                                one = "{0} grão",
                                other = "{0} grãos",
                            )
                        ),
                    )
                ),
            )
        override val powerGigawatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigawatt",
                                other = "{0} gigawatts",
                            )
                        ),
                    )
                ),
            )
        override val powerMegawatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megawatt",
                                other = "{0} megawatts",
                            )
                        ),
                    )
                ),
            )
        override val powerKilowatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilowatt",
                                other = "{0} quilowatts",
                            )
                        ),
                    )
                ),
            )
        override val powerWatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} watt",
                                other = "{0} watts",
                            )
                        ),
                    )
                ),
            )
        override val powerMilliwatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} miliwatt",
                                other = "{0} miliwatts",
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
                                one = "{0} cavalo-vapor",
                                other = "{0} cavalos-vapor",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cv",
                                other = "{0} cv",
                            )
                        ),
                    )
                ),
            )
        override val powerKilogramSquareMeterPerCubicSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecond formatter is not implemented yet for pt")
        override val pressureMillimeterOfhg
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milímetro de mercúrio",
                                other = "{0} milímetros de mercúrio",
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
        override val pressurePoundForcePerSquareInch
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} libra por polegada quadrada",
                                other = "{0} libras por polegada quadrada",
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
                                one = "{0} polegada de mercúrio",
                                other = "{0} polegadas de mercúrio",
                            )
                        ),
                    )
                ),
            )
        override val pressureBar
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bar",
                                other = "{0} bars",
                            )
                        ),
                    )
                ),
            )
        override val pressureMillibar
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milibar",
                                other = "{0} milibares",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
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
        override val pressureAtmosphere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} atmosfera",
                                other = "{0} atmosferas",
                            )
                        ),
                    )
                ),
            )
        override val pressurePascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pascal",
                                other = "{0} pascais",
                            )
                        ),
                    )
                ),
            )
        override val pressureHectopascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hectopascal",
                                other = "{0} hectopascais",
                            )
                        ),
                    )
                ),
            )
        override val pressureKilopascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilopascal",
                                other = "{0} quilopascais",
                            )
                        ),
                    )
                ),
            )
        override val pressureMegapascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megapascal",
                                other = "{0} megapascais",
                            )
                        ),
                    )
                ),
            )
        override val pressureGasolineEnergyDensity: MultiLengthGenderedPattern
            get() = TODO("GasolineEnergyDensity formatter is not implemented yet for pt")
        override val pressureKilogramPerMeterSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramPerMeterSquareSecond formatter is not implemented yet for pt")
        override val speedKilometerPerHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quilômetro por hora",
                                other = "{0} quilômetros por hora",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}km/h",
                                other = "{0}km/h",
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
                                one = "{0} metro por segundo",
                                other = "{0} metros por segundo",
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
                                one = "{0} milha por hora",
                                other = "{0} milhas por hora",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mph",
                                other = "{0} mph",
                            )
                        ),
                    )
                ),
            )
        override val speedKnot
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} nó",
                                other = "{0} nós",
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
                                one = "{0}B",
                                other = "{0}B",
                            )
                        ),
                    )
                ),
            )
        override val temperatureGeneric: MultiLengthGenderedPattern
            get() = TODO("Generic formatter is not implemented yet for pt")
        override val temperatureCelsius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} grau Celsius",
                                other = "{0} graus Celsius",
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} grau Fahrenheit",
                                other = "{0} graus Fahrenheit",
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
            )
        override val temperatureKelvin
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kelvin",
                                other = "{0} kelvins",
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
                                one = "{0} pé-libra",
                                other = "{0} pés-libra",
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
                                one = "{0} newton-metro",
                                other = "{0} newton-metros",
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
                                one = "{0} quilômetro cúbico",
                                other = "{0} quilômetros cúbicos",
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
                                one = "{0} metro cúbico",
                                other = "{0} metros cúbicos",
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
                                one = "{0} centímetro cúbico",
                                other = "{0} centímetros cúbicos",
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
                                one = "{0} milha cúbica",
                                other = "{0} milhas cúbicas",
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
                                one = "{0} jarda cúbica",
                                other = "{0} jardas cúbicas",
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
                                one = "{0} pé cúbico",
                                other = "{0} pés cúbicos",
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
                                one = "{0} polegada cúbica",
                                other = "{0} polegadas cúbicas",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pol³",
                                other = "{0} pol³",
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
                                one = "{0} megalitro",
                                other = "{0} megalitros",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Ml",
                                other = "{0} Ml",
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
                                one = "{0} hectolitro",
                                other = "{0} hectolitros",
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} litro",
                                other = "{0} litros",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}l",
                                other = "{0}l",
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
                                one = "{0} decilitro",
                                other = "{0} decilitros",
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} centilitro",
                                other = "{0} centilitros",
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mililitro",
                                other = "{0} mililitros",
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pint métrico",
                                other = "{0} pints métricos",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ptm",
                                other = "{0} ptm",
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
                                one = "{0} xícara métrica",
                                other = "{0} xícaras métricas",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} xícm",
                                other = "{0} xícm",
                            )
                        ),
                    )
                ),
            )
        override val volumeAcreFoot
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} acre-pé",
                                other = "{0} acre-pés",
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} galão",
                                other = "{0} galões",
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} galão imperial",
                                other = "{0} galões imperiais",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gal. imp.",
                                other = "{0} gal. imp.",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuart
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quarto",
                                other = "{0} quartos",
                            )
                        ),
                    )
                ),
            )
        override val volumePint
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pint",
                                other = "{0} pints",
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
                                one = "{0} xícara",
                                other = "{0} xícaras",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} xíc.",
                                other = "{0} xíc.",
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
                                one = "{0} onça fluida",
                                other = "{0} onças fluidas",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fl. oz.",
                                other = "{0} fl. oz.",
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
                                one = "{0} onça fluida imperial",
                                other = "{0} onças fluidas imperiais",
                            )
                        ),
                    )
                ),
            )
        override val volumeTablespoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} colher de sopa",
                                other = "{0} colheres de sopa",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} c. sopa",
                                other = "{0} c. sopa",
                            )
                        ),
                    )
                ),
            )
        override val volumeTeaspoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} colher de chá",
                                other = "{0} colheres de chá",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} c. chá",
                                other = "{0} c. chá",
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
                                one = "{0} barril",
                                other = "{0} barris",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} colher de sobremesa",
                                other = "{0} colheres de sobremesa",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} csb",
                                other = "{0} csb",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoonImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} colher de sobremesa imperial",
                                other = "{0} colheres de sobremesa imperiais",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} csb imp",
                                other = "{0} csb imp",
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
                                one = "{0} gota",
                                other = "{0} gotas",
                            )
                        ),
                    )
                ),
            )
        override val volumeDram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dracma líquido",
                                other = "{0} dracmas líquidos",
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
                                one = "{0} dosador",
                                other = "{0} dosadores",
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
                                one = "{0} pitada",
                                other = "{0} pitadas",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuartImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} quarto imperial",
                                other = "{0} quartos imperiais",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} impqt",
                                other = "{0} impqt",
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
