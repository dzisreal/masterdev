package kafka.Delay;


import Genprotos.DataTracking;
import com.github.javafaker.Faker;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
public class ProducerKafkaProDelay {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String topicName = "hoand68_test";
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.80.27:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "serializers.KafkaSerializer");
        props.put("schema.registry.url", "http://172.17.80.27:8081");
        Faker faker = new Faker();
        Producer<String, DataTracking> producer = new KafkaProducer<String, DataTracking>(props);

        String key = "testkey";
//        ProducerRecord<String, DataTracking> record
//                = new ProducerRecord<String, DataTracking>(topicName, key, getDataTracking(faker));
//        producer.send(record);
//        producer.close();

        while (true){
            TimeUnit.SECONDS.sleep(15);
            ProducerRecord<String, DataTracking> record
                    = new ProducerRecord<String, DataTracking>(topicName, key, getDataTracking(faker));
            TimeUnit.SECONDS.sleep(20);
            producer.send(record);
        }

    }

    public static DataTracking getDataTracking(Faker faker){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String fullName = faker.name().fullName();
        String phoneId = String.valueOf(faker.phoneNumber().phoneNumber());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long TS = Long.parseLong(sdf1.format(timestamp));

        DataTracking DT = DataTracking.newBuilder()
                .setVersion("12")
                .setName(fullName)
                .setTimestamp(TS)
                .setPhoneId(phoneId)
                .setLon(123456)
                .setLat(123456).build();
        return DT;
    }
}
