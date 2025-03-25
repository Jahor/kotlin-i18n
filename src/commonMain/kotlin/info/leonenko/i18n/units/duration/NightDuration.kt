package info.leonenko.i18n.units.duration
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
  @Serializable(NightDuration.Serializer::class)
  interface NightDuration : MeasurementUnit, Comparable<NightDuration> {
    override val measurement: String get() = "night-duration"

                    class Serializer: CustomSerializer() 
                    open class CustomSerializer(val decoders: Map<String, (MeasurementUnitValue) -> NightDuration> = emptyMap()): KSerializer<NightDuration> {
        private val delegateSerializer = MapSerializer(String.serializer(), MeasurementUnitValue.serializer())
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("NightDuration", delegateSerializer.descriptor)
        override fun deserialize(decoder: Decoder): NightDuration {
            val map = decoder.decodeSerializableValue(delegateSerializer)
            val key = map.keys.firstOrNull() ?: throw SerializationException("Expected NightDuration unit as a key. None found")
            val value = map[key]!!
            return when(key) {
                "night" -> Night(value)
                else -> decoders[key]?.let { it(value) } ?: throw SerializationException("Unknown NightDuration unit $key")
            }
        }

        override fun serialize(encoder: Encoder, value: NightDuration) {
            encoder.encodeSerializableValue(delegateSerializer, mapOf(value.unitName to value.value))
        }

    }
                    
fun toNight() : Night
override fun getBaseUnitValue() = toNight().value

                    companion object {
                                val ZERO: NightDuration = Night(0.0)
                                
                            }
                    override operator fun compareTo(other: NightDuration): Int = this.toNight().value.compareTo(other.toNight().value)
                    
                    operator fun plus(other: NightDuration): Night = this.toNight() + other.toNight()                    
                    operator fun minus(other: NightDuration): Night = this.toNight() - other.toNight()
                    operator fun div(other: NightDuration): Double = this.toNight().value / other.toNight().value
                    operator fun unaryMinus(): NightDuration
                    operator fun unaryPlus(): NightDuration
                    operator fun inc(): NightDuration
                    operator fun dec(): NightDuration 
                    
                    fun round() : NightDuration
                    fun truncate() : NightDuration
                    fun floor() : NightDuration
                    fun ceil() : NightDuration
                    fun abs() : NightDuration
                    
                               
                               operator fun times(other: Byte): NightDuration
                               operator fun div(other: Byte): NightDuration
                               operator fun rem(other: Byte): NightDuration
                            
                               
                               operator fun times(other: Short): NightDuration
                               operator fun div(other: Short): NightDuration
                               operator fun rem(other: Short): NightDuration
                            
                               
                               operator fun times(other: Int): NightDuration
                               operator fun div(other: Int): NightDuration
                               operator fun rem(other: Int): NightDuration
                            
                               
                               operator fun times(other: Long): NightDuration
                               operator fun div(other: Long): NightDuration
                               operator fun rem(other: Long): NightDuration
                            
                               
                               operator fun times(other: Float): NightDuration
                               operator fun div(other: Float): NightDuration
                               operator fun rem(other: Float): NightDuration
                            
                               
                               operator fun times(other: Double): NightDuration
                               operator fun div(other: Double): NightDuration
                               operator fun rem(other: Double): NightDuration
                            
  }

                        data class Night(override val value:MeasurementUnitValue): NightDuration {
                        
                        override val unitName: String = "night"
                        
                        override fun formatters(language: MeasurementUnitLanguage) = language.durationNight
override fun toNight() = this
override fun toString() : String = toDebugString()

                            companion object {
                                val ZERO = Night(0.0)
                                fun fromNight(value: Night) = value
                            }
                           operator fun plus(other: Night): Night = Night(this.value + other.value)                    
                           operator fun minus(other: Night): Night = Night(this.value - other.value)
                           
constructor(value: Byte) : this(value.toMeasurementUnitValue())
                               
                               override operator fun times(other: Byte): Night = Night(this.value.times(other))
                               override operator fun div(other: Byte): Night = Night(this.value.div(other))
                               override operator fun rem(other: Byte): Night = Night(this.value.rem(other))
                            
constructor(value: Short) : this(value.toMeasurementUnitValue())
                               
                               override operator fun times(other: Short): Night = Night(this.value.times(other))
                               override operator fun div(other: Short): Night = Night(this.value.div(other))
                               override operator fun rem(other: Short): Night = Night(this.value.rem(other))
                            
constructor(value: Int) : this(value.toMeasurementUnitValue())
                               
                               override operator fun times(other: Int): Night = Night(this.value.times(other))
                               override operator fun div(other: Int): Night = Night(this.value.div(other))
                               override operator fun rem(other: Int): Night = Night(this.value.rem(other))
                            
constructor(value: Long) : this(value.toMeasurementUnitValue())
                               
                               override operator fun times(other: Long): Night = Night(this.value.times(other))
                               override operator fun div(other: Long): Night = Night(this.value.div(other))
                               override operator fun rem(other: Long): Night = Night(this.value.rem(other))
                            
constructor(value: Float) : this(value.toMeasurementUnitValue())
                               
                               override operator fun times(other: Float): Night = Night(this.value.times(other))
                               override operator fun div(other: Float): Night = Night(this.value.div(other))
                               override operator fun rem(other: Float): Night = Night(this.value.rem(other))
                            
                               
                               override operator fun times(other: Double): Night = Night(this.value.times(other))
                               override operator fun div(other: Double): Night = Night(this.value.div(other))
                               override operator fun rem(other: Double): Night = Night(this.value.rem(other))
                            

                           override operator fun unaryMinus(): Night = Night(-value)
                           override operator fun unaryPlus(): Night = this
                           override operator fun inc(): Night = Night(this.value + 1)  
                           override operator fun dec(): Night = Night(this.value - 1)  
                           
                           override fun round() : Night = Night(round(this.value))
                           override fun truncate() : Night = Night(truncate(this.value))
                           override fun floor() : Night = Night(floor(this.value))
                           override fun ceil() : Night = Night(ceil(this.value))
                           override fun abs() : Night = Night(abs(this.value))
                           
                           override fun equals(other: Any?): Boolean = when (other) {
                               is Night -> this.value == other.value
                               is NightDuration -> this.toNight().value == other.toNight().value
                               else -> false
                           }    

                           override fun hashCode(): Int = this.toNight().value.hashCode()
                           
                           
}

                               val Byte.night
                                   get() = Night(this)                               
                            

                               val Short.night
                                   get() = Night(this)                               
                            

                               val Int.night
                                   get() = Night(this)                               
                            

                               val Long.night
                                   get() = Night(this)                               
                            

                               val Float.night
                                   get() = Night(this)                               
                            

                               val Double.night
                                   get() = Night(this)                               
                            
