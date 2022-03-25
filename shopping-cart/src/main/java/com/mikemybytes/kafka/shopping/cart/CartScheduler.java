package com.mikemybytes.kafka.shopping.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CartScheduler {

    private static final Logger log = LoggerFactory.getLogger(CartScheduler.class);

    private final CartService cartService;

    public CartScheduler(CartService cartService) {
        this.cartService = cartService;
    }

    @Scheduled(cron = "*/20 * * * * *") // every 20 seconds
    public void sendPeriodically() {
        // simulates REST/event trigger
        String id = UUID.randomUUID().toString();
        String user = "usr_" + Math.abs(new SecureRandom().nextInt() % 4); // 4 different users
        List<Long> items = Arrays.asList(1L, 2L, 3L);
        Cart cart = new Cart(id, user, items);

        log.info("Scheduled processing of: " + cart);
        cartService.process(cart);
    }

}
