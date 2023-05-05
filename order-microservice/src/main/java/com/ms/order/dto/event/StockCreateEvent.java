package com.ms.order.dto.event;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockCreateEvent {

    private Long orderId;
    private BigDecimal totalAmount;
    private String idempotentKey;
    private Long productId;

}