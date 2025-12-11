package com.reactive.webflux.playground.sec03.mapper;

import com.reactive.webflux.playground.sec03.dto.CustomerDto;
import com.reactive.webflux.playground.sec03.entity.Customer;

public class EntityDtoMapper {

    public static Customer toEntity(CustomerDto customerDto) {
        return new Customer(customerDto.id(), customerDto.name(), customerDto.email());
    }

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getName(), customer.getEmail());
    }
}
