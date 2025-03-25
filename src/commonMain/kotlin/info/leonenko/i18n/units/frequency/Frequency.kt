package info.leonenko.i18n.units.frequency

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

@Serializable(Frequency.Serializer::class)
interface Frequency : MeasurementUnit, Comparable<Frequency> {
    override val measurement: String get() = "frequency"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Frequency> = emptyMap()) : KSerializer<Frequency> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Frequency", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Frequency {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Frequency unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "gigahertz" -> Gigahertz(value)
                "megahertz" -> Megahertz(value)
                "kilohertz" -> Kilohertz(value)
                "hertz" -> Hertz(value)
                "revolution-per-second" -> RevolutionPerSecond(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Frequency unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Frequency) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toGigahertz(): Gigahertz {
        return Gigahertz.fromRevolutionPerSecond(toRevolutionPerSecond())
    }

    fun toMegahertz(): Megahertz {
        return Megahertz.fromRevolutionPerSecond(toRevolutionPerSecond())
    }

    fun toKilohertz(): Kilohertz {
        return Kilohertz.fromRevolutionPerSecond(toRevolutionPerSecond())
    }

    fun toHertz(): Hertz {
        return Hertz.fromRevolutionPerSecond(toRevolutionPerSecond())
    }

    fun toRevolutionPerSecond(): RevolutionPerSecond
    override fun getBaseUnitValue() = toRevolutionPerSecond().value

    companion object {
        val ZERO: Frequency = RevolutionPerSecond(0.0)

    }

    override operator fun compareTo(other: Frequency): Int = this.toRevolutionPerSecond().value.compareTo(other.toRevolutionPerSecond().value)

    operator fun plus(other: Frequency): RevolutionPerSecond = this.toRevolutionPerSecond() + other.toRevolutionPerSecond()
    operator fun minus(other: Frequency): RevolutionPerSecond = this.toRevolutionPerSecond() - other.toRevolutionPerSecond()
    operator fun div(other: Frequency): Double = this.toRevolutionPerSecond().value / other.toRevolutionPerSecond().value
    operator fun unaryMinus(): Frequency
    operator fun unaryPlus(): Frequency
    operator fun inc(): Frequency
    operator fun dec(): Frequency

    fun round(): Frequency
    fun truncate(): Frequency
    fun floor(): Frequency
    fun ceil(): Frequency
    fun abs(): Frequency


    operator fun times(other: Byte): Frequency
    operator fun div(other: Byte): Frequency
    operator fun rem(other: Byte): Frequency


    operator fun times(other: Short): Frequency
    operator fun div(other: Short): Frequency
    operator fun rem(other: Short): Frequency


    operator fun times(other: Int): Frequency
    operator fun div(other: Int): Frequency
    operator fun rem(other: Int): Frequency


    operator fun times(other: Long): Frequency
    operator fun div(other: Long): Frequency
    operator fun rem(other: Long): Frequency


    operator fun times(other: Float): Frequency
    operator fun div(other: Float): Frequency
    operator fun rem(other: Float): Frequency


    operator fun times(other: Double): Frequency
    operator fun div(other: Double): Frequency
    operator fun rem(other: Double): Frequency

}

data class Gigahertz(override val value: MeasurementUnitValue) : Frequency {

    override val unitName: String = "gigahertz"

    override fun formatters(language: MeasurementUnitLanguage) = language.frequencyGigahertz
    override fun toRevolutionPerSecond() = RevolutionPerSecond(value * (1 * (1e9)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Gigahertz(0.0)
        fun fromRevolutionPerSecond(value: RevolutionPerSecond) = Gigahertz((value.value - 0) / (1 * (1e9)))
    }

    operator fun plus(other: Gigahertz): Gigahertz = Gigahertz(this.value + other.value)
    operator fun minus(other: Gigahertz): Gigahertz = Gigahertz(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Gigahertz = Gigahertz(this.value.times(other))
    override operator fun div(other: Byte): Gigahertz = Gigahertz(this.value.div(other))
    override operator fun rem(other: Byte): Gigahertz = Gigahertz(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Gigahertz = Gigahertz(this.value.times(other))
    override operator fun div(other: Short): Gigahertz = Gigahertz(this.value.div(other))
    override operator fun rem(other: Short): Gigahertz = Gigahertz(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Gigahertz = Gigahertz(this.value.times(other))
    override operator fun div(other: Int): Gigahertz = Gigahertz(this.value.div(other))
    override operator fun rem(other: Int): Gigahertz = Gigahertz(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Gigahertz = Gigahertz(this.value.times(other))
    override operator fun div(other: Long): Gigahertz = Gigahertz(this.value.div(other))
    override operator fun rem(other: Long): Gigahertz = Gigahertz(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Gigahertz = Gigahertz(this.value.times(other))
    override operator fun div(other: Float): Gigahertz = Gigahertz(this.value.div(other))
    override operator fun rem(other: Float): Gigahertz = Gigahertz(this.value.rem(other))


    override operator fun times(other: Double): Gigahertz = Gigahertz(this.value.times(other))
    override operator fun div(other: Double): Gigahertz = Gigahertz(this.value.div(other))
    override operator fun rem(other: Double): Gigahertz = Gigahertz(this.value.rem(other))


    override operator fun unaryMinus(): Gigahertz = Gigahertz(-value)
    override operator fun unaryPlus(): Gigahertz = this
    override operator fun inc(): Gigahertz = Gigahertz(this.value + 1)
    override operator fun dec(): Gigahertz = Gigahertz(this.value - 1)

    override fun round(): Gigahertz = Gigahertz(round(this.value))
    override fun truncate(): Gigahertz = Gigahertz(truncate(this.value))
    override fun floor(): Gigahertz = Gigahertz(floor(this.value))
    override fun ceil(): Gigahertz = Gigahertz(ceil(this.value))
    override fun abs(): Gigahertz = Gigahertz(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Gigahertz -> this.value == other.value
        is Frequency -> this.toRevolutionPerSecond().value == other.toRevolutionPerSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toRevolutionPerSecond().value.hashCode()


}

val Byte.gigahertz
    get() = Gigahertz(this)


val Short.gigahertz
    get() = Gigahertz(this)


val Int.gigahertz
    get() = Gigahertz(this)


val Long.gigahertz
    get() = Gigahertz(this)


val Float.gigahertz
    get() = Gigahertz(this)


val Double.gigahertz
    get() = Gigahertz(this)


data class Megahertz(override val value: MeasurementUnitValue) : Frequency {

    override val unitName: String = "megahertz"

    override fun formatters(language: MeasurementUnitLanguage) = language.frequencyMegahertz
    override fun toRevolutionPerSecond() = RevolutionPerSecond(value * (1 * (1e6)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Megahertz(0.0)
        fun fromRevolutionPerSecond(value: RevolutionPerSecond) = Megahertz((value.value - 0) / (1 * (1e6)))
    }

    operator fun plus(other: Megahertz): Megahertz = Megahertz(this.value + other.value)
    operator fun minus(other: Megahertz): Megahertz = Megahertz(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Megahertz = Megahertz(this.value.times(other))
    override operator fun div(other: Byte): Megahertz = Megahertz(this.value.div(other))
    override operator fun rem(other: Byte): Megahertz = Megahertz(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Megahertz = Megahertz(this.value.times(other))
    override operator fun div(other: Short): Megahertz = Megahertz(this.value.div(other))
    override operator fun rem(other: Short): Megahertz = Megahertz(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Megahertz = Megahertz(this.value.times(other))
    override operator fun div(other: Int): Megahertz = Megahertz(this.value.div(other))
    override operator fun rem(other: Int): Megahertz = Megahertz(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Megahertz = Megahertz(this.value.times(other))
    override operator fun div(other: Long): Megahertz = Megahertz(this.value.div(other))
    override operator fun rem(other: Long): Megahertz = Megahertz(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Megahertz = Megahertz(this.value.times(other))
    override operator fun div(other: Float): Megahertz = Megahertz(this.value.div(other))
    override operator fun rem(other: Float): Megahertz = Megahertz(this.value.rem(other))


    override operator fun times(other: Double): Megahertz = Megahertz(this.value.times(other))
    override operator fun div(other: Double): Megahertz = Megahertz(this.value.div(other))
    override operator fun rem(other: Double): Megahertz = Megahertz(this.value.rem(other))


    override operator fun unaryMinus(): Megahertz = Megahertz(-value)
    override operator fun unaryPlus(): Megahertz = this
    override operator fun inc(): Megahertz = Megahertz(this.value + 1)
    override operator fun dec(): Megahertz = Megahertz(this.value - 1)

    override fun round(): Megahertz = Megahertz(round(this.value))
    override fun truncate(): Megahertz = Megahertz(truncate(this.value))
    override fun floor(): Megahertz = Megahertz(floor(this.value))
    override fun ceil(): Megahertz = Megahertz(ceil(this.value))
    override fun abs(): Megahertz = Megahertz(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Megahertz -> this.value == other.value
        is Frequency -> this.toRevolutionPerSecond().value == other.toRevolutionPerSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toRevolutionPerSecond().value.hashCode()


}

val Byte.megahertz
    get() = Megahertz(this)


val Short.megahertz
    get() = Megahertz(this)


val Int.megahertz
    get() = Megahertz(this)


val Long.megahertz
    get() = Megahertz(this)


val Float.megahertz
    get() = Megahertz(this)


val Double.megahertz
    get() = Megahertz(this)


data class Kilohertz(override val value: MeasurementUnitValue) : Frequency {

    override val unitName: String = "kilohertz"

    override fun formatters(language: MeasurementUnitLanguage) = language.frequencyKilohertz
    override fun toRevolutionPerSecond() = RevolutionPerSecond(value * (1 * (1e3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Kilohertz(0.0)
        fun fromRevolutionPerSecond(value: RevolutionPerSecond) = Kilohertz((value.value - 0) / (1 * (1e3)))
    }

    operator fun plus(other: Kilohertz): Kilohertz = Kilohertz(this.value + other.value)
    operator fun minus(other: Kilohertz): Kilohertz = Kilohertz(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Kilohertz = Kilohertz(this.value.times(other))
    override operator fun div(other: Byte): Kilohertz = Kilohertz(this.value.div(other))
    override operator fun rem(other: Byte): Kilohertz = Kilohertz(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Kilohertz = Kilohertz(this.value.times(other))
    override operator fun div(other: Short): Kilohertz = Kilohertz(this.value.div(other))
    override operator fun rem(other: Short): Kilohertz = Kilohertz(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Kilohertz = Kilohertz(this.value.times(other))
    override operator fun div(other: Int): Kilohertz = Kilohertz(this.value.div(other))
    override operator fun rem(other: Int): Kilohertz = Kilohertz(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Kilohertz = Kilohertz(this.value.times(other))
    override operator fun div(other: Long): Kilohertz = Kilohertz(this.value.div(other))
    override operator fun rem(other: Long): Kilohertz = Kilohertz(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Kilohertz = Kilohertz(this.value.times(other))
    override operator fun div(other: Float): Kilohertz = Kilohertz(this.value.div(other))
    override operator fun rem(other: Float): Kilohertz = Kilohertz(this.value.rem(other))


    override operator fun times(other: Double): Kilohertz = Kilohertz(this.value.times(other))
    override operator fun div(other: Double): Kilohertz = Kilohertz(this.value.div(other))
    override operator fun rem(other: Double): Kilohertz = Kilohertz(this.value.rem(other))


    override operator fun unaryMinus(): Kilohertz = Kilohertz(-value)
    override operator fun unaryPlus(): Kilohertz = this
    override operator fun inc(): Kilohertz = Kilohertz(this.value + 1)
    override operator fun dec(): Kilohertz = Kilohertz(this.value - 1)

    override fun round(): Kilohertz = Kilohertz(round(this.value))
    override fun truncate(): Kilohertz = Kilohertz(truncate(this.value))
    override fun floor(): Kilohertz = Kilohertz(floor(this.value))
    override fun ceil(): Kilohertz = Kilohertz(ceil(this.value))
    override fun abs(): Kilohertz = Kilohertz(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Kilohertz -> this.value == other.value
        is Frequency -> this.toRevolutionPerSecond().value == other.toRevolutionPerSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toRevolutionPerSecond().value.hashCode()


}

val Byte.kilohertz
    get() = Kilohertz(this)


val Short.kilohertz
    get() = Kilohertz(this)


val Int.kilohertz
    get() = Kilohertz(this)


val Long.kilohertz
    get() = Kilohertz(this)


val Float.kilohertz
    get() = Kilohertz(this)


val Double.kilohertz
    get() = Kilohertz(this)


data class Hertz(override val value: MeasurementUnitValue) : Frequency {

    override val unitName: String = "hertz"

    override fun formatters(language: MeasurementUnitLanguage) = language.frequencyHertz
    override fun toRevolutionPerSecond() = RevolutionPerSecond(value * (1) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Hertz(0.0)
        fun fromRevolutionPerSecond(value: RevolutionPerSecond) = Hertz((value.value - 0) / (1))
    }

    operator fun plus(other: Hertz): Hertz = Hertz(this.value + other.value)
    operator fun minus(other: Hertz): Hertz = Hertz(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Hertz = Hertz(this.value.times(other))
    override operator fun div(other: Byte): Hertz = Hertz(this.value.div(other))
    override operator fun rem(other: Byte): Hertz = Hertz(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Hertz = Hertz(this.value.times(other))
    override operator fun div(other: Short): Hertz = Hertz(this.value.div(other))
    override operator fun rem(other: Short): Hertz = Hertz(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Hertz = Hertz(this.value.times(other))
    override operator fun div(other: Int): Hertz = Hertz(this.value.div(other))
    override operator fun rem(other: Int): Hertz = Hertz(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Hertz = Hertz(this.value.times(other))
    override operator fun div(other: Long): Hertz = Hertz(this.value.div(other))
    override operator fun rem(other: Long): Hertz = Hertz(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Hertz = Hertz(this.value.times(other))
    override operator fun div(other: Float): Hertz = Hertz(this.value.div(other))
    override operator fun rem(other: Float): Hertz = Hertz(this.value.rem(other))


    override operator fun times(other: Double): Hertz = Hertz(this.value.times(other))
    override operator fun div(other: Double): Hertz = Hertz(this.value.div(other))
    override operator fun rem(other: Double): Hertz = Hertz(this.value.rem(other))


    override operator fun unaryMinus(): Hertz = Hertz(-value)
    override operator fun unaryPlus(): Hertz = this
    override operator fun inc(): Hertz = Hertz(this.value + 1)
    override operator fun dec(): Hertz = Hertz(this.value - 1)

    override fun round(): Hertz = Hertz(round(this.value))
    override fun truncate(): Hertz = Hertz(truncate(this.value))
    override fun floor(): Hertz = Hertz(floor(this.value))
    override fun ceil(): Hertz = Hertz(ceil(this.value))
    override fun abs(): Hertz = Hertz(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Hertz -> this.value == other.value
        is Frequency -> this.toRevolutionPerSecond().value == other.toRevolutionPerSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toRevolutionPerSecond().value.hashCode()


}

val Byte.hertz
    get() = Hertz(this)


val Short.hertz
    get() = Hertz(this)


val Int.hertz
    get() = Hertz(this)


val Long.hertz
    get() = Hertz(this)


val Float.hertz
    get() = Hertz(this)


val Double.hertz
    get() = Hertz(this)


data class RevolutionPerSecond(override val value: MeasurementUnitValue) : Frequency {

    override val unitName: String = "revolution-per-second"

    override fun formatters(language: MeasurementUnitLanguage) = language.frequencyRevolutionPerSecond
    override fun toRevolutionPerSecond() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = RevolutionPerSecond(0.0)
        fun fromRevolutionPerSecond(value: RevolutionPerSecond) = value
    }

    operator fun plus(other: RevolutionPerSecond): RevolutionPerSecond = RevolutionPerSecond(this.value + other.value)
    operator fun minus(other: RevolutionPerSecond): RevolutionPerSecond = RevolutionPerSecond(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): RevolutionPerSecond = RevolutionPerSecond(this.value.times(other))
    override operator fun div(other: Byte): RevolutionPerSecond = RevolutionPerSecond(this.value.div(other))
    override operator fun rem(other: Byte): RevolutionPerSecond = RevolutionPerSecond(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): RevolutionPerSecond = RevolutionPerSecond(this.value.times(other))
    override operator fun div(other: Short): RevolutionPerSecond = RevolutionPerSecond(this.value.div(other))
    override operator fun rem(other: Short): RevolutionPerSecond = RevolutionPerSecond(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): RevolutionPerSecond = RevolutionPerSecond(this.value.times(other))
    override operator fun div(other: Int): RevolutionPerSecond = RevolutionPerSecond(this.value.div(other))
    override operator fun rem(other: Int): RevolutionPerSecond = RevolutionPerSecond(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): RevolutionPerSecond = RevolutionPerSecond(this.value.times(other))
    override operator fun div(other: Long): RevolutionPerSecond = RevolutionPerSecond(this.value.div(other))
    override operator fun rem(other: Long): RevolutionPerSecond = RevolutionPerSecond(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): RevolutionPerSecond = RevolutionPerSecond(this.value.times(other))
    override operator fun div(other: Float): RevolutionPerSecond = RevolutionPerSecond(this.value.div(other))
    override operator fun rem(other: Float): RevolutionPerSecond = RevolutionPerSecond(this.value.rem(other))


    override operator fun times(other: Double): RevolutionPerSecond = RevolutionPerSecond(this.value.times(other))
    override operator fun div(other: Double): RevolutionPerSecond = RevolutionPerSecond(this.value.div(other))
    override operator fun rem(other: Double): RevolutionPerSecond = RevolutionPerSecond(this.value.rem(other))


    override operator fun unaryMinus(): RevolutionPerSecond = RevolutionPerSecond(-value)
    override operator fun unaryPlus(): RevolutionPerSecond = this
    override operator fun inc(): RevolutionPerSecond = RevolutionPerSecond(this.value + 1)
    override operator fun dec(): RevolutionPerSecond = RevolutionPerSecond(this.value - 1)

    override fun round(): RevolutionPerSecond = RevolutionPerSecond(round(this.value))
    override fun truncate(): RevolutionPerSecond = RevolutionPerSecond(truncate(this.value))
    override fun floor(): RevolutionPerSecond = RevolutionPerSecond(floor(this.value))
    override fun ceil(): RevolutionPerSecond = RevolutionPerSecond(ceil(this.value))
    override fun abs(): RevolutionPerSecond = RevolutionPerSecond(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is RevolutionPerSecond -> this.value == other.value
        is Frequency -> this.toRevolutionPerSecond().value == other.toRevolutionPerSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toRevolutionPerSecond().value.hashCode()


}

val Byte.revolutionPerSecond
    get() = RevolutionPerSecond(this)


val Short.revolutionPerSecond
    get() = RevolutionPerSecond(this)


val Int.revolutionPerSecond
    get() = RevolutionPerSecond(this)


val Long.revolutionPerSecond
    get() = RevolutionPerSecond(this)


val Float.revolutionPerSecond
    get() = RevolutionPerSecond(this)


val Double.revolutionPerSecond
    get() = RevolutionPerSecond(this)
                            
