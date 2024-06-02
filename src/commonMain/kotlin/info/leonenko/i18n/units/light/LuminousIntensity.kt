package info.leonenko.i18n.units.light

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

@Serializable(LuminousIntensity.Serializer::class)
interface LuminousIntensity : MeasurementUnit, Comparable<LuminousIntensity> {
    override val measurement: String get() = "luminous-intensity"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> LuminousIntensity> = emptyMap()) : KSerializer<LuminousIntensity> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())

        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("LuminousIntensity", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): LuminousIntensity {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected LuminousIntensity unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "candela" -> Candela(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown LuminousIntensity unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: LuminousIntensity) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toCandela(): Candela
    override fun getBaseUnitValue() = toCandela().value

    companion object {
        val ZERO: LuminousIntensity = Candela(0.0)

    }

    override operator fun compareTo(other: LuminousIntensity): Int = this.toCandela().value.compareTo(other.toCandela().value)

    operator fun plus(other: LuminousIntensity): Candela = this.toCandela() + other.toCandela()
    operator fun minus(other: LuminousIntensity): Candela = this.toCandela() - other.toCandela()
    operator fun div(other: LuminousIntensity): Double = this.toCandela().value / other.toCandela().value
    operator fun unaryMinus(): LuminousIntensity
    operator fun unaryPlus(): LuminousIntensity
    operator fun inc(): LuminousIntensity
    operator fun dec(): LuminousIntensity

    fun round(): LuminousIntensity
    fun truncate(): LuminousIntensity
    fun floor(): LuminousIntensity
    fun ceil(): LuminousIntensity
    fun abs(): LuminousIntensity


    operator fun times(other: kotlin.Byte): LuminousIntensity
    operator fun div(other: kotlin.Byte): LuminousIntensity
    operator fun rem(other: kotlin.Byte): LuminousIntensity


    operator fun times(other: kotlin.Short): LuminousIntensity
    operator fun div(other: kotlin.Short): LuminousIntensity
    operator fun rem(other: kotlin.Short): LuminousIntensity


    operator fun times(other: kotlin.Int): LuminousIntensity
    operator fun div(other: kotlin.Int): LuminousIntensity
    operator fun rem(other: kotlin.Int): LuminousIntensity


    operator fun times(other: kotlin.Long): LuminousIntensity
    operator fun div(other: kotlin.Long): LuminousIntensity
    operator fun rem(other: kotlin.Long): LuminousIntensity


    operator fun times(other: kotlin.Float): LuminousIntensity
    operator fun div(other: kotlin.Float): LuminousIntensity
    operator fun rem(other: kotlin.Float): LuminousIntensity


    operator fun times(other: kotlin.Double): LuminousIntensity
    operator fun div(other: kotlin.Double): LuminousIntensity
    operator fun rem(other: kotlin.Double): LuminousIntensity

}

data class Candela(override val value: MeasurementUnitValue) : LuminousIntensity {

    override val unitName: String = "candela"

    override fun formatters(language: MeasurementUnitLanguage) = language.lightCandela
    override fun toCandela() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Candela(0.0)
        fun fromCandela(value: Candela) = value
    }

    operator fun plus(other: Candela): Candela = Candela(this.value + other.value)
    operator fun minus(other: Candela): Candela = Candela(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Candela = Candela(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Candela = Candela(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Candela = Candela(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Candela = Candela(this.value.times(other))
    override operator fun div(other: kotlin.Short): Candela = Candela(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Candela = Candela(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Candela = Candela(this.value.times(other))
    override operator fun div(other: kotlin.Int): Candela = Candela(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Candela = Candela(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Candela = Candela(this.value.times(other))
    override operator fun div(other: kotlin.Long): Candela = Candela(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Candela = Candela(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Candela = Candela(this.value.times(other))
    override operator fun div(other: kotlin.Float): Candela = Candela(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Candela = Candela(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Candela = Candela(this.value.times(other))
    override operator fun div(other: kotlin.Double): Candela = Candela(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Candela = Candela(this.value.rem(other))


    override operator fun unaryMinus(): Candela = Candela(-value)
    override operator fun unaryPlus(): Candela = this
    override operator fun inc(): Candela = Candela(this.value + 1)
    override operator fun dec(): Candela = Candela(this.value - 1)

    override fun round(): Candela = Candela(round(this.value))
    override fun truncate(): Candela = Candela(truncate(this.value))
    override fun floor(): Candela = Candela(floor(this.value))
    override fun ceil(): Candela = Candela(ceil(this.value))
    override fun abs(): Candela = Candela(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Candela -> this.value == other.value
        is LuminousIntensity -> this.toCandela().value == other.toCandela().value
        else -> false
    }

    override fun hashCode(): Int = this.toCandela().value.hashCode()


}

val kotlin.Byte.candela
    get() = Candela(this)


val kotlin.Short.candela
    get() = Candela(this)


val kotlin.Int.candela
    get() = Candela(this)


val kotlin.Long.candela
    get() = Candela(this)


val kotlin.Float.candela
    get() = Candela(this)


val kotlin.Double.candela
    get() = Candela(this)
                            
