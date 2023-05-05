package com.ms.stock.dto.response;

import com.ms.stock.dto.enums.ProductOutboxStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDebeziumResponse {

    private Long id;
    private String aggregate_type;
    private Long created_date;
    private String event_type;
    private String idempotent_key;
    private String payload;
    private ProductOutboxStatus status;
    private Long order_id;
}
