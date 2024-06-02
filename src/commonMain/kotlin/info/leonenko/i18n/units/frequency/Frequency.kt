package info.leonenko.i18n.units.frequency

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


    operator fun times(other: kotlin.Byte): Frequency
    operator fun div(other: kotlin.Byte): Frequency
    operator fun rem(other: kotlin.Byte): Frequency


    operator fun times(other: kotlin.Short): Frequency
    operator fun div(other: kotlin.Short): Frequency
    operator fun rem(other: kotlin.Short): Frequency


    operator fun times(other: kotlin.Int): Frequency
    operator fun div(other: kotlin.Int): Frequency
    operator fun rem(other: kotlin.Int): Frequency


    operator fun times(other: kotlin.Long): Frequency
    operator fun div(other: kotlin.Long): Frequency
    operator fun rem(other: kotlin.Long): Frequency


    operator fun times(other: kotlin.Float): Frequency
    operator fun div(other: kotlin.Float): Frequency
    operator fun rem(other: kotlin.Float): Frequency


    operator fun times(other: kotlin.Double): Frequency
    operator fun div(other: kotlin.Double): Frequency
    operator fun rem(other: kotlin.Double): Frequency

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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Gigahertz = Gigahertz(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Gigahertz = Gigahertz(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Gigahertz = Gigahertz(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Gigahertz = Gigahertz(this.value.times(other))
    override operator fun div(other: kotlin.Short): Gigahertz = Gigahertz(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Gigahertz = Gigahertz(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Gigahertz = Gigahertz(this.value.times(other))
    override operator fun div(other: kotlin.Int): Gigahertz = Gigahertz(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Gigahertz = Gigahertz(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Gigahertz = Gigahertz(this.value.times(other))
    override operator fun div(other: kotlin.Long): Gigahertz = Gigahertz(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Gigahertz = Gigahertz(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Gigahertz = Gigahertz(this.value.times(other))
    override operator fun div(other: kotlin.Float): Gigahertz = Gigahertz(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Gigahertz = Gigahertz(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Gigahertz = Gigahertz(this.value.times(other))
    override operator fun div(other: kotlin.Double): Gigahertz = Gigahertz(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Gigahertz = Gigahertz(this.value.rem(other))


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

val kotlin.Byte.gigahertz
    get() = Gigahertz(this)


val kotlin.Short.gigahertz
    get() = Gigahertz(this)


val kotlin.Int.gigahertz
    get() = Gigahertz(this)


val kotlin.Long.gigahertz
    get() = Gigahertz(this)


val kotlin.Float.gigahertz
    get() = Gigahertz(this)


val kotlin.Double.gigahertz
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Megahertz = Megahertz(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Megahertz = Megahertz(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Megahertz = Megahertz(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Megahertz = Megahertz(this.value.times(other))
    override operator fun div(other: kotlin.Short): Megahertz = Megahertz(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Megahertz = Megahertz(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Megahertz = Megahertz(this.value.times(other))
    override operator fun div(other: kotlin.Int): Megahertz = Megahertz(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Megahertz = Megahertz(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Megahertz = Megahertz(this.value.times(other))
    override operator fun div(other: kotlin.Long): Megahertz = Megahertz(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Megahertz = Megahertz(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Megahertz = Megahertz(this.value.times(other))
    override operator fun div(other: kotlin.Float): Megahertz = Megahertz(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Megahertz = Megahertz(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Megahertz = Megahertz(this.value.times(other))
    override operator fun div(other: kotlin.Double): Megahertz = Megahertz(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Megahertz = Megahertz(this.value.rem(other))


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

val kotlin.Byte.megahertz
    get() = Megahertz(this)


val kotlin.Short.megahertz
    get() = Megahertz(this)


val kotlin.Int.megahertz
    get() = Megahertz(this)


val kotlin.Long.megahertz
    get() = Megahertz(this)


val kotlin.Float.megahertz
    get() = Megahertz(this)


val kotlin.Double.megahertz
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Kilohertz = Kilohertz(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Kilohertz = Kilohertz(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Kilohertz = Kilohertz(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Kilohertz = Kilohertz(this.value.times(other))
    override operator fun div(other: kotlin.Short): Kilohertz = Kilohertz(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Kilohertz = Kilohertz(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Kilohertz = Kilohertz(this.value.times(other))
    override operator fun div(other: kotlin.Int): Kilohertz = Kilohertz(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Kilohertz = Kilohertz(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Kilohertz = Kilohertz(this.value.times(other))
    override operator fun div(other: kotlin.Long): Kilohertz = Kilohertz(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Kilohertz = Kilohertz(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Kilohertz = Kilohertz(this.value.times(other))
    override operator fun div(other: kotlin.Float): Kilohertz = Kilohertz(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Kilohertz = Kilohertz(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Kilohertz = Kilohertz(this.value.times(other))
    override operator fun div(other: kotlin.Double): Kilohertz = Kilohertz(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Kilohertz = Kilohertz(this.value.rem(other))


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

val kotlin.Byte.kilohertz
    get() = Kilohertz(this)


val kotlin.Short.kilohertz
    get() = Kilohertz(this)


val kotlin.Int.kilohertz
    get() = Kilohertz(this)


val kotlin.Long.kilohertz
    get() = Kilohertz(this)


val kotlin.Float.kilohertz
    get() = Kilohertz(this)


val kotlin.Double.kilohertz
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Hertz = Hertz(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Hertz = Hertz(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Hertz = Hertz(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Hertz = Hertz(this.value.times(other))
    override operator fun div(other: kotlin.Short): Hertz = Hertz(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Hertz = Hertz(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Hertz = Hertz(this.value.times(other))
    override operator fun div(other: kotlin.Int): Hertz = Hertz(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Hertz = Hertz(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Hertz = Hertz(this.value.times(other))
    override operator fun div(other: kotlin.Long): Hertz = Hertz(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Hertz = Hertz(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Hertz = Hertz(this.value.times(other))
    override operator fun div(other: kotlin.Float): Hertz = Hertz(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Hertz = Hertz(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Hertz = Hertz(this.value.times(other))
    override operator fun div(other: kotlin.Double): Hertz = Hertz(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Hertz = Hertz(this.value.rem(other))


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

val kotlin.Byte.hertz
    get() = Hertz(this)


val kotlin.Short.hertz
    get() = Hertz(this)


val kotlin.Int.hertz
    get() = Hertz(this)


val kotlin.Long.hertz
    get() = Hertz(this)


val kotlin.Float.hertz
    get() = Hertz(this)


val kotlin.Double.hertz
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): RevolutionPerSecond = RevolutionPerSecond(this.value.times(other))
    override operator fun div(other: kotlin.Byte): RevolutionPerSecond = RevolutionPerSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): RevolutionPerSecond = RevolutionPerSecond(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): RevolutionPerSecond = RevolutionPerSecond(this.value.times(other))
    override operator fun div(other: kotlin.Short): RevolutionPerSecond = RevolutionPerSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Short): RevolutionPerSecond = RevolutionPerSecond(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): RevolutionPerSecond = RevolutionPerSecond(this.value.times(other))
    override operator fun div(other: kotlin.Int): RevolutionPerSecond = RevolutionPerSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Int): RevolutionPerSecond = RevolutionPerSecond(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): RevolutionPerSecond = RevolutionPerSecond(this.value.times(other))
    override operator fun div(other: kotlin.Long): RevolutionPerSecond = RevolutionPerSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Long): RevolutionPerSecond = RevolutionPerSecond(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): RevolutionPerSecond = RevolutionPerSecond(this.value.times(other))
    override operator fun div(other: kotlin.Float): RevolutionPerSecond = RevolutionPerSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Float): RevolutionPerSecond = RevolutionPerSecond(this.value.rem(other))


    override operator fun times(other: kotlin.Double): RevolutionPerSecond = RevolutionPerSecond(this.value.times(other))
    override operator fun div(other: kotlin.Double): RevolutionPerSecond = RevolutionPerSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Double): RevolutionPerSecond = RevolutionPerSecond(this.value.rem(other))


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

val kotlin.Byte.revolutionPerSecond
    get() = RevolutionPerSecond(this)


val kotlin.Short.revolutionPerSecond
    get() = RevolutionPerSecond(this)


val kotlin.Int.revolutionPerSecond
    get() = RevolutionPerSecond(this)


val kotlin.Long.revolutionPerSecond
    get() = RevolutionPerSecond(this)


val kotlin.Float.revolutionPerSecond
    get() = RevolutionPerSecond(this)


val kotlin.Double.revolutionPerSecond
    get() = RevolutionPerSecond(this)
                            
