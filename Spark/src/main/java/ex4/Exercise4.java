package ex4;

import org.apache.spark.sql.*;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.lit;

public class Exercise4 {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().appName("Read_Parquet_File").getOrCreate();

        //big file
        Dataset<Row> dataset = spark.read().parquet("/user/hoand68/data/BigFile.parquet"); //file Name
        dataset.createOrReplaceTempView("parquetFile");
        Dataset<Row> dataset1 = spark.sql("select * from parquetFile where device_model is not null");

        Dataset<Row> dataset2 = dataset1.withColumn("user_id_device_model", functions.array("user_id", "device_model"));
        dataset2.createOrReplaceTempView("parquetFile1");
        Dataset<Row> dataset3 = spark.sql("select user_id_device_model, button_id, count(*) as count from parquetFile1 where button_id is not null group by user_id_device_model,button_id having count(*)>=1");
        dataset3.show();
        dataset3.repartition(1).write().mode("overwrite").format("parquet").save("/user/hoand68/button_count_by_user_id_device_model/big file");

        //small file
        Dataset<Row> dataset4 = spark.read().parquet("/user/hoand68/data/SmallFile.parquet"); //file Name
        dataset4.createOrReplaceTempView("parquetFile2");
        Dataset<Row> dataset5 = spark.sql("select * from parquetFile where device_model is not null");

        Dataset<Row> dataset6 = dataset5.withColumn("user_id_device_model", functions.array("user_id", "device_model"));
        dataset6.createOrReplaceTempView("parquetFile3");
        Dataset<Row> dataset7 = spark.sql("select user_id_device_model, button_id, count(*) as count from parquetFile1 where button_id is not null group by user_id_device_model,button_id having count(*)>=1");
        dataset7.show();
        dataset7.repartition(1).write().mode("overwrite").format("parquet").save("/user/hoand68/button_count_by_user_id_device_model/small file");

    }
}
