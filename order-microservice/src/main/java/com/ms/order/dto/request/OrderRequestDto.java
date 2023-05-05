package com.ms.order.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private Long productId;
    private BigDecimal totalAmount;
    private Long userId;

}
