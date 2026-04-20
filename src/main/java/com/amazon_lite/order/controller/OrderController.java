package com.amazon_lite.order.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon_lite.order.dto.OrderRequestDTO;
import com.amazon_lite.order.dto.OrderResponseDTO;
import com.amazon_lite.order.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponseDTO create(@Valid @RequestBody OrderRequestDTO dto) {
        return service.createOrder(dto);
    }

    @GetMapping
    public List<OrderResponseDTO> getMyOrders() {
        return service.getOrdersForCurrentUser();
    }
}