import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;
import src.main.java.zoo;

import java.io.File;
import java.io.IOException;

public class deserialize {
    public static void main(String[] args) throws IOException {
        DatumReader<zoo> zooDatumReader = new SpecificDatumReader<zoo>(zoo.class);

        DataFileReader<zoo> dataFileReader = new DataFileReader(new File("/home/ndh29101/masterdev/_6_6_2022/src/main/java/data.avro"), zooDatumReader);

        zoo zoos = null;
        while(dataFileReader.hasNext()){

            zoos =dataFileReader.next(zoos);
            System.out.println(zoos);
        }
    }
    //asdsadsadsdas
}
