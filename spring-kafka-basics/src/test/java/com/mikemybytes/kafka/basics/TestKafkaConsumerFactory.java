package com.mikemybytes.kafka.basics;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.UUID;

class TestKafkaConsumerFactory {

    static KafkaConsumer<String, String> create(String bootstrapServers) {
        String consumerGroup = UUID.randomUUID().toString(); // random consumer group (fresh start)
        String autoCommit = "true"; // commits offset automatically time-based or with each poll() call

        var consumerProps =
                KafkaTestUtils.consumerProps(bootstrapServers, consumerGroup, autoCommit);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new KafkaConsumer<>(consumerProps);
    }

}
