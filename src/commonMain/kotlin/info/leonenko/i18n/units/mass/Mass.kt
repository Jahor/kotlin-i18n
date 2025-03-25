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


    operator fun times(other: Byte): Mass
    operator fun div(other: Byte): Mass
    operator fun rem(other: Byte): Mass


    operator fun times(other: Short): Mass
    operator fun div(other: Short): Mass
    operator fun rem(other: Short): Mass


    operator fun times(other: Int): Mass
    operator fun div(other: Int): Mass
    operator fun rem(other: Int): Mass


    operator fun times(other: Long): Mass
    operator fun div(other: Long): Mass
    operator fun rem(other: Long): Mass


    operator fun times(other: Float): Mass
    operator fun div(other: Float): Mass
    operator fun rem(other: Float): Mass


    operator fun times(other: Double): Mass
    operator fun div(other: Double): Mass
    operator fun rem(other: Double): Mass

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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Tonne = Tonne(this.value.times(other))
    override operator fun div(other: Byte): Tonne = Tonne(this.value.div(other))
    override operator fun rem(other: Byte): Tonne = Tonne(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Tonne = Tonne(this.value.times(other))
    override operator fun div(other: Short): Tonne = Tonne(this.value.div(other))
    override operator fun rem(other: Short): Tonne = Tonne(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Tonne = Tonne(this.value.times(other))
    override operator fun div(other: Int): Tonne = Tonne(this.value.div(other))
    override operator fun rem(other: Int): Tonne = Tonne(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Tonne = Tonne(this.value.times(other))
    override operator fun div(other: Long): Tonne = Tonne(this.value.div(other))
    override operator fun rem(other: Long): Tonne = Tonne(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Tonne = Tonne(this.value.times(other))
    override operator fun div(other: Float): Tonne = Tonne(this.value.div(other))
    override operator fun rem(other: Float): Tonne = Tonne(this.value.rem(other))


    override operator fun times(other: Double): Tonne = Tonne(this.value.times(other))
    override operator fun div(other: Double): Tonne = Tonne(this.value.div(other))
    override operator fun rem(other: Double): Tonne = Tonne(this.value.rem(other))


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

val Byte.tonne
    get() = Tonne(this)


val Short.tonne
    get() = Tonne(this)


val Int.tonne
    get() = Tonne(this)


val Long.tonne
    get() = Tonne(this)


val Float.tonne
    get() = Tonne(this)


val Double.tonne
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Kilogram = Kilogram(this.value.times(other))
    override operator fun div(other: Byte): Kilogram = Kilogram(this.value.div(other))
    override operator fun rem(other: Byte): Kilogram = Kilogram(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Kilogram = Kilogram(this.value.times(other))
    override operator fun div(other: Short): Kilogram = Kilogram(this.value.div(other))
    override operator fun rem(other: Short): Kilogram = Kilogram(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Kilogram = Kilogram(this.value.times(other))
    override operator fun div(other: Int): Kilogram = Kilogram(this.value.div(other))
    override operator fun rem(other: Int): Kilogram = Kilogram(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Kilogram = Kilogram(this.value.times(other))
    override operator fun div(other: Long): Kilogram = Kilogram(this.value.div(other))
    override operator fun rem(other: Long): Kilogram = Kilogram(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Kilogram = Kilogram(this.value.times(other))
    override operator fun div(other: Float): Kilogram = Kilogram(this.value.div(other))
    override operator fun rem(other: Float): Kilogram = Kilogram(this.value.rem(other))


    override operator fun times(other: Double): Kilogram = Kilogram(this.value.times(other))
    override operator fun div(other: Double): Kilogram = Kilogram(this.value.div(other))
    override operator fun rem(other: Double): Kilogram = Kilogram(this.value.rem(other))


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

val Byte.kilogram
    get() = Kilogram(this)


val Short.kilogram
    get() = Kilogram(this)


val Int.kilogram
    get() = Kilogram(this)


val Long.kilogram
    get() = Kilogram(this)


val Float.kilogram
    get() = Kilogram(this)


val Double.kilogram
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Gram = Gram(this.value.times(other))
    override operator fun div(other: Byte): Gram = Gram(this.value.div(other))
    override operator fun rem(other: Byte): Gram = Gram(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Gram = Gram(this.value.times(other))
    override operator fun div(other: Short): Gram = Gram(this.value.div(other))
    override operator fun rem(other: Short): Gram = Gram(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Gram = Gram(this.value.times(other))
    override operator fun div(other: Int): Gram = Gram(this.value.div(other))
    override operator fun rem(other: Int): Gram = Gram(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Gram = Gram(this.value.times(other))
    override operator fun div(other: Long): Gram = Gram(this.value.div(other))
    override operator fun rem(other: Long): Gram = Gram(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Gram = Gram(this.value.times(other))
    override operator fun div(other: Float): Gram = Gram(this.value.div(other))
    override operator fun rem(other: Float): Gram = Gram(this.value.rem(other))


    override operator fun times(other: Double): Gram = Gram(this.value.times(other))
    override operator fun div(other: Double): Gram = Gram(this.value.div(other))
    override operator fun rem(other: Double): Gram = Gram(this.value.rem(other))


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

val Byte.gram
    get() = Gram(this)


val Short.gram
    get() = Gram(this)


val Int.gram
    get() = Gram(this)


val Long.gram
    get() = Gram(this)


val Float.gram
    get() = Gram(this)


val Double.gram
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Milligram = Milligram(this.value.times(other))
    override operator fun div(other: Byte): Milligram = Milligram(this.value.div(other))
    override operator fun rem(other: Byte): Milligram = Milligram(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Milligram = Milligram(this.value.times(other))
    override operator fun div(other: Short): Milligram = Milligram(this.value.div(other))
    override operator fun rem(other: Short): Milligram = Milligram(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Milligram = Milligram(this.value.times(other))
    override operator fun div(other: Int): Milligram = Milligram(this.value.div(other))
    override operator fun rem(other: Int): Milligram = Milligram(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Milligram = Milligram(this.value.times(other))
    override operator fun div(other: Long): Milligram = Milligram(this.value.div(other))
    override operator fun rem(other: Long): Milligram = Milligram(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Milligram = Milligram(this.value.times(other))
    override operator fun div(other: Float): Milligram = Milligram(this.value.div(other))
    override operator fun rem(other: Float): Milligram = Milligram(this.value.rem(other))


    override operator fun times(other: Double): Milligram = Milligram(this.value.times(other))
    override operator fun div(other: Double): Milligram = Milligram(this.value.div(other))
    override operator fun rem(other: Double): Milligram = Milligram(this.value.rem(other))


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

val Byte.milligram
    get() = Milligram(this)


val Short.milligram
    get() = Milligram(this)


val Int.milligram
    get() = Milligram(this)


val Long.milligram
    get() = Milligram(this)


val Float.milligram
    get() = Milligram(this)


val Double.milligram
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Microgram = Microgram(this.value.times(other))
    override operator fun div(other: Byte): Microgram = Microgram(this.value.div(other))
    override operator fun rem(other: Byte): Microgram = Microgram(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Microgram = Microgram(this.value.times(other))
    override operator fun div(other: Short): Microgram = Microgram(this.value.div(other))
    override operator fun rem(other: Short): Microgram = Microgram(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Microgram = Microgram(this.value.times(other))
    override operator fun div(other: Int): Microgram = Microgram(this.value.div(other))
    override operator fun rem(other: Int): Microgram = Microgram(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Microgram = Microgram(this.value.times(other))
    override operator fun div(other: Long): Microgram = Microgram(this.value.div(other))
    override operator fun rem(other: Long): Microgram = Microgram(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Microgram = Microgram(this.value.times(other))
    override operator fun div(other: Float): Microgram = Microgram(this.value.div(other))
    override operator fun rem(other: Float): Microgram = Microgram(this.value.rem(other))


    override operator fun times(other: Double): Microgram = Microgram(this.value.times(other))
    override operator fun div(other: Double): Microgram = Microgram(this.value.div(other))
    override operator fun rem(other: Double): Microgram = Microgram(this.value.rem(other))


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

val Byte.microgram
    get() = Microgram(this)


val Short.microgram
    get() = Microgram(this)


val Int.microgram
    get() = Microgram(this)


val Long.microgram
    get() = Microgram(this)


val Float.microgram
    get() = Microgram(this)


val Double.microgram
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Ton = Ton(this.value.times(other))
    override operator fun div(other: Byte): Ton = Ton(this.value.div(other))
    override operator fun rem(other: Byte): Ton = Ton(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Ton = Ton(this.value.times(other))
    override operator fun div(other: Short): Ton = Ton(this.value.div(other))
    override operator fun rem(other: Short): Ton = Ton(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Ton = Ton(this.value.times(other))
    override operator fun div(other: Int): Ton = Ton(this.value.div(other))
    override operator fun rem(other: Int): Ton = Ton(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Ton = Ton(this.value.times(other))
    override operator fun div(other: Long): Ton = Ton(this.value.div(other))
    override operator fun rem(other: Long): Ton = Ton(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Ton = Ton(this.value.times(other))
    override operator fun div(other: Float): Ton = Ton(this.value.div(other))
    override operator fun rem(other: Float): Ton = Ton(this.value.rem(other))


    override operator fun times(other: Double): Ton = Ton(this.value.times(other))
    override operator fun div(other: Double): Ton = Ton(this.value.div(other))
    override operator fun rem(other: Double): Ton = Ton(this.value.rem(other))


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

val Byte.ton
    get() = Ton(this)


val Short.ton
    get() = Ton(this)


val Int.ton
    get() = Ton(this)


val Long.ton
    get() = Ton(this)


val Float.ton
    get() = Ton(this)


val Double.ton
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Stone = Stone(this.value.times(other))
    override operator fun div(other: Byte): Stone = Stone(this.value.div(other))
    override operator fun rem(other: Byte): Stone = Stone(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Stone = Stone(this.value.times(other))
    override operator fun div(other: Short): Stone = Stone(this.value.div(other))
    override operator fun rem(other: Short): Stone = Stone(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Stone = Stone(this.value.times(other))
    override operator fun div(other: Int): Stone = Stone(this.value.div(other))
    override operator fun rem(other: Int): Stone = Stone(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Stone = Stone(this.value.times(other))
    override operator fun div(other: Long): Stone = Stone(this.value.div(other))
    override operator fun rem(other: Long): Stone = Stone(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Stone = Stone(this.value.times(other))
    override operator fun div(other: Float): Stone = Stone(this.value.div(other))
    override operator fun rem(other: Float): Stone = Stone(this.value.rem(other))


    override operator fun times(other: Double): Stone = Stone(this.value.times(other))
    override operator fun div(other: Double): Stone = Stone(this.value.div(other))
    override operator fun rem(other: Double): Stone = Stone(this.value.rem(other))


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

val Byte.stone
    get() = Stone(this)


val Short.stone
    get() = Stone(this)


val Int.stone
    get() = Stone(this)


val Long.stone
    get() = Stone(this)


val Float.stone
    get() = Stone(this)


val Double.stone
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Pound = Pound(this.value.times(other))
    override operator fun div(other: Byte): Pound = Pound(this.value.div(other))
    override operator fun rem(other: Byte): Pound = Pound(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Pound = Pound(this.value.times(other))
    override operator fun div(other: Short): Pound = Pound(this.value.div(other))
    override operator fun rem(other: Short): Pound = Pound(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Pound = Pound(this.value.times(other))
    override operator fun div(other: Int): Pound = Pound(this.value.div(other))
    override operator fun rem(other: Int): Pound = Pound(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Pound = Pound(this.value.times(other))
    override operator fun div(other: Long): Pound = Pound(this.value.div(other))
    override operator fun rem(other: Long): Pound = Pound(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Pound = Pound(this.value.times(other))
    override operator fun div(other: Float): Pound = Pound(this.value.div(other))
    override operator fun rem(other: Float): Pound = Pound(this.value.rem(other))


    override operator fun times(other: Double): Pound = Pound(this.value.times(other))
    override operator fun div(other: Double): Pound = Pound(this.value.div(other))
    override operator fun rem(other: Double): Pound = Pound(this.value.rem(other))


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

val Byte.pound
    get() = Pound(this)


val Short.pound
    get() = Pound(this)


val Int.pound
    get() = Pound(this)


val Long.pound
    get() = Pound(this)


val Float.pound
    get() = Pound(this)


val Double.pound
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Ounce = Ounce(this.value.times(other))
    override operator fun div(other: Byte): Ounce = Ounce(this.value.div(other))
    override operator fun rem(other: Byte): Ounce = Ounce(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Ounce = Ounce(this.value.times(other))
    override operator fun div(other: Short): Ounce = Ounce(this.value.div(other))
    override operator fun rem(other: Short): Ounce = Ounce(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Ounce = Ounce(this.value.times(other))
    override operator fun div(other: Int): Ounce = Ounce(this.value.div(other))
    override operator fun rem(other: Int): Ounce = Ounce(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Ounce = Ounce(this.value.times(other))
    override operator fun div(other: Long): Ounce = Ounce(this.value.div(other))
    override operator fun rem(other: Long): Ounce = Ounce(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Ounce = Ounce(this.value.times(other))
    override operator fun div(other: Float): Ounce = Ounce(this.value.div(other))
    override operator fun rem(other: Float): Ounce = Ounce(this.value.rem(other))


    override operator fun times(other: Double): Ounce = Ounce(this.value.times(other))
    override operator fun div(other: Double): Ounce = Ounce(this.value.div(other))
    override operator fun rem(other: Double): Ounce = Ounce(this.value.rem(other))


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

val Byte.ounce
    get() = Ounce(this)


val Short.ounce
    get() = Ounce(this)


val Int.ounce
    get() = Ounce(this)


val Long.ounce
    get() = Ounce(this)


val Float.ounce
    get() = Ounce(this)


val Double.ounce
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): OunceTroy = OunceTroy(this.value.times(other))
    override operator fun div(other: Byte): OunceTroy = OunceTroy(this.value.div(other))
    override operator fun rem(other: Byte): OunceTroy = OunceTroy(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): OunceTroy = OunceTroy(this.value.times(other))
    override operator fun div(other: Short): OunceTroy = OunceTroy(this.value.div(other))
    override operator fun rem(other: Short): OunceTroy = OunceTroy(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): OunceTroy = OunceTroy(this.value.times(other))
    override operator fun div(other: Int): OunceTroy = OunceTroy(this.value.div(other))
    override operator fun rem(other: Int): OunceTroy = OunceTroy(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): OunceTroy = OunceTroy(this.value.times(other))
    override operator fun div(other: Long): OunceTroy = OunceTroy(this.value.div(other))
    override operator fun rem(other: Long): OunceTroy = OunceTroy(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): OunceTroy = OunceTroy(this.value.times(other))
    override operator fun div(other: Float): OunceTroy = OunceTroy(this.value.div(other))
    override operator fun rem(other: Float): OunceTroy = OunceTroy(this.value.rem(other))


    override operator fun times(other: Double): OunceTroy = OunceTroy(this.value.times(other))
    override operator fun div(other: Double): OunceTroy = OunceTroy(this.value.div(other))
    override operator fun rem(other: Double): OunceTroy = OunceTroy(this.value.rem(other))


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

val Byte.ounceTroy
    get() = OunceTroy(this)


val Short.ounceTroy
    get() = OunceTroy(this)


val Int.ounceTroy
    get() = OunceTroy(this)


val Long.ounceTroy
    get() = OunceTroy(this)


val Float.ounceTroy
    get() = OunceTroy(this)


val Double.ounceTroy
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Carat = Carat(this.value.times(other))
    override operator fun div(other: Byte): Carat = Carat(this.value.div(other))
    override operator fun rem(other: Byte): Carat = Carat(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Carat = Carat(this.value.times(other))
    override operator fun div(other: Short): Carat = Carat(this.value.div(other))
    override operator fun rem(other: Short): Carat = Carat(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Carat = Carat(this.value.times(other))
    override operator fun div(other: Int): Carat = Carat(this.value.div(other))
    override operator fun rem(other: Int): Carat = Carat(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Carat = Carat(this.value.times(other))
    override operator fun div(other: Long): Carat = Carat(this.value.div(other))
    override operator fun rem(other: Long): Carat = Carat(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Carat = Carat(this.value.times(other))
    override operator fun div(other: Float): Carat = Carat(this.value.div(other))
    override operator fun rem(other: Float): Carat = Carat(this.value.rem(other))


    override operator fun times(other: Double): Carat = Carat(this.value.times(other))
    override operator fun div(other: Double): Carat = Carat(this.value.div(other))
    override operator fun rem(other: Double): Carat = Carat(this.value.rem(other))


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

val Byte.carat
    get() = Carat(this)


val Short.carat
    get() = Carat(this)


val Int.carat
    get() = Carat(this)


val Long.carat
    get() = Carat(this)


val Float.carat
    get() = Carat(this)


val Double.carat
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Dalton = Dalton(this.value.times(other))
    override operator fun div(other: Byte): Dalton = Dalton(this.value.div(other))
    override operator fun rem(other: Byte): Dalton = Dalton(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Dalton = Dalton(this.value.times(other))
    override operator fun div(other: Short): Dalton = Dalton(this.value.div(other))
    override operator fun rem(other: Short): Dalton = Dalton(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Dalton = Dalton(this.value.times(other))
    override operator fun div(other: Int): Dalton = Dalton(this.value.div(other))
    override operator fun rem(other: Int): Dalton = Dalton(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Dalton = Dalton(this.value.times(other))
    override operator fun div(other: Long): Dalton = Dalton(this.value.div(other))
    override operator fun rem(other: Long): Dalton = Dalton(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Dalton = Dalton(this.value.times(other))
    override operator fun div(other: Float): Dalton = Dalton(this.value.div(other))
    override operator fun rem(other: Float): Dalton = Dalton(this.value.rem(other))


    override operator fun times(other: Double): Dalton = Dalton(this.value.times(other))
    override operator fun div(other: Double): Dalton = Dalton(this.value.div(other))
    override operator fun rem(other: Double): Dalton = Dalton(this.value.rem(other))


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

val Byte.dalton
    get() = Dalton(this)


val Short.dalton
    get() = Dalton(this)


val Int.dalton
    get() = Dalton(this)


val Long.dalton
    get() = Dalton(this)


val Float.dalton
    get() = Dalton(this)


val Double.dalton
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): EarthMass = EarthMass(this.value.times(other))
    override operator fun div(other: Byte): EarthMass = EarthMass(this.value.div(other))
    override operator fun rem(other: Byte): EarthMass = EarthMass(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): EarthMass = EarthMass(this.value.times(other))
    override operator fun div(other: Short): EarthMass = EarthMass(this.value.div(other))
    override operator fun rem(other: Short): EarthMass = EarthMass(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): EarthMass = EarthMass(this.value.times(other))
    override operator fun div(other: Int): EarthMass = EarthMass(this.value.div(other))
    override operator fun rem(other: Int): EarthMass = EarthMass(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): EarthMass = EarthMass(this.value.times(other))
    override operator fun div(other: Long): EarthMass = EarthMass(this.value.div(other))
    override operator fun rem(other: Long): EarthMass = EarthMass(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): EarthMass = EarthMass(this.value.times(other))
    override operator fun div(other: Float): EarthMass = EarthMass(this.value.div(other))
    override operator fun rem(other: Float): EarthMass = EarthMass(this.value.rem(other))


    override operator fun times(other: Double): EarthMass = EarthMass(this.value.times(other))
    override operator fun div(other: Double): EarthMass = EarthMass(this.value.div(other))
    override operator fun rem(other: Double): EarthMass = EarthMass(this.value.rem(other))


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

val Byte.earthMass
    get() = EarthMass(this)


val Short.earthMass
    get() = EarthMass(this)


val Int.earthMass
    get() = EarthMass(this)


val Long.earthMass
    get() = EarthMass(this)


val Float.earthMass
    get() = EarthMass(this)


val Double.earthMass
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): SolarMass = SolarMass(this.value.times(other))
    override operator fun div(other: Byte): SolarMass = SolarMass(this.value.div(other))
    override operator fun rem(other: Byte): SolarMass = SolarMass(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): SolarMass = SolarMass(this.value.times(other))
    override operator fun div(other: Short): SolarMass = SolarMass(this.value.div(other))
    override operator fun rem(other: Short): SolarMass = SolarMass(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): SolarMass = SolarMass(this.value.times(other))
    override operator fun div(other: Int): SolarMass = SolarMass(this.value.div(other))
    override operator fun rem(other: Int): SolarMass = SolarMass(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): SolarMass = SolarMass(this.value.times(other))
    override operator fun div(other: Long): SolarMass = SolarMass(this.value.div(other))
    override operator fun rem(other: Long): SolarMass = SolarMass(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): SolarMass = SolarMass(this.value.times(other))
    override operator fun div(other: Float): SolarMass = SolarMass(this.value.div(other))
    override operator fun rem(other: Float): SolarMass = SolarMass(this.value.rem(other))


    override operator fun times(other: Double): SolarMass = SolarMass(this.value.times(other))
    override operator fun div(other: Double): SolarMass = SolarMass(this.value.div(other))
    override operator fun rem(other: Double): SolarMass = SolarMass(this.value.rem(other))


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

val Byte.solarMass
    get() = SolarMass(this)


val Short.solarMass
    get() = SolarMass(this)


val Int.solarMass
    get() = SolarMass(this)


val Long.solarMass
    get() = SolarMass(this)


val Float.solarMass
    get() = SolarMass(this)


val Double.solarMass
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Grain = Grain(this.value.times(other))
    override operator fun div(other: Byte): Grain = Grain(this.value.div(other))
    override operator fun rem(other: Byte): Grain = Grain(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Grain = Grain(this.value.times(other))
    override operator fun div(other: Short): Grain = Grain(this.value.div(other))
    override operator fun rem(other: Short): Grain = Grain(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Grain = Grain(this.value.times(other))
    override operator fun div(other: Int): Grain = Grain(this.value.div(other))
    override operator fun rem(other: Int): Grain = Grain(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Grain = Grain(this.value.times(other))
    override operator fun div(other: Long): Grain = Grain(this.value.div(other))
    override operator fun rem(other: Long): Grain = Grain(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Grain = Grain(this.value.times(other))
    override operator fun div(other: Float): Grain = Grain(this.value.div(other))
    override operator fun rem(other: Float): Grain = Grain(this.value.rem(other))


    override operator fun times(other: Double): Grain = Grain(this.value.times(other))
    override operator fun div(other: Double): Grain = Grain(this.value.div(other))
    override operator fun rem(other: Double): Grain = Grain(this.value.rem(other))


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

val Byte.grain
    get() = Grain(this)


val Short.grain
    get() = Grain(this)


val Int.grain
    get() = Grain(this)


val Long.grain
    get() = Grain(this)


val Float.grain
    get() = Grain(this)


val Double.grain
    get() = Grain(this)
                            
