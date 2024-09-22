package info.leonenko.i18n.units.consumption

import info.leonenko.i18n.units.MeasurementUnitLanguage
import info.leonenko.i18n.units.MeasurementUnitValue
import info.leonenko.i18n.units.area.Area
import info.leonenko.i18n.units.area.SquareMeter
import info.leonenko.i18n.units.toMeasurementUnitValue
import kotlin.math.*

fun Area.toMilePerGallonImperial(): MilePerGallonImperial {
    return MilePerGallonImperial((toSquareMeter().value - 0) / ((0.00454609) / (1609.344)))
}

data class LiterPerKilometer(override val value: MeasurementUnitValue) : Area {

    override val unitName: String = "liter-per-kilometer"

    override fun formatters(language: MeasurementUnitLanguage) = language.consumptionLiterPerKilometer
    override fun toSquareMeter() = SquareMeter(value * ((0.001) / (1 * (1e3))) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = LiterPerKilometer(0.0)
        fun fromSquareMeter(value: SquareMeter) = LiterPerKilometer((value.value - 0) / ((0.001) / (1 * (1e3))))
    }

    operator fun plus(other: LiterPerKilometer): LiterPerKilometer = LiterPerKilometer(this.value + other.value)
    operator fun minus(other: LiterPerKilometer): LiterPerKilometer = LiterPerKilometer(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): LiterPerKilometer = LiterPerKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Byte): LiterPerKilometer = LiterPerKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): LiterPerKilometer = LiterPerKilometer(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): LiterPerKilometer = LiterPerKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Short): LiterPerKilometer = LiterPerKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Short): LiterPerKilometer = LiterPerKilometer(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): LiterPerKilometer = LiterPerKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Int): LiterPerKilometer = LiterPerKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Int): LiterPerKilometer = LiterPerKilometer(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): LiterPerKilometer = LiterPerKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Long): LiterPerKilometer = LiterPerKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Long): LiterPerKilometer = LiterPerKilometer(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): LiterPerKilometer = LiterPerKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Float): LiterPerKilometer = LiterPerKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Float): LiterPerKilometer = LiterPerKilometer(this.value.rem(other))


    override operator fun times(other: kotlin.Double): LiterPerKilometer = LiterPerKilometer(this.value.times(other))
    override operator fun div(other: kotlin.Double): LiterPerKilometer = LiterPerKilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Double): LiterPerKilometer = LiterPerKilometer(this.value.rem(other))


    override operator fun unaryMinus(): LiterPerKilometer = LiterPerKilometer(-value)
    override operator fun unaryPlus(): LiterPerKilometer = this
    override operator fun inc(): LiterPerKilometer = LiterPerKilometer(this.value + 1)
    override operator fun dec(): LiterPerKilometer = LiterPerKilometer(this.value - 1)

    override fun round(): LiterPerKilometer = LiterPerKilometer(round(this.value))
    override fun truncate(): LiterPerKilometer = LiterPerKilometer(truncate(this.value))
    override fun floor(): LiterPerKilometer = LiterPerKilometer(floor(this.value))
    override fun ceil(): LiterPerKilometer = LiterPerKilometer(ceil(this.value))
    override fun abs(): LiterPerKilometer = LiterPerKilometer(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is LiterPerKilometer -> this.value == other.value
        is Area -> this.toSquareMeter().value == other.toSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toSquareMeter().value.hashCode()


}

val kotlin.Byte.literPerKilometer
    get() = LiterPerKilometer(this)


val kotlin.Short.literPerKilometer
    get() = LiterPerKilometer(this)


val kotlin.Int.literPerKilometer
    get() = LiterPerKilometer(this)


val kotlin.Long.literPerKilometer
    get() = LiterPerKilometer(this)


val kotlin.Float.literPerKilometer
    get() = LiterPerKilometer(this)


val kotlin.Double.literPerKilometer
    get() = LiterPerKilometer(this)


data class LiterPer100Kilometer(override val value: MeasurementUnitValue) : Area {

    override val unitName: String = "liter-per-100-kilometer"

    override fun formatters(language: MeasurementUnitLanguage) = language.consumptionLiterPer100Kilometer
    override fun toSquareMeter() = SquareMeter(value * ((0.001) / (100000)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = LiterPer100Kilometer(0.0)
        fun fromSquareMeter(value: SquareMeter) = LiterPer100Kilometer((value.value - 0) / ((0.001) / (100000)))
    }

    operator fun plus(other: LiterPer100Kilometer): LiterPer100Kilometer = LiterPer100Kilometer(this.value + other.value)
    operator fun minus(other: LiterPer100Kilometer): LiterPer100Kilometer = LiterPer100Kilometer(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): LiterPer100Kilometer = LiterPer100Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Byte): LiterPer100Kilometer = LiterPer100Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): LiterPer100Kilometer = LiterPer100Kilometer(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): LiterPer100Kilometer = LiterPer100Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Short): LiterPer100Kilometer = LiterPer100Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Short): LiterPer100Kilometer = LiterPer100Kilometer(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): LiterPer100Kilometer = LiterPer100Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Int): LiterPer100Kilometer = LiterPer100Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Int): LiterPer100Kilometer = LiterPer100Kilometer(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): LiterPer100Kilometer = LiterPer100Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Long): LiterPer100Kilometer = LiterPer100Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Long): LiterPer100Kilometer = LiterPer100Kilometer(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): LiterPer100Kilometer = LiterPer100Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Float): LiterPer100Kilometer = LiterPer100Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Float): LiterPer100Kilometer = LiterPer100Kilometer(this.value.rem(other))


    override operator fun times(other: kotlin.Double): LiterPer100Kilometer = LiterPer100Kilometer(this.value.times(other))
    override operator fun div(other: kotlin.Double): LiterPer100Kilometer = LiterPer100Kilometer(this.value.div(other))
    override operator fun rem(other: kotlin.Double): LiterPer100Kilometer = LiterPer100Kilometer(this.value.rem(other))


    override operator fun unaryMinus(): LiterPer100Kilometer = LiterPer100Kilometer(-value)
    override operator fun unaryPlus(): LiterPer100Kilometer = this
    override operator fun inc(): LiterPer100Kilometer = LiterPer100Kilometer(this.value + 1)
    override operator fun dec(): LiterPer100Kilometer = LiterPer100Kilometer(this.value - 1)

    override fun round(): LiterPer100Kilometer = LiterPer100Kilometer(round(this.value))
    override fun truncate(): LiterPer100Kilometer = LiterPer100Kilometer(truncate(this.value))
    override fun floor(): LiterPer100Kilometer = LiterPer100Kilometer(floor(this.value))
    override fun ceil(): LiterPer100Kilometer = LiterPer100Kilometer(ceil(this.value))
    override fun abs(): LiterPer100Kilometer = LiterPer100Kilometer(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is LiterPer100Kilometer -> this.value == other.value
        is Area -> this.toSquareMeter().value == other.toSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toSquareMeter().value.hashCode()


}

val kotlin.Byte.literPer100Kilometer
    get() = LiterPer100Kilometer(this)


val kotlin.Short.literPer100Kilometer
    get() = LiterPer100Kilometer(this)


val kotlin.Int.literPer100Kilometer
    get() = LiterPer100Kilometer(this)


val kotlin.Long.literPer100Kilometer
    get() = LiterPer100Kilometer(this)


val kotlin.Float.literPer100Kilometer
    get() = LiterPer100Kilometer(this)


val kotlin.Double.literPer100Kilometer
    get() = LiterPer100Kilometer(this)


data class MilePerGallon(override val value: MeasurementUnitValue) : Area {

    override val unitName: String = "mile-per-gallon"

    override fun formatters(language: MeasurementUnitLanguage) = language.consumptionMilePerGallon
    override fun toSquareMeter() = SquareMeter(value * ((78.49429875302401) / (1609.344)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = MilePerGallon(0.0)
        fun fromSquareMeter(value: SquareMeter) = MilePerGallon((value.value - 0) / ((78.49429875302401) / (1609.344)))
    }

    operator fun plus(other: MilePerGallon): MilePerGallon = MilePerGallon(this.value + other.value)
    operator fun minus(other: MilePerGallon): MilePerGallon = MilePerGallon(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): MilePerGallon = MilePerGallon(this.value.times(other))
    override operator fun div(other: kotlin.Byte): MilePerGallon = MilePerGallon(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): MilePerGallon = MilePerGallon(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): MilePerGallon = MilePerGallon(this.value.times(other))
    override operator fun div(other: kotlin.Short): MilePerGallon = MilePerGallon(this.value.div(other))
    override operator fun rem(other: kotlin.Short): MilePerGallon = MilePerGallon(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): MilePerGallon = MilePerGallon(this.value.times(other))
    override operator fun div(other: kotlin.Int): MilePerGallon = MilePerGallon(this.value.div(other))
    override operator fun rem(other: kotlin.Int): MilePerGallon = MilePerGallon(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): MilePerGallon = MilePerGallon(this.value.times(other))
    override operator fun div(other: kotlin.Long): MilePerGallon = MilePerGallon(this.value.div(other))
    override operator fun rem(other: kotlin.Long): MilePerGallon = MilePerGallon(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): MilePerGallon = MilePerGallon(this.value.times(other))
    override operator fun div(other: kotlin.Float): MilePerGallon = MilePerGallon(this.value.div(other))
    override operator fun rem(other: kotlin.Float): MilePerGallon = MilePerGallon(this.value.rem(other))


    override operator fun times(other: kotlin.Double): MilePerGallon = MilePerGallon(this.value.times(other))
    override operator fun div(other: kotlin.Double): MilePerGallon = MilePerGallon(this.value.div(other))
    override operator fun rem(other: kotlin.Double): MilePerGallon = MilePerGallon(this.value.rem(other))


    override operator fun unaryMinus(): MilePerGallon = MilePerGallon(-value)
    override operator fun unaryPlus(): MilePerGallon = this
    override operator fun inc(): MilePerGallon = MilePerGallon(this.value + 1)
    override operator fun dec(): MilePerGallon = MilePerGallon(this.value - 1)

    override fun round(): MilePerGallon = MilePerGallon(round(this.value))
    override fun truncate(): MilePerGallon = MilePerGallon(truncate(this.value))
    override fun floor(): MilePerGallon = MilePerGallon(floor(this.value))
    override fun ceil(): MilePerGallon = MilePerGallon(ceil(this.value))
    override fun abs(): MilePerGallon = MilePerGallon(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is MilePerGallon -> this.value == other.value
        is Area -> this.toSquareMeter().value == other.toSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toSquareMeter().value.hashCode()


}

val kotlin.Byte.milePerGallon
    get() = MilePerGallon(this)


val kotlin.Short.milePerGallon
    get() = MilePerGallon(this)


val kotlin.Int.milePerGallon
    get() = MilePerGallon(this)


val kotlin.Long.milePerGallon
    get() = MilePerGallon(this)


val kotlin.Float.milePerGallon
    get() = MilePerGallon(this)


val kotlin.Double.milePerGallon
    get() = MilePerGallon(this)


data class MilePerGallonImperial(override val value: MeasurementUnitValue) : Area {

    override val unitName: String = "mile-per-gallon-imperial"

    override fun formatters(language: MeasurementUnitLanguage) = language.consumptionMilePerGallonImperial
    override fun toSquareMeter() = SquareMeter(value * ((0.00454609) / (1609.344)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = MilePerGallonImperial(0.0)
        fun fromSquareMeter(value: SquareMeter) = MilePerGallonImperial((value.value - 0) / ((0.00454609) / (1609.344)))
    }

    operator fun plus(other: MilePerGallonImperial): MilePerGallonImperial = MilePerGallonImperial(this.value + other.value)
    operator fun minus(other: MilePerGallonImperial): MilePerGallonImperial = MilePerGallonImperial(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): MilePerGallonImperial = MilePerGallonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Byte): MilePerGallonImperial = MilePerGallonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): MilePerGallonImperial = MilePerGallonImperial(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): MilePerGallonImperial = MilePerGallonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Short): MilePerGallonImperial = MilePerGallonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Short): MilePerGallonImperial = MilePerGallonImperial(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): MilePerGallonImperial = MilePerGallonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Int): MilePerGallonImperial = MilePerGallonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Int): MilePerGallonImperial = MilePerGallonImperial(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): MilePerGallonImperial = MilePerGallonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Long): MilePerGallonImperial = MilePerGallonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Long): MilePerGallonImperial = MilePerGallonImperial(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): MilePerGallonImperial = MilePerGallonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Float): MilePerGallonImperial = MilePerGallonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Float): MilePerGallonImperial = MilePerGallonImperial(this.value.rem(other))


    override operator fun times(other: kotlin.Double): MilePerGallonImperial = MilePerGallonImperial(this.value.times(other))
    override operator fun div(other: kotlin.Double): MilePerGallonImperial = MilePerGallonImperial(this.value.div(other))
    override operator fun rem(other: kotlin.Double): MilePerGallonImperial = MilePerGallonImperial(this.value.rem(other))


    override operator fun unaryMinus(): MilePerGallonImperial = MilePerGallonImperial(-value)
    override operator fun unaryPlus(): MilePerGallonImperial = this
    override operator fun inc(): MilePerGallonImperial = MilePerGallonImperial(this.value + 1)
    override operator fun dec(): MilePerGallonImperial = MilePerGallonImperial(this.value - 1)

    override fun round(): MilePerGallonImperial = MilePerGallonImperial(round(this.value))
    override fun truncate(): MilePerGallonImperial = MilePerGallonImperial(truncate(this.value))
    override fun floor(): MilePerGallonImperial = MilePerGallonImperial(floor(this.value))
    override fun ceil(): MilePerGallonImperial = MilePerGallonImperial(ceil(this.value))
    override fun abs(): MilePerGallonImperial = MilePerGallonImperial(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is MilePerGallonImperial -> this.value == other.value
        is Area -> this.toSquareMeter().value == other.toSquareMeter().value
        else -> false
    }

    override fun hashCode(): Int = this.toSquareMeter().value.hashCode()


}

val kotlin.Byte.milePerGallonImperial
    get() = MilePerGallonImperial(this)


val kotlin.Short.milePerGallonImperial
    get() = MilePerGallonImperial(this)


val kotlin.Int.milePerGallonImperial
    get() = MilePerGallonImperial(this)


val kotlin.Long.milePerGallonImperial
    get() = MilePerGallonImperial(this)


val kotlin.Float.milePerGallonImperial
    get() = MilePerGallonImperial(this)


val kotlin.Double.milePerGallonImperial
    get() = MilePerGallonImperial(this)
                            
