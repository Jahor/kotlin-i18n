package info.leonenko.i18n.languages

import info.leonenko.i18n.*
import info.leonenko.i18n.rbnf.RBNFRu

val Language.Companion.ru
    get() = languageRu
private val languageRu = LanguageRu()
open class LanguageRu internal constructor() : Language {
    override val code: String = "ru"
    override val language: String = "ru"
    override val script: String? = null
    override val territory: String? = null
    override val rbnf: RBNFRu
        get() = RBNFRu(this)
    override val pluralFormChooser = PluralFormChooser.Expression(
        one = { (v == 0) && ((i % 10) == 1L) && ((i % 100) != 11L) },
        few = { (v == 0) && ((i % 10) in 2L..4L) && ((i % 100) !in 12L..14L) },
        many = { (v == 0) && ((i % 10) == 0L) || (v == 0) && ((i % 10) in 5L..9L) || (v == 0) && ((i % 100) in 11L..14L) },
    )

    open class EllipsisLanguage internal constructor() : info.leonenko.i18n.EllipsisLanguage {
        override val formatter = EllipsisFormatter.Default
        override val wordFormatter = EllipsisFormatter.WordDefault
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    open class ListLanguage internal constructor(val ellipsis: EllipsisLanguage) : info.leonenko.i18n.ListLanguage {
        override val formatter: ListFormatter = MultiLengthPatternedListFormatter(
            long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} и {1}", two = "{0} и {1}", ellipsis = ellipsis.wordFormatter),
            narrow = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, {1}", two = "{0}, {1}", ellipsis = ellipsis.wordFormatter),
        )
        override val orFormatter: ListFormatter =
            MultiLengthPatternedListFormatter(long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} или {1}", two = "{0} или {1}", ellipsis = ellipsis.wordFormatter))
        override val unitFormatter: ListFormatter = MultiLengthPatternedListFormatter(
            short = PatternedListFormatter(start = "{0} {1}", middle = "{0} {1}", end = "{0} {1}", two = "{0} {1}", ellipsis = ellipsis.wordFormatter),
        )
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    open class MeasurementUnitLanguage internal constructor() : info.leonenko.i18n.units.MeasurementUnitLanguage {
        override val accelerationGForce
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} g",
                                few = "{0} g",
                                many = "{0} g",
                                other = "{0} g",
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
                                one = "{0} метр на секунду в квадрате",
                                few = "{0} метра на секунду в квадрате",
                                many = "{0} метров на секунду в квадрате",
                                other = "{0} метра на секунду в квадрате",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} метр на секунду в квадрате",
                                few = "{0} метра на секунду в квадрате",
                                many = "{0} метров на секунду в квадрате",
                                other = "{0} метра на секунду в квадрате",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} метру на секунду в квадрате",
                                few = "{0} метрам на секунду в квадрате",
                                many = "{0} метрам на секунду в квадрате",
                                other = "{0} метра на секунду в квадрате",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} метра на секунду в квадрате",
                                few = "{0} метров на секунду в квадрате",
                                many = "{0} метров на секунду в квадрате",
                                other = "{0} метра на секунду в квадрате",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} метром на секунду в квадрате",
                                few = "{0} метрами на секунду в квадрате",
                                many = "{0} метрами на секунду в квадрате",
                                other = "{0} метра на секунду в квадрате",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} метре на секунду в квадрате",
                                few = "{0} метрах на секунду в квадрате",
                                many = "{0} метрах на секунду в квадрате",
                                other = "{0} метрах на секунду в квадрате",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} метре на секунду в квадрате",
                                few = "{0} метрах на секунду в квадрате",
                                many = "{0} метрах на секунду в квадрате",
                                other = "{0} метра на секунду в квадрате",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
        override val angleRevolution
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} оборот",
                                few = "{0} оборота",
                                many = "{0} оборотов",
                                other = "{0} оборота",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} оборот",
                                few = "{0} оборота",
                                many = "{0} оборотов",
                                other = "{0} оборота",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} обороту",
                                few = "{0} оборотам",
                                many = "{0} оборотам",
                                other = "{0} оборота",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} оборота",
                                few = "{0} оборотов",
                                many = "{0} оборотов",
                                other = "{0} оборота",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} оборотом",
                                few = "{0} оборотами",
                                many = "{0} оборотами",
                                other = "{0} оборота",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} обороте",
                                few = "{0} оборотах",
                                many = "{0} оборотах",
                                other = "{0} оборота",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} об.",
                                few = "{0} об.",
                                many = "{0} об.",
                                other = "{0} об.",
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
                                one = "{0} радиан",
                                few = "{0} радиана",
                                many = "{0} радиан",
                                other = "{0} радиана",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} радиан",
                                few = "{0} радиана",
                                many = "{0} радиан",
                                other = "{0} радиана",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} радиану",
                                few = "{0} радианам",
                                many = "{0} радианам",
                                other = "{0} радиана",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} радиана",
                                few = "{0} радиан",
                                many = "{0} радиан",
                                other = "{0} радиана",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} радианом",
                                few = "{0} радианами",
                                many = "{0} радианами",
                                other = "{0} радиана",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} радиане",
                                few = "{0} радианах",
                                many = "{0} радианах",
                                other = "{0} радиана",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} рад",
                                few = "{0} рад",
                                many = "{0} рад",
                                other = "{0} рад",
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
                                one = "{0} градус",
                                few = "{0} градуса",
                                many = "{0} градусов",
                                other = "{0} градуса",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} градус",
                                few = "{0} градуса",
                                many = "{0} градусов",
                                other = "{0} градуса",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} градусу",
                                few = "{0} градусам",
                                many = "{0} градусам",
                                other = "{0} градуса",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} градуса",
                                few = "{0} градусов",
                                many = "{0} градусов",
                                other = "{0} градуса",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} градусом",
                                few = "{0} градусами",
                                many = "{0} градусами",
                                other = "{0} градуса",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} градусе",
                                few = "{0} градусах",
                                many = "{0} градусах",
                                other = "{0} градуса",
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
                                one = "{0} минута",
                                few = "{0} минуты",
                                many = "{0} минут",
                                other = "{0} минуты",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} минуту",
                                few = "{0} минуты",
                                many = "{0} минут",
                                other = "{0} минуты",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} минуте",
                                few = "{0} минутам",
                                many = "{0} минутам",
                                other = "{0} минуты",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} минуты",
                                few = "{0} минут",
                                many = "{0} минут",
                                other = "{0} минуты",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} минутой",
                                few = "{0} минутами",
                                many = "{0} минутами",
                                other = "{0} минуты",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} минуте",
                                few = "{0} минутах",
                                many = "{0} минутах",
                                other = "{0} минуты",
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
                                one = "{0} секунда",
                                few = "{0} секунды",
                                many = "{0} секунд",
                                other = "{0} секунды",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} секунду",
                                few = "{0} секунды",
                                many = "{0} секунд",
                                other = "{0} секунды",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} секунде",
                                few = "{0} секундам",
                                many = "{0} секундам",
                                other = "{0} секунды",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} секунды",
                                few = "{0} секунд",
                                many = "{0} секунд",
                                other = "{0} секунды",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} секундой",
                                few = "{0} секундами",
                                many = "{0} секундами",
                                other = "{0} секунды",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} секунде",
                                few = "{0} секундах",
                                many = "{0} секундах",
                                other = "{0} секунды",
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
                                one = "{0} квадратный километр",
                                few = "{0} квадратных километра",
                                many = "{0} квадратных километров",
                                other = "{0} квадратного километра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} квадратный километр",
                                few = "{0} квадратных километра",
                                many = "{0} квадратных километров",
                                other = "{0} квадратного километра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} квадратному километру",
                                few = "{0} квадратным километрам",
                                many = "{0} квадратным километрам",
                                other = "{0} квадратного километра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} квадратного километра",
                                few = "{0} квадратных километров",
                                many = "{0} квадратных километров",
                                other = "{0} квадратного километра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} квадратным километром",
                                few = "{0} квадратными километрами",
                                many = "{0} квадратными километрами",
                                other = "{0} квадратного километра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} квадратном километре",
                                few = "{0} квадратных километрах",
                                many = "{0} квадратных километрах",
                                other = "{0} квадратных километрах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} квадратном километре",
                                few = "{0} квадратных километрах",
                                many = "{0} квадратных километрах",
                                other = "{0} квадратного километра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} км²",
                                few = "{0} км²",
                                many = "{0} км²",
                                other = "{0} км²",
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
                                one = "{0} гектар",
                                few = "{0} гектара",
                                many = "{0} гектаров",
                                other = "{0} гектара",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гектар",
                                few = "{0} гектара",
                                many = "{0} гектаров",
                                other = "{0} гектара",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} гектару",
                                few = "{0} гектарам",
                                many = "{0} гектарам",
                                other = "{0} гектара",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} гектара",
                                few = "{0} гектаров",
                                many = "{0} гектаров",
                                other = "{0} гектара",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} гектаром",
                                few = "{0} гектарами",
                                many = "{0} гектарами",
                                other = "{0} гектара",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} гектаре",
                                few = "{0} гектарах",
                                many = "{0} гектарах",
                                other = "{0} гектарах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} гектаре",
                                few = "{0} гектарах",
                                many = "{0} гектарах",
                                other = "{0} гектара",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} га",
                                few = "{0} га",
                                many = "{0} га",
                                other = "{0} га",
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
                                one = "{0} квадратный метр",
                                few = "{0} квадратных метра",
                                many = "{0} квадратных метров",
                                other = "{0} квадратного метра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} квадратный метр",
                                few = "{0} квадратных метра",
                                many = "{0} квадратных метров",
                                other = "{0} квадратного метра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} квадратному метру",
                                few = "{0} квадратным метрам",
                                many = "{0} квадратным метрам",
                                other = "{0} квадратного метра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} квадратного метра",
                                few = "{0} квадратных метров",
                                many = "{0} квадратных метров",
                                other = "{0} квадратного метра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} квадратным метром",
                                few = "{0} квадратными метрами",
                                many = "{0} квадратными метрами",
                                other = "{0} квадратного метра",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} квадратном метре",
                                few = "{0} квадратных метрах",
                                many = "{0} квадратных метрах",
                                other = "{0} квадратного метра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} м²",
                                few = "{0} м²",
                                many = "{0} м²",
                                other = "{0} м²",
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
                                one = "{0} квадратный сантиметр",
                                few = "{0} квадратных сантиметра",
                                many = "{0} квадратных сантиметров",
                                other = "{0} квадратного сантиметра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} квадратный сантиметр",
                                few = "{0} квадратных сантиметра",
                                many = "{0} квадратных сантиметров",
                                other = "{0} квадратного сантиметра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} квадратному сантиметру",
                                few = "{0} квадратным сантиметрам",
                                many = "{0} квадратным сантиметрам",
                                other = "{0} квадратного сантиметра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} квадратного сантиметра",
                                few = "{0} квадратных сантиметров",
                                many = "{0} квадратных сантиметров",
                                other = "{0} квадратного сантиметра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} квадратным сантиметром",
                                few = "{0} квадратными сантиметрами",
                                many = "{0} квадратными сантиметрами",
                                other = "{0} квадратного сантиметра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} квадратном сантиметре",
                                few = "{0} квадратных сантиметрах",
                                many = "{0} квадратных сантиметрах",
                                other = "{0} квадратных сантиметрах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} квадратном сантиметре",
                                few = "{0} квадратных сантиметрах",
                                many = "{0} квадратных сантиметрах",
                                other = "{0} квадратного сантиметра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} см²",
                                few = "{0} см²",
                                many = "{0} см²",
                                other = "{0} см²",
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
                                one = "{0} квадратная миля",
                                few = "{0} квадратные мили",
                                many = "{0} квадратных миль",
                                other = "{0} квадратной мили",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} квадратную милю",
                                few = "{0} квадратные мили",
                                many = "{0} квадратных миль",
                                other = "{0} квадратной мили",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} квадратной миле",
                                few = "{0} квадратным милям",
                                many = "{0} квадратным милям",
                                other = "{0} квадратной мили",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} квадратной мили",
                                few = "{0} квадратных миль",
                                many = "{0} квадратных миль",
                                other = "{0} квадратной мили",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} квадратной милей",
                                few = "{0} квадратными милями",
                                many = "{0} квадратными милями",
                                other = "{0} квадратной мили",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} квадратной миле",
                                few = "{0} квадратных милях",
                                many = "{0} квадратных милях",
                                other = "{0} квадратных милях",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} квадратной миле",
                                few = "{0} квадратных милях",
                                many = "{0} квадратных милях",
                                other = "{0} квадратных милях",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ми²",
                                few = "{0} ми²",
                                many = "{0} ми²",
                                other = "{0} ми²",
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
                                one = "{0} акр",
                                few = "{0} акра",
                                many = "{0} акров",
                                other = "{0} акра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} акр",
                                few = "{0} акра",
                                many = "{0} акров",
                                other = "{0} акра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} акру",
                                few = "{0} акрам",
                                many = "{0} акрам",
                                other = "{0} акра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} акра",
                                few = "{0} акров",
                                many = "{0} акров",
                                other = "{0} акра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} акром",
                                few = "{0} акрами",
                                many = "{0} акрами",
                                other = "{0} акра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} акре",
                                few = "{0} акрах",
                                many = "{0} акрах",
                                other = "{0} акрах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} акре",
                                few = "{0} акрах",
                                many = "{0} акрах",
                                other = "{0} акрах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} акр",
                                few = "{0} акр.",
                                many = "{0} акр.",
                                other = "{0} акр.",
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
                                one = "{0} квадратный ярд",
                                few = "{0} квадратных ярда",
                                many = "{0} квадратных ярдов",
                                other = "{0} квадратного ярда",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ярд²",
                                few = "{0} ярд²",
                                many = "{0} ярд²",
                                other = "{0} ярд²",
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
                                one = "{0} квадратный фут",
                                few = "{0} квадратных фута",
                                many = "{0} квадратных футов",
                                other = "{0} квадратного фута",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} квадратный фут",
                                few = "{0} квадратных фута",
                                many = "{0} квадратных футов",
                                other = "{0} квадратного фута",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} квадратному футу",
                                few = "{0} квадратным футам",
                                many = "{0} квадратным футам",
                                other = "{0} квадратного фута",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} квадратного фута",
                                few = "{0} квадратных футов",
                                many = "{0} квадратных футов",
                                other = "{0} квадратного фута",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} квадратным футом",
                                few = "{0} квадратными футами",
                                many = "{0} квадратными футами",
                                other = "{0} квадратного фута",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} квадратном футе",
                                few = "{0} квадратных футах",
                                many = "{0} квадратных футах",
                                other = "{0} квадратных футах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} квадратном футе",
                                few = "{0} квадратных футах",
                                many = "{0} квадратных футах",
                                other = "{0} квадратных футах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фт²",
                                few = "{0} фт²",
                                many = "{0} фт²",
                                other = "{0} фт²",
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
                                one = "{0} квадратный дюйм",
                                few = "{0} квадратных дюйма",
                                many = "{0} квадратных дюймов",
                                other = "{0} квадратного дюйма",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дюйм²",
                                few = "{0} дюйм²",
                                many = "{0} дюйм²",
                                other = "{0} дюйм²",
                            )
                        ),
                    )
                ),
            )
        override val areaDunam
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дунам",
                                few = "{0} дунама",
                                many = "{0} дунамов",
                                other = "{0} дунама",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дун.",
                                few = "{0} дун.",
                                many = "{0} дун.",
                                other = "{0} дун.",
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
                                one = "{0} карат",
                                few = "{0} карата",
                                many = "{0} карат",
                                other = "{0} карата",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} карат",
                                few = "{0} карата",
                                many = "{0} карат",
                                other = "{0} карата",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} карату",
                                few = "{0} каратам",
                                many = "{0} каратам",
                                other = "{0} карата",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} карата",
                                few = "{0} карат",
                                many = "{0} карат",
                                other = "{0} карата",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} каратом",
                                few = "{0} каратами",
                                many = "{0} каратами",
                                other = "{0} карата",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} карате",
                                few = "{0} каратах",
                                many = "{0} каратах",
                                other = "{0} карата",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кар",
                                few = "{0} кар",
                                many = "{0} кар",
                                other = "{0} кар",
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
                                one = "{0} миллионная доля",
                                few = "{0} миллионные доли",
                                many = "{0} миллионных долей",
                                other = "{0} миллионной доли",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} миллионную долю",
                                few = "{0} миллионные доли",
                                many = "{0} миллионных долей",
                                other = "{0} миллионной доли",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миллионной доле",
                                few = "{0} миллионым долям",
                                many = "{0} миллионым долям",
                                other = "{0} миллионной доли",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} миллионной доли",
                                few = "{0} миллионых долей",
                                many = "{0} миллионных долей",
                                other = "{0} миллионной доли",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} миллионной долей",
                                few = "{0} миллиоными долями",
                                many = "{0} миллионными долями",
                                other = "{0} миллионной доли",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миллионной доле",
                                few = "{0} миллионых долях",
                                many = "{0} миллионных долях",
                                other = "{0} миллионной доли",
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
                                one = "{0} процент",
                                few = "{0} процента",
                                many = "{0} процентов",
                                other = "{0} процента",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} процент",
                                few = "{0} процента",
                                many = "{0} процентов",
                                other = "{0} процента",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} проценту",
                                few = "{0} процентам",
                                many = "{0} процентам",
                                other = "{0} процента",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} процента",
                                few = "{0} процентов",
                                many = "{0} процентов",
                                other = "{0} процента",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} процентом",
                                few = "{0} процентами",
                                many = "{0} процентами",
                                other = "{0} процента",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} проценте",
                                few = "{0} процентах",
                                many = "{0} процентах",
                                other = "{0} процентах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} проценте",
                                few = "{0} процентах",
                                many = "{0} процентах",
                                other = "{0} процента",
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
                                many = "{0} %",
                                other = "{0} %",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}%",
                                few = "{0}%",
                                many = "{0}%",
                                other = "{0}%",
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
                                one = "{0} промилле",
                                few = "{0} промилле",
                                many = "{0} промилле",
                                other = "{0} промилле",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} промилле",
                                few = "{0} промилле",
                                many = "{0} промилле",
                                other = "{0} промилле",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} промилле",
                                few = "{0} промилле",
                                many = "{0} промилле",
                                other = "{0} промилле",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} промилле",
                                few = "{0} промилле",
                                many = "{0} промилле",
                                other = "{0} промилле",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} промилле",
                                few = "{0} промилле",
                                many = "{0} промилле",
                                other = "{0} промилле",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} промилле",
                                few = "{0} промилле",
                                many = "{0} промилле",
                                other = "{0} промилле",
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
                                many = "{0} ‰",
                                other = "{0} ‰",
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
                                one = "{0} промириад",
                                few = "{0} промириада",
                                many = "{0} промириадов",
                                other = "{0} промириада",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} промириад",
                                few = "{0} промириада",
                                many = "{0} промириад",
                                other = "{0} промириада",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} промириаду",
                                few = "{0} промириадам",
                                many = "{0} промириадам",
                                other = "{0} промириада",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} промириада",
                                few = "{0} промириад",
                                many = "{0} промириад",
                                other = "{0} промириада",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} промириадом",
                                few = "{0} промириадами",
                                many = "{0} промириадами",
                                other = "{0} промириада",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} промириаде",
                                few = "{0} промириадах",
                                many = "{0} промириадах",
                                other = "{0} промириада",
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
                                many = "{0} ‱",
                                other = "{0} ‱",
                            )
                        ),
                    )
                ),
            )
        override val concentrationAPortion: MultiLengthGenderedPattern
            get() = TODO("APortion formatter is not implemented yet for ru")
        override val concentrationMilligramOfglucosePerDeciliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} миллиграмм на децилитр",
                                few = "{0} миллиграмма на децилитр",
                                many = "{0} миллиграммов на децилитр",
                                other = "{0} миллиграмма на децилитр",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} миллиграмм на децилитр",
                                few = "{0} миллиграмма на децилитр",
                                many = "{0} миллиграммов на децилитр",
                                other = "{0} миллиграмма на децилитр",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миллиграмму на децилитр",
                                few = "{0} миллиграммам на децилитр",
                                many = "{0} миллиграммам на децилитр",
                                other = "{0} миллиграмма на децилитр",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} миллиграмма на децилитр",
                                few = "{0} миллиграммов на децилитр",
                                many = "{0} миллиграммов на децилитр",
                                other = "{0} миллиграмма на децилитр",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} миллиграммом на децилитр",
                                few = "{0} миллиграммами на децилитр",
                                many = "{0} миллиграммами на децилитр",
                                other = "{0} миллиграмма на децилитр",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миллиграмме на децилитр",
                                few = "{0} миллиграммах на децилитр",
                                many = "{0} миллиграммах на децилитр",
                                other = "{0} миллиграмма на децилитр",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мг/дл",
                                few = "{0} мг/дл",
                                many = "{0} мг/дл",
                                other = "{0} мг/дл",
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
                                one = "{0} миллимоль на литр",
                                few = "{0} миллимоля на литр",
                                many = "{0} миллимолей на литр",
                                other = "{0} миллимоля на литр",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} миллимоль на литр",
                                few = "{0} миллимоля на литр",
                                many = "{0} миллимолей на литр",
                                other = "{0} миллимоля на литр",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миллимолю на литр",
                                few = "{0} миллимолям на литр",
                                many = "{0} миллимолям на литр",
                                other = "{0} миллимоля на литр",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} миллимоля на литр",
                                few = "{0} миллимолей на литр",
                                many = "{0} миллимолей на литр",
                                other = "{0} миллимоля на литр",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} миллимолем на литр",
                                few = "{0} миллимолями на литр",
                                many = "{0} миллимолями на литр",
                                other = "{0} миллимоля на литр",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миллимоле на литр",
                                few = "{0} миллимолях на литр",
                                many = "{0} миллимолях на литр",
                                other = "{0} миллимоля на литр",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ммоль/л",
                                few = "{0} ммоль/л",
                                many = "{0} ммоль/л",
                                other = "{0} ммоль/л",
                            )
                        ),
                    )
                ),
            )
        override val concentrationPortionPer1e9
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} миллиардная доля",
                                few = "{0} миллиардные доли",
                                many = "{0} миллиардных долей",
                                other = "{0} миллиардной доли",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} миллиардную долю",
                                few = "{0} миллиардные доли",
                                many = "{0} миллиардных долей",
                                other = "{0} миллиардной доли",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миллиардной доле",
                                few = "{0} миллиардным долям",
                                many = "{0} миллиардным долям",
                                other = "{0} миллиардной доли",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} миллиардной доли",
                                few = "{0} миллиардных долей",
                                many = "{0} миллиардных долей",
                                other = "{0} миллиардной доли",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} миллиардной долей",
                                few = "{0} миллиардными долями",
                                many = "{0} миллиардными долями",
                                other = "{0} миллиардной доли",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миллиардной доле",
                                few = "{0} миллиардных долях",
                                many = "{0} миллиардных долях",
                                other = "{0} миллиардной доли",
                            )
                        ),
                    )
                ),
            )
        override val concentrationItem
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} объект",
                                few = "{0} объекта",
                                many = "{0} объектов",
                                other = "{0} объекта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} объект",
                                few = "{0} объекта",
                                many = "{0} объектов",
                                other = "{0} объекта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} объекту",
                                few = "{0} объектам",
                                many = "{0} объектам",
                                other = "{0} объекта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} объекта",
                                few = "{0} объектов",
                                many = "{0} объектов",
                                other = "{0} объекта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} объектом",
                                few = "{0} объектами",
                                many = "{0} объектами",
                                other = "{0} объекта",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} объекте",
                                few = "{0} объектах",
                                many = "{0} объектах",
                                other = "{0} объекта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} объект",
                                few = "{0} объекта",
                                many = "{0} объектов",
                                other = "{0} объекта",
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
                                one = "{0} моль",
                                few = "{0} моля",
                                many = "{0} молей",
                                other = "{0} моля",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} моль",
                                few = "{0} моля",
                                many = "{0} молей",
                                other = "{0} моля",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} молю",
                                few = "{0} молям",
                                many = "{0} молям",
                                other = "{0} моля",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} моля",
                                few = "{0} молей",
                                many = "{0} молей",
                                other = "{0} моля",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} молем",
                                few = "{0} молями",
                                many = "{0} молями",
                                other = "{0} моля",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} моле",
                                few = "{0} молях",
                                many = "{0} молях",
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
                                few = "{0} моль",
                                many = "{0} моль",
                                other = "{0} моль",
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
                                one = "{0} литр на километр",
                                few = "{0} литра на километр",
                                many = "{0} литров на километр",
                                other = "{0} литра на километр",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} литр на километр",
                                few = "{0} литра на километр",
                                many = "{0} литров на километр",
                                other = "{0} литра на километр",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} литру на километр",
                                few = "{0} литрам на километр",
                                many = "{0} литрам на километр",
                                other = "{0} литра на километр",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} литра на километр",
                                few = "{0} литров на километр",
                                many = "{0} литров на километр",
                                other = "{0} литра на километр",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} литром на километр",
                                few = "{0} литрами на километр",
                                many = "{0} литрами на километр",
                                other = "{0} литра на километр",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} литре на километр",
                                few = "{0} литрах на километр",
                                many = "{0} литрах на километр",
                                other = "{0} литра на километр",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} л/км",
                                few = "{0} л/км",
                                many = "{0} л/км",
                                other = "{0} л/км",
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
                                one = "{0} литр на 100 километров",
                                few = "{0} литра на 100 километров",
                                many = "{0} литров на 100 километров",
                                other = "{0} литра на 100 километров",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} литр на 100 километров",
                                few = "{0} литра на 100 километров",
                                many = "{0} литров на 100 километров",
                                other = "{0} литра на 100 километров",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} литру на 100 километров",
                                few = "{0} литрам на 100 километров",
                                many = "{0} литрам на 100 километров",
                                other = "{0} литра на 100 километров",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} литра на 100 километров",
                                few = "{0} литров на 100 километров",
                                many = "{0} литров на 100 километров",
                                other = "{0} литра на 100 километров",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} литром на 100 километров",
                                few = "{0} литрами на 100 километров",
                                many = "{0} литрами на 100 километров",
                                other = "{0} литра на 100 километров",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} литре на 100 километров",
                                few = "{0} литрах на 100 километров",
                                many = "{0} литрах на 100 километров",
                                other = "{0} литрах на 100 километров",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} литре на 100 километров",
                                few = "{0} литрах на 100 километров",
                                many = "{0} литрах на 100 километров",
                                other = "{0} литра на 100 километров",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} л/100 км",
                                few = "{0} л/100 км",
                                many = "{0} л/100 км",
                                other = "{0} л/100 км",
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
                                one = "{0} миля на амер. галлон",
                                few = "{0} мили на амер. галлон",
                                many = "{0} миль на амер. галлон",
                                other = "{0} мили на амер. галлон",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} милю на амер. галлон",
                                few = "{0} мили на амер. галлон",
                                many = "{0} миль на амер. галлон",
                                other = "{0} мили на амер. галлон",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миле на амер. галлон",
                                few = "{0} милям на амер. галлон",
                                many = "{0} милям на амер. галлон",
                                other = "{0} мили на амер. галлон",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мили на амер. галлон",
                                few = "{0} миль на амер. галлон",
                                many = "{0} миль на амер. галлон",
                                other = "{0} мили на амер. галлон",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} милей на амер. галлон",
                                few = "{0} милями на амер. галлон",
                                many = "{0} милями на амер. галлон",
                                other = "{0} мили на амер. галлон",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} миле на амер. галлон",
                                few = "{0} милях на амер. галлон",
                                many = "{0} милях на амер. галлон",
                                other = "{0} милях на амер. галлон",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миле на амер. галлон",
                                few = "{0} милях на амер. галлон",
                                many = "{0} милях на амер. галлон",
                                other = "{0} милях на амер. галлон",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ми/ам. гал",
                                few = "{0} ми/ам. гал",
                                many = "{0} ми/ам. гал",
                                other = "{0} ми/ам. гал",
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
                                one = "{0} миля на имп. галлон",
                                few = "{0} мили на имп. галлон",
                                many = "{0} миль на имп. галлон",
                                other = "{0} мили на имп. галлон",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} милю на имп. галлон",
                                few = "{0} мили на имп. галлон",
                                many = "{0} миль на имп. галлон",
                                other = "{0} мили на имп. галлон",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миле на имп. галлон",
                                few = "{0} милям на имп. галлон",
                                many = "{0} милям на имп. галлон",
                                other = "{0} мили на имп. галлон",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мили на имп. галлон",
                                few = "{0} миль на имп. галлон",
                                many = "{0} миль на имп. галлон",
                                other = "{0} мили на имп. галлон",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} милей на имп. галлон",
                                few = "{0} милями на имп. галлон",
                                many = "{0} милями на имп. галлон",
                                other = "{0} мили на имп. галлон",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} миле на имп. галлон",
                                few = "{0} милях на имп. галлон",
                                many = "{0} милях на имп. галлон",
                                other = "{0} милях на имп. галлон",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миле на имп. галлон",
                                few = "{0} милях на имп. галлон",
                                many = "{0} милях на имп. галлон",
                                other = "{0} милях на имп. галлон",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} миля/имп. гал",
                                few = "{0} мили/имп. гал",
                                many = "{0} миль/имп. гал",
                                other = "{0} мили/имп. гал",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ми/имп. гал",
                                few = "{0} ми/имп. гал",
                                many = "{0} ми/имп. гал",
                                other = "{0} ми/имп. гал",
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
                                one = "{0} петабайт",
                                few = "{0} петабайта",
                                many = "{0} петабайт",
                                other = "{0} петабайта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} петабайт",
                                few = "{0} петабайта",
                                many = "{0} петабайт",
                                other = "{0} петабайта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} петабайту",
                                few = "{0} петабайтам",
                                many = "{0} петабайтам",
                                other = "{0} петабайта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} петабайта",
                                few = "{0} петабайт",
                                many = "{0} петабайт",
                                other = "{0} петабайта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} петабайтом",
                                few = "{0} петабайтами",
                                many = "{0} петабайтами",
                                other = "{0} петабайта",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} петабайте",
                                few = "{0} петабайтах",
                                many = "{0} петабайтах",
                                other = "{0} петабайта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ПБ",
                                few = "{0} ПБ",
                                many = "{0} ПБ",
                                other = "{0} ПБ",
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
                                one = "{0} терабайт",
                                few = "{0} терабайта",
                                many = "{0} терабайт",
                                other = "{0} терабайта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} терабайт",
                                few = "{0} терабайта",
                                many = "{0} терабайт",
                                other = "{0} терабайта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} терабайту",
                                few = "{0} терабайтам",
                                many = "{0} терабайтам",
                                other = "{0} терабайта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} терабайта",
                                few = "{0} терабайт",
                                many = "{0} терабайт",
                                other = "{0} терабайта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} терабайтом",
                                few = "{0} терабайтами",
                                many = "{0} терабайтами",
                                other = "{0} терабайта",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} терабайте",
                                few = "{0} терабайтах",
                                many = "{0} терабайтах",
                                other = "{0} терабайтах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} терабайте",
                                few = "{0} терабайтах",
                                many = "{0} терабайтах",
                                other = "{0} терабайта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ТБ",
                                few = "{0} ТБ",
                                many = "{0} ТБ",
                                other = "{0} ТБ",
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
                                one = "{0} терабит",
                                few = "{0} терабита",
                                many = "{0} терабит",
                                other = "{0} терабита",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} терабит",
                                few = "{0} терабита",
                                many = "{0} терабит",
                                other = "{0} терабита",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} терабиту",
                                few = "{0} терабитам",
                                many = "{0} терабитам",
                                other = "{0} терабита",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} терабита",
                                few = "{0} терабит",
                                many = "{0} терабит",
                                other = "{0} терабита",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} терабитом",
                                few = "{0} терабитами",
                                many = "{0} терабитами",
                                other = "{0} терабита",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} терабите",
                                few = "{0} терабитах",
                                many = "{0} терабитах",
                                other = "{0} терабитах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} терабите",
                                few = "{0} терабитах",
                                many = "{0} терабитах",
                                other = "{0} терабита",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Тбит",
                                few = "{0} Тбит",
                                many = "{0} Тбит",
                                other = "{0} Тбит",
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
                                one = "{0} гигабайт",
                                few = "{0} гигабайта",
                                many = "{0} гигабайт",
                                other = "{0} гигабайта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гигабайт",
                                few = "{0} гигабайта",
                                many = "{0} гигабайт",
                                other = "{0} гигабайта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} гигабайту",
                                few = "{0} гигабайтам",
                                many = "{0} гигабайтам",
                                other = "{0} гигабайта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} гигабайта",
                                few = "{0} гигабайт",
                                many = "{0} гигабайт",
                                other = "{0} гигабайта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} гигабайтом",
                                few = "{0} гигабайтами",
                                many = "{0} гигабайтами",
                                other = "{0} гигабайта",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} гигабайте",
                                few = "{0} гигабайтах",
                                many = "{0} гигабайтах",
                                other = "{0} гигабайтах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} гигабайте",
                                few = "{0} гигабайтах",
                                many = "{0} гигабайтах",
                                other = "{0} гигабайта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ГБ",
                                few = "{0} ГБ",
                                many = "{0} ГБ",
                                other = "{0} ГБ",
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
                                one = "{0} гигабит",
                                few = "{0} гигабита",
                                many = "{0} гигабит",
                                other = "{0} гигабита",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гигабит",
                                few = "{0} гигабита",
                                many = "{0} гигабит",
                                other = "{0} гигабита",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} гигабиту",
                                few = "{0} гигабитам",
                                many = "{0} гигабитам",
                                other = "{0} гигабита",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} гигабита",
                                few = "{0} гигабит",
                                many = "{0} гигабит",
                                other = "{0} гигабита",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} гигабитом",
                                few = "{0} гигабитами",
                                many = "{0} гигабитами",
                                other = "{0} гигабита",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} гигабите",
                                few = "{0} гигабитах",
                                many = "{0} гигабитах",
                                other = "{0} гигабитах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} гигабите",
                                few = "{0} гигабитах",
                                many = "{0} гигабитах",
                                other = "{0} гигабита",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Гбит",
                                few = "{0} Гбит",
                                many = "{0} Гбит",
                                other = "{0} Гбит",
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
                                one = "{0} мегабайт",
                                few = "{0} мегабайта",
                                many = "{0} мегабайт",
                                other = "{0} мегабайта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мегабайт",
                                few = "{0} мегабайта",
                                many = "{0} мегабайт",
                                other = "{0} мегабайта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мегабайту",
                                few = "{0} мегабайтам",
                                many = "{0} мегабайтам",
                                other = "{0} мегабайта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мегабайта",
                                few = "{0} мегабайт",
                                many = "{0} мегабайт",
                                other = "{0} мегабайта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мегабайтом",
                                few = "{0} мегабайтами",
                                many = "{0} мегабайтами",
                                other = "{0} мегабайта",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мегабайте",
                                few = "{0} мегабайтах",
                                many = "{0} мегабайтах",
                                other = "{0} мегабайтах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} мегабайте",
                                few = "{0} мегабайтах",
                                many = "{0} мегабайтах",
                                other = "{0} мегабайта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} МБ",
                                few = "{0} МБ",
                                many = "{0} МБ",
                                other = "{0} МБ",
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
                                one = "{0} мегабит",
                                few = "{0} мегабита",
                                many = "{0} мегабит",
                                other = "{0} мегабита",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мегабит",
                                few = "{0} мегабита",
                                many = "{0} мегабит",
                                other = "{0} мегабита",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мегабиту",
                                few = "{0} мегабитам",
                                many = "{0} мегабитам",
                                other = "{0} мегабита",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мегабита",
                                few = "{0} мегабит",
                                many = "{0} мегабит",
                                other = "{0} мегабита",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мегабитом",
                                few = "{0} мегабитами",
                                many = "{0} мегабитами",
                                other = "{0} мегабита",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мегабите",
                                few = "{0} мегабитах",
                                many = "{0} мегабитах",
                                other = "{0} мегабитах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} мегабите",
                                few = "{0} мегабитах",
                                many = "{0} мегабитах",
                                other = "{0} мегабита",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Мбит",
                                few = "{0} Мбит",
                                many = "{0} Мбит",
                                other = "{0} Мбит",
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
                                one = "{0} килобайт",
                                few = "{0} килобайта",
                                many = "{0} килобайт",
                                other = "{0} килобайта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} килобайт",
                                few = "{0} килобайта",
                                many = "{0} килобайт",
                                other = "{0} килобайта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} килобайту",
                                few = "{0} килобайтам",
                                many = "{0} килобайтам",
                                other = "{0} килобайта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} килобайта",
                                few = "{0} килобайт",
                                many = "{0} килобайт",
                                other = "{0} килобайта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} килобайтом",
                                few = "{0} килобайтами",
                                many = "{0} килобайтами",
                                other = "{0} килобайта",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} килобайте",
                                few = "{0} килобайтах",
                                many = "{0} килобайтах",
                                other = "{0} килобайтах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} килобайте",
                                few = "{0} килобайтах",
                                many = "{0} килобайтах",
                                other = "{0} килобайта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кБ",
                                few = "{0} кБ",
                                many = "{0} кБ",
                                other = "{0} кБ",
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
                                one = "{0} килобит",
                                few = "{0} килобита",
                                many = "{0} килобит",
                                other = "{0} килобита",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} килобит",
                                few = "{0} килобита",
                                many = "{0} килобит",
                                other = "{0} килобита",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} килобиту",
                                few = "{0} килобитам",
                                many = "{0} килобитам",
                                other = "{0} килобита",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} килобита",
                                few = "{0} килобит",
                                many = "{0} килобит",
                                other = "{0} килобита",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} килобитом",
                                few = "{0} килобитами",
                                many = "{0} килобитами",
                                other = "{0} килобита",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} килобите",
                                few = "{0} килобитах",
                                many = "{0} килобитах",
                                other = "{0} килобитах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} килобите",
                                few = "{0} килобитах",
                                many = "{0} килобитах",
                                other = "{0} килобита",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кбит",
                                few = "{0} кбит",
                                many = "{0} кбит",
                                other = "{0} кбит",
                            )
                        ),
                    )
                ),
            )
        override val digitalByteUnit
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} байт",
                                few = "{0} байта",
                                many = "{0} байт",
                                other = "{0} байта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} байт",
                                few = "{0} байта",
                                many = "{0} байт",
                                other = "{0} байта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} байту",
                                few = "{0} байтам",
                                many = "{0} байтам",
                                other = "{0} байта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} байта",
                                few = "{0} байт",
                                many = "{0} байт",
                                other = "{0} байта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} байтом",
                                few = "{0} байтами",
                                many = "{0} байтами",
                                other = "{0} байта",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} байте",
                                few = "{0} байтах",
                                many = "{0} байтах",
                                other = "{0} байта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Б",
                                few = "{0} Б",
                                many = "{0} Б",
                                other = "{0} Б",
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
                                one = "{0} бит",
                                few = "{0} бита",
                                many = "{0} бит",
                                other = "{0} бита",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} бит",
                                few = "{0} бита",
                                many = "{0} бит",
                                other = "{0} бита",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} биту",
                                few = "{0} битам",
                                many = "{0} битам",
                                other = "{0} бита",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} бита",
                                few = "{0} бит",
                                many = "{0} бит",
                                other = "{0} бита",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} битом",
                                few = "{0} битами",
                                many = "{0} битами",
                                other = "{0} бита",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} бите",
                                few = "{0} битах",
                                many = "{0} битах",
                                other = "{0} бита",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} бит",
                                few = "{0} бита",
                                many = "{0} бит",
                                other = "{0} бита",
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
                                one = "{0} век",
                                few = "{0} века",
                                many = "{0} веков",
                                other = "{0} века",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} век",
                                few = "{0} века",
                                many = "{0} веков",
                                other = "{0} века",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} веку",
                                few = "{0} векам",
                                many = "{0} векам",
                                other = "{0} века",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} века",
                                few = "{0} веков",
                                many = "{0} веков",
                                other = "{0} века",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} веком",
                                few = "{0} веками",
                                many = "{0} веками",
                                other = "{0} века",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} веке",
                                few = "{0} веках",
                                many = "{0} веках",
                                other = "{0} века",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} в.",
                                few = "{0} в.",
                                many = "{0} в.",
                                other = "{0} в.",
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
                                one = "{0} десятилетие",
                                few = "{0} десятилетия",
                                many = "{0} десятилетий",
                                other = "{0} десятилетия",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} десятилетие",
                                few = "{0} десятилетия",
                                many = "{0} десятилетий",
                                other = "{0} десятилетия",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} десятилетию",
                                few = "{0} десятилетиям",
                                many = "{0} десятилетиям",
                                other = "{0} десятилетия",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} десятилетия",
                                few = "{0} десятилетий",
                                many = "{0} десятилетий",
                                other = "{0} десятилетия",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} десятилетием",
                                few = "{0} десятилетиями",
                                many = "{0} десятилетиями",
                                other = "{0} десятилетия",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} десятилетии",
                                few = "{0} десятилетиях",
                                many = "{0} десятилетиях",
                                other = "{0} десятилетия",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} 10-летие",
                                few = "{0} 10-летия",
                                many = "{0} 10-летий",
                                other = "{0} 10-летия",
                            )
                        ),
                    )
                ),
            )
        override val durationYear
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} год",
                                few = "{0} года",
                                many = "{0} лет",
                                other = "{0} года",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} год",
                                few = "{0} года",
                                many = "{0} лет",
                                other = "{0} года",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} году",
                                few = "{0} годам",
                                many = "{0} годам",
                                other = "{0} года",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} года",
                                few = "{0} лет",
                                many = "{0} лет",
                                other = "{0} года",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} годом",
                                few = "{0} годами",
                                many = "{0} годами",
                                other = "{0} года",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} годе",
                                few = "{0} годах",
                                many = "{0} годах",
                                other = "{0} годах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} годе",
                                few = "{0} годах",
                                many = "{0} годах",
                                other = "{0} года",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} г.",
                                few = "{0} г.",
                                many = "{0} л.",
                                other = "{0} г.",
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
                                one = "{0} квартал",
                                few = "{0} квартала",
                                many = "{0} кварталов",
                                other = "{0} квартала",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} квартал",
                                few = "{0} квартала",
                                many = "{0} кварталов",
                                other = "{0} квартала",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кварталу",
                                few = "{0} кварталам",
                                many = "{0} кварталам",
                                other = "{0} квартала",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} квартала",
                                few = "{0} кварталов",
                                many = "{0} кварталов",
                                other = "{0} квартала",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кварталом",
                                few = "{0} кварталами",
                                many = "{0} кварталами",
                                other = "{0} квартала",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} квартале",
                                few = "{0} кварталах",
                                many = "{0} кварталах",
                                other = "{0} квартала",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кварт",
                                few = "{0} кварт",
                                many = "{0} кварт",
                                other = "{0} кварт",
                            )
                        ),
                    )
                ),
            )
        override val durationMonth
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} месяц",
                                few = "{0} месяца",
                                many = "{0} месяцев",
                                other = "{0} месяца",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} месяц",
                                few = "{0} месяца",
                                many = "{0} месяцев",
                                other = "{0} месяца",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} месяцу",
                                few = "{0} месяцам",
                                many = "{0} месяцам",
                                other = "{0} месяца",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} месяца",
                                few = "{0} месяцев",
                                many = "{0} месяцев",
                                other = "{0} месяца",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} месяцем",
                                few = "{0} месяцами",
                                many = "{0} месяцами",
                                other = "{0} месяца",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} месяце",
                                few = "{0} месяцах",
                                many = "{0} месяцах",
                                other = "{0} месяцах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} месяце",
                                few = "{0} месяцах",
                                many = "{0} месяцах",
                                other = "{0} месяца",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мес.",
                                few = "{0} мес.",
                                many = "{0} мес.",
                                other = "{0} мес.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} м.",
                                few = "{0} м.",
                                many = "{0} м.",
                                other = "{0} м.",
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
                                one = "{0} неделя",
                                few = "{0} недели",
                                many = "{0} недель",
                                other = "{0} недели",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} неделю",
                                few = "{0} недели",
                                many = "{0} недель",
                                other = "{0} недели",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} неделе",
                                few = "{0} неделям",
                                many = "{0} неделям",
                                other = "{0} недели",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} недели",
                                few = "{0} недель",
                                many = "{0} недель",
                                other = "{0} недели",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} неделей",
                                few = "{0} неделями",
                                many = "{0} неделями",
                                other = "{0} недели",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} неделе",
                                few = "{0} неделях",
                                many = "{0} неделях",
                                other = "{0} неделях",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} неделе",
                                few = "{0} неделях",
                                many = "{0} неделях",
                                other = "{0} недели",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} нед.",
                                few = "{0} нед.",
                                many = "{0} нед.",
                                other = "{0} нед.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} н.",
                                few = "{0} н.",
                                many = "{0} н.",
                                other = "{0} н.",
                            )
                        ),
                    )
                ),
            )
        override val durationDay
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} день",
                                few = "{0} дня",
                                many = "{0} дней",
                                other = "{0} дня",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} день",
                                few = "{0} дня",
                                many = "{0} дней",
                                other = "{0} дня",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} дню",
                                few = "{0} дням",
                                many = "{0} дням",
                                other = "{0} дня",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} дня",
                                few = "{0} дней",
                                many = "{0} дней",
                                other = "{0} дня",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} днём",
                                few = "{0} днями",
                                many = "{0} днями",
                                other = "{0} дня",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} дне",
                                few = "{0} днях",
                                many = "{0} днях",
                                other = "{0} днях",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} дне",
                                few = "{0} днях",
                                many = "{0} днях",
                                other = "{0} дня",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дн.",
                                few = "{0} дн.",
                                many = "{0} дн.",
                                other = "{0} дн.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} д.",
                                few = "{0} д.",
                                many = "{0} д.",
                                other = "{0} д.",
                            )
                        ),
                    )
                ),
            )
        override val durationHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} час",
                                few = "{0} часа",
                                many = "{0} часов",
                                other = "{0} часа",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} час",
                                few = "{0} часа",
                                many = "{0} часов",
                                other = "{0} часа",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} часу",
                                few = "{0} часам",
                                many = "{0} часам",
                                other = "{0} часа",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} часа",
                                few = "{0} часов",
                                many = "{0} часов",
                                other = "{0} часа",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} часом",
                                few = "{0} часами",
                                many = "{0} часами",
                                other = "{0} часа",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} часе",
                                few = "{0} часах",
                                many = "{0} часах",
                                other = "{0} часах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} часе",
                                few = "{0} часах",
                                many = "{0} часах",
                                other = "{0} часа",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ч",
                                few = "{0} ч",
                                many = "{0} ч",
                                other = "{0} ч",
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
                                one = "{0} минута",
                                few = "{0} минуты",
                                many = "{0} минут",
                                other = "{0} минуты",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} минуту",
                                few = "{0} минуты",
                                many = "{0} минут",
                                other = "{0} минуты",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} минуте",
                                few = "{0} минутам",
                                many = "{0} минутам",
                                other = "{0} минуты",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} минуты",
                                few = "{0} минут",
                                many = "{0} минут",
                                other = "{0} минуты",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} минутой",
                                few = "{0} минутами",
                                many = "{0} минутами",
                                other = "{0} минуты",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} минуте",
                                few = "{0} минутах",
                                many = "{0} минутах",
                                other = "{0} минутах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} минуте",
                                few = "{0} минутах",
                                many = "{0} минутах",
                                other = "{0} минуты",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мин",
                                few = "{0} мин",
                                many = "{0} мин",
                                other = "{0} мин",
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
                                one = "{0} секунда",
                                few = "{0} секунды",
                                many = "{0} секунд",
                                other = "{0} секунды",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} секунду",
                                few = "{0} секунды",
                                many = "{0} секунд",
                                other = "{0} секунды",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} секунде",
                                few = "{0} секундам",
                                many = "{0} секундам",
                                other = "{0} секунды",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} секунды",
                                few = "{0} секунд",
                                many = "{0} секунд",
                                other = "{0} секунды",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} секундой",
                                few = "{0} секундами",
                                many = "{0} секундами",
                                other = "{0} секунды",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} секунде",
                                few = "{0} секундах",
                                many = "{0} секундах",
                                other = "{0} секундах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} секунде",
                                few = "{0} секундах",
                                many = "{0} секундах",
                                other = "{0} секунды",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} миллисекунда",
                                few = "{0} миллисекунды",
                                many = "{0} миллисекунд",
                                other = "{0} миллисекунды",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} миллисекунду",
                                few = "{0} миллисекунды",
                                many = "{0} миллисекунд",
                                other = "{0} миллисекунды",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миллисекунде",
                                few = "{0} миллисекундам",
                                many = "{0} миллисекундам",
                                other = "{0} миллисекунды",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} миллисекунды",
                                few = "{0} миллисекунд",
                                many = "{0} миллисекунд",
                                other = "{0} миллисекунды",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} миллисекундой",
                                few = "{0} миллисекундами",
                                many = "{0} миллисекундами",
                                other = "{0} миллисекунды",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миллисекунде",
                                few = "{0} миллисекундах",
                                many = "{0} миллисекундах",
                                other = "{0} миллисекунды",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мс",
                                few = "{0} мс",
                                many = "{0} мс",
                                other = "{0} мс",
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
                                one = "{0} микросекунда",
                                few = "{0} микросекунды",
                                many = "{0} микросекунд",
                                other = "{0} микросекунды",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} микросекунду",
                                few = "{0} микросекунды",
                                many = "{0} микросекунд",
                                other = "{0} микросекунды",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} микросекунде",
                                few = "{0} микросекундам",
                                many = "{0} микросекундам",
                                other = "{0} микросекунды",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} микросекунды",
                                few = "{0} микросекунд",
                                many = "{0} микросекунд",
                                other = "{0} микросекунды",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} микросекундой",
                                few = "{0} микросекундами",
                                many = "{0} микросекундами",
                                other = "{0} микросекунды",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} микросекунде",
                                few = "{0} микросекундах",
                                many = "{0} микросекундах",
                                other = "{0} микросекунды",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мкс",
                                few = "{0} мкс",
                                many = "{0} мкс",
                                other = "{0} мкс",
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
                                one = "{0} наносекунда",
                                few = "{0} наносекунды",
                                many = "{0} наносекунд",
                                other = "{0} наносекунды",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} наносекунду",
                                few = "{0} наносекунды",
                                many = "{0} наносекунд",
                                other = "{0} наносекунды",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} наносекунде",
                                few = "{0} наносекундам",
                                many = "{0} наносекундам",
                                other = "{0} наносекунды",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} наносекунды",
                                few = "{0} наносекунд",
                                many = "{0} наносекунд",
                                other = "{0} наносекунды",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} наносекундой",
                                few = "{0} наносекундами",
                                many = "{0} наносекундами",
                                other = "{0} наносекунды",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} наносекунде",
                                few = "{0} наносекундах",
                                many = "{0} наносекундах",
                                other = "{0} наносекунды",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} нс",
                                few = "{0} нс",
                                many = "{0} нс",
                                other = "{0} нс",
                            )
                        ),
                    )
                ),
            )
        override val durationNight
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ночь",
                                few = "{0} ночи",
                                many = "{0} ночей",
                                other = "{0} ночи",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ночь",
                                few = "{0} ночи",
                                many = "{0} ночей",
                                other = "{0} ночи",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} ночи",
                                few = "{0} ночам",
                                many = "{0} ночам",
                                other = "{0} ночи",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} ночи",
                                few = "{0} ночей",
                                many = "{0} ночей",
                                other = "{0} ночи",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} ночью",
                                few = "{0} ночами",
                                many = "{0} ночами",
                                other = "{0} ночи",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} ночи",
                                few = "{0} ночах",
                                many = "{0} ночах",
                                other = "{0} ночи",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ноч.",
                                few = "{0} ноч.",
                                many = "{0} ноч.",
                                other = "{0} ноч.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ноч.",
                                few = "{0} ноч.",
                                many = "{0} ноч.",
                                other = "{0} ноч.",
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
                                one = "{0} ампер",
                                few = "{0} ампера",
                                many = "{0} ампер",
                                other = "{0} ампера",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ампер",
                                few = "{0} ампера",
                                many = "{0} ампер",
                                other = "{0} ампера",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} амперу",
                                few = "{0} амперам",
                                many = "{0} амперам",
                                other = "{0} ампера",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} ампера",
                                few = "{0} ампер",
                                many = "{0} ампер",
                                other = "{0} ампера",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} ампером",
                                few = "{0} амперами",
                                many = "{0} амперами",
                                other = "{0} ампера",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} ампере",
                                few = "{0} амперах",
                                many = "{0} амперах",
                                other = "{0} ампера",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} А",
                                few = "{0} А",
                                many = "{0} А",
                                other = "{0} А",
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
                                one = "{0} миллиампер",
                                few = "{0} миллиампера",
                                many = "{0} миллиампер",
                                other = "{0} миллиампера",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} миллиампер",
                                few = "{0} миллиампера",
                                many = "{0} миллиампер",
                                other = "{0} миллиампера",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миллиамперу",
                                few = "{0} миллиамперам",
                                many = "{0} миллиамперам",
                                other = "{0} миллиампера",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} миллиампера",
                                few = "{0} миллиампер",
                                many = "{0} миллиампер",
                                other = "{0} миллиампера",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} миллиампером",
                                few = "{0} миллиамперами",
                                many = "{0} миллиамперами",
                                other = "{0} миллиампера",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миллиампере",
                                few = "{0} миллиамперах",
                                many = "{0} миллиамперах",
                                other = "{0} миллиампера",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мА",
                                few = "{0} мА",
                                many = "{0} мА",
                                other = "{0} мА",
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
                                one = "{0} ом",
                                few = "{0} ома",
                                many = "{0} ом",
                                other = "{0} ома",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ом",
                                few = "{0} ома",
                                many = "{0} ом",
                                other = "{0} ома",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} ому",
                                few = "{0} омам",
                                many = "{0} омам",
                                other = "{0} ома",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} ома",
                                few = "{0} ом",
                                many = "{0} ом",
                                other = "{0} ома",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} омом",
                                few = "{0} омами",
                                many = "{0} омами",
                                other = "{0} ома",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} оме",
                                few = "{0} омах",
                                many = "{0} омах",
                                other = "{0} ома",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Ом",
                                few = "{0} Ом",
                                many = "{0} Ом",
                                other = "{0} Ом",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondSquareAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondSquareAmpere formatter is not implemented yet for ru")
        override val electricVolt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} вольт",
                                few = "{0} вольта",
                                many = "{0} вольт",
                                other = "{0} вольта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} вольт",
                                few = "{0} вольта",
                                many = "{0} вольт",
                                other = "{0} вольта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} вольту",
                                few = "{0} вольтам",
                                many = "{0} вольтам",
                                other = "{0} вольта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} вольта",
                                few = "{0} вольт",
                                many = "{0} вольт",
                                other = "{0} вольта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} вольтом",
                                few = "{0} вольтами",
                                many = "{0} вольтами",
                                other = "{0} вольта",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} вольте",
                                few = "{0} вольтах",
                                many = "{0} вольтах",
                                other = "{0} вольта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} В",
                                few = "{0} В",
                                many = "{0} В",
                                other = "{0} В",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondAmpere formatter is not implemented yet for ru")
        override val energyKilocalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} килокалория",
                                few = "{0} килокалории",
                                many = "{0} килокалорий",
                                other = "{0} килокалории",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} килокалорию",
                                few = "{0} килокалории",
                                many = "{0} килокалорий",
                                other = "{0} килокалории",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} килокалории",
                                few = "{0} килокалориям",
                                many = "{0} килокалориям",
                                other = "{0} килокалории",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} килокалории",
                                few = "{0} килокалорий",
                                many = "{0} килокалорий",
                                other = "{0} килокалории",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} килокалорией",
                                few = "{0} килокалориями",
                                many = "{0} килокалориями",
                                other = "{0} килокалории",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} килокалории",
                                few = "{0} килокалориях",
                                many = "{0} килокалориях",
                                other = "{0} килокалориях",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} килокалории",
                                few = "{0} килокалориях",
                                many = "{0} килокалориях",
                                other = "{0} килокалории",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ккал",
                                few = "{0} ккал",
                                many = "{0} ккал",
                                other = "{0} ккал",
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
                                one = "{0} калория",
                                few = "{0} калории",
                                many = "{0} калорий",
                                other = "{0} калории",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} калорию",
                                few = "{0} калории",
                                many = "{0} калорий",
                                other = "{0} калории",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} калории",
                                few = "{0} калориям",
                                many = "{0} калориям",
                                other = "{0} калории",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} калории",
                                few = "{0} калорий",
                                many = "{0} калорий",
                                other = "{0} калории",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} калорией",
                                few = "{0} калориями",
                                many = "{0} калориями",
                                other = "{0} калории",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} калории",
                                few = "{0} калориях",
                                many = "{0} калориях",
                                other = "{0} калории",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кал",
                                few = "{0} кал",
                                many = "{0} кал",
                                other = "{0} кал",
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
                                one = "{0} килокалория",
                                few = "{0} килокалории",
                                many = "{0} килокалорий",
                                other = "{0} килокалории",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} килокалорию",
                                few = "{0} килокалории",
                                many = "{0} килокалорий",
                                other = "{0} килокалории",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} килокалории",
                                few = "{0} килокалориям",
                                many = "{0} килокалориям",
                                other = "{0} килокалории",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} килокалории",
                                few = "{0} килокалорий",
                                many = "{0} килокалорий",
                                other = "{0} килокалории",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} килокалорией",
                                few = "{0} килокалориями",
                                many = "{0} килокалориями",
                                other = "{0} килокалории",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} килокалории",
                                few = "{0} килокалориях",
                                many = "{0} килокалориях",
                                other = "{0} килокалориях",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} килокалории",
                                few = "{0} килокалориях",
                                many = "{0} килокалориях",
                                other = "{0} килокалориях",
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
                                one = "{0} килоджоуль",
                                few = "{0} килоджоуля",
                                many = "{0} килоджоулей",
                                other = "{0} килоджоуля",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} килоджоуль",
                                few = "{0} килоджоуля",
                                many = "{0} килоджоулей",
                                other = "{0} килоджоуля",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} килоджоулю",
                                few = "{0} килоджоулям",
                                many = "{0} килоджоулям",
                                other = "{0} килоджоуля",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} килоджоуля",
                                few = "{0} килоджоулей",
                                many = "{0} килоджоулей",
                                other = "{0} килоджоуля",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} килоджоулем",
                                few = "{0} килоджоулями",
                                many = "{0} килоджоулями",
                                other = "{0} килоджоуля",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} килоджоуле",
                                few = "{0} килоджоулях",
                                many = "{0} килоджоулях",
                                other = "{0} килоджоуля",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кДж",
                                few = "{0} кДж",
                                many = "{0} кДж",
                                other = "{0} кДж",
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
                                one = "{0} джоуль",
                                few = "{0} джоуля",
                                many = "{0} джоулей",
                                other = "{0} джоуля",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} джоуль",
                                few = "{0} джоуля",
                                many = "{0} джоулей",
                                other = "{0} джоуля",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} джоулю",
                                few = "{0} джоулям",
                                many = "{0} джоулям",
                                other = "{0} джоуля",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} джоуля",
                                few = "{0} джоулей",
                                many = "{0} джоулей",
                                other = "{0} джоуля",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} джоулем",
                                few = "{0} джоулями",
                                many = "{0} джоулями",
                                other = "{0} джоуля",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} джоуле",
                                few = "{0} джоулях",
                                many = "{0} джоулях",
                                other = "{0} джоуля",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Дж",
                                few = "{0} Дж",
                                many = "{0} Дж",
                                other = "{0} Дж",
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
                                one = "{0} киловатт-час",
                                few = "{0} киловатт-часа",
                                many = "{0} киловатт-часов",
                                other = "{0} киловатт-часа",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} киловатт-час",
                                few = "{0} киловатт-часа",
                                many = "{0} киловатт-часов",
                                other = "{0} киловатт-часа",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} киловатт-часу",
                                few = "{0} киловатт-часам",
                                many = "{0} киловатт-часам",
                                other = "{0} киловатт-часа",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} киловатт-часа",
                                few = "{0} киловатт-часов",
                                many = "{0} киловатт-часов",
                                other = "{0} киловатт-часа",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} киловатт-часом",
                                few = "{0} киловатт-часами",
                                many = "{0} киловатт-часами",
                                other = "{0} киловатт-часа",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} киловатт-часе",
                                few = "{0} киловатт-часах",
                                many = "{0} киловатт-часах",
                                other = "{0} киловатт-часа",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кВт⋅ч",
                                few = "{0} кВт⋅ч",
                                many = "{0} кВт⋅ч",
                                other = "{0} кВт⋅ч",
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
                                one = "{0} электронвольт",
                                few = "{0} электронвольта",
                                many = "{0} электронвольт",
                                other = "{0} электронвольта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} эВ",
                                few = "{0} эВ",
                                many = "{0} эВ",
                                other = "{0} эВ",
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
                                one = "{0} британская тепловая единица",
                                few = "{0} британские тепловые единицы",
                                many = "{0} британских тепловых единиц",
                                other = "{0} британской тепловой единицы",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} БТЕ",
                                few = "{0} БТЕ",
                                many = "{0} БТЕ",
                                other = "{0} БТЕ",
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
                                one = "{0} американский терм",
                                few = "{0} американских терма",
                                many = "{0} американских термов",
                                other = "{0} американского терма",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} терм США",
                                few = "{0} терма США",
                                many = "{0} термов США",
                                other = "{0} терма США",
                            )
                        ),
                    )
                ),
            )
        override val energyKilogramSquareMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerSquareSecond formatter is not implemented yet for ru")
        override val forcePoundForce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фунт-сила",
                                few = "{0} фунт-силы",
                                many = "{0} фунт-сил",
                                other = "{0} фунт-силы",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фнт-с",
                                few = "{0} фнт-с",
                                many = "{0} фнт-с",
                                other = "{0} фнт-с",
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
                                one = "{0} ньютон",
                                few = "{0} ньютона",
                                many = "{0} ньютонов",
                                other = "{0} ньютона",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ньютон",
                                few = "{0} ньютона",
                                many = "{0} ньютонов",
                                other = "{0} ньютона",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} ньютону",
                                few = "{0} ньютонам",
                                many = "{0} ньютонам",
                                other = "{0} ньютона",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} ньютона",
                                few = "{0} ньютонов",
                                many = "{0} ньютонов",
                                other = "{0} ньютона",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} ньютоном",
                                few = "{0} ньютонами",
                                many = "{0} ньютонами",
                                other = "{0} ньютона",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} ньютоне",
                                few = "{0} ньютонах",
                                many = "{0} ньютонах",
                                other = "{0} ньютона",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Н",
                                few = "{0} Н",
                                many = "{0} Н",
                                other = "{0} Н",
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
                                one = "{0} киловатт-час на 100 километров",
                                few = "{0} киловатт-часа на 100 километров",
                                many = "{0} киловатт-часов на 100 километров",
                                other = "{0} киловатт-часа на 100 километров",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} киловатт-час на 100 километров",
                                few = "{0} киловатт-часа на 100 километров",
                                many = "{0} киловатт-часов на 100 километров",
                                other = "{0} киловатт-часа на 100 километров",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} киловатт-часу на 100 километров",
                                few = "{0} киловатт-часам на 100 километров",
                                many = "{0} киловатт-часам на 100 километров",
                                other = "{0} киловатт-часа на 100 километров",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} киловатт-часа на 100 километров",
                                few = "{0} киловатт-часов на 100 километров",
                                many = "{0} киловатт-часов на 100 километров",
                                other = "{0} киловатт-часа на 100 километров",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} киловатт-часом на 100 километров",
                                few = "{0} киловатт-часами на 100 километров",
                                many = "{0} киловатт-часами на 100 километров",
                                other = "{0} киловатт-часа на 100 километров",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} киловатт-часе на 100 километров",
                                few = "{0} киловатт-часах на 100 километров",
                                many = "{0} киловатт-часах на 100 километров",
                                other = "{0} киловатт-часа на 100 километров",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кВт⋅ч/100 км",
                                few = "{0} кВт⋅ч/100 км",
                                many = "{0} кВт⋅ч/100 км",
                                other = "{0} кВт⋅ч/100 км",
                            )
                        ),
                    )
                ),
            )
        override val forceKilogramMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramMeterPerSquareSecond formatter is not implemented yet for ru")
        override val frequencyGigahertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гигагерц",
                                few = "{0} гигагерца",
                                many = "{0} гигагерц",
                                other = "{0} гигагерца",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гигагерц",
                                few = "{0} гигагерца",
                                many = "{0} гигагерц",
                                other = "{0} гигагерца",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} гигагерцу",
                                few = "{0} гигагерцам",
                                many = "{0} гигагерцам",
                                other = "{0} гигагерца",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} гигагерца",
                                few = "{0} гигагерц",
                                many = "{0} гигагерц",
                                other = "{0} гигагерца",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} гигагерцем",
                                few = "{0} гигагерцами",
                                many = "{0} гигагерцами",
                                other = "{0} гигагерца",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} гигагерце",
                                few = "{0} гигагерцах",
                                many = "{0} гигагерцах",
                                other = "{0} гигагерца",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ГГц",
                                few = "{0} ГГц",
                                many = "{0} ГГц",
                                other = "{0} ГГц",
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
                                one = "{0} мегагерц",
                                few = "{0} мегагерца",
                                many = "{0} мегагерц",
                                other = "{0} мегагерца",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мегагерц",
                                few = "{0} мегагерца",
                                many = "{0} мегагерц",
                                other = "{0} мегагерца",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мегагерцу",
                                few = "{0} мегагерцам",
                                many = "{0} мегагерцам",
                                other = "{0} мегагерца",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мегагерца",
                                few = "{0} мегагерц",
                                many = "{0} мегагерц",
                                other = "{0} мегагерца",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мегагерцем",
                                few = "{0} мегагерцами",
                                many = "{0} мегагерцами",
                                other = "{0} мегагерца",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} мегагерце",
                                few = "{0} мегагерцах",
                                many = "{0} мегагерцах",
                                other = "{0} мегагерца",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} МГц",
                                few = "{0} МГц",
                                many = "{0} МГц",
                                other = "{0} МГц",
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
                                one = "{0} килогерц",
                                few = "{0} килогерца",
                                many = "{0} килогерц",
                                other = "{0} килогерца",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} килогерц",
                                few = "{0} килогерца",
                                many = "{0} килогерц",
                                other = "{0} килогерца",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} килогерцу",
                                few = "{0} килогерцам",
                                many = "{0} килогерцам",
                                other = "{0} килогерца",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} килогерца",
                                few = "{0} килогерц",
                                many = "{0} килогерц",
                                other = "{0} килогерца",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} килогерцем",
                                few = "{0} килогерцами",
                                many = "{0} килогерцами",
                                other = "{0} килогерца",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} килогерце",
                                few = "{0} килогерцах",
                                many = "{0} килогерцах",
                                other = "{0} килогерца",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кГц",
                                few = "{0} кГц",
                                many = "{0} кГц",
                                other = "{0} кГц",
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
                                one = "{0} герц",
                                few = "{0} герца",
                                many = "{0} герц",
                                other = "{0} герца",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} герц",
                                few = "{0} герца",
                                many = "{0} герц",
                                other = "{0} герца",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} герцу",
                                few = "{0} герцам",
                                many = "{0} герцам",
                                other = "{0} герца",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} герца",
                                few = "{0} герц",
                                many = "{0} герц",
                                other = "{0} герца",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} герцем",
                                few = "{0} герцами",
                                many = "{0} герцами",
                                other = "{0} герца",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} герце",
                                few = "{0} герцах",
                                many = "{0} герцах",
                                other = "{0} герца",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Гц",
                                few = "{0} Гц",
                                many = "{0} Гц",
                                other = "{0} Гц",
                            )
                        ),
                    )
                ),
            )
        override val frequencyRevolutionPerSecond: MultiLengthGenderedPattern
            get() = TODO("RevolutionPerSecond formatter is not implemented yet for ru")
        override val graphicsEm
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} эм",
                                few = "{0} эм",
                                many = "{0} эм",
                                other = "{0} эм",
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
                                one = "{0} пиксель",
                                few = "{0} пикселя",
                                many = "{0} пикселей",
                                other = "{0} пикселя",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} пиксель",
                                few = "{0} пикселя",
                                many = "{0} пикселей",
                                other = "{0} пикселя",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} пикселю",
                                few = "{0} пикселям",
                                many = "{0} пикселям",
                                other = "{0} пикселя",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} пикселя",
                                few = "{0} пикселей",
                                many = "{0} пикселей",
                                other = "{0} пикселя",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} пикселем",
                                few = "{0} пикселями",
                                many = "{0} пикселями",
                                other = "{0} пикселя",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} пикселе",
                                few = "{0} пикселях",
                                many = "{0} пикселях",
                                other = "{0} пикселя",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} пкс",
                                few = "{0} пкс",
                                many = "{0} пкс",
                                other = "{0} пкс",
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
                                one = "{0} мегапиксель",
                                few = "{0} мегапикселя",
                                many = "{0} мегапикселей",
                                other = "{0} мегапикселя",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мегапиксель",
                                few = "{0} мегапикселя",
                                many = "{0} мегапикселей",
                                other = "{0} мегапикселя",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мегапикселю",
                                few = "{0} мегапикселям",
                                many = "{0} мегапикселям",
                                other = "{0} мегапикселя",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мегапикселя",
                                few = "{0} мегапикселей",
                                many = "{0} мегапикселей",
                                other = "{0} мегапикселя",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мегапикселем",
                                few = "{0} мегапикселями",
                                many = "{0} мегапикселями",
                                other = "{0} мегапикселя",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} мегапикселе",
                                few = "{0} мегапикселях",
                                many = "{0} мегапикселях",
                                other = "{0} мегапикселя",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Мпкс",
                                few = "{0} Мпкс",
                                many = "{0} Мпкс",
                                other = "{0} Мпкс",
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
                                one = "{0} точка",
                                few = "{0} точки",
                                many = "{0} точек",
                                other = "{0} точки",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} тчк",
                                few = "{0} тчк",
                                many = "{0} тчк",
                                other = "{0} тчк",
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
                                one = "{0} пиксель на сантиметр",
                                few = "{0} пикселя на сантиметр",
                                many = "{0} пикселей на сантиметр",
                                other = "{0} пикселя на сантиметр",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} пиксель на сантиметр",
                                few = "{0} пикселя на сантиметр",
                                many = "{0} пикселей на сантиметр",
                                other = "{0} пикселя на сантиметр",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} пикселю на сантиметр",
                                few = "{0} пикселям на сантиметр",
                                many = "{0} пикселям на сантиметр",
                                other = "{0} пикселя на сантиметр",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} пикселя на сантиметр",
                                few = "{0} пикселей на сантиметр",
                                many = "{0} пикселей на сантиметр",
                                other = "{0} пикселя на сантиметр",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} пикселем на сантиметр",
                                few = "{0} пикселями на сантиметр",
                                many = "{0} пикселями на сантиметр",
                                other = "{0} пикселя на сантиметр",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} пикселе на сантиметр",
                                few = "{0} пикселях на сантиметр",
                                many = "{0} пикселях на сантиметр",
                                other = "{0} пикселя на сантиметр",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} пкс/см",
                                few = "{0} пкс/см",
                                many = "{0} пкс/см",
                                other = "{0} пкс/см",
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
                                one = "{0} пиксель на дюйм",
                                few = "{0} пикселя на дюйм",
                                many = "{0} пикселей на дюйм",
                                other = "{0} пикселя на дюйм",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} пкс/дюйм",
                                few = "{0} пкс/дюйм",
                                many = "{0} пкс/дюйм",
                                other = "{0} пкс/дюйм",
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
                                one = "{0} точка на сантиметр",
                                few = "{0} точки на сантиметр",
                                many = "{0} точек на сантиметр",
                                other = "{0} точки на сантиметр",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} тчк/см",
                                few = "{0} тчк/см",
                                many = "{0} тчк/см",
                                other = "{0} тчк/см",
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
                                one = "{0} точка на дюйм",
                                few = "{0} точки на дюйм",
                                many = "{0} точек на дюйм",
                                other = "{0} точки на дюйм",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} тчк/дюйм",
                                few = "{0} тчк/дюйм",
                                many = "{0} тчк/дюйм",
                                other = "{0} тчк/дюйм",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixelPerMeter: MultiLengthGenderedPattern
            get() = TODO("PixelPerMeter formatter is not implemented yet for ru")
        override val lengthEarthRadius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} земной радиус",
                                few = "{0} земных радиуса",
                                many = "{0} земных радиусов",
                                other = "{0} земного радиуса",
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
                                one = "{0} километр",
                                few = "{0} километра",
                                many = "{0} километров",
                                other = "{0} километра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} километр",
                                few = "{0} километра",
                                many = "{0} километров",
                                other = "{0} километра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} километру",
                                few = "{0} километрам",
                                many = "{0} километрам",
                                other = "{0} километра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} километра",
                                few = "{0} километров",
                                many = "{0} километров",
                                other = "{0} километра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} километром",
                                few = "{0} километрами",
                                many = "{0} километрами",
                                other = "{0} километра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} километре",
                                few = "{0} километрах",
                                many = "{0} километрах",
                                other = "{0} километрах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} километре",
                                few = "{0} километрах",
                                many = "{0} километрах",
                                other = "{0} километра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} км",
                                few = "{0} км",
                                many = "{0} км",
                                other = "{0} км",
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
                                one = "{0} метр",
                                few = "{0} метра",
                                many = "{0} метров",
                                other = "{0} метра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} метр",
                                few = "{0} метра",
                                many = "{0} метров",
                                other = "{0} метра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} метру",
                                few = "{0} метрам",
                                many = "{0} метрам",
                                other = "{0} метра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} метра",
                                few = "{0} метров",
                                many = "{0} метров",
                                other = "{0} метра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} метром",
                                few = "{0} метрами",
                                many = "{0} метрами",
                                other = "{0} метра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} метре",
                                few = "{0} метрах",
                                many = "{0} метрах",
                                other = "{0} метрах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} метре",
                                few = "{0} метрах",
                                many = "{0} метрах",
                                other = "{0} метра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} м",
                                few = "{0} м",
                                many = "{0} м",
                                other = "{0} м",
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
                                one = "{0} дециметр",
                                few = "{0} дециметра",
                                many = "{0} дециметров",
                                other = "{0} дециметра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} дециметр",
                                few = "{0} дециметра",
                                many = "{0} дециметров",
                                other = "{0} дециметра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} дециметру",
                                few = "{0} дециметрам",
                                many = "{0} дециметрам",
                                other = "{0} дециметра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} дециметра",
                                few = "{0} дециметров",
                                many = "{0} дециметров",
                                other = "{0} дециметра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} дециметром",
                                few = "{0} дециметрами",
                                many = "{0} дециметрами",
                                other = "{0} дециметра",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} дециметре",
                                few = "{0} дециметрах",
                                many = "{0} дециметрах",
                                other = "{0} дециметра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дм",
                                few = "{0} дм",
                                many = "{0} дм",
                                other = "{0} дм",
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
                                one = "{0} сантиметр",
                                few = "{0} сантиметра",
                                many = "{0} сантиметров",
                                other = "{0} сантиметра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} сантиметр",
                                few = "{0} сантиметра",
                                many = "{0} сантиметров",
                                other = "{0} сантиметра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} сантиметру",
                                few = "{0} сантиметрам",
                                many = "{0} сантиметрам",
                                other = "{0} сантиметра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} сантиметра",
                                few = "{0} сантиметров",
                                many = "{0} сантиметров",
                                other = "{0} сантиметра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} сантиметром",
                                few = "{0} сантиметрами",
                                many = "{0} сантиметрами",
                                other = "{0} сантиметра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} сантиметре",
                                few = "{0} сантиметрах",
                                many = "{0} сантиметрах",
                                other = "{0} сантиметрах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} сантиметре",
                                few = "{0} сантиметрах",
                                many = "{0} сантиметрах",
                                other = "{0} сантиметра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} см",
                                few = "{0} см",
                                many = "{0} см",
                                other = "{0} см",
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
                                one = "{0} миллиметр",
                                few = "{0} миллиметра",
                                many = "{0} миллиметров",
                                other = "{0} миллиметра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} миллиметр",
                                few = "{0} миллиметра",
                                many = "{0} миллиметров",
                                other = "{0} миллиметра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миллиметру",
                                few = "{0} миллиметрам",
                                many = "{0} миллиметрам",
                                other = "{0} миллиметра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} миллиметра",
                                few = "{0} миллиметров",
                                many = "{0} миллиметров",
                                other = "{0} миллиметра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} миллиметром",
                                few = "{0} миллиметрами",
                                many = "{0} миллиметрами",
                                other = "{0} миллиметра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} миллиметре",
                                few = "{0} миллиметрах",
                                many = "{0} миллиметрах",
                                other = "{0} миллиметрах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миллиметре",
                                few = "{0} миллиметрах",
                                many = "{0} миллиметрах",
                                other = "{0} миллиметра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мм",
                                few = "{0} мм",
                                many = "{0} мм",
                                other = "{0} мм",
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
                                one = "{0} микрометр",
                                few = "{0} микрометра",
                                many = "{0} микрометров",
                                other = "{0} микрометра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} микрометр",
                                few = "{0} микрометра",
                                many = "{0} микрометров",
                                other = "{0} микрометра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} микрометру",
                                few = "{0} микрометрам",
                                many = "{0} микрометрам",
                                other = "{0} микрометра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} микрометра",
                                few = "{0} микрометров",
                                many = "{0} микрометров",
                                other = "{0} микрометра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} микрометром",
                                few = "{0} микрометрами",
                                many = "{0} микрометрами",
                                other = "{0} микрометра",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} микрометре",
                                few = "{0} микрометрах",
                                many = "{0} микрометрах",
                                other = "{0} микрометра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мкм",
                                few = "{0} мкм",
                                many = "{0} мкм",
                                other = "{0} мкм",
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
                                one = "{0} нанометр",
                                few = "{0} нанометра",
                                many = "{0} нанометров",
                                other = "{0} нанометра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} нанометр",
                                few = "{0} нанометра",
                                many = "{0} нанометров",
                                other = "{0} нанометра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} нанометру",
                                few = "{0} нанометрам",
                                many = "{0} нанометрам",
                                other = "{0} нанометра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} нанометра",
                                few = "{0} нанометров",
                                many = "{0} нанометров",
                                other = "{0} нанометра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} нанометром",
                                few = "{0} нанометрами",
                                many = "{0} нанометрами",
                                other = "{0} нанометра",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} нанометре",
                                few = "{0} нанометрах",
                                many = "{0} нанометрах",
                                other = "{0} нанометра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} нм",
                                few = "{0} нм",
                                many = "{0} нм",
                                other = "{0} нм",
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
                                one = "{0} пикометр",
                                few = "{0} пикометра",
                                many = "{0} пикометров",
                                other = "{0} пикометра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} пикометр",
                                few = "{0} пикометра",
                                many = "{0} пикометров",
                                other = "{0} пикометра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} пикометру",
                                few = "{0} пикометрам",
                                many = "{0} пикометрам",
                                other = "{0} пикометра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} пикометра",
                                few = "{0} пикометров",
                                many = "{0} пикометров",
                                other = "{0} пикометра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} пикометром",
                                few = "{0} пикометрами",
                                many = "{0} пикометрами",
                                other = "{0} пикометра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} пикометре",
                                few = "{0} пикометрах",
                                many = "{0} пикометрах",
                                other = "{0} пикометрах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} пикометре",
                                few = "{0} пикометрах",
                                many = "{0} пикометрах",
                                other = "{0} пикометра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} пм",
                                few = "{0} пм",
                                many = "{0} пм",
                                other = "{0} пм",
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
                                one = "{0} миля",
                                few = "{0} мили",
                                many = "{0} миль",
                                other = "{0} мили",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} милю",
                                few = "{0} мили",
                                many = "{0} миль",
                                other = "{0} мили",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миле",
                                few = "{0} милям",
                                many = "{0} милям",
                                other = "{0} мили",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мили",
                                few = "{0} миль",
                                many = "{0} миль",
                                other = "{0} мили",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} милей",
                                few = "{0} милями",
                                many = "{0} милями",
                                other = "{0} мили",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} миле",
                                few = "{0} милях",
                                many = "{0} милях",
                                other = "{0} милях",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миле",
                                few = "{0} милях",
                                many = "{0} милях",
                                other = "{0} милях",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ми",
                                few = "{0} ми",
                                many = "{0} ми",
                                other = "{0} ми",
                            )
                        ),
                    )
                ),
            )
        override val lengthYard
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ярд",
                                few = "{0} ярда",
                                many = "{0} ярдов",
                                other = "{0} ярда",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ярд",
                                few = "{0} ярда",
                                many = "{0} ярдов",
                                other = "{0} ярда",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} ярду",
                                few = "{0} ярдам",
                                many = "{0} ярдам",
                                other = "{0} ярда",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} ярда",
                                few = "{0} ярдов",
                                many = "{0} ярдов",
                                other = "{0} ярда",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} ярдом",
                                few = "{0} ярдами",
                                many = "{0} ярдами",
                                other = "{0} ярда",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} ярде",
                                few = "{0} ярдах",
                                many = "{0} ярдах",
                                other = "{0} ярдах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} ярде",
                                few = "{0} ярдах",
                                many = "{0} ярдах",
                                other = "{0} ярдах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ярд",
                                few = "{0} ярд.",
                                many = "{0} ярд.",
                                other = "{0} ярд.",
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
                                one = "{0} фут",
                                few = "{0} фута",
                                many = "{0} футов",
                                other = "{0} фута",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} фут",
                                few = "{0} фута",
                                many = "{0} футов",
                                other = "{0} фута",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} футу",
                                few = "{0} футам",
                                many = "{0} футам",
                                other = "{0} фута",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} фута",
                                few = "{0} футов",
                                many = "{0} футов",
                                other = "{0} фута",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} футом",
                                few = "{0} футами",
                                many = "{0} футами",
                                other = "{0} фута",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} футе",
                                few = "{0} футах",
                                many = "{0} футах",
                                other = "{0} футах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} футе",
                                few = "{0} футах",
                                many = "{0} футах",
                                other = "{0} футах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фт",
                                few = "{0} фт",
                                many = "{0} фт",
                                other = "{0} фт",
                            )
                        ),
                    )
                ),
            )
        override val lengthInch
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дюйм",
                                few = "{0} дюйма",
                                many = "{0} дюймов",
                                other = "{0} дюйма",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} дюйм",
                                few = "{0} дюйма",
                                many = "{0} дюймов",
                                other = "{0} дюйма",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} дюйму",
                                few = "{0} дюймам",
                                many = "{0} дюймам",
                                other = "{0} дюйма",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} дюйма",
                                few = "{0} дюймов",
                                many = "{0} дюймов",
                                other = "{0} дюйма",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} дюймом",
                                few = "{0} дюймами",
                                many = "{0} дюймами",
                                other = "{0} дюйма",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} дюйме",
                                few = "{0} дюймах",
                                many = "{0} дюймах",
                                other = "{0} дюймах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} дюйме",
                                few = "{0} дюймах",
                                many = "{0} дюймах",
                                other = "{0} дюймах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дюйм",
                                few = "{0} дюйм.",
                                many = "{0} дюйм.",
                                other = "{0} дюйм.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дюйм.",
                                few = "{0} дюйм.",
                                many = "{0} дюйм.",
                                other = "{0} дюйм.",
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
                                one = "{0} парсек",
                                few = "{0} парсека",
                                many = "{0} парсеков",
                                other = "{0} парсека",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} парсек",
                                few = "{0} парсека",
                                many = "{0} парсеков",
                                other = "{0} парсека",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} парсеку",
                                few = "{0} парсекам",
                                many = "{0} парсекам",
                                other = "{0} парсека",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} парсека",
                                few = "{0} парсеков",
                                many = "{0} парсеков",
                                other = "{0} парсека",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} парсеком",
                                few = "{0} парсеками",
                                many = "{0} парсеками",
                                other = "{0} парсека",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} парсеке",
                                few = "{0} парсеках",
                                many = "{0} парсеках",
                                other = "{0} парсеках",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} парсеке",
                                few = "{0} парсеках",
                                many = "{0} парсеках",
                                other = "{0} парсеках",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} пк",
                                few = "{0} пк",
                                many = "{0} пк",
                                other = "{0} пк",
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
                                one = "{0} световой год",
                                few = "{0} световых года",
                                many = "{0} световых лет",
                                other = "{0} светового года",
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
                                many = "{0} св. л.",
                                other = "{0} св. г.",
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
                                one = "{0} астрономическая единица",
                                few = "{0} астрономические единицы",
                                many = "{0} астрономических единиц",
                                other = "{0} астрономической единицы",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} а. е.",
                                few = "{0} а. е.",
                                many = "{0} а. е.",
                                other = "{0} а. е.",
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
                                one = "{0} фурлонг",
                                few = "{0} фурлонга",
                                many = "{0} фурлонгов",
                                other = "{0} фурлонга",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фурл.",
                                few = "{0} фурл.",
                                many = "{0} фурл.",
                                other = "{0} фурл.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фрл",
                                few = "{0} фрл",
                                many = "{0} фрл",
                                other = "{0} фрл",
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
                                one = "{0} морская сажень",
                                few = "{0} морских сажени",
                                many = "{0} морских саженей",
                                other = "{0} морской сажени",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мор. сажень",
                                few = "{0} мор. сажени",
                                many = "{0} мор. саженей",
                                other = "{0} мор. сажени",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} м. саж.",
                                few = "{0} м. саж.",
                                many = "{0} м. саж.",
                                other = "{0} м. саж.",
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
                                one = "{0} морская миля",
                                few = "{0} морские мили",
                                many = "{0} морских миль",
                                other = "{0} морской мили",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мор. ми",
                                few = "{0} мор. ми",
                                many = "{0} мор. ми",
                                other = "{0} мор. ми",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} м. ми",
                                few = "{0} м. ми",
                                many = "{0} м. ми",
                                other = "{0} м. ми",
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
                                one = "{0} скандинавская миля",
                                few = "{0} скандинавские мили",
                                many = "{0} скандинавских миль",
                                other = "{0} скандинавской мили",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} скандинавскую милю",
                                few = "{0} скандинавские мили",
                                many = "{0} скандинавских миль",
                                other = "{0} скандинавской мили",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} скандинавской миле",
                                few = "{0} скандинавским милям",
                                many = "{0} скандинавским милям",
                                other = "{0} скандинавской мили",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} скандинавской мили",
                                few = "{0} скандинавских миль",
                                many = "{0} скандинавских миль",
                                other = "{0} скандинавской мили",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} скандинавской милей",
                                few = "{0} скандинавскими милями",
                                many = "{0} скандинавскими милями",
                                other = "{0} скандинавской мили",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} скандинавской миле",
                                few = "{0} скандинавских милях",
                                many = "{0} скандинавских милях",
                                other = "{0} скандинавской мили",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ск. ми",
                                few = "{0} ск. ми",
                                many = "{0} ск. ми",
                                other = "{0} ск. ми",
                            )
                        ),
                    )
                ),
            )
        override val lengthPoint
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} пункт",
                                few = "{0} пункта",
                                many = "{0} пунктов",
                                other = "{0} пункта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} пункт",
                                few = "{0} пункта",
                                many = "{0} пунктов",
                                other = "{0} пункта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} пункту",
                                few = "{0} пунктам",
                                many = "{0} пунктам",
                                other = "{0} пункта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} пункта",
                                few = "{0} пунктов",
                                many = "{0} пунктов",
                                other = "{0} пункта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} пунктом",
                                few = "{0} пунктами",
                                many = "{0} пунктами",
                                other = "{0} пункта",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} пункте",
                                few = "{0} пунктах",
                                many = "{0} пунктах",
                                other = "{0} пункта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} пкт",
                                few = "{0} пкт",
                                many = "{0} пкт",
                                other = "{0} пкт",
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
                                one = "{0} солнечный радиус",
                                few = "{0} солнечных радиуса",
                                many = "{0} солнечных радиусов",
                                other = "{0} солнечного радиуса",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} солнечный радиус",
                                few = "{0} солнечных радиуса",
                                many = "{0} солнечных радиусов",
                                other = "{0} солнечного радиуса",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} солнечному радиусу",
                                few = "{0} солнечным радиусам",
                                many = "{0} солнечным радиусам",
                                other = "{0} солнечного радиуса",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} солнечного радиуса",
                                few = "{0} солнечных радиусов",
                                many = "{0} солнечных радиусов",
                                other = "{0} солнечного радиуса",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} солнечным радиусом",
                                few = "{0} солнечными радиусами",
                                many = "{0} солнечными радиусами",
                                other = "{0} солнечного радиуса",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} солнечном радиусе",
                                few = "{0} солнечных радиусах",
                                many = "{0} солнечных радиусах",
                                other = "{0} солнечных радиусах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} солнечном радиусе",
                                few = "{0} солнечных радиусах",
                                many = "{0} солнечных радиусах",
                                other = "{0} солнечных радиусах",
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
                                one = "{0} люкс",
                                few = "{0} люкса",
                                many = "{0} люксов",
                                other = "{0} люкса",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} люкс",
                                few = "{0} люкса",
                                many = "{0} люкс",
                                other = "{0} люкса",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} люксу",
                                few = "{0} люксам",
                                many = "{0} люксам",
                                other = "{0} люкса",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} люкса",
                                few = "{0} люксов",
                                many = "{0} люкс",
                                other = "{0} люкса",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} люксом",
                                few = "{0} люксами",
                                many = "{0} люксами",
                                other = "{0} люкса",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} люксе",
                                few = "{0} люксах",
                                many = "{0} люксах",
                                other = "{0} люкса",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} лк",
                                few = "{0} лк",
                                many = "{0} лк",
                                other = "{0} лк",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaPerSquareMeter formatter is not implemented yet for ru")
        override val lightCandela
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кандела",
                                few = "{0} канделы",
                                many = "{0} кандел",
                                other = "{0} канделы",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} канделу",
                                few = "{0} канделы",
                                many = "{0} кандел",
                                other = "{0} канделы",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} канделе",
                                few = "{0} канделам",
                                many = "{0} канделам",
                                other = "{0} канделы",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} канделы",
                                few = "{0} кандел",
                                many = "{0} кандел",
                                other = "{0} канделы",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} канделой",
                                few = "{0} канделами",
                                many = "{0} канделами",
                                other = "{0} канделы",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} канделе",
                                few = "{0} канделах",
                                many = "{0} канделах",
                                other = "{0} канделы",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кд",
                                few = "{0} кд",
                                many = "{0} кд",
                                other = "{0} кд",
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
                                one = "{0} люмен",
                                few = "{0} люмена",
                                many = "{0} люмен",
                                other = "{0} люмена",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} люмен",
                                few = "{0} люмена",
                                many = "{0} люмен",
                                other = "{0} люмена",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} люмену",
                                few = "{0} люменам",
                                many = "{0} люменам",
                                other = "{0} люмена",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} люмена",
                                few = "{0} люмен",
                                many = "{0} люмен",
                                other = "{0} люмена",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} люменом",
                                few = "{0} люменами",
                                many = "{0} люменами",
                                other = "{0} люмена",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} люмене",
                                few = "{0} люменах",
                                many = "{0} люменах",
                                other = "{0} люмена",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} лм",
                                few = "{0} лм",
                                many = "{0} лм",
                                other = "{0} лм",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaSquareMeterPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaSquareMeterPerSquareMeter formatter is not implemented yet for ru")
        override val lightSolarLuminosity
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} солнечная светимость",
                                few = "{0} солнечные светимости",
                                many = "{0} солнечных светимостей",
                                other = "{0} солнечной светимости",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} солнечную светимость",
                                few = "{0} солнечные светимости",
                                many = "{0} солнечных светимостей",
                                other = "{0} солнечной светимости",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} солнечной светимости",
                                few = "{0} солнечным светимостям",
                                many = "{0} солнечным светимостям",
                                other = "{0} солнечной светимости",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} солнечной светимости",
                                few = "{0} солнечных светимостей",
                                many = "{0} солнечных светимостей",
                                other = "{0} солнечной светимости",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} солнечной светимостью",
                                few = "{0} солнечными светимостями",
                                many = "{0} солнечными светимостями",
                                other = "{0} солнечной светимости",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} солнечной светимости",
                                few = "{0} солнечных светимостях",
                                many = "{0} солнечных светимостях",
                                other = "{0} солнечных светимостях",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} солнечной светимости",
                                few = "{0} солнечных светимостях",
                                many = "{0} солнечных светимостях",
                                other = "{0} солнечных светимостях",
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
                                one = "{0} тонна",
                                few = "{0} тонны",
                                many = "{0} тонн",
                                other = "{0} тонны",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} тонну",
                                few = "{0} тонны",
                                many = "{0} тонн",
                                other = "{0} тонны",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} тонне",
                                few = "{0} тоннам",
                                many = "{0} тоннам",
                                other = "{0} тонны",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} тонны",
                                few = "{0} тонн",
                                many = "{0} тонн",
                                other = "{0} тонны",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} тонной",
                                few = "{0} тоннами",
                                many = "{0} тоннами",
                                other = "{0} тонны",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} тонне",
                                few = "{0} тоннах",
                                many = "{0} тоннах",
                                other = "{0} тонны",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} т",
                                few = "{0} т",
                                many = "{0} т",
                                other = "{0} т",
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
                                one = "{0} килограмм",
                                few = "{0} килограмма",
                                many = "{0} килограмм",
                                other = "{0} килограмма",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} килограмм",
                                few = "{0} килограмма",
                                many = "{0} килограмм",
                                other = "{0} килограмма",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} килограмму",
                                few = "{0} килограммам",
                                many = "{0} килограммам",
                                other = "{0} килограмма",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} килограмма",
                                few = "{0} килограмм",
                                many = "{0} килограмм",
                                other = "{0} килограмма",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} килограммом",
                                few = "{0} килограммами",
                                many = "{0} килограммами",
                                other = "{0} килограмма",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} килограмме",
                                few = "{0} килограммах",
                                many = "{0} килограммах",
                                other = "{0} килограммах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} килограмме",
                                few = "{0} килограммах",
                                many = "{0} килограммах",
                                other = "{0} килограмма",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} грамм",
                                few = "{0} грамма",
                                many = "{0} грамм",
                                other = "{0} грамма",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} грамм",
                                few = "{0} грамма",
                                many = "{0} грамм",
                                other = "{0} грамма",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} грамму",
                                few = "{0} граммам",
                                many = "{0} граммам",
                                other = "{0} грамма",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} грамма",
                                few = "{0} грамм",
                                many = "{0} грамм",
                                other = "{0} грамма",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} граммом",
                                few = "{0} граммами",
                                many = "{0} граммами",
                                other = "{0} грамма",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} грамме",
                                few = "{0} граммах",
                                many = "{0} граммах",
                                other = "{0} граммах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} грамме",
                                few = "{0} граммах",
                                many = "{0} граммах",
                                other = "{0} грамма",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} миллиграмм",
                                few = "{0} миллиграмма",
                                many = "{0} миллиграмм",
                                other = "{0} миллиграмма",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} миллиграмм",
                                few = "{0} миллиграмма",
                                many = "{0} миллиграмм",
                                other = "{0} миллиграмма",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миллиграмму",
                                few = "{0} миллиграммам",
                                many = "{0} миллиграммам",
                                other = "{0} миллиграмма",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} миллиграмма",
                                few = "{0} миллиграмм",
                                many = "{0} миллиграмм",
                                other = "{0} миллиграмма",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} миллиграммом",
                                few = "{0} миллиграммами",
                                many = "{0} миллиграммами",
                                other = "{0} миллиграмма",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} миллиграмме",
                                few = "{0} миллиграммах",
                                many = "{0} миллиграммах",
                                other = "{0} миллиграммах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миллиграмме",
                                few = "{0} миллиграммах",
                                many = "{0} миллиграммах",
                                other = "{0} миллиграмма",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} микрограмм",
                                few = "{0} микрограмма",
                                many = "{0} микрограмм",
                                other = "{0} микрограмма",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} микрограмм",
                                few = "{0} микрограмма",
                                many = "{0} микрограмм",
                                other = "{0} микрограмма",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} микрограмму",
                                few = "{0} микрограммам",
                                many = "{0} микрограммам",
                                other = "{0} микрограмма",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} микрограмма",
                                few = "{0} микрограмм",
                                many = "{0} микрограмм",
                                other = "{0} микрограмма",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} микрограммом",
                                few = "{0} микрограммами",
                                many = "{0} микрограммами",
                                other = "{0} микрограмма",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} микрограмме",
                                few = "{0} микрограммах",
                                many = "{0} микрограммах",
                                other = "{0} микрограмма",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
                                one = "{0} американская тонна",
                                few = "{0} американские тонны",
                                many = "{0} американских тонн",
                                other = "{0} американской тонны",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} амер. т",
                                few = "{0} амер. т",
                                many = "{0} амер. т",
                                other = "{0} амер. т",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ам. т",
                                few = "{0} ам. т",
                                many = "{0} ам. т",
                                other = "{0} ам. т",
                            )
                        ),
                    )
                ),
            )
        override val massStone
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} стоун",
                                few = "{0} стоуна",
                                many = "{0} стоунов",
                                other = "{0} стоуна",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} стн",
                                few = "{0} стн",
                                many = "{0} стн",
                                other = "{0} стн",
                            )
                        ),
                    )
                ),
            )
        override val massPound
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фунт",
                                few = "{0} фунта",
                                many = "{0} фунтов",
                                other = "{0} фунта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} фунт",
                                few = "{0} фунта",
                                many = "{0} фунтов",
                                other = "{0} фунта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} фунту",
                                few = "{0} фунтам",
                                many = "{0} фунтам",
                                other = "{0} фунта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} фунта",
                                few = "{0} фунтов",
                                many = "{0} фунтов",
                                other = "{0} фунта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} фунтом",
                                few = "{0} фунтами",
                                many = "{0} фунтами",
                                other = "{0} фунта",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} фунте",
                                few = "{0} фунтах",
                                many = "{0} фунтах",
                                other = "{0} фунтах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} фунте",
                                few = "{0} фунтах",
                                many = "{0} фунтах",
                                other = "{0} фунтах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фнт",
                                few = "{0} фнт",
                                many = "{0} фнт",
                                other = "{0} фнт",
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
                                one = "{0} унция",
                                few = "{0} унции",
                                many = "{0} унций",
                                other = "{0} унции",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} унцию",
                                few = "{0} унции",
                                many = "{0} унций",
                                other = "{0} унции",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} унции",
                                few = "{0} унциям",
                                many = "{0} унциям",
                                other = "{0} унции",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} унции",
                                few = "{0} унций",
                                many = "{0} унций",
                                other = "{0} унции",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} унцией",
                                few = "{0} унциями",
                                many = "{0} унциями",
                                other = "{0} унции",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} унции",
                                few = "{0} унциях",
                                many = "{0} унциях",
                                other = "{0} унциях",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} унции",
                                few = "{0} унциях",
                                many = "{0} унциях",
                                other = "{0} унциях",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} унц.",
                                few = "{0} унц.",
                                many = "{0} унц.",
                                other = "{0} унц.",
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
                                one = "{0} тройская унция",
                                few = "{0} тройские унции",
                                many = "{0} тройских унций",
                                other = "{0} тройской унции",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} тр. унц.",
                                few = "{0} тр. унц.",
                                many = "{0} тр. унц.",
                                other = "{0} тр. унц.",
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
                                one = "{0} карат",
                                few = "{0} карата",
                                many = "{0} карат",
                                other = "{0} карата",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} карат",
                                few = "{0} карата",
                                many = "{0} карат",
                                other = "{0} карата",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} карату",
                                few = "{0} каратам",
                                many = "{0} каратам",
                                other = "{0} карата",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} карата",
                                few = "{0} карат",
                                many = "{0} карат",
                                other = "{0} карата",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} каратом",
                                few = "{0} каратами",
                                many = "{0} каратами",
                                other = "{0} карата",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} карате",
                                few = "{0} каратах",
                                many = "{0} каратах",
                                other = "{0} карата",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кар",
                                few = "{0} кар",
                                many = "{0} кар",
                                other = "{0} кар",
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
                                one = "{0} дальтон",
                                few = "{0} дальтона",
                                many = "{0} дальтонов",
                                other = "{0} дальтона",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} дальтон",
                                few = "{0} дальтона",
                                many = "{0} дальтонов",
                                other = "{0} дальтона",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} дальтону",
                                few = "{0} дальтонам",
                                many = "{0} дальтонам",
                                other = "{0} дальтона",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} дальтона",
                                few = "{0} дальтонов",
                                many = "{0} дальтонов",
                                other = "{0} дальтона",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} дальтоном",
                                few = "{0} дальтонами",
                                many = "{0} дальтонами",
                                other = "{0} дальтона",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} дальтоне",
                                few = "{0} дальтонах",
                                many = "{0} дальтонах",
                                other = "{0} дальтонах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} дальтоне",
                                few = "{0} дальтонах",
                                many = "{0} дальтонах",
                                other = "{0} дальтонах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Да",
                                few = "{0} Да",
                                many = "{0} Да",
                                other = "{0} Да",
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
                                one = "{0} масса Земли",
                                few = "{0} массы Земли",
                                many = "{0} масс Земли",
                                other = "{0} массы Земли",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} массу Земли",
                                few = "{0} массы Земли",
                                many = "{0} масс Земли",
                                other = "{0} массы Земли",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} массе Земли",
                                few = "{0} массам Земли",
                                many = "{0} массам Земли",
                                other = "{0} массы Земли",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} массы Земли",
                                few = "{0} масс Земли",
                                many = "{0} масс Земли",
                                other = "{0} массы Земли",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} массой Земли",
                                few = "{0} массами Земли",
                                many = "{0} массами Земли",
                                other = "{0} массы Земли",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} массе Земли",
                                few = "{0} массах Земли",
                                many = "{0} массах Земли",
                                other = "{0} массах Земли",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} массе Земли",
                                few = "{0} массах Земли",
                                many = "{0} массах Земли",
                                other = "{0} массах Земли",
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
                                one = "{0} солнечная масса",
                                few = "{0} солнечные массы",
                                many = "{0} солнечных масс",
                                other = "{0} солнечной массы",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} солнечную массу",
                                few = "{0} солнечные массы",
                                many = "{0} солнечных масс",
                                other = "{0} солнечной массы",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} солнечной массе",
                                few = "{0} солнечным массам",
                                many = "{0} солнечным массам",
                                other = "{0} солнечной массы",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} солнечной массы",
                                few = "{0} солнечных масс",
                                many = "{0} солнечных масс",
                                other = "{0} солнечной массы",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} солнечной массой",
                                few = "{0} солнечными массами",
                                many = "{0} солнечными массами",
                                other = "{0} солнечной массы",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} солнечной массе",
                                few = "{0} солнечных массах",
                                many = "{0} солнечных массах",
                                other = "{0} солнечных массах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} солнечной массе",
                                few = "{0} солнечных массах",
                                many = "{0} солнечных массах",
                                other = "{0} солнечных массах",
                            )
                        ),
                    )
                ),
            )
        override val massGrain
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гран",
                                few = "{0} грана",
                                many = "{0} гранов",
                                other = "{0} грана",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гран",
                                few = "{0} грана",
                                many = "{0} гранов",
                                other = "{0} грана",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} грану",
                                few = "{0} гранам",
                                many = "{0} гранам",
                                other = "{0} грана",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} грана",
                                few = "{0} гранов",
                                many = "{0} гранов",
                                other = "{0} грана",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} граном",
                                few = "{0} гранами",
                                many = "{0} гранами",
                                other = "{0} грана",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} гране",
                                few = "{0} гранах",
                                many = "{0} гранах",
                                other = "{0} гранах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} гране",
                                few = "{0} гранах",
                                many = "{0} гранах",
                                other = "{0} гранах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гран",
                                few = "{0} грана",
                                many = "{0} гранов",
                                other = "{0} грана",
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
                                one = "{0} гигаватт",
                                few = "{0} гигаватта",
                                many = "{0} гигаватт",
                                other = "{0} гигаватта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гигаватт",
                                few = "{0} гигаватта",
                                many = "{0} гигаватт",
                                other = "{0} гигаватта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} гигаватту",
                                few = "{0} гигаваттам",
                                many = "{0} гигаваттам",
                                other = "{0} гигаватта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} гигаватта",
                                few = "{0} гигаватт",
                                many = "{0} гигаватт",
                                other = "{0} гигаватта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} гигаваттом",
                                few = "{0} гигаваттами",
                                many = "{0} гигаваттами",
                                other = "{0} гигаватта",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} гигаватте",
                                few = "{0} гигаваттах",
                                many = "{0} гигаваттах",
                                other = "{0} гигаватта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ГВт",
                                few = "{0} ГВт",
                                many = "{0} ГВт",
                                other = "{0} ГВт",
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
                                one = "{0} мегаватт",
                                few = "{0} мегаватта",
                                many = "{0} мегаватт",
                                other = "{0} мегаватта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мегаватт",
                                few = "{0} мегаватта",
                                many = "{0} мегаватт",
                                other = "{0} мегаватта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мегаватту",
                                few = "{0} мегаваттам",
                                many = "{0} мегаваттам",
                                other = "{0} мегаватта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мегаватта",
                                few = "{0} мегаватт",
                                many = "{0} мегаватт",
                                other = "{0} мегаватта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мегаваттом",
                                few = "{0} мегаваттами",
                                many = "{0} мегаваттами",
                                other = "{0} мегаватта",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} мегаватте",
                                few = "{0} мегаваттах",
                                many = "{0} мегаваттах",
                                other = "{0} мегаватта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} МВт",
                                few = "{0} МВт",
                                many = "{0} МВт",
                                other = "{0} МВт",
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
                                one = "{0} киловатт",
                                few = "{0} киловатта",
                                many = "{0} киловатт",
                                other = "{0} киловатта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} киловатт",
                                few = "{0} киловатта",
                                many = "{0} киловатт",
                                other = "{0} киловатта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} киловатту",
                                few = "{0} киловаттам",
                                many = "{0} киловаттам",
                                other = "{0} киловатта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} киловатта",
                                few = "{0} киловатт",
                                many = "{0} киловатт",
                                other = "{0} киловатта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} киловаттом",
                                few = "{0} киловаттами",
                                many = "{0} киловаттами",
                                other = "{0} киловатта",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} киловатте",
                                few = "{0} киловаттах",
                                many = "{0} киловаттах",
                                other = "{0} киловатта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кВт",
                                few = "{0} кВт",
                                many = "{0} кВт",
                                other = "{0} кВт",
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
                                one = "{0} ватт",
                                few = "{0} ватта",
                                many = "{0} ватт",
                                other = "{0} ватта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ватт",
                                few = "{0} ватта",
                                many = "{0} ватт",
                                other = "{0} ватта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} ватту",
                                few = "{0} ваттам",
                                many = "{0} ваттам",
                                other = "{0} ватта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} ватта",
                                few = "{0} ватт",
                                many = "{0} ватт",
                                other = "{0} ватта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} ваттом",
                                few = "{0} ваттами",
                                many = "{0} ваттами",
                                other = "{0} ватта",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} ватте",
                                few = "{0} ваттах",
                                many = "{0} ваттах",
                                other = "{0} ватта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Вт",
                                few = "{0} Вт",
                                many = "{0} Вт",
                                other = "{0} Вт",
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
                                one = "{0} милливатт",
                                few = "{0} милливатта",
                                many = "{0} милливатт",
                                other = "{0} милливатта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} милливатт",
                                few = "{0} милливатта",
                                many = "{0} милливатт",
                                other = "{0} милливатта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} милливатту",
                                few = "{0} милливаттам",
                                many = "{0} милливаттам",
                                other = "{0} милливатта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} милливатта",
                                few = "{0} милливатт",
                                many = "{0} милливатт",
                                other = "{0} милливатта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} милливаттом",
                                few = "{0} милливаттами",
                                many = "{0} милливаттами",
                                other = "{0} милливатта",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} милливатте",
                                few = "{0} милливаттах",
                                many = "{0} милливаттах",
                                other = "{0} милливатта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мВт",
                                few = "{0} мВт",
                                many = "{0} мВт",
                                other = "{0} мВт",
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
                                one = "{0} лошадиная сила",
                                few = "{0} лошадиные силы",
                                many = "{0} лошадиных сил",
                                other = "{0} лошадиной силы",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} л. с.",
                                few = "{0} л. с.",
                                many = "{0} л. с.",
                                other = "{0} л. с.",
                            )
                        ),
                    )
                ),
            )
        override val powerKilogramSquareMeterPerCubicSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecond formatter is not implemented yet for ru")
        override val pressureMillimeterOfhg
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} миллиметр ртутного столба",
                                few = "{0} миллиметра ртутного столба",
                                many = "{0} миллиметров ртутного столба",
                                other = "{0} миллиметра ртутного столба",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} миллиметр ртутного столба",
                                few = "{0} миллиметра ртутного столба",
                                many = "{0} миллиметров ртутного столба",
                                other = "{0} миллиметра ртутного столба",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миллиметру ртутного столба",
                                few = "{0} миллиметрам ртутного столба",
                                many = "{0} миллиметрам ртутного столба",
                                other = "{0} миллиметра ртутного столба",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} миллиметра ртутного столба",
                                few = "{0} миллиметров ртутного столба",
                                many = "{0} миллиметров ртутного столба",
                                other = "{0} миллиметра ртутного столба",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} миллиметром ртутного столба",
                                few = "{0} миллиметрами ртутного столба",
                                many = "{0} миллиметрами ртутного столба",
                                other = "{0} миллиметра ртутного столба",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миллиметре ртутного столба",
                                few = "{0} миллиметрах ртутного столба",
                                many = "{0} миллиметрах ртутного столба",
                                other = "{0} миллиметра ртутного столба",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мм рт. ст.",
                                few = "{0} мм рт. ст.",
                                many = "{0} мм рт. ст.",
                                other = "{0} мм рт. ст.",
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
                                one = "{0} фунт на квадратный дюйм",
                                few = "{0} фунта на квадратный дюйм",
                                many = "{0} фунтов на квадратный дюйм",
                                other = "{0} фунта на квадратный дюйм",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ф/дюйм²",
                                few = "{0} ф/дюйм²",
                                many = "{0} ф/дюйм²",
                                other = "{0} ф/дюйм²",
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
                                one = "{0} дюйм ртутного столба",
                                few = "{0} дюйма ртутного столба",
                                many = "{0} дюймов ртутного столба",
                                other = "{0} дюйма ртутного столба",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} д. рт. ст.",
                                few = "{0} д. рт. ст.",
                                many = "{0} д. рт. ст.",
                                other = "{0} д. рт. ст.",
                            )
                        ),
                    )
                ),
            )
        override val pressureBar
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} бар",
                                few = "{0} бара",
                                many = "{0} бар",
                                other = "{0} бара",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} бар",
                                few = "{0} бара",
                                many = "{0} бар",
                                other = "{0} бара",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} бару",
                                few = "{0} барам",
                                many = "{0} барам",
                                other = "{0} бара",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} бара",
                                few = "{0} бар",
                                many = "{0} бар",
                                other = "{0} бара",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} баром",
                                few = "{0} барами",
                                many = "{0} барами",
                                other = "{0} бара",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} баре",
                                few = "{0} барах",
                                many = "{0} барах",
                                other = "{0} бара",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} бар",
                                few = "{0} бар",
                                many = "{0} бар",
                                other = "{0} бар",
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
                                one = "{0} миллибар",
                                few = "{0} миллибара",
                                many = "{0} миллибар",
                                other = "{0} миллибара",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} миллибар",
                                few = "{0} миллибара",
                                many = "{0} миллибар",
                                other = "{0} миллибара",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миллибару",
                                few = "{0} миллибарам",
                                many = "{0} миллибарам",
                                other = "{0} миллибара",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} миллибара",
                                few = "{0} миллибар",
                                many = "{0} миллибар",
                                other = "{0} миллибара",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} миллибаром",
                                few = "{0} миллибарами",
                                many = "{0} миллибарами",
                                other = "{0} миллибара",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миллибаре",
                                few = "{0} миллибарах",
                                many = "{0} миллибарах",
                                other = "{0} миллибара",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мбар",
                                few = "{0} мбар",
                                many = "{0} мбар",
                                other = "{0} мбар",
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
                                one = "{0} атмосфера",
                                few = "{0} атмосферы",
                                many = "{0} атмосфер",
                                other = "{0} атмосферы",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} атмосферу",
                                few = "{0} атмосферы",
                                many = "{0} атмосфер",
                                other = "{0} атмосферы",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} атмосфере",
                                few = "{0} атмосферам",
                                many = "{0} атмосферам",
                                other = "{0} атмосферы",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} атмосферы",
                                few = "{0} атмосфер",
                                many = "{0} атмосфер",
                                other = "{0} атмосферы",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} атмосферой",
                                few = "{0} атмосферами",
                                many = "{0} атмосферами",
                                other = "{0} атмосферы",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} атмосфере",
                                few = "{0} атмосферах",
                                many = "{0} атмосферах",
                                other = "{0} атмосферы",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} атм",
                                few = "{0} атм",
                                many = "{0} атм",
                                other = "{0} атм",
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
                                one = "{0} паскаль",
                                few = "{0} паскаля",
                                many = "{0} паскалей",
                                other = "{0} паскаля",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} паскаль",
                                few = "{0} паскаля",
                                many = "{0} паскалей",
                                other = "{0} паскаля",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} паскалю",
                                few = "{0} паскалям",
                                many = "{0} паскалям",
                                other = "{0} паскаля",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} паскаля",
                                few = "{0} паскалей",
                                many = "{0} паскалей",
                                other = "{0} паскаля",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} паскалем",
                                few = "{0} паскалями",
                                many = "{0} паскалями",
                                other = "{0} паскаля",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} паскале",
                                few = "{0} паскалях",
                                many = "{0} паскалях",
                                other = "{0} паскаля",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Па",
                                few = "{0} Па",
                                many = "{0} Па",
                                other = "{0} Па",
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
                                one = "{0} гектопаскаль",
                                few = "{0} гектопаскаля",
                                many = "{0} гектопаскалей",
                                other = "{0} гектопаскаля",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гектопаскаль",
                                few = "{0} гектопаскаля",
                                many = "{0} гектопаскалей",
                                other = "{0} гектопаскаля",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} гектопаскалю",
                                few = "{0} гектопаскалям",
                                many = "{0} гектопаскалям",
                                other = "{0} гектопаскаля",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} гектопаскаля",
                                few = "{0} гектопаскалей",
                                many = "{0} гектопаскалей",
                                other = "{0} гектопаскаля",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} гектопаскалем",
                                few = "{0} гектопаскалями",
                                many = "{0} гектопаскалями",
                                other = "{0} гектопаскаля",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} гектопаскале",
                                few = "{0} гектопаскалях",
                                many = "{0} гектопаскалях",
                                other = "{0} гектопаскаля",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гПа",
                                few = "{0} гПа",
                                many = "{0} гПа",
                                other = "{0} гПа",
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
                                one = "{0} килопаскаль",
                                few = "{0} килопаскаля",
                                many = "{0} килопаскалей",
                                other = "{0} килопаскаля",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} килопаскаль",
                                few = "{0} килопаскаля",
                                many = "{0} килопаскалей",
                                other = "{0} килопаскаля",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} килопаскалю",
                                few = "{0} килопаскалям",
                                many = "{0} килопаскалям",
                                other = "{0} килопаскаля",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} килопаскаля",
                                few = "{0} килопаскалей",
                                many = "{0} килопаскалей",
                                other = "{0} килопаскаля",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} килопаскалем",
                                few = "{0} килопаскалями",
                                many = "{0} килопаскалями",
                                other = "{0} килопаскаля",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} килопаскале",
                                few = "{0} килопаскалях",
                                many = "{0} килопаскалях",
                                other = "{0} килопаскалях",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} килопаскале",
                                few = "{0} килопаскалях",
                                many = "{0} килопаскалях",
                                other = "{0} килопаскаля",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кПа",
                                few = "{0} кПа",
                                many = "{0} кПа",
                                other = "{0} кПа",
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
                                one = "{0} мегапаскаль",
                                few = "{0} мегапаскаля",
                                many = "{0} мегапаскалей",
                                other = "{0} мегапаскаля",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мегапаскаль",
                                few = "{0} мегапаскаля",
                                many = "{0} мегапаскалей",
                                other = "{0} мегапаскаля",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мегапаскалю",
                                few = "{0} мегапаскалям",
                                many = "{0} мегапаскалям",
                                other = "{0} мегапаскаля",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мегапаскаля",
                                few = "{0} мегапаскалей",
                                many = "{0} мегапаскалей",
                                other = "{0} мегапаскаля",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мегапаскалем",
                                few = "{0} мегапаскалями",
                                many = "{0} мегапаскалями",
                                other = "{0} мегапаскаля",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} мегапаскале",
                                few = "{0} мегапаскалях",
                                many = "{0} мегапаскалях",
                                other = "{0} мегапаскаля",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} МПа",
                                few = "{0} МПа",
                                many = "{0} МПа",
                                other = "{0} МПа",
                            )
                        ),
                    )
                ),
            )
        override val pressureGasolineEnergyDensity: MultiLengthGenderedPattern
            get() = TODO("GasolineEnergyDensity formatter is not implemented yet for ru")
        override val pressureKilogramPerMeterSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramPerMeterSquareSecond formatter is not implemented yet for ru")
        override val speedKilometerPerHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} километр в час",
                                few = "{0} километра в час",
                                many = "{0} километров в час",
                                other = "{0} километра в час",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} километр в час",
                                few = "{0} километра в час",
                                many = "{0} километров в час",
                                other = "{0} километра в час",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} километру в час",
                                few = "{0} километрам в час",
                                many = "{0} километрам в час",
                                other = "{0} километра в час",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} километра в час",
                                few = "{0} километров в час",
                                many = "{0} километров в час",
                                other = "{0} километра в час",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} километром в час",
                                few = "{0} километрами в час",
                                many = "{0} километрами в час",
                                other = "{0} километра в час",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} километре в час",
                                few = "{0} километрах в час",
                                many = "{0} километрах в час",
                                other = "{0} километрах в час",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} километре в час",
                                few = "{0} километрах в час",
                                many = "{0} километрах в час",
                                other = "{0} километра в час",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} км/ч",
                                few = "{0} км/ч",
                                many = "{0} км/ч",
                                other = "{0} км/ч",
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
                                one = "{0} метр в секунду",
                                few = "{0} метра в секунду",
                                many = "{0} метров в секунду",
                                other = "{0} метра в секунду",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} метр в секунду",
                                few = "{0} метра в секунду",
                                many = "{0} метров в секунду",
                                other = "{0} метра в секунду",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} метру в секунду",
                                few = "{0} метрам в секунду",
                                many = "{0} метрам в секунду",
                                other = "{0} метра в секунду",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} метра в секунду",
                                few = "{0} метров в секунду",
                                many = "{0} метров в секунду",
                                other = "{0} метра в секунду",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} метром в секунду",
                                few = "{0} метрами в секунду",
                                many = "{0} метрами в секунду",
                                other = "{0} метра в секунду",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} метре в секунду",
                                few = "{0} метрах в секунду",
                                many = "{0} метрах в секунду",
                                other = "{0} метрах в секунду",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} метре в секунду",
                                few = "{0} метрах в секунду",
                                many = "{0} метрах в секунду",
                                other = "{0} метра в секунду",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} миля в час",
                                few = "{0} мили в час",
                                many = "{0} миль в час",
                                other = "{0} мили в час",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} милю в час",
                                few = "{0} мили в час",
                                many = "{0} миль в час",
                                other = "{0} мили в час",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миле в час",
                                few = "{0} милям в час",
                                many = "{0} милям в час",
                                other = "{0} мили в час",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мили в час",
                                few = "{0} миль в час",
                                many = "{0} миль в час",
                                other = "{0} мили в час",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} милей в час",
                                few = "{0} милями в час",
                                many = "{0} милями в час",
                                other = "{0} мили в час",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} миле в час",
                                few = "{0} милях в час",
                                many = "{0} милях в час",
                                other = "{0} милях в час",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миле в час",
                                few = "{0} милях в час",
                                many = "{0} милях в час",
                                other = "{0} милях в час",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ми/ч",
                                few = "{0} ми/ч",
                                many = "{0} ми/ч",
                                other = "{0} ми/ч",
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
                                one = "{0} узел",
                                few = "{0} узла",
                                many = "{0} узлов",
                                other = "{0} узла",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} уз",
                                few = "{0} уз",
                                many = "{0} уз",
                                other = "{0} уз",
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
                                one = "{0} балл Бофорта",
                                few = "{0} балла Бофорта",
                                many = "{0} баллов Бофорта",
                                other = "{0} балла Бофорта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Бфт",
                                few = "{0} Бфт",
                                many = "{0} Бфт",
                                other = "{0} Бфт",
                            )
                        ),
                    )
                ),
            )
        override val speedLightSpeed: MultiLengthGenderedPattern
            get() = TODO("LightSpeed formatter is not implemented yet for ru")
        override val temperatureGeneric
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} градус",
                                few = "{0} градуса",
                                many = "{0} градусов",
                                other = "{0} градуса",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} градус",
                                few = "{0} градуса",
                                many = "{0} градусов",
                                other = "{0} градуса",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} градусу",
                                few = "{0} градусам",
                                many = "{0} градусам",
                                other = "{0} градуса",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} градуса",
                                few = "{0} градусов",
                                many = "{0} градусов",
                                other = "{0} градуса",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} градусом",
                                few = "{0} градусами",
                                many = "{0} градусами",
                                other = "{0} градуса",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} градусе",
                                few = "{0} градусах",
                                many = "{0} градусах",
                                other = "{0} градусах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} градусе",
                                few = "{0} градусах",
                                many = "{0} градусах",
                                other = "{0} градуса",
                            )
                        ),
                    )
                ),
            )
        override val temperatureCelsius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} градус Цельсия",
                                few = "{0} градуса Цельсия",
                                many = "{0} градусов Цельсия",
                                other = "{0} градуса Цельсия",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} градус Цельсия",
                                few = "{0} градуса Цельсия",
                                many = "{0} градусов Цельсия",
                                other = "{0} градуса Цельсия",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} градусу Цельсия",
                                few = "{0} градусам Цельсия",
                                many = "{0} градусам Цельсия",
                                other = "{0} градуса Цельсия",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} градуса Цельсия",
                                few = "{0} градусов Цельсия",
                                many = "{0} градусов Цельсия",
                                other = "{0} градуса Цельсия",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} градусом Цельсия",
                                few = "{0} градусами Цельсия",
                                many = "{0} градусами Цельсия",
                                other = "{0} градуса Цельсия",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} градусе Цельсия",
                                few = "{0} градусах Цельсия",
                                many = "{0} градусах Цельсия",
                                other = "{0} градусах Цельсия",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} градусе Цельсия",
                                few = "{0} градусах Цельсия",
                                many = "{0} градусах Цельсия",
                                other = "{0} градуса Цельсия",
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
                                many = "{0} °C",
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
                                one = "{0} градус Фаренгейта",
                                few = "{0} градуса Фаренгейта",
                                many = "{0} градусов Фаренгейта",
                                other = "{0} градуса Фаренгейта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} градус Фаренгейта",
                                few = "{0} градуса Фаренгейта",
                                many = "{0} градусов Фаренгейта",
                                other = "{0} градуса Фаренгейта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} градусу Фаренгейта",
                                few = "{0} градусам Фаренгейта",
                                many = "{0} градусам Фаренгейта",
                                other = "{0} градуса Фаренгейта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} градуса Фаренгейта",
                                few = "{0} градусов Фаренгейта",
                                many = "{0} градусов Фаренгейта",
                                other = "{0} градуса Фаренгейта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} градусом Фаренгейта",
                                few = "{0} градусами Фаренгейта",
                                many = "{0} градусами Фаренгейта",
                                other = "{0} градуса Фаренгейта",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} градусе Фаренгейта",
                                few = "{0} градусах Фаренгейта",
                                many = "{0} градусах Фаренгейта",
                                other = "{0} градусах Фаренгейта",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} градусе Фаренгейта",
                                few = "{0} градусах Фаренгейта",
                                many = "{0} градусах Фаренгейта",
                                other = "{0} градусах Фаренгейта",
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
                                many = "{0} °F",
                                other = "{0} °F",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}°F",
                                few = "{0} °F",
                                many = "{0}°F",
                                other = "{0}°F",
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
                                one = "{0} кельвин",
                                few = "{0} кельвина",
                                many = "{0} кельвинов",
                                other = "{0} кельвина",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кельвин",
                                few = "{0} кельвина",
                                many = "{0} кельвинов",
                                other = "{0} кельвина",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кельвину",
                                few = "{0} кельвинам",
                                many = "{0} кельвинам",
                                other = "{0} кельвина",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кельвина",
                                few = "{0} кельвинов",
                                many = "{0} кельвинов",
                                other = "{0} кельвина",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кельвином",
                                few = "{0} кельвинами",
                                many = "{0} кельвинами",
                                other = "{0} кельвина",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кельвине",
                                few = "{0} кельвинах",
                                many = "{0} кельвинах",
                                other = "{0} кельвинах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} кельвине",
                                few = "{0} кельвинах",
                                many = "{0} кельвинах",
                                other = "{0} кельвина",
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
                                one = "{0} фунт-фут",
                                few = "{0} фунт-фута",
                                many = "{0} фунт-футов",
                                other = "{0} фунт-фута",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фнт-фт",
                                few = "{0} фнт-фт",
                                many = "{0} фнт-фт",
                                other = "{0} фнт-фт",
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
                                one = "{0} ньютон-метр",
                                few = "{0} ньютон-метра",
                                many = "{0} ньютон-метров",
                                other = "{0} ньютон-метра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ньютон-метр",
                                few = "{0} ньютон-метра",
                                many = "{0} ньютон-метров",
                                other = "{0} ньютон-метра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} ньютон-метру",
                                few = "{0} ньютон-метрам",
                                many = "{0} ньютон-метрам",
                                other = "{0} ньютон-метра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} ньютон-метра",
                                few = "{0} ньютон-метров",
                                many = "{0} ньютон-метров",
                                other = "{0} ньютон-метра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} ньютон-метром",
                                few = "{0} ньютон-метрами",
                                many = "{0} ньютон-метрами",
                                other = "{0} ньютон-метра",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} ньютон-метре",
                                few = "{0} ньютон-метрах",
                                many = "{0} ньютон-метрах",
                                other = "{0} ньютон-метра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Н⋅м",
                                few = "{0} Н⋅м",
                                many = "{0} Н⋅м",
                                other = "{0} Н⋅м",
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
                                one = "{0} кубический километр",
                                few = "{0} кубических километра",
                                many = "{0} кубических километров",
                                other = "{0} кубического километра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кубический километр",
                                few = "{0} кубических километра",
                                many = "{0} кубических километров",
                                other = "{0} кубического километра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кубическому километру",
                                few = "{0} кубическим километрам",
                                many = "{0} кубическим километрам",
                                other = "{0} кубического километра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кубического километра",
                                few = "{0} кубических километров",
                                many = "{0} кубических километров",
                                other = "{0} кубического километра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кубическим километром",
                                few = "{0} кубическими километрами",
                                many = "{0} кубическими километрами",
                                other = "{0} кубического километра",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} кубическом километре",
                                few = "{0} кубических километрах",
                                many = "{0} кубических километрах",
                                other = "{0} кубического километра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} км³",
                                few = "{0} км³",
                                many = "{0} км³",
                                other = "{0} км³",
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
                                one = "{0} кубический метр",
                                few = "{0} кубических метра",
                                many = "{0} кубических метров",
                                other = "{0} кубического метра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кубический метр",
                                few = "{0} кубических метра",
                                many = "{0} кубических метров",
                                other = "{0} кубического метра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кубическому метру",
                                few = "{0} кубическим метрам",
                                many = "{0} кубическим метрам",
                                other = "{0} кубического метра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кубического метра",
                                few = "{0} кубических метров",
                                many = "{0} кубических метров",
                                other = "{0} кубического метра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кубическим метром",
                                few = "{0} кубическими метрами",
                                many = "{0} кубическими метрами",
                                other = "{0} кубического метра",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} кубическом метре",
                                few = "{0} кубических метрах",
                                many = "{0} кубических метрах",
                                other = "{0} кубического метра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} м³",
                                few = "{0} м³",
                                many = "{0} м³",
                                other = "{0} м³",
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
                                one = "{0} кубический сантиметр",
                                few = "{0} кубических сантиметра",
                                many = "{0} кубических сантиметров",
                                other = "{0} кубического сантиметра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кубический сантиметр",
                                few = "{0} кубических сантиметра",
                                many = "{0} кубических сантиметров",
                                other = "{0} кубического сантиметра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кубическому сантиметру",
                                few = "{0} кубическим сантиметрам",
                                many = "{0} кубическим сантиметрам",
                                other = "{0} кубического сантиметра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кубического сантиметра",
                                few = "{0} кубических сантиметров",
                                many = "{0} кубических сантиметров",
                                other = "{0} кубического сантиметра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кубическим сантиметром",
                                few = "{0} кубическими сантиметрами",
                                many = "{0} кубическими сантиметрами",
                                other = "{0} кубического сантиметра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кубическом сантиметре",
                                few = "{0} кубических сантиметрах",
                                many = "{0} кубических сантиметрах",
                                other = "{0} кубических сантиметрах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} кубическом сантиметре",
                                few = "{0} кубических сантиметрах",
                                many = "{0} кубических сантиметрах",
                                other = "{0} кубического сантиметра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} см³",
                                few = "{0} см³",
                                many = "{0} см³",
                                other = "{0} см³",
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
                                one = "{0} кубическая миля",
                                few = "{0} кубические мили",
                                many = "{0} кубических миль",
                                other = "{0} кубической мили",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кубическую милю",
                                few = "{0} кубические мили",
                                many = "{0} кубических миль",
                                other = "{0} кубической мили",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кубической миле",
                                few = "{0} кубическим милям",
                                many = "{0} кубическим милям",
                                other = "{0} кубической мили",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кубической мили",
                                few = "{0} кубических миль",
                                many = "{0} кубических миль",
                                other = "{0} кубической мили",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кубической милей",
                                few = "{0} кубическими милями",
                                many = "{0} кубическими милями",
                                other = "{0} кубической мили",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кубической миле",
                                few = "{0} кубических милях",
                                many = "{0} кубических милях",
                                other = "{0} кубических милях",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} кубической миле",
                                few = "{0} кубических милях",
                                many = "{0} кубических милях",
                                other = "{0} кубических милях",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ми³",
                                few = "{0} ми³",
                                many = "{0} ми³",
                                other = "{0} ми³",
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
                                one = "{0} кубический ярд",
                                few = "{0} кубических ярда",
                                many = "{0} кубических ярдов",
                                other = "{0} кубического ярда",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ярд³",
                                few = "{0} ярд³",
                                many = "{0} ярд³",
                                other = "{0} ярд³",
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
                                one = "{0} кубический фут",
                                few = "{0} кубических фута",
                                many = "{0} кубических футов",
                                other = "{0} кубического фута",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кубический фут",
                                few = "{0} кубических фута",
                                many = "{0} кубических футов",
                                other = "{0} кубического фута",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кубическому футу",
                                few = "{0} кубическим футам",
                                many = "{0} кубическим футам",
                                other = "{0} кубического фута",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кубического фута",
                                few = "{0} кубических футов",
                                many = "{0} кубических футов",
                                other = "{0} кубического фута",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кубическим футом",
                                few = "{0} кубическими футами",
                                many = "{0} кубическими футами",
                                other = "{0} кубического фута",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кубическом футе",
                                few = "{0} кубических футах",
                                many = "{0} кубических футах",
                                other = "{0} кубических футах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} кубическом футе",
                                few = "{0} кубических футах",
                                many = "{0} кубических футах",
                                other = "{0} кубических футах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фт³",
                                few = "{0} фт³",
                                many = "{0} фт³",
                                other = "{0} фт³",
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
                                one = "{0} кубический дюйм",
                                few = "{0} кубических дюйма",
                                many = "{0} кубических дюймов",
                                other = "{0} кубического дюйма",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дюйм³",
                                few = "{0} дюйм³",
                                many = "{0} дюйм³",
                                other = "{0} дюйм³",
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
                                one = "{0} мегалитр",
                                few = "{0} мегалитра",
                                many = "{0} мегалитров",
                                other = "{0} мегалитра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мегалитр",
                                few = "{0} мегалитра",
                                many = "{0} мегалитров",
                                other = "{0} мегалитра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мегалитру",
                                few = "{0} мегалитрам",
                                many = "{0} мегалитрам",
                                other = "{0} мегалитра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мегалитра",
                                few = "{0} мегалитров",
                                many = "{0} мегалитров",
                                other = "{0} мегалитра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мегалитром",
                                few = "{0} мегалитрами",
                                many = "{0} мегалитрами",
                                other = "{0} мегалитра",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} мегалитре",
                                few = "{0} мегалитрах",
                                many = "{0} мегалитрах",
                                other = "{0} мегалитра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Мл",
                                few = "{0} Мл",
                                many = "{0} Мл",
                                other = "{0} Мл",
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
                                one = "{0} гектолитр",
                                few = "{0} гектолитра",
                                many = "{0} гектолитров",
                                other = "{0} гектолитра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гектолитр",
                                few = "{0} гектолитра",
                                many = "{0} гектолитров",
                                other = "{0} гектолитра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} гектолитру",
                                few = "{0} гектолитрам",
                                many = "{0} гектолитрам",
                                other = "{0} гектолитра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} гектолитра",
                                few = "{0} гектолитров",
                                many = "{0} гектолитров",
                                other = "{0} гектолитра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} гектолитром",
                                few = "{0} гектолитрами",
                                many = "{0} гектолитрами",
                                other = "{0} гектолитра",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} гектолитре",
                                few = "{0} гектолитрах",
                                many = "{0} гектолитрах",
                                other = "{0} гектолитра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гл",
                                few = "{0} гл",
                                many = "{0} гл",
                                other = "{0} гл",
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
                                one = "{0} литр",
                                few = "{0} литра",
                                many = "{0} литров",
                                other = "{0} литра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} литр",
                                few = "{0} литра",
                                many = "{0} литров",
                                other = "{0} литра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} литру",
                                few = "{0} литрам",
                                many = "{0} литрам",
                                other = "{0} литра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} литра",
                                few = "{0} литров",
                                many = "{0} литров",
                                other = "{0} литра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} литром",
                                few = "{0} литрами",
                                many = "{0} литрами",
                                other = "{0} литра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} литре",
                                few = "{0} литрах",
                                many = "{0} литрах",
                                other = "{0} литрах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} литре",
                                few = "{0} литрах",
                                many = "{0} литрах",
                                other = "{0} литра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} л",
                                few = "{0} л",
                                many = "{0} л",
                                other = "{0} л",
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
                                one = "{0} децилитр",
                                few = "{0} децилитра",
                                many = "{0} децилитров",
                                other = "{0} децилитра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} децилитр",
                                few = "{0} децилитра",
                                many = "{0} децилитров",
                                other = "{0} децилитра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} децилитру",
                                few = "{0} децилитрам",
                                many = "{0} децилитрам",
                                other = "{0} децилитра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} децилитра",
                                few = "{0} децилитров",
                                many = "{0} децилитров",
                                other = "{0} децилитра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} децилитром",
                                few = "{0} децилитрами",
                                many = "{0} децилитрами",
                                other = "{0} децилитра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} децилитре",
                                few = "{0} децилитрах",
                                many = "{0} децилитрах",
                                other = "{0} децилитрах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} децилитре",
                                few = "{0} децилитрах",
                                many = "{0} децилитрах",
                                other = "{0} децилитра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дл",
                                few = "{0} дл",
                                many = "{0} дл",
                                other = "{0} дл",
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
                                one = "{0} сантилитр",
                                few = "{0} сантилитра",
                                many = "{0} сантилитров",
                                other = "{0} сантилитра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} сантилитр",
                                few = "{0} сантилитра",
                                many = "{0} сантилитров",
                                other = "{0} сантилитра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} сантилитру",
                                few = "{0} сантилитрам",
                                many = "{0} сантилитрам",
                                other = "{0} сантилитра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} сантилитра",
                                few = "{0} сантилитров",
                                many = "{0} сантилитров",
                                other = "{0} сантилитра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} сантилитром",
                                few = "{0} сантилитрами",
                                many = "{0} сантилитрами",
                                other = "{0} сантилитра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} сантилитре",
                                few = "{0} сантилитрах",
                                many = "{0} сантилитрах",
                                other = "{0} сантилитрах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} сантилитре",
                                few = "{0} сантилитрах",
                                many = "{0} сантилитрах",
                                other = "{0} сантилитра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} сл",
                                few = "{0} сл",
                                many = "{0} сл",
                                other = "{0} сл",
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
                                one = "{0} миллилитр",
                                few = "{0} миллилитра",
                                many = "{0} миллилитров",
                                other = "{0} миллилитра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} миллилитр",
                                few = "{0} миллилитра",
                                many = "{0} миллилитров",
                                other = "{0} миллилитра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} миллилитру",
                                few = "{0} миллилитрам",
                                many = "{0} миллилитрам",
                                other = "{0} миллилитра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} миллилитра",
                                few = "{0} миллилитров",
                                many = "{0} миллилитров",
                                other = "{0} миллилитра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} миллилитром",
                                few = "{0} миллилитрами",
                                many = "{0} миллилитрами",
                                other = "{0} миллилитра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} миллилитре",
                                few = "{0} миллилитрах",
                                many = "{0} миллилитрах",
                                other = "{0} миллилитрах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} миллилитре",
                                few = "{0} миллилитрах",
                                many = "{0} миллилитрах",
                                other = "{0} миллилитра",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мл",
                                few = "{0} мл",
                                many = "{0} мл",
                                other = "{0} мл",
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
                                one = "{0} метрическая пинта",
                                few = "{0} метрические пинты",
                                many = "{0} метрических пинт",
                                other = "{0} метрической пинты",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} метрическую пинту",
                                few = "{0} метрические пинты",
                                many = "{0} метрических пинт",
                                other = "{0} метрической пинты",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} метрической пинте",
                                few = "{0} метрическим пинтам",
                                many = "{0} метрическим пинтам",
                                other = "{0} метрической пинты",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} метрической пинты",
                                few = "{0} метрических пинт",
                                many = "{0} метрических пинт",
                                other = "{0} метрической пинты",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} метрической пинтой",
                                few = "{0} метрическими пинтами",
                                many = "{0} метрическими пинтами",
                                other = "{0} метрической пинты",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} метрической пинте",
                                few = "{0} метрических пинтах",
                                many = "{0} метрических пинтах",
                                other = "{0} метрической пинты",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мпт",
                                few = "{0} мпт",
                                many = "{0} мпт",
                                other = "{0} мпт",
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
                                one = "{0} метрическая чашка",
                                few = "{0} метрические чашки",
                                many = "{0} метрических чашек",
                                other = "{0} метрической чашки",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} метрическую чашку",
                                few = "{0} метрические чашки",
                                many = "{0} метрических чашек",
                                other = "{0} метрической чашки",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} метрической чашке",
                                few = "{0} метрическим чашкам",
                                many = "{0} метрическим чашкам",
                                other = "{0} метрической чашки",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} метрической чашки",
                                few = "{0} метрических чашек",
                                many = "{0} метрических чашек",
                                other = "{0} метрической чашки",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} метрической чашкой",
                                few = "{0} метрическими чашками",
                                many = "{0} метрическими чашками",
                                other = "{0} метрической чашки",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} метрической чашке",
                                few = "{0} метрических чашках",
                                many = "{0} метрических чашках",
                                other = "{0} метрической чашки",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} м. чаш.",
                                few = "{0} м. чаш.",
                                many = "{0} м. чаш.",
                                other = "{0} м. чаш.",
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
                                one = "{0} акрофут",
                                few = "{0} акрофута",
                                many = "{0} акрофутов",
                                other = "{0} акрофута",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} акрофут",
                                few = "{0} акрофут.",
                                many = "{0} акрофут.",
                                other = "{0} акрофут.",
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
                                one = "{0} бушель",
                                few = "{0} бушеля",
                                many = "{0} бушелей",
                                other = "{0} бушеля",
                            )
                        ),
                    )
                ),
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
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} амер. галлон",
                                few = "{0} амер. галлона",
                                many = "{0} амер. галлонов",
                                other = "{0} амер. галлона",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} амер. галлон",
                                few = "{0} амер. галлона",
                                many = "{0} амер. галлонов",
                                other = "{0} амер. галлона",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} амер. галлону",
                                few = "{0} амер. галлонам",
                                many = "{0} амер. галлонам",
                                other = "{0} амер. галлона",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} амер. галлона",
                                few = "{0} амер. галлонов",
                                many = "{0} амер. галлонов",
                                other = "{0} амер. галлона",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} амер. галлоном",
                                few = "{0} амер. галлонами",
                                many = "{0} амер. галлонами",
                                other = "{0} амер. галлона",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} амер. галлоне",
                                few = "{0} амер. галлонах",
                                many = "{0} амер. галлонах",
                                other = "{0} амер. галлонах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} амер. галлоне",
                                few = "{0} амер. галлонах",
                                many = "{0} амер. галлонах",
                                other = "{0} амер. галлонах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ам. гал.",
                                few = "{0} ам. гал.",
                                many = "{0} ам. гал.",
                                other = "{0} ам. гал.",
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
                                one = "{0} имп. галлон",
                                few = "{0} имп. галлона",
                                many = "{0} имп. галлонов",
                                other = "{0} имп. галлона",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} имп. галлон",
                                few = "{0} имп. галлона",
                                many = "{0} имп. галлонов",
                                other = "{0} имп. галлона",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} имп. галлону",
                                few = "{0} имп. галлонам",
                                many = "{0} имп. галлонам",
                                other = "{0} имп. галлона",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} имп. галлона",
                                few = "{0} имп. галлонов",
                                many = "{0} имп. галлонов",
                                other = "{0} имп. галлона",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} имп. галлоном",
                                few = "{0} имп. галлонами",
                                many = "{0} имп. галлонами",
                                other = "{0} имп. галлона",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} имп. галлоне",
                                few = "{0} имп. галлонах",
                                many = "{0} имп. галлонах",
                                other = "{0} имп. галлонах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} имп. галлоне",
                                few = "{0} имп. галлонах",
                                many = "{0} имп. галлонах",
                                other = "{0} имп. галлонах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} имп. гал.",
                                few = "{0} имп. гал.",
                                many = "{0} имп. гал.",
                                other = "{0} имп. гал.",
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
                                one = "{0} амер. кварта",
                                few = "{0} амер. кварты",
                                many = "{0} амер. кварт",
                                other = "{0} амер. кварты",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} амер. кварту",
                                few = "{0} амер. кварты",
                                many = "{0} амер. кварт",
                                other = "{0} амер. кварты",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} амер. кварте",
                                few = "{0} амер. квартам",
                                many = "{0} амер. квартам",
                                other = "{0} амер. кварты",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} амер. кварты",
                                few = "{0} амер. кварт",
                                many = "{0} амер. кварт",
                                other = "{0} амер. кварты",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} амер. квартой",
                                few = "{0} амер. квартами",
                                many = "{0} амер. квартами",
                                other = "{0} амер. кварты",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} амер. кварте",
                                few = "{0} амер. квартах",
                                many = "{0} амер. квартах",
                                other = "{0} амер. квартах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} амер. кварте",
                                few = "{0} амер. квартах",
                                many = "{0} амер. квартах",
                                other = "{0} амер. квартах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ам. кварт.",
                                few = "{0} ам. кварт.",
                                many = "{0} ам. кварт.",
                                other = "{0} ам. кварт.",
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
                                one = "{0} амер. пинта",
                                few = "{0} амер. пинты",
                                many = "{0} амер. пинт",
                                other = "{0} амер. пинты",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} амер. пинту",
                                few = "{0} амер. пинты",
                                many = "{0} амер. пинт",
                                other = "{0} амер. пинты",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} амер. пинте",
                                few = "{0} амер. пинтам",
                                many = "{0} амер. пинтам",
                                other = "{0} амер. пинты",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} амер. пинты",
                                few = "{0} амер. пинт",
                                many = "{0} амер. пинт",
                                other = "{0} амер. пинты",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} амер. пинтой",
                                few = "{0} амер. пинтами",
                                many = "{0} амер. пинтами",
                                other = "{0} амер. пинты",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} амер. пинте",
                                few = "{0} амер. пинтах",
                                many = "{0} амер. пинтах",
                                other = "{0} амер. пинтах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} амер. пинте",
                                few = "{0} амер. пинтах",
                                many = "{0} амер. пинтах",
                                other = "{0} амер. пинтах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ам. пинт.",
                                few = "{0} ам. пинт.",
                                many = "{0} ам. пинт.",
                                other = "{0} ам. пинт.",
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
                                one = "{0} амер. чашка",
                                few = "{0} амер. чашки",
                                many = "{0} амер. чашек",
                                other = "{0} амер. чашки",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} амер. чашку",
                                few = "{0} амер. чашки",
                                many = "{0} амер. чашек",
                                other = "{0} амер. чашки",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} амер. чашке",
                                few = "{0} амер. чашкам",
                                many = "{0} амер. чашкам",
                                other = "{0} амер. чашки",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} амер. чашки",
                                few = "{0} амер. чашек",
                                many = "{0} амер. чашек",
                                other = "{0} амер. чашки",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} амер. чашкой",
                                few = "{0} амер. чашками",
                                many = "{0} амер. чашками",
                                other = "{0} амер. чашки",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} амер. чашке",
                                few = "{0} амер. чашках",
                                many = "{0} амер. чашках",
                                other = "{0} амер. чашках",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} амер. чашке",
                                few = "{0} амер. чашках",
                                many = "{0} амер. чашках",
                                other = "{0} амер. чашках",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ам. чаш.",
                                few = "{0} ам. чаш.",
                                many = "{0} ам. чаш.",
                                other = "{0} ам. чаш.",
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
                                one = "{0} амер. жидкая унция",
                                few = "{0} амер. жидкие унции",
                                many = "{0} амер. жидких унций",
                                other = "{0} амер. жидкой унции",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} амер. жидкую унцию",
                                few = "{0} амер. жидкие унции",
                                many = "{0} амер. жидких унций",
                                other = "{0} амер. жидкой унции",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} амер. жидкой унции",
                                few = "{0} амер. жидким унциям",
                                many = "{0} амер. жидким унциям",
                                other = "{0} амер. жидкой унции",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} амер. жидкой унции",
                                few = "{0} амер. жидких унций",
                                many = "{0} амер. жидких унций",
                                other = "{0} амер. жидкой унции",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} амер. жидкой унцией",
                                few = "{0} амер. жидкими унциями",
                                many = "{0} амер. жидкими унциями",
                                other = "{0} амер. жидкой унции",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} амер. жидкой унции",
                                few = "{0} амер. жидких унциях",
                                many = "{0} амер. жидких унциях",
                                other = "{0} амер. жидких унциях",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} амер. жидкой унции",
                                few = "{0} амер. жидких унциях",
                                many = "{0} амер. жидких унциях",
                                other = "{0} амер. жидких унциях",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ам. жидк. унц.",
                                few = "{0} ам. жидк. унц.",
                                many = "{0} ам. жидк. унц.",
                                other = "{0} ам. жидк. унц.",
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
                                one = "{0} имп. жидкая унция",
                                few = "{0} имп. жидкие унции",
                                many = "{0} имп. жидких унций",
                                other = "{0} имп. жидкой унции",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} имп. жидкую унцию",
                                few = "{0} имп. жидкие унции",
                                many = "{0} имп. жидких унций",
                                other = "{0} имп. жидкой унции",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} имп. жидкой унции",
                                few = "{0} имп. жидким унциям",
                                many = "{0} имп. жидким унциям",
                                other = "{0} имп. жидкой унции",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} имп. жидкой унции",
                                few = "{0} имп. жидких унций",
                                many = "{0} имп. жидких унций",
                                other = "{0} имп. жидкой унции",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} имп. жидкой унцией",
                                few = "{0} имп. жидкими унциями",
                                many = "{0} имп. жидкими унциями",
                                other = "{0} имп. жидкой унции",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} имп. жидкой унции",
                                few = "{0} имп. жидких унциях",
                                many = "{0} имп. жидких унциях",
                                other = "{0} имп. жидких унциях",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} имп. жидкой унции",
                                few = "{0} имп. жидких унциях",
                                many = "{0} имп. жидких унциях",
                                other = "{0} имп. жидких унциях",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} имп. жидк. унц.",
                                few = "{0} имп. жидк. унц.",
                                many = "{0} имп. жидк. унц.",
                                other = "{0} имп. жидк. унц.",
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
                                one = "{0} столовая ложка",
                                few = "{0} столовые ложки",
                                many = "{0} столовых ложек",
                                other = "{0} столовой ложки",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} столовую ложку",
                                few = "{0} столовые ложки",
                                many = "{0} столовых ложек",
                                other = "{0} столовой ложки",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} столовой ложке",
                                few = "{0} столовым ложкам",
                                many = "{0} столовым ложкам",
                                other = "{0} столовой ложки",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} столовой ложки",
                                few = "{0} столовых ложек",
                                many = "{0} столовых ложек",
                                other = "{0} столовой ложки",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} столовой ложкой",
                                few = "{0} столовыми ложками",
                                many = "{0} столовыми ложками",
                                other = "{0} столовой ложки",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} столовой ложке",
                                few = "{0} столовых ложках",
                                many = "{0} столовых ложках",
                                other = "{0} столовых ложках",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} столовой ложке",
                                few = "{0} столовых ложках",
                                many = "{0} столовых ложках",
                                other = "{0} столовых ложках",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ст. л.",
                                few = "{0} ст. л.",
                                many = "{0} ст. л.",
                                other = "{0} ст. л.",
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
                                one = "{0} чайная ложка",
                                few = "{0} чайные ложки",
                                many = "{0} чайных ложек",
                                other = "{0} чайной ложки",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} чайную ложку",
                                few = "{0} чайные ложки",
                                many = "{0} чайных ложек",
                                other = "{0} чайной ложки",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} чайной ложке",
                                few = "{0} чайным ложкам",
                                many = "{0} чайным ложкам",
                                other = "{0} чайной ложки",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} чайной ложки",
                                few = "{0} чайных ложек",
                                many = "{0} чайных ложек",
                                other = "{0} чайной ложки",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} чайной ложкой",
                                few = "{0} чайными ложками",
                                many = "{0} чайными ложками",
                                other = "{0} чайной ложки",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} чайной ложке",
                                few = "{0} чайных ложках",
                                many = "{0} чайных ложках",
                                other = "{0} чайных ложках",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} чайной ложке",
                                few = "{0} чайных ложках",
                                many = "{0} чайных ложках",
                                other = "{0} чайных ложках",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ч. л.",
                                few = "{0} ч. л.",
                                many = "{0} ч. л.",
                                other = "{0} ч. л.",
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
                                one = "{0} баррель",
                                few = "{0} барреля",
                                many = "{0} баррелей",
                                other = "{0} барреля",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} барр.",
                                few = "{0} барр.",
                                many = "{0} барр.",
                                other = "{0} барр.",
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
                                one = "{0} десертная ложка",
                                few = "{0} десертные ложки",
                                many = "{0} десертных ложек",
                                other = "{0} десертной ложки",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} десертную ложку",
                                few = "{0} десертные ложки",
                                many = "{0} десертных ложек",
                                other = "{0} десертной ложки",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} десертной ложке",
                                few = "{0} десертным ложкам",
                                many = "{0} десертным ложкам",
                                other = "{0} десертной ложки",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} десертной ложки",
                                few = "{0} десертных ложек",
                                many = "{0} десертных ложек",
                                other = "{0} десертной ложки",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} десертной ложкой",
                                few = "{0} десертными ложками",
                                many = "{0} десертными ложками",
                                other = "{0} десертной ложки",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} десертной ложке",
                                few = "{0} десертных ложках",
                                many = "{0} десертных ложках",
                                other = "{0} десертных ложках",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} десертной ложке",
                                few = "{0} десертных ложках",
                                many = "{0} десертных ложках",
                                other = "{0} десертных ложках",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дес. л.",
                                few = "{0} дес. л.",
                                many = "{0} дес. л.",
                                other = "{0} дес. л.",
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
                                one = "{0} имп. десертная ложка",
                                few = "{0} имп. десертные ложки",
                                many = "{0} имп. десертных ложек",
                                other = "{0} имп. десертной ложки",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} имп. десертную ложку",
                                few = "{0} имп. десертные ложки",
                                many = "{0} имп. десертных ложек",
                                other = "{0} имп. десертной ложки",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} имп. десертной ложке",
                                few = "{0} имп. десертным ложкам",
                                many = "{0} имп. десертным ложкам",
                                other = "{0} имп. десертной ложки",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} имп. десертной ложки",
                                few = "{0} имп. десертных ложек",
                                many = "{0} имп. десертных ложек",
                                other = "{0} имп. десертной ложки",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} имп. десертной ложкой",
                                few = "{0} имп. десертными ложками",
                                many = "{0} имп. десертными ложками",
                                other = "{0} имп. десертной ложки",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} имп. десертной ложке",
                                few = "{0} имп. десертных ложках",
                                many = "{0} имп. десертных ложках",
                                other = "{0} имп. десертных ложках",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} имп. десертной ложке",
                                few = "{0} имп. десертных ложках",
                                many = "{0} имп. десертных ложках",
                                other = "{0} имп. десертных ложках",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} имп. дес. л.",
                                few = "{0} имп. дес. л.",
                                many = "{0} имп. дес. л.",
                                other = "{0} имп. дес. л.",
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
                                one = "{0} капля",
                                few = "{0} капли",
                                many = "{0} капель",
                                other = "{0} капли",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} каплю",
                                few = "{0} капли",
                                many = "{0} капель",
                                other = "{0} капли",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} капле",
                                few = "{0} каплям",
                                many = "{0} каплям",
                                other = "{0} капли",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} капли",
                                few = "{0} капель",
                                many = "{0} капель",
                                other = "{0} капли",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} каплей",
                                few = "{0} каплями",
                                many = "{0} каплями",
                                other = "{0} капли",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} капле",
                                few = "{0} каплях",
                                many = "{0} каплях",
                                other = "{0} каплях",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} капле",
                                few = "{0} каплях",
                                many = "{0} каплях",
                                other = "{0} каплях",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кап.",
                                few = "{0} кап.",
                                many = "{0} кап.",
                                other = "{0} кап.",
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
                                one = "{0} жидкая драхма",
                                few = "{0} жидкие драхмы",
                                many = "{0} жидких драхм",
                                other = "{0} жидкой драхмы",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} жидкую драхму",
                                few = "{0} жидкие драхмы",
                                many = "{0} жидких драхм",
                                other = "{0} жидкой драхмы",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} жидкой драхме",
                                few = "{0} жидким драхмам",
                                many = "{0} жидким драхмам",
                                other = "{0} жидкой драхмы",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} жидкой драхмы",
                                few = "{0} жидких драхм",
                                many = "{0} жидких драхм",
                                other = "{0} жидкой драхмы",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} жидкой драхмой",
                                few = "{0} жидкими драхмами",
                                many = "{0} жидкими драхмами",
                                other = "{0} жидкой драхмы",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} жидкой драхме",
                                few = "{0} жидких драхмах",
                                many = "{0} жидких драхмах",
                                other = "{0} жидких драхмах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} жидкой драхме",
                                few = "{0} жидких драхмах",
                                many = "{0} жидких драхмах",
                                other = "{0} жидких драхмах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} жидк. др.",
                                few = "{0} жидк. др.",
                                many = "{0} жидк. др.",
                                other = "{0} жидк. др.",
                            )
                        ),
                    )
                ),
            )
        override val volumeJigger
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} джиггер",
                                few = "{0} джиггера",
                                many = "{0} джиггеров",
                                other = "{0} джиггера",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} джиггер",
                                few = "{0} джиггера",
                                many = "{0} джиггеров",
                                other = "{0} джиггера",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} джиггеру",
                                few = "{0} джиггерам",
                                many = "{0} джиггерам",
                                other = "{0} джиггера",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} джиггера",
                                few = "{0} джиггеров",
                                many = "{0} джиггеров",
                                other = "{0} джиггера",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} джиггером",
                                few = "{0} джиггерами",
                                many = "{0} джиггерами",
                                other = "{0} джиггера",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} джиггере",
                                few = "{0} джиггерах",
                                many = "{0} джиггерах",
                                other = "{0} джиггерах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} джиггере",
                                few = "{0} джиггерах",
                                many = "{0} джиггерах",
                                other = "{0} джиггерах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} джиггер",
                                few = "{0} джиггера",
                                many = "{0} джиггеров",
                                other = "{0} джиггера",
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
                                one = "{0} щепотка",
                                few = "{0} щепотки",
                                many = "{0} щепоток",
                                other = "{0} щепотки",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} щепотку",
                                few = "{0} щепотки",
                                many = "{0} щепоток",
                                other = "{0} щепотки",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} щепотке",
                                few = "{0} щепоткам",
                                many = "{0} щепоткам",
                                other = "{0} щепотки",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} щепотки",
                                few = "{0} щепоток",
                                many = "{0} щепоток",
                                other = "{0} щепотки",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} щепоткой",
                                few = "{0} щепотками",
                                many = "{0} щепотками",
                                other = "{0} щепотки",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} щепотке",
                                few = "{0} щепотках",
                                many = "{0} щепотках",
                                other = "{0} щепотках",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} щепотке",
                                few = "{0} щепотках",
                                many = "{0} щепотках",
                                other = "{0} щепотках",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} щепот.",
                                few = "{0} щепот.",
                                many = "{0} щепот.",
                                other = "{0} щепот.",
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
                                one = "{0} имп. кварта",
                                few = "{0} имп. кварты",
                                many = "{0} имп. кварт",
                                other = "{0} имп. кварты",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} имп. кварту",
                                few = "{0} имп. кварты",
                                many = "{0} имп. кварт",
                                other = "{0} имп. кварты",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} имп. кварте",
                                few = "{0} имп. квартам",
                                many = "{0} имп. квартам",
                                other = "{0} имп. кварты",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} имп. кварты",
                                few = "{0} имп. кварт",
                                many = "{0} имп. кварт",
                                other = "{0} имп. кварты",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} имп. квартой",
                                few = "{0} имп. квартами",
                                many = "{0} имп. квартами",
                                other = "{0} имп. кварты",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} имп. кварте",
                                few = "{0} имп. квартах",
                                many = "{0} имп. квартах",
                                other = "{0} имп. квартах",
                            )
                        ),
                        prepositional = PluralPattern(
                            Plurals(
                                one = "{0} имп. кварте",
                                few = "{0} имп. квартах",
                                many = "{0} имп. квартах",
                                other = "{0} имп. квартах",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} имп. кварт.",
                                few = "{0} имп. кварт.",
                                many = "{0} имп. кварт.",
                                other = "{0} имп. кварт.",
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
            approximatelySignSymbol = "≈",
            nanSymbol = "не число",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
