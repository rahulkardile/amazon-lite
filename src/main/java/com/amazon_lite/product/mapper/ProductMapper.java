package com.amazon_lite.product.mapper;

import com.amazon_lite.product.dto.ProductRequestDTO;
import com.amazon_lite.product.dto.ProductResponseDTO;
import com.amazon_lite.product.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequestDTO dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setStock(dto.getStock());
        return p;
    }

    public static ProductResponseDTO toDTO(Product p) {
        return ProductResponseDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .price(p.getPrice())
                .ownerEmail(p.getOwner() != null ? p.getOwner().getEmail()
                        : null)
                .build();
    }
}