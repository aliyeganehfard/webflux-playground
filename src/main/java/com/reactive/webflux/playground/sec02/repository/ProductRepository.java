package com.reactive.webflux.playground.sec02.repository;

import com.reactive.webflux.playground.sec02.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

//    @Query("""
//            SELECT p FROM Product p
//            WHERE p.price BETWEEN :from AND :to
//            """)
    Flux<Product> findByPriceBetween(int from, int to);

    Flux<Product> findAllBy(Pageable pageable);
}
