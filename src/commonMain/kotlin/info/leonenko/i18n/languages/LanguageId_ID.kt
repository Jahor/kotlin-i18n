package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.id_ID
    get() = languageId_ID
private val languageId_ID = LanguageId_ID()
class LanguageId_ID internal constructor() : LanguageId() {
    override val code: String = "id_ID"
    override val script: String? = null
    override val territory: String = "ID"

    class EllipsisLanguage internal constructor() : LanguageId.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageId.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageId.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageId.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
