package info.leonenko.i18n.units.concentration

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


    operator fun times(other: kotlin.Byte): Portion
    operator fun div(other: kotlin.Byte): Portion
    operator fun rem(other: kotlin.Byte): Portion


    operator fun times(other: kotlin.Short): Portion
    operator fun div(other: kotlin.Short): Portion
    operator fun rem(other: kotlin.Short): Portion


    operator fun times(other: kotlin.Int): Portion
    operator fun div(other: kotlin.Int): Portion
    operator fun rem(other: kotlin.Int): Portion


    operator fun times(other: kotlin.Long): Portion
    operator fun div(other: kotlin.Long): Portion
    operator fun rem(other: kotlin.Long): Portion


    operator fun times(other: kotlin.Float): Portion
    operator fun div(other: kotlin.Float): Portion
    operator fun rem(other: kotlin.Float): Portion


    operator fun times(other: kotlin.Double): Portion
    operator fun div(other: kotlin.Double): Portion
    operator fun rem(other: kotlin.Double): Portion

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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Karat = Karat(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Karat = Karat(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Karat = Karat(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Karat = Karat(this.value.times(other))
    override operator fun div(other: kotlin.Short): Karat = Karat(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Karat = Karat(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Karat = Karat(this.value.times(other))
    override operator fun div(other: kotlin.Int): Karat = Karat(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Karat = Karat(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Karat = Karat(this.value.times(other))
    override operator fun div(other: kotlin.Long): Karat = Karat(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Karat = Karat(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Karat = Karat(this.value.times(other))
    override operator fun div(other: kotlin.Float): Karat = Karat(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Karat = Karat(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Karat = Karat(this.value.times(other))
    override operator fun div(other: kotlin.Double): Karat = Karat(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Karat = Karat(this.value.rem(other))


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

val kotlin.Byte.karat
    get() = Karat(this)


val kotlin.Short.karat
    get() = Karat(this)


val kotlin.Int.karat
    get() = Karat(this)


val kotlin.Long.karat
    get() = Karat(this)


val kotlin.Float.karat
    get() = Karat(this)


val kotlin.Double.karat
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Permillion = Permillion(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Permillion = Permillion(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Permillion = Permillion(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Permillion = Permillion(this.value.times(other))
    override operator fun div(other: kotlin.Short): Permillion = Permillion(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Permillion = Permillion(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Permillion = Permillion(this.value.times(other))
    override operator fun div(other: kotlin.Int): Permillion = Permillion(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Permillion = Permillion(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Permillion = Permillion(this.value.times(other))
    override operator fun div(other: kotlin.Long): Permillion = Permillion(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Permillion = Permillion(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Permillion = Permillion(this.value.times(other))
    override operator fun div(other: kotlin.Float): Permillion = Permillion(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Permillion = Permillion(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Permillion = Permillion(this.value.times(other))
    override operator fun div(other: kotlin.Double): Permillion = Permillion(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Permillion = Permillion(this.value.rem(other))


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

val kotlin.Byte.permillion
    get() = Permillion(this)


val kotlin.Short.permillion
    get() = Permillion(this)


val kotlin.Int.permillion
    get() = Permillion(this)


val kotlin.Long.permillion
    get() = Permillion(this)


val kotlin.Float.permillion
    get() = Permillion(this)


val kotlin.Double.permillion
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Percent = Percent(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Percent = Percent(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Percent = Percent(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Percent = Percent(this.value.times(other))
    override operator fun div(other: kotlin.Short): Percent = Percent(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Percent = Percent(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Percent = Percent(this.value.times(other))
    override operator fun div(other: kotlin.Int): Percent = Percent(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Percent = Percent(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Percent = Percent(this.value.times(other))
    override operator fun div(other: kotlin.Long): Percent = Percent(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Percent = Percent(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Percent = Percent(this.value.times(other))
    override operator fun div(other: kotlin.Float): Percent = Percent(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Percent = Percent(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Percent = Percent(this.value.times(other))
    override operator fun div(other: kotlin.Double): Percent = Percent(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Percent = Percent(this.value.rem(other))


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

val kotlin.Byte.percent
    get() = Percent(this)


val kotlin.Short.percent
    get() = Percent(this)


val kotlin.Int.percent
    get() = Percent(this)


val kotlin.Long.percent
    get() = Percent(this)


val kotlin.Float.percent
    get() = Percent(this)


val kotlin.Double.percent
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Permille = Permille(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Permille = Permille(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Permille = Permille(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Permille = Permille(this.value.times(other))
    override operator fun div(other: kotlin.Short): Permille = Permille(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Permille = Permille(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Permille = Permille(this.value.times(other))
    override operator fun div(other: kotlin.Int): Permille = Permille(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Permille = Permille(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Permille = Permille(this.value.times(other))
    override operator fun div(other: kotlin.Long): Permille = Permille(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Permille = Permille(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Permille = Permille(this.value.times(other))
    override operator fun div(other: kotlin.Float): Permille = Permille(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Permille = Permille(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Permille = Permille(this.value.times(other))
    override operator fun div(other: kotlin.Double): Permille = Permille(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Permille = Permille(this.value.rem(other))


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

val kotlin.Byte.permille
    get() = Permille(this)


val kotlin.Short.permille
    get() = Permille(this)


val kotlin.Int.permille
    get() = Permille(this)


val kotlin.Long.permille
    get() = Permille(this)


val kotlin.Float.permille
    get() = Permille(this)


val kotlin.Double.permille
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Permyriad = Permyriad(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Permyriad = Permyriad(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Permyriad = Permyriad(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Permyriad = Permyriad(this.value.times(other))
    override operator fun div(other: kotlin.Short): Permyriad = Permyriad(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Permyriad = Permyriad(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Permyriad = Permyriad(this.value.times(other))
    override operator fun div(other: kotlin.Int): Permyriad = Permyriad(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Permyriad = Permyriad(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Permyriad = Permyriad(this.value.times(other))
    override operator fun div(other: kotlin.Long): Permyriad = Permyriad(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Permyriad = Permyriad(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Permyriad = Permyriad(this.value.times(other))
    override operator fun div(other: kotlin.Float): Permyriad = Permyriad(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Permyriad = Permyriad(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Permyriad = Permyriad(this.value.times(other))
    override operator fun div(other: kotlin.Double): Permyriad = Permyriad(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Permyriad = Permyriad(this.value.rem(other))


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

val kotlin.Byte.permyriad
    get() = Permyriad(this)


val kotlin.Short.permyriad
    get() = Permyriad(this)


val kotlin.Int.permyriad
    get() = Permyriad(this)


val kotlin.Long.permyriad
    get() = Permyriad(this)


val kotlin.Float.permyriad
    get() = Permyriad(this)


val kotlin.Double.permyriad
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): APortion = APortion(this.value.times(other))
    override operator fun div(other: kotlin.Byte): APortion = APortion(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): APortion = APortion(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): APortion = APortion(this.value.times(other))
    override operator fun div(other: kotlin.Short): APortion = APortion(this.value.div(other))
    override operator fun rem(other: kotlin.Short): APortion = APortion(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): APortion = APortion(this.value.times(other))
    override operator fun div(other: kotlin.Int): APortion = APortion(this.value.div(other))
    override operator fun rem(other: kotlin.Int): APortion = APortion(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): APortion = APortion(this.value.times(other))
    override operator fun div(other: kotlin.Long): APortion = APortion(this.value.div(other))
    override operator fun rem(other: kotlin.Long): APortion = APortion(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): APortion = APortion(this.value.times(other))
    override operator fun div(other: kotlin.Float): APortion = APortion(this.value.div(other))
    override operator fun rem(other: kotlin.Float): APortion = APortion(this.value.rem(other))


    override operator fun times(other: kotlin.Double): APortion = APortion(this.value.times(other))
    override operator fun div(other: kotlin.Double): APortion = APortion(this.value.div(other))
    override operator fun rem(other: kotlin.Double): APortion = APortion(this.value.rem(other))


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

val kotlin.Byte.aPortion
    get() = APortion(this)


val kotlin.Short.aPortion
    get() = APortion(this)


val kotlin.Int.aPortion
    get() = APortion(this)


val kotlin.Long.aPortion
    get() = APortion(this)


val kotlin.Float.aPortion
    get() = APortion(this)


val kotlin.Double.aPortion
    get() = APortion(this)
                            
