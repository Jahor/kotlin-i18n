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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): SolarLuminosity = SolarLuminosity(this.value.times(other))
    override operator fun div(other: kotlin.Byte): SolarLuminosity = SolarLuminosity(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): SolarLuminosity = SolarLuminosity(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): SolarLuminosity = SolarLuminosity(this.value.times(other))
    override operator fun div(other: kotlin.Short): SolarLuminosity = SolarLuminosity(this.value.div(other))
    override operator fun rem(other: kotlin.Short): SolarLuminosity = SolarLuminosity(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): SolarLuminosity = SolarLuminosity(this.value.times(other))
    override operator fun div(other: kotlin.Int): SolarLuminosity = SolarLuminosity(this.value.div(other))
    override operator fun rem(other: kotlin.Int): SolarLuminosity = SolarLuminosity(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): SolarLuminosity = SolarLuminosity(this.value.times(other))
    override operator fun div(other: kotlin.Long): SolarLuminosity = SolarLuminosity(this.value.div(other))
    override operator fun rem(other: kotlin.Long): SolarLuminosity = SolarLuminosity(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): SolarLuminosity = SolarLuminosity(this.value.times(other))
    override operator fun div(other: kotlin.Float): SolarLuminosity = SolarLuminosity(this.value.div(other))
    override operator fun rem(other: kotlin.Float): SolarLuminosity = SolarLuminosity(this.value.rem(other))


    override operator fun times(other: kotlin.Double): SolarLuminosity = SolarLuminosity(this.value.times(other))
    override operator fun div(other: kotlin.Double): SolarLuminosity = SolarLuminosity(this.value.div(other))
    override operator fun rem(other: kotlin.Double): SolarLuminosity = SolarLuminosity(this.value.rem(other))


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

val kotlin.Byte.solarLuminosity
    get() = SolarLuminosity(this)


val kotlin.Short.solarLuminosity
    get() = SolarLuminosity(this)


val kotlin.Int.solarLuminosity
    get() = SolarLuminosity(this)


val kotlin.Long.solarLuminosity
    get() = SolarLuminosity(this)


val kotlin.Float.solarLuminosity
    get() = SolarLuminosity(this)


val kotlin.Double.solarLuminosity
    get() = SolarLuminosity(this)
                            
