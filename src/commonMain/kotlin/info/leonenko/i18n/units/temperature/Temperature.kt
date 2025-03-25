package info.leonenko.i18n.units.temperature

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

@Serializable(Temperature.Serializer::class)
interface Temperature : MeasurementUnit, Comparable<Temperature> {
    override val measurement: String get() = "temperature"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Temperature> = emptyMap()) : KSerializer<Temperature> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Temperature", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Temperature {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Temperature unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "generic" -> Generic(value)
                "celsius" -> Celsius(value)
                "fahrenheit" -> Fahrenheit(value)
                "kelvin" -> Kelvin(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Temperature unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Temperature) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toGeneric(): Generic = Generic(value)
    fun toCelsius(): Celsius {
        return Celsius.fromKelvin(toKelvin())
    }

    fun toFahrenheit(): Fahrenheit {
        return Fahrenheit.fromKelvin(toKelvin())
    }

    fun toKelvin(): Kelvin
    override fun getBaseUnitValue() = toKelvin().value

    companion object {
        val ZERO: Temperature = Kelvin(0.0)

    }

    override operator fun compareTo(other: Temperature): Int = this.toKelvin().value.compareTo(other.toKelvin().value)

    operator fun plus(other: Temperature): Kelvin = this.toKelvin() + other.toKelvin()
    operator fun minus(other: Temperature): Kelvin = this.toKelvin() - other.toKelvin()
    operator fun div(other: Temperature): Double = this.toKelvin().value / other.toKelvin().value
    operator fun unaryMinus(): Temperature
    operator fun unaryPlus(): Temperature
    operator fun inc(): Temperature
    operator fun dec(): Temperature

    fun round(): Temperature
    fun truncate(): Temperature
    fun floor(): Temperature
    fun ceil(): Temperature
    fun abs(): Temperature


    operator fun times(other: Byte): Temperature
    operator fun div(other: Byte): Temperature
    operator fun rem(other: Byte): Temperature


    operator fun times(other: Short): Temperature
    operator fun div(other: Short): Temperature
    operator fun rem(other: Short): Temperature


    operator fun times(other: Int): Temperature
    operator fun div(other: Int): Temperature
    operator fun rem(other: Int): Temperature


    operator fun times(other: Long): Temperature
    operator fun div(other: Long): Temperature
    operator fun rem(other: Long): Temperature


    operator fun times(other: Float): Temperature
    operator fun div(other: Float): Temperature
    operator fun rem(other: Float): Temperature


    operator fun times(other: Double): Temperature
    operator fun div(other: Double): Temperature
    operator fun rem(other: Double): Temperature

}

data class Generic(override val value: MeasurementUnitValue) : Temperature {

    override val unitName: String = "generic"

    override fun formatters(language: MeasurementUnitLanguage) = language.temperatureGeneric
    override fun toKelvin(): Kelvin = TODO("Conversion of Generic to Kelvin is not supported")
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Generic(0.0)
        fun fromKelvin(value: Kelvin): Generic = TODO("Conversion of Kelvin to Generic is not supported")
    }

    operator fun plus(other: Generic): Generic = Generic(this.value + other.value)
    operator fun minus(other: Generic): Generic = Generic(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Generic = Generic(this.value.times(other))
    override operator fun div(other: Byte): Generic = Generic(this.value.div(other))
    override operator fun rem(other: Byte): Generic = Generic(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Generic = Generic(this.value.times(other))
    override operator fun div(other: Short): Generic = Generic(this.value.div(other))
    override operator fun rem(other: Short): Generic = Generic(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Generic = Generic(this.value.times(other))
    override operator fun div(other: Int): Generic = Generic(this.value.div(other))
    override operator fun rem(other: Int): Generic = Generic(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Generic = Generic(this.value.times(other))
    override operator fun div(other: Long): Generic = Generic(this.value.div(other))
    override operator fun rem(other: Long): Generic = Generic(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Generic = Generic(this.value.times(other))
    override operator fun div(other: Float): Generic = Generic(this.value.div(other))
    override operator fun rem(other: Float): Generic = Generic(this.value.rem(other))


    override operator fun times(other: Double): Generic = Generic(this.value.times(other))
    override operator fun div(other: Double): Generic = Generic(this.value.div(other))
    override operator fun rem(other: Double): Generic = Generic(this.value.rem(other))


    override operator fun unaryMinus(): Generic = Generic(-value)
    override operator fun unaryPlus(): Generic = this
    override operator fun inc(): Generic = Generic(this.value + 1)
    override operator fun dec(): Generic = Generic(this.value - 1)

    override fun round(): Generic = Generic(round(this.value))
    override fun truncate(): Generic = Generic(truncate(this.value))
    override fun floor(): Generic = Generic(floor(this.value))
    override fun ceil(): Generic = Generic(ceil(this.value))
    override fun abs(): Generic = Generic(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Generic -> this.value == other.value
        is Temperature -> this.toKelvin().value == other.toKelvin().value
        else -> false
    }

    override fun hashCode(): Int = this.toKelvin().value.hashCode()


}

val Byte.generic
    get() = Generic(this)


val Short.generic
    get() = Generic(this)


val Int.generic
    get() = Generic(this)


val Long.generic
    get() = Generic(this)


val Float.generic
    get() = Generic(this)


val Double.generic
    get() = Generic(this)


data class Celsius(override val value: MeasurementUnitValue) : Temperature {

    override val unitName: String = "celsius"

    override fun formatters(language: MeasurementUnitLanguage) = language.temperatureCelsius
    override fun toKelvin() = Kelvin(value * (1) + 273.15)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Celsius(0.0)
        fun fromKelvin(value: Kelvin) = Celsius((value.value - 273.15) / (1))
    }

    operator fun plus(other: Celsius): Celsius = Celsius(this.value + other.value)
    operator fun minus(other: Celsius): Celsius = Celsius(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Celsius = Celsius(this.value.times(other))
    override operator fun div(other: Byte): Celsius = Celsius(this.value.div(other))
    override operator fun rem(other: Byte): Celsius = Celsius(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Celsius = Celsius(this.value.times(other))
    override operator fun div(other: Short): Celsius = Celsius(this.value.div(other))
    override operator fun rem(other: Short): Celsius = Celsius(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Celsius = Celsius(this.value.times(other))
    override operator fun div(other: Int): Celsius = Celsius(this.value.div(other))
    override operator fun rem(other: Int): Celsius = Celsius(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Celsius = Celsius(this.value.times(other))
    override operator fun div(other: Long): Celsius = Celsius(this.value.div(other))
    override operator fun rem(other: Long): Celsius = Celsius(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Celsius = Celsius(this.value.times(other))
    override operator fun div(other: Float): Celsius = Celsius(this.value.div(other))
    override operator fun rem(other: Float): Celsius = Celsius(this.value.rem(other))


    override operator fun times(other: Double): Celsius = Celsius(this.value.times(other))
    override operator fun div(other: Double): Celsius = Celsius(this.value.div(other))
    override operator fun rem(other: Double): Celsius = Celsius(this.value.rem(other))


    override operator fun unaryMinus(): Celsius = Celsius(-value)
    override operator fun unaryPlus(): Celsius = this
    override operator fun inc(): Celsius = Celsius(this.value + 1)
    override operator fun dec(): Celsius = Celsius(this.value - 1)

    override fun round(): Celsius = Celsius(round(this.value))
    override fun truncate(): Celsius = Celsius(truncate(this.value))
    override fun floor(): Celsius = Celsius(floor(this.value))
    override fun ceil(): Celsius = Celsius(ceil(this.value))
    override fun abs(): Celsius = Celsius(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Celsius -> this.value == other.value
        is Temperature -> this.toKelvin().value == other.toKelvin().value
        else -> false
    }

    override fun hashCode(): Int = this.toKelvin().value.hashCode()


}

val Byte.celsius
    get() = Celsius(this)


val Short.celsius
    get() = Celsius(this)


val Int.celsius
    get() = Celsius(this)


val Long.celsius
    get() = Celsius(this)


val Float.celsius
    get() = Celsius(this)


val Double.celsius
    get() = Celsius(this)


data class Fahrenheit(override val value: MeasurementUnitValue) : Temperature {

    override val unitName: String = "fahrenheit"

    override fun formatters(language: MeasurementUnitLanguage) = language.temperatureFahrenheit
    override fun toKelvin() = Kelvin(value * (0.5555555555555556) + 255.3722222222222)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Fahrenheit(0.0)
        fun fromKelvin(value: Kelvin) = Fahrenheit((value.value - 255.3722222222222) / (0.5555555555555556))
    }

    operator fun plus(other: Fahrenheit): Fahrenheit = Fahrenheit(this.value + other.value)
    operator fun minus(other: Fahrenheit): Fahrenheit = Fahrenheit(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Fahrenheit = Fahrenheit(this.value.times(other))
    override operator fun div(other: Byte): Fahrenheit = Fahrenheit(this.value.div(other))
    override operator fun rem(other: Byte): Fahrenheit = Fahrenheit(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Fahrenheit = Fahrenheit(this.value.times(other))
    override operator fun div(other: Short): Fahrenheit = Fahrenheit(this.value.div(other))
    override operator fun rem(other: Short): Fahrenheit = Fahrenheit(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Fahrenheit = Fahrenheit(this.value.times(other))
    override operator fun div(other: Int): Fahrenheit = Fahrenheit(this.value.div(other))
    override operator fun rem(other: Int): Fahrenheit = Fahrenheit(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Fahrenheit = Fahrenheit(this.value.times(other))
    override operator fun div(other: Long): Fahrenheit = Fahrenheit(this.value.div(other))
    override operator fun rem(other: Long): Fahrenheit = Fahrenheit(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Fahrenheit = Fahrenheit(this.value.times(other))
    override operator fun div(other: Float): Fahrenheit = Fahrenheit(this.value.div(other))
    override operator fun rem(other: Float): Fahrenheit = Fahrenheit(this.value.rem(other))


    override operator fun times(other: Double): Fahrenheit = Fahrenheit(this.value.times(other))
    override operator fun div(other: Double): Fahrenheit = Fahrenheit(this.value.div(other))
    override operator fun rem(other: Double): Fahrenheit = Fahrenheit(this.value.rem(other))


    override operator fun unaryMinus(): Fahrenheit = Fahrenheit(-value)
    override operator fun unaryPlus(): Fahrenheit = this
    override operator fun inc(): Fahrenheit = Fahrenheit(this.value + 1)
    override operator fun dec(): Fahrenheit = Fahrenheit(this.value - 1)

    override fun round(): Fahrenheit = Fahrenheit(round(this.value))
    override fun truncate(): Fahrenheit = Fahrenheit(truncate(this.value))
    override fun floor(): Fahrenheit = Fahrenheit(floor(this.value))
    override fun ceil(): Fahrenheit = Fahrenheit(ceil(this.value))
    override fun abs(): Fahrenheit = Fahrenheit(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Fahrenheit -> this.value == other.value
        is Temperature -> this.toKelvin().value == other.toKelvin().value
        else -> false
    }

    override fun hashCode(): Int = this.toKelvin().value.hashCode()


}

val Byte.fahrenheit
    get() = Fahrenheit(this)


val Short.fahrenheit
    get() = Fahrenheit(this)


val Int.fahrenheit
    get() = Fahrenheit(this)


val Long.fahrenheit
    get() = Fahrenheit(this)


val Float.fahrenheit
    get() = Fahrenheit(this)


val Double.fahrenheit
    get() = Fahrenheit(this)


data class Kelvin(override val value: MeasurementUnitValue) : Temperature {

    override val unitName: String = "kelvin"

    override fun formatters(language: MeasurementUnitLanguage) = language.temperatureKelvin
    override fun toKelvin() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Kelvin(0.0)
        fun fromKelvin(value: Kelvin) = value
    }

    operator fun plus(other: Kelvin): Kelvin = Kelvin(this.value + other.value)
    operator fun minus(other: Kelvin): Kelvin = Kelvin(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Kelvin = Kelvin(this.value.times(other))
    override operator fun div(other: Byte): Kelvin = Kelvin(this.value.div(other))
    override operator fun rem(other: Byte): Kelvin = Kelvin(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Kelvin = Kelvin(this.value.times(other))
    override operator fun div(other: Short): Kelvin = Kelvin(this.value.div(other))
    override operator fun rem(other: Short): Kelvin = Kelvin(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Kelvin = Kelvin(this.value.times(other))
    override operator fun div(other: Int): Kelvin = Kelvin(this.value.div(other))
    override operator fun rem(other: Int): Kelvin = Kelvin(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Kelvin = Kelvin(this.value.times(other))
    override operator fun div(other: Long): Kelvin = Kelvin(this.value.div(other))
    override operator fun rem(other: Long): Kelvin = Kelvin(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Kelvin = Kelvin(this.value.times(other))
    override operator fun div(other: Float): Kelvin = Kelvin(this.value.div(other))
    override operator fun rem(other: Float): Kelvin = Kelvin(this.value.rem(other))


    override operator fun times(other: Double): Kelvin = Kelvin(this.value.times(other))
    override operator fun div(other: Double): Kelvin = Kelvin(this.value.div(other))
    override operator fun rem(other: Double): Kelvin = Kelvin(this.value.rem(other))


    override operator fun unaryMinus(): Kelvin = Kelvin(-value)
    override operator fun unaryPlus(): Kelvin = this
    override operator fun inc(): Kelvin = Kelvin(this.value + 1)
    override operator fun dec(): Kelvin = Kelvin(this.value - 1)

    override fun round(): Kelvin = Kelvin(round(this.value))
    override fun truncate(): Kelvin = Kelvin(truncate(this.value))
    override fun floor(): Kelvin = Kelvin(floor(this.value))
    override fun ceil(): Kelvin = Kelvin(ceil(this.value))
    override fun abs(): Kelvin = Kelvin(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Kelvin -> this.value == other.value
        is Temperature -> this.toKelvin().value == other.toKelvin().value
        else -> false
    }

    override fun hashCode(): Int = this.toKelvin().value.hashCode()


}

val Byte.kelvin
    get() = Kelvin(this)


val Short.kelvin
    get() = Kelvin(this)


val Int.kelvin
    get() = Kelvin(this)


val Long.kelvin
    get() = Kelvin(this)


val Float.kelvin
    get() = Kelvin(this)


val Double.kelvin
    get() = Kelvin(this)
                            
