package info.leonenko.i18n.units.digital

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
                "byte-unit" -> ByteUnit(value)
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

    fun toByteUnit(): ByteUnit = ByteUnit(value)
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


    operator fun times(other: Byte): Digital
    operator fun div(other: Byte): Digital
    operator fun rem(other: Byte): Digital


    operator fun times(other: Short): Digital
    operator fun div(other: Short): Digital
    operator fun rem(other: Short): Digital


    operator fun times(other: Int): Digital
    operator fun div(other: Int): Digital
    operator fun rem(other: Int): Digital


    operator fun times(other: Long): Digital
    operator fun div(other: Long): Digital
    operator fun rem(other: Long): Digital


    operator fun times(other: Float): Digital
    operator fun div(other: Float): Digital
    operator fun rem(other: Float): Digital


    operator fun times(other: Double): Digital
    operator fun div(other: Double): Digital
    operator fun rem(other: Double): Digital

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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Petabyte = Petabyte(this.value.times(other))
    override operator fun div(other: Byte): Petabyte = Petabyte(this.value.div(other))
    override operator fun rem(other: Byte): Petabyte = Petabyte(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Petabyte = Petabyte(this.value.times(other))
    override operator fun div(other: Short): Petabyte = Petabyte(this.value.div(other))
    override operator fun rem(other: Short): Petabyte = Petabyte(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Petabyte = Petabyte(this.value.times(other))
    override operator fun div(other: Int): Petabyte = Petabyte(this.value.div(other))
    override operator fun rem(other: Int): Petabyte = Petabyte(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Petabyte = Petabyte(this.value.times(other))
    override operator fun div(other: Long): Petabyte = Petabyte(this.value.div(other))
    override operator fun rem(other: Long): Petabyte = Petabyte(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Petabyte = Petabyte(this.value.times(other))
    override operator fun div(other: Float): Petabyte = Petabyte(this.value.div(other))
    override operator fun rem(other: Float): Petabyte = Petabyte(this.value.rem(other))


    override operator fun times(other: Double): Petabyte = Petabyte(this.value.times(other))
    override operator fun div(other: Double): Petabyte = Petabyte(this.value.div(other))
    override operator fun rem(other: Double): Petabyte = Petabyte(this.value.rem(other))


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

val Byte.petabyte
    get() = Petabyte(this)


val Short.petabyte
    get() = Petabyte(this)


val Int.petabyte
    get() = Petabyte(this)


val Long.petabyte
    get() = Petabyte(this)


val Float.petabyte
    get() = Petabyte(this)


val Double.petabyte
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Terabyte = Terabyte(this.value.times(other))
    override operator fun div(other: Byte): Terabyte = Terabyte(this.value.div(other))
    override operator fun rem(other: Byte): Terabyte = Terabyte(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Terabyte = Terabyte(this.value.times(other))
    override operator fun div(other: Short): Terabyte = Terabyte(this.value.div(other))
    override operator fun rem(other: Short): Terabyte = Terabyte(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Terabyte = Terabyte(this.value.times(other))
    override operator fun div(other: Int): Terabyte = Terabyte(this.value.div(other))
    override operator fun rem(other: Int): Terabyte = Terabyte(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Terabyte = Terabyte(this.value.times(other))
    override operator fun div(other: Long): Terabyte = Terabyte(this.value.div(other))
    override operator fun rem(other: Long): Terabyte = Terabyte(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Terabyte = Terabyte(this.value.times(other))
    override operator fun div(other: Float): Terabyte = Terabyte(this.value.div(other))
    override operator fun rem(other: Float): Terabyte = Terabyte(this.value.rem(other))


    override operator fun times(other: Double): Terabyte = Terabyte(this.value.times(other))
    override operator fun div(other: Double): Terabyte = Terabyte(this.value.div(other))
    override operator fun rem(other: Double): Terabyte = Terabyte(this.value.rem(other))


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

val Byte.terabyte
    get() = Terabyte(this)


val Short.terabyte
    get() = Terabyte(this)


val Int.terabyte
    get() = Terabyte(this)


val Long.terabyte
    get() = Terabyte(this)


val Float.terabyte
    get() = Terabyte(this)


val Double.terabyte
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Terabit = Terabit(this.value.times(other))
    override operator fun div(other: Byte): Terabit = Terabit(this.value.div(other))
    override operator fun rem(other: Byte): Terabit = Terabit(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Terabit = Terabit(this.value.times(other))
    override operator fun div(other: Short): Terabit = Terabit(this.value.div(other))
    override operator fun rem(other: Short): Terabit = Terabit(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Terabit = Terabit(this.value.times(other))
    override operator fun div(other: Int): Terabit = Terabit(this.value.div(other))
    override operator fun rem(other: Int): Terabit = Terabit(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Terabit = Terabit(this.value.times(other))
    override operator fun div(other: Long): Terabit = Terabit(this.value.div(other))
    override operator fun rem(other: Long): Terabit = Terabit(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Terabit = Terabit(this.value.times(other))
    override operator fun div(other: Float): Terabit = Terabit(this.value.div(other))
    override operator fun rem(other: Float): Terabit = Terabit(this.value.rem(other))


    override operator fun times(other: Double): Terabit = Terabit(this.value.times(other))
    override operator fun div(other: Double): Terabit = Terabit(this.value.div(other))
    override operator fun rem(other: Double): Terabit = Terabit(this.value.rem(other))


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

val Byte.terabit
    get() = Terabit(this)


val Short.terabit
    get() = Terabit(this)


val Int.terabit
    get() = Terabit(this)


val Long.terabit
    get() = Terabit(this)


val Float.terabit
    get() = Terabit(this)


val Double.terabit
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Gigabyte = Gigabyte(this.value.times(other))
    override operator fun div(other: Byte): Gigabyte = Gigabyte(this.value.div(other))
    override operator fun rem(other: Byte): Gigabyte = Gigabyte(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Gigabyte = Gigabyte(this.value.times(other))
    override operator fun div(other: Short): Gigabyte = Gigabyte(this.value.div(other))
    override operator fun rem(other: Short): Gigabyte = Gigabyte(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Gigabyte = Gigabyte(this.value.times(other))
    override operator fun div(other: Int): Gigabyte = Gigabyte(this.value.div(other))
    override operator fun rem(other: Int): Gigabyte = Gigabyte(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Gigabyte = Gigabyte(this.value.times(other))
    override operator fun div(other: Long): Gigabyte = Gigabyte(this.value.div(other))
    override operator fun rem(other: Long): Gigabyte = Gigabyte(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Gigabyte = Gigabyte(this.value.times(other))
    override operator fun div(other: Float): Gigabyte = Gigabyte(this.value.div(other))
    override operator fun rem(other: Float): Gigabyte = Gigabyte(this.value.rem(other))


    override operator fun times(other: Double): Gigabyte = Gigabyte(this.value.times(other))
    override operator fun div(other: Double): Gigabyte = Gigabyte(this.value.div(other))
    override operator fun rem(other: Double): Gigabyte = Gigabyte(this.value.rem(other))


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

val Byte.gigabyte
    get() = Gigabyte(this)


val Short.gigabyte
    get() = Gigabyte(this)


val Int.gigabyte
    get() = Gigabyte(this)


val Long.gigabyte
    get() = Gigabyte(this)


val Float.gigabyte
    get() = Gigabyte(this)


val Double.gigabyte
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Gigabit = Gigabit(this.value.times(other))
    override operator fun div(other: Byte): Gigabit = Gigabit(this.value.div(other))
    override operator fun rem(other: Byte): Gigabit = Gigabit(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Gigabit = Gigabit(this.value.times(other))
    override operator fun div(other: Short): Gigabit = Gigabit(this.value.div(other))
    override operator fun rem(other: Short): Gigabit = Gigabit(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Gigabit = Gigabit(this.value.times(other))
    override operator fun div(other: Int): Gigabit = Gigabit(this.value.div(other))
    override operator fun rem(other: Int): Gigabit = Gigabit(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Gigabit = Gigabit(this.value.times(other))
    override operator fun div(other: Long): Gigabit = Gigabit(this.value.div(other))
    override operator fun rem(other: Long): Gigabit = Gigabit(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Gigabit = Gigabit(this.value.times(other))
    override operator fun div(other: Float): Gigabit = Gigabit(this.value.div(other))
    override operator fun rem(other: Float): Gigabit = Gigabit(this.value.rem(other))


    override operator fun times(other: Double): Gigabit = Gigabit(this.value.times(other))
    override operator fun div(other: Double): Gigabit = Gigabit(this.value.div(other))
    override operator fun rem(other: Double): Gigabit = Gigabit(this.value.rem(other))


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

val Byte.gigabit
    get() = Gigabit(this)


val Short.gigabit
    get() = Gigabit(this)


val Int.gigabit
    get() = Gigabit(this)


val Long.gigabit
    get() = Gigabit(this)


val Float.gigabit
    get() = Gigabit(this)


val Double.gigabit
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Megabyte = Megabyte(this.value.times(other))
    override operator fun div(other: Byte): Megabyte = Megabyte(this.value.div(other))
    override operator fun rem(other: Byte): Megabyte = Megabyte(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Megabyte = Megabyte(this.value.times(other))
    override operator fun div(other: Short): Megabyte = Megabyte(this.value.div(other))
    override operator fun rem(other: Short): Megabyte = Megabyte(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Megabyte = Megabyte(this.value.times(other))
    override operator fun div(other: Int): Megabyte = Megabyte(this.value.div(other))
    override operator fun rem(other: Int): Megabyte = Megabyte(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Megabyte = Megabyte(this.value.times(other))
    override operator fun div(other: Long): Megabyte = Megabyte(this.value.div(other))
    override operator fun rem(other: Long): Megabyte = Megabyte(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Megabyte = Megabyte(this.value.times(other))
    override operator fun div(other: Float): Megabyte = Megabyte(this.value.div(other))
    override operator fun rem(other: Float): Megabyte = Megabyte(this.value.rem(other))


    override operator fun times(other: Double): Megabyte = Megabyte(this.value.times(other))
    override operator fun div(other: Double): Megabyte = Megabyte(this.value.div(other))
    override operator fun rem(other: Double): Megabyte = Megabyte(this.value.rem(other))


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

val Byte.megabyte
    get() = Megabyte(this)


val Short.megabyte
    get() = Megabyte(this)


val Int.megabyte
    get() = Megabyte(this)


val Long.megabyte
    get() = Megabyte(this)


val Float.megabyte
    get() = Megabyte(this)


val Double.megabyte
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Megabit = Megabit(this.value.times(other))
    override operator fun div(other: Byte): Megabit = Megabit(this.value.div(other))
    override operator fun rem(other: Byte): Megabit = Megabit(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Megabit = Megabit(this.value.times(other))
    override operator fun div(other: Short): Megabit = Megabit(this.value.div(other))
    override operator fun rem(other: Short): Megabit = Megabit(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Megabit = Megabit(this.value.times(other))
    override operator fun div(other: Int): Megabit = Megabit(this.value.div(other))
    override operator fun rem(other: Int): Megabit = Megabit(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Megabit = Megabit(this.value.times(other))
    override operator fun div(other: Long): Megabit = Megabit(this.value.div(other))
    override operator fun rem(other: Long): Megabit = Megabit(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Megabit = Megabit(this.value.times(other))
    override operator fun div(other: Float): Megabit = Megabit(this.value.div(other))
    override operator fun rem(other: Float): Megabit = Megabit(this.value.rem(other))


    override operator fun times(other: Double): Megabit = Megabit(this.value.times(other))
    override operator fun div(other: Double): Megabit = Megabit(this.value.div(other))
    override operator fun rem(other: Double): Megabit = Megabit(this.value.rem(other))


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

val Byte.megabit
    get() = Megabit(this)


val Short.megabit
    get() = Megabit(this)


val Int.megabit
    get() = Megabit(this)


val Long.megabit
    get() = Megabit(this)


val Float.megabit
    get() = Megabit(this)


val Double.megabit
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Kilobyte = Kilobyte(this.value.times(other))
    override operator fun div(other: Byte): Kilobyte = Kilobyte(this.value.div(other))
    override operator fun rem(other: Byte): Kilobyte = Kilobyte(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Kilobyte = Kilobyte(this.value.times(other))
    override operator fun div(other: Short): Kilobyte = Kilobyte(this.value.div(other))
    override operator fun rem(other: Short): Kilobyte = Kilobyte(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Kilobyte = Kilobyte(this.value.times(other))
    override operator fun div(other: Int): Kilobyte = Kilobyte(this.value.div(other))
    override operator fun rem(other: Int): Kilobyte = Kilobyte(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Kilobyte = Kilobyte(this.value.times(other))
    override operator fun div(other: Long): Kilobyte = Kilobyte(this.value.div(other))
    override operator fun rem(other: Long): Kilobyte = Kilobyte(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Kilobyte = Kilobyte(this.value.times(other))
    override operator fun div(other: Float): Kilobyte = Kilobyte(this.value.div(other))
    override operator fun rem(other: Float): Kilobyte = Kilobyte(this.value.rem(other))


    override operator fun times(other: Double): Kilobyte = Kilobyte(this.value.times(other))
    override operator fun div(other: Double): Kilobyte = Kilobyte(this.value.div(other))
    override operator fun rem(other: Double): Kilobyte = Kilobyte(this.value.rem(other))


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

val Byte.kilobyte
    get() = Kilobyte(this)


val Short.kilobyte
    get() = Kilobyte(this)


val Int.kilobyte
    get() = Kilobyte(this)


val Long.kilobyte
    get() = Kilobyte(this)


val Float.kilobyte
    get() = Kilobyte(this)


val Double.kilobyte
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Kilobit = Kilobit(this.value.times(other))
    override operator fun div(other: Byte): Kilobit = Kilobit(this.value.div(other))
    override operator fun rem(other: Byte): Kilobit = Kilobit(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Kilobit = Kilobit(this.value.times(other))
    override operator fun div(other: Short): Kilobit = Kilobit(this.value.div(other))
    override operator fun rem(other: Short): Kilobit = Kilobit(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Kilobit = Kilobit(this.value.times(other))
    override operator fun div(other: Int): Kilobit = Kilobit(this.value.div(other))
    override operator fun rem(other: Int): Kilobit = Kilobit(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Kilobit = Kilobit(this.value.times(other))
    override operator fun div(other: Long): Kilobit = Kilobit(this.value.div(other))
    override operator fun rem(other: Long): Kilobit = Kilobit(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Kilobit = Kilobit(this.value.times(other))
    override operator fun div(other: Float): Kilobit = Kilobit(this.value.div(other))
    override operator fun rem(other: Float): Kilobit = Kilobit(this.value.rem(other))


    override operator fun times(other: Double): Kilobit = Kilobit(this.value.times(other))
    override operator fun div(other: Double): Kilobit = Kilobit(this.value.div(other))
    override operator fun rem(other: Double): Kilobit = Kilobit(this.value.rem(other))


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

val Byte.kilobit
    get() = Kilobit(this)


val Short.kilobit
    get() = Kilobit(this)


val Int.kilobit
    get() = Kilobit(this)


val Long.kilobit
    get() = Kilobit(this)


val Float.kilobit
    get() = Kilobit(this)


val Double.kilobit
    get() = Kilobit(this)


data class ByteUnit(override val value: MeasurementUnitValue) : Digital {

    override val unitName: String = "byte-unit"

    override fun formatters(language: MeasurementUnitLanguage) = language.digitalByteUnit
    override fun toBit(): Bit = TODO("Conversion of ByteUnit to Bit is not supported")
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = ByteUnit(0.0)
        fun fromBit(value: Bit): ByteUnit = TODO("Conversion of Bit to ByteUnit is not supported")
    }

    operator fun plus(other: ByteUnit): ByteUnit = ByteUnit(this.value + other.value)
    operator fun minus(other: ByteUnit): ByteUnit = ByteUnit(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): ByteUnit = ByteUnit(this.value.times(other))
    override operator fun div(other: Byte): ByteUnit = ByteUnit(this.value.div(other))
    override operator fun rem(other: Byte): ByteUnit = ByteUnit(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): ByteUnit = ByteUnit(this.value.times(other))
    override operator fun div(other: Short): ByteUnit = ByteUnit(this.value.div(other))
    override operator fun rem(other: Short): ByteUnit = ByteUnit(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): ByteUnit = ByteUnit(this.value.times(other))
    override operator fun div(other: Int): ByteUnit = ByteUnit(this.value.div(other))
    override operator fun rem(other: Int): ByteUnit = ByteUnit(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): ByteUnit = ByteUnit(this.value.times(other))
    override operator fun div(other: Long): ByteUnit = ByteUnit(this.value.div(other))
    override operator fun rem(other: Long): ByteUnit = ByteUnit(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): ByteUnit = ByteUnit(this.value.times(other))
    override operator fun div(other: Float): ByteUnit = ByteUnit(this.value.div(other))
    override operator fun rem(other: Float): ByteUnit = ByteUnit(this.value.rem(other))


    override operator fun times(other: Double): ByteUnit = ByteUnit(this.value.times(other))
    override operator fun div(other: Double): ByteUnit = ByteUnit(this.value.div(other))
    override operator fun rem(other: Double): ByteUnit = ByteUnit(this.value.rem(other))


    override operator fun unaryMinus(): ByteUnit = ByteUnit(-value)
    override operator fun unaryPlus(): ByteUnit = this
    override operator fun inc(): ByteUnit = ByteUnit(this.value + 1)
    override operator fun dec(): ByteUnit = ByteUnit(this.value - 1)

    override fun round(): ByteUnit = ByteUnit(round(this.value))
    override fun truncate(): ByteUnit = ByteUnit(truncate(this.value))
    override fun floor(): ByteUnit = ByteUnit(floor(this.value))
    override fun ceil(): ByteUnit = ByteUnit(ceil(this.value))
    override fun abs(): ByteUnit = ByteUnit(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is ByteUnit -> this.value == other.value
        is Digital -> this.toBit().value == other.toBit().value
        else -> false
    }

    override fun hashCode(): Int = this.toBit().value.hashCode()


}

val Byte.byteUnit
    get() = ByteUnit(this)


val Short.byteUnit
    get() = ByteUnit(this)


val Int.byteUnit
    get() = ByteUnit(this)


val Long.byteUnit
    get() = ByteUnit(this)


val Float.byteUnit
    get() = ByteUnit(this)


val Double.byteUnit
    get() = ByteUnit(this)


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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Bit = Bit(this.value.times(other))
    override operator fun div(other: Byte): Bit = Bit(this.value.div(other))
    override operator fun rem(other: Byte): Bit = Bit(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Bit = Bit(this.value.times(other))
    override operator fun div(other: Short): Bit = Bit(this.value.div(other))
    override operator fun rem(other: Short): Bit = Bit(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Bit = Bit(this.value.times(other))
    override operator fun div(other: Int): Bit = Bit(this.value.div(other))
    override operator fun rem(other: Int): Bit = Bit(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Bit = Bit(this.value.times(other))
    override operator fun div(other: Long): Bit = Bit(this.value.div(other))
    override operator fun rem(other: Long): Bit = Bit(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Bit = Bit(this.value.times(other))
    override operator fun div(other: Float): Bit = Bit(this.value.div(other))
    override operator fun rem(other: Float): Bit = Bit(this.value.rem(other))


    override operator fun times(other: Double): Bit = Bit(this.value.times(other))
    override operator fun div(other: Double): Bit = Bit(this.value.div(other))
    override operator fun rem(other: Double): Bit = Bit(this.value.rem(other))


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

val Byte.bit
    get() = Bit(this)


val Short.bit
    get() = Bit(this)


val Int.bit
    get() = Bit(this)


val Long.bit
    get() = Bit(this)


val Float.bit
    get() = Bit(this)


val Double.bit
    get() = Bit(this)
                            
