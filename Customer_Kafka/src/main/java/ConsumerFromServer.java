import com.opencsv.CSVWriter;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import schema.customer;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;



public class ConsumerFromServer {
    public static void main(String[] args) throws IOException {
        final Logger logger = LoggerFactory.getLogger(Consumer.class.getName());
        Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.ALL);


        final String bootstrapServers = "172.17.80.26:9092";
        final String consumerGroupID = "java-group-consumer";

        Properties p = new Properties();
        p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        p.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupID);
        p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(p);

        consumer.subscribe(Arrays.asList("customer"));

        ArrayList<Integer>lstID = new ArrayList<Integer>();

        File f = new File("output.csv");
        FileWriter outputfile = new FileWriter(f);
        CSVWriter writer = new CSVWriter(outputfile,
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.NO_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        String[] header = {"customerId","num_order","age","phoneNumber"};
        writer.writeNext(header);
        writer.flush();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                logger.info(record.value());
                String[] x = record.value().split(",");
                int id = Integer.parseInt(x[0]);
                int numOrder = Integer.parseInt(x[1]);
                int age_ = Integer.parseInt(x[2]);
                String phone = x[3];
                try{
                    if (!lstID.contains(id) && numOrder > 100 && age_ < 20 )  {
                        lstID.add(id);
                        writer.writeNext(new String[]{Integer.toString(id),Integer.toString(numOrder),Integer.toString(age_),phone});
                        writer.flush();
                        logger.info(record.value());
                    }
                }
                catch (Exception e) {
                    writer.flush();
                }
            }
        }
    }


}


