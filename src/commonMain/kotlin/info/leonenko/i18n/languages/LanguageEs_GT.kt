package info.leonenko.i18n.languages

import info.leonenko.i18n.*

val Language.Companion.es_GT
    get() = languageEs_GT
private val languageEs_GT = LanguageEs_GT()
class LanguageEs_GT internal constructor() : LanguageEs() {
    override val code: String = "es_GT"
    override val script: String? = null
    override val territory: String = "GT"

    class EllipsisLanguage internal constructor() : LanguageEs.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEs.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEs.MeasurementUnitLanguage() {
        override val electricAmpere
            get() = MultiLengthGenderedPattern(
                base = super.electricAmpere,
                long = GenderedPattern(
                    cases = Cases(
                        nominative = PluralPattern(
                            Plurals(
                                one = "{0} amperio",
                                other = "{0} amperios",
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
                                one = "{0} miliamperio",
                                other = "{0} miliamperios",
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
                                one = "{0} ohmio",
                                other = "{0} ohmios",
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
                                one = "{0} kilojulio",
                                other = "{0} kilojulios",
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
                                one = "{0} julio",
                                other = "{0} julios",
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
                                one = "{0} caballos de fuerza",
                                other = "{0} caballos de fuerza",
                            )
                        ),
                    )
                ),
            )
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageEs.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
