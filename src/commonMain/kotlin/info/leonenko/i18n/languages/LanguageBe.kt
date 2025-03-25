package info.leonenko.i18n.languages

import info.leonenko.i18n.*
import info.leonenko.i18n.rbnf.RBNFBe

val Language.Companion.be
    get() = languageBe
private val languageBe = LanguageBe()
open class LanguageBe internal constructor() : Language {
    override val code: String = "be"
    override val language: String = "be"
    override val script: String? = null
    override val territory: String? = null
    override val rbnf: RBNFBe
        get() = RBNFBe(this)
    override val pluralFormChooser = PluralFormChooser.Expression(
        one = { ((n % 10) == 1.0) && ((n % 100) != 11.0) },
        few = { ((n % 10) in 2.0..4.0) && ((n % 100) !in 12.0..14.0) },
        many = { ((n % 10) == 0.0) || ((n % 10) in 5.0..9.0) || ((n % 100) in 11.0..14.0) },
    )

    open class EllipsisLanguage internal constructor() : info.leonenko.i18n.EllipsisLanguage {
        override val formatter = EllipsisFormatter.Default
        override val wordFormatter = EllipsisFormatter.WordDefault
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    open class ListLanguage internal constructor(val ellipsis: EllipsisLanguage) : info.leonenko.i18n.ListLanguage {
        override val formatter: ListFormatter
            get() = TODO("ListFormatter is not yet implemented for be")
        override val orFormatter: ListFormatter
            get() = TODO("Or ListFormatter is not yet implemented for be")
        override val unitFormatter: ListFormatter
            get() = TODO("Unit ListFormatter is not yet implemented for be")
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    open class MeasurementUnitLanguage internal constructor() : info.leonenko.i18n.units.MeasurementUnitLanguage {
        override val accelerationGForce: MultiLengthGenderedPattern
            get() = TODO("GForce formatter is not implemented yet for be")
        override val accelerationMeterPerSquareSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} м/с²",
                                few = "{0} м/с²",
                                many = "{0} м/с²",
                                other = "{0} м/с²",
                            )
                        ),
                    )
                ),
            )
        override val angleRevolution: MultiLengthGenderedPattern
            get() = TODO("Revolution formatter is not implemented yet for be")
        override val angleRadian: MultiLengthGenderedPattern
            get() = TODO("Radian formatter is not implemented yet for be")
        override val angleDegree: MultiLengthGenderedPattern
            get() = TODO("Degree formatter is not implemented yet for be")
        override val angleArcMinute
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кутняя хвіліна",
                                few = "{0} кутнія хвіліны",
                                many = "{0} кутніх хвілінаў",
                                other = "{0} кутняй хвіліны",
                            )
                        ),
                    )
                ),
            )
        override val angleArcSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кутняя сэкунда",
                                few = "{0} кутнія сэкунды",
                                many = "{0} кутніх сэкундаў",
                                other = "{0} кутняй сэкунды",
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
                                one = "{0} квадратны кілямэтар",
                                few = "{0} квадратныя кілямэтры",
                                many = "{0} квадратных кілямэтраў",
                                other = "{0} квадратнага кілямэтра",
                            )
                        ),
                    )
                ),
            )
        override val areaHectare
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гэктар",
                                few = "{0} гэктары",
                                many = "{0} гэктараў",
                                other = "{0} гэктара",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} квадратны мэтар",
                                few = "{0} квадратныя мэтры",
                                many = "{0} квадратных мэтраў",
                                other = "{0} квадратнага мэтра",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} квадратны сантымэтар",
                                few = "{0} квадратныя сантымэтры",
                                many = "{0} квадратных сантымэтраў",
                                other = "{0} квадратнага сантымэтра",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareMile: MultiLengthGenderedPattern
            get() = TODO("SquareMile formatter is not implemented yet for be")
        override val areaAcre: MultiLengthGenderedPattern
            get() = TODO("Acre formatter is not implemented yet for be")
        override val areaSquareYard: MultiLengthGenderedPattern
            get() = TODO("SquareYard formatter is not implemented yet for be")
        override val areaSquareFoot: MultiLengthGenderedPattern
            get() = TODO("SquareFoot formatter is not implemented yet for be")
        override val areaSquareInch: MultiLengthGenderedPattern
            get() = TODO("SquareInch formatter is not implemented yet for be")
        override val areaDunam: MultiLengthGenderedPattern
            get() = TODO("Dunam formatter is not implemented yet for be")
        override val concentrationKarat: MultiLengthGenderedPattern
            get() = TODO("Karat formatter is not implemented yet for be")
        override val concentrationPermillion: MultiLengthGenderedPattern
            get() = TODO("Permillion formatter is not implemented yet for be")
        override val concentrationPercent
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} адсотак",
                                few = "{0} адсоткі",
                                many = "{0} адсоткаў",
                                other = "{0} адсотка",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermille: MultiLengthGenderedPattern
            get() = TODO("Permille formatter is not implemented yet for be")
        override val concentrationPermyriad: MultiLengthGenderedPattern
            get() = TODO("Permyriad formatter is not implemented yet for be")
        override val concentrationAPortion: MultiLengthGenderedPattern
            get() = TODO("APortion formatter is not implemented yet for be")
        override val concentrationMilligramOfglucosePerDeciliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} міліграм на дэцылітар",
                                few = "{0} міліграмы на дэцылітар",
                                many = "{0} міліграм на дэцылітар",
                                other = "{0} міліграма на дэцылітар",
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
                                one = "{0} мілімоль на літар",
                                few = "{0} мілімолі на літар",
                                many = "{0} мілімоляў на літар",
                                other = "{0} мілімоля на літар",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPortionPer1e9: MultiLengthGenderedPattern
            get() = TODO("PortionPer1e9 formatter is not implemented yet for be")
        override val concentrationItem: MultiLengthGenderedPattern
            get() = TODO("Item formatter is not implemented yet for be")
        override val concentrationMole
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} моль",
                                few = "{0} моля",
                                many = "{0} моляў",
                                other = "{0} моля",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} моль",
                                few = "{0} моля",
                                many = "{0} моляў",
                                other = "{0} моля",
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
                                one = "{0} літар на кілямэтар",
                                few = "{0} літары на кілямэтар",
                                many = "{0} літараў на кілямэтар",
                                other = "{0} літара на кілямэтар",
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
                                one = "{0} літар на 100 кілямэтраў",
                                few = "{0} літары на 100 кілямэтраў",
                                many = "{0} літараў на 100 кілямэтраў",
                                other = "{0} літара на 100 кілямэтраў",
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
                                one = "{0} міля на галён",
                                few = "{0} мілі на галён",
                                many = "{0} міль на галён",
                                other = "{0} мілі на галён",
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
                                one = "{0} міля на імп. галён",
                                few = "{0} мілі на імп. галён",
                                many = "{0} міль на імп. галён",
                                other = "{0} мілі на імп. галён",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} міля/імп. гал.",
                                few = "{0} мілі/імп. галён",
                                many = "{0} міль/імп. галён",
                                other = "{0} мілі/імп. галён",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                many = "{0} міль/імп. галён",
                                other = "{0} мілі/імп. галён",
                            )
                        ),
                    )
                ),
            )
        override val digitalPetabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} пэтабайт",
                                few = "{0} пэтабайты",
                                many = "{0} пэтабайт",
                                other = "{0} пэтабайта",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabyte: MultiLengthGenderedPattern
            get() = TODO("Terabyte formatter is not implemented yet for be")
        override val digitalTerabit: MultiLengthGenderedPattern
            get() = TODO("Terabit formatter is not implemented yet for be")
        override val digitalGigabyte: MultiLengthGenderedPattern
            get() = TODO("Gigabyte formatter is not implemented yet for be")
        override val digitalGigabit: MultiLengthGenderedPattern
            get() = TODO("Gigabit formatter is not implemented yet for be")
        override val digitalMegabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мэгабайт",
                                few = "{0} мэгабайты",
                                many = "{0} мэгабайт",
                                other = "{0} мэгабайта",
                            )
                        ),
                    )
                ),
            )
        override val digitalMegabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мэгабіт",
                                few = "{0} мэгабіты",
                                many = "{0} мэгабіт",
                                other = "{0} мэгабіта",
                            )
                        ),
                    )
                ),
            )
        override val digitalKilobyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кілябайт",
                                few = "{0} кілябайты",
                                many = "{0} кілябайт",
                                other = "{0} кілябайта",
                            )
                        ),
                    )
                ),
            )
        override val digitalKilobit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кілябіт",
                                few = "{0} кілябіты",
                                many = "{0} кілябіт",
                                other = "{0} кілябіта",
                            )
                        ),
                    )
                ),
            )
        override val digitalByteUnit: MultiLengthGenderedPattern
            get() = TODO("ByteUnit formatter is not implemented yet for be")
        override val digitalBit: MultiLengthGenderedPattern
            get() = TODO("Bit formatter is not implemented yet for be")
        override val durationCentury
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} стагодьдзе",
                                few = "{0} стагодзьдзі",
                                many = "{0} стагодзьдзяў",
                                other = "{0} стагодзьдзя",
                            )
                        ),
                    )
                ),
            )
        override val durationDecade
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дзесяцігодьдзе",
                                few = "{0} дзесяцігодьдзі",
                                many = "{0} дзесяцігодзьдзяў",
                                other = "{0} дзесяцігодзьдзя",
                            )
                        ),
                    )
                ),
            )
        override val durationYear: MultiLengthGenderedPattern
            get() = TODO("Year formatter is not implemented yet for be")
        override val durationQuarter: MultiLengthGenderedPattern
            get() = TODO("Quarter formatter is not implemented yet for be")
        override val durationMonth: MultiLengthGenderedPattern
            get() = TODO("Month formatter is not implemented yet for be")
        override val durationWeek
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} тыдз.",
                                few = "{0} тыдз.",
                                many = "{0} тыдз.",
                                other = "{0} тыдз.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} тыдз.",
                                few = "{0} тыдз.",
                                many = "{0} тыдз.",
                                other = "{0} тыдз.",
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
                                one = "{0} сут.",
                                few = "{0} сут.",
                                many = "{0} сут.",
                                other = "{0} сут.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} сут",
                                few = "{0} сут",
                                many = "{0} сут",
                                other = "{0} сут",
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
                                one = "{0} гадз.",
                                few = "{0} гадз.",
                                many = "{0} гадз.",
                                other = "{0} гадз.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гадз",
                                few = "{0} гадз",
                                many = "{0} гадз",
                                other = "{0} гадз",
                            )
                        ),
                    )
                ),
            )
        override val durationMinute
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} хв.",
                                few = "{0} хв.",
                                many = "{0} хв.",
                                other = "{0} хв.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} хв",
                                few = "{0} хв",
                                many = "{0} хв",
                                other = "{0} хв",
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
                                one = "{0} сэкунда",
                                few = "{0} сэкунды",
                                many = "{0} сэкундаў",
                                other = "{0} сэкунды",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} сэк.",
                                few = "{0} сэк.",
                                many = "{0} сэк.",
                                other = "{0} сэк.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} с",
                                few = "{0} с",
                                many = "{0} с",
                                other = "{0} с",
                            )
                        ),
                    )
                ),
            )
        override val durationMillisecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мілісэкунда",
                                few = "{0} мілісэкунды",
                                many = "{0} мілісэкундаў",
                                other = "{0} мілісэкунды",
                            )
                        ),
                    )
                ),
            )
        override val durationMicrosecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мікрасэкунда",
                                few = "{0} мікрасэкунды",
                                many = "{0} мікрасэкунд",
                                other = "{0} мікрасэкунды",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мкс.",
                                few = "{0} мкс.",
                                many = "{0} мкс.",
                                other = "{0} мкс.",
                            )
                        ),
                    )
                ),
            )
        override val durationNanosecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} нанасэкунда",
                                few = "{0} нанасэкунды",
                                many = "{0} нанасэкунд",
                                other = "{0} нанасэкунды",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} нсэк.",
                                few = "{0} нсэк.",
                                many = "{0} нсэк.",
                                other = "{0} нсэк.",
                            )
                        ),
                    )
                ),
            )
        override val durationNight: MultiLengthGenderedPattern
            get() = TODO("Night formatter is not implemented yet for be")
        override val electricAmpere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ампэр",
                                few = "{0} ампэры",
                                many = "{0} ампэр",
                                other = "{0} ампэра",
                            )
                        ),
                    )
                ),
            )
        override val electricMilliampere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} міліампэр",
                                few = "{0} міліампэры",
                                many = "{0} міліампэр",
                                other = "{0} міліампэра",
                            )
                        ),
                    )
                ),
            )
        override val electricOhm: MultiLengthGenderedPattern
            get() = TODO("Ohm formatter is not implemented yet for be")
        override val electricKilogramSquareMeterPerCubicSecondSquareAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondSquareAmpere formatter is not implemented yet for be")
        override val electricVolt: MultiLengthGenderedPattern
            get() = TODO("Volt formatter is not implemented yet for be")
        override val electricKilogramSquareMeterPerCubicSecondAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondAmpere formatter is not implemented yet for be")
        override val energyKilocalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кілякалёрыя",
                                few = "{0} кілякалёрыі",
                                many = "{0} кілякалёрый",
                                other = "{0} кілякалёрыі",
                            )
                        ),
                    )
                ),
            )
        override val energyCalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} калёрыя",
                                few = "{0} калёрыі",
                                many = "{0} калёрый",
                                other = "{0} калёрыі",
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
                                one = "{0} калёрыя",
                                few = "{0} калёрыі",
                                many = "{0} калёрый",
                                other = "{0} калёрыі",
                            )
                        ),
                    )
                ),
            )
        override val energyKilojoule
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кіляджоўль",
                                few = "{0} кіляджоўлі",
                                many = "{0} кіляджоўляў",
                                other = "{0} кіляджоўля",
                            )
                        ),
                    )
                ),
            )
        override val energyJoule: MultiLengthGenderedPattern
            get() = TODO("Joule formatter is not implemented yet for be")
        override val energyKilowattHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кіляват-гадзіна",
                                few = "{0} кіляват-гадзіны",
                                many = "{0} кіляват-гадзінаў",
                                other = "{0} кіляват-гадзіны",
                            )
                        ),
                    )
                ),
            )
        override val energyElectronvolt: MultiLengthGenderedPattern
            get() = TODO("Electronvolt formatter is not implemented yet for be")
        override val energyBritishThermalUnit
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} БЦА",
                                few = "{0} БЦА",
                                many = "{0} БЦА",
                                other = "{0} БЦА",
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
                                one = "{0} амэрыканскі тэрм",
                                few = "{0} амэрыканскія тэрмы",
                                many = "{0} амэрыканскіх тэрмаў",
                                other = "{0} амэрыканскага тэрма",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} амэр. тэрм",
                                few = "{0} амэр. тэрмы",
                                many = "{0} амэр. тэрмаў",
                                other = "{0} амэр. тэрма",
                            )
                        ),
                    )
                ),
            )
        override val energyKilogramSquareMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerSquareSecond formatter is not implemented yet for be")
        override val forcePoundForce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фунт-сіла",
                                few = "{0} фунт-сілы",
                                many = "{0} фунт-сілаў",
                                other = "{0} фунт-сілы",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фунт-сіла",
                                few = "{0} фунт-сілы",
                                many = "{0} lbf",
                                other = "{0} lbf",
                            )
                        ),
                    )
                ),
            )
        override val forceNewton: MultiLengthGenderedPattern
            get() = TODO("Newton formatter is not implemented yet for be")
        override val forceKilowattHourPer100Kilometer: MultiLengthGenderedPattern
            get() = TODO("KilowattHourPer100Kilometer formatter is not implemented yet for be")
        override val forceKilogramMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramMeterPerSquareSecond formatter is not implemented yet for be")
        override val frequencyGigahertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гігагэрц",
                                few = "{0} гігагэрцы",
                                many = "{0} гігагэрц",
                                other = "{0} гігагэрца",
                            )
                        ),
                    )
                ),
            )
        override val frequencyMegahertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мэгагэрц",
                                few = "{0} мэгагэрцы",
                                many = "{0} мэгагэрц",
                                other = "{0} мэгагэрца",
                            )
                        ),
                    )
                ),
            )
        override val frequencyKilohertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кілягэрц",
                                few = "{0} кілягэрцы",
                                many = "{0} кілягэрц",
                                other = "{0} кілягэрца",
                            )
                        ),
                    )
                ),
            )
        override val frequencyHertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гэрц",
                                few = "{0} гэрцы",
                                many = "{0} гэрца",
                                other = "{0} гэрцы",
                            )
                        ),
                    )
                ),
            )
        override val frequencyRevolutionPerSecond: MultiLengthGenderedPattern
            get() = TODO("RevolutionPerSecond formatter is not implemented yet for be")
        override val graphicsEm: MultiLengthGenderedPattern
            get() = TODO("Em formatter is not implemented yet for be")
        override val graphicsPixel
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} піксэль",
                                few = "{0} піксэлі",
                                many = "{0} піксэляў",
                                other = "{0} піксэля",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} піксэль",
                                few = "{0} піксэлі",
                                many = "{0} піксэляў",
                                other = "{0} піксэля",
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
                                one = "{0} мэгапіксэль",
                                few = "{0} мэгапіксэль",
                                many = "{0} мэгапіксэль",
                                other = "{0} мэгапіксэля",
                            )
                        ),
                    )
                ),
            )
        override val graphicsDot: MultiLengthGenderedPattern
            get() = TODO("Dot formatter is not implemented yet for be")
        override val graphicsPixelPerCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} піксэль на сантымэтар",
                                few = "{0} піксэлі на сантымэтар",
                                many = "{0} піксэляў на сантымэтар",
                                other = "{0} піксэля на сантымэтар",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} піксэль/см",
                                few = "{0} піксэлі/см",
                                many = "{0} піксэляў/см",
                                other = "{0} піксэля/см",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} піксэль/см",
                                few = "{0} піксэлі/см",
                                many = "{0} піксэляў/см",
                                other = "{0} піксэля/см",
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
                                one = "{0} піксэль на цалю",
                                few = "{0} піксэлі на цалю",
                                many = "{0} піксэляў на цалю",
                                other = "{0} піксэля на цалю",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} піксэль/цалю",
                                few = "{0} піксэлі/цалю",
                                many = "{0} піксэляў/цалю",
                                other = "{0} піксэля/цалю",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} піксэль/цалю",
                                few = "{0} піксэлі/цалю",
                                many = "{0} піксэляў/цалю",
                                other = "{0} піксэля/цалю",
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
                                one = "{0} кропка на сантымэтар",
                                few = "{0} кропкі на сантымэтар",
                                many = "{0} кропак на сантымэтар",
                                other = "{0} кропкі на сантымэтар",
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
                                one = "{0} dpi",
                                few = "{0} dpi",
                                many = "{0} dpi",
                                other = "{0} dpi",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixelPerMeter: MultiLengthGenderedPattern
            get() = TODO("PixelPerMeter formatter is not implemented yet for be")
        override val lengthEarthRadius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} радыюс Зямлі",
                                few = "{0} R⊕",
                                many = "{0} R⊕",
                                other = "{0} радыюса Зямлі",
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
                                one = "{0} кілямэтар",
                                few = "{0} кілямэтры",
                                many = "{0} кілямэтраў",
                                other = "{0} кілямэтра",
                            )
                        ),
                    )
                ),
            )
        override val lengthMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мэтар",
                                few = "{0} мэтры",
                                many = "{0} мэтраў",
                                other = "{0} мэтра",
                            )
                        ),
                    )
                ),
            )
        override val lengthDecimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дэцымэтар",
                                few = "{0} дэцымэтры",
                                many = "{0} дэцымэтраў",
                                other = "{0} дэцымэтра",
                            )
                        ),
                    )
                ),
            )
        override val lengthCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} сантымэтар",
                                few = "{0} сантымэтры",
                                many = "{0} сантымэтраў",
                                other = "{0} сантымэтра",
                            )
                        ),
                    )
                ),
            )
        override val lengthMillimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мілімэтар",
                                few = "{0} мілімэтры",
                                many = "{0} мілімэтраў",
                                other = "{0} мілімэтра",
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
                                one = "{0} мікрамэтар",
                                few = "{0} мікрамэтры",
                                many = "{0} мікрамэтраў",
                                other = "{0} мікрамэтра",
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
                                one = "{0} нанамэтар",
                                few = "{0} нанамэтры",
                                many = "{0} нанамэтраў",
                                other = "{0} нанамэтра",
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
                                one = "{0} пікамэтар",
                                few = "{0} пікамэтры",
                                many = "{0} пікамэтраў",
                                other = "{0} пікамэтра",
                            )
                        ),
                    )
                ),
            )
        override val lengthMile: MultiLengthGenderedPattern
            get() = TODO("Mile formatter is not implemented yet for be")
        override val lengthYard: MultiLengthGenderedPattern
            get() = TODO("Yard formatter is not implemented yet for be")
        override val lengthFoot
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фут",
                                few = "{0} футы",
                                many = "{0} футаў",
                                other = "{0} фута",
                            )
                        ),
                    )
                ),
            )
        override val lengthInch
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} цаля",
                                few = "{0} цалі",
                                many = "{0} цаляў",
                                other = "{0} цалі",
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
                                one = "{0} парсэк",
                                few = "{0} парсэкі",
                                many = "{0} парсэкаў",
                                other = "{0} парсэка",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} пс",
                                few = "{0} пс",
                                many = "{0} пс",
                                other = "{0} пс",
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
                                one = "{0} сьветлавы год",
                                few = "{0} сьветлавыя гады",
                                many = "{0} сьветлавых гадоў",
                                other = "{0} сьветлавога года",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} св. г.",
                                few = "{0} св. г.",
                                many = "{0} св. г.",
                                other = "{0} св. г.",
                            )
                        ),
                    )
                ),
            )
        override val lengthAstronomicalUnit: MultiLengthGenderedPattern
            get() = TODO("AstronomicalUnit formatter is not implemented yet for be")
        override val lengthFurlong
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фур.",
                                few = "{0} фур.",
                                many = "{0} фур.",
                                other = "{0} фур.",
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
                                one = "{0} фат.",
                                few = "{0} фат.",
                                many = "{0} фат.",
                                other = "{0} фат.",
                            )
                        ),
                    )
                ),
            )
        override val lengthNauticalMile: MultiLengthGenderedPattern
            get() = TODO("NauticalMile formatter is not implemented yet for be")
        override val lengthMileScandinavian: MultiLengthGenderedPattern
            get() = TODO("MileScandinavian formatter is not implemented yet for be")
        override val lengthPoint
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} пункт",
                                few = "{0} пункты",
                                many = "{0} пунктаў",
                                other = "{0} пункту",
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
                                one = "{0} сонечны радыюс",
                                few = "{0} сонечныя радыюсы",
                                many = "{0} сонечных радыюсаў",
                                other = "{0} сонечнага радыюса",
                            )
                        ),
                    )
                ),
            )
        override val lightLux: MultiLengthGenderedPattern
            get() = TODO("Lux formatter is not implemented yet for be")
        override val lightCandelaPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaPerSquareMeter formatter is not implemented yet for be")
        override val lightCandela: MultiLengthGenderedPattern
            get() = TODO("Candela formatter is not implemented yet for be")
        override val lightLumen
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} люмэн",
                                few = "{0} люмэны",
                                many = "{0} люмэнаў",
                                other = "{0} люмэна",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaSquareMeterPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaSquareMeterPerSquareMeter formatter is not implemented yet for be")
        override val lightSolarLuminosity
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} сонечная сьвяцільнасьць",
                                few = "{0} сонечныя сьвяцільнасьці",
                                many = "{0} сонечных сьвяцільнасьцяў",
                                other = "{0} сонечнай сьвяцільнасьці",
                            )
                        ),
                    )
                ),
            )
        override val massTonne
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} тона",
                                few = "{0} тоны",
                                many = "{0} тонаў",
                                other = "{0} тоны",
                            )
                        ),
                    )
                ),
            )
        override val massKilogram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кг",
                                few = "{0} кг",
                                many = "{0} кг",
                                other = "{0} кг",
                            )
                        ),
                    )
                ),
            )
        override val massGram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} г",
                                few = "{0} г",
                                many = "{0} г",
                                other = "{0} г",
                            )
                        ),
                    )
                ),
            )
        override val massMilligram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мг",
                                few = "{0} мг",
                                many = "{0} мг",
                                other = "{0} мг",
                            )
                        ),
                    )
                ),
            )
        override val massMicrogram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мкг",
                                few = "{0} мкг",
                                many = "{0} мкг",
                                other = "{0} мкг",
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
                                one = "{0} амэрыканская тона",
                                few = "{0} амэрыканскія тоны",
                                many = "{0} амэрыканскіх тонаў",
                                other = "{0} амэрыканскай тоны",
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
                                one = "{0} стоўн",
                                few = "{0} стоны",
                                many = "{0} стонаў",
                                other = "{0} стона",
                            )
                        ),
                    )
                ),
            )
        override val massPound: MultiLengthGenderedPattern
            get() = TODO("Pound formatter is not implemented yet for be")
        override val massOunce: MultiLengthGenderedPattern
            get() = TODO("Ounce formatter is not implemented yet for be")
        override val massOunceTroy: MultiLengthGenderedPattern
            get() = TODO("OunceTroy formatter is not implemented yet for be")
        override val massCarat: MultiLengthGenderedPattern
            get() = TODO("Carat formatter is not implemented yet for be")
        override val massDalton: MultiLengthGenderedPattern
            get() = TODO("Dalton formatter is not implemented yet for be")
        override val massEarthMass: MultiLengthGenderedPattern
            get() = TODO("EarthMass formatter is not implemented yet for be")
        override val massSolarMass: MultiLengthGenderedPattern
            get() = TODO("SolarMass formatter is not implemented yet for be")
        override val massGrain: MultiLengthGenderedPattern
            get() = TODO("Grain formatter is not implemented yet for be")
        override val powerGigawatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гігават",
                                few = "{0} гігаваты",
                                many = "{0} гігаватаў",
                                other = "{0} гігавата",
                            )
                        ),
                    )
                ),
            )
        override val powerMegawatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мэгават",
                                few = "{0} мэгаваты",
                                many = "{0} мэгаватаў",
                                other = "{0} мэгавата",
                            )
                        ),
                    )
                ),
            )
        override val powerKilowatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кіляват",
                                few = "{0} кіляваты",
                                many = "{0} кіляватаў",
                                other = "{0} кіляватаў",
                            )
                        ),
                    )
                ),
            )
        override val powerWatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ват",
                                few = "{0} ваты",
                                many = "{0} ватаў",
                                other = "{0} вата",
                            )
                        ),
                    )
                ),
            )
        override val powerMilliwatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} міліват",
                                few = "{0} міліваты",
                                many = "{0} міліватаў",
                                other = "{0} мілівата",
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
                                one = "{0} конская сіла",
                                few = "{0} конскія сілы",
                                many = "{0} конскіх сілаў",
                                other = "{0} конскай сілы",
                            )
                        ),
                    )
                ),
            )
        override val powerKilogramSquareMeterPerCubicSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecond formatter is not implemented yet for be")
        override val pressureMillimeterOfhg
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мілімэтар ртутнага слупа",
                                few = "{0} мілімэтры ртутнага слупа",
                                many = "{0} мілімэтраў ртутнага слупа",
                                other = "{0} мілімэтра ртутнага слупа",
                            )
                        ),
                    )
                ),
            )
        override val pressurePoundForcePerSquareInch: MultiLengthGenderedPattern
            get() = TODO("PoundForcePerSquareInch formatter is not implemented yet for be")
        override val pressureInchOfhg: MultiLengthGenderedPattern
            get() = TODO("InchOfhg formatter is not implemented yet for be")
        override val pressureBar: MultiLengthGenderedPattern
            get() = TODO("Bar formatter is not implemented yet for be")
        override val pressureMillibar
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мілібар",
                                few = "{0} мілібары",
                                many = "{0} мілібараў",
                                other = "{0} мілібара",
                            )
                        ),
                    )
                ),
            )
        override val pressureAtmosphere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} атмасфэра",
                                few = "{0} атм",
                                many = "{0} атмасфэраў",
                                other = "{0} атмасфэры",
                            )
                        ),
                    )
                ),
            )
        override val pressurePascal: MultiLengthGenderedPattern
            get() = TODO("Pascal formatter is not implemented yet for be")
        override val pressureHectopascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гэктапаскаль",
                                few = "{0} гэктапаскалі",
                                many = "{0} гэктапаскаляў",
                                other = "{0} гэктапаскаля",
                            )
                        ),
                    )
                ),
            )
        override val pressureKilopascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кіляпаскаль",
                                few = "{0} кіляпаскалі",
                                many = "{0} кіляпаскаляў",
                                other = "{0} кіляпаскаля",
                            )
                        ),
                    )
                ),
            )
        override val pressureMegapascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мэгапаскаль",
                                few = "{0} мэгапаскалі",
                                many = "{0} мэгапаскаляў",
                                other = "{0} мэгапаскаля",
                            )
                        ),
                    )
                ),
            )
        override val pressureGasolineEnergyDensity: MultiLengthGenderedPattern
            get() = TODO("GasolineEnergyDensity formatter is not implemented yet for be")
        override val pressureKilogramPerMeterSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramPerMeterSquareSecond formatter is not implemented yet for be")
        override val speedKilometerPerHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} км/гадз",
                                few = "{0} км/гадз",
                                many = "{0} км/гадз",
                                other = "{0} км/гадз",
                            )
                        ),
                    )
                ),
            )
        override val speedMeterPerSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} м/с",
                                few = "{0} м/с",
                                many = "{0} м/с",
                                other = "{0} м/с",
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
                                one = "{0} міля/гадз",
                                few = "{0} мілі/гадз",
                                many = "{0} міль/гадз",
                                other = "{0} мілі/гадз",
                            )
                        ),
                    )
                ),
            )
        override val speedKnot: MultiLengthGenderedPattern
            get() = TODO("Knot formatter is not implemented yet for be")
        override val speedBeaufort: MultiLengthGenderedPattern
            get() = TODO("Beaufort formatter is not implemented yet for be")
        override val speedLightSpeed: MultiLengthGenderedPattern
            get() = TODO("LightSpeed formatter is not implemented yet for be")
        override val temperatureGeneric: MultiLengthGenderedPattern
            get() = TODO("Generic formatter is not implemented yet for be")
        override val temperatureCelsius: MultiLengthGenderedPattern
            get() = TODO("Celsius formatter is not implemented yet for be")
        override val temperatureFahrenheit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} градус Фарэнгайта",
                                few = "{0} градусы Фарэнгайта",
                                many = "{0} градусаў Фарэнгайта",
                                other = "{0} градуса Фарэнгайта",
                            )
                        ),
                    )
                ),
            )
        override val temperatureKelvin: MultiLengthGenderedPattern
            get() = TODO("Kelvin formatter is not implemented yet for be")
        override val torquePoundForceFoot: MultiLengthGenderedPattern
            get() = TODO("PoundForceFoot formatter is not implemented yet for be")
        override val torqueNewtonMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ньютан-мэтар",
                                few = "{0} ньютан-мэтры",
                                many = "{0} ньютан-мэтраў",
                                other = "{0} ньютан-мэтра",
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
                                one = "{0} кубічны кілямэтар",
                                few = "{0} кубічныя кілямэтры",
                                many = "{0} кубічных кілямэтраў",
                                other = "{0} кубічнага кілямэтра",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кубічны мэтар",
                                few = "{0} кубічныя мэтры",
                                many = "{0} кубічных мэтраў",
                                other = "{0} кубічнага мэтра",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кубічны сантымэтар",
                                few = "{0} кубічныя сантымэтры",
                                many = "{0} кубічных сантымэтраў",
                                other = "{0} кубічнага сантымэтра",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicMile: MultiLengthGenderedPattern
            get() = TODO("CubicMile formatter is not implemented yet for be")
        override val volumeCubicYard: MultiLengthGenderedPattern
            get() = TODO("CubicYard formatter is not implemented yet for be")
        override val volumeCubicFoot: MultiLengthGenderedPattern
            get() = TODO("CubicFoot formatter is not implemented yet for be")
        override val volumeCubicInch: MultiLengthGenderedPattern
            get() = TODO("CubicInch formatter is not implemented yet for be")
        override val volumeMegaliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мэгалітар",
                                few = "{0} мэгалітары",
                                many = "{0} мэгалітараў",
                                other = "{0} мэгалітара",
                            )
                        ),
                    )
                ),
            )
        override val volumeHectoliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гэкталітар",
                                few = "{0} гэкталітры",
                                many = "{0} гэкталітраў",
                                other = "{0} гэкталітру",
                            )
                        ),
                    )
                ),
            )
        override val volumeLiter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} літар",
                                few = "{0} літры",
                                many = "{0} літраў",
                                other = "{0} літру",
                            )
                        ),
                    )
                ),
            )
        override val volumeDeciliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дэцылітар",
                                few = "{0} дэцылітры",
                                many = "{0} дэцылітраў",
                                other = "{0} дэцылітру",
                            )
                        ),
                    )
                ),
            )
        override val volumeCentiliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} сантылітар",
                                few = "{0} сантылітары",
                                many = "{0} сантылітраў",
                                other = "{0} сантылітру",
                            )
                        ),
                    )
                ),
            )
        override val volumeMilliliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мілілітар",
                                few = "{0} мілілітары",
                                many = "{0} мілілітараў",
                                other = "{0} мілілітара",
                            )
                        ),
                    )
                ),
            )
        override val volumePintMetric
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мэтрычная пінта",
                                few = "{0} мэтрычныя пінты",
                                many = "{0} мэтрычных пінтаў",
                                other = "{0} мэтрычнай пінты",
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
                                one = "{0} мэтрычны кубак",
                                few = "{0} мэтрычныя кубкі",
                                many = "{0} мэтрычных кубкаў",
                                other = "{0} мэтрычнага кубка",
                            )
                        ),
                    )
                ),
            )
        override val volumeAcreFoot: MultiLengthGenderedPattern
            get() = TODO("AcreFoot formatter is not implemented yet for be")
        override val volumeBushel
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} буш.",
                                few = "{0} буш.",
                                many = "{0} буш.",
                                other = "{0} буш.",
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
                                one = "{0} галён",
                                few = "{0} галёны",
                                many = "{0} галёнаў",
                                other = "{0} галёна",
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
                                one = "{0} імп. галён",
                                few = "{0} імп. галёны",
                                many = "{0} імп. галёнаў",
                                other = "{0} імп. галёна",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} імп. гал.",
                                few = "{0} імп. гал.",
                                many = "{0} імп. гал.",
                                other = "{0} імп. гал.",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuart: MultiLengthGenderedPattern
            get() = TODO("Quart formatter is not implemented yet for be")
        override val volumePint: MultiLengthGenderedPattern
            get() = TODO("Pint formatter is not implemented yet for be")
        override val volumeCup: MultiLengthGenderedPattern
            get() = TODO("Cup formatter is not implemented yet for be")
        override val volumeFluidOunce: MultiLengthGenderedPattern
            get() = TODO("FluidOunce formatter is not implemented yet for be")
        override val volumeFluidOunceImperial: MultiLengthGenderedPattern
            get() = TODO("FluidOunceImperial formatter is not implemented yet for be")
        override val volumeTablespoon: MultiLengthGenderedPattern
            get() = TODO("Tablespoon formatter is not implemented yet for be")
        override val volumeTeaspoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гарбатная лыжка",
                                few = "{0} гарбатныя лыжкі",
                                many = "{0} гарбатных лыжак",
                                other = "{0} гарбатнай лыжкі",
                            )
                        ),
                    )
                ),
            )
        override val volumeBarrel: MultiLengthGenderedPattern
            get() = TODO("Barrel formatter is not implemented yet for be")
        override val volumeDessertSpoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дэсэртная лыжка",
                                few = "{0} дэсэртныя лыжкі",
                                many = "{0} дэсэртных лыжак",
                                other = "{0} дэсэртнай лыжкі",
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
                                one = "{0} брытанская дэсэртная лыжка",
                                few = "{0} брыт. дэс. л.",
                                many = "{0} брыт. дэс. л.",
                                other = "{0} брытанскай дэсэртнай лыжкі",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} брыт. дэс. л.",
                                few = "{0} брыт. дэс. л.",
                                many = "{0} брыт. дэс. л.",
                                other = "{0} брыт. дэс. л.",
                            )
                        ),
                    )
                ),
            )
        override val volumeDrop
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кропля",
                                few = "{0} кроплі",
                                many = "{0} кропляў",
                                other = "{0} кроплі",
                            )
                        ),
                    )
                ),
            )
        override val volumeDram: MultiLengthGenderedPattern
            get() = TODO("Dram formatter is not implemented yet for be")
        override val volumeJigger
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} джыгэр",
                                few = "{0} джыгэры",
                                many = "{0} джыгэраў",
                                other = "{0} джыгэра",
                            )
                        ),
                    )
                ),
            )
        override val volumePinch: MultiLengthGenderedPattern
            get() = TODO("Pinch formatter is not implemented yet for be")
        override val volumeQuartImperial: MultiLengthGenderedPattern
            get() = TODO("QuartImperial formatter is not implemented yet for be")
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    open class NumberLanguage internal constructor() : info.leonenko.i18n.NumberLanguage {
        override val symbols: NumberSymbols
            get() = LanguageRoot().numbers.symbols
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
