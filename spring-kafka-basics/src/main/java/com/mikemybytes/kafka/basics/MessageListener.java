package com.mikemybytes.kafka.basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

  private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

  @KafkaListener(topics = "messages")
  public void onMessage(String message) {
    log.warn("Message received: '{}'", message);
  }

}
