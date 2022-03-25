package com.mikemybytes.kafka.basics;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

  private final MessageSender messageSender;

  public MessageController(MessageSender messageSender) {
    this.messageSender = messageSender;
  }

  @PostMapping
  public void createMessage(@RequestBody String message) {
    messageSender.send(message);
  }

}


