package com.reactive.webflux.playground.sec09.controller;

import com.reactive.webflux.playground.sec09.dto.ProductDto;
import com.reactive.webflux.playground.sec09.dto.UploadResponse;
import com.reactive.webflux.playground.sec09.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> mono){
        return this.service.saveProducts(mono);
    }

    @GetMapping(value = "/stream/{maxPrice}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductDto> productStream(@PathVariable Integer maxPrice){
        return this.service.productStream()
                .filter(dto -> dto.price() <= maxPrice);
    }
}
