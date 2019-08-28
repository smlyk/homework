package com.smlyk.basic.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author yekai
 */
@Component
public class DelayConsumer {

    @RabbitHandler
    @RabbitListener(queues = "${delayqueue.yk}", containerFactory="rabbitListenerContainerFactory")
    public void process(String msg) throws IOException {
        System.out.println("delay queue received msg : " + msg);
    }


}
