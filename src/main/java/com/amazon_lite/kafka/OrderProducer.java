package com.amazon_lite.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.amazon_lite.order.event.OrderCreatedEvent;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.order-created}")
    private String topic;

    public OrderProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        try {
            kafkaTemplate.send(topic, event).get();
            System.out.println("Kafka event sent: " + event);
        } catch (Exception e) {
            System.out.println("Kafka send failed: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Kafka send failed", e);
        }
    }
}