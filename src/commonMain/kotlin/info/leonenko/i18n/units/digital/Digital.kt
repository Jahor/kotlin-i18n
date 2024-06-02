package info.leonenko.i18n.units.digital

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

@Serializable(Digital.Serializer::class)
interface Digital : MeasurementUnit, Comparable<Digital> {
    override val measurement: String get() = "digital"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Digital> = emptyMap()) : KSerializer<Digital> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())

        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Digital", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Digital {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Digital unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "petabyte" -> Petabyte(value)
                "terabyte" -> Terabyte(value)
                "terabit" -> Terabit(value)
                "gigabyte" -> Gigabyte(value)
                "gigabit" -> Gigabit(value)
                "megabyte" -> Megabyte(value)
                "megabit" -> Megabit(value)
                "kilobyte" -> Kilobyte(value)
                "kilobit" -> Kilobit(value)
                "byte" -> Byte(value)
                "bit" -> Bit(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Digital unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Digital) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toPetabyte(): Petabyte {
        return Petabyte.fromBit(toBit())
    }

    fun toTerabyte(): Terabyte {
        return Terabyte.fromBit(toBit())
    }

    fun toTerabit(): Terabit {
        return Terabit.fromBit(toBit())
    }

    fun toGigabyte(): Gigabyte {
        return Gigabyte.fromBit(toBit())
    }

    fun toGigabit(): Gigabit {
        return Gigabit.fromBit(toBit())
    }

    fun toMegabyte(): Megabyte {
        return Megabyte.fromBit(toBit())
    }

    fun toMegabit(): Megabit {
        return Megabit.fromBit(toBit())
    }

    fun toKilobyte(): Kilobyte {
        return Kilobyte.fromBit(toBit())
    }

    fun toKilobit(): Kilobit {
        return Kilobit.fromBit(toBit())
    }

    fun toByte(): Byte {
        return Byte.fromBit(toBit())
    }

    fun toBit(): Bit
    override fun getBaseUnitValue() = toBit().value

    companion object {
        val ZERO: Digital = Bit(0.0)

    }

    override operator fun compareTo(other: Digital): Int = this.toBit().value.compareTo(other.toBit().value)

    operator fun plus(other: Digital): Bit = this.toBit() + other.toBit()
    operator fun minus(other: Digital): Bit = this.toBit() - other.toBit()
    operator fun div(other: Digital): Double = this.toBit().value / other.toBit().value
    operator fun unaryMinus(): Digital
    operator fun unaryPlus(): Digital
    operator fun inc(): Digital
    operator fun dec(): Digital

    fun round(): Digital
    fun truncate(): Digital
    fun floor(): Digital
    fun ceil(): Digital
    fun abs(): Digital


    operator fun times(other: kotlin.Byte): Digital
    operator fun div(other: kotlin.Byte): Digital
    operator fun rem(other: kotlin.Byte): Digital


    operator fun times(other: kotlin.Short): Digital
    operator fun div(other: kotlin.Short): Digital
    operator fun rem(other: kotlin.Short): Digital


    operator fun times(other: kotlin.Int): Digital
    operator fun div(other: kotlin.Int): Digital
    operator fun rem(other: kotlin.Int): Digital


    operator fun times(other: kotlin.Long): Digital
    operator fun div(other: kotlin.Long): Digital
    operator fun rem(other: kotlin.Long): Digital


    operator fun times(other: kotlin.Float): Digital
    operator fun div(other: kotlin.Float): Digital
    operator fun rem(other: kotlin.Float): Digital


    operator fun times(other: kotlin.Double): Digital
    operator fun div(other: kotlin.Double): Digital
    operator fun rem(other: kotlin.Double): Digital

}

data class Petabyte(override val value: MeasurementUnitValue) : Digital {

    override val unitName: String = "petabyte"

    override fun formatters(language: MeasurementUnitLanguage) = language.digitalPetabyte
    override fun toBit() = Bit(value * (8 * (1e15)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Petabyte(0.0)
        fun fromBit(value: Bit) = Petabyte((value.value - 0) / (8 * (1e15)))
    }

    operator fun plus(other: Petabyte): Petabyte = Petabyte(this.value + other.value)
    operator fun minus(other: Petabyte): Petabyte = Petabyte(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Petabyte = Petabyte(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Petabyte = Petabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Petabyte = Petabyte(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Petabyte = Petabyte(this.value.times(other))
    override operator fun div(other: kotlin.Short): Petabyte = Petabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Petabyte = Petabyte(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Petabyte = Petabyte(this.value.times(other))
    override operator fun div(other: kotlin.Int): Petabyte = Petabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Petabyte = Petabyte(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Petabyte = Petabyte(this.value.times(other))
    override operator fun div(other: kotlin.Long): Petabyte = Petabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Petabyte = Petabyte(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Petabyte = Petabyte(this.value.times(other))
    override operator fun div(other: kotlin.Float): Petabyte = Petabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Petabyte = Petabyte(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Petabyte = Petabyte(this.value.times(other))
    override operator fun div(other: kotlin.Double): Petabyte = Petabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Petabyte = Petabyte(this.value.rem(other))


    override operator fun unaryMinus(): Petabyte = Petabyte(-value)
    override operator fun unaryPlus(): Petabyte = this
    override operator fun inc(): Petabyte = Petabyte(this.value + 1)
    override operator fun dec(): Petabyte = Petabyte(this.value - 1)

    override fun round(): Petabyte = Petabyte(round(this.value))
    override fun truncate(): Petabyte = Petabyte(truncate(this.value))
    override fun floor(): Petabyte = Petabyte(floor(this.value))
    override fun ceil(): Petabyte = Petabyte(ceil(this.value))
    override fun abs(): Petabyte = Petabyte(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Petabyte -> this.value == other.value
        is Digital -> this.toBit().value == other.toBit().value
        else -> false
    }

    override fun hashCode(): Int = this.toBit().value.hashCode()


}

val kotlin.Byte.petabyte
    get() = Petabyte(this)


val kotlin.Short.petabyte
    get() = Petabyte(this)


val kotlin.Int.petabyte
    get() = Petabyte(this)


val kotlin.Long.petabyte
    get() = Petabyte(this)


val kotlin.Float.petabyte
    get() = Petabyte(this)


val kotlin.Double.petabyte
    get() = Petabyte(this)


data class Terabyte(override val value: MeasurementUnitValue) : Digital {

    override val unitName: String = "terabyte"

    override fun formatters(language: MeasurementUnitLanguage) = language.digitalTerabyte
    override fun toBit() = Bit(value * (8 * (1e12)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Terabyte(0.0)
        fun fromBit(value: Bit) = Terabyte((value.value - 0) / (8 * (1e12)))
    }

    operator fun plus(other: Terabyte): Terabyte = Terabyte(this.value + other.value)
    operator fun minus(other: Terabyte): Terabyte = Terabyte(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Terabyte = Terabyte(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Terabyte = Terabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Terabyte = Terabyte(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Terabyte = Terabyte(this.value.times(other))
    override operator fun div(other: kotlin.Short): Terabyte = Terabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Terabyte = Terabyte(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Terabyte = Terabyte(this.value.times(other))
    override operator fun div(other: kotlin.Int): Terabyte = Terabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Terabyte = Terabyte(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Terabyte = Terabyte(this.value.times(other))
    override operator fun div(other: kotlin.Long): Terabyte = Terabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Terabyte = Terabyte(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Terabyte = Terabyte(this.value.times(other))
    override operator fun div(other: kotlin.Float): Terabyte = Terabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Terabyte = Terabyte(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Terabyte = Terabyte(this.value.times(other))
    override operator fun div(other: kotlin.Double): Terabyte = Terabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Terabyte = Terabyte(this.value.rem(other))


    override operator fun unaryMinus(): Terabyte = Terabyte(-value)
    override operator fun unaryPlus(): Terabyte = this
    override operator fun inc(): Terabyte = Terabyte(this.value + 1)
    override operator fun dec(): Terabyte = Terabyte(this.value - 1)

    override fun round(): Terabyte = Terabyte(round(this.value))
    override fun truncate(): Terabyte = Terabyte(truncate(this.value))
    override fun floor(): Terabyte = Terabyte(floor(this.value))
    override fun ceil(): Terabyte = Terabyte(ceil(this.value))
    override fun abs(): Terabyte = Terabyte(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Terabyte -> this.value == other.value
        is Digital -> this.toBit().value == other.toBit().value
        else -> false
    }

    override fun hashCode(): Int = this.toBit().value.hashCode()


}

val kotlin.Byte.terabyte
    get() = Terabyte(this)


val kotlin.Short.terabyte
    get() = Terabyte(this)


val kotlin.Int.terabyte
    get() = Terabyte(this)


val kotlin.Long.terabyte
    get() = Terabyte(this)


val kotlin.Float.terabyte
    get() = Terabyte(this)


val kotlin.Double.terabyte
    get() = Terabyte(this)


data class Terabit(override val value: MeasurementUnitValue) : Digital {

    override val unitName: String = "terabit"

    override fun formatters(language: MeasurementUnitLanguage) = language.digitalTerabit
    override fun toBit() = Bit(value * (1 * (1e12)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Terabit(0.0)
        fun fromBit(value: Bit) = Terabit((value.value - 0) / (1 * (1e12)))
    }

    operator fun plus(other: Terabit): Terabit = Terabit(this.value + other.value)
    operator fun minus(other: Terabit): Terabit = Terabit(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Terabit = Terabit(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Terabit = Terabit(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Terabit = Terabit(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Terabit = Terabit(this.value.times(other))
    override operator fun div(other: kotlin.Short): Terabit = Terabit(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Terabit = Terabit(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Terabit = Terabit(this.value.times(other))
    override operator fun div(other: kotlin.Int): Terabit = Terabit(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Terabit = Terabit(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Terabit = Terabit(this.value.times(other))
    override operator fun div(other: kotlin.Long): Terabit = Terabit(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Terabit = Terabit(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Terabit = Terabit(this.value.times(other))
    override operator fun div(other: kotlin.Float): Terabit = Terabit(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Terabit = Terabit(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Terabit = Terabit(this.value.times(other))
    override operator fun div(other: kotlin.Double): Terabit = Terabit(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Terabit = Terabit(this.value.rem(other))


    override operator fun unaryMinus(): Terabit = Terabit(-value)
    override operator fun unaryPlus(): Terabit = this
    override operator fun inc(): Terabit = Terabit(this.value + 1)
    override operator fun dec(): Terabit = Terabit(this.value - 1)

    override fun round(): Terabit = Terabit(round(this.value))
    override fun truncate(): Terabit = Terabit(truncate(this.value))
    override fun floor(): Terabit = Terabit(floor(this.value))
    override fun ceil(): Terabit = Terabit(ceil(this.value))
    override fun abs(): Terabit = Terabit(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Terabit -> this.value == other.value
        is Digital -> this.toBit().value == other.toBit().value
        else -> false
    }

    override fun hashCode(): Int = this.toBit().value.hashCode()


}

val kotlin.Byte.terabit
    get() = Terabit(this)


val kotlin.Short.terabit
    get() = Terabit(this)


val kotlin.Int.terabit
    get() = Terabit(this)


val kotlin.Long.terabit
    get() = Terabit(this)


val kotlin.Float.terabit
    get() = Terabit(this)


val kotlin.Double.terabit
    get() = Terabit(this)


data class Gigabyte(override val value: MeasurementUnitValue) : Digital {

    override val unitName: String = "gigabyte"

    override fun formatters(language: MeasurementUnitLanguage) = language.digitalGigabyte
    override fun toBit() = Bit(value * (8 * (1e9)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Gigabyte(0.0)
        fun fromBit(value: Bit) = Gigabyte((value.value - 0) / (8 * (1e9)))
    }

    operator fun plus(other: Gigabyte): Gigabyte = Gigabyte(this.value + other.value)
    operator fun minus(other: Gigabyte): Gigabyte = Gigabyte(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Gigabyte = Gigabyte(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Gigabyte = Gigabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Gigabyte = Gigabyte(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Gigabyte = Gigabyte(this.value.times(other))
    override operator fun div(other: kotlin.Short): Gigabyte = Gigabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Gigabyte = Gigabyte(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Gigabyte = Gigabyte(this.value.times(other))
    override operator fun div(other: kotlin.Int): Gigabyte = Gigabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Gigabyte = Gigabyte(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Gigabyte = Gigabyte(this.value.times(other))
    override operator fun div(other: kotlin.Long): Gigabyte = Gigabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Gigabyte = Gigabyte(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Gigabyte = Gigabyte(this.value.times(other))
    override operator fun div(other: kotlin.Float): Gigabyte = Gigabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Gigabyte = Gigabyte(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Gigabyte = Gigabyte(this.value.times(other))
    override operator fun div(other: kotlin.Double): Gigabyte = Gigabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Gigabyte = Gigabyte(this.value.rem(other))


    override operator fun unaryMinus(): Gigabyte = Gigabyte(-value)
    override operator fun unaryPlus(): Gigabyte = this
    override operator fun inc(): Gigabyte = Gigabyte(this.value + 1)
    override operator fun dec(): Gigabyte = Gigabyte(this.value - 1)

    override fun round(): Gigabyte = Gigabyte(round(this.value))
    override fun truncate(): Gigabyte = Gigabyte(truncate(this.value))
    override fun floor(): Gigabyte = Gigabyte(floor(this.value))
    override fun ceil(): Gigabyte = Gigabyte(ceil(this.value))
    override fun abs(): Gigabyte = Gigabyte(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Gigabyte -> this.value == other.value
        is Digital -> this.toBit().value == other.toBit().value
        else -> false
    }

    override fun hashCode(): Int = this.toBit().value.hashCode()


}

val kotlin.Byte.gigabyte
    get() = Gigabyte(this)


val kotlin.Short.gigabyte
    get() = Gigabyte(this)


val kotlin.Int.gigabyte
    get() = Gigabyte(this)


val kotlin.Long.gigabyte
    get() = Gigabyte(this)


val kotlin.Float.gigabyte
    get() = Gigabyte(this)


val kotlin.Double.gigabyte
    get() = Gigabyte(this)


data class Gigabit(override val value: MeasurementUnitValue) : Digital {

    override val unitName: String = "gigabit"

    override fun formatters(language: MeasurementUnitLanguage) = language.digitalGigabit
    override fun toBit() = Bit(value * (1 * (1e9)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Gigabit(0.0)
        fun fromBit(value: Bit) = Gigabit((value.value - 0) / (1 * (1e9)))
    }

    operator fun plus(other: Gigabit): Gigabit = Gigabit(this.value + other.value)
    operator fun minus(other: Gigabit): Gigabit = Gigabit(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Gigabit = Gigabit(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Gigabit = Gigabit(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Gigabit = Gigabit(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Gigabit = Gigabit(this.value.times(other))
    override operator fun div(other: kotlin.Short): Gigabit = Gigabit(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Gigabit = Gigabit(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Gigabit = Gigabit(this.value.times(other))
    override operator fun div(other: kotlin.Int): Gigabit = Gigabit(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Gigabit = Gigabit(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Gigabit = Gigabit(this.value.times(other))
    override operator fun div(other: kotlin.Long): Gigabit = Gigabit(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Gigabit = Gigabit(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Gigabit = Gigabit(this.value.times(other))
    override operator fun div(other: kotlin.Float): Gigabit = Gigabit(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Gigabit = Gigabit(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Gigabit = Gigabit(this.value.times(other))
    override operator fun div(other: kotlin.Double): Gigabit = Gigabit(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Gigabit = Gigabit(this.value.rem(other))


    override operator fun unaryMinus(): Gigabit = Gigabit(-value)
    override operator fun unaryPlus(): Gigabit = this
    override operator fun inc(): Gigabit = Gigabit(this.value + 1)
    override operator fun dec(): Gigabit = Gigabit(this.value - 1)

    override fun round(): Gigabit = Gigabit(round(this.value))
    override fun truncate(): Gigabit = Gigabit(truncate(this.value))
    override fun floor(): Gigabit = Gigabit(floor(this.value))
    override fun ceil(): Gigabit = Gigabit(ceil(this.value))
    override fun abs(): Gigabit = Gigabit(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Gigabit -> this.value == other.value
        is Digital -> this.toBit().value == other.toBit().value
        else -> false
    }

    override fun hashCode(): Int = this.toBit().value.hashCode()


}

val kotlin.Byte.gigabit
    get() = Gigabit(this)


val kotlin.Short.gigabit
    get() = Gigabit(this)


val kotlin.Int.gigabit
    get() = Gigabit(this)


val kotlin.Long.gigabit
    get() = Gigabit(this)


val kotlin.Float.gigabit
    get() = Gigabit(this)


val kotlin.Double.gigabit
    get() = Gigabit(this)


data class Megabyte(override val value: MeasurementUnitValue) : Digital {

    override val unitName: String = "megabyte"

    override fun formatters(language: MeasurementUnitLanguage) = language.digitalMegabyte
    override fun toBit() = Bit(value * (8 * (1e6)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Megabyte(0.0)
        fun fromBit(value: Bit) = Megabyte((value.value - 0) / (8 * (1e6)))
    }

    operator fun plus(other: Megabyte): Megabyte = Megabyte(this.value + other.value)
    operator fun minus(other: Megabyte): Megabyte = Megabyte(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Megabyte = Megabyte(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Megabyte = Megabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Megabyte = Megabyte(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Megabyte = Megabyte(this.value.times(other))
    override operator fun div(other: kotlin.Short): Megabyte = Megabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Megabyte = Megabyte(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Megabyte = Megabyte(this.value.times(other))
    override operator fun div(other: kotlin.Int): Megabyte = Megabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Megabyte = Megabyte(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Megabyte = Megabyte(this.value.times(other))
    override operator fun div(other: kotlin.Long): Megabyte = Megabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Megabyte = Megabyte(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Megabyte = Megabyte(this.value.times(other))
    override operator fun div(other: kotlin.Float): Megabyte = Megabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Megabyte = Megabyte(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Megabyte = Megabyte(this.value.times(other))
    override operator fun div(other: kotlin.Double): Megabyte = Megabyte(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Megabyte = Megabyte(this.value.rem(other))


    override operator fun unaryMinus(): Megabyte = Megabyte(-value)
    override operator fun unaryPlus(): Megabyte = this
    override operator fun inc(): Megabyte = Megabyte(this.value + 1)
    override operator fun dec(): Megabyte = Megabyte(this.value - 1)

    override fun round(): Megabyte = Megabyte(round(this.value))
    override fun truncate(): Megabyte = Megabyte(truncate(this.value))
    override fun floor(): Megabyte = Megabyte(floor(this.value))
    override fun ceil(): Megabyte = Megabyte(ceil(this.value))
    override fun abs(): Megabyte = Megabyte(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Megabyte -> this.value == other.value
        is Digital -> this.toBit().value == other.toBit().value
        else -> false
    }

    override fun hashCode(): Int = this.toBit().value.hashCode()


}

val kotlin.Byte.megabyte
    get() = Megabyte(this)


val kotlin.Short.megabyte
    get() = Megabyte(this)


val kotlin.Int.megabyte
    get() = Megabyte(this)


val kotlin.Long.megabyte
    get() = Megabyte(this)


val kotlin.Float.megabyte
    get() = Megabyte(this)


val kotlin.Double.megabyte
    get() = Megabyte(this)


data class Megabit(override val value: MeasurementUnitValue) : Digital {

    override val unitName: String = "megabit"

    override fun formatters(language: MeasurementUnitLanguage) = language.digitalMegabit
    override fun toBit() = Bit(value * (1 * (1e6)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Megabit(0.0)
        fun fromBit(value: Bit) = Megabit((value.value - 0) / (1 * (1e6)))
    }

    operator fun plus(other: Megabit): Megabit = Megabit(this.value + other.value)
    operator fun minus(other: Megabit): Megabit = Megabit(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Megabit = Megabit(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Megabit = Megabit(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Megabit = Megabit(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Megabit = Megabit(this.value.times(other))
    override operator fun div(other: kotlin.Short): Megabit = Megabit(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Megabit = Megabit(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Megabit = Megabit(this.value.times(other))
    override operator fun div(other: kotlin.Int): Megabit = Megabit(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Megabit = Megabit(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Megabit = Megabit(this.value.times(other))
    override operator fun div(other: kotlin.Long): Megabit = Megabit(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Megabit = Megabit(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Megabit = Megabit(this.value.times(other))
    override operator fun div(other: kotlin.Float): Megabit = Megabit(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Megabit = Megabit(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Megabit = Megabit(this.value.times(other))
    override operator fun div(other: kotlin.Double): Megabit = Megabit(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Megabit = Megabit(this.value.rem(other))


    override operator fun unaryMinus(): Megabit = Megabit(-value)
    override operator fun unaryPlus(): Megabit = this
    override operator fun inc(): Megabit = Megabit(this.value + 1)
    override operator fun dec(): Megabit = Megabit(this.value - 1)

    override fun round(): Megabit = Megabit(round(this.value))
    override fun truncate(): Megabit = Megabit(truncate(this.value))
    override fun floor(): Megabit = Megabit(floor(this.value))
    override fun ceil(): Megabit = Megabit(ceil(this.value))
    override fun abs(): Megabit = Megabit(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Megabit -> this.value == other.value
        is Digital -> this.toBit().value == other.toBit().value
        else -> false
    }

    override fun hashCode(): Int = this.toBit().value.hashCode()


}

val kotlin.Byte.megabit
    get() = Megabit(this)


val kotlin.Short.megabit
    get() = Megabit(this)


val kotlin.Int.megabit
    get() = Megabit(this)


val kotlin.Long.megabit
    get() = Megabit(this)


val kotlin.Float.megabit
    get() = Megabit(this)


val kotlin.Double.megabit
    get() = Megabit(this)


data class Kilobyte(override val value: MeasurementUnitValue) : Digital {

    override val unitName: String = "kilobyte"

    override fun formatters(language: MeasurementUnitLanguage) = language.digitalKilobyte
    override fun toBit() = Bit(value * (8 * (1e3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Kilobyte(0.0)
        fun fromBit(value: Bit) = Kilobyte((value.value - 0) / (8 * (1e3)))
    }

    operator fun plus(other: Kilobyte): Kilobyte = Kilobyte(this.value + other.value)
    operator fun minus(other: Kilobyte): Kilobyte = Kilobyte(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Kilobyte = Kilobyte(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Kilobyte = Kilobyte(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Kilobyte = Kilobyte(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Kilobyte = Kilobyte(this.value.times(other))
    override operator fun div(other: kotlin.Short): Kilobyte = Kilobyte(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Kilobyte = Kilobyte(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Kilobyte = Kilobyte(this.value.times(other))
    override operator fun div(other: kotlin.Int): Kilobyte = Kilobyte(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Kilobyte = Kilobyte(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Kilobyte = Kilobyte(this.value.times(other))
    override operator fun div(other: kotlin.Long): Kilobyte = Kilobyte(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Kilobyte = Kilobyte(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Kilobyte = Kilobyte(this.value.times(other))
    override operator fun div(other: kotlin.Float): Kilobyte = Kilobyte(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Kilobyte = Kilobyte(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Kilobyte = Kilobyte(this.value.times(other))
    override operator fun div(other: kotlin.Double): Kilobyte = Kilobyte(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Kilobyte = Kilobyte(this.value.rem(other))


    override operator fun unaryMinus(): Kilobyte = Kilobyte(-value)
    override operator fun unaryPlus(): Kilobyte = this
    override operator fun inc(): Kilobyte = Kilobyte(this.value + 1)
    override operator fun dec(): Kilobyte = Kilobyte(this.value - 1)

    override fun round(): Kilobyte = Kilobyte(round(this.value))
    override fun truncate(): Kilobyte = Kilobyte(truncate(this.value))
    override fun floor(): Kilobyte = Kilobyte(floor(this.value))
    override fun ceil(): Kilobyte = Kilobyte(ceil(this.value))
    override fun abs(): Kilobyte = Kilobyte(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Kilobyte -> this.value == other.value
        is Digital -> this.toBit().value == other.toBit().value
        else -> false
    }

    override fun hashCode(): Int = this.toBit().value.hashCode()


}

val kotlin.Byte.kilobyte
    get() = Kilobyte(this)


val kotlin.Short.kilobyte
    get() = Kilobyte(this)


val kotlin.Int.kilobyte
    get() = Kilobyte(this)


val kotlin.Long.kilobyte
    get() = Kilobyte(this)


val kotlin.Float.kilobyte
    get() = Kilobyte(this)


val kotlin.Double.kilobyte
    get() = Kilobyte(this)


data class Kilobit(override val value: MeasurementUnitValue) : Digital {

    override val unitName: String = "kilobit"

    override fun formatters(language: MeasurementUnitLanguage) = language.digitalKilobit
    override fun toBit() = Bit(value * (1 * (1e3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Kilobit(0.0)
        fun fromBit(value: Bit) = Kilobit((value.value - 0) / (1 * (1e3)))
    }

    operator fun plus(other: Kilobit): Kilobit = Kilobit(this.value + other.value)
    operator fun minus(other: Kilobit): Kilobit = Kilobit(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Kilobit = Kilobit(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Kilobit = Kilobit(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Kilobit = Kilobit(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Kilobit = Kilobit(this.value.times(other))
    override operator fun div(other: kotlin.Short): Kilobit = Kilobit(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Kilobit = Kilobit(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Kilobit = Kilobit(this.value.times(other))
    override operator fun div(other: kotlin.Int): Kilobit = Kilobit(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Kilobit = Kilobit(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Kilobit = Kilobit(this.value.times(other))
    override operator fun div(other: kotlin.Long): Kilobit = Kilobit(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Kilobit = Kilobit(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Kilobit = Kilobit(this.value.times(other))
    override operator fun div(other: kotlin.Float): Kilobit = Kilobit(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Kilobit = Kilobit(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Kilobit = Kilobit(this.value.times(other))
    override operator fun div(other: kotlin.Double): Kilobit = Kilobit(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Kilobit = Kilobit(this.value.rem(other))


    override operator fun unaryMinus(): Kilobit = Kilobit(-value)
    override operator fun unaryPlus(): Kilobit = this
    override operator fun inc(): Kilobit = Kilobit(this.value + 1)
    override operator fun dec(): Kilobit = Kilobit(this.value - 1)

    override fun round(): Kilobit = Kilobit(round(this.value))
    override fun truncate(): Kilobit = Kilobit(truncate(this.value))
    override fun floor(): Kilobit = Kilobit(floor(this.value))
    override fun ceil(): Kilobit = Kilobit(ceil(this.value))
    override fun abs(): Kilobit = Kilobit(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Kilobit -> this.value == other.value
        is Digital -> this.toBit().value == other.toBit().value
        else -> false
    }

    override fun hashCode(): Int = this.toBit().value.hashCode()


}

val kotlin.Byte.kilobit
    get() = Kilobit(this)


val kotlin.Short.kilobit
    get() = Kilobit(this)


val kotlin.Int.kilobit
    get() = Kilobit(this)


val kotlin.Long.kilobit
    get() = Kilobit(this)


val kotlin.Float.kilobit
    get() = Kilobit(this)


val kotlin.Double.kilobit
    get() = Kilobit(this)


data class Byte(override val value: MeasurementUnitValue) : Digital {

    override val unitName: String = "byte"

    override fun formatters(language: MeasurementUnitLanguage) = language.digitalByte
    override fun toBit() = Bit(value * (8) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Byte(0.0)
        fun fromBit(value: Bit) = Byte((value.value - 0) / (8))
    }

    operator fun plus(other: Byte): Byte = Byte(this.value + other.value)
    operator fun minus(other: Byte): Byte = Byte(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Byte = Byte(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Byte = Byte(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Byte = Byte(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Byte = Byte(this.value.times(other))
    override operator fun div(other: kotlin.Short): Byte = Byte(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Byte = Byte(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Byte = Byte(this.value.times(other))
    override operator fun div(other: kotlin.Int): Byte = Byte(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Byte = Byte(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Byte = Byte(this.value.times(other))
    override operator fun div(other: kotlin.Long): Byte = Byte(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Byte = Byte(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Byte = Byte(this.value.times(other))
    override operator fun div(other: kotlin.Float): Byte = Byte(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Byte = Byte(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Byte = Byte(this.value.times(other))
    override operator fun div(other: kotlin.Double): Byte = Byte(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Byte = Byte(this.value.rem(other))


    override operator fun unaryMinus(): Byte = Byte(-value)
    override operator fun unaryPlus(): Byte = this
    override operator fun inc(): Byte = Byte(this.value + 1)
    override operator fun dec(): Byte = Byte(this.value - 1)

    override fun round(): Byte = Byte(round(this.value))
    override fun truncate(): Byte = Byte(truncate(this.value))
    override fun floor(): Byte = Byte(floor(this.value))
    override fun ceil(): Byte = Byte(ceil(this.value))
    override fun abs(): Byte = Byte(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Byte -> this.value == other.value
        is Digital -> this.toBit().value == other.toBit().value
        else -> false
    }

    override fun hashCode(): Int = this.toBit().value.hashCode()


}

val kotlin.Byte.byte
    get() = Byte(this)


val kotlin.Short.byte
    get() = Byte(this)


val kotlin.Int.byte
    get() = Byte(this)


val kotlin.Long.byte
    get() = Byte(this)


val kotlin.Float.byte
    get() = Byte(this)


val kotlin.Double.byte
    get() = Byte(this)


data class Bit(override val value: MeasurementUnitValue) : Digital {

    override val unitName: String = "bit"

    override fun formatters(language: MeasurementUnitLanguage) = language.digitalBit
    override fun toBit() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Bit(0.0)
        fun fromBit(value: Bit) = value
    }

    operator fun plus(other: Bit): Bit = Bit(this.value + other.value)
    operator fun minus(other: Bit): Bit = Bit(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Bit = Bit(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Bit = Bit(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Bit = Bit(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Bit = Bit(this.value.times(other))
    override operator fun div(other: kotlin.Short): Bit = Bit(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Bit = Bit(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Bit = Bit(this.value.times(other))
    override operator fun div(other: kotlin.Int): Bit = Bit(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Bit = Bit(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Bit = Bit(this.value.times(other))
    override operator fun div(other: kotlin.Long): Bit = Bit(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Bit = Bit(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Bit = Bit(this.value.times(other))
    override operator fun div(other: kotlin.Float): Bit = Bit(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Bit = Bit(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Bit = Bit(this.value.times(other))
    override operator fun div(other: kotlin.Double): Bit = Bit(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Bit = Bit(this.value.rem(other))


    override operator fun unaryMinus(): Bit = Bit(-value)
    override operator fun unaryPlus(): Bit = this
    override operator fun inc(): Bit = Bit(this.value + 1)
    override operator fun dec(): Bit = Bit(this.value - 1)

    override fun round(): Bit = Bit(round(this.value))
    override fun truncate(): Bit = Bit(truncate(this.value))
    override fun floor(): Bit = Bit(floor(this.value))
    override fun ceil(): Bit = Bit(ceil(this.value))
    override fun abs(): Bit = Bit(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Bit -> this.value == other.value
        is Digital -> this.toBit().value == other.toBit().value
        else -> false
    }

    override fun hashCode(): Int = this.toBit().value.hashCode()


}

val kotlin.Byte.bit
    get() = Bit(this)


val kotlin.Short.bit
    get() = Bit(this)


val kotlin.Int.bit
    get() = Bit(this)


val kotlin.Long.bit
    get() = Bit(this)


val kotlin.Float.bit
    get() = Bit(this)


val kotlin.Double.bit
    get() = Bit(this)
                            
