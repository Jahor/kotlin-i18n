package info.leonenko.i18n.units.acceleration

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


    operator fun times(other: Byte): Acceleration
    operator fun div(other: Byte): Acceleration
    operator fun rem(other: Byte): Acceleration


    operator fun times(other: Short): Acceleration
    operator fun div(other: Short): Acceleration
    operator fun rem(other: Short): Acceleration


    operator fun times(other: Int): Acceleration
    operator fun div(other: Int): Acceleration
    operator fun rem(other: Int): Acceleration


    operator fun times(other: Long): Acceleration
    operator fun div(other: Long): Acceleration
    operator fun rem(other: Long): Acceleration


    operator fun times(other: Float): Acceleration
    operator fun div(other: Float): Acceleration
    operator fun rem(other: Float): Acceleration


    operator fun times(other: Double): Acceleration
    operator fun div(other: Double): Acceleration
    operator fun rem(other: Double): Acceleration

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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): GForce = GForce(this.value.times(other))
    override operator fun div(other: Byte): GForce = GForce(this.value.div(other))
    override operator fun rem(other: Byte): GForce = GForce(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): GForce = GForce(this.value.times(other))
    override operator fun div(other: Short): GForce = GForce(this.value.div(other))
    override operator fun rem(other: Short): GForce = GForce(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): GForce = GForce(this.value.times(other))
    override operator fun div(other: Int): GForce = GForce(this.value.div(other))
    override operator fun rem(other: Int): GForce = GForce(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): GForce = GForce(this.value.times(other))
    override operator fun div(other: Long): GForce = GForce(this.value.div(other))
    override operator fun rem(other: Long): GForce = GForce(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): GForce = GForce(this.value.times(other))
    override operator fun div(other: Float): GForce = GForce(this.value.div(other))
    override operator fun rem(other: Float): GForce = GForce(this.value.rem(other))


    override operator fun times(other: Double): GForce = GForce(this.value.times(other))
    override operator fun div(other: Double): GForce = GForce(this.value.div(other))
    override operator fun rem(other: Double): GForce = GForce(this.value.rem(other))


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

val Byte.gForce
    get() = GForce(this)


val Short.gForce
    get() = GForce(this)


val Int.gForce
    get() = GForce(this)


val Long.gForce
    get() = GForce(this)


val Float.gForce
    get() = GForce(this)


val Double.gForce
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): MeterPerSquareSecond = MeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: Byte): MeterPerSquareSecond = MeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: Byte): MeterPerSquareSecond = MeterPerSquareSecond(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): MeterPerSquareSecond = MeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: Short): MeterPerSquareSecond = MeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: Short): MeterPerSquareSecond = MeterPerSquareSecond(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): MeterPerSquareSecond = MeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: Int): MeterPerSquareSecond = MeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: Int): MeterPerSquareSecond = MeterPerSquareSecond(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): MeterPerSquareSecond = MeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: Long): MeterPerSquareSecond = MeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: Long): MeterPerSquareSecond = MeterPerSquareSecond(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): MeterPerSquareSecond = MeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: Float): MeterPerSquareSecond = MeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: Float): MeterPerSquareSecond = MeterPerSquareSecond(this.value.rem(other))


    override operator fun times(other: Double): MeterPerSquareSecond = MeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: Double): MeterPerSquareSecond = MeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: Double): MeterPerSquareSecond = MeterPerSquareSecond(this.value.rem(other))


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

val Byte.meterPerSquareSecond
    get() = MeterPerSquareSecond(this)


val Short.meterPerSquareSecond
    get() = MeterPerSquareSecond(this)


val Int.meterPerSquareSecond
    get() = MeterPerSquareSecond(this)


val Long.meterPerSquareSecond
    get() = MeterPerSquareSecond(this)


val Float.meterPerSquareSecond
    get() = MeterPerSquareSecond(this)


val Double.meterPerSquareSecond
    get() = MeterPerSquareSecond(this)
                            
