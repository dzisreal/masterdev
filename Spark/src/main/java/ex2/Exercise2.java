package ex2;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Exercise2 {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().appName("Read_Parquet_File").getOrCreate();

        //Big File
        Dataset<Row> datasetBig = spark.read().parquet("/user/hoand68/data/BigFile.parquet"); //file Name
        datasetBig.createOrReplaceTempView("parquetFileBig");
        Dataset<Row> datasetDFBig= spark.sql("select * from parquetFileBig where device_model is not null");

        datasetDFBig.createOrReplaceTempView("NotNullDFBig");
        Dataset<Row> countDFBig = spark.sql("select device_model, count(distinct user_id) as count from NotNullDFBig group by device_model ");
        countDFBig.show();
        countDFBig.repartition(1).write().mode("overwrite").format("parquet").save("/user/hoand68/device_model_list_user/big file");


        Dataset<Row> lstUserIdDFBig = spark.sql("select device_model, collect_set(user_id) as list_user_id from NotNullDFBig group by device_model");
        lstUserIdDFBig.show();
        lstUserIdDFBig.repartition(1).write().mode("overwrite").format("orc").save("/user/hoand68/device_model_num_user/big file");




        //Small File
        Dataset<Row> datasetSmall = spark.read().parquet("/user/hoand68/data/SmallFile.parquet"); //file Name
        datasetSmall.createOrReplaceTempView("parquetFileSmall");
        Dataset<Row> datasetDFSmall= spark.sql("select * from parquetFileSmall where device_model is not null");

        datasetDFSmall.createOrReplaceTempView("NotNullDFSmall");
        Dataset<Row> countDFSmall = spark.sql("select device_model, count(distinct user_id) as count from NotNullDFSmall group by device_model ");
        countDFSmall.show();
        countDFSmall.repartition(1).write().mode("overwrite").format("parquet").save("/user/hoand68/device_model_list_user/small file");


        Dataset<Row> lstUserIdDFSmall = spark.sql("select device_model, collect_set(user_id) as list_user_id from NotNullDFSmall group by device_model");
        lstUserIdDFSmall.show();
        lstUserIdDFSmall.repartition(1).write().mode("overwrite").format("orc").save("/user/hoand68/device_model_num_user/small file");
    }
}
