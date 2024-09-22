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


    operator fun times(other: kotlin.Byte): Voltage
    operator fun div(other: kotlin.Byte): Voltage
    operator fun rem(other: kotlin.Byte): Voltage


    operator fun times(other: kotlin.Short): Voltage
    operator fun div(other: kotlin.Short): Voltage
    operator fun rem(other: kotlin.Short): Voltage


    operator fun times(other: kotlin.Int): Voltage
    operator fun div(other: kotlin.Int): Voltage
    operator fun rem(other: kotlin.Int): Voltage


    operator fun times(other: kotlin.Long): Voltage
    operator fun div(other: kotlin.Long): Voltage
    operator fun rem(other: kotlin.Long): Voltage


    operator fun times(other: kotlin.Float): Voltage
    operator fun div(other: kotlin.Float): Voltage
    operator fun rem(other: kotlin.Float): Voltage


    operator fun times(other: kotlin.Double): Voltage
    operator fun div(other: kotlin.Double): Voltage
    operator fun rem(other: kotlin.Double): Voltage

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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Volt = Volt(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Volt = Volt(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Volt = Volt(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Volt = Volt(this.value.times(other))
    override operator fun div(other: kotlin.Short): Volt = Volt(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Volt = Volt(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Volt = Volt(this.value.times(other))
    override operator fun div(other: kotlin.Int): Volt = Volt(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Volt = Volt(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Volt = Volt(this.value.times(other))
    override operator fun div(other: kotlin.Long): Volt = Volt(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Volt = Volt(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Volt = Volt(this.value.times(other))
    override operator fun div(other: kotlin.Float): Volt = Volt(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Volt = Volt(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Volt = Volt(this.value.times(other))
    override operator fun div(other: kotlin.Double): Volt = Volt(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Volt = Volt(this.value.rem(other))


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

val kotlin.Byte.volt
    get() = Volt(this)


val kotlin.Short.volt
    get() = Volt(this)


val kotlin.Int.volt
    get() = Volt(this)


val kotlin.Long.volt
    get() = Volt(this)


val kotlin.Float.volt
    get() = Volt(this)


val kotlin.Double.volt
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.times(other))
    override operator fun div(other: kotlin.Byte): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.times(other))
    override operator fun div(other: kotlin.Short): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.div(other))
    override operator fun rem(other: kotlin.Short): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.times(other))
    override operator fun div(other: kotlin.Int): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.div(other))
    override operator fun rem(other: kotlin.Int): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.times(other))
    override operator fun div(other: kotlin.Long): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.div(other))
    override operator fun rem(other: kotlin.Long): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.times(other))
    override operator fun div(other: kotlin.Float): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.div(other))
    override operator fun rem(other: kotlin.Float): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.rem(other))


    override operator fun times(other: kotlin.Double): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.times(other))
    override operator fun div(other: kotlin.Double): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.div(other))
    override operator fun rem(other: kotlin.Double): KilogramSquareMeterPerCubicSecondAmpere = KilogramSquareMeterPerCubicSecondAmpere(this.value.rem(other))


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

val kotlin.Byte.kilogramSquareMeterPerCubicSecondAmpere
    get() = KilogramSquareMeterPerCubicSecondAmpere(this)


val kotlin.Short.kilogramSquareMeterPerCubicSecondAmpere
    get() = KilogramSquareMeterPerCubicSecondAmpere(this)


val kotlin.Int.kilogramSquareMeterPerCubicSecondAmpere
    get() = KilogramSquareMeterPerCubicSecondAmpere(this)


val kotlin.Long.kilogramSquareMeterPerCubicSecondAmpere
    get() = KilogramSquareMeterPerCubicSecondAmpere(this)


val kotlin.Float.kilogramSquareMeterPerCubicSecondAmpere
    get() = KilogramSquareMeterPerCubicSecondAmpere(this)


val kotlin.Double.kilogramSquareMeterPerCubicSecondAmpere
    get() = KilogramSquareMeterPerCubicSecondAmpere(this)
                            
