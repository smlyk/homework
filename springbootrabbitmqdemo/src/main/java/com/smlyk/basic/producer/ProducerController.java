package com.smlyk.basic.producer;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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

    @Value("${delayexchange.yk}")
    private String delayexchange;

    @Value("${directrountingkey.yk}")
    private String directrountingkey;

    @Value("${topicrountingkey.yk}")
    private String topicrountingkey;

    @Value("${delayroutingkey.yk}")
    private String delayrountingkey;

    @GetMapping("direct")
    public String direct(){

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(directexchange, directrountingkey, "aaaa", correlationData);

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

    public final static Long DELAY_TIME=5000L;

    @GetMapping("delay")
    public String delay(){

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        LocalDateTime localDateTime = LocalDateTime.now();

        rabbitTemplate.convertAndSend(delayexchange,
                delayrountingkey,
                "dddd"+" 发送时间"+localDateTime,
                message ->  {
                    message.getMessageProperties().setHeader("x-delay", DELAY_TIME);
                    return message;
                },correlationData);


        return "Success";
    }



}
