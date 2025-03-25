package info.leonenko.i18n.units.concentration
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

@Serializable(Portion.Serializer::class)
interface Portion : MeasurementUnit, Comparable<Portion> {
    override val measurement: String get() = "portion"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Portion> = emptyMap()) : KSerializer<Portion> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Portion", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Portion {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Portion unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "karat" -> Karat(value)
                "permillion" -> Permillion(value)
                "percent" -> Percent(value)
                "permille" -> Permille(value)
                "permyriad" -> Permyriad(value)
                "portion" -> APortion(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Portion unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Portion) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toKarat(): Karat {
        return Karat.fromAPortion(toAPortion())
    }

    fun toPermillion(): Permillion {
        return Permillion.fromAPortion(toAPortion())
    }

    fun toPercent(): Percent {
        return Percent.fromAPortion(toAPortion())
    }

    fun toPermille(): Permille {
        return Permille.fromAPortion(toAPortion())
    }

    fun toPermyriad(): Permyriad {
        return Permyriad.fromAPortion(toAPortion())
    }

    fun toAPortion(): APortion
    override fun getBaseUnitValue() = toAPortion().value

    companion object {
        val ZERO: Portion = APortion(0.0)

    }

    override operator fun compareTo(other: Portion): Int = this.toAPortion().value.compareTo(other.toAPortion().value)

    operator fun plus(other: Portion): APortion = this.toAPortion() + other.toAPortion()
    operator fun minus(other: Portion): APortion = this.toAPortion() - other.toAPortion()
    operator fun div(other: Portion): Double = this.toAPortion().value / other.toAPortion().value
    operator fun unaryMinus(): Portion
    operator fun unaryPlus(): Portion
    operator fun inc(): Portion
    operator fun dec(): Portion

    fun round(): Portion
    fun truncate(): Portion
    fun floor(): Portion
    fun ceil(): Portion
    fun abs(): Portion


    operator fun times(other: Byte): Portion
    operator fun div(other: Byte): Portion
    operator fun rem(other: Byte): Portion


    operator fun times(other: Short): Portion
    operator fun div(other: Short): Portion
    operator fun rem(other: Short): Portion


    operator fun times(other: Int): Portion
    operator fun div(other: Int): Portion
    operator fun rem(other: Int): Portion


    operator fun times(other: Long): Portion
    operator fun div(other: Long): Portion
    operator fun rem(other: Long): Portion


    operator fun times(other: Float): Portion
    operator fun div(other: Float): Portion
    operator fun rem(other: Float): Portion


    operator fun times(other: Double): Portion
    operator fun div(other: Double): Portion
    operator fun rem(other: Double): Portion

}

data class Karat(override val value: MeasurementUnitValue) : Portion {

    override val unitName: String = "karat"

    override fun formatters(language: MeasurementUnitLanguage) = language.concentrationKarat
    override fun toAPortion() = APortion(value * (0.041666666666666664) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Karat(0.0)
        fun fromAPortion(value: APortion) = Karat((value.value - 0) / (0.041666666666666664))
    }

    operator fun plus(other: Karat): Karat = Karat(this.value + other.value)
    operator fun minus(other: Karat): Karat = Karat(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Karat = Karat(this.value.times(other))
    override operator fun div(other: Byte): Karat = Karat(this.value.div(other))
    override operator fun rem(other: Byte): Karat = Karat(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Karat = Karat(this.value.times(other))
    override operator fun div(other: Short): Karat = Karat(this.value.div(other))
    override operator fun rem(other: Short): Karat = Karat(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Karat = Karat(this.value.times(other))
    override operator fun div(other: Int): Karat = Karat(this.value.div(other))
    override operator fun rem(other: Int): Karat = Karat(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Karat = Karat(this.value.times(other))
    override operator fun div(other: Long): Karat = Karat(this.value.div(other))
    override operator fun rem(other: Long): Karat = Karat(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Karat = Karat(this.value.times(other))
    override operator fun div(other: Float): Karat = Karat(this.value.div(other))
    override operator fun rem(other: Float): Karat = Karat(this.value.rem(other))


    override operator fun times(other: Double): Karat = Karat(this.value.times(other))
    override operator fun div(other: Double): Karat = Karat(this.value.div(other))
    override operator fun rem(other: Double): Karat = Karat(this.value.rem(other))


    override operator fun unaryMinus(): Karat = Karat(-value)
    override operator fun unaryPlus(): Karat = this
    override operator fun inc(): Karat = Karat(this.value + 1)
    override operator fun dec(): Karat = Karat(this.value - 1)

    override fun round(): Karat = Karat(round(this.value))
    override fun truncate(): Karat = Karat(truncate(this.value))
    override fun floor(): Karat = Karat(floor(this.value))
    override fun ceil(): Karat = Karat(ceil(this.value))
    override fun abs(): Karat = Karat(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Karat -> this.value == other.value
        is Portion -> this.toAPortion().value == other.toAPortion().value
        else -> false
    }

    override fun hashCode(): Int = this.toAPortion().value.hashCode()


}

val Byte.karat
    get() = Karat(this)


val Short.karat
    get() = Karat(this)


val Int.karat
    get() = Karat(this)


val Long.karat
    get() = Karat(this)


val Float.karat
    get() = Karat(this)


val Double.karat
    get() = Karat(this)


data class Permillion(override val value: MeasurementUnitValue) : Portion {

    override val unitName: String = "permillion"

    override fun formatters(language: MeasurementUnitLanguage) = language.concentrationPermillion
    override fun toAPortion() = APortion(value * (1e-06) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Permillion(0.0)
        fun fromAPortion(value: APortion) = Permillion((value.value - 0) / (1e-06))
    }

    operator fun plus(other: Permillion): Permillion = Permillion(this.value + other.value)
    operator fun minus(other: Permillion): Permillion = Permillion(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Permillion = Permillion(this.value.times(other))
    override operator fun div(other: Byte): Permillion = Permillion(this.value.div(other))
    override operator fun rem(other: Byte): Permillion = Permillion(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Permillion = Permillion(this.value.times(other))
    override operator fun div(other: Short): Permillion = Permillion(this.value.div(other))
    override operator fun rem(other: Short): Permillion = Permillion(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Permillion = Permillion(this.value.times(other))
    override operator fun div(other: Int): Permillion = Permillion(this.value.div(other))
    override operator fun rem(other: Int): Permillion = Permillion(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Permillion = Permillion(this.value.times(other))
    override operator fun div(other: Long): Permillion = Permillion(this.value.div(other))
    override operator fun rem(other: Long): Permillion = Permillion(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Permillion = Permillion(this.value.times(other))
    override operator fun div(other: Float): Permillion = Permillion(this.value.div(other))
    override operator fun rem(other: Float): Permillion = Permillion(this.value.rem(other))


    override operator fun times(other: Double): Permillion = Permillion(this.value.times(other))
    override operator fun div(other: Double): Permillion = Permillion(this.value.div(other))
    override operator fun rem(other: Double): Permillion = Permillion(this.value.rem(other))


    override operator fun unaryMinus(): Permillion = Permillion(-value)
    override operator fun unaryPlus(): Permillion = this
    override operator fun inc(): Permillion = Permillion(this.value + 1)
    override operator fun dec(): Permillion = Permillion(this.value - 1)

    override fun round(): Permillion = Permillion(round(this.value))
    override fun truncate(): Permillion = Permillion(truncate(this.value))
    override fun floor(): Permillion = Permillion(floor(this.value))
    override fun ceil(): Permillion = Permillion(ceil(this.value))
    override fun abs(): Permillion = Permillion(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Permillion -> this.value == other.value
        is Portion -> this.toAPortion().value == other.toAPortion().value
        else -> false
    }

    override fun hashCode(): Int = this.toAPortion().value.hashCode()


}

val Byte.permillion
    get() = Permillion(this)


val Short.permillion
    get() = Permillion(this)


val Int.permillion
    get() = Permillion(this)


val Long.permillion
    get() = Permillion(this)


val Float.permillion
    get() = Permillion(this)


val Double.permillion
    get() = Permillion(this)


data class Percent(override val value: MeasurementUnitValue) : Portion {

    override val unitName: String = "percent"

    override fun formatters(language: MeasurementUnitLanguage) = language.concentrationPercent
    override fun toAPortion() = APortion(value * (0.01) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Percent(0.0)
        fun fromAPortion(value: APortion) = Percent((value.value - 0) / (0.01))
    }

    operator fun plus(other: Percent): Percent = Percent(this.value + other.value)
    operator fun minus(other: Percent): Percent = Percent(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Percent = Percent(this.value.times(other))
    override operator fun div(other: Byte): Percent = Percent(this.value.div(other))
    override operator fun rem(other: Byte): Percent = Percent(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Percent = Percent(this.value.times(other))
    override operator fun div(other: Short): Percent = Percent(this.value.div(other))
    override operator fun rem(other: Short): Percent = Percent(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Percent = Percent(this.value.times(other))
    override operator fun div(other: Int): Percent = Percent(this.value.div(other))
    override operator fun rem(other: Int): Percent = Percent(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Percent = Percent(this.value.times(other))
    override operator fun div(other: Long): Percent = Percent(this.value.div(other))
    override operator fun rem(other: Long): Percent = Percent(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Percent = Percent(this.value.times(other))
    override operator fun div(other: Float): Percent = Percent(this.value.div(other))
    override operator fun rem(other: Float): Percent = Percent(this.value.rem(other))


    override operator fun times(other: Double): Percent = Percent(this.value.times(other))
    override operator fun div(other: Double): Percent = Percent(this.value.div(other))
    override operator fun rem(other: Double): Percent = Percent(this.value.rem(other))


    override operator fun unaryMinus(): Percent = Percent(-value)
    override operator fun unaryPlus(): Percent = this
    override operator fun inc(): Percent = Percent(this.value + 1)
    override operator fun dec(): Percent = Percent(this.value - 1)

    override fun round(): Percent = Percent(round(this.value))
    override fun truncate(): Percent = Percent(truncate(this.value))
    override fun floor(): Percent = Percent(floor(this.value))
    override fun ceil(): Percent = Percent(ceil(this.value))
    override fun abs(): Percent = Percent(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Percent -> this.value == other.value
        is Portion -> this.toAPortion().value == other.toAPortion().value
        else -> false
    }

    override fun hashCode(): Int = this.toAPortion().value.hashCode()


}

val Byte.percent
    get() = Percent(this)


val Short.percent
    get() = Percent(this)


val Int.percent
    get() = Percent(this)


val Long.percent
    get() = Percent(this)


val Float.percent
    get() = Percent(this)


val Double.percent
    get() = Percent(this)


data class Permille(override val value: MeasurementUnitValue) : Portion {

    override val unitName: String = "permille"

    override fun formatters(language: MeasurementUnitLanguage) = language.concentrationPermille
    override fun toAPortion() = APortion(value * (0.001) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Permille(0.0)
        fun fromAPortion(value: APortion) = Permille((value.value - 0) / (0.001))
    }

    operator fun plus(other: Permille): Permille = Permille(this.value + other.value)
    operator fun minus(other: Permille): Permille = Permille(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Permille = Permille(this.value.times(other))
    override operator fun div(other: Byte): Permille = Permille(this.value.div(other))
    override operator fun rem(other: Byte): Permille = Permille(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Permille = Permille(this.value.times(other))
    override operator fun div(other: Short): Permille = Permille(this.value.div(other))
    override operator fun rem(other: Short): Permille = Permille(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Permille = Permille(this.value.times(other))
    override operator fun div(other: Int): Permille = Permille(this.value.div(other))
    override operator fun rem(other: Int): Permille = Permille(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Permille = Permille(this.value.times(other))
    override operator fun div(other: Long): Permille = Permille(this.value.div(other))
    override operator fun rem(other: Long): Permille = Permille(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Permille = Permille(this.value.times(other))
    override operator fun div(other: Float): Permille = Permille(this.value.div(other))
    override operator fun rem(other: Float): Permille = Permille(this.value.rem(other))


    override operator fun times(other: Double): Permille = Permille(this.value.times(other))
    override operator fun div(other: Double): Permille = Permille(this.value.div(other))
    override operator fun rem(other: Double): Permille = Permille(this.value.rem(other))


    override operator fun unaryMinus(): Permille = Permille(-value)
    override operator fun unaryPlus(): Permille = this
    override operator fun inc(): Permille = Permille(this.value + 1)
    override operator fun dec(): Permille = Permille(this.value - 1)

    override fun round(): Permille = Permille(round(this.value))
    override fun truncate(): Permille = Permille(truncate(this.value))
    override fun floor(): Permille = Permille(floor(this.value))
    override fun ceil(): Permille = Permille(ceil(this.value))
    override fun abs(): Permille = Permille(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Permille -> this.value == other.value
        is Portion -> this.toAPortion().value == other.toAPortion().value
        else -> false
    }

    override fun hashCode(): Int = this.toAPortion().value.hashCode()


}

val Byte.permille
    get() = Permille(this)


val Short.permille
    get() = Permille(this)


val Int.permille
    get() = Permille(this)


val Long.permille
    get() = Permille(this)


val Float.permille
    get() = Permille(this)


val Double.permille
    get() = Permille(this)


data class Permyriad(override val value: MeasurementUnitValue) : Portion {

    override val unitName: String = "permyriad"

    override fun formatters(language: MeasurementUnitLanguage) = language.concentrationPermyriad
    override fun toAPortion() = APortion(value * (0.0001) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Permyriad(0.0)
        fun fromAPortion(value: APortion) = Permyriad((value.value - 0) / (0.0001))
    }

    operator fun plus(other: Permyriad): Permyriad = Permyriad(this.value + other.value)
    operator fun minus(other: Permyriad): Permyriad = Permyriad(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Permyriad = Permyriad(this.value.times(other))
    override operator fun div(other: Byte): Permyriad = Permyriad(this.value.div(other))
    override operator fun rem(other: Byte): Permyriad = Permyriad(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Permyriad = Permyriad(this.value.times(other))
    override operator fun div(other: Short): Permyriad = Permyriad(this.value.div(other))
    override operator fun rem(other: Short): Permyriad = Permyriad(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Permyriad = Permyriad(this.value.times(other))
    override operator fun div(other: Int): Permyriad = Permyriad(this.value.div(other))
    override operator fun rem(other: Int): Permyriad = Permyriad(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Permyriad = Permyriad(this.value.times(other))
    override operator fun div(other: Long): Permyriad = Permyriad(this.value.div(other))
    override operator fun rem(other: Long): Permyriad = Permyriad(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Permyriad = Permyriad(this.value.times(other))
    override operator fun div(other: Float): Permyriad = Permyriad(this.value.div(other))
    override operator fun rem(other: Float): Permyriad = Permyriad(this.value.rem(other))


    override operator fun times(other: Double): Permyriad = Permyriad(this.value.times(other))
    override operator fun div(other: Double): Permyriad = Permyriad(this.value.div(other))
    override operator fun rem(other: Double): Permyriad = Permyriad(this.value.rem(other))


    override operator fun unaryMinus(): Permyriad = Permyriad(-value)
    override operator fun unaryPlus(): Permyriad = this
    override operator fun inc(): Permyriad = Permyriad(this.value + 1)
    override operator fun dec(): Permyriad = Permyriad(this.value - 1)

    override fun round(): Permyriad = Permyriad(round(this.value))
    override fun truncate(): Permyriad = Permyriad(truncate(this.value))
    override fun floor(): Permyriad = Permyriad(floor(this.value))
    override fun ceil(): Permyriad = Permyriad(ceil(this.value))
    override fun abs(): Permyriad = Permyriad(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Permyriad -> this.value == other.value
        is Portion -> this.toAPortion().value == other.toAPortion().value
        else -> false
    }

    override fun hashCode(): Int = this.toAPortion().value.hashCode()


}

val Byte.permyriad
    get() = Permyriad(this)


val Short.permyriad
    get() = Permyriad(this)


val Int.permyriad
    get() = Permyriad(this)


val Long.permyriad
    get() = Permyriad(this)


val Float.permyriad
    get() = Permyriad(this)


val Double.permyriad
    get() = Permyriad(this)


data class APortion(override val value: MeasurementUnitValue) : Portion {

    override val unitName: String = "portion"

    override fun formatters(language: MeasurementUnitLanguage) = language.concentrationAPortion
    override fun toAPortion() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = APortion(0.0)
        fun fromAPortion(value: APortion) = value
    }

    operator fun plus(other: APortion): APortion = APortion(this.value + other.value)
    operator fun minus(other: APortion): APortion = APortion(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): APortion = APortion(this.value.times(other))
    override operator fun div(other: Byte): APortion = APortion(this.value.div(other))
    override operator fun rem(other: Byte): APortion = APortion(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): APortion = APortion(this.value.times(other))
    override operator fun div(other: Short): APortion = APortion(this.value.div(other))
    override operator fun rem(other: Short): APortion = APortion(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): APortion = APortion(this.value.times(other))
    override operator fun div(other: Int): APortion = APortion(this.value.div(other))
    override operator fun rem(other: Int): APortion = APortion(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): APortion = APortion(this.value.times(other))
    override operator fun div(other: Long): APortion = APortion(this.value.div(other))
    override operator fun rem(other: Long): APortion = APortion(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): APortion = APortion(this.value.times(other))
    override operator fun div(other: Float): APortion = APortion(this.value.div(other))
    override operator fun rem(other: Float): APortion = APortion(this.value.rem(other))


    override operator fun times(other: Double): APortion = APortion(this.value.times(other))
    override operator fun div(other: Double): APortion = APortion(this.value.div(other))
    override operator fun rem(other: Double): APortion = APortion(this.value.rem(other))


    override operator fun unaryMinus(): APortion = APortion(-value)
    override operator fun unaryPlus(): APortion = this
    override operator fun inc(): APortion = APortion(this.value + 1)
    override operator fun dec(): APortion = APortion(this.value - 1)

    override fun round(): APortion = APortion(round(this.value))
    override fun truncate(): APortion = APortion(truncate(this.value))
    override fun floor(): APortion = APortion(floor(this.value))
    override fun ceil(): APortion = APortion(ceil(this.value))
    override fun abs(): APortion = APortion(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is APortion -> this.value == other.value
        is Portion -> this.toAPortion().value == other.toAPortion().value
        else -> false
    }

    override fun hashCode(): Int = this.toAPortion().value.hashCode()


}

val Byte.aPortion
    get() = APortion(this)


val Short.aPortion
    get() = APortion(this)


val Int.aPortion
    get() = APortion(this)


val Long.aPortion
    get() = APortion(this)


val Float.aPortion
    get() = APortion(this)


val Double.aPortion
    get() = APortion(this)
                            
