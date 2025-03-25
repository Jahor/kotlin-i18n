package info.leonenko.i18n.units.torque

import info.leonenko.i18n.units.MeasurementUnitLanguage
import info.leonenko.i18n.units.MeasurementUnitValue
import info.leonenko.i18n.units.energy.Energy
import info.leonenko.i18n.units.energy.KilogramSquareMeterPerSquareSecond
import info.leonenko.i18n.units.toMeasurementUnitValue
import kotlin.math.*

fun Energy.toNewtonMeter(): NewtonMeter {
    return NewtonMeter((toKilogramSquareMeterPerSquareSecond().value - 0) / ((1) * (1)))
}

data class PoundForceFoot(override val value: MeasurementUnitValue) : Energy {

    override val unitName: String = "pound-force-foot"

    override fun formatters(language: MeasurementUnitLanguage) = language.torquePoundForceFoot
    override fun toKilogramSquareMeterPerSquareSecond() = KilogramSquareMeterPerSquareSecond(value * ((4.4482216152605) * (0.3048)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = PoundForceFoot(0.0)
        fun fromKilogramSquareMeterPerSquareSecond(value: KilogramSquareMeterPerSquareSecond) = PoundForceFoot((value.value - 0) / ((4.4482216152605) * (0.3048)))
    }

    operator fun plus(other: PoundForceFoot): PoundForceFoot = PoundForceFoot(this.value + other.value)
    operator fun minus(other: PoundForceFoot): PoundForceFoot = PoundForceFoot(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): PoundForceFoot = PoundForceFoot(this.value.times(other))
    override operator fun div(other: Byte): PoundForceFoot = PoundForceFoot(this.value.div(other))
    override operator fun rem(other: Byte): PoundForceFoot = PoundForceFoot(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): PoundForceFoot = PoundForceFoot(this.value.times(other))
    override operator fun div(other: Short): PoundForceFoot = PoundForceFoot(this.value.div(other))
    override operator fun rem(other: Short): PoundForceFoot = PoundForceFoot(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): PoundForceFoot = PoundForceFoot(this.value.times(other))
    override operator fun div(other: Int): PoundForceFoot = PoundForceFoot(this.value.div(other))
    override operator fun rem(other: Int): PoundForceFoot = PoundForceFoot(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): PoundForceFoot = PoundForceFoot(this.value.times(other))
    override operator fun div(other: Long): PoundForceFoot = PoundForceFoot(this.value.div(other))
    override operator fun rem(other: Long): PoundForceFoot = PoundForceFoot(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): PoundForceFoot = PoundForceFoot(this.value.times(other))
    override operator fun div(other: Float): PoundForceFoot = PoundForceFoot(this.value.div(other))
    override operator fun rem(other: Float): PoundForceFoot = PoundForceFoot(this.value.rem(other))


    override operator fun times(other: Double): PoundForceFoot = PoundForceFoot(this.value.times(other))
    override operator fun div(other: Double): PoundForceFoot = PoundForceFoot(this.value.div(other))
    override operator fun rem(other: Double): PoundForceFoot = PoundForceFoot(this.value.rem(other))


    override operator fun unaryMinus(): PoundForceFoot = PoundForceFoot(-value)
    override operator fun unaryPlus(): PoundForceFoot = this
    override operator fun inc(): PoundForceFoot = PoundForceFoot(this.value + 1)
    override operator fun dec(): PoundForceFoot = PoundForceFoot(this.value - 1)

    override fun round(): PoundForceFoot = PoundForceFoot(round(this.value))
    override fun truncate(): PoundForceFoot = PoundForceFoot(truncate(this.value))
    override fun floor(): PoundForceFoot = PoundForceFoot(floor(this.value))
    override fun ceil(): PoundForceFoot = PoundForceFoot(ceil(this.value))
    override fun abs(): PoundForceFoot = PoundForceFoot(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is PoundForceFoot -> this.value == other.value
        is Energy -> this.toKilogramSquareMeterPerSquareSecond().value == other.toKilogramSquareMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerSquareSecond().value.hashCode()


}

val Byte.poundForceFoot
    get() = PoundForceFoot(this)


val Short.poundForceFoot
    get() = PoundForceFoot(this)


val Int.poundForceFoot
    get() = PoundForceFoot(this)


val Long.poundForceFoot
    get() = PoundForceFoot(this)


val Float.poundForceFoot
    get() = PoundForceFoot(this)


val Double.poundForceFoot
    get() = PoundForceFoot(this)


data class NewtonMeter(override val value: MeasurementUnitValue) : Energy {

    override val unitName: String = "newton-meter"

    override fun formatters(language: MeasurementUnitLanguage) = language.torqueNewtonMeter
    override fun toKilogramSquareMeterPerSquareSecond() = KilogramSquareMeterPerSquareSecond(value * ((1) * (1)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = NewtonMeter(0.0)
        fun fromKilogramSquareMeterPerSquareSecond(value: KilogramSquareMeterPerSquareSecond) = NewtonMeter((value.value - 0) / ((1) * (1)))
    }

    operator fun plus(other: NewtonMeter): NewtonMeter = NewtonMeter(this.value + other.value)
    operator fun minus(other: NewtonMeter): NewtonMeter = NewtonMeter(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): NewtonMeter = NewtonMeter(this.value.times(other))
    override operator fun div(other: Byte): NewtonMeter = NewtonMeter(this.value.div(other))
    override operator fun rem(other: Byte): NewtonMeter = NewtonMeter(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): NewtonMeter = NewtonMeter(this.value.times(other))
    override operator fun div(other: Short): NewtonMeter = NewtonMeter(this.value.div(other))
    override operator fun rem(other: Short): NewtonMeter = NewtonMeter(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): NewtonMeter = NewtonMeter(this.value.times(other))
    override operator fun div(other: Int): NewtonMeter = NewtonMeter(this.value.div(other))
    override operator fun rem(other: Int): NewtonMeter = NewtonMeter(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): NewtonMeter = NewtonMeter(this.value.times(other))
    override operator fun div(other: Long): NewtonMeter = NewtonMeter(this.value.div(other))
    override operator fun rem(other: Long): NewtonMeter = NewtonMeter(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): NewtonMeter = NewtonMeter(this.value.times(other))
    override operator fun div(other: Float): NewtonMeter = NewtonMeter(this.value.div(other))
    override operator fun rem(other: Float): NewtonMeter = NewtonMeter(this.value.rem(other))


    override operator fun times(other: Double): NewtonMeter = NewtonMeter(this.value.times(other))
    override operator fun div(other: Double): NewtonMeter = NewtonMeter(this.value.div(other))
    override operator fun rem(other: Double): NewtonMeter = NewtonMeter(this.value.rem(other))


    override operator fun unaryMinus(): NewtonMeter = NewtonMeter(-value)
    override operator fun unaryPlus(): NewtonMeter = this
    override operator fun inc(): NewtonMeter = NewtonMeter(this.value + 1)
    override operator fun dec(): NewtonMeter = NewtonMeter(this.value - 1)

    override fun round(): NewtonMeter = NewtonMeter(round(this.value))
    override fun truncate(): NewtonMeter = NewtonMeter(truncate(this.value))
    override fun floor(): NewtonMeter = NewtonMeter(floor(this.value))
    override fun ceil(): NewtonMeter = NewtonMeter(ceil(this.value))
    override fun abs(): NewtonMeter = NewtonMeter(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is NewtonMeter -> this.value == other.value
        is Energy -> this.toKilogramSquareMeterPerSquareSecond().value == other.toKilogramSquareMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerSquareSecond().value.hashCode()


}

val Byte.newtonMeter
    get() = NewtonMeter(this)


val Short.newtonMeter
    get() = NewtonMeter(this)


val Int.newtonMeter
    get() = NewtonMeter(this)


val Long.newtonMeter
    get() = NewtonMeter(this)


val Float.newtonMeter
    get() = NewtonMeter(this)


val Double.newtonMeter
    get() = NewtonMeter(this)
                            
