package info.leonenko.i18n.units.acceleration

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

@Serializable(Acceleration.Serializer::class)
interface Acceleration : MeasurementUnit, Comparable<Acceleration> {
    override val measurement: String get() = "acceleration"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Acceleration> = emptyMap()) : KSerializer<Acceleration> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())

        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Acceleration", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Acceleration {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Acceleration unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "g-force" -> GForce(value)
                "meter-per-square-second" -> MeterPerSquareSecond(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Acceleration unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Acceleration) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toGForce(): GForce {
        return GForce.fromMeterPerSquareSecond(toMeterPerSquareSecond())
    }

    fun toMeterPerSquareSecond(): MeterPerSquareSecond
    override fun getBaseUnitValue() = toMeterPerSquareSecond().value

    companion object {
        val ZERO: Acceleration = MeterPerSquareSecond(0.0)

    }

    override operator fun compareTo(other: Acceleration): Int = this.toMeterPerSquareSecond().value.compareTo(other.toMeterPerSquareSecond().value)

    operator fun plus(other: Acceleration): MeterPerSquareSecond = this.toMeterPerSquareSecond() + other.toMeterPerSquareSecond()
    operator fun minus(other: Acceleration): MeterPerSquareSecond = this.toMeterPerSquareSecond() - other.toMeterPerSquareSecond()
    operator fun div(other: Acceleration): Double = this.toMeterPerSquareSecond().value / other.toMeterPerSquareSecond().value
    operator fun unaryMinus(): Acceleration
    operator fun unaryPlus(): Acceleration
    operator fun inc(): Acceleration
    operator fun dec(): Acceleration

    fun round(): Acceleration
    fun truncate(): Acceleration
    fun floor(): Acceleration
    fun ceil(): Acceleration
    fun abs(): Acceleration


    operator fun times(other: kotlin.Byte): Acceleration
    operator fun div(other: kotlin.Byte): Acceleration
    operator fun rem(other: kotlin.Byte): Acceleration


    operator fun times(other: kotlin.Short): Acceleration
    operator fun div(other: kotlin.Short): Acceleration
    operator fun rem(other: kotlin.Short): Acceleration


    operator fun times(other: kotlin.Int): Acceleration
    operator fun div(other: kotlin.Int): Acceleration
    operator fun rem(other: kotlin.Int): Acceleration


    operator fun times(other: kotlin.Long): Acceleration
    operator fun div(other: kotlin.Long): Acceleration
    operator fun rem(other: kotlin.Long): Acceleration


    operator fun times(other: kotlin.Float): Acceleration
    operator fun div(other: kotlin.Float): Acceleration
    operator fun rem(other: kotlin.Float): Acceleration


    operator fun times(other: kotlin.Double): Acceleration
    operator fun div(other: kotlin.Double): Acceleration
    operator fun rem(other: kotlin.Double): Acceleration

}

data class GForce(override val value: MeasurementUnitValue) : Acceleration {

    override val unitName: String = "g-force"

    override fun formatters(language: MeasurementUnitLanguage) = language.accelerationGForce
    override fun toMeterPerSquareSecond() = MeterPerSquareSecond(value * (9.80665) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = GForce(0.0)
        fun fromMeterPerSquareSecond(value: MeterPerSquareSecond) = GForce((value.value - 0) / (9.80665))
    }

    operator fun plus(other: GForce): GForce = GForce(this.value + other.value)
    operator fun minus(other: GForce): GForce = GForce(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): GForce = GForce(this.value.times(other))
    override operator fun div(other: kotlin.Byte): GForce = GForce(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): GForce = GForce(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): GForce = GForce(this.value.times(other))
    override operator fun div(other: kotlin.Short): GForce = GForce(this.value.div(other))
    override operator fun rem(other: kotlin.Short): GForce = GForce(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): GForce = GForce(this.value.times(other))
    override operator fun div(other: kotlin.Int): GForce = GForce(this.value.div(other))
    override operator fun rem(other: kotlin.Int): GForce = GForce(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): GForce = GForce(this.value.times(other))
    override operator fun div(other: kotlin.Long): GForce = GForce(this.value.div(other))
    override operator fun rem(other: kotlin.Long): GForce = GForce(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): GForce = GForce(this.value.times(other))
    override operator fun div(other: kotlin.Float): GForce = GForce(this.value.div(other))
    override operator fun rem(other: kotlin.Float): GForce = GForce(this.value.rem(other))


    override operator fun times(other: kotlin.Double): GForce = GForce(this.value.times(other))
    override operator fun div(other: kotlin.Double): GForce = GForce(this.value.div(other))
    override operator fun rem(other: kotlin.Double): GForce = GForce(this.value.rem(other))


    override operator fun unaryMinus(): GForce = GForce(-value)
    override operator fun unaryPlus(): GForce = this
    override operator fun inc(): GForce = GForce(this.value + 1)
    override operator fun dec(): GForce = GForce(this.value - 1)

    override fun round(): GForce = GForce(round(this.value))
    override fun truncate(): GForce = GForce(truncate(this.value))
    override fun floor(): GForce = GForce(floor(this.value))
    override fun ceil(): GForce = GForce(ceil(this.value))
    override fun abs(): GForce = GForce(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is GForce -> this.value == other.value
        is Acceleration -> this.toMeterPerSquareSecond().value == other.toMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.gForce
    get() = GForce(this)


val kotlin.Short.gForce
    get() = GForce(this)


val kotlin.Int.gForce
    get() = GForce(this)


val kotlin.Long.gForce
    get() = GForce(this)


val kotlin.Float.gForce
    get() = GForce(this)


val kotlin.Double.gForce
    get() = GForce(this)


data class MeterPerSquareSecond(override val value: MeasurementUnitValue) : Acceleration {

    override val unitName: String = "meter-per-square-second"

    override fun formatters(language: MeasurementUnitLanguage) = language.accelerationMeterPerSquareSecond
    override fun toMeterPerSquareSecond() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = MeterPerSquareSecond(0.0)
        fun fromMeterPerSquareSecond(value: MeterPerSquareSecond) = value
    }

    operator fun plus(other: MeterPerSquareSecond): MeterPerSquareSecond = MeterPerSquareSecond(this.value + other.value)
    operator fun minus(other: MeterPerSquareSecond): MeterPerSquareSecond = MeterPerSquareSecond(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): MeterPerSquareSecond = MeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Byte): MeterPerSquareSecond = MeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): MeterPerSquareSecond = MeterPerSquareSecond(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): MeterPerSquareSecond = MeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Short): MeterPerSquareSecond = MeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Short): MeterPerSquareSecond = MeterPerSquareSecond(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): MeterPerSquareSecond = MeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Int): MeterPerSquareSecond = MeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Int): MeterPerSquareSecond = MeterPerSquareSecond(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): MeterPerSquareSecond = MeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Long): MeterPerSquareSecond = MeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Long): MeterPerSquareSecond = MeterPerSquareSecond(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): MeterPerSquareSecond = MeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Float): MeterPerSquareSecond = MeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Float): MeterPerSquareSecond = MeterPerSquareSecond(this.value.rem(other))


    override operator fun times(other: kotlin.Double): MeterPerSquareSecond = MeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Double): MeterPerSquareSecond = MeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Double): MeterPerSquareSecond = MeterPerSquareSecond(this.value.rem(other))


    override operator fun unaryMinus(): MeterPerSquareSecond = MeterPerSquareSecond(-value)
    override operator fun unaryPlus(): MeterPerSquareSecond = this
    override operator fun inc(): MeterPerSquareSecond = MeterPerSquareSecond(this.value + 1)
    override operator fun dec(): MeterPerSquareSecond = MeterPerSquareSecond(this.value - 1)

    override fun round(): MeterPerSquareSecond = MeterPerSquareSecond(round(this.value))
    override fun truncate(): MeterPerSquareSecond = MeterPerSquareSecond(truncate(this.value))
    override fun floor(): MeterPerSquareSecond = MeterPerSquareSecond(floor(this.value))
    override fun ceil(): MeterPerSquareSecond = MeterPerSquareSecond(ceil(this.value))
    override fun abs(): MeterPerSquareSecond = MeterPerSquareSecond(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is MeterPerSquareSecond -> this.value == other.value
        is Acceleration -> this.toMeterPerSquareSecond().value == other.toMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.meterPerSquareSecond
    get() = MeterPerSquareSecond(this)


val kotlin.Short.meterPerSquareSecond
    get() = MeterPerSquareSecond(this)


val kotlin.Int.meterPerSquareSecond
    get() = MeterPerSquareSecond(this)


val kotlin.Long.meterPerSquareSecond
    get() = MeterPerSquareSecond(this)


val kotlin.Float.meterPerSquareSecond
    get() = MeterPerSquareSecond(this)


val kotlin.Double.meterPerSquareSecond
    get() = MeterPerSquareSecond(this)
                            
