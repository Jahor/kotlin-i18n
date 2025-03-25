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

@Serializable(Typewidth.Serializer::class)
interface Typewidth : MeasurementUnit, Comparable<Typewidth> {
    override val measurement: String get() = "typewidth"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Typewidth> = emptyMap()) : KSerializer<Typewidth> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Typewidth", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Typewidth {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Typewidth unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "em" -> Em(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Typewidth unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Typewidth) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toEm(): Em
    override fun getBaseUnitValue() = toEm().value

    companion object {
        val ZERO: Typewidth = Em(0.0)

    }

    override operator fun compareTo(other: Typewidth): Int = this.toEm().value.compareTo(other.toEm().value)

    operator fun plus(other: Typewidth): Em = this.toEm() + other.toEm()
    operator fun minus(other: Typewidth): Em = this.toEm() - other.toEm()
    operator fun div(other: Typewidth): Double = this.toEm().value / other.toEm().value
    operator fun unaryMinus(): Typewidth
    operator fun unaryPlus(): Typewidth
    operator fun inc(): Typewidth
    operator fun dec(): Typewidth

    fun round(): Typewidth
    fun truncate(): Typewidth
    fun floor(): Typewidth
    fun ceil(): Typewidth
    fun abs(): Typewidth


    operator fun times(other: Byte): Typewidth
    operator fun div(other: Byte): Typewidth
    operator fun rem(other: Byte): Typewidth


    operator fun times(other: Short): Typewidth
    operator fun div(other: Short): Typewidth
    operator fun rem(other: Short): Typewidth


    operator fun times(other: Int): Typewidth
    operator fun div(other: Int): Typewidth
    operator fun rem(other: Int): Typewidth


    operator fun times(other: Long): Typewidth
    operator fun div(other: Long): Typewidth
    operator fun rem(other: Long): Typewidth


    operator fun times(other: Float): Typewidth
    operator fun div(other: Float): Typewidth
    operator fun rem(other: Float): Typewidth


    operator fun times(other: Double): Typewidth
    operator fun div(other: Double): Typewidth
    operator fun rem(other: Double): Typewidth

}

data class Em(override val value: MeasurementUnitValue) : Typewidth {

    override val unitName: String = "em"

    override fun formatters(language: MeasurementUnitLanguage) = language.graphicsEm
    override fun toEm() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Em(0.0)
        fun fromEm(value: Em) = value
    }

    operator fun plus(other: Em): Em = Em(this.value + other.value)
    operator fun minus(other: Em): Em = Em(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Em = Em(this.value.times(other))
    override operator fun div(other: Byte): Em = Em(this.value.div(other))
    override operator fun rem(other: Byte): Em = Em(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Em = Em(this.value.times(other))
    override operator fun div(other: Short): Em = Em(this.value.div(other))
    override operator fun rem(other: Short): Em = Em(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Em = Em(this.value.times(other))
    override operator fun div(other: Int): Em = Em(this.value.div(other))
    override operator fun rem(other: Int): Em = Em(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Em = Em(this.value.times(other))
    override operator fun div(other: Long): Em = Em(this.value.div(other))
    override operator fun rem(other: Long): Em = Em(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Em = Em(this.value.times(other))
    override operator fun div(other: Float): Em = Em(this.value.div(other))
    override operator fun rem(other: Float): Em = Em(this.value.rem(other))


    override operator fun times(other: Double): Em = Em(this.value.times(other))
    override operator fun div(other: Double): Em = Em(this.value.div(other))
    override operator fun rem(other: Double): Em = Em(this.value.rem(other))


    override operator fun unaryMinus(): Em = Em(-value)
    override operator fun unaryPlus(): Em = this
    override operator fun inc(): Em = Em(this.value + 1)
    override operator fun dec(): Em = Em(this.value - 1)

    override fun round(): Em = Em(round(this.value))
    override fun truncate(): Em = Em(truncate(this.value))
    override fun floor(): Em = Em(floor(this.value))
    override fun ceil(): Em = Em(ceil(this.value))
    override fun abs(): Em = Em(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Em -> this.value == other.value
        is Typewidth -> this.toEm().value == other.toEm().value
        else -> false
    }

    override fun hashCode(): Int = this.toEm().value.hashCode()


}

val Byte.em
    get() = Em(this)


val Short.em
    get() = Em(this)


val Int.em
    get() = Em(this)


val Long.em
    get() = Em(this)


val Float.em
    get() = Em(this)


val Double.em
    get() = Em(this)
                            
