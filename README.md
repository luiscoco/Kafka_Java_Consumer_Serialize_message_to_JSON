# Kafka Java Consumer application

## 1. Project structure

![image](https://github.com/luiscoco/Kafka_Java_Consumer_Serialize_message_to_JSON/assets/32194879/a0c7e5ef-9d16-4b28-857e-835627ccc81c)

## 2. Source Code

**YourMessageClass.java**

```java
public class YourMessageClass {
    private String content;

    public YourMessageClass() {
        // Default constructor for Jackson
    }

    public YourMessageClass(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
```

**KafkaConsumerApp.java**
```java
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerApp {
    public static void main(String[] args) {
        // Set up consumer properties
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "your-group");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // Create Kafka consumer
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Subscribe to the topic
        String topic = "your-topic";
        consumer.subscribe(Collections.singletonList(topic));

        // Create ObjectMapper for JSON deserialization
        ObjectMapper objectMapper = new ObjectMapper();

        // Poll for new messages
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            records.forEach(record -> {
                // Deserialize the message from JSON
                YourMessageClass message;
                try {
                    message = objectMapper.readValue(record.value(), YourMessageClass.class);
                    System.out.println("Received message: " + message.getContent());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
```

**log4j.properties**
```
log4j.rootLogger=INFO, stdout

log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
```

## 3. Download the application dependencies (JAR file)

Navigate to the Apache Kafka web page URL, and download the **Kafka** JAR file

https://kafka.apache.org/downloads

Download the Scala 2.13 file: **kafka_2.13-3.6.0.tgz**

Donwload the **Jackson Databind** and **Jackson Core** JAR files

https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core/2.15.3

https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.15.3

Create a new **lib** folder in your Java application and place all the dependencies (JAR files)

![image](https://github.com/luiscoco/Kafka_Java_Consumer_Serialize_message_to_JSON/assets/32194879/7570c1b1-b6ce-48df-a973-7c2f08cf0680)

![image](https://github.com/luiscoco/Kafka_Java_Consumer_Serialize_message_to_JSON/assets/32194879/6052dc30-43dc-48f0-a724-deb6ec1ed6ff)

## 4. Run zookeeper and kafka-server

Run the following command to execute **zookeeper**:

```
zookeeper-server-start C:\kafka_2.13-3.6.0\config\zookeeper.properties
```

Run the followind command to start **kafka-server**:

```
kafka-server-start C:\kafka_2.13-3.6.0\config\server.properties
```

## 5. Compiling the application

Run the command

```
C:\Kafka with Java\OrderConsumer> javac -cp ".;lib/*" src/YourMessageClass.java src/KafkaConsumerApp.java
```

## 6. Running the application

```
C:\Kafka with Java\OrderConsumer> java -cp ".;lib/*;src" KafkaConsumerApp
```
