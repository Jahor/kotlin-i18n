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

@Serializable(YearDuration.Serializer::class)
interface YearDuration : MeasurementUnit, Comparable<YearDuration> {
    override val measurement: String get() = "year-duration"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> YearDuration> = emptyMap()) : KSerializer<YearDuration> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())

        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("YearDuration", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): YearDuration {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected YearDuration unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "century" -> Century(value)
                "decade" -> Decade(value)
                "year" -> Year(value)
                "quarter" -> Quarter(value)
                "month" -> Month(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown YearDuration unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: YearDuration) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toCentury(): Century {
        return Century.fromYear(toYear())
    }

    fun toDecade(): Decade {
        return Decade.fromYear(toYear())
    }

    fun toYear(): Year
    override fun getBaseUnitValue() = toYear().value
    fun toQuarter(): Quarter {
        return Quarter.fromYear(toYear())
    }

    fun toMonth(): Month {
        return Month.fromYear(toYear())
    }

    companion object {
        val ZERO: YearDuration = Year(0.0)

    }

    override operator fun compareTo(other: YearDuration): Int = this.toYear().value.compareTo(other.toYear().value)

    operator fun plus(other: YearDuration): Year = this.toYear() + other.toYear()
    operator fun minus(other: YearDuration): Year = this.toYear() - other.toYear()
    operator fun div(other: YearDuration): Double = this.toYear().value / other.toYear().value
    operator fun unaryMinus(): YearDuration
    operator fun unaryPlus(): YearDuration
    operator fun inc(): YearDuration
    operator fun dec(): YearDuration

    fun round(): YearDuration
    fun truncate(): YearDuration
    fun floor(): YearDuration
    fun ceil(): YearDuration
    fun abs(): YearDuration


    operator fun times(other: kotlin.Byte): YearDuration
    operator fun div(other: kotlin.Byte): YearDuration
    operator fun rem(other: kotlin.Byte): YearDuration


    operator fun times(other: kotlin.Short): YearDuration
    operator fun div(other: kotlin.Short): YearDuration
    operator fun rem(other: kotlin.Short): YearDuration


    operator fun times(other: kotlin.Int): YearDuration
    operator fun div(other: kotlin.Int): YearDuration
    operator fun rem(other: kotlin.Int): YearDuration


    operator fun times(other: kotlin.Long): YearDuration
    operator fun div(other: kotlin.Long): YearDuration
    operator fun rem(other: kotlin.Long): YearDuration


    operator fun times(other: kotlin.Float): YearDuration
    operator fun div(other: kotlin.Float): YearDuration
    operator fun rem(other: kotlin.Float): YearDuration


    operator fun times(other: kotlin.Double): YearDuration
    operator fun div(other: kotlin.Double): YearDuration
    operator fun rem(other: kotlin.Double): YearDuration

}

data class Century(override val value: MeasurementUnitValue) : YearDuration {

    override val unitName: String = "century"

    override fun formatters(language: MeasurementUnitLanguage) = language.durationCentury
    override fun toYear() = Year(value * (100) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Century(0.0)
        fun fromYear(value: Year) = Century((value.value - 0) / (100))
    }

    operator fun plus(other: Century): Century = Century(this.value + other.value)
    operator fun minus(other: Century): Century = Century(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Century = Century(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Century = Century(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Century = Century(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Century = Century(this.value.times(other))
    override operator fun div(other: kotlin.Short): Century = Century(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Century = Century(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Century = Century(this.value.times(other))
    override operator fun div(other: kotlin.Int): Century = Century(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Century = Century(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Century = Century(this.value.times(other))
    override operator fun div(other: kotlin.Long): Century = Century(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Century = Century(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Century = Century(this.value.times(other))
    override operator fun div(other: kotlin.Float): Century = Century(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Century = Century(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Century = Century(this.value.times(other))
    override operator fun div(other: kotlin.Double): Century = Century(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Century = Century(this.value.rem(other))


    override operator fun unaryMinus(): Century = Century(-value)
    override operator fun unaryPlus(): Century = this
    override operator fun inc(): Century = Century(this.value + 1)
    override operator fun dec(): Century = Century(this.value - 1)

    override fun round(): Century = Century(round(this.value))
    override fun truncate(): Century = Century(truncate(this.value))
    override fun floor(): Century = Century(floor(this.value))
    override fun ceil(): Century = Century(ceil(this.value))
    override fun abs(): Century = Century(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Century -> this.value == other.value
        is YearDuration -> this.toYear().value == other.toYear().value
        else -> false
    }

    override fun hashCode(): Int = this.toYear().value.hashCode()


}

val kotlin.Byte.century
    get() = Century(this)


val kotlin.Short.century
    get() = Century(this)


val kotlin.Int.century
    get() = Century(this)


val kotlin.Long.century
    get() = Century(this)


val kotlin.Float.century
    get() = Century(this)


val kotlin.Double.century
    get() = Century(this)


data class Decade(override val value: MeasurementUnitValue) : YearDuration {

    override val unitName: String = "decade"

    override fun formatters(language: MeasurementUnitLanguage) = language.durationDecade
    override fun toYear() = Year(value * (10) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Decade(0.0)
        fun fromYear(value: Year) = Decade((value.value - 0) / (10))
    }

    operator fun plus(other: Decade): Decade = Decade(this.value + other.value)
    operator fun minus(other: Decade): Decade = Decade(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Decade = Decade(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Decade = Decade(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Decade = Decade(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Decade = Decade(this.value.times(other))
    override operator fun div(other: kotlin.Short): Decade = Decade(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Decade = Decade(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Decade = Decade(this.value.times(other))
    override operator fun div(other: kotlin.Int): Decade = Decade(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Decade = Decade(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Decade = Decade(this.value.times(other))
    override operator fun div(other: kotlin.Long): Decade = Decade(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Decade = Decade(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Decade = Decade(this.value.times(other))
    override operator fun div(other: kotlin.Float): Decade = Decade(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Decade = Decade(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Decade = Decade(this.value.times(other))
    override operator fun div(other: kotlin.Double): Decade = Decade(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Decade = Decade(this.value.rem(other))


    override operator fun unaryMinus(): Decade = Decade(-value)
    override operator fun unaryPlus(): Decade = this
    override operator fun inc(): Decade = Decade(this.value + 1)
    override operator fun dec(): Decade = Decade(this.value - 1)

    override fun round(): Decade = Decade(round(this.value))
    override fun truncate(): Decade = Decade(truncate(this.value))
    override fun floor(): Decade = Decade(floor(this.value))
    override fun ceil(): Decade = Decade(ceil(this.value))
    override fun abs(): Decade = Decade(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Decade -> this.value == other.value
        is YearDuration -> this.toYear().value == other.toYear().value
        else -> false
    }

    override fun hashCode(): Int = this.toYear().value.hashCode()


}

val kotlin.Byte.decade
    get() = Decade(this)


val kotlin.Short.decade
    get() = Decade(this)


val kotlin.Int.decade
    get() = Decade(this)


val kotlin.Long.decade
    get() = Decade(this)


val kotlin.Float.decade
    get() = Decade(this)


val kotlin.Double.decade
    get() = Decade(this)


data class Year(override val value: MeasurementUnitValue) : YearDuration {

    override val unitName: String = "year"

    override fun formatters(language: MeasurementUnitLanguage) = language.durationYear
    override fun toYear() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Year(0.0)
        fun fromYear(value: Year) = value
    }

    operator fun plus(other: Year): Year = Year(this.value + other.value)
    operator fun minus(other: Year): Year = Year(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Year = Year(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Year = Year(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Year = Year(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Year = Year(this.value.times(other))
    override operator fun div(other: kotlin.Short): Year = Year(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Year = Year(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Year = Year(this.value.times(other))
    override operator fun div(other: kotlin.Int): Year = Year(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Year = Year(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Year = Year(this.value.times(other))
    override operator fun div(other: kotlin.Long): Year = Year(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Year = Year(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Year = Year(this.value.times(other))
    override operator fun div(other: kotlin.Float): Year = Year(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Year = Year(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Year = Year(this.value.times(other))
    override operator fun div(other: kotlin.Double): Year = Year(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Year = Year(this.value.rem(other))


    override operator fun unaryMinus(): Year = Year(-value)
    override operator fun unaryPlus(): Year = this
    override operator fun inc(): Year = Year(this.value + 1)
    override operator fun dec(): Year = Year(this.value - 1)

    override fun round(): Year = Year(round(this.value))
    override fun truncate(): Year = Year(truncate(this.value))
    override fun floor(): Year = Year(floor(this.value))
    override fun ceil(): Year = Year(ceil(this.value))
    override fun abs(): Year = Year(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Year -> this.value == other.value
        is YearDuration -> this.toYear().value == other.toYear().value
        else -> false
    }

    override fun hashCode(): Int = this.toYear().value.hashCode()


}

val kotlin.Byte.year
    get() = Year(this)


val kotlin.Short.year
    get() = Year(this)


val kotlin.Int.year
    get() = Year(this)


val kotlin.Long.year
    get() = Year(this)


val kotlin.Float.year
    get() = Year(this)


val kotlin.Double.year
    get() = Year(this)


data class Quarter(override val value: MeasurementUnitValue) : YearDuration {

    override val unitName: String = "quarter"

    override fun formatters(language: MeasurementUnitLanguage) = language.durationQuarter
    override fun toYear() = Year(value * (0.25) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Quarter(0.0)
        fun fromYear(value: Year) = Quarter((value.value - 0) / (0.25))
    }

    operator fun plus(other: Quarter): Quarter = Quarter(this.value + other.value)
    operator fun minus(other: Quarter): Quarter = Quarter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Quarter = Quarter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Quarter = Quarter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Quarter = Quarter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Quarter = Quarter(this.value.times(other))
    override operator fun div(other: kotlin.Short): Quarter = Quarter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Quarter = Quarter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Quarter = Quarter(this.value.times(other))
    override operator fun div(other: kotlin.Int): Quarter = Quarter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Quarter = Quarter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Quarter = Quarter(this.value.times(other))
    override operator fun div(other: kotlin.Long): Quarter = Quarter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Quarter = Quarter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Quarter = Quarter(this.value.times(other))
    override operator fun div(other: kotlin.Float): Quarter = Quarter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Quarter = Quarter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Quarter = Quarter(this.value.times(other))
    override operator fun div(other: kotlin.Double): Quarter = Quarter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Quarter = Quarter(this.value.rem(other))


    override operator fun unaryMinus(): Quarter = Quarter(-value)
    override operator fun unaryPlus(): Quarter = this
    override operator fun inc(): Quarter = Quarter(this.value + 1)
    override operator fun dec(): Quarter = Quarter(this.value - 1)

    override fun round(): Quarter = Quarter(round(this.value))
    override fun truncate(): Quarter = Quarter(truncate(this.value))
    override fun floor(): Quarter = Quarter(floor(this.value))
    override fun ceil(): Quarter = Quarter(ceil(this.value))
    override fun abs(): Quarter = Quarter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Quarter -> this.value == other.value
        is YearDuration -> this.toYear().value == other.toYear().value
        else -> false
    }

    override fun hashCode(): Int = this.toYear().value.hashCode()


}

val kotlin.Byte.quarter
    get() = Quarter(this)


val kotlin.Short.quarter
    get() = Quarter(this)


val kotlin.Int.quarter
    get() = Quarter(this)


val kotlin.Long.quarter
    get() = Quarter(this)


val kotlin.Float.quarter
    get() = Quarter(this)


val kotlin.Double.quarter
    get() = Quarter(this)


data class Month(override val value: MeasurementUnitValue) : YearDuration {

    override val unitName: String = "month"

    override fun formatters(language: MeasurementUnitLanguage) = language.durationMonth
    override fun toYear() = Year(value * (0.08333333333333333) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Month(0.0)
        fun fromYear(value: Year) = Month((value.value - 0) / (0.08333333333333333))
    }

    operator fun plus(other: Month): Month = Month(this.value + other.value)
    operator fun minus(other: Month): Month = Month(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Month = Month(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Month = Month(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Month = Month(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Month = Month(this.value.times(other))
    override operator fun div(other: kotlin.Short): Month = Month(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Month = Month(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Month = Month(this.value.times(other))
    override operator fun div(other: kotlin.Int): Month = Month(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Month = Month(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Month = Month(this.value.times(other))
    override operator fun div(other: kotlin.Long): Month = Month(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Month = Month(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Month = Month(this.value.times(other))
    override operator fun div(other: kotlin.Float): Month = Month(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Month = Month(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Month = Month(this.value.times(other))
    override operator fun div(other: kotlin.Double): Month = Month(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Month = Month(this.value.rem(other))


    override operator fun unaryMinus(): Month = Month(-value)
    override operator fun unaryPlus(): Month = this
    override operator fun inc(): Month = Month(this.value + 1)
    override operator fun dec(): Month = Month(this.value - 1)

    override fun round(): Month = Month(round(this.value))
    override fun truncate(): Month = Month(truncate(this.value))
    override fun floor(): Month = Month(floor(this.value))
    override fun ceil(): Month = Month(ceil(this.value))
    override fun abs(): Month = Month(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Month -> this.value == other.value
        is YearDuration -> this.toYear().value == other.toYear().value
        else -> false
    }

    override fun hashCode(): Int = this.toYear().value.hashCode()


}

val kotlin.Byte.month
    get() = Month(this)


val kotlin.Short.month
    get() = Month(this)


val kotlin.Int.month
    get() = Month(this)


val kotlin.Long.month
    get() = Month(this)


val kotlin.Float.month
    get() = Month(this)


val kotlin.Double.month
    get() = Month(this)
                            
