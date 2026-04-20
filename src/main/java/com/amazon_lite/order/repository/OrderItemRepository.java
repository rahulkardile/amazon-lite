package com.amazon_lite.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amazon_lite.order.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>  {
}
