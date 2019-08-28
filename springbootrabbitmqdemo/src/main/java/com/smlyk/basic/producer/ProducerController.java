package com.smlyk.basic.producer;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author yekai
 */
@RestController
@RequestMapping("produce")
public class ProducerController {

    @Autowired
    @Qualifier("ykRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @Value("${directexchange.yk}")
    private String directexchange;

    @Value("${topicexchange.yk}")
    private String topicexchange;

    @Value("${fanoutexchange.yk}")
    private String fanoutexchange;

    @Value("${directrountingkey.yk}")
    private String directrountingkey;

    @Value("${topicrountingkey.yk}")
    private String topicrountingkey;

    @GetMapping("direct")
    public String direct(){

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(directexchange, "aa", "aaaa", correlationData);

        return "Success";
    }

    @GetMapping("topic")
    public String topic(){

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(topicexchange, "a.yk.a", "bbbb", correlationData);

        return "Success";
    }

    @GetMapping("fanout")
    public String fanout(){

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(fanoutexchange, "", "cccc", correlationData);

        return "Success";
    }



}
