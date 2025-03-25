package info.leonenko.i18n.units.speed

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

@Serializable(Speed.Serializer::class)
interface Speed : MeasurementUnit, Comparable<Speed> {
    override val measurement: String get() = "speed"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Speed> = emptyMap()) : KSerializer<Speed> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Speed", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Speed {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Speed unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "kilometer-per-hour" -> KilometerPerHour(value)
                "meter-per-second" -> MeterPerSecond(value)
                "mile-per-hour" -> MilePerHour(value)
                "knot" -> Knot(value)
                "beaufort" -> Beaufort(value)
                "light-speed" -> LightSpeed(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Speed unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Speed) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toKilometerPerHour(): KilometerPerHour {
        return KilometerPerHour.fromMeterPerSecond(toMeterPerSecond())
    }

    fun toMeterPerSecond(): MeterPerSecond
    override fun getBaseUnitValue() = toMeterPerSecond().value
    fun toMilePerHour(): MilePerHour {
        return MilePerHour.fromMeterPerSecond(toMeterPerSecond())
    }

    fun toKnot(): Knot {
        return Knot.fromMeterPerSecond(toMeterPerSecond())
    }

    fun toBeaufort(): Beaufort {
        return Beaufort.fromMeterPerSecond(toMeterPerSecond())
    }

    fun toLightSpeed(): LightSpeed {
        return LightSpeed.fromMeterPerSecond(toMeterPerSecond())
    }

    companion object {
        val ZERO: Speed = MeterPerSecond(0.0)

    }

    override operator fun compareTo(other: Speed): Int = this.toMeterPerSecond().value.compareTo(other.toMeterPerSecond().value)

    operator fun plus(other: Speed): MeterPerSecond = this.toMeterPerSecond() + other.toMeterPerSecond()
    operator fun minus(other: Speed): MeterPerSecond = this.toMeterPerSecond() - other.toMeterPerSecond()
    operator fun div(other: Speed): Double = this.toMeterPerSecond().value / other.toMeterPerSecond().value
    operator fun unaryMinus(): Speed
    operator fun unaryPlus(): Speed
    operator fun inc(): Speed
    operator fun dec(): Speed

    fun round(): Speed
    fun truncate(): Speed
    fun floor(): Speed
    fun ceil(): Speed
    fun abs(): Speed


    operator fun times(other: Byte): Speed
    operator fun div(other: Byte): Speed
    operator fun rem(other: Byte): Speed


    operator fun times(other: Short): Speed
    operator fun div(other: Short): Speed
    operator fun rem(other: Short): Speed


    operator fun times(other: Int): Speed
    operator fun div(other: Int): Speed
    operator fun rem(other: Int): Speed


    operator fun times(other: Long): Speed
    operator fun div(other: Long): Speed
    operator fun rem(other: Long): Speed


    operator fun times(other: Float): Speed
    operator fun div(other: Float): Speed
    operator fun rem(other: Float): Speed


    operator fun times(other: Double): Speed
    operator fun div(other: Double): Speed
    operator fun rem(other: Double): Speed

}

data class KilometerPerHour(override val value: MeasurementUnitValue) : Speed {

    override val unitName: String = "kilometer-per-hour"

    override fun formatters(language: MeasurementUnitLanguage) = language.speedKilometerPerHour
    override fun toMeterPerSecond() = MeterPerSecond(value * ((1 * (1e3)) / (3600)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = KilometerPerHour(0.0)
        fun fromMeterPerSecond(value: MeterPerSecond) = KilometerPerHour((value.value - 0) / ((1 * (1e3)) / (3600)))
    }

    operator fun plus(other: KilometerPerHour): KilometerPerHour = KilometerPerHour(this.value + other.value)
    operator fun minus(other: KilometerPerHour): KilometerPerHour = KilometerPerHour(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): KilometerPerHour = KilometerPerHour(this.value.times(other))
    override operator fun div(other: Byte): KilometerPerHour = KilometerPerHour(this.value.div(other))
    override operator fun rem(other: Byte): KilometerPerHour = KilometerPerHour(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): KilometerPerHour = KilometerPerHour(this.value.times(other))
    override operator fun div(other: Short): KilometerPerHour = KilometerPerHour(this.value.div(other))
    override operator fun rem(other: Short): KilometerPerHour = KilometerPerHour(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): KilometerPerHour = KilometerPerHour(this.value.times(other))
    override operator fun div(other: Int): KilometerPerHour = KilometerPerHour(this.value.div(other))
    override operator fun rem(other: Int): KilometerPerHour = KilometerPerHour(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): KilometerPerHour = KilometerPerHour(this.value.times(other))
    override operator fun div(other: Long): KilometerPerHour = KilometerPerHour(this.value.div(other))
    override operator fun rem(other: Long): KilometerPerHour = KilometerPerHour(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): KilometerPerHour = KilometerPerHour(this.value.times(other))
    override operator fun div(other: Float): KilometerPerHour = KilometerPerHour(this.value.div(other))
    override operator fun rem(other: Float): KilometerPerHour = KilometerPerHour(this.value.rem(other))


    override operator fun times(other: Double): KilometerPerHour = KilometerPerHour(this.value.times(other))
    override operator fun div(other: Double): KilometerPerHour = KilometerPerHour(this.value.div(other))
    override operator fun rem(other: Double): KilometerPerHour = KilometerPerHour(this.value.rem(other))


    override operator fun unaryMinus(): KilometerPerHour = KilometerPerHour(-value)
    override operator fun unaryPlus(): KilometerPerHour = this
    override operator fun inc(): KilometerPerHour = KilometerPerHour(this.value + 1)
    override operator fun dec(): KilometerPerHour = KilometerPerHour(this.value - 1)

    override fun round(): KilometerPerHour = KilometerPerHour(round(this.value))
    override fun truncate(): KilometerPerHour = KilometerPerHour(truncate(this.value))
    override fun floor(): KilometerPerHour = KilometerPerHour(floor(this.value))
    override fun ceil(): KilometerPerHour = KilometerPerHour(ceil(this.value))
    override fun abs(): KilometerPerHour = KilometerPerHour(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is KilometerPerHour -> this.value == other.value
        is Speed -> this.toMeterPerSecond().value == other.toMeterPerSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeterPerSecond().value.hashCode()


}

val Byte.kilometerPerHour
    get() = KilometerPerHour(this)


val Short.kilometerPerHour
    get() = KilometerPerHour(this)


val Int.kilometerPerHour
    get() = KilometerPerHour(this)


val Long.kilometerPerHour
    get() = KilometerPerHour(this)


val Float.kilometerPerHour
    get() = KilometerPerHour(this)


val Double.kilometerPerHour
    get() = KilometerPerHour(this)


data class MeterPerSecond(override val value: MeasurementUnitValue) : Speed {

    override val unitName: String = "meter-per-second"

    override fun formatters(language: MeasurementUnitLanguage) = language.speedMeterPerSecond
    override fun toMeterPerSecond() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = MeterPerSecond(0.0)
        fun fromMeterPerSecond(value: MeterPerSecond) = value
    }

    operator fun plus(other: MeterPerSecond): MeterPerSecond = MeterPerSecond(this.value + other.value)
    operator fun minus(other: MeterPerSecond): MeterPerSecond = MeterPerSecond(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): MeterPerSecond = MeterPerSecond(this.value.times(other))
    override operator fun div(other: Byte): MeterPerSecond = MeterPerSecond(this.value.div(other))
    override operator fun rem(other: Byte): MeterPerSecond = MeterPerSecond(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): MeterPerSecond = MeterPerSecond(this.value.times(other))
    override operator fun div(other: Short): MeterPerSecond = MeterPerSecond(this.value.div(other))
    override operator fun rem(other: Short): MeterPerSecond = MeterPerSecond(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): MeterPerSecond = MeterPerSecond(this.value.times(other))
    override operator fun div(other: Int): MeterPerSecond = MeterPerSecond(this.value.div(other))
    override operator fun rem(other: Int): MeterPerSecond = MeterPerSecond(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): MeterPerSecond = MeterPerSecond(this.value.times(other))
    override operator fun div(other: Long): MeterPerSecond = MeterPerSecond(this.value.div(other))
    override operator fun rem(other: Long): MeterPerSecond = MeterPerSecond(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): MeterPerSecond = MeterPerSecond(this.value.times(other))
    override operator fun div(other: Float): MeterPerSecond = MeterPerSecond(this.value.div(other))
    override operator fun rem(other: Float): MeterPerSecond = MeterPerSecond(this.value.rem(other))


    override operator fun times(other: Double): MeterPerSecond = MeterPerSecond(this.value.times(other))
    override operator fun div(other: Double): MeterPerSecond = MeterPerSecond(this.value.div(other))
    override operator fun rem(other: Double): MeterPerSecond = MeterPerSecond(this.value.rem(other))


    override operator fun unaryMinus(): MeterPerSecond = MeterPerSecond(-value)
    override operator fun unaryPlus(): MeterPerSecond = this
    override operator fun inc(): MeterPerSecond = MeterPerSecond(this.value + 1)
    override operator fun dec(): MeterPerSecond = MeterPerSecond(this.value - 1)

    override fun round(): MeterPerSecond = MeterPerSecond(round(this.value))
    override fun truncate(): MeterPerSecond = MeterPerSecond(truncate(this.value))
    override fun floor(): MeterPerSecond = MeterPerSecond(floor(this.value))
    override fun ceil(): MeterPerSecond = MeterPerSecond(ceil(this.value))
    override fun abs(): MeterPerSecond = MeterPerSecond(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is MeterPerSecond -> this.value == other.value
        is Speed -> this.toMeterPerSecond().value == other.toMeterPerSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeterPerSecond().value.hashCode()


}

val Byte.meterPerSecond
    get() = MeterPerSecond(this)


val Short.meterPerSecond
    get() = MeterPerSecond(this)


val Int.meterPerSecond
    get() = MeterPerSecond(this)


val Long.meterPerSecond
    get() = MeterPerSecond(this)


val Float.meterPerSecond
    get() = MeterPerSecond(this)


val Double.meterPerSecond
    get() = MeterPerSecond(this)


data class MilePerHour(override val value: MeasurementUnitValue) : Speed {

    override val unitName: String = "mile-per-hour"

    override fun formatters(language: MeasurementUnitLanguage) = language.speedMilePerHour
    override fun toMeterPerSecond() = MeterPerSecond(value * ((1609.344) / (3600)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = MilePerHour(0.0)
        fun fromMeterPerSecond(value: MeterPerSecond) = MilePerHour((value.value - 0) / ((1609.344) / (3600)))
    }

    operator fun plus(other: MilePerHour): MilePerHour = MilePerHour(this.value + other.value)
    operator fun minus(other: MilePerHour): MilePerHour = MilePerHour(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): MilePerHour = MilePerHour(this.value.times(other))
    override operator fun div(other: Byte): MilePerHour = MilePerHour(this.value.div(other))
    override operator fun rem(other: Byte): MilePerHour = MilePerHour(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): MilePerHour = MilePerHour(this.value.times(other))
    override operator fun div(other: Short): MilePerHour = MilePerHour(this.value.div(other))
    override operator fun rem(other: Short): MilePerHour = MilePerHour(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): MilePerHour = MilePerHour(this.value.times(other))
    override operator fun div(other: Int): MilePerHour = MilePerHour(this.value.div(other))
    override operator fun rem(other: Int): MilePerHour = MilePerHour(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): MilePerHour = MilePerHour(this.value.times(other))
    override operator fun div(other: Long): MilePerHour = MilePerHour(this.value.div(other))
    override operator fun rem(other: Long): MilePerHour = MilePerHour(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): MilePerHour = MilePerHour(this.value.times(other))
    override operator fun div(other: Float): MilePerHour = MilePerHour(this.value.div(other))
    override operator fun rem(other: Float): MilePerHour = MilePerHour(this.value.rem(other))


    override operator fun times(other: Double): MilePerHour = MilePerHour(this.value.times(other))
    override operator fun div(other: Double): MilePerHour = MilePerHour(this.value.div(other))
    override operator fun rem(other: Double): MilePerHour = MilePerHour(this.value.rem(other))


    override operator fun unaryMinus(): MilePerHour = MilePerHour(-value)
    override operator fun unaryPlus(): MilePerHour = this
    override operator fun inc(): MilePerHour = MilePerHour(this.value + 1)
    override operator fun dec(): MilePerHour = MilePerHour(this.value - 1)

    override fun round(): MilePerHour = MilePerHour(round(this.value))
    override fun truncate(): MilePerHour = MilePerHour(truncate(this.value))
    override fun floor(): MilePerHour = MilePerHour(floor(this.value))
    override fun ceil(): MilePerHour = MilePerHour(ceil(this.value))
    override fun abs(): MilePerHour = MilePerHour(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is MilePerHour -> this.value == other.value
        is Speed -> this.toMeterPerSecond().value == other.toMeterPerSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeterPerSecond().value.hashCode()


}

val Byte.milePerHour
    get() = MilePerHour(this)


val Short.milePerHour
    get() = MilePerHour(this)


val Int.milePerHour
    get() = MilePerHour(this)


val Long.milePerHour
    get() = MilePerHour(this)


val Float.milePerHour
    get() = MilePerHour(this)


val Double.milePerHour
    get() = MilePerHour(this)


data class Knot(override val value: MeasurementUnitValue) : Speed {

    override val unitName: String = "knot"

    override fun formatters(language: MeasurementUnitLanguage) = language.speedKnot
    override fun toMeterPerSecond() = MeterPerSecond(value * (0.5144444444444445) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Knot(0.0)
        fun fromMeterPerSecond(value: MeterPerSecond) = Knot((value.value - 0) / (0.5144444444444445))
    }

    operator fun plus(other: Knot): Knot = Knot(this.value + other.value)
    operator fun minus(other: Knot): Knot = Knot(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Knot = Knot(this.value.times(other))
    override operator fun div(other: Byte): Knot = Knot(this.value.div(other))
    override operator fun rem(other: Byte): Knot = Knot(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Knot = Knot(this.value.times(other))
    override operator fun div(other: Short): Knot = Knot(this.value.div(other))
    override operator fun rem(other: Short): Knot = Knot(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Knot = Knot(this.value.times(other))
    override operator fun div(other: Int): Knot = Knot(this.value.div(other))
    override operator fun rem(other: Int): Knot = Knot(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Knot = Knot(this.value.times(other))
    override operator fun div(other: Long): Knot = Knot(this.value.div(other))
    override operator fun rem(other: Long): Knot = Knot(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Knot = Knot(this.value.times(other))
    override operator fun div(other: Float): Knot = Knot(this.value.div(other))
    override operator fun rem(other: Float): Knot = Knot(this.value.rem(other))


    override operator fun times(other: Double): Knot = Knot(this.value.times(other))
    override operator fun div(other: Double): Knot = Knot(this.value.div(other))
    override operator fun rem(other: Double): Knot = Knot(this.value.rem(other))


    override operator fun unaryMinus(): Knot = Knot(-value)
    override operator fun unaryPlus(): Knot = this
    override operator fun inc(): Knot = Knot(this.value + 1)
    override operator fun dec(): Knot = Knot(this.value - 1)

    override fun round(): Knot = Knot(round(this.value))
    override fun truncate(): Knot = Knot(truncate(this.value))
    override fun floor(): Knot = Knot(floor(this.value))
    override fun ceil(): Knot = Knot(ceil(this.value))
    override fun abs(): Knot = Knot(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Knot -> this.value == other.value
        is Speed -> this.toMeterPerSecond().value == other.toMeterPerSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeterPerSecond().value.hashCode()


}

val Byte.knot
    get() = Knot(this)


val Short.knot
    get() = Knot(this)


val Int.knot
    get() = Knot(this)


val Long.knot
    get() = Knot(this)


val Float.knot
    get() = Knot(this)


val Double.knot
    get() = Knot(this)


data class Beaufort(override val value: MeasurementUnitValue) : Speed {

    override val unitName: String = "beaufort"

    override fun formatters(language: MeasurementUnitLanguage) = language.speedBeaufort
    override fun toMeterPerSecond() = MeterPerSecond(value * (1) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Beaufort(0.0)
        fun fromMeterPerSecond(value: MeterPerSecond) = Beaufort((value.value - 0) / (1))
    }

    operator fun plus(other: Beaufort): Beaufort = Beaufort(this.value + other.value)
    operator fun minus(other: Beaufort): Beaufort = Beaufort(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): Beaufort = Beaufort(this.value.times(other))
    override operator fun div(other: Byte): Beaufort = Beaufort(this.value.div(other))
    override operator fun rem(other: Byte): Beaufort = Beaufort(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): Beaufort = Beaufort(this.value.times(other))
    override operator fun div(other: Short): Beaufort = Beaufort(this.value.div(other))
    override operator fun rem(other: Short): Beaufort = Beaufort(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): Beaufort = Beaufort(this.value.times(other))
    override operator fun div(other: Int): Beaufort = Beaufort(this.value.div(other))
    override operator fun rem(other: Int): Beaufort = Beaufort(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): Beaufort = Beaufort(this.value.times(other))
    override operator fun div(other: Long): Beaufort = Beaufort(this.value.div(other))
    override operator fun rem(other: Long): Beaufort = Beaufort(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): Beaufort = Beaufort(this.value.times(other))
    override operator fun div(other: Float): Beaufort = Beaufort(this.value.div(other))
    override operator fun rem(other: Float): Beaufort = Beaufort(this.value.rem(other))


    override operator fun times(other: Double): Beaufort = Beaufort(this.value.times(other))
    override operator fun div(other: Double): Beaufort = Beaufort(this.value.div(other))
    override operator fun rem(other: Double): Beaufort = Beaufort(this.value.rem(other))


    override operator fun unaryMinus(): Beaufort = Beaufort(-value)
    override operator fun unaryPlus(): Beaufort = this
    override operator fun inc(): Beaufort = Beaufort(this.value + 1)
    override operator fun dec(): Beaufort = Beaufort(this.value - 1)

    override fun round(): Beaufort = Beaufort(round(this.value))
    override fun truncate(): Beaufort = Beaufort(truncate(this.value))
    override fun floor(): Beaufort = Beaufort(floor(this.value))
    override fun ceil(): Beaufort = Beaufort(ceil(this.value))
    override fun abs(): Beaufort = Beaufort(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Beaufort -> this.value == other.value
        is Speed -> this.toMeterPerSecond().value == other.toMeterPerSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeterPerSecond().value.hashCode()


}

val Byte.beaufort
    get() = Beaufort(this)


val Short.beaufort
    get() = Beaufort(this)


val Int.beaufort
    get() = Beaufort(this)


val Long.beaufort
    get() = Beaufort(this)


val Float.beaufort
    get() = Beaufort(this)


val Double.beaufort
    get() = Beaufort(this)


data class LightSpeed(override val value: MeasurementUnitValue) : Speed {

    override val unitName: String = "light-speed"

    override fun formatters(language: MeasurementUnitLanguage) = language.speedLightSpeed
    override fun toMeterPerSecond() = MeterPerSecond(value * (299792458) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = LightSpeed(0.0)
        fun fromMeterPerSecond(value: MeterPerSecond) = LightSpeed((value.value - 0) / (299792458))
    }

    operator fun plus(other: LightSpeed): LightSpeed = LightSpeed(this.value + other.value)
    operator fun minus(other: LightSpeed): LightSpeed = LightSpeed(this.value - other.value)

    constructor(value: Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Byte): LightSpeed = LightSpeed(this.value.times(other))
    override operator fun div(other: Byte): LightSpeed = LightSpeed(this.value.div(other))
    override operator fun rem(other: Byte): LightSpeed = LightSpeed(this.value.rem(other))

    constructor(value: Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Short): LightSpeed = LightSpeed(this.value.times(other))
    override operator fun div(other: Short): LightSpeed = LightSpeed(this.value.div(other))
    override operator fun rem(other: Short): LightSpeed = LightSpeed(this.value.rem(other))

    constructor(value: Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Int): LightSpeed = LightSpeed(this.value.times(other))
    override operator fun div(other: Int): LightSpeed = LightSpeed(this.value.div(other))
    override operator fun rem(other: Int): LightSpeed = LightSpeed(this.value.rem(other))

    constructor(value: Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Long): LightSpeed = LightSpeed(this.value.times(other))
    override operator fun div(other: Long): LightSpeed = LightSpeed(this.value.div(other))
    override operator fun rem(other: Long): LightSpeed = LightSpeed(this.value.rem(other))

    constructor(value: Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: Float): LightSpeed = LightSpeed(this.value.times(other))
    override operator fun div(other: Float): LightSpeed = LightSpeed(this.value.div(other))
    override operator fun rem(other: Float): LightSpeed = LightSpeed(this.value.rem(other))


    override operator fun times(other: Double): LightSpeed = LightSpeed(this.value.times(other))
    override operator fun div(other: Double): LightSpeed = LightSpeed(this.value.div(other))
    override operator fun rem(other: Double): LightSpeed = LightSpeed(this.value.rem(other))


    override operator fun unaryMinus(): LightSpeed = LightSpeed(-value)
    override operator fun unaryPlus(): LightSpeed = this
    override operator fun inc(): LightSpeed = LightSpeed(this.value + 1)
    override operator fun dec(): LightSpeed = LightSpeed(this.value - 1)

    override fun round(): LightSpeed = LightSpeed(round(this.value))
    override fun truncate(): LightSpeed = LightSpeed(truncate(this.value))
    override fun floor(): LightSpeed = LightSpeed(floor(this.value))
    override fun ceil(): LightSpeed = LightSpeed(ceil(this.value))
    override fun abs(): LightSpeed = LightSpeed(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is LightSpeed -> this.value == other.value
        is Speed -> this.toMeterPerSecond().value == other.toMeterPerSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toMeterPerSecond().value.hashCode()


}

val Byte.lightSpeed
    get() = LightSpeed(this)


val Short.lightSpeed
    get() = LightSpeed(this)


val Int.lightSpeed
    get() = LightSpeed(this)


val Long.lightSpeed
    get() = LightSpeed(this)


val Float.lightSpeed
    get() = LightSpeed(this)


val Double.lightSpeed
    get() = LightSpeed(this)
                            
