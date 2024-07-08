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

@Serializable(Concentration.Serializer::class)
interface Concentration : MeasurementUnit, Comparable<Concentration> {
    override val measurement: String get() = "concentration"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Concentration> = emptyMap()) : KSerializer<Concentration> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Concentration", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Concentration {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Concentration unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "milligram-ofglucose-per-deciliter" -> MilligramOfglucosePerDeciliter(value)
                "millimole-per-liter" -> MillimolePerLiter(value)
                "item-per-cubic-meter" -> ItemPerCubicMeter(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Concentration unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Concentration) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toMilligramOfglucosePerDeciliter(): MilligramOfglucosePerDeciliter {
        return MilligramOfglucosePerDeciliter.fromItemPerCubicMeter(toItemPerCubicMeter())
    }

    fun toMillimolePerLiter(): MillimolePerLiter {
        return MillimolePerLiter.fromItemPerCubicMeter(toItemPerCubicMeter())
    }

    fun toItemPerCubicMeter(): ItemPerCubicMeter
    override fun getBaseUnitValue() = toItemPerCubicMeter().value

    companion object {
        val ZERO: Concentration = ItemPerCubicMeter(0.0)

    }

    override operator fun compareTo(other: Concentration): Int = this.toItemPerCubicMeter().value.compareTo(other.toItemPerCubicMeter().value)

    operator fun plus(other: Concentration): ItemPerCubicMeter = this.toItemPerCubicMeter() + other.toItemPerCubicMeter()
    operator fun minus(other: Concentration): ItemPerCubicMeter = this.toItemPerCubicMeter() - other.toItemPerCubicMeter()
    operator fun div(other: Concentration): Double = this.toItemPerCubicMeter().value / other.toItemPerCubicMeter().value
    operator fun unaryMinus(): Concentration
    operator fun unaryPlus(): Concentration
    operator fun inc(): Concentration
    operator fun dec(): Concentration

    fun round(): Concentration
    fun truncate(): Concentration
    fun floor(): Concentration
    fun ceil(): Concentration
    fun abs(): Concentration


    operator fun times(other: kotlin.Byte): Concentration
    operator fun div(other: kotlin.Byte): Concentration
    operator fun rem(other: kotlin.Byte): Concentration


    operator fun times(other: kotlin.Short): Concentration
    operator fun div(other: kotlin.Short): Concentration
    operator fun rem(other: kotlin.Short): Concentration


    operator fun times(other: kotlin.Int): Concentration
    operator fun div(other: kotlin.Int): Concentration
    operator fun rem(other: kotlin.Int): Concentration


    operator fun times(other: kotlin.Long): Concentration
    operator fun div(other: kotlin.Long): Concentration
    operator fun rem(other: kotlin.Long): Concentration


    operator fun times(other: kotlin.Float): Concentration
    operator fun div(other: kotlin.Float): Concentration
    operator fun rem(other: kotlin.Float): Concentration


    operator fun times(other: kotlin.Double): Concentration
    operator fun div(other: kotlin.Double): Concentration
    operator fun rem(other: kotlin.Double): Concentration

}

data class MilligramOfglucosePerDeciliter(override val value: MeasurementUnitValue) : Concentration {

    override val unitName: String = "milligram-ofglucose-per-deciliter"

    override fun formatters(language: MeasurementUnitLanguage) = language.concentrationMilligramOfglucosePerDeciliter
    override fun toItemPerCubicMeter() = ItemPerCubicMeter(value * (((0.001 * (1e-3)) * (3.342742283480345e+24)) / (0.001 * (1e-1))) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = MilligramOfglucosePerDeciliter(0.0)
        fun fromItemPerCubicMeter(value: ItemPerCubicMeter) = MilligramOfglucosePerDeciliter((value.value - 0) / (((0.001 * (1e-3)) * (3.342742283480345e+24)) / (0.001 * (1e-1))))
    }

    operator fun plus(other: MilligramOfglucosePerDeciliter): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value + other.value)
    operator fun minus(other: MilligramOfglucosePerDeciliter): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.times(other))
    override operator fun div(other: kotlin.Short): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.times(other))
    override operator fun div(other: kotlin.Int): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.times(other))
    override operator fun div(other: kotlin.Long): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.times(other))
    override operator fun div(other: kotlin.Float): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.times(other))
    override operator fun div(other: kotlin.Double): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value.rem(other))


    override operator fun unaryMinus(): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(-value)
    override operator fun unaryPlus(): MilligramOfglucosePerDeciliter = this
    override operator fun inc(): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value + 1)
    override operator fun dec(): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(this.value - 1)

    override fun round(): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(round(this.value))
    override fun truncate(): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(truncate(this.value))
    override fun floor(): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(floor(this.value))
    override fun ceil(): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(ceil(this.value))
    override fun abs(): MilligramOfglucosePerDeciliter = MilligramOfglucosePerDeciliter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is MilligramOfglucosePerDeciliter -> this.value == other.value
        is Concentration -> this.toItemPerCubicMeter().value == other.toItemPerCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toItemPerCubicMeter().value.hashCode()


}

val kotlin.Byte.milligramOfglucosePerDeciliter
    get() = MilligramOfglucosePerDeciliter(this)


val kotlin.Short.milligramOfglucosePerDeciliter
    get() = MilligramOfglucosePerDeciliter(this)


val kotlin.Int.milligramOfglucosePerDeciliter
    get() = MilligramOfglucosePerDeciliter(this)


val kotlin.Long.milligramOfglucosePerDeciliter
    get() = MilligramOfglucosePerDeciliter(this)


val kotlin.Float.milligramOfglucosePerDeciliter
    get() = MilligramOfglucosePerDeciliter(this)


val kotlin.Double.milligramOfglucosePerDeciliter
    get() = MilligramOfglucosePerDeciliter(this)


data class MillimolePerLiter(override val value: MeasurementUnitValue) : Concentration {

    override val unitName: String = "millimole-per-liter"

    override fun formatters(language: MeasurementUnitLanguage) = language.concentrationMillimolePerLiter
    override fun toItemPerCubicMeter() = ItemPerCubicMeter(value * ((6.02214076e+23 * (1e-3)) / (0.001)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = MillimolePerLiter(0.0)
        fun fromItemPerCubicMeter(value: ItemPerCubicMeter) = MillimolePerLiter((value.value - 0) / ((6.02214076e+23 * (1e-3)) / (0.001)))
    }

    operator fun plus(other: MillimolePerLiter): MillimolePerLiter = MillimolePerLiter(this.value + other.value)
    operator fun minus(other: MillimolePerLiter): MillimolePerLiter = MillimolePerLiter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): MillimolePerLiter = MillimolePerLiter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): MillimolePerLiter = MillimolePerLiter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): MillimolePerLiter = MillimolePerLiter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): MillimolePerLiter = MillimolePerLiter(this.value.times(other))
    override operator fun div(other: kotlin.Short): MillimolePerLiter = MillimolePerLiter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): MillimolePerLiter = MillimolePerLiter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): MillimolePerLiter = MillimolePerLiter(this.value.times(other))
    override operator fun div(other: kotlin.Int): MillimolePerLiter = MillimolePerLiter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): MillimolePerLiter = MillimolePerLiter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): MillimolePerLiter = MillimolePerLiter(this.value.times(other))
    override operator fun div(other: kotlin.Long): MillimolePerLiter = MillimolePerLiter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): MillimolePerLiter = MillimolePerLiter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): MillimolePerLiter = MillimolePerLiter(this.value.times(other))
    override operator fun div(other: kotlin.Float): MillimolePerLiter = MillimolePerLiter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): MillimolePerLiter = MillimolePerLiter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): MillimolePerLiter = MillimolePerLiter(this.value.times(other))
    override operator fun div(other: kotlin.Double): MillimolePerLiter = MillimolePerLiter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): MillimolePerLiter = MillimolePerLiter(this.value.rem(other))


    override operator fun unaryMinus(): MillimolePerLiter = MillimolePerLiter(-value)
    override operator fun unaryPlus(): MillimolePerLiter = this
    override operator fun inc(): MillimolePerLiter = MillimolePerLiter(this.value + 1)
    override operator fun dec(): MillimolePerLiter = MillimolePerLiter(this.value - 1)

    override fun round(): MillimolePerLiter = MillimolePerLiter(round(this.value))
    override fun truncate(): MillimolePerLiter = MillimolePerLiter(truncate(this.value))
    override fun floor(): MillimolePerLiter = MillimolePerLiter(floor(this.value))
    override fun ceil(): MillimolePerLiter = MillimolePerLiter(ceil(this.value))
    override fun abs(): MillimolePerLiter = MillimolePerLiter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is MillimolePerLiter -> this.value == other.value
        is Concentration -> this.toItemPerCubicMeter().value == other.toItemPerCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toItemPerCubicMeter().value.hashCode()


}

val kotlin.Byte.millimolePerLiter
    get() = MillimolePerLiter(this)


val kotlin.Short.millimolePerLiter
    get() = MillimolePerLiter(this)


val kotlin.Int.millimolePerLiter
    get() = MillimolePerLiter(this)


val kotlin.Long.millimolePerLiter
    get() = MillimolePerLiter(this)


val kotlin.Float.millimolePerLiter
    get() = MillimolePerLiter(this)


val kotlin.Double.millimolePerLiter
    get() = MillimolePerLiter(this)


data class ItemPerCubicMeter(override val value: MeasurementUnitValue) : Concentration {

    override val unitName: String = "item-per-cubic-meter"

    override fun formatters(language: MeasurementUnitLanguage) = language.concentrationItemPerCubicMeter
    override fun toItemPerCubicMeter() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = ItemPerCubicMeter(0.0)
        fun fromItemPerCubicMeter(value: ItemPerCubicMeter) = value
    }

    operator fun plus(other: ItemPerCubicMeter): ItemPerCubicMeter = ItemPerCubicMeter(this.value + other.value)
    operator fun minus(other: ItemPerCubicMeter): ItemPerCubicMeter = ItemPerCubicMeter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): ItemPerCubicMeter = ItemPerCubicMeter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): ItemPerCubicMeter = ItemPerCubicMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): ItemPerCubicMeter = ItemPerCubicMeter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): ItemPerCubicMeter = ItemPerCubicMeter(this.value.times(other))
    override operator fun div(other: kotlin.Short): ItemPerCubicMeter = ItemPerCubicMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): ItemPerCubicMeter = ItemPerCubicMeter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): ItemPerCubicMeter = ItemPerCubicMeter(this.value.times(other))
    override operator fun div(other: kotlin.Int): ItemPerCubicMeter = ItemPerCubicMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): ItemPerCubicMeter = ItemPerCubicMeter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): ItemPerCubicMeter = ItemPerCubicMeter(this.value.times(other))
    override operator fun div(other: kotlin.Long): ItemPerCubicMeter = ItemPerCubicMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): ItemPerCubicMeter = ItemPerCubicMeter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): ItemPerCubicMeter = ItemPerCubicMeter(this.value.times(other))
    override operator fun div(other: kotlin.Float): ItemPerCubicMeter = ItemPerCubicMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): ItemPerCubicMeter = ItemPerCubicMeter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): ItemPerCubicMeter = ItemPerCubicMeter(this.value.times(other))
    override operator fun div(other: kotlin.Double): ItemPerCubicMeter = ItemPerCubicMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): ItemPerCubicMeter = ItemPerCubicMeter(this.value.rem(other))


    override operator fun unaryMinus(): ItemPerCubicMeter = ItemPerCubicMeter(-value)
    override operator fun unaryPlus(): ItemPerCubicMeter = this
    override operator fun inc(): ItemPerCubicMeter = ItemPerCubicMeter(this.value + 1)
    override operator fun dec(): ItemPerCubicMeter = ItemPerCubicMeter(this.value - 1)

    override fun round(): ItemPerCubicMeter = ItemPerCubicMeter(round(this.value))
    override fun truncate(): ItemPerCubicMeter = ItemPerCubicMeter(truncate(this.value))
    override fun floor(): ItemPerCubicMeter = ItemPerCubicMeter(floor(this.value))
    override fun ceil(): ItemPerCubicMeter = ItemPerCubicMeter(ceil(this.value))
    override fun abs(): ItemPerCubicMeter = ItemPerCubicMeter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is ItemPerCubicMeter -> this.value == other.value
        is Concentration -> this.toItemPerCubicMeter().value == other.toItemPerCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toItemPerCubicMeter().value.hashCode()


}

val kotlin.Byte.itemPerCubicMeter
    get() = ItemPerCubicMeter(this)


val kotlin.Short.itemPerCubicMeter
    get() = ItemPerCubicMeter(this)


val kotlin.Int.itemPerCubicMeter
    get() = ItemPerCubicMeter(this)


val kotlin.Long.itemPerCubicMeter
    get() = ItemPerCubicMeter(this)


val kotlin.Float.itemPerCubicMeter
    get() = ItemPerCubicMeter(this)


val kotlin.Double.itemPerCubicMeter
    get() = ItemPerCubicMeter(this)
                            
