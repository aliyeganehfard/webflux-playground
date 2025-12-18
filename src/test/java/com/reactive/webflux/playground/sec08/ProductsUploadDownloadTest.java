package com.reactive.webflux.playground.sec08;

import com.reactive.webflux.playground.sec08.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.nio.file.Path;
import java.time.Duration;

@Slf4j
public class ProductsUploadDownloadTest {

    private final ProductClient productClient = new ProductClient();

//    @Test
    public void upload() {
//        var flux = Flux.just(new ProductDto(null, "iphone", 1000))
//                .delayElements(Duration.ofSeconds(10));

        var flux = Flux.range(1, 1_000_000)
                .map(i -> new ProductDto(null, "product-" + i, i));
        this.productClient.uploadProducts(flux)
                .doOnNext(r -> log.info("received {}", r))
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }

    @Test
    public void downloadProducts() {
        this.productClient.downloadProducts()
                .map(ProductDto::toString)
                .as(flux -> FileWriter.create(flux, Path.of("src/main/resources/tmp/products.txt")))
                .as(StepVerifier::create)
                .expectComplete()
                .verify();


    }
}
