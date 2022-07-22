Tạo folders proto ( file DataTrackingProto.proto với nội dung như trong đề bài, DataTracking.sh để gen ra folder Genprotos)

Ta run file DataTracking.sh, gen ra folder chứa 3 file java : class DataTracking.java, DataTrackingPro, inteface DataTrackingOrBuilder, 

Class ProducerKafkaProto gửi 1 mess/s lên topic:data_tracking_hoand68 tren kafka

Class ReadkafkaProto để consume dữ liệu từ kafka

Class SparkStream3 lấy dữ liệu từ kafka đẩy lên HDFS. partition by year, month, day, hour.

folder serializers chứa KafkaDeserializer: giải mã mess khi nhận được từ kafka, KafkaSerializer :mã hóa mess trước khi đẩy lên kafka
