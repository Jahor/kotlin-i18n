package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.hu_HU
    get() = languageHu_HU
private val languageHu_HU = LanguageHu_HU()
class LanguageHu_HU internal constructor() : LanguageHu() {
    override val code: String = "hu_HU"
    override val script: String? = null
    override val territory: String = "HU"

    class EllipsisLanguage internal constructor() : LanguageHu.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageHu.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageHu.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageHu.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
