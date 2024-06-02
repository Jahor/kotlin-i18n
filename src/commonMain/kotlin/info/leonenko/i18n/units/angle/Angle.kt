package info.leonenko.i18n.units.angle

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


    operator fun times(other: kotlin.Byte): Angle
    operator fun div(other: kotlin.Byte): Angle
    operator fun rem(other: kotlin.Byte): Angle


    operator fun times(other: kotlin.Short): Angle
    operator fun div(other: kotlin.Short): Angle
    operator fun rem(other: kotlin.Short): Angle


    operator fun times(other: kotlin.Int): Angle
    operator fun div(other: kotlin.Int): Angle
    operator fun rem(other: kotlin.Int): Angle


    operator fun times(other: kotlin.Long): Angle
    operator fun div(other: kotlin.Long): Angle
    operator fun rem(other: kotlin.Long): Angle


    operator fun times(other: kotlin.Float): Angle
    operator fun div(other: kotlin.Float): Angle
    operator fun rem(other: kotlin.Float): Angle


    operator fun times(other: kotlin.Double): Angle
    operator fun div(other: kotlin.Double): Angle
    operator fun rem(other: kotlin.Double): Angle

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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Revolution = Revolution(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Revolution = Revolution(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Revolution = Revolution(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Revolution = Revolution(this.value.times(other))
    override operator fun div(other: kotlin.Short): Revolution = Revolution(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Revolution = Revolution(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Revolution = Revolution(this.value.times(other))
    override operator fun div(other: kotlin.Int): Revolution = Revolution(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Revolution = Revolution(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Revolution = Revolution(this.value.times(other))
    override operator fun div(other: kotlin.Long): Revolution = Revolution(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Revolution = Revolution(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Revolution = Revolution(this.value.times(other))
    override operator fun div(other: kotlin.Float): Revolution = Revolution(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Revolution = Revolution(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Revolution = Revolution(this.value.times(other))
    override operator fun div(other: kotlin.Double): Revolution = Revolution(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Revolution = Revolution(this.value.rem(other))


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

val kotlin.Byte.revolution
    get() = Revolution(this)


val kotlin.Short.revolution
    get() = Revolution(this)


val kotlin.Int.revolution
    get() = Revolution(this)


val kotlin.Long.revolution
    get() = Revolution(this)


val kotlin.Float.revolution
    get() = Revolution(this)


val kotlin.Double.revolution
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Radian = Radian(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Radian = Radian(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Radian = Radian(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Radian = Radian(this.value.times(other))
    override operator fun div(other: kotlin.Short): Radian = Radian(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Radian = Radian(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Radian = Radian(this.value.times(other))
    override operator fun div(other: kotlin.Int): Radian = Radian(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Radian = Radian(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Radian = Radian(this.value.times(other))
    override operator fun div(other: kotlin.Long): Radian = Radian(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Radian = Radian(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Radian = Radian(this.value.times(other))
    override operator fun div(other: kotlin.Float): Radian = Radian(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Radian = Radian(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Radian = Radian(this.value.times(other))
    override operator fun div(other: kotlin.Double): Radian = Radian(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Radian = Radian(this.value.rem(other))


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

val kotlin.Byte.radian
    get() = Radian(this)


val kotlin.Short.radian
    get() = Radian(this)


val kotlin.Int.radian
    get() = Radian(this)


val kotlin.Long.radian
    get() = Radian(this)


val kotlin.Float.radian
    get() = Radian(this)


val kotlin.Double.radian
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Degree = Degree(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Degree = Degree(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Degree = Degree(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Degree = Degree(this.value.times(other))
    override operator fun div(other: kotlin.Short): Degree = Degree(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Degree = Degree(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Degree = Degree(this.value.times(other))
    override operator fun div(other: kotlin.Int): Degree = Degree(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Degree = Degree(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Degree = Degree(this.value.times(other))
    override operator fun div(other: kotlin.Long): Degree = Degree(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Degree = Degree(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Degree = Degree(this.value.times(other))
    override operator fun div(other: kotlin.Float): Degree = Degree(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Degree = Degree(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Degree = Degree(this.value.times(other))
    override operator fun div(other: kotlin.Double): Degree = Degree(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Degree = Degree(this.value.rem(other))


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

val kotlin.Byte.degree
    get() = Degree(this)


val kotlin.Short.degree
    get() = Degree(this)


val kotlin.Int.degree
    get() = Degree(this)


val kotlin.Long.degree
    get() = Degree(this)


val kotlin.Float.degree
    get() = Degree(this)


val kotlin.Double.degree
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): ArcMinute = ArcMinute(this.value.times(other))
    override operator fun div(other: kotlin.Byte): ArcMinute = ArcMinute(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): ArcMinute = ArcMinute(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): ArcMinute = ArcMinute(this.value.times(other))
    override operator fun div(other: kotlin.Short): ArcMinute = ArcMinute(this.value.div(other))
    override operator fun rem(other: kotlin.Short): ArcMinute = ArcMinute(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): ArcMinute = ArcMinute(this.value.times(other))
    override operator fun div(other: kotlin.Int): ArcMinute = ArcMinute(this.value.div(other))
    override operator fun rem(other: kotlin.Int): ArcMinute = ArcMinute(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): ArcMinute = ArcMinute(this.value.times(other))
    override operator fun div(other: kotlin.Long): ArcMinute = ArcMinute(this.value.div(other))
    override operator fun rem(other: kotlin.Long): ArcMinute = ArcMinute(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): ArcMinute = ArcMinute(this.value.times(other))
    override operator fun div(other: kotlin.Float): ArcMinute = ArcMinute(this.value.div(other))
    override operator fun rem(other: kotlin.Float): ArcMinute = ArcMinute(this.value.rem(other))


    override operator fun times(other: kotlin.Double): ArcMinute = ArcMinute(this.value.times(other))
    override operator fun div(other: kotlin.Double): ArcMinute = ArcMinute(this.value.div(other))
    override operator fun rem(other: kotlin.Double): ArcMinute = ArcMinute(this.value.rem(other))


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

val kotlin.Byte.arcMinute
    get() = ArcMinute(this)


val kotlin.Short.arcMinute
    get() = ArcMinute(this)


val kotlin.Int.arcMinute
    get() = ArcMinute(this)


val kotlin.Long.arcMinute
    get() = ArcMinute(this)


val kotlin.Float.arcMinute
    get() = ArcMinute(this)


val kotlin.Double.arcMinute
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): ArcSecond = ArcSecond(this.value.times(other))
    override operator fun div(other: kotlin.Byte): ArcSecond = ArcSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): ArcSecond = ArcSecond(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): ArcSecond = ArcSecond(this.value.times(other))
    override operator fun div(other: kotlin.Short): ArcSecond = ArcSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Short): ArcSecond = ArcSecond(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): ArcSecond = ArcSecond(this.value.times(other))
    override operator fun div(other: kotlin.Int): ArcSecond = ArcSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Int): ArcSecond = ArcSecond(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): ArcSecond = ArcSecond(this.value.times(other))
    override operator fun div(other: kotlin.Long): ArcSecond = ArcSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Long): ArcSecond = ArcSecond(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): ArcSecond = ArcSecond(this.value.times(other))
    override operator fun div(other: kotlin.Float): ArcSecond = ArcSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Float): ArcSecond = ArcSecond(this.value.rem(other))


    override operator fun times(other: kotlin.Double): ArcSecond = ArcSecond(this.value.times(other))
    override operator fun div(other: kotlin.Double): ArcSecond = ArcSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Double): ArcSecond = ArcSecond(this.value.rem(other))


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

val kotlin.Byte.arcSecond
    get() = ArcSecond(this)


val kotlin.Short.arcSecond
    get() = ArcSecond(this)


val kotlin.Int.arcSecond
    get() = ArcSecond(this)


val kotlin.Long.arcSecond
    get() = ArcSecond(this)


val kotlin.Float.arcSecond
    get() = ArcSecond(this)


val kotlin.Double.arcSecond
    get() = ArcSecond(this)
                            
