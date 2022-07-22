package kafka;

import Genprotos.DataTracking;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import serializers.KafkaDeserializer;



import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;


public class ReadkafkaProto {

    public static void main(String[] args) throws IOException {
        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.80.27:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group2");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "serializers.KafkaDeserializer");
        props.put("schema.registry.url", "http://172.17.80.27:8081");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        String topic = "kafka_data_tracking_hoand68";
        final Consumer<String, DataTracking> consumer = new KafkaConsumer<String, DataTracking>(props,
                new StringDeserializer(),
                new KafkaDeserializer<>(DataTracking.parser()));
        consumer.subscribe(Arrays.asList(topic));

        try {
            while (true) {
                ConsumerRecords<String, DataTracking> records = consumer.poll(100);
                for (ConsumerRecord<String, DataTracking> record : records) {
                    System.out.printf("offset = %d, key = %s,\n value = %s \n", record.offset(), record.key(), record.value());
                }
            }
        } finally {
            consumer.close();
        }


    }
}
