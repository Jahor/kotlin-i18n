package info.leonenko.i18n.units.mass

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

@Serializable(Mass.Serializer::class)
interface Mass : MeasurementUnit, Comparable<Mass> {
    override val measurement: String get() = "mass"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Mass> = emptyMap()) : KSerializer<Mass> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())

        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Mass", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Mass {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Mass unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "tonne" -> Tonne(value)
                "kilogram" -> Kilogram(value)
                "gram" -> Gram(value)
                "milligram" -> Milligram(value)
                "microgram" -> Microgram(value)
                "ton" -> Ton(value)
                "stone" -> Stone(value)
                "pound" -> Pound(value)
                "ounce" -> Ounce(value)
                "ounce-troy" -> OunceTroy(value)
                "carat" -> Carat(value)
                "dalton" -> Dalton(value)
                "earth-mass" -> EarthMass(value)
                "solar-mass" -> SolarMass(value)
                "grain" -> Grain(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Mass unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Mass) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toTonne(): Tonne {
        return Tonne.fromKilogram(toKilogram())
    }

    fun toKilogram(): Kilogram
    override fun getBaseUnitValue() = toKilogram().value
    fun toGram(): Gram {
        return Gram.fromKilogram(toKilogram())
    }

    fun toMilligram(): Milligram {
        return Milligram.fromKilogram(toKilogram())
    }

    fun toMicrogram(): Microgram {
        return Microgram.fromKilogram(toKilogram())
    }

    fun toTon(): Ton {
        return Ton.fromKilogram(toKilogram())
    }

    fun toStone(): Stone {
        return Stone.fromKilogram(toKilogram())
    }

    fun toPound(): Pound {
        return Pound.fromKilogram(toKilogram())
    }

    fun toOunce(): Ounce {
        return Ounce.fromKilogram(toKilogram())
    }

    fun toOunceTroy(): OunceTroy {
        return OunceTroy.fromKilogram(toKilogram())
    }

    fun toCarat(): Carat {
        return Carat.fromKilogram(toKilogram())
    }

    fun toDalton(): Dalton {
        return Dalton.fromKilogram(toKilogram())
    }

    fun toEarthMass(): EarthMass {
        return EarthMass.fromKilogram(toKilogram())
    }

    fun toSolarMass(): SolarMass {
        return SolarMass.fromKilogram(toKilogram())
    }

    fun toGrain(): Grain {
        return Grain.fromKilogram(toKilogram())
    }

    companion object {
        val ZERO: Mass = Kilogram(0.0)

    }

    override operator fun compareTo(other: Mass): Int = this.toKilogram().value.compareTo(other.toKilogram().value)

    operator fun plus(other: Mass): Kilogram = this.toKilogram() + other.toKilogram()
    operator fun minus(other: Mass): Kilogram = this.toKilogram() - other.toKilogram()
    operator fun div(other: Mass): Double = this.toKilogram().value / other.toKilogram().value
    operator fun unaryMinus(): Mass
    operator fun unaryPlus(): Mass
    operator fun inc(): Mass
    operator fun dec(): Mass

    fun round(): Mass
    fun truncate(): Mass
    fun floor(): Mass
    fun ceil(): Mass
    fun abs(): Mass


    operator fun times(other: kotlin.Byte): Mass
    operator fun div(other: kotlin.Byte): Mass
    operator fun rem(other: kotlin.Byte): Mass


    operator fun times(other: kotlin.Short): Mass
    operator fun div(other: kotlin.Short): Mass
    operator fun rem(other: kotlin.Short): Mass


    operator fun times(other: kotlin.Int): Mass
    operator fun div(other: kotlin.Int): Mass
    operator fun rem(other: kotlin.Int): Mass


    operator fun times(other: kotlin.Long): Mass
    operator fun div(other: kotlin.Long): Mass
    operator fun rem(other: kotlin.Long): Mass


    operator fun times(other: kotlin.Float): Mass
    operator fun div(other: kotlin.Float): Mass
    operator fun rem(other: kotlin.Float): Mass


    operator fun times(other: kotlin.Double): Mass
    operator fun div(other: kotlin.Double): Mass
    operator fun rem(other: kotlin.Double): Mass

}

data class Tonne(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "tonne"

    override fun formatters(language: MeasurementUnitLanguage) = language.massTonne
    override fun toKilogram() = Kilogram(value * (1000) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Tonne(0.0)
        fun fromKilogram(value: Kilogram) = Tonne((value.value - 0) / (1000))
    }

    operator fun plus(other: Tonne): Tonne = Tonne(this.value + other.value)
    operator fun minus(other: Tonne): Tonne = Tonne(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Tonne = Tonne(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Tonne = Tonne(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Tonne = Tonne(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Tonne = Tonne(this.value.times(other))
    override operator fun div(other: kotlin.Short): Tonne = Tonne(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Tonne = Tonne(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Tonne = Tonne(this.value.times(other))
    override operator fun div(other: kotlin.Int): Tonne = Tonne(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Tonne = Tonne(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Tonne = Tonne(this.value.times(other))
    override operator fun div(other: kotlin.Long): Tonne = Tonne(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Tonne = Tonne(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Tonne = Tonne(this.value.times(other))
    override operator fun div(other: kotlin.Float): Tonne = Tonne(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Tonne = Tonne(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Tonne = Tonne(this.value.times(other))
    override operator fun div(other: kotlin.Double): Tonne = Tonne(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Tonne = Tonne(this.value.rem(other))


    override operator fun unaryMinus(): Tonne = Tonne(-value)
    override operator fun unaryPlus(): Tonne = this
    override operator fun inc(): Tonne = Tonne(this.value + 1)
    override operator fun dec(): Tonne = Tonne(this.value - 1)

    override fun round(): Tonne = Tonne(round(this.value))
    override fun truncate(): Tonne = Tonne(truncate(this.value))
    override fun floor(): Tonne = Tonne(floor(this.value))
    override fun ceil(): Tonne = Tonne(ceil(this.value))
    override fun abs(): Tonne = Tonne(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Tonne -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.tonne
    get() = Tonne(this)


val kotlin.Short.tonne
    get() = Tonne(this)


val kotlin.Int.tonne
    get() = Tonne(this)


val kotlin.Long.tonne
    get() = Tonne(this)


val kotlin.Float.tonne
    get() = Tonne(this)


val kotlin.Double.tonne
    get() = Tonne(this)


data class Kilogram(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "kilogram"

    override fun formatters(language: MeasurementUnitLanguage) = language.massKilogram
    override fun toKilogram() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Kilogram(0.0)
        fun fromKilogram(value: Kilogram) = value
    }

    operator fun plus(other: Kilogram): Kilogram = Kilogram(this.value + other.value)
    operator fun minus(other: Kilogram): Kilogram = Kilogram(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Kilogram = Kilogram(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Kilogram = Kilogram(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Kilogram = Kilogram(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Kilogram = Kilogram(this.value.times(other))
    override operator fun div(other: kotlin.Short): Kilogram = Kilogram(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Kilogram = Kilogram(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Kilogram = Kilogram(this.value.times(other))
    override operator fun div(other: kotlin.Int): Kilogram = Kilogram(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Kilogram = Kilogram(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Kilogram = Kilogram(this.value.times(other))
    override operator fun div(other: kotlin.Long): Kilogram = Kilogram(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Kilogram = Kilogram(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Kilogram = Kilogram(this.value.times(other))
    override operator fun div(other: kotlin.Float): Kilogram = Kilogram(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Kilogram = Kilogram(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Kilogram = Kilogram(this.value.times(other))
    override operator fun div(other: kotlin.Double): Kilogram = Kilogram(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Kilogram = Kilogram(this.value.rem(other))


    override operator fun unaryMinus(): Kilogram = Kilogram(-value)
    override operator fun unaryPlus(): Kilogram = this
    override operator fun inc(): Kilogram = Kilogram(this.value + 1)
    override operator fun dec(): Kilogram = Kilogram(this.value - 1)

    override fun round(): Kilogram = Kilogram(round(this.value))
    override fun truncate(): Kilogram = Kilogram(truncate(this.value))
    override fun floor(): Kilogram = Kilogram(floor(this.value))
    override fun ceil(): Kilogram = Kilogram(ceil(this.value))
    override fun abs(): Kilogram = Kilogram(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Kilogram -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.kilogram
    get() = Kilogram(this)


val kotlin.Short.kilogram
    get() = Kilogram(this)


val kotlin.Int.kilogram
    get() = Kilogram(this)


val kotlin.Long.kilogram
    get() = Kilogram(this)


val kotlin.Float.kilogram
    get() = Kilogram(this)


val kotlin.Double.kilogram
    get() = Kilogram(this)


data class Gram(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "gram"

    override fun formatters(language: MeasurementUnitLanguage) = language.massGram
    override fun toKilogram() = Kilogram(value * (0.001) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Gram(0.0)
        fun fromKilogram(value: Kilogram) = Gram((value.value - 0) / (0.001))
    }

    operator fun plus(other: Gram): Gram = Gram(this.value + other.value)
    operator fun minus(other: Gram): Gram = Gram(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Gram = Gram(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Gram = Gram(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Gram = Gram(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Gram = Gram(this.value.times(other))
    override operator fun div(other: kotlin.Short): Gram = Gram(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Gram = Gram(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Gram = Gram(this.value.times(other))
    override operator fun div(other: kotlin.Int): Gram = Gram(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Gram = Gram(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Gram = Gram(this.value.times(other))
    override operator fun div(other: kotlin.Long): Gram = Gram(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Gram = Gram(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Gram = Gram(this.value.times(other))
    override operator fun div(other: kotlin.Float): Gram = Gram(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Gram = Gram(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Gram = Gram(this.value.times(other))
    override operator fun div(other: kotlin.Double): Gram = Gram(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Gram = Gram(this.value.rem(other))


    override operator fun unaryMinus(): Gram = Gram(-value)
    override operator fun unaryPlus(): Gram = this
    override operator fun inc(): Gram = Gram(this.value + 1)
    override operator fun dec(): Gram = Gram(this.value - 1)

    override fun round(): Gram = Gram(round(this.value))
    override fun truncate(): Gram = Gram(truncate(this.value))
    override fun floor(): Gram = Gram(floor(this.value))
    override fun ceil(): Gram = Gram(ceil(this.value))
    override fun abs(): Gram = Gram(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Gram -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.gram
    get() = Gram(this)


val kotlin.Short.gram
    get() = Gram(this)


val kotlin.Int.gram
    get() = Gram(this)


val kotlin.Long.gram
    get() = Gram(this)


val kotlin.Float.gram
    get() = Gram(this)


val kotlin.Double.gram
    get() = Gram(this)


data class Milligram(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "milligram"

    override fun formatters(language: MeasurementUnitLanguage) = language.massMilligram
    override fun toKilogram() = Kilogram(value * (0.001 * (1e-3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Milligram(0.0)
        fun fromKilogram(value: Kilogram) = Milligram((value.value - 0) / (0.001 * (1e-3)))
    }

    operator fun plus(other: Milligram): Milligram = Milligram(this.value + other.value)
    operator fun minus(other: Milligram): Milligram = Milligram(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Milligram = Milligram(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Milligram = Milligram(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Milligram = Milligram(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Milligram = Milligram(this.value.times(other))
    override operator fun div(other: kotlin.Short): Milligram = Milligram(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Milligram = Milligram(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Milligram = Milligram(this.value.times(other))
    override operator fun div(other: kotlin.Int): Milligram = Milligram(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Milligram = Milligram(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Milligram = Milligram(this.value.times(other))
    override operator fun div(other: kotlin.Long): Milligram = Milligram(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Milligram = Milligram(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Milligram = Milligram(this.value.times(other))
    override operator fun div(other: kotlin.Float): Milligram = Milligram(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Milligram = Milligram(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Milligram = Milligram(this.value.times(other))
    override operator fun div(other: kotlin.Double): Milligram = Milligram(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Milligram = Milligram(this.value.rem(other))


    override operator fun unaryMinus(): Milligram = Milligram(-value)
    override operator fun unaryPlus(): Milligram = this
    override operator fun inc(): Milligram = Milligram(this.value + 1)
    override operator fun dec(): Milligram = Milligram(this.value - 1)

    override fun round(): Milligram = Milligram(round(this.value))
    override fun truncate(): Milligram = Milligram(truncate(this.value))
    override fun floor(): Milligram = Milligram(floor(this.value))
    override fun ceil(): Milligram = Milligram(ceil(this.value))
    override fun abs(): Milligram = Milligram(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Milligram -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.milligram
    get() = Milligram(this)


val kotlin.Short.milligram
    get() = Milligram(this)


val kotlin.Int.milligram
    get() = Milligram(this)


val kotlin.Long.milligram
    get() = Milligram(this)


val kotlin.Float.milligram
    get() = Milligram(this)


val kotlin.Double.milligram
    get() = Milligram(this)


data class Microgram(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "microgram"

    override fun formatters(language: MeasurementUnitLanguage) = language.massMicrogram
    override fun toKilogram() = Kilogram(value * (0.001 * (1e-6)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Microgram(0.0)
        fun fromKilogram(value: Kilogram) = Microgram((value.value - 0) / (0.001 * (1e-6)))
    }

    operator fun plus(other: Microgram): Microgram = Microgram(this.value + other.value)
    operator fun minus(other: Microgram): Microgram = Microgram(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Microgram = Microgram(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Microgram = Microgram(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Microgram = Microgram(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Microgram = Microgram(this.value.times(other))
    override operator fun div(other: kotlin.Short): Microgram = Microgram(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Microgram = Microgram(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Microgram = Microgram(this.value.times(other))
    override operator fun div(other: kotlin.Int): Microgram = Microgram(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Microgram = Microgram(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Microgram = Microgram(this.value.times(other))
    override operator fun div(other: kotlin.Long): Microgram = Microgram(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Microgram = Microgram(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Microgram = Microgram(this.value.times(other))
    override operator fun div(other: kotlin.Float): Microgram = Microgram(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Microgram = Microgram(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Microgram = Microgram(this.value.times(other))
    override operator fun div(other: kotlin.Double): Microgram = Microgram(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Microgram = Microgram(this.value.rem(other))


    override operator fun unaryMinus(): Microgram = Microgram(-value)
    override operator fun unaryPlus(): Microgram = this
    override operator fun inc(): Microgram = Microgram(this.value + 1)
    override operator fun dec(): Microgram = Microgram(this.value - 1)

    override fun round(): Microgram = Microgram(round(this.value))
    override fun truncate(): Microgram = Microgram(truncate(this.value))
    override fun floor(): Microgram = Microgram(floor(this.value))
    override fun ceil(): Microgram = Microgram(ceil(this.value))
    override fun abs(): Microgram = Microgram(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Microgram -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.microgram
    get() = Microgram(this)


val kotlin.Short.microgram
    get() = Microgram(this)


val kotlin.Int.microgram
    get() = Microgram(this)


val kotlin.Long.microgram
    get() = Microgram(this)


val kotlin.Float.microgram
    get() = Microgram(this)


val kotlin.Double.microgram
    get() = Microgram(this)


data class Ton(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "ton"

    override fun formatters(language: MeasurementUnitLanguage) = language.massTon
    override fun toKilogram() = Kilogram(value * (907.18474) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Ton(0.0)
        fun fromKilogram(value: Kilogram) = Ton((value.value - 0) / (907.18474))
    }

    operator fun plus(other: Ton): Ton = Ton(this.value + other.value)
    operator fun minus(other: Ton): Ton = Ton(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Ton = Ton(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Ton = Ton(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Ton = Ton(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Ton = Ton(this.value.times(other))
    override operator fun div(other: kotlin.Short): Ton = Ton(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Ton = Ton(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Ton = Ton(this.value.times(other))
    override operator fun div(other: kotlin.Int): Ton = Ton(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Ton = Ton(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Ton = Ton(this.value.times(other))
    override operator fun div(other: kotlin.Long): Ton = Ton(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Ton = Ton(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Ton = Ton(this.value.times(other))
    override operator fun div(other: kotlin.Float): Ton = Ton(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Ton = Ton(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Ton = Ton(this.value.times(other))
    override operator fun div(other: kotlin.Double): Ton = Ton(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Ton = Ton(this.value.rem(other))


    override operator fun unaryMinus(): Ton = Ton(-value)
    override operator fun unaryPlus(): Ton = this
    override operator fun inc(): Ton = Ton(this.value + 1)
    override operator fun dec(): Ton = Ton(this.value - 1)

    override fun round(): Ton = Ton(round(this.value))
    override fun truncate(): Ton = Ton(truncate(this.value))
    override fun floor(): Ton = Ton(floor(this.value))
    override fun ceil(): Ton = Ton(ceil(this.value))
    override fun abs(): Ton = Ton(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Ton -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.ton
    get() = Ton(this)


val kotlin.Short.ton
    get() = Ton(this)


val kotlin.Int.ton
    get() = Ton(this)


val kotlin.Long.ton
    get() = Ton(this)


val kotlin.Float.ton
    get() = Ton(this)


val kotlin.Double.ton
    get() = Ton(this)


data class Stone(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "stone"

    override fun formatters(language: MeasurementUnitLanguage) = language.massStone
    override fun toKilogram() = Kilogram(value * (6.35029318) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Stone(0.0)
        fun fromKilogram(value: Kilogram) = Stone((value.value - 0) / (6.35029318))
    }

    operator fun plus(other: Stone): Stone = Stone(this.value + other.value)
    operator fun minus(other: Stone): Stone = Stone(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Stone = Stone(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Stone = Stone(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Stone = Stone(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Stone = Stone(this.value.times(other))
    override operator fun div(other: kotlin.Short): Stone = Stone(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Stone = Stone(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Stone = Stone(this.value.times(other))
    override operator fun div(other: kotlin.Int): Stone = Stone(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Stone = Stone(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Stone = Stone(this.value.times(other))
    override operator fun div(other: kotlin.Long): Stone = Stone(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Stone = Stone(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Stone = Stone(this.value.times(other))
    override operator fun div(other: kotlin.Float): Stone = Stone(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Stone = Stone(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Stone = Stone(this.value.times(other))
    override operator fun div(other: kotlin.Double): Stone = Stone(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Stone = Stone(this.value.rem(other))


    override operator fun unaryMinus(): Stone = Stone(-value)
    override operator fun unaryPlus(): Stone = this
    override operator fun inc(): Stone = Stone(this.value + 1)
    override operator fun dec(): Stone = Stone(this.value - 1)

    override fun round(): Stone = Stone(round(this.value))
    override fun truncate(): Stone = Stone(truncate(this.value))
    override fun floor(): Stone = Stone(floor(this.value))
    override fun ceil(): Stone = Stone(ceil(this.value))
    override fun abs(): Stone = Stone(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Stone -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.stone
    get() = Stone(this)


val kotlin.Short.stone
    get() = Stone(this)


val kotlin.Int.stone
    get() = Stone(this)


val kotlin.Long.stone
    get() = Stone(this)


val kotlin.Float.stone
    get() = Stone(this)


val kotlin.Double.stone
    get() = Stone(this)


data class Pound(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "pound"

    override fun formatters(language: MeasurementUnitLanguage) = language.massPound
    override fun toKilogram() = Kilogram(value * (0.45359237) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Pound(0.0)
        fun fromKilogram(value: Kilogram) = Pound((value.value - 0) / (0.45359237))
    }

    operator fun plus(other: Pound): Pound = Pound(this.value + other.value)
    operator fun minus(other: Pound): Pound = Pound(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Pound = Pound(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Pound = Pound(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Pound = Pound(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Pound = Pound(this.value.times(other))
    override operator fun div(other: kotlin.Short): Pound = Pound(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Pound = Pound(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Pound = Pound(this.value.times(other))
    override operator fun div(other: kotlin.Int): Pound = Pound(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Pound = Pound(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Pound = Pound(this.value.times(other))
    override operator fun div(other: kotlin.Long): Pound = Pound(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Pound = Pound(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Pound = Pound(this.value.times(other))
    override operator fun div(other: kotlin.Float): Pound = Pound(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Pound = Pound(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Pound = Pound(this.value.times(other))
    override operator fun div(other: kotlin.Double): Pound = Pound(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Pound = Pound(this.value.rem(other))


    override operator fun unaryMinus(): Pound = Pound(-value)
    override operator fun unaryPlus(): Pound = this
    override operator fun inc(): Pound = Pound(this.value + 1)
    override operator fun dec(): Pound = Pound(this.value - 1)

    override fun round(): Pound = Pound(round(this.value))
    override fun truncate(): Pound = Pound(truncate(this.value))
    override fun floor(): Pound = Pound(floor(this.value))
    override fun ceil(): Pound = Pound(ceil(this.value))
    override fun abs(): Pound = Pound(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Pound -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.pound
    get() = Pound(this)


val kotlin.Short.pound
    get() = Pound(this)


val kotlin.Int.pound
    get() = Pound(this)


val kotlin.Long.pound
    get() = Pound(this)


val kotlin.Float.pound
    get() = Pound(this)


val kotlin.Double.pound
    get() = Pound(this)


data class Ounce(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "ounce"

    override fun formatters(language: MeasurementUnitLanguage) = language.massOunce
    override fun toKilogram() = Kilogram(value * (0.028349523125) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Ounce(0.0)
        fun fromKilogram(value: Kilogram) = Ounce((value.value - 0) / (0.028349523125))
    }

    operator fun plus(other: Ounce): Ounce = Ounce(this.value + other.value)
    operator fun minus(other: Ounce): Ounce = Ounce(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Ounce = Ounce(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Ounce = Ounce(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Ounce = Ounce(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Ounce = Ounce(this.value.times(other))
    override operator fun div(other: kotlin.Short): Ounce = Ounce(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Ounce = Ounce(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Ounce = Ounce(this.value.times(other))
    override operator fun div(other: kotlin.Int): Ounce = Ounce(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Ounce = Ounce(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Ounce = Ounce(this.value.times(other))
    override operator fun div(other: kotlin.Long): Ounce = Ounce(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Ounce = Ounce(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Ounce = Ounce(this.value.times(other))
    override operator fun div(other: kotlin.Float): Ounce = Ounce(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Ounce = Ounce(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Ounce = Ounce(this.value.times(other))
    override operator fun div(other: kotlin.Double): Ounce = Ounce(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Ounce = Ounce(this.value.rem(other))


    override operator fun unaryMinus(): Ounce = Ounce(-value)
    override operator fun unaryPlus(): Ounce = this
    override operator fun inc(): Ounce = Ounce(this.value + 1)
    override operator fun dec(): Ounce = Ounce(this.value - 1)

    override fun round(): Ounce = Ounce(round(this.value))
    override fun truncate(): Ounce = Ounce(truncate(this.value))
    override fun floor(): Ounce = Ounce(floor(this.value))
    override fun ceil(): Ounce = Ounce(ceil(this.value))
    override fun abs(): Ounce = Ounce(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Ounce -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.ounce
    get() = Ounce(this)


val kotlin.Short.ounce
    get() = Ounce(this)


val kotlin.Int.ounce
    get() = Ounce(this)


val kotlin.Long.ounce
    get() = Ounce(this)


val kotlin.Float.ounce
    get() = Ounce(this)


val kotlin.Double.ounce
    get() = Ounce(this)


data class OunceTroy(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "ounce-troy"

    override fun formatters(language: MeasurementUnitLanguage) = language.massOunceTroy
    override fun toKilogram() = Kilogram(value * (0.03110348) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = OunceTroy(0.0)
        fun fromKilogram(value: Kilogram) = OunceTroy((value.value - 0) / (0.03110348))
    }

    operator fun plus(other: OunceTroy): OunceTroy = OunceTroy(this.value + other.value)
    operator fun minus(other: OunceTroy): OunceTroy = OunceTroy(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): OunceTroy = OunceTroy(this.value.times(other))
    override operator fun div(other: kotlin.Byte): OunceTroy = OunceTroy(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): OunceTroy = OunceTroy(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): OunceTroy = OunceTroy(this.value.times(other))
    override operator fun div(other: kotlin.Short): OunceTroy = OunceTroy(this.value.div(other))
    override operator fun rem(other: kotlin.Short): OunceTroy = OunceTroy(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): OunceTroy = OunceTroy(this.value.times(other))
    override operator fun div(other: kotlin.Int): OunceTroy = OunceTroy(this.value.div(other))
    override operator fun rem(other: kotlin.Int): OunceTroy = OunceTroy(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): OunceTroy = OunceTroy(this.value.times(other))
    override operator fun div(other: kotlin.Long): OunceTroy = OunceTroy(this.value.div(other))
    override operator fun rem(other: kotlin.Long): OunceTroy = OunceTroy(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): OunceTroy = OunceTroy(this.value.times(other))
    override operator fun div(other: kotlin.Float): OunceTroy = OunceTroy(this.value.div(other))
    override operator fun rem(other: kotlin.Float): OunceTroy = OunceTroy(this.value.rem(other))


    override operator fun times(other: kotlin.Double): OunceTroy = OunceTroy(this.value.times(other))
    override operator fun div(other: kotlin.Double): OunceTroy = OunceTroy(this.value.div(other))
    override operator fun rem(other: kotlin.Double): OunceTroy = OunceTroy(this.value.rem(other))


    override operator fun unaryMinus(): OunceTroy = OunceTroy(-value)
    override operator fun unaryPlus(): OunceTroy = this
    override operator fun inc(): OunceTroy = OunceTroy(this.value + 1)
    override operator fun dec(): OunceTroy = OunceTroy(this.value - 1)

    override fun round(): OunceTroy = OunceTroy(round(this.value))
    override fun truncate(): OunceTroy = OunceTroy(truncate(this.value))
    override fun floor(): OunceTroy = OunceTroy(floor(this.value))
    override fun ceil(): OunceTroy = OunceTroy(ceil(this.value))
    override fun abs(): OunceTroy = OunceTroy(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is OunceTroy -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.ounceTroy
    get() = OunceTroy(this)


val kotlin.Short.ounceTroy
    get() = OunceTroy(this)


val kotlin.Int.ounceTroy
    get() = OunceTroy(this)


val kotlin.Long.ounceTroy
    get() = OunceTroy(this)


val kotlin.Float.ounceTroy
    get() = OunceTroy(this)


val kotlin.Double.ounceTroy
    get() = OunceTroy(this)


data class Carat(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "carat"

    override fun formatters(language: MeasurementUnitLanguage) = language.massCarat
    override fun toKilogram() = Kilogram(value * (0.0002) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Carat(0.0)
        fun fromKilogram(value: Kilogram) = Carat((value.value - 0) / (0.0002))
    }

    operator fun plus(other: Carat): Carat = Carat(this.value + other.value)
    operator fun minus(other: Carat): Carat = Carat(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Carat = Carat(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Carat = Carat(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Carat = Carat(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Carat = Carat(this.value.times(other))
    override operator fun div(other: kotlin.Short): Carat = Carat(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Carat = Carat(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Carat = Carat(this.value.times(other))
    override operator fun div(other: kotlin.Int): Carat = Carat(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Carat = Carat(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Carat = Carat(this.value.times(other))
    override operator fun div(other: kotlin.Long): Carat = Carat(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Carat = Carat(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Carat = Carat(this.value.times(other))
    override operator fun div(other: kotlin.Float): Carat = Carat(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Carat = Carat(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Carat = Carat(this.value.times(other))
    override operator fun div(other: kotlin.Double): Carat = Carat(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Carat = Carat(this.value.rem(other))


    override operator fun unaryMinus(): Carat = Carat(-value)
    override operator fun unaryPlus(): Carat = this
    override operator fun inc(): Carat = Carat(this.value + 1)
    override operator fun dec(): Carat = Carat(this.value - 1)

    override fun round(): Carat = Carat(round(this.value))
    override fun truncate(): Carat = Carat(truncate(this.value))
    override fun floor(): Carat = Carat(floor(this.value))
    override fun ceil(): Carat = Carat(ceil(this.value))
    override fun abs(): Carat = Carat(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Carat -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.carat
    get() = Carat(this)


val kotlin.Short.carat
    get() = Carat(this)


val kotlin.Int.carat
    get() = Carat(this)


val kotlin.Long.carat
    get() = Carat(this)


val kotlin.Float.carat
    get() = Carat(this)


val kotlin.Double.carat
    get() = Carat(this)


data class Dalton(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "dalton"

    override fun formatters(language: MeasurementUnitLanguage) = language.massDalton
    override fun toKilogram() = Kilogram(value * (1.66053878283e-27) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Dalton(0.0)
        fun fromKilogram(value: Kilogram) = Dalton((value.value - 0) / (1.66053878283e-27))
    }

    operator fun plus(other: Dalton): Dalton = Dalton(this.value + other.value)
    operator fun minus(other: Dalton): Dalton = Dalton(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Dalton = Dalton(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Dalton = Dalton(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Dalton = Dalton(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Dalton = Dalton(this.value.times(other))
    override operator fun div(other: kotlin.Short): Dalton = Dalton(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Dalton = Dalton(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Dalton = Dalton(this.value.times(other))
    override operator fun div(other: kotlin.Int): Dalton = Dalton(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Dalton = Dalton(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Dalton = Dalton(this.value.times(other))
    override operator fun div(other: kotlin.Long): Dalton = Dalton(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Dalton = Dalton(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Dalton = Dalton(this.value.times(other))
    override operator fun div(other: kotlin.Float): Dalton = Dalton(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Dalton = Dalton(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Dalton = Dalton(this.value.times(other))
    override operator fun div(other: kotlin.Double): Dalton = Dalton(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Dalton = Dalton(this.value.rem(other))


    override operator fun unaryMinus(): Dalton = Dalton(-value)
    override operator fun unaryPlus(): Dalton = this
    override operator fun inc(): Dalton = Dalton(this.value + 1)
    override operator fun dec(): Dalton = Dalton(this.value - 1)

    override fun round(): Dalton = Dalton(round(this.value))
    override fun truncate(): Dalton = Dalton(truncate(this.value))
    override fun floor(): Dalton = Dalton(floor(this.value))
    override fun ceil(): Dalton = Dalton(ceil(this.value))
    override fun abs(): Dalton = Dalton(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Dalton -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.dalton
    get() = Dalton(this)


val kotlin.Short.dalton
    get() = Dalton(this)


val kotlin.Int.dalton
    get() = Dalton(this)


val kotlin.Long.dalton
    get() = Dalton(this)


val kotlin.Float.dalton
    get() = Dalton(this)


val kotlin.Double.dalton
    get() = Dalton(this)


data class EarthMass(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "earth-mass"

    override fun formatters(language: MeasurementUnitLanguage) = language.massEarthMass
    override fun toKilogram() = Kilogram(value * (5.9722e+24) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = EarthMass(0.0)
        fun fromKilogram(value: Kilogram) = EarthMass((value.value - 0) / (5.9722e+24))
    }

    operator fun plus(other: EarthMass): EarthMass = EarthMass(this.value + other.value)
    operator fun minus(other: EarthMass): EarthMass = EarthMass(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): EarthMass = EarthMass(this.value.times(other))
    override operator fun div(other: kotlin.Byte): EarthMass = EarthMass(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): EarthMass = EarthMass(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): EarthMass = EarthMass(this.value.times(other))
    override operator fun div(other: kotlin.Short): EarthMass = EarthMass(this.value.div(other))
    override operator fun rem(other: kotlin.Short): EarthMass = EarthMass(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): EarthMass = EarthMass(this.value.times(other))
    override operator fun div(other: kotlin.Int): EarthMass = EarthMass(this.value.div(other))
    override operator fun rem(other: kotlin.Int): EarthMass = EarthMass(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): EarthMass = EarthMass(this.value.times(other))
    override operator fun div(other: kotlin.Long): EarthMass = EarthMass(this.value.div(other))
    override operator fun rem(other: kotlin.Long): EarthMass = EarthMass(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): EarthMass = EarthMass(this.value.times(other))
    override operator fun div(other: kotlin.Float): EarthMass = EarthMass(this.value.div(other))
    override operator fun rem(other: kotlin.Float): EarthMass = EarthMass(this.value.rem(other))


    override operator fun times(other: kotlin.Double): EarthMass = EarthMass(this.value.times(other))
    override operator fun div(other: kotlin.Double): EarthMass = EarthMass(this.value.div(other))
    override operator fun rem(other: kotlin.Double): EarthMass = EarthMass(this.value.rem(other))


    override operator fun unaryMinus(): EarthMass = EarthMass(-value)
    override operator fun unaryPlus(): EarthMass = this
    override operator fun inc(): EarthMass = EarthMass(this.value + 1)
    override operator fun dec(): EarthMass = EarthMass(this.value - 1)

    override fun round(): EarthMass = EarthMass(round(this.value))
    override fun truncate(): EarthMass = EarthMass(truncate(this.value))
    override fun floor(): EarthMass = EarthMass(floor(this.value))
    override fun ceil(): EarthMass = EarthMass(ceil(this.value))
    override fun abs(): EarthMass = EarthMass(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is EarthMass -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.earthMass
    get() = EarthMass(this)


val kotlin.Short.earthMass
    get() = EarthMass(this)


val kotlin.Int.earthMass
    get() = EarthMass(this)


val kotlin.Long.earthMass
    get() = EarthMass(this)


val kotlin.Float.earthMass
    get() = EarthMass(this)


val kotlin.Double.earthMass
    get() = EarthMass(this)


data class SolarMass(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "solar-mass"

    override fun formatters(language: MeasurementUnitLanguage) = language.massSolarMass
    override fun toKilogram() = Kilogram(value * (1.98847e+30) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = SolarMass(0.0)
        fun fromKilogram(value: Kilogram) = SolarMass((value.value - 0) / (1.98847e+30))
    }

    operator fun plus(other: SolarMass): SolarMass = SolarMass(this.value + other.value)
    operator fun minus(other: SolarMass): SolarMass = SolarMass(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): SolarMass = SolarMass(this.value.times(other))
    override operator fun div(other: kotlin.Byte): SolarMass = SolarMass(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): SolarMass = SolarMass(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): SolarMass = SolarMass(this.value.times(other))
    override operator fun div(other: kotlin.Short): SolarMass = SolarMass(this.value.div(other))
    override operator fun rem(other: kotlin.Short): SolarMass = SolarMass(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): SolarMass = SolarMass(this.value.times(other))
    override operator fun div(other: kotlin.Int): SolarMass = SolarMass(this.value.div(other))
    override operator fun rem(other: kotlin.Int): SolarMass = SolarMass(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): SolarMass = SolarMass(this.value.times(other))
    override operator fun div(other: kotlin.Long): SolarMass = SolarMass(this.value.div(other))
    override operator fun rem(other: kotlin.Long): SolarMass = SolarMass(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): SolarMass = SolarMass(this.value.times(other))
    override operator fun div(other: kotlin.Float): SolarMass = SolarMass(this.value.div(other))
    override operator fun rem(other: kotlin.Float): SolarMass = SolarMass(this.value.rem(other))


    override operator fun times(other: kotlin.Double): SolarMass = SolarMass(this.value.times(other))
    override operator fun div(other: kotlin.Double): SolarMass = SolarMass(this.value.div(other))
    override operator fun rem(other: kotlin.Double): SolarMass = SolarMass(this.value.rem(other))


    override operator fun unaryMinus(): SolarMass = SolarMass(-value)
    override operator fun unaryPlus(): SolarMass = this
    override operator fun inc(): SolarMass = SolarMass(this.value + 1)
    override operator fun dec(): SolarMass = SolarMass(this.value - 1)

    override fun round(): SolarMass = SolarMass(round(this.value))
    override fun truncate(): SolarMass = SolarMass(truncate(this.value))
    override fun floor(): SolarMass = SolarMass(floor(this.value))
    override fun ceil(): SolarMass = SolarMass(ceil(this.value))
    override fun abs(): SolarMass = SolarMass(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is SolarMass -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.solarMass
    get() = SolarMass(this)


val kotlin.Short.solarMass
    get() = SolarMass(this)


val kotlin.Int.solarMass
    get() = SolarMass(this)


val kotlin.Long.solarMass
    get() = SolarMass(this)


val kotlin.Float.solarMass
    get() = SolarMass(this)


val kotlin.Double.solarMass
    get() = SolarMass(this)


data class Grain(override val value: MeasurementUnitValue) : Mass {

    override val unitName: String = "grain"

    override fun formatters(language: MeasurementUnitLanguage) = language.massGrain
    override fun toKilogram() = Kilogram(value * (6.479891000000001e-05) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Grain(0.0)
        fun fromKilogram(value: Kilogram) = Grain((value.value - 0) / (6.479891000000001e-05))
    }

    operator fun plus(other: Grain): Grain = Grain(this.value + other.value)
    operator fun minus(other: Grain): Grain = Grain(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Grain = Grain(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Grain = Grain(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Grain = Grain(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Grain = Grain(this.value.times(other))
    override operator fun div(other: kotlin.Short): Grain = Grain(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Grain = Grain(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Grain = Grain(this.value.times(other))
    override operator fun div(other: kotlin.Int): Grain = Grain(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Grain = Grain(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Grain = Grain(this.value.times(other))
    override operator fun div(other: kotlin.Long): Grain = Grain(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Grain = Grain(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Grain = Grain(this.value.times(other))
    override operator fun div(other: kotlin.Float): Grain = Grain(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Grain = Grain(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Grain = Grain(this.value.times(other))
    override operator fun div(other: kotlin.Double): Grain = Grain(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Grain = Grain(this.value.rem(other))


    override operator fun unaryMinus(): Grain = Grain(-value)
    override operator fun unaryPlus(): Grain = this
    override operator fun inc(): Grain = Grain(this.value + 1)
    override operator fun dec(): Grain = Grain(this.value - 1)

    override fun round(): Grain = Grain(round(this.value))
    override fun truncate(): Grain = Grain(truncate(this.value))
    override fun floor(): Grain = Grain(floor(this.value))
    override fun ceil(): Grain = Grain(ceil(this.value))
    override fun abs(): Grain = Grain(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Grain -> this.value == other.value
        is Mass -> this.toKilogram().value == other.toKilogram().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogram().value.hashCode()


}

val kotlin.Byte.grain
    get() = Grain(this)


val kotlin.Short.grain
    get() = Grain(this)


val kotlin.Int.grain
    get() = Grain(this)


val kotlin.Long.grain
    get() = Grain(this)


val kotlin.Float.grain
    get() = Grain(this)


val kotlin.Double.grain
    get() = Grain(this)
                            
