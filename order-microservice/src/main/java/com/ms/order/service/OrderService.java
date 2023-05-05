package com.ms.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.order.dto.enums.OrderOutboxStatus;
import com.ms.order.dto.enums.OrderStatus;
import com.ms.order.dto.event.StockCreateEvent;
import com.ms.order.dto.request.OrderRequestDto;
import com.ms.order.entity.Order;
import com.ms.order.entity.OrderOutbox;
import com.ms.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OutboxService outboxService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Boolean createOrder(OrderRequestDto orderRequestDto){
        Order order = orderRepository.save(convertToOrder(orderRequestDto));
        outboxService.save(toOutboxEntity(order));
        return true;
    }

    private OrderOutbox toOutboxEntity(Order order){
        String payload = null;
        String idempotentKey = RandomStringUtils.randomAlphanumeric(10);
        try{
            StockCreateEvent stockCreateEvent = StockCreateEvent.builder()
                    .orderId(order.getId())
                    .productId(order.getProductId())
                    .idempotentKey(idempotentKey)
                    .totalAmount(order.getTotalAmount())
                    .build();

            payload = objectMapper.writeValueAsString(stockCreateEvent);

        }catch (JsonProcessingException ex){
            log.error("Object could not convert to String. Object: {}", order.toString());
            throw new RuntimeException(ex);
        }

        return OrderOutbox.builder()
                .status(OrderOutboxStatus.CREATED)
                .createdDate(LocalDateTime.now())
                .idempotentKey(idempotentKey)
                .payload(payload)
                .aggregateType("Order")
                .eventType("OrderCreated")
                .orderId(order.getId())
                .build();
    }

    private Order convertToOrder(OrderRequestDto orderRequestDto){
        return Order.builder()
                .createdDate(LocalDateTime.now())
                .totalAmount(orderRequestDto.getTotalAmount())
                .productId(orderRequestDto.getProductId())
                .userId(orderRequestDto.getUserId())
                .orderStatus(OrderStatus.CREATED)
                .description("Order Created")
                .build();
    }
}
