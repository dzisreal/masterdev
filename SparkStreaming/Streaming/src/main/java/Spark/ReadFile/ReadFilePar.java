package Spark.ReadFile;


import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
public class ReadFilePar {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().appName("Read_Parquet_File").master("local").getOrCreate();
        Dataset<Row> parquetFileDF = spark.read().parquet("hoand68");
        parquetFileDF.show();
        parquetFileDF.printSchema();

    }
}
