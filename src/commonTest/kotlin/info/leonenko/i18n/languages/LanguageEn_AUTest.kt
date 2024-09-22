package info.leonenko.i18n.languages

import info.leonenko.i18n.FormatLength
import info.leonenko.i18n.helpers.split
import info.leonenko.i18n.joinToStringLocalized
import info.leonenko.i18n.units.duration.Second
import kotlin.test.Test
import kotlin.test.assertEquals

class LanguageEn_AUTest {
    private val language = LanguageEn_AU()

    @Test
    fun secondsLong() {
        assertEquals("20 seconds", Second(20).split().joinToStringLocalized(language, length = FormatLength.Long) { _, _, v ->
            "${v.toLong()}"
        })

    }
}