package com.ms.order.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.order.dto.response.OrderDebeziumResponse;
import com.ms.order.entity.OrderOutbox;
import com.ms.order.repository.OrderOutboxRepository;
import com.ms.order.util.Operation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxService {

    private final OrderOutboxRepository orderOutboxRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    public void save(OrderOutbox orderOutbox){
        orderOutboxRepository.save(orderOutbox);
    }

    @SneakyThrows
    public void maintainReadModel(Map<String, Object> productData, Operation operation)  {
        final OrderDebeziumResponse response = mapper.convertValue(productData, OrderDebeziumResponse.class);
        kafkaTemplate.send("order-created",response.getIdempotent_key(),mapper.writeValueAsString(response));
    }
}
