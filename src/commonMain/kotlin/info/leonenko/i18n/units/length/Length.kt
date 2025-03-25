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


    operator fun times(other: Byte): Length
    operator fun div(other: Byte): Length
    operator fun rem(other: Byte): Length


    operator fun times(other: Short): Length
    operator fun div(other: Short): Length
    operator fun rem(other: Short): Length


    operator fun times(other: Int): Length
    operator fun div(other: Int): Length
    operator fun rem(other: Int): Length


    operator fun times(other: Long): Length
    operator fun div(other: Long): Length
    operator fun rem(other: Long): Length


    operator fun times(other: Float): Length
    operator fun div(other: Float): Length
    operator fun rem(other: Float): Length


    operator fun times(other: Double): Length
    operator fun div(other: Double): Length
    operator fun rem(other: Double): Length

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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): EarthRadius = EarthRadius(this.value.times(other))
    override operator fun div(other: Byte): EarthRadius = EarthRadius(this.value.div(other))
    override operator fun rem(other: Byte): EarthRadius = EarthRadius(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): EarthRadius = EarthRadius(this.value.times(other))
    override operator fun div(other: Short): EarthRadius = EarthRadius(this.value.div(other))
    override operator fun rem(other: Short): EarthRadius = EarthRadius(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): EarthRadius = EarthRadius(this.value.times(other))
    override operator fun div(other: Int): EarthRadius = EarthRadius(this.value.div(other))
    override operator fun rem(other: Int): EarthRadius = EarthRadius(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): EarthRadius = EarthRadius(this.value.times(other))
    override operator fun div(other: Long): EarthRadius = EarthRadius(this.value.div(other))
    override operator fun rem(other: Long): EarthRadius = EarthRadius(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): EarthRadius = EarthRadius(this.value.times(other))
    override operator fun div(other: Float): EarthRadius = EarthRadius(this.value.div(other))
    override operator fun rem(other: Float): EarthRadius = EarthRadius(this.value.rem(other))


    override operator fun times(other: Double): EarthRadius = EarthRadius(this.value.times(other))
    override operator fun div(other: Double): EarthRadius = EarthRadius(this.value.div(other))
    override operator fun rem(other: Double): EarthRadius = EarthRadius(this.value.rem(other))


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

val Byte.earthRadius
    get() = EarthRadius(this)


val Short.earthRadius
    get() = EarthRadius(this)


val Int.earthRadius
    get() = EarthRadius(this)


val Long.earthRadius
    get() = EarthRadius(this)


val Float.earthRadius
    get() = EarthRadius(this)


val Double.earthRadius
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Kilometer = Kilometer(this.value.times(other))
    override operator fun div(other: Byte): Kilometer = Kilometer(this.value.div(other))
    override operator fun rem(other: Byte): Kilometer = Kilometer(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Kilometer = Kilometer(this.value.times(other))
    override operator fun div(other: Short): Kilometer = Kilometer(this.value.div(other))
    override operator fun rem(other: Short): Kilometer = Kilometer(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Kilometer = Kilometer(this.value.times(other))
    override operator fun div(other: Int): Kilometer = Kilometer(this.value.div(other))
    override operator fun rem(other: Int): Kilometer = Kilometer(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Kilometer = Kilometer(this.value.times(other))
    override operator fun div(other: Long): Kilometer = Kilometer(this.value.div(other))
    override operator fun rem(other: Long): Kilometer = Kilometer(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Kilometer = Kilometer(this.value.times(other))
    override operator fun div(other: Float): Kilometer = Kilometer(this.value.div(other))
    override operator fun rem(other: Float): Kilometer = Kilometer(this.value.rem(other))


    override operator fun times(other: Double): Kilometer = Kilometer(this.value.times(other))
    override operator fun div(other: Double): Kilometer = Kilometer(this.value.div(other))
    override operator fun rem(other: Double): Kilometer = Kilometer(this.value.rem(other))


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

val Byte.kilometer
    get() = Kilometer(this)


val Short.kilometer
    get() = Kilometer(this)


val Int.kilometer
    get() = Kilometer(this)


val Long.kilometer
    get() = Kilometer(this)


val Float.kilometer
    get() = Kilometer(this)


val Double.kilometer
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Meter = Meter(this.value.times(other))
    override operator fun div(other: Byte): Meter = Meter(this.value.div(other))
    override operator fun rem(other: Byte): Meter = Meter(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Meter = Meter(this.value.times(other))
    override operator fun div(other: Short): Meter = Meter(this.value.div(other))
    override operator fun rem(other: Short): Meter = Meter(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Meter = Meter(this.value.times(other))
    override operator fun div(other: Int): Meter = Meter(this.value.div(other))
    override operator fun rem(other: Int): Meter = Meter(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Meter = Meter(this.value.times(other))
    override operator fun div(other: Long): Meter = Meter(this.value.div(other))
    override operator fun rem(other: Long): Meter = Meter(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Meter = Meter(this.value.times(other))
    override operator fun div(other: Float): Meter = Meter(this.value.div(other))
    override operator fun rem(other: Float): Meter = Meter(this.value.rem(other))


    override operator fun times(other: Double): Meter = Meter(this.value.times(other))
    override operator fun div(other: Double): Meter = Meter(this.value.div(other))
    override operator fun rem(other: Double): Meter = Meter(this.value.rem(other))


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

val Byte.meter
    get() = Meter(this)


val Short.meter
    get() = Meter(this)


val Int.meter
    get() = Meter(this)


val Long.meter
    get() = Meter(this)


val Float.meter
    get() = Meter(this)


val Double.meter
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Decimeter = Decimeter(this.value.times(other))
    override operator fun div(other: Byte): Decimeter = Decimeter(this.value.div(other))
    override operator fun rem(other: Byte): Decimeter = Decimeter(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Decimeter = Decimeter(this.value.times(other))
    override operator fun div(other: Short): Decimeter = Decimeter(this.value.div(other))
    override operator fun rem(other: Short): Decimeter = Decimeter(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Decimeter = Decimeter(this.value.times(other))
    override operator fun div(other: Int): Decimeter = Decimeter(this.value.div(other))
    override operator fun rem(other: Int): Decimeter = Decimeter(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Decimeter = Decimeter(this.value.times(other))
    override operator fun div(other: Long): Decimeter = Decimeter(this.value.div(other))
    override operator fun rem(other: Long): Decimeter = Decimeter(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Decimeter = Decimeter(this.value.times(other))
    override operator fun div(other: Float): Decimeter = Decimeter(this.value.div(other))
    override operator fun rem(other: Float): Decimeter = Decimeter(this.value.rem(other))


    override operator fun times(other: Double): Decimeter = Decimeter(this.value.times(other))
    override operator fun div(other: Double): Decimeter = Decimeter(this.value.div(other))
    override operator fun rem(other: Double): Decimeter = Decimeter(this.value.rem(other))


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

val Byte.decimeter
    get() = Decimeter(this)


val Short.decimeter
    get() = Decimeter(this)


val Int.decimeter
    get() = Decimeter(this)


val Long.decimeter
    get() = Decimeter(this)


val Float.decimeter
    get() = Decimeter(this)


val Double.decimeter
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Centimeter = Centimeter(this.value.times(other))
    override operator fun div(other: Byte): Centimeter = Centimeter(this.value.div(other))
    override operator fun rem(other: Byte): Centimeter = Centimeter(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Centimeter = Centimeter(this.value.times(other))
    override operator fun div(other: Short): Centimeter = Centimeter(this.value.div(other))
    override operator fun rem(other: Short): Centimeter = Centimeter(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Centimeter = Centimeter(this.value.times(other))
    override operator fun div(other: Int): Centimeter = Centimeter(this.value.div(other))
    override operator fun rem(other: Int): Centimeter = Centimeter(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Centimeter = Centimeter(this.value.times(other))
    override operator fun div(other: Long): Centimeter = Centimeter(this.value.div(other))
    override operator fun rem(other: Long): Centimeter = Centimeter(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Centimeter = Centimeter(this.value.times(other))
    override operator fun div(other: Float): Centimeter = Centimeter(this.value.div(other))
    override operator fun rem(other: Float): Centimeter = Centimeter(this.value.rem(other))


    override operator fun times(other: Double): Centimeter = Centimeter(this.value.times(other))
    override operator fun div(other: Double): Centimeter = Centimeter(this.value.div(other))
    override operator fun rem(other: Double): Centimeter = Centimeter(this.value.rem(other))


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

val Byte.centimeter
    get() = Centimeter(this)


val Short.centimeter
    get() = Centimeter(this)


val Int.centimeter
    get() = Centimeter(this)


val Long.centimeter
    get() = Centimeter(this)


val Float.centimeter
    get() = Centimeter(this)


val Double.centimeter
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Millimeter = Millimeter(this.value.times(other))
    override operator fun div(other: Byte): Millimeter = Millimeter(this.value.div(other))
    override operator fun rem(other: Byte): Millimeter = Millimeter(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Millimeter = Millimeter(this.value.times(other))
    override operator fun div(other: Short): Millimeter = Millimeter(this.value.div(other))
    override operator fun rem(other: Short): Millimeter = Millimeter(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Millimeter = Millimeter(this.value.times(other))
    override operator fun div(other: Int): Millimeter = Millimeter(this.value.div(other))
    override operator fun rem(other: Int): Millimeter = Millimeter(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Millimeter = Millimeter(this.value.times(other))
    override operator fun div(other: Long): Millimeter = Millimeter(this.value.div(other))
    override operator fun rem(other: Long): Millimeter = Millimeter(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Millimeter = Millimeter(this.value.times(other))
    override operator fun div(other: Float): Millimeter = Millimeter(this.value.div(other))
    override operator fun rem(other: Float): Millimeter = Millimeter(this.value.rem(other))


    override operator fun times(other: Double): Millimeter = Millimeter(this.value.times(other))
    override operator fun div(other: Double): Millimeter = Millimeter(this.value.div(other))
    override operator fun rem(other: Double): Millimeter = Millimeter(this.value.rem(other))


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

val Byte.millimeter
    get() = Millimeter(this)


val Short.millimeter
    get() = Millimeter(this)


val Int.millimeter
    get() = Millimeter(this)


val Long.millimeter
    get() = Millimeter(this)


val Float.millimeter
    get() = Millimeter(this)


val Double.millimeter
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Micrometer = Micrometer(this.value.times(other))
    override operator fun div(other: Byte): Micrometer = Micrometer(this.value.div(other))
    override operator fun rem(other: Byte): Micrometer = Micrometer(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Micrometer = Micrometer(this.value.times(other))
    override operator fun div(other: Short): Micrometer = Micrometer(this.value.div(other))
    override operator fun rem(other: Short): Micrometer = Micrometer(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Micrometer = Micrometer(this.value.times(other))
    override operator fun div(other: Int): Micrometer = Micrometer(this.value.div(other))
    override operator fun rem(other: Int): Micrometer = Micrometer(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Micrometer = Micrometer(this.value.times(other))
    override operator fun div(other: Long): Micrometer = Micrometer(this.value.div(other))
    override operator fun rem(other: Long): Micrometer = Micrometer(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Micrometer = Micrometer(this.value.times(other))
    override operator fun div(other: Float): Micrometer = Micrometer(this.value.div(other))
    override operator fun rem(other: Float): Micrometer = Micrometer(this.value.rem(other))


    override operator fun times(other: Double): Micrometer = Micrometer(this.value.times(other))
    override operator fun div(other: Double): Micrometer = Micrometer(this.value.div(other))
    override operator fun rem(other: Double): Micrometer = Micrometer(this.value.rem(other))


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

val Byte.micrometer
    get() = Micrometer(this)


val Short.micrometer
    get() = Micrometer(this)


val Int.micrometer
    get() = Micrometer(this)


val Long.micrometer
    get() = Micrometer(this)


val Float.micrometer
    get() = Micrometer(this)


val Double.micrometer
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Nanometer = Nanometer(this.value.times(other))
    override operator fun div(other: Byte): Nanometer = Nanometer(this.value.div(other))
    override operator fun rem(other: Byte): Nanometer = Nanometer(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Nanometer = Nanometer(this.value.times(other))
    override operator fun div(other: Short): Nanometer = Nanometer(this.value.div(other))
    override operator fun rem(other: Short): Nanometer = Nanometer(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Nanometer = Nanometer(this.value.times(other))
    override operator fun div(other: Int): Nanometer = Nanometer(this.value.div(other))
    override operator fun rem(other: Int): Nanometer = Nanometer(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Nanometer = Nanometer(this.value.times(other))
    override operator fun div(other: Long): Nanometer = Nanometer(this.value.div(other))
    override operator fun rem(other: Long): Nanometer = Nanometer(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Nanometer = Nanometer(this.value.times(other))
    override operator fun div(other: Float): Nanometer = Nanometer(this.value.div(other))
    override operator fun rem(other: Float): Nanometer = Nanometer(this.value.rem(other))


    override operator fun times(other: Double): Nanometer = Nanometer(this.value.times(other))
    override operator fun div(other: Double): Nanometer = Nanometer(this.value.div(other))
    override operator fun rem(other: Double): Nanometer = Nanometer(this.value.rem(other))


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

val Byte.nanometer
    get() = Nanometer(this)


val Short.nanometer
    get() = Nanometer(this)


val Int.nanometer
    get() = Nanometer(this)


val Long.nanometer
    get() = Nanometer(this)


val Float.nanometer
    get() = Nanometer(this)


val Double.nanometer
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Picometer = Picometer(this.value.times(other))
    override operator fun div(other: Byte): Picometer = Picometer(this.value.div(other))
    override operator fun rem(other: Byte): Picometer = Picometer(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Picometer = Picometer(this.value.times(other))
    override operator fun div(other: Short): Picometer = Picometer(this.value.div(other))
    override operator fun rem(other: Short): Picometer = Picometer(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Picometer = Picometer(this.value.times(other))
    override operator fun div(other: Int): Picometer = Picometer(this.value.div(other))
    override operator fun rem(other: Int): Picometer = Picometer(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Picometer = Picometer(this.value.times(other))
    override operator fun div(other: Long): Picometer = Picometer(this.value.div(other))
    override operator fun rem(other: Long): Picometer = Picometer(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Picometer = Picometer(this.value.times(other))
    override operator fun div(other: Float): Picometer = Picometer(this.value.div(other))
    override operator fun rem(other: Float): Picometer = Picometer(this.value.rem(other))


    override operator fun times(other: Double): Picometer = Picometer(this.value.times(other))
    override operator fun div(other: Double): Picometer = Picometer(this.value.div(other))
    override operator fun rem(other: Double): Picometer = Picometer(this.value.rem(other))


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

val Byte.picometer
    get() = Picometer(this)


val Short.picometer
    get() = Picometer(this)


val Int.picometer
    get() = Picometer(this)


val Long.picometer
    get() = Picometer(this)


val Float.picometer
    get() = Picometer(this)


val Double.picometer
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Mile = Mile(this.value.times(other))
    override operator fun div(other: Byte): Mile = Mile(this.value.div(other))
    override operator fun rem(other: Byte): Mile = Mile(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Mile = Mile(this.value.times(other))
    override operator fun div(other: Short): Mile = Mile(this.value.div(other))
    override operator fun rem(other: Short): Mile = Mile(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Mile = Mile(this.value.times(other))
    override operator fun div(other: Int): Mile = Mile(this.value.div(other))
    override operator fun rem(other: Int): Mile = Mile(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Mile = Mile(this.value.times(other))
    override operator fun div(other: Long): Mile = Mile(this.value.div(other))
    override operator fun rem(other: Long): Mile = Mile(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Mile = Mile(this.value.times(other))
    override operator fun div(other: Float): Mile = Mile(this.value.div(other))
    override operator fun rem(other: Float): Mile = Mile(this.value.rem(other))


    override operator fun times(other: Double): Mile = Mile(this.value.times(other))
    override operator fun div(other: Double): Mile = Mile(this.value.div(other))
    override operator fun rem(other: Double): Mile = Mile(this.value.rem(other))


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

val Byte.mile
    get() = Mile(this)


val Short.mile
    get() = Mile(this)


val Int.mile
    get() = Mile(this)


val Long.mile
    get() = Mile(this)


val Float.mile
    get() = Mile(this)


val Double.mile
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Yard = Yard(this.value.times(other))
    override operator fun div(other: Byte): Yard = Yard(this.value.div(other))
    override operator fun rem(other: Byte): Yard = Yard(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Yard = Yard(this.value.times(other))
    override operator fun div(other: Short): Yard = Yard(this.value.div(other))
    override operator fun rem(other: Short): Yard = Yard(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Yard = Yard(this.value.times(other))
    override operator fun div(other: Int): Yard = Yard(this.value.div(other))
    override operator fun rem(other: Int): Yard = Yard(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Yard = Yard(this.value.times(other))
    override operator fun div(other: Long): Yard = Yard(this.value.div(other))
    override operator fun rem(other: Long): Yard = Yard(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Yard = Yard(this.value.times(other))
    override operator fun div(other: Float): Yard = Yard(this.value.div(other))
    override operator fun rem(other: Float): Yard = Yard(this.value.rem(other))


    override operator fun times(other: Double): Yard = Yard(this.value.times(other))
    override operator fun div(other: Double): Yard = Yard(this.value.div(other))
    override operator fun rem(other: Double): Yard = Yard(this.value.rem(other))


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

val Byte.yard
    get() = Yard(this)


val Short.yard
    get() = Yard(this)


val Int.yard
    get() = Yard(this)


val Long.yard
    get() = Yard(this)


val Float.yard
    get() = Yard(this)


val Double.yard
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Foot = Foot(this.value.times(other))
    override operator fun div(other: Byte): Foot = Foot(this.value.div(other))
    override operator fun rem(other: Byte): Foot = Foot(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Foot = Foot(this.value.times(other))
    override operator fun div(other: Short): Foot = Foot(this.value.div(other))
    override operator fun rem(other: Short): Foot = Foot(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Foot = Foot(this.value.times(other))
    override operator fun div(other: Int): Foot = Foot(this.value.div(other))
    override operator fun rem(other: Int): Foot = Foot(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Foot = Foot(this.value.times(other))
    override operator fun div(other: Long): Foot = Foot(this.value.div(other))
    override operator fun rem(other: Long): Foot = Foot(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Foot = Foot(this.value.times(other))
    override operator fun div(other: Float): Foot = Foot(this.value.div(other))
    override operator fun rem(other: Float): Foot = Foot(this.value.rem(other))


    override operator fun times(other: Double): Foot = Foot(this.value.times(other))
    override operator fun div(other: Double): Foot = Foot(this.value.div(other))
    override operator fun rem(other: Double): Foot = Foot(this.value.rem(other))


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

val Byte.foot
    get() = Foot(this)


val Short.foot
    get() = Foot(this)


val Int.foot
    get() = Foot(this)


val Long.foot
    get() = Foot(this)


val Float.foot
    get() = Foot(this)


val Double.foot
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Inch = Inch(this.value.times(other))
    override operator fun div(other: Byte): Inch = Inch(this.value.div(other))
    override operator fun rem(other: Byte): Inch = Inch(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Inch = Inch(this.value.times(other))
    override operator fun div(other: Short): Inch = Inch(this.value.div(other))
    override operator fun rem(other: Short): Inch = Inch(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Inch = Inch(this.value.times(other))
    override operator fun div(other: Int): Inch = Inch(this.value.div(other))
    override operator fun rem(other: Int): Inch = Inch(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Inch = Inch(this.value.times(other))
    override operator fun div(other: Long): Inch = Inch(this.value.div(other))
    override operator fun rem(other: Long): Inch = Inch(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Inch = Inch(this.value.times(other))
    override operator fun div(other: Float): Inch = Inch(this.value.div(other))
    override operator fun rem(other: Float): Inch = Inch(this.value.rem(other))


    override operator fun times(other: Double): Inch = Inch(this.value.times(other))
    override operator fun div(other: Double): Inch = Inch(this.value.div(other))
    override operator fun rem(other: Double): Inch = Inch(this.value.rem(other))


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

val Byte.inch
    get() = Inch(this)


val Short.inch
    get() = Inch(this)


val Int.inch
    get() = Inch(this)


val Long.inch
    get() = Inch(this)


val Float.inch
    get() = Inch(this)


val Double.inch
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Parsec = Parsec(this.value.times(other))
    override operator fun div(other: Byte): Parsec = Parsec(this.value.div(other))
    override operator fun rem(other: Byte): Parsec = Parsec(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Parsec = Parsec(this.value.times(other))
    override operator fun div(other: Short): Parsec = Parsec(this.value.div(other))
    override operator fun rem(other: Short): Parsec = Parsec(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Parsec = Parsec(this.value.times(other))
    override operator fun div(other: Int): Parsec = Parsec(this.value.div(other))
    override operator fun rem(other: Int): Parsec = Parsec(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Parsec = Parsec(this.value.times(other))
    override operator fun div(other: Long): Parsec = Parsec(this.value.div(other))
    override operator fun rem(other: Long): Parsec = Parsec(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Parsec = Parsec(this.value.times(other))
    override operator fun div(other: Float): Parsec = Parsec(this.value.div(other))
    override operator fun rem(other: Float): Parsec = Parsec(this.value.rem(other))


    override operator fun times(other: Double): Parsec = Parsec(this.value.times(other))
    override operator fun div(other: Double): Parsec = Parsec(this.value.div(other))
    override operator fun rem(other: Double): Parsec = Parsec(this.value.rem(other))


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

val Byte.parsec
    get() = Parsec(this)


val Short.parsec
    get() = Parsec(this)


val Int.parsec
    get() = Parsec(this)


val Long.parsec
    get() = Parsec(this)


val Float.parsec
    get() = Parsec(this)


val Double.parsec
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): LightYear = LightYear(this.value.times(other))
    override operator fun div(other: Byte): LightYear = LightYear(this.value.div(other))
    override operator fun rem(other: Byte): LightYear = LightYear(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): LightYear = LightYear(this.value.times(other))
    override operator fun div(other: Short): LightYear = LightYear(this.value.div(other))
    override operator fun rem(other: Short): LightYear = LightYear(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): LightYear = LightYear(this.value.times(other))
    override operator fun div(other: Int): LightYear = LightYear(this.value.div(other))
    override operator fun rem(other: Int): LightYear = LightYear(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): LightYear = LightYear(this.value.times(other))
    override operator fun div(other: Long): LightYear = LightYear(this.value.div(other))
    override operator fun rem(other: Long): LightYear = LightYear(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): LightYear = LightYear(this.value.times(other))
    override operator fun div(other: Float): LightYear = LightYear(this.value.div(other))
    override operator fun rem(other: Float): LightYear = LightYear(this.value.rem(other))


    override operator fun times(other: Double): LightYear = LightYear(this.value.times(other))
    override operator fun div(other: Double): LightYear = LightYear(this.value.div(other))
    override operator fun rem(other: Double): LightYear = LightYear(this.value.rem(other))


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

val Byte.lightYear
    get() = LightYear(this)


val Short.lightYear
    get() = LightYear(this)


val Int.lightYear
    get() = LightYear(this)


val Long.lightYear
    get() = LightYear(this)


val Float.lightYear
    get() = LightYear(this)


val Double.lightYear
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): AstronomicalUnit = AstronomicalUnit(this.value.times(other))
    override operator fun div(other: Byte): AstronomicalUnit = AstronomicalUnit(this.value.div(other))
    override operator fun rem(other: Byte): AstronomicalUnit = AstronomicalUnit(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): AstronomicalUnit = AstronomicalUnit(this.value.times(other))
    override operator fun div(other: Short): AstronomicalUnit = AstronomicalUnit(this.value.div(other))
    override operator fun rem(other: Short): AstronomicalUnit = AstronomicalUnit(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): AstronomicalUnit = AstronomicalUnit(this.value.times(other))
    override operator fun div(other: Int): AstronomicalUnit = AstronomicalUnit(this.value.div(other))
    override operator fun rem(other: Int): AstronomicalUnit = AstronomicalUnit(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): AstronomicalUnit = AstronomicalUnit(this.value.times(other))
    override operator fun div(other: Long): AstronomicalUnit = AstronomicalUnit(this.value.div(other))
    override operator fun rem(other: Long): AstronomicalUnit = AstronomicalUnit(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): AstronomicalUnit = AstronomicalUnit(this.value.times(other))
    override operator fun div(other: Float): AstronomicalUnit = AstronomicalUnit(this.value.div(other))
    override operator fun rem(other: Float): AstronomicalUnit = AstronomicalUnit(this.value.rem(other))


    override operator fun times(other: Double): AstronomicalUnit = AstronomicalUnit(this.value.times(other))
    override operator fun div(other: Double): AstronomicalUnit = AstronomicalUnit(this.value.div(other))
    override operator fun rem(other: Double): AstronomicalUnit = AstronomicalUnit(this.value.rem(other))


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

val Byte.astronomicalUnit
    get() = AstronomicalUnit(this)


val Short.astronomicalUnit
    get() = AstronomicalUnit(this)


val Int.astronomicalUnit
    get() = AstronomicalUnit(this)


val Long.astronomicalUnit
    get() = AstronomicalUnit(this)


val Float.astronomicalUnit
    get() = AstronomicalUnit(this)


val Double.astronomicalUnit
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Furlong = Furlong(this.value.times(other))
    override operator fun div(other: Byte): Furlong = Furlong(this.value.div(other))
    override operator fun rem(other: Byte): Furlong = Furlong(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Furlong = Furlong(this.value.times(other))
    override operator fun div(other: Short): Furlong = Furlong(this.value.div(other))
    override operator fun rem(other: Short): Furlong = Furlong(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Furlong = Furlong(this.value.times(other))
    override operator fun div(other: Int): Furlong = Furlong(this.value.div(other))
    override operator fun rem(other: Int): Furlong = Furlong(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Furlong = Furlong(this.value.times(other))
    override operator fun div(other: Long): Furlong = Furlong(this.value.div(other))
    override operator fun rem(other: Long): Furlong = Furlong(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Furlong = Furlong(this.value.times(other))
    override operator fun div(other: Float): Furlong = Furlong(this.value.div(other))
    override operator fun rem(other: Float): Furlong = Furlong(this.value.rem(other))


    override operator fun times(other: Double): Furlong = Furlong(this.value.times(other))
    override operator fun div(other: Double): Furlong = Furlong(this.value.div(other))
    override operator fun rem(other: Double): Furlong = Furlong(this.value.rem(other))


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

val Byte.furlong
    get() = Furlong(this)


val Short.furlong
    get() = Furlong(this)


val Int.furlong
    get() = Furlong(this)


val Long.furlong
    get() = Furlong(this)


val Float.furlong
    get() = Furlong(this)


val Double.furlong
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Fathom = Fathom(this.value.times(other))
    override operator fun div(other: Byte): Fathom = Fathom(this.value.div(other))
    override operator fun rem(other: Byte): Fathom = Fathom(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Fathom = Fathom(this.value.times(other))
    override operator fun div(other: Short): Fathom = Fathom(this.value.div(other))
    override operator fun rem(other: Short): Fathom = Fathom(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Fathom = Fathom(this.value.times(other))
    override operator fun div(other: Int): Fathom = Fathom(this.value.div(other))
    override operator fun rem(other: Int): Fathom = Fathom(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Fathom = Fathom(this.value.times(other))
    override operator fun div(other: Long): Fathom = Fathom(this.value.div(other))
    override operator fun rem(other: Long): Fathom = Fathom(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Fathom = Fathom(this.value.times(other))
    override operator fun div(other: Float): Fathom = Fathom(this.value.div(other))
    override operator fun rem(other: Float): Fathom = Fathom(this.value.rem(other))


    override operator fun times(other: Double): Fathom = Fathom(this.value.times(other))
    override operator fun div(other: Double): Fathom = Fathom(this.value.div(other))
    override operator fun rem(other: Double): Fathom = Fathom(this.value.rem(other))


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

val Byte.fathom
    get() = Fathom(this)


val Short.fathom
    get() = Fathom(this)


val Int.fathom
    get() = Fathom(this)


val Long.fathom
    get() = Fathom(this)


val Float.fathom
    get() = Fathom(this)


val Double.fathom
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): NauticalMile = NauticalMile(this.value.times(other))
    override operator fun div(other: Byte): NauticalMile = NauticalMile(this.value.div(other))
    override operator fun rem(other: Byte): NauticalMile = NauticalMile(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): NauticalMile = NauticalMile(this.value.times(other))
    override operator fun div(other: Short): NauticalMile = NauticalMile(this.value.div(other))
    override operator fun rem(other: Short): NauticalMile = NauticalMile(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): NauticalMile = NauticalMile(this.value.times(other))
    override operator fun div(other: Int): NauticalMile = NauticalMile(this.value.div(other))
    override operator fun rem(other: Int): NauticalMile = NauticalMile(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): NauticalMile = NauticalMile(this.value.times(other))
    override operator fun div(other: Long): NauticalMile = NauticalMile(this.value.div(other))
    override operator fun rem(other: Long): NauticalMile = NauticalMile(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): NauticalMile = NauticalMile(this.value.times(other))
    override operator fun div(other: Float): NauticalMile = NauticalMile(this.value.div(other))
    override operator fun rem(other: Float): NauticalMile = NauticalMile(this.value.rem(other))


    override operator fun times(other: Double): NauticalMile = NauticalMile(this.value.times(other))
    override operator fun div(other: Double): NauticalMile = NauticalMile(this.value.div(other))
    override operator fun rem(other: Double): NauticalMile = NauticalMile(this.value.rem(other))


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

val Byte.nauticalMile
    get() = NauticalMile(this)


val Short.nauticalMile
    get() = NauticalMile(this)


val Int.nauticalMile
    get() = NauticalMile(this)


val Long.nauticalMile
    get() = NauticalMile(this)


val Float.nauticalMile
    get() = NauticalMile(this)


val Double.nauticalMile
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): MileScandinavian = MileScandinavian(this.value.times(other))
    override operator fun div(other: Byte): MileScandinavian = MileScandinavian(this.value.div(other))
    override operator fun rem(other: Byte): MileScandinavian = MileScandinavian(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): MileScandinavian = MileScandinavian(this.value.times(other))
    override operator fun div(other: Short): MileScandinavian = MileScandinavian(this.value.div(other))
    override operator fun rem(other: Short): MileScandinavian = MileScandinavian(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): MileScandinavian = MileScandinavian(this.value.times(other))
    override operator fun div(other: Int): MileScandinavian = MileScandinavian(this.value.div(other))
    override operator fun rem(other: Int): MileScandinavian = MileScandinavian(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): MileScandinavian = MileScandinavian(this.value.times(other))
    override operator fun div(other: Long): MileScandinavian = MileScandinavian(this.value.div(other))
    override operator fun rem(other: Long): MileScandinavian = MileScandinavian(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): MileScandinavian = MileScandinavian(this.value.times(other))
    override operator fun div(other: Float): MileScandinavian = MileScandinavian(this.value.div(other))
    override operator fun rem(other: Float): MileScandinavian = MileScandinavian(this.value.rem(other))


    override operator fun times(other: Double): MileScandinavian = MileScandinavian(this.value.times(other))
    override operator fun div(other: Double): MileScandinavian = MileScandinavian(this.value.div(other))
    override operator fun rem(other: Double): MileScandinavian = MileScandinavian(this.value.rem(other))


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

val Byte.mileScandinavian
    get() = MileScandinavian(this)


val Short.mileScandinavian
    get() = MileScandinavian(this)


val Int.mileScandinavian
    get() = MileScandinavian(this)


val Long.mileScandinavian
    get() = MileScandinavian(this)


val Float.mileScandinavian
    get() = MileScandinavian(this)


val Double.mileScandinavian
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Point = Point(this.value.times(other))
    override operator fun div(other: Byte): Point = Point(this.value.div(other))
    override operator fun rem(other: Byte): Point = Point(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Point = Point(this.value.times(other))
    override operator fun div(other: Short): Point = Point(this.value.div(other))
    override operator fun rem(other: Short): Point = Point(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Point = Point(this.value.times(other))
    override operator fun div(other: Int): Point = Point(this.value.div(other))
    override operator fun rem(other: Int): Point = Point(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Point = Point(this.value.times(other))
    override operator fun div(other: Long): Point = Point(this.value.div(other))
    override operator fun rem(other: Long): Point = Point(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Point = Point(this.value.times(other))
    override operator fun div(other: Float): Point = Point(this.value.div(other))
    override operator fun rem(other: Float): Point = Point(this.value.rem(other))


    override operator fun times(other: Double): Point = Point(this.value.times(other))
    override operator fun div(other: Double): Point = Point(this.value.div(other))
    override operator fun rem(other: Double): Point = Point(this.value.rem(other))


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

val Byte.point
    get() = Point(this)


val Short.point
    get() = Point(this)


val Int.point
    get() = Point(this)


val Long.point
    get() = Point(this)


val Float.point
    get() = Point(this)


val Double.point
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): SolarRadius = SolarRadius(this.value.times(other))
    override operator fun div(other: Byte): SolarRadius = SolarRadius(this.value.div(other))
    override operator fun rem(other: Byte): SolarRadius = SolarRadius(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): SolarRadius = SolarRadius(this.value.times(other))
    override operator fun div(other: Short): SolarRadius = SolarRadius(this.value.div(other))
    override operator fun rem(other: Short): SolarRadius = SolarRadius(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): SolarRadius = SolarRadius(this.value.times(other))
    override operator fun div(other: Int): SolarRadius = SolarRadius(this.value.div(other))
    override operator fun rem(other: Int): SolarRadius = SolarRadius(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): SolarRadius = SolarRadius(this.value.times(other))
    override operator fun div(other: Long): SolarRadius = SolarRadius(this.value.div(other))
    override operator fun rem(other: Long): SolarRadius = SolarRadius(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): SolarRadius = SolarRadius(this.value.times(other))
    override operator fun div(other: Float): SolarRadius = SolarRadius(this.value.div(other))
    override operator fun rem(other: Float): SolarRadius = SolarRadius(this.value.rem(other))


    override operator fun times(other: Double): SolarRadius = SolarRadius(this.value.times(other))
    override operator fun div(other: Double): SolarRadius = SolarRadius(this.value.div(other))
    override operator fun rem(other: Double): SolarRadius = SolarRadius(this.value.rem(other))


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

val Byte.solarRadius
    get() = SolarRadius(this)


val Short.solarRadius
    get() = SolarRadius(this)


val Int.solarRadius
    get() = SolarRadius(this)


val Long.solarRadius
    get() = SolarRadius(this)


val Float.solarRadius
    get() = SolarRadius(this)


val Double.solarRadius
    get() = SolarRadius(this)
                            
