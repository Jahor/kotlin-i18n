package info.leonenko.i18n.units.duration

import kotlin.math.truncate
import kotlin.math.round
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.abs
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import info.leonenko.i18n.units.MeasurementUnit
import info.leonenko.i18n.units.MeasurementUnitValue
import info.leonenko.i18n.units.MeasurementUnitLanguage
import info.leonenko.i18n.units.toMeasurementUnitValue

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


    operator fun times(other: kotlin.Byte): Duration
    operator fun div(other: kotlin.Byte): Duration
    operator fun rem(other: kotlin.Byte): Duration


    operator fun times(other: kotlin.Short): Duration
    operator fun div(other: kotlin.Short): Duration
    operator fun rem(other: kotlin.Short): Duration


    operator fun times(other: kotlin.Int): Duration
    operator fun div(other: kotlin.Int): Duration
    operator fun rem(other: kotlin.Int): Duration


    operator fun times(other: kotlin.Long): Duration
    operator fun div(other: kotlin.Long): Duration
    operator fun rem(other: kotlin.Long): Duration


    operator fun times(other: kotlin.Float): Duration
    operator fun div(other: kotlin.Float): Duration
    operator fun rem(other: kotlin.Float): Duration


    operator fun times(other: kotlin.Double): Duration
    operator fun div(other: kotlin.Double): Duration
    operator fun rem(other: kotlin.Double): Duration

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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Week = Week(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Week = Week(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Week = Week(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Week = Week(this.value.times(other))
    override operator fun div(other: kotlin.Short): Week = Week(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Week = Week(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Week = Week(this.value.times(other))
    override operator fun div(other: kotlin.Int): Week = Week(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Week = Week(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Week = Week(this.value.times(other))
    override operator fun div(other: kotlin.Long): Week = Week(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Week = Week(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Week = Week(this.value.times(other))
    override operator fun div(other: kotlin.Float): Week = Week(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Week = Week(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Week = Week(this.value.times(other))
    override operator fun div(other: kotlin.Double): Week = Week(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Week = Week(this.value.rem(other))


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

val kotlin.Byte.week
    get() = Week(this)


val kotlin.Short.week
    get() = Week(this)


val kotlin.Int.week
    get() = Week(this)


val kotlin.Long.week
    get() = Week(this)


val kotlin.Float.week
    get() = Week(this)


val kotlin.Double.week
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Day = Day(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Day = Day(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Day = Day(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Day = Day(this.value.times(other))
    override operator fun div(other: kotlin.Short): Day = Day(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Day = Day(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Day = Day(this.value.times(other))
    override operator fun div(other: kotlin.Int): Day = Day(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Day = Day(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Day = Day(this.value.times(other))
    override operator fun div(other: kotlin.Long): Day = Day(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Day = Day(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Day = Day(this.value.times(other))
    override operator fun div(other: kotlin.Float): Day = Day(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Day = Day(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Day = Day(this.value.times(other))
    override operator fun div(other: kotlin.Double): Day = Day(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Day = Day(this.value.rem(other))


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

val kotlin.Byte.day
    get() = Day(this)


val kotlin.Short.day
    get() = Day(this)


val kotlin.Int.day
    get() = Day(this)


val kotlin.Long.day
    get() = Day(this)


val kotlin.Float.day
    get() = Day(this)


val kotlin.Double.day
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Hour = Hour(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Hour = Hour(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Hour = Hour(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Hour = Hour(this.value.times(other))
    override operator fun div(other: kotlin.Short): Hour = Hour(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Hour = Hour(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Hour = Hour(this.value.times(other))
    override operator fun div(other: kotlin.Int): Hour = Hour(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Hour = Hour(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Hour = Hour(this.value.times(other))
    override operator fun div(other: kotlin.Long): Hour = Hour(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Hour = Hour(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Hour = Hour(this.value.times(other))
    override operator fun div(other: kotlin.Float): Hour = Hour(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Hour = Hour(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Hour = Hour(this.value.times(other))
    override operator fun div(other: kotlin.Double): Hour = Hour(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Hour = Hour(this.value.rem(other))


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

val kotlin.Byte.hour
    get() = Hour(this)


val kotlin.Short.hour
    get() = Hour(this)


val kotlin.Int.hour
    get() = Hour(this)


val kotlin.Long.hour
    get() = Hour(this)


val kotlin.Float.hour
    get() = Hour(this)


val kotlin.Double.hour
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Minute = Minute(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Minute = Minute(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Minute = Minute(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Minute = Minute(this.value.times(other))
    override operator fun div(other: kotlin.Short): Minute = Minute(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Minute = Minute(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Minute = Minute(this.value.times(other))
    override operator fun div(other: kotlin.Int): Minute = Minute(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Minute = Minute(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Minute = Minute(this.value.times(other))
    override operator fun div(other: kotlin.Long): Minute = Minute(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Minute = Minute(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Minute = Minute(this.value.times(other))
    override operator fun div(other: kotlin.Float): Minute = Minute(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Minute = Minute(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Minute = Minute(this.value.times(other))
    override operator fun div(other: kotlin.Double): Minute = Minute(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Minute = Minute(this.value.rem(other))


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

val kotlin.Byte.minute
    get() = Minute(this)


val kotlin.Short.minute
    get() = Minute(this)


val kotlin.Int.minute
    get() = Minute(this)


val kotlin.Long.minute
    get() = Minute(this)


val kotlin.Float.minute
    get() = Minute(this)


val kotlin.Double.minute
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Second = Second(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Second = Second(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Second = Second(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Second = Second(this.value.times(other))
    override operator fun div(other: kotlin.Short): Second = Second(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Second = Second(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Second = Second(this.value.times(other))
    override operator fun div(other: kotlin.Int): Second = Second(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Second = Second(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Second = Second(this.value.times(other))
    override operator fun div(other: kotlin.Long): Second = Second(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Second = Second(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Second = Second(this.value.times(other))
    override operator fun div(other: kotlin.Float): Second = Second(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Second = Second(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Second = Second(this.value.times(other))
    override operator fun div(other: kotlin.Double): Second = Second(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Second = Second(this.value.rem(other))


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

val kotlin.Byte.second
    get() = Second(this)


val kotlin.Short.second
    get() = Second(this)


val kotlin.Int.second
    get() = Second(this)


val kotlin.Long.second
    get() = Second(this)


val kotlin.Float.second
    get() = Second(this)


val kotlin.Double.second
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Millisecond = Millisecond(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Millisecond = Millisecond(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Millisecond = Millisecond(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Millisecond = Millisecond(this.value.times(other))
    override operator fun div(other: kotlin.Short): Millisecond = Millisecond(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Millisecond = Millisecond(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Millisecond = Millisecond(this.value.times(other))
    override operator fun div(other: kotlin.Int): Millisecond = Millisecond(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Millisecond = Millisecond(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Millisecond = Millisecond(this.value.times(other))
    override operator fun div(other: kotlin.Long): Millisecond = Millisecond(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Millisecond = Millisecond(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Millisecond = Millisecond(this.value.times(other))
    override operator fun div(other: kotlin.Float): Millisecond = Millisecond(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Millisecond = Millisecond(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Millisecond = Millisecond(this.value.times(other))
    override operator fun div(other: kotlin.Double): Millisecond = Millisecond(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Millisecond = Millisecond(this.value.rem(other))


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

val kotlin.Byte.millisecond
    get() = Millisecond(this)


val kotlin.Short.millisecond
    get() = Millisecond(this)


val kotlin.Int.millisecond
    get() = Millisecond(this)


val kotlin.Long.millisecond
    get() = Millisecond(this)


val kotlin.Float.millisecond
    get() = Millisecond(this)


val kotlin.Double.millisecond
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Microsecond = Microsecond(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Microsecond = Microsecond(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Microsecond = Microsecond(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Microsecond = Microsecond(this.value.times(other))
    override operator fun div(other: kotlin.Short): Microsecond = Microsecond(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Microsecond = Microsecond(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Microsecond = Microsecond(this.value.times(other))
    override operator fun div(other: kotlin.Int): Microsecond = Microsecond(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Microsecond = Microsecond(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Microsecond = Microsecond(this.value.times(other))
    override operator fun div(other: kotlin.Long): Microsecond = Microsecond(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Microsecond = Microsecond(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Microsecond = Microsecond(this.value.times(other))
    override operator fun div(other: kotlin.Float): Microsecond = Microsecond(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Microsecond = Microsecond(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Microsecond = Microsecond(this.value.times(other))
    override operator fun div(other: kotlin.Double): Microsecond = Microsecond(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Microsecond = Microsecond(this.value.rem(other))


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

val kotlin.Byte.microsecond
    get() = Microsecond(this)


val kotlin.Short.microsecond
    get() = Microsecond(this)


val kotlin.Int.microsecond
    get() = Microsecond(this)


val kotlin.Long.microsecond
    get() = Microsecond(this)


val kotlin.Float.microsecond
    get() = Microsecond(this)


val kotlin.Double.microsecond
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Nanosecond = Nanosecond(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Nanosecond = Nanosecond(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Nanosecond = Nanosecond(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Nanosecond = Nanosecond(this.value.times(other))
    override operator fun div(other: kotlin.Short): Nanosecond = Nanosecond(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Nanosecond = Nanosecond(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Nanosecond = Nanosecond(this.value.times(other))
    override operator fun div(other: kotlin.Int): Nanosecond = Nanosecond(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Nanosecond = Nanosecond(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Nanosecond = Nanosecond(this.value.times(other))
    override operator fun div(other: kotlin.Long): Nanosecond = Nanosecond(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Nanosecond = Nanosecond(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Nanosecond = Nanosecond(this.value.times(other))
    override operator fun div(other: kotlin.Float): Nanosecond = Nanosecond(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Nanosecond = Nanosecond(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Nanosecond = Nanosecond(this.value.times(other))
    override operator fun div(other: kotlin.Double): Nanosecond = Nanosecond(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Nanosecond = Nanosecond(this.value.rem(other))


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

val kotlin.Byte.nanosecond
    get() = Nanosecond(this)


val kotlin.Short.nanosecond
    get() = Nanosecond(this)


val kotlin.Int.nanosecond
    get() = Nanosecond(this)


val kotlin.Long.nanosecond
    get() = Nanosecond(this)


val kotlin.Float.nanosecond
    get() = Nanosecond(this)


val kotlin.Double.nanosecond
    get() = Nanosecond(this)
                            
