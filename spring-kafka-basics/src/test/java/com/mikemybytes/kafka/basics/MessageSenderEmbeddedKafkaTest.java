package com.mikemybytes.kafka.basics;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.StreamSupport;

@SpringBootTest(properties = {
    "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}"
})
@DirtiesContext // slows tests down (invalidates Spring context)
@EmbeddedKafka(
    partitions = 1,
    topics = "messages"
)
public class MessageSenderEmbeddedKafkaTest {

  @Autowired
  private MessageSender sender;

  @Autowired
  private EmbeddedKafkaBroker embeddedKafkaBroker;

  @Test
  void sendsKafkaMessage() {
    // given
    KafkaConsumer<String, String> consumer = TestKafkaConsumerFactory.create(embeddedKafkaBroker.getBrokersAsString());
    embeddedKafkaBroker.consumeFromEmbeddedTopics(consumer, "messages");
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
