package com.amazon_lite.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.amazon_lite.order.event.OrderCreatedEvent;

@Service
public class OrderConsumer {
 
    @KafkaListener(topics = "order-created", groupId = "order-service")
    public void consume(OrderCreatedEvent event) {
        System.out.println("Order Event Received: " + event);
    }
    
}
