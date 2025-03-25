package info.leonenko.i18n.units.graphics

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

@Serializable(Resolution.Serializer::class)
interface Resolution : MeasurementUnit, Comparable<Resolution> {
    override val measurement: String get() = "resolution"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Resolution> = emptyMap()) : KSerializer<Resolution> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Resolution", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Resolution {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Resolution unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "pixel-per-centimeter" -> PixelPerCentimeter(value)
                "pixel-per-inch" -> PixelPerInch(value)
                "dot-per-centimeter" -> DotPerCentimeter(value)
                "dot-per-inch" -> DotPerInch(value)
                "pixel-per-meter" -> PixelPerMeter(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Resolution unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Resolution) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toPixelPerCentimeter(): PixelPerCentimeter {
        return PixelPerCentimeter.fromPixelPerMeter(toPixelPerMeter())
    }

    fun toPixelPerInch(): PixelPerInch {
        return PixelPerInch.fromPixelPerMeter(toPixelPerMeter())
    }

    fun toDotPerCentimeter(): DotPerCentimeter {
        return DotPerCentimeter.fromPixelPerMeter(toPixelPerMeter())
    }

    fun toDotPerInch(): DotPerInch {
        return DotPerInch.fromPixelPerMeter(toPixelPerMeter())
    }

    fun toPixelPerMeter(): PixelPerMeter
    override fun getBaseUnitValue() = toPixelPerMeter().value

    companion object {
        val ZERO: Resolution = PixelPerMeter(0.0)

    }

    override operator fun compareTo(other: Resolution): Int = this.toPixelPerMeter().value.compareTo(other.toPixelPerMeter().value)

    operator fun plus(other: Resolution): PixelPerMeter = this.toPixelPerMeter() + other.toPixelPerMeter()
    operator fun minus(other: Resolution): PixelPerMeter = this.toPixelPerMeter() - other.toPixelPerMeter()
    operator fun div(other: Resolution): Double = this.toPixelPerMeter().value / other.toPixelPerMeter().value
    operator fun unaryMinus(): Resolution
    operator fun unaryPlus(): Resolution
    operator fun inc(): Resolution
    operator fun dec(): Resolution

    fun round(): Resolution
    fun truncate(): Resolution
    fun floor(): Resolution
    fun ceil(): Resolution
    fun abs(): Resolution


    operator fun times(other: Byte): Resolution
    operator fun div(other: Byte): Resolution
    operator fun rem(other: Byte): Resolution


    operator fun times(other: Short): Resolution
    operator fun div(other: Short): Resolution
    operator fun rem(other: Short): Resolution


    operator fun times(other: Int): Resolution
    operator fun div(other: Int): Resolution
    operator fun rem(other: Int): Resolution


    operator fun times(other: Long): Resolution
    operator fun div(other: Long): Resolution
    operator fun rem(other: Long): Resolution


    operator fun times(other: Float): Resolution
    operator fun div(other: Float): Resolution
    operator fun rem(other: Float): Resolution


    operator fun times(other: Double): Resolution
    operator fun div(other: Double): Resolution
    operator fun rem(other: Double): Resolution

}

data class PixelPerCentimeter(override val value: MeasurementUnitValue) : Resolution {

    override val unitName: String = "pixel-per-centimeter"

    override fun formatters(language: MeasurementUnitLanguage) = language.graphicsPixelPerCentimeter
    override fun toPixelPerMeter() = PixelPerMeter(value * ((1) / (1 * (1e-2))) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = PixelPerCentimeter(0.0)
        fun fromPixelPerMeter(value: PixelPerMeter) = PixelPerCentimeter((value.value - 0) / ((1) / (1 * (1e-2))))
    }

    operator fun plus(other: PixelPerCentimeter): PixelPerCentimeter = PixelPerCentimeter(this.value + other.value)
    operator fun minus(other: PixelPerCentimeter): PixelPerCentimeter = PixelPerCentimeter(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): PixelPerCentimeter = PixelPerCentimeter(this.value.times(other))
    override operator fun div(other: Byte): PixelPerCentimeter = PixelPerCentimeter(this.value.div(other))
    override operator fun rem(other: Byte): PixelPerCentimeter = PixelPerCentimeter(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): PixelPerCentimeter = PixelPerCentimeter(this.value.times(other))
    override operator fun div(other: Short): PixelPerCentimeter = PixelPerCentimeter(this.value.div(other))
    override operator fun rem(other: Short): PixelPerCentimeter = PixelPerCentimeter(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): PixelPerCentimeter = PixelPerCentimeter(this.value.times(other))
    override operator fun div(other: Int): PixelPerCentimeter = PixelPerCentimeter(this.value.div(other))
    override operator fun rem(other: Int): PixelPerCentimeter = PixelPerCentimeter(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): PixelPerCentimeter = PixelPerCentimeter(this.value.times(other))
    override operator fun div(other: Long): PixelPerCentimeter = PixelPerCentimeter(this.value.div(other))
    override operator fun rem(other: Long): PixelPerCentimeter = PixelPerCentimeter(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): PixelPerCentimeter = PixelPerCentimeter(this.value.times(other))
    override operator fun div(other: Float): PixelPerCentimeter = PixelPerCentimeter(this.value.div(other))
    override operator fun rem(other: Float): PixelPerCentimeter = PixelPerCentimeter(this.value.rem(other))


    override operator fun times(other: Double): PixelPerCentimeter = PixelPerCentimeter(this.value.times(other))
    override operator fun div(other: Double): PixelPerCentimeter = PixelPerCentimeter(this.value.div(other))
    override operator fun rem(other: Double): PixelPerCentimeter = PixelPerCentimeter(this.value.rem(other))


    override operator fun unaryMinus(): PixelPerCentimeter = PixelPerCentimeter(-value)
    override operator fun unaryPlus(): PixelPerCentimeter = this
    override operator fun inc(): PixelPerCentimeter = PixelPerCentimeter(this.value + 1)
    override operator fun dec(): PixelPerCentimeter = PixelPerCentimeter(this.value - 1)

    override fun round(): PixelPerCentimeter = PixelPerCentimeter(round(this.value))
    override fun truncate(): PixelPerCentimeter = PixelPerCentimeter(truncate(this.value))
    override fun floor(): PixelPerCentimeter = PixelPerCentimeter(floor(this.value))
    override fun ceil(): PixelPerCentimeter = PixelPerCentimeter(ceil(this.value))
    override fun abs(): PixelPerCentimeter = PixelPerCentimeter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is PixelPerCentimeter -> this.value == other.value
        is Resolution -> this.toPixelPerMeter().value == other.toPixelPerMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toPixelPerMeter().value.hashCode()


}

val Byte.pixelPerCentimeter
    get() = PixelPerCentimeter(this)


val Short.pixelPerCentimeter
    get() = PixelPerCentimeter(this)


val Int.pixelPerCentimeter
    get() = PixelPerCentimeter(this)


val Long.pixelPerCentimeter
    get() = PixelPerCentimeter(this)


val Float.pixelPerCentimeter
    get() = PixelPerCentimeter(this)


val Double.pixelPerCentimeter
    get() = PixelPerCentimeter(this)


data class PixelPerInch(override val value: MeasurementUnitValue) : Resolution {

    override val unitName: String = "pixel-per-inch"

    override fun formatters(language: MeasurementUnitLanguage) = language.graphicsPixelPerInch
    override fun toPixelPerMeter() = PixelPerMeter(value * ((1) / (0.025400000000000002)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = PixelPerInch(0.0)
        fun fromPixelPerMeter(value: PixelPerMeter) = PixelPerInch((value.value - 0) / ((1) / (0.025400000000000002)))
    }

    operator fun plus(other: PixelPerInch): PixelPerInch = PixelPerInch(this.value + other.value)
    operator fun minus(other: PixelPerInch): PixelPerInch = PixelPerInch(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): PixelPerInch = PixelPerInch(this.value.times(other))
    override operator fun div(other: Byte): PixelPerInch = PixelPerInch(this.value.div(other))
    override operator fun rem(other: Byte): PixelPerInch = PixelPerInch(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): PixelPerInch = PixelPerInch(this.value.times(other))
    override operator fun div(other: Short): PixelPerInch = PixelPerInch(this.value.div(other))
    override operator fun rem(other: Short): PixelPerInch = PixelPerInch(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): PixelPerInch = PixelPerInch(this.value.times(other))
    override operator fun div(other: Int): PixelPerInch = PixelPerInch(this.value.div(other))
    override operator fun rem(other: Int): PixelPerInch = PixelPerInch(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): PixelPerInch = PixelPerInch(this.value.times(other))
    override operator fun div(other: Long): PixelPerInch = PixelPerInch(this.value.div(other))
    override operator fun rem(other: Long): PixelPerInch = PixelPerInch(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): PixelPerInch = PixelPerInch(this.value.times(other))
    override operator fun div(other: Float): PixelPerInch = PixelPerInch(this.value.div(other))
    override operator fun rem(other: Float): PixelPerInch = PixelPerInch(this.value.rem(other))


    override operator fun times(other: Double): PixelPerInch = PixelPerInch(this.value.times(other))
    override operator fun div(other: Double): PixelPerInch = PixelPerInch(this.value.div(other))
    override operator fun rem(other: Double): PixelPerInch = PixelPerInch(this.value.rem(other))


    override operator fun unaryMinus(): PixelPerInch = PixelPerInch(-value)
    override operator fun unaryPlus(): PixelPerInch = this
    override operator fun inc(): PixelPerInch = PixelPerInch(this.value + 1)
    override operator fun dec(): PixelPerInch = PixelPerInch(this.value - 1)

    override fun round(): PixelPerInch = PixelPerInch(round(this.value))
    override fun truncate(): PixelPerInch = PixelPerInch(truncate(this.value))
    override fun floor(): PixelPerInch = PixelPerInch(floor(this.value))
    override fun ceil(): PixelPerInch = PixelPerInch(ceil(this.value))
    override fun abs(): PixelPerInch = PixelPerInch(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is PixelPerInch -> this.value == other.value
        is Resolution -> this.toPixelPerMeter().value == other.toPixelPerMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toPixelPerMeter().value.hashCode()


}

val Byte.pixelPerInch
    get() = PixelPerInch(this)


val Short.pixelPerInch
    get() = PixelPerInch(this)


val Int.pixelPerInch
    get() = PixelPerInch(this)


val Long.pixelPerInch
    get() = PixelPerInch(this)


val Float.pixelPerInch
    get() = PixelPerInch(this)


val Double.pixelPerInch
    get() = PixelPerInch(this)


data class DotPerCentimeter(override val value: MeasurementUnitValue) : Resolution {

    override val unitName: String = "dot-per-centimeter"

    override fun formatters(language: MeasurementUnitLanguage) = language.graphicsDotPerCentimeter
    override fun toPixelPerMeter() = PixelPerMeter(value * ((1) / (1 * (1e-2))) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = DotPerCentimeter(0.0)
        fun fromPixelPerMeter(value: PixelPerMeter) = DotPerCentimeter((value.value - 0) / ((1) / (1 * (1e-2))))
    }

    operator fun plus(other: DotPerCentimeter): DotPerCentimeter = DotPerCentimeter(this.value + other.value)
    operator fun minus(other: DotPerCentimeter): DotPerCentimeter = DotPerCentimeter(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): DotPerCentimeter = DotPerCentimeter(this.value.times(other))
    override operator fun div(other: Byte): DotPerCentimeter = DotPerCentimeter(this.value.div(other))
    override operator fun rem(other: Byte): DotPerCentimeter = DotPerCentimeter(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): DotPerCentimeter = DotPerCentimeter(this.value.times(other))
    override operator fun div(other: Short): DotPerCentimeter = DotPerCentimeter(this.value.div(other))
    override operator fun rem(other: Short): DotPerCentimeter = DotPerCentimeter(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): DotPerCentimeter = DotPerCentimeter(this.value.times(other))
    override operator fun div(other: Int): DotPerCentimeter = DotPerCentimeter(this.value.div(other))
    override operator fun rem(other: Int): DotPerCentimeter = DotPerCentimeter(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): DotPerCentimeter = DotPerCentimeter(this.value.times(other))
    override operator fun div(other: Long): DotPerCentimeter = DotPerCentimeter(this.value.div(other))
    override operator fun rem(other: Long): DotPerCentimeter = DotPerCentimeter(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): DotPerCentimeter = DotPerCentimeter(this.value.times(other))
    override operator fun div(other: Float): DotPerCentimeter = DotPerCentimeter(this.value.div(other))
    override operator fun rem(other: Float): DotPerCentimeter = DotPerCentimeter(this.value.rem(other))


    override operator fun times(other: Double): DotPerCentimeter = DotPerCentimeter(this.value.times(other))
    override operator fun div(other: Double): DotPerCentimeter = DotPerCentimeter(this.value.div(other))
    override operator fun rem(other: Double): DotPerCentimeter = DotPerCentimeter(this.value.rem(other))


    override operator fun unaryMinus(): DotPerCentimeter = DotPerCentimeter(-value)
    override operator fun unaryPlus(): DotPerCentimeter = this
    override operator fun inc(): DotPerCentimeter = DotPerCentimeter(this.value + 1)
    override operator fun dec(): DotPerCentimeter = DotPerCentimeter(this.value - 1)

    override fun round(): DotPerCentimeter = DotPerCentimeter(round(this.value))
    override fun truncate(): DotPerCentimeter = DotPerCentimeter(truncate(this.value))
    override fun floor(): DotPerCentimeter = DotPerCentimeter(floor(this.value))
    override fun ceil(): DotPerCentimeter = DotPerCentimeter(ceil(this.value))
    override fun abs(): DotPerCentimeter = DotPerCentimeter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is DotPerCentimeter -> this.value == other.value
        is Resolution -> this.toPixelPerMeter().value == other.toPixelPerMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toPixelPerMeter().value.hashCode()


}

val Byte.dotPerCentimeter
    get() = DotPerCentimeter(this)


val Short.dotPerCentimeter
    get() = DotPerCentimeter(this)


val Int.dotPerCentimeter
    get() = DotPerCentimeter(this)


val Long.dotPerCentimeter
    get() = DotPerCentimeter(this)


val Float.dotPerCentimeter
    get() = DotPerCentimeter(this)


val Double.dotPerCentimeter
    get() = DotPerCentimeter(this)


data class DotPerInch(override val value: MeasurementUnitValue) : Resolution {

    override val unitName: String = "dot-per-inch"

    override fun formatters(language: MeasurementUnitLanguage) = language.graphicsDotPerInch
    override fun toPixelPerMeter() = PixelPerMeter(value * ((1) / (0.025400000000000002)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = DotPerInch(0.0)
        fun fromPixelPerMeter(value: PixelPerMeter) = DotPerInch((value.value - 0) / ((1) / (0.025400000000000002)))
    }

    operator fun plus(other: DotPerInch): DotPerInch = DotPerInch(this.value + other.value)
    operator fun minus(other: DotPerInch): DotPerInch = DotPerInch(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): DotPerInch = DotPerInch(this.value.times(other))
    override operator fun div(other: Byte): DotPerInch = DotPerInch(this.value.div(other))
    override operator fun rem(other: Byte): DotPerInch = DotPerInch(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): DotPerInch = DotPerInch(this.value.times(other))
    override operator fun div(other: Short): DotPerInch = DotPerInch(this.value.div(other))
    override operator fun rem(other: Short): DotPerInch = DotPerInch(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): DotPerInch = DotPerInch(this.value.times(other))
    override operator fun div(other: Int): DotPerInch = DotPerInch(this.value.div(other))
    override operator fun rem(other: Int): DotPerInch = DotPerInch(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): DotPerInch = DotPerInch(this.value.times(other))
    override operator fun div(other: Long): DotPerInch = DotPerInch(this.value.div(other))
    override operator fun rem(other: Long): DotPerInch = DotPerInch(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): DotPerInch = DotPerInch(this.value.times(other))
    override operator fun div(other: Float): DotPerInch = DotPerInch(this.value.div(other))
    override operator fun rem(other: Float): DotPerInch = DotPerInch(this.value.rem(other))


    override operator fun times(other: Double): DotPerInch = DotPerInch(this.value.times(other))
    override operator fun div(other: Double): DotPerInch = DotPerInch(this.value.div(other))
    override operator fun rem(other: Double): DotPerInch = DotPerInch(this.value.rem(other))


    override operator fun unaryMinus(): DotPerInch = DotPerInch(-value)
    override operator fun unaryPlus(): DotPerInch = this
    override operator fun inc(): DotPerInch = DotPerInch(this.value + 1)
    override operator fun dec(): DotPerInch = DotPerInch(this.value - 1)

    override fun round(): DotPerInch = DotPerInch(round(this.value))
    override fun truncate(): DotPerInch = DotPerInch(truncate(this.value))
    override fun floor(): DotPerInch = DotPerInch(floor(this.value))
    override fun ceil(): DotPerInch = DotPerInch(ceil(this.value))
    override fun abs(): DotPerInch = DotPerInch(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is DotPerInch -> this.value == other.value
        is Resolution -> this.toPixelPerMeter().value == other.toPixelPerMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toPixelPerMeter().value.hashCode()


}

val Byte.dotPerInch
    get() = DotPerInch(this)


val Short.dotPerInch
    get() = DotPerInch(this)


val Int.dotPerInch
    get() = DotPerInch(this)


val Long.dotPerInch
    get() = DotPerInch(this)


val Float.dotPerInch
    get() = DotPerInch(this)


val Double.dotPerInch
    get() = DotPerInch(this)


data class PixelPerMeter(override val value: MeasurementUnitValue) : Resolution {

    override val unitName: String = "pixel-per-meter"

    override fun formatters(language: MeasurementUnitLanguage) = language.graphicsPixelPerMeter
    override fun toPixelPerMeter() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = PixelPerMeter(0.0)
        fun fromPixelPerMeter(value: PixelPerMeter) = value
    }

    operator fun plus(other: PixelPerMeter): PixelPerMeter = PixelPerMeter(this.value + other.value)
    operator fun minus(other: PixelPerMeter): PixelPerMeter = PixelPerMeter(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): PixelPerMeter = PixelPerMeter(this.value.times(other))
    override operator fun div(other: Byte): PixelPerMeter = PixelPerMeter(this.value.div(other))
    override operator fun rem(other: Byte): PixelPerMeter = PixelPerMeter(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): PixelPerMeter = PixelPerMeter(this.value.times(other))
    override operator fun div(other: Short): PixelPerMeter = PixelPerMeter(this.value.div(other))
    override operator fun rem(other: Short): PixelPerMeter = PixelPerMeter(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): PixelPerMeter = PixelPerMeter(this.value.times(other))
    override operator fun div(other: Int): PixelPerMeter = PixelPerMeter(this.value.div(other))
    override operator fun rem(other: Int): PixelPerMeter = PixelPerMeter(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): PixelPerMeter = PixelPerMeter(this.value.times(other))
    override operator fun div(other: Long): PixelPerMeter = PixelPerMeter(this.value.div(other))
    override operator fun rem(other: Long): PixelPerMeter = PixelPerMeter(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): PixelPerMeter = PixelPerMeter(this.value.times(other))
    override operator fun div(other: Float): PixelPerMeter = PixelPerMeter(this.value.div(other))
    override operator fun rem(other: Float): PixelPerMeter = PixelPerMeter(this.value.rem(other))


    override operator fun times(other: Double): PixelPerMeter = PixelPerMeter(this.value.times(other))
    override operator fun div(other: Double): PixelPerMeter = PixelPerMeter(this.value.div(other))
    override operator fun rem(other: Double): PixelPerMeter = PixelPerMeter(this.value.rem(other))


    override operator fun unaryMinus(): PixelPerMeter = PixelPerMeter(-value)
    override operator fun unaryPlus(): PixelPerMeter = this
    override operator fun inc(): PixelPerMeter = PixelPerMeter(this.value + 1)
    override operator fun dec(): PixelPerMeter = PixelPerMeter(this.value - 1)

    override fun round(): PixelPerMeter = PixelPerMeter(round(this.value))
    override fun truncate(): PixelPerMeter = PixelPerMeter(truncate(this.value))
    override fun floor(): PixelPerMeter = PixelPerMeter(floor(this.value))
    override fun ceil(): PixelPerMeter = PixelPerMeter(ceil(this.value))
    override fun abs(): PixelPerMeter = PixelPerMeter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is PixelPerMeter -> this.value == other.value
        is Resolution -> this.toPixelPerMeter().value == other.toPixelPerMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toPixelPerMeter().value.hashCode()


}

val Byte.pixelPerMeter
    get() = PixelPerMeter(this)


val Short.pixelPerMeter
    get() = PixelPerMeter(this)


val Int.pixelPerMeter
    get() = PixelPerMeter(this)


val Long.pixelPerMeter
    get() = PixelPerMeter(this)


val Float.pixelPerMeter
    get() = PixelPerMeter(this)


val Double.pixelPerMeter
    get() = PixelPerMeter(this)
                            
