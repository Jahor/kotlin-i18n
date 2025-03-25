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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): LiterPerKilometer = LiterPerKilometer(this.value.times(other))
    override operator fun div(other: Byte): LiterPerKilometer = LiterPerKilometer(this.value.div(other))
    override operator fun rem(other: Byte): LiterPerKilometer = LiterPerKilometer(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): LiterPerKilometer = LiterPerKilometer(this.value.times(other))
    override operator fun div(other: Short): LiterPerKilometer = LiterPerKilometer(this.value.div(other))
    override operator fun rem(other: Short): LiterPerKilometer = LiterPerKilometer(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): LiterPerKilometer = LiterPerKilometer(this.value.times(other))
    override operator fun div(other: Int): LiterPerKilometer = LiterPerKilometer(this.value.div(other))
    override operator fun rem(other: Int): LiterPerKilometer = LiterPerKilometer(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): LiterPerKilometer = LiterPerKilometer(this.value.times(other))
    override operator fun div(other: Long): LiterPerKilometer = LiterPerKilometer(this.value.div(other))
    override operator fun rem(other: Long): LiterPerKilometer = LiterPerKilometer(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): LiterPerKilometer = LiterPerKilometer(this.value.times(other))
    override operator fun div(other: Float): LiterPerKilometer = LiterPerKilometer(this.value.div(other))
    override operator fun rem(other: Float): LiterPerKilometer = LiterPerKilometer(this.value.rem(other))


    override operator fun times(other: Double): LiterPerKilometer = LiterPerKilometer(this.value.times(other))
    override operator fun div(other: Double): LiterPerKilometer = LiterPerKilometer(this.value.div(other))
    override operator fun rem(other: Double): LiterPerKilometer = LiterPerKilometer(this.value.rem(other))


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

val Byte.literPerKilometer
    get() = LiterPerKilometer(this)


val Short.literPerKilometer
    get() = LiterPerKilometer(this)


val Int.literPerKilometer
    get() = LiterPerKilometer(this)


val Long.literPerKilometer
    get() = LiterPerKilometer(this)


val Float.literPerKilometer
    get() = LiterPerKilometer(this)


val Double.literPerKilometer
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): LiterPer100Kilometer = LiterPer100Kilometer(this.value.times(other))
    override operator fun div(other: Byte): LiterPer100Kilometer = LiterPer100Kilometer(this.value.div(other))
    override operator fun rem(other: Byte): LiterPer100Kilometer = LiterPer100Kilometer(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): LiterPer100Kilometer = LiterPer100Kilometer(this.value.times(other))
    override operator fun div(other: Short): LiterPer100Kilometer = LiterPer100Kilometer(this.value.div(other))
    override operator fun rem(other: Short): LiterPer100Kilometer = LiterPer100Kilometer(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): LiterPer100Kilometer = LiterPer100Kilometer(this.value.times(other))
    override operator fun div(other: Int): LiterPer100Kilometer = LiterPer100Kilometer(this.value.div(other))
    override operator fun rem(other: Int): LiterPer100Kilometer = LiterPer100Kilometer(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): LiterPer100Kilometer = LiterPer100Kilometer(this.value.times(other))
    override operator fun div(other: Long): LiterPer100Kilometer = LiterPer100Kilometer(this.value.div(other))
    override operator fun rem(other: Long): LiterPer100Kilometer = LiterPer100Kilometer(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): LiterPer100Kilometer = LiterPer100Kilometer(this.value.times(other))
    override operator fun div(other: Float): LiterPer100Kilometer = LiterPer100Kilometer(this.value.div(other))
    override operator fun rem(other: Float): LiterPer100Kilometer = LiterPer100Kilometer(this.value.rem(other))


    override operator fun times(other: Double): LiterPer100Kilometer = LiterPer100Kilometer(this.value.times(other))
    override operator fun div(other: Double): LiterPer100Kilometer = LiterPer100Kilometer(this.value.div(other))
    override operator fun rem(other: Double): LiterPer100Kilometer = LiterPer100Kilometer(this.value.rem(other))


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

val Byte.literPer100Kilometer
    get() = LiterPer100Kilometer(this)


val Short.literPer100Kilometer
    get() = LiterPer100Kilometer(this)


val Int.literPer100Kilometer
    get() = LiterPer100Kilometer(this)


val Long.literPer100Kilometer
    get() = LiterPer100Kilometer(this)


val Float.literPer100Kilometer
    get() = LiterPer100Kilometer(this)


val Double.literPer100Kilometer
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): MilePerGallon = MilePerGallon(this.value.times(other))
    override operator fun div(other: Byte): MilePerGallon = MilePerGallon(this.value.div(other))
    override operator fun rem(other: Byte): MilePerGallon = MilePerGallon(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): MilePerGallon = MilePerGallon(this.value.times(other))
    override operator fun div(other: Short): MilePerGallon = MilePerGallon(this.value.div(other))
    override operator fun rem(other: Short): MilePerGallon = MilePerGallon(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): MilePerGallon = MilePerGallon(this.value.times(other))
    override operator fun div(other: Int): MilePerGallon = MilePerGallon(this.value.div(other))
    override operator fun rem(other: Int): MilePerGallon = MilePerGallon(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): MilePerGallon = MilePerGallon(this.value.times(other))
    override operator fun div(other: Long): MilePerGallon = MilePerGallon(this.value.div(other))
    override operator fun rem(other: Long): MilePerGallon = MilePerGallon(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): MilePerGallon = MilePerGallon(this.value.times(other))
    override operator fun div(other: Float): MilePerGallon = MilePerGallon(this.value.div(other))
    override operator fun rem(other: Float): MilePerGallon = MilePerGallon(this.value.rem(other))


    override operator fun times(other: Double): MilePerGallon = MilePerGallon(this.value.times(other))
    override operator fun div(other: Double): MilePerGallon = MilePerGallon(this.value.div(other))
    override operator fun rem(other: Double): MilePerGallon = MilePerGallon(this.value.rem(other))


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

val Byte.milePerGallon
    get() = MilePerGallon(this)


val Short.milePerGallon
    get() = MilePerGallon(this)


val Int.milePerGallon
    get() = MilePerGallon(this)


val Long.milePerGallon
    get() = MilePerGallon(this)


val Float.milePerGallon
    get() = MilePerGallon(this)


val Double.milePerGallon
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

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): MilePerGallonImperial = MilePerGallonImperial(this.value.times(other))
    override operator fun div(other: Byte): MilePerGallonImperial = MilePerGallonImperial(this.value.div(other))
    override operator fun rem(other: Byte): MilePerGallonImperial = MilePerGallonImperial(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): MilePerGallonImperial = MilePerGallonImperial(this.value.times(other))
    override operator fun div(other: Short): MilePerGallonImperial = MilePerGallonImperial(this.value.div(other))
    override operator fun rem(other: Short): MilePerGallonImperial = MilePerGallonImperial(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): MilePerGallonImperial = MilePerGallonImperial(this.value.times(other))
    override operator fun div(other: Int): MilePerGallonImperial = MilePerGallonImperial(this.value.div(other))
    override operator fun rem(other: Int): MilePerGallonImperial = MilePerGallonImperial(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): MilePerGallonImperial = MilePerGallonImperial(this.value.times(other))
    override operator fun div(other: Long): MilePerGallonImperial = MilePerGallonImperial(this.value.div(other))
    override operator fun rem(other: Long): MilePerGallonImperial = MilePerGallonImperial(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): MilePerGallonImperial = MilePerGallonImperial(this.value.times(other))
    override operator fun div(other: Float): MilePerGallonImperial = MilePerGallonImperial(this.value.div(other))
    override operator fun rem(other: Float): MilePerGallonImperial = MilePerGallonImperial(this.value.rem(other))


    override operator fun times(other: Double): MilePerGallonImperial = MilePerGallonImperial(this.value.times(other))
    override operator fun div(other: Double): MilePerGallonImperial = MilePerGallonImperial(this.value.div(other))
    override operator fun rem(other: Double): MilePerGallonImperial = MilePerGallonImperial(this.value.rem(other))


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

val Byte.milePerGallonImperial
    get() = MilePerGallonImperial(this)


val Short.milePerGallonImperial
    get() = MilePerGallonImperial(this)


val Int.milePerGallonImperial
    get() = MilePerGallonImperial(this)


val Long.milePerGallonImperial
    get() = MilePerGallonImperial(this)


val Float.milePerGallonImperial
    get() = MilePerGallonImperial(this)


val Double.milePerGallonImperial
    get() = MilePerGallonImperial(this)
                            
