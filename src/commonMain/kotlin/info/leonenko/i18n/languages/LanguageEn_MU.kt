package info.leonenko.i18n.languages

import info.leonenko.i18n.Language

val Language.Companion.en_MU
    get() = languageEn_MU
private val languageEn_MU = LanguageEn_MU()

class LanguageEn_MU internal constructor() : LanguageEn() {
    override val code: String = "en_MU"
    override val script: String? = null
    override val territory: String = "MU"

    class EllipsisLanguage internal constructor() : LanguageEn.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEn.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEn.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageEn.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
