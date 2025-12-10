package com.reactive.webflux.playground.sec02.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@Slf4j
@SpringBootTest
public class Lec03CustomerOrderRepositoryTest {

    @Autowired
    private CustomerOrderRepository repository;

    @Test
    public void productsOrderedByCustomer() {
        this.repository.getProductsOrderedByCustomer("mike")
                .doOnNext(p -> log.info("{}", p))
                .as(StepVerifier::create)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    public void getOrderDetailsByProduct() {
        this.repository.getOrderDetailsByProduct("iphone 20")
                .doOnNext(dto -> log.info("{}", dto))
                .as(StepVerifier::create)
                .assertNext(dto -> Assertions.assertEquals(975, dto.amount()))
                .assertNext(dto -> Assertions.assertEquals(950, dto.amount()))
                .verifyComplete();
    }
}
