package info.leonenko.i18n.units.volume

import kotlin.math.truncate
import kotlin.math.round
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.abs
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import info.leonenko.i18n.units.MeasurementUnit
import info.leonenko.i18n.units.MeasurementUnitValue
import info.leonenko.i18n.units.MeasurementUnitLanguage
import info.leonenko.i18n.units.toMeasurementUnitValue

@Serializable(Volume.Serializer::class)
interface Volume : MeasurementUnit, Comparable<Volume> {
    override val measurement: String get() = "volume"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Volume> = emptyMap()) : KSerializer<Volume> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())

        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Volume", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Volume {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Volume unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "cubic-kilometer" -> CubicKilometer(value)
                "cubic-meter" -> CubicMeter(value)
                "cubic-centimeter" -> CubicCentimeter(value)
                "cubic-mile" -> CubicMile(value)
                "cubic-yard" -> CubicYard(value)
                "cubic-foot" -> CubicFoot(value)
                "cubic-inch" -> CubicInch(value)
                "megaliter" -> Megaliter(value)
                "hectoliter" -> Hectoliter(value)
                "liter" -> Liter(value)
                "deciliter" -> Deciliter(value)
                "centiliter" -> Centiliter(value)
                "milliliter" -> Milliliter(value)
                "pint-metric" -> PintMetric(value)
                "cup-metric" -> CupMetric(value)
                "acre-foot" -> AcreFoot(value)
                "bushel" -> Bushel(value)
                "gallon" -> Gallon(value)
                "gallon-imperial" -> GallonImperial(value)
                "quart" -> Quart(value)
                "pint" -> Pint(value)
                "cup" -> Cup(value)
                "fluid-ounce" -> FluidOunce(value)
                "fluid-ounce-imperial" -> FluidOunceImperial(value)
                "tablespoon" -> Tablespoon(value)
                "teaspoon" -> Teaspoon(value)
                "barrel" -> Barrel(value)
                "dessert-spoon" -> DessertSpoon(value)
                "dessert-spoon-imperial" -> DessertSpoonImperial(value)
                "drop" -> Drop(value)
                "dram" -> Dram(value)
                "jigger" -> Jigger(value)
                "pinch" -> Pinch(value)
                "quart-imperial" -> QuartImperial(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Volume unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Volume) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toCubicKilometer(): CubicKilometer {
        return CubicKilometer.fromCubicMeter(toCubicMeter())
    }

    fun toCubicMeter(): CubicMeter
    override fun getBaseUnitValue() = toCubicMeter().value
    fun toCubicCentimeter(): CubicCentimeter {
        return CubicCentimeter.fromCubicMeter(toCubicMeter())
    }

    fun toCubicMile(): CubicMile {
        return CubicMile.fromCubicMeter(toCubicMeter())
    }

    fun toCubicYard(): CubicYard {
        return CubicYard.fromCubicMeter(toCubicMeter())
    }

    fun toCubicFoot(): CubicFoot {
        return CubicFoot.fromCubicMeter(toCubicMeter())
    }

    fun toCubicInch(): CubicInch {
        return CubicInch.fromCubicMeter(toCubicMeter())
    }

    fun toMegaliter(): Megaliter {
        return Megaliter.fromCubicMeter(toCubicMeter())
    }

    fun toHectoliter(): Hectoliter {
        return Hectoliter.fromCubicMeter(toCubicMeter())
    }

    fun toLiter(): Liter {
        return Liter.fromCubicMeter(toCubicMeter())
    }

    fun toDeciliter(): Deciliter {
        return Deciliter.fromCubicMeter(toCubicMeter())
    }

    fun toCentiliter(): Centiliter {
        return Centiliter.fromCubicMeter(toCubicMeter())
    }

    fun toMilliliter(): Milliliter {
        return Milliliter.fromCubicMeter(toCubicMeter())
    }

    fun toPintMetric(): PintMetric {
        return PintMetric.fromCubicMeter(toCubicMeter())
    }

    fun toCupMetric(): CupMetric {
        return CupMetric.fromCubicMeter(toCubicMeter())
    }

    fun toAcreFoot(): AcreFoot {
        return AcreFoot.fromCubicMeter(toCubicMeter())
    }

    fun toBushel(): Bushel {
        return Bushel.fromCubicMeter(toCubicMeter())
    }

    fun toGallon(): Gallon {
        return Gallon.fromCubicMeter(toCubicMeter())
    }

    fun toGallonImperial(): GallonImperial {
        return GallonImperial.fromCubicMeter(toCubicMeter())
    }

    fun toQuart(): Quart {
        return Quart.fromCubicMeter(toCubicMeter())
    }

    fun toPint(): Pint {
        return Pint.fromCubicMeter(toCubicMeter())
    }

    fun toCup(): Cup {
        return Cup.fromCubicMeter(toCubicMeter())
    }

    fun toFluidOunce(): FluidOunce {
        return FluidOunce.fromCubicMeter(toCubicMeter())
    }

    fun toFluidOunceImperial(): FluidOunceImperial {
        return FluidOunceImperial.fromCubicMeter(toCubicMeter())
    }

    fun toTablespoon(): Tablespoon {
        return Tablespoon.fromCubicMeter(toCubicMeter())
    }

    fun toTeaspoon(): Teaspoon {
        return Teaspoon.fromCubicMeter(toCubicMeter())
    }

    fun toBarrel(): Barrel {
        return Barrel.fromCubicMeter(toCubicMeter())
    }

    fun toDessertSpoon(): DessertSpoon {
        return DessertSpoon.fromCubicMeter(toCubicMeter())
    }

    fun toDessertSpoonImperial(): DessertSpoonImperial {
        return DessertSpoonImperial.fromCubicMeter(toCubicMeter())
    }

    fun toDrop(): Drop {
        return Drop.fromCubicMeter(toCubicMeter())
    }

    fun toDram(): Dram {
        return Dram.fromCubicMeter(toCubicMeter())
    }

    fun toJigger(): Jigger {
        return Jigger.fromCubicMeter(toCubicMeter())
    }

    fun toPinch(): Pinch {
        return Pinch.fromCubicMeter(toCubicMeter())
    }

    fun toQuartImperial(): QuartImperial {
        return QuartImperial.fromCubicMeter(toCubicMeter())
    }

    companion object {
        val ZERO: Volume = CubicMeter(0.0)

    }

    override operator fun compareTo(other: Volume): Int = this.toCubicMeter().value.compareTo(other.toCubicMeter().value)

    operator fun plus(other: Volume): CubicMeter = this.toCubicMeter() + other.toCubicMeter()
    operator fun minus(other: Volume): CubicMeter = this.toCubicMeter() - other.toCubicMeter()
    operator fun div(other: Volume): Double = this.toCubicMeter().value / other.toCubicMeter().value
    operator fun unaryMinus(): Volume
    operator fun unaryPlus(): Volume
    operator fun inc(): Volume
    operator fun dec(): Volume

    fun round(): Volume
    fun truncate(): Volume
    fun floor(): Volume
    fun ceil(): Volume
    fun abs(): Volume


    operator fun times(other: kotlin.Byte): Volume
    operator fun div(other: kotlin.Byte): Volume
    operator fun rem(other: kotlin.Byte): Volume


    operator fun times(other: kotlin.Short): Volume
    operator fun div(other: kotlin.Short): Volume
    operator fun rem(other: kotlin.Short): Volume


    operator fun times(other: kotlin.Int): Volume
    operator fun div(other: kotlin.Int): Volume
    operator fun rem(other: kotlin.Int): Volume


    operator fun times(other: kotlin.Long): Volume
    operator fun div(other: kotlin.Long): Volume
    operator fun rem(other: kotlin.Long): Volume


    operator fun times(other: kotlin.Float): Volume
    operator fun div(other: kotlin.Float): Volume
    operator fun rem(other: kotlin.Float): Volume


    operator fun times(other: kotlin.Double): Volume
    operator fun div(other: kotlin.Double): Volume
    operator fun rem(other: kotlin.Double): Volume

}

data class CubicKilometer(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "cubic-kilometer"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeCubicKilometer
    override fun toCubicMeter() = CubicMeter(value * ((1 * (1e3)) * (1 * (1e3)) * (1 * (1e3))) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = CubicKilometer(0.0)
        fun fromCubicMeter(value: CubicMeter) = CubicKilometer((value.value - 0) / ((1 * (1e3)) * (1 * (1e3)) * (1 * (1e3))))
    }

    operator fun plus(other: CubicKilometer): CubicKilometer = CubicKilometer(this.value + other.value)
    operator fun minus(other: CubicKilometer): CubicKilometer = CubicKilometer(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): CubicKilometer = CubicKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Byte): CubicKilometer = CubicKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): CubicKilometer = CubicKilometer(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): CubicKilometer = CubicKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Short): CubicKilometer = CubicKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Short): CubicKilometer = CubicKilometer(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): CubicKilometer = CubicKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Int): CubicKilometer = CubicKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Int): CubicKilometer = CubicKilometer(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): CubicKilometer = CubicKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Long): CubicKilometer = CubicKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Long): CubicKilometer = CubicKilometer(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): CubicKilometer = CubicKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Float): CubicKilometer = CubicKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Float): CubicKilometer = CubicKilometer(this.value.rem(other))


    override operator fun times(other: kotlin.Double): CubicKilometer = CubicKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Double): CubicKilometer = CubicKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Double): CubicKilometer = CubicKilometer(this.value.rem(other))


    override operator fun unaryMinus(): CubicKilometer = CubicKilometer(-value)
    override operator fun unaryPlus(): CubicKilometer = this
    override operator fun inc(): CubicKilometer = CubicKilometer(this.value + 1)
    override operator fun dec(): CubicKilometer = CubicKilometer(this.value - 1)

    override fun round(): CubicKilometer = CubicKilometer(round(this.value))
    override fun truncate(): CubicKilometer = CubicKilometer(truncate(this.value))
    override fun floor(): CubicKilometer = CubicKilometer(floor(this.value))
    override fun ceil(): CubicKilometer = CubicKilometer(ceil(this.value))
    override fun abs(): CubicKilometer = CubicKilometer(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is CubicKilometer -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.cubicKilometer
    get() = CubicKilometer(this)


val kotlin.Short.cubicKilometer
    get() = CubicKilometer(this)


val kotlin.Int.cubicKilometer
    get() = CubicKilometer(this)


val kotlin.Long.cubicKilometer
    get() = CubicKilometer(this)


val kotlin.Float.cubicKilometer
    get() = CubicKilometer(this)


val kotlin.Double.cubicKilometer
    get() = CubicKilometer(this)


data class CubicMeter(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "cubic-meter"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeCubicMeter
    override fun toCubicMeter() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = CubicMeter(0.0)
        fun fromCubicMeter(value: CubicMeter) = value
    }

    operator fun plus(other: CubicMeter): CubicMeter = CubicMeter(this.value + other.value)
    operator fun minus(other: CubicMeter): CubicMeter = CubicMeter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): CubicMeter = CubicMeter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): CubicMeter = CubicMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): CubicMeter = CubicMeter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): CubicMeter = CubicMeter(this.value.times(other))
    override operator fun div(other: kotlin.Short): CubicMeter = CubicMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): CubicMeter = CubicMeter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): CubicMeter = CubicMeter(this.value.times(other))
    override operator fun div(other: kotlin.Int): CubicMeter = CubicMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): CubicMeter = CubicMeter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): CubicMeter = CubicMeter(this.value.times(other))
    override operator fun div(other: kotlin.Long): CubicMeter = CubicMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): CubicMeter = CubicMeter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): CubicMeter = CubicMeter(this.value.times(other))
    override operator fun div(other: kotlin.Float): CubicMeter = CubicMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): CubicMeter = CubicMeter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): CubicMeter = CubicMeter(this.value.times(other))
    override operator fun div(other: kotlin.Double): CubicMeter = CubicMeter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): CubicMeter = CubicMeter(this.value.rem(other))


    override operator fun unaryMinus(): CubicMeter = CubicMeter(-value)
    override operator fun unaryPlus(): CubicMeter = this
    override operator fun inc(): CubicMeter = CubicMeter(this.value + 1)
    override operator fun dec(): CubicMeter = CubicMeter(this.value - 1)

    override fun round(): CubicMeter = CubicMeter(round(this.value))
    override fun truncate(): CubicMeter = CubicMeter(truncate(this.value))
    override fun floor(): CubicMeter = CubicMeter(floor(this.value))
    override fun ceil(): CubicMeter = CubicMeter(ceil(this.value))
    override fun abs(): CubicMeter = CubicMeter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is CubicMeter -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.cubicMeter
    get() = CubicMeter(this)


val kotlin.Short.cubicMeter
    get() = CubicMeter(this)


val kotlin.Int.cubicMeter
    get() = CubicMeter(this)


val kotlin.Long.cubicMeter
    get() = CubicMeter(this)


val kotlin.Float.cubicMeter
    get() = CubicMeter(this)


val kotlin.Double.cubicMeter
    get() = CubicMeter(this)


data class CubicCentimeter(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "cubic-centimeter"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeCubicCentimeter
    override fun toCubicMeter() = CubicMeter(value * ((1 * (1e-2)) * (1 * (1e-2)) * (1 * (1e-2))) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = CubicCentimeter(0.0)
        fun fromCubicMeter(value: CubicMeter) = CubicCentimeter((value.value - 0) / ((1 * (1e-2)) * (1 * (1e-2)) * (1 * (1e-2))))
    }

    operator fun plus(other: CubicCentimeter): CubicCentimeter = CubicCentimeter(this.value + other.value)
    operator fun minus(other: CubicCentimeter): CubicCentimeter = CubicCentimeter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): CubicCentimeter = CubicCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): CubicCentimeter = CubicCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): CubicCentimeter = CubicCentimeter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): CubicCentimeter = CubicCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Short): CubicCentimeter = CubicCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): CubicCentimeter = CubicCentimeter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): CubicCentimeter = CubicCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Int): CubicCentimeter = CubicCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): CubicCentimeter = CubicCentimeter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): CubicCentimeter = CubicCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Long): CubicCentimeter = CubicCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): CubicCentimeter = CubicCentimeter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): CubicCentimeter = CubicCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Float): CubicCentimeter = CubicCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): CubicCentimeter = CubicCentimeter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): CubicCentimeter = CubicCentimeter(this.value.times(other))
    override operator fun div(other: kotlin.Double): CubicCentimeter = CubicCentimeter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): CubicCentimeter = CubicCentimeter(this.value.rem(other))


    override operator fun unaryMinus(): CubicCentimeter = CubicCentimeter(-value)
    override operator fun unaryPlus(): CubicCentimeter = this
    override operator fun inc(): CubicCentimeter = CubicCentimeter(this.value + 1)
    override operator fun dec(): CubicCentimeter = CubicCentimeter(this.value - 1)

    override fun round(): CubicCentimeter = CubicCentimeter(round(this.value))
    override fun truncate(): CubicCentimeter = CubicCentimeter(truncate(this.value))
    override fun floor(): CubicCentimeter = CubicCentimeter(floor(this.value))
    override fun ceil(): CubicCentimeter = CubicCentimeter(ceil(this.value))
    override fun abs(): CubicCentimeter = CubicCentimeter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is CubicCentimeter -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.cubicCentimeter
    get() = CubicCentimeter(this)


val kotlin.Short.cubicCentimeter
    get() = CubicCentimeter(this)


val kotlin.Int.cubicCentimeter
    get() = CubicCentimeter(this)


val kotlin.Long.cubicCentimeter
    get() = CubicCentimeter(this)


val kotlin.Float.cubicCentimeter
    get() = CubicCentimeter(this)


val kotlin.Double.cubicCentimeter
    get() = CubicCentimeter(this)


data class CubicMile(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "cubic-mile"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeCubicMile
    override fun toCubicMeter() = CubicMeter(value * ((1609.344) * (1609.344) * (1609.344)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = CubicMile(0.0)
        fun fromCubicMeter(value: CubicMeter) = CubicMile((value.value - 0) / ((1609.344) * (1609.344) * (1609.344)))
    }

    operator fun plus(other: CubicMile): CubicMile = CubicMile(this.value + other.value)
    operator fun minus(other: CubicMile): CubicMile = CubicMile(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): CubicMile = CubicMile(this.value.times(other))
    override operator fun div(other: kotlin.Byte): CubicMile = CubicMile(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): CubicMile = CubicMile(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): CubicMile = CubicMile(this.value.times(other))
    override operator fun div(other: kotlin.Short): CubicMile = CubicMile(this.value.div(other))
    override operator fun rem(other: kotlin.Short): CubicMile = CubicMile(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): CubicMile = CubicMile(this.value.times(other))
    override operator fun div(other: kotlin.Int): CubicMile = CubicMile(this.value.div(other))
    override operator fun rem(other: kotlin.Int): CubicMile = CubicMile(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): CubicMile = CubicMile(this.value.times(other))
    override operator fun div(other: kotlin.Long): CubicMile = CubicMile(this.value.div(other))
    override operator fun rem(other: kotlin.Long): CubicMile = CubicMile(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): CubicMile = CubicMile(this.value.times(other))
    override operator fun div(other: kotlin.Float): CubicMile = CubicMile(this.value.div(other))
    override operator fun rem(other: kotlin.Float): CubicMile = CubicMile(this.value.rem(other))


    override operator fun times(other: kotlin.Double): CubicMile = CubicMile(this.value.times(other))
    override operator fun div(other: kotlin.Double): CubicMile = CubicMile(this.value.div(other))
    override operator fun rem(other: kotlin.Double): CubicMile = CubicMile(this.value.rem(other))


    override operator fun unaryMinus(): CubicMile = CubicMile(-value)
    override operator fun unaryPlus(): CubicMile = this
    override operator fun inc(): CubicMile = CubicMile(this.value + 1)
    override operator fun dec(): CubicMile = CubicMile(this.value - 1)

    override fun round(): CubicMile = CubicMile(round(this.value))
    override fun truncate(): CubicMile = CubicMile(truncate(this.value))
    override fun floor(): CubicMile = CubicMile(floor(this.value))
    override fun ceil(): CubicMile = CubicMile(ceil(this.value))
    override fun abs(): CubicMile = CubicMile(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is CubicMile -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.cubicMile
    get() = CubicMile(this)


val kotlin.Short.cubicMile
    get() = CubicMile(this)


val kotlin.Int.cubicMile
    get() = CubicMile(this)


val kotlin.Long.cubicMile
    get() = CubicMile(this)


val kotlin.Float.cubicMile
    get() = CubicMile(this)


val kotlin.Double.cubicMile
    get() = CubicMile(this)


data class CubicYard(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "cubic-yard"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeCubicYard
    override fun toCubicMeter() = CubicMeter(value * ((0.9144000000000001) * (0.9144000000000001) * (0.9144000000000001)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = CubicYard(0.0)
        fun fromCubicMeter(value: CubicMeter) = CubicYard((value.value - 0) / ((0.9144000000000001) * (0.9144000000000001) * (0.9144000000000001)))
    }

    operator fun plus(other: CubicYard): CubicYard = CubicYard(this.value + other.value)
    operator fun minus(other: CubicYard): CubicYard = CubicYard(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): CubicYard = CubicYard(this.value.times(other))
    override operator fun div(other: kotlin.Byte): CubicYard = CubicYard(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): CubicYard = CubicYard(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): CubicYard = CubicYard(this.value.times(other))
    override operator fun div(other: kotlin.Short): CubicYard = CubicYard(this.value.div(other))
    override operator fun rem(other: kotlin.Short): CubicYard = CubicYard(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): CubicYard = CubicYard(this.value.times(other))
    override operator fun div(other: kotlin.Int): CubicYard = CubicYard(this.value.div(other))
    override operator fun rem(other: kotlin.Int): CubicYard = CubicYard(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): CubicYard = CubicYard(this.value.times(other))
    override operator fun div(other: kotlin.Long): CubicYard = CubicYard(this.value.div(other))
    override operator fun rem(other: kotlin.Long): CubicYard = CubicYard(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): CubicYard = CubicYard(this.value.times(other))
    override operator fun div(other: kotlin.Float): CubicYard = CubicYard(this.value.div(other))
    override operator fun rem(other: kotlin.Float): CubicYard = CubicYard(this.value.rem(other))


    override operator fun times(other: kotlin.Double): CubicYard = CubicYard(this.value.times(other))
    override operator fun div(other: kotlin.Double): CubicYard = CubicYard(this.value.div(other))
    override operator fun rem(other: kotlin.Double): CubicYard = CubicYard(this.value.rem(other))


    override operator fun unaryMinus(): CubicYard = CubicYard(-value)
    override operator fun unaryPlus(): CubicYard = this
    override operator fun inc(): CubicYard = CubicYard(this.value + 1)
    override operator fun dec(): CubicYard = CubicYard(this.value - 1)

    override fun round(): CubicYard = CubicYard(round(this.value))
    override fun truncate(): CubicYard = CubicYard(truncate(this.value))
    override fun floor(): CubicYard = CubicYard(floor(this.value))
    override fun ceil(): CubicYard = CubicYard(ceil(this.value))
    override fun abs(): CubicYard = CubicYard(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is CubicYard -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.cubicYard
    get() = CubicYard(this)


val kotlin.Short.cubicYard
    get() = CubicYard(this)


val kotlin.Int.cubicYard
    get() = CubicYard(this)


val kotlin.Long.cubicYard
    get() = CubicYard(this)


val kotlin.Float.cubicYard
    get() = CubicYard(this)


val kotlin.Double.cubicYard
    get() = CubicYard(this)


data class CubicFoot(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "cubic-foot"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeCubicFoot
    override fun toCubicMeter() = CubicMeter(value * ((0.3048) * (0.3048) * (0.3048)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = CubicFoot(0.0)
        fun fromCubicMeter(value: CubicMeter) = CubicFoot((value.value - 0) / ((0.3048) * (0.3048) * (0.3048)))
    }

    operator fun plus(other: CubicFoot): CubicFoot = CubicFoot(this.value + other.value)
    operator fun minus(other: CubicFoot): CubicFoot = CubicFoot(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): CubicFoot = CubicFoot(this.value.times(other))
    override operator fun div(other: kotlin.Byte): CubicFoot = CubicFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): CubicFoot = CubicFoot(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): CubicFoot = CubicFoot(this.value.times(other))
    override operator fun div(other: kotlin.Short): CubicFoot = CubicFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Short): CubicFoot = CubicFoot(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): CubicFoot = CubicFoot(this.value.times(other))
    override operator fun div(other: kotlin.Int): CubicFoot = CubicFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Int): CubicFoot = CubicFoot(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): CubicFoot = CubicFoot(this.value.times(other))
    override operator fun div(other: kotlin.Long): CubicFoot = CubicFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Long): CubicFoot = CubicFoot(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): CubicFoot = CubicFoot(this.value.times(other))
    override operator fun div(other: kotlin.Float): CubicFoot = CubicFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Float): CubicFoot = CubicFoot(this.value.rem(other))


    override operator fun times(other: kotlin.Double): CubicFoot = CubicFoot(this.value.times(other))
    override operator fun div(other: kotlin.Double): CubicFoot = CubicFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Double): CubicFoot = CubicFoot(this.value.rem(other))


    override operator fun unaryMinus(): CubicFoot = CubicFoot(-value)
    override operator fun unaryPlus(): CubicFoot = this
    override operator fun inc(): CubicFoot = CubicFoot(this.value + 1)
    override operator fun dec(): CubicFoot = CubicFoot(this.value - 1)

    override fun round(): CubicFoot = CubicFoot(round(this.value))
    override fun truncate(): CubicFoot = CubicFoot(truncate(this.value))
    override fun floor(): CubicFoot = CubicFoot(floor(this.value))
    override fun ceil(): CubicFoot = CubicFoot(ceil(this.value))
    override fun abs(): CubicFoot = CubicFoot(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is CubicFoot -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.cubicFoot
    get() = CubicFoot(this)


val kotlin.Short.cubicFoot
    get() = CubicFoot(this)


val kotlin.Int.cubicFoot
    get() = CubicFoot(this)


val kotlin.Long.cubicFoot
    get() = CubicFoot(this)


val kotlin.Float.cubicFoot
    get() = CubicFoot(this)


val kotlin.Double.cubicFoot
    get() = CubicFoot(this)


data class CubicInch(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "cubic-inch"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeCubicInch
    override fun toCubicMeter() = CubicMeter(value * ((0.025400000000000002) * (0.025400000000000002) * (0.025400000000000002)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = CubicInch(0.0)
        fun fromCubicMeter(value: CubicMeter) = CubicInch((value.value - 0) / ((0.025400000000000002) * (0.025400000000000002) * (0.025400000000000002)))
    }

    operator fun plus(other: CubicInch): CubicInch = CubicInch(this.value + other.value)
    operator fun minus(other: CubicInch): CubicInch = CubicInch(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): CubicInch = CubicInch(this.value.times(other))
    override operator fun div(other: kotlin.Byte): CubicInch = CubicInch(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): CubicInch = CubicInch(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): CubicInch = CubicInch(this.value.times(other))
    override operator fun div(other: kotlin.Short): CubicInch = CubicInch(this.value.div(other))
    override operator fun rem(other: kotlin.Short): CubicInch = CubicInch(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): CubicInch = CubicInch(this.value.times(other))
    override operator fun div(other: kotlin.Int): CubicInch = CubicInch(this.value.div(other))
    override operator fun rem(other: kotlin.Int): CubicInch = CubicInch(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): CubicInch = CubicInch(this.value.times(other))
    override operator fun div(other: kotlin.Long): CubicInch = CubicInch(this.value.div(other))
    override operator fun rem(other: kotlin.Long): CubicInch = CubicInch(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): CubicInch = CubicInch(this.value.times(other))
    override operator fun div(other: kotlin.Float): CubicInch = CubicInch(this.value.div(other))
    override operator fun rem(other: kotlin.Float): CubicInch = CubicInch(this.value.rem(other))


    override operator fun times(other: kotlin.Double): CubicInch = CubicInch(this.value.times(other))
    override operator fun div(other: kotlin.Double): CubicInch = CubicInch(this.value.div(other))
    override operator fun rem(other: kotlin.Double): CubicInch = CubicInch(this.value.rem(other))


    override operator fun unaryMinus(): CubicInch = CubicInch(-value)
    override operator fun unaryPlus(): CubicInch = this
    override operator fun inc(): CubicInch = CubicInch(this.value + 1)
    override operator fun dec(): CubicInch = CubicInch(this.value - 1)

    override fun round(): CubicInch = CubicInch(round(this.value))
    override fun truncate(): CubicInch = CubicInch(truncate(this.value))
    override fun floor(): CubicInch = CubicInch(floor(this.value))
    override fun ceil(): CubicInch = CubicInch(ceil(this.value))
    override fun abs(): CubicInch = CubicInch(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is CubicInch -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.cubicInch
    get() = CubicInch(this)


val kotlin.Short.cubicInch
    get() = CubicInch(this)


val kotlin.Int.cubicInch
    get() = CubicInch(this)


val kotlin.Long.cubicInch
    get() = CubicInch(this)


val kotlin.Float.cubicInch
    get() = CubicInch(this)


val kotlin.Double.cubicInch
    get() = CubicInch(this)


data class Megaliter(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "megaliter"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeMegaliter
    override fun toCubicMeter() = CubicMeter(value * (0.001 * (1e6)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Megaliter(0.0)
        fun fromCubicMeter(value: CubicMeter) = Megaliter((value.value - 0) / (0.001 * (1e6)))
    }

    operator fun plus(other: Megaliter): Megaliter = Megaliter(this.value + other.value)
    operator fun minus(other: Megaliter): Megaliter = Megaliter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Megaliter = Megaliter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Megaliter = Megaliter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Megaliter = Megaliter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Megaliter = Megaliter(this.value.times(other))
    override operator fun div(other: kotlin.Short): Megaliter = Megaliter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Megaliter = Megaliter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Megaliter = Megaliter(this.value.times(other))
    override operator fun div(other: kotlin.Int): Megaliter = Megaliter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Megaliter = Megaliter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Megaliter = Megaliter(this.value.times(other))
    override operator fun div(other: kotlin.Long): Megaliter = Megaliter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Megaliter = Megaliter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Megaliter = Megaliter(this.value.times(other))
    override operator fun div(other: kotlin.Float): Megaliter = Megaliter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Megaliter = Megaliter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Megaliter = Megaliter(this.value.times(other))
    override operator fun div(other: kotlin.Double): Megaliter = Megaliter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Megaliter = Megaliter(this.value.rem(other))


    override operator fun unaryMinus(): Megaliter = Megaliter(-value)
    override operator fun unaryPlus(): Megaliter = this
    override operator fun inc(): Megaliter = Megaliter(this.value + 1)
    override operator fun dec(): Megaliter = Megaliter(this.value - 1)

    override fun round(): Megaliter = Megaliter(round(this.value))
    override fun truncate(): Megaliter = Megaliter(truncate(this.value))
    override fun floor(): Megaliter = Megaliter(floor(this.value))
    override fun ceil(): Megaliter = Megaliter(ceil(this.value))
    override fun abs(): Megaliter = Megaliter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Megaliter -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.megaliter
    get() = Megaliter(this)


val kotlin.Short.megaliter
    get() = Megaliter(this)


val kotlin.Int.megaliter
    get() = Megaliter(this)


val kotlin.Long.megaliter
    get() = Megaliter(this)


val kotlin.Float.megaliter
    get() = Megaliter(this)


val kotlin.Double.megaliter
    get() = Megaliter(this)


data class Hectoliter(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "hectoliter"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeHectoliter
    override fun toCubicMeter() = CubicMeter(value * (0.001 * (1e2)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Hectoliter(0.0)
        fun fromCubicMeter(value: CubicMeter) = Hectoliter((value.value - 0) / (0.001 * (1e2)))
    }

    operator fun plus(other: Hectoliter): Hectoliter = Hectoliter(this.value + other.value)
    operator fun minus(other: Hectoliter): Hectoliter = Hectoliter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Hectoliter = Hectoliter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Hectoliter = Hectoliter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Hectoliter = Hectoliter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Hectoliter = Hectoliter(this.value.times(other))
    override operator fun div(other: kotlin.Short): Hectoliter = Hectoliter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Hectoliter = Hectoliter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Hectoliter = Hectoliter(this.value.times(other))
    override operator fun div(other: kotlin.Int): Hectoliter = Hectoliter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Hectoliter = Hectoliter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Hectoliter = Hectoliter(this.value.times(other))
    override operator fun div(other: kotlin.Long): Hectoliter = Hectoliter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Hectoliter = Hectoliter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Hectoliter = Hectoliter(this.value.times(other))
    override operator fun div(other: kotlin.Float): Hectoliter = Hectoliter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Hectoliter = Hectoliter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Hectoliter = Hectoliter(this.value.times(other))
    override operator fun div(other: kotlin.Double): Hectoliter = Hectoliter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Hectoliter = Hectoliter(this.value.rem(other))


    override operator fun unaryMinus(): Hectoliter = Hectoliter(-value)
    override operator fun unaryPlus(): Hectoliter = this
    override operator fun inc(): Hectoliter = Hectoliter(this.value + 1)
    override operator fun dec(): Hectoliter = Hectoliter(this.value - 1)

    override fun round(): Hectoliter = Hectoliter(round(this.value))
    override fun truncate(): Hectoliter = Hectoliter(truncate(this.value))
    override fun floor(): Hectoliter = Hectoliter(floor(this.value))
    override fun ceil(): Hectoliter = Hectoliter(ceil(this.value))
    override fun abs(): Hectoliter = Hectoliter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Hectoliter -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.hectoliter
    get() = Hectoliter(this)


val kotlin.Short.hectoliter
    get() = Hectoliter(this)


val kotlin.Int.hectoliter
    get() = Hectoliter(this)


val kotlin.Long.hectoliter
    get() = Hectoliter(this)


val kotlin.Float.hectoliter
    get() = Hectoliter(this)


val kotlin.Double.hectoliter
    get() = Hectoliter(this)


data class Liter(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "liter"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeLiter
    override fun toCubicMeter() = CubicMeter(value * (0.001) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Liter(0.0)
        fun fromCubicMeter(value: CubicMeter) = Liter((value.value - 0) / (0.001))
    }

    operator fun plus(other: Liter): Liter = Liter(this.value + other.value)
    operator fun minus(other: Liter): Liter = Liter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Liter = Liter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Liter = Liter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Liter = Liter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Liter = Liter(this.value.times(other))
    override operator fun div(other: kotlin.Short): Liter = Liter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Liter = Liter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Liter = Liter(this.value.times(other))
    override operator fun div(other: kotlin.Int): Liter = Liter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Liter = Liter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Liter = Liter(this.value.times(other))
    override operator fun div(other: kotlin.Long): Liter = Liter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Liter = Liter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Liter = Liter(this.value.times(other))
    override operator fun div(other: kotlin.Float): Liter = Liter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Liter = Liter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Liter = Liter(this.value.times(other))
    override operator fun div(other: kotlin.Double): Liter = Liter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Liter = Liter(this.value.rem(other))


    override operator fun unaryMinus(): Liter = Liter(-value)
    override operator fun unaryPlus(): Liter = this
    override operator fun inc(): Liter = Liter(this.value + 1)
    override operator fun dec(): Liter = Liter(this.value - 1)

    override fun round(): Liter = Liter(round(this.value))
    override fun truncate(): Liter = Liter(truncate(this.value))
    override fun floor(): Liter = Liter(floor(this.value))
    override fun ceil(): Liter = Liter(ceil(this.value))
    override fun abs(): Liter = Liter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Liter -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.liter
    get() = Liter(this)


val kotlin.Short.liter
    get() = Liter(this)


val kotlin.Int.liter
    get() = Liter(this)


val kotlin.Long.liter
    get() = Liter(this)


val kotlin.Float.liter
    get() = Liter(this)


val kotlin.Double.liter
    get() = Liter(this)


data class Deciliter(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "deciliter"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeDeciliter
    override fun toCubicMeter() = CubicMeter(value * (0.001 * (1e-1)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Deciliter(0.0)
        fun fromCubicMeter(value: CubicMeter) = Deciliter((value.value - 0) / (0.001 * (1e-1)))
    }

    operator fun plus(other: Deciliter): Deciliter = Deciliter(this.value + other.value)
    operator fun minus(other: Deciliter): Deciliter = Deciliter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Deciliter = Deciliter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Deciliter = Deciliter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Deciliter = Deciliter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Deciliter = Deciliter(this.value.times(other))
    override operator fun div(other: kotlin.Short): Deciliter = Deciliter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Deciliter = Deciliter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Deciliter = Deciliter(this.value.times(other))
    override operator fun div(other: kotlin.Int): Deciliter = Deciliter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Deciliter = Deciliter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Deciliter = Deciliter(this.value.times(other))
    override operator fun div(other: kotlin.Long): Deciliter = Deciliter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Deciliter = Deciliter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Deciliter = Deciliter(this.value.times(other))
    override operator fun div(other: kotlin.Float): Deciliter = Deciliter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Deciliter = Deciliter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Deciliter = Deciliter(this.value.times(other))
    override operator fun div(other: kotlin.Double): Deciliter = Deciliter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Deciliter = Deciliter(this.value.rem(other))


    override operator fun unaryMinus(): Deciliter = Deciliter(-value)
    override operator fun unaryPlus(): Deciliter = this
    override operator fun inc(): Deciliter = Deciliter(this.value + 1)
    override operator fun dec(): Deciliter = Deciliter(this.value - 1)

    override fun round(): Deciliter = Deciliter(round(this.value))
    override fun truncate(): Deciliter = Deciliter(truncate(this.value))
    override fun floor(): Deciliter = Deciliter(floor(this.value))
    override fun ceil(): Deciliter = Deciliter(ceil(this.value))
    override fun abs(): Deciliter = Deciliter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Deciliter -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.deciliter
    get() = Deciliter(this)


val kotlin.Short.deciliter
    get() = Deciliter(this)


val kotlin.Int.deciliter
    get() = Deciliter(this)


val kotlin.Long.deciliter
    get() = Deciliter(this)


val kotlin.Float.deciliter
    get() = Deciliter(this)


val kotlin.Double.deciliter
    get() = Deciliter(this)


data class Centiliter(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "centiliter"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeCentiliter
    override fun toCubicMeter() = CubicMeter(value * (0.001 * (1e-2)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Centiliter(0.0)
        fun fromCubicMeter(value: CubicMeter) = Centiliter((value.value - 0) / (0.001 * (1e-2)))
    }

    operator fun plus(other: Centiliter): Centiliter = Centiliter(this.value + other.value)
    operator fun minus(other: Centiliter): Centiliter = Centiliter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Centiliter = Centiliter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Centiliter = Centiliter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Centiliter = Centiliter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Centiliter = Centiliter(this.value.times(other))
    override operator fun div(other: kotlin.Short): Centiliter = Centiliter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Centiliter = Centiliter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Centiliter = Centiliter(this.value.times(other))
    override operator fun div(other: kotlin.Int): Centiliter = Centiliter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Centiliter = Centiliter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Centiliter = Centiliter(this.value.times(other))
    override operator fun div(other: kotlin.Long): Centiliter = Centiliter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Centiliter = Centiliter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Centiliter = Centiliter(this.value.times(other))
    override operator fun div(other: kotlin.Float): Centiliter = Centiliter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Centiliter = Centiliter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Centiliter = Centiliter(this.value.times(other))
    override operator fun div(other: kotlin.Double): Centiliter = Centiliter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Centiliter = Centiliter(this.value.rem(other))


    override operator fun unaryMinus(): Centiliter = Centiliter(-value)
    override operator fun unaryPlus(): Centiliter = this
    override operator fun inc(): Centiliter = Centiliter(this.value + 1)
    override operator fun dec(): Centiliter = Centiliter(this.value - 1)

    override fun round(): Centiliter = Centiliter(round(this.value))
    override fun truncate(): Centiliter = Centiliter(truncate(this.value))
    override fun floor(): Centiliter = Centiliter(floor(this.value))
    override fun ceil(): Centiliter = Centiliter(ceil(this.value))
    override fun abs(): Centiliter = Centiliter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Centiliter -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.centiliter
    get() = Centiliter(this)


val kotlin.Short.centiliter
    get() = Centiliter(this)


val kotlin.Int.centiliter
    get() = Centiliter(this)


val kotlin.Long.centiliter
    get() = Centiliter(this)


val kotlin.Float.centiliter
    get() = Centiliter(this)


val kotlin.Double.centiliter
    get() = Centiliter(this)


data class Milliliter(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "milliliter"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeMilliliter
    override fun toCubicMeter() = CubicMeter(value * (0.001 * (1e-3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Milliliter(0.0)
        fun fromCubicMeter(value: CubicMeter) = Milliliter((value.value - 0) / (0.001 * (1e-3)))
    }

    operator fun plus(other: Milliliter): Milliliter = Milliliter(this.value + other.value)
    operator fun minus(other: Milliliter): Milliliter = Milliliter(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Milliliter = Milliliter(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Milliliter = Milliliter(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Milliliter = Milliliter(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Milliliter = Milliliter(this.value.times(other))
    override operator fun div(other: kotlin.Short): Milliliter = Milliliter(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Milliliter = Milliliter(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Milliliter = Milliliter(this.value.times(other))
    override operator fun div(other: kotlin.Int): Milliliter = Milliliter(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Milliliter = Milliliter(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Milliliter = Milliliter(this.value.times(other))
    override operator fun div(other: kotlin.Long): Milliliter = Milliliter(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Milliliter = Milliliter(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Milliliter = Milliliter(this.value.times(other))
    override operator fun div(other: kotlin.Float): Milliliter = Milliliter(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Milliliter = Milliliter(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Milliliter = Milliliter(this.value.times(other))
    override operator fun div(other: kotlin.Double): Milliliter = Milliliter(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Milliliter = Milliliter(this.value.rem(other))


    override operator fun unaryMinus(): Milliliter = Milliliter(-value)
    override operator fun unaryPlus(): Milliliter = this
    override operator fun inc(): Milliliter = Milliliter(this.value + 1)
    override operator fun dec(): Milliliter = Milliliter(this.value - 1)

    override fun round(): Milliliter = Milliliter(round(this.value))
    override fun truncate(): Milliliter = Milliliter(truncate(this.value))
    override fun floor(): Milliliter = Milliliter(floor(this.value))
    override fun ceil(): Milliliter = Milliliter(ceil(this.value))
    override fun abs(): Milliliter = Milliliter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Milliliter -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.milliliter
    get() = Milliliter(this)


val kotlin.Short.milliliter
    get() = Milliliter(this)


val kotlin.Int.milliliter
    get() = Milliliter(this)


val kotlin.Long.milliliter
    get() = Milliliter(this)


val kotlin.Float.milliliter
    get() = Milliliter(this)


val kotlin.Double.milliliter
    get() = Milliliter(this)


data class PintMetric(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "pint-metric"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumePintMetric
    override fun toCubicMeter() = CubicMeter(value * (0.0005) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = PintMetric(0.0)
        fun fromCubicMeter(value: CubicMeter) = PintMetric((value.value - 0) / (0.0005))
    }

    operator fun plus(other: PintMetric): PintMetric = PintMetric(this.value + other.value)
    operator fun minus(other: PintMetric): PintMetric = PintMetric(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): PintMetric = PintMetric(this.value.times(other))
    override operator fun div(other: kotlin.Byte): PintMetric = PintMetric(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): PintMetric = PintMetric(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): PintMetric = PintMetric(this.value.times(other))
    override operator fun div(other: kotlin.Short): PintMetric = PintMetric(this.value.div(other))
    override operator fun rem(other: kotlin.Short): PintMetric = PintMetric(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): PintMetric = PintMetric(this.value.times(other))
    override operator fun div(other: kotlin.Int): PintMetric = PintMetric(this.value.div(other))
    override operator fun rem(other: kotlin.Int): PintMetric = PintMetric(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): PintMetric = PintMetric(this.value.times(other))
    override operator fun div(other: kotlin.Long): PintMetric = PintMetric(this.value.div(other))
    override operator fun rem(other: kotlin.Long): PintMetric = PintMetric(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): PintMetric = PintMetric(this.value.times(other))
    override operator fun div(other: kotlin.Float): PintMetric = PintMetric(this.value.div(other))
    override operator fun rem(other: kotlin.Float): PintMetric = PintMetric(this.value.rem(other))


    override operator fun times(other: kotlin.Double): PintMetric = PintMetric(this.value.times(other))
    override operator fun div(other: kotlin.Double): PintMetric = PintMetric(this.value.div(other))
    override operator fun rem(other: kotlin.Double): PintMetric = PintMetric(this.value.rem(other))


    override operator fun unaryMinus(): PintMetric = PintMetric(-value)
    override operator fun unaryPlus(): PintMetric = this
    override operator fun inc(): PintMetric = PintMetric(this.value + 1)
    override operator fun dec(): PintMetric = PintMetric(this.value - 1)

    override fun round(): PintMetric = PintMetric(round(this.value))
    override fun truncate(): PintMetric = PintMetric(truncate(this.value))
    override fun floor(): PintMetric = PintMetric(floor(this.value))
    override fun ceil(): PintMetric = PintMetric(ceil(this.value))
    override fun abs(): PintMetric = PintMetric(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is PintMetric -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.pintMetric
    get() = PintMetric(this)


val kotlin.Short.pintMetric
    get() = PintMetric(this)


val kotlin.Int.pintMetric
    get() = PintMetric(this)


val kotlin.Long.pintMetric
    get() = PintMetric(this)


val kotlin.Float.pintMetric
    get() = PintMetric(this)


val kotlin.Double.pintMetric
    get() = PintMetric(this)


data class CupMetric(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "cup-metric"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeCupMetric
    override fun toCubicMeter() = CubicMeter(value * (0.00025) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = CupMetric(0.0)
        fun fromCubicMeter(value: CubicMeter) = CupMetric((value.value - 0) / (0.00025))
    }

    operator fun plus(other: CupMetric): CupMetric = CupMetric(this.value + other.value)
    operator fun minus(other: CupMetric): CupMetric = CupMetric(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): CupMetric = CupMetric(this.value.times(other))
    override operator fun div(other: kotlin.Byte): CupMetric = CupMetric(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): CupMetric = CupMetric(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): CupMetric = CupMetric(this.value.times(other))
    override operator fun div(other: kotlin.Short): CupMetric = CupMetric(this.value.div(other))
    override operator fun rem(other: kotlin.Short): CupMetric = CupMetric(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): CupMetric = CupMetric(this.value.times(other))
    override operator fun div(other: kotlin.Int): CupMetric = CupMetric(this.value.div(other))
    override operator fun rem(other: kotlin.Int): CupMetric = CupMetric(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): CupMetric = CupMetric(this.value.times(other))
    override operator fun div(other: kotlin.Long): CupMetric = CupMetric(this.value.div(other))
    override operator fun rem(other: kotlin.Long): CupMetric = CupMetric(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): CupMetric = CupMetric(this.value.times(other))
    override operator fun div(other: kotlin.Float): CupMetric = CupMetric(this.value.div(other))
    override operator fun rem(other: kotlin.Float): CupMetric = CupMetric(this.value.rem(other))


    override operator fun times(other: kotlin.Double): CupMetric = CupMetric(this.value.times(other))
    override operator fun div(other: kotlin.Double): CupMetric = CupMetric(this.value.div(other))
    override operator fun rem(other: kotlin.Double): CupMetric = CupMetric(this.value.rem(other))


    override operator fun unaryMinus(): CupMetric = CupMetric(-value)
    override operator fun unaryPlus(): CupMetric = this
    override operator fun inc(): CupMetric = CupMetric(this.value + 1)
    override operator fun dec(): CupMetric = CupMetric(this.value - 1)

    override fun round(): CupMetric = CupMetric(round(this.value))
    override fun truncate(): CupMetric = CupMetric(truncate(this.value))
    override fun floor(): CupMetric = CupMetric(floor(this.value))
    override fun ceil(): CupMetric = CupMetric(ceil(this.value))
    override fun abs(): CupMetric = CupMetric(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is CupMetric -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.cupMetric
    get() = CupMetric(this)


val kotlin.Short.cupMetric
    get() = CupMetric(this)


val kotlin.Int.cupMetric
    get() = CupMetric(this)


val kotlin.Long.cupMetric
    get() = CupMetric(this)


val kotlin.Float.cupMetric
    get() = CupMetric(this)


val kotlin.Double.cupMetric
    get() = CupMetric(this)


data class AcreFoot(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "acre-foot"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeAcreFoot
    override fun toCubicMeter() = CubicMeter(value * ((4046.8564224) * (0.3048)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = AcreFoot(0.0)
        fun fromCubicMeter(value: CubicMeter) = AcreFoot((value.value - 0) / ((4046.8564224) * (0.3048)))
    }

    operator fun plus(other: AcreFoot): AcreFoot = AcreFoot(this.value + other.value)
    operator fun minus(other: AcreFoot): AcreFoot = AcreFoot(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): AcreFoot = AcreFoot(this.value.times(other))
    override operator fun div(other: kotlin.Byte): AcreFoot = AcreFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): AcreFoot = AcreFoot(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): AcreFoot = AcreFoot(this.value.times(other))
    override operator fun div(other: kotlin.Short): AcreFoot = AcreFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Short): AcreFoot = AcreFoot(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): AcreFoot = AcreFoot(this.value.times(other))
    override operator fun div(other: kotlin.Int): AcreFoot = AcreFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Int): AcreFoot = AcreFoot(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): AcreFoot = AcreFoot(this.value.times(other))
    override operator fun div(other: kotlin.Long): AcreFoot = AcreFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Long): AcreFoot = AcreFoot(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): AcreFoot = AcreFoot(this.value.times(other))
    override operator fun div(other: kotlin.Float): AcreFoot = AcreFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Float): AcreFoot = AcreFoot(this.value.rem(other))


    override operator fun times(other: kotlin.Double): AcreFoot = AcreFoot(this.value.times(other))
    override operator fun div(other: kotlin.Double): AcreFoot = AcreFoot(this.value.div(other))
    override operator fun rem(other: kotlin.Double): AcreFoot = AcreFoot(this.value.rem(other))


    override operator fun unaryMinus(): AcreFoot = AcreFoot(-value)
    override operator fun unaryPlus(): AcreFoot = this
    override operator fun inc(): AcreFoot = AcreFoot(this.value + 1)
    override operator fun dec(): AcreFoot = AcreFoot(this.value - 1)

    override fun round(): AcreFoot = AcreFoot(round(this.value))
    override fun truncate(): AcreFoot = AcreFoot(truncate(this.value))
    override fun floor(): AcreFoot = AcreFoot(floor(this.value))
    override fun ceil(): AcreFoot = AcreFoot(ceil(this.value))
    override fun abs(): AcreFoot = AcreFoot(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is AcreFoot -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.acreFoot
    get() = AcreFoot(this)


val kotlin.Short.acreFoot
    get() = AcreFoot(this)


val kotlin.Int.acreFoot
    get() = AcreFoot(this)


val kotlin.Long.acreFoot
    get() = AcreFoot(this)


val kotlin.Float.acreFoot
    get() = AcreFoot(this)


val kotlin.Double.acreFoot
    get() = AcreFoot(this)


data class Bushel(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "bushel"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeBushel
    override fun toCubicMeter() = CubicMeter(value * (730.7173589804238) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Bushel(0.0)
        fun fromCubicMeter(value: CubicMeter) = Bushel((value.value - 0) / (730.7173589804238))
    }

    operator fun plus(other: Bushel): Bushel = Bushel(this.value + other.value)
    operator fun minus(other: Bushel): Bushel = Bushel(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Bushel = Bushel(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Bushel = Bushel(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Bushel = Bushel(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Bushel = Bushel(this.value.times(other))
    override operator fun div(other: kotlin.Short): Bushel = Bushel(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Bushel = Bushel(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Bushel = Bushel(this.value.times(other))
    override operator fun div(other: kotlin.Int): Bushel = Bushel(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Bushel = Bushel(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Bushel = Bushel(this.value.times(other))
    override operator fun div(other: kotlin.Long): Bushel = Bushel(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Bushel = Bushel(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Bushel = Bushel(this.value.times(other))
    override operator fun div(other: kotlin.Float): Bushel = Bushel(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Bushel = Bushel(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Bushel = Bushel(this.value.times(other))
    override operator fun div(other: kotlin.Double): Bushel = Bushel(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Bushel = Bushel(this.value.rem(other))


    override operator fun unaryMinus(): Bushel = Bushel(-value)
    override operator fun unaryPlus(): Bushel = this
    override operator fun inc(): Bushel = Bushel(this.value + 1)
    override operator fun dec(): Bushel = Bushel(this.value - 1)

    override fun round(): Bushel = Bushel(round(this.value))
    override fun truncate(): Bushel = Bushel(truncate(this.value))
    override fun floor(): Bushel = Bushel(floor(this.value))
    override fun ceil(): Bushel = Bushel(ceil(this.value))
    override fun abs(): Bushel = Bushel(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Bushel -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.bushel
    get() = Bushel(this)


val kotlin.Short.bushel
    get() = Bushel(this)


val kotlin.Int.bushel
    get() = Bushel(this)


val kotlin.Long.bushel
    get() = Bushel(this)


val kotlin.Float.bushel
    get() = Bushel(this)


val kotlin.Double.bushel
    get() = Bushel(this)


data class Gallon(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "gallon"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeGallon
    override fun toCubicMeter() = CubicMeter(value * (78.49429875302401) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Gallon(0.0)
        fun fromCubicMeter(value: CubicMeter) = Gallon((value.value - 0) / (78.49429875302401))
    }

    operator fun plus(other: Gallon): Gallon = Gallon(this.value + other.value)
    operator fun minus(other: Gallon): Gallon = Gallon(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Gallon = Gallon(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Gallon = Gallon(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Gallon = Gallon(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Gallon = Gallon(this.value.times(other))
    override operator fun div(other: kotlin.Short): Gallon = Gallon(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Gallon = Gallon(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Gallon = Gallon(this.value.times(other))
    override operator fun div(other: kotlin.Int): Gallon = Gallon(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Gallon = Gallon(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Gallon = Gallon(this.value.times(other))
    override operator fun div(other: kotlin.Long): Gallon = Gallon(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Gallon = Gallon(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Gallon = Gallon(this.value.times(other))
    override operator fun div(other: kotlin.Float): Gallon = Gallon(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Gallon = Gallon(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Gallon = Gallon(this.value.times(other))
    override operator fun div(other: kotlin.Double): Gallon = Gallon(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Gallon = Gallon(this.value.rem(other))


    override operator fun unaryMinus(): Gallon = Gallon(-value)
    override operator fun unaryPlus(): Gallon = this
    override operator fun inc(): Gallon = Gallon(this.value + 1)
    override operator fun dec(): Gallon = Gallon(this.value - 1)

    override fun round(): Gallon = Gallon(round(this.value))
    override fun truncate(): Gallon = Gallon(truncate(this.value))
    override fun floor(): Gallon = Gallon(floor(this.value))
    override fun ceil(): Gallon = Gallon(ceil(this.value))
    override fun abs(): Gallon = Gallon(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Gallon -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.gallon
    get() = Gallon(this)


val kotlin.Short.gallon
    get() = Gallon(this)


val kotlin.Int.gallon
    get() = Gallon(this)


val kotlin.Long.gallon
    get() = Gallon(this)


val kotlin.Float.gallon
    get() = Gallon(this)


val kotlin.Double.gallon
    get() = Gallon(this)


data class GallonImperial(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "gallon-imperial"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeGallonImperial
    override fun toCubicMeter() = CubicMeter(value * (0.00454609) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = GallonImperial(0.0)
        fun fromCubicMeter(value: CubicMeter) = GallonImperial((value.value - 0) / (0.00454609))
    }

    operator fun plus(other: GallonImperial): GallonImperial = GallonImperial(this.value + other.value)
    operator fun minus(other: GallonImperial): GallonImperial = GallonImperial(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): GallonImperial = GallonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Byte): GallonImperial = GallonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): GallonImperial = GallonImperial(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): GallonImperial = GallonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Short): GallonImperial = GallonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Short): GallonImperial = GallonImperial(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): GallonImperial = GallonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Int): GallonImperial = GallonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Int): GallonImperial = GallonImperial(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): GallonImperial = GallonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Long): GallonImperial = GallonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Long): GallonImperial = GallonImperial(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): GallonImperial = GallonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Float): GallonImperial = GallonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Float): GallonImperial = GallonImperial(this.value.rem(other))


    override operator fun times(other: kotlin.Double): GallonImperial = GallonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Double): GallonImperial = GallonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Double): GallonImperial = GallonImperial(this.value.rem(other))


    override operator fun unaryMinus(): GallonImperial = GallonImperial(-value)
    override operator fun unaryPlus(): GallonImperial = this
    override operator fun inc(): GallonImperial = GallonImperial(this.value + 1)
    override operator fun dec(): GallonImperial = GallonImperial(this.value - 1)

    override fun round(): GallonImperial = GallonImperial(round(this.value))
    override fun truncate(): GallonImperial = GallonImperial(truncate(this.value))
    override fun floor(): GallonImperial = GallonImperial(floor(this.value))
    override fun ceil(): GallonImperial = GallonImperial(ceil(this.value))
    override fun abs(): GallonImperial = GallonImperial(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is GallonImperial -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.gallonImperial
    get() = GallonImperial(this)


val kotlin.Short.gallonImperial
    get() = GallonImperial(this)


val kotlin.Int.gallonImperial
    get() = GallonImperial(this)


val kotlin.Long.gallonImperial
    get() = GallonImperial(this)


val kotlin.Float.gallonImperial
    get() = GallonImperial(this)


val kotlin.Double.gallonImperial
    get() = GallonImperial(this)


data class Quart(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "quart"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeQuart
    override fun toCubicMeter() = CubicMeter(value * (19.623574688256003) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Quart(0.0)
        fun fromCubicMeter(value: CubicMeter) = Quart((value.value - 0) / (19.623574688256003))
    }

    operator fun plus(other: Quart): Quart = Quart(this.value + other.value)
    operator fun minus(other: Quart): Quart = Quart(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Quart = Quart(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Quart = Quart(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Quart = Quart(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Quart = Quart(this.value.times(other))
    override operator fun div(other: kotlin.Short): Quart = Quart(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Quart = Quart(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Quart = Quart(this.value.times(other))
    override operator fun div(other: kotlin.Int): Quart = Quart(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Quart = Quart(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Quart = Quart(this.value.times(other))
    override operator fun div(other: kotlin.Long): Quart = Quart(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Quart = Quart(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Quart = Quart(this.value.times(other))
    override operator fun div(other: kotlin.Float): Quart = Quart(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Quart = Quart(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Quart = Quart(this.value.times(other))
    override operator fun div(other: kotlin.Double): Quart = Quart(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Quart = Quart(this.value.rem(other))


    override operator fun unaryMinus(): Quart = Quart(-value)
    override operator fun unaryPlus(): Quart = this
    override operator fun inc(): Quart = Quart(this.value + 1)
    override operator fun dec(): Quart = Quart(this.value - 1)

    override fun round(): Quart = Quart(round(this.value))
    override fun truncate(): Quart = Quart(truncate(this.value))
    override fun floor(): Quart = Quart(floor(this.value))
    override fun ceil(): Quart = Quart(ceil(this.value))
    override fun abs(): Quart = Quart(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Quart -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.quart
    get() = Quart(this)


val kotlin.Short.quart
    get() = Quart(this)


val kotlin.Int.quart
    get() = Quart(this)


val kotlin.Long.quart
    get() = Quart(this)


val kotlin.Float.quart
    get() = Quart(this)


val kotlin.Double.quart
    get() = Quart(this)


data class Pint(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "pint"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumePint
    override fun toCubicMeter() = CubicMeter(value * (9.811787344128001) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Pint(0.0)
        fun fromCubicMeter(value: CubicMeter) = Pint((value.value - 0) / (9.811787344128001))
    }

    operator fun plus(other: Pint): Pint = Pint(this.value + other.value)
    operator fun minus(other: Pint): Pint = Pint(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Pint = Pint(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Pint = Pint(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Pint = Pint(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Pint = Pint(this.value.times(other))
    override operator fun div(other: kotlin.Short): Pint = Pint(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Pint = Pint(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Pint = Pint(this.value.times(other))
    override operator fun div(other: kotlin.Int): Pint = Pint(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Pint = Pint(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Pint = Pint(this.value.times(other))
    override operator fun div(other: kotlin.Long): Pint = Pint(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Pint = Pint(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Pint = Pint(this.value.times(other))
    override operator fun div(other: kotlin.Float): Pint = Pint(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Pint = Pint(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Pint = Pint(this.value.times(other))
    override operator fun div(other: kotlin.Double): Pint = Pint(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Pint = Pint(this.value.rem(other))


    override operator fun unaryMinus(): Pint = Pint(-value)
    override operator fun unaryPlus(): Pint = this
    override operator fun inc(): Pint = Pint(this.value + 1)
    override operator fun dec(): Pint = Pint(this.value - 1)

    override fun round(): Pint = Pint(round(this.value))
    override fun truncate(): Pint = Pint(truncate(this.value))
    override fun floor(): Pint = Pint(floor(this.value))
    override fun ceil(): Pint = Pint(ceil(this.value))
    override fun abs(): Pint = Pint(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Pint -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.pint
    get() = Pint(this)


val kotlin.Short.pint
    get() = Pint(this)


val kotlin.Int.pint
    get() = Pint(this)


val kotlin.Long.pint
    get() = Pint(this)


val kotlin.Float.pint
    get() = Pint(this)


val kotlin.Double.pint
    get() = Pint(this)


data class Cup(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "cup"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeCup
    override fun toCubicMeter() = CubicMeter(value * (4.905893672064001) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Cup(0.0)
        fun fromCubicMeter(value: CubicMeter) = Cup((value.value - 0) / (4.905893672064001))
    }

    operator fun plus(other: Cup): Cup = Cup(this.value + other.value)
    operator fun minus(other: Cup): Cup = Cup(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Cup = Cup(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Cup = Cup(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Cup = Cup(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Cup = Cup(this.value.times(other))
    override operator fun div(other: kotlin.Short): Cup = Cup(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Cup = Cup(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Cup = Cup(this.value.times(other))
    override operator fun div(other: kotlin.Int): Cup = Cup(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Cup = Cup(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Cup = Cup(this.value.times(other))
    override operator fun div(other: kotlin.Long): Cup = Cup(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Cup = Cup(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Cup = Cup(this.value.times(other))
    override operator fun div(other: kotlin.Float): Cup = Cup(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Cup = Cup(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Cup = Cup(this.value.times(other))
    override operator fun div(other: kotlin.Double): Cup = Cup(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Cup = Cup(this.value.rem(other))


    override operator fun unaryMinus(): Cup = Cup(-value)
    override operator fun unaryPlus(): Cup = this
    override operator fun inc(): Cup = Cup(this.value + 1)
    override operator fun dec(): Cup = Cup(this.value - 1)

    override fun round(): Cup = Cup(round(this.value))
    override fun truncate(): Cup = Cup(truncate(this.value))
    override fun floor(): Cup = Cup(floor(this.value))
    override fun ceil(): Cup = Cup(ceil(this.value))
    override fun abs(): Cup = Cup(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Cup -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.cup
    get() = Cup(this)


val kotlin.Short.cup
    get() = Cup(this)


val kotlin.Int.cup
    get() = Cup(this)


val kotlin.Long.cup
    get() = Cup(this)


val kotlin.Float.cup
    get() = Cup(this)


val kotlin.Double.cup
    get() = Cup(this)


data class FluidOunce(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "fluid-ounce"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeFluidOunce
    override fun toCubicMeter() = CubicMeter(value * (0.6132367090080001) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = FluidOunce(0.0)
        fun fromCubicMeter(value: CubicMeter) = FluidOunce((value.value - 0) / (0.6132367090080001))
    }

    operator fun plus(other: FluidOunce): FluidOunce = FluidOunce(this.value + other.value)
    operator fun minus(other: FluidOunce): FluidOunce = FluidOunce(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): FluidOunce = FluidOunce(this.value.times(other))
    override operator fun div(other: kotlin.Byte): FluidOunce = FluidOunce(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): FluidOunce = FluidOunce(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): FluidOunce = FluidOunce(this.value.times(other))
    override operator fun div(other: kotlin.Short): FluidOunce = FluidOunce(this.value.div(other))
    override operator fun rem(other: kotlin.Short): FluidOunce = FluidOunce(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): FluidOunce = FluidOunce(this.value.times(other))
    override operator fun div(other: kotlin.Int): FluidOunce = FluidOunce(this.value.div(other))
    override operator fun rem(other: kotlin.Int): FluidOunce = FluidOunce(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): FluidOunce = FluidOunce(this.value.times(other))
    override operator fun div(other: kotlin.Long): FluidOunce = FluidOunce(this.value.div(other))
    override operator fun rem(other: kotlin.Long): FluidOunce = FluidOunce(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): FluidOunce = FluidOunce(this.value.times(other))
    override operator fun div(other: kotlin.Float): FluidOunce = FluidOunce(this.value.div(other))
    override operator fun rem(other: kotlin.Float): FluidOunce = FluidOunce(this.value.rem(other))


    override operator fun times(other: kotlin.Double): FluidOunce = FluidOunce(this.value.times(other))
    override operator fun div(other: kotlin.Double): FluidOunce = FluidOunce(this.value.div(other))
    override operator fun rem(other: kotlin.Double): FluidOunce = FluidOunce(this.value.rem(other))


    override operator fun unaryMinus(): FluidOunce = FluidOunce(-value)
    override operator fun unaryPlus(): FluidOunce = this
    override operator fun inc(): FluidOunce = FluidOunce(this.value + 1)
    override operator fun dec(): FluidOunce = FluidOunce(this.value - 1)

    override fun round(): FluidOunce = FluidOunce(round(this.value))
    override fun truncate(): FluidOunce = FluidOunce(truncate(this.value))
    override fun floor(): FluidOunce = FluidOunce(floor(this.value))
    override fun ceil(): FluidOunce = FluidOunce(ceil(this.value))
    override fun abs(): FluidOunce = FluidOunce(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is FluidOunce -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.fluidOunce
    get() = FluidOunce(this)


val kotlin.Short.fluidOunce
    get() = FluidOunce(this)


val kotlin.Int.fluidOunce
    get() = FluidOunce(this)


val kotlin.Long.fluidOunce
    get() = FluidOunce(this)


val kotlin.Float.fluidOunce
    get() = FluidOunce(this)


val kotlin.Double.fluidOunce
    get() = FluidOunce(this)


data class FluidOunceImperial(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "fluid-ounce-imperial"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeFluidOunceImperial
    override fun toCubicMeter() = CubicMeter(value * (2.84130625e-05) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = FluidOunceImperial(0.0)
        fun fromCubicMeter(value: CubicMeter) = FluidOunceImperial((value.value - 0) / (2.84130625e-05))
    }

    operator fun plus(other: FluidOunceImperial): FluidOunceImperial = FluidOunceImperial(this.value + other.value)
    operator fun minus(other: FluidOunceImperial): FluidOunceImperial = FluidOunceImperial(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): FluidOunceImperial = FluidOunceImperial(this.value.times(other))
    override operator fun div(other: kotlin.Byte): FluidOunceImperial = FluidOunceImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): FluidOunceImperial = FluidOunceImperial(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): FluidOunceImperial = FluidOunceImperial(this.value.times(other))
    override operator fun div(other: kotlin.Short): FluidOunceImperial = FluidOunceImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Short): FluidOunceImperial = FluidOunceImperial(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): FluidOunceImperial = FluidOunceImperial(this.value.times(other))
    override operator fun div(other: kotlin.Int): FluidOunceImperial = FluidOunceImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Int): FluidOunceImperial = FluidOunceImperial(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): FluidOunceImperial = FluidOunceImperial(this.value.times(other))
    override operator fun div(other: kotlin.Long): FluidOunceImperial = FluidOunceImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Long): FluidOunceImperial = FluidOunceImperial(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): FluidOunceImperial = FluidOunceImperial(this.value.times(other))
    override operator fun div(other: kotlin.Float): FluidOunceImperial = FluidOunceImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Float): FluidOunceImperial = FluidOunceImperial(this.value.rem(other))


    override operator fun times(other: kotlin.Double): FluidOunceImperial = FluidOunceImperial(this.value.times(other))
    override operator fun div(other: kotlin.Double): FluidOunceImperial = FluidOunceImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Double): FluidOunceImperial = FluidOunceImperial(this.value.rem(other))


    override operator fun unaryMinus(): FluidOunceImperial = FluidOunceImperial(-value)
    override operator fun unaryPlus(): FluidOunceImperial = this
    override operator fun inc(): FluidOunceImperial = FluidOunceImperial(this.value + 1)
    override operator fun dec(): FluidOunceImperial = FluidOunceImperial(this.value - 1)

    override fun round(): FluidOunceImperial = FluidOunceImperial(round(this.value))
    override fun truncate(): FluidOunceImperial = FluidOunceImperial(truncate(this.value))
    override fun floor(): FluidOunceImperial = FluidOunceImperial(floor(this.value))
    override fun ceil(): FluidOunceImperial = FluidOunceImperial(ceil(this.value))
    override fun abs(): FluidOunceImperial = FluidOunceImperial(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is FluidOunceImperial -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.fluidOunceImperial
    get() = FluidOunceImperial(this)


val kotlin.Short.fluidOunceImperial
    get() = FluidOunceImperial(this)


val kotlin.Int.fluidOunceImperial
    get() = FluidOunceImperial(this)


val kotlin.Long.fluidOunceImperial
    get() = FluidOunceImperial(this)


val kotlin.Float.fluidOunceImperial
    get() = FluidOunceImperial(this)


val kotlin.Double.fluidOunceImperial
    get() = FluidOunceImperial(this)


data class Tablespoon(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "tablespoon"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeTablespoon
    override fun toCubicMeter() = CubicMeter(value * (0.30661835450400005) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Tablespoon(0.0)
        fun fromCubicMeter(value: CubicMeter) = Tablespoon((value.value - 0) / (0.30661835450400005))
    }

    operator fun plus(other: Tablespoon): Tablespoon = Tablespoon(this.value + other.value)
    operator fun minus(other: Tablespoon): Tablespoon = Tablespoon(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Tablespoon = Tablespoon(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Tablespoon = Tablespoon(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Tablespoon = Tablespoon(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Tablespoon = Tablespoon(this.value.times(other))
    override operator fun div(other: kotlin.Short): Tablespoon = Tablespoon(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Tablespoon = Tablespoon(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Tablespoon = Tablespoon(this.value.times(other))
    override operator fun div(other: kotlin.Int): Tablespoon = Tablespoon(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Tablespoon = Tablespoon(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Tablespoon = Tablespoon(this.value.times(other))
    override operator fun div(other: kotlin.Long): Tablespoon = Tablespoon(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Tablespoon = Tablespoon(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Tablespoon = Tablespoon(this.value.times(other))
    override operator fun div(other: kotlin.Float): Tablespoon = Tablespoon(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Tablespoon = Tablespoon(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Tablespoon = Tablespoon(this.value.times(other))
    override operator fun div(other: kotlin.Double): Tablespoon = Tablespoon(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Tablespoon = Tablespoon(this.value.rem(other))


    override operator fun unaryMinus(): Tablespoon = Tablespoon(-value)
    override operator fun unaryPlus(): Tablespoon = this
    override operator fun inc(): Tablespoon = Tablespoon(this.value + 1)
    override operator fun dec(): Tablespoon = Tablespoon(this.value - 1)

    override fun round(): Tablespoon = Tablespoon(round(this.value))
    override fun truncate(): Tablespoon = Tablespoon(truncate(this.value))
    override fun floor(): Tablespoon = Tablespoon(floor(this.value))
    override fun ceil(): Tablespoon = Tablespoon(ceil(this.value))
    override fun abs(): Tablespoon = Tablespoon(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Tablespoon -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.tablespoon
    get() = Tablespoon(this)


val kotlin.Short.tablespoon
    get() = Tablespoon(this)


val kotlin.Int.tablespoon
    get() = Tablespoon(this)


val kotlin.Long.tablespoon
    get() = Tablespoon(this)


val kotlin.Float.tablespoon
    get() = Tablespoon(this)


val kotlin.Double.tablespoon
    get() = Tablespoon(this)


data class Teaspoon(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "teaspoon"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeTeaspoon
    override fun toCubicMeter() = CubicMeter(value * (235.48289625907205) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Teaspoon(0.0)
        fun fromCubicMeter(value: CubicMeter) = Teaspoon((value.value - 0) / (235.48289625907205))
    }

    operator fun plus(other: Teaspoon): Teaspoon = Teaspoon(this.value + other.value)
    operator fun minus(other: Teaspoon): Teaspoon = Teaspoon(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Teaspoon = Teaspoon(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Teaspoon = Teaspoon(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Teaspoon = Teaspoon(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Teaspoon = Teaspoon(this.value.times(other))
    override operator fun div(other: kotlin.Short): Teaspoon = Teaspoon(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Teaspoon = Teaspoon(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Teaspoon = Teaspoon(this.value.times(other))
    override operator fun div(other: kotlin.Int): Teaspoon = Teaspoon(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Teaspoon = Teaspoon(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Teaspoon = Teaspoon(this.value.times(other))
    override operator fun div(other: kotlin.Long): Teaspoon = Teaspoon(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Teaspoon = Teaspoon(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Teaspoon = Teaspoon(this.value.times(other))
    override operator fun div(other: kotlin.Float): Teaspoon = Teaspoon(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Teaspoon = Teaspoon(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Teaspoon = Teaspoon(this.value.times(other))
    override operator fun div(other: kotlin.Double): Teaspoon = Teaspoon(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Teaspoon = Teaspoon(this.value.rem(other))


    override operator fun unaryMinus(): Teaspoon = Teaspoon(-value)
    override operator fun unaryPlus(): Teaspoon = this
    override operator fun inc(): Teaspoon = Teaspoon(this.value + 1)
    override operator fun dec(): Teaspoon = Teaspoon(this.value - 1)

    override fun round(): Teaspoon = Teaspoon(round(this.value))
    override fun truncate(): Teaspoon = Teaspoon(truncate(this.value))
    override fun floor(): Teaspoon = Teaspoon(floor(this.value))
    override fun ceil(): Teaspoon = Teaspoon(ceil(this.value))
    override fun abs(): Teaspoon = Teaspoon(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Teaspoon -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.teaspoon
    get() = Teaspoon(this)


val kotlin.Short.teaspoon
    get() = Teaspoon(this)


val kotlin.Int.teaspoon
    get() = Teaspoon(this)


val kotlin.Long.teaspoon
    get() = Teaspoon(this)


val kotlin.Float.teaspoon
    get() = Teaspoon(this)


val kotlin.Double.teaspoon
    get() = Teaspoon(this)


data class Barrel(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "barrel"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeBarrel
    override fun toCubicMeter() = CubicMeter(value * (3296.7605476270087) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Barrel(0.0)
        fun fromCubicMeter(value: CubicMeter) = Barrel((value.value - 0) / (3296.7605476270087))
    }

    operator fun plus(other: Barrel): Barrel = Barrel(this.value + other.value)
    operator fun minus(other: Barrel): Barrel = Barrel(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Barrel = Barrel(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Barrel = Barrel(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Barrel = Barrel(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Barrel = Barrel(this.value.times(other))
    override operator fun div(other: kotlin.Short): Barrel = Barrel(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Barrel = Barrel(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Barrel = Barrel(this.value.times(other))
    override operator fun div(other: kotlin.Int): Barrel = Barrel(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Barrel = Barrel(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Barrel = Barrel(this.value.times(other))
    override operator fun div(other: kotlin.Long): Barrel = Barrel(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Barrel = Barrel(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Barrel = Barrel(this.value.times(other))
    override operator fun div(other: kotlin.Float): Barrel = Barrel(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Barrel = Barrel(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Barrel = Barrel(this.value.times(other))
    override operator fun div(other: kotlin.Double): Barrel = Barrel(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Barrel = Barrel(this.value.rem(other))


    override operator fun unaryMinus(): Barrel = Barrel(-value)
    override operator fun unaryPlus(): Barrel = this
    override operator fun inc(): Barrel = Barrel(this.value + 1)
    override operator fun dec(): Barrel = Barrel(this.value - 1)

    override fun round(): Barrel = Barrel(round(this.value))
    override fun truncate(): Barrel = Barrel(truncate(this.value))
    override fun floor(): Barrel = Barrel(floor(this.value))
    override fun ceil(): Barrel = Barrel(ceil(this.value))
    override fun abs(): Barrel = Barrel(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Barrel -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.barrel
    get() = Barrel(this)


val kotlin.Short.barrel
    get() = Barrel(this)


val kotlin.Int.barrel
    get() = Barrel(this)


val kotlin.Long.barrel
    get() = Barrel(this)


val kotlin.Float.barrel
    get() = Barrel(this)


val kotlin.Double.barrel
    get() = Barrel(this)


data class DessertSpoon(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "dessert-spoon"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeDessertSpoon
    override fun toCubicMeter() = CubicMeter(value * (627.9543900241921) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = DessertSpoon(0.0)
        fun fromCubicMeter(value: CubicMeter) = DessertSpoon((value.value - 0) / (627.9543900241921))
    }

    operator fun plus(other: DessertSpoon): DessertSpoon = DessertSpoon(this.value + other.value)
    operator fun minus(other: DessertSpoon): DessertSpoon = DessertSpoon(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): DessertSpoon = DessertSpoon(this.value.times(other))
    override operator fun div(other: kotlin.Byte): DessertSpoon = DessertSpoon(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): DessertSpoon = DessertSpoon(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): DessertSpoon = DessertSpoon(this.value.times(other))
    override operator fun div(other: kotlin.Short): DessertSpoon = DessertSpoon(this.value.div(other))
    override operator fun rem(other: kotlin.Short): DessertSpoon = DessertSpoon(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): DessertSpoon = DessertSpoon(this.value.times(other))
    override operator fun div(other: kotlin.Int): DessertSpoon = DessertSpoon(this.value.div(other))
    override operator fun rem(other: kotlin.Int): DessertSpoon = DessertSpoon(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): DessertSpoon = DessertSpoon(this.value.times(other))
    override operator fun div(other: kotlin.Long): DessertSpoon = DessertSpoon(this.value.div(other))
    override operator fun rem(other: kotlin.Long): DessertSpoon = DessertSpoon(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): DessertSpoon = DessertSpoon(this.value.times(other))
    override operator fun div(other: kotlin.Float): DessertSpoon = DessertSpoon(this.value.div(other))
    override operator fun rem(other: kotlin.Float): DessertSpoon = DessertSpoon(this.value.rem(other))


    override operator fun times(other: kotlin.Double): DessertSpoon = DessertSpoon(this.value.times(other))
    override operator fun div(other: kotlin.Double): DessertSpoon = DessertSpoon(this.value.div(other))
    override operator fun rem(other: kotlin.Double): DessertSpoon = DessertSpoon(this.value.rem(other))


    override operator fun unaryMinus(): DessertSpoon = DessertSpoon(-value)
    override operator fun unaryPlus(): DessertSpoon = this
    override operator fun inc(): DessertSpoon = DessertSpoon(this.value + 1)
    override operator fun dec(): DessertSpoon = DessertSpoon(this.value - 1)

    override fun round(): DessertSpoon = DessertSpoon(round(this.value))
    override fun truncate(): DessertSpoon = DessertSpoon(truncate(this.value))
    override fun floor(): DessertSpoon = DessertSpoon(floor(this.value))
    override fun ceil(): DessertSpoon = DessertSpoon(ceil(this.value))
    override fun abs(): DessertSpoon = DessertSpoon(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is DessertSpoon -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.dessertSpoon
    get() = DessertSpoon(this)


val kotlin.Short.dessertSpoon
    get() = DessertSpoon(this)


val kotlin.Int.dessertSpoon
    get() = DessertSpoon(this)


val kotlin.Long.dessertSpoon
    get() = DessertSpoon(this)


val kotlin.Float.dessertSpoon
    get() = DessertSpoon(this)


val kotlin.Double.dessertSpoon
    get() = DessertSpoon(this)


data class DessertSpoonImperial(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "dessert-spoon-imperial"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeDessertSpoonImperial
    override fun toCubicMeter() = CubicMeter(value * (0.03636872) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = DessertSpoonImperial(0.0)
        fun fromCubicMeter(value: CubicMeter) = DessertSpoonImperial((value.value - 0) / (0.03636872))
    }

    operator fun plus(other: DessertSpoonImperial): DessertSpoonImperial = DessertSpoonImperial(this.value + other.value)
    operator fun minus(other: DessertSpoonImperial): DessertSpoonImperial = DessertSpoonImperial(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): DessertSpoonImperial = DessertSpoonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Byte): DessertSpoonImperial = DessertSpoonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): DessertSpoonImperial = DessertSpoonImperial(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): DessertSpoonImperial = DessertSpoonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Short): DessertSpoonImperial = DessertSpoonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Short): DessertSpoonImperial = DessertSpoonImperial(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): DessertSpoonImperial = DessertSpoonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Int): DessertSpoonImperial = DessertSpoonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Int): DessertSpoonImperial = DessertSpoonImperial(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): DessertSpoonImperial = DessertSpoonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Long): DessertSpoonImperial = DessertSpoonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Long): DessertSpoonImperial = DessertSpoonImperial(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): DessertSpoonImperial = DessertSpoonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Float): DessertSpoonImperial = DessertSpoonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Float): DessertSpoonImperial = DessertSpoonImperial(this.value.rem(other))


    override operator fun times(other: kotlin.Double): DessertSpoonImperial = DessertSpoonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Double): DessertSpoonImperial = DessertSpoonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Double): DessertSpoonImperial = DessertSpoonImperial(this.value.rem(other))


    override operator fun unaryMinus(): DessertSpoonImperial = DessertSpoonImperial(-value)
    override operator fun unaryPlus(): DessertSpoonImperial = this
    override operator fun inc(): DessertSpoonImperial = DessertSpoonImperial(this.value + 1)
    override operator fun dec(): DessertSpoonImperial = DessertSpoonImperial(this.value - 1)

    override fun round(): DessertSpoonImperial = DessertSpoonImperial(round(this.value))
    override fun truncate(): DessertSpoonImperial = DessertSpoonImperial(truncate(this.value))
    override fun floor(): DessertSpoonImperial = DessertSpoonImperial(floor(this.value))
    override fun ceil(): DessertSpoonImperial = DessertSpoonImperial(ceil(this.value))
    override fun abs(): DessertSpoonImperial = DessertSpoonImperial(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is DessertSpoonImperial -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.dessertSpoonImperial
    get() = DessertSpoonImperial(this)


val kotlin.Short.dessertSpoonImperial
    get() = DessertSpoonImperial(this)


val kotlin.Int.dessertSpoonImperial
    get() = DessertSpoonImperial(this)


val kotlin.Long.dessertSpoonImperial
    get() = DessertSpoonImperial(this)


val kotlin.Float.dessertSpoonImperial
    get() = DessertSpoonImperial(this)


val kotlin.Double.dessertSpoonImperial
    get() = DessertSpoonImperial(this)


data class Drop(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "drop"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeDrop
    override fun toCubicMeter() = CubicMeter(value * (353.2243443886081) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Drop(0.0)
        fun fromCubicMeter(value: CubicMeter) = Drop((value.value - 0) / (353.2243443886081))
    }

    operator fun plus(other: Drop): Drop = Drop(this.value + other.value)
    operator fun minus(other: Drop): Drop = Drop(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Drop = Drop(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Drop = Drop(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Drop = Drop(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Drop = Drop(this.value.times(other))
    override operator fun div(other: kotlin.Short): Drop = Drop(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Drop = Drop(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Drop = Drop(this.value.times(other))
    override operator fun div(other: kotlin.Int): Drop = Drop(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Drop = Drop(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Drop = Drop(this.value.times(other))
    override operator fun div(other: kotlin.Long): Drop = Drop(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Drop = Drop(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Drop = Drop(this.value.times(other))
    override operator fun div(other: kotlin.Float): Drop = Drop(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Drop = Drop(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Drop = Drop(this.value.times(other))
    override operator fun div(other: kotlin.Double): Drop = Drop(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Drop = Drop(this.value.rem(other))


    override operator fun unaryMinus(): Drop = Drop(-value)
    override operator fun unaryPlus(): Drop = this
    override operator fun inc(): Drop = Drop(this.value + 1)
    override operator fun dec(): Drop = Drop(this.value - 1)

    override fun round(): Drop = Drop(round(this.value))
    override fun truncate(): Drop = Drop(truncate(this.value))
    override fun floor(): Drop = Drop(floor(this.value))
    override fun ceil(): Drop = Drop(ceil(this.value))
    override fun abs(): Drop = Drop(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Drop -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.drop
    get() = Drop(this)


val kotlin.Short.drop
    get() = Drop(this)


val kotlin.Int.drop
    get() = Drop(this)


val kotlin.Long.drop
    get() = Drop(this)


val kotlin.Float.drop
    get() = Drop(this)


val kotlin.Double.drop
    get() = Drop(this)


data class Dram(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "dram"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeDram
    override fun toCubicMeter() = CubicMeter(value * (4.905893672064001) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Dram(0.0)
        fun fromCubicMeter(value: CubicMeter) = Dram((value.value - 0) / (4.905893672064001))
    }

    operator fun plus(other: Dram): Dram = Dram(this.value + other.value)
    operator fun minus(other: Dram): Dram = Dram(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Dram = Dram(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Dram = Dram(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Dram = Dram(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Dram = Dram(this.value.times(other))
    override operator fun div(other: kotlin.Short): Dram = Dram(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Dram = Dram(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Dram = Dram(this.value.times(other))
    override operator fun div(other: kotlin.Int): Dram = Dram(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Dram = Dram(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Dram = Dram(this.value.times(other))
    override operator fun div(other: kotlin.Long): Dram = Dram(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Dram = Dram(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Dram = Dram(this.value.times(other))
    override operator fun div(other: kotlin.Float): Dram = Dram(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Dram = Dram(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Dram = Dram(this.value.times(other))
    override operator fun div(other: kotlin.Double): Dram = Dram(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Dram = Dram(this.value.rem(other))


    override operator fun unaryMinus(): Dram = Dram(-value)
    override operator fun unaryPlus(): Dram = this
    override operator fun inc(): Dram = Dram(this.value + 1)
    override operator fun dec(): Dram = Dram(this.value - 1)

    override fun round(): Dram = Dram(round(this.value))
    override fun truncate(): Dram = Dram(truncate(this.value))
    override fun floor(): Dram = Dram(floor(this.value))
    override fun ceil(): Dram = Dram(ceil(this.value))
    override fun abs(): Dram = Dram(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Dram -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.dram
    get() = Dram(this)


val kotlin.Short.dram
    get() = Dram(this)


val kotlin.Int.dram
    get() = Dram(this)


val kotlin.Long.dram
    get() = Dram(this)


val kotlin.Float.dram
    get() = Dram(this)


val kotlin.Double.dram
    get() = Dram(this)


data class Jigger(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "jigger"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeJigger
    override fun toCubicMeter() = CubicMeter(value * (3.6794202540480008) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Jigger(0.0)
        fun fromCubicMeter(value: CubicMeter) = Jigger((value.value - 0) / (3.6794202540480008))
    }

    operator fun plus(other: Jigger): Jigger = Jigger(this.value + other.value)
    operator fun minus(other: Jigger): Jigger = Jigger(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Jigger = Jigger(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Jigger = Jigger(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Jigger = Jigger(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Jigger = Jigger(this.value.times(other))
    override operator fun div(other: kotlin.Short): Jigger = Jigger(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Jigger = Jigger(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Jigger = Jigger(this.value.times(other))
    override operator fun div(other: kotlin.Int): Jigger = Jigger(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Jigger = Jigger(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Jigger = Jigger(this.value.times(other))
    override operator fun div(other: kotlin.Long): Jigger = Jigger(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Jigger = Jigger(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Jigger = Jigger(this.value.times(other))
    override operator fun div(other: kotlin.Float): Jigger = Jigger(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Jigger = Jigger(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Jigger = Jigger(this.value.times(other))
    override operator fun div(other: kotlin.Double): Jigger = Jigger(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Jigger = Jigger(this.value.rem(other))


    override operator fun unaryMinus(): Jigger = Jigger(-value)
    override operator fun unaryPlus(): Jigger = this
    override operator fun inc(): Jigger = Jigger(this.value + 1)
    override operator fun dec(): Jigger = Jigger(this.value - 1)

    override fun round(): Jigger = Jigger(round(this.value))
    override fun truncate(): Jigger = Jigger(truncate(this.value))
    override fun floor(): Jigger = Jigger(floor(this.value))
    override fun ceil(): Jigger = Jigger(ceil(this.value))
    override fun abs(): Jigger = Jigger(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Jigger -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.jigger
    get() = Jigger(this)


val kotlin.Short.jigger
    get() = Jigger(this)


val kotlin.Int.jigger
    get() = Jigger(this)


val kotlin.Long.jigger
    get() = Jigger(this)


val kotlin.Float.jigger
    get() = Jigger(this)


val kotlin.Double.jigger
    get() = Jigger(this)


data class Pinch(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "pinch"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumePinch
    override fun toCubicMeter() = CubicMeter(value * (78.49429875302401) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Pinch(0.0)
        fun fromCubicMeter(value: CubicMeter) = Pinch((value.value - 0) / (78.49429875302401))
    }

    operator fun plus(other: Pinch): Pinch = Pinch(this.value + other.value)
    operator fun minus(other: Pinch): Pinch = Pinch(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Pinch = Pinch(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Pinch = Pinch(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Pinch = Pinch(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Pinch = Pinch(this.value.times(other))
    override operator fun div(other: kotlin.Short): Pinch = Pinch(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Pinch = Pinch(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Pinch = Pinch(this.value.times(other))
    override operator fun div(other: kotlin.Int): Pinch = Pinch(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Pinch = Pinch(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Pinch = Pinch(this.value.times(other))
    override operator fun div(other: kotlin.Long): Pinch = Pinch(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Pinch = Pinch(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Pinch = Pinch(this.value.times(other))
    override operator fun div(other: kotlin.Float): Pinch = Pinch(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Pinch = Pinch(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Pinch = Pinch(this.value.times(other))
    override operator fun div(other: kotlin.Double): Pinch = Pinch(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Pinch = Pinch(this.value.rem(other))


    override operator fun unaryMinus(): Pinch = Pinch(-value)
    override operator fun unaryPlus(): Pinch = this
    override operator fun inc(): Pinch = Pinch(this.value + 1)
    override operator fun dec(): Pinch = Pinch(this.value - 1)

    override fun round(): Pinch = Pinch(round(this.value))
    override fun truncate(): Pinch = Pinch(truncate(this.value))
    override fun floor(): Pinch = Pinch(floor(this.value))
    override fun ceil(): Pinch = Pinch(ceil(this.value))
    override fun abs(): Pinch = Pinch(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Pinch -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.pinch
    get() = Pinch(this)


val kotlin.Short.pinch
    get() = Pinch(this)


val kotlin.Int.pinch
    get() = Pinch(this)


val kotlin.Long.pinch
    get() = Pinch(this)


val kotlin.Float.pinch
    get() = Pinch(this)


val kotlin.Double.pinch
    get() = Pinch(this)


data class QuartImperial(override val value: MeasurementUnitValue) : Volume {

    override val unitName: String = "quart-imperial"

    override fun formatters(language: MeasurementUnitLanguage) = language.volumeQuartImperial
    override fun toCubicMeter() = CubicMeter(value * (0.0011365225) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = QuartImperial(0.0)
        fun fromCubicMeter(value: CubicMeter) = QuartImperial((value.value - 0) / (0.0011365225))
    }

    operator fun plus(other: QuartImperial): QuartImperial = QuartImperial(this.value + other.value)
    operator fun minus(other: QuartImperial): QuartImperial = QuartImperial(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): QuartImperial = QuartImperial(this.value.times(other))
    override operator fun div(other: kotlin.Byte): QuartImperial = QuartImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): QuartImperial = QuartImperial(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): QuartImperial = QuartImperial(this.value.times(other))
    override operator fun div(other: kotlin.Short): QuartImperial = QuartImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Short): QuartImperial = QuartImperial(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): QuartImperial = QuartImperial(this.value.times(other))
    override operator fun div(other: kotlin.Int): QuartImperial = QuartImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Int): QuartImperial = QuartImperial(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): QuartImperial = QuartImperial(this.value.times(other))
    override operator fun div(other: kotlin.Long): QuartImperial = QuartImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Long): QuartImperial = QuartImperial(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): QuartImperial = QuartImperial(this.value.times(other))
    override operator fun div(other: kotlin.Float): QuartImperial = QuartImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Float): QuartImperial = QuartImperial(this.value.rem(other))


    override operator fun times(other: kotlin.Double): QuartImperial = QuartImperial(this.value.times(other))
    override operator fun div(other: kotlin.Double): QuartImperial = QuartImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Double): QuartImperial = QuartImperial(this.value.rem(other))


    override operator fun unaryMinus(): QuartImperial = QuartImperial(-value)
    override operator fun unaryPlus(): QuartImperial = this
    override operator fun inc(): QuartImperial = QuartImperial(this.value + 1)
    override operator fun dec(): QuartImperial = QuartImperial(this.value - 1)

    override fun round(): QuartImperial = QuartImperial(round(this.value))
    override fun truncate(): QuartImperial = QuartImperial(truncate(this.value))
    override fun floor(): QuartImperial = QuartImperial(floor(this.value))
    override fun ceil(): QuartImperial = QuartImperial(ceil(this.value))
    override fun abs(): QuartImperial = QuartImperial(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is QuartImperial -> this.value == other.value
        is Volume -> this.toCubicMeter().value == other.toCubicMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toCubicMeter().value.hashCode()


}

val kotlin.Byte.quartImperial
    get() = QuartImperial(this)


val kotlin.Short.quartImperial
    get() = QuartImperial(this)


val kotlin.Int.quartImperial
    get() = QuartImperial(this)


val kotlin.Long.quartImperial
    get() = QuartImperial(this)


val kotlin.Float.quartImperial
    get() = QuartImperial(this)


val kotlin.Double.quartImperial
    get() = QuartImperial(this)
                            
