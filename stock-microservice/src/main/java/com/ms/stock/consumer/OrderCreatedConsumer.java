package com.ms.stock.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.stock.dto.response.OrderDebeziumResponse;
import com.ms.stock.entity.ProductInbox;
import com.ms.stock.repository.ProductInboxRepository;
import com.ms.stock.service.StockService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderCreatedConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ProductInboxRepository stockInboxRepository;
    private final StockService stockService;

    @KafkaListener(topics = "order-created", groupId = "orderCreatedConsumer")
    public void handleOrderCreate(@Payload String message,
                                  @Header(KafkaHeaders.RECEIVED_KEY) @NonNull String idempotentKey,
                                  Acknowledgment acknowledgment) throws JsonProcessingException {

        log.info("message " + message);
        log.info("key " + idempotentKey);

        if (stockInboxRepository.findByIdempotentKey(idempotentKey).isPresent()){
            log.error("Stock inbox not created, {} already exist!", idempotentKey);
            acknowledgment.acknowledge();
            return;
        }

        final OrderDebeziumResponse response = objectMapper.readValue(message,OrderDebeziumResponse.class);

        stockInboxRepository.save(createStockInbox(message,idempotentKey));

        stockService.stockEventCreate(response);

        acknowledgment.acknowledge();
    }

    private ProductInbox createStockInbox(String event, String idempotentKey){
        return ProductInbox.builder()
                .payload(event)
                .createdDate(LocalDateTime.now())
                .idempotentKey(idempotentKey)
                .build();
    }
}
