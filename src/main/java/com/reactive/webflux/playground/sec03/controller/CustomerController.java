package com.reactive.webflux.playground.sec03.controller;

import com.reactive.webflux.playground.sec03.dto.CustomerDto;
import com.reactive.webflux.playground.sec03.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Flux<CustomerDto> findAll() {
        return this.customerService.getAllCustomers();
    }

    @GetMapping("/paginated")
    public Mono<List<CustomerDto>> allCustomersPaginated(@RequestParam(defaultValue = "0") Integer page,
                                                         @RequestParam(defaultValue = "3") Integer size) {
        return this.customerService.getAllCustomers(page, size)
                .collectList();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<CustomerDto>> findById(@PathVariable Integer id) {
        return this.customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<CustomerDto> save(@RequestBody Mono<CustomerDto> customerDto) {
        return this.customerService.saveCustomer(customerDto);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<CustomerDto>> update(@PathVariable Integer id, @RequestBody Mono<CustomerDto> customerDto) {
        return this.customerService.updateCustomer(id, customerDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Integer id) {
        return this.customerService.deleteCustomerById(id)
                .filter(b -> b)
                .map(b -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
