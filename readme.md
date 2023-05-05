# Saga Distributed Transaction Pattern - Spring boot + embedded debezium + kafka + inbox-outbox pattern

Medium article link -> [Medium Link](https://medium.com/@htyesilyurt/saga-distributed-transaction-pattern-spring-boot-embedded-debezium-apache-kafka-8d111de0b444).

Technologies
------------
- `Spring Boot`
- `Changes Data Capture (CDC) with embedded debezium` 
- `Inbox-Outbox Pattern ( Inbox For Exactly once semantic - Outbox for CDC)`
- `Apache Kafka ( Data streaming )`
- `PostgreSQL`

## Run the System

We can easily run the whole with only a single command:

* `docker-compose up -d`

#### 5: Starting order-microservice`

```shell
./order-microservice
mvn spring-boot:run
```

#### 5: Starting stopk-microservice`

```shell
./stock-microservice
mvn spring-boot:run
```

