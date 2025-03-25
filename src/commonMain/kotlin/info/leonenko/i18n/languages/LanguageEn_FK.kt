package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.en_FK
    get() = languageEn_FK
private val languageEn_FK = LanguageEn_FK()
class LanguageEn_FK internal constructor() : LanguageEn() {
    override val code: String = "en_FK"
    override val script: String? = null
    override val territory: String = "FK"

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
