package com.mikemybytes.kafka.orderprocessing;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;

import java.util.Map;

@Configuration
public class KafkaConfiguration {

    private static final Logger log = LoggerFactory.getLogger(KafkaConfiguration.class);

//    TODO: uncomment when things will go wrong just as expected ;)
//    @Bean
//    ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
//            ConsumerFactory<Object, Object> consumerFactory, ProducerFactory<Object, Object> producerFactory) {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        factory.getContainerProperties().setAckMode(AckMode.RECORD);
//
//        DefaultErrorHandler errorHandler = createErrorHandler(producerFactory);
//        factory.setCommonErrorHandler(errorHandler);
//        return factory;
//    }
//
//    private DefaultErrorHandler createErrorHandler(ProducerFactory<Object, Object> producerFactory) {
//        KafkaOperations<Object, Object> template = new KafkaTemplate<>(producerFactory, Map.of(
//                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//                org.apache.kafka.common.serialization.ByteArraySerializer.class
//        ));
//        DefaultErrorHandler errorHandler = new DefaultErrorHandler(new DeadLetterPublishingRecoverer(template) {
//            @Override
//            public void accept(ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, Exception exception) {
//                log.warn("Sending failed record to DLQ", exception);
//                super.accept(record, consumer, exception);
//            }
//        });
//        errorHandler.setAckAfterHandle(true);
//        return errorHandler;
//    }

}
