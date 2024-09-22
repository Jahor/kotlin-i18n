package info.leonenko.i18n.languages

import info.leonenko.i18n.*
import info.leonenko.i18n.rbnf.RBNFHu

val Language.Companion.hu
    get() = languageHu
private val languageHu = LanguageHu()

open class LanguageHu internal constructor() : Language {
    override val code: String = "hu"
    override val language: String = "hu"
    override val script: String? = null
    override val territory: String? = null
    override val rbnf: RBNFHu
        get() = RBNFHu(this)
    override val pluralFormChooser = PluralFormChooser.Expression(
        one = { (n == 1.0) },
    )

    open class EllipsisLanguage internal constructor() : info.leonenko.i18n.EllipsisLanguage {
        override val formatter = EllipsisFormatter.Default
        override val wordFormatter = EllipsisFormatter.WordDefault
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    open class ListLanguage internal constructor(val ellipsis: EllipsisLanguage) : info.leonenko.i18n.ListLanguage {
        override val formatter: ListFormatter = MultiLengthPatternedListFormatter(
            long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} és {1}", two = "{0} és {1}", ellipsis = ellipsis.wordFormatter),
        )
        override val orFormatter: ListFormatter =
            MultiLengthPatternedListFormatter(long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} vagy {1}", two = "{0} vagy {1}", ellipsis = ellipsis.wordFormatter))
        override val unitFormatter: ListFormatter = formatter
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    open class MeasurementUnitLanguage internal constructor() : info.leonenko.i18n.units.MeasurementUnitLanguage {
        override val accelerationGForce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} g gyorsulás",
                                other = "{0} g gyorsulás",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} g gyorsulást",
                                other = "{0} g gyorsulást",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} g gyorsulással",
                                other = "{0} g gyorsulással",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} g gyorsulásig",
                                other = "{0} g gyorsulásig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} g gyorsulássá",
                                other = "{0} g gyorsulássá",
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
                                one = "{0} méter per másodpercnégyzet",
                                other = "{0} méter per másodpercnégyzet",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} méter per másodpercnégyzetet",
                                other = "{0} méter per másodpercnégyzetet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} méter per másodpercnégyzettel",
                                other = "{0} méter per másodpercnégyzettel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} méter per másodpercnégyzetig",
                                other = "{0} méter per másodpercnégyzetig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} méter per másodpercnégyzetté",
                                other = "{0} méter per másodpercnégyzetté",
                            )
                        ),
                    )
                ),
            )
        override val angleRevolution
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fordulat",
                                other = "{0} fordulat",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} fordulatot",
                                other = "{0} fordulatot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} fordulattal",
                                other = "{0} fordulattal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} fordulatig",
                                other = "{0} fordulatig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} fordulattá",
                                other = "{0} fordulattá",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ford.",
                                other = "{0} ford.",
                            )
                        ),
                    )
                ),
            )
        override val angleRadian
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} radián",
                                other = "{0} radián",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} radiánt",
                                other = "{0} radiánt",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} radiánnal",
                                other = "{0} radiánnal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} radiánig",
                                other = "{0} radiánig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} radiánná",
                                other = "{0} radiánná",
                            )
                        ),
                    )
                ),
            )
        override val angleDegree
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fok",
                                other = "{0} fok",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} fokot",
                                other = "{0} fokot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} fokkal",
                                other = "{0} fokkal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} fokig",
                                other = "{0} fokig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} fokká",
                                other = "{0} fokká",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fok",
                                other = "{0} fok",
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
        override val angleArcMinute
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ívperc",
                                other = "{0} ívperc",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ívpercet",
                                other = "{0} ívpercet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} ívperccel",
                                other = "{0} ívperccel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} ívpercig",
                                other = "{0} ívpercig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} ívperccé",
                                other = "{0} ívperccé",
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
                                one = "{0} ívmásodperc",
                                other = "{0} ívmásodperc",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ívmásodpercet",
                                other = "{0} ívmásodpercet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} ívmásodperccel",
                                other = "{0} ívmásodperccel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} ívmásodpercig",
                                other = "{0} ívmásodpercig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} ívmásodperccé",
                                other = "{0} ívmásodperccé",
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
                                one = "{0} négyzetkilométer",
                                other = "{0} négyzetkilométer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} négyzetkilométert",
                                other = "{0} négyzetkilométert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} négyzetkilométerrel",
                                other = "{0} négyzetkilométerrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} négyzetkilométerig",
                                other = "{0} négyzetkilométerig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} négyzetkilométerré",
                                other = "{0} négyzetkilométerré",
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
                                one = "{0} hektár",
                                other = "{0} hektár",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} hektárt",
                                other = "{0} hektárt",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} hektárral",
                                other = "{0} hektárral",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} hektárig",
                                other = "{0} hektárig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} hektárrá",
                                other = "{0} hektárrá",
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
                                one = "{0} négyzetméter",
                                other = "{0} négyzetméter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} négyzetmétert",
                                other = "{0} négyzetmétert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} négyzetméterrel",
                                other = "{0} négyzetméterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} négyzetméterig",
                                other = "{0} négyzetméterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} négyzetméterré",
                                other = "{0} négyzetméterré",
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
                                one = "{0} négyzetcentiméter",
                                other = "{0} négyzetcentiméter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} négyzetcentimétert",
                                other = "{0} négyzetcentimétert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} négyzetcentiméterrel",
                                other = "{0} négyzetcentiméterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} négyzetcentiméterig",
                                other = "{0} négyzetcentiméterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} négyzetcentiméterré",
                                other = "{0} négyzetcentiméterré",
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
                                one = "{0} négyzetmérföld",
                                other = "{0} négyzetmérföld",
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
                                one = "{0} hold",
                                other = "{0} hold",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kh",
                                other = "{0} kh",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
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
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} négyzetyard",
                                other = "{0} négyzetyard",
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
                                one = "{0} négyzetláb",
                                other = "{0} négyzetláb",
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
                                one = "{0} négyzethüvelyk",
                                other = "{0} négyzethüvelyk",
                            )
                        ),
                    )
                ),
            )
        override val areaDunam: MultiLengthGenderedPattern
            get() = TODO("Dunam formatter is not implemented yet for hu")
        override val concentrationKarat
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} karát",
                                other = "{0} karát",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} karátot",
                                other = "{0} karátot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} karáttal",
                                other = "{0} karáttal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} karátig",
                                other = "{0} karátig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} karáttá",
                                other = "{0} karáttá",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermillion
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} részecske/millió",
                                other = "{0} részecske/millió",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} részecske/milliót",
                                other = "{0} részecske/milliót",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} részecske/millióval",
                                other = "{0} részecske/millióval",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} részecske/millióig",
                                other = "{0} részecske/millióig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} részecske/millióvá",
                                other = "{0} részecske/millióvá",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPercent
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} százalék",
                                other = "{0} százalék",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} százalékot",
                                other = "{0} százalékot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} százalékkal",
                                other = "{0} százalékkal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} százalékig",
                                other = "{0} százalékig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} százalékká",
                                other = "{0} százalékká",
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
                                one = "{0} ezrelék",
                                other = "{0} ezrelék",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ezreléket",
                                other = "{0} ezreléket",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} ezrelékkel",
                                other = "{0} ezrelékkel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} ezrelékig",
                                other = "{0} ezrelékig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} ezrelékké",
                                other = "{0} ezrelékké",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPermyriad
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} tízezrelék",
                                other = "{0} tízezrelék",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} tízezreléket",
                                other = "{0} tízezreléket",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} tízezrelékkel",
                                other = "{0} tízezrelékkel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} tízezrelékig",
                                other = "{0} tízezrelékig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} tízezrelékké",
                                other = "{0} tízezrelékké",
                            )
                        ),
                    )
                ),
            )
        override val concentrationAPortion: MultiLengthGenderedPattern
            get() = TODO("APortion formatter is not implemented yet for hu")
        override val concentrationMilligramOfglucosePerDeciliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milligramm/deciliter",
                                other = "{0} milligramm/deciliter",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} millimól/liter",
                                other = "{0} millimól/liter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} millimól/litert",
                                other = "{0} millimól/litert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} millimól/literrel",
                                other = "{0} millimól/literrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} millimól/literig",
                                other = "{0} millimól/literig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} millimól/literré",
                                other = "{0} millimól/literré",
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
            get() = TODO("ItemPerCubicMeter formatter is not implemented yet for hu")
        override val concentrationItem
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} item",
                                other = "{0} item",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} itemet",
                                other = "{0} itemet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} itemmel",
                                other = "{0} itemmel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} itemig",
                                other = "{0} itemig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} itemmé",
                                other = "{0} itemmé",
                            )
                        ),
                    )
                ),
            )
        override val concentrationMole
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mól",
                                other = "{0} mól",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mólt",
                                other = "{0} mólt",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} móllal",
                                other = "{0} móllal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} mólig",
                                other = "{0} mólig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} móllá",
                                other = "{0} móllá",
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
                                one = "{0} liter per kilométer",
                                other = "{0} liter per kilométer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} liter per kilométert",
                                other = "{0} liter per kilométert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} liter per kilométerrel",
                                other = "{0} liter per kilométerrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} liter per kilométerig",
                                other = "{0} liter per kilométerig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} liter per kilométerré",
                                other = "{0} liter per kilométerré",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} liter/100 km",
                                other = "{0} liter/100 km",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} liter/100 km-t",
                                other = "{0} liter/100 km-t",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} liter/100 km-rel",
                                other = "{0} liter/100 km-rel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} liter/100 km-ig",
                                other = "{0} liter/100 km-ig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} liter/100 km-ré",
                                other = "{0} liter/100 km-ré",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} l/100 km",
                                other = "{0} l/100km",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mérföld per gallon",
                                other = "{0} mérföld per gallon",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mérföld/birodalmi gallon",
                                other = "{0} mérföld/birodalmi gallon",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mpg bir.",
                                other = "{0} mpg bir.",
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
                                one = "{0} petabájt",
                                other = "{0} petabájt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} petabájtot",
                                other = "{0} petabájtot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} petabájttal",
                                other = "{0} petabájttal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} petabájtig",
                                other = "{0} petabájtig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} petabájttá",
                                other = "{0} petabájttá",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} terabájt",
                                other = "{0} terabájt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} terabájtot",
                                other = "{0} terabájtot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} terabájttal",
                                other = "{0} terabájttal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} terabájtig",
                                other = "{0} terabájtig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} terabájttá",
                                other = "{0} terabájttá",
                            )
                        ),
                    )
                ),
            )
        override val digitalTerabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} terabit",
                                other = "{0} terabit",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} terabitet",
                                other = "{0} terabitet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} terabittel",
                                other = "{0} terabittel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} terabitig",
                                other = "{0} terabitig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} terabitté",
                                other = "{0} terabitté",
                            )
                        ),
                    )
                ),
            )
        override val digitalGigabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigabájt",
                                other = "{0} gigabájt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gigabájtot",
                                other = "{0} gigabájtot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} gigabájttal",
                                other = "{0} gigabájttal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} gigabájtig",
                                other = "{0} gigabájtig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} gigabájttá",
                                other = "{0} gigabájttá",
                            )
                        ),
                    )
                ),
            )
        override val digitalGigabit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigabit",
                                other = "{0} gigabit",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gigabitet",
                                other = "{0} gigabitet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} gigabittel",
                                other = "{0} gigabittel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} gigabitig",
                                other = "{0} gigabitig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} gigabitté",
                                other = "{0} gigabitté",
                            )
                        ),
                    )
                ),
            )
        override val digitalMegabyte
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megabájt",
                                other = "{0} megabájt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megabájtot",
                                other = "{0} megabájtot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} megabájttal",
                                other = "{0} megabájttal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} megabájtig",
                                other = "{0} megabájtig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} megabájttá",
                                other = "{0} megabájttá",
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
                                one = "{0} megabit",
                                other = "{0} megabit",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megabitet",
                                other = "{0} megabitet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} megabittel",
                                other = "{0} megabittel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} megabitig",
                                other = "{0} megabitig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} megabitté",
                                other = "{0} megabitté",
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
                                one = "{0} kilobájt",
                                other = "{0} kilobájt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilobájtot",
                                other = "{0} kilobájtot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilobájttal",
                                other = "{0} kilobájttal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kilobájtig",
                                other = "{0} kilobájtig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kilobájttá",
                                other = "{0} kilobájttá",
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
                                one = "{0} kilobit",
                                other = "{0} kilobit",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilobitet",
                                other = "{0} kilobitet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilobittel",
                                other = "{0} kilobittel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kilobitig",
                                other = "{0} kilobitig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kilobitté",
                                other = "{0} kilobitté",
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
                                one = "{0} bájt",
                                other = "{0} bájt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} bájtot",
                                other = "{0} bájtot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} bájttal",
                                other = "{0} bájttal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} bájtig",
                                other = "{0} bájtig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} bájttá",
                                other = "{0} bájttá",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bájt",
                                other = "{0} bájt",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bit",
                                other = "{0} bit",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} bitet",
                                other = "{0} bitet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} bittel",
                                other = "{0} bittel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} bitig",
                                other = "{0} bitig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} bitté",
                                other = "{0} bitté",
                            )
                        ),
                    )
                ),
            )
        override val durationCentury
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} évszázad",
                                other = "{0} évszázad",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} évszázadot",
                                other = "{0} évszázadot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} évszázaddal",
                                other = "{0} évszázaddal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} évszázadig",
                                other = "{0} évszázadig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} évszázaddá",
                                other = "{0} évszázaddá",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} sz.",
                                other = "{0} sz.",
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
                                one = "{0} évtized",
                                other = "{0} évtized",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} évtizedet",
                                other = "{0} évtizedet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} évtizeddel",
                                other = "{0} évtizeddel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} évtizedig",
                                other = "{0} évtizedig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} évtizeddé",
                                other = "{0} évtizeddé",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} dek",
                                other = "{0} dek",
                            )
                        ),
                    )
                ),
            )
        override val durationYear
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} év",
                                other = "{0} év",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} évet",
                                other = "{0} évet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} évvel",
                                other = "{0} évvel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} évig",
                                other = "{0} évig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} évvé",
                                other = "{0} évvé",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} év",
                                other = "{0} év",
                            )
                        ),
                    )
                ),
            )
        override val durationQuarter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} negyedév",
                                other = "{0} negyedév",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} negyedévet",
                                other = "{0} negyedévet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} negyedévvel",
                                other = "{0} negyedévvel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} negyedévig",
                                other = "{0} negyedévig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} negyedévvé",
                                other = "{0} negyedévvé",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} n.év",
                                other = "{0} n.év",
                            )
                        ),
                    )
                ),
            )
        override val durationMonth
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hónap",
                                other = "{0} hónap",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} hónapot",
                                other = "{0} hónapot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} hónappal",
                                other = "{0} hónappal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} hónapig",
                                other = "{0} hónapig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} hónappá",
                                other = "{0} hónappá",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hónap",
                                other = "{0} hónap",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} h.",
                                other = "{0} h.",
                            )
                        ),
                    )
                ),
            )
        override val durationWeek
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hét",
                                other = "{0} hét",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} hetet",
                                other = "{0} hetet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} héttel",
                                other = "{0} héttel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} hétig",
                                other = "{0} hétig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} hétté",
                                other = "{0} hétté",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hét",
                                other = "{0} hét",
                            )
                        ),
                    )
                ),
            )
        override val durationDay
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} nap",
                                other = "{0} nap",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} napot",
                                other = "{0} napot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} nappal",
                                other = "{0} nappal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} napig",
                                other = "{0} napig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} nappá",
                                other = "{0} nappá",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} nap",
                                other = "{0} nap",
                            )
                        ),
                    )
                ),
            )
        override val durationHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} óra",
                                other = "{0} óra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} órát",
                                other = "{0} órát",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} órával",
                                other = "{0} órával",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} óráig",
                                other = "{0} óráig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} órává",
                                other = "{0} órává",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ó",
                                other = "{0} ó",
                            )
                        ),
                    )
                ),
            )
        override val durationMinute
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} perc",
                                other = "{0} perc",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} percet",
                                other = "{0} percet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} perccel",
                                other = "{0} perccel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} percig",
                                other = "{0} percig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} perccé",
                                other = "{0} perccé",
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
        override val durationSecond
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} másodperc",
                                other = "{0} másodperc",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} másodpercet",
                                other = "{0} másodpercet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} másodperccel",
                                other = "{0} másodperccel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} másodpercig",
                                other = "{0} másodpercig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} másodperccé",
                                other = "{0} másodperccé",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mp",
                                other = "{0} mp",
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
                                one = "{0} ezredmásodperc",
                                other = "{0} ezredmásodperc",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ezredmásodpercet",
                                other = "{0} ezredmásodpercig",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} ezredmásodperccel",
                                other = "{0} ezredmásodperccel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} ezredmásodpercig",
                                other = "{0} ezredmásodpercig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} ezredmásodperccé",
                                other = "{0} ezredmásodperccé",
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
                                one = "{0} mikroszekundum",
                                other = "{0} mikroszekundum",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mikroszekundumot",
                                other = "{0} mikroszekundumot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} mikroszekundummal",
                                other = "{0} mikroszekundummal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} mikroszekundumig",
                                other = "{0} mikroszekundumig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} mikroszekundummá",
                                other = "{0} mikroszekundummá",
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
                                one = "{0} nanoszekundum",
                                other = "{0} nanoszekundum",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} nanoszekundumot",
                                other = "{0} nanoszekundumot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} nanoszekundummal",
                                other = "{0} nanoszekundummal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} nanoszekundumig",
                                other = "{0} nanoszekundumig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} nanoszekundummá",
                                other = "{0} nanoszekundummá",
                            )
                        ),
                    )
                ),
            )
        override val electricAmpere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} amper",
                                other = "{0} amper",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ampert",
                                other = "{0} ampert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} amperrel",
                                other = "{0} amperrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} amperig",
                                other = "{0} amperig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} amperré",
                                other = "{0} amperré",
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
                                one = "{0} milliamper",
                                other = "{0} milliamper",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milliampert",
                                other = "{0} milliampert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milliamperrel",
                                other = "{0} milliamperrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} milliamperig",
                                other = "{0} milliamperig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} milliamperré",
                                other = "{0} milliamperré",
                            )
                        ),
                    )
                ),
            )
        override val electricOhm
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ohm",
                                other = "{0} ohm",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ohmot",
                                other = "{0} ohmot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} ohmmal",
                                other = "{0} ohmmal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} ohmig",
                                other = "{0} ohmig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} ohmmá",
                                other = "{0} ohmmá",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondSquareAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondSquareAmpere formatter is not implemented yet for hu")
        override val electricVolt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} volt",
                                other = "{0} volt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} voltot",
                                other = "{0} voltot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} volttal",
                                other = "{0} volttal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} voltig",
                                other = "{0} voltig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} volttá",
                                other = "{0} volttá",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondAmpere formatter is not implemented yet for hu")
        override val energyKilocalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilokalória",
                                other = "{0} kilokalória",
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
                                one = "{0} kalória",
                                other = "{0} kalória",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kalóriát",
                                other = "{0} kalóriát",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kalóriával",
                                other = "{0} kalóriával",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kalóriáig",
                                other = "{0} kalóriáig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kalóriává",
                                other = "{0} kalóriává",
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
                                one = "{0} kalória",
                                other = "{0} kalória",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilojoule",
                                other = "{0} kilojoule",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilojoule-t",
                                other = "{0} kilojoule-t",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilojoule-lal",
                                other = "{0} kilojoule-lal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kilojoule-ig",
                                other = "{0} kilojoule-ig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kilojoule-lá",
                                other = "{0} kilojoule-lá",
                            )
                        ),
                    )
                ),
            )
        override val energyJoule
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} joule",
                                other = "{0} joule",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} joule-t",
                                other = "{0} joule-t",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} joule-lal",
                                other = "{0} joule-lal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} joule-ig",
                                other = "{0} joule-ig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} joule-lá",
                                other = "{0} joule-lá",
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
                                one = "{0} kilowattóra",
                                other = "{0} kilowattóra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilowattórát",
                                other = "{0} kilowattórát",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilowattórával",
                                other = "{0} kilowattórával",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kilowattóráig",
                                other = "{0} kilowattóráig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kilowattórává",
                                other = "{0} kilowattórává",
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
                                one = "{0} brit hőegység",
                                other = "{0} brit hőegység",
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
                                one = "{0} amerikai therm",
                                other = "{0} amerikai therm",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} USA therm",
                                other = "{0} USA therm",
                            )
                        ),
                    )
                ),
            )
        override val energyKilogramSquareMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerSquareSecond formatter is not implemented yet for hu")
        override val forcePoundForce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fonterő",
                                other = "{0} fonterő",
                            )
                        ),
                    )
                ),
            )
        override val forceNewton
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} newton",
                                other = "{0} newton",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} newtont",
                                other = "{0} newtont",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} newtonnal",
                                other = "{0} newtonnal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} newtonig",
                                other = "{0} newtonig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} newtonná",
                                other = "{0} newtonná",
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
                                one = "{0} kWh/100 km",
                                other = "{0} kWh/100 km",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kWh/100 km-t",
                                other = "{0} kWh/100 km-t",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kWh/100 km-rel",
                                other = "{0} kWh/100 km-rel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kWh/100 km-ig",
                                other = "{0} kWh/100 km-ig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kWh/100 km-ré",
                                other = "{0} kWh/100 km-ré",
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
            )
        override val forceKilogramMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramMeterPerSquareSecond formatter is not implemented yet for hu")
        override val frequencyGigahertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigahertz",
                                other = "{0} gigahertz",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gigahertzet",
                                other = "{0} gigahertzet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} gigahertzcel",
                                other = "{0} gigahertzcel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} gigahertzig",
                                other = "{0} gigahertzig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} gigahertzcé",
                                other = "{0} gigahertzcé",
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
                                one = "{0} megahertz",
                                other = "{0} megahertz",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megahertzet",
                                other = "{0} megahertzet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} megahertzcel",
                                other = "{0} megahertzcel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} megahertzig",
                                other = "{0} megahertzig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} megahertzcé",
                                other = "{0} megahertzcé",
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
                                one = "{0} kilohertz",
                                other = "{0} kilohertz",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilohertzet",
                                other = "{0} kilohertzet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilohertzcel",
                                other = "{0} kilohertzcel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kilohertzig",
                                other = "{0} kilohertzig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kilohertzcé",
                                other = "{0} kilohertzcé",
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
                                one = "{0} hertz",
                                other = "{0} hertz",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} hertzet",
                                other = "{0} hertzet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} hertzcel",
                                other = "{0} hertzcel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} hertzig",
                                other = "{0} hertzig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} hertzcé",
                                other = "{0} hertzcé",
                            )
                        ),
                    )
                ),
            )
        override val frequencyRevolutionPerSecond: MultiLengthGenderedPattern
            get() = TODO("RevolutionPerSecond formatter is not implemented yet for hu")
        override val graphicsEm
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kvirt",
                                other = "{0} kvirt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kvirtet",
                                other = "{0} kvirtet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kvirttel",
                                other = "{0} kvirttel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kvirtig",
                                other = "{0} kvirtig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kvirtté",
                                other = "{0} kvirtté",
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
                                one = "{0} képpont",
                                other = "{0} képpont",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} képpontot",
                                other = "{0} képpontot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} képponttal",
                                other = "{0} képponttal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} képpontig",
                                other = "{0} képpontig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} képponttá",
                                other = "{0} képponttá",
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
                                one = "{0} millió képpont",
                                other = "{0} millió képpont",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} millió képpontot",
                                other = "{0} millió képpontot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} millió képponttal",
                                other = "{0} millió képponttal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} millió képpontig",
                                other = "{0} millió képpontig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} millió képponttá",
                                other = "{0} millió képponttá",
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
                                one = "{0} képpont",
                                other = "{0} képpont",
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
                                one = "{0} képpont per centiméter",
                                other = "{0} képpont per centiméter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} képpont per centimétert",
                                other = "{0} képpont per centimétert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} képpont per centiméterrel",
                                other = "{0} képpont per centiméterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} képpont per centiméterig",
                                other = "{0} képpont per centiméterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} képpont per centiméterré",
                                other = "{0} képpont per centiméterré",
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
                                one = "{0} képpont per hüvelyk",
                                other = "{0} képpont per hüvelyk",
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
                                one = "{0} pont per centiméter",
                                other = "{0} pont per centiméter",
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
                                one = "{0} pont per hüvelyk",
                                other = "{0} pont per hüvelyk",
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
            get() = TODO("PixelPerMeter formatter is not implemented yet for hu")
        override val lengthEarthRadius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} földsugár",
                                other = "{0} földsugár",
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
                                one = "{0} kilométer",
                                other = "{0} kilométer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilométert",
                                other = "{0} kilométert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilométerrel",
                                other = "{0} kilométerrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kilométerig",
                                other = "{0} kilométerig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kilométerré",
                                other = "{0} kilométerré",
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
                                one = "{0} méter",
                                other = "{0} méter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} métert",
                                other = "{0} métert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} méterrel",
                                other = "{0} méterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} méterig",
                                other = "{0} méterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} méterré",
                                other = "{0} méterré",
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
                                one = "{0} deciméter",
                                other = "{0} deciméter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} decimétert",
                                other = "{0} decimétert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} deciméterrel",
                                other = "{0} deciméterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} deciméterig",
                                other = "{0} deciméterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} deciméterré",
                                other = "{0} deciméterré",
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
                                one = "{0} centiméter",
                                other = "{0} centiméter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} centimétert",
                                other = "{0} centimétert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} centiméterrel",
                                other = "{0} centiméterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} centiméterig",
                                other = "{0} centiméterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} centiméterré",
                                other = "{0} centiméterré",
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
                                one = "{0} milliméter",
                                other = "{0} milliméter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} millimétert",
                                other = "{0} millimétert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milliméterrel",
                                other = "{0} milliméterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} milliméterig",
                                other = "{0} milliméterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} milliméterré",
                                other = "{0} milliméterré",
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
                                one = "{0} mikrométer",
                                other = "{0} mikrométer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mikrométert",
                                other = "{0} mikrométert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} mikrométerrel",
                                other = "{0} mikrométerrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} mikrométerig",
                                other = "{0} mikrométerig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} mikrométerré",
                                other = "{0} mikrométerré",
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
                                one = "{0} nanométer",
                                other = "{0} nanométer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} nanométert",
                                other = "{0} nanométert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} nanométerrel",
                                other = "{0} nanométerrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} nanométerig",
                                other = "{0} nanométerig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} nanométerré",
                                other = "{0} nanométerré",
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
                                one = "{0} pikométer",
                                other = "{0} pikométer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} pikométert",
                                other = "{0} pikométert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} pikométerrel",
                                other = "{0} pikométerrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} pikométerig",
                                other = "{0} pikométerig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} pikométerré",
                                other = "{0} pikométerré",
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
                                one = "{0} mérföld",
                                other = "{0} mérföld",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mf",
                                other = "{0} mf",
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
                                one = "{0} yard",
                                other = "{0} yard",
                            )
                        ),
                    )
                ),
            )
        override val lengthFoot
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} láb",
                                other = "{0} láb",
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
                                one = "{0} hüvelyk",
                                other = "{0} hüvelyk",
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
                                one = "{0} parszek",
                                other = "{0} parszek",
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
                                one = "{0} fényév",
                                other = "{0} fényév",
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
                                one = "{0} csillagászati egység",
                                other = "{0} csillagászati egység",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} CsE",
                                other = "{0} CsE",
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
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} öl",
                                other = "{0} öl",
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
                                one = "{0} tengeri mérföld",
                                other = "{0} tengeri mérföld",
                            )
                        ),
                    )
                ),
            )
        override val lengthMileScandinavian
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} svéd mérföld",
                                other = "{0} svéd mérföld",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} svéd mérföldet",
                                other = "{0} svéd mérföldet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} svéd mérfölddel",
                                other = "{0} svéd mérfölddel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} svéd mérföldig",
                                other = "{0} svéd mérföldig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} svéd mérfölddé",
                                other = "{0} svéd mérfölddé",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} mil",
                                other = "{0} mil",
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
                                one = "{0} pont",
                                other = "{0} pont",
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
                                one = "{0} Nap-sugár",
                                other = "{0} Nap-sugár",
                            )
                        ),
                    )
                ),
            )
        override val lightLux
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} lux",
                                other = "{0} lux",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} luxot",
                                other = "{0} luxot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} luxszal",
                                other = "{0} luxszal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} luxig",
                                other = "{0} luxig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} luxszá",
                                other = "{0} luxszá",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaPerSquareMeter formatter is not implemented yet for hu")
        override val lightCandela
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kandela",
                                other = "{0} kandela",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kandelát",
                                other = "{0} kandelát",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kandelával",
                                other = "{0} kandelával",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kandeláig",
                                other = "{0} kandeláig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kandelává",
                                other = "{0} kandelává",
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
                                one = "{0} lumen",
                                other = "{0} lumen",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} lument",
                                other = "{0} lument",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} lumennel",
                                other = "{0} lumennel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} lumenig",
                                other = "{0} lumenig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} lumenné",
                                other = "{0} lumenné",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaSquareMeterPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaSquareMeterPerSquareMeter formatter is not implemented yet for hu")
        override val lightSolarLuminosity
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Nap-fényerő",
                                other = "{0} Nap-fényerő",
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
                                one = "{0} metrikus tonna",
                                other = "{0} metrikus tonna",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} metrikus tonnát",
                                other = "{0} metrikus tonnát",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} metrikus tonnával",
                                other = "{0} metrikus tonnával",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} metrikus tonnáig",
                                other = "{0} metrikus tonnáig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} metrikus tonnává",
                                other = "{0} metrikus tonnává",
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
                                one = "{0} kilogramm",
                                other = "{0} kilogramm",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilogrammot",
                                other = "{0} kilogrammot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilogrammal",
                                other = "{0} kilogrammal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kilogrammig",
                                other = "{0} kilogrammig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kilogrammá",
                                other = "{0} kilogrammá",
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
                                one = "{0} gramm",
                                other = "{0} gramm",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} grammot",
                                other = "{0} grammot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} grammal",
                                other = "{0} grammal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} grammig",
                                other = "{0} grammig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} grammá",
                                other = "{0} grammá",
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
                                one = "{0} milligramm",
                                other = "{0} milligramm",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milligrammot",
                                other = "{0} milligrammot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milligrammal",
                                other = "{0} milligrammal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} milligrammig",
                                other = "{0} milligrammig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} milligrammá",
                                other = "{0} milligrammá",
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
                                one = "{0} mikrogramm",
                                other = "{0} mikrogramm",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} mikrogrammot",
                                other = "{0} mikrogrammot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} mikrogrammal",
                                other = "{0} mikrogrammal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} mikrogrammig",
                                other = "{0} mikrogrammig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} mikrogrammá",
                                other = "{0} mikrogrammá",
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
                                one = "{0} amerikai tonna",
                                other = "{0} amerikai tonna",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} font",
                                other = "{0} font",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} lb",
                                other = "{0} font",
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
                                one = "{0} uncia",
                                other = "{0} uncia",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} oz",
                                other = "{0} uncia",
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
                                one = "{0} troy uncia",
                                other = "{0} troy uncia",
                            )
                        ),
                    )
                ),
            )
        override val massCarat
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} karát",
                                other = "{0} karát",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} karátot",
                                other = "{0} karátot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} karáttal",
                                other = "{0} karáttal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} karátig",
                                other = "{0} karátig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} karáttá",
                                other = "{0} karáttá",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Kt",
                                other = "{0} Kt",
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
                                other = "{0} dalton",
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
                                one = "{0} Föld-tömeg",
                                other = "{0} Föld-tömeg",
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
                                one = "{0} Nap-tömeg",
                                other = "{0} Nap-tömeg",
                            )
                        ),
                    )
                ),
            )
        override val massGrain: MultiLengthGenderedPattern
            get() = TODO("Grain formatter is not implemented yet for hu")
        override val powerGigawatt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} gigawatt",
                                other = "{0} gigawatt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} gigawattot",
                                other = "{0} gigawattot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} gigawattal",
                                other = "{0} gigawattal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} gigawattig",
                                other = "{0} gigawattig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} gigawattá",
                                other = "{0} gigawattá",
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
                                one = "{0} megawatt",
                                other = "{0} megawatt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megawattot",
                                other = "{0} megawattot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} megawattal",
                                other = "{0} megawattal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} megawattig",
                                other = "{0} megawattig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} megawattá",
                                other = "{0} megawattá",
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
                                one = "{0} kilowatt",
                                other = "{0} kilowatt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilowattot",
                                other = "{0} kilowattot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilowattal",
                                other = "{0} kilowattal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kilowattig",
                                other = "{0} kilowattig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kilowattá",
                                other = "{0} kilowattá",
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
                                one = "{0} watt",
                                other = "{0} watt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} wattot",
                                other = "{0} wattot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} wattal",
                                other = "{0} wattal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} wattig",
                                other = "{0} wattig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} wattá",
                                other = "{0} wattá",
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
                                one = "{0} milliwatt",
                                other = "{0} milliwatt",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} milliwattot",
                                other = "{0} milliwattot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milliwattal",
                                other = "{0} milliwattal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} milliwattig",
                                other = "{0} milliwattig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} milliwattá",
                                other = "{0} milliwattá",
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
                                one = "{0} lóerő",
                                other = "{0} lóerő",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} LE",
                                other = "{0} LE",
                            )
                        ),
                    )
                ),
            )
        override val powerKilogramSquareMeterPerCubicSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecond formatter is not implemented yet for hu")
        override val pressureMillimeterOfhg
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} higanymilliméter",
                                other = "{0} higanymilliméter",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Hgmm",
                                other = "{0} Hgmm",
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
                                one = "{0} font per négyzethüvelyk",
                                other = "{0} font per négyzethüvelyk",
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
                                one = "{0} higanyhüvelyk",
                                other = "{0} higanyhüvelyk",
                            )
                        ),
                    )
                ),
            )
        override val pressureBar
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bar",
                                other = "{0} bar",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} bart",
                                other = "{0} bart",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} barral",
                                other = "{0} barral",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} barig",
                                other = "{0} barig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} barrá",
                                other = "{0} barrá",
                            )
                        ),
                    )
                ),
            )
        override val pressureMillibar
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} millibar",
                                other = "{0} millibar",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} millibart",
                                other = "{0} millibart",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} millibarral",
                                other = "{0} millibarral",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} millibarig",
                                other = "{0} millibarig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} millibarrá",
                                other = "{0} millibarrá",
                            )
                        ),
                    )
                ),
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
        override val pressureAtmosphere
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} atmoszféra",
                                other = "{0} atmoszféra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} atmoszférát",
                                other = "{0} atmoszférát",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} atmoszférával",
                                other = "{0} atmoszférával",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} atmoszféráig",
                                other = "{0} atmoszféráig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} atmoszférává",
                                other = "{0} atmoszférává",
                            )
                        ),
                    )
                ),
            )
        override val pressurePascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} pascal",
                                other = "{0} pascal",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} pascalt",
                                other = "{0} pascalt",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} pascallal",
                                other = "{0} pascallal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} pascalig",
                                other = "{0} pascalig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} pascallá",
                                other = "{0} pascallá",
                            )
                        ),
                    )
                ),
            )
        override val pressureHectopascal
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hektopascal",
                                other = "{0} hektopascal",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} hektopascalt",
                                other = "{0} hektopascalt",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} hektopascallal",
                                other = "{0} hektopascallal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} hektopascalig",
                                other = "{0} hektopascalig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} hektopascallá",
                                other = "{0} hektopascallá",
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
                                one = "{0} kilopascal",
                                other = "{0} kilopascal",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilopascalt",
                                other = "{0} kilopascalt",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilopascallal",
                                other = "{0} kilopascallal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kilopascalig",
                                other = "{0} kilopascalig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kilopascallá",
                                other = "{0} kilopascallá",
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
                                one = "{0} megapascal",
                                other = "{0} megapascal",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megapascalt",
                                other = "{0} megapascalt",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} megapascallal",
                                other = "{0} megapascallal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} megapascalig",
                                other = "{0} megapascalig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} megapascallá",
                                other = "{0} megapascallá",
                            )
                        ),
                    )
                ),
            )
        override val pressureGasolineEnergyDensity: MultiLengthGenderedPattern
            get() = TODO("GasolineEnergyDensity formatter is not implemented yet for hu")
        override val pressureKilogramPerMeterSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramPerMeterSquareSecond formatter is not implemented yet for hu")
        override val speedKilometerPerHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kilométer per óra",
                                other = "{0} kilométer per óra",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kilométer per órát",
                                other = "{0} kilométer per órát",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kilométer per órával",
                                other = "{0} kilométer per órával",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kilométer per óráig",
                                other = "{0} kilométer per óráig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kilométer per órává",
                                other = "{0} kilométer per órává",
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
                                one = "{0} méter per másodperc",
                                other = "{0} méter per másodperc",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} méter per másodpercet",
                                other = "{0} méter per másodpercet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} méter per másodperccel",
                                other = "{0} méter per másodperccel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} méter per másodpercig",
                                other = "{0} méter per másodpercig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} méter per másodperccé",
                                other = "{0} méter per másodperccé",
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
                                one = "{0} mérföld per óra",
                                other = "{0} mérföld per óra",
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
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} csomó",
                                other = "{0} csomó",
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
                                one = "Beaufort {0}",
                                other = "Beaufort {0}",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "B{0}",
                                other = "B{0}",
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
                                one = "{0} fok",
                                other = "{0} fok",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} fokot",
                                other = "{0} fokot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} fokkal",
                                other = "{0} fokkal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} fokig",
                                other = "{0} fokig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} fokká",
                                other = "{0} fokká",
                            )
                        ),
                    )
                ),
            )
        override val temperatureCelsius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Celsius-fok",
                                other = "{0} Celsius-fok",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} Celsius-fokot",
                                other = "{0} Celsius-fokot",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} Celsius-fokkal",
                                other = "{0} Celsius-fokkal",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} Celsius-fokig",
                                other = "{0} Celsius-fokig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} Celsius-fokká",
                                other = "{0} Celsius-fokká",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Fahrenheit-fok",
                                other = "{0} Fahrenheit-fok",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kelvin",
                                other = "{0} kelvin",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} kelvint",
                                other = "{0} kelvint",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} kelvinnel",
                                other = "{0} kelvinnel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} kelvinig",
                                other = "{0} kelvinig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} kelvinné",
                                other = "{0} kelvinné",
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
                                one = "{0} fontláb",
                                other = "{0} fontláb",
                            )
                        ),
                    )
                ),
            )
        override val torqueNewtonMeter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} newtonméter",
                                other = "{0} newtonméter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} newtonmétert",
                                other = "{0} newtonmétert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} newtonméterrel",
                                other = "{0} newtonméterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} newtonméterig",
                                other = "{0} newtonméterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} newtonméterré",
                                other = "{0} newtonméterré",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} köbkilométer",
                                other = "{0} köbkilométer",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} köbkilométert",
                                other = "{0} köbkilométert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} köbkilométerrel",
                                other = "{0} köbkilométerrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} köbkilométerig",
                                other = "{0} köbkilométerig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} köbkilométerré",
                                other = "{0} köbkilométerré",
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
                                one = "{0} köbméter",
                                other = "{0} köbméter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} köbmétert",
                                other = "{0} köbmétert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} köbméterrel",
                                other = "{0} köbméterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} köbméterig",
                                other = "{0} köbméterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} köbméterré",
                                other = "{0} köbméterré",
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
                                one = "{0} köbcentiméter",
                                other = "{0} köbcentiméter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} köbcentimétert",
                                other = "{0} köbcentimétert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} köbcentiméterrel",
                                other = "{0} köbcentiméterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} köbcentiméterig",
                                other = "{0} köbcentiméterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} köbcentiméterré",
                                other = "{0} köbcentiméterré",
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
                                one = "{0} köbmérföld",
                                other = "{0} köbmérföld",
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
                                one = "{0} köbyard",
                                other = "{0} köbyard",
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
                                one = "{0} köbláb",
                                other = "{0} köbláb",
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
                                one = "{0} köbhüvelyk",
                                other = "{0} köbhüvelyk",
                            )
                        ),
                    )
                ),
            )
        override val volumeMegaliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} megaliter",
                                other = "{0} megaliter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} megalitert",
                                other = "{0} megalitert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} megaliterrel",
                                other = "{0} megaliterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} megaliterig",
                                other = "{0} megaliterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} megaliterré",
                                other = "{0} megaliterré",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hektoliter",
                                other = "{0} hektoliter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} hektolitert",
                                other = "{0} hektolitert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} hektoliterrel",
                                other = "{0} hektoliterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} hektoliterig",
                                other = "{0} hektoliterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} hektoliterré",
                                other = "{0} hektoliterré",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} liter",
                                other = "{0} liter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} litert",
                                other = "{0} litert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} literrel",
                                other = "{0} literrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} literig",
                                other = "{0} literig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} literré",
                                other = "{0} literré",
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
                                one = "{0} deciliter",
                                other = "{0} deciliter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} decilitert",
                                other = "{0} decilitert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} deciliterrel",
                                other = "{0} deciliterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} deciliterig",
                                other = "{0} deciliterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} deciliterré",
                                other = "{0} deciliterré",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} centiliter",
                                other = "{0} centiliter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} centilitert",
                                other = "{0} centilitert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} centiliterrel",
                                other = "{0} centiliterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} centiliterig",
                                other = "{0} centiliterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} centiliterré",
                                other = "{0} centiliterré",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} milliliter",
                                other = "{0} milliliter",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} millilitert",
                                other = "{0} millilitert",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} milliliterrel",
                                other = "{0} milliliterrel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} milliliterig",
                                other = "{0} milliliterig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} milliliterré",
                                other = "{0} milliliterré",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} metrikus pint",
                                other = "{0} metrikus pint",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} metrikus pintet",
                                other = "{0} metrikus pintet",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} metrikus pinttel",
                                other = "{0} metrikus pinttel",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} metrikus pintig",
                                other = "{0} metrikus pintig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} metrikus pintté",
                                other = "{0} metrikus pintté",
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
                                one = "{0} bögre",
                                other = "{0} bögre",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} bögrét",
                                other = "{0} bögrét",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} bögrével",
                                other = "{0} bögrével",
                            )
                        ),
                        terminative = PluralPattern(
                            Plurals(
                                one = "{0} bögréig",
                                other = "{0} bögréig",
                            )
                        ),
                        translative = PluralPattern(
                            Plurals(
                                one = "{0} bögrévé",
                                other = "{0} bögrévé",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bg",
                                other = "{0} bg",
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
                                one = "{0} hold-láb",
                                other = "{0} hold-láb",
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
                                one = "{0} véka",
                                other = "{0} véka",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} birodalmi gallon",
                                other = "{0} birodalmi gallon",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bir. gal",
                                other = "{0} bir. gal",
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
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} csésze",
                                other = "{0} csésze",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} cs.",
                                other = "{0} cs.",
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
                                one = "{0} folyadékuncia",
                                other = "{0} folyadékuncia",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bir. folyadék uncia",
                                other = "{0} bir. folyadék uncia",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bir. f. uncia",
                                other = "{0} bir. f. uncia",
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
                                one = "{0} evőkanál",
                                other = "{0} evőkanál",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ek.",
                                other = "{0} ek.",
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
                                one = "{0} kávéskanál",
                                other = "{0} kávéskanál",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} kk.",
                                other = "{0} kk.",
                            )
                        ),
                    )
                ),
            )
        override val volumeBarrel
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} hordó",
                                other = "{0} hordó",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoon
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} desszertkanál",
                                other = "{0} desszertkanál",
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
                                one = "{0} bir. desszertkanál",
                                other = "{0} bir. desszertkanál",
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
                                one = "{0} csepp",
                                other = "{0} csepp",
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
                                one = "{0} dram",
                                other = "{0} dram",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} fl dram",
                                other = "{0} fl dram",
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
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} adagolópohár",
                                other = "{0} adagolópohár",
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
                                one = "{0} csipet",
                                other = "{0} csipet",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} csi",
                                other = "{0} csi",
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
                                one = "{0} birodalmi kvart",
                                other = "{0} birodalmi kvart",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} bir. qt",
                                other = "{0} bir. qt",
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
