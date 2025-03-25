package info.leonenko.i18n.units.light

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

@Serializable(LuminousFlux.Serializer::class)
interface LuminousFlux : MeasurementUnit, Comparable<LuminousFlux> {
    override val measurement: String get() = "luminous-flux"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> LuminousFlux> = emptyMap()) : KSerializer<LuminousFlux> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("LuminousFlux", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): LuminousFlux {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected LuminousFlux unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "lumen" -> Lumen(value)
                "candela-square-meter-per-square-meter" -> CandelaSquareMeterPerSquareMeter(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown LuminousFlux unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: LuminousFlux) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toLumen(): Lumen {
        return Lumen.fromCandelaSquareMeterPerSquareMeter(toCandelaSquareMeterPerSquareMeter())
    }

    fun toCandelaSquareMeterPerSquareMeter(): CandelaSquareMeterPerSquareMeter
    override fun getBaseUnitValue() = toCandelaSquareMeterPerSquareMeter().value

    companion object {
        val ZERO: LuminousFlux = CandelaSquareMeterPerSquareMeter(0.0)

    }

    override operator fun compareTo(other: LuminousFlux): Int = this.toCandelaSquareMeterPerSquareMeter().value.compareTo(other.toCandelaSquareMeterPerSquareMeter().value)

    operator fun plus(other: LuminousFlux): CandelaSquareMeterPerSquareMeter = this.toCandelaSquareMeterPerSquareMeter() + other.toCandelaSquareMeterPerSquareMeter()
    operator fun minus(other: LuminousFlux): CandelaSquareMeterPerSquareMeter = this.toCandelaSquareMeterPerSquareMeter() - other.toCandelaSquareMeterPerSquareMeter()
    operator fun div(other: LuminousFlux): Double = this.toCandelaSquareMeterPerSquareMeter().value / other.toCandelaSquareMeterPerSquareMeter().value
    operator fun unaryMinus(): LuminousFlux
    operator fun unaryPlus(): LuminousFlux
    operator fun inc(): LuminousFlux
    operator fun dec(): LuminousFlux

    fun round(): LuminousFlux
    fun truncate(): LuminousFlux
    fun floor(): LuminousFlux
    fun ceil(): LuminousFlux
    fun abs(): LuminousFlux


    operator fun times(other: Byte): LuminousFlux
    operator fun div(other: Byte): LuminousFlux
    operator fun rem(other: Byte): LuminousFlux


    operator fun times(other: Short): LuminousFlux
    operator fun div(other: Short): LuminousFlux
    operator fun rem(other: Short): LuminousFlux


    operator fun times(other: Int): LuminousFlux
    operator fun div(other: Int): LuminousFlux
    operator fun rem(other: Int): LuminousFlux


    operator fun times(other: Long): LuminousFlux
    operator fun div(other: Long): LuminousFlux
    operator fun rem(other: Long): LuminousFlux


    operator fun times(other: Float): LuminousFlux
    operator fun div(other: Float): LuminousFlux
    operator fun rem(other: Float): LuminousFlux


    operator fun times(other: Double): LuminousFlux
    operator fun div(other: Double): LuminousFlux
    operator fun rem(other: Double): LuminousFlux

}

data class Lumen(override val value: MeasurementUnitValue) : LuminousFlux {

    override val unitName: String = "lumen"

    override fun formatters(language: MeasurementUnitLanguage) = language.lightLumen
    override fun toCandelaSquareMeterPerSquareMeter() = CandelaSquareMeterPerSquareMeter(value * (1) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Lumen(0.0)
        fun fromCandelaSquareMeterPerSquareMeter(value: CandelaSquareMeterPerSquareMeter) = Lumen((value.value - 0) / (1))
    }

    operator fun plus(other: Lumen): Lumen = Lumen(this.value + other.value)
    operator fun minus(other: Lumen): Lumen = Lumen(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Lumen = Lumen(this.value.times(other))
    override operator fun div(other: Byte): Lumen = Lumen(this.value.div(other))
    override operator fun rem(other: Byte): Lumen = Lumen(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Lumen = Lumen(this.value.times(other))
    override operator fun div(other: Short): Lumen = Lumen(this.value.div(other))
    override operator fun rem(other: Short): Lumen = Lumen(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Lumen = Lumen(this.value.times(other))
    override operator fun div(other: Int): Lumen = Lumen(this.value.div(other))
    override operator fun rem(other: Int): Lumen = Lumen(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Lumen = Lumen(this.value.times(other))
    override operator fun div(other: Long): Lumen = Lumen(this.value.div(other))
    override operator fun rem(other: Long): Lumen = Lumen(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Lumen = Lumen(this.value.times(other))
    override operator fun div(other: Float): Lumen = Lumen(this.value.div(other))
    override operator fun rem(other: Float): Lumen = Lumen(this.value.rem(other))


    override operator fun times(other: Double): Lumen = Lumen(this.value.times(other))
    override operator fun div(other: Double): Lumen = Lumen(this.value.div(other))
    override operator fun rem(other: Double): Lumen = Lumen(this.value.rem(other))


    override operator fun unaryMinus(): Lumen = Lumen(-value)
    override operator fun unaryPlus(): Lumen = this
    override operator fun inc(): Lumen = Lumen(this.value + 1)
    override operator fun dec(): Lumen = Lumen(this.value - 1)

    override fun round(): Lumen = Lumen(round(this.value))
    override fun truncate(): Lumen = Lumen(truncate(this.value))
    override fun floor(): Lumen = Lumen(floor(this.value))
    override fun ceil(): Lumen = Lumen(ceil(this.value))
    override fun abs(): Lumen = Lumen(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Lumen -> this.value == other.value
        is LuminousFlux -> this.toCandelaSquareMeterPerSquareMeter().value == other.toCandelaSquareMeterPerSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCandelaSquareMeterPerSquareMeter().value.hashCode()


}

val Byte.lumen
    get() = Lumen(this)


val Short.lumen
    get() = Lumen(this)


val Int.lumen
    get() = Lumen(this)


val Long.lumen
    get() = Lumen(this)


val Float.lumen
    get() = Lumen(this)


val Double.lumen
    get() = Lumen(this)


data class CandelaSquareMeterPerSquareMeter(override val value: MeasurementUnitValue) : LuminousFlux {

    override val unitName: String = "candela-square-meter-per-square-meter"

    override fun formatters(language: MeasurementUnitLanguage) = language.lightCandelaSquareMeterPerSquareMeter
    override fun toCandelaSquareMeterPerSquareMeter() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = CandelaSquareMeterPerSquareMeter(0.0)
        fun fromCandelaSquareMeterPerSquareMeter(value: CandelaSquareMeterPerSquareMeter) = value
    }

    operator fun plus(other: CandelaSquareMeterPerSquareMeter): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value + other.value)
    operator fun minus(other: CandelaSquareMeterPerSquareMeter): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.times(other))
    override operator fun div(other: Byte): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.div(other))
    override operator fun rem(other: Byte): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.times(other))
    override operator fun div(other: Short): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.div(other))
    override operator fun rem(other: Short): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.times(other))
    override operator fun div(other: Int): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.div(other))
    override operator fun rem(other: Int): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.times(other))
    override operator fun div(other: Long): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.div(other))
    override operator fun rem(other: Long): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.times(other))
    override operator fun div(other: Float): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.div(other))
    override operator fun rem(other: Float): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.rem(other))


    override operator fun times(other: Double): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.times(other))
    override operator fun div(other: Double): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.div(other))
    override operator fun rem(other: Double): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value.rem(other))


    override operator fun unaryMinus(): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(-value)
    override operator fun unaryPlus(): CandelaSquareMeterPerSquareMeter = this
    override operator fun inc(): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value + 1)
    override operator fun dec(): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(this.value - 1)

    override fun round(): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(round(this.value))
    override fun truncate(): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(truncate(this.value))
    override fun floor(): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(floor(this.value))
    override fun ceil(): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(ceil(this.value))
    override fun abs(): CandelaSquareMeterPerSquareMeter = CandelaSquareMeterPerSquareMeter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is CandelaSquareMeterPerSquareMeter -> this.value == other.value
        is LuminousFlux -> this.toCandelaSquareMeterPerSquareMeter().value == other.toCandelaSquareMeterPerSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCandelaSquareMeterPerSquareMeter().value.hashCode()


}

val Byte.candelaSquareMeterPerSquareMeter
    get() = CandelaSquareMeterPerSquareMeter(this)


val Short.candelaSquareMeterPerSquareMeter
    get() = CandelaSquareMeterPerSquareMeter(this)


val Int.candelaSquareMeterPerSquareMeter
    get() = CandelaSquareMeterPerSquareMeter(this)


val Long.candelaSquareMeterPerSquareMeter
    get() = CandelaSquareMeterPerSquareMeter(this)


val Float.candelaSquareMeterPerSquareMeter
    get() = CandelaSquareMeterPerSquareMeter(this)


val Double.candelaSquareMeterPerSquareMeter
    get() = CandelaSquareMeterPerSquareMeter(this)
                            
