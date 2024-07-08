package info.leonenko.i18n.units.length
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

@Serializable(Length.Serializer::class)
interface Length : MeasurementUnit, Comparable<Length> {
    override val measurement: String get() = "length"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Length> = emptyMap()) : KSerializer<Length> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Length", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Length {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Length unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "earth-radius" -> EarthRadius(value)
                "kilometer" -> Kilometer(value)
                "meter" -> Meter(value)
                "decimeter" -> Decimeter(value)
                "centimeter" -> Centimeter(value)
                "millimeter" -> Millimeter(value)
                "micrometer" -> Micrometer(value)
                "nanometer" -> Nanometer(value)
                "picometer" -> Picometer(value)
                "mile" -> Mile(value)
                "yard" -> Yard(value)
                "foot" -> Foot(value)
                "inch" -> Inch(value)
                "parsec" -> Parsec(value)
                "light-year" -> LightYear(value)
                "astronomical-unit" -> AstronomicalUnit(value)
                "furlong" -> Furlong(value)
                "fathom" -> Fathom(value)
                "nautical-mile" -> NauticalMile(value)
                "mile-scandinavian" -> MileScandinavian(value)
                "point" -> Point(value)
                "solar-radius" -> SolarRadius(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Length unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Length) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toEarthRadius(): EarthRadius {
        return EarthRadius.fromMeter(toMeter())
    }

    fun toKilometer(): Kilometer {
        return Kilometer.fromMeter(toMeter())
    }

    fun toMeter(): Meter
    override fun getBaseUnitValue() = toMeter().value
    fun toDecimeter(): Decimeter {
        return Decimeter.fromMeter(toMeter())
    }

    fun toCentimeter(): Centimeter {
        return Centimeter.fromMeter(toMeter())
    }

    fun toMillimeter(): Millimeter {
        return Millimeter.fromMeter(toMeter())
    }

    fun toMicrometer(): Micrometer {
        return Micrometer.fromMeter(toMeter())
    }

    fun toNanometer(): Nanometer {
        return Nanometer.fromMeter(toMeter())
    }

    fun toPicometer(): Picometer {
        return Picometer.fromMeter(toMeter())
    }

    fun toMile(): Mile {
        return Mile.fromMeter(toMeter())
    }

    fun toYard(): Yard {
        return Yard.fromMeter(toMeter())
    }

    fun toFoot(): Foot {
        return Foot.fromMeter(toMeter())
    }

    fun toInch(): Inch {
        return Inch.fromMeter(toMeter())
    }

    fun toParsec(): Parsec {
        return Parsec.fromMeter(toMeter())
    }

    fun toLightYear(): LightYear {
        return LightYear.fromMeter(toMeter())
    }

    fun toAstronomicalUnit(): AstronomicalUnit {
        return AstronomicalUnit.fromMeter(toMeter())
    }

    fun toFurlong(): Furlong {
        return Furlong.fromMeter(toMeter())
    }

    fun toFathom(): Fathom {
        return Fathom.fromMeter(toMeter())
    }

    fun toNauticalMile(): NauticalMile {
        return NauticalMile.fromMeter(toMeter())
    }

    fun toMileScandinavian(): MileScandinavian {
        return MileScandinavian.fromMeter(toMeter())
    }

    fun toPoint(): Point {
        return Point.fromMeter(toMeter())
    }

    fun toSolarRadius(): SolarRadius {
        return SolarRadius.fromMeter(toMeter())
    }

    companion object {
        val ZERO: Length = Meter(0.0)

    }

    override operator fun compareTo(other: Length): Int = this.toMeter().value.compareTo(other.toMeter().value)

    operator fun plus(other: Length): Meter = this.toMeter() + other.toMeter()
    operator fun minus(other: Length): Meter = this.toMeter() - other.toMeter()
    operator fun div(other: Length): Double = this.toMeter().value / other.toMeter().value
    operator fun unaryMinus(): Length
    operator fun unaryPlus(): Length
    operator fun inc(): Length
    operator fun dec(): Length

    fun round(): Length
    fun truncate(): Length
    fun floor(): Length
    fun ceil(): Length
    fun abs(): Length


    operator fun times(other: kotlin.Byte): Length
    operator fun div(other: kotlin.Byte): Length
    operator fun rem(other: kotlin.Byte): Length


    operator fun times(other: kotlin.Short): Length
    operator fun div(other: kotlin.Short): Length
    operator fun rem(other: kotlin.Short): Length


    operator fun times(other: kotlin.Int): Length
    operator fun div(other: kotlin.Int): Length
    operator fun rem(other: kotlin.Int): Length


    operator fun times(other: kotlin.Long): Length
    operator fun div(other: kotlin.Long): Length
    operator fun rem(other: kotlin.Long): Length


    operator fun times(other: kotlin.Float): Length
    operator fun div(other: kotlin.Float): Length
    operator fun rem(other: kotlin.Float): Length


    operator fun times(other: kotlin.Double): Length
    operator fun div(other: kotlin.Double): Length
    operator fun rem(other: kotlin.Double): Length

}

data class EarthRadius(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "earth-radius"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthEarthRadius
    override fun toMeter() = Meter(value * (6378100.0) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = EarthRadius(0.0)
        fun fromMeter(value: Meter) = EarthRadius((value.value - 0) / (6378100.0))
    }

    operator fun plus(other: EarthRadius): EarthRadius = EarthRadius(this.value + other.value)
    operator fun minus(other: EarthRadius): EarthRadius = EarthRadius(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): EarthRadius = EarthRadius(this.value.times(other))
    override operator fun div(other: kotlin.Byte): EarthRadius = EarthRadius(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): EarthRadius = EarthRadius(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): EarthRadius = EarthRadius(this.value.times(other))
    override operator fun div(other: kotlin.Short): EarthRadius = EarthRadius(this.value.div(other))
    override operator fun rem(other: kotlin.Short): EarthRadius = EarthRadius(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): EarthRadius = EarthRadius(this.value.times(other))
    override operator fun div(other: kotlin.Int): EarthRadius = EarthRadius(this.value.div(other))
    override operator fun rem(other: kotlin.Int): EarthRadius = EarthRadius(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): EarthRadius = EarthRadius(this.value.times(other))
    override operator fun div(other: kotlin.Long): EarthRadius = EarthRadius(this.value.div(other))
    override operator fun rem(other: kotlin.Long): EarthRadius = EarthRadius(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): EarthRadius = EarthRadius(this.value.times(other))
    override operator fun div(other: kotlin.Float): EarthRadius = EarthRadius(this.value.div(other))
    override operator fun rem(other: kotlin.Float): EarthRadius = EarthRadius(this.value.rem(other))


    override operator fun times(other: kotlin.Double): EarthRadius = EarthRadius(this.value.times(other))
    override operator fun div(other: kotlin.Double): EarthRadius = EarthRadius(this.value.div(other))
    override operator fun rem(other: kotlin.Double): EarthRadius = EarthRadius(this.value.rem(other))


    override operator fun unaryMinus(): EarthRadius = EarthRadius(-value)
    override operator fun unaryPlus(): EarthRadius = this
    override operator fun inc(): EarthRadius = EarthRadius(this.value + 1)
    override operator fun dec(): EarthRadius = EarthRadius(this.value - 1)

    override fun round(): EarthRadius = EarthRadius(round(this.value))
    override fun truncate(): EarthRadius = EarthRadius(truncate(this.value))
    override fun floor(): EarthRadius = EarthRadius(floor(this.value))
    override fun ceil(): EarthRadius = EarthRadius(ceil(this.value))
    override fun abs(): EarthRadius = EarthRadius(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is EarthRadius -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.earthRadius
    get() = EarthRadius(this)


val kotlin.Short.earthRadius
    get() = EarthRadius(this)


val kotlin.Int.earthRadius
    get() = EarthRadius(this)


val kotlin.Long.earthRadius
    get() = EarthRadius(this)


val kotlin.Float.earthRadius
    get() = EarthRadius(this)


val kotlin.Double.earthRadius
    get() = EarthRadius(this)


data class Kilometer(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "kilometer"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthKilometer
    override fun toMeter() = Meter(value * (1 * (1e3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Kilometer(0.0)
        fun fromMeter(value: Meter) = Kilometer((value.value - 0) / (1 * (1e3)))
    }

    operator fun plus(other: Kilometer): Kilometer = Kilometer(this.value + other.value)
    operator fun minus(other: Kilometer): Kilometer = Kilometer(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Kilometer = Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Kilometer = Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Kilometer = Kilometer(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Kilometer = Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Short): Kilometer = Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Kilometer = Kilometer(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Kilometer = Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Int): Kilometer = Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Kilometer = Kilometer(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Kilometer = Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Long): Kilometer = Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Kilometer = Kilometer(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Kilometer = Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Float): Kilometer = Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Kilometer = Kilometer(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Kilometer = Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Double): Kilometer = Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Kilometer = Kilometer(this.value.rem(other))


    override operator fun unaryMinus(): Kilometer = Kilometer(-value)
    override operator fun unaryPlus(): Kilometer = this
    override operator fun inc(): Kilometer = Kilometer(this.value + 1)
    override operator fun dec(): Kilometer = Kilometer(this.value - 1)

    override fun round(): Kilometer = Kilometer(round(this.value))
    override fun truncate(): Kilometer = Kilometer(truncate(this.value))
    override fun floor(): Kilometer = Kilometer(floor(this.value))
    override fun ceil(): Kilometer = Kilometer(ceil(this.value))
    override fun abs(): Kilometer = Kilometer(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Kilometer -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.kilometer
    get() = Kilometer(this)


val kotlin.Short.kilometer
    get() = Kilometer(this)


val kotlin.Int.kilometer
    get() = Kilometer(this)


val kotlin.Long.kilometer
    get() = Kilometer(this)


val kotlin.Float.kilometer
    get() = Kilometer(this)


val kotlin.Double.kilometer
    get() = Kilometer(this)


data class Meter(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "meter"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthMeter
    override fun toMeter() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Meter(0.0)
        fun fromMeter(value: Meter) = value
    }

    operator fun plus(other: Meter): Meter = Meter(this.value + other.value)
    operator fun minus(other: Meter): Meter = Meter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Meter = Meter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Meter = Meter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Meter = Meter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Meter = Meter(this.value.times(other))
    override operator fun div(other: kotlin.Short): Meter = Meter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Meter = Meter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Meter = Meter(this.value.times(other))
    override operator fun div(other: kotlin.Int): Meter = Meter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Meter = Meter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Meter = Meter(this.value.times(other))
    override operator fun div(other: kotlin.Long): Meter = Meter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Meter = Meter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Meter = Meter(this.value.times(other))
    override operator fun div(other: kotlin.Float): Meter = Meter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Meter = Meter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Meter = Meter(this.value.times(other))
    override operator fun div(other: kotlin.Double): Meter = Meter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Meter = Meter(this.value.rem(other))


    override operator fun unaryMinus(): Meter = Meter(-value)
    override operator fun unaryPlus(): Meter = this
    override operator fun inc(): Meter = Meter(this.value + 1)
    override operator fun dec(): Meter = Meter(this.value - 1)

    override fun round(): Meter = Meter(round(this.value))
    override fun truncate(): Meter = Meter(truncate(this.value))
    override fun floor(): Meter = Meter(floor(this.value))
    override fun ceil(): Meter = Meter(ceil(this.value))
    override fun abs(): Meter = Meter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Meter -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.meter
    get() = Meter(this)


val kotlin.Short.meter
    get() = Meter(this)


val kotlin.Int.meter
    get() = Meter(this)


val kotlin.Long.meter
    get() = Meter(this)


val kotlin.Float.meter
    get() = Meter(this)


val kotlin.Double.meter
    get() = Meter(this)


data class Decimeter(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "decimeter"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthDecimeter
    override fun toMeter() = Meter(value * (1 * (1e-1)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Decimeter(0.0)
        fun fromMeter(value: Meter) = Decimeter((value.value - 0) / (1 * (1e-1)))
    }

    operator fun plus(other: Decimeter): Decimeter = Decimeter(this.value + other.value)
    operator fun minus(other: Decimeter): Decimeter = Decimeter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Decimeter = Decimeter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Decimeter = Decimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Decimeter = Decimeter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Decimeter = Decimeter(this.value.times(other))
    override operator fun div(other: kotlin.Short): Decimeter = Decimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Decimeter = Decimeter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Decimeter = Decimeter(this.value.times(other))
    override operator fun div(other: kotlin.Int): Decimeter = Decimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Decimeter = Decimeter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Decimeter = Decimeter(this.value.times(other))
    override operator fun div(other: kotlin.Long): Decimeter = Decimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Decimeter = Decimeter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Decimeter = Decimeter(this.value.times(other))
    override operator fun div(other: kotlin.Float): Decimeter = Decimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Decimeter = Decimeter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Decimeter = Decimeter(this.value.times(other))
    override operator fun div(other: kotlin.Double): Decimeter = Decimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Decimeter = Decimeter(this.value.rem(other))


    override operator fun unaryMinus(): Decimeter = Decimeter(-value)
    override operator fun unaryPlus(): Decimeter = this
    override operator fun inc(): Decimeter = Decimeter(this.value + 1)
    override operator fun dec(): Decimeter = Decimeter(this.value - 1)

    override fun round(): Decimeter = Decimeter(round(this.value))
    override fun truncate(): Decimeter = Decimeter(truncate(this.value))
    override fun floor(): Decimeter = Decimeter(floor(this.value))
    override fun ceil(): Decimeter = Decimeter(ceil(this.value))
    override fun abs(): Decimeter = Decimeter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Decimeter -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.decimeter
    get() = Decimeter(this)


val kotlin.Short.decimeter
    get() = Decimeter(this)


val kotlin.Int.decimeter
    get() = Decimeter(this)


val kotlin.Long.decimeter
    get() = Decimeter(this)


val kotlin.Float.decimeter
    get() = Decimeter(this)


val kotlin.Double.decimeter
    get() = Decimeter(this)


data class Centimeter(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "centimeter"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthCentimeter
    override fun toMeter() = Meter(value * (1 * (1e-2)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Centimeter(0.0)
        fun fromMeter(value: Meter) = Centimeter((value.value - 0) / (1 * (1e-2)))
    }

    operator fun plus(other: Centimeter): Centimeter = Centimeter(this.value + other.value)
    operator fun minus(other: Centimeter): Centimeter = Centimeter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Centimeter = Centimeter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Centimeter = Centimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Centimeter = Centimeter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Centimeter = Centimeter(this.value.times(other))
    override operator fun div(other: kotlin.Short): Centimeter = Centimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Centimeter = Centimeter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Centimeter = Centimeter(this.value.times(other))
    override operator fun div(other: kotlin.Int): Centimeter = Centimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Centimeter = Centimeter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Centimeter = Centimeter(this.value.times(other))
    override operator fun div(other: kotlin.Long): Centimeter = Centimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Centimeter = Centimeter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Centimeter = Centimeter(this.value.times(other))
    override operator fun div(other: kotlin.Float): Centimeter = Centimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Centimeter = Centimeter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Centimeter = Centimeter(this.value.times(other))
    override operator fun div(other: kotlin.Double): Centimeter = Centimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Centimeter = Centimeter(this.value.rem(other))


    override operator fun unaryMinus(): Centimeter = Centimeter(-value)
    override operator fun unaryPlus(): Centimeter = this
    override operator fun inc(): Centimeter = Centimeter(this.value + 1)
    override operator fun dec(): Centimeter = Centimeter(this.value - 1)

    override fun round(): Centimeter = Centimeter(round(this.value))
    override fun truncate(): Centimeter = Centimeter(truncate(this.value))
    override fun floor(): Centimeter = Centimeter(floor(this.value))
    override fun ceil(): Centimeter = Centimeter(ceil(this.value))
    override fun abs(): Centimeter = Centimeter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Centimeter -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.centimeter
    get() = Centimeter(this)


val kotlin.Short.centimeter
    get() = Centimeter(this)


val kotlin.Int.centimeter
    get() = Centimeter(this)


val kotlin.Long.centimeter
    get() = Centimeter(this)


val kotlin.Float.centimeter
    get() = Centimeter(this)


val kotlin.Double.centimeter
    get() = Centimeter(this)


data class Millimeter(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "millimeter"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthMillimeter
    override fun toMeter() = Meter(value * (1 * (1e-3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Millimeter(0.0)
        fun fromMeter(value: Meter) = Millimeter((value.value - 0) / (1 * (1e-3)))
    }

    operator fun plus(other: Millimeter): Millimeter = Millimeter(this.value + other.value)
    operator fun minus(other: Millimeter): Millimeter = Millimeter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Millimeter = Millimeter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Millimeter = Millimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Millimeter = Millimeter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Millimeter = Millimeter(this.value.times(other))
    override operator fun div(other: kotlin.Short): Millimeter = Millimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Millimeter = Millimeter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Millimeter = Millimeter(this.value.times(other))
    override operator fun div(other: kotlin.Int): Millimeter = Millimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Millimeter = Millimeter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Millimeter = Millimeter(this.value.times(other))
    override operator fun div(other: kotlin.Long): Millimeter = Millimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Millimeter = Millimeter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Millimeter = Millimeter(this.value.times(other))
    override operator fun div(other: kotlin.Float): Millimeter = Millimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Millimeter = Millimeter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Millimeter = Millimeter(this.value.times(other))
    override operator fun div(other: kotlin.Double): Millimeter = Millimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Millimeter = Millimeter(this.value.rem(other))


    override operator fun unaryMinus(): Millimeter = Millimeter(-value)
    override operator fun unaryPlus(): Millimeter = this
    override operator fun inc(): Millimeter = Millimeter(this.value + 1)
    override operator fun dec(): Millimeter = Millimeter(this.value - 1)

    override fun round(): Millimeter = Millimeter(round(this.value))
    override fun truncate(): Millimeter = Millimeter(truncate(this.value))
    override fun floor(): Millimeter = Millimeter(floor(this.value))
    override fun ceil(): Millimeter = Millimeter(ceil(this.value))
    override fun abs(): Millimeter = Millimeter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Millimeter -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.millimeter
    get() = Millimeter(this)


val kotlin.Short.millimeter
    get() = Millimeter(this)


val kotlin.Int.millimeter
    get() = Millimeter(this)


val kotlin.Long.millimeter
    get() = Millimeter(this)


val kotlin.Float.millimeter
    get() = Millimeter(this)


val kotlin.Double.millimeter
    get() = Millimeter(this)


data class Micrometer(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "micrometer"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthMicrometer
    override fun toMeter() = Meter(value * (1 * (1e-6)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Micrometer(0.0)
        fun fromMeter(value: Meter) = Micrometer((value.value - 0) / (1 * (1e-6)))
    }

    operator fun plus(other: Micrometer): Micrometer = Micrometer(this.value + other.value)
    operator fun minus(other: Micrometer): Micrometer = Micrometer(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Micrometer = Micrometer(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Micrometer = Micrometer(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Micrometer = Micrometer(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Micrometer = Micrometer(this.value.times(other))
    override operator fun div(other: kotlin.Short): Micrometer = Micrometer(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Micrometer = Micrometer(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Micrometer = Micrometer(this.value.times(other))
    override operator fun div(other: kotlin.Int): Micrometer = Micrometer(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Micrometer = Micrometer(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Micrometer = Micrometer(this.value.times(other))
    override operator fun div(other: kotlin.Long): Micrometer = Micrometer(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Micrometer = Micrometer(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Micrometer = Micrometer(this.value.times(other))
    override operator fun div(other: kotlin.Float): Micrometer = Micrometer(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Micrometer = Micrometer(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Micrometer = Micrometer(this.value.times(other))
    override operator fun div(other: kotlin.Double): Micrometer = Micrometer(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Micrometer = Micrometer(this.value.rem(other))


    override operator fun unaryMinus(): Micrometer = Micrometer(-value)
    override operator fun unaryPlus(): Micrometer = this
    override operator fun inc(): Micrometer = Micrometer(this.value + 1)
    override operator fun dec(): Micrometer = Micrometer(this.value - 1)

    override fun round(): Micrometer = Micrometer(round(this.value))
    override fun truncate(): Micrometer = Micrometer(truncate(this.value))
    override fun floor(): Micrometer = Micrometer(floor(this.value))
    override fun ceil(): Micrometer = Micrometer(ceil(this.value))
    override fun abs(): Micrometer = Micrometer(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Micrometer -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.micrometer
    get() = Micrometer(this)


val kotlin.Short.micrometer
    get() = Micrometer(this)


val kotlin.Int.micrometer
    get() = Micrometer(this)


val kotlin.Long.micrometer
    get() = Micrometer(this)


val kotlin.Float.micrometer
    get() = Micrometer(this)


val kotlin.Double.micrometer
    get() = Micrometer(this)


data class Nanometer(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "nanometer"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthNanometer
    override fun toMeter() = Meter(value * (1 * (1e-9)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Nanometer(0.0)
        fun fromMeter(value: Meter) = Nanometer((value.value - 0) / (1 * (1e-9)))
    }

    operator fun plus(other: Nanometer): Nanometer = Nanometer(this.value + other.value)
    operator fun minus(other: Nanometer): Nanometer = Nanometer(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Nanometer = Nanometer(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Nanometer = Nanometer(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Nanometer = Nanometer(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Nanometer = Nanometer(this.value.times(other))
    override operator fun div(other: kotlin.Short): Nanometer = Nanometer(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Nanometer = Nanometer(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Nanometer = Nanometer(this.value.times(other))
    override operator fun div(other: kotlin.Int): Nanometer = Nanometer(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Nanometer = Nanometer(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Nanometer = Nanometer(this.value.times(other))
    override operator fun div(other: kotlin.Long): Nanometer = Nanometer(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Nanometer = Nanometer(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Nanometer = Nanometer(this.value.times(other))
    override operator fun div(other: kotlin.Float): Nanometer = Nanometer(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Nanometer = Nanometer(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Nanometer = Nanometer(this.value.times(other))
    override operator fun div(other: kotlin.Double): Nanometer = Nanometer(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Nanometer = Nanometer(this.value.rem(other))


    override operator fun unaryMinus(): Nanometer = Nanometer(-value)
    override operator fun unaryPlus(): Nanometer = this
    override operator fun inc(): Nanometer = Nanometer(this.value + 1)
    override operator fun dec(): Nanometer = Nanometer(this.value - 1)

    override fun round(): Nanometer = Nanometer(round(this.value))
    override fun truncate(): Nanometer = Nanometer(truncate(this.value))
    override fun floor(): Nanometer = Nanometer(floor(this.value))
    override fun ceil(): Nanometer = Nanometer(ceil(this.value))
    override fun abs(): Nanometer = Nanometer(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Nanometer -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.nanometer
    get() = Nanometer(this)


val kotlin.Short.nanometer
    get() = Nanometer(this)


val kotlin.Int.nanometer
    get() = Nanometer(this)


val kotlin.Long.nanometer
    get() = Nanometer(this)


val kotlin.Float.nanometer
    get() = Nanometer(this)


val kotlin.Double.nanometer
    get() = Nanometer(this)


data class Picometer(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "picometer"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthPicometer
    override fun toMeter() = Meter(value * (1 * (1e-12)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Picometer(0.0)
        fun fromMeter(value: Meter) = Picometer((value.value - 0) / (1 * (1e-12)))
    }

    operator fun plus(other: Picometer): Picometer = Picometer(this.value + other.value)
    operator fun minus(other: Picometer): Picometer = Picometer(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Picometer = Picometer(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Picometer = Picometer(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Picometer = Picometer(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Picometer = Picometer(this.value.times(other))
    override operator fun div(other: kotlin.Short): Picometer = Picometer(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Picometer = Picometer(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Picometer = Picometer(this.value.times(other))
    override operator fun div(other: kotlin.Int): Picometer = Picometer(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Picometer = Picometer(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Picometer = Picometer(this.value.times(other))
    override operator fun div(other: kotlin.Long): Picometer = Picometer(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Picometer = Picometer(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Picometer = Picometer(this.value.times(other))
    override operator fun div(other: kotlin.Float): Picometer = Picometer(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Picometer = Picometer(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Picometer = Picometer(this.value.times(other))
    override operator fun div(other: kotlin.Double): Picometer = Picometer(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Picometer = Picometer(this.value.rem(other))


    override operator fun unaryMinus(): Picometer = Picometer(-value)
    override operator fun unaryPlus(): Picometer = this
    override operator fun inc(): Picometer = Picometer(this.value + 1)
    override operator fun dec(): Picometer = Picometer(this.value - 1)

    override fun round(): Picometer = Picometer(round(this.value))
    override fun truncate(): Picometer = Picometer(truncate(this.value))
    override fun floor(): Picometer = Picometer(floor(this.value))
    override fun ceil(): Picometer = Picometer(ceil(this.value))
    override fun abs(): Picometer = Picometer(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Picometer -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.picometer
    get() = Picometer(this)


val kotlin.Short.picometer
    get() = Picometer(this)


val kotlin.Int.picometer
    get() = Picometer(this)


val kotlin.Long.picometer
    get() = Picometer(this)


val kotlin.Float.picometer
    get() = Picometer(this)


val kotlin.Double.picometer
    get() = Picometer(this)


data class Mile(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "mile"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthMile
    override fun toMeter() = Meter(value * (1609.344) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Mile(0.0)
        fun fromMeter(value: Meter) = Mile((value.value - 0) / (1609.344))
    }

    operator fun plus(other: Mile): Mile = Mile(this.value + other.value)
    operator fun minus(other: Mile): Mile = Mile(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Mile = Mile(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Mile = Mile(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Mile = Mile(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Mile = Mile(this.value.times(other))
    override operator fun div(other: kotlin.Short): Mile = Mile(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Mile = Mile(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Mile = Mile(this.value.times(other))
    override operator fun div(other: kotlin.Int): Mile = Mile(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Mile = Mile(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Mile = Mile(this.value.times(other))
    override operator fun div(other: kotlin.Long): Mile = Mile(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Mile = Mile(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Mile = Mile(this.value.times(other))
    override operator fun div(other: kotlin.Float): Mile = Mile(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Mile = Mile(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Mile = Mile(this.value.times(other))
    override operator fun div(other: kotlin.Double): Mile = Mile(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Mile = Mile(this.value.rem(other))


    override operator fun unaryMinus(): Mile = Mile(-value)
    override operator fun unaryPlus(): Mile = this
    override operator fun inc(): Mile = Mile(this.value + 1)
    override operator fun dec(): Mile = Mile(this.value - 1)

    override fun round(): Mile = Mile(round(this.value))
    override fun truncate(): Mile = Mile(truncate(this.value))
    override fun floor(): Mile = Mile(floor(this.value))
    override fun ceil(): Mile = Mile(ceil(this.value))
    override fun abs(): Mile = Mile(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Mile -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.mile
    get() = Mile(this)


val kotlin.Short.mile
    get() = Mile(this)


val kotlin.Int.mile
    get() = Mile(this)


val kotlin.Long.mile
    get() = Mile(this)


val kotlin.Float.mile
    get() = Mile(this)


val kotlin.Double.mile
    get() = Mile(this)


data class Yard(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "yard"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthYard
    override fun toMeter() = Meter(value * (0.9144000000000001) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Yard(0.0)
        fun fromMeter(value: Meter) = Yard((value.value - 0) / (0.9144000000000001))
    }

    operator fun plus(other: Yard): Yard = Yard(this.value + other.value)
    operator fun minus(other: Yard): Yard = Yard(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Yard = Yard(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Yard = Yard(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Yard = Yard(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Yard = Yard(this.value.times(other))
    override operator fun div(other: kotlin.Short): Yard = Yard(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Yard = Yard(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Yard = Yard(this.value.times(other))
    override operator fun div(other: kotlin.Int): Yard = Yard(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Yard = Yard(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Yard = Yard(this.value.times(other))
    override operator fun div(other: kotlin.Long): Yard = Yard(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Yard = Yard(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Yard = Yard(this.value.times(other))
    override operator fun div(other: kotlin.Float): Yard = Yard(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Yard = Yard(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Yard = Yard(this.value.times(other))
    override operator fun div(other: kotlin.Double): Yard = Yard(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Yard = Yard(this.value.rem(other))


    override operator fun unaryMinus(): Yard = Yard(-value)
    override operator fun unaryPlus(): Yard = this
    override operator fun inc(): Yard = Yard(this.value + 1)
    override operator fun dec(): Yard = Yard(this.value - 1)

    override fun round(): Yard = Yard(round(this.value))
    override fun truncate(): Yard = Yard(truncate(this.value))
    override fun floor(): Yard = Yard(floor(this.value))
    override fun ceil(): Yard = Yard(ceil(this.value))
    override fun abs(): Yard = Yard(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Yard -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.yard
    get() = Yard(this)


val kotlin.Short.yard
    get() = Yard(this)


val kotlin.Int.yard
    get() = Yard(this)


val kotlin.Long.yard
    get() = Yard(this)


val kotlin.Float.yard
    get() = Yard(this)


val kotlin.Double.yard
    get() = Yard(this)


data class Foot(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "foot"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthFoot
    override fun toMeter() = Meter(value * (0.3048) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Foot(0.0)
        fun fromMeter(value: Meter) = Foot((value.value - 0) / (0.3048))
    }

    operator fun plus(other: Foot): Foot = Foot(this.value + other.value)
    operator fun minus(other: Foot): Foot = Foot(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Foot = Foot(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Foot = Foot(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Foot = Foot(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Foot = Foot(this.value.times(other))
    override operator fun div(other: kotlin.Short): Foot = Foot(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Foot = Foot(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Foot = Foot(this.value.times(other))
    override operator fun div(other: kotlin.Int): Foot = Foot(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Foot = Foot(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Foot = Foot(this.value.times(other))
    override operator fun div(other: kotlin.Long): Foot = Foot(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Foot = Foot(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Foot = Foot(this.value.times(other))
    override operator fun div(other: kotlin.Float): Foot = Foot(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Foot = Foot(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Foot = Foot(this.value.times(other))
    override operator fun div(other: kotlin.Double): Foot = Foot(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Foot = Foot(this.value.rem(other))


    override operator fun unaryMinus(): Foot = Foot(-value)
    override operator fun unaryPlus(): Foot = this
    override operator fun inc(): Foot = Foot(this.value + 1)
    override operator fun dec(): Foot = Foot(this.value - 1)

    override fun round(): Foot = Foot(round(this.value))
    override fun truncate(): Foot = Foot(truncate(this.value))
    override fun floor(): Foot = Foot(floor(this.value))
    override fun ceil(): Foot = Foot(ceil(this.value))
    override fun abs(): Foot = Foot(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Foot -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.foot
    get() = Foot(this)


val kotlin.Short.foot
    get() = Foot(this)


val kotlin.Int.foot
    get() = Foot(this)


val kotlin.Long.foot
    get() = Foot(this)


val kotlin.Float.foot
    get() = Foot(this)


val kotlin.Double.foot
    get() = Foot(this)


data class Inch(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "inch"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthInch
    override fun toMeter() = Meter(value * (0.025400000000000002) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Inch(0.0)
        fun fromMeter(value: Meter) = Inch((value.value - 0) / (0.025400000000000002))
    }

    operator fun plus(other: Inch): Inch = Inch(this.value + other.value)
    operator fun minus(other: Inch): Inch = Inch(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Inch = Inch(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Inch = Inch(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Inch = Inch(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Inch = Inch(this.value.times(other))
    override operator fun div(other: kotlin.Short): Inch = Inch(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Inch = Inch(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Inch = Inch(this.value.times(other))
    override operator fun div(other: kotlin.Int): Inch = Inch(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Inch = Inch(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Inch = Inch(this.value.times(other))
    override operator fun div(other: kotlin.Long): Inch = Inch(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Inch = Inch(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Inch = Inch(this.value.times(other))
    override operator fun div(other: kotlin.Float): Inch = Inch(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Inch = Inch(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Inch = Inch(this.value.times(other))
    override operator fun div(other: kotlin.Double): Inch = Inch(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Inch = Inch(this.value.rem(other))


    override operator fun unaryMinus(): Inch = Inch(-value)
    override operator fun unaryPlus(): Inch = this
    override operator fun inc(): Inch = Inch(this.value + 1)
    override operator fun dec(): Inch = Inch(this.value - 1)

    override fun round(): Inch = Inch(round(this.value))
    override fun truncate(): Inch = Inch(truncate(this.value))
    override fun floor(): Inch = Inch(floor(this.value))
    override fun ceil(): Inch = Inch(ceil(this.value))
    override fun abs(): Inch = Inch(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Inch -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.inch
    get() = Inch(this)


val kotlin.Short.inch
    get() = Inch(this)


val kotlin.Int.inch
    get() = Inch(this)


val kotlin.Long.inch
    get() = Inch(this)


val kotlin.Float.inch
    get() = Inch(this)


val kotlin.Double.inch
    get() = Inch(this)


data class Parsec(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "parsec"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthParsec
    override fun toMeter() = Meter(value * (3.085677581491367e+16) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Parsec(0.0)
        fun fromMeter(value: Meter) = Parsec((value.value - 0) / (3.085677581491367e+16))
    }

    operator fun plus(other: Parsec): Parsec = Parsec(this.value + other.value)
    operator fun minus(other: Parsec): Parsec = Parsec(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Parsec = Parsec(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Parsec = Parsec(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Parsec = Parsec(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Parsec = Parsec(this.value.times(other))
    override operator fun div(other: kotlin.Short): Parsec = Parsec(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Parsec = Parsec(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Parsec = Parsec(this.value.times(other))
    override operator fun div(other: kotlin.Int): Parsec = Parsec(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Parsec = Parsec(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Parsec = Parsec(this.value.times(other))
    override operator fun div(other: kotlin.Long): Parsec = Parsec(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Parsec = Parsec(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Parsec = Parsec(this.value.times(other))
    override operator fun div(other: kotlin.Float): Parsec = Parsec(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Parsec = Parsec(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Parsec = Parsec(this.value.times(other))
    override operator fun div(other: kotlin.Double): Parsec = Parsec(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Parsec = Parsec(this.value.rem(other))


    override operator fun unaryMinus(): Parsec = Parsec(-value)
    override operator fun unaryPlus(): Parsec = this
    override operator fun inc(): Parsec = Parsec(this.value + 1)
    override operator fun dec(): Parsec = Parsec(this.value - 1)

    override fun round(): Parsec = Parsec(round(this.value))
    override fun truncate(): Parsec = Parsec(truncate(this.value))
    override fun floor(): Parsec = Parsec(floor(this.value))
    override fun ceil(): Parsec = Parsec(ceil(this.value))
    override fun abs(): Parsec = Parsec(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Parsec -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.parsec
    get() = Parsec(this)


val kotlin.Short.parsec
    get() = Parsec(this)


val kotlin.Int.parsec
    get() = Parsec(this)


val kotlin.Long.parsec
    get() = Parsec(this)


val kotlin.Float.parsec
    get() = Parsec(this)


val kotlin.Double.parsec
    get() = Parsec(this)


data class LightYear(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "light-year"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthLightYear
    override fun toMeter() = Meter(value * (9460730472580800) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = LightYear(0.0)
        fun fromMeter(value: Meter) = LightYear((value.value - 0) / (9460730472580800))
    }

    operator fun plus(other: LightYear): LightYear = LightYear(this.value + other.value)
    operator fun minus(other: LightYear): LightYear = LightYear(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): LightYear = LightYear(this.value.times(other))
    override operator fun div(other: kotlin.Byte): LightYear = LightYear(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): LightYear = LightYear(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): LightYear = LightYear(this.value.times(other))
    override operator fun div(other: kotlin.Short): LightYear = LightYear(this.value.div(other))
    override operator fun rem(other: kotlin.Short): LightYear = LightYear(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): LightYear = LightYear(this.value.times(other))
    override operator fun div(other: kotlin.Int): LightYear = LightYear(this.value.div(other))
    override operator fun rem(other: kotlin.Int): LightYear = LightYear(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): LightYear = LightYear(this.value.times(other))
    override operator fun div(other: kotlin.Long): LightYear = LightYear(this.value.div(other))
    override operator fun rem(other: kotlin.Long): LightYear = LightYear(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): LightYear = LightYear(this.value.times(other))
    override operator fun div(other: kotlin.Float): LightYear = LightYear(this.value.div(other))
    override operator fun rem(other: kotlin.Float): LightYear = LightYear(this.value.rem(other))


    override operator fun times(other: kotlin.Double): LightYear = LightYear(this.value.times(other))
    override operator fun div(other: kotlin.Double): LightYear = LightYear(this.value.div(other))
    override operator fun rem(other: kotlin.Double): LightYear = LightYear(this.value.rem(other))


    override operator fun unaryMinus(): LightYear = LightYear(-value)
    override operator fun unaryPlus(): LightYear = this
    override operator fun inc(): LightYear = LightYear(this.value + 1)
    override operator fun dec(): LightYear = LightYear(this.value - 1)

    override fun round(): LightYear = LightYear(round(this.value))
    override fun truncate(): LightYear = LightYear(truncate(this.value))
    override fun floor(): LightYear = LightYear(floor(this.value))
    override fun ceil(): LightYear = LightYear(ceil(this.value))
    override fun abs(): LightYear = LightYear(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is LightYear -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.lightYear
    get() = LightYear(this)


val kotlin.Short.lightYear
    get() = LightYear(this)


val kotlin.Int.lightYear
    get() = LightYear(this)


val kotlin.Long.lightYear
    get() = LightYear(this)


val kotlin.Float.lightYear
    get() = LightYear(this)


val kotlin.Double.lightYear
    get() = LightYear(this)


data class AstronomicalUnit(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "astronomical-unit"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthAstronomicalUnit
    override fun toMeter() = Meter(value * (149597870700) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = AstronomicalUnit(0.0)
        fun fromMeter(value: Meter) = AstronomicalUnit((value.value - 0) / (149597870700))
    }

    operator fun plus(other: AstronomicalUnit): AstronomicalUnit = AstronomicalUnit(this.value + other.value)
    operator fun minus(other: AstronomicalUnit): AstronomicalUnit = AstronomicalUnit(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): AstronomicalUnit = AstronomicalUnit(this.value.times(other))
    override operator fun div(other: kotlin.Byte): AstronomicalUnit = AstronomicalUnit(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): AstronomicalUnit = AstronomicalUnit(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): AstronomicalUnit = AstronomicalUnit(this.value.times(other))
    override operator fun div(other: kotlin.Short): AstronomicalUnit = AstronomicalUnit(this.value.div(other))
    override operator fun rem(other: kotlin.Short): AstronomicalUnit = AstronomicalUnit(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): AstronomicalUnit = AstronomicalUnit(this.value.times(other))
    override operator fun div(other: kotlin.Int): AstronomicalUnit = AstronomicalUnit(this.value.div(other))
    override operator fun rem(other: kotlin.Int): AstronomicalUnit = AstronomicalUnit(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): AstronomicalUnit = AstronomicalUnit(this.value.times(other))
    override operator fun div(other: kotlin.Long): AstronomicalUnit = AstronomicalUnit(this.value.div(other))
    override operator fun rem(other: kotlin.Long): AstronomicalUnit = AstronomicalUnit(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): AstronomicalUnit = AstronomicalUnit(this.value.times(other))
    override operator fun div(other: kotlin.Float): AstronomicalUnit = AstronomicalUnit(this.value.div(other))
    override operator fun rem(other: kotlin.Float): AstronomicalUnit = AstronomicalUnit(this.value.rem(other))


    override operator fun times(other: kotlin.Double): AstronomicalUnit = AstronomicalUnit(this.value.times(other))
    override operator fun div(other: kotlin.Double): AstronomicalUnit = AstronomicalUnit(this.value.div(other))
    override operator fun rem(other: kotlin.Double): AstronomicalUnit = AstronomicalUnit(this.value.rem(other))


    override operator fun unaryMinus(): AstronomicalUnit = AstronomicalUnit(-value)
    override operator fun unaryPlus(): AstronomicalUnit = this
    override operator fun inc(): AstronomicalUnit = AstronomicalUnit(this.value + 1)
    override operator fun dec(): AstronomicalUnit = AstronomicalUnit(this.value - 1)

    override fun round(): AstronomicalUnit = AstronomicalUnit(round(this.value))
    override fun truncate(): AstronomicalUnit = AstronomicalUnit(truncate(this.value))
    override fun floor(): AstronomicalUnit = AstronomicalUnit(floor(this.value))
    override fun ceil(): AstronomicalUnit = AstronomicalUnit(ceil(this.value))
    override fun abs(): AstronomicalUnit = AstronomicalUnit(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is AstronomicalUnit -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.astronomicalUnit
    get() = AstronomicalUnit(this)


val kotlin.Short.astronomicalUnit
    get() = AstronomicalUnit(this)


val kotlin.Int.astronomicalUnit
    get() = AstronomicalUnit(this)


val kotlin.Long.astronomicalUnit
    get() = AstronomicalUnit(this)


val kotlin.Float.astronomicalUnit
    get() = AstronomicalUnit(this)


val kotlin.Double.astronomicalUnit
    get() = AstronomicalUnit(this)


data class Furlong(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "furlong"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthFurlong
    override fun toMeter() = Meter(value * (201.168) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Furlong(0.0)
        fun fromMeter(value: Meter) = Furlong((value.value - 0) / (201.168))
    }

    operator fun plus(other: Furlong): Furlong = Furlong(this.value + other.value)
    operator fun minus(other: Furlong): Furlong = Furlong(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Furlong = Furlong(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Furlong = Furlong(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Furlong = Furlong(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Furlong = Furlong(this.value.times(other))
    override operator fun div(other: kotlin.Short): Furlong = Furlong(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Furlong = Furlong(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Furlong = Furlong(this.value.times(other))
    override operator fun div(other: kotlin.Int): Furlong = Furlong(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Furlong = Furlong(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Furlong = Furlong(this.value.times(other))
    override operator fun div(other: kotlin.Long): Furlong = Furlong(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Furlong = Furlong(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Furlong = Furlong(this.value.times(other))
    override operator fun div(other: kotlin.Float): Furlong = Furlong(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Furlong = Furlong(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Furlong = Furlong(this.value.times(other))
    override operator fun div(other: kotlin.Double): Furlong = Furlong(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Furlong = Furlong(this.value.rem(other))


    override operator fun unaryMinus(): Furlong = Furlong(-value)
    override operator fun unaryPlus(): Furlong = this
    override operator fun inc(): Furlong = Furlong(this.value + 1)
    override operator fun dec(): Furlong = Furlong(this.value - 1)

    override fun round(): Furlong = Furlong(round(this.value))
    override fun truncate(): Furlong = Furlong(truncate(this.value))
    override fun floor(): Furlong = Furlong(floor(this.value))
    override fun ceil(): Furlong = Furlong(ceil(this.value))
    override fun abs(): Furlong = Furlong(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Furlong -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.furlong
    get() = Furlong(this)


val kotlin.Short.furlong
    get() = Furlong(this)


val kotlin.Int.furlong
    get() = Furlong(this)


val kotlin.Long.furlong
    get() = Furlong(this)


val kotlin.Float.furlong
    get() = Furlong(this)


val kotlin.Double.furlong
    get() = Furlong(this)


data class Fathom(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "fathom"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthFathom
    override fun toMeter() = Meter(value * (1.8288000000000002) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Fathom(0.0)
        fun fromMeter(value: Meter) = Fathom((value.value - 0) / (1.8288000000000002))
    }

    operator fun plus(other: Fathom): Fathom = Fathom(this.value + other.value)
    operator fun minus(other: Fathom): Fathom = Fathom(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Fathom = Fathom(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Fathom = Fathom(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Fathom = Fathom(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Fathom = Fathom(this.value.times(other))
    override operator fun div(other: kotlin.Short): Fathom = Fathom(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Fathom = Fathom(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Fathom = Fathom(this.value.times(other))
    override operator fun div(other: kotlin.Int): Fathom = Fathom(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Fathom = Fathom(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Fathom = Fathom(this.value.times(other))
    override operator fun div(other: kotlin.Long): Fathom = Fathom(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Fathom = Fathom(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Fathom = Fathom(this.value.times(other))
    override operator fun div(other: kotlin.Float): Fathom = Fathom(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Fathom = Fathom(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Fathom = Fathom(this.value.times(other))
    override operator fun div(other: kotlin.Double): Fathom = Fathom(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Fathom = Fathom(this.value.rem(other))


    override operator fun unaryMinus(): Fathom = Fathom(-value)
    override operator fun unaryPlus(): Fathom = this
    override operator fun inc(): Fathom = Fathom(this.value + 1)
    override operator fun dec(): Fathom = Fathom(this.value - 1)

    override fun round(): Fathom = Fathom(round(this.value))
    override fun truncate(): Fathom = Fathom(truncate(this.value))
    override fun floor(): Fathom = Fathom(floor(this.value))
    override fun ceil(): Fathom = Fathom(ceil(this.value))
    override fun abs(): Fathom = Fathom(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Fathom -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.fathom
    get() = Fathom(this)


val kotlin.Short.fathom
    get() = Fathom(this)


val kotlin.Int.fathom
    get() = Fathom(this)


val kotlin.Long.fathom
    get() = Fathom(this)


val kotlin.Float.fathom
    get() = Fathom(this)


val kotlin.Double.fathom
    get() = Fathom(this)


data class NauticalMile(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "nautical-mile"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthNauticalMile
    override fun toMeter() = Meter(value * (1852) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = NauticalMile(0.0)
        fun fromMeter(value: Meter) = NauticalMile((value.value - 0) / (1852))
    }

    operator fun plus(other: NauticalMile): NauticalMile = NauticalMile(this.value + other.value)
    operator fun minus(other: NauticalMile): NauticalMile = NauticalMile(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): NauticalMile = NauticalMile(this.value.times(other))
    override operator fun div(other: kotlin.Byte): NauticalMile = NauticalMile(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): NauticalMile = NauticalMile(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): NauticalMile = NauticalMile(this.value.times(other))
    override operator fun div(other: kotlin.Short): NauticalMile = NauticalMile(this.value.div(other))
    override operator fun rem(other: kotlin.Short): NauticalMile = NauticalMile(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): NauticalMile = NauticalMile(this.value.times(other))
    override operator fun div(other: kotlin.Int): NauticalMile = NauticalMile(this.value.div(other))
    override operator fun rem(other: kotlin.Int): NauticalMile = NauticalMile(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): NauticalMile = NauticalMile(this.value.times(other))
    override operator fun div(other: kotlin.Long): NauticalMile = NauticalMile(this.value.div(other))
    override operator fun rem(other: kotlin.Long): NauticalMile = NauticalMile(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): NauticalMile = NauticalMile(this.value.times(other))
    override operator fun div(other: kotlin.Float): NauticalMile = NauticalMile(this.value.div(other))
    override operator fun rem(other: kotlin.Float): NauticalMile = NauticalMile(this.value.rem(other))


    override operator fun times(other: kotlin.Double): NauticalMile = NauticalMile(this.value.times(other))
    override operator fun div(other: kotlin.Double): NauticalMile = NauticalMile(this.value.div(other))
    override operator fun rem(other: kotlin.Double): NauticalMile = NauticalMile(this.value.rem(other))


    override operator fun unaryMinus(): NauticalMile = NauticalMile(-value)
    override operator fun unaryPlus(): NauticalMile = this
    override operator fun inc(): NauticalMile = NauticalMile(this.value + 1)
    override operator fun dec(): NauticalMile = NauticalMile(this.value - 1)

    override fun round(): NauticalMile = NauticalMile(round(this.value))
    override fun truncate(): NauticalMile = NauticalMile(truncate(this.value))
    override fun floor(): NauticalMile = NauticalMile(floor(this.value))
    override fun ceil(): NauticalMile = NauticalMile(ceil(this.value))
    override fun abs(): NauticalMile = NauticalMile(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is NauticalMile -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.nauticalMile
    get() = NauticalMile(this)


val kotlin.Short.nauticalMile
    get() = NauticalMile(this)


val kotlin.Int.nauticalMile
    get() = NauticalMile(this)


val kotlin.Long.nauticalMile
    get() = NauticalMile(this)


val kotlin.Float.nauticalMile
    get() = NauticalMile(this)


val kotlin.Double.nauticalMile
    get() = NauticalMile(this)


data class MileScandinavian(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "mile-scandinavian"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthMileScandinavian
    override fun toMeter() = Meter(value * (10000) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = MileScandinavian(0.0)
        fun fromMeter(value: Meter) = MileScandinavian((value.value - 0) / (10000))
    }

    operator fun plus(other: MileScandinavian): MileScandinavian = MileScandinavian(this.value + other.value)
    operator fun minus(other: MileScandinavian): MileScandinavian = MileScandinavian(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): MileScandinavian = MileScandinavian(this.value.times(other))
    override operator fun div(other: kotlin.Byte): MileScandinavian = MileScandinavian(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): MileScandinavian = MileScandinavian(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): MileScandinavian = MileScandinavian(this.value.times(other))
    override operator fun div(other: kotlin.Short): MileScandinavian = MileScandinavian(this.value.div(other))
    override operator fun rem(other: kotlin.Short): MileScandinavian = MileScandinavian(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): MileScandinavian = MileScandinavian(this.value.times(other))
    override operator fun div(other: kotlin.Int): MileScandinavian = MileScandinavian(this.value.div(other))
    override operator fun rem(other: kotlin.Int): MileScandinavian = MileScandinavian(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): MileScandinavian = MileScandinavian(this.value.times(other))
    override operator fun div(other: kotlin.Long): MileScandinavian = MileScandinavian(this.value.div(other))
    override operator fun rem(other: kotlin.Long): MileScandinavian = MileScandinavian(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): MileScandinavian = MileScandinavian(this.value.times(other))
    override operator fun div(other: kotlin.Float): MileScandinavian = MileScandinavian(this.value.div(other))
    override operator fun rem(other: kotlin.Float): MileScandinavian = MileScandinavian(this.value.rem(other))


    override operator fun times(other: kotlin.Double): MileScandinavian = MileScandinavian(this.value.times(other))
    override operator fun div(other: kotlin.Double): MileScandinavian = MileScandinavian(this.value.div(other))
    override operator fun rem(other: kotlin.Double): MileScandinavian = MileScandinavian(this.value.rem(other))


    override operator fun unaryMinus(): MileScandinavian = MileScandinavian(-value)
    override operator fun unaryPlus(): MileScandinavian = this
    override operator fun inc(): MileScandinavian = MileScandinavian(this.value + 1)
    override operator fun dec(): MileScandinavian = MileScandinavian(this.value - 1)

    override fun round(): MileScandinavian = MileScandinavian(round(this.value))
    override fun truncate(): MileScandinavian = MileScandinavian(truncate(this.value))
    override fun floor(): MileScandinavian = MileScandinavian(floor(this.value))
    override fun ceil(): MileScandinavian = MileScandinavian(ceil(this.value))
    override fun abs(): MileScandinavian = MileScandinavian(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is MileScandinavian -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.mileScandinavian
    get() = MileScandinavian(this)


val kotlin.Short.mileScandinavian
    get() = MileScandinavian(this)


val kotlin.Int.mileScandinavian
    get() = MileScandinavian(this)


val kotlin.Long.mileScandinavian
    get() = MileScandinavian(this)


val kotlin.Float.mileScandinavian
    get() = MileScandinavian(this)


val kotlin.Double.mileScandinavian
    get() = MileScandinavian(this)


data class Point(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "point"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthPoint
    override fun toMeter() = Meter(value * (0.0003527777777777778) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Point(0.0)
        fun fromMeter(value: Meter) = Point((value.value - 0) / (0.0003527777777777778))
    }

    operator fun plus(other: Point): Point = Point(this.value + other.value)
    operator fun minus(other: Point): Point = Point(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Point = Point(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Point = Point(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Point = Point(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Point = Point(this.value.times(other))
    override operator fun div(other: kotlin.Short): Point = Point(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Point = Point(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Point = Point(this.value.times(other))
    override operator fun div(other: kotlin.Int): Point = Point(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Point = Point(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Point = Point(this.value.times(other))
    override operator fun div(other: kotlin.Long): Point = Point(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Point = Point(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Point = Point(this.value.times(other))
    override operator fun div(other: kotlin.Float): Point = Point(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Point = Point(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Point = Point(this.value.times(other))
    override operator fun div(other: kotlin.Double): Point = Point(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Point = Point(this.value.rem(other))


    override operator fun unaryMinus(): Point = Point(-value)
    override operator fun unaryPlus(): Point = this
    override operator fun inc(): Point = Point(this.value + 1)
    override operator fun dec(): Point = Point(this.value - 1)

    override fun round(): Point = Point(round(this.value))
    override fun truncate(): Point = Point(truncate(this.value))
    override fun floor(): Point = Point(floor(this.value))
    override fun ceil(): Point = Point(ceil(this.value))
    override fun abs(): Point = Point(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Point -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.point
    get() = Point(this)


val kotlin.Short.point
    get() = Point(this)


val kotlin.Int.point
    get() = Point(this)


val kotlin.Long.point
    get() = Point(this)


val kotlin.Float.point
    get() = Point(this)


val kotlin.Double.point
    get() = Point(this)


data class SolarRadius(override val value: MeasurementUnitValue) : Length {

    override val unitName: String = "solar-radius"

    override fun formatters(language: MeasurementUnitLanguage) = language.lengthSolarRadius
    override fun toMeter() = Meter(value * (695700000) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = SolarRadius(0.0)
        fun fromMeter(value: Meter) = SolarRadius((value.value - 0) / (695700000))
    }

    operator fun plus(other: SolarRadius): SolarRadius = SolarRadius(this.value + other.value)
    operator fun minus(other: SolarRadius): SolarRadius = SolarRadius(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): SolarRadius = SolarRadius(this.value.times(other))
    override operator fun div(other: kotlin.Byte): SolarRadius = SolarRadius(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): SolarRadius = SolarRadius(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): SolarRadius = SolarRadius(this.value.times(other))
    override operator fun div(other: kotlin.Short): SolarRadius = SolarRadius(this.value.div(other))
    override operator fun rem(other: kotlin.Short): SolarRadius = SolarRadius(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): SolarRadius = SolarRadius(this.value.times(other))
    override operator fun div(other: kotlin.Int): SolarRadius = SolarRadius(this.value.div(other))
    override operator fun rem(other: kotlin.Int): SolarRadius = SolarRadius(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): SolarRadius = SolarRadius(this.value.times(other))
    override operator fun div(other: kotlin.Long): SolarRadius = SolarRadius(this.value.div(other))
    override operator fun rem(other: kotlin.Long): SolarRadius = SolarRadius(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): SolarRadius = SolarRadius(this.value.times(other))
    override operator fun div(other: kotlin.Float): SolarRadius = SolarRadius(this.value.div(other))
    override operator fun rem(other: kotlin.Float): SolarRadius = SolarRadius(this.value.rem(other))


    override operator fun times(other: kotlin.Double): SolarRadius = SolarRadius(this.value.times(other))
    override operator fun div(other: kotlin.Double): SolarRadius = SolarRadius(this.value.div(other))
    override operator fun rem(other: kotlin.Double): SolarRadius = SolarRadius(this.value.rem(other))


    override operator fun unaryMinus(): SolarRadius = SolarRadius(-value)
    override operator fun unaryPlus(): SolarRadius = this
    override operator fun inc(): SolarRadius = SolarRadius(this.value + 1)
    override operator fun dec(): SolarRadius = SolarRadius(this.value - 1)

    override fun round(): SolarRadius = SolarRadius(round(this.value))
    override fun truncate(): SolarRadius = SolarRadius(truncate(this.value))
    override fun floor(): SolarRadius = SolarRadius(floor(this.value))
    override fun ceil(): SolarRadius = SolarRadius(ceil(this.value))
    override fun abs(): SolarRadius = SolarRadius(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is SolarRadius -> this.value == other.value
        is Length -> this.toMeter().value == other.toMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeter().value.hashCode()


}

val kotlin.Byte.solarRadius
    get() = SolarRadius(this)


val kotlin.Short.solarRadius
    get() = SolarRadius(this)


val kotlin.Int.solarRadius
    get() = SolarRadius(this)


val kotlin.Long.solarRadius
    get() = SolarRadius(this)


val kotlin.Float.solarRadius
    get() = SolarRadius(this)


val kotlin.Double.solarRadius
    get() = SolarRadius(this)
                            
