package com.reactive.webflux.playground.sec09.mapper;

import com.reactive.webflux.playground.sec09.dto.ProductDto;
import com.reactive.webflux.playground.sec09.entity.Product;

public class EntityDtoMapper {

    public static Product toEntity(ProductDto productDto) {
        var product = new Product();
        product.setId(productDto.id());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        return product;
    }

    public static ProductDto toDto(Product product) {
        return new ProductDto(product.getId(), product.getDescription(), product.getPrice());
    }
}
