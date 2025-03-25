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


    operator fun times(other: Byte): Graphics
    operator fun div(other: Byte): Graphics
    operator fun rem(other: Byte): Graphics


    operator fun times(other: Short): Graphics
    operator fun div(other: Short): Graphics
    operator fun rem(other: Short): Graphics


    operator fun times(other: Int): Graphics
    operator fun div(other: Int): Graphics
    operator fun rem(other: Int): Graphics


    operator fun times(other: Long): Graphics
    operator fun div(other: Long): Graphics
    operator fun rem(other: Long): Graphics


    operator fun times(other: Float): Graphics
    operator fun div(other: Float): Graphics
    operator fun rem(other: Float): Graphics


    operator fun times(other: Double): Graphics
    operator fun div(other: Double): Graphics
    operator fun rem(other: Double): Graphics

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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Pixel = Pixel(this.value.times(other))
    override operator fun div(other: Byte): Pixel = Pixel(this.value.div(other))
    override operator fun rem(other: Byte): Pixel = Pixel(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Pixel = Pixel(this.value.times(other))
    override operator fun div(other: Short): Pixel = Pixel(this.value.div(other))
    override operator fun rem(other: Short): Pixel = Pixel(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Pixel = Pixel(this.value.times(other))
    override operator fun div(other: Int): Pixel = Pixel(this.value.div(other))
    override operator fun rem(other: Int): Pixel = Pixel(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Pixel = Pixel(this.value.times(other))
    override operator fun div(other: Long): Pixel = Pixel(this.value.div(other))
    override operator fun rem(other: Long): Pixel = Pixel(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Pixel = Pixel(this.value.times(other))
    override operator fun div(other: Float): Pixel = Pixel(this.value.div(other))
    override operator fun rem(other: Float): Pixel = Pixel(this.value.rem(other))


    override operator fun times(other: Double): Pixel = Pixel(this.value.times(other))
    override operator fun div(other: Double): Pixel = Pixel(this.value.div(other))
    override operator fun rem(other: Double): Pixel = Pixel(this.value.rem(other))


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

val Byte.pixel
    get() = Pixel(this)


val Short.pixel
    get() = Pixel(this)


val Int.pixel
    get() = Pixel(this)


val Long.pixel
    get() = Pixel(this)


val Float.pixel
    get() = Pixel(this)


val Double.pixel
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Megapixel = Megapixel(this.value.times(other))
    override operator fun div(other: Byte): Megapixel = Megapixel(this.value.div(other))
    override operator fun rem(other: Byte): Megapixel = Megapixel(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Megapixel = Megapixel(this.value.times(other))
    override operator fun div(other: Short): Megapixel = Megapixel(this.value.div(other))
    override operator fun rem(other: Short): Megapixel = Megapixel(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Megapixel = Megapixel(this.value.times(other))
    override operator fun div(other: Int): Megapixel = Megapixel(this.value.div(other))
    override operator fun rem(other: Int): Megapixel = Megapixel(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Megapixel = Megapixel(this.value.times(other))
    override operator fun div(other: Long): Megapixel = Megapixel(this.value.div(other))
    override operator fun rem(other: Long): Megapixel = Megapixel(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Megapixel = Megapixel(this.value.times(other))
    override operator fun div(other: Float): Megapixel = Megapixel(this.value.div(other))
    override operator fun rem(other: Float): Megapixel = Megapixel(this.value.rem(other))


    override operator fun times(other: Double): Megapixel = Megapixel(this.value.times(other))
    override operator fun div(other: Double): Megapixel = Megapixel(this.value.div(other))
    override operator fun rem(other: Double): Megapixel = Megapixel(this.value.rem(other))


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

val Byte.megapixel
    get() = Megapixel(this)


val Short.megapixel
    get() = Megapixel(this)


val Int.megapixel
    get() = Megapixel(this)


val Long.megapixel
    get() = Megapixel(this)


val Float.megapixel
    get() = Megapixel(this)


val Double.megapixel
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Dot = Dot(this.value.times(other))
    override operator fun div(other: Byte): Dot = Dot(this.value.div(other))
    override operator fun rem(other: Byte): Dot = Dot(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Dot = Dot(this.value.times(other))
    override operator fun div(other: Short): Dot = Dot(this.value.div(other))
    override operator fun rem(other: Short): Dot = Dot(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Dot = Dot(this.value.times(other))
    override operator fun div(other: Int): Dot = Dot(this.value.div(other))
    override operator fun rem(other: Int): Dot = Dot(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Dot = Dot(this.value.times(other))
    override operator fun div(other: Long): Dot = Dot(this.value.div(other))
    override operator fun rem(other: Long): Dot = Dot(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Dot = Dot(this.value.times(other))
    override operator fun div(other: Float): Dot = Dot(this.value.div(other))
    override operator fun rem(other: Float): Dot = Dot(this.value.rem(other))


    override operator fun times(other: Double): Dot = Dot(this.value.times(other))
    override operator fun div(other: Double): Dot = Dot(this.value.div(other))
    override operator fun rem(other: Double): Dot = Dot(this.value.rem(other))


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

val Byte.dot
    get() = Dot(this)


val Short.dot
    get() = Dot(this)


val Int.dot
    get() = Dot(this)


val Long.dot
    get() = Dot(this)


val Float.dot
    get() = Dot(this)


val Double.dot
    get() = Dot(this)
                            
