package com.amazon_lite.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amazon_lite.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
