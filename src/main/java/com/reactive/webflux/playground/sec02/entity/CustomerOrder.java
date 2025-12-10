package com.reactive.webflux.playground.sec02.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "customer_order")
public class CustomerOrder {

    @Id
    private UUID orderId;
    private Integer customerId;
    private Integer productId;
    private Integer amount;
    private Instant orderDate;
}
