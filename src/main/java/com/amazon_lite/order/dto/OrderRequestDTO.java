package com.amazon_lite.order.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class OrderRequestDTO {
    
    @NotEmpty(message = "Items cannot be empty")
    private List<OrderItemRequestDTO> items;
}