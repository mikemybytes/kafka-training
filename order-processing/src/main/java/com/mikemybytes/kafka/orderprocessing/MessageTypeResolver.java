package com.mikemybytes.kafka.orderprocessing;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.support.serializer.JsonTypeResolver;

public class MessageTypeResolver implements JsonTypeResolver {

  public JavaType resolveType(String topic, byte[] data, Headers headers) {
    if ("cart_orders".equals(topic)) {
      return new ObjectMapper().getTypeFactory().constructFromCanonical(CartOrderedEvent.class.getName());
    }
    return null;
  }

  public static JavaType resolve(String topic, byte[] data, Headers headers) {
    return new MessageTypeResolver().resolveType(topic, data, headers);
  }

}
