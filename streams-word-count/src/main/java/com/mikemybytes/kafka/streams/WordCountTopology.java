package com.mikemybytes.kafka.streams;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.Stores;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class WordCountTopology {

    static final String TEXT_LINES_TOPIC = "text_lines";
    static final String WORDS_COUNTED_TOPIC = "words_counted";

    @Bean
    NewTopic textLinesTopic() {
        return TopicBuilder.name(TEXT_LINES_TOPIC).partitions(3).build();
    }

    @Bean
    KStream<String, String> wordCountStream(StreamsBuilder builder) {
        KStream<String, String> textLinesStream = builder.stream(TEXT_LINES_TOPIC);
        KTable<String, Long> wordCounts = textLinesStream
                .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
                .groupBy((key, word) -> word)
                .count(Materialized.as("counts-store"));
        // replace with the following when using Apple Silicon:
        // .count(Materialized.as(Stores.inMemoryKeyValueStore("counts-store")));

        wordCounts.toStream().to(WORDS_COUNTED_TOPIC, Produced.with(Serdes.String(), Serdes.Long()));
        return textLinesStream;
    }

}
