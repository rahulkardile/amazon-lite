package com.amazon_lite.product.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO implements Serializable {
    private Long id;
    private String name;
    private Double price;
    private String ownerEmail;
}