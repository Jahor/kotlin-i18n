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


    operator fun times(other: kotlin.Byte): Area
    operator fun div(other: kotlin.Byte): Area
    operator fun rem(other: kotlin.Byte): Area


    operator fun times(other: kotlin.Short): Area
    operator fun div(other: kotlin.Short): Area
    operator fun rem(other: kotlin.Short): Area


    operator fun times(other: kotlin.Int): Area
    operator fun div(other: kotlin.Int): Area
    operator fun rem(other: kotlin.Int): Area


    operator fun times(other: kotlin.Long): Area
    operator fun div(other: kotlin.Long): Area
    operator fun rem(other: kotlin.Long): Area


    operator fun times(other: kotlin.Float): Area
    operator fun div(other: kotlin.Float): Area
    operator fun rem(other: kotlin.Float): Area


    operator fun times(other: kotlin.Double): Area
    operator fun div(other: kotlin.Double): Area
    operator fun rem(other: kotlin.Double): Area

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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): SquareKilometer = SquareKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Byte): SquareKilometer = SquareKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): SquareKilometer = SquareKilometer(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): SquareKilometer = SquareKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Short): SquareKilometer = SquareKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Short): SquareKilometer = SquareKilometer(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): SquareKilometer = SquareKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Int): SquareKilometer = SquareKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Int): SquareKilometer = SquareKilometer(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): SquareKilometer = SquareKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Long): SquareKilometer = SquareKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Long): SquareKilometer = SquareKilometer(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): SquareKilometer = SquareKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Float): SquareKilometer = SquareKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Float): SquareKilometer = SquareKilometer(this.value.rem(other))


    override operator fun times(other: kotlin.Double): SquareKilometer = SquareKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Double): SquareKilometer = SquareKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Double): SquareKilometer = SquareKilometer(this.value.rem(other))


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

val kotlin.Byte.squareKilometer
    get() = SquareKilometer(this)


val kotlin.Short.squareKilometer
    get() = SquareKilometer(this)


val kotlin.Int.squareKilometer
    get() = SquareKilometer(this)


val kotlin.Long.squareKilometer
    get() = SquareKilometer(this)


val kotlin.Float.squareKilometer
    get() = SquareKilometer(this)


val kotlin.Double.squareKilometer
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Hectare = Hectare(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Hectare = Hectare(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Hectare = Hectare(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Hectare = Hectare(this.value.times(other))
    override operator fun div(other: kotlin.Short): Hectare = Hectare(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Hectare = Hectare(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Hectare = Hectare(this.value.times(other))
    override operator fun div(other: kotlin.Int): Hectare = Hectare(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Hectare = Hectare(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Hectare = Hectare(this.value.times(other))
    override operator fun div(other: kotlin.Long): Hectare = Hectare(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Hectare = Hectare(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Hectare = Hectare(this.value.times(other))
    override operator fun div(other: kotlin.Float): Hectare = Hectare(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Hectare = Hectare(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Hectare = Hectare(this.value.times(other))
    override operator fun div(other: kotlin.Double): Hectare = Hectare(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Hectare = Hectare(this.value.rem(other))


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

val kotlin.Byte.hectare
    get() = Hectare(this)


val kotlin.Short.hectare
    get() = Hectare(this)


val kotlin.Int.hectare
    get() = Hectare(this)


val kotlin.Long.hectare
    get() = Hectare(this)


val kotlin.Float.hectare
    get() = Hectare(this)


val kotlin.Double.hectare
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): SquareMeter = SquareMeter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): SquareMeter = SquareMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): SquareMeter = SquareMeter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): SquareMeter = SquareMeter(this.value.times(other))
    override operator fun div(other: kotlin.Short): SquareMeter = SquareMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): SquareMeter = SquareMeter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): SquareMeter = SquareMeter(this.value.times(other))
    override operator fun div(other: kotlin.Int): SquareMeter = SquareMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): SquareMeter = SquareMeter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): SquareMeter = SquareMeter(this.value.times(other))
    override operator fun div(other: kotlin.Long): SquareMeter = SquareMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): SquareMeter = SquareMeter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): SquareMeter = SquareMeter(this.value.times(other))
    override operator fun div(other: kotlin.Float): SquareMeter = SquareMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): SquareMeter = SquareMeter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): SquareMeter = SquareMeter(this.value.times(other))
    override operator fun div(other: kotlin.Double): SquareMeter = SquareMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): SquareMeter = SquareMeter(this.value.rem(other))


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

val kotlin.Byte.squareMeter
    get() = SquareMeter(this)


val kotlin.Short.squareMeter
    get() = SquareMeter(this)


val kotlin.Int.squareMeter
    get() = SquareMeter(this)


val kotlin.Long.squareMeter
    get() = SquareMeter(this)


val kotlin.Float.squareMeter
    get() = SquareMeter(this)


val kotlin.Double.squareMeter
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): SquareCentimeter = SquareCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): SquareCentimeter = SquareCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): SquareCentimeter = SquareCentimeter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): SquareCentimeter = SquareCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Short): SquareCentimeter = SquareCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): SquareCentimeter = SquareCentimeter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): SquareCentimeter = SquareCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Int): SquareCentimeter = SquareCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): SquareCentimeter = SquareCentimeter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): SquareCentimeter = SquareCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Long): SquareCentimeter = SquareCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): SquareCentimeter = SquareCentimeter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): SquareCentimeter = SquareCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Float): SquareCentimeter = SquareCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): SquareCentimeter = SquareCentimeter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): SquareCentimeter = SquareCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Double): SquareCentimeter = SquareCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): SquareCentimeter = SquareCentimeter(this.value.rem(other))


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

val kotlin.Byte.squareCentimeter
    get() = SquareCentimeter(this)


val kotlin.Short.squareCentimeter
    get() = SquareCentimeter(this)


val kotlin.Int.squareCentimeter
    get() = SquareCentimeter(this)


val kotlin.Long.squareCentimeter
    get() = SquareCentimeter(this)


val kotlin.Float.squareCentimeter
    get() = SquareCentimeter(this)


val kotlin.Double.squareCentimeter
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): SquareMile = SquareMile(this.value.times(other))
    override operator fun div(other: kotlin.Byte): SquareMile = SquareMile(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): SquareMile = SquareMile(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): SquareMile = SquareMile(this.value.times(other))
    override operator fun div(other: kotlin.Short): SquareMile = SquareMile(this.value.div(other))
    override operator fun rem(other: kotlin.Short): SquareMile = SquareMile(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): SquareMile = SquareMile(this.value.times(other))
    override operator fun div(other: kotlin.Int): SquareMile = SquareMile(this.value.div(other))
    override operator fun rem(other: kotlin.Int): SquareMile = SquareMile(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): SquareMile = SquareMile(this.value.times(other))
    override operator fun div(other: kotlin.Long): SquareMile = SquareMile(this.value.div(other))
    override operator fun rem(other: kotlin.Long): SquareMile = SquareMile(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): SquareMile = SquareMile(this.value.times(other))
    override operator fun div(other: kotlin.Float): SquareMile = SquareMile(this.value.div(other))
    override operator fun rem(other: kotlin.Float): SquareMile = SquareMile(this.value.rem(other))


    override operator fun times(other: kotlin.Double): SquareMile = SquareMile(this.value.times(other))
    override operator fun div(other: kotlin.Double): SquareMile = SquareMile(this.value.div(other))
    override operator fun rem(other: kotlin.Double): SquareMile = SquareMile(this.value.rem(other))


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

val kotlin.Byte.squareMile
    get() = SquareMile(this)


val kotlin.Short.squareMile
    get() = SquareMile(this)


val kotlin.Int.squareMile
    get() = SquareMile(this)


val kotlin.Long.squareMile
    get() = SquareMile(this)


val kotlin.Float.squareMile
    get() = SquareMile(this)


val kotlin.Double.squareMile
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Acre = Acre(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Acre = Acre(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Acre = Acre(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Acre = Acre(this.value.times(other))
    override operator fun div(other: kotlin.Short): Acre = Acre(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Acre = Acre(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Acre = Acre(this.value.times(other))
    override operator fun div(other: kotlin.Int): Acre = Acre(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Acre = Acre(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Acre = Acre(this.value.times(other))
    override operator fun div(other: kotlin.Long): Acre = Acre(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Acre = Acre(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Acre = Acre(this.value.times(other))
    override operator fun div(other: kotlin.Float): Acre = Acre(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Acre = Acre(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Acre = Acre(this.value.times(other))
    override operator fun div(other: kotlin.Double): Acre = Acre(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Acre = Acre(this.value.rem(other))


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

val kotlin.Byte.acre
    get() = Acre(this)


val kotlin.Short.acre
    get() = Acre(this)


val kotlin.Int.acre
    get() = Acre(this)


val kotlin.Long.acre
    get() = Acre(this)


val kotlin.Float.acre
    get() = Acre(this)


val kotlin.Double.acre
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): SquareYard = SquareYard(this.value.times(other))
    override operator fun div(other: kotlin.Byte): SquareYard = SquareYard(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): SquareYard = SquareYard(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): SquareYard = SquareYard(this.value.times(other))
    override operator fun div(other: kotlin.Short): SquareYard = SquareYard(this.value.div(other))
    override operator fun rem(other: kotlin.Short): SquareYard = SquareYard(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): SquareYard = SquareYard(this.value.times(other))
    override operator fun div(other: kotlin.Int): SquareYard = SquareYard(this.value.div(other))
    override operator fun rem(other: kotlin.Int): SquareYard = SquareYard(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): SquareYard = SquareYard(this.value.times(other))
    override operator fun div(other: kotlin.Long): SquareYard = SquareYard(this.value.div(other))
    override operator fun rem(other: kotlin.Long): SquareYard = SquareYard(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): SquareYard = SquareYard(this.value.times(other))
    override operator fun div(other: kotlin.Float): SquareYard = SquareYard(this.value.div(other))
    override operator fun rem(other: kotlin.Float): SquareYard = SquareYard(this.value.rem(other))


    override operator fun times(other: kotlin.Double): SquareYard = SquareYard(this.value.times(other))
    override operator fun div(other: kotlin.Double): SquareYard = SquareYard(this.value.div(other))
    override operator fun rem(other: kotlin.Double): SquareYard = SquareYard(this.value.rem(other))


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

val kotlin.Byte.squareYard
    get() = SquareYard(this)


val kotlin.Short.squareYard
    get() = SquareYard(this)


val kotlin.Int.squareYard
    get() = SquareYard(this)


val kotlin.Long.squareYard
    get() = SquareYard(this)


val kotlin.Float.squareYard
    get() = SquareYard(this)


val kotlin.Double.squareYard
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): SquareFoot = SquareFoot(this.value.times(other))
    override operator fun div(other: kotlin.Byte): SquareFoot = SquareFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): SquareFoot = SquareFoot(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): SquareFoot = SquareFoot(this.value.times(other))
    override operator fun div(other: kotlin.Short): SquareFoot = SquareFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Short): SquareFoot = SquareFoot(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): SquareFoot = SquareFoot(this.value.times(other))
    override operator fun div(other: kotlin.Int): SquareFoot = SquareFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Int): SquareFoot = SquareFoot(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): SquareFoot = SquareFoot(this.value.times(other))
    override operator fun div(other: kotlin.Long): SquareFoot = SquareFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Long): SquareFoot = SquareFoot(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): SquareFoot = SquareFoot(this.value.times(other))
    override operator fun div(other: kotlin.Float): SquareFoot = SquareFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Float): SquareFoot = SquareFoot(this.value.rem(other))


    override operator fun times(other: kotlin.Double): SquareFoot = SquareFoot(this.value.times(other))
    override operator fun div(other: kotlin.Double): SquareFoot = SquareFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Double): SquareFoot = SquareFoot(this.value.rem(other))


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

val kotlin.Byte.squareFoot
    get() = SquareFoot(this)


val kotlin.Short.squareFoot
    get() = SquareFoot(this)


val kotlin.Int.squareFoot
    get() = SquareFoot(this)


val kotlin.Long.squareFoot
    get() = SquareFoot(this)


val kotlin.Float.squareFoot
    get() = SquareFoot(this)


val kotlin.Double.squareFoot
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): SquareInch = SquareInch(this.value.times(other))
    override operator fun div(other: kotlin.Byte): SquareInch = SquareInch(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): SquareInch = SquareInch(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): SquareInch = SquareInch(this.value.times(other))
    override operator fun div(other: kotlin.Short): SquareInch = SquareInch(this.value.div(other))
    override operator fun rem(other: kotlin.Short): SquareInch = SquareInch(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): SquareInch = SquareInch(this.value.times(other))
    override operator fun div(other: kotlin.Int): SquareInch = SquareInch(this.value.div(other))
    override operator fun rem(other: kotlin.Int): SquareInch = SquareInch(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): SquareInch = SquareInch(this.value.times(other))
    override operator fun div(other: kotlin.Long): SquareInch = SquareInch(this.value.div(other))
    override operator fun rem(other: kotlin.Long): SquareInch = SquareInch(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): SquareInch = SquareInch(this.value.times(other))
    override operator fun div(other: kotlin.Float): SquareInch = SquareInch(this.value.div(other))
    override operator fun rem(other: kotlin.Float): SquareInch = SquareInch(this.value.rem(other))


    override operator fun times(other: kotlin.Double): SquareInch = SquareInch(this.value.times(other))
    override operator fun div(other: kotlin.Double): SquareInch = SquareInch(this.value.div(other))
    override operator fun rem(other: kotlin.Double): SquareInch = SquareInch(this.value.rem(other))


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

val kotlin.Byte.squareInch
    get() = SquareInch(this)


val kotlin.Short.squareInch
    get() = SquareInch(this)


val kotlin.Int.squareInch
    get() = SquareInch(this)


val kotlin.Long.squareInch
    get() = SquareInch(this)


val kotlin.Float.squareInch
    get() = SquareInch(this)


val kotlin.Double.squareInch
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Dunam = Dunam(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Dunam = Dunam(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Dunam = Dunam(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Dunam = Dunam(this.value.times(other))
    override operator fun div(other: kotlin.Short): Dunam = Dunam(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Dunam = Dunam(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Dunam = Dunam(this.value.times(other))
    override operator fun div(other: kotlin.Int): Dunam = Dunam(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Dunam = Dunam(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Dunam = Dunam(this.value.times(other))
    override operator fun div(other: kotlin.Long): Dunam = Dunam(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Dunam = Dunam(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Dunam = Dunam(this.value.times(other))
    override operator fun div(other: kotlin.Float): Dunam = Dunam(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Dunam = Dunam(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Dunam = Dunam(this.value.times(other))
    override operator fun div(other: kotlin.Double): Dunam = Dunam(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Dunam = Dunam(this.value.rem(other))


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

val kotlin.Byte.dunam
    get() = Dunam(this)


val kotlin.Short.dunam
    get() = Dunam(this)


val kotlin.Int.dunam
    get() = Dunam(this)


val kotlin.Long.dunam
    get() = Dunam(this)


val kotlin.Float.dunam
    get() = Dunam(this)


val kotlin.Double.dunam
    get() = Dunam(this)
                            
