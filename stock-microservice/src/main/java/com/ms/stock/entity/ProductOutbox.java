package com.ms.stock.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ms.stock.dto.enums.ProductOutboxStatus;
import com.ms.stock.dto.enums.ProductOutboxStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "product_outbox")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductOutbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdDate;

    private String aggregateType;

    private String eventType;

    private String payload;

    @Column(unique = true)
    private String idempotentKey;

    private ProductOutboxStatus status;

    private Long orderId;

}
