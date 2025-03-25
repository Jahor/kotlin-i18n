package info.leonenko.i18n.units.area

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

@Serializable(Area.Serializer::class)
interface Area : MeasurementUnit, Comparable<Area> {
    override val measurement: String get() = "area"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Area> = emptyMap()) : KSerializer<Area> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Area", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Area {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Area unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "square-kilometer" -> SquareKilometer(value)
                "hectare" -> Hectare(value)
                "square-meter" -> SquareMeter(value)
                "square-centimeter" -> SquareCentimeter(value)
                "square-mile" -> SquareMile(value)
                "acre" -> Acre(value)
                "square-yard" -> SquareYard(value)
                "square-foot" -> SquareFoot(value)
                "square-inch" -> SquareInch(value)
                "dunam" -> Dunam(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Area unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Area) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toSquareKilometer(): SquareKilometer {
        return SquareKilometer.fromSquareMeter(toSquareMeter())
    }

    fun toHectare(): Hectare {
        return Hectare.fromSquareMeter(toSquareMeter())
    }

    fun toSquareMeter(): SquareMeter
    override fun getBaseUnitValue() = toSquareMeter().value
    fun toSquareCentimeter(): SquareCentimeter {
        return SquareCentimeter.fromSquareMeter(toSquareMeter())
    }

    fun toSquareMile(): SquareMile {
        return SquareMile.fromSquareMeter(toSquareMeter())
    }

    fun toAcre(): Acre {
        return Acre.fromSquareMeter(toSquareMeter())
    }

    fun toSquareYard(): SquareYard {
        return SquareYard.fromSquareMeter(toSquareMeter())
    }

    fun toSquareFoot(): SquareFoot {
        return SquareFoot.fromSquareMeter(toSquareMeter())
    }

    fun toSquareInch(): SquareInch {
        return SquareInch.fromSquareMeter(toSquareMeter())
    }

    fun toDunam(): Dunam {
        return Dunam.fromSquareMeter(toSquareMeter())
    }

    companion object {
        val ZERO: Area = SquareMeter(0.0)

    }

    override operator fun compareTo(other: Area): Int = this.toSquareMeter().value.compareTo(other.toSquareMeter().value)

    operator fun plus(other: Area): SquareMeter = this.toSquareMeter() + other.toSquareMeter()
    operator fun minus(other: Area): SquareMeter = this.toSquareMeter() - other.toSquareMeter()
    operator fun div(other: Area): Double = this.toSquareMeter().value / other.toSquareMeter().value
    operator fun unaryMinus(): Area
    operator fun unaryPlus(): Area
    operator fun inc(): Area
    operator fun dec(): Area

    fun round(): Area
    fun truncate(): Area
    fun floor(): Area
    fun ceil(): Area
    fun abs(): Area


    operator fun times(other: Byte): Area
    operator fun div(other: Byte): Area
    operator fun rem(other: Byte): Area


    operator fun times(other: Short): Area
    operator fun div(other: Short): Area
    operator fun rem(other: Short): Area


    operator fun times(other: Int): Area
    operator fun div(other: Int): Area
    operator fun rem(other: Int): Area


    operator fun times(other: Long): Area
    operator fun div(other: Long): Area
    operator fun rem(other: Long): Area


    operator fun times(other: Float): Area
    operator fun div(other: Float): Area
    operator fun rem(other: Float): Area


    operator fun times(other: Double): Area
    operator fun div(other: Double): Area
    operator fun rem(other: Double): Area

}

data class SquareKilometer(override val value: MeasurementUnitValue) : Area {

    override val unitName: String = "square-kilometer"

    override fun formatters(language: MeasurementUnitLanguage) = language.areaSquareKilometer
    override fun toSquareMeter() = SquareMeter(value * ((1 * (1e3)) * (1 * (1e3))) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = SquareKilometer(0.0)
        fun fromSquareMeter(value: SquareMeter) = SquareKilometer((value.value - 0) / ((1 * (1e3)) * (1 * (1e3))))
    }

    operator fun plus(other: SquareKilometer): SquareKilometer = SquareKilometer(this.value + other.value)
    operator fun minus(other: SquareKilometer): SquareKilometer = SquareKilometer(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): SquareKilometer = SquareKilometer(this.value.times(other))
    override operator fun div(other: Byte): SquareKilometer = SquareKilometer(this.value.div(other))
    override operator fun rem(other: Byte): SquareKilometer = SquareKilometer(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): SquareKilometer = SquareKilometer(this.value.times(other))
    override operator fun div(other: Short): SquareKilometer = SquareKilometer(this.value.div(other))
    override operator fun rem(other: Short): SquareKilometer = SquareKilometer(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): SquareKilometer = SquareKilometer(this.value.times(other))
    override operator fun div(other: Int): SquareKilometer = SquareKilometer(this.value.div(other))
    override operator fun rem(other: Int): SquareKilometer = SquareKilometer(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): SquareKilometer = SquareKilometer(this.value.times(other))
    override operator fun div(other: Long): SquareKilometer = SquareKilometer(this.value.div(other))
    override operator fun rem(other: Long): SquareKilometer = SquareKilometer(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): SquareKilometer = SquareKilometer(this.value.times(other))
    override operator fun div(other: Float): SquareKilometer = SquareKilometer(this.value.div(other))
    override operator fun rem(other: Float): SquareKilometer = SquareKilometer(this.value.rem(other))


    override operator fun times(other: Double): SquareKilometer = SquareKilometer(this.value.times(other))
    override operator fun div(other: Double): SquareKilometer = SquareKilometer(this.value.div(other))
    override operator fun rem(other: Double): SquareKilometer = SquareKilometer(this.value.rem(other))


    override operator fun unaryMinus(): SquareKilometer = SquareKilometer(-value)
    override operator fun unaryPlus(): SquareKilometer = this
    override operator fun inc(): SquareKilometer = SquareKilometer(this.value + 1)
    override operator fun dec(): SquareKilometer = SquareKilometer(this.value - 1)

    override fun round(): SquareKilometer = SquareKilometer(round(this.value))
    override fun truncate(): SquareKilometer = SquareKilometer(truncate(this.value))
    override fun floor(): SquareKilometer = SquareKilometer(floor(this.value))
    override fun ceil(): SquareKilometer = SquareKilometer(ceil(this.value))
    override fun abs(): SquareKilometer = SquareKilometer(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is SquareKilometer -> this.value == other.value
        is Area -> this.toSquareMeter().value == other.toSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toSquareMeter().value.hashCode()


}

val Byte.squareKilometer
    get() = SquareKilometer(this)


val Short.squareKilometer
    get() = SquareKilometer(this)


val Int.squareKilometer
    get() = SquareKilometer(this)


val Long.squareKilometer
    get() = SquareKilometer(this)


val Float.squareKilometer
    get() = SquareKilometer(this)


val Double.squareKilometer
    get() = SquareKilometer(this)


data class Hectare(override val value: MeasurementUnitValue) : Area {

    override val unitName: String = "hectare"

    override fun formatters(language: MeasurementUnitLanguage) = language.areaHectare
    override fun toSquareMeter() = SquareMeter(value * (10000) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Hectare(0.0)
        fun fromSquareMeter(value: SquareMeter) = Hectare((value.value - 0) / (10000))
    }

    operator fun plus(other: Hectare): Hectare = Hectare(this.value + other.value)
    operator fun minus(other: Hectare): Hectare = Hectare(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Hectare = Hectare(this.value.times(other))
    override operator fun div(other: Byte): Hectare = Hectare(this.value.div(other))
    override operator fun rem(other: Byte): Hectare = Hectare(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Hectare = Hectare(this.value.times(other))
    override operator fun div(other: Short): Hectare = Hectare(this.value.div(other))
    override operator fun rem(other: Short): Hectare = Hectare(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Hectare = Hectare(this.value.times(other))
    override operator fun div(other: Int): Hectare = Hectare(this.value.div(other))
    override operator fun rem(other: Int): Hectare = Hectare(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Hectare = Hectare(this.value.times(other))
    override operator fun div(other: Long): Hectare = Hectare(this.value.div(other))
    override operator fun rem(other: Long): Hectare = Hectare(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Hectare = Hectare(this.value.times(other))
    override operator fun div(other: Float): Hectare = Hectare(this.value.div(other))
    override operator fun rem(other: Float): Hectare = Hectare(this.value.rem(other))


    override operator fun times(other: Double): Hectare = Hectare(this.value.times(other))
    override operator fun div(other: Double): Hectare = Hectare(this.value.div(other))
    override operator fun rem(other: Double): Hectare = Hectare(this.value.rem(other))


    override operator fun unaryMinus(): Hectare = Hectare(-value)
    override operator fun unaryPlus(): Hectare = this
    override operator fun inc(): Hectare = Hectare(this.value + 1)
    override operator fun dec(): Hectare = Hectare(this.value - 1)

    override fun round(): Hectare = Hectare(round(this.value))
    override fun truncate(): Hectare = Hectare(truncate(this.value))
    override fun floor(): Hectare = Hectare(floor(this.value))
    override fun ceil(): Hectare = Hectare(ceil(this.value))
    override fun abs(): Hectare = Hectare(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Hectare -> this.value == other.value
        is Area -> this.toSquareMeter().value == other.toSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toSquareMeter().value.hashCode()


}

val Byte.hectare
    get() = Hectare(this)


val Short.hectare
    get() = Hectare(this)


val Int.hectare
    get() = Hectare(this)


val Long.hectare
    get() = Hectare(this)


val Float.hectare
    get() = Hectare(this)


val Double.hectare
    get() = Hectare(this)


data class SquareMeter(override val value: MeasurementUnitValue) : Area {

    override val unitName: String = "square-meter"

    override fun formatters(language: MeasurementUnitLanguage) = language.areaSquareMeter
    override fun toSquareMeter() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = SquareMeter(0.0)
        fun fromSquareMeter(value: SquareMeter) = value
    }

    operator fun plus(other: SquareMeter): SquareMeter = SquareMeter(this.value + other.value)
    operator fun minus(other: SquareMeter): SquareMeter = SquareMeter(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): SquareMeter = SquareMeter(this.value.times(other))
    override operator fun div(other: Byte): SquareMeter = SquareMeter(this.value.div(other))
    override operator fun rem(other: Byte): SquareMeter = SquareMeter(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): SquareMeter = SquareMeter(this.value.times(other))
    override operator fun div(other: Short): SquareMeter = SquareMeter(this.value.div(other))
    override operator fun rem(other: Short): SquareMeter = SquareMeter(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): SquareMeter = SquareMeter(this.value.times(other))
    override operator fun div(other: Int): SquareMeter = SquareMeter(this.value.div(other))
    override operator fun rem(other: Int): SquareMeter = SquareMeter(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): SquareMeter = SquareMeter(this.value.times(other))
    override operator fun div(other: Long): SquareMeter = SquareMeter(this.value.div(other))
    override operator fun rem(other: Long): SquareMeter = SquareMeter(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): SquareMeter = SquareMeter(this.value.times(other))
    override operator fun div(other: Float): SquareMeter = SquareMeter(this.value.div(other))
    override operator fun rem(other: Float): SquareMeter = SquareMeter(this.value.rem(other))


    override operator fun times(other: Double): SquareMeter = SquareMeter(this.value.times(other))
    override operator fun div(other: Double): SquareMeter = SquareMeter(this.value.div(other))
    override operator fun rem(other: Double): SquareMeter = SquareMeter(this.value.rem(other))


    override operator fun unaryMinus(): SquareMeter = SquareMeter(-value)
    override operator fun unaryPlus(): SquareMeter = this
    override operator fun inc(): SquareMeter = SquareMeter(this.value + 1)
    override operator fun dec(): SquareMeter = SquareMeter(this.value - 1)

    override fun round(): SquareMeter = SquareMeter(round(this.value))
    override fun truncate(): SquareMeter = SquareMeter(truncate(this.value))
    override fun floor(): SquareMeter = SquareMeter(floor(this.value))
    override fun ceil(): SquareMeter = SquareMeter(ceil(this.value))
    override fun abs(): SquareMeter = SquareMeter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is SquareMeter -> this.value == other.value
        is Area -> this.toSquareMeter().value == other.toSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toSquareMeter().value.hashCode()


}

val Byte.squareMeter
    get() = SquareMeter(this)


val Short.squareMeter
    get() = SquareMeter(this)


val Int.squareMeter
    get() = SquareMeter(this)


val Long.squareMeter
    get() = SquareMeter(this)


val Float.squareMeter
    get() = SquareMeter(this)


val Double.squareMeter
    get() = SquareMeter(this)


data class SquareCentimeter(override val value: MeasurementUnitValue) : Area {

    override val unitName: String = "square-centimeter"

    override fun formatters(language: MeasurementUnitLanguage) = language.areaSquareCentimeter
    override fun toSquareMeter() = SquareMeter(value * ((1 * (1e-2)) * (1 * (1e-2))) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = SquareCentimeter(0.0)
        fun fromSquareMeter(value: SquareMeter) = SquareCentimeter((value.value - 0) / ((1 * (1e-2)) * (1 * (1e-2))))
    }

    operator fun plus(other: SquareCentimeter): SquareCentimeter = SquareCentimeter(this.value + other.value)
    operator fun minus(other: SquareCentimeter): SquareCentimeter = SquareCentimeter(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): SquareCentimeter = SquareCentimeter(this.value.times(other))
    override operator fun div(other: Byte): SquareCentimeter = SquareCentimeter(this.value.div(other))
    override operator fun rem(other: Byte): SquareCentimeter = SquareCentimeter(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): SquareCentimeter = SquareCentimeter(this.value.times(other))
    override operator fun div(other: Short): SquareCentimeter = SquareCentimeter(this.value.div(other))
    override operator fun rem(other: Short): SquareCentimeter = SquareCentimeter(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): SquareCentimeter = SquareCentimeter(this.value.times(other))
    override operator fun div(other: Int): SquareCentimeter = SquareCentimeter(this.value.div(other))
    override operator fun rem(other: Int): SquareCentimeter = SquareCentimeter(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): SquareCentimeter = SquareCentimeter(this.value.times(other))
    override operator fun div(other: Long): SquareCentimeter = SquareCentimeter(this.value.div(other))
    override operator fun rem(other: Long): SquareCentimeter = SquareCentimeter(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): SquareCentimeter = SquareCentimeter(this.value.times(other))
    override operator fun div(other: Float): SquareCentimeter = SquareCentimeter(this.value.div(other))
    override operator fun rem(other: Float): SquareCentimeter = SquareCentimeter(this.value.rem(other))


    override operator fun times(other: Double): SquareCentimeter = SquareCentimeter(this.value.times(other))
    override operator fun div(other: Double): SquareCentimeter = SquareCentimeter(this.value.div(other))
    override operator fun rem(other: Double): SquareCentimeter = SquareCentimeter(this.value.rem(other))


    override operator fun unaryMinus(): SquareCentimeter = SquareCentimeter(-value)
    override operator fun unaryPlus(): SquareCentimeter = this
    override operator fun inc(): SquareCentimeter = SquareCentimeter(this.value + 1)
    override operator fun dec(): SquareCentimeter = SquareCentimeter(this.value - 1)

    override fun round(): SquareCentimeter = SquareCentimeter(round(this.value))
    override fun truncate(): SquareCentimeter = SquareCentimeter(truncate(this.value))
    override fun floor(): SquareCentimeter = SquareCentimeter(floor(this.value))
    override fun ceil(): SquareCentimeter = SquareCentimeter(ceil(this.value))
    override fun abs(): SquareCentimeter = SquareCentimeter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is SquareCentimeter -> this.value == other.value
        is Area -> this.toSquareMeter().value == other.toSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toSquareMeter().value.hashCode()


}

val Byte.squareCentimeter
    get() = SquareCentimeter(this)


val Short.squareCentimeter
    get() = SquareCentimeter(this)


val Int.squareCentimeter
    get() = SquareCentimeter(this)


val Long.squareCentimeter
    get() = SquareCentimeter(this)


val Float.squareCentimeter
    get() = SquareCentimeter(this)


val Double.squareCentimeter
    get() = SquareCentimeter(this)


data class SquareMile(override val value: MeasurementUnitValue) : Area {

    override val unitName: String = "square-mile"

    override fun formatters(language: MeasurementUnitLanguage) = language.areaSquareMile
    override fun toSquareMeter() = SquareMeter(value * ((1609.344) * (1609.344)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = SquareMile(0.0)
        fun fromSquareMeter(value: SquareMeter) = SquareMile((value.value - 0) / ((1609.344) * (1609.344)))
    }

    operator fun plus(other: SquareMile): SquareMile = SquareMile(this.value + other.value)
    operator fun minus(other: SquareMile): SquareMile = SquareMile(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): SquareMile = SquareMile(this.value.times(other))
    override operator fun div(other: Byte): SquareMile = SquareMile(this.value.div(other))
    override operator fun rem(other: Byte): SquareMile = SquareMile(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): SquareMile = SquareMile(this.value.times(other))
    override operator fun div(other: Short): SquareMile = SquareMile(this.value.div(other))
    override operator fun rem(other: Short): SquareMile = SquareMile(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): SquareMile = SquareMile(this.value.times(other))
    override operator fun div(other: Int): SquareMile = SquareMile(this.value.div(other))
    override operator fun rem(other: Int): SquareMile = SquareMile(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): SquareMile = SquareMile(this.value.times(other))
    override operator fun div(other: Long): SquareMile = SquareMile(this.value.div(other))
    override operator fun rem(other: Long): SquareMile = SquareMile(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): SquareMile = SquareMile(this.value.times(other))
    override operator fun div(other: Float): SquareMile = SquareMile(this.value.div(other))
    override operator fun rem(other: Float): SquareMile = SquareMile(this.value.rem(other))


    override operator fun times(other: Double): SquareMile = SquareMile(this.value.times(other))
    override operator fun div(other: Double): SquareMile = SquareMile(this.value.div(other))
    override operator fun rem(other: Double): SquareMile = SquareMile(this.value.rem(other))


    override operator fun unaryMinus(): SquareMile = SquareMile(-value)
    override operator fun unaryPlus(): SquareMile = this
    override operator fun inc(): SquareMile = SquareMile(this.value + 1)
    override operator fun dec(): SquareMile = SquareMile(this.value - 1)

    override fun round(): SquareMile = SquareMile(round(this.value))
    override fun truncate(): SquareMile = SquareMile(truncate(this.value))
    override fun floor(): SquareMile = SquareMile(floor(this.value))
    override fun ceil(): SquareMile = SquareMile(ceil(this.value))
    override fun abs(): SquareMile = SquareMile(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is SquareMile -> this.value == other.value
        is Area -> this.toSquareMeter().value == other.toSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toSquareMeter().value.hashCode()


}

val Byte.squareMile
    get() = SquareMile(this)


val Short.squareMile
    get() = SquareMile(this)


val Int.squareMile
    get() = SquareMile(this)


val Long.squareMile
    get() = SquareMile(this)


val Float.squareMile
    get() = SquareMile(this)


val Double.squareMile
    get() = SquareMile(this)


data class Acre(override val value: MeasurementUnitValue) : Area {

    override val unitName: String = "acre"

    override fun formatters(language: MeasurementUnitLanguage) = language.areaAcre
    override fun toSquareMeter() = SquareMeter(value * (4046.8564224) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Acre(0.0)
        fun fromSquareMeter(value: SquareMeter) = Acre((value.value - 0) / (4046.8564224))
    }

    operator fun plus(other: Acre): Acre = Acre(this.value + other.value)
    operator fun minus(other: Acre): Acre = Acre(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Acre = Acre(this.value.times(other))
    override operator fun div(other: Byte): Acre = Acre(this.value.div(other))
    override operator fun rem(other: Byte): Acre = Acre(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Acre = Acre(this.value.times(other))
    override operator fun div(other: Short): Acre = Acre(this.value.div(other))
    override operator fun rem(other: Short): Acre = Acre(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Acre = Acre(this.value.times(other))
    override operator fun div(other: Int): Acre = Acre(this.value.div(other))
    override operator fun rem(other: Int): Acre = Acre(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Acre = Acre(this.value.times(other))
    override operator fun div(other: Long): Acre = Acre(this.value.div(other))
    override operator fun rem(other: Long): Acre = Acre(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Acre = Acre(this.value.times(other))
    override operator fun div(other: Float): Acre = Acre(this.value.div(other))
    override operator fun rem(other: Float): Acre = Acre(this.value.rem(other))


    override operator fun times(other: Double): Acre = Acre(this.value.times(other))
    override operator fun div(other: Double): Acre = Acre(this.value.div(other))
    override operator fun rem(other: Double): Acre = Acre(this.value.rem(other))


    override operator fun unaryMinus(): Acre = Acre(-value)
    override operator fun unaryPlus(): Acre = this
    override operator fun inc(): Acre = Acre(this.value + 1)
    override operator fun dec(): Acre = Acre(this.value - 1)

    override fun round(): Acre = Acre(round(this.value))
    override fun truncate(): Acre = Acre(truncate(this.value))
    override fun floor(): Acre = Acre(floor(this.value))
    override fun ceil(): Acre = Acre(ceil(this.value))
    override fun abs(): Acre = Acre(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Acre -> this.value == other.value
        is Area -> this.toSquareMeter().value == other.toSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toSquareMeter().value.hashCode()


}

val Byte.acre
    get() = Acre(this)


val Short.acre
    get() = Acre(this)


val Int.acre
    get() = Acre(this)


val Long.acre
    get() = Acre(this)


val Float.acre
    get() = Acre(this)


val Double.acre
    get() = Acre(this)


data class SquareYard(override val value: MeasurementUnitValue) : Area {

    override val unitName: String = "square-yard"

    override fun formatters(language: MeasurementUnitLanguage) = language.areaSquareYard
    override fun toSquareMeter() = SquareMeter(value * ((0.9144000000000001) * (0.9144000000000001)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = SquareYard(0.0)
        fun fromSquareMeter(value: SquareMeter) = SquareYard((value.value - 0) / ((0.9144000000000001) * (0.9144000000000001)))
    }

    operator fun plus(other: SquareYard): SquareYard = SquareYard(this.value + other.value)
    operator fun minus(other: SquareYard): SquareYard = SquareYard(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): SquareYard = SquareYard(this.value.times(other))
    override operator fun div(other: Byte): SquareYard = SquareYard(this.value.div(other))
    override operator fun rem(other: Byte): SquareYard = SquareYard(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): SquareYard = SquareYard(this.value.times(other))
    override operator fun div(other: Short): SquareYard = SquareYard(this.value.div(other))
    override operator fun rem(other: Short): SquareYard = SquareYard(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): SquareYard = SquareYard(this.value.times(other))
    override operator fun div(other: Int): SquareYard = SquareYard(this.value.div(other))
    override operator fun rem(other: Int): SquareYard = SquareYard(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): SquareYard = SquareYard(this.value.times(other))
    override operator fun div(other: Long): SquareYard = SquareYard(this.value.div(other))
    override operator fun rem(other: Long): SquareYard = SquareYard(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): SquareYard = SquareYard(this.value.times(other))
    override operator fun div(other: Float): SquareYard = SquareYard(this.value.div(other))
    override operator fun rem(other: Float): SquareYard = SquareYard(this.value.rem(other))


    override operator fun times(other: Double): SquareYard = SquareYard(this.value.times(other))
    override operator fun div(other: Double): SquareYard = SquareYard(this.value.div(other))
    override operator fun rem(other: Double): SquareYard = SquareYard(this.value.rem(other))


    override operator fun unaryMinus(): SquareYard = SquareYard(-value)
    override operator fun unaryPlus(): SquareYard = this
    override operator fun inc(): SquareYard = SquareYard(this.value + 1)
    override operator fun dec(): SquareYard = SquareYard(this.value - 1)

    override fun round(): SquareYard = SquareYard(round(this.value))
    override fun truncate(): SquareYard = SquareYard(truncate(this.value))
    override fun floor(): SquareYard = SquareYard(floor(this.value))
    override fun ceil(): SquareYard = SquareYard(ceil(this.value))
    override fun abs(): SquareYard = SquareYard(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is SquareYard -> this.value == other.value
        is Area -> this.toSquareMeter().value == other.toSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toSquareMeter().value.hashCode()


}

val Byte.squareYard
    get() = SquareYard(this)


val Short.squareYard
    get() = SquareYard(this)


val Int.squareYard
    get() = SquareYard(this)


val Long.squareYard
    get() = SquareYard(this)


val Float.squareYard
    get() = SquareYard(this)


val Double.squareYard
    get() = SquareYard(this)


data class SquareFoot(override val value: MeasurementUnitValue) : Area {

    override val unitName: String = "square-foot"

    override fun formatters(language: MeasurementUnitLanguage) = language.areaSquareFoot
    override fun toSquareMeter() = SquareMeter(value * ((0.3048) * (0.3048)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = SquareFoot(0.0)
        fun fromSquareMeter(value: SquareMeter) = SquareFoot((value.value - 0) / ((0.3048) * (0.3048)))
    }

    operator fun plus(other: SquareFoot): SquareFoot = SquareFoot(this.value + other.value)
    operator fun minus(other: SquareFoot): SquareFoot = SquareFoot(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): SquareFoot = SquareFoot(this.value.times(other))
    override operator fun div(other: Byte): SquareFoot = SquareFoot(this.value.div(other))
    override operator fun rem(other: Byte): SquareFoot = SquareFoot(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): SquareFoot = SquareFoot(this.value.times(other))
    override operator fun div(other: Short): SquareFoot = SquareFoot(this.value.div(other))
    override operator fun rem(other: Short): SquareFoot = SquareFoot(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): SquareFoot = SquareFoot(this.value.times(other))
    override operator fun div(other: Int): SquareFoot = SquareFoot(this.value.div(other))
    override operator fun rem(other: Int): SquareFoot = SquareFoot(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): SquareFoot = SquareFoot(this.value.times(other))
    override operator fun div(other: Long): SquareFoot = SquareFoot(this.value.div(other))
    override operator fun rem(other: Long): SquareFoot = SquareFoot(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): SquareFoot = SquareFoot(this.value.times(other))
    override operator fun div(other: Float): SquareFoot = SquareFoot(this.value.div(other))
    override operator fun rem(other: Float): SquareFoot = SquareFoot(this.value.rem(other))


    override operator fun times(other: Double): SquareFoot = SquareFoot(this.value.times(other))
    override operator fun div(other: Double): SquareFoot = SquareFoot(this.value.div(other))
    override operator fun rem(other: Double): SquareFoot = SquareFoot(this.value.rem(other))


    override operator fun unaryMinus(): SquareFoot = SquareFoot(-value)
    override operator fun unaryPlus(): SquareFoot = this
    override operator fun inc(): SquareFoot = SquareFoot(this.value + 1)
    override operator fun dec(): SquareFoot = SquareFoot(this.value - 1)

    override fun round(): SquareFoot = SquareFoot(round(this.value))
    override fun truncate(): SquareFoot = SquareFoot(truncate(this.value))
    override fun floor(): SquareFoot = SquareFoot(floor(this.value))
    override fun ceil(): SquareFoot = SquareFoot(ceil(this.value))
    override fun abs(): SquareFoot = SquareFoot(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is SquareFoot -> this.value == other.value
        is Area -> this.toSquareMeter().value == other.toSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toSquareMeter().value.hashCode()


}

val Byte.squareFoot
    get() = SquareFoot(this)


val Short.squareFoot
    get() = SquareFoot(this)


val Int.squareFoot
    get() = SquareFoot(this)


val Long.squareFoot
    get() = SquareFoot(this)


val Float.squareFoot
    get() = SquareFoot(this)


val Double.squareFoot
    get() = SquareFoot(this)


data class SquareInch(override val value: MeasurementUnitValue) : Area {

    override val unitName: String = "square-inch"

    override fun formatters(language: MeasurementUnitLanguage) = language.areaSquareInch
    override fun toSquareMeter() = SquareMeter(value * ((0.025400000000000002) * (0.025400000000000002)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = SquareInch(0.0)
        fun fromSquareMeter(value: SquareMeter) = SquareInch((value.value - 0) / ((0.025400000000000002) * (0.025400000000000002)))
    }

    operator fun plus(other: SquareInch): SquareInch = SquareInch(this.value + other.value)
    operator fun minus(other: SquareInch): SquareInch = SquareInch(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): SquareInch = SquareInch(this.value.times(other))
    override operator fun div(other: Byte): SquareInch = SquareInch(this.value.div(other))
    override operator fun rem(other: Byte): SquareInch = SquareInch(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): SquareInch = SquareInch(this.value.times(other))
    override operator fun div(other: Short): SquareInch = SquareInch(this.value.div(other))
    override operator fun rem(other: Short): SquareInch = SquareInch(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): SquareInch = SquareInch(this.value.times(other))
    override operator fun div(other: Int): SquareInch = SquareInch(this.value.div(other))
    override operator fun rem(other: Int): SquareInch = SquareInch(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): SquareInch = SquareInch(this.value.times(other))
    override operator fun div(other: Long): SquareInch = SquareInch(this.value.div(other))
    override operator fun rem(other: Long): SquareInch = SquareInch(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): SquareInch = SquareInch(this.value.times(other))
    override operator fun div(other: Float): SquareInch = SquareInch(this.value.div(other))
    override operator fun rem(other: Float): SquareInch = SquareInch(this.value.rem(other))


    override operator fun times(other: Double): SquareInch = SquareInch(this.value.times(other))
    override operator fun div(other: Double): SquareInch = SquareInch(this.value.div(other))
    override operator fun rem(other: Double): SquareInch = SquareInch(this.value.rem(other))


    override operator fun unaryMinus(): SquareInch = SquareInch(-value)
    override operator fun unaryPlus(): SquareInch = this
    override operator fun inc(): SquareInch = SquareInch(this.value + 1)
    override operator fun dec(): SquareInch = SquareInch(this.value - 1)

    override fun round(): SquareInch = SquareInch(round(this.value))
    override fun truncate(): SquareInch = SquareInch(truncate(this.value))
    override fun floor(): SquareInch = SquareInch(floor(this.value))
    override fun ceil(): SquareInch = SquareInch(ceil(this.value))
    override fun abs(): SquareInch = SquareInch(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is SquareInch -> this.value == other.value
        is Area -> this.toSquareMeter().value == other.toSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toSquareMeter().value.hashCode()


}

val Byte.squareInch
    get() = SquareInch(this)


val Short.squareInch
    get() = SquareInch(this)


val Int.squareInch
    get() = SquareInch(this)


val Long.squareInch
    get() = SquareInch(this)


val Float.squareInch
    get() = SquareInch(this)


val Double.squareInch
    get() = SquareInch(this)


data class Dunam(override val value: MeasurementUnitValue) : Area {

    override val unitName: String = "dunam"

    override fun formatters(language: MeasurementUnitLanguage) = language.areaDunam
    override fun toSquareMeter() = SquareMeter(value * (1000) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Dunam(0.0)
        fun fromSquareMeter(value: SquareMeter) = Dunam((value.value - 0) / (1000))
    }

    operator fun plus(other: Dunam): Dunam = Dunam(this.value + other.value)
    operator fun minus(other: Dunam): Dunam = Dunam(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Dunam = Dunam(this.value.times(other))
    override operator fun div(other: Byte): Dunam = Dunam(this.value.div(other))
    override operator fun rem(other: Byte): Dunam = Dunam(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Dunam = Dunam(this.value.times(other))
    override operator fun div(other: Short): Dunam = Dunam(this.value.div(other))
    override operator fun rem(other: Short): Dunam = Dunam(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Dunam = Dunam(this.value.times(other))
    override operator fun div(other: Int): Dunam = Dunam(this.value.div(other))
    override operator fun rem(other: Int): Dunam = Dunam(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Dunam = Dunam(this.value.times(other))
    override operator fun div(other: Long): Dunam = Dunam(this.value.div(other))
    override operator fun rem(other: Long): Dunam = Dunam(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Dunam = Dunam(this.value.times(other))
    override operator fun div(other: Float): Dunam = Dunam(this.value.div(other))
    override operator fun rem(other: Float): Dunam = Dunam(this.value.rem(other))


    override operator fun times(other: Double): Dunam = Dunam(this.value.times(other))
    override operator fun div(other: Double): Dunam = Dunam(this.value.div(other))
    override operator fun rem(other: Double): Dunam = Dunam(this.value.rem(other))


    override operator fun unaryMinus(): Dunam = Dunam(-value)
    override operator fun unaryPlus(): Dunam = this
    override operator fun inc(): Dunam = Dunam(this.value + 1)
    override operator fun dec(): Dunam = Dunam(this.value - 1)

    override fun round(): Dunam = Dunam(round(this.value))
    override fun truncate(): Dunam = Dunam(truncate(this.value))
    override fun floor(): Dunam = Dunam(floor(this.value))
    override fun ceil(): Dunam = Dunam(ceil(this.value))
    override fun abs(): Dunam = Dunam(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Dunam -> this.value == other.value
        is Area -> this.toSquareMeter().value == other.toSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toSquareMeter().value.hashCode()


}

val Byte.dunam
    get() = Dunam(this)


val Short.dunam
    get() = Dunam(this)


val Int.dunam
    get() = Dunam(this)


val Long.dunam
    get() = Dunam(this)


val Float.dunam
    get() = Dunam(this)


val Double.dunam
    get() = Dunam(this)
                            
