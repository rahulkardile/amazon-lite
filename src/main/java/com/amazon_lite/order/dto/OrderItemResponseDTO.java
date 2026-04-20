package com.amazon_lite.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponseDTO {
    private String productName;
    private Integer quantity;
    private Double price;
}
