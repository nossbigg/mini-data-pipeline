import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SparkStreamingExample {
  public static void main(String[] args) throws InterruptedException {
    SparkConf conf = new SparkConf()
        .setAppName("some-app-name")
        .setJars(new String[]{
            SparkContext.jarOfClass(org.apache.spark.streaming.kafka010.KafkaRDDPartition.class).get(),
            SparkContext.jarOfClass(org.apache.kafka.common.TopicPartition.class).get()
        })
        .setMaster("local[4]");
    JavaStreamingContext streamingContext
        = new JavaStreamingContext(conf, Durations.seconds(2));

    Map<String, Object> kafkaParams = new HashMap<>();
    kafkaParams.put("bootstrap.servers", "localhost:9092");
    kafkaParams.put("key.deserializer", StringDeserializer.class);
    kafkaParams.put("value.deserializer", StringDeserializer.class);
    kafkaParams.put("group.id", "some-group-id-2");
    kafkaParams.put("auto.offset.reset", "latest");
    kafkaParams.put("enable.auto.commit", false);

    Collection<String> topics = Arrays.asList("some-topic");

    JavaInputDStream<ConsumerRecord<String, String>> stream =
        KafkaUtils.createDirectStream(
            streamingContext,
            LocationStrategies.PreferConsistent(),
            ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
        );

    stream
        .map(ConsumerRecord::value)
        .print();

    streamingContext.start();
    streamingContext.awaitTermination();
  }
}
