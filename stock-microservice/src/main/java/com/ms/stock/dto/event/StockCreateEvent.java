package com.ms.stock.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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