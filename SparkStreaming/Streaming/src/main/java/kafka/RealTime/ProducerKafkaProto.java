package kafka.RealTime;



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
//import org.apache.kafka.common.serialization.ByteArraySerializer;

public class ProducerKafkaProto {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String topicName = "kafka_data_tracking_hoand68";
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
            TimeUnit.SECONDS.sleep(1);
            ProducerRecord<String, DataTracking> record
                    = new ProducerRecord<String, DataTracking>(topicName, key, getDataTracking(faker));
            producer.send(record);
        }

    }

    public static DataTracking getDataTracking(Faker faker){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String version = String.valueOf(faker.random().nextInt(10,20));
        String fullName = faker.name().fullName();
        String phoneId = String.valueOf(faker.phoneNumber().phoneNumber());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long TS = Long.parseLong(sdf1.format(timestamp));
        int lon = faker.random().nextInt(100,999);
        int lat = faker.random().nextInt(100,999);

        DataTracking DT = DataTracking.newBuilder()
                .setVersion(version)
                .setName(fullName)
                .setTimestamp(TS)
                .setPhoneId(phoneId)
                .setLon(lon)
                .setLat(lat).build();
        return DT;
    }
}
