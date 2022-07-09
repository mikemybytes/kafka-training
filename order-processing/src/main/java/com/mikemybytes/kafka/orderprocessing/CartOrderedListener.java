package com.mikemybytes.kafka.orderprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class CartOrderedListener {

    private static final Logger log = LoggerFactory.getLogger(CartOrderedListener.class);

    @KafkaListener(topics = "cart_orders")
    public void onEvent(@Payload CartOrderedEvent event) {
        log.warn("Cart ordered: {}", event);
    }

}
