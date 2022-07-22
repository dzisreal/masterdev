package serializers;

import com.google.protobuf.Message;
//import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import org.apache.kafka.common.serialization.Serializer;

/**
 * Serializer for Kafka to serialize Protocol Buffers messages
 *
 * @param <T> Protobuf message type
 */
public class KafkaSerializer<T extends Message> implements Serializer<T> {
    @Override
    public byte[] serialize(String topic, T record) {
        return record.toByteArray();
    }


}