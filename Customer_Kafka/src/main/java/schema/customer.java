package schema;


import com.opencsv.bean.CsvBindByName;

public class customer {

    @CsvBindByName
    public int customerId;

    @CsvBindByName
    public int num_order;

    @CsvBindByName
    public int age;

    @CsvBindByName
    public String phoneNumber;

}
