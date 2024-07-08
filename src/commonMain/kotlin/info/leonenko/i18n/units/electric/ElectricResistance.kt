package info.leonenko.i18n.units.electric
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

@Serializable(ElectricResistance.Serializer::class)
interface ElectricResistance : MeasurementUnit, Comparable<ElectricResistance> {
    override val measurement: String get() = "electric-resistance"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> ElectricResistance> = emptyMap()) : KSerializer<ElectricResistance> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("ElectricResistance", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): ElectricResistance {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected ElectricResistance unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "ohm" -> Ohm(value)
                "kilogram-square-meter-per-cubic-second-square-ampere" -> KilogramSquareMeterPerCubicSecondSquareAmpere(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown ElectricResistance unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: ElectricResistance) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toOhm(): Ohm {
        return Ohm.fromKilogramSquareMeterPerCubicSecondSquareAmpere(toKilogramSquareMeterPerCubicSecondSquareAmpere())
    }

    fun toKilogramSquareMeterPerCubicSecondSquareAmpere(): KilogramSquareMeterPerCubicSecondSquareAmpere
    override fun getBaseUnitValue() = toKilogramSquareMeterPerCubicSecondSquareAmpere().value

    companion object {
        val ZERO: ElectricResistance = KilogramSquareMeterPerCubicSecondSquareAmpere(0.0)

    }

    override operator fun compareTo(other: ElectricResistance): Int =
        this.toKilogramSquareMeterPerCubicSecondSquareAmpere().value.compareTo(other.toKilogramSquareMeterPerCubicSecondSquareAmpere().value)

    operator fun plus(other: ElectricResistance): KilogramSquareMeterPerCubicSecondSquareAmpere =
        this.toKilogramSquareMeterPerCubicSecondSquareAmpere() + other.toKilogramSquareMeterPerCubicSecondSquareAmpere()

    operator fun minus(other: ElectricResistance): KilogramSquareMeterPerCubicSecondSquareAmpere =
        this.toKilogramSquareMeterPerCubicSecondSquareAmpere() - other.toKilogramSquareMeterPerCubicSecondSquareAmpere()

    operator fun div(other: ElectricResistance): Double = this.toKilogramSquareMeterPerCubicSecondSquareAmpere().value / other.toKilogramSquareMeterPerCubicSecondSquareAmpere().value
    operator fun unaryMinus(): ElectricResistance
    operator fun unaryPlus(): ElectricResistance
    operator fun inc(): ElectricResistance
    operator fun dec(): ElectricResistance

    fun round(): ElectricResistance
    fun truncate(): ElectricResistance
    fun floor(): ElectricResistance
    fun ceil(): ElectricResistance
    fun abs(): ElectricResistance


    operator fun times(other: kotlin.Byte): ElectricResistance
    operator fun div(other: kotlin.Byte): ElectricResistance
    operator fun rem(other: kotlin.Byte): ElectricResistance


    operator fun times(other: kotlin.Short): ElectricResistance
    operator fun div(other: kotlin.Short): ElectricResistance
    operator fun rem(other: kotlin.Short): ElectricResistance


    operator fun times(other: kotlin.Int): ElectricResistance
    operator fun div(other: kotlin.Int): ElectricResistance
    operator fun rem(other: kotlin.Int): ElectricResistance


    operator fun times(other: kotlin.Long): ElectricResistance
    operator fun div(other: kotlin.Long): ElectricResistance
    operator fun rem(other: kotlin.Long): ElectricResistance


    operator fun times(other: kotlin.Float): ElectricResistance
    operator fun div(other: kotlin.Float): ElectricResistance
    operator fun rem(other: kotlin.Float): ElectricResistance


    operator fun times(other: kotlin.Double): ElectricResistance
    operator fun div(other: kotlin.Double): ElectricResistance
    operator fun rem(other: kotlin.Double): ElectricResistance

}

data class Ohm(override val value: MeasurementUnitValue) : ElectricResistance {

    override val unitName: String = "ohm"

    override fun formatters(language: MeasurementUnitLanguage) = language.electricOhm
    override fun toKilogramSquareMeterPerCubicSecondSquareAmpere() = KilogramSquareMeterPerCubicSecondSquareAmpere(value * (1) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Ohm(0.0)
        fun fromKilogramSquareMeterPerCubicSecondSquareAmpere(value: KilogramSquareMeterPerCubicSecondSquareAmpere) = Ohm((value.value - 0) / (1))
    }

    operator fun plus(other: Ohm): Ohm = Ohm(this.value + other.value)
    operator fun minus(other: Ohm): Ohm = Ohm(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Ohm = Ohm(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Ohm = Ohm(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Ohm = Ohm(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Ohm = Ohm(this.value.times(other))
    override operator fun div(other: kotlin.Short): Ohm = Ohm(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Ohm = Ohm(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Ohm = Ohm(this.value.times(other))
    override operator fun div(other: kotlin.Int): Ohm = Ohm(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Ohm = Ohm(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Ohm = Ohm(this.value.times(other))
    override operator fun div(other: kotlin.Long): Ohm = Ohm(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Ohm = Ohm(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Ohm = Ohm(this.value.times(other))
    override operator fun div(other: kotlin.Float): Ohm = Ohm(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Ohm = Ohm(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Ohm = Ohm(this.value.times(other))
    override operator fun div(other: kotlin.Double): Ohm = Ohm(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Ohm = Ohm(this.value.rem(other))


    override operator fun unaryMinus(): Ohm = Ohm(-value)
    override operator fun unaryPlus(): Ohm = this
    override operator fun inc(): Ohm = Ohm(this.value + 1)
    override operator fun dec(): Ohm = Ohm(this.value - 1)

    override fun round(): Ohm = Ohm(round(this.value))
    override fun truncate(): Ohm = Ohm(truncate(this.value))
    override fun floor(): Ohm = Ohm(floor(this.value))
    override fun ceil(): Ohm = Ohm(ceil(this.value))
    override fun abs(): Ohm = Ohm(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Ohm -> this.value == other.value
        is ElectricResistance -> this.toKilogramSquareMeterPerCubicSecondSquareAmpere().value == other.toKilogramSquareMeterPerCubicSecondSquareAmpere().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerCubicSecondSquareAmpere().value.hashCode()


}

val kotlin.Byte.ohm
    get() = Ohm(this)


val kotlin.Short.ohm
    get() = Ohm(this)


val kotlin.Int.ohm
    get() = Ohm(this)


val kotlin.Long.ohm
    get() = Ohm(this)


val kotlin.Float.ohm
    get() = Ohm(this)


val kotlin.Double.ohm
    get() = Ohm(this)


data class KilogramSquareMeterPerCubicSecondSquareAmpere(override val value: MeasurementUnitValue) : ElectricResistance {

    override val unitName: String = "kilogram-square-meter-per-cubic-second-square-ampere"

    override fun formatters(language: MeasurementUnitLanguage) = language.electricKilogramSquareMeterPerCubicSecondSquareAmpere
    override fun toKilogramSquareMeterPerCubicSecondSquareAmpere() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = KilogramSquareMeterPerCubicSecondSquareAmpere(0.0)
        fun fromKilogramSquareMeterPerCubicSecondSquareAmpere(value: KilogramSquareMeterPerCubicSecondSquareAmpere) = value
    }

    operator fun plus(other: KilogramSquareMeterPerCubicSecondSquareAmpere): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value + other.value)
    operator fun minus(other: KilogramSquareMeterPerCubicSecondSquareAmpere): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.times(other))
    override operator fun div(other: kotlin.Byte): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.times(other))
    override operator fun div(other: kotlin.Short): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.div(other))
    override operator fun rem(other: kotlin.Short): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.times(other))
    override operator fun div(other: kotlin.Int): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.div(other))
    override operator fun rem(other: kotlin.Int): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.times(other))
    override operator fun div(other: kotlin.Long): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.div(other))
    override operator fun rem(other: kotlin.Long): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.times(other))
    override operator fun div(other: kotlin.Float): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.div(other))
    override operator fun rem(other: kotlin.Float): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.rem(other))


    override operator fun times(other: kotlin.Double): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.times(other))
    override operator fun div(other: kotlin.Double): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.div(other))
    override operator fun rem(other: kotlin.Double): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value.rem(other))


    override operator fun unaryMinus(): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(-value)
    override operator fun unaryPlus(): KilogramSquareMeterPerCubicSecondSquareAmpere = this
    override operator fun inc(): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value + 1)
    override operator fun dec(): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(this.value - 1)

    override fun round(): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(round(this.value))
    override fun truncate(): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(truncate(this.value))
    override fun floor(): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(floor(this.value))
    override fun ceil(): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(ceil(this.value))
    override fun abs(): KilogramSquareMeterPerCubicSecondSquareAmpere = KilogramSquareMeterPerCubicSecondSquareAmpere(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is KilogramSquareMeterPerCubicSecondSquareAmpere -> this.value == other.value
        is ElectricResistance -> this.toKilogramSquareMeterPerCubicSecondSquareAmpere().value == other.toKilogramSquareMeterPerCubicSecondSquareAmpere().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerCubicSecondSquareAmpere().value.hashCode()


}

val kotlin.Byte.kilogramSquareMeterPerCubicSecondSquareAmpere
    get() = KilogramSquareMeterPerCubicSecondSquareAmpere(this)


val kotlin.Short.kilogramSquareMeterPerCubicSecondSquareAmpere
    get() = KilogramSquareMeterPerCubicSecondSquareAmpere(this)


val kotlin.Int.kilogramSquareMeterPerCubicSecondSquareAmpere
    get() = KilogramSquareMeterPerCubicSecondSquareAmpere(this)


val kotlin.Long.kilogramSquareMeterPerCubicSecondSquareAmpere
    get() = KilogramSquareMeterPerCubicSecondSquareAmpere(this)


val kotlin.Float.kilogramSquareMeterPerCubicSecondSquareAmpere
    get() = KilogramSquareMeterPerCubicSecondSquareAmpere(this)


val kotlin.Double.kilogramSquareMeterPerCubicSecondSquareAmpere
    get() = KilogramSquareMeterPerCubicSecondSquareAmpere(this)
                            
