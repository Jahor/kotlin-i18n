package info.leonenko.i18n.languages
import info.leonenko.i18n.Language

val Language.Companion.en_US_DSRT
    get() = languageEn_US_DSRT
private val languageEn_US_DSRT = LanguageEn_US_DSRT()
class LanguageEn_US_DSRT internal constructor() : LanguageEn_DSRT() {
    override val code: String = "en_US_#Dsrt"
    override val script: String = "Dsrt"
    override val territory: String = "US"

    class EllipsisLanguage internal constructor() : LanguageEn_DSRT.EllipsisLanguage() {
    }

    override val ellipsis: EllipsisLanguage
        get() = EllipsisLanguage()

    class ListLanguage internal constructor(ellipsis: EllipsisLanguage) : LanguageEn_DSRT.ListLanguage(ellipsis) {
    }

    override val list: ListLanguage
        get() = ListLanguage(ellipsis)

    class MeasurementUnitLanguage internal constructor() : LanguageEn_DSRT.MeasurementUnitLanguage() {
    }

    override val measurementUnits: MeasurementUnitLanguage
        get() = MeasurementUnitLanguage()

    class NumberLanguage internal constructor() : LanguageEn_DSRT.NumberLanguage() {
    }

    override val numbers: NumberLanguage
        get() = NumberLanguage()
}
