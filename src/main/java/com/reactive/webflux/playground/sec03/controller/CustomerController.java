package com.reactive.webflux.playground.sec03.controller;

import com.reactive.webflux.playground.sec03.dto.CustomerDto;
import com.reactive.webflux.playground.sec03.exception.ApplicationExceptions;
import com.reactive.webflux.playground.sec03.filter.Category;
import com.reactive.webflux.playground.sec03.service.CustomerService;
import com.reactive.webflux.playground.sec03.validator.RequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Flux<CustomerDto> findAll(@RequestAttribute("category") Category category) {
        log.info(category.name());
        return this.customerService.getAllCustomers();
    }

    @GetMapping("/paginated")
    public Mono<List<CustomerDto>> allCustomersPaginated(@RequestParam(defaultValue = "0") Integer page,
                                                         @RequestParam(defaultValue = "3") Integer size) {
        return this.customerService.getAllCustomers(page, size)
                .collectList();
    }

    @GetMapping("{id}")
    public Mono<CustomerDto> findById(@PathVariable Integer id) {
        return this.customerService.getCustomerById(id)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id));
    }

    @PostMapping
    public Mono<CustomerDto> save(@RequestBody Mono<CustomerDto> customerDto) {
        return customerDto.transform(RequestValidator.validate())
                .as(this.customerService::saveCustomer);
    }

    @PutMapping("{id}")
    public Mono<CustomerDto> update(@PathVariable Integer id, @RequestBody Mono<CustomerDto> customerDto) {
        return customerDto.transform(RequestValidator.validate()).
                as(req -> this.customerService.updateCustomer(id, req))
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id));
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return this.customerService.deleteCustomerById(id)
                .filter(b -> b)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id))
                .then();
    }

}
