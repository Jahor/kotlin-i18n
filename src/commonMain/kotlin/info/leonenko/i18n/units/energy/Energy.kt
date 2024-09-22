package info.leonenko.i18n.units.energy

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

@Serializable(Energy.Serializer::class)
interface Energy : MeasurementUnit, Comparable<Energy> {
    override val measurement: String get() = "energy"

    class Serializer : CustomSerializer()
    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> Energy> = emptyMap()) : KSerializer<Energy> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())

        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("Energy", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): Energy {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected Energy unit as a key. None found")
            val value = map[key]!!
            return when (key) {
                "kilocalorie" -> Kilocalorie(value)
                "calorie" -> Calorie(value)
                "foodcalorie" -> Foodcalorie(value)
                "kilojoule" -> Kilojoule(value)
                "joule" -> Joule(value)
                "kilowatt-hour" -> KilowattHour(value)
                "electronvolt" -> Electronvolt(value)
                "british-thermal-unit" -> BritishThermalUnit(value)
                "therm-us" -> ThermUs(value)
                "kilogram-square-meter-per-square-second" -> KilogramSquareMeterPerSquareSecond(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown Energy unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: Energy) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }

    fun toKilocalorie(): Kilocalorie {
        return Kilocalorie.fromKilogramSquareMeterPerSquareSecond(toKilogramSquareMeterPerSquareSecond())
    }

    fun toCalorie(): Calorie {
        return Calorie.fromKilogramSquareMeterPerSquareSecond(toKilogramSquareMeterPerSquareSecond())
    }

    fun toFoodcalorie(): Foodcalorie {
        return Foodcalorie.fromKilogramSquareMeterPerSquareSecond(toKilogramSquareMeterPerSquareSecond())
    }

    fun toKilojoule(): Kilojoule {
        return Kilojoule.fromKilogramSquareMeterPerSquareSecond(toKilogramSquareMeterPerSquareSecond())
    }

    fun toJoule(): Joule {
        return Joule.fromKilogramSquareMeterPerSquareSecond(toKilogramSquareMeterPerSquareSecond())
    }

    fun toKilowattHour(): KilowattHour {
        return KilowattHour.fromKilogramSquareMeterPerSquareSecond(toKilogramSquareMeterPerSquareSecond())
    }

    fun toElectronvolt(): Electronvolt {
        return Electronvolt.fromKilogramSquareMeterPerSquareSecond(toKilogramSquareMeterPerSquareSecond())
    }

    fun toBritishThermalUnit(): BritishThermalUnit {
        return BritishThermalUnit.fromKilogramSquareMeterPerSquareSecond(toKilogramSquareMeterPerSquareSecond())
    }

    fun toThermUs(): ThermUs {
        return ThermUs.fromKilogramSquareMeterPerSquareSecond(toKilogramSquareMeterPerSquareSecond())
    }

    fun toKilogramSquareMeterPerSquareSecond(): KilogramSquareMeterPerSquareSecond
    override fun getBaseUnitValue() = toKilogramSquareMeterPerSquareSecond().value

    companion object {
        val ZERO: Energy = KilogramSquareMeterPerSquareSecond(0.0)

    }

    override operator fun compareTo(other: Energy): Int = this.toKilogramSquareMeterPerSquareSecond().value.compareTo(other.toKilogramSquareMeterPerSquareSecond().value)

    operator fun plus(other: Energy): KilogramSquareMeterPerSquareSecond = this.toKilogramSquareMeterPerSquareSecond() + other.toKilogramSquareMeterPerSquareSecond()
    operator fun minus(other: Energy): KilogramSquareMeterPerSquareSecond = this.toKilogramSquareMeterPerSquareSecond() - other.toKilogramSquareMeterPerSquareSecond()
    operator fun div(other: Energy): Double = this.toKilogramSquareMeterPerSquareSecond().value / other.toKilogramSquareMeterPerSquareSecond().value
    operator fun unaryMinus(): Energy
    operator fun unaryPlus(): Energy
    operator fun inc(): Energy
    operator fun dec(): Energy

    fun round(): Energy
    fun truncate(): Energy
    fun floor(): Energy
    fun ceil(): Energy
    fun abs(): Energy


    operator fun times(other: kotlin.Byte): Energy
    operator fun div(other: kotlin.Byte): Energy
    operator fun rem(other: kotlin.Byte): Energy


    operator fun times(other: kotlin.Short): Energy
    operator fun div(other: kotlin.Short): Energy
    operator fun rem(other: kotlin.Short): Energy


    operator fun times(other: kotlin.Int): Energy
    operator fun div(other: kotlin.Int): Energy
    operator fun rem(other: kotlin.Int): Energy


    operator fun times(other: kotlin.Long): Energy
    operator fun div(other: kotlin.Long): Energy
    operator fun rem(other: kotlin.Long): Energy


    operator fun times(other: kotlin.Float): Energy
    operator fun div(other: kotlin.Float): Energy
    operator fun rem(other: kotlin.Float): Energy


    operator fun times(other: kotlin.Double): Energy
    operator fun div(other: kotlin.Double): Energy
    operator fun rem(other: kotlin.Double): Energy

}

data class Kilocalorie(override val value: MeasurementUnitValue) : Energy {

    override val unitName: String = "kilocalorie"

    override fun formatters(language: MeasurementUnitLanguage) = language.energyKilocalorie
    override fun toKilogramSquareMeterPerSquareSecond() = KilogramSquareMeterPerSquareSecond(value * (4.184 * (1e3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Kilocalorie(0.0)
        fun fromKilogramSquareMeterPerSquareSecond(value: KilogramSquareMeterPerSquareSecond) = Kilocalorie((value.value - 0) / (4.184 * (1e3)))
    }

    operator fun plus(other: Kilocalorie): Kilocalorie = Kilocalorie(this.value + other.value)
    operator fun minus(other: Kilocalorie): Kilocalorie = Kilocalorie(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Kilocalorie = Kilocalorie(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Kilocalorie = Kilocalorie(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Kilocalorie = Kilocalorie(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Kilocalorie = Kilocalorie(this.value.times(other))
    override operator fun div(other: kotlin.Short): Kilocalorie = Kilocalorie(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Kilocalorie = Kilocalorie(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Kilocalorie = Kilocalorie(this.value.times(other))
    override operator fun div(other: kotlin.Int): Kilocalorie = Kilocalorie(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Kilocalorie = Kilocalorie(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Kilocalorie = Kilocalorie(this.value.times(other))
    override operator fun div(other: kotlin.Long): Kilocalorie = Kilocalorie(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Kilocalorie = Kilocalorie(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Kilocalorie = Kilocalorie(this.value.times(other))
    override operator fun div(other: kotlin.Float): Kilocalorie = Kilocalorie(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Kilocalorie = Kilocalorie(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Kilocalorie = Kilocalorie(this.value.times(other))
    override operator fun div(other: kotlin.Double): Kilocalorie = Kilocalorie(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Kilocalorie = Kilocalorie(this.value.rem(other))


    override operator fun unaryMinus(): Kilocalorie = Kilocalorie(-value)
    override operator fun unaryPlus(): Kilocalorie = this
    override operator fun inc(): Kilocalorie = Kilocalorie(this.value + 1)
    override operator fun dec(): Kilocalorie = Kilocalorie(this.value - 1)

    override fun round(): Kilocalorie = Kilocalorie(round(this.value))
    override fun truncate(): Kilocalorie = Kilocalorie(truncate(this.value))
    override fun floor(): Kilocalorie = Kilocalorie(floor(this.value))
    override fun ceil(): Kilocalorie = Kilocalorie(ceil(this.value))
    override fun abs(): Kilocalorie = Kilocalorie(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Kilocalorie -> this.value == other.value
        is Energy -> this.toKilogramSquareMeterPerSquareSecond().value == other.toKilogramSquareMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.kilocalorie
    get() = Kilocalorie(this)


val kotlin.Short.kilocalorie
    get() = Kilocalorie(this)


val kotlin.Int.kilocalorie
    get() = Kilocalorie(this)


val kotlin.Long.kilocalorie
    get() = Kilocalorie(this)


val kotlin.Float.kilocalorie
    get() = Kilocalorie(this)


val kotlin.Double.kilocalorie
    get() = Kilocalorie(this)


data class Calorie(override val value: MeasurementUnitValue) : Energy {

    override val unitName: String = "calorie"

    override fun formatters(language: MeasurementUnitLanguage) = language.energyCalorie
    override fun toKilogramSquareMeterPerSquareSecond() = KilogramSquareMeterPerSquareSecond(value * (4.184) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Calorie(0.0)
        fun fromKilogramSquareMeterPerSquareSecond(value: KilogramSquareMeterPerSquareSecond) = Calorie((value.value - 0) / (4.184))
    }

    operator fun plus(other: Calorie): Calorie = Calorie(this.value + other.value)
    operator fun minus(other: Calorie): Calorie = Calorie(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Calorie = Calorie(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Calorie = Calorie(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Calorie = Calorie(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Calorie = Calorie(this.value.times(other))
    override operator fun div(other: kotlin.Short): Calorie = Calorie(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Calorie = Calorie(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Calorie = Calorie(this.value.times(other))
    override operator fun div(other: kotlin.Int): Calorie = Calorie(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Calorie = Calorie(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Calorie = Calorie(this.value.times(other))
    override operator fun div(other: kotlin.Long): Calorie = Calorie(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Calorie = Calorie(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Calorie = Calorie(this.value.times(other))
    override operator fun div(other: kotlin.Float): Calorie = Calorie(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Calorie = Calorie(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Calorie = Calorie(this.value.times(other))
    override operator fun div(other: kotlin.Double): Calorie = Calorie(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Calorie = Calorie(this.value.rem(other))


    override operator fun unaryMinus(): Calorie = Calorie(-value)
    override operator fun unaryPlus(): Calorie = this
    override operator fun inc(): Calorie = Calorie(this.value + 1)
    override operator fun dec(): Calorie = Calorie(this.value - 1)

    override fun round(): Calorie = Calorie(round(this.value))
    override fun truncate(): Calorie = Calorie(truncate(this.value))
    override fun floor(): Calorie = Calorie(floor(this.value))
    override fun ceil(): Calorie = Calorie(ceil(this.value))
    override fun abs(): Calorie = Calorie(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Calorie -> this.value == other.value
        is Energy -> this.toKilogramSquareMeterPerSquareSecond().value == other.toKilogramSquareMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.calorie
    get() = Calorie(this)


val kotlin.Short.calorie
    get() = Calorie(this)


val kotlin.Int.calorie
    get() = Calorie(this)


val kotlin.Long.calorie
    get() = Calorie(this)


val kotlin.Float.calorie
    get() = Calorie(this)


val kotlin.Double.calorie
    get() = Calorie(this)


data class Foodcalorie(override val value: MeasurementUnitValue) : Energy {

    override val unitName: String = "foodcalorie"

    override fun formatters(language: MeasurementUnitLanguage) = language.energyFoodcalorie
    override fun toKilogramSquareMeterPerSquareSecond() = KilogramSquareMeterPerSquareSecond(value * (4184) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Foodcalorie(0.0)
        fun fromKilogramSquareMeterPerSquareSecond(value: KilogramSquareMeterPerSquareSecond) = Foodcalorie((value.value - 0) / (4184))
    }

    operator fun plus(other: Foodcalorie): Foodcalorie = Foodcalorie(this.value + other.value)
    operator fun minus(other: Foodcalorie): Foodcalorie = Foodcalorie(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Foodcalorie = Foodcalorie(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Foodcalorie = Foodcalorie(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Foodcalorie = Foodcalorie(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Foodcalorie = Foodcalorie(this.value.times(other))
    override operator fun div(other: kotlin.Short): Foodcalorie = Foodcalorie(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Foodcalorie = Foodcalorie(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Foodcalorie = Foodcalorie(this.value.times(other))
    override operator fun div(other: kotlin.Int): Foodcalorie = Foodcalorie(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Foodcalorie = Foodcalorie(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Foodcalorie = Foodcalorie(this.value.times(other))
    override operator fun div(other: kotlin.Long): Foodcalorie = Foodcalorie(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Foodcalorie = Foodcalorie(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Foodcalorie = Foodcalorie(this.value.times(other))
    override operator fun div(other: kotlin.Float): Foodcalorie = Foodcalorie(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Foodcalorie = Foodcalorie(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Foodcalorie = Foodcalorie(this.value.times(other))
    override operator fun div(other: kotlin.Double): Foodcalorie = Foodcalorie(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Foodcalorie = Foodcalorie(this.value.rem(other))


    override operator fun unaryMinus(): Foodcalorie = Foodcalorie(-value)
    override operator fun unaryPlus(): Foodcalorie = this
    override operator fun inc(): Foodcalorie = Foodcalorie(this.value + 1)
    override operator fun dec(): Foodcalorie = Foodcalorie(this.value - 1)

    override fun round(): Foodcalorie = Foodcalorie(round(this.value))
    override fun truncate(): Foodcalorie = Foodcalorie(truncate(this.value))
    override fun floor(): Foodcalorie = Foodcalorie(floor(this.value))
    override fun ceil(): Foodcalorie = Foodcalorie(ceil(this.value))
    override fun abs(): Foodcalorie = Foodcalorie(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Foodcalorie -> this.value == other.value
        is Energy -> this.toKilogramSquareMeterPerSquareSecond().value == other.toKilogramSquareMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.foodcalorie
    get() = Foodcalorie(this)


val kotlin.Short.foodcalorie
    get() = Foodcalorie(this)


val kotlin.Int.foodcalorie
    get() = Foodcalorie(this)


val kotlin.Long.foodcalorie
    get() = Foodcalorie(this)


val kotlin.Float.foodcalorie
    get() = Foodcalorie(this)


val kotlin.Double.foodcalorie
    get() = Foodcalorie(this)


data class Kilojoule(override val value: MeasurementUnitValue) : Energy {

    override val unitName: String = "kilojoule"

    override fun formatters(language: MeasurementUnitLanguage) = language.energyKilojoule
    override fun toKilogramSquareMeterPerSquareSecond() = KilogramSquareMeterPerSquareSecond(value * (1 * (1e3)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Kilojoule(0.0)
        fun fromKilogramSquareMeterPerSquareSecond(value: KilogramSquareMeterPerSquareSecond) = Kilojoule((value.value - 0) / (1 * (1e3)))
    }

    operator fun plus(other: Kilojoule): Kilojoule = Kilojoule(this.value + other.value)
    operator fun minus(other: Kilojoule): Kilojoule = Kilojoule(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Kilojoule = Kilojoule(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Kilojoule = Kilojoule(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Kilojoule = Kilojoule(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Kilojoule = Kilojoule(this.value.times(other))
    override operator fun div(other: kotlin.Short): Kilojoule = Kilojoule(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Kilojoule = Kilojoule(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Kilojoule = Kilojoule(this.value.times(other))
    override operator fun div(other: kotlin.Int): Kilojoule = Kilojoule(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Kilojoule = Kilojoule(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Kilojoule = Kilojoule(this.value.times(other))
    override operator fun div(other: kotlin.Long): Kilojoule = Kilojoule(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Kilojoule = Kilojoule(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Kilojoule = Kilojoule(this.value.times(other))
    override operator fun div(other: kotlin.Float): Kilojoule = Kilojoule(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Kilojoule = Kilojoule(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Kilojoule = Kilojoule(this.value.times(other))
    override operator fun div(other: kotlin.Double): Kilojoule = Kilojoule(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Kilojoule = Kilojoule(this.value.rem(other))


    override operator fun unaryMinus(): Kilojoule = Kilojoule(-value)
    override operator fun unaryPlus(): Kilojoule = this
    override operator fun inc(): Kilojoule = Kilojoule(this.value + 1)
    override operator fun dec(): Kilojoule = Kilojoule(this.value - 1)

    override fun round(): Kilojoule = Kilojoule(round(this.value))
    override fun truncate(): Kilojoule = Kilojoule(truncate(this.value))
    override fun floor(): Kilojoule = Kilojoule(floor(this.value))
    override fun ceil(): Kilojoule = Kilojoule(ceil(this.value))
    override fun abs(): Kilojoule = Kilojoule(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Kilojoule -> this.value == other.value
        is Energy -> this.toKilogramSquareMeterPerSquareSecond().value == other.toKilogramSquareMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.kilojoule
    get() = Kilojoule(this)


val kotlin.Short.kilojoule
    get() = Kilojoule(this)


val kotlin.Int.kilojoule
    get() = Kilojoule(this)


val kotlin.Long.kilojoule
    get() = Kilojoule(this)


val kotlin.Float.kilojoule
    get() = Kilojoule(this)


val kotlin.Double.kilojoule
    get() = Kilojoule(this)


data class Joule(override val value: MeasurementUnitValue) : Energy {

    override val unitName: String = "joule"

    override fun formatters(language: MeasurementUnitLanguage) = language.energyJoule
    override fun toKilogramSquareMeterPerSquareSecond() = KilogramSquareMeterPerSquareSecond(value * (1) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Joule(0.0)
        fun fromKilogramSquareMeterPerSquareSecond(value: KilogramSquareMeterPerSquareSecond) = Joule((value.value - 0) / (1))
    }

    operator fun plus(other: Joule): Joule = Joule(this.value + other.value)
    operator fun minus(other: Joule): Joule = Joule(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Joule = Joule(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Joule = Joule(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Joule = Joule(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Joule = Joule(this.value.times(other))
    override operator fun div(other: kotlin.Short): Joule = Joule(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Joule = Joule(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Joule = Joule(this.value.times(other))
    override operator fun div(other: kotlin.Int): Joule = Joule(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Joule = Joule(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Joule = Joule(this.value.times(other))
    override operator fun div(other: kotlin.Long): Joule = Joule(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Joule = Joule(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Joule = Joule(this.value.times(other))
    override operator fun div(other: kotlin.Float): Joule = Joule(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Joule = Joule(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Joule = Joule(this.value.times(other))
    override operator fun div(other: kotlin.Double): Joule = Joule(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Joule = Joule(this.value.rem(other))


    override operator fun unaryMinus(): Joule = Joule(-value)
    override operator fun unaryPlus(): Joule = this
    override operator fun inc(): Joule = Joule(this.value + 1)
    override operator fun dec(): Joule = Joule(this.value - 1)

    override fun round(): Joule = Joule(round(this.value))
    override fun truncate(): Joule = Joule(truncate(this.value))
    override fun floor(): Joule = Joule(floor(this.value))
    override fun ceil(): Joule = Joule(ceil(this.value))
    override fun abs(): Joule = Joule(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Joule -> this.value == other.value
        is Energy -> this.toKilogramSquareMeterPerSquareSecond().value == other.toKilogramSquareMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.joule
    get() = Joule(this)


val kotlin.Short.joule
    get() = Joule(this)


val kotlin.Int.joule
    get() = Joule(this)


val kotlin.Long.joule
    get() = Joule(this)


val kotlin.Float.joule
    get() = Joule(this)


val kotlin.Double.joule
    get() = Joule(this)


data class KilowattHour(override val value: MeasurementUnitValue) : Energy {

    override val unitName: String = "kilowatt-hour"

    override fun formatters(language: MeasurementUnitLanguage) = language.energyKilowattHour
    override fun toKilogramSquareMeterPerSquareSecond() = KilogramSquareMeterPerSquareSecond(value * ((1 * (1e3)) * (3600)) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = KilowattHour(0.0)
        fun fromKilogramSquareMeterPerSquareSecond(value: KilogramSquareMeterPerSquareSecond) = KilowattHour((value.value - 0) / ((1 * (1e3)) * (3600)))
    }

    operator fun plus(other: KilowattHour): KilowattHour = KilowattHour(this.value + other.value)
    operator fun minus(other: KilowattHour): KilowattHour = KilowattHour(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): KilowattHour = KilowattHour(this.value.times(other))
    override operator fun div(other: kotlin.Byte): KilowattHour = KilowattHour(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): KilowattHour = KilowattHour(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): KilowattHour = KilowattHour(this.value.times(other))
    override operator fun div(other: kotlin.Short): KilowattHour = KilowattHour(this.value.div(other))
    override operator fun rem(other: kotlin.Short): KilowattHour = KilowattHour(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): KilowattHour = KilowattHour(this.value.times(other))
    override operator fun div(other: kotlin.Int): KilowattHour = KilowattHour(this.value.div(other))
    override operator fun rem(other: kotlin.Int): KilowattHour = KilowattHour(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): KilowattHour = KilowattHour(this.value.times(other))
    override operator fun div(other: kotlin.Long): KilowattHour = KilowattHour(this.value.div(other))
    override operator fun rem(other: kotlin.Long): KilowattHour = KilowattHour(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): KilowattHour = KilowattHour(this.value.times(other))
    override operator fun div(other: kotlin.Float): KilowattHour = KilowattHour(this.value.div(other))
    override operator fun rem(other: kotlin.Float): KilowattHour = KilowattHour(this.value.rem(other))


    override operator fun times(other: kotlin.Double): KilowattHour = KilowattHour(this.value.times(other))
    override operator fun div(other: kotlin.Double): KilowattHour = KilowattHour(this.value.div(other))
    override operator fun rem(other: kotlin.Double): KilowattHour = KilowattHour(this.value.rem(other))


    override operator fun unaryMinus(): KilowattHour = KilowattHour(-value)
    override operator fun unaryPlus(): KilowattHour = this
    override operator fun inc(): KilowattHour = KilowattHour(this.value + 1)
    override operator fun dec(): KilowattHour = KilowattHour(this.value - 1)

    override fun round(): KilowattHour = KilowattHour(round(this.value))
    override fun truncate(): KilowattHour = KilowattHour(truncate(this.value))
    override fun floor(): KilowattHour = KilowattHour(floor(this.value))
    override fun ceil(): KilowattHour = KilowattHour(ceil(this.value))
    override fun abs(): KilowattHour = KilowattHour(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is KilowattHour -> this.value == other.value
        is Energy -> this.toKilogramSquareMeterPerSquareSecond().value == other.toKilogramSquareMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.kilowattHour
    get() = KilowattHour(this)


val kotlin.Short.kilowattHour
    get() = KilowattHour(this)


val kotlin.Int.kilowattHour
    get() = KilowattHour(this)


val kotlin.Long.kilowattHour
    get() = KilowattHour(this)


val kotlin.Float.kilowattHour
    get() = KilowattHour(this)


val kotlin.Double.kilowattHour
    get() = KilowattHour(this)


data class Electronvolt(override val value: MeasurementUnitValue) : Energy {

    override val unitName: String = "electronvolt"

    override fun formatters(language: MeasurementUnitLanguage) = language.energyElectronvolt
    override fun toKilogramSquareMeterPerSquareSecond() = KilogramSquareMeterPerSquareSecond(value * (1.602177e-19) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = Electronvolt(0.0)
        fun fromKilogramSquareMeterPerSquareSecond(value: KilogramSquareMeterPerSquareSecond) = Electronvolt((value.value - 0) / (1.602177e-19))
    }

    operator fun plus(other: Electronvolt): Electronvolt = Electronvolt(this.value + other.value)
    operator fun minus(other: Electronvolt): Electronvolt = Electronvolt(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): Electronvolt = Electronvolt(this.value.times(other))
    override operator fun div(other: kotlin.Byte): Electronvolt = Electronvolt(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): Electronvolt = Electronvolt(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): Electronvolt = Electronvolt(this.value.times(other))
    override operator fun div(other: kotlin.Short): Electronvolt = Electronvolt(this.value.div(other))
    override operator fun rem(other: kotlin.Short): Electronvolt = Electronvolt(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): Electronvolt = Electronvolt(this.value.times(other))
    override operator fun div(other: kotlin.Int): Electronvolt = Electronvolt(this.value.div(other))
    override operator fun rem(other: kotlin.Int): Electronvolt = Electronvolt(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): Electronvolt = Electronvolt(this.value.times(other))
    override operator fun div(other: kotlin.Long): Electronvolt = Electronvolt(this.value.div(other))
    override operator fun rem(other: kotlin.Long): Electronvolt = Electronvolt(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): Electronvolt = Electronvolt(this.value.times(other))
    override operator fun div(other: kotlin.Float): Electronvolt = Electronvolt(this.value.div(other))
    override operator fun rem(other: kotlin.Float): Electronvolt = Electronvolt(this.value.rem(other))


    override operator fun times(other: kotlin.Double): Electronvolt = Electronvolt(this.value.times(other))
    override operator fun div(other: kotlin.Double): Electronvolt = Electronvolt(this.value.div(other))
    override operator fun rem(other: kotlin.Double): Electronvolt = Electronvolt(this.value.rem(other))


    override operator fun unaryMinus(): Electronvolt = Electronvolt(-value)
    override operator fun unaryPlus(): Electronvolt = this
    override operator fun inc(): Electronvolt = Electronvolt(this.value + 1)
    override operator fun dec(): Electronvolt = Electronvolt(this.value - 1)

    override fun round(): Electronvolt = Electronvolt(round(this.value))
    override fun truncate(): Electronvolt = Electronvolt(truncate(this.value))
    override fun floor(): Electronvolt = Electronvolt(floor(this.value))
    override fun ceil(): Electronvolt = Electronvolt(ceil(this.value))
    override fun abs(): Electronvolt = Electronvolt(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is Electronvolt -> this.value == other.value
        is Energy -> this.toKilogramSquareMeterPerSquareSecond().value == other.toKilogramSquareMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.electronvolt
    get() = Electronvolt(this)


val kotlin.Short.electronvolt
    get() = Electronvolt(this)


val kotlin.Int.electronvolt
    get() = Electronvolt(this)


val kotlin.Long.electronvolt
    get() = Electronvolt(this)


val kotlin.Float.electronvolt
    get() = Electronvolt(this)


val kotlin.Double.electronvolt
    get() = Electronvolt(this)


data class BritishThermalUnit(override val value: MeasurementUnitValue) : Energy {

    override val unitName: String = "british-thermal-unit"

    override fun formatters(language: MeasurementUnitLanguage) = language.energyBritishThermalUnit
    override fun toKilogramSquareMeterPerSquareSecond() = KilogramSquareMeterPerSquareSecond(value * (1054.350264488889) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = BritishThermalUnit(0.0)
        fun fromKilogramSquareMeterPerSquareSecond(value: KilogramSquareMeterPerSquareSecond) = BritishThermalUnit((value.value - 0) / (1054.350264488889))
    }

    operator fun plus(other: BritishThermalUnit): BritishThermalUnit = BritishThermalUnit(this.value + other.value)
    operator fun minus(other: BritishThermalUnit): BritishThermalUnit = BritishThermalUnit(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): BritishThermalUnit = BritishThermalUnit(this.value.times(other))
    override operator fun div(other: kotlin.Byte): BritishThermalUnit = BritishThermalUnit(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): BritishThermalUnit = BritishThermalUnit(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): BritishThermalUnit = BritishThermalUnit(this.value.times(other))
    override operator fun div(other: kotlin.Short): BritishThermalUnit = BritishThermalUnit(this.value.div(other))
    override operator fun rem(other: kotlin.Short): BritishThermalUnit = BritishThermalUnit(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): BritishThermalUnit = BritishThermalUnit(this.value.times(other))
    override operator fun div(other: kotlin.Int): BritishThermalUnit = BritishThermalUnit(this.value.div(other))
    override operator fun rem(other: kotlin.Int): BritishThermalUnit = BritishThermalUnit(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): BritishThermalUnit = BritishThermalUnit(this.value.times(other))
    override operator fun div(other: kotlin.Long): BritishThermalUnit = BritishThermalUnit(this.value.div(other))
    override operator fun rem(other: kotlin.Long): BritishThermalUnit = BritishThermalUnit(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): BritishThermalUnit = BritishThermalUnit(this.value.times(other))
    override operator fun div(other: kotlin.Float): BritishThermalUnit = BritishThermalUnit(this.value.div(other))
    override operator fun rem(other: kotlin.Float): BritishThermalUnit = BritishThermalUnit(this.value.rem(other))


    override operator fun times(other: kotlin.Double): BritishThermalUnit = BritishThermalUnit(this.value.times(other))
    override operator fun div(other: kotlin.Double): BritishThermalUnit = BritishThermalUnit(this.value.div(other))
    override operator fun rem(other: kotlin.Double): BritishThermalUnit = BritishThermalUnit(this.value.rem(other))


    override operator fun unaryMinus(): BritishThermalUnit = BritishThermalUnit(-value)
    override operator fun unaryPlus(): BritishThermalUnit = this
    override operator fun inc(): BritishThermalUnit = BritishThermalUnit(this.value + 1)
    override operator fun dec(): BritishThermalUnit = BritishThermalUnit(this.value - 1)

    override fun round(): BritishThermalUnit = BritishThermalUnit(round(this.value))
    override fun truncate(): BritishThermalUnit = BritishThermalUnit(truncate(this.value))
    override fun floor(): BritishThermalUnit = BritishThermalUnit(floor(this.value))
    override fun ceil(): BritishThermalUnit = BritishThermalUnit(ceil(this.value))
    override fun abs(): BritishThermalUnit = BritishThermalUnit(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is BritishThermalUnit -> this.value == other.value
        is Energy -> this.toKilogramSquareMeterPerSquareSecond().value == other.toKilogramSquareMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.britishThermalUnit
    get() = BritishThermalUnit(this)


val kotlin.Short.britishThermalUnit
    get() = BritishThermalUnit(this)


val kotlin.Int.britishThermalUnit
    get() = BritishThermalUnit(this)


val kotlin.Long.britishThermalUnit
    get() = BritishThermalUnit(this)


val kotlin.Float.britishThermalUnit
    get() = BritishThermalUnit(this)


val kotlin.Double.britishThermalUnit
    get() = BritishThermalUnit(this)


data class ThermUs(override val value: MeasurementUnitValue) : Energy {

    override val unitName: String = "therm-us"

    override fun formatters(language: MeasurementUnitLanguage) = language.energyThermUs
    override fun toKilogramSquareMeterPerSquareSecond() = KilogramSquareMeterPerSquareSecond(value * (105480400) + 0)
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = ThermUs(0.0)
        fun fromKilogramSquareMeterPerSquareSecond(value: KilogramSquareMeterPerSquareSecond) = ThermUs((value.value - 0) / (105480400))
    }

    operator fun plus(other: ThermUs): ThermUs = ThermUs(this.value + other.value)
    operator fun minus(other: ThermUs): ThermUs = ThermUs(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): ThermUs = ThermUs(this.value.times(other))
    override operator fun div(other: kotlin.Byte): ThermUs = ThermUs(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): ThermUs = ThermUs(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): ThermUs = ThermUs(this.value.times(other))
    override operator fun div(other: kotlin.Short): ThermUs = ThermUs(this.value.div(other))
    override operator fun rem(other: kotlin.Short): ThermUs = ThermUs(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): ThermUs = ThermUs(this.value.times(other))
    override operator fun div(other: kotlin.Int): ThermUs = ThermUs(this.value.div(other))
    override operator fun rem(other: kotlin.Int): ThermUs = ThermUs(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): ThermUs = ThermUs(this.value.times(other))
    override operator fun div(other: kotlin.Long): ThermUs = ThermUs(this.value.div(other))
    override operator fun rem(other: kotlin.Long): ThermUs = ThermUs(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): ThermUs = ThermUs(this.value.times(other))
    override operator fun div(other: kotlin.Float): ThermUs = ThermUs(this.value.div(other))
    override operator fun rem(other: kotlin.Float): ThermUs = ThermUs(this.value.rem(other))


    override operator fun times(other: kotlin.Double): ThermUs = ThermUs(this.value.times(other))
    override operator fun div(other: kotlin.Double): ThermUs = ThermUs(this.value.div(other))
    override operator fun rem(other: kotlin.Double): ThermUs = ThermUs(this.value.rem(other))


    override operator fun unaryMinus(): ThermUs = ThermUs(-value)
    override operator fun unaryPlus(): ThermUs = this
    override operator fun inc(): ThermUs = ThermUs(this.value + 1)
    override operator fun dec(): ThermUs = ThermUs(this.value - 1)

    override fun round(): ThermUs = ThermUs(round(this.value))
    override fun truncate(): ThermUs = ThermUs(truncate(this.value))
    override fun floor(): ThermUs = ThermUs(floor(this.value))
    override fun ceil(): ThermUs = ThermUs(ceil(this.value))
    override fun abs(): ThermUs = ThermUs(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is ThermUs -> this.value == other.value
        is Energy -> this.toKilogramSquareMeterPerSquareSecond().value == other.toKilogramSquareMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.thermUs
    get() = ThermUs(this)


val kotlin.Short.thermUs
    get() = ThermUs(this)


val kotlin.Int.thermUs
    get() = ThermUs(this)


val kotlin.Long.thermUs
    get() = ThermUs(this)


val kotlin.Float.thermUs
    get() = ThermUs(this)


val kotlin.Double.thermUs
    get() = ThermUs(this)


data class KilogramSquareMeterPerSquareSecond(override val value: MeasurementUnitValue) : Energy {

    override val unitName: String = "kilogram-square-meter-per-square-second"

    override fun formatters(language: MeasurementUnitLanguage) = language.energyKilogramSquareMeterPerSquareSecond
    override fun toKilogramSquareMeterPerSquareSecond() = this
    override fun toString(): String = toDebugString()

    companion object {
        val ZERO = KilogramSquareMeterPerSquareSecond(0.0)
        fun fromKilogramSquareMeterPerSquareSecond(value: KilogramSquareMeterPerSquareSecond) = value
    }

    operator fun plus(other: KilogramSquareMeterPerSquareSecond): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value + other.value)
    operator fun minus(other: KilogramSquareMeterPerSquareSecond): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value - other.value)

    constructor(value: kotlin.Byte) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Byte): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Byte): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Byte): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.rem(other))

    constructor(value: kotlin.Short) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Short): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Short): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Short): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.rem(other))

    constructor(value: kotlin.Int) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Int): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Int): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Int): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.rem(other))

    constructor(value: kotlin.Long) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Long): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Long): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Long): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.rem(other))

    constructor(value: kotlin.Float) : this(value.toMeasurementUnitValue())

    override operator fun times(other: kotlin.Float): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Float): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Float): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.rem(other))


    override operator fun times(other: kotlin.Double): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.times(other))
    override operator fun div(other: kotlin.Double): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.div(other))
    override operator fun rem(other: kotlin.Double): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value.rem(other))


    override operator fun unaryMinus(): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(-value)
    override operator fun unaryPlus(): KilogramSquareMeterPerSquareSecond = this
    override operator fun inc(): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value + 1)
    override operator fun dec(): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(this.value - 1)

    override fun round(): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(round(this.value))
    override fun truncate(): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(truncate(this.value))
    override fun floor(): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(floor(this.value))
    override fun ceil(): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(ceil(this.value))
    override fun abs(): KilogramSquareMeterPerSquareSecond = KilogramSquareMeterPerSquareSecond(abs(this.value))

    override fun equals(other: Any?): Boolean = when (other) {
        is KilogramSquareMeterPerSquareSecond -> this.value == other.value
        is Energy -> this.toKilogramSquareMeterPerSquareSecond().value == other.toKilogramSquareMeterPerSquareSecond().value
        else -> false
    }

    override fun hashCode(): Int = this.toKilogramSquareMeterPerSquareSecond().value.hashCode()


}

val kotlin.Byte.kilogramSquareMeterPerSquareSecond
    get() = KilogramSquareMeterPerSquareSecond(this)


val kotlin.Short.kilogramSquareMeterPerSquareSecond
    get() = KilogramSquareMeterPerSquareSecond(this)


val kotlin.Int.kilogramSquareMeterPerSquareSecond
    get() = KilogramSquareMeterPerSquareSecond(this)


val kotlin.Long.kilogramSquareMeterPerSquareSecond
    get() = KilogramSquareMeterPerSquareSecond(this)


val kotlin.Float.kilogramSquareMeterPerSquareSecond
    get() = KilogramSquareMeterPerSquareSecond(this)


val kotlin.Double.kilogramSquareMeterPerSquareSecond
    get() = KilogramSquareMeterPerSquareSecond(this)
                            
