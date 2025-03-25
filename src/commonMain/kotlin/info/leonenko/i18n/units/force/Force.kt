package info.leonenko.i18n.units.force

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


    operator fun times(other: Byte): Force
    operator fun div(other: Byte): Force
    operator fun rem(other: Byte): Force


    operator fun times(other: Short): Force
    operator fun div(other: Short): Force
    operator fun rem(other: Short): Force


    operator fun times(other: Int): Force
    operator fun div(other: Int): Force
    operator fun rem(other: Int): Force


    operator fun times(other: Long): Force
    operator fun div(other: Long): Force
    operator fun rem(other: Long): Force


    operator fun times(other: Float): Force
    operator fun div(other: Float): Force
    operator fun rem(other: Float): Force


    operator fun times(other: Double): Force
    operator fun div(other: Double): Force
    operator fun rem(other: Double): Force

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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): PoundForce = PoundForce(this.value.times(other))
    override operator fun div(other: Byte): PoundForce = PoundForce(this.value.div(other))
    override operator fun rem(other: Byte): PoundForce = PoundForce(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): PoundForce = PoundForce(this.value.times(other))
    override operator fun div(other: Short): PoundForce = PoundForce(this.value.div(other))
    override operator fun rem(other: Short): PoundForce = PoundForce(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): PoundForce = PoundForce(this.value.times(other))
    override operator fun div(other: Int): PoundForce = PoundForce(this.value.div(other))
    override operator fun rem(other: Int): PoundForce = PoundForce(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): PoundForce = PoundForce(this.value.times(other))
    override operator fun div(other: Long): PoundForce = PoundForce(this.value.div(other))
    override operator fun rem(other: Long): PoundForce = PoundForce(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): PoundForce = PoundForce(this.value.times(other))
    override operator fun div(other: Float): PoundForce = PoundForce(this.value.div(other))
    override operator fun rem(other: Float): PoundForce = PoundForce(this.value.rem(other))


    override operator fun times(other: Double): PoundForce = PoundForce(this.value.times(other))
    override operator fun div(other: Double): PoundForce = PoundForce(this.value.div(other))
    override operator fun rem(other: Double): PoundForce = PoundForce(this.value.rem(other))


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

val Byte.poundForce
    get() = PoundForce(this)


val Short.poundForce
    get() = PoundForce(this)


val Int.poundForce
    get() = PoundForce(this)


val Long.poundForce
    get() = PoundForce(this)


val Float.poundForce
    get() = PoundForce(this)


val Double.poundForce
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Newton = Newton(this.value.times(other))
    override operator fun div(other: Byte): Newton = Newton(this.value.div(other))
    override operator fun rem(other: Byte): Newton = Newton(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Newton = Newton(this.value.times(other))
    override operator fun div(other: Short): Newton = Newton(this.value.div(other))
    override operator fun rem(other: Short): Newton = Newton(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Newton = Newton(this.value.times(other))
    override operator fun div(other: Int): Newton = Newton(this.value.div(other))
    override operator fun rem(other: Int): Newton = Newton(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Newton = Newton(this.value.times(other))
    override operator fun div(other: Long): Newton = Newton(this.value.div(other))
    override operator fun rem(other: Long): Newton = Newton(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Newton = Newton(this.value.times(other))
    override operator fun div(other: Float): Newton = Newton(this.value.div(other))
    override operator fun rem(other: Float): Newton = Newton(this.value.rem(other))


    override operator fun times(other: Double): Newton = Newton(this.value.times(other))
    override operator fun div(other: Double): Newton = Newton(this.value.div(other))
    override operator fun rem(other: Double): Newton = Newton(this.value.rem(other))


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

val Byte.newton
    get() = Newton(this)


val Short.newton
    get() = Newton(this)


val Int.newton
    get() = Newton(this)


val Long.newton
    get() = Newton(this)


val Float.newton
    get() = Newton(this)


val Double.newton
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.times(other))
    override operator fun div(other: Byte): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.div(other))
    override operator fun rem(other: Byte): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.times(other))
    override operator fun div(other: Short): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.div(other))
    override operator fun rem(other: Short): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.times(other))
    override operator fun div(other: Int): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.div(other))
    override operator fun rem(other: Int): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.times(other))
    override operator fun div(other: Long): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.div(other))
    override operator fun rem(other: Long): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.times(other))
    override operator fun div(other: Float): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.div(other))
    override operator fun rem(other: Float): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.rem(other))


    override operator fun times(other: Double): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.times(other))
    override operator fun div(other: Double): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.div(other))
    override operator fun rem(other: Double): KilowattHourPer100Kilometer = KilowattHourPer100Kilometer(this.value.rem(other))


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

val Byte.kilowattHourPer100Kilometer
    get() = KilowattHourPer100Kilometer(this)


val Short.kilowattHourPer100Kilometer
    get() = KilowattHourPer100Kilometer(this)


val Int.kilowattHourPer100Kilometer
    get() = KilowattHourPer100Kilometer(this)


val Long.kilowattHourPer100Kilometer
    get() = KilowattHourPer100Kilometer(this)


val Float.kilowattHourPer100Kilometer
    get() = KilowattHourPer100Kilometer(this)


val Double.kilowattHourPer100Kilometer
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: Byte): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: Byte): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: Short): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: Short): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: Int): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: Int): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: Long): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: Long): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: Float): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: Float): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.rem(other))


    override operator fun times(other: Double): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: Double): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: Double): KilogramMeterPerSquareSecond = KilogramMeterPerSquareSecond(this.value.rem(other))


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

val Byte.kilogramMeterPerSquareSecond
    get() = KilogramMeterPerSquareSecond(this)


val Short.kilogramMeterPerSquareSecond
    get() = KilogramMeterPerSquareSecond(this)


val Int.kilogramMeterPerSquareSecond
    get() = KilogramMeterPerSquareSecond(this)


val Long.kilogramMeterPerSquareSecond
    get() = KilogramMeterPerSquareSecond(this)


val Float.kilogramMeterPerSquareSecond
    get() = KilogramMeterPerSquareSecond(this)


val Double.kilogramMeterPerSquareSecond
    get() = KilogramMeterPerSquareSecond(this)
                            
