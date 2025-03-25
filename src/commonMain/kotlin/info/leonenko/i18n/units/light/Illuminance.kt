package info.leonenko.i18n.units.light

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

@Serializable(Illuminance.Serializer::class)
interface Illuminance : MeasurementUnit, Comparable<Illuminance> {
    override val measurement: String get() = "illuminance"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Illuminance> = emptyMap()) : KSerializer<Illuminance> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Illuminance", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Illuminance {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Illuminance unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "lux" -> Lux(value)
                "candela-per-square-meter" -> CandelaPerSquareMeter(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Illuminance unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Illuminance) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toLux(): Lux {
        return Lux.fromCandelaPerSquareMeter(toCandelaPerSquareMeter())
    }

    fun toCandelaPerSquareMeter(): CandelaPerSquareMeter
    override fun getBaseUnitValue() = toCandelaPerSquareMeter().value

    companion object {
        val ZERO: Illuminance = CandelaPerSquareMeter(0.0)

    }

    override operator fun compareTo(other: Illuminance): Int = this.toCandelaPerSquareMeter().value.compareTo(other.toCandelaPerSquareMeter().value)

    operator fun plus(other: Illuminance): CandelaPerSquareMeter = this.toCandelaPerSquareMeter() + other.toCandelaPerSquareMeter()
    operator fun minus(other: Illuminance): CandelaPerSquareMeter = this.toCandelaPerSquareMeter() - other.toCandelaPerSquareMeter()
    operator fun div(other: Illuminance): Double = this.toCandelaPerSquareMeter().value / other.toCandelaPerSquareMeter().value
    operator fun unaryMinus(): Illuminance
    operator fun unaryPlus(): Illuminance
    operator fun inc(): Illuminance
    operator fun dec(): Illuminance

    fun round(): Illuminance
    fun truncate(): Illuminance
    fun floor(): Illuminance
    fun ceil(): Illuminance
    fun abs(): Illuminance


    operator fun times(other: Byte): Illuminance
    operator fun div(other: Byte): Illuminance
    operator fun rem(other: Byte): Illuminance


    operator fun times(other: Short): Illuminance
    operator fun div(other: Short): Illuminance
    operator fun rem(other: Short): Illuminance


    operator fun times(other: Int): Illuminance
    operator fun div(other: Int): Illuminance
    operator fun rem(other: Int): Illuminance


    operator fun times(other: Long): Illuminance
    operator fun div(other: Long): Illuminance
    operator fun rem(other: Long): Illuminance


    operator fun times(other: Float): Illuminance
    operator fun div(other: Float): Illuminance
    operator fun rem(other: Float): Illuminance


    operator fun times(other: Double): Illuminance
    operator fun div(other: Double): Illuminance
    operator fun rem(other: Double): Illuminance

}

data class Lux(override val value: MeasurementUnitValue) : Illuminance {

    override val unitName: String = "lux"

    override fun formatters(language: MeasurementUnitLanguage) = language.lightLux
    override fun toCandelaPerSquareMeter() = CandelaPerSquareMeter(value * (1) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Lux(0.0)
        fun fromCandelaPerSquareMeter(value: CandelaPerSquareMeter) = Lux((value.value - 0) / (1))
    }

    operator fun plus(other: Lux): Lux = Lux(this.value + other.value)
    operator fun minus(other: Lux): Lux = Lux(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Lux = Lux(this.value.times(other))
    override operator fun div(other: Byte): Lux = Lux(this.value.div(other))
    override operator fun rem(other: Byte): Lux = Lux(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Lux = Lux(this.value.times(other))
    override operator fun div(other: Short): Lux = Lux(this.value.div(other))
    override operator fun rem(other: Short): Lux = Lux(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Lux = Lux(this.value.times(other))
    override operator fun div(other: Int): Lux = Lux(this.value.div(other))
    override operator fun rem(other: Int): Lux = Lux(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Lux = Lux(this.value.times(other))
    override operator fun div(other: Long): Lux = Lux(this.value.div(other))
    override operator fun rem(other: Long): Lux = Lux(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Lux = Lux(this.value.times(other))
    override operator fun div(other: Float): Lux = Lux(this.value.div(other))
    override operator fun rem(other: Float): Lux = Lux(this.value.rem(other))


    override operator fun times(other: Double): Lux = Lux(this.value.times(other))
    override operator fun div(other: Double): Lux = Lux(this.value.div(other))
    override operator fun rem(other: Double): Lux = Lux(this.value.rem(other))


    override operator fun unaryMinus(): Lux = Lux(-value)
    override operator fun unaryPlus(): Lux = this
    override operator fun inc(): Lux = Lux(this.value + 1)
    override operator fun dec(): Lux = Lux(this.value - 1)

    override fun round(): Lux = Lux(round(this.value))
    override fun truncate(): Lux = Lux(truncate(this.value))
    override fun floor(): Lux = Lux(floor(this.value))
    override fun ceil(): Lux = Lux(ceil(this.value))
    override fun abs(): Lux = Lux(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Lux -> this.value == other.value
        is Illuminance -> this.toCandelaPerSquareMeter().value == other.toCandelaPerSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCandelaPerSquareMeter().value.hashCode()


}

val Byte.lux
    get() = Lux(this)


val Short.lux
    get() = Lux(this)


val Int.lux
    get() = Lux(this)


val Long.lux
    get() = Lux(this)


val Float.lux
    get() = Lux(this)


val Double.lux
    get() = Lux(this)


data class CandelaPerSquareMeter(override val value: MeasurementUnitValue) : Illuminance {

    override val unitName: String = "candela-per-square-meter"

    override fun formatters(language: MeasurementUnitLanguage) = language.lightCandelaPerSquareMeter
    override fun toCandelaPerSquareMeter() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = CandelaPerSquareMeter(0.0)
        fun fromCandelaPerSquareMeter(value: CandelaPerSquareMeter) = value
    }

    operator fun plus(other: CandelaPerSquareMeter): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value + other.value)
    operator fun minus(other: CandelaPerSquareMeter): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.times(other))
    override operator fun div(other: Byte): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.div(other))
    override operator fun rem(other: Byte): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.times(other))
    override operator fun div(other: Short): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.div(other))
    override operator fun rem(other: Short): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.times(other))
    override operator fun div(other: Int): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.div(other))
    override operator fun rem(other: Int): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.times(other))
    override operator fun div(other: Long): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.div(other))
    override operator fun rem(other: Long): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.times(other))
    override operator fun div(other: Float): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.div(other))
    override operator fun rem(other: Float): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.rem(other))


    override operator fun times(other: Double): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.times(other))
    override operator fun div(other: Double): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.div(other))
    override operator fun rem(other: Double): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value.rem(other))


    override operator fun unaryMinus(): CandelaPerSquareMeter = CandelaPerSquareMeter(-value)
    override operator fun unaryPlus(): CandelaPerSquareMeter = this
    override operator fun inc(): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value + 1)
    override operator fun dec(): CandelaPerSquareMeter = CandelaPerSquareMeter(this.value - 1)

    override fun round(): CandelaPerSquareMeter = CandelaPerSquareMeter(round(this.value))
    override fun truncate(): CandelaPerSquareMeter = CandelaPerSquareMeter(truncate(this.value))
    override fun floor(): CandelaPerSquareMeter = CandelaPerSquareMeter(floor(this.value))
    override fun ceil(): CandelaPerSquareMeter = CandelaPerSquareMeter(ceil(this.value))
    override fun abs(): CandelaPerSquareMeter = CandelaPerSquareMeter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is CandelaPerSquareMeter -> this.value == other.value
        is Illuminance -> this.toCandelaPerSquareMeter().value == other.toCandelaPerSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCandelaPerSquareMeter().value.hashCode()


}

val Byte.candelaPerSquareMeter
    get() = CandelaPerSquareMeter(this)


val Short.candelaPerSquareMeter
    get() = CandelaPerSquareMeter(this)


val Int.candelaPerSquareMeter
    get() = CandelaPerSquareMeter(this)


val Long.candelaPerSquareMeter
    get() = CandelaPerSquareMeter(this)


val Float.candelaPerSquareMeter
    get() = CandelaPerSquareMeter(this)


val Double.candelaPerSquareMeter
    get() = CandelaPerSquareMeter(this)
                            
