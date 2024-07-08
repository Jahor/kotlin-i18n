package info.leonenko.i18n.languages

import info.leonenko.i18n.*
import info.leonenko.i18n.rbnf.RBNFUk

val Language.Companion.uk
    get() = languageUk
private val languageUk = LanguageUk()

open class LanguageUk internal constructor() : Language {
    override val code: String = "uk"
    override val language: String = "uk"
    override val script: String? = null
    override val territory: String? = null
    override val rbnf: RBNFUk
        get() = RBNFUk(this)
    override val pluralFormChooser = PluralFormChooser.Expression(
        one = { (v == 0) && ((i % 10) == 1L) && ((i % 100) != 11L) },
        few = { (v == 0) && ((i % 10) in 2L..4L) && ((i % 100) !in 12L..14L) },
        many = { (v == 0) && ((i % 10) == 0L) || (v == 0) && ((i % 10) in 5L..9L) || (v == 0) && ((i % 100) in 11L..14L) },
    )

    open class EllipsisLanguage internal constructor() : info.leonenko.i18n.EllipsisLanguage {
        override val formatter = EllipsisFormatter.Default
        override val wordFormatter: EllipsisFormatter = EllipsisFormatter(
            final = "{0}…",
            initial = "…{0}",
            medial = "{0}…{1}",
        )
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    open class ListLanguage internal constructor(val ellipsis: EllipsisLanguage) : info.leonenko.i18n.ListLanguage {
        override val formatter: ListFormatter = MultiLengthPatternedListFormatter(
            long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} і {1}", two = "{0} і {1}", ellipsis = ellipsis.wordFormatter),
            narrow = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, {1}", two = "{0}, {1}", ellipsis = ellipsis.wordFormatter),
        )
        override val orFormatter: ListFormatter =
            MultiLengthPatternedListFormatter(long = PatternedListFormatter(start = "{0}, {1}", middle = "{0}, {1}", end = "{0} або {1}", two = "{0} або {1}", ellipsis = ellipsis.wordFormatter))
        override val unitFormatter: ListFormatter = formatter
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}g",
                                few = "{0}g",
                                many = "{0}g",
                                other = "{0}g",
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
                                one = "{0} метр на секунду у квадраті",
                                few = "{0} метри на секунду у квадраті",
                                many = "{0} метрів на секунду у квадраті",
                                other = "{0} метра на секунду у квадраті",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} метр на секунду у квадраті",
                                few = "{0} метри на секунду у квадраті",
                                many = "{0} метрів на секунду у квадраті",
                                other = "{0} метра на секунду у квадраті",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} метру на секунду у квадраті",
                                few = "{0} метру на секунду у квадраті",
                                many = "{0} метрам на секунду у квадраті",
                                other = "{0} метра на секунду у квадраті",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} метра на секунду у квадраті",
                                few = "{0} метрів на секунду у квадраті",
                                many = "{0} метрів на секунду у квадраті",
                                other = "{0} метра на секунду у квадраті",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} метром на секунду у квадраті",
                                few = "{0} метром на секунду у квадраті",
                                many = "{0} метрами на секунду у квадраті",
                                other = "{0} метра на секунду у квадраті",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} метрі на секунду у квадраті",
                                few = "{0} метрах на секунду у квадраті",
                                many = "{0} метрах на секунду у квадраті",
                                other = "{0} метра на секунду у квадраті",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}м/с²",
                                few = "{0}м/с²",
                                many = "{0}м/с²",
                                other = "{0}м/с²",
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
                                one = "{0} оберт",
                                few = "{0} оберти",
                                many = "{0} обертів",
                                other = "{0} оберта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} оберт",
                                few = "{0} оберти",
                                many = "{0} обертів",
                                other = "{0} оберта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} оберту",
                                few = "{0} обертам",
                                many = "{0} обертам",
                                other = "{0} оберта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} оберта",
                                few = "{0} обертів",
                                many = "{0} обертів",
                                other = "{0} оберта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} обертом",
                                few = "{0} обертами",
                                many = "{0} обертами",
                                other = "{0} оберта",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} оберті",
                                few = "{0} обертах",
                                many = "{0} обертах",
                                other = "{0} оберта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} об",
                                few = "{0} об",
                                many = "{0} об",
                                other = "{0} об",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}об",
                                few = "{0}об",
                                many = "{0}об",
                                other = "{0}об",
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
                                one = "{0} радіан",
                                few = "{0} радіани",
                                many = "{0} радіанів",
                                other = "{0} радіана",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} радіан",
                                few = "{0} радіани",
                                many = "{0} радіанів",
                                other = "{0} радіана",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} радіану",
                                few = "{0} радіанам",
                                many = "{0} радіанам",
                                other = "{0} радіана",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} радіана",
                                few = "{0} радіанів",
                                many = "{0} радіанів",
                                other = "{0} радіана",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} радіаном",
                                few = "{0} радіанами",
                                many = "{0} радіанами",
                                other = "{0} радіана",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} радіані",
                                few = "{0} радіанах",
                                many = "{0} радіанах",
                                other = "{0} радіана",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} рад.",
                                few = "{0} рад.",
                                many = "{0} рад.",
                                other = "{0} рад.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}рад.",
                                few = "{0}рад.",
                                many = "{0}рад.",
                                other = "{0}рад.",
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
                                few = "{0} градуси",
                                many = "{0} градусів",
                                other = "{0} градуса",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} градус",
                                few = "{0} градуси",
                                many = "{0} градусів",
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
                                few = "{0} градусів",
                                many = "{0} градусів",
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
                                one = "{0} градусі",
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
                                one = "{0} кутова мінута",
                                few = "{0} кутові мінути",
                                many = "{0} кутових мінут",
                                other = "{0} кутової мінути",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кутову мінуту",
                                few = "{0} кутові мінути",
                                many = "{0} кутових мінут",
                                other = "{0} кутової мінути",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кутовій мінуті",
                                few = "{0} кутовим мінутам",
                                many = "{0} кутовим мінутам",
                                other = "{0} кутової мінути",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кутової мінути",
                                few = "{0} кутових мінут",
                                many = "{0} кутових мінут",
                                other = "{0} кутової мінути",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кутовою мінутою",
                                few = "{0} кутовими мінутами",
                                many = "{0} кутовими мінутами",
                                other = "{0} кутової мінути",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кутовій мінуті",
                                few = "{0} кутових мінутах",
                                many = "{0} кутових мінутах",
                                other = "{0} кутової мінути",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кут. мін.",
                                few = "{0} кут. мін.",
                                many = "{0} кут. мін.",
                                other = "{0} кут. мін.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}′",
                                few = "{0}′",
                                many = "{0}′",
                                other = "{0}′",
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
                                one = "{0} кутова секунда",
                                few = "{0} кутові секунди",
                                many = "{0} кутових секунд",
                                other = "{0} кутової секунди",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кутову секунду",
                                few = "{0} кутові секунди",
                                many = "{0} кутових секунд",
                                other = "{0} кутової секунди",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кутовій секунді",
                                few = "{0} кутовим секундам",
                                many = "{0} кутовим секундам",
                                other = "{0} кутової секунди",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кутової секунди",
                                few = "{0} кутових секунд",
                                many = "{0} кутових секунд",
                                other = "{0} кутової секунди",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кутовою секундою",
                                few = "{0} кутовими секундами",
                                many = "{0} кутовими секундами",
                                other = "{0} кутової секунди",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кутовій секунді",
                                few = "{0} кутових секундах",
                                many = "{0} кутових секундах",
                                other = "{0} кутової секунди",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кут. сек.",
                                few = "{0} кут. сек.",
                                many = "{0} кут. сек.",
                                other = "{0} кут. сек.",
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
        override val areaSquareKilometer
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} квадратний кілометр",
                                few = "{0} квадратні кілометри",
                                many = "{0} квадратних кілометрів",
                                other = "{0} квадратного кілометра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} квадратний кілометр",
                                few = "{0} квадратні кілометри",
                                many = "{0} квадратних кілометрів",
                                other = "{0} квадратного кілометра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} квадратному кілометру",
                                few = "{0} квадратним кілометрам",
                                many = "{0} квадратним кілометрам",
                                other = "{0} квадратного кілометра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} квадратного кілометра",
                                few = "{0} квадратних кілометрів",
                                many = "{0} квадратних кілометрів",
                                other = "{0} квадратного кілометра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} квадратним кілометром",
                                few = "{0} квадратними кілометрами",
                                many = "{0} квадратними кілометрами",
                                other = "{0} квадратного кілометра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} квадратному кілометрі",
                                few = "{0} квадратних кілометрах",
                                many = "{0} квадратних кілометрах",
                                other = "{0} квадратного кілометра",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}км²",
                                few = "{0}км²",
                                many = "{0}км²",
                                other = "{0}км²",
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
                                few = "{0} гектари",
                                many = "{0} гектарів",
                                other = "{0} гектара",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гектар",
                                few = "{0} гектари",
                                many = "{0} гектарів",
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
                                few = "{0} гектарів",
                                many = "{0} гектарів",
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
                                one = "{0} гектарі",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}га",
                                few = "{0}га",
                                many = "{0}га",
                                other = "{0}га",
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
                                one = "{0} квадратний метр",
                                few = "{0} квадратні метри",
                                many = "{0} квадратних метрів",
                                other = "{0} квадратного метра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} квадратний метр",
                                few = "{0} квадратні метри",
                                many = "{0} квадратних метрів",
                                other = "{0} квадратного метра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} квадратному метру",
                                few = "{0} квадратним метрам",
                                many = "{0} квадратним метрам",
                                other = "{0} квадратного метра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} квадратного метра",
                                few = "{0} квадратних метрів",
                                many = "{0} квадратних метрів",
                                other = "{0} квадратного метра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} квадратним метром",
                                few = "{0} квадратними метрами",
                                many = "{0} квадратними метрами",
                                other = "{0} квадратного метра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} квадратному метрі",
                                few = "{0} квадратних метрах",
                                many = "{0} квадратних метрах",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}м²",
                                few = "{0}м²",
                                many = "{0}м²",
                                other = "{0}м²",
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
                                one = "{0} квадратний сантиметр",
                                few = "{0} квадратні сантиметри",
                                many = "{0} квадратних сантиметрів",
                                other = "{0} квадратного сантиметра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} квадратний сантиметр",
                                few = "{0} квадратні сантиметри",
                                many = "{0} квадратних сантиметрів",
                                other = "{0} квадратного сантиметра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} квадратному сантиметру",
                                few = "{0} квадратним сантиметрам",
                                many = "{0} квадратним сантиметрам",
                                other = "{0} квадратного сантиметра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} квадратного сантиметра",
                                few = "{0} квадратних сантиметрів",
                                many = "{0} квадратних сантиметрів",
                                other = "{0} квадратного сантиметра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} квадратним сантиметром",
                                few = "{0} квадратними сантиметрами",
                                many = "{0} квадратними сантиметрами",
                                other = "{0} квадратного сантиметра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} квадратному сантиметрі",
                                few = "{0} квадратних сантиметрах",
                                many = "{0} квадратних сантиметрах",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}см²",
                                few = "{0}см²",
                                many = "{0}см²",
                                other = "{0}см²",
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
                                one = "{0} квадратна миля",
                                few = "{0} квадратні милі",
                                many = "{0} квадратних миль",
                                other = "{0} квадратної милі",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} миля²",
                                few = "{0} милі²",
                                many = "{0} миль²",
                                other = "{0} милі²",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мл²",
                                few = "{0}мл²",
                                many = "{0}мл²",
                                other = "{0}мл²",
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
                                one = "{0} акр",
                                few = "{0} акри",
                                many = "{0} акрів",
                                other = "{0} акра",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}акр",
                                few = "{0}акр",
                                many = "{0}акр",
                                other = "{0}акр",
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
                                one = "{0} квадратний ярд",
                                few = "{0} квадратні ярди",
                                many = "{0} квадратних ярдів",
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
                                few = "{0} ярди²",
                                many = "{0} ярдів²",
                                other = "{0} ярда²",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ярд²",
                                few = "{0}ярд²",
                                many = "{0}ярд²",
                                other = "{0}ярд²",
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
                                one = "{0} квадратний фут",
                                few = "{0} квадратні фути",
                                many = "{0} квадратних футів",
                                other = "{0} квадратного фута",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фут²",
                                few = "{0} фути²",
                                many = "{0} футів²",
                                other = "{0} фута²",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}фт²",
                                few = "{0}фт²",
                                many = "{0}фт²",
                                other = "{0}фт²",
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
                                one = "{0} квадратний дюйм",
                                few = "{0} квадратні дюйми",
                                many = "{0} квадратних дюймів",
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
                                few = "{0} дюйми²",
                                many = "{0} дюймів²",
                                other = "{0} дюйма²",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}дюйм²",
                                few = "{0}дюйм²",
                                many = "{0}дюйм²",
                                other = "{0}дюйм²",
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
                                one = "{0} дунам",
                                few = "{0} дунами",
                                many = "{0} дунамів",
                                other = "{0} дунама",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}дун.",
                                few = "{0}дун.",
                                many = "{0}дун.",
                                other = "{0}дун.",
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
                                few = "{0} карати",
                                many = "{0} каратів",
                                other = "{0} карата",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} карат",
                                few = "{0} карати",
                                many = "{0} каратів",
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
                                few = "{0} каратів",
                                many = "{0} каратів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} караті",
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
                                one = "{0} кар.",
                                few = "{0} кар.",
                                many = "{0} кар.",
                                other = "{0} кар.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кар.",
                                few = "{0}кар.",
                                many = "{0}кар.",
                                other = "{0}кар.",
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
                                one = "{0} мільйонна доля",
                                few = "{0} мільйонні долі",
                                many = "{0} мільйонних доль",
                                other = "{0} мільйонної долі",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мільйонну долю",
                                few = "{0} мільйонні долі",
                                many = "{0} мільйонних доль",
                                other = "{0} мільйонної долі",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мільйонній долі",
                                few = "{0} мільйонним долям",
                                many = "{0} мільйонним долям",
                                other = "{0} мільйонної долі",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мільйонної долі",
                                few = "{0} мільйонних доль",
                                many = "{0} мільйонних доль",
                                other = "{0} мільйонної долі",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мільйонною долею",
                                few = "{0} мільйонними долями",
                                many = "{0} мільйонними долями",
                                other = "{0} мільйонної долі",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мільйонній долі",
                                few = "{0} мільйонних долях",
                                many = "{0} мільйонних долях",
                                other = "{0} мільйонної долі",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} м. д.",
                                few = "{0} м. д.",
                                many = "{0} м. д.",
                                other = "{0} м. д.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}м.д.",
                                few = "{0}м.д.",
                                many = "{0}м.д.",
                                other = "{0}м.д.",
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
                                one = "{0} відсоток",
                                few = "{0} відсотки",
                                many = "{0} відсотків",
                                other = "{0} відсотка",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} відсоток",
                                few = "{0} відсотки",
                                many = "{0} відсотків",
                                other = "{0} відсотка",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} відсотку",
                                few = "{0} відсоткам",
                                many = "{0} відсоткам",
                                other = "{0} відсотка",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} відсотка",
                                few = "{0} відсотків",
                                many = "{0} відсотків",
                                other = "{0} відсотка",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} відсотком",
                                few = "{0} відсотками",
                                many = "{0} відсотками",
                                other = "{0} відсотка",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} відсотку",
                                few = "{0} відсотках",
                                many = "{0} відсотках",
                                other = "{0} відсотка",
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
                                one = "{0} проміле",
                                few = "{0} проміле",
                                many = "{0} проміле",
                                other = "{0} проміле",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} проміле",
                                few = "{0} проміле",
                                many = "{0} проміле",
                                other = "{0} проміле",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} проміле",
                                few = "{0} проміле",
                                many = "{0} проміле",
                                other = "{0} проміле",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} проміле",
                                few = "{0} проміле",
                                many = "{0} проміле",
                                other = "{0} проміле",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} проміле",
                                few = "{0} проміле",
                                many = "{0} проміле",
                                other = "{0} проміле",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} проміле",
                                few = "{0} проміле",
                                many = "{0} проміле",
                                other = "{0} проміле",
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
                                one = "{0} проміріад",
                                few = "{0} проміріади",
                                many = "{0} проміріадів",
                                other = "{0} проміріада",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} проміріад",
                                few = "{0} проміріади",
                                many = "{0} проміріадів",
                                other = "{0} проміріада",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} проміріаду",
                                few = "{0} проміріадам",
                                many = "{0} проміріадам",
                                other = "{0} проміріада",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} проміріада",
                                few = "{0} проміріадів",
                                many = "{0} проміріадів",
                                other = "{0} проміріада",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} проміріадом",
                                few = "{0} проміріадами",
                                many = "{0} проміріадами",
                                other = "{0} проміріада",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} проміріаді",
                                few = "{0} проміріадах",
                                many = "{0} проміріадах",
                                other = "{0} проміріада",
                            )
                        ),
                    )
                ),
            )
        override val concentrationAPortion: MultiLengthGenderedPattern
            get() = TODO("APortion formatter is not implemented yet for uk")
        override val concentrationMilligramOfglucosePerDeciliter
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} міліграм на децилітр",
                                few = "{0} міліграми на децилітр",
                                many = "{0} міліграмів на децилітр",
                                other = "{0} міліграма на децилітр",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мг/дл",
                                few = "{0}мг/дл",
                                many = "{0}мг/дл",
                                other = "{0}мг/дл",
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
                                one = "{0} мілімоль на літр",
                                few = "{0} мілімолі на літр",
                                many = "{0} мілімолів на літр",
                                other = "{0} мілімоля на літр",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мілімоль на літр",
                                few = "{0} мілімолі на літр",
                                many = "{0} мілімолів на літр",
                                other = "{0} мілімоля на літр",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мілімолю на літр",
                                few = "{0} мілімолям на літр",
                                many = "{0} мілімолям на літр",
                                other = "{0} мілімоля на літр",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мілімоля на літр",
                                few = "{0} мілімолів на літр",
                                many = "{0} мілімолів на літр",
                                other = "{0} мілімоля на літр",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мілімолем на літр",
                                few = "{0} мілімолями на літр",
                                many = "{0} мілімолями на літр",
                                other = "{0} мілімоля на літр",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мілімолі на літр",
                                few = "{0} мілімолях на літр",
                                many = "{0} мілімолях на літр",
                                other = "{0} мілімоля на літр",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ммоль/л",
                                few = "{0}ммоль/л",
                                many = "{0}ммоль/л",
                                other = "{0}ммоль/л",
                            )
                        ),
                    )
                ),
            )
        override val concentrationItemPerCubicMeter: MultiLengthGenderedPattern
            get() = TODO("ItemPerCubicMeter formatter is not implemented yet for uk")
        override val concentrationItem
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} елемент",
                                few = "{0} елементи",
                                many = "{0} елементів",
                                other = "{0} елемента",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} елемент",
                                few = "{0} елементи",
                                many = "{0} елементів",
                                other = "{0} елемента",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} елементу",
                                few = "{0} елементам",
                                many = "{0} елементам",
                                other = "{0} елемента",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} елемента",
                                few = "{0} елементів",
                                many = "{0} елементів",
                                other = "{0} елемента",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} елементом",
                                few = "{0} елементами",
                                many = "{0} елементами",
                                other = "{0} елемента",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} елементі",
                                few = "{0} елементах",
                                many = "{0} елементах",
                                other = "{0} елемента",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ел.",
                                few = "{0} ел.",
                                many = "{0} ел.",
                                other = "{0} ел.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ел.",
                                few = "{0}ел.",
                                many = "{0}ел.",
                                other = "{0}ел.",
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
                                few = "{0} молі",
                                many = "{0} молів",
                                other = "{0} моля",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} моль",
                                few = "{0} молі",
                                many = "{0} молів",
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
                                few = "{0} молів",
                                many = "{0} молів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} молі",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}моль",
                                few = "{0}моль",
                                many = "{0}моль",
                                other = "{0}моль",
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
                                one = "{0} літр на кілометр",
                                few = "{0} літри на кілометр",
                                many = "{0} літрів на кілометр",
                                other = "{0} літра на кілометр",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} літр на кілометр",
                                few = "{0} літри на кілометр",
                                many = "{0} літрів на кілометр",
                                other = "{0} літра на кілометр",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} літру на кілометр",
                                few = "{0} літрам на кілометр",
                                many = "{0} літрам на кілометр",
                                other = "{0} літра на кілометр",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} літра на кілометр",
                                few = "{0} літрів на кілометр",
                                many = "{0} літрів на кілометр",
                                other = "{0} літра на кілометр",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} літром на кілометр",
                                few = "{0} літрами на кілометр",
                                many = "{0} літрами на кілометр",
                                other = "{0} літра на кілометр",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} літрі на кілометр",
                                few = "{0} літрах на кілометр",
                                many = "{0} літрах на кілометр",
                                other = "{0} літра на кілометр",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}л/км",
                                few = "{0}л/км",
                                many = "{0}л/км",
                                other = "{0}л/км",
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
                                one = "{0} літр на 100 кілометрів",
                                few = "{0} літри на 100 кілометрів",
                                many = "{0} літрів на 100 кілометрів",
                                other = "{0} літра на 100 кілометрів",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} літр на 100 кілометрів",
                                few = "{0} літри на 100 кілометрів",
                                many = "{0} літрів на 100 кілометрів",
                                other = "{0} літра на 100 кілометрів",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} літру на 100 кілометрів",
                                few = "{0} літрам на 100 кілометрів",
                                many = "{0} літрам на 100 кілометрів",
                                other = "{0} літра на 100 кілометрів",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} літра на 100 кілометрів",
                                few = "{0} літрів на 100 кілометрів",
                                many = "{0} літрів на 100 кілометрів",
                                other = "{0} літра на 100 кілометрів",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} літром на 100 кілометрів",
                                few = "{0} літрами на 100 кілометрів",
                                many = "{0} літрами на 100 кілометрів",
                                other = "{0} літра на 100 кілометрів",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} літрі на 100 кілометрів",
                                few = "{0} літрах на 100 кілометрів",
                                many = "{0} літрах на 100 кілометрів",
                                other = "{0} літра на 100 кілометрів",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}л/100 км",
                                few = "{0}л/100 км",
                                many = "{0}л/100 км",
                                other = "{0}л/100 км",
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
                                one = "{0} миля на галон",
                                few = "{0} милі на галон",
                                many = "{0} миль на галон",
                                other = "{0} милі на галон",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} миля/гал.",
                                few = "{0} милі/гал.",
                                many = "{0} миль/гал.",
                                other = "{0} милі/гал.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мл/гал",
                                few = "{0}мл/гал",
                                many = "{0}мл/гал",
                                other = "{0}мл/гал",
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
                                one = "{0} миля на англійський галон",
                                few = "{0} милі на англійський галон",
                                many = "{0} миль на англійський галон",
                                other = "{0} милі на англійський галон",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} миля/англ. гал.",
                                few = "{0} милі/англ. гал.",
                                many = "{0} миль/англ. гал.",
                                other = "{0} милі/англ. гал.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мл/англ.гал.",
                                few = "{0}мл/англ.гал.",
                                many = "{0}мл/англ.гал.",
                                other = "{0}мл/англ.гал.",
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
                                few = "{0} петабайти",
                                many = "{0} петабайтів",
                                other = "{0} петабайта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} петабайт",
                                few = "{0} петабайти",
                                many = "{0} петабайтів",
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
                                few = "{0} петабайтів",
                                many = "{0} петабайтів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} петабайті",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ПБ",
                                few = "{0}ПБ",
                                many = "{0}ПБ",
                                other = "{0}ПБ",
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
                                few = "{0} терабайти",
                                many = "{0} терабайтів",
                                other = "{0} терабайта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} терабайт",
                                few = "{0} терабайти",
                                many = "{0} терабайтів",
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
                                few = "{0} терабайтів",
                                many = "{0} терабайтів",
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
                                one = "{0} терабайті",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ТБ",
                                few = "{0}ТБ",
                                many = "{0}ТБ",
                                other = "{0}ТБ",
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
                                one = "{0} терабіт",
                                few = "{0} терабіти",
                                many = "{0} терабітів",
                                other = "{0} терабіта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} терабіт",
                                few = "{0} терабіти",
                                many = "{0} терабітів",
                                other = "{0} терабіта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} терабіту",
                                few = "{0} терабітам",
                                many = "{0} терабітам",
                                other = "{0} терабіта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} терабіта",
                                few = "{0} терабітів",
                                many = "{0} терабітів",
                                other = "{0} терабіта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} терабітом",
                                few = "{0} терабітами",
                                many = "{0} терабітами",
                                other = "{0} терабіта",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} терабіті",
                                few = "{0} терабітах",
                                many = "{0} терабітах",
                                other = "{0} терабіта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Тб",
                                few = "{0} Тб",
                                many = "{0} Тб",
                                other = "{0} Тб",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Тб",
                                few = "{0}Тб",
                                many = "{0}Тб",
                                other = "{0}Тб",
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
                                one = "{0} гігабайт",
                                few = "{0} гігабайти",
                                many = "{0} гігабайтів",
                                other = "{0} гігабайта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гігабайт",
                                few = "{0} гігабайти",
                                many = "{0} гігабайтів",
                                other = "{0} гігабайта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} гігабайту",
                                few = "{0} гігабайтам",
                                many = "{0} гігабайтам",
                                other = "{0} гігабайта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} гігабайта",
                                few = "{0} гігабайтів",
                                many = "{0} гігабайтів",
                                other = "{0} гігабайта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} гігабайтом",
                                few = "{0} гігабайтами",
                                many = "{0} гігабайтами",
                                other = "{0} гігабайта",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} гігабайті",
                                few = "{0} гігабайтах",
                                many = "{0} гігабайтах",
                                other = "{0} гігабайта",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ГБ",
                                few = "{0}ГБ",
                                many = "{0}ГБ",
                                other = "{0}ГБ",
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
                                one = "{0} гігабіт",
                                few = "{0} гігабіти",
                                many = "{0} гігабітів",
                                other = "{0} гігабіта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гігабіт",
                                few = "{0} гігабіти",
                                many = "{0} гігабітів",
                                other = "{0} гігабіта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} гігабіту",
                                few = "{0} гігабітам",
                                many = "{0} гігабітам",
                                other = "{0} гігабіта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} гігабіта",
                                few = "{0} гігабітів",
                                many = "{0} гігабітів",
                                other = "{0} гігабіта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} гігабітом",
                                few = "{0} гігабітами",
                                many = "{0} гігабітами",
                                other = "{0} гігабіта",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} гігабіті",
                                few = "{0} гігабітах",
                                many = "{0} гігабітах",
                                other = "{0} гігабіта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Гб",
                                few = "{0} Гб",
                                many = "{0} Гб",
                                other = "{0} Гб",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Гб",
                                few = "{0}Гб",
                                many = "{0}Гб",
                                other = "{0}Гб",
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
                                few = "{0} мегабайти",
                                many = "{0} мегабайтів",
                                other = "{0} мегабайта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мегабайт",
                                few = "{0} мегабайти",
                                many = "{0} мегабайтів",
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
                                few = "{0} мегабайтів",
                                many = "{0} мегабайтів",
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
                                one = "{0} мегабайті",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}МБ",
                                few = "{0}МБ",
                                many = "{0}МБ",
                                other = "{0}МБ",
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
                                one = "{0} мегабіт",
                                few = "{0} мегабіти",
                                many = "{0} мегабітів",
                                other = "{0} мегабіта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мегабіт",
                                few = "{0} мегабіти",
                                many = "{0} мегабітів",
                                other = "{0} мегабіта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мегабіту",
                                few = "{0} мегабітам",
                                many = "{0} мегабітам",
                                other = "{0} мегабіта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мегабіта",
                                few = "{0} мегабітів",
                                many = "{0} мегабітів",
                                other = "{0} мегабіта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мегабітом",
                                few = "{0} мегабітами",
                                many = "{0} мегабітами",
                                other = "{0} мегабіта",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мегабіті",
                                few = "{0} мегабітах",
                                many = "{0} мегабітах",
                                other = "{0} мегабіта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} Мб",
                                few = "{0} Мб",
                                many = "{0} Мб",
                                other = "{0} Мб",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Мб",
                                few = "{0}Мб",
                                many = "{0}Мб",
                                other = "{0}Мб",
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
                                one = "{0} кілобайт",
                                few = "{0} кілобайти",
                                many = "{0} кілобайтів",
                                other = "{0} кілобайта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кілобайт",
                                few = "{0} кілобайти",
                                many = "{0} кілобайтів",
                                other = "{0} кілобайта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кілобайту",
                                few = "{0} кілобайтам",
                                many = "{0} кілобайтам",
                                other = "{0} кілобайта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кілобайта",
                                few = "{0} кілобайтів",
                                many = "{0} кілобайтів",
                                other = "{0} кілобайта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кілобайтом",
                                few = "{0} кілобайтами",
                                many = "{0} кілобайтами",
                                other = "{0} кілобайта",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кілобайті",
                                few = "{0} кілобайтах",
                                many = "{0} кілобайтах",
                                other = "{0} кілобайта",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кБ",
                                few = "{0}кБ",
                                many = "{0}кБ",
                                other = "{0}кБ",
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
                                one = "{0} кілобіт",
                                few = "{0} кілобіти",
                                many = "{0} кілобітів",
                                other = "{0} кілобіта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кілобіт",
                                few = "{0} кілобіти",
                                many = "{0} кілобітів",
                                other = "{0} кілобіта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кілобіту",
                                few = "{0} кілобітам",
                                many = "{0} кілобітам",
                                other = "{0} кілобіта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кілобіта",
                                few = "{0} кілобітів",
                                many = "{0} кілобітів",
                                other = "{0} кілобіта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кілобітом",
                                few = "{0} кілобітами",
                                many = "{0} кілобітами",
                                other = "{0} кілобіта",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кілобіті",
                                few = "{0} кілобітах",
                                many = "{0} кілобітах",
                                other = "{0} кілобіта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кб",
                                few = "{0} кб",
                                many = "{0} кб",
                                other = "{0} кб",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кб",
                                few = "{0}кб",
                                many = "{0}кб",
                                other = "{0}кб",
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
                                one = "{0} байт",
                                few = "{0} байти",
                                many = "{0} байтів",
                                other = "{0} байта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} байт",
                                few = "{0} байти",
                                many = "{0} байтів",
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
                                few = "{0} байтів",
                                many = "{0} байтів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} байті",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Б",
                                few = "{0}Б",
                                many = "{0}Б",
                                other = "{0}Б",
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
                                one = "{0} біт",
                                few = "{0} біти",
                                many = "{0} бітів",
                                other = "{0} біта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} біт",
                                few = "{0} біти",
                                many = "{0} бітів",
                                other = "{0} біта",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} біту",
                                few = "{0} бітам",
                                many = "{0} бітам",
                                other = "{0} біта",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} біта",
                                few = "{0} бітів",
                                many = "{0} бітів",
                                other = "{0} біта",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} бітом",
                                few = "{0} бітами",
                                many = "{0} бітами",
                                other = "{0} біта",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} біті",
                                few = "{0} бітах",
                                many = "{0} бітах",
                                other = "{0} біта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} б",
                                few = "{0} б",
                                many = "{0} б",
                                other = "{0} б",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}б",
                                few = "{0}б",
                                many = "{0}б",
                                other = "{0}б",
                            )
                        ),
                    )
                ),
            )
        override val durationCentury
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Neuter,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} століття",
                                few = "{0} століття",
                                many = "{0} століть",
                                other = "{0} століття",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} століття",
                                few = "{0} століття",
                                many = "{0} століть",
                                other = "{0} століття",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} століттю",
                                few = "{0} століттям",
                                many = "{0} століттям",
                                other = "{0} століття",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} століття",
                                few = "{0} століть",
                                many = "{0} століть",
                                other = "{0} століття",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} століттям",
                                few = "{0} століттями",
                                many = "{0} століттями",
                                other = "{0} століття",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} столітті",
                                few = "{0} століттях",
                                many = "{0} століттях",
                                other = "{0} століття",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ст.",
                                few = "{0} ст.",
                                many = "{0} ст.",
                                other = "{0} ст.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ст",
                                few = "{0}ст",
                                many = "{0}ст",
                                other = "{0}ст",
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
                                one = "{0} десятиріччя",
                                few = "{0} десятиріччя",
                                many = "{0} десятиріч",
                                other = "{0} десятиріччя",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} десятиріччя",
                                few = "{0} десятиріччя",
                                many = "{0} десятиріч",
                                other = "{0} десятиріччя",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} десятиріччю",
                                few = "{0} десятиріччям",
                                many = "{0} десятиріччям",
                                other = "{0} десятиріччя",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} десятиріччя",
                                few = "{0} десятиріч",
                                many = "{0} десятиріч",
                                other = "{0} десятиріччя",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} десятиріччям",
                                few = "{0} десятиріччями",
                                many = "{0} десятиріччями",
                                other = "{0} десятиріччя",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} десятиріччі",
                                few = "{0} десятиріччях",
                                many = "{0} десятиріччях",
                                other = "{0} десятиріччя",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} 10-річчя",
                                few = "{0} 10-річчя",
                                many = "{0} 10-річ",
                                other = "{0} 10-річ",
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
                                one = "{0} рік",
                                few = "{0} роки",
                                many = "{0} років",
                                other = "{0} року",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} рік",
                                few = "{0} роки",
                                many = "{0} років",
                                other = "{0} року",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} року",
                                few = "{0} рокам",
                                many = "{0} рокам",
                                other = "{0} року",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} року",
                                few = "{0} років",
                                many = "{0} років",
                                other = "{0} року",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} роком",
                                few = "{0} роками",
                                many = "{0} роками",
                                other = "{0} року",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} році",
                                few = "{0} роках",
                                many = "{0} роках",
                                other = "{0} року",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} р.",
                                few = "{0} р.",
                                many = "{0} р.",
                                other = "{0} р.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}р",
                                few = "{0}р",
                                many = "{0}р",
                                other = "{0}р",
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
                                few = "{0} квартали",
                                many = "{0} кварталів",
                                other = "{0} кварталу",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} квартал",
                                few = "{0} квартали",
                                many = "{0} кварталів",
                                other = "{0} кварталу",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кварталу",
                                few = "{0} кварталам",
                                many = "{0} кварталам",
                                other = "{0} кварталу",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кварталу",
                                few = "{0} кварталів",
                                many = "{0} кварталів",
                                other = "{0} кварталу",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кварталом",
                                few = "{0} кварталами",
                                many = "{0} кварталами",
                                other = "{0} кварталу",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кварталі",
                                few = "{0} кварталах",
                                many = "{0} кварталах",
                                other = "{0} кварталу",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кв.",
                                few = "{0} кв.",
                                many = "{0} кв.",
                                other = "{0} кв.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кв",
                                few = "{0}кв",
                                many = "{0}кв",
                                other = "{0}кв",
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
                                one = "{0} місяць",
                                few = "{0} місяці",
                                many = "{0} місяців",
                                other = "{0} місяця",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} місяць",
                                few = "{0} місяці",
                                many = "{0} місяців",
                                other = "{0} місяця",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} місяцю",
                                few = "{0} місяцям",
                                many = "{0} місяцям",
                                other = "{0} місяця",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} місяця",
                                few = "{0} місяців",
                                many = "{0} місяців",
                                other = "{0} місяця",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} місяцем",
                                few = "{0} місяцями",
                                many = "{0} місяцями",
                                other = "{0} місяця",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} місяці",
                                few = "{0} місяцях",
                                many = "{0} місяцях",
                                other = "{0} місяця",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} міс.",
                                few = "{0} міс.",
                                many = "{0} міс.",
                                other = "{0} міс.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}м",
                                few = "{0}м",
                                many = "{0}м",
                                other = "{0}м",
                            )
                        ),
                    )
                ),
            )
        override val durationWeek
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} тиждень",
                                few = "{0} тижні",
                                many = "{0} тижнів",
                                other = "{0} тижня",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} тиждень",
                                few = "{0} тижні",
                                many = "{0} тижнів",
                                other = "{0} тижня",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} тижню",
                                few = "{0} тижням",
                                many = "{0} тижням",
                                other = "{0} тижня",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} тижня",
                                few = "{0} тижнів",
                                many = "{0} тижнів",
                                other = "{0} тижня",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} тижнем",
                                few = "{0} тижнями",
                                many = "{0} тижнями",
                                other = "{0} тижня",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} тижні",
                                few = "{0} тижнях",
                                many = "{0} тижнях",
                                other = "{0} тижня",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} тиж.",
                                few = "{0} тиж.",
                                many = "{0} тиж.",
                                other = "{0} тиж.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}т",
                                few = "{0}т",
                                many = "{0}т",
                                other = "{0}т",
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
                                few = "{0} дні",
                                many = "{0} днів",
                                other = "{0} дня",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} день",
                                few = "{0} дні",
                                many = "{0} днів",
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
                                few = "{0} днів",
                                many = "{0} днів",
                                other = "{0} дня",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} днем",
                                few = "{0} днями",
                                many = "{0} днями",
                                other = "{0} дня",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} дні",
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
                                one = "{0}д",
                                few = "{0}д",
                                many = "{0}д",
                                other = "{0}д",
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
                                one = "{0} година",
                                few = "{0} години",
                                many = "{0} годин",
                                other = "{0} години",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} годину",
                                few = "{0} години",
                                many = "{0} годин",
                                other = "{0} години",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} годині",
                                few = "{0} годинам",
                                many = "{0} годинам",
                                other = "{0} години",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} години",
                                few = "{0} годин",
                                many = "{0} годин",
                                other = "{0} години",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} годиною",
                                few = "{0} годинами",
                                many = "{0} годинами",
                                other = "{0} години",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} годині",
                                few = "{0} годинах",
                                many = "{0} годинах",
                                other = "{0} години",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} год",
                                few = "{0} год",
                                many = "{0} год",
                                other = "{0} год",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}г",
                                few = "{0}г",
                                many = "{0}г",
                                other = "{0}г",
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
                                one = "{0} хвилина",
                                few = "{0} хвилини",
                                many = "{0} хвилин",
                                other = "{0} хвилини",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} хвилину",
                                few = "{0} хвилини",
                                many = "{0} хвилин",
                                other = "{0} хвилини",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} хвилині",
                                few = "{0} хвилинам",
                                many = "{0} хвилинам",
                                other = "{0} хвилини",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} хвилини",
                                few = "{0} хвилин",
                                many = "{0} хвилин",
                                other = "{0} хвилини",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} хвилиною",
                                few = "{0} хвилинами",
                                many = "{0} хвилинами",
                                other = "{0} хвилини",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} хвилині",
                                few = "{0} хвилинах",
                                many = "{0} хвилинах",
                                other = "{0} хвилини",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}х",
                                few = "{0}х",
                                many = "{0}х",
                                other = "{0}х",
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
                                few = "{0} секунди",
                                many = "{0} секунд",
                                other = "{0} секунди",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} секунду",
                                few = "{0} секунди",
                                many = "{0} секунд",
                                other = "{0} секунди",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} секунді",
                                few = "{0} секундам",
                                many = "{0} секундам",
                                other = "{0} секунди",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} секунди",
                                few = "{0} секунд",
                                many = "{0} секунд",
                                other = "{0} секунди",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} секундою",
                                few = "{0} секундами",
                                many = "{0} секундами",
                                other = "{0} секунди",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} секунді",
                                few = "{0} секундах",
                                many = "{0} секундах",
                                other = "{0} секунди",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}с",
                                few = "{0}с",
                                many = "{0}с",
                                other = "{0}с",
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
                                one = "{0} мілісекунда",
                                few = "{0} мілісекунди",
                                many = "{0} мілісекунд",
                                other = "{0} мілісекунди",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мілісекунду",
                                few = "{0} мілісекунди",
                                many = "{0} мілісекунд",
                                other = "{0} мілісекунди",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мілісекунді",
                                few = "{0} мілісекундам",
                                many = "{0} мілісекундам",
                                other = "{0} мілісекунди",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мілісекунди",
                                few = "{0} мілісекунд",
                                many = "{0} мілісекунд",
                                other = "{0} мілісекунди",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мілісекундою",
                                few = "{0} мілісекундами",
                                many = "{0} мілісекундами",
                                other = "{0} мілісекунди",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мілісекунді",
                                few = "{0} мілісекундах",
                                many = "{0} мілісекундах",
                                other = "{0} мілісекунди",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мс",
                                few = "{0}мс",
                                many = "{0}мс",
                                other = "{0}мс",
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
                                one = "{0} мікросекунда",
                                few = "{0} мікросекунди",
                                many = "{0} мікросекунд",
                                other = "{0} мікросекунди",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мікросекунду",
                                few = "{0} мікросекунди",
                                many = "{0} мікросекунд",
                                other = "{0} мікросекунди",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мікросекунді",
                                few = "{0} мікросекундам",
                                many = "{0} мікросекундам",
                                other = "{0} мікросекунди",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мікросекунди",
                                few = "{0} мікросекунд",
                                many = "{0} мікросекунд",
                                other = "{0} мікросекунди",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мікросекундою",
                                few = "{0} мікросекундами",
                                many = "{0} мікросекундами",
                                other = "{0} мікросекунди",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мікросекунді",
                                few = "{0} мікросекундах",
                                many = "{0} мікросекундах",
                                other = "{0} мікросекунди",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мкс",
                                few = "{0}мкс",
                                many = "{0}мкс",
                                other = "{0}мкс",
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
                                few = "{0} наносекунди",
                                many = "{0} наносекунд",
                                other = "{0} наносекунди",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} наносекунду",
                                few = "{0} наносекунди",
                                many = "{0} наносекунд",
                                other = "{0} наносекунди",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} наносекунді",
                                few = "{0} наносекундам",
                                many = "{0} наносекундам",
                                other = "{0} наносекунди",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} наносекунди",
                                few = "{0} наносекунд",
                                many = "{0} наносекунд",
                                other = "{0} наносекунди",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} наносекундою",
                                few = "{0} наносекундами",
                                many = "{0} наносекундами",
                                other = "{0} наносекунди",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} наносекунді",
                                few = "{0} наносекундах",
                                many = "{0} наносекундах",
                                other = "{0} наносекунди",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}нс",
                                few = "{0}нс",
                                many = "{0}нс",
                                other = "{0}нс",
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
                                few = "{0} ампери",
                                many = "{0} амперів",
                                other = "{0} ампера",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ампер",
                                few = "{0} ампери",
                                many = "{0} амперів",
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
                                few = "{0} амперів",
                                many = "{0} амперів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} ампері",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}А",
                                few = "{0}А",
                                many = "{0}А",
                                other = "{0}А",
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
                                one = "{0} міліампер",
                                few = "{0} міліампери",
                                many = "{0} міліамперів",
                                other = "{0} міліампера",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} міліампер",
                                few = "{0} міліампери",
                                many = "{0} міліамперів",
                                other = "{0} міліампера",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} міліамперу",
                                few = "{0} міліамперам",
                                many = "{0} міліамперам",
                                other = "{0} міліампера",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} міліампера",
                                few = "{0} міліамперів",
                                many = "{0} міліамперів",
                                other = "{0} міліампера",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} міліампером",
                                few = "{0} міліамперами",
                                many = "{0} міліамперами",
                                other = "{0} міліампера",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} міліампері",
                                few = "{0} міліамперах",
                                many = "{0} міліамперах",
                                other = "{0} міліампера",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мА",
                                few = "{0}мА",
                                many = "{0}мА",
                                other = "{0}мА",
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
                                few = "{0} оми",
                                many = "{0} омів",
                                other = "{0} ома",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ом",
                                few = "{0} оми",
                                many = "{0} омів",
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
                                few = "{0} омів",
                                many = "{0} омів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} омі",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Ом",
                                few = "{0}Ом",
                                many = "{0}Ом",
                                other = "{0}Ом",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondSquareAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondSquareAmpere formatter is not implemented yet for uk")
        override val electricVolt
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} вольт",
                                few = "{0} вольти",
                                many = "{0} вольтів",
                                other = "{0} вольта",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} вольт",
                                few = "{0} вольти",
                                many = "{0} вольтів",
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
                                few = "{0} вольтів",
                                many = "{0} вольтів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} вольті",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}В",
                                few = "{0}В",
                                many = "{0}В",
                                other = "{0}В",
                            )
                        ),
                    )
                ),
            )
        override val electricKilogramSquareMeterPerCubicSecondAmpere: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecondAmpere formatter is not implemented yet for uk")
        override val energyKilocalorie
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кілокалорія",
                                few = "{0} кілокалорії",
                                many = "{0} кілокалорій",
                                other = "{0} кілокалорії",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ккал",
                                few = "{0}ккал",
                                many = "{0}ккал",
                                other = "{0}ккал",
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
                                one = "{0} калорія",
                                few = "{0} калорії",
                                many = "{0} калорій",
                                other = "{0} калорії",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} калорію",
                                few = "{0} калорії",
                                many = "{0} калорій",
                                other = "{0} калорії",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} калорії",
                                few = "{0} калоріям",
                                many = "{0} калоріям",
                                other = "{0} калорії",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} калорії",
                                few = "{0} калорій",
                                many = "{0} калорій",
                                other = "{0} калорії",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} калорією",
                                few = "{0} калоріями",
                                many = "{0} калоріями",
                                other = "{0} калорії",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} калорії",
                                few = "{0} калоріях",
                                many = "{0} калоріях",
                                other = "{0} калорії",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кал",
                                few = "{0}кал",
                                many = "{0}кал",
                                other = "{0}кал",
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
                                one = "{0} кілокалорія",
                                few = "{0} кілокалорії",
                                many = "{0} кілокалорій",
                                other = "{0} кілокалорії",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ккал",
                                few = "{0}ккал",
                                many = "{0}ккал",
                                other = "{0}ккал",
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
                                one = "{0} кілоджоуль",
                                few = "{0} кілоджоулі",
                                many = "{0} кілоджоулів",
                                other = "{0} кілоджоуля",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кілоджоуль",
                                few = "{0} кілоджоулі",
                                many = "{0} кілоджоулів",
                                other = "{0} кілоджоуля",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кілоджоулю",
                                few = "{0} кілоджоулям",
                                many = "{0} кілоджоулям",
                                other = "{0} кілоджоуля",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кілоджоуля",
                                few = "{0} кілоджоулі",
                                many = "{0} кілоджоулів",
                                other = "{0} кілоджоуля",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кілоджоулем",
                                few = "{0} кілоджоулями",
                                many = "{0} кілоджоулями",
                                other = "{0} кілоджоуля",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кілоджоулі",
                                few = "{0} кілоджоулів",
                                many = "{0} кілоджоулях",
                                other = "{0} кілоджоуля",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кДж",
                                few = "{0}кДж",
                                many = "{0}кДж",
                                other = "{0}кДж",
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
                                few = "{0} джоулі",
                                many = "{0} джоулів",
                                other = "{0} джоуля",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} джоуль",
                                few = "{0} джоулі",
                                many = "{0} джоулів",
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
                                few = "{0} джоулів",
                                many = "{0} джоулів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} джоулі",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Дж",
                                few = "{0}Дж",
                                many = "{0}Дж",
                                other = "{0}Дж",
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
                                one = "{0} кіловат-година",
                                few = "{0} кіловат-години",
                                many = "{0} кіловат-годин",
                                other = "{0} кіловат-години",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кіловат-годину",
                                few = "{0} кіловат-години",
                                many = "{0} кіловат-годин",
                                other = "{0} кіловат-години",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кіловат-годині",
                                few = "{0} кіловат-годинам",
                                many = "{0} кіловат-годинам",
                                other = "{0} кіловат-години",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кіловат-години",
                                few = "{0} кіловат-годин",
                                many = "{0} кіловат-годин",
                                other = "{0} кіловат-години",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кіловат-годиною",
                                few = "{0} кіловат-годинами",
                                many = "{0} кіловат-годинами",
                                other = "{0} кіловат-години",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кіловат-годині",
                                few = "{0} кіловат-годинах",
                                many = "{0} кіловат-годинах",
                                other = "{0} кіловат-години",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кВт⋅год",
                                few = "{0} кВт⋅год",
                                many = "{0} кВт⋅год",
                                other = "{0} кВт⋅год",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кВт⋅год",
                                few = "{0}кВт⋅год",
                                many = "{0}кВт⋅год",
                                other = "{0}кВт⋅год",
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
                                one = "{0} електрон-вольт",
                                few = "{0} електрон-вольти",
                                many = "{0} електрон-вольтів",
                                other = "{0} електрон-вольта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} еВ",
                                few = "{0} еВ",
                                many = "{0} еВ",
                                other = "{0} еВ",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}еВ",
                                few = "{0}еВ",
                                many = "{0}еВ",
                                other = "{0}еВ",
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
                                one = "{0} британська теплова одиниця",
                                few = "{0} британські теплові одиниці",
                                many = "{0} британських теплових одиниць",
                                other = "{0} британської теплової одиниці",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} БТО",
                                few = "{0} БТО",
                                many = "{0} БТО",
                                other = "{0} БТО",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}БТО",
                                few = "{0}БТО",
                                many = "{0}БТО",
                                other = "{0}БТО",
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
                                one = "{0} американський терм",
                                few = "{0} американських терми",
                                many = "{0} американських термів",
                                other = "{0} американського терма",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ам. терм",
                                few = "{0} ам. терми",
                                many = "{0} ам. термів",
                                other = "{0} ам. терма",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ам.терм.",
                                few = "{0}ам.терм.",
                                many = "{0}ам.терм.",
                                other = "{0}ам.терм.",
                            )
                        ),
                    )
                ),
            )
        override val energyKilogramSquareMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerSquareSecond formatter is not implemented yet for uk")
        override val forcePoundForce
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фунт-сила",
                                few = "{0} фунт-сили",
                                many = "{0} фунт-сил",
                                other = "{0} фунт-сили",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фнтс",
                                few = "{0} фнтс",
                                many = "{0} фнтс",
                                other = "{0} фнтс",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}фнтс",
                                few = "{0}фнтс",
                                many = "{0}фнтс",
                                other = "{0}фнтс",
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
                                few = "{0} ньютони",
                                many = "{0} ньютонів",
                                other = "{0} ньютона",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ньютон",
                                few = "{0} ньютони",
                                many = "{0} ньютонів",
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
                                few = "{0} ньютонів",
                                many = "{0} ньютонів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} ньютоні",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Н",
                                few = "{0}Н",
                                many = "{0}Н",
                                other = "{0}Н",
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
                                one = "{0} кіловат-година на 100 кілометрів",
                                few = "{0} кіловат-години на 100 кілометрів",
                                many = "{0} кіловат-годин на 100 кілометрів",
                                other = "{0} кіловат-години на 100 кілометрів",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кіловат-годину на 100 кілометрів",
                                few = "{0} кіловат-години на 100 кілометрів",
                                many = "{0} кіловат-годин на 100 кілометрів",
                                other = "{0} кіловат-години на 100 кілометрів",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кіловат-годині на 100 кілометрів",
                                few = "{0} кіловат-годинам на 100 кілометрів",
                                many = "{0} кіловат-годинам на 100 кілометрів",
                                other = "{0} кіловат-години на 100 кілометрів",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кіловат-години на 100 кілометрів",
                                few = "{0} кіловат-годин на 100 кілометрів",
                                many = "{0} кіловат-годин на 100 кілометрів",
                                other = "{0} кіловат-години на 100 кілометрів",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кіловат-годиною на 100 кілометрів",
                                few = "{0} кіловат-годинами на 100 кілометрів",
                                many = "{0} кіловат-годинами на 100 кілометрів",
                                other = "{0} кіловат-години на 100 кілометрів",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кіловат-годині на 100 кілометрів",
                                few = "{0} кіловат-годинах на 100 кілометрів",
                                many = "{0} кіловат-годинах на 100 кілометрів",
                                other = "{0} кіловат-години на 100 кілометрів",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кВт⋅год/100 км",
                                few = "{0} кВт⋅год/100 км",
                                many = "{0} кВт⋅год/100 км",
                                other = "{0} кВт⋅год/100 км",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кВт⋅год/100км",
                                few = "{0}кВт⋅год/100км",
                                many = "{0}кВт⋅год/100км",
                                other = "{0}кВт⋅год/100км",
                            )
                        ),
                    )
                ),
            )
        override val forceKilogramMeterPerSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramMeterPerSquareSecond formatter is not implemented yet for uk")
        override val frequencyGigahertz
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гігагерц",
                                few = "{0} гігагерци",
                                many = "{0} гігагерців",
                                other = "{0} гігагерца",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гігагерц",
                                few = "{0} гігагерци",
                                many = "{0} гігагерців",
                                other = "{0} гігагерца",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} гігагерцу",
                                few = "{0} гігагерцам",
                                many = "{0} гігагерцам",
                                other = "{0} гігагерца",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} гігагерца",
                                few = "{0} гігагерців",
                                many = "{0} гігагерців",
                                other = "{0} гігагерца",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} гігагерцом",
                                few = "{0} гігагерцами",
                                many = "{0} гігагерцами",
                                other = "{0} гігагерца",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} гігагерці",
                                few = "{0} гігагерцах",
                                many = "{0} гігагерцах",
                                other = "{0} гігагерца",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ГГц",
                                few = "{0}ГГц",
                                many = "{0}ГГц",
                                other = "{0}ГГц",
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
                                few = "{0} мегагерци",
                                many = "{0} мегагерців",
                                other = "{0} мегагерца",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мегагерц",
                                few = "{0} мегагерци",
                                many = "{0} мегагерців",
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
                                few = "{0} мегагерців",
                                many = "{0} мегагерців",
                                other = "{0} мегагерца",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мегагерцом",
                                few = "{0} мегагерцами",
                                many = "{0} мегагерцами",
                                other = "{0} мегагерца",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мегагерці",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}МГц",
                                few = "{0}МГц",
                                many = "{0}МГц",
                                other = "{0}МГц",
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
                                one = "{0} кілогерц",
                                few = "{0} кілогерци",
                                many = "{0} кілогерців",
                                other = "{0} кілогерца",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кілогерц",
                                few = "{0} кілогерци",
                                many = "{0} кілогерців",
                                other = "{0} кілогерца",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кілогерцу",
                                few = "{0} кілогерцам",
                                many = "{0} кілогерцам",
                                other = "{0} кілогерца",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кілогерца",
                                few = "{0} кілогерців",
                                many = "{0} кілогерців",
                                other = "{0} кілогерца",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кілогерцом",
                                few = "{0} кілогерцами",
                                many = "{0} кілогерцами",
                                other = "{0} кілогерца",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кілогерці",
                                few = "{0} кілогерцах",
                                many = "{0} кілогерцах",
                                other = "{0} кілогерца",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кГц",
                                few = "{0}кГц",
                                many = "{0}кГц",
                                other = "{0}кГц",
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
                                few = "{0} герци",
                                many = "{0} герців",
                                other = "{0} герца",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} герц",
                                few = "{0} герци",
                                many = "{0} герців",
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
                                few = "{0} герців",
                                many = "{0} герців",
                                other = "{0} герца",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} герцом",
                                few = "{0} герцами",
                                many = "{0} герцами",
                                other = "{0} герца",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} герці",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Гц",
                                few = "{0}Гц",
                                many = "{0}Гц",
                                other = "{0}Гц",
                            )
                        ),
                    )
                ),
            )
        override val frequencyRevolutionPerSecond: MultiLengthGenderedPattern
            get() = TODO("RevolutionPerSecond formatter is not implemented yet for uk")
        override val graphicsEm
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} типографський ем",
                                few = "{0} типографські еми",
                                many = "{0} типографських емів",
                                other = "{0} типографського ема",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} типографський ем",
                                few = "{0} типографські еми",
                                many = "{0} типографських емів",
                                other = "{0} типографського ема",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} типографському ему",
                                few = "{0} типографським емам",
                                many = "{0} типографським емам",
                                other = "{0} типографського ема",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} типографського ема",
                                few = "{0} типографських емів",
                                many = "{0} типографських емів",
                                other = "{0} типографського ема",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} типографським емом",
                                few = "{0} типографськими емами",
                                many = "{0} типографськими емами",
                                other = "{0} типографського ема",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} типографському емі",
                                few = "{0} типографських емах",
                                many = "{0} типографських емах",
                                other = "{0} типографського ема",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ем",
                                few = "{0} ем",
                                many = "{0} ем",
                                other = "{0} ем",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ем",
                                few = "{0}ем",
                                many = "{0}ем",
                                other = "{0}ем",
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
                                one = "{0} піксель",
                                few = "{0} пікселі",
                                many = "{0} пікселів",
                                other = "{0} пікселя",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} піксель",
                                few = "{0} пікселі",
                                many = "{0} пікселів",
                                other = "{0} пікселя",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} пікселю",
                                few = "{0} пікселям",
                                many = "{0} пікселям",
                                other = "{0} пікселя",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} пікселя",
                                few = "{0} пікселів",
                                many = "{0} пікселів",
                                other = "{0} пікселя",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} пікселем",
                                few = "{0} пікселями",
                                many = "{0} пікселями",
                                other = "{0} пікселя",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} пікселі",
                                few = "{0} пікселях",
                                many = "{0} пікселях",
                                other = "{0} пікселя",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}пкс",
                                few = "{0}пкс",
                                many = "{0}пкс",
                                other = "{0}пкс",
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
                                one = "{0} мегапіксель",
                                few = "{0} мегапікселі",
                                many = "{0} мегапікселів",
                                other = "{0} мегапікселя",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мегапіксель",
                                few = "{0} мегапікселі",
                                many = "{0} мегапікселів",
                                other = "{0} мегапікселя",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мегапікселю",
                                few = "{0} мегапікселям",
                                many = "{0} мегапікселям",
                                other = "{0} мегапікселя",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мегапікселя",
                                few = "{0} мегапікселів",
                                many = "{0} мегапікселів",
                                other = "{0} мегапікселя",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мегапікселем",
                                few = "{0} мегапікселями",
                                many = "{0} мегапікселями",
                                other = "{0} мегапікселя",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мегапікселі",
                                few = "{0} мегапікселях",
                                many = "{0} мегапікселях",
                                other = "{0} мегапікселя",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Мп",
                                few = "{0}Мп",
                                many = "{0}Мп",
                                other = "{0}Мп",
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
                                many = "{0} точок",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}тчк",
                                few = "{0}тчк",
                                many = "{0}тчк",
                                other = "{0}тчк",
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
                                one = "{0} піксель на сантиметр",
                                few = "{0} пікселі на сантиметр",
                                many = "{0} пікселів на сантиметр",
                                other = "{0} пікселя на сантиметр",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} піксель на сантиметр",
                                few = "{0} пікселі на сантиметр",
                                many = "{0} пікселів на сантиметр",
                                other = "{0} пікселя на сантиметр",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} пікселю на сантиметр",
                                few = "{0} пікселям на сантиметр",
                                many = "{0} пікселям на сантиметр",
                                other = "{0} пікселя на сантиметр",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} пікселя на сантиметр",
                                few = "{0} пікселів на сантиметр",
                                many = "{0} пікселів на сантиметр",
                                other = "{0} пікселя на сантиметр",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} пікселем на сантиметр",
                                few = "{0} пікселями на сантиметр",
                                many = "{0} пікселями на сантиметр",
                                other = "{0} пікселя на сантиметр",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} пікселі на сантиметр",
                                few = "{0} пікселях на сантиметр",
                                many = "{0} пікселях на сантиметр",
                                other = "{0} пікселя на сантиметр",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}пкс/см",
                                few = "{0}пкс/см",
                                many = "{0}пкс/см",
                                other = "{0}пкс/см",
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
                                one = "{0} піксель на дюйм",
                                few = "{0} пікселі на дюйм",
                                many = "{0} пікселів на дюйм",
                                other = "{0} пікселя на дюйм",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} пкс/“",
                                few = "{0} пкс/“",
                                many = "{0} пкс/“",
                                other = "{0} пкс/“",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}пкс/“",
                                few = "{0}пкс/“",
                                many = "{0}пкс/“",
                                other = "{0}пкс/“",
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
                                many = "{0} точок на сантиметр",
                                other = "{0} точки на сантиметр",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} точка на см",
                                few = "{0} точки на см",
                                many = "{0} точок на см",
                                other = "{0} точки на см",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}тчк/см",
                                few = "{0}тчк/см",
                                many = "{0}тчк/см",
                                other = "{0}тчк/см",
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
                                many = "{0} точок на дюйм",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}тчк/“",
                                few = "{0}тчк/“",
                                many = "{0}тчк/“",
                                other = "{0}тчк/“",
                            )
                        ),
                    )
                ),
            )
        override val graphicsPixelPerMeter: MultiLengthGenderedPattern
            get() = TODO("PixelPerMeter formatter is not implemented yet for uk")
        override val lengthEarthRadius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} радіус Землі",
                                few = "{0} радіуси Землі",
                                many = "{0} радіусів Землі",
                                other = "{0} радіуса Землі",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}R⊕",
                                few = "{0}R⊕",
                                many = "{0}R⊕",
                                other = "{0}R⊕",
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
                                one = "{0} кілометр",
                                few = "{0} кілометри",
                                many = "{0} кілометрів",
                                other = "{0} кілометра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кілометр",
                                few = "{0} кілометри",
                                many = "{0} кілометрів",
                                other = "{0} кілометра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кілометру",
                                few = "{0} кілометрам",
                                many = "{0} кілометрам",
                                other = "{0} кілометра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кілометра",
                                few = "{0} кілометрів",
                                many = "{0} кілометрів",
                                other = "{0} кілометра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кілометром",
                                few = "{0} кілометрами",
                                many = "{0} кілометрами",
                                other = "{0} кілометра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кілометрі",
                                few = "{0} кілометрах",
                                many = "{0} кілометрах",
                                other = "{0} кілометра",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}км",
                                few = "{0}км",
                                many = "{0}км",
                                other = "{0}км",
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
                                few = "{0} метри",
                                many = "{0} метрів",
                                other = "{0} метра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} метр",
                                few = "{0} метри",
                                many = "{0} метрів",
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
                                few = "{0} метрів",
                                many = "{0} метрів",
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
                                one = "{0} метрі",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}м",
                                few = "{0}м",
                                many = "{0}м",
                                other = "{0}м",
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
                                few = "{0} дециметри",
                                many = "{0} дециметрів",
                                other = "{0} дециметра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} дециметр",
                                few = "{0} дециметри",
                                many = "{0} дециметрів",
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
                                few = "{0} дециметрів",
                                many = "{0} дециметрів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} дециметрі",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}дм",
                                few = "{0}дм",
                                many = "{0}дм",
                                other = "{0}дм",
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
                                few = "{0} сантиметри",
                                many = "{0} сантиметрів",
                                other = "{0} сантиметра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} сантиметр",
                                few = "{0} сантиметри",
                                many = "{0} сантиметрів",
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
                                few = "{0} сантиметрів",
                                many = "{0} сантиметрів",
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
                                one = "{0} сантиметрі",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}см",
                                few = "{0}см",
                                many = "{0}см",
                                other = "{0}см",
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
                                one = "{0} міліметр",
                                few = "{0} міліметри",
                                many = "{0} міліметрів",
                                other = "{0} міліметра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} міліметр",
                                few = "{0} міліметри",
                                many = "{0} міліметрів",
                                other = "{0} міліметра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} міліметру",
                                few = "{0} міліметрам",
                                many = "{0} міліметрам",
                                other = "{0} міліметра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} міліметра",
                                few = "{0} міліметрів",
                                many = "{0} міліметрів",
                                other = "{0} міліметра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} міліметром",
                                few = "{0} міліметрами",
                                many = "{0} міліметрами",
                                other = "{0} міліметра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} міліметрі",
                                few = "{0} міліметрах",
                                many = "{0} міліметрах",
                                other = "{0} міліметра",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мм",
                                few = "{0}мм",
                                many = "{0}мм",
                                other = "{0}мм",
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
                                one = "{0} мікрометр",
                                few = "{0} мікрометри",
                                many = "{0} мікрометрів",
                                other = "{0} мікрометра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мікрометр",
                                few = "{0} мікрометри",
                                many = "{0} мікрометрів",
                                other = "{0} мікрометра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мікрометру",
                                few = "{0} мікрометрам",
                                many = "{0} мікрометрам",
                                other = "{0} мікрометра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мікрометра",
                                few = "{0} мікрометрів",
                                many = "{0} мікрометрів",
                                other = "{0} мікрометра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мікрометром",
                                few = "{0} мікрометрами",
                                many = "{0} мікрометрами",
                                other = "{0} мікрометра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мікрометрі",
                                few = "{0} мікрометрах",
                                many = "{0} мікрометрах",
                                other = "{0} мікрометра",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мкм",
                                few = "{0}мкм",
                                many = "{0}мкм",
                                other = "{0}мкм",
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
                                few = "{0} нанометри",
                                many = "{0} нанометрів",
                                other = "{0} нанометра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} нанометр",
                                few = "{0} нанометри",
                                many = "{0} нанометрів",
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
                                few = "{0} нанометрів",
                                many = "{0} нанометрів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} нанометрі",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}нм",
                                few = "{0}нм",
                                many = "{0}нм",
                                other = "{0}нм",
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
                                one = "{0} пікометр",
                                few = "{0} пікометри",
                                many = "{0} пікометрів",
                                other = "{0} пікометра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} пікометр",
                                few = "{0} пікометри",
                                many = "{0} пікометрів",
                                other = "{0} пікометра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} пікометру",
                                few = "{0} пікометрам",
                                many = "{0} пікометрам",
                                other = "{0} пікометра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} пікометра",
                                few = "{0} пікометрів",
                                many = "{0} пікометрів",
                                other = "{0} пікометра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} пікометром",
                                few = "{0} пікометрами",
                                many = "{0} пікометрами",
                                other = "{0} пікометра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} пікометрі",
                                few = "{0} пікометрах",
                                many = "{0} пікометрах",
                                other = "{0} пікометра",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}пм",
                                few = "{0}пм",
                                many = "{0}пм",
                                other = "{0}пм",
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
                                one = "{0} миля",
                                few = "{0} милі",
                                many = "{0} миль",
                                other = "{0} милі",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мл",
                                few = "{0}мл",
                                many = "{0}мл",
                                other = "{0}мл",
                            )
                        ),
                    )
                ),
            )
        override val lengthYard
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ярд",
                                few = "{0} ярди",
                                many = "{0} ярдів",
                                other = "{0} ярда",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ярд",
                                few = "{0}ярд",
                                many = "{0}ярд",
                                other = "{0}ярд",
                            )
                        ),
                    )
                ),
            )
        override val lengthFoot
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фут",
                                few = "{0} фути",
                                many = "{0} футів",
                                other = "{0} фута",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}фт",
                                few = "{0}фт",
                                many = "{0}фт",
                                other = "{0}фт",
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
                                one = "{0} дюйм",
                                few = "{0} дюйми",
                                many = "{0} дюймів",
                                other = "{0} дюйма",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} парсек",
                                few = "{0} парсеки",
                                many = "{0} парсеків",
                                other = "{0} парсека",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}пк",
                                few = "{0}пк",
                                many = "{0}пк",
                                other = "{0}пк",
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
                                one = "{0} світловий рік",
                                few = "{0} світлові роки",
                                many = "{0} світлових років",
                                other = "{0} світлового року",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} св. р.",
                                few = "{0} св. р.",
                                many = "{0} св. р.",
                                other = "{0} св. р.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}св.р.",
                                few = "{0}св.р.",
                                many = "{0}св.р.",
                                other = "{0}св.р.",
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
                                one = "{0} астрономічна одиниця",
                                few = "{0} астрономічні одиниці",
                                many = "{0} астрономічних одиниць",
                                other = "{0} астрономічної одиниці",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} а. о.",
                                few = "{0} а. о.",
                                many = "{0} а. о.",
                                other = "{0} а. о.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}а.о.",
                                few = "{0}а.о.",
                                many = "{0}а.о.",
                                other = "{0}а.о.",
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
                                one = "{0} фурлонг",
                                few = "{0} фурлонги",
                                many = "{0} фурлонгів",
                                other = "{0} фурлонга",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фрл",
                                few = "{0} фрл",
                                many = "{0} фрл",
                                other = "{0} фрл",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}фрл",
                                few = "{0}фрл",
                                many = "{0}фрл",
                                other = "{0}фрл",
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
                                one = "{0} фатом",
                                few = "{0} фатоми",
                                many = "{0} фатомів",
                                other = "{0} фатома",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фтм",
                                few = "{0} фтм",
                                many = "{0} фтм",
                                other = "{0} фтм",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}фтм",
                                few = "{0}фтм",
                                many = "{0}фтм",
                                other = "{0}фтм",
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
                                one = "{0} морська миля",
                                few = "{0} морські милі",
                                many = "{0} морських миль",
                                other = "{0} морської милі",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} м. миля",
                                few = "{0} м. милі",
                                many = "{0} м. миль",
                                other = "{0} м. милі",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}м.мл",
                                few = "{0}м.мл",
                                many = "{0}м.мл",
                                other = "{0}м.мл",
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
                                one = "{0} скандинавська миля",
                                few = "{0} скандинавські милі",
                                many = "{0} скандинавських миль",
                                other = "{0} скандинавської милі",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} скандинавську милю",
                                few = "{0} скандинавські милі",
                                many = "{0} скандинавських миль",
                                other = "{0} скандинавської милі",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} скандинавській милі",
                                few = "{0} скандинавським милям",
                                many = "{0} скандинавським милям",
                                other = "{0} скандинавської милі",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} скандинавської милі",
                                few = "{0} скандинавських миль",
                                many = "{0} скандинавських миль",
                                other = "{0} скандинавської милі",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} скандинавською милею",
                                few = "{0} скандинавськими милями",
                                many = "{0} скандинавськими милями",
                                other = "{0} скандинавської милі",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} скандинавській милі",
                                few = "{0} скандинавських милях",
                                many = "{0} скандинавських милях",
                                other = "{0} скандинавської милі",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} сканд. миля",
                                few = "{0} сканд. милі",
                                many = "{0} сканд. миль",
                                other = "{0} сканд. милі",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ск.мл",
                                few = "{0}ск.мл",
                                many = "{0}ск.мл",
                                other = "{0}ск.мл",
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
                                one = "{0} пункт",
                                few = "{0} пункти",
                                many = "{0} пунктів",
                                other = "{0} пункту",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} пт",
                                few = "{0} пт",
                                many = "{0} пт",
                                other = "{0} пт",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}пт",
                                few = "{0}пт",
                                many = "{0}пт",
                                other = "{0}пт",
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
                                one = "{0} радіус Сонця",
                                few = "{0} радіуси Сонця",
                                many = "{0} радіусів Сонця",
                                other = "{0} радіуса Сонця",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}R☉",
                                few = "{0}R☉",
                                many = "{0}R☉",
                                other = "{0}R☉",
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
                                few = "{0} люкси",
                                many = "{0} люксів",
                                other = "{0} люкса",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} люкс",
                                few = "{0} люкси",
                                many = "{0} люксів",
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
                                few = "{0} люксів",
                                many = "{0} люксів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} люксі",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}лк",
                                few = "{0}лк",
                                many = "{0}лк",
                                other = "{0}лк",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaPerSquareMeter formatter is not implemented yet for uk")
        override val lightCandela
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Feminine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кандела",
                                few = "{0} кандели",
                                many = "{0} кандел",
                                other = "{0} кандели",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} канделу",
                                few = "{0} кандели",
                                many = "{0} кандел",
                                other = "{0} кандели",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} канделі",
                                few = "{0} канделам",
                                many = "{0} канделам",
                                other = "{0} кандели",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кандели",
                                few = "{0} кандел",
                                many = "{0} кандел",
                                other = "{0} кандели",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} канделою",
                                few = "{0} канделами",
                                many = "{0} канделами",
                                other = "{0} кандели",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} канделі",
                                few = "{0} канделах",
                                many = "{0} канделах",
                                other = "{0} кандели",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кд",
                                few = "{0}кд",
                                many = "{0}кд",
                                other = "{0}кд",
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
                                few = "{0} люмени",
                                many = "{0} люменів",
                                other = "{0} люмена",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} люмен",
                                few = "{0} люмени",
                                many = "{0} люменів",
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
                                few = "{0} люменів",
                                many = "{0} люменів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} люмені",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}лм",
                                few = "{0}лм",
                                many = "{0}лм",
                                other = "{0}лм",
                            )
                        ),
                    )
                ),
            )
        override val lightCandelaSquareMeterPerSquareMeter: MultiLengthGenderedPattern
            get() = TODO("CandelaSquareMeterPerSquareMeter formatter is not implemented yet for uk")
        override val lightSolarLuminosity
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} світність Сонця",
                                few = "{0} світності Сонця",
                                many = "{0} світностей Сонця",
                                other = "{0} світності Сонця",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}L☉",
                                few = "{0}L☉",
                                many = "{0}L☉",
                                other = "{0}L☉",
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
                                one = "{0} метрична тонна",
                                few = "{0} метричні тонни",
                                many = "{0} метричних тонн",
                                other = "{0} метричної тонни",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} метричну тонну",
                                few = "{0} метричні тонни",
                                many = "{0} метричних тонн",
                                other = "{0} метричної тонни",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} метричній тонні",
                                few = "{0} метричним тоннам",
                                many = "{0} метричним тоннам",
                                other = "{0} метричної тонни",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} метричної тонни",
                                few = "{0} метричних тонн",
                                many = "{0} метричних тонн",
                                other = "{0} метричної тонни",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} метричною тонною",
                                few = "{0} метричними тоннами",
                                many = "{0} метричними тоннами",
                                other = "{0} метричної тонни",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} метричній тонні",
                                few = "{0} метричних тоннах",
                                many = "{0} метричних тоннах",
                                other = "{0} метричної тонни",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} метр. т",
                                few = "{0} метр. т",
                                many = "{0} метр. т",
                                other = "{0} метр. т",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}т",
                                few = "{0}т",
                                many = "{0}т",
                                other = "{0}т",
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
                                one = "{0} кілограм",
                                few = "{0} кілограми",
                                many = "{0} кілограмів",
                                other = "{0} кілограма",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кілограм",
                                few = "{0} кілограми",
                                many = "{0} кілограмів",
                                other = "{0} кілограма",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кілограму",
                                few = "{0} кілограмам",
                                many = "{0} кілограмам",
                                other = "{0} кілограма",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кілограма",
                                few = "{0} кілограмів",
                                many = "{0} кілограмів",
                                other = "{0} кілограма",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кілограмом",
                                few = "{0} кілограмами",
                                many = "{0} кілограмами",
                                other = "{0} кілограма",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кілограмі",
                                few = "{0} кілограмах",
                                many = "{0} кілограмах",
                                other = "{0} кілограма",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кг",
                                few = "{0}кг",
                                many = "{0}кг",
                                other = "{0}кг",
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
                                one = "{0} грам",
                                few = "{0} грами",
                                many = "{0} грамів",
                                other = "{0} грама",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} грам",
                                few = "{0} грами",
                                many = "{0} грамів",
                                other = "{0} грама",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} граму",
                                few = "{0} грамам",
                                many = "{0} грамам",
                                other = "{0} грама",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} грама",
                                few = "{0} грамів",
                                many = "{0} грамів",
                                other = "{0} грама",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} грамом",
                                few = "{0} грамами",
                                many = "{0} грамами",
                                other = "{0} грама",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} грамі",
                                few = "{0} грамах",
                                many = "{0} грамах",
                                other = "{0} грама",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}г",
                                few = "{0}г",
                                many = "{0}г",
                                other = "{0}г",
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
                                one = "{0} міліграм",
                                few = "{0} міліграми",
                                many = "{0} міліграмів",
                                other = "{0} міліграма",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} міліграм",
                                few = "{0} міліграми",
                                many = "{0} міліграмів",
                                other = "{0} міліграма",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} міліграму",
                                few = "{0} міліграмам",
                                many = "{0} міліграмам",
                                other = "{0} міліграма",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} міліграма",
                                few = "{0} міліграмів",
                                many = "{0} міліграмів",
                                other = "{0} міліграма",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} міліграмом",
                                few = "{0} міліграмами",
                                many = "{0} міліграмами",
                                other = "{0} міліграма",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} міліграмі",
                                few = "{0} міліграмах",
                                many = "{0} міліграмах",
                                other = "{0} міліграма",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мг",
                                few = "{0}мг",
                                many = "{0}мг",
                                other = "{0}мг",
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
                                one = "{0} мікрограм",
                                few = "{0} мікрограми",
                                many = "{0} мікрограмів",
                                other = "{0} мікрограма",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мікрограм",
                                few = "{0} мікрограми",
                                many = "{0} мікрограмів",
                                other = "{0} мікрограма",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мікрограму",
                                few = "{0} мікрограмам",
                                many = "{0} мікрограмам",
                                other = "{0} мікрограма",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мікрограма",
                                few = "{0} мікрограмів",
                                many = "{0} мікрограмів",
                                other = "{0} мікрограма",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мікрограмом",
                                few = "{0} мікрограмами",
                                many = "{0} мікрограмами",
                                other = "{0} мікрограма",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мікрограмі",
                                few = "{0} мікрограмах",
                                many = "{0} мікрограмах",
                                other = "{0} мікрограма",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мкг",
                                few = "{0}мкг",
                                many = "{0}мкг",
                                other = "{0}мкг",
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
                                one = "{0} тонна",
                                few = "{0} тонни",
                                many = "{0} тонн",
                                other = "{0} тонни",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}т",
                                few = "{0}т",
                                many = "{0}т",
                                other = "{0}т",
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
                                one = "{0} стоун",
                                few = "{0} стоуни",
                                many = "{0} стоунів",
                                other = "{0} стоуна",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}стн",
                                few = "{0}стн",
                                many = "{0}стн",
                                other = "{0}стн",
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
                                one = "{0} фунт",
                                few = "{0} фунти",
                                many = "{0} фунтів",
                                other = "{0} фунта",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}фнт",
                                few = "{0}фнт",
                                many = "{0}фнт",
                                other = "{0}фнт",
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
                                one = "{0} унція",
                                few = "{0} унції",
                                many = "{0} унцій",
                                other = "{0} унції",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ун.",
                                few = "{0}ун.",
                                many = "{0}ун.",
                                other = "{0}ун.",
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
                                one = "{0} тройська унція",
                                few = "{0} тройські унції",
                                many = "{0} тройських унцій",
                                other = "{0} тройської унції",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} тр. унція",
                                few = "{0} тр. унції",
                                many = "{0} тр. унцій",
                                other = "{0} тр. унції",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}тр.ун.",
                                few = "{0}тр.ун.",
                                many = "{0}тр.ун.",
                                other = "{0}тр.ун.",
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
                                few = "{0} карати",
                                many = "{0} каратів",
                                other = "{0} карата",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} карат",
                                few = "{0} карати",
                                many = "{0} каратів",
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
                                few = "{0} каратів",
                                many = "{0} каратів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} караті",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кар",
                                few = "{0}кар",
                                many = "{0}кар",
                                other = "{0}кар",
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
                                one = "{0} дальтон",
                                few = "{0} дальтони",
                                many = "{0} дальтонів",
                                other = "{0} дальтона",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Да",
                                few = "{0}Да",
                                many = "{0}Да",
                                other = "{0}Да",
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
                                one = "{0} маса Землі",
                                few = "{0} маси Землі",
                                many = "{0} мас Землі",
                                other = "{0} маси Землі",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}M⊕",
                                few = "{0}M⊕",
                                many = "{0}M⊕",
                                other = "{0}M⊕",
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
                                one = "{0} маса Сонця",
                                few = "{0} маси Сонця",
                                many = "{0} мас Сонця",
                                other = "{0} маси Сонця",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}M☉",
                                few = "{0}M☉",
                                many = "{0}M☉",
                                other = "{0}M☉",
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
                                one = "{0} гран",
                                few = "{0} грани",
                                many = "{0} гранів",
                                other = "{0} грана",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}гр.",
                                few = "{0}гр.",
                                many = "{0}гр.",
                                other = "{0}гр.",
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
                                one = "{0} гігават",
                                few = "{0} гігавати",
                                many = "{0} гігаватів",
                                other = "{0} гігавата",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гігават",
                                few = "{0} гігавати",
                                many = "{0} гігаватів",
                                other = "{0} гігавата",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} гігавату",
                                few = "{0} гігаватам",
                                many = "{0} гігаватам",
                                other = "{0} гігавата",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} гігавата",
                                few = "{0} гігаватів",
                                many = "{0} гігаватів",
                                other = "{0} гігавата",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} гігаватом",
                                few = "{0} гігаватами",
                                many = "{0} гігаватами",
                                other = "{0} гігавата",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} гігаваті",
                                few = "{0} гігаватах",
                                many = "{0} гігаватах",
                                other = "{0} гігавата",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ГВт",
                                few = "{0}ГВт",
                                many = "{0}ГВт",
                                other = "{0}ГВт",
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
                                one = "{0} мегават",
                                few = "{0} мегавати",
                                many = "{0} мегаватів",
                                other = "{0} мегавата",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мегават",
                                few = "{0} мегавати",
                                many = "{0} мегаватів",
                                other = "{0} мегавата",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мегавату",
                                few = "{0} мегаватам",
                                many = "{0} мегаватам",
                                other = "{0} мегавата",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мегавата",
                                few = "{0} мегаватів",
                                many = "{0} мегаватів",
                                other = "{0} мегавата",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мегаватом",
                                few = "{0} мегаватами",
                                many = "{0} мегаватами",
                                other = "{0} мегавата",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мегаваті",
                                few = "{0} мегаватах",
                                many = "{0} мегаватах",
                                other = "{0} мегавата",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}МВт",
                                few = "{0}МВт",
                                many = "{0}МВт",
                                other = "{0}МВт",
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
                                one = "{0} кіловат",
                                few = "{0} кіловати",
                                many = "{0} кіловатів",
                                other = "{0} кіловата",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кіловат",
                                few = "{0} кіловати",
                                many = "{0} кіловатів",
                                other = "{0} кіловата",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кіловату",
                                few = "{0} кіловатам",
                                many = "{0} кіловатам",
                                other = "{0} кіловата",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кіловата",
                                few = "{0} кіловат",
                                many = "{0} кіловатів",
                                other = "{0} кіловата",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кіловатом",
                                few = "{0} кіловатами",
                                many = "{0} кіловатами",
                                other = "{0} кіловата",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кіловаті",
                                few = "{0} кіловатах",
                                many = "{0} кіловатах",
                                other = "{0} кіловата",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кВт",
                                few = "{0}кВт",
                                many = "{0}кВт",
                                other = "{0}кВт",
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
                                one = "{0} ват",
                                few = "{0} вати",
                                many = "{0} ватів",
                                other = "{0} вата",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ват",
                                few = "{0} вати",
                                many = "{0} ватів",
                                other = "{0} вата",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} вату",
                                few = "{0} ватам",
                                many = "{0} ватам",
                                other = "{0} вата",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} вата",
                                few = "{0} ватів",
                                many = "{0} ватів",
                                other = "{0} вата",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} ватом",
                                few = "{0} ватами",
                                many = "{0} ватами",
                                other = "{0} вата",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} ваті",
                                few = "{0} ватах",
                                many = "{0} ватах",
                                other = "{0} вата",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Вт",
                                few = "{0}Вт",
                                many = "{0}Вт",
                                other = "{0}Вт",
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
                                one = "{0} міліват",
                                few = "{0} мілівати",
                                many = "{0} міліватів",
                                other = "{0} мілівата",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} міліват",
                                few = "{0} мілівати",
                                many = "{0} міліватів",
                                other = "{0} мілівата",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мілівату",
                                few = "{0} міліватам",
                                many = "{0} міліватам",
                                other = "{0} мілівата",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мілівата",
                                few = "{0} міліватів",
                                many = "{0} міліватів",
                                other = "{0} мілівата",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} міліватом",
                                few = "{0} міліватами",
                                many = "{0} міліватами",
                                other = "{0} мілівата",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} міліваті",
                                few = "{0} міліватах",
                                many = "{0} міліватах",
                                other = "{0} мілівата",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мВт",
                                few = "{0}мВт",
                                many = "{0}мВт",
                                other = "{0}мВт",
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
                                one = "{0} кінська сила",
                                few = "{0} кінські сили",
                                many = "{0} кінських сил",
                                other = "{0} кінської сили",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} к. с.",
                                few = "{0} к. с.",
                                many = "{0} к. с.",
                                other = "{0} к. с.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}к.с.",
                                few = "{0}к.с.",
                                many = "{0}к.с.",
                                other = "{0}к.с.",
                            )
                        ),
                    )
                ),
            )
        override val powerKilogramSquareMeterPerCubicSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramSquareMeterPerCubicSecond formatter is not implemented yet for uk")
        override val pressureMillimeterOfhg
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} міліметр ртутного стовпа",
                                few = "{0} міліметри ртутного стовпа",
                                many = "{0} міліметрів ртутного стовпа",
                                other = "{0} міліметра ртутного стовпа",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ммрс",
                                few = "{0}ммрс",
                                many = "{0}ммрс",
                                other = "{0}ммрс",
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
                                one = "{0} фунт на квадратний дюйм",
                                few = "{0} фунти на квадратний дюйм",
                                many = "{0} фунтів на квадратний дюйм",
                                other = "{0} фунта на квадратний дюйм",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фунт/дюйм²",
                                few = "{0} фунти/дюйм²",
                                many = "{0} фунтів/дюйм²",
                                other = "{0} фунта/дюйм²",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}фнт/дюйм²",
                                few = "{0}фнт/дюйм²",
                                many = "{0}фнт/дюйм²",
                                other = "{0}фнт/дюйм²",
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
                                one = "{0} дюйм ртутного стовпа",
                                few = "{0} дюйми ртутного стовпа",
                                many = "{0} дюймів ртутного стовпа",
                                other = "{0} дюйма ртутного стовпа",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дюйм рт. ст.",
                                few = "{0} дюйми рт. ст.",
                                many = "{0} дюймів рт. ст.",
                                other = "{0} дюйма рт. ст.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}дмрс",
                                few = "{0}дмрс",
                                many = "{0}дмрс",
                                other = "{0}дмрс",
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
                                few = "{0} бари",
                                many = "{0} барів",
                                other = "{0} бара",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} бар",
                                few = "{0} бари",
                                many = "{0} барів",
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
                                few = "{0} барів",
                                many = "{0} барів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} барі",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}бар",
                                few = "{0}бар",
                                many = "{0}бар",
                                other = "{0}бар",
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
                                one = "{0} мілібар",
                                few = "{0} мілібари",
                                many = "{0} мілібарів",
                                other = "{0} мілібара",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мілібар",
                                few = "{0} мілібари",
                                many = "{0} мілібарів",
                                other = "{0} мілібара",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мілібару",
                                few = "{0} мілібарам",
                                many = "{0} мілібарам",
                                other = "{0} мілібара",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мілібара",
                                few = "{0} мілібарів",
                                many = "{0} мілібарів",
                                other = "{0} мілібара",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мілібаром",
                                few = "{0} мілібарами",
                                many = "{0} мілібарами",
                                other = "{0} мілібара",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мілібарі",
                                few = "{0} мілібарах",
                                many = "{0} мілібарах",
                                other = "{0} мілібара",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мбар",
                                few = "{0} мбари",
                                many = "{0} мбарів",
                                other = "{0} мбара",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мбар",
                                few = "{0}мбар",
                                many = "{0}мбар",
                                other = "{0}мбар",
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
                                few = "{0} атмосфери",
                                many = "{0} атмосфер",
                                other = "{0} атмосфери",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} атмосферу",
                                few = "{0} атмосфери",
                                many = "{0} атмосфер",
                                other = "{0} атмосфери",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} атмосфері",
                                few = "{0} атмосферам",
                                many = "{0} атмосферам",
                                other = "{0} атмосфери",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} атмосфери",
                                few = "{0} атмосфер",
                                many = "{0} атмосфер",
                                other = "{0} атмосфери",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} атмосферою",
                                few = "{0} атмосферами",
                                many = "{0} атмосферами",
                                other = "{0} атмосфери",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} атмосфері",
                                few = "{0} атмосферах",
                                many = "{0} атмосферах",
                                other = "{0} атмосфери",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}атм",
                                few = "{0}атм",
                                many = "{0}атм",
                                other = "{0}атм",
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
                                few = "{0} паскалі",
                                many = "{0} паскалів",
                                other = "{0} паскаля",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} паскаль",
                                few = "{0} паскалі",
                                many = "{0} паскалів",
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
                                few = "{0} паскалів",
                                many = "{0} паскалів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} паскалі",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Па",
                                few = "{0}Па",
                                many = "{0}Па",
                                other = "{0}Па",
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
                                few = "{0} гектопаскалі",
                                many = "{0} гектопаскалів",
                                other = "{0} гектопаскаля",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гектопаскаль",
                                few = "{0} гектопаскалі",
                                many = "{0} гектопаскалів",
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
                                few = "{0} гектопаскалів",
                                many = "{0} гектопаскалів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} гектопаскалі",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}гПа",
                                few = "{0}гПа",
                                many = "{0}гПа",
                                other = "{0}гПа",
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
                                one = "{0} кілопаскаль",
                                few = "{0} кілопаскалі",
                                many = "{0} кілопаскалів",
                                other = "{0} кілопаскаля",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кілопаскаль",
                                few = "{0} кілопаскалі",
                                many = "{0} кілопаскалів",
                                other = "{0} кілопаскаля",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кілопаскалю",
                                few = "{0} кілопаскалям",
                                many = "{0} кілопаскалям",
                                other = "{0} кілопаскаля",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кілопаскаля",
                                few = "{0} кілопаскалів",
                                many = "{0} кілопаскалів",
                                other = "{0} кілопаскаля",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кілопаскалем",
                                few = "{0} кілопаскалями",
                                many = "{0} кілопаскалями",
                                other = "{0} кілопаскаля",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кілопаскалі",
                                few = "{0} кілопаскалях",
                                many = "{0} кілопаскалях",
                                other = "{0} кілопаскаля",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кПа",
                                few = "{0}кПа",
                                many = "{0}кПа",
                                other = "{0}кПа",
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
                                few = "{0} мегапаскалі",
                                many = "{0} мегапаскалів",
                                other = "{0} мегапаскаля",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мегапаскаль",
                                few = "{0} мегапаскалі",
                                many = "{0} мегапаскалів",
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
                                few = "{0} мегапаскалів",
                                many = "{0} мегапаскалів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мегапаскалі",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}МПа",
                                few = "{0}МПа",
                                many = "{0}МПа",
                                other = "{0}МПа",
                            )
                        ),
                    )
                ),
            )
        override val pressureGasolineEnergyDensity: MultiLengthGenderedPattern
            get() = TODO("GasolineEnergyDensity formatter is not implemented yet for uk")
        override val pressureKilogramPerMeterSquareSecond: MultiLengthGenderedPattern
            get() = TODO("KilogramPerMeterSquareSecond formatter is not implemented yet for uk")
        override val speedKilometerPerHour
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} кілометр за годину",
                                few = "{0} кілометри за годину",
                                many = "{0} кілометрів за годину",
                                other = "{0} кілометра за годину",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кілометр за годину",
                                few = "{0} кілометри за годину",
                                many = "{0} кілометрів за годину",
                                other = "{0} кілометра за годину",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кілометру за годину",
                                few = "{0} кілометрам за годину",
                                many = "{0} кілометрам за годину",
                                other = "{0} кілометра за годину",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кілометра за годину",
                                few = "{0} кілометрів за годину",
                                many = "{0} кілометрів за годину",
                                other = "{0} кілометра за годину",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кілометром за годину",
                                few = "{0} кілометрами за годину",
                                many = "{0} кілометрами за годину",
                                other = "{0} кілометра за годину",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кілометрі за годину",
                                few = "{0} кілометрах за годину",
                                many = "{0} кілометрах за годину",
                                other = "{0} кілометра за годину",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} км/год",
                                few = "{0} км/год",
                                many = "{0} км/год",
                                other = "{0} км/год",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}км/год",
                                few = "{0}км/год",
                                many = "{0}км/год",
                                other = "{0}км/год",
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
                                one = "{0} метр на секунду",
                                few = "{0} метри на секунду",
                                many = "{0} метрів на секунду",
                                other = "{0} метра на секунду",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} метр на секунду",
                                few = "{0} метри на секунду",
                                many = "{0} метрів на секунду",
                                other = "{0} метра на секунду",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} метру на секунду",
                                few = "{0} метрам на секунду",
                                many = "{0} метрам на секунду",
                                other = "{0} метра на секунду",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} метра на секунду",
                                few = "{0} метрів на секунду",
                                many = "{0} метрів на секунду",
                                other = "{0} метра на секунду",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} метром на секунду",
                                few = "{0} метрами на секунду",
                                many = "{0} метрами на секунду",
                                other = "{0} метра на секунду",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} метрі на секунду",
                                few = "{0} метрах на секунду",
                                many = "{0} метрах на секунду",
                                other = "{0} метра на секунду",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}м/с",
                                few = "{0}м/с",
                                many = "{0}м/с",
                                other = "{0}м/с",
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
                                one = "{0} миля на годину",
                                few = "{0} милі на годину",
                                many = "{0} миль на годину",
                                other = "{0} милі на годину",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} миля/год",
                                few = "{0} милі/год",
                                many = "{0} миль/год",
                                other = "{0} милі/год",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мл/год",
                                few = "{0}мл/год",
                                many = "{0}мл/год",
                                other = "{0}мл/год",
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
                                one = "{0} вузол",
                                few = "{0} вузли",
                                many = "{0} вузлів",
                                other = "{0} вузла",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} вуз.",
                                few = "{0} вуз.",
                                many = "{0} вуз.",
                                other = "{0} вуз.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}вуз.",
                                few = "{0}вуз.",
                                many = "{0}вуз.",
                                other = "{0}вуз.",
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
                                one = "{0} бал за шкалою Бофорта",
                                few = "{0} бали за шкалою Бофорта",
                                many = "{0} балів за шкалою Бофорта",
                                other = "{0} бала за шкалою Бофорта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} бал Бофорта",
                                few = "{0} бали Бофорта",
                                many = "{0} балів Бофорта",
                                other = "{0} бала Бофорта",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} бал",
                                few = "{0} бали",
                                many = "{0} балів",
                                other = "{0} бала",
                            )
                        ),
                    )
                ),
            )
        override val temperatureGeneric: MultiLengthGenderedPattern
            get() = TODO("Generic formatter is not implemented yet for uk")
        override val temperatureCelsius
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    gender = Gender.Masculine,
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} градус Цельсія",
                                few = "{0} градуси Цельсія",
                                many = "{0} градусів Цельсія",
                                other = "{0} градуса Цельсія",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} градус Цельсія",
                                few = "{0} градуси Цельсія",
                                many = "{0} градусів Цельсія",
                                other = "{0} градуса Цельсія",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} градусу Цельсія",
                                few = "{0} градусам Цельсія",
                                many = "{0} градусам Цельсія",
                                other = "{0} градуса Цельсія",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} градуса Цельсія",
                                few = "{0} градусів Цельсія",
                                many = "{0} градусів Цельсія",
                                other = "{0} градуса Цельсія",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} градусом Цельсія",
                                few = "{0} градусами Цельсія",
                                many = "{0} градусами Цельсія",
                                other = "{0} градуса Цельсія",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} градусі Цельсія",
                                few = "{0} градусах Цельсія",
                                many = "{0} градусах Цельсія",
                                other = "{0} градуса Цельсія",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} °C",
                                few = "{0} °C",
                                many = "{0} °C",
                                other = "{0} °C",
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} градус Фаренгейта",
                                few = "{0} градуси Фаренгейта",
                                many = "{0} градусів Фаренгейта",
                                other = "{0} градуса Фаренгейта",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} °F",
                                few = "{0} °F",
                                many = "{0} °F",
                                other = "{0} °F",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}°F",
                                few = "{0}°F",
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
                                one = "{0} кельвін",
                                few = "{0} кельвіни",
                                many = "{0} кельвінів",
                                other = "{0} кельвіна",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кельвін",
                                few = "{0} кельвіни",
                                many = "{0} кельвінів",
                                other = "{0} кельвіна",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кельвіну",
                                few = "{0} кельвінам",
                                many = "{0} кельвінам",
                                other = "{0} кельвіна",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кельвіна",
                                few = "{0} кельвінів",
                                many = "{0} кельвінів",
                                other = "{0} кельвіна",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кельвіном",
                                few = "{0} кельвінами",
                                many = "{0} кельвінами",
                                other = "{0} кельвіна",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кельвіні",
                                few = "{0} кельвінах",
                                many = "{0} кельвінах",
                                other = "{0} кельвіна",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} К",
                                few = "{0} К",
                                many = "{0} К",
                                other = "{0} К",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}К",
                                few = "{0}К",
                                many = "{0}К",
                                other = "{0}К",
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
                                few = "{0} фунт-фути",
                                many = "{0} фунт-футів",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}фнт-фт",
                                few = "{0}фнт-фт",
                                many = "{0}фнт-фт",
                                other = "{0}фнт-фт",
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
                                few = "{0} ньютон-метри",
                                many = "{0} ньютон-метрів",
                                other = "{0} ньютон-метра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} ньютон-метр",
                                few = "{0} ньютон-метри",
                                many = "{0} ньютон-метрів",
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
                                few = "{0} ньютон-метрів",
                                many = "{0} ньютон-метрів",
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
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} ньютон-метрі",
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
                                one = "{0} Н·м",
                                few = "{0} Н·м",
                                many = "{0} Н·м",
                                other = "{0} Н·м",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Н·м",
                                few = "{0}Н·м",
                                many = "{0}Н·м",
                                other = "{0}Н·м",
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
                                one = "{0} кубічний кілометр",
                                few = "{0} кубічні кілометри",
                                many = "{0} кубічних кілометрів",
                                other = "{0} кубічного кілометра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кубічний кілометр",
                                few = "{0} кубічні кілометри",
                                many = "{0} кубічних кілометрів",
                                other = "{0} кубічного кілометра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кубічному кілометру",
                                few = "{0} кубічним кілометрам",
                                many = "{0} кубічним кілометрам",
                                other = "{0} кубічного кілометра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кубічного кілометра",
                                few = "{0} кубічних кілометрів",
                                many = "{0} кубічних кілометрів",
                                other = "{0} кубічного кілометра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кубічним кілометром",
                                few = "{0} кубічними кілометрами",
                                many = "{0} кубічними кілометрами",
                                other = "{0} кубічного кілометра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кубічному кілометрі",
                                few = "{0} кубічних кілометрах",
                                many = "{0} кубічних кілометрах",
                                other = "{0} кубічного кілометра",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}км³",
                                few = "{0}км³",
                                many = "{0}км³",
                                other = "{0}км³",
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
                                one = "{0} кубічний метр",
                                few = "{0} кубічні метри",
                                many = "{0} кубічних метрів",
                                other = "{0} кубічного метра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кубічний метр",
                                few = "{0} кубічні метри",
                                many = "{0} кубічних метрів",
                                other = "{0} кубічного метра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кубічному метру",
                                few = "{0} кубічним метрам",
                                many = "{0} кубічним метрам",
                                other = "{0} кубічного метра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кубічного метра",
                                few = "{0} кубічних метрів",
                                many = "{0} кубічних метрів",
                                other = "{0} кубічного метра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кубічним метром",
                                few = "{0} кубічними метрами",
                                many = "{0} кубічними метрами",
                                other = "{0} кубічного метра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кубічному метрі",
                                few = "{0} кубічних метрах",
                                many = "{0} кубічних метрах",
                                other = "{0} кубічного метра",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}м³",
                                few = "{0}м³",
                                many = "{0}м³",
                                other = "{0}м³",
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
                                one = "{0} кубічний сантиметр",
                                few = "{0} кубічні сантиметри",
                                many = "{0} кубічних сантиметрів",
                                other = "{0} кубічного сантиметра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} кубічний сантиметр",
                                few = "{0} кубічні сантиметри",
                                many = "{0} кубічних сантиметрів",
                                other = "{0} кубічного сантиметра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} кубічному сантиметру",
                                few = "{0} кубічним сантиметрам",
                                many = "{0} кубічним сантиметрам",
                                other = "{0} кубічного сантиметра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} кубічного сантиметра",
                                few = "{0} кубічних сантиметрів",
                                many = "{0} кубічних сантиметрів",
                                other = "{0} кубічного сантиметра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} кубічним сантиметром",
                                few = "{0} кубічними сантиметрами",
                                many = "{0} кубічними сантиметрами",
                                other = "{0} кубічного сантиметра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} кубічному сантиметрі",
                                few = "{0} кубічних сантиметрах",
                                many = "{0} кубічних сантиметрах",
                                other = "{0} кубічного сантиметра",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}см³",
                                few = "{0}см³",
                                many = "{0}см³",
                                other = "{0}см³",
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
                                one = "{0} кубічна миля",
                                few = "{0} кубічні милі",
                                many = "{0} кубічних миль",
                                other = "{0} кубічної милі",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} миля³",
                                few = "{0} милі³",
                                many = "{0} миль³",
                                other = "{0} милі³",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мл³",
                                few = "{0}мл³",
                                many = "{0}мл³",
                                other = "{0}мл³",
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
                                one = "{0} кубічний ярд",
                                few = "{0} кубічні ярди",
                                many = "{0} кубічних ярдів",
                                other = "{0} кубічного ярда",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ярд³",
                                few = "{0} ярди³",
                                many = "{0} ярдів³",
                                other = "{0} ярда³",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ярд³",
                                few = "{0}ярд³",
                                many = "{0}ярд³",
                                other = "{0}ярд³",
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
                                one = "{0} кубічний фут",
                                few = "{0} кубічні фути",
                                many = "{0} кубічних футів",
                                other = "{0} кубічного фута",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} фут³",
                                few = "{0} фт³",
                                many = "{0} фт³",
                                other = "{0} фт³",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}фт³",
                                few = "{0}фт³",
                                many = "{0}фт³",
                                other = "{0}фт³",
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
                                one = "{0} кубічний дюйм",
                                few = "{0} кубічні дюйми",
                                many = "{0} кубічних дюймів",
                                other = "{0} кубічного дюйма",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дюйм³",
                                few = "{0} дюйми³",
                                many = "{0} дюймів³",
                                other = "{0} дюйма³",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}дюйм³",
                                few = "{0}дюйм³",
                                many = "{0}дюйм³",
                                other = "{0}дюйм³",
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
                                one = "{0} мегалітр",
                                few = "{0} мегалітри",
                                many = "{0} мегалітрів",
                                other = "{0} мегалітра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мегалітр",
                                few = "{0} мегалітри",
                                many = "{0} мегалітрів",
                                other = "{0} мегалітра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мегалітру",
                                few = "{0} мегалітрам",
                                many = "{0} мегалітрам",
                                other = "{0} мегалітра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мегалітра",
                                few = "{0} мегалітрів",
                                many = "{0} мегалітрів",
                                other = "{0} мегалітра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мегалітром",
                                few = "{0} мегалітрами",
                                many = "{0} мегалітрами",
                                other = "{0} мегалітра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мегалітрі",
                                few = "{0} мегалітрах",
                                many = "{0} мегалітрах",
                                other = "{0} мегалітра",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}Мл",
                                few = "{0}Мл",
                                many = "{0}Мл",
                                other = "{0}Мл",
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
                                one = "{0} гектолітр",
                                few = "{0} гектолітри",
                                many = "{0} гектолітрів",
                                other = "{0} гектолітра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} гектолітр",
                                few = "{0} гектолітри",
                                many = "{0} гектолітрів",
                                other = "{0} гектолітра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} гектолітру",
                                few = "{0} гектолітрам",
                                many = "{0} гектолітрам",
                                other = "{0} гектолітра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} гектолітра",
                                few = "{0} гектолітрів",
                                many = "{0} гектолітрів",
                                other = "{0} гектолітра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} гектолітром",
                                few = "{0} гектолітрами",
                                many = "{0} гектолітрами",
                                other = "{0} гектолітра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} гектолітрі",
                                few = "{0} гектолітрах",
                                many = "{0} гектолітрах",
                                other = "{0} гектолітра",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}гл",
                                few = "{0}гл",
                                many = "{0}гл",
                                other = "{0}гл",
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
                                one = "{0} літр",
                                few = "{0} літри",
                                many = "{0} літрів",
                                other = "{0} літра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} літр",
                                few = "{0} літри",
                                many = "{0} літрів",
                                other = "{0} літра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} літру",
                                few = "{0} літрам",
                                many = "{0} літрам",
                                other = "{0} літра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} літра",
                                few = "{0} літрів",
                                many = "{0} літрів",
                                other = "{0} літра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} літром",
                                few = "{0} літрами",
                                many = "{0} літрами",
                                other = "{0} літра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} літрі",
                                few = "{0} літрах",
                                many = "{0} літрах",
                                other = "{0} літра",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}л",
                                few = "{0}л",
                                many = "{0}л",
                                other = "{0}л",
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
                                one = "{0} децилітр",
                                few = "{0} децилітри",
                                many = "{0} децилітрів",
                                other = "{0} децилітра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} децилітр",
                                few = "{0} децилітри",
                                many = "{0} децилітрів",
                                other = "{0} децилітра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} децилітру",
                                few = "{0} децилітрам",
                                many = "{0} децилітрам",
                                other = "{0} децилітра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} децилітра",
                                few = "{0} децилітрів",
                                many = "{0} децилітрів",
                                other = "{0} децилітра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} децилітром",
                                few = "{0} децилітрами",
                                many = "{0} децилітрами",
                                other = "{0} децилітра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} децилітрі",
                                few = "{0} децилітрах",
                                many = "{0} децилітрах",
                                other = "{0} децилітра",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}дл",
                                few = "{0}дл",
                                many = "{0}дл",
                                other = "{0}дл",
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
                                one = "{0} сантилітр",
                                few = "{0} сантилітри",
                                many = "{0} сантилітрів",
                                other = "{0} сантилітра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} сантилітр",
                                few = "{0} сантилітри",
                                many = "{0} сантилітрів",
                                other = "{0} сантилітра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} сантилітру",
                                few = "{0} сантилітрам",
                                many = "{0} сантилітрам",
                                other = "{0} сантилітра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} сантилітра",
                                few = "{0} сантилітрів",
                                many = "{0} сантилітрів",
                                other = "{0} сантилітра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} сантилітром",
                                few = "{0} сантилітрами",
                                many = "{0} сантилітрами",
                                other = "{0} сантилітра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} сантилітрі",
                                few = "{0} сантилітрах",
                                many = "{0} сантилітрах",
                                other = "{0} сантилітра",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}сл",
                                few = "{0}сл",
                                many = "{0}сл",
                                other = "{0}сл",
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
                                one = "{0} мілілітр",
                                few = "{0} мілілітри",
                                many = "{0} мілілітрів",
                                other = "{0} мілілітра",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} мілілітр",
                                few = "{0} мілілітри",
                                many = "{0} мілілітрів",
                                other = "{0} мілілітра",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} мілілітру",
                                few = "{0} мілілітрам",
                                many = "{0} мілілітрам",
                                other = "{0} мілілітра",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} мілілітра",
                                few = "{0} мілілітрів",
                                many = "{0} мілілітрів",
                                other = "{0} мілілітра",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} мілілітром",
                                few = "{0} мілілітрами",
                                many = "{0} мілілітрами",
                                other = "{0} мілілітра",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} мілілітрі",
                                few = "{0} мілілітрах",
                                many = "{0} мілілітрах",
                                other = "{0} мілілітра",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мл",
                                few = "{0}мл",
                                many = "{0}мл",
                                other = "{0}мл",
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
                                one = "{0} метрична пінта",
                                few = "{0} метричні пінти",
                                many = "{0} метричних пінт",
                                other = "{0} метричної пінти",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} метричну пінту",
                                few = "{0} метричні пінти",
                                many = "{0} метричних пінт",
                                other = "{0} метричної пінти",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} метричній пінті",
                                few = "{0} метричним пінтам",
                                many = "{0} метричним пінтам",
                                other = "{0} метричної пінти",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} метричної пінти",
                                few = "{0} метричних пінт",
                                many = "{0} метричних пінт",
                                other = "{0} метричної пінти",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} метричною пінтою",
                                few = "{0} метричними пінтами",
                                many = "{0} метричними пінтами",
                                other = "{0} метричної пінти",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} метричній пінті",
                                few = "{0} метричних пінтах",
                                many = "{0} метричних пінтах",
                                other = "{0} метричної пінти",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} метр. пінта",
                                few = "{0} метр. пінти",
                                many = "{0} метр. пінт",
                                other = "{0} метр. пінти",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}м.пінт.",
                                few = "{0}м.пінт.",
                                many = "{0}м.пінт.",
                                other = "{0}м.пінт.",
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
                                one = "{0} метрична чашка",
                                few = "{0} метричні чашки",
                                many = "{0} метричних чашок",
                                other = "{0} метричної чашки",
                            )
                        ),
                        accusative = PluralPattern(
                            Plurals(
                                one = "{0} метричну чашку",
                                few = "{0} метричні чашки",
                                many = "{0} метричних чашок",
                                other = "{0} метричної чашки",
                            )
                        ),
                        dative = PluralPattern(
                            Plurals(
                                one = "{0} метричній чашці",
                                few = "{0} метричним чашкам",
                                many = "{0} метричним чашкам",
                                other = "{0} метричної чашки",
                            )
                        ),
                        genitive = PluralPattern(
                            Plurals(
                                one = "{0} метричної чашки",
                                few = "{0} метричних чашок",
                                many = "{0} метричних чашок",
                                other = "{0} метричної чашки",
                            )
                        ),
                        instrumental = PluralPattern(
                            Plurals(
                                one = "{0} метричною чашкою",
                                few = "{0} метричними чашками",
                                many = "{0} метричними чашками",
                                other = "{0} метричної чашки",
                            )
                        ),
                        locative = PluralPattern(
                            Plurals(
                                one = "{0} метричній чашці",
                                few = "{0} метричних чашках",
                                many = "{0} метричних чашках",
                                other = "{0} метричної чашки",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} метр. чашка",
                                few = "{0} метр. чашки",
                                many = "{0} метр. чашок",
                                other = "{0} метр. чашки",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}м.чаш.",
                                few = "{0}м.чаш.",
                                many = "{0}м.чаш.",
                                other = "{0}м.чаш.",
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
                                one = "{0} акр-фут",
                                few = "{0} акр-фути",
                                many = "{0} акр-футів",
                                other = "{0} акр-фута",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} акр-фт",
                                few = "{0} акр-фт",
                                many = "{0} акр-фт",
                                other = "{0} акр-фт",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}акр-фт",
                                few = "{0}акр-фт",
                                many = "{0}акр-фт",
                                other = "{0}акр-фт",
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
                                few = "{0} бушелі",
                                many = "{0} бушелів",
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
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}буш",
                                few = "{0}буш",
                                many = "{0}буш",
                                other = "{0}буш",
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
                                one = "{0} галон",
                                few = "{0} галони",
                                many = "{0} галонів",
                                other = "{0} галона",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} гал.",
                                few = "{0} гал.",
                                many = "{0} гал.",
                                other = "{0} гал.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}гал.",
                                few = "{0}гал.",
                                many = "{0}гал.",
                                other = "{0}гал.",
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
                                one = "{0} англійський галон",
                                few = "{0} англійські галони",
                                many = "{0} англійських галонів",
                                other = "{0} англійського галона",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} англ. галон",
                                few = "{0} англ. галони",
                                many = "{0} англ. галонів",
                                other = "{0} англ. галона",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}англ.гал.",
                                few = "{0}англ.гал.",
                                many = "{0}англ.гал.",
                                other = "{0}англ.гал.",
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
                                one = "{0} кварта",
                                few = "{0} кварти",
                                many = "{0} кварт",
                                other = "{0} кварти",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} квар.",
                                few = "{0} квар.",
                                many = "{0} квар.",
                                other = "{0} квар.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}квар.",
                                few = "{0}квар.",
                                many = "{0}квар.",
                                other = "{0}квар.",
                            )
                        ),
                    )
                ),
            )
        override val volumePint
            get() = MultiLengthGenderedPattern(
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} пінта",
                                few = "{0} пінти",
                                many = "{0} пінт",
                                other = "{0} пінти",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}пінт.",
                                few = "{0}пінт.",
                                many = "{0}пінт.",
                                other = "{0}пінт.",
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
                                one = "{0} чашка",
                                few = "{0} чашки",
                                many = "{0} чашок",
                                other = "{0} чашки",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} чаш.",
                                few = "{0} чаш.",
                                many = "{0} чаш.",
                                other = "{0} чаш.",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}чаш.",
                                few = "{0}чаш.",
                                many = "{0}чаш.",
                                other = "{0}чаш.",
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
                                one = "{0} рідинна унція",
                                few = "{0} рідинні унції",
                                many = "{0} рідинних унцій",
                                other = "{0} рідинної унції",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} рід. унція",
                                few = "{0} рід. унції",
                                many = "{0} рід. унцій",
                                other = "{0} рід. унції",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}рід.ун.",
                                few = "{0}рід.ун.",
                                many = "{0}рід.ун.",
                                other = "{0}рід.ун.",
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
                                one = "{0} англійська рідинна унція",
                                few = "{0} англійські рідинні унції",
                                many = "{0} англійських рідинних унцій",
                                other = "{0} англійської рідинної унції",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} англ. рід. унція",
                                few = "{0} англ. рід. унції",
                                many = "{0} англ. рід. унцій",
                                other = "{0} англ. рід. унції",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}англ.рід.ун.",
                                few = "{0}англ.рід.ун.",
                                many = "{0}англ.рід.ун.",
                                other = "{0}англ.рід.ун.",
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
                                one = "{0} столова ложка",
                                few = "{0} столові ложки",
                                many = "{0} столових ложок",
                                other = "{0} столової ложки",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ст. ложка",
                                few = "{0} ст. ложки",
                                many = "{0} ст. ложок",
                                other = "{0} ст. ложки",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ст.л.",
                                few = "{0}ст.л.",
                                many = "{0}ст.л.",
                                other = "{0}ст.л.",
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
                                one = "{0} чайна ложка",
                                few = "{0} чайні ложки",
                                many = "{0} чайних ложок",
                                other = "{0} чайної ложки",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} ч. ложка",
                                few = "{0} ч. ложки",
                                many = "{0} ч. ложок",
                                other = "{0} ч. ложки",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}ч.л.",
                                few = "{0}ч.л.",
                                many = "{0}ч.л.",
                                other = "{0}ч.л.",
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
                                one = "{0} барель",
                                few = "{0} барелі",
                                many = "{0} барелів",
                                other = "{0} бареля",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} брл",
                                few = "{0} брл",
                                many = "{0} брл",
                                other = "{0} брл",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}брл",
                                few = "{0}брл",
                                many = "{0}брл",
                                other = "{0}брл",
                            )
                        ),
                    )
                ),
            )
        override val volumeDessertSpoon
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} десертна ложка",
                                few = "{0} десертні ложки",
                                many = "{0} десертних ложок",
                                other = "{0} десертної ложки",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} дес. ложка",
                                few = "{0} дес. ложки",
                                many = "{0} дес. ложок",
                                other = "{0} дес. ложки",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
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
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} англійська десертна ложка",
                                few = "{0} англійські десертні ложки",
                                many = "{0} англійських десертних ложок",
                                other = "{0} англійської десертної ложки",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} англ. дес. ложка",
                                few = "{0} англ. дес. ложки",
                                many = "{0} англ. дес. ложок",
                                other = "{0} англ. дес. ложки",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} англ. дес. л.",
                                few = "{0} англ. дес. л.",
                                many = "{0} англ. дес. л.",
                                other = "{0} англ. дес. л.",
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
                                one = "{0} крапля",
                                few = "{0} краплі",
                                many = "{0} крапель",
                                other = "{0} краплі",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}кр.",
                                few = "{0}кр.",
                                many = "{0}кр.",
                                other = "{0}кр.",
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
                                one = "{0} рідинна драхма",
                                few = "{0} рідинні драхми",
                                many = "{0} рідинних драхм",
                                other = "{0} рідинної драхми",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} рід. драхма",
                                few = "{0} рід. драхми",
                                many = "{0} рід. драхм",
                                other = "{0} рід. драхми",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}рід.дрхм",
                                few = "{0}рід.дрхм",
                                many = "{0}рід.дрхм",
                                other = "{0}рід.дрхм",
                            )
                        ),
                    )
                ),
            )
        override val volumeJigger
            get() = MultiLengthGenderedPattern(
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мірна склянка",
                                few = "{0} мірні склянки",
                                many = "{0} мірних склянок",
                                other = "{0} мірна склянка",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} мірка",
                                few = "{0} мірки",
                                many = "{0} мірок",
                                other = "{0} мірки",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}мір.",
                                few = "{0}мір.",
                                many = "{0}мір.",
                                other = "{0}мір.",
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
                                one = "{0} дрібка",
                                few = "{0} дрібки",
                                many = "{0} дрібок",
                                other = "{0} дрібки",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0}дріб.",
                                few = "{0}дріб.",
                                many = "{0}дріб.",
                                other = "{0}дріб.",
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
                                one = "{0} англійська кварта",
                                few = "{0} англійські кварти",
                                many = "{0} англійських кварт",
                                other = "{0} англійської кварти",
                            )
                        ),
                    )
                ),
                short = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} англ. кварта",
                                few = "{0} англ. кварти",
                                many = "{0} англ. кварт",
                                other = "{0} англ. кварти",
                            )
                        ),
                    )
                ),
                narrow = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} англ. квар.",
                                few = "{0} англ. квар.",
                                many = "{0} англ. квар.",
                                other = "{0} англ. квар.",
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
            exponentialSymbol = "Е",
        )
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
