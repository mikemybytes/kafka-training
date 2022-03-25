package com.mikemybytes.kafka.basics;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public MessageSender(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Bean
  public NewTopic messagesTopic() {
    return TopicBuilder.name("messages").partitions(3).build();
  }

  public void send(String message) {
    kafkaTemplate.send("messages", message);
  }

}
