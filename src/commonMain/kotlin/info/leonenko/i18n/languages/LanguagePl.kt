package info.leonenko.i18n.languages

import info.leonenko.i18n.*
import info.leonenko.i18n.rbnf.RBNFPl

val Language.Companion.pl
    get() = languagePl
private val languagePl = LanguagePl()

open class LanguagePl internal constructor() : Language {
    override val code: String = "pl"
    override val language: String = "pl"
    override val script: String? = null
    override val territory: String? = null
    override val rbnf: RBNFPl
        get() = RBNFPl(this)
    override val pluralFormChooser = PluralFormChooser.Expression(
        one = { (i == 1L) && (v == 0) },
        few = { (v == 0) && ((i % 10) in 2L..4L) && ((i % 100) !in 12L..14L) },
        many = { (v == 0) && (i != 1L) && ((i % 10) in 0L..1L) || (v == 0) && ((i % 10) in 5L..9L) || (v == 0) && ((i % 100) in 12L..14L) },
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
            MultiLengthPatternedListFormatter(long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} lub {1}", two = "{0} lub {1}", ellipsis = ellipsis.wordFormatter))
        override val unitFormatter: ListFormatter = formatter
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    open class MeasurementUnitLanguage internal constructor() : info.leonenko.i18n.units.MeasurementUnitLanguage {
        override val accelerationGForce: MultiLengthGenderedPattern
            get() = TODO("GForce formatter is not implemented yet for pl")
        override val accelerationMeterPerSquareSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} metr na sekundę do kwadratu",
                                few = "{0} metry na sekundę do kwadratu",
                                many = "{0} metrów na sekundę do kwadratu",
                                other = "{0} metra na sekundę do kwadratu",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} metr na sekundę do kwadratu",
                                few = "{0} metry na sekundę do kwadratu",
                                many = "{0} metrów na sekundę do kwadratu",
                                other = "{0} metra na sekundę do kwadratu",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} metrowi na sekundę do kwadratu",
                                few = "{0} metrom na sekundę do kwadratu",
                                many = "{0} metrom na sekundę do kwadratu",
                                other = "{0} metrom na sekundę do kwadratu",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} metra na sekundę do kwadratu",
                                few = "{0} metrów na sekundę do kwadratu",
                                many = "{0} metrów na sekundę do kwadratu",
                                other = "{0} metra na sekundę do kwadratu",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} metrem na sekundę do kwadratu",
                                few = "{0} metrami na sekundę do kwadratu",
                                many = "{0} metrami na sekundę do kwadratu",
                                other = "{0} metrami na sekundę do kwadratu",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} metrze na sekundę do kwadratu",
                                few = "{0} metrach na sekundę do kwadratu",
                                many = "{0} metrach na sekundę do kwadratu",
                                other = "{0} metrach na sekundę do kwadratu",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} metrze na sekundę do kwadratu",
                                other = "{0} metrze na sekundę do kwadratu",
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
                                one = "{0} obrót",
                                few = "{0} obroty",
                                many = "{0} obrotów",
                                other = "{0} obrotu",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} obrót",
                                few = "{0} obroty",
                                many = "{0} obrotów",
                                other = "{0} obrotu",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} obrotu",
                                few = "{0} obrotów",
                                many = "{0} obrotów",
                                other = "{0} obrotu",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} obr.",
                                few = "{0} obr.",
                                many = "{0} obr.",
                                other = "{0} obr.",
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
                                one = "{0} radian",
                                few = "{0} radiany",
                                many = "{0} radianów",
                                other = "{0} radiana",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} radian",
                                few = "{0} radiany",
                                many = "{0} radianów",
                                other = "{0} radiana",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} radiana",
                                few = "{0} radianów",
                                many = "{0} radianów",
                                other = "{0} radiana",
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
                                one = "{0} stopień",
                                few = "{0} stopnie",
                                many = "{0} stopni",
                                other = "{0} stopnia",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} stopień",
                                few = "{0} stopnie",
                                many = "{0} stopni",
                                other = "{0} stopnia",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} stopnia",
                                few = "{0} stopni",
                                many = "{0} stopni",
                                other = "{0} stopnia",
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
                                one = "{0} minuta kątowa",
                                few = "{0} minuty kątowe",
                                many = "{0} minut kątowych",
                                other = "{0} minuty kątowej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} minutę kątową",
                                few = "{0} minuty kątowe",
                                many = "{0} minut kątowych",
                                other = "{0} minuty kątowej",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} minuty kątowej",
                                few = "{0} minut kątkowych",
                                many = "{0} minut kątowych",
                                other = "{0} minuty kątowej",
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
                                one = "{0} sekunda kątowa",
                                few = "{0} sekundy kątowe",
                                many = "{0} sekund kątowych",
                                other = "{0} sekundy kątowej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} sekundę kątową",
                                few = "{0} sekundy kątowe",
                                many = "{0} sekund kątowych",
                                other = "{0} sekundy kątowej",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} sekundy kątowej",
                                few = "{0} sekund kątkowych",
                                many = "{0} sekund kątowych",
                                other = "{0} sekundy kątowej",
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
                                one = "{0} kilometr kwadratowy",
                                few = "{0} kilometry kwadratowe",
                                many = "{0} kilometrów kwadratowych",
                                other = "{0} kilometra kwadratowego",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilometr kwadratowy",
                                few = "{0} kilometry kwadratowe",
                                many = "{0} kilometrów kwadratowych",
                                other = "{0} kilometra kwadratowego",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} kilometrowi kwadratowemu",
                                few = "{0} kilometrom kwadratowym",
                                many = "{0} kilometrom kwadratowym",
                                other = "{0} kilometrom kwadratowym",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilometra kwadratowego",
                                few = "{0} kilometrów kwadratowych",
                                many = "{0} kilometrów kwadratowych",
                                other = "{0} kilometra kwadratowego",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilometrem kwadratowym",
                                few = "{0} kilometrami kwadratowymi",
                                many = "{0} kilometrami kwadratowymi",
                                other = "{0} kilometrami kwadratowymi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} kilometrze kwadratowym",
                                few = "{0} kilometrach kwadratowych",
                                many = "{0} kilometrach kwadratowych",
                                other = "{0} kilometrach kwadratowych",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} kilometrze kwadratowy",
                                other = "{0} kilometrze kwadratowy",
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
                                few = "{0} hektary",
                                many = "{0} hektarów",
                                other = "{0} hektara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} hektar",
                                few = "{0} hektary",
                                many = "{0} hektarów",
                                other = "{0} hektara",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} hektarowi",
                                few = "{0} hektarom",
                                many = "{0} hektarom",
                                other = "{0} hektarom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} hektara",
                                few = "{0} hektarów",
                                many = "{0} hektarów",
                                other = "{0} hektara",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} hektarem",
                                few = "{0} hektarami",
                                many = "{0} hektarami",
                                other = "{0} hektarami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} hektarze",
                                few = "{0} hektarach",
                                many = "{0} hektarach",
                                other = "{0} hektarach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} hektarze",
                                other = "{0} hektarze",
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
                                one = "{0} metr kwadratowy",
                                few = "{0} metry kwadratowe",
                                many = "{0} metrów kwadratowych",
                                other = "{0} metra kwadratowego",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} metr kwadratowy",
                                few = "{0} metry kwadratowe",
                                many = "{0} metrów kwadratowych",
                                other = "{0} metra kwadratowego",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} metra kwadratowego",
                                few = "{0} metrów kwadratowych",
                                many = "{0} metrów kwadratowych",
                                other = "{0} metra kwadratowego",
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
                                one = "{0} centymetr kwadratowy",
                                few = "{0} centymetry kwadratowe",
                                many = "{0} centymetrów kwadratowych",
                                other = "{0} centymetra kwadratowego",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} centymetr kwadratowy",
                                few = "{0} centymetry kwadratowe",
                                many = "{0} centymetrów kwadratowych",
                                other = "{0} centymetra kwadratowego",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} centymetrowi kwadratowemu",
                                few = "{0} centymetrom kwadratowym",
                                many = "{0} centymetrom kwadratowym",
                                other = "{0} centymetrom kwadratowym",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} centymetra kwadratowego",
                                few = "{0} centymetrów kwadratowych",
                                many = "{0} centymetrów kwadratowych",
                                other = "{0} centymetra kwadratowego",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} centymetrem kwadratowym",
                                few = "{0} centymetrami kwadratowymi",
                                many = "{0} centymetrami kwadratowymi",
                                other = "{0} centymetrami kwadratowymi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} centymetrze kwadratowym",
                                few = "{0} centymetrach kwadratowych",
                                many = "{0} centymetrach kwadratowych",
                                other = "{0} centymetrach kwadratowych",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} centymetrze kwadratowy",
                                other = "{0} centymetrze kwadratowy",
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
                                one = "{0} mila kwadratowa",
                                few = "{0} mile kwadratowe",
                                many = "{0} mil kwadratowych",
                                other = "{0} mili kwadratowej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milę kwadratową",
                                few = "{0} mile kwadratowe",
                                many = "{0} mil kwadratowych",
                                other = "{0} mili kwadratowej",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} mili kwadratowej",
                                few = "{0} milom kwadratowym",
                                many = "{0} milom kwadratowym",
                                other = "{0} milom kwadratowym",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mili kwadratowej",
                                few = "{0} mil kwadratowych",
                                many = "{0} mil kwadratowych",
                                other = "{0} mili kwadratowej",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milą kwadratową",
                                few = "{0} milami kwadratowymi",
                                many = "{0} milami kwadratowymi",
                                other = "{0} milami kwadratowymi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} mili kwadratowej",
                                few = "{0} milach kwadratowych",
                                many = "{0} milach kwadratowych",
                                other = "{0} milach kwadratowych",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} milo kwadratowa",
                                other = "{0} milo kwadratowa",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mila kw.",
                                few = "{0} mile kw.",
                                many = "{0} mil kw.",
                                other = "{0} mili kw.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mi²",
                                few = "{0} mi²",
                                many = "{0} mi²",
                                other = "{0} mi²",
                            )
                        ),
                    )
                ),
            )
        override val areaAcre
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} akr",
                                few = "{0} akry",
                                many = "{0} akrów",
                                other = "{0} akra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} akr",
                                few = "{0} akry",
                                many = "{0} akrów",
                                other = "{0} akra",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} akrowi",
                                few = "{0} akrom",
                                many = "{0} akrom",
                                other = "{0} akrom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} akra",
                                few = "{0} akrów",
                                many = "{0} akrów",
                                other = "{0} akra",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} akrem",
                                few = "{0} akrami",
                                many = "{0} akrami",
                                other = "{0} akrami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} akrze",
                                few = "{0} akrach",
                                many = "{0} akrach",
                                other = "{0} akrach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} akrze",
                                other = "{0} akrze",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} akr",
                                few = "{0} akry",
                                many = "{0} akrów",
                                other = "{0} akra",
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
                                one = "{0} jard kwadratowy",
                                few = "{0} jardy kwadratowe",
                                many = "{0} jardów kwadratowych",
                                other = "{0} jarda kwadratowego",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} jard kw.",
                                few = "{0} jardy kw.",
                                many = "{0} jardów kw.",
                                other = "{0} jarda kw.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} yd²",
                                few = "{0} yd²",
                                many = "{0} yd²",
                                other = "{0} yd²",
                            )
                        ),
                    )
                ),
            )
        override val areaSquareFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} stopa kwadratowa",
                                few = "{0} stopy kwadratowe",
                                many = "{0} stóp kwadratowych",
                                other = "{0} stopy kwadratowej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} stopę kwadratową",
                                few = "{0} stopy kwadratowe",
                                many = "{0} stóp kwadratowych",
                                other = "{0} stopy kwadratowej",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} stopie kwadratowej",
                                few = "{0} stopom kwadratowym",
                                many = "{0} stopom kwadratowym",
                                other = "{0} stopom kwadratowym",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} stopy kwadratowej",
                                few = "{0} stóp kwadratowych",
                                many = "{0} stóp kwadratowych",
                                other = "{0} stopy kwadratowej",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} stopą kwadratową",
                                few = "{0} stopami kwadratowymi",
                                many = "{0} stopami kwadratowymi",
                                other = "{0} stopami kwadratowymi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} stopie kwadratowej",
                                few = "{0} stopach kwadratowych",
                                many = "{0} stopach kwadratowych",
                                other = "{0} stopach kwadratowych",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} stopo kwadratowa",
                                other = "{0} stopo kwadratowa",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} stopa kw.",
                                few = "{0} stopy kw.",
                                many = "{0} stóp kw.",
                                other = "{0} stopy kw.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ft²",
                                few = "{0} ft²",
                                many = "{0} ft²",
                                other = "{0} ft²",
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
                                one = "{0} cal kwadratowy",
                                few = "{0} cale kwadratowe",
                                many = "{0} cali kwadratowych",
                                other = "{0} cala kwadratowego",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cal kw.",
                                few = "{0} cale kw.",
                                many = "{0} cali kw.",
                                other = "{0} cala kw.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} in²",
                                few = "{0} in²",
                                many = "{0} in²",
                                other = "{0} in²",
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
                                few = "{0} dunamy",
                                many = "{0} dunamów",
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
                                few = "{0} karaty",
                                many = "{0} karatów",
                                other = "{0} karata",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} karat",
                                few = "{0} karaty",
                                many = "{0} karatów",
                                other = "{0} karata",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} karata",
                                few = "{0} karatów",
                                many = "{0} karatów",
                                other = "{0} karata",
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
                                one = "{0} część na milion",
                                few = "{0} części na milion",
                                many = "{0} części na milion",
                                other = "{0} części na milion",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} część na milion",
                                few = "{0} części na milion",
                                many = "{0} części na milion",
                                other = "{0} części na milion",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} części na milion",
                                few = "{0} części na milion",
                                many = "{0} części na milion",
                                other = "{0} części na milion",
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
                                one = "{0} procent",
                                few = "{0} procent",
                                many = "{0} procent",
                                other = "{0} procent",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} procent",
                                few = "{0} procent",
                                many = "{0} procent",
                                other = "{0} procent",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} procentowi",
                                few = "{0} procentom",
                                many = "{0} procentom",
                                other = "{0} procentom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} procentu",
                                few = "{0} procent",
                                many = "{0} procent",
                                other = "{0} procent",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} procentem",
                                few = "{0} procentami",
                                many = "{0} procentami",
                                other = "{0} procentami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} procencie",
                                few = "{0} procentach",
                                many = "{0} procentach",
                                other = "{0} procentach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} procencie",
                                other = "{0} procencie",
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
                                few = "{0} promile",
                                many = "{0} promili",
                                other = "{0} promila",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} promil",
                                few = "{0} promile",
                                many = "{0} promili",
                                other = "{0} promila",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} promila",
                                few = "{0} promili",
                                many = "{0} promili",
                                other = "{0} promila",
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
                                one = "{0} punkt bazowy",
                                few = "{0} punkty bazowe",
                                many = "{0} punktów bazowych",
                                other = "{0} punktu bazowego",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} punkt bazowy",
                                few = "{0} punkty bazowe",
                                many = "{0} punktów bazowych",
                                other = "{0} punktu bazowego",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} punktu bazowego",
                                few = "{0} punktów bazowych",
                                many = "{0} punktów bazowych",
                                other = "{0} punktu bazowego",
                            )
                        ),
                    )
                ),
            )
        override val concentrationAPortion: MultiLengthGenderedPattern
            get() = TODO("APortion formatter is not implemented yet for pl")
        override val concentrationMilligramOfglucosePerDeciliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} miligram na decylitr",
                                few = "{0} miligramy na decylitr",
                                many = "{0} miligramów na decylitr",
                                other = "{0} miligrama na decylitr",
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
                                many = "{0} mg/dl",
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
                                one = "{0} milimol na litr",
                                few = "{0} milimole na litr",
                                many = "{0} milimoli na litr",
                                other = "{0} milimola na litr",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milimol na litr",
                                few = "{0} milimole na litr",
                                many = "{0} milimoli na litr",
                                other = "{0} milimola na litr",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} milimola na litr",
                                few = "{0} milimoli na litr",
                                many = "{0} milimoli na litr",
                                other = "{0} milimola na litr",
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
                                many = "{0} mmol/l",
                                other = "{0} mmol/l",
                            )
                        ),
                    )
                ),
            )
        override val concentrationItemPerCubicMeter: MultiLengthGenderedPattern
            get() = TODO("ItemPerCubicMeter formatter is not implemented yet for pl")
        override val concentrationItem
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} sztuka",
                                few = "{0} sztuki",
                                many = "{0} sztuk",
                                other = "{0} sztuki",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} sztukę",
                                few = "{0} sztuki",
                                many = "{0} sztuk",
                                other = "{0} sztuki",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} sztuki",
                                few = "{0} sztuk",
                                many = "{0} sztuk",
                                other = "{0} sztuki",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} szt.",
                                few = "{0} szt.",
                                many = "{0} szt.",
                                other = "{0} szt.",
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
                                one = "{0} mol",
                                few = "{0} mole",
                                many = "{0} moli",
                                other = "{0} mola",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mol",
                                few = "{0} mole",
                                many = "{0} moli",
                                other = "{0} mola",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mola",
                                few = "{0} moli",
                                many = "{0} moli",
                                other = "{0} mola",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mol",
                                few = "{0} mole",
                                many = "{0} moli",
                                other = "{0} mola",
                            )
                        ),
                    )
                ),
            )
        override val consumptionLiterPerKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} litr na kilometr",
                                few = "{0} litry na kilometr",
                                many = "{0} litrów na kilometr",
                                other = "{0} litra na kilometr",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} litr na kilometr",
                                few = "{0} litry na kilometr",
                                many = "{0} litrów na kilometr",
                                other = "{0} litra na kilometr",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} litra na kilometr",
                                few = "{0} litrów na kilometr",
                                many = "{0} litrów na kilometr",
                                other = "{0} litra na kilometr",
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
                                many = "{0} l/km",
                                other = "{0} l/km",
                            )
                        ),
                    )
                ),
            )
        override val consumptionLiterPer100Kilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} litr na 100 kilometrów",
                                few = "{0} litry na 100 kilometrów",
                                many = "{0} litrów na 100 kilometrów",
                                other = "{0} litra na 100 kilometrów",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} litr na 100 kilometrów",
                                few = "{0} litry na 100 kilometrów",
                                many = "{0} litrów na 100 kilometrów",
                                other = "{0} litra na 100 kilometrów",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} litrowi na 100 kilometrów",
                                few = "{0} litrom na 100 kilometrów",
                                many = "{0} litrom na 100 kilometrów",
                                other = "{0} litrom na 100 kilometrów",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} litra na 100 kilometrów",
                                few = "{0} litrów na 100 kilometrów",
                                many = "{0} litrów na 100 kilometrów",
                                other = "{0} litra na 100 kilometrów",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} litrem na 100 kilometrów",
                                few = "{0} litrami na 100 kilometrów",
                                many = "{0} litrami na 100 kilometrów",
                                other = "{0} litrami na 100 kilometrów",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} litrze na 100 kilometrów",
                                few = "{0} litrach na 100 kilometrów",
                                many = "{0} litrach na 100 kilometrów",
                                other = "{0} litrach na 100 kilometrów",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} litrze na 100 kilometrów",
                                other = "{0} litrze na 100 kilometrów",
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
                                many = "{0} l/100 km",
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
                                one = "{0} mila na galon",
                                few = "{0} mile na galon",
                                many = "{0} mil na galon",
                                other = "{0} mili na galon",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milę na galon",
                                few = "{0} mile na galon",
                                many = "{0} mil na galon",
                                other = "{0} mili na galon",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} mili na galon",
                                few = "{0} milom na galon",
                                many = "{0} milom na galon",
                                other = "{0} milom na galon",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mili na galon",
                                few = "{0} mil na galon",
                                many = "{0} mil na galon",
                                other = "{0} mili na galon",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milą na galon",
                                few = "{0} milami na galon",
                                many = "{0} milami na galon",
                                other = "{0} milami na galon",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} mili na galon",
                                few = "{0} milach na galon",
                                many = "{0} milach na galon",
                                other = "{0} milach na galon",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} milo na galon",
                                other = "{0} milo na galon",
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
                                many = "{0} mpg",
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
                                one = "{0} mila na galon angielski",
                                few = "{0} mile na galon angielski",
                                many = "{0} mil na galon angielski",
                                other = "{0} mili na galon angielski",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milę na galon angielski",
                                few = "{0} mile na galon angielski",
                                many = "{0} mil na galon angielski",
                                other = "{0} mili na galon angielski",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} mili na galon angielski",
                                few = "{0} milom na galon angielski",
                                many = "{0} milom na galon angielski",
                                other = "{0} milom na galon angielski",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mili na galon angielski",
                                few = "{0} mil na galon angielski",
                                many = "{0} mil na galon angielski",
                                other = "{0} mili na galon angielski",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milą na galon angielski",
                                few = "{0} milami na galon angielski",
                                many = "{0} milami na galon angielski",
                                other = "{0} milami na galon angielski",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} mili na galon angielski",
                                few = "{0} milach na galon angielski",
                                many = "{0} milach na galon angielski",
                                other = "{0} milach na galon angielski",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} milo na galon angielski",
                                other = "{0} milo na galon angielski",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mi/gal ang.",
                                few = "{0} mi/gal ang.",
                                many = "{0} mi/gal ang.",
                                other = "{0} mi/gal ang.",
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
                                few = "{0} petabajty",
                                many = "{0} petabajtów",
                                other = "{0} petabajta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} petabajt",
                                few = "{0} petabajty",
                                many = "{0} petabajtów",
                                other = "{0} petabajta",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} petabajta",
                                few = "{0} petabajtów",
                                many = "{0} petabajtów",
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
                                few = "{0} terabajty",
                                many = "{0} terabajtów",
                                other = "{0} terabajta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} terabajt",
                                few = "{0} terabajty",
                                many = "{0} terabajtów",
                                other = "{0} terabajta",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} terabajta",
                                few = "{0} terabajtów",
                                many = "{0} terabajtów",
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
                                few = "{0} terabity",
                                many = "{0} terabitów",
                                other = "{0} terabita",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} terabit",
                                few = "{0} terabity",
                                many = "{0} terabitów",
                                other = "{0} terabita",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} terabita",
                                few = "{0} terabitów",
                                many = "{0} terabitów",
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
                                few = "{0} gigabajty",
                                many = "{0} gigabajtów",
                                other = "{0} gigabajta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gigabajt",
                                few = "{0} gigabajty",
                                many = "{0} gigabajtów",
                                other = "{0} gigabajta",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} gigabajta",
                                few = "{0} gigabajtów",
                                many = "{0} gigabajtów",
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
                                few = "{0} gigabity",
                                many = "{0} gigabitów",
                                other = "{0} gigabita",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gigabit",
                                few = "{0} gigabity",
                                many = "{0} gigabitów",
                                other = "{0} gigabita",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} gigabita",
                                few = "{0} gigabitów",
                                many = "{0} gigabitów",
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
                                few = "{0} megabajty",
                                many = "{0} megabajtów",
                                other = "{0} megabajta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megabajt",
                                few = "{0} megabajty",
                                many = "{0} megabajtów",
                                other = "{0} megabajta",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} megabajta",
                                few = "{0} megabajtów",
                                many = "{0} megabajtów",
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
                                few = "{0} megabity",
                                many = "{0} megabitów",
                                other = "{0} megabita",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megabit",
                                few = "{0} megabity",
                                many = "{0} megabitów",
                                other = "{0} megabita",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} megabita",
                                few = "{0} megabitów",
                                many = "{0} megabitów",
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
                                few = "{0} kilobajty",
                                many = "{0} kilobajtów",
                                other = "{0} kilobajta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilobajt",
                                few = "{0} kilobajty",
                                many = "{0} kilobajtów",
                                other = "{0} kilobajta",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilobajta",
                                few = "{0} kilobajtów",
                                many = "{0} kilobajtów",
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
                                few = "{0} kilobity",
                                many = "{0} kilobitów",
                                other = "{0} kilobita",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilobit",
                                few = "{0} kilobity",
                                many = "{0} kilobitów",
                                other = "{0} kilobita",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilobita",
                                few = "{0} kilobitów",
                                many = "{0} kilobitów",
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
                                few = "{0} bajty",
                                many = "{0} bajtów",
                                other = "{0} bajta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} bajt",
                                few = "{0} bajty",
                                many = "{0} bajtów",
                                other = "{0} bajta",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} bajta",
                                few = "{0} bajtów",
                                many = "{0} bajtów",
                                other = "{0} bajta",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} B",
                                few = "{0} B",
                                many = "{0} B",
                                other = "{0} B",
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
                                few = "{0} bity",
                                many = "{0} bitów",
                                other = "{0} bita",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} bit",
                                few = "{0} bity",
                                many = "{0} bitów",
                                other = "{0} bita",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} bita",
                                few = "{0} bitów",
                                many = "{0} bitów",
                                other = "{0} bita",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} b",
                                few = "{0} b",
                                many = "{0} b",
                                other = "{0} b",
                            )
                        ),
                    )
                ),
            )
        override val durationCentury
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} wiek",
                                few = "{0} wieki",
                                many = "{0} wieków",
                                other = "{0} wieku",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} wiek",
                                few = "{0} wieki",
                                many = "{0} wieków",
                                other = "{0} wieku",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} wieku",
                                few = "{0} wieków",
                                many = "{0} wieków",
                                other = "{0} wieku",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} w.",
                                few = "{0} w.",
                                many = "{0} w.",
                                other = "{0} w.",
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
                                one = "{0} dekada",
                                few = "{0} dekady",
                                many = "{0} dekad",
                                other = "{0} dekady",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} dekadę",
                                few = "{0} dekady",
                                many = "{0} dekad",
                                other = "{0} dekady",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} dekady",
                                few = "{0} dekad",
                                many = "{0} dekad",
                                other = "{0} dekady",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dek",
                                few = "{0} dek",
                                many = "{0} dek",
                                other = "{0} dek",
                            )
                        ),
                    )
                ),
            )
        override val durationYear
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} rok",
                                few = "{0} lata",
                                many = "{0} lat",
                                other = "{0} roku",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} rok",
                                few = "{0} lata",
                                many = "{0} lat",
                                other = "{0} roku",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} rokowi",
                                few = "{0} latom",
                                many = "{0} latom",
                                other = "{0} latom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} roku",
                                few = "{0} lat",
                                many = "{0} lat",
                                other = "{0} roku",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} rokiem",
                                few = "{0} latami",
                                many = "{0} latami",
                                other = "{0} latami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} roku",
                                few = "{0} latach",
                                many = "{0} latach",
                                other = "{0} latach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} roku",
                                other = "{0} roku",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} rok",
                                few = "{0} lata",
                                many = "{0} lat",
                                other = "{0} roku",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} r.",
                                few = "{0} l.",
                                many = "{0} l.",
                                other = "{0} r.",
                            )
                        ),
                    )
                ),
            )
        override val durationQuarter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kwartał",
                                few = "{0} kwartały",
                                many = "{0} kwartałów",
                                other = "{0} kwartału",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kwartał",
                                few = "{0} kwartały",
                                many = "{0} kwartałów",
                                other = "{0} kwartału",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kwartału",
                                few = "{0} kwartałów",
                                many = "{0} kwartałów",
                                other = "{0} kwartału",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kw.",
                                few = "{0} kw.",
                                many = "{0} kw.",
                                other = "{0} kw.",
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
                                one = "{0} miesiąc",
                                few = "{0} miesiące",
                                many = "{0} miesięcy",
                                other = "{0} miesiąca",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} miesiąc",
                                few = "{0} miesiące",
                                many = "{0} miesięcy",
                                other = "{0} miesiąca",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} miesiącowi",
                                few = "{0} miesiącom",
                                many = "{0} miesiącom",
                                other = "{0} miesiącom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} miesiąca",
                                few = "{0} miesięcy",
                                many = "{0} miesięcy",
                                other = "{0} miesiąca",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} miesiącem",
                                few = "{0} miesiącami",
                                many = "{0} miesiącami",
                                other = "{0} miesiącami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} miesiącu",
                                few = "{0} miesiącach",
                                many = "{0} miesiącach",
                                other = "{0} miesiącach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} miesiącu",
                                other = "{0} miesiącu",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mies.",
                                few = "{0} mies.",
                                many = "{0} mies.",
                                other = "{0} mies.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} m-c",
                                few = "{0} m-ce",
                                many = "{0} m-cy",
                                other = "{0} m-ca",
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
                                one = "{0} tydzień",
                                few = "{0} tygodnie",
                                many = "{0} tygodni",
                                other = "{0} tygodnia",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} tydzień",
                                few = "{0} tygodnie",
                                many = "{0} tygodni",
                                other = "{0} tygodnia",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} tygodniowi",
                                few = "{0} tygodniom",
                                many = "{0} tygodniom",
                                other = "{0} tygodniom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} tygodnia",
                                few = "{0} tygodni",
                                many = "{0} tygodni",
                                other = "{0} tygodnia",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} tygodniem",
                                few = "{0} tygodniami",
                                many = "{0} tygodniami",
                                other = "{0} tygodniami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} tygodniu",
                                few = "{0} tygodniach",
                                many = "{0} tygodniach",
                                other = "{0} tygodniach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} tygodniu",
                                other = "{0} tygodniu",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} tydz.",
                                few = "{0} tyg.",
                                many = "{0} tyg.",
                                other = "{0} tyg.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} t.",
                                few = "{0} t.",
                                many = "{0} t.",
                                other = "{0} t.",
                            )
                        ),
                    )
                ),
            )
        override val durationDay
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} doba",
                                few = "{0} doby",
                                many = "{0} dób",
                                other = "{0} doby",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} dobę",
                                few = "{0} doby",
                                many = "{0} dób",
                                other = "{0} doby",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} dobie",
                                few = "{0} dobom",
                                many = "{0} dobom",
                                other = "{0} dobom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} doby",
                                few = "{0} dób",
                                many = "{0} dób",
                                other = "{0} doby",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} dobą",
                                few = "{0} dobami",
                                many = "{0} dobami",
                                other = "{0} dobami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} dobie",
                                few = "{0} dobach",
                                many = "{0} dobach",
                                other = "{0} dobach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} dobo",
                                other = "{0} dobo",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} doba",
                                few = "{0} doby",
                                many = "{0} dób",
                                other = "{0} doby",
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
                                many = "{0} d.",
                                other = "{0} d.",
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
                                one = "{0} godzina",
                                few = "{0} godziny",
                                many = "{0} godzin",
                                other = "{0} godziny",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} godzinę",
                                few = "{0} godziny",
                                many = "{0} godzin",
                                other = "{0} godziny",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} godzinie",
                                few = "{0} godzinom",
                                many = "{0} godzinom",
                                other = "{0} godzinom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} godziny",
                                few = "{0} godzin",
                                many = "{0} godzin",
                                other = "{0} godziny",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} godziną",
                                few = "{0} godzinami",
                                many = "{0} godzinami",
                                other = "{0} godzinami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} godzinie",
                                few = "{0} godzinach",
                                many = "{0} godzinach",
                                other = "{0} godzinach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} godzino",
                                other = "{0} godzino",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} godz.",
                                few = "{0} godz.",
                                many = "{0} godz.",
                                other = "{0} godz.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} h",
                                few = "{0} h",
                                many = "{0} h",
                                other = "{0} h",
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
                                few = "{0} minuty",
                                many = "{0} minut",
                                other = "{0} minuty",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} minutę",
                                few = "{0} minuty",
                                many = "{0} minut",
                                other = "{0} minuty",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} minucie",
                                few = "{0} minutom",
                                many = "{0} minutom",
                                other = "{0} minutom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} minuty",
                                few = "{0} minut",
                                many = "{0} minut",
                                other = "{0} minuty",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} minutą",
                                few = "{0} minutami",
                                many = "{0} minutami",
                                other = "{0} minutami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} minucie",
                                few = "{0} minutach",
                                many = "{0} minutach",
                                other = "{0} minutach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} minuto",
                                other = "{0} minuto",
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
                                few = "{0} sekundy",
                                many = "{0} sekund",
                                other = "{0} sekundy",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} sekundę",
                                few = "{0} sekundy",
                                many = "{0} sekund",
                                other = "{0} sekundy",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} sekundzie",
                                few = "{0} sekundom",
                                many = "{0} sekundom",
                                other = "{0} sekundom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} sekundy",
                                few = "{0} sekund",
                                many = "{0} sekund",
                                other = "{0} sekundy",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} sekundą",
                                few = "{0} sekundami",
                                many = "{0} sekundami",
                                other = "{0} sekundami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} sekundzie",
                                few = "{0} sekundach",
                                many = "{0} sekundach",
                                other = "{0} sekundach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} sekundo",
                                other = "{0} sekundo",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} sek.",
                                few = "{0} sek.",
                                many = "{0} sek.",
                                other = "{0} sek.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} s",
                                few = "{0} s",
                                many = "{0} s",
                                other = "{0} s",
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
                                few = "{0} milisekundy",
                                many = "{0} milisekund",
                                other = "{0} milisekundy",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milisekundę",
                                few = "{0} milisekundy",
                                many = "{0} milisekund",
                                other = "{0} milisekundy",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} milisekundy",
                                few = "{0} milisekund",
                                many = "{0} milisekund",
                                other = "{0} milisekundy",
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
                                few = "{0} mikrosekundy",
                                many = "{0} mikrosekund",
                                other = "{0} mikrosekundy",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mikrosekundę",
                                few = "{0} mikrosekundy",
                                many = "{0} mikrosekund",
                                other = "{0} mikrosekundy",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mikrosekundy",
                                few = "{0} mikrosekund",
                                many = "{0} mikrosekund",
                                other = "{0} mikrosekundy",
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
                                few = "{0} nanosekundy",
                                many = "{0} nanosekund",
                                other = "{0} nanosekundy",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} nanosekundę",
                                few = "{0} nanosekundy",
                                many = "{0} nanosekund",
                                other = "{0} nanosekundy",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} nanosekundy",
                                few = "{0} nanosekund",
                                many = "{0} nanosekund",
                                other = "{0} nanosekundy",
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
                                few = "{0} ampery",
                                many = "{0} amperów",
                                other = "{0} ampera",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} amper",
                                few = "{0} ampery",
                                many = "{0} amperów",
                                other = "{0} ampera",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} ampera",
                                few = "{0} amperów",
                                many = "{0} amperów",
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
                                few = "{0} miliampery",
                                many = "{0} miliamperów",
                                other = "{0} miliampera",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} miliamper",
                                few = "{0} miliampery",
                                many = "{0} miliamperów",
                                other = "{0} miliampera",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} miliampera",
                                few = "{0} miliamperów",
                                many = "{0} miliamperów",
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
                                few = "{0} omy",
                                many = "{0} omów",
                                other = "{0} oma",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} om",
                                few = "{0} omy",
                                many = "{0} omów",
                                other = "{0} oma",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} oma",
                                few = "{0} omów",
                                many = "{0} omów",
                                other = "{0} oma",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondSquareAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondSquareAmpere formatter is not implemented yet for pl")
        override val electricVolt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} wolt",
                                few = "{0} wolty",
                                many = "{0} woltów",
                                other = "{0} wolta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} wolt",
                                few = "{0} wolty",
                                many = "{0} woltów",
                                other = "{0} wolta",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} wolta",
                                few = "{0} woltów",
                                many = "{0} woltów",
                                other = "{0} wolta",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondAmpere formatter is not implemented yet for pl")
        override val energyKilocalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilokaloria",
                                few = "{0} kilokalorie",
                                many = "{0} kilokalorii",
                                other = "{0} kilokalorii",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilokalorię",
                                few = "{0} kilokalorie",
                                many = "{0} kilokalorii",
                                other = "{0} kilokalorii",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} kilokalorii",
                                few = "{0} kilokaloriom",
                                many = "{0} kilokaloriom",
                                other = "{0} kilokaloriom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilokalorii",
                                few = "{0} kilokalorii",
                                many = "{0} kilokalorii",
                                other = "{0} kilokalorii",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilokalorią",
                                few = "{0} kilokaloriami",
                                many = "{0} kilokaloriami",
                                other = "{0} kilokaloriami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} kilokalorii",
                                few = "{0} kilokaloriach",
                                many = "{0} kilokaloriach",
                                other = "{0} kilokaloriach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} kilokalorio",
                                other = "{0} kilokalorio",
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
                                one = "{0} kaloria",
                                few = "{0} kalorie",
                                many = "{0} kalorii",
                                other = "{0} kalorii",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kalorię",
                                few = "{0} kalorie",
                                many = "{0} kalorii",
                                other = "{0} kalorii",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kalorii",
                                few = "{0} kalorii",
                                many = "{0} kalorii",
                                other = "{0} kalorii",
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
                                one = "{0} kaloria",
                                few = "{0} kilokalorie",
                                many = "{0} kilokalorii",
                                other = "{0} kalorii",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kalorię",
                                few = "{0} kilokalorie",
                                many = "{0} kilokalorii",
                                other = "{0} kalorii",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} kalorii",
                                few = "{0} kilokaloriom",
                                many = "{0} kilokaloriom",
                                other = "{0} kilokaloriom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kalorii",
                                few = "{0} kilokalorii",
                                many = "{0} kilokalorii",
                                other = "{0} kalorii",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kalorią",
                                few = "{0} kilokaloriami",
                                many = "{0} kilokaloriami",
                                other = "{0} kilokaloriami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} kalorii",
                                few = "{0} kilokaloriach",
                                many = "{0} kilokaloriach",
                                other = "{0} kilokaloriach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} kalorio",
                                other = "{0} kalorio",
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
                                one = "{0} kilodżul",
                                few = "{0} kilodżule",
                                many = "{0} kilodżuli",
                                other = "{0} kilodżula",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilodżul",
                                few = "{0} kilodżule",
                                many = "{0} kilodżuli",
                                other = "{0} kilodżula",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilodżula",
                                few = "{0} kilodżuli",
                                many = "{0} kilodżuli",
                                other = "{0} kilodżula",
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
                                one = "{0} dżul",
                                few = "{0} dżule",
                                many = "{0} dżuli",
                                other = "{0} dżula",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} dżul",
                                few = "{0} dżule",
                                many = "{0} dżuli",
                                other = "{0} dżula",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} dżula",
                                few = "{0} dżuli",
                                many = "{0} dżuli",
                                other = "{0} dżula",
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
                                one = "{0} kilowatogodzina",
                                few = "{0} kilowatogodziny",
                                many = "{0} kilowatogodzin",
                                other = "{0} kilowatogodziny",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilowatogodzinę",
                                few = "{0} kilowatogodziny",
                                many = "{0} kilowatogodzin",
                                other = "{0} kilowatogodziny",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilowatogodziny",
                                few = "{0} kilowatogodzin",
                                many = "{0} kilowatogodzin",
                                other = "{0} kilowatogodziny",
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
                                one = "{0} elektronowolt",
                                few = "{0} elektronowolty",
                                many = "{0} elektronowoltów",
                                other = "{0} elektronowolta",
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
                                one = "{0} brytyjska jednostka ciepła",
                                few = "{0} brytyjskie jednostki ciepła",
                                many = "{0} brytyjskich jednostek ciepła",
                                other = "{0} brytyjskiej jednostki ciepła",
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
                                one = "{0} therm amerykański",
                                few = "{0} thermy amerykańskie",
                                many = "{0} thermów amerykańskich",
                                other = "{0} therma amerykańskiego",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} therm amer.",
                                few = "{0} thermy amer.",
                                many = "{0} thermów amer.",
                                other = "{0} therma amer.",
                            )
                        ),
                    )
                ),
            )
        override val energyKilogramSquareMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerSquareSecond formatter is not implemented yet for pl")
        override val forcePoundForce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} funt-siła",
                                few = "{0} funty-siły",
                                many = "{0} funtów-siły",
                                other = "{0} funta-siły",
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
                                one = "{0} niuton",
                                few = "{0} niutony",
                                many = "{0} niutonów",
                                other = "{0} niutona",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} niuton",
                                few = "{0} niutony",
                                many = "{0} niutonów",
                                other = "{0} niutona",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} niutona",
                                few = "{0} niutonów",
                                many = "{0} niutonów",
                                other = "{0} niutona",
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
                                one = "{0} kilowatogodzina na 100 km",
                                few = "{0} kilowatogodziny na 100 km",
                                many = "{0} kilowatogodzin na 100 km",
                                other = "{0} kilowatogodziny na 100 km",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilowatogodzinę na 100 km",
                                few = "{0} kilowatogodziny na 100 km",
                                many = "{0} kilowatogodzin na 100 km",
                                other = "{0} kilowatogodziny na 100 km",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilowatogodziny na 100 km",
                                few = "{0} kilowatogodzin na 100 km",
                                many = "{0} kilowatogodzin na 100 km",
                                other = "{0} kilowatogodziny na 100 km",
                            )
                        ),
                    )
                ),
            )
        override val forceKilogramMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramMeterPerSquareSecond formatter is not implemented yet for pl")
        override val frequencyGigahertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigaherc",
                                few = "{0} gigaherce",
                                many = "{0} gigaherców",
                                other = "{0} gigaherca",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gigaherc",
                                few = "{0} gigaherce",
                                many = "{0} gigaherców",
                                other = "{0} gigaherca",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} gigaherca",
                                few = "{0} gigaherców",
                                many = "{0} gigaherców",
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
                                few = "{0} megaherce",
                                many = "{0} megaherców",
                                other = "{0} megaherca",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megaherc",
                                few = "{0} megaherce",
                                many = "{0} megaherców",
                                other = "{0} megaherca",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} megaherca",
                                few = "{0} megaherców",
                                many = "{0} megaherców",
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
                                few = "{0} kiloherce",
                                many = "{0} kiloherców",
                                other = "{0} kiloherca",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kiloherc",
                                few = "{0} kiloherce",
                                many = "{0} kiloherców",
                                other = "{0} kiloherca",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kiloherca",
                                few = "{0} kiloherców",
                                many = "{0} kiloherców",
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
                                few = "{0} herce",
                                many = "{0} herców",
                                other = "{0} herca",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} herc",
                                few = "{0} herce",
                                many = "{0} herców",
                                other = "{0} herca",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} herca",
                                few = "{0} herców",
                                many = "{0} herców",
                                other = "{0} herca",
                            )
                        ),
                    )
                ),
            )
        override val frequencyRevolutionPerSecond: MultiLengthGenderedPattern
            get() = TODO("RevolutionPerSecond formatter is not implemented yet for pl")
        override val graphicsEm
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} firet",
                                few = "{0} firety",
                                many = "{0} firetów",
                                other = "{0} firetu",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} firet",
                                few = "{0} firety",
                                many = "{0} firetów",
                                other = "{0} firetu",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} firetu",
                                few = "{0} firetów",
                                many = "{0} firetów",
                                other = "{0} firetu",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} firet",
                                few = "{0} firety",
                                many = "{0} firetów",
                                other = "{0} firetu",
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
                                few = "{0} piksele",
                                many = "{0} pikseli",
                                other = "{0} piksela",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} piksel",
                                few = "{0} piksele",
                                many = "{0} pikseli",
                                other = "{0} piksela",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} piksela",
                                few = "{0} pikseli",
                                many = "{0} pikseli",
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
                                few = "{0} megapiksele",
                                many = "{0} megapikseli",
                                other = "{0} megapiksela",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megapiksel",
                                few = "{0} megapiksele",
                                many = "{0} megapikseli",
                                other = "{0} megapiksela",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} megapiksela",
                                few = "{0} megapikseli",
                                many = "{0} megapikseli",
                                other = "{0} megapiksela",
                            )
                        ),
                    )
                ),
            )
        override val graphicsDot: MultiLengthGenderedPattern
            get() = TODO("Dot formatter is not implemented yet for pl")
        override val graphicsPixelPerCentimeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} piksel na centymetr",
                                few = "{0} piksele na centymetr",
                                many = "{0} pikseli na centymetr",
                                other = "{0} piksela na centymetr",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} piksel na centymetr",
                                few = "{0} piksele na centymetr",
                                many = "{0} pikseli na centymetr",
                                other = "{0} piksela na centymetr",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} piksela na centymetr",
                                few = "{0} pikseli na centymetr",
                                many = "{0} pikseli na centymetr",
                                other = "{0} piksela na centymetr",
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
                                one = "{0} piksel na cal",
                                few = "{0} piksele na cal",
                                many = "{0} pikseli na cal",
                                other = "{0} piksela na cal",
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
                                one = "{0} punkt na centymetr",
                                few = "{0} punkty na centymetr",
                                many = "{0} punktów na centymetr",
                                other = "{0} punktu na centymetr",
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
                                one = "{0} punkt na cal",
                                few = "{0} punkty na cal",
                                many = "{0} punktów na cal",
                                other = "{0} punktu na cal",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixelPerMeter: MultiLengthGenderedPattern
            get() = TODO("PixelPerMeter formatter is not implemented yet for pl")
        override val lengthEarthRadius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} promień Ziemi",
                                few = "{0} promienie Ziemi",
                                many = "{0} promieni Ziemi",
                                other = "{0} promienia Ziemi",
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
                                one = "{0} kilometr",
                                few = "{0} kilometry",
                                many = "{0} kilometrów",
                                other = "{0} kilometra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilometr",
                                few = "{0} kilometry",
                                many = "{0} kilometrów",
                                other = "{0} kilometra",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} kilometrowi",
                                few = "{0} kilometrom",
                                many = "{0} kilometrom",
                                other = "{0} kilometrom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilometra",
                                few = "{0} kilometrów",
                                many = "{0} kilometrów",
                                other = "{0} kilometra",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilomentrem",
                                few = "{0} kilometrami",
                                many = "{0} kilometrami",
                                other = "{0} kilometrami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} kilometrze",
                                few = "{0} kilometrach",
                                many = "{0} kilometrach",
                                other = "{0} kilometrach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} kilometrze",
                                other = "{0} kilometrze",
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
                                one = "{0} metr",
                                few = "{0} metry",
                                many = "{0} metrów",
                                other = "{0} metra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} metr",
                                few = "{0} metry",
                                many = "{0} metrów",
                                other = "{0} metra",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} metrowi",
                                few = "{0} metrom",
                                many = "{0} metrom",
                                other = "{0} metrom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} metra",
                                few = "{0} metrów",
                                many = "{0} metrów",
                                other = "{0} metra",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} metrem",
                                few = "{0} metrami",
                                many = "{0} metrami",
                                other = "{0} metrami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} metrze",
                                few = "{0} metrach",
                                many = "{0} metrach",
                                other = "{0} metrach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} metrze",
                                other = "{0} metrze",
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
                                one = "{0} decymetr",
                                few = "{0} decymetry",
                                many = "{0} decymetrów",
                                other = "{0} decymetra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} decymetr",
                                few = "{0} decymetry",
                                many = "{0} decymetrów",
                                other = "{0} decymetra",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} decymetra",
                                few = "{0} decymetrów",
                                many = "{0} decymetrów",
                                other = "{0} decymetra",
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
                                one = "{0} centymetr",
                                few = "{0} centymetry",
                                many = "{0} centymetrów",
                                other = "{0} centymetra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} centymetr",
                                few = "{0} centymetry",
                                many = "{0} centymetrów",
                                other = "{0} centymetra",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} centymetrowi",
                                few = "{0} centymetrom",
                                many = "{0} centymetrom",
                                other = "{0} centymetrom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} centymetra",
                                few = "{0} centymetrów",
                                many = "{0} centymetrów",
                                other = "{0} centymetra",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} centymetrem",
                                few = "{0} centymetrami",
                                many = "{0} centymetrami",
                                other = "{0} centymetrami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} centymetrze",
                                few = "{0} centymetrach",
                                many = "{0} centymetrach",
                                other = "{0} centymetrach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} centymetrze",
                                other = "{0} centymetrze",
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
                                one = "{0} milimetr",
                                few = "{0} milimetry",
                                many = "{0} milimetrów",
                                other = "{0} milimetra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milimetr",
                                few = "{0} milimetry",
                                many = "{0} milimetrów",
                                other = "{0} milimetra",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} milimetrowi",
                                few = "{0} milimetrom",
                                many = "{0} milimetrom",
                                other = "{0} milimetrom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} milimetra",
                                few = "{0} milimetrów",
                                many = "{0} milimetrów",
                                other = "{0} milimetra",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milimentrem",
                                few = "{0} milimetrami",
                                many = "{0} milimetrami",
                                other = "{0} milimetrami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} milimetrze",
                                few = "{0} milimetrach",
                                many = "{0} milimetrach",
                                other = "{0} milimetrach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} milimetrrze",
                                other = "{0} milimetrrze",
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
                                one = "{0} mikrometr",
                                few = "{0} mikrometry",
                                many = "{0} mikrometrów",
                                other = "{0} mikrometra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mikrometr",
                                few = "{0} mikrometry",
                                many = "{0} mikrometrów",
                                other = "{0} mikrometra",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mikrometra",
                                few = "{0} mikrometrów",
                                many = "{0} mikrometrów",
                                other = "{0} mikrometra",
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
                                one = "{0} nanometr",
                                few = "{0} nanometry",
                                many = "{0} nanometrów",
                                other = "{0} nanometra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} nanometr",
                                few = "{0} nanometry",
                                many = "{0} nanometrów",
                                other = "{0} nanometra",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} nanometra",
                                few = "{0} nanometrów",
                                many = "{0} nanometrów",
                                other = "{0} nanometra",
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
                                one = "{0} pikometr",
                                few = "{0} pikometry",
                                many = "{0} pikometrów",
                                other = "{0} pikometra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} pikometr",
                                few = "{0} pikometry",
                                many = "{0} pikometrów",
                                other = "{0} pikometra",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} pikometrowi",
                                few = "{0} pikometrom",
                                many = "{0} pikometrom",
                                other = "{0} pikometrom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} pikometra",
                                few = "{0} pikometrów",
                                many = "{0} pikometrów",
                                other = "{0} pikometra",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} pikometrem",
                                few = "{0} pikometrami",
                                many = "{0} pikometrami",
                                other = "{0} pikometrami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} pikometrze",
                                few = "{0} pikometrach",
                                many = "{0} pikometrach",
                                other = "{0} pikometrach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} pikometrze",
                                other = "{0} pikometrze",
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
                                one = "{0} mila",
                                few = "{0} mile",
                                many = "{0} mil",
                                other = "{0} mili",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milę",
                                few = "{0} mile",
                                many = "{0} mil",
                                other = "{0} mili",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} mili",
                                few = "{0} milom",
                                many = "{0} milom",
                                other = "{0} milom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mili",
                                few = "{0} mil",
                                many = "{0} mil",
                                other = "{0} mili",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milą",
                                few = "{0} milami",
                                many = "{0} milami",
                                other = "{0} milami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} mili",
                                few = "{0} milach",
                                many = "{0} milach",
                                other = "{0} milach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} milo",
                                other = "{0} milo",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mila",
                                few = "{0} mile",
                                many = "{0} mil",
                                other = "{0} mili",
                            )
                        ),
                    )
                ),
            )
        override val lengthYard
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} jard",
                                few = "{0} jardy",
                                many = "{0} jardów",
                                other = "{0} jarda",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} jard",
                                few = "{0} jardy",
                                many = "{0} jardów",
                                other = "{0} jarda",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} jardowi",
                                few = "{0} jardom",
                                many = "{0} jardom",
                                other = "{0} jardom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} jardu",
                                few = "{0} jardów",
                                many = "{0} jardów",
                                other = "{0} jarda",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} jardem",
                                few = "{0} jardami",
                                many = "{0} jardami",
                                other = "{0} jardami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} jardzie",
                                few = "{0} jardach",
                                many = "{0} jardach",
                                other = "{0} jardach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} jardzie",
                                other = "{0} jardzie",
                            )
                        ),
                    )
                ),
            )
        override val lengthFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} stopa",
                                few = "{0} stopy",
                                many = "{0} stóp",
                                other = "{0} stopy",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} stopę",
                                few = "{0} stopy",
                                many = "{0} stóp",
                                other = "{0} stopy",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} stopie",
                                few = "{0} stopom",
                                many = "{0} stopom",
                                other = "{0} stopom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} stopy",
                                few = "{0} stóp",
                                many = "{0} stóp",
                                other = "{0} stopy",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} stopą",
                                few = "{0} stopami",
                                many = "{0} stopami",
                                other = "{0} stopami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} stopie",
                                few = "{0} stopach",
                                many = "{0} stopach",
                                other = "{0} stopach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} stopo",
                                other = "{0} stopo",
                            )
                        ),
                    )
                ),
            )
        override val lengthInch
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cal",
                                few = "{0} cale",
                                many = "{0} cali",
                                other = "{0} cala",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} cal",
                                few = "{0} cale",
                                many = "{0} cali",
                                other = "{0} cala",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} calowi",
                                few = "{0} calom",
                                many = "{0} calom",
                                other = "{0} calom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} cala",
                                few = "{0} cali",
                                many = "{0} cali",
                                other = "{0} cala",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} calem",
                                few = "{0} calami",
                                many = "{0} calami",
                                other = "{0} calami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} calu",
                                few = "{0} calach",
                                many = "{0} calach",
                                other = "{0} calach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} calu",
                                other = "{0} calu",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cal",
                                few = "{0} cale",
                                many = "{0} cali",
                                other = "{0} cala",
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
                                many = "{0}″",
                                other = "{0}″",
                            )
                        ),
                    )
                ),
            )
        override val lengthParsec
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} parsek",
                                few = "{0} parseki",
                                many = "{0} parseków",
                                other = "{0} parseka",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} parsek",
                                few = "{0} parseki",
                                many = "{0} parseków",
                                other = "{0} parseka",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} parsekowi",
                                few = "{0} parsekom",
                                many = "{0} parsekom",
                                other = "{0} parsekom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} parseka",
                                few = "{0} parseków",
                                many = "{0} parseków",
                                other = "{0} parseka",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} parsekiem",
                                few = "{0} parsekami",
                                many = "{0} parsekami",
                                other = "{0} parsekami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} parseku",
                                few = "{0} parsekach",
                                many = "{0} parsekach",
                                other = "{0} parsekach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} parseku",
                                other = "{0} parseku",
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
                                one = "{0} rok świetlny",
                                few = "{0} lata świetlne",
                                many = "{0} lat świetlnych",
                                other = "{0} roku świetlnego",
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
                                one = "{0} jednostka astronomiczna",
                                few = "{0} jednostki astronomiczne",
                                many = "{0} jednostek astronomicznych",
                                other = "{0} jednostki astronomicznej",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} j.a.",
                                few = "{0} j.a.",
                                many = "{0} j.a.",
                                other = "{0} j.a.",
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
                                few = "{0} furlongi",
                                many = "{0} furlongów",
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
                                one = "{0} sążeń",
                                few = "{0} sążnie",
                                many = "{0} sążni",
                                other = "{0} sążnia",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fm",
                                few = "{0} fm",
                                many = "{0} fm",
                                other = "{0} fm",
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
                                one = "{0} mila morska",
                                few = "{0} mile morskie",
                                many = "{0} mil morskich",
                                other = "{0} mili morskiej",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Mm",
                                few = "{0} Mm",
                                many = "{0} Mm",
                                other = "{0} Mm",
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
                                one = "{0} mila skandynawska",
                                few = "{0} mile skandynawskie",
                                many = "{0} mil skandynawskich",
                                other = "{0} mili skandynawskiej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milę skandynawską",
                                few = "{0} mile skandynawskie",
                                many = "{0} mil skandynawskich",
                                other = "{0} mili skandynawskiej",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mili skandynawskiej",
                                few = "{0} mil skandynawskich",
                                many = "{0} mil skandynawskich",
                                other = "{0} mili skandynawskiej",
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
                                one = "{0} punkt",
                                few = "{0} punkty",
                                many = "{0} punktów",
                                other = "{0} punktu",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pkt.",
                                few = "{0} pkt.",
                                many = "{0} pkt.",
                                other = "{0} pkt.",
                            )
                        ),
                    )
                ),
            )
        override val lengthSolarRadius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} promień Słońca",
                                few = "{0} promienie Słońca",
                                many = "{0} promieni Słońca",
                                other = "{0} promienia Słońca",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} promień Słońca",
                                few = "{0} promienie Słońca",
                                many = "{0} promieni Słońca",
                                other = "{0} promienia Słońca",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} promieniowi Słońca",
                                few = "{0} promieniom Słońca",
                                many = "{0} promieniom Słońca",
                                other = "{0} promieniom Słońca",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} promienia Słońca",
                                few = "{0} promieni Słońca",
                                many = "{0} promieni Słońca",
                                other = "{0} promienia Słońca",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} promieniem Słońca",
                                few = "{0} promieniami Słońca",
                                many = "{0} promieniami Słońca",
                                other = "{0} promieniami Słońca",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} promieniu Słońca",
                                few = "{0} promieniach Słońca",
                                many = "{0} promieniach Słońca",
                                other = "{0} promieniach Słońca",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} promieniu Słońca",
                                other = "{0} promieniu Słońca",
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
                                few = "{0} luksy",
                                many = "{0} luksów",
                                other = "{0} luksu",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} luks",
                                few = "{0} luksy",
                                many = "{0} luksów",
                                other = "{0} luksu",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} luksu",
                                few = "{0} luksów",
                                many = "{0} luksów",
                                other = "{0} luksu",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaPerSquareMeter formatter is not implemented yet for pl")
        override val lightCandela
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kandela",
                                few = "{0} kandele",
                                many = "{0} kandeli",
                                other = "{0} kandeli",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kandelę",
                                few = "{0} kandele",
                                many = "{0} kandeli",
                                other = "{0} kandeli",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kandeli",
                                few = "{0} kandeli",
                                many = "{0} kandeli",
                                other = "{0} kandeli",
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
                                few = "{0} lumeny",
                                many = "{0} lumenów",
                                other = "{0} lumena",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} lumen",
                                few = "{0} lumeny",
                                many = "{0} lumenów",
                                other = "{0} lumena",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} lumena",
                                few = "{0} lumenów",
                                many = "{0} lumenów",
                                other = "{0} lumena",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaSquareMeterPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaSquareMeterPerSquareMeter formatter is not implemented yet for pl")
        override val lightSolarLuminosity
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} jasność Słońca",
                                few = "{0} jasności Słońca",
                                many = "{0} jasności Słońca",
                                other = "{0} jasności Słońca",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} jasność Słońca",
                                few = "{0} jasności Słońca",
                                many = "{0} jasności Słońca",
                                other = "{0} jasności Słońca",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} jasności Słońca",
                                few = "{0} jasnościom Słońca",
                                many = "{0} jasnościom Słońca",
                                other = "{0} jasnościom Słońca",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} jasności Słońca",
                                few = "{0} jasności Słońca",
                                many = "{0} jasności Słońca",
                                other = "{0} jasności Słońca",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} jasnością Słońca",
                                few = "{0} jasnościami Słońca",
                                many = "{0} jasnościami Słońca",
                                other = "{0} jasnościami Słońca",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} jasności Słońca",
                                few = "{0} jasnościach Słońca",
                                many = "{0} jasnościach Słońca",
                                other = "{0} jasnościach Słońca",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} jasności Słońca",
                                other = "{0} jasności Słońca",
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
                                few = "{0} tony",
                                many = "{0} ton",
                                other = "{0} tony",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} tonę",
                                few = "{0} tony",
                                many = "{0} ton",
                                other = "{0} tony",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} tony",
                                few = "{0} ton",
                                many = "{0} ton",
                                other = "{0} tony",
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
                                few = "{0} kilogramy",
                                many = "{0} kilogramów",
                                other = "{0} kilograma",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilogram",
                                few = "{0} kilogramy",
                                many = "{0} kilogramów",
                                other = "{0} kilograma",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} kilogramowi",
                                few = "{0} kilogramom",
                                many = "{0} kilogramom",
                                other = "{0} kilogramom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilograma",
                                few = "{0} kilogramów",
                                many = "{0} kilogramów",
                                other = "{0} kilograma",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilogramem",
                                few = "{0} kilogramami",
                                many = "{0} kilogramami",
                                other = "{0} kilogramami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} kilogramie",
                                few = "{0} kilogramach",
                                many = "{0} kilogramach",
                                other = "{0} kilogramach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} kilogramie",
                                other = "{0} kilogramie",
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
                                few = "{0} gramy",
                                many = "{0} gramów",
                                other = "{0} grama",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gram",
                                few = "{0} gramy",
                                many = "{0} gramów",
                                other = "{0} grama",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} gramowi",
                                few = "{0} gramom",
                                many = "{0} gramom",
                                other = "{0} gramom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} grama",
                                few = "{0} gramów",
                                many = "{0} gramów",
                                other = "{0} grama",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} gramem",
                                few = "{0} gramami",
                                many = "{0} gramami",
                                other = "{0} gramami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} gramie",
                                few = "{0} gramach",
                                many = "{0} gramach",
                                other = "{0} gramach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} gramie",
                                other = "{0} gramie",
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
                                few = "{0} miligramy",
                                many = "{0} miligramów",
                                other = "{0} miligrama",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} miligram",
                                few = "{0} miligramy",
                                many = "{0} miligramów",
                                other = "{0} miligrama",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} miligramowi",
                                few = "{0} miligramom",
                                many = "{0} miligramom",
                                other = "{0} miligramom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} miligrama",
                                few = "{0} miligramów",
                                many = "{0} miligramów",
                                other = "{0} miligrama",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} miligramem",
                                few = "{0} miligramami",
                                many = "{0} miligramami",
                                other = "{0} miligramami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} miligramie",
                                few = "{0} miligramach",
                                many = "{0} miligramach",
                                other = "{0} miligramach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} miligramie",
                                other = "{0} miligramie",
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
                                few = "{0} mikrogramy",
                                many = "{0} mikrogramów",
                                other = "{0} mikrograma",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mikrogram",
                                few = "{0} mikrogramy",
                                many = "{0} mikrogramów",
                                other = "{0} mikrograma",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mikrograma",
                                few = "{0} mikrogramów",
                                many = "{0} mikrogramów",
                                other = "{0} mikrograma",
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
                                one = "{0} krótka tona",
                                few = "{0} krótkie tony",
                                many = "{0} krótkich ton",
                                other = "{0} krótkiej tony",
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
                                one = "{0} kamień",
                                few = "{0} kamienie",
                                many = "{0} kamieni",
                                other = "{0} kamienia",
                            )
                        ),
                    )
                ),
            )
        override val massPound
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} funt",
                                few = "{0} funty",
                                many = "{0} funtów",
                                other = "{0} funta",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} funt",
                                few = "{0} funty",
                                many = "{0} funtów",
                                other = "{0} funta",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} funtowi",
                                few = "{0} funtom",
                                many = "{0} funtom",
                                other = "{0} funtom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} funta",
                                few = "{0} funtów",
                                many = "{0} funtów",
                                other = "{0} funta",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} funtem",
                                few = "{0} funtami",
                                many = "{0} funtami",
                                other = "{0} funtami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} funcie",
                                few = "{0} funtach",
                                many = "{0} funtach",
                                other = "{0} funtach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} funcie",
                                other = "{0} funcie",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} funt",
                                few = "{0} funty",
                                many = "{0} funtów",
                                other = "{0} funta",
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
                                one = "{0} uncja",
                                few = "{0} uncje",
                                many = "{0} uncji",
                                other = "{0} uncji",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} uncję",
                                few = "{0} uncje",
                                many = "{0} uncji",
                                other = "{0} uncji",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} uncji",
                                few = "{0} uncjom",
                                many = "{0} uncjom",
                                other = "{0} uncjom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} uncji",
                                few = "{0} uncji",
                                many = "{0} uncji",
                                other = "{0} uncji",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} uncją",
                                few = "{0} uncjami",
                                many = "{0} uncjami",
                                other = "{0} uncjami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} uncji",
                                few = "{0} uncjach",
                                many = "{0} uncjach",
                                other = "{0} uncjach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} uncjo",
                                other = "{0} uncjo",
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
                                one = "{0} uncja trojańska",
                                few = "{0} uncje trojańskie",
                                many = "{0} uncji trojańskich",
                                other = "{0} uncji trojańskiej",
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
                                few = "{0} karaty",
                                many = "{0} karatów",
                                other = "{0} karata",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} karat",
                                few = "{0} karaty",
                                many = "{0} karatów",
                                other = "{0} karata",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} karata",
                                few = "{0} karatów",
                                many = "{0} karatów",
                                other = "{0} karata",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kt",
                                few = "{0} kt",
                                many = "{0} kt",
                                other = "{0} kt",
                            )
                        ),
                    )
                ),
            )
        override val massDalton
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} jednostka masy atomowej",
                                few = "{0} jednostki masy atomowej",
                                many = "{0} jednostek masy atomowej",
                                other = "{0} jednostki masy atomowej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} jednostkę masy atomowej",
                                few = "{0} jednostki masy atomowej",
                                many = "{0} jednostek masy atomowej",
                                other = "{0} jednostki masy atomowej",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} jednostce masy atomowej",
                                few = "{0} jednostkom masy atomowej",
                                many = "{0} jednostkom masy atomowej",
                                other = "{0} jednostkom masy atomowej",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} jednostki masy atomowej",
                                few = "{0} jednostek masy atomowej",
                                many = "{0} jednostek masy atomowej",
                                other = "{0} jednostki masy atomowej",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} jednostką masy atomowej",
                                few = "{0} jednostkami masy atomowej",
                                many = "{0} jednostkami masy atomowej",
                                other = "{0} jednostkami masy atomowej",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} jednostce masy atomowej",
                                few = "{0} jednostkach masy atomowej",
                                many = "{0} jednostkach masy atomowej",
                                other = "{0} jednostkach masy atomowej",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} jednostko masy atomowej",
                                other = "{0} jednostko masy atomowej",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} u",
                                few = "{0} u",
                                many = "{0} u",
                                other = "{0} u",
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
                                one = "{0} masa Ziemi",
                                few = "{0} masy Ziemi",
                                many = "{0} mas Ziemi",
                                other = "{0} masy Ziemi",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} masę Ziemi",
                                few = "{0} masy Ziemi",
                                many = "{0} mas Ziemi",
                                other = "{0} masy Ziemi",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} masie Ziemi",
                                few = "{0} masom Ziemi",
                                many = "{0} masom Ziemi",
                                other = "{0} masom Ziemi",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} masy Ziemi",
                                few = "{0} mas Ziemi",
                                many = "{0} mas Ziemi",
                                other = "{0} masy Ziemi",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} masą Ziemi",
                                few = "{0} masami Ziemi",
                                many = "{0} masami Ziemi",
                                other = "{0} masami Ziemi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} masie Ziemi",
                                few = "{0} masach Ziemi",
                                many = "{0} masach Ziemi",
                                other = "{0} masach Ziemi",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} maso Ziemi",
                                other = "{0} maso Ziemi",
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
                                one = "{0} masa Słońca",
                                few = "{0} masy Słońca",
                                many = "{0} mas Słońca",
                                other = "{0} masy Słońca",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} masę Słońca",
                                few = "{0} masy Słońca",
                                many = "{0} mas Słońca",
                                other = "{0} masy Słońca",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} masie Słońca",
                                few = "{0} masom Słońca",
                                many = "{0} masom Słońca",
                                other = "{0} masom Słońca",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} masy Słońca",
                                few = "{0} mas Słońca",
                                many = "{0} mas Słońca",
                                other = "{0} masy Słońca",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} masą Słońca",
                                few = "{0} masami Słońca",
                                many = "{0} masami Słońca",
                                other = "{0} masami Słońca",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} masie Słońca",
                                few = "{0} masach Słońca",
                                many = "{0} masach Słońca",
                                other = "{0} masach Słońca",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} maso Słońca",
                                other = "{0} maso Słońca",
                            )
                        ),
                    )
                ),
            )
        override val massGrain
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gran",
                                few = "{0} grany",
                                many = "{0} granów",
                                other = "{0} grana",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gran",
                                few = "{0} grany",
                                many = "{0} granów",
                                other = "{0} grana",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} granowi",
                                few = "{0} granom",
                                many = "{0} granom",
                                other = "{0} granom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} grana",
                                few = "{0} granów",
                                many = "{0} granów",
                                other = "{0} grana",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} granem",
                                few = "{0} granami",
                                many = "{0} granami",
                                other = "{0} granami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} granie",
                                few = "{0} granach",
                                many = "{0} granach",
                                other = "{0} granach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} granie",
                                other = "{0} granie",
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
                                many = "{0} gr",
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
                                one = "{0} gigawat",
                                few = "{0} gigawaty",
                                many = "{0} gigawatów",
                                other = "{0} gigawata",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gigawat",
                                few = "{0} gigawaty",
                                many = "{0} gigawatów",
                                other = "{0} gigawata",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} gigawata",
                                few = "{0} gigawatów",
                                many = "{0} gigawatów",
                                other = "{0} gigawata",
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
                                one = "{0} megawat",
                                few = "{0} megawaty",
                                many = "{0} megawatów",
                                other = "{0} megawata",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megawat",
                                few = "{0} megawaty",
                                many = "{0} megawatów",
                                other = "{0} megawata",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} megawata",
                                few = "{0} megawatów",
                                many = "{0} megawatów",
                                other = "{0} megawata",
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
                                one = "{0} kilowat",
                                few = "{0} kilowaty",
                                many = "{0} kilowatów",
                                other = "{0} kilowata",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilowat",
                                few = "{0} kilowaty",
                                many = "{0} kilowatów",
                                other = "{0} kilowata",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilowata",
                                few = "{0} kilowatów",
                                many = "{0} kilowatów",
                                other = "{0} kilowata",
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
                                one = "{0} wat",
                                few = "{0} waty",
                                many = "{0} watów",
                                other = "{0} wata",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} wat",
                                few = "{0} waty",
                                many = "{0} watów",
                                other = "{0} wata",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} wata",
                                few = "{0} watów",
                                many = "{0} watów",
                                other = "{0} wata",
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
                                one = "{0} miliwat",
                                few = "{0} miliwaty",
                                many = "{0} miliwatów",
                                other = "{0} miliwata",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} miliwat",
                                few = "{0} miliwaty",
                                many = "{0} miliwatów",
                                other = "{0} miliwata",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} miliwata",
                                few = "{0} miliwatów",
                                many = "{0} miliwatów",
                                other = "{0} miliwata",
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
                                one = "{0} koń mechaniczny",
                                few = "{0} konie mechaniczne",
                                many = "{0} koni mechanicznych",
                                other = "{0} konia mechanicznego",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} KM",
                                few = "{0} KM",
                                many = "{0} KM",
                                other = "{0} KM",
                            )
                        ),
                    )
                ),
            )
        override val powerKilogramSquareMeterPerCubicSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecond formatter is not implemented yet for pl")
        override val pressureMillimeterOfhg
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milimetr słupa rtęci",
                                few = "{0} milimetry słupa rtęci",
                                many = "{0} milimetrów słupa rtęci",
                                other = "{0} milimetra słupa rtęci",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mmHg",
                                few = "{0} mmHg",
                                many = "{0} mmHg",
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
                                one = "{0} funt na cal kwadratowy",
                                few = "{0} funty na cal kwadratowy",
                                many = "{0} funtów na cal kwadratowy",
                                other = "{0} funta na cal kwadratowy",
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
                                one = "{0} cal słupa rtęci",
                                few = "{0} cale słupa rtęci",
                                many = "{0} cali słupa rtęci",
                                other = "{0} cala słupa rtęci",
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
                                few = "{0} bary",
                                many = "{0} barów",
                                other = "{0} bara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} bar",
                                few = "{0} bary",
                                many = "{0} barów",
                                other = "{0} bara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} bara",
                                few = "{0} barów",
                                many = "{0} barów",
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
                                one = "{0} millibar",
                                few = "{0} millibary",
                                many = "{0} millibarów",
                                other = "{0} millibara",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} millibar",
                                few = "{0} millibary",
                                many = "{0} millibarów",
                                other = "{0} millibara",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} milibara",
                                few = "{0} milibarów",
                                many = "{0} millibarów",
                                other = "{0} millibara",
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
                                few = "{0} atmosfery",
                                many = "{0} atmosfer",
                                other = "{0} atmosfery",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} atmosferę",
                                few = "{0} atmosfery",
                                many = "{0} atmosfer",
                                other = "{0} atmosfery",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} atmosfery",
                                few = "{0} atmosfer",
                                many = "{0} atmosfer",
                                other = "{0} atmosfery",
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
                                few = "{0} paskale",
                                many = "{0} paskali",
                                other = "{0} paskala",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} paskal",
                                few = "{0} paskale",
                                many = "{0} paskali",
                                other = "{0} paskala",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} paskala",
                                few = "{0} paskali",
                                many = "{0} paskali",
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
                                few = "{0} hektopaskale",
                                many = "{0} hektopaskali",
                                other = "{0} hektopaskala",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} hektopaskal",
                                few = "{0} hektopaskale",
                                many = "{0} hektopaskali",
                                other = "{0} hektopaskala",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} hektopaskala",
                                few = "{0} hektopaskali",
                                many = "{0} hektopaskali",
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
                                few = "{0} kilopaskale",
                                many = "{0} kilopaskali",
                                other = "{0} kilopaskala",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilopaskal",
                                few = "{0} kilopaskale",
                                many = "{0} kilopaskali",
                                other = "{0} kilopaskala",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} kilopaskalowi",
                                few = "{0} kilopaskalom",
                                many = "{0} kilopaskalom",
                                other = "{0} kilopaskalom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilopaskala",
                                few = "{0} kilopaskali",
                                many = "{0} kilopaskali",
                                other = "{0} kilopaskala",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilopaskalem",
                                few = "{0} kilopaskalami",
                                many = "{0} kilopaskalami",
                                other = "{0} kilopaskalami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} kilopaskalu",
                                few = "{0} kilopaskalach",
                                many = "{0} kilopaskalach",
                                other = "{0} kilopaskalach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} kilopaskalu",
                                other = "{0} kilopaskalu",
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
                                few = "{0} megapaskale",
                                many = "{0} megapaskali",
                                other = "{0} megapaskala",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megapaskal",
                                few = "{0} megapaskale",
                                many = "{0} megapaskali",
                                other = "{0} megapaskala",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} megapaskala",
                                few = "{0} megapaskali",
                                many = "{0} megapaskali",
                                other = "{0} megapaskala",
                            )
                        ),
                    )
                ),
            )
        override val pressureGasolineEnergyDensity: MultiLengthGenderedPattern
            get() = TODO("GasolineEnergyDensity formatter is not implemented yet for pl")
        override val pressureKilogramPerMeterSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramPerMeterSquareSecond formatter is not implemented yet for pl")
        override val speedKilometerPerHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilometr na godzinę",
                                few = "{0} kilometry na godzinę",
                                many = "{0} kilometrów na godzinę",
                                other = "{0} kilometra na godzinę",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilometr na godzinę",
                                few = "{0} kilometry na godzinę",
                                many = "{0} kilometrów na godzinę",
                                other = "{0} kilometra na godzinę",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} kilometrowi na godzinę",
                                few = "{0} kilometrom na godzinę",
                                many = "{0} kilometrom na godzinę",
                                other = "{0} kilometrom na godzinę",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilometra na godzinę",
                                few = "{0} kilometrów na godzinę",
                                many = "{0} kilometrów na godzinę",
                                other = "{0} kilometra na godzinę",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilometrem na godzinę",
                                few = "{0} kilometrami na godzinę",
                                many = "{0} kilometrami na godzinę",
                                other = "{0} kilometrami na godzinę",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} kilometrze na godzinę",
                                few = "{0} kilometrach na godzinę",
                                many = "{0} kilometrach na godzinę",
                                other = "{0} kilometrach na godzinę",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} kilometrze na godzinę",
                                other = "{0} kilometrze na godzinę",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} km/godz.",
                                few = "{0} km/godz.",
                                many = "{0} km/godz.",
                                other = "{0} km/godz.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} km/h",
                                few = "{0} km/h",
                                many = "{0} km/h",
                                other = "{0}km/h",
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
                                one = "{0} metr na sekundę",
                                few = "{0} metry na sekundę",
                                many = "{0} metrów na sekundę",
                                other = "{0} metra na sekundę",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} metr na sekundę",
                                few = "{0} metry na sekundę",
                                many = "{0} metrów na sekundę",
                                other = "{0} metra na sekundę",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} metrowi na sekundę",
                                few = "{0} metrom na sekundę",
                                many = "{0} metrom na sekundę",
                                other = "{0} metrom na sekundę",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} metra na sekundę",
                                few = "{0} metrów na sekundę",
                                many = "{0} metrów na sekundę",
                                other = "{0} metra na sekundę",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} metrem na sekundę",
                                few = "{0} metrami na sekundę",
                                many = "{0} metrami na sekundę",
                                other = "{0} metrami na sekundę",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} metrze na sekundę",
                                few = "{0} metrach na sekundę",
                                many = "{0} metrach na sekundę",
                                other = "{0} metrach na sekundę",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} metrze na sekundę",
                                other = "{0} metrze na sekundę",
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
                                one = "{0} mila na godzinę",
                                few = "{0} mile na godzinę",
                                many = "{0} mil na godzinę",
                                other = "{0} mili na godzinę",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milę na godzinę",
                                few = "{0} mile na godzinę",
                                many = "{0} mil na godzinę",
                                other = "{0} mili na godzinę",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} mili na godzinę",
                                few = "{0} milom na godzinę",
                                many = "{0} milom na godzinę",
                                other = "{0} milom na godzinę",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mili na godzinę",
                                few = "{0} mil na godzinę",
                                many = "{0} mil na godzinę",
                                other = "{0} mili na godzinę",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milą na godzinę",
                                few = "{0} milami na godzinę",
                                many = "{0} milami na godzinę",
                                other = "{0} milami na godzinę",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} mili na godzinę",
                                few = "{0} milach na godzinę",
                                many = "{0} milach na godzinę",
                                other = "{0} milach na godzinę",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} milo na godzinę",
                                other = "{0} milo na godzinę",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mila/h",
                                few = "{0} mile/h",
                                many = "{0} mil/h",
                                other = "{0} mili/h",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mph",
                                few = "{0} mph",
                                many = "{0} mph",
                                other = "{0} mph",
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
                                one = "{0} węzeł",
                                few = "{0} węzły",
                                many = "{0} węzłów",
                                other = "{0} węzła",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} w.",
                                few = "{0} w.",
                                many = "{0} w.",
                                other = "{0} w.",
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
                                one = "{0} w skali Beauforta",
                                few = "{0} w skali Beauforta",
                                many = "{0} w skali Beauforta",
                                other = "{0} w skali Beauforta",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Bft",
                                few = "{0} Bft",
                                many = "{0} Bft",
                                other = "{0} Bft",
                            )
                        ),
                    )
                ),
            )
        override val temperatureGeneric
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} stopień",
                                few = "{0} stopnie",
                                many = "{0} stopni",
                                other = "{0} stopnia",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} stopień",
                                few = "{0} stopnie",
                                many = "{0} stopni",
                                other = "{0} stopnia",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} stopniowi",
                                few = "{0} stopniom",
                                many = "{0} stopniom",
                                other = "{0} stopniom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} stopnia",
                                few = "{0} stopni",
                                many = "{0} stopni",
                                other = "{0} stopnia",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} stopniem",
                                few = "{0} stopniami",
                                many = "{0} stopniami",
                                other = "{0} stopniami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} stopniu",
                                few = "{0} stopniach",
                                many = "{0} stopniach",
                                other = "{0} stopniach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} stopniu",
                                other = "{0} stopniu",
                            )
                        ),
                    )
                ),
            )
        override val temperatureCelsius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} stopień Celsjusza",
                                few = "{0} stopnie Celsjusza",
                                many = "{0} stopni Celsjusza",
                                other = "{0} stopnia Celsjusza",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} stopień Celsjusza",
                                few = "{0} stopnie Celsjusza",
                                many = "{0} stopni Celsjusza",
                                other = "{0} stopnia Celsjusza",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} stopniowi Celsjusza",
                                few = "{0} stopniom Celsjusza",
                                many = "{0} stopniom Celsjusza",
                                other = "{0} stopniom Celsjusza",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} stopnia Celsjusza",
                                few = "{0} stopni Celsjusza",
                                many = "{0} stopni Celsjusza",
                                other = "{0} stopnia Celsjusza",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} stopniem Celsjusza",
                                few = "{0} stopniami Celsjusza",
                                many = "{0} stopniami Celsjusza",
                                other = "{0} stopniami Celsjusza",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} stopniu Celsjusza",
                                few = "{0} stopniach Celsjusza",
                                many = "{0} stopniach Celsjusza",
                                other = "{0} stopniach Celsjusza",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} stopniu Celsjusza",
                                other = "{0} stopniu Celsjusza",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} st. C",
                                few = "{0} st. C",
                                many = "{0} st. C",
                                other = "{0} st. C",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}°C",
                                few = "{0}°C",
                                many = "{0}°C",
                                other = "{0}°C",
                            )
                        ),
                    )
                ),
            )
        override val temperatureFahrenheit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} stopień Fahrenheita",
                                few = "{0} stopnie Fahrenheita",
                                many = "{0} stopni Fahrenheita",
                                other = "{0} stopnia Fahrenheita",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} stopień Fahrenheita",
                                few = "{0} stopnie Fahrenheita",
                                many = "{0} stopni Fahrenheita",
                                other = "{0} stopnia Fahrenheita",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} stopniowi Fahrenheita",
                                few = "{0} stopniom Fahrenheita",
                                many = "{0} stopniom Fahrenheita",
                                other = "{0} stopniom Fahrenheita",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} stopnia Fahrenheita",
                                few = "{0} stopni Fahrenheita",
                                many = "{0} stopni Fahrenheita",
                                other = "{0} stopnia Fahrenheita",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} stopniem Fahrenheita",
                                few = "{0} stopniami Fahrenheita",
                                many = "{0} stopniami Fahrenheita",
                                other = "{0} stopniami Fahrenheita",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} stopniu Fahrenheita",
                                few = "{0} stopniach Fahrenheita",
                                many = "{0} stopniach Fahrenheita",
                                other = "{0} stopniach Fahrenheita",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} stopniu Fahrenheita",
                                other = "{0} stopniu Fahrenheita",
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
                                one = "{0} kelwin",
                                few = "{0} kelwiny",
                                many = "{0} kelwinów",
                                other = "{0} kelwina",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kelwin",
                                few = "{0} kelwiny",
                                many = "{0} kelwinów",
                                other = "{0} kelwina",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} kelwinowi",
                                few = "{0} kelwinom",
                                many = "{0} kelwinom",
                                other = "{0} kelwinom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kelwina",
                                few = "{0} kelwinów",
                                many = "{0} kelwinów",
                                other = "{0} kelwina",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kelwinem",
                                few = "{0} kelwinami",
                                many = "{0} kelwinami",
                                other = "{0} kelwinami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} kelwinie",
                                few = "{0} kelwinach",
                                many = "{0} kelwinach",
                                other = "{0} kelwinach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} kelwinie",
                                other = "{0} kelwinie",
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
                                one = "{0} stopofunt",
                                few = "{0} stopofunty",
                                many = "{0} stopofuntów",
                                other = "{0} stopofunta",
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
                                one = "{0} niutonometr",
                                few = "{0} niutonometry",
                                many = "{0} niutonometrów",
                                other = "{0} niutonometra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} niutonometr",
                                few = "{0} niutonometry",
                                many = "{0} niutonometrów",
                                other = "{0} niutonometra",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} niutonometra",
                                few = "{0} niutonometrów",
                                many = "{0} niutonometrów",
                                other = "{0} niutonometra",
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
                                one = "{0} kilometr sześcienny",
                                few = "{0} kilometry sześcienne",
                                many = "{0} kilometrów sześciennych",
                                other = "{0} kilometra sześciennego",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilometr sześcienny",
                                few = "{0} kilometry sześcienne",
                                many = "{0} kilometrów sześciennych",
                                other = "{0} kilometra sześciennego",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kilometra sześciennego",
                                few = "{0} kilometrów sześciennych",
                                many = "{0} kilometrów sześciennych",
                                other = "{0} kilometra sześciennego",
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
                                one = "{0} metr sześcienny",
                                few = "{0} metry sześcienne",
                                many = "{0} metrów sześciennych",
                                other = "{0} metra sześciennego",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} metr sześcienny",
                                few = "{0} metry sześcienne",
                                many = "{0} metrów sześciennych",
                                other = "{0} metra sześciennego",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} metra sześciennego",
                                few = "{0} metrów sześciennych",
                                many = "{0} metrów sześciennych",
                                other = "{0} metra sześciennego",
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
                                one = "{0} centymetr sześcienny",
                                few = "{0} centymetry sześcienne",
                                many = "{0} centymetrów sześciennych",
                                other = "{0} centymetra sześciennego",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} centymetr sześcienny",
                                few = "{0} centymetry sześcienne",
                                many = "{0} centymetrów sześciennych",
                                other = "{0} centymetra sześciennego",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} centymetrowi sześciennemu",
                                few = "{0} centymetrom sześciennym",
                                many = "{0} centymetrom sześciennym",
                                other = "{0} centymetrom sześciennym",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} centymetra sześciennego",
                                few = "{0} centymetrów sześciennych",
                                many = "{0} centymetrów sześciennych",
                                other = "{0} centymetra sześciennego",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} centymetrem sześciennym",
                                few = "{0} centymetrami sześciennymi",
                                many = "{0} centymetrami sześciennymi",
                                other = "{0} centymetrami sześciennymi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} centymetrze sześciennym",
                                few = "{0} centymetrach sześciennych",
                                many = "{0} centymetrach sześciennych",
                                other = "{0} centymetrach sześciennych",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} centymetrze sześcienny",
                                other = "{0} centymetrze sześcienny",
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
                                one = "{0} mila sześcienna",
                                few = "{0} mile sześcienne",
                                many = "{0} mil sześciennych",
                                other = "{0} mili sześciennej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milę sześcienną",
                                few = "{0} mile sześcienne",
                                many = "{0} mil sześciennych",
                                other = "{0} mili sześciennej",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} mili sześciennej",
                                few = "{0} milom sześciennym",
                                many = "{0} milom sześciennym",
                                other = "{0} milom sześciennym",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mili sześciennej",
                                few = "{0} mil sześciennych",
                                many = "{0} mil sześciennych",
                                other = "{0} mili sześciennej",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milą sześcienną",
                                few = "{0} milami sześciennymi",
                                many = "{0} milami sześciennymi",
                                other = "{0} milami sześciennymi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} mili sześciennej",
                                few = "{0} milach sześciennych",
                                many = "{0} milach sześciennych",
                                other = "{0} milach sześciennych",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} milo sześcienna",
                                other = "{0} milo sześcienna",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mila sześc.",
                                few = "{0} mile sześc.",
                                many = "{0} mil sześc.",
                                other = "{0} mili sześc.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mi³",
                                few = "{0} mi³",
                                many = "{0} mi³",
                                other = "{0} mi³",
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
                                one = "{0} jard sześcienny",
                                few = "{0} jardy sześcienne",
                                many = "{0} jardów sześciennych",
                                other = "{0} jarda sześciennego",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} jard sześc.",
                                few = "{0} jardy sześc.",
                                many = "{0} jardów sześc.",
                                other = "{0} jarda sześc.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} yd³",
                                few = "{0} yd³",
                                many = "{0} yd³",
                                other = "{0} yd³",
                            )
                        ),
                    )
                ),
            )
        override val volumeCubicFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} stopa sześcienna",
                                few = "{0} stopy sześcienne",
                                many = "{0} stóp sześciennych",
                                other = "{0} stopy sześciennej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} stopę sześcienną",
                                few = "{0} stopy sześcienne",
                                many = "{0} stóp sześciennych",
                                other = "{0} stopy sześciennej",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} stopie sześciennej",
                                few = "{0} stopom sześciennym",
                                many = "{0} stopom sześciennym",
                                other = "{0} stopom sześciennym",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} stopy sześciennej",
                                few = "{0} stóp sześciennych",
                                many = "{0} stóp sześciennych",
                                other = "{0} stopy sześciennej",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} stopą sześcienną",
                                few = "{0} stopami sześciennymi",
                                many = "{0} stopami sześciennymi",
                                other = "{0} stopami sześciennymi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} stopie sześciennej",
                                few = "{0} stopach sześciennych",
                                many = "{0} stopach sześciennych",
                                other = "{0} stopach sześciennych",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} stopo sześcienna",
                                other = "{0} stopo sześcienna",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} stopa sześc.",
                                few = "{0} stopy sześc.",
                                many = "{0} stóp sześc.",
                                other = "{0} stopy sześc.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ft³",
                                few = "{0} ft³",
                                many = "{0} ft³",
                                other = "{0} ft³",
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
                                one = "{0} cal sześcienny",
                                few = "{0} cale sześcienne",
                                many = "{0} cali sześciennych",
                                other = "{0} cala sześciennego",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cal sześc.",
                                few = "{0} cale sześc.",
                                many = "{0} cali sześc.",
                                other = "{0} cala sześc.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} in³",
                                few = "{0} in³",
                                many = "{0} in³",
                                other = "{0} in³",
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
                                one = "{0} megalitr",
                                few = "{0} megalitry",
                                many = "{0} megalitrów",
                                other = "{0} megalitra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megalitr",
                                few = "{0} megalitry",
                                many = "{0} megalitrów",
                                other = "{0} megalitra",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} megalitra",
                                few = "{0} megalitrów",
                                many = "{0} megalitrów",
                                other = "{0} megalitra",
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
                                many = "{0} Ml",
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
                                one = "{0} hektolitr",
                                few = "{0} hektolitry",
                                many = "{0} hektolitrów",
                                other = "{0} hektolitra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} hektolitr",
                                few = "{0} hektolitry",
                                many = "{0} hektolitrów",
                                other = "{0} hektolitra",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} hektolitra",
                                few = "{0} hektolitrów",
                                many = "{0} hektolitrów",
                                other = "{0} hektolitra",
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
                                many = "{0} hl",
                                other = "{0} hl",
                            )
                        ),
                    )
                ),
            )
        override val volumeLiter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} litr",
                                few = "{0} litry",
                                many = "{0} litrów",
                                other = "{0} litra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} litr",
                                few = "{0} litry",
                                many = "{0} litrów",
                                other = "{0} litra",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} litrowi",
                                few = "{0} litrom",
                                many = "{0} litrom",
                                other = "{0} litrom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} litra",
                                few = "{0} litrów",
                                many = "{0} litrów",
                                other = "{0} litra",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} litrem",
                                few = "{0} litrami",
                                many = "{0} litrami",
                                other = "{0} litrami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} litrze",
                                few = "{0} litrach",
                                many = "{0} litrach",
                                other = "{0} litrach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} litrze",
                                other = "{0} litrze",
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
                                one = "{0} decylitr",
                                few = "{0} decylitry",
                                many = "{0} decylitrów",
                                other = "{0} decylitra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} decylitr",
                                few = "{0} decylitry",
                                many = "{0} decylitrów",
                                other = "{0} decylitra",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} decylitrowi",
                                few = "{0} decylitrom",
                                many = "{0} decylitrom",
                                other = "{0} decylitrom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} decylitra",
                                few = "{0} decylitrów",
                                many = "{0} decylitrów",
                                other = "{0} decylitra",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} decylitrem",
                                few = "{0} decylitrami",
                                many = "{0} decylitrami",
                                other = "{0} decylitrami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} decylitrze",
                                few = "{0} decylitrach",
                                many = "{0} decylitrach",
                                other = "{0} decylitrach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} decylitrze",
                                other = "{0} decylitrze",
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
                                many = "{0} dl",
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
                                one = "{0} centylitr",
                                few = "{0} centylitry",
                                many = "{0} centylitrów",
                                other = "{0} centylitra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} centylitr",
                                few = "{0} centylitry",
                                many = "{0} centylitrów",
                                other = "{0} centylitra",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} centylitrowi",
                                few = "{0} centylitrom",
                                many = "{0} centylitrom",
                                other = "{0} centylitrom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} centylitra",
                                few = "{0} centylitrów",
                                many = "{0} centylitrów",
                                other = "{0} centylitra",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} centylitrem",
                                few = "{0} centylitrami",
                                many = "{0} centylitrami",
                                other = "{0} centylitrami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} centylitrze",
                                few = "{0} centylitrach",
                                many = "{0} centylitrach",
                                other = "{0} centylitrach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} centylitrze",
                                other = "{0} centylitrze",
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
                                many = "{0} cl",
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
                                one = "{0} mililitr",
                                few = "{0} mililitry",
                                many = "{0} mililitrów",
                                other = "{0} mililitra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mililitr",
                                few = "{0} mililitry",
                                many = "{0} mililitrów",
                                other = "{0} mililitra",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} mililitrowi",
                                few = "{0} mililitrom",
                                many = "{0} mililitrom",
                                other = "{0} mililitrom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} mililitra",
                                few = "{0} mililitrów",
                                many = "{0} mililitrów",
                                other = "{0} mililitra",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} mililitrem",
                                few = "{0} mililitrami",
                                many = "{0} mililitrami",
                                other = "{0} mililitrami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} mililitrze",
                                few = "{0} mililitrach",
                                many = "{0} mililitrach",
                                other = "{0} mililitrach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} mililitrze",
                                other = "{0} mililitrze",
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
                                many = "{0} ml",
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
                                one = "{0} półkwarta metryczna",
                                few = "{0} półkwarty metryczne",
                                many = "{0} półkwart metrycznych",
                                other = "{0} półkwarty metrycznej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} półkwartę metryczną",
                                few = "{0} półkwarty metryczne",
                                many = "{0} półkwart metrycznych",
                                other = "{0} półkwarty metrycznej",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} półkwarty metrycznej",
                                few = "{0} półkwart metrycznych",
                                many = "{0} półkwart metrycznych",
                                other = "{0} półkwarty metrycznej",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} półkwarta metr.",
                                few = "{0} półkwarty metr.",
                                many = "{0} półkwart metr.",
                                other = "{0} półkwarty metr.",
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
                                one = "{0} ćwierćkwarta metryczna",
                                few = "{0} ćwierćkwarty metryczne",
                                many = "{0} ćwierćkwart metrycznych",
                                other = "{0} ćwierćkwarty metrycznej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ćwierćkwartę metryczną",
                                few = "{0} ćwierćkwarty metryczne",
                                many = "{0} ćwierćkwart metrycznych",
                                other = "{0} ćwierćkwarty metrycznej",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} ćwierćkwarty metrycznej",
                                few = "{0} ćwierćkwart metrycznych",
                                many = "{0} ćwierćkwart metrycznych",
                                other = "{0} ćwierćkwarty metrycznej",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ćwierćkwarta metr.",
                                few = "{0} ćwierćkwarty metr.",
                                many = "{0} ćwierćkwart metr.",
                                other = "{0} ćwierćkwarty metr.",
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
                                one = "{0} akrostopa",
                                few = "{0} akrostopy",
                                many = "{0} akrostóp",
                                other = "{0} akrostopy",
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
                                one = "{0} buszel",
                                few = "{0} buszle",
                                many = "{0} buszli",
                                other = "{0} buszla",
                            )
                        ),
                    )
                ),
            )
        override val volumeGallon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} galon amerykański",
                                few = "{0} galony amerykańskie",
                                many = "{0} galonów amerykańskich",
                                other = "{0} galona amerykańskiego",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} galon amerykański",
                                few = "{0} galony amerykańskie",
                                many = "{0} galonów amerykańskich",
                                other = "{0} galona amerykańskiego",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} galonowi amerykańskiemu",
                                few = "{0} galonom amerykańskim",
                                many = "{0} galonom amerykańskim",
                                other = "{0} galonom amerykańskim",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} galona amerykańskiego",
                                few = "{0} galonów amerykańskich",
                                many = "{0} galonów amerykańskich",
                                other = "{0} galona amerykańskiego",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} galonem amerykańskim",
                                few = "{0} galonami amerykańskimi",
                                many = "{0} galonami amerykańskimi",
                                other = "{0} galonami amerykańskimi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} galonie amerykańskim",
                                few = "{0} galonach amerykańskich",
                                many = "{0} galonach amerykańskich",
                                other = "{0} galonach amerykańskich",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} galonie amerykański",
                                other = "{0} galonie amerykański",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gal am.",
                                few = "{0} gal am.",
                                many = "{0} gal am.",
                                other = "{0} gal am.",
                            )
                        ),
                    )
                ),
            )
        override val volumeGallonImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} galon angielski",
                                few = "{0} galony angielskie",
                                many = "{0} galonów angielskich",
                                other = "{0} galona angielskiego",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} galon angielski",
                                few = "{0} galony angielskie",
                                many = "{0} galonów angielskich",
                                other = "{0} galona angielskiego",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} galonowi angielskiemu",
                                few = "{0} galonom angielskim",
                                many = "{0} galonom angielskim",
                                other = "{0} galonom angielskim",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} galonu angielskiego",
                                few = "{0} galonów angielskich",
                                many = "{0} galonów angielskich",
                                other = "{0} galona angielskiego",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} galonem angielskim",
                                few = "{0} galonami angielskimi",
                                many = "{0} galonami angielskimi",
                                other = "{0} galonami angielskimi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} galonie angielskim",
                                few = "{0} galonach angielskich",
                                many = "{0} galonach angielskich",
                                other = "{0} galonach angielskich",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} galonie angielski",
                                other = "{0} galonie angielski",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gal ang.",
                                few = "{0} gal ang.",
                                many = "{0} gal ang.",
                                other = "{0} gal ang.",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuart
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kwarta amerykańska",
                                few = "{0} kwarty amerykańskie",
                                many = "{0} kwart amerykańskich",
                                other = "{0} kwarty amerykańskiej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kwartę amerykańską",
                                few = "{0} kwarty amerykańskie",
                                many = "{0} kwart amerykańskich",
                                other = "{0} kwarty amerykańskiej",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} kwarcie amerykańskiej",
                                few = "{0} kwartom amerykańskim",
                                many = "{0} kwartom amerykańskim",
                                other = "{0} kwartom amerykańskim",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kwarty amerykańskiej",
                                few = "{0} kwart amerykańskich",
                                many = "{0} kwart amerykańskich",
                                other = "{0} kwarty amerykańskiej",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kwartą amerykańską",
                                few = "{0} kwartami amerykańskimi",
                                many = "{0} kwartami amerykańskimi",
                                other = "{0} kwartami amerykańskimi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} kwarcie amerykańskiej",
                                few = "{0} kwartach amerykańskich",
                                many = "{0} kwartach amerykańskich",
                                other = "{0} kwartach amerykańskich",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} kwarto amerykańska",
                                other = "{0} kwarto amerykańska",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kwarta am.",
                                few = "{0} kwarty am.",
                                many = "{0} kwart am.",
                                other = "{0} kwarty am.",
                            )
                        ),
                    )
                ),
            )
        override val volumePint
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} półkwarta amerykańska",
                                few = "{0} półkwarty amerykańskie",
                                many = "{0} półkwart amerykańskich",
                                other = "{0} półkwarty amerykańskiej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} półkwartę amerykańską",
                                few = "{0} półkwarty amerykańskie",
                                many = "{0} półkwart amerykańskich",
                                other = "{0} półkwarty amerykańskiej",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} półkwarcie amerykańskiej",
                                few = "{0} półkwartom amerykańskim",
                                many = "{0} półkwartom amerykańskim",
                                other = "{0} półkwartom amerykańskim",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} półkwarty amerykańskiej",
                                few = "{0} półkwart amerykańskich",
                                many = "{0} półkwart amerykańskich",
                                other = "{0} półkwarty amerykańskiej",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} półkwartą amerykańską",
                                few = "{0} półkwartami amerykańskimi",
                                many = "{0} półkwartami amerykańskimi",
                                other = "{0} półkwartami amerykańskimi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} półkwarcie amerykańskiej",
                                few = "{0} półkwartach amerykańskich",
                                many = "{0} półkwartach amerykańskich",
                                other = "{0} półkwartach amerykańskich",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} półkwarto amerykańska",
                                other = "{0} półkwarto amerykańska",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} półkwarta am.",
                                few = "{0} półkwarty am.",
                                many = "{0} półkwart am.",
                                other = "{0} półkwarty am.",
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
                                one = "{0} ćwierćkwarta amerykańska",
                                few = "{0} ćwierćkwarty amerykańskie",
                                many = "{0} ćwierćkwart amerykańskich",
                                other = "{0} ćwierćkwarty amerykańskiej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ćwierćkwartę amerykańską",
                                few = "{0} ćwierćkwarty amerykańskie",
                                many = "{0} ćwierćkwart amerykańskich",
                                other = "{0} ćwierćkwarty amerykańskiej",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} ćwierćkwarcie amerykańskiej",
                                few = "{0} ćwierćkwartom amerykańskim",
                                many = "{0} ćwierćkwartom amerykańskim",
                                other = "{0} ćwierćkwartom amerykańskim",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} ćwierćkwarty amerykańskiej",
                                few = "{0} ćwierćkwart amerykańskich",
                                many = "{0} ćwierćkwart amerykańskich",
                                other = "{0} ćwierćkwarty amerykańskiej",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} ćwierćkwartą amerykańską",
                                few = "{0} ćwierćkwartami amerykańskimi",
                                many = "{0} ćwierćkwartami amerykańskimi",
                                other = "{0} ćwierćkwartami amerykańskimi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} ćwierćkwarcie amerykańskiej",
                                few = "{0} ćwierćkwartach amerykańskich",
                                many = "{0} ćwierćkwartach amerykańskich",
                                other = "{0} ćwierćkwartach amerykańskich",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} ćwierćkwarto amerykańska",
                                other = "{0} ćwierćkwarty am.",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ćwierćkwarta am.",
                                few = "{0} ćwierćkwarty am.",
                                many = "{0} ćwierćkwart am.",
                                other = "{0} ćwierćkwarty am.",
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
                                one = "{0} uncja płynu amerykańska",
                                few = "{0} uncje płynu amerykańskie",
                                many = "{0} uncji płynu amerykańskich",
                                other = "{0} uncji płynu amerykańskiej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} uncję płynu amerykańską",
                                few = "{0} uncje płynu amerykańskie",
                                many = "{0} uncji płynu amerykańskich",
                                other = "{0} uncji płynu amerykańskiej",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} uncji płynu amerykańskiej",
                                few = "{0} uncjom płynu amerykańskim",
                                many = "{0} uncjom płynu amerykańskim",
                                other = "{0} uncjom płynu amerykańskim",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} uncji płynu amerykańskiej",
                                few = "{0} uncji płynu amerykańskich",
                                many = "{0} uncji płynu amerykańskich",
                                other = "{0} uncji płynu amerykańskiej",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} uncją płynu amerykańską",
                                few = "{0} uncjami płynu amerykańskimi",
                                many = "{0} uncjami płynu amerykańskimi",
                                other = "{0} uncjami płynu amerykańskimi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} uncji płynu amerykańskiej",
                                few = "{0} uncjach płynu amerykańskich",
                                many = "{0} uncjach płynu amerykańskich",
                                other = "{0} uncjach płynu amerykańskich",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} uncjo płynu amerykańska",
                                other = "{0} uncjo płynu amerykańska",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fl oz am.",
                                few = "{0} fl oz am.",
                                many = "{0} fl oz am.",
                                other = "{0} fl oz am.",
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
                                one = "{0} uncja płynu angielska",
                                few = "{0} uncje płynu angielskie",
                                many = "{0} uncji płynu angielskich",
                                other = "{0} uncji płynu angielskiej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} uncję płynu angielską",
                                few = "{0} uncje płynu angielskie",
                                many = "{0} uncji płynu angielskich",
                                other = "{0} uncji płynu angielskiej",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} uncji płynu angielskiej",
                                few = "{0} uncjom płynu angielskim",
                                many = "{0} uncjom płynu angielskim",
                                other = "{0} uncjom płynu angielskim",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} uncji płynu angielskiej",
                                few = "{0} uncji płynu angielskich",
                                many = "{0} uncji płynu angielskich",
                                other = "{0} uncji płynu angielskiej",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} uncją płynu angielską",
                                few = "{0} uncjami płynu angielskimi",
                                many = "{0} uncjami płynu angielskimi",
                                other = "{0} uncjami płynu angielskimi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} uncji płynu angielskiej",
                                few = "{0} uncjach płynu angielskich",
                                many = "{0} uncjach płynu angielskich",
                                other = "{0} uncjach płynu angielskich",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} uncjo płynu angielska",
                                other = "{0} uncjo płynu angielska",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fl oz ang.",
                                few = "{0} fl oz ang.",
                                many = "{0} fl oz ang.",
                                other = "{0} fl oz ang.",
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
                                one = "{0} łyżka stołowa",
                                few = "{0} łyżki stołowe",
                                many = "{0} łyżek stołowych",
                                other = "{0} łyżki stołowej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} łyżkę stołową",
                                few = "{0} łyżki stołowe",
                                many = "{0} łyżek stołowych",
                                other = "{0} łyżki stołowej",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} łyżce stołowej",
                                few = "{0} łyżkom stołowym",
                                many = "{0} łyżkom stołowym",
                                other = "{0} łyżkom stołowym",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} łyżki stołowej",
                                few = "{0} łyżek stołowych",
                                many = "{0} łyżek stołowych",
                                other = "{0} łyżki stołowej",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} łyżką stołową",
                                few = "{0} łyżkami stołowymi",
                                many = "{0} łyżkami stołowymi",
                                other = "{0} łyżkami stołowymi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} łyżce stołowej",
                                few = "{0} łyżkach stołowych",
                                many = "{0} łyżkach stołowych",
                                other = "{0} łyżkach stołowych",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} łyżko stołowa",
                                other = "{0} łyżko stołowa",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ł. stoł.",
                                few = "{0} ł. stoł.",
                                many = "{0} ł. stoł.",
                                other = "{0} ł. stoł.",
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
                                one = "{0} łyżeczka",
                                few = "{0} łyżeczki",
                                many = "{0} łyżeczek",
                                other = "{0} łyżeczki",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} łyżeczkę",
                                few = "{0} łyżeczki",
                                many = "{0} łyżeczek",
                                other = "{0} łyżeczki",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} łyżeczkce",
                                few = "{0} łyżeczkom",
                                many = "{0} łyżeczkom",
                                other = "{0} łyżeczkom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} łyżeczki",
                                few = "{0} lyżeczek",
                                many = "{0} łyżeczek",
                                other = "{0} łyżeczki",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} łyżeczką",
                                few = "{0} łyżeczkami",
                                many = "{0} łyżeczkami",
                                other = "{0} łyżeczkami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} łyżeczce",
                                few = "{0} łyżeczkach",
                                many = "{0} łyżeczkach",
                                other = "{0} łyżeczkach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} łyżeczko",
                                other = "{0} łyżeczko",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} łyżeczka",
                                few = "{0} łyżeczki",
                                many = "{0} łyżeczek",
                                other = "{0} łyżeczki",
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
                                one = "{0} baryłka",
                                few = "{0} baryłki",
                                many = "{0} baryłek",
                                other = "{0} baryłki",
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
                                one = "{0} łyżka deserowa",
                                few = "{0} łyżki deserowe",
                                many = "{0} łyżek deserowych",
                                other = "{0} łyżki deserowej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} łyżkę deserową",
                                few = "{0} łyżki deserowe",
                                many = "{0} łyżek deserowych",
                                other = "{0} łyżki deserowej",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} łyżce deserowej",
                                few = "{0} łyżkom deserowym",
                                many = "{0} łyżkom deserowym",
                                other = "{0} łyżkom deserowym",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} łyżki deserowej",
                                few = "{0} łyżek deserowych",
                                many = "{0} łyżek deserowych",
                                other = "{0} łyżki deserowej",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} łyżką deserową",
                                few = "{0} łyżkami deserowymi",
                                many = "{0} łyżkami deserowymi",
                                other = "{0} łyżkami deserowymi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} łyżce deserowej",
                                few = "{0} łyżkach deserowych",
                                many = "{0} łyżkach deserowych",
                                other = "{0} łyżkach deserowych",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} łyżko deserowa",
                                other = "{0} łyżko deserowa",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ł. deser.",
                                few = "{0} ł. deser.",
                                many = "{0} ł. deser.",
                                other = "{0} ł. deser.",
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
                                one = "{0} imperialna łyżeczka deserowa",
                                few = "{0} imperialne łyżeczki deserowe",
                                many = "{0} imperialnych łyżeczek deserowych",
                                other = "{0} imperialnej łyżeczki deserowej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} imperialną łyżeczkę deserową",
                                few = "{0} imperialne łyżeczki deserowe",
                                many = "{0} imperialnych łyżeczek deserowych",
                                other = "{0} imperialnej łyżeczki deserowej",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} imperialnej łyżeczkce deserowej",
                                few = "{0} imperialnym łyżeczkom deserowym",
                                many = "{0} imperialnym łyżeczkom deserowym",
                                other = "{0} imperialnym łyżeczkom deserowym",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} imperialnej łyżeczki deserowej",
                                few = "{0} imperialnych łyżeczek deserowych",
                                many = "{0} imperialnych łyżeczek deserowych",
                                other = "{0} imperialnej łyżeczki deserowej",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} imperialną łyżeczką deserową",
                                few = "{0} imperialnymi łyżeczkami deserowymi",
                                many = "{0} imperialnymi łyżeczkami deserowymi",
                                other = "{0} imperialnymi łyżeczkami deserowymi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} imperialnej łyżeczce deserowej",
                                few = "{0} imperialnych łyżeczkach deserowych",
                                many = "{0} imperialnych łyżeczkach deserowych",
                                other = "{0} imperialnych łyżeczkach deserowych",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} imperialna łyżeczko deserowa",
                                other = "{0} imperialna łyżeczko deserowa",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ł. deser. ang.",
                                few = "{0} ł. deser. ang.",
                                many = "{0} ł. deser. ang.",
                                other = "{0} ł. deser. ang.",
                            )
                        ),
                    )
                ),
            )
        override val volumeDrop
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kropla",
                                few = "{0} krople",
                                many = "{0} kropli",
                                other = "{0} kropli",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kroplę",
                                few = "{0} krople",
                                many = "{0} kropli",
                                other = "{0} kropli",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} kropli",
                                few = "{0} kroplom",
                                many = "{0} kroplom",
                                other = "{0} kroplom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kropli",
                                few = "{0} kropel",
                                many = "{0} kropel",
                                other = "{0} kropli",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kroplą",
                                few = "{0} kroplami",
                                many = "{0} kroplami",
                                other = "{0} kroplami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} kropli",
                                few = "{0} kroplach",
                                many = "{0} kroplach",
                                other = "{0} kroplach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} kroplo",
                                other = "{0} kroplo",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kropla",
                                few = "{0} krople",
                                many = "{0} kropli",
                                other = "{0} kropli",
                            )
                        ),
                    )
                ),
            )
        override val volumeDram
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} drachma płynu",
                                few = "{0} drachmy płynu",
                                many = "{0} drachm płynu",
                                other = "{0} drachmy płynu",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} drachmę płynu",
                                few = "{0} drachmy płynu",
                                many = "{0} drachm płynu",
                                other = "{0} drachmy płynu",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} drachmie płynu",
                                few = "{0} drachmom płynu",
                                many = "{0} drachmom płynu",
                                other = "{0} drachmom płynu",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} drachmy płynu",
                                few = "{0} drachm płynu",
                                many = "{0} drachm płynu",
                                other = "{0} drachmy płynu",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} drachmą płynu",
                                few = "{0} drachmami płynu",
                                many = "{0} drachmami płynu",
                                other = "{0} drachmami płynu",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} drachmie płynu",
                                few = "{0} drachmach płynu",
                                many = "{0} drachmach płynu",
                                other = "{0} drachmach płynu",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} drachmo płynu",
                                other = "{0} drachmo płynu",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} drachma",
                                few = "{0} drachmy",
                                many = "{0} drachm",
                                other = "{0} drachmy",
                            )
                        ),
                    )
                ),
            )
        override val volumeJigger
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Inanimate,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} jigger",
                                few = "{0} jiggery",
                                many = "{0} jiggerów",
                                other = "{0} jiggera",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} jigger",
                                few = "{0} jiggery",
                                many = "{0} jiggerów",
                                other = "{0} jiggera",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} jiggerowi",
                                few = "{0} jiggerom",
                                many = "{0} jiggerom",
                                other = "{0} jiggerom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} jiggera",
                                few = "{0} jiggerów",
                                many = "{0} jiggerów",
                                other = "{0} jiggera",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} jiggerem",
                                few = "{0} jiggerami",
                                many = "{0} jiggerami",
                                other = "{0} jiggerami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} jiggerze",
                                few = "{0} jiggerach",
                                many = "{0} jiggerach",
                                other = "{0} jiggerach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} jiggerze",
                                other = "{0} jiggerze",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} jigger",
                                few = "{0} jiggery",
                                many = "{0} jiggerów",
                                other = "{0} jiggera",
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
                                one = "{0} szczypta",
                                few = "{0} szczypty",
                                many = "{0} szczypt",
                                other = "{0} szczypty",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} szczyptę",
                                few = "{0} szczypty",
                                many = "{0} szczypt",
                                other = "{0} szczypty",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} szczypcie",
                                few = "{0} szczyptom",
                                many = "{0} szczyptom",
                                other = "{0} szczyptom",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} szczypty",
                                few = "{0} szczypt",
                                many = "{0} szczypt",
                                other = "{0} szczypty",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} szczyptą",
                                few = "{0} szczyptami",
                                many = "{0} szczyptami",
                                other = "{0} szczyptami",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} szczypcie",
                                few = "{0} szczyptach",
                                many = "{0} szczyptach",
                                other = "{0} szczyptach",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} szczypto",
                                other = "{0} szczypto",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} szcz.",
                                few = "{0} szcz.",
                                many = "{0} szcz.",
                                other = "{0} szcz.",
                            )
                        ),
                    )
                ),
            )
        override val volumeQuartImperial
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kwarta angielska",
                                few = "{0} kwarty angielskie",
                                many = "{0} kwart angielskich",
                                other = "{0} kwarty angielskiej",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kwartę angielską",
                                few = "{0} kwarty angielskie",
                                many = "{0} kwart angielskich",
                                other = "{0} kwarty angielskiej",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} kwarcie angielskiej",
                                few = "{0} kwartom angielskim",
                                many = "{0} kwartom angielskim",
                                other = "{0} kwartom angielskim",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} kwarty angielskiej",
                                few = "{0} kwart angielskich",
                                many = "{0} kwart angielskich",
                                other = "{0} kwarty angielskiej",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kwartą angielską",
                                few = "{0} kwartami angielskimi",
                                many = "{0} kwartami angielskimi",
                                other = "{0} kwartami angielskimi",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} kwarcie angielskiej",
                                few = "{0} kwartach angielskich",
                                many = "{0} kwartach angielskich",
                                other = "{0} kwartach angielskich",
                            )
                        ),
                        vocative = PluralPattern(
                            Plurals(
                                one = "{0} kwarto angielska",
                                other = "{0} kwarto angielska",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kwarta ang.",
                                few = "{0} kwarty ang.",
                                many = "{0} kwart ang.",
                                other = "{0} kwarty ang.",
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
            groupSymbol = " ",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
