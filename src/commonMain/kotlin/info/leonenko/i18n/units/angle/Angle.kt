package info.leonenko.i18n.units.angle

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

@Serializable(Angle.Serializer::class)
interface Angle : MeasurementUnit, Comparable<Angle> {
    override val measurement: String get() = "angle"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Angle> = emptyMap()) : KSerializer<Angle> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Angle", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Angle {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Angle unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "revolution" -> Revolution(value)
                "radian" -> Radian(value)
                "degree" -> Degree(value)
                "arc-minute" -> ArcMinute(value)
                "arc-second" -> ArcSecond(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Angle unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Angle) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toRevolution(): Revolution
    override fun getBaseUnitValue() = toRevolution().value
    fun toRadian(): Radian {
        return Radian.fromRevolution(toRevolution())
    }

    fun toDegree(): Degree {
        return Degree.fromRevolution(toRevolution())
    }

    fun toArcMinute(): ArcMinute {
        return ArcMinute.fromRevolution(toRevolution())
    }

    fun toArcSecond(): ArcSecond {
        return ArcSecond.fromRevolution(toRevolution())
    }

    companion object {
        val ZERO: Angle = Revolution(0.0)

    }

    override operator fun compareTo(other: Angle): Int = this.toRevolution().value.compareTo(other.toRevolution().value)

    operator fun plus(other: Angle): Revolution = this.toRevolution() + other.toRevolution()
    operator fun minus(other: Angle): Revolution = this.toRevolution() - other.toRevolution()
    operator fun div(other: Angle): Double = this.toRevolution().value / other.toRevolution().value
    operator fun unaryMinus(): Angle
    operator fun unaryPlus(): Angle
    operator fun inc(): Angle
    operator fun dec(): Angle

    fun round(): Angle
    fun truncate(): Angle
    fun floor(): Angle
    fun ceil(): Angle
    fun abs(): Angle


    operator fun times(other: Byte): Angle
    operator fun div(other: Byte): Angle
    operator fun rem(other: Byte): Angle


    operator fun times(other: Short): Angle
    operator fun div(other: Short): Angle
    operator fun rem(other: Short): Angle


    operator fun times(other: Int): Angle
    operator fun div(other: Int): Angle
    operator fun rem(other: Int): Angle


    operator fun times(other: Long): Angle
    operator fun div(other: Long): Angle
    operator fun rem(other: Long): Angle


    operator fun times(other: Float): Angle
    operator fun div(other: Float): Angle
    operator fun rem(other: Float): Angle


    operator fun times(other: Double): Angle
    operator fun div(other: Double): Angle
    operator fun rem(other: Double): Angle

}

data class Revolution(override val value: MeasurementUnitValue) : Angle {

    override val unitName: String = "revolution"

    override fun formatters(language: MeasurementUnitLanguage) = language.angleRevolution
    override fun toRevolution() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Revolution(0.0)
        fun fromRevolution(value: Revolution) = value
    }

    operator fun plus(other: Revolution): Revolution = Revolution(this.value + other.value)
    operator fun minus(other: Revolution): Revolution = Revolution(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Revolution = Revolution(this.value.times(other))
    override operator fun div(other: Byte): Revolution = Revolution(this.value.div(other))
    override operator fun rem(other: Byte): Revolution = Revolution(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Revolution = Revolution(this.value.times(other))
    override operator fun div(other: Short): Revolution = Revolution(this.value.div(other))
    override operator fun rem(other: Short): Revolution = Revolution(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Revolution = Revolution(this.value.times(other))
    override operator fun div(other: Int): Revolution = Revolution(this.value.div(other))
    override operator fun rem(other: Int): Revolution = Revolution(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Revolution = Revolution(this.value.times(other))
    override operator fun div(other: Long): Revolution = Revolution(this.value.div(other))
    override operator fun rem(other: Long): Revolution = Revolution(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Revolution = Revolution(this.value.times(other))
    override operator fun div(other: Float): Revolution = Revolution(this.value.div(other))
    override operator fun rem(other: Float): Revolution = Revolution(this.value.rem(other))


    override operator fun times(other: Double): Revolution = Revolution(this.value.times(other))
    override operator fun div(other: Double): Revolution = Revolution(this.value.div(other))
    override operator fun rem(other: Double): Revolution = Revolution(this.value.rem(other))


    override operator fun unaryMinus(): Revolution = Revolution(-value)
    override operator fun unaryPlus(): Revolution = this
    override operator fun inc(): Revolution = Revolution(this.value + 1)
    override operator fun dec(): Revolution = Revolution(this.value - 1)

    override fun round(): Revolution = Revolution(round(this.value))
    override fun truncate(): Revolution = Revolution(truncate(this.value))
    override fun floor(): Revolution = Revolution(floor(this.value))
    override fun ceil(): Revolution = Revolution(ceil(this.value))
    override fun abs(): Revolution = Revolution(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Revolution -> this.value == other.value
        is Angle -> this.toRevolution().value == other.toRevolution().value
        else -> false
    }

    override fun hashCode(): Int = this.toRevolution().value.hashCode()


}

val Byte.revolution
    get() = Revolution(this)


val Short.revolution
    get() = Revolution(this)


val Int.revolution
    get() = Revolution(this)


val Long.revolution
    get() = Revolution(this)


val Float.revolution
    get() = Revolution(this)


val Double.revolution
    get() = Revolution(this)


data class Radian(override val value: MeasurementUnitValue) : Angle {

    override val unitName: String = "radian"

    override fun formatters(language: MeasurementUnitLanguage) = language.angleRadian
    override fun toRevolution() = Revolution(value * (0.15915494309189535) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Radian(0.0)
        fun fromRevolution(value: Revolution) = Radian((value.value - 0) / (0.15915494309189535))
    }

    operator fun plus(other: Radian): Radian = Radian(this.value + other.value)
    operator fun minus(other: Radian): Radian = Radian(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Radian = Radian(this.value.times(other))
    override operator fun div(other: Byte): Radian = Radian(this.value.div(other))
    override operator fun rem(other: Byte): Radian = Radian(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Radian = Radian(this.value.times(other))
    override operator fun div(other: Short): Radian = Radian(this.value.div(other))
    override operator fun rem(other: Short): Radian = Radian(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Radian = Radian(this.value.times(other))
    override operator fun div(other: Int): Radian = Radian(this.value.div(other))
    override operator fun rem(other: Int): Radian = Radian(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Radian = Radian(this.value.times(other))
    override operator fun div(other: Long): Radian = Radian(this.value.div(other))
    override operator fun rem(other: Long): Radian = Radian(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Radian = Radian(this.value.times(other))
    override operator fun div(other: Float): Radian = Radian(this.value.div(other))
    override operator fun rem(other: Float): Radian = Radian(this.value.rem(other))


    override operator fun times(other: Double): Radian = Radian(this.value.times(other))
    override operator fun div(other: Double): Radian = Radian(this.value.div(other))
    override operator fun rem(other: Double): Radian = Radian(this.value.rem(other))


    override operator fun unaryMinus(): Radian = Radian(-value)
    override operator fun unaryPlus(): Radian = this
    override operator fun inc(): Radian = Radian(this.value + 1)
    override operator fun dec(): Radian = Radian(this.value - 1)

    override fun round(): Radian = Radian(round(this.value))
    override fun truncate(): Radian = Radian(truncate(this.value))
    override fun floor(): Radian = Radian(floor(this.value))
    override fun ceil(): Radian = Radian(ceil(this.value))
    override fun abs(): Radian = Radian(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Radian -> this.value == other.value
        is Angle -> this.toRevolution().value == other.toRevolution().value
        else -> false
    }

    override fun hashCode(): Int = this.toRevolution().value.hashCode()


}

val Byte.radian
    get() = Radian(this)


val Short.radian
    get() = Radian(this)


val Int.radian
    get() = Radian(this)


val Long.radian
    get() = Radian(this)


val Float.radian
    get() = Radian(this)


val Double.radian
    get() = Radian(this)


data class Degree(override val value: MeasurementUnitValue) : Angle {

    override val unitName: String = "degree"

    override fun formatters(language: MeasurementUnitLanguage) = language.angleDegree
    override fun toRevolution() = Revolution(value * (0.002777777777777778) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Degree(0.0)
        fun fromRevolution(value: Revolution) = Degree((value.value - 0) / (0.002777777777777778))
    }

    operator fun plus(other: Degree): Degree = Degree(this.value + other.value)
    operator fun minus(other: Degree): Degree = Degree(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Degree = Degree(this.value.times(other))
    override operator fun div(other: Byte): Degree = Degree(this.value.div(other))
    override operator fun rem(other: Byte): Degree = Degree(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Degree = Degree(this.value.times(other))
    override operator fun div(other: Short): Degree = Degree(this.value.div(other))
    override operator fun rem(other: Short): Degree = Degree(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Degree = Degree(this.value.times(other))
    override operator fun div(other: Int): Degree = Degree(this.value.div(other))
    override operator fun rem(other: Int): Degree = Degree(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Degree = Degree(this.value.times(other))
    override operator fun div(other: Long): Degree = Degree(this.value.div(other))
    override operator fun rem(other: Long): Degree = Degree(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Degree = Degree(this.value.times(other))
    override operator fun div(other: Float): Degree = Degree(this.value.div(other))
    override operator fun rem(other: Float): Degree = Degree(this.value.rem(other))


    override operator fun times(other: Double): Degree = Degree(this.value.times(other))
    override operator fun div(other: Double): Degree = Degree(this.value.div(other))
    override operator fun rem(other: Double): Degree = Degree(this.value.rem(other))


    override operator fun unaryMinus(): Degree = Degree(-value)
    override operator fun unaryPlus(): Degree = this
    override operator fun inc(): Degree = Degree(this.value + 1)
    override operator fun dec(): Degree = Degree(this.value - 1)

    override fun round(): Degree = Degree(round(this.value))
    override fun truncate(): Degree = Degree(truncate(this.value))
    override fun floor(): Degree = Degree(floor(this.value))
    override fun ceil(): Degree = Degree(ceil(this.value))
    override fun abs(): Degree = Degree(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Degree -> this.value == other.value
        is Angle -> this.toRevolution().value == other.toRevolution().value
        else -> false
    }

    override fun hashCode(): Int = this.toRevolution().value.hashCode()


}

val Byte.degree
    get() = Degree(this)


val Short.degree
    get() = Degree(this)


val Int.degree
    get() = Degree(this)


val Long.degree
    get() = Degree(this)


val Float.degree
    get() = Degree(this)


val Double.degree
    get() = Degree(this)


data class ArcMinute(override val value: MeasurementUnitValue) : Angle {

    override val unitName: String = "arc-minute"

    override fun formatters(language: MeasurementUnitLanguage) = language.angleArcMinute
    override fun toRevolution() = Revolution(value * (4.6296296296296294e-05) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = ArcMinute(0.0)
        fun fromRevolution(value: Revolution) = ArcMinute((value.value - 0) / (4.6296296296296294e-05))
    }

    operator fun plus(other: ArcMinute): ArcMinute = ArcMinute(this.value + other.value)
    operator fun minus(other: ArcMinute): ArcMinute = ArcMinute(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): ArcMinute = ArcMinute(this.value.times(other))
    override operator fun div(other: Byte): ArcMinute = ArcMinute(this.value.div(other))
    override operator fun rem(other: Byte): ArcMinute = ArcMinute(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): ArcMinute = ArcMinute(this.value.times(other))
    override operator fun div(other: Short): ArcMinute = ArcMinute(this.value.div(other))
    override operator fun rem(other: Short): ArcMinute = ArcMinute(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): ArcMinute = ArcMinute(this.value.times(other))
    override operator fun div(other: Int): ArcMinute = ArcMinute(this.value.div(other))
    override operator fun rem(other: Int): ArcMinute = ArcMinute(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): ArcMinute = ArcMinute(this.value.times(other))
    override operator fun div(other: Long): ArcMinute = ArcMinute(this.value.div(other))
    override operator fun rem(other: Long): ArcMinute = ArcMinute(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): ArcMinute = ArcMinute(this.value.times(other))
    override operator fun div(other: Float): ArcMinute = ArcMinute(this.value.div(other))
    override operator fun rem(other: Float): ArcMinute = ArcMinute(this.value.rem(other))


    override operator fun times(other: Double): ArcMinute = ArcMinute(this.value.times(other))
    override operator fun div(other: Double): ArcMinute = ArcMinute(this.value.div(other))
    override operator fun rem(other: Double): ArcMinute = ArcMinute(this.value.rem(other))


    override operator fun unaryMinus(): ArcMinute = ArcMinute(-value)
    override operator fun unaryPlus(): ArcMinute = this
    override operator fun inc(): ArcMinute = ArcMinute(this.value + 1)
    override operator fun dec(): ArcMinute = ArcMinute(this.value - 1)

    override fun round(): ArcMinute = ArcMinute(round(this.value))
    override fun truncate(): ArcMinute = ArcMinute(truncate(this.value))
    override fun floor(): ArcMinute = ArcMinute(floor(this.value))
    override fun ceil(): ArcMinute = ArcMinute(ceil(this.value))
    override fun abs(): ArcMinute = ArcMinute(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is ArcMinute -> this.value == other.value
        is Angle -> this.toRevolution().value == other.toRevolution().value
        else -> false
    }

    override fun hashCode(): Int = this.toRevolution().value.hashCode()


}

val Byte.arcMinute
    get() = ArcMinute(this)


val Short.arcMinute
    get() = ArcMinute(this)


val Int.arcMinute
    get() = ArcMinute(this)


val Long.arcMinute
    get() = ArcMinute(this)


val Float.arcMinute
    get() = ArcMinute(this)


val Double.arcMinute
    get() = ArcMinute(this)


data class ArcSecond(override val value: MeasurementUnitValue) : Angle {

    override val unitName: String = "arc-second"

    override fun formatters(language: MeasurementUnitLanguage) = language.angleArcSecond
    override fun toRevolution() = Revolution(value * (7.716049382716049e-07) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = ArcSecond(0.0)
        fun fromRevolution(value: Revolution) = ArcSecond((value.value - 0) / (7.716049382716049e-07))
    }

    operator fun plus(other: ArcSecond): ArcSecond = ArcSecond(this.value + other.value)
    operator fun minus(other: ArcSecond): ArcSecond = ArcSecond(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): ArcSecond = ArcSecond(this.value.times(other))
    override operator fun div(other: Byte): ArcSecond = ArcSecond(this.value.div(other))
    override operator fun rem(other: Byte): ArcSecond = ArcSecond(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): ArcSecond = ArcSecond(this.value.times(other))
    override operator fun div(other: Short): ArcSecond = ArcSecond(this.value.div(other))
    override operator fun rem(other: Short): ArcSecond = ArcSecond(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): ArcSecond = ArcSecond(this.value.times(other))
    override operator fun div(other: Int): ArcSecond = ArcSecond(this.value.div(other))
    override operator fun rem(other: Int): ArcSecond = ArcSecond(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): ArcSecond = ArcSecond(this.value.times(other))
    override operator fun div(other: Long): ArcSecond = ArcSecond(this.value.div(other))
    override operator fun rem(other: Long): ArcSecond = ArcSecond(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): ArcSecond = ArcSecond(this.value.times(other))
    override operator fun div(other: Float): ArcSecond = ArcSecond(this.value.div(other))
    override operator fun rem(other: Float): ArcSecond = ArcSecond(this.value.rem(other))


    override operator fun times(other: Double): ArcSecond = ArcSecond(this.value.times(other))
    override operator fun div(other: Double): ArcSecond = ArcSecond(this.value.div(other))
    override operator fun rem(other: Double): ArcSecond = ArcSecond(this.value.rem(other))


    override operator fun unaryMinus(): ArcSecond = ArcSecond(-value)
    override operator fun unaryPlus(): ArcSecond = this
    override operator fun inc(): ArcSecond = ArcSecond(this.value + 1)
    override operator fun dec(): ArcSecond = ArcSecond(this.value - 1)

    override fun round(): ArcSecond = ArcSecond(round(this.value))
    override fun truncate(): ArcSecond = ArcSecond(truncate(this.value))
    override fun floor(): ArcSecond = ArcSecond(floor(this.value))
    override fun ceil(): ArcSecond = ArcSecond(ceil(this.value))
    override fun abs(): ArcSecond = ArcSecond(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is ArcSecond -> this.value == other.value
        is Angle -> this.toRevolution().value == other.toRevolution().value
        else -> false
    }

    override fun hashCode(): Int = this.toRevolution().value.hashCode()


}

val Byte.arcSecond
    get() = ArcSecond(this)


val Short.arcSecond
    get() = ArcSecond(this)


val Int.arcSecond
    get() = ArcSecond(this)


val Long.arcSecond
    get() = ArcSecond(this)


val Float.arcSecond
    get() = ArcSecond(this)


val Double.arcSecond
    get() = ArcSecond(this)
                            
