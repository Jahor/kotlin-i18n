package info.leonenko.i18n.units.duration

import info.leonenko.i18n.units.MeasurementUnit
import info.leonenko.i18n.units.MeasurementUnitLanguage
import info.leonenko.i18n.units.MeasurementUnitValue
import info.leonenko.i18n.units.toMeasurementUnitValue
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.math.*

@Serializable(Duration.Serializer::class)
interface Duration : MeasurementUnit, Comparable<Duration> {
    override val measurement: String get() = "duration"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Duration> = emptyMap()) : KSerializer<Duration> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Duration", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Duration {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Duration unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "week" -> Week(value)
                "day" -> Day(value)
                "hour" -> Hour(value)
                "minute" -> Minute(value)
                "second" -> Second(value)
                "millisecond" -> Millisecond(value)
                "microsecond" -> Microsecond(value)
                "nanosecond" -> Nanosecond(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Duration unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Duration) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toWeek(): Week {
        return Week.fromSecond(toSecond())
    }

    fun toDay(): Day {
        return Day.fromSecond(toSecond())
    }

    fun toHour(): Hour {
        return Hour.fromSecond(toSecond())
    }

    fun toMinute(): Minute {
        return Minute.fromSecond(toSecond())
    }

    fun toSecond(): Second
    override fun getBaseUnitValue() = toSecond().value
    fun toMillisecond(): Millisecond {
        return Millisecond.fromSecond(toSecond())
    }

    fun toMicrosecond(): Microsecond {
        return Microsecond.fromSecond(toSecond())
    }

    fun toNanosecond(): Nanosecond {
        return Nanosecond.fromSecond(toSecond())
    }

    companion object {
        val ZERO: Duration = Second(0.0)

    }

    override operator fun compareTo(other: Duration): Int = this.toSecond().value.compareTo(other.toSecond().value)

    operator fun plus(other: Duration): Second = this.toSecond() + other.toSecond()
    operator fun minus(other: Duration): Second = this.toSecond() - other.toSecond()
    operator fun div(other: Duration): Double = this.toSecond().value / other.toSecond().value
    operator fun unaryMinus(): Duration
    operator fun unaryPlus(): Duration
    operator fun inc(): Duration
    operator fun dec(): Duration

    fun round(): Duration
    fun truncate(): Duration
    fun floor(): Duration
    fun ceil(): Duration
    fun abs(): Duration


    operator fun times(other: Byte): Duration
    operator fun div(other: Byte): Duration
    operator fun rem(other: Byte): Duration


    operator fun times(other: Short): Duration
    operator fun div(other: Short): Duration
    operator fun rem(other: Short): Duration


    operator fun times(other: Int): Duration
    operator fun div(other: Int): Duration
    operator fun rem(other: Int): Duration


    operator fun times(other: Long): Duration
    operator fun div(other: Long): Duration
    operator fun rem(other: Long): Duration


    operator fun times(other: Float): Duration
    operator fun div(other: Float): Duration
    operator fun rem(other: Float): Duration


    operator fun times(other: Double): Duration
    operator fun div(other: Double): Duration
    operator fun rem(other: Double): Duration

}

data class Week(override val value: MeasurementUnitValue) : Duration {

    override val unitName: String = "week"

    override fun formatters(language: MeasurementUnitLanguage) = language.durationWeek
    override fun toSecond() = Second(value * (604800) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Week(0.0)
        fun fromSecond(value: Second) = Week((value.value - 0) / (604800))
    }

    operator fun plus(other: Week): Week = Week(this.value + other.value)
    operator fun minus(other: Week): Week = Week(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Week = Week(this.value.times(other))
    override operator fun div(other: Byte): Week = Week(this.value.div(other))
    override operator fun rem(other: Byte): Week = Week(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Week = Week(this.value.times(other))
    override operator fun div(other: Short): Week = Week(this.value.div(other))
    override operator fun rem(other: Short): Week = Week(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Week = Week(this.value.times(other))
    override operator fun div(other: Int): Week = Week(this.value.div(other))
    override operator fun rem(other: Int): Week = Week(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Week = Week(this.value.times(other))
    override operator fun div(other: Long): Week = Week(this.value.div(other))
    override operator fun rem(other: Long): Week = Week(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Week = Week(this.value.times(other))
    override operator fun div(other: Float): Week = Week(this.value.div(other))
    override operator fun rem(other: Float): Week = Week(this.value.rem(other))


    override operator fun times(other: Double): Week = Week(this.value.times(other))
    override operator fun div(other: Double): Week = Week(this.value.div(other))
    override operator fun rem(other: Double): Week = Week(this.value.rem(other))


    override operator fun unaryMinus(): Week = Week(-value)
    override operator fun unaryPlus(): Week = this
    override operator fun inc(): Week = Week(this.value + 1)
    override operator fun dec(): Week = Week(this.value - 1)

    override fun round(): Week = Week(round(this.value))
    override fun truncate(): Week = Week(truncate(this.value))
    override fun floor(): Week = Week(floor(this.value))
    override fun ceil(): Week = Week(ceil(this.value))
    override fun abs(): Week = Week(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Week -> this.value == other.value
        is Duration -> this.toSecond().value == other.toSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toSecond().value.hashCode()


}

val Byte.week
    get() = Week(this)


val Short.week
    get() = Week(this)


val Int.week
    get() = Week(this)


val Long.week
    get() = Week(this)


val Float.week
    get() = Week(this)


val Double.week
    get() = Week(this)


data class Day(override val value: MeasurementUnitValue) : Duration {

    override val unitName: String = "day"

    override fun formatters(language: MeasurementUnitLanguage) = language.durationDay
    override fun toSecond() = Second(value * (86400) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Day(0.0)
        fun fromSecond(value: Second) = Day((value.value - 0) / (86400))
    }

    operator fun plus(other: Day): Day = Day(this.value + other.value)
    operator fun minus(other: Day): Day = Day(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Day = Day(this.value.times(other))
    override operator fun div(other: Byte): Day = Day(this.value.div(other))
    override operator fun rem(other: Byte): Day = Day(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Day = Day(this.value.times(other))
    override operator fun div(other: Short): Day = Day(this.value.div(other))
    override operator fun rem(other: Short): Day = Day(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Day = Day(this.value.times(other))
    override operator fun div(other: Int): Day = Day(this.value.div(other))
    override operator fun rem(other: Int): Day = Day(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Day = Day(this.value.times(other))
    override operator fun div(other: Long): Day = Day(this.value.div(other))
    override operator fun rem(other: Long): Day = Day(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Day = Day(this.value.times(other))
    override operator fun div(other: Float): Day = Day(this.value.div(other))
    override operator fun rem(other: Float): Day = Day(this.value.rem(other))


    override operator fun times(other: Double): Day = Day(this.value.times(other))
    override operator fun div(other: Double): Day = Day(this.value.div(other))
    override operator fun rem(other: Double): Day = Day(this.value.rem(other))


    override operator fun unaryMinus(): Day = Day(-value)
    override operator fun unaryPlus(): Day = this
    override operator fun inc(): Day = Day(this.value + 1)
    override operator fun dec(): Day = Day(this.value - 1)

    override fun round(): Day = Day(round(this.value))
    override fun truncate(): Day = Day(truncate(this.value))
    override fun floor(): Day = Day(floor(this.value))
    override fun ceil(): Day = Day(ceil(this.value))
    override fun abs(): Day = Day(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Day -> this.value == other.value
        is Duration -> this.toSecond().value == other.toSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toSecond().value.hashCode()


}

val Byte.day
    get() = Day(this)


val Short.day
    get() = Day(this)


val Int.day
    get() = Day(this)


val Long.day
    get() = Day(this)


val Float.day
    get() = Day(this)


val Double.day
    get() = Day(this)


data class Hour(override val value: MeasurementUnitValue) : Duration {

    override val unitName: String = "hour"

    override fun formatters(language: MeasurementUnitLanguage) = language.durationHour
    override fun toSecond() = Second(value * (3600) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Hour(0.0)
        fun fromSecond(value: Second) = Hour((value.value - 0) / (3600))
    }

    operator fun plus(other: Hour): Hour = Hour(this.value + other.value)
    operator fun minus(other: Hour): Hour = Hour(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Hour = Hour(this.value.times(other))
    override operator fun div(other: Byte): Hour = Hour(this.value.div(other))
    override operator fun rem(other: Byte): Hour = Hour(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Hour = Hour(this.value.times(other))
    override operator fun div(other: Short): Hour = Hour(this.value.div(other))
    override operator fun rem(other: Short): Hour = Hour(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Hour = Hour(this.value.times(other))
    override operator fun div(other: Int): Hour = Hour(this.value.div(other))
    override operator fun rem(other: Int): Hour = Hour(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Hour = Hour(this.value.times(other))
    override operator fun div(other: Long): Hour = Hour(this.value.div(other))
    override operator fun rem(other: Long): Hour = Hour(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Hour = Hour(this.value.times(other))
    override operator fun div(other: Float): Hour = Hour(this.value.div(other))
    override operator fun rem(other: Float): Hour = Hour(this.value.rem(other))


    override operator fun times(other: Double): Hour = Hour(this.value.times(other))
    override operator fun div(other: Double): Hour = Hour(this.value.div(other))
    override operator fun rem(other: Double): Hour = Hour(this.value.rem(other))


    override operator fun unaryMinus(): Hour = Hour(-value)
    override operator fun unaryPlus(): Hour = this
    override operator fun inc(): Hour = Hour(this.value + 1)
    override operator fun dec(): Hour = Hour(this.value - 1)

    override fun round(): Hour = Hour(round(this.value))
    override fun truncate(): Hour = Hour(truncate(this.value))
    override fun floor(): Hour = Hour(floor(this.value))
    override fun ceil(): Hour = Hour(ceil(this.value))
    override fun abs(): Hour = Hour(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Hour -> this.value == other.value
        is Duration -> this.toSecond().value == other.toSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toSecond().value.hashCode()


}

val Byte.hour
    get() = Hour(this)


val Short.hour
    get() = Hour(this)


val Int.hour
    get() = Hour(this)


val Long.hour
    get() = Hour(this)


val Float.hour
    get() = Hour(this)


val Double.hour
    get() = Hour(this)


data class Minute(override val value: MeasurementUnitValue) : Duration {

    override val unitName: String = "minute"

    override fun formatters(language: MeasurementUnitLanguage) = language.durationMinute
    override fun toSecond() = Second(value * (60) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Minute(0.0)
        fun fromSecond(value: Second) = Minute((value.value - 0) / (60))
    }

    operator fun plus(other: Minute): Minute = Minute(this.value + other.value)
    operator fun minus(other: Minute): Minute = Minute(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Minute = Minute(this.value.times(other))
    override operator fun div(other: Byte): Minute = Minute(this.value.div(other))
    override operator fun rem(other: Byte): Minute = Minute(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Minute = Minute(this.value.times(other))
    override operator fun div(other: Short): Minute = Minute(this.value.div(other))
    override operator fun rem(other: Short): Minute = Minute(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Minute = Minute(this.value.times(other))
    override operator fun div(other: Int): Minute = Minute(this.value.div(other))
    override operator fun rem(other: Int): Minute = Minute(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Minute = Minute(this.value.times(other))
    override operator fun div(other: Long): Minute = Minute(this.value.div(other))
    override operator fun rem(other: Long): Minute = Minute(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Minute = Minute(this.value.times(other))
    override operator fun div(other: Float): Minute = Minute(this.value.div(other))
    override operator fun rem(other: Float): Minute = Minute(this.value.rem(other))


    override operator fun times(other: Double): Minute = Minute(this.value.times(other))
    override operator fun div(other: Double): Minute = Minute(this.value.div(other))
    override operator fun rem(other: Double): Minute = Minute(this.value.rem(other))


    override operator fun unaryMinus(): Minute = Minute(-value)
    override operator fun unaryPlus(): Minute = this
    override operator fun inc(): Minute = Minute(this.value + 1)
    override operator fun dec(): Minute = Minute(this.value - 1)

    override fun round(): Minute = Minute(round(this.value))
    override fun truncate(): Minute = Minute(truncate(this.value))
    override fun floor(): Minute = Minute(floor(this.value))
    override fun ceil(): Minute = Minute(ceil(this.value))
    override fun abs(): Minute = Minute(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Minute -> this.value == other.value
        is Duration -> this.toSecond().value == other.toSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toSecond().value.hashCode()


}

val Byte.minute
    get() = Minute(this)


val Short.minute
    get() = Minute(this)


val Int.minute
    get() = Minute(this)


val Long.minute
    get() = Minute(this)


val Float.minute
    get() = Minute(this)


val Double.minute
    get() = Minute(this)


data class Second(override val value: MeasurementUnitValue) : Duration {

    override val unitName: String = "second"

    override fun formatters(language: MeasurementUnitLanguage) = language.durationSecond
    override fun toSecond() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Second(0.0)
        fun fromSecond(value: Second) = value
    }

    operator fun plus(other: Second): Second = Second(this.value + other.value)
    operator fun minus(other: Second): Second = Second(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Second = Second(this.value.times(other))
    override operator fun div(other: Byte): Second = Second(this.value.div(other))
    override operator fun rem(other: Byte): Second = Second(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Second = Second(this.value.times(other))
    override operator fun div(other: Short): Second = Second(this.value.div(other))
    override operator fun rem(other: Short): Second = Second(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Second = Second(this.value.times(other))
    override operator fun div(other: Int): Second = Second(this.value.div(other))
    override operator fun rem(other: Int): Second = Second(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Second = Second(this.value.times(other))
    override operator fun div(other: Long): Second = Second(this.value.div(other))
    override operator fun rem(other: Long): Second = Second(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Second = Second(this.value.times(other))
    override operator fun div(other: Float): Second = Second(this.value.div(other))
    override operator fun rem(other: Float): Second = Second(this.value.rem(other))


    override operator fun times(other: Double): Second = Second(this.value.times(other))
    override operator fun div(other: Double): Second = Second(this.value.div(other))
    override operator fun rem(other: Double): Second = Second(this.value.rem(other))


    override operator fun unaryMinus(): Second = Second(-value)
    override operator fun unaryPlus(): Second = this
    override operator fun inc(): Second = Second(this.value + 1)
    override operator fun dec(): Second = Second(this.value - 1)

    override fun round(): Second = Second(round(this.value))
    override fun truncate(): Second = Second(truncate(this.value))
    override fun floor(): Second = Second(floor(this.value))
    override fun ceil(): Second = Second(ceil(this.value))
    override fun abs(): Second = Second(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Second -> this.value == other.value
        is Duration -> this.toSecond().value == other.toSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toSecond().value.hashCode()


}

val Byte.second
    get() = Second(this)


val Short.second
    get() = Second(this)


val Int.second
    get() = Second(this)


val Long.second
    get() = Second(this)


val Float.second
    get() = Second(this)


val Double.second
    get() = Second(this)


data class Millisecond(override val value: MeasurementUnitValue) : Duration {

    override val unitName: String = "millisecond"

    override fun formatters(language: MeasurementUnitLanguage) = language.durationMillisecond
    override fun toSecond() = Second(value * (1 * (1e-3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Millisecond(0.0)
        fun fromSecond(value: Second) = Millisecond((value.value - 0) / (1 * (1e-3)))
    }

    operator fun plus(other: Millisecond): Millisecond = Millisecond(this.value + other.value)
    operator fun minus(other: Millisecond): Millisecond = Millisecond(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Millisecond = Millisecond(this.value.times(other))
    override operator fun div(other: Byte): Millisecond = Millisecond(this.value.div(other))
    override operator fun rem(other: Byte): Millisecond = Millisecond(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Millisecond = Millisecond(this.value.times(other))
    override operator fun div(other: Short): Millisecond = Millisecond(this.value.div(other))
    override operator fun rem(other: Short): Millisecond = Millisecond(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Millisecond = Millisecond(this.value.times(other))
    override operator fun div(other: Int): Millisecond = Millisecond(this.value.div(other))
    override operator fun rem(other: Int): Millisecond = Millisecond(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Millisecond = Millisecond(this.value.times(other))
    override operator fun div(other: Long): Millisecond = Millisecond(this.value.div(other))
    override operator fun rem(other: Long): Millisecond = Millisecond(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Millisecond = Millisecond(this.value.times(other))
    override operator fun div(other: Float): Millisecond = Millisecond(this.value.div(other))
    override operator fun rem(other: Float): Millisecond = Millisecond(this.value.rem(other))


    override operator fun times(other: Double): Millisecond = Millisecond(this.value.times(other))
    override operator fun div(other: Double): Millisecond = Millisecond(this.value.div(other))
    override operator fun rem(other: Double): Millisecond = Millisecond(this.value.rem(other))


    override operator fun unaryMinus(): Millisecond = Millisecond(-value)
    override operator fun unaryPlus(): Millisecond = this
    override operator fun inc(): Millisecond = Millisecond(this.value + 1)
    override operator fun dec(): Millisecond = Millisecond(this.value - 1)

    override fun round(): Millisecond = Millisecond(round(this.value))
    override fun truncate(): Millisecond = Millisecond(truncate(this.value))
    override fun floor(): Millisecond = Millisecond(floor(this.value))
    override fun ceil(): Millisecond = Millisecond(ceil(this.value))
    override fun abs(): Millisecond = Millisecond(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Millisecond -> this.value == other.value
        is Duration -> this.toSecond().value == other.toSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toSecond().value.hashCode()


}

val Byte.millisecond
    get() = Millisecond(this)


val Short.millisecond
    get() = Millisecond(this)


val Int.millisecond
    get() = Millisecond(this)


val Long.millisecond
    get() = Millisecond(this)


val Float.millisecond
    get() = Millisecond(this)


val Double.millisecond
    get() = Millisecond(this)


data class Microsecond(override val value: MeasurementUnitValue) : Duration {

    override val unitName: String = "microsecond"

    override fun formatters(language: MeasurementUnitLanguage) = language.durationMicrosecond
    override fun toSecond() = Second(value * (1 * (1e-6)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Microsecond(0.0)
        fun fromSecond(value: Second) = Microsecond((value.value - 0) / (1 * (1e-6)))
    }

    operator fun plus(other: Microsecond): Microsecond = Microsecond(this.value + other.value)
    operator fun minus(other: Microsecond): Microsecond = Microsecond(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Microsecond = Microsecond(this.value.times(other))
    override operator fun div(other: Byte): Microsecond = Microsecond(this.value.div(other))
    override operator fun rem(other: Byte): Microsecond = Microsecond(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Microsecond = Microsecond(this.value.times(other))
    override operator fun div(other: Short): Microsecond = Microsecond(this.value.div(other))
    override operator fun rem(other: Short): Microsecond = Microsecond(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Microsecond = Microsecond(this.value.times(other))
    override operator fun div(other: Int): Microsecond = Microsecond(this.value.div(other))
    override operator fun rem(other: Int): Microsecond = Microsecond(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Microsecond = Microsecond(this.value.times(other))
    override operator fun div(other: Long): Microsecond = Microsecond(this.value.div(other))
    override operator fun rem(other: Long): Microsecond = Microsecond(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Microsecond = Microsecond(this.value.times(other))
    override operator fun div(other: Float): Microsecond = Microsecond(this.value.div(other))
    override operator fun rem(other: Float): Microsecond = Microsecond(this.value.rem(other))


    override operator fun times(other: Double): Microsecond = Microsecond(this.value.times(other))
    override operator fun div(other: Double): Microsecond = Microsecond(this.value.div(other))
    override operator fun rem(other: Double): Microsecond = Microsecond(this.value.rem(other))


    override operator fun unaryMinus(): Microsecond = Microsecond(-value)
    override operator fun unaryPlus(): Microsecond = this
    override operator fun inc(): Microsecond = Microsecond(this.value + 1)
    override operator fun dec(): Microsecond = Microsecond(this.value - 1)

    override fun round(): Microsecond = Microsecond(round(this.value))
    override fun truncate(): Microsecond = Microsecond(truncate(this.value))
    override fun floor(): Microsecond = Microsecond(floor(this.value))
    override fun ceil(): Microsecond = Microsecond(ceil(this.value))
    override fun abs(): Microsecond = Microsecond(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Microsecond -> this.value == other.value
        is Duration -> this.toSecond().value == other.toSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toSecond().value.hashCode()


}

val Byte.microsecond
    get() = Microsecond(this)


val Short.microsecond
    get() = Microsecond(this)


val Int.microsecond
    get() = Microsecond(this)


val Long.microsecond
    get() = Microsecond(this)


val Float.microsecond
    get() = Microsecond(this)


val Double.microsecond
    get() = Microsecond(this)


data class Nanosecond(override val value: MeasurementUnitValue) : Duration {

    override val unitName: String = "nanosecond"

    override fun formatters(language: MeasurementUnitLanguage) = language.durationNanosecond
    override fun toSecond() = Second(value * (1 * (1e-9)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Nanosecond(0.0)
        fun fromSecond(value: Second) = Nanosecond((value.value - 0) / (1 * (1e-9)))
    }

    operator fun plus(other: Nanosecond): Nanosecond = Nanosecond(this.value + other.value)
    operator fun minus(other: Nanosecond): Nanosecond = Nanosecond(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Nanosecond = Nanosecond(this.value.times(other))
    override operator fun div(other: Byte): Nanosecond = Nanosecond(this.value.div(other))
    override operator fun rem(other: Byte): Nanosecond = Nanosecond(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Nanosecond = Nanosecond(this.value.times(other))
    override operator fun div(other: Short): Nanosecond = Nanosecond(this.value.div(other))
    override operator fun rem(other: Short): Nanosecond = Nanosecond(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Nanosecond = Nanosecond(this.value.times(other))
    override operator fun div(other: Int): Nanosecond = Nanosecond(this.value.div(other))
    override operator fun rem(other: Int): Nanosecond = Nanosecond(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Nanosecond = Nanosecond(this.value.times(other))
    override operator fun div(other: Long): Nanosecond = Nanosecond(this.value.div(other))
    override operator fun rem(other: Long): Nanosecond = Nanosecond(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Nanosecond = Nanosecond(this.value.times(other))
    override operator fun div(other: Float): Nanosecond = Nanosecond(this.value.div(other))
    override operator fun rem(other: Float): Nanosecond = Nanosecond(this.value.rem(other))


    override operator fun times(other: Double): Nanosecond = Nanosecond(this.value.times(other))
    override operator fun div(other: Double): Nanosecond = Nanosecond(this.value.div(other))
    override operator fun rem(other: Double): Nanosecond = Nanosecond(this.value.rem(other))


    override operator fun unaryMinus(): Nanosecond = Nanosecond(-value)
    override operator fun unaryPlus(): Nanosecond = this
    override operator fun inc(): Nanosecond = Nanosecond(this.value + 1)
    override operator fun dec(): Nanosecond = Nanosecond(this.value - 1)

    override fun round(): Nanosecond = Nanosecond(round(this.value))
    override fun truncate(): Nanosecond = Nanosecond(truncate(this.value))
    override fun floor(): Nanosecond = Nanosecond(floor(this.value))
    override fun ceil(): Nanosecond = Nanosecond(ceil(this.value))
    override fun abs(): Nanosecond = Nanosecond(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Nanosecond -> this.value == other.value
        is Duration -> this.toSecond().value == other.toSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toSecond().value.hashCode()


}

val Byte.nanosecond
    get() = Nanosecond(this)


val Short.nanosecond
    get() = Nanosecond(this)


val Int.nanosecond
    get() = Nanosecond(this)


val Long.nanosecond
    get() = Nanosecond(this)


val Float.nanosecond
    get() = Nanosecond(this)


val Double.nanosecond
    get() = Nanosecond(this)
                            
