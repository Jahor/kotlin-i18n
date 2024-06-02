package info.leonenko.i18n.helpers

import info.leonenko.i18n.Case
import info.leonenko.i18n.FormatLength
import info.leonenko.i18n.Language
import info.leonenko.i18n.units.angle.Angle
import info.leonenko.i18n.units.duration.*
import info.leonenko.i18n.units.length.*
import info.leonenko.i18n.units.speed.*
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan
import kotlin.time.Duration.Companion.seconds


// TODO add ability to get fractional last
fun Duration.split(
    includeZeros: Boolean = false,
    maxValue: Duration = Nanosecond(Double.MAX_VALUE),
    minValue: Duration = Nanosecond(0)
): List<Duration> {
    val result = mutableListOf<Duration>()

    var (seconds, sign) = if (value < 0) {
        -toSecond() to -1
    } else {
        toSecond() to 1
    }

//    for (converter in listOf<(Second) -> Duration>({it.toDay().truncate()},
//            {it.toHour().truncate()}, {it.toMinute().truncate()},
//            {it.toSecond().truncate()}, {it.toMillisecond().truncate()},
//            {it.toMicrosecond().truncate()}, {it.toNanosecond().truncate()})) {
//        val c = converter(seconds)
//        if(c < maxValue) {
//            seconds -= c
//            if (c > Duration.Zero || includeZeros) {
//                result.add(c * sign)
//                sign = 1
//            }
//        }
//        if (seconds < minValue) {
//            break
//        }
//    }

    if (Day(1) <= maxValue) {
        val days = seconds.toDay().truncate()

        seconds -= days
        if (days > Day.ZERO || includeZeros) {
            result.add(days * sign)
            sign = 1
        }
    }
    if (seconds < minValue) {
        return result
    }

    if (Hour(1) <= maxValue) {
        val hours = seconds.toHour().truncate()

        seconds -= hours
        if (hours > Hour.ZERO || includeZeros) {
            result.add(hours * sign)
            sign = 1
        }
    }

    if (seconds < minValue) {
        return result
    }
    if (Minute(1) <= maxValue) {
        val minutes = seconds.toMinute().truncate()

        seconds -= minutes
        if (minutes > Minute.ZERO || includeZeros) {
            result.add(minutes * sign)
            sign = 1
        }
    }

    if (seconds < minValue) {
        return result
    }
    if (Second(1) <= maxValue) {
        val secs = seconds.toSecond().truncate()

        seconds -= secs
        if (secs > Second.ZERO || includeZeros) {
            result.add(secs * sign)
            sign = 1
        }
    }

    if (seconds < minValue) {
        return result
    }
    if (Millisecond(1) <= maxValue) {
        val millisecond = seconds.toMillisecond().truncate()

        seconds -= millisecond
        if (millisecond > Millisecond.ZERO || includeZeros) {
            result.add(millisecond * sign)
            sign = 1
        }
    }

    if (seconds < minValue) {
        return result
    }
    if (Microsecond(1) <= maxValue) {
        val microsecond = seconds.toMicrosecond().truncate()

        seconds -= microsecond
        if (microsecond > Microsecond.ZERO || includeZeros) {
            result.add(microsecond * sign)
            sign = 1
        }
    }

    if (seconds < minValue) {
        return result
    }
    if (Nanosecond(1) <= maxValue) {
        val nanosecond = seconds.toNanosecond().truncate()
        seconds -= nanosecond
        if (nanosecond > Nanosecond.ZERO || includeZeros) {
            result.add(nanosecond * sign)
        }
    }

    return result
}

fun Foot.split(): Pair<Foot, Inch> {
    val feet = floor()
    val inches = (this - feet).toInch()
    return feet to inches
}

fun Foot.formatSplit(language: Language, case: Case, length: FormatLength): String {
    val (feet, inches) = split()
    return "${feet.format(language, case, length) { _, _, v -> v.toLong().toString() }} ${
        inches.round().format(language, case, length) { _, _, v -> v.toLong().toString() }
    }"
}


private fun Speed.baseLength(): Length {
    return when (this) {
        is KilometerPerHour -> Kilometer(value)
        is Knot -> NauticalMile(value)
        is MeterPerSecond -> Meter(value)
        is MilePerHour -> Mile(value)
        else -> TODO("Unknown base length for $this")
    }
}

private fun Speed.baseDuration(): Duration {
    return when (this) {
        is KilometerPerHour -> Hour(1.0)
        is Knot -> Hour(1.0)
        is MeterPerSecond -> Second(1.0)
        is MilePerHour -> Hour(1.0)
        else -> TODO("Unknown base duration for $this")
    }
}


fun Duration.toKotlinDuration(): kotlin.time.Duration = toSecond().value.seconds

operator fun Speed.times(time: Duration): Length = (this * (time.div(baseDuration()))).baseLength()
operator fun Speed.times(time: kotlin.time.Duration): Length =
    (this * (time.div(baseDuration().toKotlinDuration()))).baseLength()


operator fun Kilometer.div(time: Hour): KilometerPerHour = KilometerPerHour(value / time.value)
operator fun Mile.div(time: Hour): MilePerHour = MilePerHour(value / time.value)
operator fun NauticalMile.div(time: Hour): Knot = Knot(value / time.value)
operator fun Length.div(time: Duration): MeterPerSecond = MeterPerSecond(toMeter().value / time.toSecond().value)

operator fun Meter.div(speed: MeterPerSecond): Second = Second(value / speed.value)
operator fun Mile.div(speed: Speed): Hour = toMeter().div(speed.toMeterPerSecond()).toHour()
operator fun NauticalMile.div(speed: Speed): Hour = toMeter().div(speed.toMeterPerSecond()).toHour()
operator fun Kilometer.div(speed: Speed): Hour = toMeter().div(speed.toMeterPerSecond()).toHour()
operator fun Length.div(speed: Speed): Duration {
    val mps = toMeter().div(speed.toMeterPerSecond())
    val secPerBaseDuration = Second(1) / speed.baseDuration()
    return speed.baseDuration() * (mps * secPerBaseDuration).value
}

fun cos(angle: Angle): Double = cos(angle.toRadian().value)
fun sin(angle: Angle): Double = sin(angle.toRadian().value)
fun tan(angle: Angle): Double = tan(angle.toRadian().value)