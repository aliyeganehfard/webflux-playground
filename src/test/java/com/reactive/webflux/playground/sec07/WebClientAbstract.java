package com.reactive.webflux.playground.sec07;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ProblemDetail;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Consumer;

@Slf4j
@SpringBootTest
public class WebClientAbstract {


    private WebClient createWebClient() {
        return createWebClient((builder) ->

                log.info("Creating web client"));
    }

    private WebClient createWebClient(Consumer<WebClient.Builder> consumer){
        var webClient = WebClient.builder().baseUrl("http://localhost:8080");
        consumer.accept(webClient);
        return webClient.build();
    }

    @Test
    public void createTest(){
        createWebClient();

        var web1 = createWebClient(builder -> builder.baseUrl("http://localhost:8088"));
        web1.get();
    }
}
