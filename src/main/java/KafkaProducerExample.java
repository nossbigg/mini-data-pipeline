import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Date;
import java.util.Properties;

public class KafkaProducerExample {
  public static void main(String[] args) throws InterruptedException {
    String topicName = "some-topic";

    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());

    Producer<String, String> producer = new KafkaProducer<>(props);

    while (true) {
      String messageContent = "hello world :" + new Date().toString();
      ProducerRecord<String, String> message
          = new ProducerRecord<>(topicName, messageContent);

      producer.send(message);
      producer.flush();

      System.out.println("Published message");

      Thread.sleep(1000);
    }
  }
}
