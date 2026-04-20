package com.amazon_lite.order.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderResponseDTO {
    private Long id;
    private String status;
    private Double totalAmount;

    private List<OrderItemResponseDTO> items;

}