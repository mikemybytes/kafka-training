package com.mikemybytes.kafka.basics;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.StreamSupport;

@Testcontainers
@SpringBootTest
public class MessageSenderTestContainersTest {

    @Container
    private static final KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));

    @DynamicPropertySource
    static void springKafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    private MessageSender sender;

    @Test
    public void sendsKafkaMessage() {
        // given
        KafkaConsumer<String, String> consumer = TestKafkaConsumerFactory.create(kafka.getBootstrapServers());
        consumer.subscribe(List.of("messages"));

        // when
        sender.send("hello");

        // then
        Awaitility.await()
                .atMost(30, TimeUnit.SECONDS)
                .pollInterval(250, TimeUnit.MILLISECONDS)
                .until(() -> {
                    var records = consumer.poll(Duration.ofMillis(50));
                    return StreamSupport.stream(records.spliterator(), false)
                            .anyMatch(r -> "hello".equals(r.value()));
                });
    }

}
