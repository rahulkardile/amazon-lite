package com.amazon_lite.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.amazon_lite.enums.OrderStatus;
import com.amazon_lite.order.dto.OrderItemRequestDTO;
import com.amazon_lite.order.dto.OrderRequestDTO;
import com.amazon_lite.order.dto.OrderResponseDTO;
import com.amazon_lite.order.entity.Order;
import com.amazon_lite.order.entity.OrderItem;
import com.amazon_lite.order.mapper.OrderMapper;
import com.amazon_lite.order.repository.OrderRepository;
import com.amazon_lite.product.entity.Product;
import com.amazon_lite.product.repository.ProductRepository;
import com.amazon_lite.user.entity.User;
import com.amazon_lite.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private UserRepository userRepo;
    private ProductRepository productRepo;
    private OrderRepository orderRepo;

    public OrderService(UserRepository userRepo, ProductRepository productRepo, OrderRepository orderRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO dto) {

        if(dto.getItems() == null || dto.getItems().isEmpty()) {
                throw new RuntimeException("Order items cannot be empty");
        }

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        for (OrderItemRequestDTO itemDTO : dto.getItems()) {

            Product product = productRepo.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < itemDTO.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            // REDUCE STOCK
            product.setStock(product.getStock() - itemDTO.getQuantity());

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(product.getPrice());
            item.setOrder(order);

            total += product.getPrice() * itemDTO.getQuantity();
            items.add(item);
        }

        order.setItems(items);
        order.setTotalAmount(total);

        Order saved = orderRepo.save(order);

        return OrderMapper.toDTO(saved);
    }

    public List<OrderResponseDTO> getOrdersForCurrentUser() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepo.findByUserId(user.getId())
                .stream()
                .map(OrderMapper::toDTO)
                .toList();
    }
}
