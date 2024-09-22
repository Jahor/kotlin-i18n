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


    operator fun times(other: kotlin.Byte): Typewidth
    operator fun div(other: kotlin.Byte): Typewidth
    operator fun rem(other: kotlin.Byte): Typewidth


    operator fun times(other: kotlin.Short): Typewidth
    operator fun div(other: kotlin.Short): Typewidth
    operator fun rem(other: kotlin.Short): Typewidth


    operator fun times(other: kotlin.Int): Typewidth
    operator fun div(other: kotlin.Int): Typewidth
    operator fun rem(other: kotlin.Int): Typewidth


    operator fun times(other: kotlin.Long): Typewidth
    operator fun div(other: kotlin.Long): Typewidth
    operator fun rem(other: kotlin.Long): Typewidth


    operator fun times(other: kotlin.Float): Typewidth
    operator fun div(other: kotlin.Float): Typewidth
    operator fun rem(other: kotlin.Float): Typewidth


    operator fun times(other: kotlin.Double): Typewidth
    operator fun div(other: kotlin.Double): Typewidth
    operator fun rem(other: kotlin.Double): Typewidth

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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Em = Em(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Em = Em(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Em = Em(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Em = Em(this.value.times(other))
    override operator fun div(other: kotlin.Short): Em = Em(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Em = Em(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Em = Em(this.value.times(other))
    override operator fun div(other: kotlin.Int): Em = Em(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Em = Em(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Em = Em(this.value.times(other))
    override operator fun div(other: kotlin.Long): Em = Em(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Em = Em(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Em = Em(this.value.times(other))
    override operator fun div(other: kotlin.Float): Em = Em(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Em = Em(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Em = Em(this.value.times(other))
    override operator fun div(other: kotlin.Double): Em = Em(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Em = Em(this.value.rem(other))


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

val kotlin.Byte.em
    get() = Em(this)


val kotlin.Short.em
    get() = Em(this)


val kotlin.Int.em
    get() = Em(this)


val kotlin.Long.em
    get() = Em(this)


val kotlin.Float.em
    get() = Em(this)


val kotlin.Double.em
    get() = Em(this)
                            
