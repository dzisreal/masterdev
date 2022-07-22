package Spark.Streaming;

import Genprotos.DataTracking;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.streaming.Trigger;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.util.LongAccumulator;
import serializers.KafkaDeserializer;

import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import static org.apache.spark.sql.functions.udf;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.split;
public class SparkStream3 {

    public static void main(String[] args) throws StreamingQueryException {

        SparkSession spark = SparkSession
                .builder()
                .appName("Spark Kafka Integration using Structured Streaming hoand68")
//                .master("local")
                .getOrCreate();

        UserDefinedFunction strLen = udf(
                ( byte[] x) -> new KafkaDeserializer<>(DataTracking.parser()).deserialize("kafka_data_tracking_hoand68",x).toString1(),
                DataTypes.StringType);

        spark.udf().register("strLen", strLen);
//        LongAccumulator accum = spark.sparkContext().longAccumulator("accum");
//        HashMap<String, LongAccumulator> accumulatorHashMap = new HashMap<>();

        Dataset<Row> df = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "172.17.80.27:9092")
                .option("subscribe", "kafka_data_tracking_hoand68")
                .option("group.id","group2")
                .option("startingOffsets","earliest")
//                .option("auto.offset.reset","true")
                .load();

        Dataset<Row> df1 = df.selectExpr("CAST(key as string)","strLen(value) as value", "topic",
                "offset", "partition")
                .select(col("key"),
                        split(col("value"),",").getItem(0).as("version"),
                        split(col("value"),",").getItem(1).as("Name"),
                        split(col("value"),",").getItem(2).as("year"),
                        split(col("value"),",").getItem(3).as("month"),
                        split(col("value"),",").getItem(4).as("day"),
                        split(col("value"),",").getItem(5).as("hour"),
                        split(col("value"),",").getItem(6).as("PhoneId"),
                        split(col("value"),",").getItem(7).as("Lon"),
                        split(col("value"),",").getItem(8).as("lat"),
                        col("topic"),
                        col("partition"),
                        col("offset"));

//        df.map((MapFunction<Row, Row>) row -> {
//                    accum.add(1);
//                    return  row;
//                }, RowEncoder.apply(df.schema()))
//                .count();
//
//        System.out.println(accum.value());
//        df1.foreach();
//        df1.show();
//        Dataset<Row> DF = df;

        //.printSchema();
        StreamingQuery query = df1.writeStream()
//                .option("partition",1)
                .outputMode("append")
                .format("parquet")
//                .option("compression","snappy")
                .option("checkpointLocation", "/user/hoand68/checkpoint")
                .option("path", "/user/hoand68/data_tracking")
                .partitionBy("year", "month","day", "hour")
                .trigger(Trigger.ProcessingTime(5000))
                .start();
        query.awaitTermination();
//        spark.streams().awaitAnyTermination();
    }
}
