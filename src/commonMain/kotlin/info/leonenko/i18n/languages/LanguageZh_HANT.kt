package info.leonenko.i18n.languages

import info.leonenko.i18n.*

val Language.Companion.zh_HANT
    get() = languageZh_HANT
private val languageZh_HANT = LanguageZh_HANT()
open class LanguageZh_HANT internal constructor() : LanguageZh() {
    override val code: String = "zh#Hant"
    override val script: String = "Hant"
    override val territory: String? = null

    open class EllipsisLanguage internal constructor() : LanguageZh.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    open class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageZh.ListLanguage(ellipsis) {
        override val formatter: ListFormatter = MultiLengthPatternedListFormatter(
            long = PatternedListFormatter(start = "{0}、{1}", middle = "{0}、{1}", end = "{0}和{1}", two = "{0}和{1}", ellipsis = ellipsis.wordFormatter),
        )
        override val orFormatter: ListFormatter =
            MultiLengthPatternedListFormatter(long = PatternedListFormatter(start = "{0}、{1}", middle = "{0}、{1}", end = "{0}或{1}", two = "{0}或{1}", ellipsis = ellipsis.wordFormatter))
        override val unitFormatter: ListFormatter = MultiLengthPatternedListFormatter(
            narrow = PatternedListFormatter(start = "{0}{1}", middle = "{0}{1}", end = "{0}{1}", two = "{0}{1}", ellipsis = ellipsis.wordFormatter),
            short = PatternedListFormatter(start = "{0} {1}", middle = "{0} {1}", end = "{0} {1}", two = "{0} {1}", ellipsis = ellipsis.wordFormatter),
        )
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    open class MeasurementUnitLanguage internal constructor() : LanguageZh.MeasurementUnitLanguage() {
        override val accelerationGForce
            get() = MultiLengthGenderedPattern(
                base = super.accelerationGForce,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} G 力",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}G",
                            )
                        ),
                    )
                ),
            )
        override val accelerationMeterPerSquareSecond
            get() = MultiLengthGenderedPattern(
                base = super.accelerationMeterPerSquareSecond,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "每平方秒 {0} 公尺",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公尺/平方秒",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公尺/平方秒",
                            )
                        ),
                    )
                ),
            )
        override val angleRevolution
            get() = MultiLengthGenderedPattern(
                base = super.angleRevolution,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 圈",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}圈",
                            )
                        ),
                    )
                ),
            )
        override val angleRadian
            get() = MultiLengthGenderedPattern(
                base = super.angleRadian,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 弧度",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}弧度",
                            )
                        ),
                    )
                ),
            )
        override val angleDegree
            get() = MultiLengthGenderedPattern(
                base = super.angleDegree,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 度",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}°",
                            )
                        ),
                    )
                ),
            )
        override val angleArcMinute
            get() = MultiLengthGenderedPattern(
                base = super.angleArcMinute,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 角分",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}角分",
                            )
                        ),
                    )
                ),
            )
        override val angleArcSecond
            get() = MultiLengthGenderedPattern(
                base = super.angleArcSecond,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 角秒",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}角秒",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareKilometer
            get() = MultiLengthGenderedPattern(
                base = super.areaSquareKilometer,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 平方公里",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}km²",
                            )
                        ),
                    )
                ),
            )
        override val areaHectare
            get() = MultiLengthGenderedPattern(
                base = super.areaHectare,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公頃",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公頃",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareMeter
            get() = MultiLengthGenderedPattern(
                base = super.areaSquareMeter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 平方公尺",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}m²",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareCentimeter
            get() = MultiLengthGenderedPattern(
                base = super.areaSquareCentimeter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 平方公分",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}cm²",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareMile
            get() = MultiLengthGenderedPattern(
                base = super.areaSquareMile,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 平方英里",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}平方英里",
                            )
                        ),
                    )
                ),
            )
        override val areaAcre
            get() = MultiLengthGenderedPattern(
                base = super.areaAcre,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英畝",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}英畝",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareYard
            get() = MultiLengthGenderedPattern(
                base = super.areaSquareYard,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 平方碼",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}yd²",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareFoot
            get() = MultiLengthGenderedPattern(
                base = super.areaSquareFoot,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 平方英尺",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}平方英尺",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareInch
            get() = MultiLengthGenderedPattern(
                base = super.areaSquareInch,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 平方英寸",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}平方英寸",
                            )
                        ),
                    )
                ),
            )
        override val areaDunam
            get() = MultiLengthGenderedPattern(
                base = super.areaDunam,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 杜納畝",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}杜納畝",
                            )
                        ),
                    )
                ),
            )
        override val concentrationKarat
            get() = MultiLengthGenderedPattern(
                base = super.concentrationKarat,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 克拉",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}克拉",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermillion
            get() = MultiLengthGenderedPattern(
                base = super.concentrationPermillion,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 百萬分率",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}百萬分率",
                            )
                        ),
                    )
                ),
            )
        override val concentrationMilligramOfglucosePerDeciliter
            get() = MultiLengthGenderedPattern(
                base = super.concentrationMilligramOfglucosePerDeciliter,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "每分升 {0} 毫克",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 毫克/分升",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}毫克/分升",
                            )
                        ),
                    )
                ),
            )
        override val concentrationMillimolePerLiter
            get() = MultiLengthGenderedPattern(
                base = super.concentrationMillimolePerLiter,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "每公升 {0} 毫莫耳",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 毫莫耳/公升",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}毫莫耳/公升",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPortionPer1e9
            get() = MultiLengthGenderedPattern(
                base = super.concentrationPortionPer1e9,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 十億分點濃度",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}ppb",
                            )
                        ),
                    )
                ),
            )
        override val concentrationItem
            get() = MultiLengthGenderedPattern(
                base = super.concentrationItem,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 個項目",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}個項目",
                            )
                        ),
                    )
                ),
            )
        override val concentrationMole
            get() = MultiLengthGenderedPattern(
                base = super.concentrationMole,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 莫耳",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}莫耳",
                            )
                        ),
                    )
                ),
            )
        override val consumptionLiterPerKilometer
            get() = MultiLengthGenderedPattern(
                base = super.consumptionLiterPerKilometer,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "每公里 {0} 公升",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 升/公里",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}升/公里",
                            )
                        ),
                    )
                ),
            )
        override val consumptionLiterPer100Kilometer
            get() = MultiLengthGenderedPattern(
                base = super.consumptionLiterPer100Kilometer,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "每 100 公里 {0} 公升",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 升/100 公里",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}升/100公里",
                            )
                        ),
                    )
                ),
            )
        override val consumptionMilePerGallon
            get() = MultiLengthGenderedPattern(
                base = super.consumptionMilePerGallon,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "每加侖 {0} 英里",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英里/加侖",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}英里/加侖",
                            )
                        ),
                    )
                ),
            )
        override val consumptionMilePerGallonImperial
            get() = MultiLengthGenderedPattern(
                base = super.consumptionMilePerGallonImperial,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "每英制加侖 {0} 英里",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英里/英制加侖",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}英里/英制加侖",
                            )
                        ),
                    )
                ),
            )
        override val digitalPetabyte
            get() = MultiLengthGenderedPattern(
                base = super.digitalPetabyte,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}PB",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabyte
            get() = MultiLengthGenderedPattern(
                base = super.digitalTerabyte,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}TB",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabit
            get() = MultiLengthGenderedPattern(
                base = super.digitalTerabit,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}Tb",
                            )
                        ),
                    )
                ),
            )
        override val digitalGigabyte
            get() = MultiLengthGenderedPattern(
                base = super.digitalGigabyte,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}GB",
                            )
                        ),
                    )
                ),
            )
        override val digitalGigabit
            get() = MultiLengthGenderedPattern(
                base = super.digitalGigabit,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}Gb",
                            )
                        ),
                    )
                ),
            )
        override val digitalMegabyte
            get() = MultiLengthGenderedPattern(
                base = super.digitalMegabyte,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}MB",
                            )
                        ),
                    )
                ),
            )
        override val digitalMegabit
            get() = MultiLengthGenderedPattern(
                base = super.digitalMegabit,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}Mb",
                            )
                        ),
                    )
                ),
            )
        override val digitalKilobyte
            get() = MultiLengthGenderedPattern(
                base = super.digitalKilobyte,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}kB",
                            )
                        ),
                    )
                ),
            )
        override val digitalKilobit
            get() = MultiLengthGenderedPattern(
                base = super.digitalKilobit,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}kb",
                            )
                        ),
                    )
                ),
            )
        override val digitalByteUnit
            get() = MultiLengthGenderedPattern(
                base = super.digitalByteUnit,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}byte",
                            )
                        ),
                    )
                ),
            )
        override val digitalBit
            get() = MultiLengthGenderedPattern(
                base = super.digitalBit,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}bit",
                            )
                        ),
                    )
                ),
            )
        override val durationCentury
            get() = MultiLengthGenderedPattern(
                base = super.durationCentury,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 個世紀",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 世紀",
                            )
                        ),
                    )
                ),
            )
        override val durationDecade
            get() = MultiLengthGenderedPattern(
                base = super.durationDecade,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}0 年",
                            )
                        ),
                    )
                ),
            )
        override val durationYear
            get() = MultiLengthGenderedPattern(
                base = super.durationYear,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 年",
                            )
                        ),
                    )
                ),
            )
        override val durationQuarter
            get() = MultiLengthGenderedPattern(
                base = super.durationQuarter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 刻",
                            )
                        ),
                    )
                ),
            )
        override val durationMonth
            get() = MultiLengthGenderedPattern(
                base = super.durationMonth,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 個月",
                            )
                        ),
                    )
                ),
            )
        override val durationWeek
            get() = MultiLengthGenderedPattern(
                base = super.durationWeek,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 週",
                            )
                        ),
                    )
                ),
            )
        override val durationDay
            get() = MultiLengthGenderedPattern(
                base = super.durationDay,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 天",
                            )
                        ),
                    )
                ),
            )
        override val durationHour
            get() = MultiLengthGenderedPattern(
                base = super.durationHour,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 小時",
                            )
                        ),
                    )
                ),
            )
        override val durationMinute
            get() = MultiLengthGenderedPattern(
                base = super.durationMinute,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 分鐘",
                            )
                        ),
                    )
                ),
            )
        override val durationSecond
            get() = MultiLengthGenderedPattern(
                base = super.durationSecond,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 秒",
                            )
                        ),
                    )
                ),
            )
        override val durationMillisecond
            get() = MultiLengthGenderedPattern(
                base = super.durationMillisecond,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 毫秒",
                            )
                        ),
                    )
                ),
            )
        override val durationMicrosecond
            get() = MultiLengthGenderedPattern(
                base = super.durationMicrosecond,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 微秒",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}μs",
                            )
                        ),
                    )
                ),
            )
        override val durationNanosecond
            get() = MultiLengthGenderedPattern(
                base = super.durationNanosecond,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 奈秒",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}ns",
                            )
                        ),
                    )
                ),
            )
        override val durationNight
            get() = MultiLengthGenderedPattern(
                base = super.durationNight,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 夜",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 夜",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}夜",
                            )
                        ),
                    )
                ),
            )
        override val electricAmpere
            get() = MultiLengthGenderedPattern(
                base = super.electricAmpere,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 安培",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}安培",
                            )
                        ),
                    )
                ),
            )
        override val electricMilliampere
            get() = MultiLengthGenderedPattern(
                base = super.electricMilliampere,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 毫安培",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}毫安培",
                            )
                        ),
                    )
                ),
            )
        override val electricOhm
            get() = MultiLengthGenderedPattern(
                base = super.electricOhm,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 歐姆",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}歐姆",
                            )
                        ),
                    )
                ),
            )
        override val electricVolt
            get() = MultiLengthGenderedPattern(
                base = super.electricVolt,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 伏特",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}伏特",
                            )
                        ),
                    )
                ),
            )
        override val energyKilocalorie
            get() = MultiLengthGenderedPattern(
                base = super.energyKilocalorie,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 千卡路里",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 千卡",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}千卡",
                            )
                        ),
                    )
                ),
            )
        override val energyCalorie
            get() = MultiLengthGenderedPattern(
                base = super.energyCalorie,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 卡路里",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 卡",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}卡",
                            )
                        ),
                    )
                ),
            )
        override val energyFoodcalorie
            get() = MultiLengthGenderedPattern(
                base = super.energyFoodcalorie,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 大卡",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}大卡",
                            )
                        ),
                    )
                ),
            )
        override val energyKilojoule
            get() = MultiLengthGenderedPattern(
                base = super.energyKilojoule,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 千焦耳",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 千焦",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}千焦耳",
                            )
                        ),
                    )
                ),
            )
        override val energyJoule
            get() = MultiLengthGenderedPattern(
                base = super.energyJoule,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 焦耳",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 焦",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}焦",
                            )
                        ),
                    )
                ),
            )
        override val energyKilowattHour
            get() = MultiLengthGenderedPattern(
                base = super.energyKilowattHour,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 千瓦小時",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}千瓦小時",
                            )
                        ),
                    )
                ),
            )
        override val energyElectronvolt
            get() = MultiLengthGenderedPattern(
                base = super.energyElectronvolt,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 電子伏特",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}電子伏特",
                            )
                        ),
                    )
                ),
            )
        override val energyBritishThermalUnit
            get() = MultiLengthGenderedPattern(
                base = super.energyBritishThermalUnit,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英熱單位",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}英熱單位",
                            )
                        ),
                    )
                ),
            )
        override val energyThermUs
            get() = MultiLengthGenderedPattern(
                base = super.energyThermUs,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 美制撒姆",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}美制撒姆",
                            )
                        ),
                    )
                ),
            )
        override val forcePoundForce
            get() = MultiLengthGenderedPattern(
                base = super.forcePoundForce,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 磅力",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}lbf",
                            )
                        ),
                    )
                ),
            )
        override val forceNewton
            get() = MultiLengthGenderedPattern(
                base = super.forceNewton,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 牛頓",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}N",
                            )
                        ),
                    )
                ),
            )
        override val forceKilowattHourPer100Kilometer
            get() = MultiLengthGenderedPattern(
                base = super.forceKilowattHourPer100Kilometer,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}kWh/100km",
                            )
                        ),
                    )
                ),
            )
        override val frequencyGigahertz
            get() = MultiLengthGenderedPattern(
                base = super.frequencyGigahertz,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 吉赫",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}吉赫",
                            )
                        ),
                    )
                ),
            )
        override val frequencyMegahertz
            get() = MultiLengthGenderedPattern(
                base = super.frequencyMegahertz,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 兆赫",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}兆赫",
                            )
                        ),
                    )
                ),
            )
        override val frequencyKilohertz
            get() = MultiLengthGenderedPattern(
                base = super.frequencyKilohertz,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 千赫",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}千赫",
                            )
                        ),
                    )
                ),
            )
        override val frequencyHertz
            get() = MultiLengthGenderedPattern(
                base = super.frequencyHertz,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 赫茲",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}赫茲",
                            )
                        ),
                    )
                ),
            )
        override val graphicsEm
            get() = MultiLengthGenderedPattern(
                base = super.graphicsEm,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}em",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixel
            get() = MultiLengthGenderedPattern(
                base = super.graphicsPixel,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 像素",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}px",
                            )
                        ),
                    )
                ),
            )
        override val graphicsMegapixel
            get() = MultiLengthGenderedPattern(
                base = super.graphicsMegapixel,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 百萬像素",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}MP",
                            )
                        ),
                    )
                ),
            )
        override val graphicsDot
            get() = MultiLengthGenderedPattern(
                base = super.graphicsDot,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}px",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixelPerCentimeter
            get() = MultiLengthGenderedPattern(
                base = super.graphicsPixelPerCentimeter,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}ppcm",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixelPerInch
            get() = MultiLengthGenderedPattern(
                base = super.graphicsPixelPerInch,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}ppi",
                            )
                        ),
                    )
                ),
            )
        override val graphicsDotPerCentimeter
            get() = MultiLengthGenderedPattern(
                base = super.graphicsDotPerCentimeter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} dpcm",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}dpcm",
                            )
                        ),
                    )
                ),
            )
        override val graphicsDotPerInch
            get() = MultiLengthGenderedPattern(
                base = super.graphicsDotPerInch,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} dpi",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}dpi",
                            )
                        ),
                    )
                ),
            )
        override val lengthEarthRadius
            get() = MultiLengthGenderedPattern(
                base = super.lengthEarthRadius,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 地球半徑",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}R⊕",
                            )
                        ),
                    )
                ),
            )
        override val lengthKilometer
            get() = MultiLengthGenderedPattern(
                base = super.lengthKilometer,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公里",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公里",
                            )
                        ),
                    )
                ),
            )
        override val lengthMeter
            get() = MultiLengthGenderedPattern(
                base = super.lengthMeter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公尺",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公尺",
                            )
                        ),
                    )
                ),
            )
        override val lengthDecimeter
            get() = MultiLengthGenderedPattern(
                base = super.lengthDecimeter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公寸",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公寸",
                            )
                        ),
                    )
                ),
            )
        override val lengthCentimeter
            get() = MultiLengthGenderedPattern(
                base = super.lengthCentimeter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公分",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公分",
                            )
                        ),
                    )
                ),
            )
        override val lengthMillimeter
            get() = MultiLengthGenderedPattern(
                base = super.lengthMillimeter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公釐",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公釐",
                            )
                        ),
                    )
                ),
            )
        override val lengthMicrometer
            get() = MultiLengthGenderedPattern(
                base = super.lengthMicrometer,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 微米",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}μm",
                            )
                        ),
                    )
                ),
            )
        override val lengthNanometer
            get() = MultiLengthGenderedPattern(
                base = super.lengthNanometer,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 奈米",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}奈米",
                            )
                        ),
                    )
                ),
            )
        override val lengthPicometer
            get() = MultiLengthGenderedPattern(
                base = super.lengthPicometer,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 皮米",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}皮米",
                            )
                        ),
                    )
                ),
            )
        override val lengthMile
            get() = MultiLengthGenderedPattern(
                base = super.lengthMile,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英里",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}英里",
                            )
                        ),
                    )
                ),
            )
        override val lengthYard
            get() = MultiLengthGenderedPattern(
                base = super.lengthYard,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 碼",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}碼",
                            )
                        ),
                    )
                ),
            )
        override val lengthFoot
            get() = MultiLengthGenderedPattern(
                base = super.lengthFoot,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英尺",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 呎",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}呎",
                            )
                        ),
                    )
                ),
            )
        override val lengthInch
            get() = MultiLengthGenderedPattern(
                base = super.lengthInch,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英寸",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 吋",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}吋",
                            )
                        ),
                    )
                ),
            )
        override val lengthParsec
            get() = MultiLengthGenderedPattern(
                base = super.lengthParsec,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 秒差距",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}秒差距",
                            )
                        ),
                    )
                ),
            )
        override val lengthLightYear
            get() = MultiLengthGenderedPattern(
                base = super.lengthLightYear,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 光年",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}光年",
                            )
                        ),
                    )
                ),
            )
        override val lengthAstronomicalUnit
            get() = MultiLengthGenderedPattern(
                base = super.lengthAstronomicalUnit,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 天文單位",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}天文單位",
                            )
                        ),
                    )
                ),
            )
        override val lengthFurlong
            get() = MultiLengthGenderedPattern(
                base = super.lengthFurlong,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 化朗",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}化朗",
                            )
                        ),
                    )
                ),
            )
        override val lengthFathom
            get() = MultiLengthGenderedPattern(
                base = super.lengthFathom,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英尋",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}fth",
                            )
                        ),
                    )
                ),
            )
        override val lengthNauticalMile
            get() = MultiLengthGenderedPattern(
                base = super.lengthNauticalMile,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 海里",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}nmi",
                            )
                        ),
                    )
                ),
            )
        override val lengthMileScandinavian
            get() = MultiLengthGenderedPattern(
                base = super.lengthMileScandinavian,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 斯堪地那維亞里",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 斯堪地那維亞里",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}smi",
                            )
                        ),
                    )
                ),
            )
        override val lengthPoint
            get() = MultiLengthGenderedPattern(
                base = super.lengthPoint,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 點",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}點",
                            )
                        ),
                    )
                ),
            )
        override val lengthSolarRadius
            get() = MultiLengthGenderedPattern(
                base = super.lengthSolarRadius,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 太陽半徑",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}R☉",
                            )
                        ),
                    )
                ),
            )
        override val lightLux
            get() = MultiLengthGenderedPattern(
                base = super.lightLux,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 勒克斯",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}勒克斯",
                            )
                        ),
                    )
                ),
            )
        override val lightCandela
            get() = MultiLengthGenderedPattern(
                base = super.lightCandela,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 燭光",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}燭光",
                            )
                        ),
                    )
                ),
            )
        override val lightLumen
            get() = MultiLengthGenderedPattern(
                base = super.lightLumen,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 流明",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}流明",
                            )
                        ),
                    )
                ),
            )
        override val lightSolarLuminosity
            get() = MultiLengthGenderedPattern(
                base = super.lightSolarLuminosity,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 太陽光度",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}L☉",
                            )
                        ),
                    )
                ),
            )
        override val massTonne
            get() = MultiLengthGenderedPattern(
                base = super.massTonne,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公噸",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公噸",
                            )
                        ),
                    )
                ),
            )
        override val massKilogram
            get() = MultiLengthGenderedPattern(
                base = super.massKilogram,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公斤",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公斤",
                            )
                        ),
                    )
                ),
            )
        override val massGram
            get() = MultiLengthGenderedPattern(
                base = super.massGram,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 克",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}克",
                            )
                        ),
                    )
                ),
            )
        override val massMilligram
            get() = MultiLengthGenderedPattern(
                base = super.massMilligram,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 毫克",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}毫克",
                            )
                        ),
                    )
                ),
            )
        override val massMicrogram
            get() = MultiLengthGenderedPattern(
                base = super.massMicrogram,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 微克",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}微克",
                            )
                        ),
                    )
                ),
            )
        override val massTon
            get() = MultiLengthGenderedPattern(
                base = super.massTon,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 美噸",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}美噸",
                            )
                        ),
                    )
                ),
            )
        override val massStone
            get() = MultiLengthGenderedPattern(
                base = super.massStone,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英石",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}英石",
                            )
                        ),
                    )
                ),
            )
        override val massPound
            get() = MultiLengthGenderedPattern(
                base = super.massPound,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 磅",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}磅",
                            )
                        ),
                    )
                ),
            )
        override val massOunce
            get() = MultiLengthGenderedPattern(
                base = super.massOunce,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 盎司",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}盎司",
                            )
                        ),
                    )
                ),
            )
        override val massOunceTroy
            get() = MultiLengthGenderedPattern(
                base = super.massOunceTroy,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 金衡盎司",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}金衡盎司",
                            )
                        ),
                    )
                ),
            )
        override val massCarat
            get() = MultiLengthGenderedPattern(
                base = super.massCarat,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 克拉",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}克拉",
                            )
                        ),
                    )
                ),
            )
        override val massDalton
            get() = MultiLengthGenderedPattern(
                base = super.massDalton,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 達爾頓",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}達爾頓",
                            )
                        ),
                    )
                ),
            )
        override val massEarthMass
            get() = MultiLengthGenderedPattern(
                base = super.massEarthMass,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 地球質量",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}地球質量",
                            )
                        ),
                    )
                ),
            )
        override val massSolarMass
            get() = MultiLengthGenderedPattern(
                base = super.massSolarMass,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 太陽質量",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}太陽質量",
                            )
                        ),
                    )
                ),
            )
        override val massGrain
            get() = MultiLengthGenderedPattern(
                base = super.massGrain,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 格林",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}格林",
                            )
                        ),
                    )
                ),
            )
        override val powerGigawatt
            get() = MultiLengthGenderedPattern(
                base = super.powerGigawatt,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 吉瓦",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}吉瓦",
                            )
                        ),
                    )
                ),
            )
        override val powerMegawatt
            get() = MultiLengthGenderedPattern(
                base = super.powerMegawatt,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 百萬瓦特",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 百萬瓦",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}百萬瓦",
                            )
                        ),
                    )
                ),
            )
        override val powerKilowatt
            get() = MultiLengthGenderedPattern(
                base = super.powerKilowatt,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 千瓦特",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 千瓦",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}千瓦",
                            )
                        ),
                    )
                ),
            )
        override val powerWatt
            get() = MultiLengthGenderedPattern(
                base = super.powerWatt,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 瓦特",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 瓦",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}瓦特",
                            )
                        ),
                    )
                ),
            )
        override val powerMilliwatt
            get() = MultiLengthGenderedPattern(
                base = super.powerMilliwatt,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 毫瓦特",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 毫瓦",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}毫瓦",
                            )
                        ),
                    )
                ),
            )
        override val powerHorsepower
            get() = MultiLengthGenderedPattern(
                base = super.powerHorsepower,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 匹馬力",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 匹",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}匹",
                            )
                        ),
                    )
                ),
            )
        override val pressureMillimeterOfhg
            get() = MultiLengthGenderedPattern(
                base = super.pressureMillimeterOfhg,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 毫米汞柱",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}mmHg",
                            )
                        ),
                    )
                ),
            )
        override val pressurePoundForcePerSquareInch
            get() = MultiLengthGenderedPattern(
                base = super.pressurePoundForcePerSquareInch,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "每平方英寸 {0} 磅力",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 磅力/平方英寸",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}psi",
                            )
                        ),
                    )
                ),
            )
        override val pressureInchOfhg
            get() = MultiLengthGenderedPattern(
                base = super.pressureInchOfhg,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英寸汞柱",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}英吋汞柱",
                            )
                        ),
                    )
                ),
            )
        override val pressureBar
            get() = MultiLengthGenderedPattern(
                base = super.pressureBar,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 巴",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}巴",
                            )
                        ),
                    )
                ),
            )
        override val pressureMillibar
            get() = MultiLengthGenderedPattern(
                base = super.pressureMillibar,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 毫巴",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}毫巴",
                            )
                        ),
                    )
                ),
            )
        override val pressureAtmosphere
            get() = MultiLengthGenderedPattern(
                base = super.pressureAtmosphere,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 大氣壓",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}atm",
                            )
                        ),
                    )
                ),
            )
        override val pressurePascal
            get() = MultiLengthGenderedPattern(
                base = super.pressurePascal,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 帕斯卡",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 帕",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}帕",
                            )
                        ),
                    )
                ),
            )
        override val pressureHectopascal
            get() = MultiLengthGenderedPattern(
                base = super.pressureHectopascal,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 百帕",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}百帕",
                            )
                        ),
                    )
                ),
            )
        override val pressureKilopascal
            get() = MultiLengthGenderedPattern(
                base = super.pressureKilopascal,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 千帕",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}千帕",
                            )
                        ),
                    )
                ),
            )
        override val pressureMegapascal
            get() = MultiLengthGenderedPattern(
                base = super.pressureMegapascal,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 兆帕",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}兆帕",
                            )
                        ),
                    )
                ),
            )
        override val speedKilometerPerHour
            get() = MultiLengthGenderedPattern(
                base = super.speedKilometerPerHour,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "每小時 {0} 公里",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公里/小時",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公里/小時",
                            )
                        ),
                    )
                ),
            )
        override val speedMeterPerSecond
            get() = MultiLengthGenderedPattern(
                base = super.speedMeterPerSecond,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "每秒 {0} 公尺",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公尺/秒",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公尺/秒",
                            )
                        ),
                    )
                ),
            )
        override val speedMilePerHour
            get() = MultiLengthGenderedPattern(
                base = super.speedMilePerHour,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "每小時 {0} 英里",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英里/小時",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}英里/小時",
                            )
                        ),
                    )
                ),
            )
        override val speedKnot
            get() = MultiLengthGenderedPattern(
                base = super.speedKnot,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 節",
                            )
                        ),
                    )
                ),
            )
        override val speedBeaufort
            get() = MultiLengthGenderedPattern(
                base = super.speedBeaufort,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "蒲福風級 {0} 級",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "蒲福風級{0}級",
                            )
                        ),
                    )
                ),
            )
        override val temperatureCelsius
            get() = MultiLengthGenderedPattern(
                base = super.temperatureCelsius,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "攝氏 {0} 度",
                            )
                        ),
                    )
                ),
            )
        override val temperatureFahrenheit
            get() = MultiLengthGenderedPattern(
                base = super.temperatureFahrenheit,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "華氏 {0} 度",
                            )
                        ),
                    )
                ),
            )
        override val temperatureKelvin
            get() = MultiLengthGenderedPattern(
                base = super.temperatureKelvin,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 克耳文",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}克耳文",
                            )
                        ),
                    )
                ),
            )
        override val torquePoundForceFoot
            get() = MultiLengthGenderedPattern(
                base = super.torquePoundForceFoot,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 磅英尺",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}磅英尺",
                            )
                        ),
                    )
                ),
            )
        override val torqueNewtonMeter
            get() = MultiLengthGenderedPattern(
                base = super.torqueNewtonMeter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 牛頓米",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}牛頓米",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicKilometer
            get() = MultiLengthGenderedPattern(
                base = super.volumeCubicKilometer,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 立方公里",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}立方公里",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicMeter
            get() = MultiLengthGenderedPattern(
                base = super.volumeCubicMeter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 立方公尺",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}立方公尺",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicCentimeter
            get() = MultiLengthGenderedPattern(
                base = super.volumeCubicCentimeter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 立方公分",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}立方公分",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicMile
            get() = MultiLengthGenderedPattern(
                base = super.volumeCubicMile,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 立方英里",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}立方英里",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicYard
            get() = MultiLengthGenderedPattern(
                base = super.volumeCubicYard,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 立方碼",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}立方碼",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicFoot
            get() = MultiLengthGenderedPattern(
                base = super.volumeCubicFoot,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 立方英尺",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}立方英尺",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicInch
            get() = MultiLengthGenderedPattern(
                base = super.volumeCubicInch,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 立方英寸",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}立方英寸",
                            )
                        ),
                    )
                ),
            )
        override val volumeMegaliter
            get() = MultiLengthGenderedPattern(
                base = super.volumeMegaliter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 兆升",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}兆升",
                            )
                        ),
                    )
                ),
            )
        override val volumeHectoliter
            get() = MultiLengthGenderedPattern(
                base = super.volumeHectoliter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公石",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公石",
                            )
                        ),
                    )
                ),
            )
        override val volumeLiter
            get() = MultiLengthGenderedPattern(
                base = super.volumeLiter,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公升",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 升",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}升",
                            )
                        ),
                    )
                ),
            )
        override val volumeDeciliter
            get() = MultiLengthGenderedPattern(
                base = super.volumeDeciliter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公合",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公合",
                            )
                        ),
                    )
                ),
            )
        override val volumeCentiliter
            get() = MultiLengthGenderedPattern(
                base = super.volumeCentiliter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 釐升",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}釐升",
                            )
                        ),
                    )
                ),
            )
        override val volumeMilliliter
            get() = MultiLengthGenderedPattern(
                base = super.volumeMilliliter,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 毫升",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}毫升",
                            )
                        ),
                    )
                ),
            )
        override val volumePintMetric
            get() = MultiLengthGenderedPattern(
                base = super.volumePintMetric,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公制品脫",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公制品脫",
                            )
                        ),
                    )
                ),
            )
        override val volumeCupMetric
            get() = MultiLengthGenderedPattern(
                base = super.volumeCupMetric,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 公制杯",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}公制杯",
                            )
                        ),
                    )
                ),
            )
        override val volumeAcreFoot
            get() = MultiLengthGenderedPattern(
                base = super.volumeAcreFoot,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英畝英尺",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}英畝英尺",
                            )
                        ),
                    )
                ),
            )
        override val volumeBushel
            get() = MultiLengthGenderedPattern(
                base = super.volumeBushel,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 蒲式耳",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}蒲式耳",
                            )
                        ),
                    )
                ),
            )
        override val volumeGallon
            get() = MultiLengthGenderedPattern(
                base = super.volumeGallon,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 加侖",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}加侖",
                            )
                        ),
                    )
                ),
            )
        override val volumeGallonImperial
            get() = MultiLengthGenderedPattern(
                base = super.volumeGallonImperial,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英制加侖",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}英制加侖",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuart
            get() = MultiLengthGenderedPattern(
                base = super.volumeQuart,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 夸脫",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}夸脫",
                            )
                        ),
                    )
                ),
            )
        override val volumePint
            get() = MultiLengthGenderedPattern(
                base = super.volumePint,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 品脫",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}品脫",
                            )
                        ),
                    )
                ),
            )
        override val volumeCup
            get() = MultiLengthGenderedPattern(
                base = super.volumeCup,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 杯",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}杯",
                            )
                        ),
                    )
                ),
            )
        override val volumeFluidOunce
            get() = MultiLengthGenderedPattern(
                base = super.volumeFluidOunce,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 液盎司",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}液盎司",
                            )
                        ),
                    )
                ),
            )
        override val volumeFluidOunceImperial
            get() = MultiLengthGenderedPattern(
                base = super.volumeFluidOunceImperial,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英液盎司",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}英液盎司",
                            )
                        ),
                    )
                ),
            )
        override val volumeTablespoon
            get() = MultiLengthGenderedPattern(
                base = super.volumeTablespoon,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 湯匙",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}匙",
                            )
                        ),
                    )
                ),
            )
        override val volumeTeaspoon
            get() = MultiLengthGenderedPattern(
                base = super.volumeTeaspoon,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 茶匙",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}茶匙",
                            )
                        ),
                    )
                ),
            )
        override val volumeBarrel
            get() = MultiLengthGenderedPattern(
                base = super.volumeBarrel,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 桶",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}桶",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoon
            get() = MultiLengthGenderedPattern(
                base = super.volumeDessertSpoon,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 甜品匙",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}甜品匙",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoonImperial
            get() = MultiLengthGenderedPattern(
                base = super.volumeDessertSpoonImperial,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英制甜品匙",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}甜品匙",
                            )
                        ),
                    )
                ),
            )
        override val volumeDrop
            get() = MultiLengthGenderedPattern(
                base = super.volumeDrop,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 滴",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}滴",
                            )
                        ),
                    )
                ),
            )
        override val volumeDram
            get() = MultiLengthGenderedPattern(
                base = super.volumeDram,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 打蘭",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}打蘭",
                            )
                        ),
                    )
                ),
            )
        override val volumeJigger
            get() = MultiLengthGenderedPattern(
                base = super.volumeJigger,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 量酒杯",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}量酒杯",
                            )
                        ),
                    )
                ),
            )
        override val volumePinch
            get() = MultiLengthGenderedPattern(
                base = super.volumePinch,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 小撮",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}小撮",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuartImperial
            get() = MultiLengthGenderedPattern(
                base = super.volumeQuartImperial,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0} 英制夸脫",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                other = "{0}英制夸脫",
                            )
                        ),
                    )
                ),
            )
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    open class NumberLanguage internal constructor() : LanguageZh.NumberLanguage() {
        override val symbols = LanguageZh.NumberLanguage().symbols.copy(
            nanSymbol = "非數值",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
