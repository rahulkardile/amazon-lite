package com.amazon_lite.product.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private Double price;
    private String ownerEmail;
}