package com.ms.stock.configuration;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class DebeziumConnectorConfig {
    @Bean
    public io.debezium.config.Configuration sagaConnector() {
        return io.debezium.config.Configuration.create()
                .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
                .with("offset.storage",  "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", "/Users/tugayesilyurt/Desktop/Projects/saga-offset.dat")
                .with("offset.flush.interval.ms", 60000)
                .with("name", "saga-postgres-connector-product")
                .with("database.server.name", "saga-outbox-server-product")
                .with("database.hostname", "localhost")
                .with("database.port", "5432")
                .with("database.user", "saga")
                .with("database.password", "saga")
                .with("database.dbname", "saga")
                .with("topic.prefix","test-product")
                .with("decimal.handling.mode","string")
                .with("wal_level","logical")
                .with("plugin.name","pgoutput")
                .with("table.include.list","public.product_outbox")
                .with("slot.name", RandomStringUtils.randomAlphabetic(5).toLowerCase()).build();
    }
}
