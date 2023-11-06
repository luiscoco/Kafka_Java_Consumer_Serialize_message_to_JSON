# Kafka Java Consumer application

## 1. Project structure



## 2. Source Code

**YourMessageClass.java**

```java



```

**KafkaConsumerApp.java**
```java

```

**log4j.properties**
```

```

## 3. Download the application dependencies (JAR file)

Navigate to the Apache Kafka web page URL, and download the **Kafka** JAR file

https://kafka.apache.org/downloads

Download the Scala 2.13 file: **kafka_2.13-3.6.0.tgz**

Donwload the **Jackson Databind** and **Jackson Core** JAR files

https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core/2.15.3

https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.15.3

Create a new **lib** folder in your Java application and place all the dependencies (JAR files)



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



