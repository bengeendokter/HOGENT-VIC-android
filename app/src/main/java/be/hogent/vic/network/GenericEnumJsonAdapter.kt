package be.hogent.vic.network

import android.util.Log
import com.squareup.moshi.*

interface IEnumValue {
    val value: Int
}

inline fun <reified T> createEnumJsonAdapter(): JsonAdapter<T> where T : Enum<T>, T : IEnumValue {
    return object : JsonAdapter<T>() {
        @FromJson
        override fun fromJson(reader: JsonReader): T? {
            return if (reader.peek() != JsonReader.Token.NULL) {
                val next = reader.nextInt()
                enumValues<T>().firstOrNull { it.value == next }
            } else {
                reader.nextNull()
            }
        }

        @ToJson
        override fun toJson(writer: JsonWriter, value: T?) {
            writer.value(value?.value)
        }
    }
}