package info.leonenko.i18n.languages

import info.leonenko.i18n.*

val Language.Companion.fr_CA
    get() = languageFr_CA
private val languageFr_CA = LanguageFr_CA()
class LanguageFr_CA internal constructor() : LanguageFr() {
    override val code: String = "fr_CA"
    override val script: String? = null
    override val territory: String = "CA"

    class EllipsisLanguage internal constructor() : LanguageFr.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageFr.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageFr.MeasurementUnitLanguage() {
        override val accelerationGForce
            get() = MultiLengthGenderedPattern(
                base = super.accelerationGForce,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} G",
                                other = "{0} G",
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
                                one = "{0} mètre par seconde carrée",
                                other = "{0} mètres par seconde carrée",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} m/s²",
                                other = "{0} m/s²",
                            )
                        ),
                    )
                ),
            )
        override val angleRadian
            get() = MultiLengthGenderedPattern(
                base = super.angleRadian,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} radian",
                                other = "{0} radians",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} rad",
                                other = "{0} rad",
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
                base = super.angleDegree,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} degré",
                                other = "{0} degrés",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareKilometer
            get() = MultiLengthGenderedPattern(
                base = super.areaSquareKilometer,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilomètre carré",
                                other = "{0} kilomètres carrés",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} km²",
                                other = "{0} km²",
                            )
                        ),
                    )
                ),
            )
        override val areaHectare
            get() = MultiLengthGenderedPattern(
                base = super.areaHectare,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hectare",
                                other = "{0} hectares",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ha",
                                other = "{0} ha",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareMeter
            get() = MultiLengthGenderedPattern(
                base = super.areaSquareMeter,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mètre carré",
                                other = "{0} mètres carrés",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} m²",
                                other = "{0} m²",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareCentimeter
            get() = MultiLengthGenderedPattern(
                base = super.areaSquareCentimeter,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} centimètre carré",
                                other = "{0} centimètres carrés",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cm²",
                                other = "{0} cm²",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareMile
            get() = MultiLengthGenderedPattern(
                base = super.areaSquareMile,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mille carré",
                                other = "{0} milles carrés",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mi²",
                                other = "{0} mi²",
                            )
                        ),
                    )
                ),
            )
        override val areaAcre
            get() = MultiLengthGenderedPattern(
                base = super.areaAcre,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} acre",
                                other = "{0} acres",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ac",
                                other = "{0} ac",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareYard
            get() = MultiLengthGenderedPattern(
                base = super.areaSquareYard,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} verge carrée",
                                other = "{0} verges carrées",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} vg²",
                                other = "{0} vg²",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}vg²",
                                other = "{0}vg²",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareFoot
            get() = MultiLengthGenderedPattern(
                base = super.areaSquareFoot,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pied carré",
                                other = "{0} pieds carrés",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pi²",
                                other = "{0} pi²",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareInch
            get() = MultiLengthGenderedPattern(
                base = super.areaSquareInch,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pouce carré",
                                other = "{0} pouces carrés",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} po²",
                                other = "{0} po²",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermillion
            get() = MultiLengthGenderedPattern(
                base = super.concentrationPermillion,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} partie par million",
                                other = "{0} parties par million",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPercent
            get() = MultiLengthGenderedPattern(
                base = super.concentrationPercent,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} %",
                                other = "{0} %",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermille
            get() = MultiLengthGenderedPattern(
                base = super.concentrationPermille,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pour mille",
                                other = "{0} pour mille",
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
            )
        override val concentrationPortionPer1e9
            get() = MultiLengthGenderedPattern(
                base = super.concentrationPortionPer1e9,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} partie par milliard",
                                other = "{0} parties par milliard",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} p.p. 10⁹",
                                other = "{0} p.p. 10⁹",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}pp10⁹",
                                other = "{0}pp10⁹",
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
                                one = "{0} item",
                                other = "{0} items",
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
                                one = "{0} litre aux 100 kilomètres",
                                other = "{0} litres aux 100 kilomètres",
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
                                one = "{0} mille au gallon",
                                other = "{0} milles au gallon",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mi/gal",
                                other = "{0} mi/gal",
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
                                one = "{0} mille au gallon impérial",
                                other = "{0} milles au gallon impérial",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabyte
            get() = MultiLengthGenderedPattern(
                base = super.digitalTerabyte,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} téraoctet",
                                other = "{0} téraoctets",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} To",
                                other = "{0} To",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabit
            get() = MultiLengthGenderedPattern(
                base = super.digitalTerabit,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} térabit",
                                other = "{0} térabits",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Tb",
                                other = "{0} Tb",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Tb",
                                other = "{0}Tb",
                            )
                        ),
                    )
                ),
            )
        override val digitalGigabyte
            get() = MultiLengthGenderedPattern(
                base = super.digitalGigabyte,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigaoctet",
                                other = "{0} gigaoctets",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Go",
                                other = "{0} Go",
                            )
                        ),
                    )
                ),
            )
        override val digitalGigabit
            get() = MultiLengthGenderedPattern(
                base = super.digitalGigabit,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigabit",
                                other = "{0} gigabits",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Gb",
                                other = "{0} Gb",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Gb",
                                other = "{0}Gb",
                            )
                        ),
                    )
                ),
            )
        override val digitalMegabyte
            get() = MultiLengthGenderedPattern(
                base = super.digitalMegabyte,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mégaoctet",
                                other = "{0} mégaoctets",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Mo",
                                other = "{0} Mo",
                            )
                        ),
                    )
                ),
            )
        override val digitalMegabit
            get() = MultiLengthGenderedPattern(
                base = super.digitalMegabit,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mégabit",
                                other = "{0} mégabits",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Mb",
                                other = "{0} Mb",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Mb",
                                other = "{0}Mb",
                            )
                        ),
                    )
                ),
            )
        override val digitalKilobyte
            get() = MultiLengthGenderedPattern(
                base = super.digitalKilobyte,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilooctet",
                                other = "{0} kilooctets",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ko",
                                other = "{0} ko",
                            )
                        ),
                    )
                ),
            )
        override val digitalKilobit
            get() = MultiLengthGenderedPattern(
                base = super.digitalKilobit,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilobit",
                                other = "{0} kilobits",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kb",
                                other = "{0} kb",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}kb",
                                other = "{0}kb",
                            )
                        ),
                    )
                ),
            )
        override val digitalByteUnit
            get() = MultiLengthGenderedPattern(
                base = super.digitalByteUnit,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} octet",
                                other = "{0} octets",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} octet",
                                other = "{0} octet",
                            )
                        ),
                    )
                ),
            )
        override val digitalBit
            get() = MultiLengthGenderedPattern(
                base = super.digitalBit,
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
        override val durationDecade
            get() = MultiLengthGenderedPattern(
                base = super.durationDecade,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}déc",
                                other = "{0}déc",
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
                                one = "{0} an",
                                other = "{0} ans",
                            )
                        ),
                    )
                ),
            )
        override val durationMonth
            get() = MultiLengthGenderedPattern(
                base = super.durationMonth,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mois",
                                other = "{0} mois",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} m.",
                                other = "{0} m.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}m",
                                other = "{0}m",
                            )
                        ),
                    )
                ),
            )
        override val durationWeek
            get() = MultiLengthGenderedPattern(
                base = super.durationWeek,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} semaine",
                                other = "{0} semaines",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}sem",
                                other = "{0}sem",
                            )
                        ),
                    )
                ),
            )
        override val durationDay
            get() = MultiLengthGenderedPattern(
                base = super.durationDay,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} jour",
                                other = "{0} jours",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} j",
                                other = "{0} j",
                            )
                        ),
                    )
                ),
            )
        override val durationHour
            get() = MultiLengthGenderedPattern(
                base = super.durationHour,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} heure",
                                other = "{0} heures",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} h",
                                other = "{0} h",
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
                                one = "{0} min",
                                other = "{0} min",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}m",
                                other = "{0}m",
                            )
                        ),
                    )
                ),
            )
        override val durationSecond
            get() = MultiLengthGenderedPattern(
                base = super.durationSecond,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} seconde",
                                other = "{0} secondes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
                base = super.durationMillisecond,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milliseconde",
                                other = "{0} millisecondes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ms",
                                other = "{0} ms",
                            )
                        ),
                    )
                ),
            )
        override val durationMicrosecond
            get() = MultiLengthGenderedPattern(
                base = super.durationMicrosecond,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} microseconde",
                                other = "{0} microsecondes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} μs",
                                other = "{0} μs",
                            )
                        ),
                    )
                ),
            )
        override val durationNanosecond
            get() = MultiLengthGenderedPattern(
                base = super.durationNanosecond,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} nanoseconde",
                                other = "{0} nanosecondes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ns",
                                other = "{0} ns",
                            )
                        ),
                    )
                ),
            )
        override val electricAmpere
            get() = MultiLengthGenderedPattern(
                base = super.electricAmpere,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ampère",
                                other = "{0} ampères",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} A",
                                other = "{0} A",
                            )
                        ),
                    )
                ),
            )
        override val electricMilliampere
            get() = MultiLengthGenderedPattern(
                base = super.electricMilliampere,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milliampère",
                                other = "{0} milliampères",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mA",
                                other = "{0} mA",
                            )
                        ),
                    )
                ),
            )
        override val electricOhm
            get() = MultiLengthGenderedPattern(
                base = super.electricOhm,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ohm",
                                other = "{0} ohms",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Ω",
                                other = "{0} Ω",
                            )
                        ),
                    )
                ),
            )
        override val electricVolt
            get() = MultiLengthGenderedPattern(
                base = super.electricVolt,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} volt",
                                other = "{0} volts",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} V",
                                other = "{0} V",
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
                                one = "{0} kilocalorie",
                                other = "{0} kilocalories",
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
        override val energyCalorie
            get() = MultiLengthGenderedPattern(
                base = super.energyCalorie,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} calorie",
                                other = "{0} calories",
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
        override val energyFoodcalorie
            get() = MultiLengthGenderedPattern(
                base = super.energyFoodcalorie,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilocalorie",
                                other = "{0} kilocalories",
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
                                one = "{0} kilojoule",
                                other = "{0} kilojoules",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kJ",
                                other = "{0} kJ",
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
                                one = "{0} joule",
                                other = "{0} joules",
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
        override val energyKilowattHour
            get() = MultiLengthGenderedPattern(
                base = super.energyKilowattHour,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilowattheure",
                                other = "{0} kilowattheures",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kWh",
                                other = "{0} kWh",
                            )
                        ),
                    )
                ),
            )
        override val energyBritishThermalUnit
            get() = MultiLengthGenderedPattern(
                base = super.energyBritishThermalUnit,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} unité thermique britannique",
                                other = "{0} unités thermiques britanniques",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} BTU",
                                other = "{0} BTU",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}BTU",
                                other = "{0}BTU",
                            )
                        ),
                    )
                ),
            )
        override val energyThermUs
            get() = MultiLengthGenderedPattern(
                base = super.energyThermUs,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} therm américain",
                                other = "{0} therms américains",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}therm US",
                                other = "{0}therm US",
                            )
                        ),
                    )
                ),
            )
        override val frequencyGigahertz
            get() = MultiLengthGenderedPattern(
                base = super.frequencyGigahertz,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigahertz",
                                other = "{0} gigahertz",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} GHz",
                                other = "{0} GHz",
                            )
                        ),
                    )
                ),
            )
        override val frequencyMegahertz
            get() = MultiLengthGenderedPattern(
                base = super.frequencyMegahertz,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mégahertz",
                                other = "{0} mégahertz",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} MHz",
                                other = "{0} MHz",
                            )
                        ),
                    )
                ),
            )
        override val frequencyKilohertz
            get() = MultiLengthGenderedPattern(
                base = super.frequencyKilohertz,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilohertz",
                                other = "{0} kilohertz",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kHz",
                                other = "{0} kHz",
                            )
                        ),
                    )
                ),
            )
        override val frequencyHertz
            get() = MultiLengthGenderedPattern(
                base = super.frequencyHertz,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hertz",
                                other = "{0} hertz",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Hz",
                                other = "{0} Hz",
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
                                one = "{0} pt/cm",
                                other = "{0} pt/cm",
                            )
                        ),
                    )
                ),
            )
        override val lengthEarthRadius
            get() = MultiLengthGenderedPattern(
                base = super.lengthEarthRadius,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}R⊕",
                                other = "{0}R⊕",
                            )
                        ),
                    )
                ),
            )
        override val lengthKilometer
            get() = MultiLengthGenderedPattern(
                base = super.lengthKilometer,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilomètre",
                                other = "{0} kilomètres",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} km",
                                other = "{0} km",
                            )
                        ),
                    )
                ),
            )
        override val lengthMeter
            get() = MultiLengthGenderedPattern(
                base = super.lengthMeter,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mètre",
                                other = "{0} mètres",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
        override val lengthDecimeter
            get() = MultiLengthGenderedPattern(
                base = super.lengthDecimeter,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} décimètre",
                                other = "{0} décimètres",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dm",
                                other = "{0} dm",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}dm",
                                other = "{0}dm",
                            )
                        ),
                    )
                ),
            )
        override val lengthCentimeter
            get() = MultiLengthGenderedPattern(
                base = super.lengthCentimeter,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} centimètre",
                                other = "{0} centimètres",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cm",
                                other = "{0} cm",
                            )
                        ),
                    )
                ),
            )
        override val lengthMillimeter
            get() = MultiLengthGenderedPattern(
                base = super.lengthMillimeter,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} millimètre",
                                other = "{0} millimètres",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mm",
                                other = "{0} mm",
                            )
                        ),
                    )
                ),
            )
        override val lengthMicrometer
            get() = MultiLengthGenderedPattern(
                base = super.lengthMicrometer,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} micromètre",
                                other = "{0} micromètres",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} μm",
                                other = "{0} μm",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}μm",
                                other = "{0}μm",
                            )
                        ),
                    )
                ),
            )
        override val lengthNanometer
            get() = MultiLengthGenderedPattern(
                base = super.lengthNanometer,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} nanomètre",
                                other = "{0} nanomètres",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} nm",
                                other = "{0} nm",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}nm",
                                other = "{0}nm",
                            )
                        ),
                    )
                ),
            )
        override val lengthPicometer
            get() = MultiLengthGenderedPattern(
                base = super.lengthPicometer,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} picomètre",
                                other = "{0} picomètres",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pm",
                                other = "{0} pm",
                            )
                        ),
                    )
                ),
            )
        override val lengthMile
            get() = MultiLengthGenderedPattern(
                base = super.lengthMile,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mille",
                                other = "{0} milles",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mi",
                                other = "{0} mi",
                            )
                        ),
                    )
                ),
            )
        override val lengthYard
            get() = MultiLengthGenderedPattern(
                base = super.lengthYard,
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} verge",
                                other = "{0} verges",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} vg",
                                other = "{0} vg",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}vg",
                                other = "{0}vg",
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
                                one = "{0} pied",
                                other = "{0} pieds",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pi",
                                other = "{0} pi",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}pi",
                                other = "{0}pi",
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
                                one = "{0} pouce",
                                other = "{0} pouces",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} po",
                                other = "{0} po",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}po",
                                other = "{0}po",
                            )
                        ),
                    )
                ),
            )
        override val lengthParsec
            get() = MultiLengthGenderedPattern(
                base = super.lengthParsec,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} parsec",
                                other = "{0} parsecs",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pc",
                                other = "{0} pc",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}pc",
                                other = "{0}pc",
                            )
                        ),
                    )
                ),
            )
        override val lengthLightYear
            get() = MultiLengthGenderedPattern(
                base = super.lengthLightYear,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} année-lumière",
                                other = "{0} années-lumière",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} al",
                                other = "{0} al",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}al",
                                other = "{0}al",
                            )
                        ),
                    )
                ),
            )
        override val lengthAstronomicalUnit
            get() = MultiLengthGenderedPattern(
                base = super.lengthAstronomicalUnit,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} unité astronomique",
                                other = "{0} unités astronomiques",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ua",
                                other = "{0} ua",
                            )
                        ),
                    )
                ),
            )
        override val lengthFurlong
            get() = MultiLengthGenderedPattern(
                base = super.lengthFurlong,
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
                base = super.lengthFathom,
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
                base = super.lengthNauticalMile,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mille marin",
                                other = "{0} milles marins",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} NM",
                                other = "{0} NM",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}NM",
                                other = "{0}NM",
                            )
                        ),
                    )
                ),
            )
        override val lengthMileScandinavian
            get() = MultiLengthGenderedPattern(
                base = super.lengthMileScandinavian,
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
                base = super.lengthPoint,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}pt",
                                other = "{0}pt",
                            )
                        ),
                    )
                ),
            )
        override val lightLux
            get() = MultiLengthGenderedPattern(
                base = super.lightLux,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} lux",
                                other = "{0} lux",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} lx",
                                other = "{0} lx",
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
                                one = "{0} t",
                                other = "{0} t",
                            )
                        ),
                    )
                ),
            )
        override val massKilogram
            get() = MultiLengthGenderedPattern(
                base = super.massKilogram,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilogramme",
                                other = "{0} kilogrammes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
                base = super.massGram,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gramme",
                                other = "{0} grammes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
        override val massMilligram
            get() = MultiLengthGenderedPattern(
                base = super.massMilligram,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milligramme",
                                other = "{0} milligrammes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mg",
                                other = "{0} mg",
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
                                one = "{0} μg",
                                other = "{0} μg",
                            )
                        ),
                    )
                ),
            )
        override val massTon
            get() = MultiLengthGenderedPattern(
                base = super.massTon,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} tonne courte",
                                other = "{0} tonnes courtes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} tc",
                                other = "{0} tc",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}tc",
                                other = "{0}tc",
                            )
                        ),
                    )
                ),
            )
        override val massStone
            get() = MultiLengthGenderedPattern(
                base = super.massStone,
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}st",
                                other = "{0}st",
                            )
                        ),
                    )
                ),
            )
        override val massPound
            get() = MultiLengthGenderedPattern(
                base = super.massPound,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} livre",
                                other = "{0} livres",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} lb",
                                other = "{0} lb",
                            )
                        ),
                    )
                ),
            )
        override val massOunce
            get() = MultiLengthGenderedPattern(
                base = super.massOunce,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} once",
                                other = "{0} onces",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} oz",
                                other = "{0} oz",
                            )
                        ),
                    )
                ),
            )
        override val massOunceTroy
            get() = MultiLengthGenderedPattern(
                base = super.massOunceTroy,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} once troy",
                                other = "{0} onces troy",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} oz t",
                                other = "{0} oz t",
                            )
                        ),
                    )
                ),
            )
        override val massCarat
            get() = MultiLengthGenderedPattern(
                base = super.massCarat,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} carat",
                                other = "{0} carats",
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
            )
        override val massGrain
            get() = MultiLengthGenderedPattern(
                base = super.massGrain,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} grain",
                                other = "{0} grains",
                            )
                        ),
                    )
                ),
            )
        override val powerGigawatt
            get() = MultiLengthGenderedPattern(
                base = super.powerGigawatt,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigawatt",
                                other = "{0} gigawatts",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} GW",
                                other = "{0} GW",
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
                                one = "{0} mégawatt",
                                other = "{0} mégawatts",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} MW",
                                other = "{0} MW",
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
                                one = "{0} kilowatt",
                                other = "{0} kilowatts",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kW",
                                other = "{0} kW",
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
                                one = "{0} watt",
                                other = "{0} watts",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
        override val powerMilliwatt
            get() = MultiLengthGenderedPattern(
                base = super.powerMilliwatt,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milliwatt",
                                other = "{0} milliwatts",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mW",
                                other = "{0} mW",
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
                                one = "{0} cheval-vapeur",
                                other = "{0} chevaux-vapeur",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ch",
                                other = "{0} ch",
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
                                one = "{0} mm Hg",
                                other = "{0} mm Hg",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}mmHg",
                                other = "{0}mmHg",
                            )
                        ),
                    )
                ),
            )
        override val pressurePoundForcePerSquareInch
            get() = MultiLengthGenderedPattern(
                base = super.pressurePoundForcePerSquareInch,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} psi",
                                other = "{0} psi",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}psi",
                                other = "{0}psi",
                            )
                        ),
                    )
                ),
            )
        override val pressureInchOfhg
            get() = MultiLengthGenderedPattern(
                base = super.pressureInchOfhg,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pouce de mercure",
                                other = "{0} pouces de mercure",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} po Hg",
                                other = "{0} po Hg",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}poHg",
                                other = "{0}poHg",
                            )
                        ),
                    )
                ),
            )
        override val pressureMillibar
            get() = MultiLengthGenderedPattern(
                base = super.pressureMillibar,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} millibar",
                                other = "{0} millibars",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mbar",
                                other = "{0} mbar",
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
                                one = "{0} atmosphère",
                                other = "{0} atmosphères",
                            )
                        ),
                    )
                ),
            )
        override val pressureHectopascal
            get() = MultiLengthGenderedPattern(
                base = super.pressureHectopascal,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hectopascal",
                                other = "{0} hectopascals",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hPa",
                                other = "{0} hPa",
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
                                one = "{0} kilomètre par heure",
                                other = "{0} kilomètres par heure",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
                base = super.speedMeterPerSecond,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mètre par seconde",
                                other = "{0} mètres par seconde",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} m/s",
                                other = "{0} m/s",
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
                                one = "{0} mille à l’heure",
                                other = "{0} milles à l’heure",
                            )
                        ),
                    )
                ),
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
        override val speedKnot
            get() = MultiLengthGenderedPattern(
                base = super.speedKnot,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}nd",
                                other = "{0}nd",
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
                                one = "{0} Bf",
                                other = "{0} Bf",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Bf",
                                other = "{0} Bf",
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
        override val temperatureCelsius
            get() = MultiLengthGenderedPattern(
                base = super.temperatureCelsius,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} degré Celsius",
                                other = "{0} degrés Celsius",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} °C",
                                other = "{0} °C",
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
                                one = "{0} degré Fahrenheit",
                                other = "{0} degrés Fahrenheit",
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
                base = super.temperatureKelvin,
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
        override val torquePoundForceFoot
            get() = MultiLengthGenderedPattern(
                base = super.torquePoundForceFoot,
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} lb-pi",
                                other = "{0} lb-pi",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}lb-pi",
                                other = "{0}lb-pi",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicKilometer
            get() = MultiLengthGenderedPattern(
                base = super.volumeCubicKilometer,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilomètre cube",
                                other = "{0} kilomètres cubes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} km³",
                                other = "{0} km³",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicMeter
            get() = MultiLengthGenderedPattern(
                base = super.volumeCubicMeter,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mètre cube",
                                other = "{0} mètres cubes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} m³",
                                other = "{0} m³",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicCentimeter
            get() = MultiLengthGenderedPattern(
                base = super.volumeCubicCentimeter,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} centimètre cube",
                                other = "{0} centimètres cubes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cm³",
                                other = "{0} cm³",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicMile
            get() = MultiLengthGenderedPattern(
                base = super.volumeCubicMile,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mille cube",
                                other = "{0} milles cubes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mi³",
                                other = "{0} mi³",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicYard
            get() = MultiLengthGenderedPattern(
                base = super.volumeCubicYard,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} verge cube",
                                other = "{0} verges cubes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} vg³",
                                other = "{0} vg³",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}vg³",
                                other = "{0}vg³",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicFoot
            get() = MultiLengthGenderedPattern(
                base = super.volumeCubicFoot,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pied cube",
                                other = "{0} pieds cubes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pi³",
                                other = "{0} pi³",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicInch
            get() = MultiLengthGenderedPattern(
                base = super.volumeCubicInch,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pouce cube",
                                other = "{0} pouces cubes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} po³",
                                other = "{0} po³",
                            )
                        ),
                    )
                ),
            )
        override val volumeMegaliter
            get() = MultiLengthGenderedPattern(
                base = super.volumeMegaliter,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mégalitre",
                                other = "{0} mégalitres",
                            )
                        ),
                    )
                ),
            )
        override val volumeHectoliter
            get() = MultiLengthGenderedPattern(
                base = super.volumeHectoliter,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hectolitre",
                                other = "{0} hectolitres",
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
                                one = "{0} litre",
                                other = "{0} litres",
                            )
                        ),
                    )
                ),
            )
        override val volumeAcreFoot
            get() = MultiLengthGenderedPattern(
                base = super.volumeAcreFoot,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ac pi",
                                other = "{0}ac pi",
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
                                one = "{0} gal Imp",
                                other = "{0} gal Imp",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}/gal imp",
                                other = "{0}/gal imp",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuart
            get() = MultiLengthGenderedPattern(
                base = super.volumeQuart,
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pinte",
                                other = "{0} pintes",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pte",
                                other = "{0} pte",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}pte",
                                other = "{0}pte",
                            )
                        ),
                    )
                ),
            )
        override val volumePint
            get() = MultiLengthGenderedPattern(
                base = super.volumePint,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} chopine",
                                other = "{0} chopines",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} chop",
                                other = "{0} chop",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}chop",
                                other = "{0}chop",
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
                                one = "{0} oz liq.",
                                other = "{0} oz liq.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}oz liq.",
                                other = "{0}oz liq.",
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
                                one = "{0} oz liq imp.",
                                other = "{0} oz liq imp.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}oz liq imp.",
                                other = "{0}oz liq imp.",
                            )
                        ),
                    )
                ),
            )
        override val volumeTablespoon
            get() = MultiLengthGenderedPattern(
                base = super.volumeTablespoon,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}c. à s.",
                                other = "{0}c. à s.",
                            )
                        ),
                    )
                ),
            )
        override val volumeTeaspoon
            get() = MultiLengthGenderedPattern(
                base = super.volumeTeaspoon,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cuillère à thé",
                                other = "{0} cuillères à thé",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} c. à t.",
                                other = "{0} c. à t.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}c. à t.",
                                other = "{0}c. à t.",
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
                                one = "{0} cuill. à d.",
                                other = "{0} cuill. à d.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}c. à d.",
                                other = "{0}c. à d.",
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
                                one = "{0} cuill. à d. imp.",
                                other = "{0} cuill. à d. imp.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}c. à. d. imp.",
                                other = "{0} c. à. d. imp.",
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
                                one = "{0} goutte",
                                other = "{0} gouttes",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}gtt.",
                                other = "{0}gtt.",
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
                                one = "{0} dram liq",
                                other = "{0} dram liq",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}dr. liq",
                                other = "{0}dr. liq",
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
                                one = "{0} gobelet doseur",
                                other = "{0} gobelets doseurs",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}gob. doseur",
                                other = "{0}gob. doseurs",
                            )
                        ),
                    )
                ),
            )
        override val volumePinch
            get() = MultiLengthGenderedPattern(
                base = super.volumePinch,
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}pincée",
                                other = "{0}pincées",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuartImperial
            get() = MultiLengthGenderedPattern(
                base = super.volumeQuartImperial,
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pinte impériale",
                                other = "{0} pintes impériales",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pte imp",
                                other = "{0} pte imp",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}pte imp",
                                other = "{0}pte imp",
                            )
                        ),
                    )
                ),
            )
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageFr.NumberLanguage() {
        override val symbols = LanguageFr.NumberLanguage().symbols.copy(
            groupSymbol = " ",
            approximatelySignSymbol = "≈",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
