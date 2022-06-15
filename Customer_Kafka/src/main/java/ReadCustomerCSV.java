import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import schema.customer;
import serde.JsonSerializer;

import javax.swing.*;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ReadCustomerCSV {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG,"my-app-readcsv");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        logger.info("Start sending Customer Record");

        KafkaProducer<String, customer> producer = new KafkaProducer<>(props);

        ReadCSV readCSV = new ReadCSV();
        List customerList = readCSV.ReadCSVFile();
        for (Object customerObject : customerList){
            customer ctmObject = (customer) customerObject;
            producer.send(new ProducerRecord<String, customer>("customer",null,ctmObject));
        }
        logger.info("Done!");
        producer.close();
    }
}
