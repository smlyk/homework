package com.smlyk.basic.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

/**
 * @author yekai
 */
//@Component
public class DirectConsumer {

    @RabbitHandler
    @RabbitListener(queues = "${directqueue.yk}", containerFactory="rabbitListenerContainerFactory")
    public void process(String msg) throws IOException {
        System.out.println("direct queue received msg : " + msg);
    }


}
