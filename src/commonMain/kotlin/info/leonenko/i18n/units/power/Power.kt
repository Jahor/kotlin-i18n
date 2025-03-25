package info.leonenko.i18n.units.power

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

@Serializable(Power.Serializer::class)
interface Power : MeasurementUnit, Comparable<Power> {
    override val measurement: String get() = "power"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Power> = emptyMap()) : KSerializer<Power> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Power", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Power {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Power unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "gigawatt" -> Gigawatt(value)
                "megawatt" -> Megawatt(value)
                "kilowatt" -> Kilowatt(value)
                "watt" -> Watt(value)
                "milliwatt" -> Milliwatt(value)
                "horsepower" -> Horsepower(value)
                "kilogram-square-meter-per-cubic-second" -> KilogramSquareMeterPerCubicSecond(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Power unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Power) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toGigawatt(): Gigawatt {
        return Gigawatt.fromKilogramSquareMeterPerCubicSecond(toKilogramSquareMeterPerCubicSecond())
    }

    fun toMegawatt(): Megawatt {
        return Megawatt.fromKilogramSquareMeterPerCubicSecond(toKilogramSquareMeterPerCubicSecond())
    }

    fun toKilowatt(): Kilowatt {
        return Kilowatt.fromKilogramSquareMeterPerCubicSecond(toKilogramSquareMeterPerCubicSecond())
    }

    fun toWatt(): Watt {
        return Watt.fromKilogramSquareMeterPerCubicSecond(toKilogramSquareMeterPerCubicSecond())
    }

    fun toMilliwatt(): Milliwatt {
        return Milliwatt.fromKilogramSquareMeterPerCubicSecond(toKilogramSquareMeterPerCubicSecond())
    }

    fun toHorsepower(): Horsepower {
        return Horsepower.fromKilogramSquareMeterPerCubicSecond(toKilogramSquareMeterPerCubicSecond())
    }

    fun toKilogramSquareMeterPerCubicSecond(): KilogramSquareMeterPerCubicSecond
    override fun getBaseUnitValue() = toKilogramSquareMeterPerCubicSecond().value

    companion object {
        val ZERO: Power = KilogramSquareMeterPerCubicSecond(0.0)

    }

    override operator fun compareTo(other: Power): Int = this.toKilogramSquareMeterPerCubicSecond().value.compareTo(other.toKilogramSquareMeterPerCubicSecond().value)

    operator fun plus(other: Power): KilogramSquareMeterPerCubicSecond = this.toKilogramSquareMeterPerCubicSecond() + other.toKilogramSquareMeterPerCubicSecond()
    operator fun minus(other: Power): KilogramSquareMeterPerCubicSecond = this.toKilogramSquareMeterPerCubicSecond() - other.toKilogramSquareMeterPerCubicSecond()
    operator fun div(other: Power): Double = this.toKilogramSquareMeterPerCubicSecond().value / other.toKilogramSquareMeterPerCubicSecond().value
    operator fun unaryMinus(): Power
    operator fun unaryPlus(): Power
    operator fun inc(): Power
    operator fun dec(): Power

    fun round(): Power
    fun truncate(): Power
    fun floor(): Power
    fun ceil(): Power
    fun abs(): Power


    operator fun times(other: Byte): Power
    operator fun div(other: Byte): Power
    operator fun rem(other: Byte): Power


    operator fun times(other: Short): Power
    operator fun div(other: Short): Power
    operator fun rem(other: Short): Power


    operator fun times(other: Int): Power
    operator fun div(other: Int): Power
    operator fun rem(other: Int): Power


    operator fun times(other: Long): Power
    operator fun div(other: Long): Power
    operator fun rem(other: Long): Power


    operator fun times(other: Float): Power
    operator fun div(other: Float): Power
    operator fun rem(other: Float): Power


    operator fun times(other: Double): Power
    operator fun div(other: Double): Power
    operator fun rem(other: Double): Power

}

data class Gigawatt(override val value: MeasurementUnitValue) : Power {

    override val unitName: String = "gigawatt"

    override fun formatters(language: MeasurementUnitLanguage) = language.powerGigawatt
    override fun toKilogramSquareMeterPerCubicSecond() = KilogramSquareMeterPerCubicSecond(value * (1 * (1e9)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Gigawatt(0.0)
        fun fromKilogramSquareMeterPerCubicSecond(value: KilogramSquareMeterPerCubicSecond) = Gigawatt((value.value - 0) / (1 * (1e9)))
    }

    operator fun plus(other: Gigawatt): Gigawatt = Gigawatt(this.value + other.value)
    operator fun minus(other: Gigawatt): Gigawatt = Gigawatt(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Gigawatt = Gigawatt(this.value.times(other))
    override operator fun div(other: Byte): Gigawatt = Gigawatt(this.value.div(other))
    override operator fun rem(other: Byte): Gigawatt = Gigawatt(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Gigawatt = Gigawatt(this.value.times(other))
    override operator fun div(other: Short): Gigawatt = Gigawatt(this.value.div(other))
    override operator fun rem(other: Short): Gigawatt = Gigawatt(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Gigawatt = Gigawatt(this.value.times(other))
    override operator fun div(other: Int): Gigawatt = Gigawatt(this.value.div(other))
    override operator fun rem(other: Int): Gigawatt = Gigawatt(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Gigawatt = Gigawatt(this.value.times(other))
    override operator fun div(other: Long): Gigawatt = Gigawatt(this.value.div(other))
    override operator fun rem(other: Long): Gigawatt = Gigawatt(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Gigawatt = Gigawatt(this.value.times(other))
    override operator fun div(other: Float): Gigawatt = Gigawatt(this.value.div(other))
    override operator fun rem(other: Float): Gigawatt = Gigawatt(this.value.rem(other))


    override operator fun times(other: Double): Gigawatt = Gigawatt(this.value.times(other))
    override operator fun div(other: Double): Gigawatt = Gigawatt(this.value.div(other))
    override operator fun rem(other: Double): Gigawatt = Gigawatt(this.value.rem(other))


    override operator fun unaryMinus(): Gigawatt = Gigawatt(-value)
    override operator fun unaryPlus(): Gigawatt = this
    override operator fun inc(): Gigawatt = Gigawatt(this.value + 1)
    override operator fun dec(): Gigawatt = Gigawatt(this.value - 1)

    override fun round(): Gigawatt = Gigawatt(round(this.value))
    override fun truncate(): Gigawatt = Gigawatt(truncate(this.value))
    override fun floor(): Gigawatt = Gigawatt(floor(this.value))
    override fun ceil(): Gigawatt = Gigawatt(ceil(this.value))
    override fun abs(): Gigawatt = Gigawatt(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Gigawatt -> this.value == other.value
        is Power -> this.toKilogramSquareMeterPerCubicSecond().value == other.toKilogramSquareMeterPerCubicSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerCubicSecond().value.hashCode()


}

val Byte.gigawatt
    get() = Gigawatt(this)


val Short.gigawatt
    get() = Gigawatt(this)


val Int.gigawatt
    get() = Gigawatt(this)


val Long.gigawatt
    get() = Gigawatt(this)


val Float.gigawatt
    get() = Gigawatt(this)


val Double.gigawatt
    get() = Gigawatt(this)


data class Megawatt(override val value: MeasurementUnitValue) : Power {

    override val unitName: String = "megawatt"

    override fun formatters(language: MeasurementUnitLanguage) = language.powerMegawatt
    override fun toKilogramSquareMeterPerCubicSecond() = KilogramSquareMeterPerCubicSecond(value * (1 * (1e6)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Megawatt(0.0)
        fun fromKilogramSquareMeterPerCubicSecond(value: KilogramSquareMeterPerCubicSecond) = Megawatt((value.value - 0) / (1 * (1e6)))
    }

    operator fun plus(other: Megawatt): Megawatt = Megawatt(this.value + other.value)
    operator fun minus(other: Megawatt): Megawatt = Megawatt(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Megawatt = Megawatt(this.value.times(other))
    override operator fun div(other: Byte): Megawatt = Megawatt(this.value.div(other))
    override operator fun rem(other: Byte): Megawatt = Megawatt(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Megawatt = Megawatt(this.value.times(other))
    override operator fun div(other: Short): Megawatt = Megawatt(this.value.div(other))
    override operator fun rem(other: Short): Megawatt = Megawatt(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Megawatt = Megawatt(this.value.times(other))
    override operator fun div(other: Int): Megawatt = Megawatt(this.value.div(other))
    override operator fun rem(other: Int): Megawatt = Megawatt(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Megawatt = Megawatt(this.value.times(other))
    override operator fun div(other: Long): Megawatt = Megawatt(this.value.div(other))
    override operator fun rem(other: Long): Megawatt = Megawatt(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Megawatt = Megawatt(this.value.times(other))
    override operator fun div(other: Float): Megawatt = Megawatt(this.value.div(other))
    override operator fun rem(other: Float): Megawatt = Megawatt(this.value.rem(other))


    override operator fun times(other: Double): Megawatt = Megawatt(this.value.times(other))
    override operator fun div(other: Double): Megawatt = Megawatt(this.value.div(other))
    override operator fun rem(other: Double): Megawatt = Megawatt(this.value.rem(other))


    override operator fun unaryMinus(): Megawatt = Megawatt(-value)
    override operator fun unaryPlus(): Megawatt = this
    override operator fun inc(): Megawatt = Megawatt(this.value + 1)
    override operator fun dec(): Megawatt = Megawatt(this.value - 1)

    override fun round(): Megawatt = Megawatt(round(this.value))
    override fun truncate(): Megawatt = Megawatt(truncate(this.value))
    override fun floor(): Megawatt = Megawatt(floor(this.value))
    override fun ceil(): Megawatt = Megawatt(ceil(this.value))
    override fun abs(): Megawatt = Megawatt(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Megawatt -> this.value == other.value
        is Power -> this.toKilogramSquareMeterPerCubicSecond().value == other.toKilogramSquareMeterPerCubicSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerCubicSecond().value.hashCode()


}

val Byte.megawatt
    get() = Megawatt(this)


val Short.megawatt
    get() = Megawatt(this)


val Int.megawatt
    get() = Megawatt(this)


val Long.megawatt
    get() = Megawatt(this)


val Float.megawatt
    get() = Megawatt(this)


val Double.megawatt
    get() = Megawatt(this)


data class Kilowatt(override val value: MeasurementUnitValue) : Power {

    override val unitName: String = "kilowatt"

    override fun formatters(language: MeasurementUnitLanguage) = language.powerKilowatt
    override fun toKilogramSquareMeterPerCubicSecond() = KilogramSquareMeterPerCubicSecond(value * (1 * (1e3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Kilowatt(0.0)
        fun fromKilogramSquareMeterPerCubicSecond(value: KilogramSquareMeterPerCubicSecond) = Kilowatt((value.value - 0) / (1 * (1e3)))
    }

    operator fun plus(other: Kilowatt): Kilowatt = Kilowatt(this.value + other.value)
    operator fun minus(other: Kilowatt): Kilowatt = Kilowatt(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Kilowatt = Kilowatt(this.value.times(other))
    override operator fun div(other: Byte): Kilowatt = Kilowatt(this.value.div(other))
    override operator fun rem(other: Byte): Kilowatt = Kilowatt(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Kilowatt = Kilowatt(this.value.times(other))
    override operator fun div(other: Short): Kilowatt = Kilowatt(this.value.div(other))
    override operator fun rem(other: Short): Kilowatt = Kilowatt(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Kilowatt = Kilowatt(this.value.times(other))
    override operator fun div(other: Int): Kilowatt = Kilowatt(this.value.div(other))
    override operator fun rem(other: Int): Kilowatt = Kilowatt(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Kilowatt = Kilowatt(this.value.times(other))
    override operator fun div(other: Long): Kilowatt = Kilowatt(this.value.div(other))
    override operator fun rem(other: Long): Kilowatt = Kilowatt(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Kilowatt = Kilowatt(this.value.times(other))
    override operator fun div(other: Float): Kilowatt = Kilowatt(this.value.div(other))
    override operator fun rem(other: Float): Kilowatt = Kilowatt(this.value.rem(other))


    override operator fun times(other: Double): Kilowatt = Kilowatt(this.value.times(other))
    override operator fun div(other: Double): Kilowatt = Kilowatt(this.value.div(other))
    override operator fun rem(other: Double): Kilowatt = Kilowatt(this.value.rem(other))


    override operator fun unaryMinus(): Kilowatt = Kilowatt(-value)
    override operator fun unaryPlus(): Kilowatt = this
    override operator fun inc(): Kilowatt = Kilowatt(this.value + 1)
    override operator fun dec(): Kilowatt = Kilowatt(this.value - 1)

    override fun round(): Kilowatt = Kilowatt(round(this.value))
    override fun truncate(): Kilowatt = Kilowatt(truncate(this.value))
    override fun floor(): Kilowatt = Kilowatt(floor(this.value))
    override fun ceil(): Kilowatt = Kilowatt(ceil(this.value))
    override fun abs(): Kilowatt = Kilowatt(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Kilowatt -> this.value == other.value
        is Power -> this.toKilogramSquareMeterPerCubicSecond().value == other.toKilogramSquareMeterPerCubicSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerCubicSecond().value.hashCode()


}

val Byte.kilowatt
    get() = Kilowatt(this)


val Short.kilowatt
    get() = Kilowatt(this)


val Int.kilowatt
    get() = Kilowatt(this)


val Long.kilowatt
    get() = Kilowatt(this)


val Float.kilowatt
    get() = Kilowatt(this)


val Double.kilowatt
    get() = Kilowatt(this)


data class Watt(override val value: MeasurementUnitValue) : Power {

    override val unitName: String = "watt"

    override fun formatters(language: MeasurementUnitLanguage) = language.powerWatt
    override fun toKilogramSquareMeterPerCubicSecond() = KilogramSquareMeterPerCubicSecond(value * (1) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Watt(0.0)
        fun fromKilogramSquareMeterPerCubicSecond(value: KilogramSquareMeterPerCubicSecond) = Watt((value.value - 0) / (1))
    }

    operator fun plus(other: Watt): Watt = Watt(this.value + other.value)
    operator fun minus(other: Watt): Watt = Watt(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Watt = Watt(this.value.times(other))
    override operator fun div(other: Byte): Watt = Watt(this.value.div(other))
    override operator fun rem(other: Byte): Watt = Watt(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Watt = Watt(this.value.times(other))
    override operator fun div(other: Short): Watt = Watt(this.value.div(other))
    override operator fun rem(other: Short): Watt = Watt(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Watt = Watt(this.value.times(other))
    override operator fun div(other: Int): Watt = Watt(this.value.div(other))
    override operator fun rem(other: Int): Watt = Watt(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Watt = Watt(this.value.times(other))
    override operator fun div(other: Long): Watt = Watt(this.value.div(other))
    override operator fun rem(other: Long): Watt = Watt(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Watt = Watt(this.value.times(other))
    override operator fun div(other: Float): Watt = Watt(this.value.div(other))
    override operator fun rem(other: Float): Watt = Watt(this.value.rem(other))


    override operator fun times(other: Double): Watt = Watt(this.value.times(other))
    override operator fun div(other: Double): Watt = Watt(this.value.div(other))
    override operator fun rem(other: Double): Watt = Watt(this.value.rem(other))


    override operator fun unaryMinus(): Watt = Watt(-value)
    override operator fun unaryPlus(): Watt = this
    override operator fun inc(): Watt = Watt(this.value + 1)
    override operator fun dec(): Watt = Watt(this.value - 1)

    override fun round(): Watt = Watt(round(this.value))
    override fun truncate(): Watt = Watt(truncate(this.value))
    override fun floor(): Watt = Watt(floor(this.value))
    override fun ceil(): Watt = Watt(ceil(this.value))
    override fun abs(): Watt = Watt(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Watt -> this.value == other.value
        is Power -> this.toKilogramSquareMeterPerCubicSecond().value == other.toKilogramSquareMeterPerCubicSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerCubicSecond().value.hashCode()


}

val Byte.watt
    get() = Watt(this)


val Short.watt
    get() = Watt(this)


val Int.watt
    get() = Watt(this)


val Long.watt
    get() = Watt(this)


val Float.watt
    get() = Watt(this)


val Double.watt
    get() = Watt(this)


data class Milliwatt(override val value: MeasurementUnitValue) : Power {

    override val unitName: String = "milliwatt"

    override fun formatters(language: MeasurementUnitLanguage) = language.powerMilliwatt
    override fun toKilogramSquareMeterPerCubicSecond() = KilogramSquareMeterPerCubicSecond(value * (1 * (1e-3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Milliwatt(0.0)
        fun fromKilogramSquareMeterPerCubicSecond(value: KilogramSquareMeterPerCubicSecond) = Milliwatt((value.value - 0) / (1 * (1e-3)))
    }

    operator fun plus(other: Milliwatt): Milliwatt = Milliwatt(this.value + other.value)
    operator fun minus(other: Milliwatt): Milliwatt = Milliwatt(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Milliwatt = Milliwatt(this.value.times(other))
    override operator fun div(other: Byte): Milliwatt = Milliwatt(this.value.div(other))
    override operator fun rem(other: Byte): Milliwatt = Milliwatt(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Milliwatt = Milliwatt(this.value.times(other))
    override operator fun div(other: Short): Milliwatt = Milliwatt(this.value.div(other))
    override operator fun rem(other: Short): Milliwatt = Milliwatt(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Milliwatt = Milliwatt(this.value.times(other))
    override operator fun div(other: Int): Milliwatt = Milliwatt(this.value.div(other))
    override operator fun rem(other: Int): Milliwatt = Milliwatt(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Milliwatt = Milliwatt(this.value.times(other))
    override operator fun div(other: Long): Milliwatt = Milliwatt(this.value.div(other))
    override operator fun rem(other: Long): Milliwatt = Milliwatt(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Milliwatt = Milliwatt(this.value.times(other))
    override operator fun div(other: Float): Milliwatt = Milliwatt(this.value.div(other))
    override operator fun rem(other: Float): Milliwatt = Milliwatt(this.value.rem(other))


    override operator fun times(other: Double): Milliwatt = Milliwatt(this.value.times(other))
    override operator fun div(other: Double): Milliwatt = Milliwatt(this.value.div(other))
    override operator fun rem(other: Double): Milliwatt = Milliwatt(this.value.rem(other))


    override operator fun unaryMinus(): Milliwatt = Milliwatt(-value)
    override operator fun unaryPlus(): Milliwatt = this
    override operator fun inc(): Milliwatt = Milliwatt(this.value + 1)
    override operator fun dec(): Milliwatt = Milliwatt(this.value - 1)

    override fun round(): Milliwatt = Milliwatt(round(this.value))
    override fun truncate(): Milliwatt = Milliwatt(truncate(this.value))
    override fun floor(): Milliwatt = Milliwatt(floor(this.value))
    override fun ceil(): Milliwatt = Milliwatt(ceil(this.value))
    override fun abs(): Milliwatt = Milliwatt(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Milliwatt -> this.value == other.value
        is Power -> this.toKilogramSquareMeterPerCubicSecond().value == other.toKilogramSquareMeterPerCubicSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerCubicSecond().value.hashCode()


}

val Byte.milliwatt
    get() = Milliwatt(this)


val Short.milliwatt
    get() = Milliwatt(this)


val Int.milliwatt
    get() = Milliwatt(this)


val Long.milliwatt
    get() = Milliwatt(this)


val Float.milliwatt
    get() = Milliwatt(this)


val Double.milliwatt
    get() = Milliwatt(this)


data class Horsepower(override val value: MeasurementUnitValue) : Power {

    override val unitName: String = "horsepower"

    override fun formatters(language: MeasurementUnitLanguage) = language.powerHorsepower
    override fun toKilogramSquareMeterPerCubicSecond() = KilogramSquareMeterPerCubicSecond(value * (745.6998715822704) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Horsepower(0.0)
        fun fromKilogramSquareMeterPerCubicSecond(value: KilogramSquareMeterPerCubicSecond) = Horsepower((value.value - 0) / (745.6998715822704))
    }

    operator fun plus(other: Horsepower): Horsepower = Horsepower(this.value + other.value)
    operator fun minus(other: Horsepower): Horsepower = Horsepower(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Horsepower = Horsepower(this.value.times(other))
    override operator fun div(other: Byte): Horsepower = Horsepower(this.value.div(other))
    override operator fun rem(other: Byte): Horsepower = Horsepower(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Horsepower = Horsepower(this.value.times(other))
    override operator fun div(other: Short): Horsepower = Horsepower(this.value.div(other))
    override operator fun rem(other: Short): Horsepower = Horsepower(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Horsepower = Horsepower(this.value.times(other))
    override operator fun div(other: Int): Horsepower = Horsepower(this.value.div(other))
    override operator fun rem(other: Int): Horsepower = Horsepower(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Horsepower = Horsepower(this.value.times(other))
    override operator fun div(other: Long): Horsepower = Horsepower(this.value.div(other))
    override operator fun rem(other: Long): Horsepower = Horsepower(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Horsepower = Horsepower(this.value.times(other))
    override operator fun div(other: Float): Horsepower = Horsepower(this.value.div(other))
    override operator fun rem(other: Float): Horsepower = Horsepower(this.value.rem(other))


    override operator fun times(other: Double): Horsepower = Horsepower(this.value.times(other))
    override operator fun div(other: Double): Horsepower = Horsepower(this.value.div(other))
    override operator fun rem(other: Double): Horsepower = Horsepower(this.value.rem(other))


    override operator fun unaryMinus(): Horsepower = Horsepower(-value)
    override operator fun unaryPlus(): Horsepower = this
    override operator fun inc(): Horsepower = Horsepower(this.value + 1)
    override operator fun dec(): Horsepower = Horsepower(this.value - 1)

    override fun round(): Horsepower = Horsepower(round(this.value))
    override fun truncate(): Horsepower = Horsepower(truncate(this.value))
    override fun floor(): Horsepower = Horsepower(floor(this.value))
    override fun ceil(): Horsepower = Horsepower(ceil(this.value))
    override fun abs(): Horsepower = Horsepower(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Horsepower -> this.value == other.value
        is Power -> this.toKilogramSquareMeterPerCubicSecond().value == other.toKilogramSquareMeterPerCubicSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerCubicSecond().value.hashCode()


}

val Byte.horsepower
    get() = Horsepower(this)


val Short.horsepower
    get() = Horsepower(this)


val Int.horsepower
    get() = Horsepower(this)


val Long.horsepower
    get() = Horsepower(this)


val Float.horsepower
    get() = Horsepower(this)


val Double.horsepower
    get() = Horsepower(this)


data class KilogramSquareMeterPerCubicSecond(override val value: MeasurementUnitValue) : Power {

    override val unitName: String = "kilogram-square-meter-per-cubic-second"

    override fun formatters(language: MeasurementUnitLanguage) = language.powerKilogramSquareMeterPerCubicSecond
    override fun toKilogramSquareMeterPerCubicSecond() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = KilogramSquareMeterPerCubicSecond(0.0)
        fun fromKilogramSquareMeterPerCubicSecond(value: KilogramSquareMeterPerCubicSecond) = value
    }

    operator fun plus(other: KilogramSquareMeterPerCubicSecond): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value + other.value)
    operator fun minus(other: KilogramSquareMeterPerCubicSecond): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.times(other))
    override operator fun div(other: Byte): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.div(other))
    override operator fun rem(other: Byte): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.times(other))
    override operator fun div(other: Short): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.div(other))
    override operator fun rem(other: Short): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.times(other))
    override operator fun div(other: Int): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.div(other))
    override operator fun rem(other: Int): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.times(other))
    override operator fun div(other: Long): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.div(other))
    override operator fun rem(other: Long): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.times(other))
    override operator fun div(other: Float): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.div(other))
    override operator fun rem(other: Float): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.rem(other))


    override operator fun times(other: Double): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.times(other))
    override operator fun div(other: Double): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.div(other))
    override operator fun rem(other: Double): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.rem(other))


    override operator fun unaryMinus(): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(-value)
    override operator fun unaryPlus(): KilogramSquareMeterPerCubicSecond = this
    override operator fun inc(): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value + 1)
    override operator fun dec(): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value - 1)

    override fun round(): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(round(this.value))
    override fun truncate(): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(truncate(this.value))
    override fun floor(): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(floor(this.value))
    override fun ceil(): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(ceil(this.value))
    override fun abs(): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is KilogramSquareMeterPerCubicSecond -> this.value == other.value
        is Power -> this.toKilogramSquareMeterPerCubicSecond().value == other.toKilogramSquareMeterPerCubicSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerCubicSecond().value.hashCode()


}

val Byte.kilogramSquareMeterPerCubicSecond
    get() = KilogramSquareMeterPerCubicSecond(this)


val Short.kilogramSquareMeterPerCubicSecond
    get() = KilogramSquareMeterPerCubicSecond(this)


val Int.kilogramSquareMeterPerCubicSecond
    get() = KilogramSquareMeterPerCubicSecond(this)


val Long.kilogramSquareMeterPerCubicSecond
    get() = KilogramSquareMeterPerCubicSecond(this)


val Float.kilogramSquareMeterPerCubicSecond
    get() = KilogramSquareMeterPerCubicSecond(this)


val Double.kilogramSquareMeterPerCubicSecond
    get() = KilogramSquareMeterPerCubicSecond(this)
                            
