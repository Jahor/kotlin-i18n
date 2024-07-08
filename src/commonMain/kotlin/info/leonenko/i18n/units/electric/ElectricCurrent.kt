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

@Serializable(ElectricCurrent.Serializer::class)
interface ElectricCurrent : MeasurementUnit, Comparable<ElectricCurrent> {
    override val measurement: String get() = "electric-current"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> ElectricCurrent> = emptyMap()) : KSerializer<ElectricCurrent> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("ElectricCurrent", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): ElectricCurrent {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected ElectricCurrent unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "ampere" -> Ampere(value)
                "milliampere" -> Milliampere(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown ElectricCurrent unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: ElectricCurrent) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toAmpere(): Ampere
    override fun getBaseUnitValue() = toAmpere().value
    fun toMilliampere(): Milliampere {
        return Milliampere.fromAmpere(toAmpere())
    }

    companion object {
        val ZERO: ElectricCurrent = Ampere(0.0)

    }

    override operator fun compareTo(other: ElectricCurrent): Int = this.toAmpere().value.compareTo(other.toAmpere().value)

    operator fun plus(other: ElectricCurrent): Ampere = this.toAmpere() + other.toAmpere()
    operator fun minus(other: ElectricCurrent): Ampere = this.toAmpere() - other.toAmpere()
    operator fun div(other: ElectricCurrent): Double = this.toAmpere().value / other.toAmpere().value
    operator fun unaryMinus(): ElectricCurrent
    operator fun unaryPlus(): ElectricCurrent
    operator fun inc(): ElectricCurrent
    operator fun dec(): ElectricCurrent

    fun round(): ElectricCurrent
    fun truncate(): ElectricCurrent
    fun floor(): ElectricCurrent
    fun ceil(): ElectricCurrent
    fun abs(): ElectricCurrent


    operator fun times(other: kotlin.Byte): ElectricCurrent
    operator fun div(other: kotlin.Byte): ElectricCurrent
    operator fun rem(other: kotlin.Byte): ElectricCurrent


    operator fun times(other: kotlin.Short): ElectricCurrent
    operator fun div(other: kotlin.Short): ElectricCurrent
    operator fun rem(other: kotlin.Short): ElectricCurrent


    operator fun times(other: kotlin.Int): ElectricCurrent
    operator fun div(other: kotlin.Int): ElectricCurrent
    operator fun rem(other: kotlin.Int): ElectricCurrent


    operator fun times(other: kotlin.Long): ElectricCurrent
    operator fun div(other: kotlin.Long): ElectricCurrent
    operator fun rem(other: kotlin.Long): ElectricCurrent


    operator fun times(other: kotlin.Float): ElectricCurrent
    operator fun div(other: kotlin.Float): ElectricCurrent
    operator fun rem(other: kotlin.Float): ElectricCurrent


    operator fun times(other: kotlin.Double): ElectricCurrent
    operator fun div(other: kotlin.Double): ElectricCurrent
    operator fun rem(other: kotlin.Double): ElectricCurrent

}

data class Ampere(override val value: MeasurementUnitValue) : ElectricCurrent {

    override val unitName: String = "ampere"

    override fun formatters(language: MeasurementUnitLanguage) = language.electricAmpere
    override fun toAmpere() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Ampere(0.0)
        fun fromAmpere(value: Ampere) = value
    }

    operator fun plus(other: Ampere): Ampere = Ampere(this.value + other.value)
    operator fun minus(other: Ampere): Ampere = Ampere(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Ampere = Ampere(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Ampere = Ampere(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Ampere = Ampere(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Ampere = Ampere(this.value.times(other))
    override operator fun div(other: kotlin.Short): Ampere = Ampere(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Ampere = Ampere(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Ampere = Ampere(this.value.times(other))
    override operator fun div(other: kotlin.Int): Ampere = Ampere(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Ampere = Ampere(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Ampere = Ampere(this.value.times(other))
    override operator fun div(other: kotlin.Long): Ampere = Ampere(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Ampere = Ampere(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Ampere = Ampere(this.value.times(other))
    override operator fun div(other: kotlin.Float): Ampere = Ampere(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Ampere = Ampere(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Ampere = Ampere(this.value.times(other))
    override operator fun div(other: kotlin.Double): Ampere = Ampere(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Ampere = Ampere(this.value.rem(other))


    override operator fun unaryMinus(): Ampere = Ampere(-value)
    override operator fun unaryPlus(): Ampere = this
    override operator fun inc(): Ampere = Ampere(this.value + 1)
    override operator fun dec(): Ampere = Ampere(this.value - 1)

    override fun round(): Ampere = Ampere(round(this.value))
    override fun truncate(): Ampere = Ampere(truncate(this.value))
    override fun floor(): Ampere = Ampere(floor(this.value))
    override fun ceil(): Ampere = Ampere(ceil(this.value))
    override fun abs(): Ampere = Ampere(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Ampere -> this.value == other.value
        is ElectricCurrent -> this.toAmpere().value == other.toAmpere().value
        else -> false
    }

    override fun hashCode(): Int = this.toAmpere().value.hashCode()


}

val kotlin.Byte.ampere
    get() = Ampere(this)


val kotlin.Short.ampere
    get() = Ampere(this)


val kotlin.Int.ampere
    get() = Ampere(this)


val kotlin.Long.ampere
    get() = Ampere(this)


val kotlin.Float.ampere
    get() = Ampere(this)


val kotlin.Double.ampere
    get() = Ampere(this)


data class Milliampere(override val value: MeasurementUnitValue) : ElectricCurrent {

    override val unitName: String = "milliampere"

    override fun formatters(language: MeasurementUnitLanguage) = language.electricMilliampere
    override fun toAmpere() = Ampere(value * (1 * (1e-3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Milliampere(0.0)
        fun fromAmpere(value: Ampere) = Milliampere((value.value - 0) / (1 * (1e-3)))
    }

    operator fun plus(other: Milliampere): Milliampere = Milliampere(this.value + other.value)
    operator fun minus(other: Milliampere): Milliampere = Milliampere(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Milliampere = Milliampere(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Milliampere = Milliampere(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Milliampere = Milliampere(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Milliampere = Milliampere(this.value.times(other))
    override operator fun div(other: kotlin.Short): Milliampere = Milliampere(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Milliampere = Milliampere(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Milliampere = Milliampere(this.value.times(other))
    override operator fun div(other: kotlin.Int): Milliampere = Milliampere(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Milliampere = Milliampere(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Milliampere = Milliampere(this.value.times(other))
    override operator fun div(other: kotlin.Long): Milliampere = Milliampere(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Milliampere = Milliampere(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Milliampere = Milliampere(this.value.times(other))
    override operator fun div(other: kotlin.Float): Milliampere = Milliampere(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Milliampere = Milliampere(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Milliampere = Milliampere(this.value.times(other))
    override operator fun div(other: kotlin.Double): Milliampere = Milliampere(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Milliampere = Milliampere(this.value.rem(other))


    override operator fun unaryMinus(): Milliampere = Milliampere(-value)
    override operator fun unaryPlus(): Milliampere = this
    override operator fun inc(): Milliampere = Milliampere(this.value + 1)
    override operator fun dec(): Milliampere = Milliampere(this.value - 1)

    override fun round(): Milliampere = Milliampere(round(this.value))
    override fun truncate(): Milliampere = Milliampere(truncate(this.value))
    override fun floor(): Milliampere = Milliampere(floor(this.value))
    override fun ceil(): Milliampere = Milliampere(ceil(this.value))
    override fun abs(): Milliampere = Milliampere(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Milliampere -> this.value == other.value
        is ElectricCurrent -> this.toAmpere().value == other.toAmpere().value
        else -> false
    }

    override fun hashCode(): Int = this.toAmpere().value.hashCode()


}

val kotlin.Byte.milliampere
    get() = Milliampere(this)


val kotlin.Short.milliampere
    get() = Milliampere(this)


val kotlin.Int.milliampere
    get() = Milliampere(this)


val kotlin.Long.milliampere
    get() = Milliampere(this)


val kotlin.Float.milliampere
    get() = Milliampere(this)


val kotlin.Double.milliampere
    get() = Milliampere(this)
                            
