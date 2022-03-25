package com.mikemybytes.kafka.shopping.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class CartService {

    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    private final KafkaTemplate<String, CartOrderedEvent> kafkaTemplate;

    public CartService(KafkaTemplate<String, CartOrderedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

//    @Scheduled(cron = "*/20 * * * * *") // every 20 seconds
//    public void sendPeriodically() {
//        // simulates REST/event trigger
//        String id = UUID.randomUUID().toString();
//        String user = "usr_" + Math.abs(new SecureRandom().nextInt() % 4); // 4 different users
//        List<Long> items = Arrays.asList(1L, 2L, 3L);
//        Cart cart = new Cart(id, user, items);
//
//        log.info("Processing cart: " + cart);
//        process(cart);
//    }

    @Transactional
    public void process(Cart cart) {
        var event = new CartOrderedEvent();
        event.setId(cart.getId());
        event.setUser(cart.getUser());
        event.setItems(cart.getItems());
        log.info("Sending {}", event);
        try {
            kafkaTemplate.send("cart_orders", cart.getUser(), event).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while sending event", e);
        } catch (ExecutionException e) {
            throw new IllegalStateException("Failed to send event", e);
        }
    }

}
