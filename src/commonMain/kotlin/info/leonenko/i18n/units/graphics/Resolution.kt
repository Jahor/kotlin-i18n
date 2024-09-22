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


    operator fun times(other: kotlin.Byte): Resolution
    operator fun div(other: kotlin.Byte): Resolution
    operator fun rem(other: kotlin.Byte): Resolution


    operator fun times(other: kotlin.Short): Resolution
    operator fun div(other: kotlin.Short): Resolution
    operator fun rem(other: kotlin.Short): Resolution


    operator fun times(other: kotlin.Int): Resolution
    operator fun div(other: kotlin.Int): Resolution
    operator fun rem(other: kotlin.Int): Resolution


    operator fun times(other: kotlin.Long): Resolution
    operator fun div(other: kotlin.Long): Resolution
    operator fun rem(other: kotlin.Long): Resolution


    operator fun times(other: kotlin.Float): Resolution
    operator fun div(other: kotlin.Float): Resolution
    operator fun rem(other: kotlin.Float): Resolution


    operator fun times(other: kotlin.Double): Resolution
    operator fun div(other: kotlin.Double): Resolution
    operator fun rem(other: kotlin.Double): Resolution

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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): PixelPerCentimeter = PixelPerCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): PixelPerCentimeter = PixelPerCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): PixelPerCentimeter = PixelPerCentimeter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): PixelPerCentimeter = PixelPerCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Short): PixelPerCentimeter = PixelPerCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): PixelPerCentimeter = PixelPerCentimeter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): PixelPerCentimeter = PixelPerCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Int): PixelPerCentimeter = PixelPerCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): PixelPerCentimeter = PixelPerCentimeter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): PixelPerCentimeter = PixelPerCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Long): PixelPerCentimeter = PixelPerCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): PixelPerCentimeter = PixelPerCentimeter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): PixelPerCentimeter = PixelPerCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Float): PixelPerCentimeter = PixelPerCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): PixelPerCentimeter = PixelPerCentimeter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): PixelPerCentimeter = PixelPerCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Double): PixelPerCentimeter = PixelPerCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): PixelPerCentimeter = PixelPerCentimeter(this.value.rem(other))


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

val kotlin.Byte.pixelPerCentimeter
    get() = PixelPerCentimeter(this)


val kotlin.Short.pixelPerCentimeter
    get() = PixelPerCentimeter(this)


val kotlin.Int.pixelPerCentimeter
    get() = PixelPerCentimeter(this)


val kotlin.Long.pixelPerCentimeter
    get() = PixelPerCentimeter(this)


val kotlin.Float.pixelPerCentimeter
    get() = PixelPerCentimeter(this)


val kotlin.Double.pixelPerCentimeter
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): PixelPerInch = PixelPerInch(this.value.times(other))
    override operator fun div(other: kotlin.Byte): PixelPerInch = PixelPerInch(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): PixelPerInch = PixelPerInch(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): PixelPerInch = PixelPerInch(this.value.times(other))
    override operator fun div(other: kotlin.Short): PixelPerInch = PixelPerInch(this.value.div(other))
    override operator fun rem(other: kotlin.Short): PixelPerInch = PixelPerInch(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): PixelPerInch = PixelPerInch(this.value.times(other))
    override operator fun div(other: kotlin.Int): PixelPerInch = PixelPerInch(this.value.div(other))
    override operator fun rem(other: kotlin.Int): PixelPerInch = PixelPerInch(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): PixelPerInch = PixelPerInch(this.value.times(other))
    override operator fun div(other: kotlin.Long): PixelPerInch = PixelPerInch(this.value.div(other))
    override operator fun rem(other: kotlin.Long): PixelPerInch = PixelPerInch(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): PixelPerInch = PixelPerInch(this.value.times(other))
    override operator fun div(other: kotlin.Float): PixelPerInch = PixelPerInch(this.value.div(other))
    override operator fun rem(other: kotlin.Float): PixelPerInch = PixelPerInch(this.value.rem(other))


    override operator fun times(other: kotlin.Double): PixelPerInch = PixelPerInch(this.value.times(other))
    override operator fun div(other: kotlin.Double): PixelPerInch = PixelPerInch(this.value.div(other))
    override operator fun rem(other: kotlin.Double): PixelPerInch = PixelPerInch(this.value.rem(other))


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

val kotlin.Byte.pixelPerInch
    get() = PixelPerInch(this)


val kotlin.Short.pixelPerInch
    get() = PixelPerInch(this)


val kotlin.Int.pixelPerInch
    get() = PixelPerInch(this)


val kotlin.Long.pixelPerInch
    get() = PixelPerInch(this)


val kotlin.Float.pixelPerInch
    get() = PixelPerInch(this)


val kotlin.Double.pixelPerInch
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): DotPerCentimeter = DotPerCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): DotPerCentimeter = DotPerCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): DotPerCentimeter = DotPerCentimeter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): DotPerCentimeter = DotPerCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Short): DotPerCentimeter = DotPerCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): DotPerCentimeter = DotPerCentimeter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): DotPerCentimeter = DotPerCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Int): DotPerCentimeter = DotPerCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): DotPerCentimeter = DotPerCentimeter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): DotPerCentimeter = DotPerCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Long): DotPerCentimeter = DotPerCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): DotPerCentimeter = DotPerCentimeter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): DotPerCentimeter = DotPerCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Float): DotPerCentimeter = DotPerCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): DotPerCentimeter = DotPerCentimeter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): DotPerCentimeter = DotPerCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Double): DotPerCentimeter = DotPerCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): DotPerCentimeter = DotPerCentimeter(this.value.rem(other))


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

val kotlin.Byte.dotPerCentimeter
    get() = DotPerCentimeter(this)


val kotlin.Short.dotPerCentimeter
    get() = DotPerCentimeter(this)


val kotlin.Int.dotPerCentimeter
    get() = DotPerCentimeter(this)


val kotlin.Long.dotPerCentimeter
    get() = DotPerCentimeter(this)


val kotlin.Float.dotPerCentimeter
    get() = DotPerCentimeter(this)


val kotlin.Double.dotPerCentimeter
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): DotPerInch = DotPerInch(this.value.times(other))
    override operator fun div(other: kotlin.Byte): DotPerInch = DotPerInch(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): DotPerInch = DotPerInch(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): DotPerInch = DotPerInch(this.value.times(other))
    override operator fun div(other: kotlin.Short): DotPerInch = DotPerInch(this.value.div(other))
    override operator fun rem(other: kotlin.Short): DotPerInch = DotPerInch(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): DotPerInch = DotPerInch(this.value.times(other))
    override operator fun div(other: kotlin.Int): DotPerInch = DotPerInch(this.value.div(other))
    override operator fun rem(other: kotlin.Int): DotPerInch = DotPerInch(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): DotPerInch = DotPerInch(this.value.times(other))
    override operator fun div(other: kotlin.Long): DotPerInch = DotPerInch(this.value.div(other))
    override operator fun rem(other: kotlin.Long): DotPerInch = DotPerInch(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): DotPerInch = DotPerInch(this.value.times(other))
    override operator fun div(other: kotlin.Float): DotPerInch = DotPerInch(this.value.div(other))
    override operator fun rem(other: kotlin.Float): DotPerInch = DotPerInch(this.value.rem(other))


    override operator fun times(other: kotlin.Double): DotPerInch = DotPerInch(this.value.times(other))
    override operator fun div(other: kotlin.Double): DotPerInch = DotPerInch(this.value.div(other))
    override operator fun rem(other: kotlin.Double): DotPerInch = DotPerInch(this.value.rem(other))


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

val kotlin.Byte.dotPerInch
    get() = DotPerInch(this)


val kotlin.Short.dotPerInch
    get() = DotPerInch(this)


val kotlin.Int.dotPerInch
    get() = DotPerInch(this)


val kotlin.Long.dotPerInch
    get() = DotPerInch(this)


val kotlin.Float.dotPerInch
    get() = DotPerInch(this)


val kotlin.Double.dotPerInch
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): PixelPerMeter = PixelPerMeter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): PixelPerMeter = PixelPerMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): PixelPerMeter = PixelPerMeter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): PixelPerMeter = PixelPerMeter(this.value.times(other))
    override operator fun div(other: kotlin.Short): PixelPerMeter = PixelPerMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): PixelPerMeter = PixelPerMeter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): PixelPerMeter = PixelPerMeter(this.value.times(other))
    override operator fun div(other: kotlin.Int): PixelPerMeter = PixelPerMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): PixelPerMeter = PixelPerMeter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): PixelPerMeter = PixelPerMeter(this.value.times(other))
    override operator fun div(other: kotlin.Long): PixelPerMeter = PixelPerMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): PixelPerMeter = PixelPerMeter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): PixelPerMeter = PixelPerMeter(this.value.times(other))
    override operator fun div(other: kotlin.Float): PixelPerMeter = PixelPerMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): PixelPerMeter = PixelPerMeter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): PixelPerMeter = PixelPerMeter(this.value.times(other))
    override operator fun div(other: kotlin.Double): PixelPerMeter = PixelPerMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): PixelPerMeter = PixelPerMeter(this.value.rem(other))


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

val kotlin.Byte.pixelPerMeter
    get() = PixelPerMeter(this)


val kotlin.Short.pixelPerMeter
    get() = PixelPerMeter(this)


val kotlin.Int.pixelPerMeter
    get() = PixelPerMeter(this)


val kotlin.Long.pixelPerMeter
    get() = PixelPerMeter(this)


val kotlin.Float.pixelPerMeter
    get() = PixelPerMeter(this)


val kotlin.Double.pixelPerMeter
    get() = PixelPerMeter(this)
                            
