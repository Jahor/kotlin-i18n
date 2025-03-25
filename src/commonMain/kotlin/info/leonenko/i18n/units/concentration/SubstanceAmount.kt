package info.leonenko.i18n.units.concentration

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

@Serializable(SubstanceAmount.Serializer::class)
interface SubstanceAmount : MeasurementUnit, Comparable<SubstanceAmount> {
    override val measurement: String get() = "substance-amount"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> SubstanceAmount> = emptyMap()) : KSerializer<SubstanceAmount> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("SubstanceAmount", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): SubstanceAmount {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected SubstanceAmount unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "item" -> Item(value)
                "mole" -> Mole(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown SubstanceAmount unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: SubstanceAmount) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toItem(): Item
    override fun getBaseUnitValue() = toItem().value
    fun toMole(): Mole {
        return Mole.fromItem(toItem())
    }

    companion object {
        val ZERO: SubstanceAmount = Item(0.0)

    }

    override operator fun compareTo(other: SubstanceAmount): Int = this.toItem().value.compareTo(other.toItem().value)

    operator fun plus(other: SubstanceAmount): Item = this.toItem() + other.toItem()
    operator fun minus(other: SubstanceAmount): Item = this.toItem() - other.toItem()
    operator fun div(other: SubstanceAmount): Double = this.toItem().value / other.toItem().value
    operator fun unaryMinus(): SubstanceAmount
    operator fun unaryPlus(): SubstanceAmount
    operator fun inc(): SubstanceAmount
    operator fun dec(): SubstanceAmount

    fun round(): SubstanceAmount
    fun truncate(): SubstanceAmount
    fun floor(): SubstanceAmount
    fun ceil(): SubstanceAmount
    fun abs(): SubstanceAmount


    operator fun times(other: Byte): SubstanceAmount
    operator fun div(other: Byte): SubstanceAmount
    operator fun rem(other: Byte): SubstanceAmount


    operator fun times(other: Short): SubstanceAmount
    operator fun div(other: Short): SubstanceAmount
    operator fun rem(other: Short): SubstanceAmount


    operator fun times(other: Int): SubstanceAmount
    operator fun div(other: Int): SubstanceAmount
    operator fun rem(other: Int): SubstanceAmount


    operator fun times(other: Long): SubstanceAmount
    operator fun div(other: Long): SubstanceAmount
    operator fun rem(other: Long): SubstanceAmount


    operator fun times(other: Float): SubstanceAmount
    operator fun div(other: Float): SubstanceAmount
    operator fun rem(other: Float): SubstanceAmount


    operator fun times(other: Double): SubstanceAmount
    operator fun div(other: Double): SubstanceAmount
    operator fun rem(other: Double): SubstanceAmount

}

data class Item(override val value: MeasurementUnitValue) : SubstanceAmount {

    override val unitName: String = "item"

    override fun formatters(language: MeasurementUnitLanguage) = language.concentrationItem
    override fun toItem() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Item(0.0)
        fun fromItem(value: Item) = value
    }

    operator fun plus(other: Item): Item = Item(this.value + other.value)
    operator fun minus(other: Item): Item = Item(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Item = Item(this.value.times(other))
    override operator fun div(other: Byte): Item = Item(this.value.div(other))
    override operator fun rem(other: Byte): Item = Item(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Item = Item(this.value.times(other))
    override operator fun div(other: Short): Item = Item(this.value.div(other))
    override operator fun rem(other: Short): Item = Item(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Item = Item(this.value.times(other))
    override operator fun div(other: Int): Item = Item(this.value.div(other))
    override operator fun rem(other: Int): Item = Item(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Item = Item(this.value.times(other))
    override operator fun div(other: Long): Item = Item(this.value.div(other))
    override operator fun rem(other: Long): Item = Item(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Item = Item(this.value.times(other))
    override operator fun div(other: Float): Item = Item(this.value.div(other))
    override operator fun rem(other: Float): Item = Item(this.value.rem(other))


    override operator fun times(other: Double): Item = Item(this.value.times(other))
    override operator fun div(other: Double): Item = Item(this.value.div(other))
    override operator fun rem(other: Double): Item = Item(this.value.rem(other))


    override operator fun unaryMinus(): Item = Item(-value)
    override operator fun unaryPlus(): Item = this
    override operator fun inc(): Item = Item(this.value + 1)
    override operator fun dec(): Item = Item(this.value - 1)

    override fun round(): Item = Item(round(this.value))
    override fun truncate(): Item = Item(truncate(this.value))
    override fun floor(): Item = Item(floor(this.value))
    override fun ceil(): Item = Item(ceil(this.value))
    override fun abs(): Item = Item(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Item -> this.value == other.value
        is SubstanceAmount -> this.toItem().value == other.toItem().value
        else -> false
    }

    override fun hashCode(): Int = this.toItem().value.hashCode()


}

val Byte.item
    get() = Item(this)


val Short.item
    get() = Item(this)


val Int.item
    get() = Item(this)


val Long.item
    get() = Item(this)


val Float.item
    get() = Item(this)


val Double.item
    get() = Item(this)


data class Mole(override val value: MeasurementUnitValue) : SubstanceAmount {

    override val unitName: String = "mole"

    override fun formatters(language: MeasurementUnitLanguage) = language.concentrationMole
    override fun toItem() = Item(value * (6.02214076e+23) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Mole(0.0)
        fun fromItem(value: Item) = Mole((value.value - 0) / (6.02214076e+23))
    }

    operator fun plus(other: Mole): Mole = Mole(this.value + other.value)
    operator fun minus(other: Mole): Mole = Mole(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Mole = Mole(this.value.times(other))
    override operator fun div(other: Byte): Mole = Mole(this.value.div(other))
    override operator fun rem(other: Byte): Mole = Mole(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Mole = Mole(this.value.times(other))
    override operator fun div(other: Short): Mole = Mole(this.value.div(other))
    override operator fun rem(other: Short): Mole = Mole(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Mole = Mole(this.value.times(other))
    override operator fun div(other: Int): Mole = Mole(this.value.div(other))
    override operator fun rem(other: Int): Mole = Mole(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Mole = Mole(this.value.times(other))
    override operator fun div(other: Long): Mole = Mole(this.value.div(other))
    override operator fun rem(other: Long): Mole = Mole(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Mole = Mole(this.value.times(other))
    override operator fun div(other: Float): Mole = Mole(this.value.div(other))
    override operator fun rem(other: Float): Mole = Mole(this.value.rem(other))


    override operator fun times(other: Double): Mole = Mole(this.value.times(other))
    override operator fun div(other: Double): Mole = Mole(this.value.div(other))
    override operator fun rem(other: Double): Mole = Mole(this.value.rem(other))


    override operator fun unaryMinus(): Mole = Mole(-value)
    override operator fun unaryPlus(): Mole = this
    override operator fun inc(): Mole = Mole(this.value + 1)
    override operator fun dec(): Mole = Mole(this.value - 1)

    override fun round(): Mole = Mole(round(this.value))
    override fun truncate(): Mole = Mole(truncate(this.value))
    override fun floor(): Mole = Mole(floor(this.value))
    override fun ceil(): Mole = Mole(ceil(this.value))
    override fun abs(): Mole = Mole(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Mole -> this.value == other.value
        is SubstanceAmount -> this.toItem().value == other.toItem().value
        else -> false
    }

    override fun hashCode(): Int = this.toItem().value.hashCode()


}

val Byte.mole
    get() = Mole(this)


val Short.mole
    get() = Mole(this)


val Int.mole
    get() = Mole(this)


val Long.mole
    get() = Mole(this)


val Float.mole
    get() = Mole(this)


val Double.mole
    get() = Mole(this)
                            
