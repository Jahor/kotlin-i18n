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


    operator fun times(other: kotlin.Byte): Speed
    operator fun div(other: kotlin.Byte): Speed
    operator fun rem(other: kotlin.Byte): Speed


    operator fun times(other: kotlin.Short): Speed
    operator fun div(other: kotlin.Short): Speed
    operator fun rem(other: kotlin.Short): Speed


    operator fun times(other: kotlin.Int): Speed
    operator fun div(other: kotlin.Int): Speed
    operator fun rem(other: kotlin.Int): Speed


    operator fun times(other: kotlin.Long): Speed
    operator fun div(other: kotlin.Long): Speed
    operator fun rem(other: kotlin.Long): Speed


    operator fun times(other: kotlin.Float): Speed
    operator fun div(other: kotlin.Float): Speed
    operator fun rem(other: kotlin.Float): Speed


    operator fun times(other: kotlin.Double): Speed
    operator fun div(other: kotlin.Double): Speed
    operator fun rem(other: kotlin.Double): Speed

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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): KilometerPerHour = KilometerPerHour(this.value.times(other))
    override operator fun div(other: kotlin.Byte): KilometerPerHour = KilometerPerHour(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): KilometerPerHour = KilometerPerHour(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): KilometerPerHour = KilometerPerHour(this.value.times(other))
    override operator fun div(other: kotlin.Short): KilometerPerHour = KilometerPerHour(this.value.div(other))
    override operator fun rem(other: kotlin.Short): KilometerPerHour = KilometerPerHour(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): KilometerPerHour = KilometerPerHour(this.value.times(other))
    override operator fun div(other: kotlin.Int): KilometerPerHour = KilometerPerHour(this.value.div(other))
    override operator fun rem(other: kotlin.Int): KilometerPerHour = KilometerPerHour(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): KilometerPerHour = KilometerPerHour(this.value.times(other))
    override operator fun div(other: kotlin.Long): KilometerPerHour = KilometerPerHour(this.value.div(other))
    override operator fun rem(other: kotlin.Long): KilometerPerHour = KilometerPerHour(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): KilometerPerHour = KilometerPerHour(this.value.times(other))
    override operator fun div(other: kotlin.Float): KilometerPerHour = KilometerPerHour(this.value.div(other))
    override operator fun rem(other: kotlin.Float): KilometerPerHour = KilometerPerHour(this.value.rem(other))


    override operator fun times(other: kotlin.Double): KilometerPerHour = KilometerPerHour(this.value.times(other))
    override operator fun div(other: kotlin.Double): KilometerPerHour = KilometerPerHour(this.value.div(other))
    override operator fun rem(other: kotlin.Double): KilometerPerHour = KilometerPerHour(this.value.rem(other))


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

val kotlin.Byte.kilometerPerHour
    get() = KilometerPerHour(this)


val kotlin.Short.kilometerPerHour
    get() = KilometerPerHour(this)


val kotlin.Int.kilometerPerHour
    get() = KilometerPerHour(this)


val kotlin.Long.kilometerPerHour
    get() = KilometerPerHour(this)


val kotlin.Float.kilometerPerHour
    get() = KilometerPerHour(this)


val kotlin.Double.kilometerPerHour
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): MeterPerSecond = MeterPerSecond(this.value.times(other))
    override operator fun div(other: kotlin.Byte): MeterPerSecond = MeterPerSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): MeterPerSecond = MeterPerSecond(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): MeterPerSecond = MeterPerSecond(this.value.times(other))
    override operator fun div(other: kotlin.Short): MeterPerSecond = MeterPerSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Short): MeterPerSecond = MeterPerSecond(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): MeterPerSecond = MeterPerSecond(this.value.times(other))
    override operator fun div(other: kotlin.Int): MeterPerSecond = MeterPerSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Int): MeterPerSecond = MeterPerSecond(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): MeterPerSecond = MeterPerSecond(this.value.times(other))
    override operator fun div(other: kotlin.Long): MeterPerSecond = MeterPerSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Long): MeterPerSecond = MeterPerSecond(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): MeterPerSecond = MeterPerSecond(this.value.times(other))
    override operator fun div(other: kotlin.Float): MeterPerSecond = MeterPerSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Float): MeterPerSecond = MeterPerSecond(this.value.rem(other))


    override operator fun times(other: kotlin.Double): MeterPerSecond = MeterPerSecond(this.value.times(other))
    override operator fun div(other: kotlin.Double): MeterPerSecond = MeterPerSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Double): MeterPerSecond = MeterPerSecond(this.value.rem(other))


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

val kotlin.Byte.meterPerSecond
    get() = MeterPerSecond(this)


val kotlin.Short.meterPerSecond
    get() = MeterPerSecond(this)


val kotlin.Int.meterPerSecond
    get() = MeterPerSecond(this)


val kotlin.Long.meterPerSecond
    get() = MeterPerSecond(this)


val kotlin.Float.meterPerSecond
    get() = MeterPerSecond(this)


val kotlin.Double.meterPerSecond
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): MilePerHour = MilePerHour(this.value.times(other))
    override operator fun div(other: kotlin.Byte): MilePerHour = MilePerHour(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): MilePerHour = MilePerHour(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): MilePerHour = MilePerHour(this.value.times(other))
    override operator fun div(other: kotlin.Short): MilePerHour = MilePerHour(this.value.div(other))
    override operator fun rem(other: kotlin.Short): MilePerHour = MilePerHour(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): MilePerHour = MilePerHour(this.value.times(other))
    override operator fun div(other: kotlin.Int): MilePerHour = MilePerHour(this.value.div(other))
    override operator fun rem(other: kotlin.Int): MilePerHour = MilePerHour(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): MilePerHour = MilePerHour(this.value.times(other))
    override operator fun div(other: kotlin.Long): MilePerHour = MilePerHour(this.value.div(other))
    override operator fun rem(other: kotlin.Long): MilePerHour = MilePerHour(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): MilePerHour = MilePerHour(this.value.times(other))
    override operator fun div(other: kotlin.Float): MilePerHour = MilePerHour(this.value.div(other))
    override operator fun rem(other: kotlin.Float): MilePerHour = MilePerHour(this.value.rem(other))


    override operator fun times(other: kotlin.Double): MilePerHour = MilePerHour(this.value.times(other))
    override operator fun div(other: kotlin.Double): MilePerHour = MilePerHour(this.value.div(other))
    override operator fun rem(other: kotlin.Double): MilePerHour = MilePerHour(this.value.rem(other))


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

val kotlin.Byte.milePerHour
    get() = MilePerHour(this)


val kotlin.Short.milePerHour
    get() = MilePerHour(this)


val kotlin.Int.milePerHour
    get() = MilePerHour(this)


val kotlin.Long.milePerHour
    get() = MilePerHour(this)


val kotlin.Float.milePerHour
    get() = MilePerHour(this)


val kotlin.Double.milePerHour
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Knot = Knot(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Knot = Knot(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Knot = Knot(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Knot = Knot(this.value.times(other))
    override operator fun div(other: kotlin.Short): Knot = Knot(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Knot = Knot(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Knot = Knot(this.value.times(other))
    override operator fun div(other: kotlin.Int): Knot = Knot(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Knot = Knot(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Knot = Knot(this.value.times(other))
    override operator fun div(other: kotlin.Long): Knot = Knot(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Knot = Knot(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Knot = Knot(this.value.times(other))
    override operator fun div(other: kotlin.Float): Knot = Knot(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Knot = Knot(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Knot = Knot(this.value.times(other))
    override operator fun div(other: kotlin.Double): Knot = Knot(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Knot = Knot(this.value.rem(other))


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

val kotlin.Byte.knot
    get() = Knot(this)


val kotlin.Short.knot
    get() = Knot(this)


val kotlin.Int.knot
    get() = Knot(this)


val kotlin.Long.knot
    get() = Knot(this)


val kotlin.Float.knot
    get() = Knot(this)


val kotlin.Double.knot
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

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Beaufort = Beaufort(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Beaufort = Beaufort(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Beaufort = Beaufort(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Beaufort = Beaufort(this.value.times(other))
    override operator fun div(other: kotlin.Short): Beaufort = Beaufort(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Beaufort = Beaufort(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Beaufort = Beaufort(this.value.times(other))
    override operator fun div(other: kotlin.Int): Beaufort = Beaufort(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Beaufort = Beaufort(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Beaufort = Beaufort(this.value.times(other))
    override operator fun div(other: kotlin.Long): Beaufort = Beaufort(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Beaufort = Beaufort(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Beaufort = Beaufort(this.value.times(other))
    override operator fun div(other: kotlin.Float): Beaufort = Beaufort(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Beaufort = Beaufort(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Beaufort = Beaufort(this.value.times(other))
    override operator fun div(other: kotlin.Double): Beaufort = Beaufort(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Beaufort = Beaufort(this.value.rem(other))


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

val kotlin.Byte.beaufort
    get() = Beaufort(this)


val kotlin.Short.beaufort
    get() = Beaufort(this)


val kotlin.Int.beaufort
    get() = Beaufort(this)


val kotlin.Long.beaufort
    get() = Beaufort(this)


val kotlin.Float.beaufort
    get() = Beaufort(this)


val kotlin.Double.beaufort
    get() = Beaufort(this)
                            
