package com.reactive.webflux.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

//@EnableR2dbcRepositories()
@EnableR2dbcRepositories(basePackages = "com.reactive.webflux.playground.${sec}")
@SpringBootApplication(scanBasePackages = "com.reactive.webflux.playground.${sec}")
//@SpringBootApplication()
public class WebfluxPlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxPlaygroundApplication.class, args);
    }

}
