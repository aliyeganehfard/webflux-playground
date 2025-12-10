package com.reactive.webflux.playground.sec02.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "product")
public class Product {

    @Id
    private Integer id;

    private String description;

    private Integer price;
}
