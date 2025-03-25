package info.leonenko.i18n.units.pressure

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

@Serializable(Pressure.Serializer::class)
interface Pressure : MeasurementUnit, Comparable<Pressure> {
    override val measurement: String get() = "pressure"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Pressure> = emptyMap()) : KSerializer<Pressure> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Pressure", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Pressure {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Pressure unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "millimeter-ofhg" -> MillimeterOfhg(value)
                "pound-force-per-square-inch" -> PoundForcePerSquareInch(value)
                "inch-ofhg" -> InchOfhg(value)
                "bar" -> Bar(value)
                "millibar" -> Millibar(value)
                "atmosphere" -> Atmosphere(value)
                "pascal" -> Pascal(value)
                "hectopascal" -> Hectopascal(value)
                "kilopascal" -> Kilopascal(value)
                "megapascal" -> Megapascal(value)
                "gasoline-energy-density" -> GasolineEnergyDensity(value)
                "kilogram-per-meter-square-second" -> KilogramPerMeterSquareSecond(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Pressure unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Pressure) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toMillimeterOfhg(): MillimeterOfhg {
        return MillimeterOfhg.fromKilogramPerMeterSquareSecond(toKilogramPerMeterSquareSecond())
    }

    fun toPoundForcePerSquareInch(): PoundForcePerSquareInch {
        return PoundForcePerSquareInch.fromKilogramPerMeterSquareSecond(toKilogramPerMeterSquareSecond())
    }

    fun toInchOfhg(): InchOfhg {
        return InchOfhg.fromKilogramPerMeterSquareSecond(toKilogramPerMeterSquareSecond())
    }

    fun toBar(): Bar {
        return Bar.fromKilogramPerMeterSquareSecond(toKilogramPerMeterSquareSecond())
    }

    fun toMillibar(): Millibar {
        return Millibar.fromKilogramPerMeterSquareSecond(toKilogramPerMeterSquareSecond())
    }

    fun toAtmosphere(): Atmosphere {
        return Atmosphere.fromKilogramPerMeterSquareSecond(toKilogramPerMeterSquareSecond())
    }

    fun toPascal(): Pascal {
        return Pascal.fromKilogramPerMeterSquareSecond(toKilogramPerMeterSquareSecond())
    }

    fun toHectopascal(): Hectopascal {
        return Hectopascal.fromKilogramPerMeterSquareSecond(toKilogramPerMeterSquareSecond())
    }

    fun toKilopascal(): Kilopascal {
        return Kilopascal.fromKilogramPerMeterSquareSecond(toKilogramPerMeterSquareSecond())
    }

    fun toMegapascal(): Megapascal {
        return Megapascal.fromKilogramPerMeterSquareSecond(toKilogramPerMeterSquareSecond())
    }

    fun toGasolineEnergyDensity(): GasolineEnergyDensity {
        return GasolineEnergyDensity.fromKilogramPerMeterSquareSecond(toKilogramPerMeterSquareSecond())
    }

    fun toKilogramPerMeterSquareSecond(): KilogramPerMeterSquareSecond
    override fun getBaseUnitValue() = toKilogramPerMeterSquareSecond().value

    companion object {
        val ZERO: Pressure = KilogramPerMeterSquareSecond(0.0)

    }

    override operator fun compareTo(other: Pressure): Int = this.toKilogramPerMeterSquareSecond().value.compareTo(other.toKilogramPerMeterSquareSecond().value)

    operator fun plus(other: Pressure): KilogramPerMeterSquareSecond = this.toKilogramPerMeterSquareSecond() + other.toKilogramPerMeterSquareSecond()
    operator fun minus(other: Pressure): KilogramPerMeterSquareSecond = this.toKilogramPerMeterSquareSecond() - other.toKilogramPerMeterSquareSecond()
    operator fun div(other: Pressure): Double = this.toKilogramPerMeterSquareSecond().value / other.toKilogramPerMeterSquareSecond().value
    operator fun unaryMinus(): Pressure
    operator fun unaryPlus(): Pressure
    operator fun inc(): Pressure
    operator fun dec(): Pressure

    fun round(): Pressure
    fun truncate(): Pressure
    fun floor(): Pressure
    fun ceil(): Pressure
    fun abs(): Pressure


    operator fun times(other: Byte): Pressure
    operator fun div(other: Byte): Pressure
    operator fun rem(other: Byte): Pressure


    operator fun times(other: Short): Pressure
    operator fun div(other: Short): Pressure
    operator fun rem(other: Short): Pressure


    operator fun times(other: Int): Pressure
    operator fun div(other: Int): Pressure
    operator fun rem(other: Int): Pressure


    operator fun times(other: Long): Pressure
    operator fun div(other: Long): Pressure
    operator fun rem(other: Long): Pressure


    operator fun times(other: Float): Pressure
    operator fun div(other: Float): Pressure
    operator fun rem(other: Float): Pressure


    operator fun times(other: Double): Pressure
    operator fun div(other: Double): Pressure
    operator fun rem(other: Double): Pressure

}

data class MillimeterOfhg(override val value: MeasurementUnitValue) : Pressure {

    override val unitName: String = "millimeter-ofhg"

    override fun formatters(language: MeasurementUnitLanguage) = language.pressureMillimeterOfhg
    override fun toKilogramPerMeterSquareSecond() = KilogramPerMeterSquareSecond(value * ((1 * (1e-3)) * (133322.387415)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = MillimeterOfhg(0.0)
        fun fromKilogramPerMeterSquareSecond(value: KilogramPerMeterSquareSecond) = MillimeterOfhg((value.value - 0) / ((1 * (1e-3)) * (133322.387415)))
    }

    operator fun plus(other: MillimeterOfhg): MillimeterOfhg = MillimeterOfhg(this.value + other.value)
    operator fun minus(other: MillimeterOfhg): MillimeterOfhg = MillimeterOfhg(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): MillimeterOfhg = MillimeterOfhg(this.value.times(other))
    override operator fun div(other: Byte): MillimeterOfhg = MillimeterOfhg(this.value.div(other))
    override operator fun rem(other: Byte): MillimeterOfhg = MillimeterOfhg(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): MillimeterOfhg = MillimeterOfhg(this.value.times(other))
    override operator fun div(other: Short): MillimeterOfhg = MillimeterOfhg(this.value.div(other))
    override operator fun rem(other: Short): MillimeterOfhg = MillimeterOfhg(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): MillimeterOfhg = MillimeterOfhg(this.value.times(other))
    override operator fun div(other: Int): MillimeterOfhg = MillimeterOfhg(this.value.div(other))
    override operator fun rem(other: Int): MillimeterOfhg = MillimeterOfhg(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): MillimeterOfhg = MillimeterOfhg(this.value.times(other))
    override operator fun div(other: Long): MillimeterOfhg = MillimeterOfhg(this.value.div(other))
    override operator fun rem(other: Long): MillimeterOfhg = MillimeterOfhg(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): MillimeterOfhg = MillimeterOfhg(this.value.times(other))
    override operator fun div(other: Float): MillimeterOfhg = MillimeterOfhg(this.value.div(other))
    override operator fun rem(other: Float): MillimeterOfhg = MillimeterOfhg(this.value.rem(other))


    override operator fun times(other: Double): MillimeterOfhg = MillimeterOfhg(this.value.times(other))
    override operator fun div(other: Double): MillimeterOfhg = MillimeterOfhg(this.value.div(other))
    override operator fun rem(other: Double): MillimeterOfhg = MillimeterOfhg(this.value.rem(other))


    override operator fun unaryMinus(): MillimeterOfhg = MillimeterOfhg(-value)
    override operator fun unaryPlus(): MillimeterOfhg = this
    override operator fun inc(): MillimeterOfhg = MillimeterOfhg(this.value + 1)
    override operator fun dec(): MillimeterOfhg = MillimeterOfhg(this.value - 1)

    override fun round(): MillimeterOfhg = MillimeterOfhg(round(this.value))
    override fun truncate(): MillimeterOfhg = MillimeterOfhg(truncate(this.value))
    override fun floor(): MillimeterOfhg = MillimeterOfhg(floor(this.value))
    override fun ceil(): MillimeterOfhg = MillimeterOfhg(ceil(this.value))
    override fun abs(): MillimeterOfhg = MillimeterOfhg(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is MillimeterOfhg -> this.value == other.value
        is Pressure -> this.toKilogramPerMeterSquareSecond().value == other.toKilogramPerMeterSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramPerMeterSquareSecond().value.hashCode()


}

val Byte.millimeterOfhg
    get() = MillimeterOfhg(this)


val Short.millimeterOfhg
    get() = MillimeterOfhg(this)


val Int.millimeterOfhg
    get() = MillimeterOfhg(this)


val Long.millimeterOfhg
    get() = MillimeterOfhg(this)


val Float.millimeterOfhg
    get() = MillimeterOfhg(this)


val Double.millimeterOfhg
    get() = MillimeterOfhg(this)


data class PoundForcePerSquareInch(override val value: MeasurementUnitValue) : Pressure {

    override val unitName: String = "pound-force-per-square-inch"

    override fun formatters(language: MeasurementUnitLanguage) = language.pressurePoundForcePerSquareInch
    override fun toKilogramPerMeterSquareSecond() = KilogramPerMeterSquareSecond(value * ((4.4482216152605) / ((0.025400000000000002) * (0.025400000000000002))) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = PoundForcePerSquareInch(0.0)
        fun fromKilogramPerMeterSquareSecond(value: KilogramPerMeterSquareSecond) = PoundForcePerSquareInch((value.value - 0) / ((4.4482216152605) / ((0.025400000000000002) * (0.025400000000000002))))
    }

    operator fun plus(other: PoundForcePerSquareInch): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value + other.value)
    operator fun minus(other: PoundForcePerSquareInch): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.times(other))
    override operator fun div(other: Byte): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.div(other))
    override operator fun rem(other: Byte): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.times(other))
    override operator fun div(other: Short): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.div(other))
    override operator fun rem(other: Short): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.times(other))
    override operator fun div(other: Int): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.div(other))
    override operator fun rem(other: Int): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.times(other))
    override operator fun div(other: Long): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.div(other))
    override operator fun rem(other: Long): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.times(other))
    override operator fun div(other: Float): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.div(other))
    override operator fun rem(other: Float): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.rem(other))


    override operator fun times(other: Double): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.times(other))
    override operator fun div(other: Double): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.div(other))
    override operator fun rem(other: Double): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value.rem(other))


    override operator fun unaryMinus(): PoundForcePerSquareInch = PoundForcePerSquareInch(-value)
    override operator fun unaryPlus(): PoundForcePerSquareInch = this
    override operator fun inc(): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value + 1)
    override operator fun dec(): PoundForcePerSquareInch = PoundForcePerSquareInch(this.value - 1)

    override fun round(): PoundForcePerSquareInch = PoundForcePerSquareInch(round(this.value))
    override fun truncate(): PoundForcePerSquareInch = PoundForcePerSquareInch(truncate(this.value))
    override fun floor(): PoundForcePerSquareInch = PoundForcePerSquareInch(floor(this.value))
    override fun ceil(): PoundForcePerSquareInch = PoundForcePerSquareInch(ceil(this.value))
    override fun abs(): PoundForcePerSquareInch = PoundForcePerSquareInch(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is PoundForcePerSquareInch -> this.value == other.value
        is Pressure -> this.toKilogramPerMeterSquareSecond().value == other.toKilogramPerMeterSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramPerMeterSquareSecond().value.hashCode()


}

val Byte.poundForcePerSquareInch
    get() = PoundForcePerSquareInch(this)


val Short.poundForcePerSquareInch
    get() = PoundForcePerSquareInch(this)


val Int.poundForcePerSquareInch
    get() = PoundForcePerSquareInch(this)


val Long.poundForcePerSquareInch
    get() = PoundForcePerSquareInch(this)


val Float.poundForcePerSquareInch
    get() = PoundForcePerSquareInch(this)


val Double.poundForcePerSquareInch
    get() = PoundForcePerSquareInch(this)


data class InchOfhg(override val value: MeasurementUnitValue) : Pressure {

    override val unitName: String = "inch-ofhg"

    override fun formatters(language: MeasurementUnitLanguage) = language.pressureInchOfhg
    override fun toKilogramPerMeterSquareSecond() = KilogramPerMeterSquareSecond(value * ((0.025400000000000002) * (133322.387415)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = InchOfhg(0.0)
        fun fromKilogramPerMeterSquareSecond(value: KilogramPerMeterSquareSecond) = InchOfhg((value.value - 0) / ((0.025400000000000002) * (133322.387415)))
    }

    operator fun plus(other: InchOfhg): InchOfhg = InchOfhg(this.value + other.value)
    operator fun minus(other: InchOfhg): InchOfhg = InchOfhg(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): InchOfhg = InchOfhg(this.value.times(other))
    override operator fun div(other: Byte): InchOfhg = InchOfhg(this.value.div(other))
    override operator fun rem(other: Byte): InchOfhg = InchOfhg(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): InchOfhg = InchOfhg(this.value.times(other))
    override operator fun div(other: Short): InchOfhg = InchOfhg(this.value.div(other))
    override operator fun rem(other: Short): InchOfhg = InchOfhg(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): InchOfhg = InchOfhg(this.value.times(other))
    override operator fun div(other: Int): InchOfhg = InchOfhg(this.value.div(other))
    override operator fun rem(other: Int): InchOfhg = InchOfhg(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): InchOfhg = InchOfhg(this.value.times(other))
    override operator fun div(other: Long): InchOfhg = InchOfhg(this.value.div(other))
    override operator fun rem(other: Long): InchOfhg = InchOfhg(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): InchOfhg = InchOfhg(this.value.times(other))
    override operator fun div(other: Float): InchOfhg = InchOfhg(this.value.div(other))
    override operator fun rem(other: Float): InchOfhg = InchOfhg(this.value.rem(other))


    override operator fun times(other: Double): InchOfhg = InchOfhg(this.value.times(other))
    override operator fun div(other: Double): InchOfhg = InchOfhg(this.value.div(other))
    override operator fun rem(other: Double): InchOfhg = InchOfhg(this.value.rem(other))


    override operator fun unaryMinus(): InchOfhg = InchOfhg(-value)
    override operator fun unaryPlus(): InchOfhg = this
    override operator fun inc(): InchOfhg = InchOfhg(this.value + 1)
    override operator fun dec(): InchOfhg = InchOfhg(this.value - 1)

    override fun round(): InchOfhg = InchOfhg(round(this.value))
    override fun truncate(): InchOfhg = InchOfhg(truncate(this.value))
    override fun floor(): InchOfhg = InchOfhg(floor(this.value))
    override fun ceil(): InchOfhg = InchOfhg(ceil(this.value))
    override fun abs(): InchOfhg = InchOfhg(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is InchOfhg -> this.value == other.value
        is Pressure -> this.toKilogramPerMeterSquareSecond().value == other.toKilogramPerMeterSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramPerMeterSquareSecond().value.hashCode()


}

val Byte.inchOfhg
    get() = InchOfhg(this)


val Short.inchOfhg
    get() = InchOfhg(this)


val Int.inchOfhg
    get() = InchOfhg(this)


val Long.inchOfhg
    get() = InchOfhg(this)


val Float.inchOfhg
    get() = InchOfhg(this)


val Double.inchOfhg
    get() = InchOfhg(this)


data class Bar(override val value: MeasurementUnitValue) : Pressure {

    override val unitName: String = "bar"

    override fun formatters(language: MeasurementUnitLanguage) = language.pressureBar
    override fun toKilogramPerMeterSquareSecond() = KilogramPerMeterSquareSecond(value * (100000) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Bar(0.0)
        fun fromKilogramPerMeterSquareSecond(value: KilogramPerMeterSquareSecond) = Bar((value.value - 0) / (100000))
    }

    operator fun plus(other: Bar): Bar = Bar(this.value + other.value)
    operator fun minus(other: Bar): Bar = Bar(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Bar = Bar(this.value.times(other))
    override operator fun div(other: Byte): Bar = Bar(this.value.div(other))
    override operator fun rem(other: Byte): Bar = Bar(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Bar = Bar(this.value.times(other))
    override operator fun div(other: Short): Bar = Bar(this.value.div(other))
    override operator fun rem(other: Short): Bar = Bar(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Bar = Bar(this.value.times(other))
    override operator fun div(other: Int): Bar = Bar(this.value.div(other))
    override operator fun rem(other: Int): Bar = Bar(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Bar = Bar(this.value.times(other))
    override operator fun div(other: Long): Bar = Bar(this.value.div(other))
    override operator fun rem(other: Long): Bar = Bar(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Bar = Bar(this.value.times(other))
    override operator fun div(other: Float): Bar = Bar(this.value.div(other))
    override operator fun rem(other: Float): Bar = Bar(this.value.rem(other))


    override operator fun times(other: Double): Bar = Bar(this.value.times(other))
    override operator fun div(other: Double): Bar = Bar(this.value.div(other))
    override operator fun rem(other: Double): Bar = Bar(this.value.rem(other))


    override operator fun unaryMinus(): Bar = Bar(-value)
    override operator fun unaryPlus(): Bar = this
    override operator fun inc(): Bar = Bar(this.value + 1)
    override operator fun dec(): Bar = Bar(this.value - 1)

    override fun round(): Bar = Bar(round(this.value))
    override fun truncate(): Bar = Bar(truncate(this.value))
    override fun floor(): Bar = Bar(floor(this.value))
    override fun ceil(): Bar = Bar(ceil(this.value))
    override fun abs(): Bar = Bar(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Bar -> this.value == other.value
        is Pressure -> this.toKilogramPerMeterSquareSecond().value == other.toKilogramPerMeterSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramPerMeterSquareSecond().value.hashCode()


}

val Byte.bar
    get() = Bar(this)


val Short.bar
    get() = Bar(this)


val Int.bar
    get() = Bar(this)


val Long.bar
    get() = Bar(this)


val Float.bar
    get() = Bar(this)


val Double.bar
    get() = Bar(this)


data class Millibar(override val value: MeasurementUnitValue) : Pressure {

    override val unitName: String = "millibar"

    override fun formatters(language: MeasurementUnitLanguage) = language.pressureMillibar
    override fun toKilogramPerMeterSquareSecond() = KilogramPerMeterSquareSecond(value * (100000 * (1e-3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Millibar(0.0)
        fun fromKilogramPerMeterSquareSecond(value: KilogramPerMeterSquareSecond) = Millibar((value.value - 0) / (100000 * (1e-3)))
    }

    operator fun plus(other: Millibar): Millibar = Millibar(this.value + other.value)
    operator fun minus(other: Millibar): Millibar = Millibar(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Millibar = Millibar(this.value.times(other))
    override operator fun div(other: Byte): Millibar = Millibar(this.value.div(other))
    override operator fun rem(other: Byte): Millibar = Millibar(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Millibar = Millibar(this.value.times(other))
    override operator fun div(other: Short): Millibar = Millibar(this.value.div(other))
    override operator fun rem(other: Short): Millibar = Millibar(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Millibar = Millibar(this.value.times(other))
    override operator fun div(other: Int): Millibar = Millibar(this.value.div(other))
    override operator fun rem(other: Int): Millibar = Millibar(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Millibar = Millibar(this.value.times(other))
    override operator fun div(other: Long): Millibar = Millibar(this.value.div(other))
    override operator fun rem(other: Long): Millibar = Millibar(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Millibar = Millibar(this.value.times(other))
    override operator fun div(other: Float): Millibar = Millibar(this.value.div(other))
    override operator fun rem(other: Float): Millibar = Millibar(this.value.rem(other))


    override operator fun times(other: Double): Millibar = Millibar(this.value.times(other))
    override operator fun div(other: Double): Millibar = Millibar(this.value.div(other))
    override operator fun rem(other: Double): Millibar = Millibar(this.value.rem(other))


    override operator fun unaryMinus(): Millibar = Millibar(-value)
    override operator fun unaryPlus(): Millibar = this
    override operator fun inc(): Millibar = Millibar(this.value + 1)
    override operator fun dec(): Millibar = Millibar(this.value - 1)

    override fun round(): Millibar = Millibar(round(this.value))
    override fun truncate(): Millibar = Millibar(truncate(this.value))
    override fun floor(): Millibar = Millibar(floor(this.value))
    override fun ceil(): Millibar = Millibar(ceil(this.value))
    override fun abs(): Millibar = Millibar(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Millibar -> this.value == other.value
        is Pressure -> this.toKilogramPerMeterSquareSecond().value == other.toKilogramPerMeterSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramPerMeterSquareSecond().value.hashCode()


}

val Byte.millibar
    get() = Millibar(this)


val Short.millibar
    get() = Millibar(this)


val Int.millibar
    get() = Millibar(this)


val Long.millibar
    get() = Millibar(this)


val Float.millibar
    get() = Millibar(this)


val Double.millibar
    get() = Millibar(this)


data class Atmosphere(override val value: MeasurementUnitValue) : Pressure {

    override val unitName: String = "atmosphere"

    override fun formatters(language: MeasurementUnitLanguage) = language.pressureAtmosphere
    override fun toKilogramPerMeterSquareSecond() = KilogramPerMeterSquareSecond(value * (101325) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Atmosphere(0.0)
        fun fromKilogramPerMeterSquareSecond(value: KilogramPerMeterSquareSecond) = Atmosphere((value.value - 0) / (101325))
    }

    operator fun plus(other: Atmosphere): Atmosphere = Atmosphere(this.value + other.value)
    operator fun minus(other: Atmosphere): Atmosphere = Atmosphere(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Atmosphere = Atmosphere(this.value.times(other))
    override operator fun div(other: Byte): Atmosphere = Atmosphere(this.value.div(other))
    override operator fun rem(other: Byte): Atmosphere = Atmosphere(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Atmosphere = Atmosphere(this.value.times(other))
    override operator fun div(other: Short): Atmosphere = Atmosphere(this.value.div(other))
    override operator fun rem(other: Short): Atmosphere = Atmosphere(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Atmosphere = Atmosphere(this.value.times(other))
    override operator fun div(other: Int): Atmosphere = Atmosphere(this.value.div(other))
    override operator fun rem(other: Int): Atmosphere = Atmosphere(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Atmosphere = Atmosphere(this.value.times(other))
    override operator fun div(other: Long): Atmosphere = Atmosphere(this.value.div(other))
    override operator fun rem(other: Long): Atmosphere = Atmosphere(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Atmosphere = Atmosphere(this.value.times(other))
    override operator fun div(other: Float): Atmosphere = Atmosphere(this.value.div(other))
    override operator fun rem(other: Float): Atmosphere = Atmosphere(this.value.rem(other))


    override operator fun times(other: Double): Atmosphere = Atmosphere(this.value.times(other))
    override operator fun div(other: Double): Atmosphere = Atmosphere(this.value.div(other))
    override operator fun rem(other: Double): Atmosphere = Atmosphere(this.value.rem(other))


    override operator fun unaryMinus(): Atmosphere = Atmosphere(-value)
    override operator fun unaryPlus(): Atmosphere = this
    override operator fun inc(): Atmosphere = Atmosphere(this.value + 1)
    override operator fun dec(): Atmosphere = Atmosphere(this.value - 1)

    override fun round(): Atmosphere = Atmosphere(round(this.value))
    override fun truncate(): Atmosphere = Atmosphere(truncate(this.value))
    override fun floor(): Atmosphere = Atmosphere(floor(this.value))
    override fun ceil(): Atmosphere = Atmosphere(ceil(this.value))
    override fun abs(): Atmosphere = Atmosphere(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Atmosphere -> this.value == other.value
        is Pressure -> this.toKilogramPerMeterSquareSecond().value == other.toKilogramPerMeterSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramPerMeterSquareSecond().value.hashCode()


}

val Byte.atmosphere
    get() = Atmosphere(this)


val Short.atmosphere
    get() = Atmosphere(this)


val Int.atmosphere
    get() = Atmosphere(this)


val Long.atmosphere
    get() = Atmosphere(this)


val Float.atmosphere
    get() = Atmosphere(this)


val Double.atmosphere
    get() = Atmosphere(this)


data class Pascal(override val value: MeasurementUnitValue) : Pressure {

    override val unitName: String = "pascal"

    override fun formatters(language: MeasurementUnitLanguage) = language.pressurePascal
    override fun toKilogramPerMeterSquareSecond() = KilogramPerMeterSquareSecond(value * (1) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Pascal(0.0)
        fun fromKilogramPerMeterSquareSecond(value: KilogramPerMeterSquareSecond) = Pascal((value.value - 0) / (1))
    }

    operator fun plus(other: Pascal): Pascal = Pascal(this.value + other.value)
    operator fun minus(other: Pascal): Pascal = Pascal(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Pascal = Pascal(this.value.times(other))
    override operator fun div(other: Byte): Pascal = Pascal(this.value.div(other))
    override operator fun rem(other: Byte): Pascal = Pascal(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Pascal = Pascal(this.value.times(other))
    override operator fun div(other: Short): Pascal = Pascal(this.value.div(other))
    override operator fun rem(other: Short): Pascal = Pascal(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Pascal = Pascal(this.value.times(other))
    override operator fun div(other: Int): Pascal = Pascal(this.value.div(other))
    override operator fun rem(other: Int): Pascal = Pascal(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Pascal = Pascal(this.value.times(other))
    override operator fun div(other: Long): Pascal = Pascal(this.value.div(other))
    override operator fun rem(other: Long): Pascal = Pascal(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Pascal = Pascal(this.value.times(other))
    override operator fun div(other: Float): Pascal = Pascal(this.value.div(other))
    override operator fun rem(other: Float): Pascal = Pascal(this.value.rem(other))


    override operator fun times(other: Double): Pascal = Pascal(this.value.times(other))
    override operator fun div(other: Double): Pascal = Pascal(this.value.div(other))
    override operator fun rem(other: Double): Pascal = Pascal(this.value.rem(other))


    override operator fun unaryMinus(): Pascal = Pascal(-value)
    override operator fun unaryPlus(): Pascal = this
    override operator fun inc(): Pascal = Pascal(this.value + 1)
    override operator fun dec(): Pascal = Pascal(this.value - 1)

    override fun round(): Pascal = Pascal(round(this.value))
    override fun truncate(): Pascal = Pascal(truncate(this.value))
    override fun floor(): Pascal = Pascal(floor(this.value))
    override fun ceil(): Pascal = Pascal(ceil(this.value))
    override fun abs(): Pascal = Pascal(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Pascal -> this.value == other.value
        is Pressure -> this.toKilogramPerMeterSquareSecond().value == other.toKilogramPerMeterSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramPerMeterSquareSecond().value.hashCode()


}

val Byte.pascal
    get() = Pascal(this)


val Short.pascal
    get() = Pascal(this)


val Int.pascal
    get() = Pascal(this)


val Long.pascal
    get() = Pascal(this)


val Float.pascal
    get() = Pascal(this)


val Double.pascal
    get() = Pascal(this)


data class Hectopascal(override val value: MeasurementUnitValue) : Pressure {

    override val unitName: String = "hectopascal"

    override fun formatters(language: MeasurementUnitLanguage) = language.pressureHectopascal
    override fun toKilogramPerMeterSquareSecond() = KilogramPerMeterSquareSecond(value * (1 * (1e2)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Hectopascal(0.0)
        fun fromKilogramPerMeterSquareSecond(value: KilogramPerMeterSquareSecond) = Hectopascal((value.value - 0) / (1 * (1e2)))
    }

    operator fun plus(other: Hectopascal): Hectopascal = Hectopascal(this.value + other.value)
    operator fun minus(other: Hectopascal): Hectopascal = Hectopascal(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Hectopascal = Hectopascal(this.value.times(other))
    override operator fun div(other: Byte): Hectopascal = Hectopascal(this.value.div(other))
    override operator fun rem(other: Byte): Hectopascal = Hectopascal(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Hectopascal = Hectopascal(this.value.times(other))
    override operator fun div(other: Short): Hectopascal = Hectopascal(this.value.div(other))
    override operator fun rem(other: Short): Hectopascal = Hectopascal(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Hectopascal = Hectopascal(this.value.times(other))
    override operator fun div(other: Int): Hectopascal = Hectopascal(this.value.div(other))
    override operator fun rem(other: Int): Hectopascal = Hectopascal(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Hectopascal = Hectopascal(this.value.times(other))
    override operator fun div(other: Long): Hectopascal = Hectopascal(this.value.div(other))
    override operator fun rem(other: Long): Hectopascal = Hectopascal(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Hectopascal = Hectopascal(this.value.times(other))
    override operator fun div(other: Float): Hectopascal = Hectopascal(this.value.div(other))
    override operator fun rem(other: Float): Hectopascal = Hectopascal(this.value.rem(other))


    override operator fun times(other: Double): Hectopascal = Hectopascal(this.value.times(other))
    override operator fun div(other: Double): Hectopascal = Hectopascal(this.value.div(other))
    override operator fun rem(other: Double): Hectopascal = Hectopascal(this.value.rem(other))


    override operator fun unaryMinus(): Hectopascal = Hectopascal(-value)
    override operator fun unaryPlus(): Hectopascal = this
    override operator fun inc(): Hectopascal = Hectopascal(this.value + 1)
    override operator fun dec(): Hectopascal = Hectopascal(this.value - 1)

    override fun round(): Hectopascal = Hectopascal(round(this.value))
    override fun truncate(): Hectopascal = Hectopascal(truncate(this.value))
    override fun floor(): Hectopascal = Hectopascal(floor(this.value))
    override fun ceil(): Hectopascal = Hectopascal(ceil(this.value))
    override fun abs(): Hectopascal = Hectopascal(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Hectopascal -> this.value == other.value
        is Pressure -> this.toKilogramPerMeterSquareSecond().value == other.toKilogramPerMeterSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramPerMeterSquareSecond().value.hashCode()


}

val Byte.hectopascal
    get() = Hectopascal(this)


val Short.hectopascal
    get() = Hectopascal(this)


val Int.hectopascal
    get() = Hectopascal(this)


val Long.hectopascal
    get() = Hectopascal(this)


val Float.hectopascal
    get() = Hectopascal(this)


val Double.hectopascal
    get() = Hectopascal(this)


data class Kilopascal(override val value: MeasurementUnitValue) : Pressure {

    override val unitName: String = "kilopascal"

    override fun formatters(language: MeasurementUnitLanguage) = language.pressureKilopascal
    override fun toKilogramPerMeterSquareSecond() = KilogramPerMeterSquareSecond(value * (1 * (1e3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Kilopascal(0.0)
        fun fromKilogramPerMeterSquareSecond(value: KilogramPerMeterSquareSecond) = Kilopascal((value.value - 0) / (1 * (1e3)))
    }

    operator fun plus(other: Kilopascal): Kilopascal = Kilopascal(this.value + other.value)
    operator fun minus(other: Kilopascal): Kilopascal = Kilopascal(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Kilopascal = Kilopascal(this.value.times(other))
    override operator fun div(other: Byte): Kilopascal = Kilopascal(this.value.div(other))
    override operator fun rem(other: Byte): Kilopascal = Kilopascal(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Kilopascal = Kilopascal(this.value.times(other))
    override operator fun div(other: Short): Kilopascal = Kilopascal(this.value.div(other))
    override operator fun rem(other: Short): Kilopascal = Kilopascal(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Kilopascal = Kilopascal(this.value.times(other))
    override operator fun div(other: Int): Kilopascal = Kilopascal(this.value.div(other))
    override operator fun rem(other: Int): Kilopascal = Kilopascal(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Kilopascal = Kilopascal(this.value.times(other))
    override operator fun div(other: Long): Kilopascal = Kilopascal(this.value.div(other))
    override operator fun rem(other: Long): Kilopascal = Kilopascal(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Kilopascal = Kilopascal(this.value.times(other))
    override operator fun div(other: Float): Kilopascal = Kilopascal(this.value.div(other))
    override operator fun rem(other: Float): Kilopascal = Kilopascal(this.value.rem(other))


    override operator fun times(other: Double): Kilopascal = Kilopascal(this.value.times(other))
    override operator fun div(other: Double): Kilopascal = Kilopascal(this.value.div(other))
    override operator fun rem(other: Double): Kilopascal = Kilopascal(this.value.rem(other))


    override operator fun unaryMinus(): Kilopascal = Kilopascal(-value)
    override operator fun unaryPlus(): Kilopascal = this
    override operator fun inc(): Kilopascal = Kilopascal(this.value + 1)
    override operator fun dec(): Kilopascal = Kilopascal(this.value - 1)

    override fun round(): Kilopascal = Kilopascal(round(this.value))
    override fun truncate(): Kilopascal = Kilopascal(truncate(this.value))
    override fun floor(): Kilopascal = Kilopascal(floor(this.value))
    override fun ceil(): Kilopascal = Kilopascal(ceil(this.value))
    override fun abs(): Kilopascal = Kilopascal(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Kilopascal -> this.value == other.value
        is Pressure -> this.toKilogramPerMeterSquareSecond().value == other.toKilogramPerMeterSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramPerMeterSquareSecond().value.hashCode()


}

val Byte.kilopascal
    get() = Kilopascal(this)


val Short.kilopascal
    get() = Kilopascal(this)


val Int.kilopascal
    get() = Kilopascal(this)


val Long.kilopascal
    get() = Kilopascal(this)


val Float.kilopascal
    get() = Kilopascal(this)


val Double.kilopascal
    get() = Kilopascal(this)


data class Megapascal(override val value: MeasurementUnitValue) : Pressure {

    override val unitName: String = "megapascal"

    override fun formatters(language: MeasurementUnitLanguage) = language.pressureMegapascal
    override fun toKilogramPerMeterSquareSecond() = KilogramPerMeterSquareSecond(value * (1 * (1e6)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Megapascal(0.0)
        fun fromKilogramPerMeterSquareSecond(value: KilogramPerMeterSquareSecond) = Megapascal((value.value - 0) / (1 * (1e6)))
    }

    operator fun plus(other: Megapascal): Megapascal = Megapascal(this.value + other.value)
    operator fun minus(other: Megapascal): Megapascal = Megapascal(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Megapascal = Megapascal(this.value.times(other))
    override operator fun div(other: Byte): Megapascal = Megapascal(this.value.div(other))
    override operator fun rem(other: Byte): Megapascal = Megapascal(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Megapascal = Megapascal(this.value.times(other))
    override operator fun div(other: Short): Megapascal = Megapascal(this.value.div(other))
    override operator fun rem(other: Short): Megapascal = Megapascal(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Megapascal = Megapascal(this.value.times(other))
    override operator fun div(other: Int): Megapascal = Megapascal(this.value.div(other))
    override operator fun rem(other: Int): Megapascal = Megapascal(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Megapascal = Megapascal(this.value.times(other))
    override operator fun div(other: Long): Megapascal = Megapascal(this.value.div(other))
    override operator fun rem(other: Long): Megapascal = Megapascal(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Megapascal = Megapascal(this.value.times(other))
    override operator fun div(other: Float): Megapascal = Megapascal(this.value.div(other))
    override operator fun rem(other: Float): Megapascal = Megapascal(this.value.rem(other))


    override operator fun times(other: Double): Megapascal = Megapascal(this.value.times(other))
    override operator fun div(other: Double): Megapascal = Megapascal(this.value.div(other))
    override operator fun rem(other: Double): Megapascal = Megapascal(this.value.rem(other))


    override operator fun unaryMinus(): Megapascal = Megapascal(-value)
    override operator fun unaryPlus(): Megapascal = this
    override operator fun inc(): Megapascal = Megapascal(this.value + 1)
    override operator fun dec(): Megapascal = Megapascal(this.value - 1)

    override fun round(): Megapascal = Megapascal(round(this.value))
    override fun truncate(): Megapascal = Megapascal(truncate(this.value))
    override fun floor(): Megapascal = Megapascal(floor(this.value))
    override fun ceil(): Megapascal = Megapascal(ceil(this.value))
    override fun abs(): Megapascal = Megapascal(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Megapascal -> this.value == other.value
        is Pressure -> this.toKilogramPerMeterSquareSecond().value == other.toKilogramPerMeterSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramPerMeterSquareSecond().value.hashCode()


}

val Byte.megapascal
    get() = Megapascal(this)


val Short.megapascal
    get() = Megapascal(this)


val Int.megapascal
    get() = Megapascal(this)


val Long.megapascal
    get() = Megapascal(this)


val Float.megapascal
    get() = Megapascal(this)


val Double.megapascal
    get() = Megapascal(this)


data class GasolineEnergyDensity(override val value: MeasurementUnitValue) : Pressure {

    override val unitName: String = "gasoline-energy-density"

    override fun formatters(language: MeasurementUnitLanguage) = language.pressureGasolineEnergyDensity
    override fun toKilogramPerMeterSquareSecond() = KilogramPerMeterSquareSecond(value * (1545819.2751269776) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = GasolineEnergyDensity(0.0)
        fun fromKilogramPerMeterSquareSecond(value: KilogramPerMeterSquareSecond) = GasolineEnergyDensity((value.value - 0) / (1545819.2751269776))
    }

    operator fun plus(other: GasolineEnergyDensity): GasolineEnergyDensity = GasolineEnergyDensity(this.value + other.value)
    operator fun minus(other: GasolineEnergyDensity): GasolineEnergyDensity = GasolineEnergyDensity(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): GasolineEnergyDensity = GasolineEnergyDensity(this.value.times(other))
    override operator fun div(other: Byte): GasolineEnergyDensity = GasolineEnergyDensity(this.value.div(other))
    override operator fun rem(other: Byte): GasolineEnergyDensity = GasolineEnergyDensity(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): GasolineEnergyDensity = GasolineEnergyDensity(this.value.times(other))
    override operator fun div(other: Short): GasolineEnergyDensity = GasolineEnergyDensity(this.value.div(other))
    override operator fun rem(other: Short): GasolineEnergyDensity = GasolineEnergyDensity(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): GasolineEnergyDensity = GasolineEnergyDensity(this.value.times(other))
    override operator fun div(other: Int): GasolineEnergyDensity = GasolineEnergyDensity(this.value.div(other))
    override operator fun rem(other: Int): GasolineEnergyDensity = GasolineEnergyDensity(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): GasolineEnergyDensity = GasolineEnergyDensity(this.value.times(other))
    override operator fun div(other: Long): GasolineEnergyDensity = GasolineEnergyDensity(this.value.div(other))
    override operator fun rem(other: Long): GasolineEnergyDensity = GasolineEnergyDensity(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): GasolineEnergyDensity = GasolineEnergyDensity(this.value.times(other))
    override operator fun div(other: Float): GasolineEnergyDensity = GasolineEnergyDensity(this.value.div(other))
    override operator fun rem(other: Float): GasolineEnergyDensity = GasolineEnergyDensity(this.value.rem(other))


    override operator fun times(other: Double): GasolineEnergyDensity = GasolineEnergyDensity(this.value.times(other))
    override operator fun div(other: Double): GasolineEnergyDensity = GasolineEnergyDensity(this.value.div(other))
    override operator fun rem(other: Double): GasolineEnergyDensity = GasolineEnergyDensity(this.value.rem(other))


    override operator fun unaryMinus(): GasolineEnergyDensity = GasolineEnergyDensity(-value)
    override operator fun unaryPlus(): GasolineEnergyDensity = this
    override operator fun inc(): GasolineEnergyDensity = GasolineEnergyDensity(this.value + 1)
    override operator fun dec(): GasolineEnergyDensity = GasolineEnergyDensity(this.value - 1)

    override fun round(): GasolineEnergyDensity = GasolineEnergyDensity(round(this.value))
    override fun truncate(): GasolineEnergyDensity = GasolineEnergyDensity(truncate(this.value))
    override fun floor(): GasolineEnergyDensity = GasolineEnergyDensity(floor(this.value))
    override fun ceil(): GasolineEnergyDensity = GasolineEnergyDensity(ceil(this.value))
    override fun abs(): GasolineEnergyDensity = GasolineEnergyDensity(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is GasolineEnergyDensity -> this.value == other.value
        is Pressure -> this.toKilogramPerMeterSquareSecond().value == other.toKilogramPerMeterSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramPerMeterSquareSecond().value.hashCode()


}

val Byte.gasolineEnergyDensity
    get() = GasolineEnergyDensity(this)


val Short.gasolineEnergyDensity
    get() = GasolineEnergyDensity(this)


val Int.gasolineEnergyDensity
    get() = GasolineEnergyDensity(this)


val Long.gasolineEnergyDensity
    get() = GasolineEnergyDensity(this)


val Float.gasolineEnergyDensity
    get() = GasolineEnergyDensity(this)


val Double.gasolineEnergyDensity
    get() = GasolineEnergyDensity(this)


data class KilogramPerMeterSquareSecond(override val value: MeasurementUnitValue) : Pressure {

    override val unitName: String = "kilogram-per-meter-square-second"

    override fun formatters(language: MeasurementUnitLanguage) = language.pressureKilogramPerMeterSquareSecond
    override fun toKilogramPerMeterSquareSecond() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = KilogramPerMeterSquareSecond(0.0)
        fun fromKilogramPerMeterSquareSecond(value: KilogramPerMeterSquareSecond) = value
    }

    operator fun plus(other: KilogramPerMeterSquareSecond): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value + other.value)
    operator fun minus(other: KilogramPerMeterSquareSecond): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.times(other))
    override operator fun div(other: Byte): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.div(other))
    override operator fun rem(other: Byte): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.times(other))
    override operator fun div(other: Short): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.div(other))
    override operator fun rem(other: Short): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.times(other))
    override operator fun div(other: Int): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.div(other))
    override operator fun rem(other: Int): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.times(other))
    override operator fun div(other: Long): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.div(other))
    override operator fun rem(other: Long): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.times(other))
    override operator fun div(other: Float): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.div(other))
    override operator fun rem(other: Float): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.rem(other))


    override operator fun times(other: Double): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.times(other))
    override operator fun div(other: Double): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.div(other))
    override operator fun rem(other: Double): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value.rem(other))


    override operator fun unaryMinus(): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(-value)
    override operator fun unaryPlus(): KilogramPerMeterSquareSecond = this
    override operator fun inc(): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value + 1)
    override operator fun dec(): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(this.value - 1)

    override fun round(): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(round(this.value))
    override fun truncate(): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(truncate(this.value))
    override fun floor(): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(floor(this.value))
    override fun ceil(): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(ceil(this.value))
    override fun abs(): KilogramPerMeterSquareSecond = KilogramPerMeterSquareSecond(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is KilogramPerMeterSquareSecond -> this.value == other.value
        is Pressure -> this.toKilogramPerMeterSquareSecond().value == other.toKilogramPerMeterSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramPerMeterSquareSecond().value.hashCode()


}

val Byte.kilogramPerMeterSquareSecond
    get() = KilogramPerMeterSquareSecond(this)


val Short.kilogramPerMeterSquareSecond
    get() = KilogramPerMeterSquareSecond(this)


val Int.kilogramPerMeterSquareSecond
    get() = KilogramPerMeterSquareSecond(this)


val Long.kilogramPerMeterSquareSecond
    get() = KilogramPerMeterSquareSecond(this)


val Float.kilogramPerMeterSquareSecond
    get() = KilogramPerMeterSquareSecond(this)


val Double.kilogramPerMeterSquareSecond
    get() = KilogramPerMeterSquareSecond(this)
                            
