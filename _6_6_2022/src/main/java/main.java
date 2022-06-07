import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import src.main.java.person;

import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        person per1 = new person();
        per1.setName("nameTest1");
        per1.setAge(21);
        per1.setAddress("Bac Giang");

        DatumWriter<person> empDatumWriter = new SpecificDatumWriter<person>(person.class);

        DataFileWriter<person> empFileWriter = new DataFileWriter<person>(empDatumWriter);

        empFileWriter.create(per1.getSchema(),new File("/home/ndh29101/masterdev/_6_6_2022/src/main/java/person.avsc"));

        empFileWriter.append(per1);

        empFileWriter.close();

        System.out.println("Done!");

    }


}
