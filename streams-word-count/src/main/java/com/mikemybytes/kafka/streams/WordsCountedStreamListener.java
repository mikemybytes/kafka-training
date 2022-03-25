package com.mikemybytes.kafka.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WordsCountedStreamListener {

    @Bean
    KStream<String, Long> wordCountListenerStream(StreamsBuilder builder) {
        var stream = builder.stream(WordCountTopology.WORDS_COUNTED_TOPIC, Consumed.with(Serdes.String(), Serdes.Long()));
        stream.print(Printed.toSysOut());
        return stream;
    }

}
