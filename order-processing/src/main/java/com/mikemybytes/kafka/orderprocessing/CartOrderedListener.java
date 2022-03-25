package com.mikemybytes.kafka.orderprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class CartOrderedListener {

    private static final Logger log = LoggerFactory.getLogger(CartOrderedListener.class);

    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @KafkaListener(topics = "cart_orders")
    public void onEvent(@Payload CartOrderedEvent event) {
        log.warn("Cart ordered: {}", event);
    }

}
