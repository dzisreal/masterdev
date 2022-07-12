package ex1;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class readParquetFile {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().appName("Read_Parquet_File").master("local").getOrCreate();

        Dataset<Row> dataset = spark.read().parquet("../Spark/src/main/java/data/SmallFile.parquet"); //file Name
        dataset.printSchema();
        dataset.show();
    }
}
