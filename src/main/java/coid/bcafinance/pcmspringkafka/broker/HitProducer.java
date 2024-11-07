package coid.bcafinance.pcmspringkafka.broker;

import coid.bcafinance.pcmspringkafka.configuration.KafkaProConfig;
import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.List;
import java.util.Properties;
public class HitProducer {


    public void producerHitTopics(List lt)
    {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,KafkaProConfig.getKafkaProHost());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String,String> producer = new KafkaProducer<String,String>(properties);
        for (int i = 0; i < lt.size(); i++) {
            ProducerRecord<String,String> record = new ProducerRecord<>(KafkaProConfig.getKafkaProTopics(),new Gson().toJson(lt.get(i)));
            System.out.println("Hasil Record "+record);
            producer.send(record);
        }
        producer.close();
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String,String> producer = new KafkaProducer<String,String>(properties);
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String,String> record = new ProducerRecord<>("sample-topic-4","Data ke "+i);
            producer.send(record);
        }
        producer.close();
    }
}