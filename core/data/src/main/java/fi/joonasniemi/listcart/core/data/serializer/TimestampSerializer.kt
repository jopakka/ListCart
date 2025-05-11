package fi.joonasniemi.listcart.core.data.serializer

import com.google.firebase.Timestamp
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

object TimestampSerializer : KSerializer<Timestamp> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("com.google.firebase.Timestamp") {
        element<Long>("seconds")
        element<Int>("nanoseconds")
    }

    override fun deserialize(decoder: Decoder): Timestamp = decoder.decodeStructure(descriptor) {
        var seconds: Long? = null
        var nanoseconds: Int? = null

        while (true) {
            when (val index = decodeElementIndex(descriptor)) {
                0 -> seconds = decodeLongElement(descriptor, 0)
                1 -> nanoseconds = decodeIntElement(descriptor, 1)
                CompositeDecoder.DECODE_DONE -> break
                else -> error("Unexpected index: $index")
            }
        }
        check(seconds != null && nanoseconds != null) { "Missing seconds or nanoseconds" }
        Timestamp(seconds, nanoseconds)
    }

    override fun serialize(encoder: Encoder, value: Timestamp) = encoder.encodeStructure(descriptor) {
        encodeLongElement(descriptor, 0, value.seconds)
        encodeIntElement(descriptor, 1, value.nanoseconds)
    }
}