package com.amazon_lite.order.mapper;

import com.amazon_lite.order.dto.OrderItemResponseDTO;
import com.amazon_lite.order.dto.OrderResponseDTO;
import com.amazon_lite.order.entity.Order;

public class OrderMapper {

        public static OrderResponseDTO toDTO(Order order) {
                return OrderResponseDTO.builder()
                                .id(order.getId())
                                .status(order.getStatus().name())
                                .totalAmount(order.getTotalAmount())
                                .items(
                                        order.getItems().stream()
                                                .map(item -> OrderItemResponseDTO.builder()
                                                        .productName(item.getProduct()
                                                                        .getName())
                                                        .quantity(item.getQuantity())
                                                        .price(item.getPrice())
                                                        .build())
                                                .toList())
                                .build();

        }

}
