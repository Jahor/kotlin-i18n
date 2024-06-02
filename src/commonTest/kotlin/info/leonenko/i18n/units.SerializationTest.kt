package info.leonenko.i18n

import info.leonenko.i18n.units.length.Length
import info.leonenko.i18n.units.length.kilometer
import info.leonenko.i18n.units.speed.Speed
import info.leonenko.i18n.units.speed.meterPerSecond
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class SerializationTest {

    @Serializable
    data class TestContainer(val speed: Speed, val length: Length)

    @Test
    fun jsonSerialization() {
        val value = TestContainer(20.meterPerSecond, 30.kilometer)
        val encoded = Json.encodeToString(TestContainer.serializer(), value)
        println(encoded)
        val decoded = Json.decodeFromString(TestContainer.serializer(), encoded)
        println(decoded)
        assertEquals(value, decoded)
    }
}