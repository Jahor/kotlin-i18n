package info.leonenko.i18n.units.light

import info.leonenko.i18n.units.MeasurementUnitLanguage
import info.leonenko.i18n.units.MeasurementUnitValue
import info.leonenko.i18n.units.power.KilogramSquareMeterPerCubicSecond
import info.leonenko.i18n.units.power.Power
import info.leonenko.i18n.units.toMeasurementUnitValue
import kotlin.math.*

fun Power.toSolarLuminosity(): SolarLuminosity {
    return SolarLuminosity((toKilogramSquareMeterPerCubicSecond().value - 0) / (3.828e+26))
}

data class SolarLuminosity(override val value: MeasurementUnitValue) : Power {

    override val unitName: String = "solar-luminosity"

    override fun formatters(language: MeasurementUnitLanguage) = language.lightSolarLuminosity
    override fun toKilogramSquareMeterPerCubicSecond() = KilogramSquareMeterPerCubicSecond(value * (3.828e+26) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = SolarLuminosity(0.0)
        fun fromKilogramSquareMeterPerCubicSecond(value: KilogramSquareMeterPerCubicSecond) = SolarLuminosity((value.value - 0) / (3.828e+26))
    }

    operator fun plus(other: SolarLuminosity): SolarLuminosity = SolarLuminosity(this.value + other.value)
    operator fun minus(other: SolarLuminosity): SolarLuminosity = SolarLuminosity(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): SolarLuminosity = SolarLuminosity(this.value.times(other))
    override operator fun div(other: Byte): SolarLuminosity = SolarLuminosity(this.value.div(other))
    override operator fun rem(other: Byte): SolarLuminosity = SolarLuminosity(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): SolarLuminosity = SolarLuminosity(this.value.times(other))
    override operator fun div(other: Short): SolarLuminosity = SolarLuminosity(this.value.div(other))
    override operator fun rem(other: Short): SolarLuminosity = SolarLuminosity(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): SolarLuminosity = SolarLuminosity(this.value.times(other))
    override operator fun div(other: Int): SolarLuminosity = SolarLuminosity(this.value.div(other))
    override operator fun rem(other: Int): SolarLuminosity = SolarLuminosity(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): SolarLuminosity = SolarLuminosity(this.value.times(other))
    override operator fun div(other: Long): SolarLuminosity = SolarLuminosity(this.value.div(other))
    override operator fun rem(other: Long): SolarLuminosity = SolarLuminosity(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): SolarLuminosity = SolarLuminosity(this.value.times(other))
    override operator fun div(other: Float): SolarLuminosity = SolarLuminosity(this.value.div(other))
    override operator fun rem(other: Float): SolarLuminosity = SolarLuminosity(this.value.rem(other))


    override operator fun times(other: Double): SolarLuminosity = SolarLuminosity(this.value.times(other))
    override operator fun div(other: Double): SolarLuminosity = SolarLuminosity(this.value.div(other))
    override operator fun rem(other: Double): SolarLuminosity = SolarLuminosity(this.value.rem(other))


    override operator fun unaryMinus(): SolarLuminosity = SolarLuminosity(-value)
    override operator fun unaryPlus(): SolarLuminosity = this
    override operator fun inc(): SolarLuminosity = SolarLuminosity(this.value + 1)
    override operator fun dec(): SolarLuminosity = SolarLuminosity(this.value - 1)

    override fun round(): SolarLuminosity = SolarLuminosity(round(this.value))
    override fun truncate(): SolarLuminosity = SolarLuminosity(truncate(this.value))
    override fun floor(): SolarLuminosity = SolarLuminosity(floor(this.value))
    override fun ceil(): SolarLuminosity = SolarLuminosity(ceil(this.value))
    override fun abs(): SolarLuminosity = SolarLuminosity(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is SolarLuminosity -> this.value == other.value
        is Power -> this.toKilogramSquareMeterPerCubicSecond().value == other.toKilogramSquareMeterPerCubicSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerCubicSecond().value.hashCode()


}

val Byte.solarLuminosity
    get() = SolarLuminosity(this)


val Short.solarLuminosity
    get() = SolarLuminosity(this)


val Int.solarLuminosity
    get() = SolarLuminosity(this)


val Long.solarLuminosity
    get() = SolarLuminosity(this)


val Float.solarLuminosity
    get() = SolarLuminosity(this)


val Double.solarLuminosity
    get() = SolarLuminosity(this)
                            
