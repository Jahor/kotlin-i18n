package info.leonenko.i18n.units.graphics

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

@Serializable(Graphics.Serializer::class)
interface Graphics : MeasurementUnit, Comparable<Graphics> {
    override val measurement: String get() = "graphics"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Graphics> = emptyMap()) : KSerializer<Graphics> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())

        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Graphics", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Graphics {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Graphics unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "pixel" -> Pixel(value)
                "megapixel" -> Megapixel(value)
                "dot" -> Dot(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Graphics unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Graphics) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toPixel(): Pixel
    override fun getBaseUnitValue() = toPixel().value
    fun toMegapixel(): Megapixel {
        return Megapixel.fromPixel(toPixel())
    }

    fun toDot(): Dot {
        return Dot.fromPixel(toPixel())
    }

    companion object {
        val ZERO: Graphics = Pixel(0.0)

    }

    override operator fun compareTo(other: Graphics): Int = this.toPixel().value.compareTo(other.toPixel().value)

    operator fun plus(other: Graphics): Pixel = this.toPixel() + other.toPixel()
    operator fun minus(other: Graphics): Pixel = this.toPixel() - other.toPixel()
    operator fun div(other: Graphics): Double = this.toPixel().value / other.toPixel().value
    operator fun unaryMinus(): Graphics
    operator fun unaryPlus(): Graphics
    operator fun inc(): Graphics
    operator fun dec(): Graphics

    fun round(): Graphics
    fun truncate(): Graphics
    fun floor(): Graphics
    fun ceil(): Graphics
    fun abs(): Graphics


    operator fun times(other: kotlin.Byte): Graphics
    operator fun div(other: kotlin.Byte): Graphics
    operator fun rem(other: kotlin.Byte): Graphics


    operator fun times(other: kotlin.Short): Graphics
    operator fun div(other: kotlin.Short): Graphics
    operator fun rem(other: kotlin.Short): Graphics


    operator fun times(other: kotlin.Int): Graphics
    operator fun div(other: kotlin.Int): Graphics
    operator fun rem(other: kotlin.Int): Graphics


    operator fun times(other: kotlin.Long): Graphics
    operator fun div(other: kotlin.Long): Graphics
    operator fun rem(other: kotlin.Long): Graphics


    operator fun times(other: kotlin.Float): Graphics
    operator fun div(other: kotlin.Float): Graphics
    operator fun rem(other: kotlin.Float): Graphics


    operator fun times(other: kotlin.Double): Graphics
    operator fun div(other: kotlin.Double): Graphics
    operator fun rem(other: kotlin.Double): Graphics

}

data class Pixel(override val value: MeasurementUnitValue) : Graphics {

    override val unitName: String = "pixel"

    override fun formatters(language: MeasurementUnitLanguage) = language.graphicsPixel
    override fun toPixel() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Pixel(0.0)
        fun fromPixel(value: Pixel) = value
    }

    operator fun plus(other: Pixel): Pixel = Pixel(this.value + other.value)
    operator fun minus(other: Pixel): Pixel = Pixel(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Pixel = Pixel(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Pixel = Pixel(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Pixel = Pixel(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Pixel = Pixel(this.value.times(other))
    override operator fun div(other: kotlin.Short): Pixel = Pixel(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Pixel = Pixel(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Pixel = Pixel(this.value.times(other))
    override operator fun div(other: kotlin.Int): Pixel = Pixel(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Pixel = Pixel(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Pixel = Pixel(this.value.times(other))
    override operator fun div(other: kotlin.Long): Pixel = Pixel(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Pixel = Pixel(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Pixel = Pixel(this.value.times(other))
    override operator fun div(other: kotlin.Float): Pixel = Pixel(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Pixel = Pixel(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Pixel = Pixel(this.value.times(other))
    override operator fun div(other: kotlin.Double): Pixel = Pixel(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Pixel = Pixel(this.value.rem(other))


    override operator fun unaryMinus(): Pixel = Pixel(-value)
    override operator fun unaryPlus(): Pixel = this
    override operator fun inc(): Pixel = Pixel(this.value + 1)
    override operator fun dec(): Pixel = Pixel(this.value - 1)

    override fun round(): Pixel = Pixel(round(this.value))
    override fun truncate(): Pixel = Pixel(truncate(this.value))
    override fun floor(): Pixel = Pixel(floor(this.value))
    override fun ceil(): Pixel = Pixel(ceil(this.value))
    override fun abs(): Pixel = Pixel(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Pixel -> this.value == other.value
        is Graphics -> this.toPixel().value == other.toPixel().value
        else -> false
    }

    override fun hashCode(): Int = this.toPixel().value.hashCode()


}

val kotlin.Byte.pixel
    get() = Pixel(this)


val kotlin.Short.pixel
    get() = Pixel(this)


val kotlin.Int.pixel
    get() = Pixel(this)


val kotlin.Long.pixel
    get() = Pixel(this)


val kotlin.Float.pixel
    get() = Pixel(this)


val kotlin.Double.pixel
    get() = Pixel(this)


data class Megapixel(override val value: MeasurementUnitValue) : Graphics {

    override val unitName: String = "megapixel"

    override fun formatters(language: MeasurementUnitLanguage) = language.graphicsMegapixel
    override fun toPixel() = Pixel(value * (1 * (1e6)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Megapixel(0.0)
        fun fromPixel(value: Pixel) = Megapixel((value.value - 0) / (1 * (1e6)))
    }

    operator fun plus(other: Megapixel): Megapixel = Megapixel(this.value + other.value)
    operator fun minus(other: Megapixel): Megapixel = Megapixel(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Megapixel = Megapixel(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Megapixel = Megapixel(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Megapixel = Megapixel(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Megapixel = Megapixel(this.value.times(other))
    override operator fun div(other: kotlin.Short): Megapixel = Megapixel(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Megapixel = Megapixel(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Megapixel = Megapixel(this.value.times(other))
    override operator fun div(other: kotlin.Int): Megapixel = Megapixel(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Megapixel = Megapixel(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Megapixel = Megapixel(this.value.times(other))
    override operator fun div(other: kotlin.Long): Megapixel = Megapixel(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Megapixel = Megapixel(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Megapixel = Megapixel(this.value.times(other))
    override operator fun div(other: kotlin.Float): Megapixel = Megapixel(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Megapixel = Megapixel(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Megapixel = Megapixel(this.value.times(other))
    override operator fun div(other: kotlin.Double): Megapixel = Megapixel(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Megapixel = Megapixel(this.value.rem(other))


    override operator fun unaryMinus(): Megapixel = Megapixel(-value)
    override operator fun unaryPlus(): Megapixel = this
    override operator fun inc(): Megapixel = Megapixel(this.value + 1)
    override operator fun dec(): Megapixel = Megapixel(this.value - 1)

    override fun round(): Megapixel = Megapixel(round(this.value))
    override fun truncate(): Megapixel = Megapixel(truncate(this.value))
    override fun floor(): Megapixel = Megapixel(floor(this.value))
    override fun ceil(): Megapixel = Megapixel(ceil(this.value))
    override fun abs(): Megapixel = Megapixel(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Megapixel -> this.value == other.value
        is Graphics -> this.toPixel().value == other.toPixel().value
        else -> false
    }

    override fun hashCode(): Int = this.toPixel().value.hashCode()


}

val kotlin.Byte.megapixel
    get() = Megapixel(this)


val kotlin.Short.megapixel
    get() = Megapixel(this)


val kotlin.Int.megapixel
    get() = Megapixel(this)


val kotlin.Long.megapixel
    get() = Megapixel(this)


val kotlin.Float.megapixel
    get() = Megapixel(this)


val kotlin.Double.megapixel
    get() = Megapixel(this)


data class Dot(override val value: MeasurementUnitValue) : Graphics {

    override val unitName: String = "dot"

    override fun formatters(language: MeasurementUnitLanguage) = language.graphicsDot
    override fun toPixel() = Pixel(value * (1) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Dot(0.0)
        fun fromPixel(value: Pixel) = Dot((value.value - 0) / (1))
    }

    operator fun plus(other: Dot): Dot = Dot(this.value + other.value)
    operator fun minus(other: Dot): Dot = Dot(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Dot = Dot(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Dot = Dot(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Dot = Dot(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Dot = Dot(this.value.times(other))
    override operator fun div(other: kotlin.Short): Dot = Dot(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Dot = Dot(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Dot = Dot(this.value.times(other))
    override operator fun div(other: kotlin.Int): Dot = Dot(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Dot = Dot(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Dot = Dot(this.value.times(other))
    override operator fun div(other: kotlin.Long): Dot = Dot(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Dot = Dot(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Dot = Dot(this.value.times(other))
    override operator fun div(other: kotlin.Float): Dot = Dot(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Dot = Dot(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Dot = Dot(this.value.times(other))
    override operator fun div(other: kotlin.Double): Dot = Dot(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Dot = Dot(this.value.rem(other))


    override operator fun unaryMinus(): Dot = Dot(-value)
    override operator fun unaryPlus(): Dot = this
    override operator fun inc(): Dot = Dot(this.value + 1)
    override operator fun dec(): Dot = Dot(this.value - 1)

    override fun round(): Dot = Dot(round(this.value))
    override fun truncate(): Dot = Dot(truncate(this.value))
    override fun floor(): Dot = Dot(floor(this.value))
    override fun ceil(): Dot = Dot(ceil(this.value))
    override fun abs(): Dot = Dot(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Dot -> this.value == other.value
        is Graphics -> this.toPixel().value == other.toPixel().value
        else -> false
    }

    override fun hashCode(): Int = this.toPixel().value.hashCode()


}

val kotlin.Byte.dot
    get() = Dot(this)


val kotlin.Short.dot
    get() = Dot(this)


val kotlin.Int.dot
    get() = Dot(this)


val kotlin.Long.dot
    get() = Dot(this)


val kotlin.Float.dot
    get() = Dot(this)


val kotlin.Double.dot
    get() = Dot(this)
                            
