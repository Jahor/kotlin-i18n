package info.leonenko.i18n.helpers

import info.leonenko.i18n.Language
import info.leonenko.i18n.languages.*
import info.leonenko.i18n.units.MeasurementUnit
import info.leonenko.i18n.units.angle.Angle
import info.leonenko.i18n.units.angle.Degree
import info.leonenko.i18n.units.angle.Radian
import info.leonenko.i18n.units.angle.Revolution
import info.leonenko.i18n.units.duration.Duration
import info.leonenko.i18n.units.duration.hour
import info.leonenko.i18n.units.duration.minute
import info.leonenko.i18n.units.duration.second
import info.leonenko.i18n.units.length.foot
import info.leonenko.i18n.units.length.inch
import info.leonenko.i18n.units.length.kilometer
import info.leonenko.i18n.units.length.meter
import info.leonenko.i18n.units.speed.kilometerPerHour
import info.leonenko.i18n.units.speed.knot
import info.leonenko.i18n.units.speed.meterPerSecond
import kotlin.math.PI
import kotlin.math.abs
import kotlin.test.DefaultAsserter.assertTrue
import kotlin.test.Test
import kotlin.test.assertEquals

class HelpersTest {
    @Test
    fun speedTimesDuration() {
        assertEquals(0.6.kilometer, 36.kilometerPerHour * 60.second, 0.01.meter)
        assertEquals(600.meter, 36.kilometerPerHour * 60.second, 0.01.meter)
    }

    @Test
    fun lengthDivSpeed() {
        assertEquals(1.hour, 3.704.kilometer / 2.knot, 1.second)
    }

    @Test
    fun lengthDivDuration() {
        assertEquals(2.kilometerPerHour, 1.kilometer / 30.minute, 1.meterPerSecond)
    }

    @Test
    fun closedRange() {
        println(2.kilometer..5.kilometer)
    }

    @Test
    fun splitDuration() {
        assertEquals("01:00", 1.minute.toPositionalString())
        assertEquals("00:00", 0.minute.toPositionalString())
        assertEquals("02:01:00", 121.minute.toPositionalString())
    }

    @Test
    fun angleConversion() {
        assertEquals<Angle>(Revolution(0.5), Radian(PI).toRevolution())
        assertEquals<Angle>(Revolution(0.5).toRadian(), Radian(PI))
        assertEquals<Angle>(Revolution(0.5), Degree(180).toRevolution())
        assertEquals<Angle>(Revolution(0.5).toDegree(), Degree(180))
    }

    @Test
    fun splitFoot() {
        val (feet, inches) = 6.26.meter.toFoot().split()
        assertEquals(20.foot, feet)
        assertEquals(6.inch, inches.round())
    }

    @Test
    fun codeTest() {
        assertEquals(Language.en, Language.byCode("en"))
        assertEquals(Language.en_US, Language.byCode("en_US"))
        assertEquals(Language.en_US, Language.byCode("en-US"))
        assertEquals(Language.zh_CN_HANS, Language.byCode("zh_CN"))
        assertEquals(Language.id_ID, Language.byCode("in_ID"))
        assertEquals(Language.id_ID, Language.byCode("id-ID"))
        assertEquals(Language.fr_BE, Language.byCode("fr_BEL"))

    }
}

fun Duration.toPositionalString(): String {
    return this.split(includeZeros = true, maxValue = 1.hour).let { (hours, minutes, seconds) ->
        listOfNotNull(
            if (hours.value > 0) hours.value.toInt().toString().padStart(2, '0') else null,
            minutes.value.toInt().toString().padStart(2, '0'),
            seconds.value.toInt().toString().padStart(2, '0'),
        ).joinToString(":")
    }
}

fun <T : MeasurementUnit> assertEquals(expected: T, actual: T, absoluteTolerance: T, message: String? = null) {
    val equal = expected == actual || abs(expected.getBaseUnitValue() - actual.getBaseUnitValue()) <= absoluteTolerance.value

    assertTrue(
        { (if (message == null) "" else "$message. ") + "Expected <$expected> with absolute tolerance <$absoluteTolerance>, actual <$actual>." },
        equal
    )
}