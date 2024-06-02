package info.leonenko.i18n.units.force

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

@Serializable(Force.Serializer::class)
interface Force : MeasurementUnit, Comparable<Force> {
    override val measurement: String get() = "force"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Force> = emptyMap()) : KSerializer<Force> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())

        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Force", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Force {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Force unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "pound-force" -> PoundForce(value)
                "newton" -> Newton(value)
                "kilowatt-hour-per-100-kilometer" -> KilowattHourPer100Kilometer(value)
                "kilogram-meter-per-square-second" -> KilogramMeterPerSquareSecond(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Force unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Force) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toPoundForce(): PoundForce {
        return PoundForce.fromKilogramMeterPerSquareSecond(toKilogramMeterPerSquareSecond())
    }

    fun toNewton(): Newton {
        return Newton.fromKilogramMeterPerSquareSecond(toKilogramMeterPerSquareSecond())
    }

    fun toKilowattHourPer100Kilometer(): KilowattHourPer100Kilometer {
        return KilowattHourPer100Kilometer.fromKilogramMeterPerSquareSecond(toKilogramMeterPerSquareSecond())
    }

    fun toKilogramMeterPerSquareSecond(): KilogramMeterPerSquareSecond
    override fun getBaseUnitValue() = toKilogramMeterPerSquareSecond().value

    companion object {
        val ZERO: Force = KilogramMeterPerSquareSecond(0.0)

    }

    override operator fun compareTo(other: Force): Int = this.toKilogramMeterPerSquareSecond().value.compareTo(other.toKilogramMeterPerSquareSecond().value)

    operator fun plus(other: Force): KilogramMeterPerSquareSecond = this.toKilogramMeterPerSquareSecond() + other.toKilogramMeterPerSquareSecond()
    operator fun minus(other: Force): KilogramMeterPerSquareSecond = this.toKilogramMeterPerSquareSecond() - other.toKilogramMeterPerSquareSecond()
    operator fun div(other: Force): Double = this.toKilogramMeterPerSquareSecond().value / other.toKilogramMeterPerSquareSecond().value
    operator fun unaryMinus(): Force
    operator fun unaryPlus(): Force
    operator fun inc(): Force
    operator fun dec(): Force

    fun round(): Force
    fun truncate(): Force
    fun floor(): Force
    fun ceil(): Force
    fun abs(): Force


    operator fun times(other: kotlin.Byte): Force
    operator fun div(other: kotlin.Byte): Force
    operator fun rem(other: kotlin.Byte): Force


    operator fun times(other: kotlin.Short): Force
    operator fun div(other: kotlin.Short): Force
    operator fun rem(other: kotlin.Short): Force


    operator fun times(other: kotlin.Int): Force
    operator fun div(other: kotlin.Int): Force
    operator fun rem(other: kotlin.Int): Force


    operator fun times(other: kotlin.Long): Force
    operator fun div(other: kotlin.Long): Force
    operator fun rem(other: kotlin.Long): Force


    operator fun times(other: kotlin.Float): Force
    operator fun div(other: kotlin.Float): Force
    operator fun rem(other: kotlin.Float): Force


    operator fun times(other: kotlin.Double): Force
    operator fun div(other: kotlin.Double): Force
    operator fun rem(other: kotlin.Double): Force

}

data class PoundForce(override val value: MeasurementUnitValue) : Force {

    override val unitName: String = "pound-force"

    override fun formatters(language: MeasurementUnitLanguage) = language.forcePoundForce
    override fun toKilogramMeterPerSquareSecond() = KilogramMeterPerSquareSecond(value * (4.4482216152605) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = PoundForce(0.0)
        fun fromKilogramMeterPerSquareSecond(value: KilogramMeterPerSquareSecond) = PoundForce((value.value - 0) / (4.4482216152605))
    }

    operator fun plus(other: PoundForce): PoundForce = PoundForce(this.value + other.value)
    operator fun minus(other: PoundForce): PoundForce = PoundForce(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): PoundForce = PoundForce(this.value.times(other))
    override operator fun div(other: kotlin.Byte): PoundForce = PoundForce(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): PoundForce = PoundForce(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): PoundForce = PoundForce(this.value.times(other))
    override operator fun div(other: kotlin.Short): PoundForce = PoundForce(this.value.div(other))
    override operator fun rem(other: kotlin.Short): PoundForce = PoundForce(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): PoundForce = PoundForce(this.value.times(other))
    override operator fun div(other: kotlin.Int): PoundForce = PoundForce(this.value.div(other))
    override operator fun rem(other: kotlin.Int): PoundForce = PoundForce(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): PoundForce = PoundForce(this.value.times(other))
    override operator fun div(other: kotlin.Long): PoundForce = PoundForce(this.value.div(other))
    override operator fun rem(other: kotlin.Long): PoundForce = PoundForce(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): PoundForce = PoundForce(this.value.times(other))
    override operator fun div(other: kotlin.Float): PoundForce = PoundForce(this.value.div(other))
    override operator fun rem(other: kotlin.Float): PoundForce = PoundForce(this.value.rem(other))


    override operator fun times(other: kotlin.Double): PoundForce = PoundForce(this.value.times(other))
    override operator fun div(other: kotlin.Double): PoundForce = PoundForce(this.value.div(other))
    override operator fun rem(other: kotlin.Double): PoundForce = PoundForce(this.value.rem(other))


    override operator fun unaryMinus(): PoundForce = PoundForce(-value)
    override operator fun unaryPlus(): PoundForce = this
    override operator fun inc(): PoundForce = PoundForce(this.value + 1)
    override operator fun dec(): PoundForce = PoundForce(this.value - 1)

    override fun round(): PoundForce = PoundForce(round(this.value))
    override fun truncate(): PoundForce = PoundForce(truncate(this.value))
    override fun floor(): PoundForce = PoundForce(floor(this.value))
    override fun ceil(): PoundForce = PoundForce(ceil(this.value))
    override fun abs(): PoundForce = PoundForce(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is PoundForce -> this.value == other.value
        is Force -> this.toKilogramMeterPerSquareSecond().value == other.toKilogramMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.poundForce
    get() = PoundForce(this)


val kotlin.Short.poundForce
    get() = PoundForce(this)


val kotlin.Int.poundForce
    get() = PoundForce(this)


val kotlin.Long.poundForce
    get() = PoundForce(this)


val kotlin.Float.poundForce
    get() = PoundForce(this)


val kotlin.Double.poundForce
    get() = PoundForce(this)


data class Newton(override val value: MeasurementUnitValue) : Force {

    override val unitName: String = "newton"

    override fun formatters(language: MeasurementUnitLanguage) = language.forceNewton
    override fun toKilogramMeterPerSquareSecond() = KilogramMeterPerSquareSecond(value * (1) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Newton(0.0)
        fun fromKilogramMeterPerSquareSecond(value: KilogramMeterPerSquareSecond) = Newton((value.value - 0) / (1))
    }

    operator fun plus(other: Newton): Newton = Newton(this.value + other.value)
    operator fun minus(other: Newton): Newton = Newton(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Newton = Newton(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Newton = Newton(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Newton = Newton(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Newton = Newton(this.value.times(other))
    override operator fun div(other: kotlin.Short): Newton = Newton(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Newton = Newton(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Newton = Newton(this.value.times(other))
    override operator fun div(other: kotlin.Int): Newton = Newton(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Newton = Newton(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Newton = Newton(this.value.times(other))
    override operator fun div(other: kotlin.Long): Newton = Newton(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Newton = Newton(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Newton = Newton(this.value.times(other))
    override operator fun div(other: kotlin.Float): Newton = Newton(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Newton = Newton(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Newton = Newton(this.value.times(other))
    override operator fun div(other: kotlin.Double): Newton = Newton(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Newton = Newton(this.value.rem(other))


    override operator fun unaryMinus(): Newton = Newton(-value)
    override operator fun unaryPlus(): Newton = this
    override operator fun inc(): Newton = Newton(this.value + 1)
    override operator fun dec(): Newton = Newton(this.value - 1)

    override fun round(): Newton = Newton(round(this.value))
    override fun truncate(): Newton = Newton(truncate(this.value))
    override fun floor(): Newton = Newton(floor(this.value))
    override fun ceil(): Newton = Newton(ceil(this.value))
    override fun abs(): Newton = Newton(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Newton -> this.value == other.value
        is Force -> this.toKilogramMeterPerSquareSecond().value == other.toKilogramMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.newton
    get() = Newton(this)


val kotlin.Short.newton
    get() = Newton(this)


val kotlin.Int.newton
    get() = Newton(this)


val kotlin.Long.newton
    get() = Newton(this)


val kotlin.Float.newton
    get() = Newton(this)


val kotlin.Double.newton
    get() = Newton(this)


data class KilowattHourPer100Kilometer(override val value: MeasurementUnitValue) : Force {

    override val unitName: String = "kilowatt-hour-per-100-kilometer"

    override fun formatters(language: MeasurementUnitLanguage) = language.forceKilowattHourPer100Kilometer
    override fun toKilogramMeterPerSquareSecond() = KilogramMeterPerSquareSecond(value * (((1 * (1e3)) * (3600)) / (100000)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = KilowattHourPer100Kilometer(0.0)
        fun fromKilogramMeterPerSquareSecond(value: KilogramMeterPerSquareSecond) = KilowattHourPer100Kilometer((value.value - 0) / (((1 * (1e3)) * (3600)) / (100000)))
    }

    operator fun plus(other: KilowattHourPer100Kilometer): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value + other.value)
    operator fun minus(other: KilowattHourPer100Kilometer): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Byte): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Short): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Short): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Int): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Int): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Long): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Long): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Float): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Float): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.rem(other))


    override operator fun times(other: kotlin.Double): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Double): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Double): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.rem(other))


    override operator fun unaryMinus(): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(-value)
    override operator fun unaryPlus(): KilowattHourPer100Kilometer = this
    override operator fun inc(): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value + 1)
    override operator fun dec(): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value - 1)

    override fun round(): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(round(this.value))
    override fun truncate(): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(truncate(this.value))
    override fun floor(): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(floor(this.value))
    override fun ceil(): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(ceil(this.value))
    override fun abs(): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is KilowattHourPer100Kilometer -> this.value == other.value
        is Force -> this.toKilogramMeterPerSquareSecond().value == other.toKilogramMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.kilowattHourPer100Kilometer
    get() = KilowattHourPer100Kilometer(this)


val kotlin.Short.kilowattHourPer100Kilometer
    get() = KilowattHourPer100Kilometer(this)


val kotlin.Int.kilowattHourPer100Kilometer
    get() = KilowattHourPer100Kilometer(this)


val kotlin.Long.kilowattHourPer100Kilometer
    get() = KilowattHourPer100Kilometer(this)


val kotlin.Float.kilowattHourPer100Kilometer
    get() = KilowattHourPer100Kilometer(this)


val kotlin.Double.kilowattHourPer100Kilometer
    get() = KilowattHourPer100Kilometer(this)


data class KilogramMeterPerSquareSecond(override val value: MeasurementUnitValue) : Force {

    override val unitName: String = "kilogram-meter-per-square-second"

    override fun formatters(language: MeasurementUnitLanguage) = language.forceKilogramMeterPerSquareSecond
    override fun toKilogramMeterPerSquareSecond() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = KilogramMeterPerSquareSecond(0.0)
        fun fromKilogramMeterPerSquareSecond(value: KilogramMeterPerSquareSecond) = value
    }

    operator fun plus(other: KilogramMeterPerSquareSecond): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value + other.value)
    operator fun minus(other: KilogramMeterPerSquareSecond): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Byte): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Short): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Short): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Int): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Int): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Long): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Long): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Float): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Float): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.rem(other))


    override operator fun times(other: kotlin.Double): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Double): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Double): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.rem(other))


    override operator fun unaryMinus(): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(-value)
    override operator fun unaryPlus(): KilogramMeterPerSquareSecond = this
    override operator fun inc(): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value + 1)
    override operator fun dec(): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value - 1)

    override fun round(): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(round(this.value))
    override fun truncate(): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(truncate(this.value))
    override fun floor(): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(floor(this.value))
    override fun ceil(): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(ceil(this.value))
    override fun abs(): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is KilogramMeterPerSquareSecond -> this.value == other.value
        is Force -> this.toKilogramMeterPerSquareSecond().value == other.toKilogramMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.kilogramMeterPerSquareSecond
    get() = KilogramMeterPerSquareSecond(this)


val kotlin.Short.kilogramMeterPerSquareSecond
    get() = KilogramMeterPerSquareSecond(this)


val kotlin.Int.kilogramMeterPerSquareSecond
    get() = KilogramMeterPerSquareSecond(this)


val kotlin.Long.kilogramMeterPerSquareSecond
    get() = KilogramMeterPerSquareSecond(this)


val kotlin.Float.kilogramMeterPerSquareSecond
    get() = KilogramMeterPerSquareSecond(this)


val kotlin.Double.kilogramMeterPerSquareSecond
    get() = KilogramMeterPerSquareSecond(this)
                            
