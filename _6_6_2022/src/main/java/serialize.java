import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.checkerframework.checker.units.qual.A;
import src.main.java.Animal;
import src.main.java.zoo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class serialize {
    public static void main(String[] args) throws IOException {
        zoo zooSource = new zoo();
        zooSource.setVersion(1);

        Animal a1 = new Animal();
        Animal a2 = new Animal();
        Animal a3 = new Animal();

        a1.setName("Dog");
        a1.setWeight(1.2);

        a2.setName("Cat");
        a2.setWeight(2.3);

        a3.setName("Elephant");
        a3.setWeight(3.4);

        List<Animal> lstAnimals = new ArrayList<Animal>();

        lstAnimals.add(a1);
        lstAnimals.add(a2);
        lstAnimals.add(a3);

        zooSource.setAnimals(lstAnimals);

        DatumWriter<zoo> zooDatumWriter = new SpecificDatumWriter<zoo>(zoo.class);
        DataFileWriter<zoo> zooDataFileWriter = new DataFileWriter<zoo>(zooDatumWriter);

        zooDataFileWriter.create(zooSource.getSchema(),new File("/home/ndh29101/masterdev/_6_6_2022/src/main/java/data.avro"));

        zooDataFileWriter.append(zooSource);

        zooDataFileWriter.close();

        System.out.println("Done! See data in file data.avro");
    }

}
