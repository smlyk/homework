package com.smlyk;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yekai
 */
@Component
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(){
        //send(String topic, K key, V data);
//        kafkaTemplate.send("test_topic","msgKey","springBoot kafka...");


        Map<String, Object> heads = new HashMap<>();
        heads.put(KafkaHeaders.TOPIC, "firstTopic");
        heads.put(KafkaHeaders.PARTITION_ID, 0);
        heads.put(KafkaHeaders.MESSAGE_KEY, "msgKey");

        Message message = new GenericMessage("GenericMessage send...", heads);
        kafkaTemplate.send(message);

    }




}
