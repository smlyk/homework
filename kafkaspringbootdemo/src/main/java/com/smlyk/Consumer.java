package com.smlyk;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author yekai
 */
@Component
public class Consumer {

    @KafkaListener(topics = {"firstTopic"})
    public void listener(ConsumerRecord record){

        Optional<Object> optional = Optional.ofNullable(record.value());

        if (optional.isPresent()){
            System.out.println(optional.get());
        }

    }

}
