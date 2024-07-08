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


    operator fun times(other: kotlin.Byte): Temperature
    operator fun div(other: kotlin.Byte): Temperature
    operator fun rem(other: kotlin.Byte): Temperature


    operator fun times(other: kotlin.Short): Temperature
    operator fun div(other: kotlin.Short): Temperature
    operator fun rem(other: kotlin.Short): Temperature


    operator fun times(other: kotlin.Int): Temperature
    operator fun div(other: kotlin.Int): Temperature
    operator fun rem(other: kotlin.Int): Temperature


    operator fun times(other: kotlin.Long): Temperature
    operator fun div(other: kotlin.Long): Temperature
    operator fun rem(other: kotlin.Long): Temperature


    operator fun times(other: kotlin.Float): Temperature
    operator fun div(other: kotlin.Float): Temperature
    operator fun rem(other: kotlin.Float): Temperature


    operator fun times(other: kotlin.Double): Temperature
    operator fun div(other: kotlin.Double): Temperature
    operator fun rem(other: kotlin.Double): Temperature

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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Generic = Generic(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Generic = Generic(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Generic = Generic(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Generic = Generic(this.value.times(other))
    override operator fun div(other: kotlin.Short): Generic = Generic(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Generic = Generic(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Generic = Generic(this.value.times(other))
    override operator fun div(other: kotlin.Int): Generic = Generic(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Generic = Generic(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Generic = Generic(this.value.times(other))
    override operator fun div(other: kotlin.Long): Generic = Generic(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Generic = Generic(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Generic = Generic(this.value.times(other))
    override operator fun div(other: kotlin.Float): Generic = Generic(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Generic = Generic(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Generic = Generic(this.value.times(other))
    override operator fun div(other: kotlin.Double): Generic = Generic(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Generic = Generic(this.value.rem(other))


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

val kotlin.Byte.generic
    get() = Generic(this)


val kotlin.Short.generic
    get() = Generic(this)


val kotlin.Int.generic
    get() = Generic(this)


val kotlin.Long.generic
    get() = Generic(this)


val kotlin.Float.generic
    get() = Generic(this)


val kotlin.Double.generic
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Celsius = Celsius(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Celsius = Celsius(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Celsius = Celsius(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Celsius = Celsius(this.value.times(other))
    override operator fun div(other: kotlin.Short): Celsius = Celsius(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Celsius = Celsius(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Celsius = Celsius(this.value.times(other))
    override operator fun div(other: kotlin.Int): Celsius = Celsius(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Celsius = Celsius(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Celsius = Celsius(this.value.times(other))
    override operator fun div(other: kotlin.Long): Celsius = Celsius(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Celsius = Celsius(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Celsius = Celsius(this.value.times(other))
    override operator fun div(other: kotlin.Float): Celsius = Celsius(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Celsius = Celsius(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Celsius = Celsius(this.value.times(other))
    override operator fun div(other: kotlin.Double): Celsius = Celsius(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Celsius = Celsius(this.value.rem(other))


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

val kotlin.Byte.celsius
    get() = Celsius(this)


val kotlin.Short.celsius
    get() = Celsius(this)


val kotlin.Int.celsius
    get() = Celsius(this)


val kotlin.Long.celsius
    get() = Celsius(this)


val kotlin.Float.celsius
    get() = Celsius(this)


val kotlin.Double.celsius
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Fahrenheit = Fahrenheit(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Fahrenheit = Fahrenheit(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Fahrenheit = Fahrenheit(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Fahrenheit = Fahrenheit(this.value.times(other))
    override operator fun div(other: kotlin.Short): Fahrenheit = Fahrenheit(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Fahrenheit = Fahrenheit(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Fahrenheit = Fahrenheit(this.value.times(other))
    override operator fun div(other: kotlin.Int): Fahrenheit = Fahrenheit(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Fahrenheit = Fahrenheit(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Fahrenheit = Fahrenheit(this.value.times(other))
    override operator fun div(other: kotlin.Long): Fahrenheit = Fahrenheit(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Fahrenheit = Fahrenheit(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Fahrenheit = Fahrenheit(this.value.times(other))
    override operator fun div(other: kotlin.Float): Fahrenheit = Fahrenheit(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Fahrenheit = Fahrenheit(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Fahrenheit = Fahrenheit(this.value.times(other))
    override operator fun div(other: kotlin.Double): Fahrenheit = Fahrenheit(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Fahrenheit = Fahrenheit(this.value.rem(other))


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

val kotlin.Byte.fahrenheit
    get() = Fahrenheit(this)


val kotlin.Short.fahrenheit
    get() = Fahrenheit(this)


val kotlin.Int.fahrenheit
    get() = Fahrenheit(this)


val kotlin.Long.fahrenheit
    get() = Fahrenheit(this)


val kotlin.Float.fahrenheit
    get() = Fahrenheit(this)


val kotlin.Double.fahrenheit
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Kelvin = Kelvin(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Kelvin = Kelvin(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Kelvin = Kelvin(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Kelvin = Kelvin(this.value.times(other))
    override operator fun div(other: kotlin.Short): Kelvin = Kelvin(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Kelvin = Kelvin(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Kelvin = Kelvin(this.value.times(other))
    override operator fun div(other: kotlin.Int): Kelvin = Kelvin(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Kelvin = Kelvin(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Kelvin = Kelvin(this.value.times(other))
    override operator fun div(other: kotlin.Long): Kelvin = Kelvin(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Kelvin = Kelvin(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Kelvin = Kelvin(this.value.times(other))
    override operator fun div(other: kotlin.Float): Kelvin = Kelvin(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Kelvin = Kelvin(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Kelvin = Kelvin(this.value.times(other))
    override operator fun div(other: kotlin.Double): Kelvin = Kelvin(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Kelvin = Kelvin(this.value.rem(other))


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

val kotlin.Byte.kelvin
    get() = Kelvin(this)


val kotlin.Short.kelvin
    get() = Kelvin(this)


val kotlin.Int.kelvin
    get() = Kelvin(this)


val kotlin.Long.kelvin
    get() = Kelvin(this)


val kotlin.Float.kelvin
    get() = Kelvin(this)


val kotlin.Double.kelvin
    get() = Kelvin(this)
                            
