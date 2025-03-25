package info.leonenko.i18n.units.electric

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

@Serializable(Voltage.Serializer::class)
interface Voltage : MeasurementUnit, Comparable<Voltage> {
    override val measurement: String get() = "voltage"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Voltage> = emptyMap()) : KSerializer<Voltage> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Voltage", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Voltage {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Voltage unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "volt" -> Volt(value)
                "kilogram-square-meter-per-cubic-second-ampere" -> KilogramSquareMeterPerCubicSecondAmpere(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Voltage unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Voltage) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toVolt(): Volt {
        return Volt.fromKilogramSquareMeterPerCubicSecondAmpere(toKilogramSquareMeterPerCubicSecondAmpere())
    }

    fun toKilogramSquareMeterPerCubicSecondAmpere(): KilogramSquareMeterPerCubicSecondAmpere
    override fun getBaseUnitValue() = toKilogramSquareMeterPerCubicSecondAmpere().value

    companion object {
        val ZERO: Voltage = KilogramSquareMeterPerCubicSecondAmpere(0.0)

    }

    override operator fun compareTo(other: Voltage): Int = this.toKilogramSquareMeterPerCubicSecondAmpere().value.compareTo(other.toKilogramSquareMeterPerCubicSecondAmpere().value)

    operator fun plus(other: Voltage): KilogramSquareMeterPerCubicSecondAmpere = this.toKilogramSquareMeterPerCubicSecondAmpere() + other.toKilogramSquareMeterPerCubicSecondAmpere()
    operator fun minus(other: Voltage): KilogramSquareMeterPerCubicSecondAmpere = this.toKilogramSquareMeterPerCubicSecondAmpere() - other.toKilogramSquareMeterPerCubicSecondAmpere()
    operator fun div(other: Voltage): Double = this.toKilogramSquareMeterPerCubicSecondAmpere().value / other.toKilogramSquareMeterPerCubicSecondAmpere().value
    operator fun unaryMinus(): Voltage
    operator fun unaryPlus(): Voltage
    operator fun inc(): Voltage
    operator fun dec(): Voltage

    fun round(): Voltage
    fun truncate(): Voltage
    fun floor(): Voltage
    fun ceil(): Voltage
    fun abs(): Voltage


    operator fun times(other: Byte): Voltage
    operator fun div(other: Byte): Voltage
    operator fun rem(other: Byte): Voltage


    operator fun times(other: Short): Voltage
    operator fun div(other: Short): Voltage
    operator fun rem(other: Short): Voltage


    operator fun times(other: Int): Voltage
    operator fun div(other: Int): Voltage
    operator fun rem(other: Int): Voltage


    operator fun times(other: Long): Voltage
    operator fun div(other: Long): Voltage
    operator fun rem(other: Long): Voltage


    operator fun times(other: Float): Voltage
    operator fun div(other: Float): Voltage
    operator fun rem(other: Float): Voltage


    operator fun times(other: Double): Voltage
    operator fun div(other: Double): Voltage
    operator fun rem(other: Double): Voltage

}

data class Volt(override val value: MeasurementUnitValue) : Voltage {

    override val unitName: String = "volt"

    override fun formatters(language: MeasurementUnitLanguage) = language.electricVolt
    override fun toKilogramSquareMeterPerCubicSecondAmpere() = KilogramSquareMeterPerCubicSecondAmpere(value * (1) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Volt(0.0)
        fun fromKilogramSquareMeterPerCubicSecondAmpere(value: KilogramSquareMeterPerCubicSecondAmpere) = Volt((value.value - 0) / (1))
    }

    operator fun plus(other: Volt): Volt = Volt(this.value + other.value)
    operator fun minus(other: Volt): Volt = Volt(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Volt = Volt(this.value.times(other))
    override operator fun div(other: Byte): Volt = Volt(this.value.div(other))
    override operator fun rem(other: Byte): Volt = Volt(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Volt = Volt(this.value.times(other))
    override operator fun div(other: Short): Volt = Volt(this.value.div(other))
    override operator fun rem(other: Short): Volt = Volt(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Volt = Volt(this.value.times(other))
    override operator fun div(other: Int): Volt = Volt(this.value.div(other))
    override operator fun rem(other: Int): Volt = Volt(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Volt = Volt(this.value.times(other))
    override operator fun div(other: Long): Volt = Volt(this.value.div(other))
    override operator fun rem(other: Long): Volt = Volt(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Volt = Volt(this.value.times(other))
    override operator fun div(other: Float): Volt = Volt(this.value.div(other))
    override operator fun rem(other: Float): Volt = Volt(this.value.rem(other))


    override operator fun times(other: Double): Volt = Volt(this.value.times(other))
    override operator fun div(other: Double): Volt = Volt(this.value.div(other))
    override operator fun rem(other: Double): Volt = Volt(this.value.rem(other))


    override operator fun unaryMinus(): Volt = Volt(-value)
    override operator fun unaryPlus(): Volt = this
    override operator fun inc(): Volt = Volt(this.value + 1)
    override operator fun dec(): Volt = Volt(this.value - 1)

    override fun round(): Volt = Volt(round(this.value))
    override fun truncate(): Volt = Volt(truncate(this.value))
    override fun floor(): Volt = Volt(floor(this.value))
    override fun ceil(): Volt = Volt(ceil(this.value))
    override fun abs(): Volt = Volt(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Volt -> this.value == other.value
        is Voltage -> this.toKilogramSquareMeterPerCubicSecondAmpere().value == other.toKilogramSquareMeterPerCubicSecondAmpere().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerCubicSecondAmpere().value.hashCode()


}

val Byte.volt
    get() = Volt(this)


val Short.volt
    get() = Volt(this)


val Int.volt
    get() = Volt(this)


val Long.volt
    get() = Volt(this)


val Float.volt
    get() = Volt(this)


val Double.volt
    get() = Volt(this)


data class KilogramSquareMeterPerCubicSecondAmpere(override val value: MeasurementUnitValue) : Voltage {

    override val unitName: String = "kilogram-square-meter-per-cubic-second-ampere"

    override fun formatters(language: MeasurementUnitLanguage) = language.electricKilogramSquareMeterPerCubicSecondAmpere
    override fun toKilogramSquareMeterPerCubicSecondAmpere() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = KilogramSquareMeterPerCubicSecondAmpere(0.0)
        fun fromKilogramSquareMeterPerCubicSecondAmpere(value: KilogramSquareMeterPerCubicSecondAmpere) = value
    }

    operator fun plus(other: KilogramSquareMeterPerCubicSecondAmpere): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value + other.value)
    operator fun minus(other: KilogramSquareMeterPerCubicSecondAmpere): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.times(other))
    override operator fun div(other: Byte): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.div(other))
    override operator fun rem(other: Byte): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.times(other))
    override operator fun div(other: Short): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.div(other))
    override operator fun rem(other: Short): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.times(other))
    override operator fun div(other: Int): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.div(other))
    override operator fun rem(other: Int): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.times(other))
    override operator fun div(other: Long): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.div(other))
    override operator fun rem(other: Long): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.times(other))
    override operator fun div(other: Float): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.div(other))
    override operator fun rem(other: Float): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.rem(other))


    override operator fun times(other: Double): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.times(other))
    override operator fun div(other: Double): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.div(other))
    override operator fun rem(other: Double): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.rem(other))


    override operator fun unaryMinus(): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(-value)
    override operator fun unaryPlus(): KilogramSquareMeterPerCubicSecondAmpere = this
    override operator fun inc(): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value + 1)
    override operator fun dec(): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value - 1)

    override fun round(): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(round(this.value))
    override fun truncate(): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(truncate(this.value))
    override fun floor(): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(floor(this.value))
    override fun ceil(): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(ceil(this.value))
    override fun abs(): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is KilogramSquareMeterPerCubicSecondAmpere -> this.value == other.value
        is Voltage -> this.toKilogramSquareMeterPerCubicSecondAmpere().value == other.toKilogramSquareMeterPerCubicSecondAmpere().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerCubicSecondAmpere().value.hashCode()


}

val Byte.kilogramSquareMeterPerCubicSecondAmpere
    get() = KilogramSquareMeterPerCubicSecondAmpere(this)


val Short.kilogramSquareMeterPerCubicSecondAmpere
    get() = KilogramSquareMeterPerCubicSecondAmpere(this)


val Int.kilogramSquareMeterPerCubicSecondAmpere
    get() = KilogramSquareMeterPerCubicSecondAmpere(this)


val Long.kilogramSquareMeterPerCubicSecondAmpere
    get() = KilogramSquareMeterPerCubicSecondAmpere(this)


val Float.kilogramSquareMeterPerCubicSecondAmpere
    get() = KilogramSquareMeterPerCubicSecondAmpere(this)


val Double.kilogramSquareMeterPerCubicSecondAmpere
    get() = KilogramSquareMeterPerCubicSecondAmpere(this)
                            
