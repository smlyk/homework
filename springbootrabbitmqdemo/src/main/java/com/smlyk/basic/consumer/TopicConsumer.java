package com.smlyk.basic.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author yekai
 */
@Component
public class TopicConsumer {


    @RabbitHandler
    @RabbitListener(queues = "${topicqueue.yk}", containerFactory="rabbitListenerContainerFactory")
    public void process(String msg) throws IOException {
        System.out.println("topic queue received msg : " + msg);
    }

}
