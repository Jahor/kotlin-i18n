package info.leonenko.i18n.units.power

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


    operator fun times(other: kotlin.Byte): Power
    operator fun div(other: kotlin.Byte): Power
    operator fun rem(other: kotlin.Byte): Power


    operator fun times(other: kotlin.Short): Power
    operator fun div(other: kotlin.Short): Power
    operator fun rem(other: kotlin.Short): Power


    operator fun times(other: kotlin.Int): Power
    operator fun div(other: kotlin.Int): Power
    operator fun rem(other: kotlin.Int): Power


    operator fun times(other: kotlin.Long): Power
    operator fun div(other: kotlin.Long): Power
    operator fun rem(other: kotlin.Long): Power


    operator fun times(other: kotlin.Float): Power
    operator fun div(other: kotlin.Float): Power
    operator fun rem(other: kotlin.Float): Power


    operator fun times(other: kotlin.Double): Power
    operator fun div(other: kotlin.Double): Power
    operator fun rem(other: kotlin.Double): Power

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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Gigawatt = Gigawatt(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Gigawatt = Gigawatt(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Gigawatt = Gigawatt(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Gigawatt = Gigawatt(this.value.times(other))
    override operator fun div(other: kotlin.Short): Gigawatt = Gigawatt(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Gigawatt = Gigawatt(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Gigawatt = Gigawatt(this.value.times(other))
    override operator fun div(other: kotlin.Int): Gigawatt = Gigawatt(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Gigawatt = Gigawatt(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Gigawatt = Gigawatt(this.value.times(other))
    override operator fun div(other: kotlin.Long): Gigawatt = Gigawatt(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Gigawatt = Gigawatt(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Gigawatt = Gigawatt(this.value.times(other))
    override operator fun div(other: kotlin.Float): Gigawatt = Gigawatt(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Gigawatt = Gigawatt(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Gigawatt = Gigawatt(this.value.times(other))
    override operator fun div(other: kotlin.Double): Gigawatt = Gigawatt(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Gigawatt = Gigawatt(this.value.rem(other))


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

val kotlin.Byte.gigawatt
    get() = Gigawatt(this)


val kotlin.Short.gigawatt
    get() = Gigawatt(this)


val kotlin.Int.gigawatt
    get() = Gigawatt(this)


val kotlin.Long.gigawatt
    get() = Gigawatt(this)


val kotlin.Float.gigawatt
    get() = Gigawatt(this)


val kotlin.Double.gigawatt
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Megawatt = Megawatt(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Megawatt = Megawatt(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Megawatt = Megawatt(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Megawatt = Megawatt(this.value.times(other))
    override operator fun div(other: kotlin.Short): Megawatt = Megawatt(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Megawatt = Megawatt(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Megawatt = Megawatt(this.value.times(other))
    override operator fun div(other: kotlin.Int): Megawatt = Megawatt(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Megawatt = Megawatt(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Megawatt = Megawatt(this.value.times(other))
    override operator fun div(other: kotlin.Long): Megawatt = Megawatt(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Megawatt = Megawatt(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Megawatt = Megawatt(this.value.times(other))
    override operator fun div(other: kotlin.Float): Megawatt = Megawatt(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Megawatt = Megawatt(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Megawatt = Megawatt(this.value.times(other))
    override operator fun div(other: kotlin.Double): Megawatt = Megawatt(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Megawatt = Megawatt(this.value.rem(other))


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

val kotlin.Byte.megawatt
    get() = Megawatt(this)


val kotlin.Short.megawatt
    get() = Megawatt(this)


val kotlin.Int.megawatt
    get() = Megawatt(this)


val kotlin.Long.megawatt
    get() = Megawatt(this)


val kotlin.Float.megawatt
    get() = Megawatt(this)


val kotlin.Double.megawatt
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Kilowatt = Kilowatt(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Kilowatt = Kilowatt(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Kilowatt = Kilowatt(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Kilowatt = Kilowatt(this.value.times(other))
    override operator fun div(other: kotlin.Short): Kilowatt = Kilowatt(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Kilowatt = Kilowatt(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Kilowatt = Kilowatt(this.value.times(other))
    override operator fun div(other: kotlin.Int): Kilowatt = Kilowatt(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Kilowatt = Kilowatt(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Kilowatt = Kilowatt(this.value.times(other))
    override operator fun div(other: kotlin.Long): Kilowatt = Kilowatt(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Kilowatt = Kilowatt(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Kilowatt = Kilowatt(this.value.times(other))
    override operator fun div(other: kotlin.Float): Kilowatt = Kilowatt(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Kilowatt = Kilowatt(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Kilowatt = Kilowatt(this.value.times(other))
    override operator fun div(other: kotlin.Double): Kilowatt = Kilowatt(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Kilowatt = Kilowatt(this.value.rem(other))


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

val kotlin.Byte.kilowatt
    get() = Kilowatt(this)


val kotlin.Short.kilowatt
    get() = Kilowatt(this)


val kotlin.Int.kilowatt
    get() = Kilowatt(this)


val kotlin.Long.kilowatt
    get() = Kilowatt(this)


val kotlin.Float.kilowatt
    get() = Kilowatt(this)


val kotlin.Double.kilowatt
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Watt = Watt(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Watt = Watt(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Watt = Watt(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Watt = Watt(this.value.times(other))
    override operator fun div(other: kotlin.Short): Watt = Watt(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Watt = Watt(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Watt = Watt(this.value.times(other))
    override operator fun div(other: kotlin.Int): Watt = Watt(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Watt = Watt(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Watt = Watt(this.value.times(other))
    override operator fun div(other: kotlin.Long): Watt = Watt(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Watt = Watt(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Watt = Watt(this.value.times(other))
    override operator fun div(other: kotlin.Float): Watt = Watt(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Watt = Watt(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Watt = Watt(this.value.times(other))
    override operator fun div(other: kotlin.Double): Watt = Watt(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Watt = Watt(this.value.rem(other))


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

val kotlin.Byte.watt
    get() = Watt(this)


val kotlin.Short.watt
    get() = Watt(this)


val kotlin.Int.watt
    get() = Watt(this)


val kotlin.Long.watt
    get() = Watt(this)


val kotlin.Float.watt
    get() = Watt(this)


val kotlin.Double.watt
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Milliwatt = Milliwatt(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Milliwatt = Milliwatt(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Milliwatt = Milliwatt(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Milliwatt = Milliwatt(this.value.times(other))
    override operator fun div(other: kotlin.Short): Milliwatt = Milliwatt(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Milliwatt = Milliwatt(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Milliwatt = Milliwatt(this.value.times(other))
    override operator fun div(other: kotlin.Int): Milliwatt = Milliwatt(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Milliwatt = Milliwatt(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Milliwatt = Milliwatt(this.value.times(other))
    override operator fun div(other: kotlin.Long): Milliwatt = Milliwatt(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Milliwatt = Milliwatt(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Milliwatt = Milliwatt(this.value.times(other))
    override operator fun div(other: kotlin.Float): Milliwatt = Milliwatt(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Milliwatt = Milliwatt(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Milliwatt = Milliwatt(this.value.times(other))
    override operator fun div(other: kotlin.Double): Milliwatt = Milliwatt(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Milliwatt = Milliwatt(this.value.rem(other))


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

val kotlin.Byte.milliwatt
    get() = Milliwatt(this)


val kotlin.Short.milliwatt
    get() = Milliwatt(this)


val kotlin.Int.milliwatt
    get() = Milliwatt(this)


val kotlin.Long.milliwatt
    get() = Milliwatt(this)


val kotlin.Float.milliwatt
    get() = Milliwatt(this)


val kotlin.Double.milliwatt
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Horsepower = Horsepower(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Horsepower = Horsepower(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Horsepower = Horsepower(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Horsepower = Horsepower(this.value.times(other))
    override operator fun div(other: kotlin.Short): Horsepower = Horsepower(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Horsepower = Horsepower(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Horsepower = Horsepower(this.value.times(other))
    override operator fun div(other: kotlin.Int): Horsepower = Horsepower(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Horsepower = Horsepower(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Horsepower = Horsepower(this.value.times(other))
    override operator fun div(other: kotlin.Long): Horsepower = Horsepower(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Horsepower = Horsepower(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Horsepower = Horsepower(this.value.times(other))
    override operator fun div(other: kotlin.Float): Horsepower = Horsepower(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Horsepower = Horsepower(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Horsepower = Horsepower(this.value.times(other))
    override operator fun div(other: kotlin.Double): Horsepower = Horsepower(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Horsepower = Horsepower(this.value.rem(other))


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

val kotlin.Byte.horsepower
    get() = Horsepower(this)


val kotlin.Short.horsepower
    get() = Horsepower(this)


val kotlin.Int.horsepower
    get() = Horsepower(this)


val kotlin.Long.horsepower
    get() = Horsepower(this)


val kotlin.Float.horsepower
    get() = Horsepower(this)


val kotlin.Double.horsepower
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.times(other))
    override operator fun div(other: kotlin.Byte): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.times(other))
    override operator fun div(other: kotlin.Short): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Short): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.times(other))
    override operator fun div(other: kotlin.Int): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Int): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.times(other))
    override operator fun div(other: kotlin.Long): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Long): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.times(other))
    override operator fun div(other: kotlin.Float): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Float): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.rem(other))


    override operator fun times(other: kotlin.Double): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.times(other))
    override operator fun div(other: kotlin.Double): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Double): KilogramSquareMeterPerCubicSecond = KilogramSquareMeterPerCubicSecond(this.value.rem(other))


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

val kotlin.Byte.kilogramSquareMeterPerCubicSecond
    get() = KilogramSquareMeterPerCubicSecond(this)


val kotlin.Short.kilogramSquareMeterPerCubicSecond
    get() = KilogramSquareMeterPerCubicSecond(this)


val kotlin.Int.kilogramSquareMeterPerCubicSecond
    get() = KilogramSquareMeterPerCubicSecond(this)


val kotlin.Long.kilogramSquareMeterPerCubicSecond
    get() = KilogramSquareMeterPerCubicSecond(this)


val kotlin.Float.kilogramSquareMeterPerCubicSecond
    get() = KilogramSquareMeterPerCubicSecond(this)


val kotlin.Double.kilogramSquareMeterPerCubicSecond
    get() = KilogramSquareMeterPerCubicSecond(this)
                            
