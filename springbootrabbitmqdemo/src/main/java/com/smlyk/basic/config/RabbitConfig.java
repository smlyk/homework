package com.smlyk.basic.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.UUID;

/**
 * @author yekai
 */
@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.addresses}")
    private String rabbitMqAddress;

    @Value("${spring.rabbitmq.username}")
    private String rabbitMqUserName;

    @Value("${spring.rabbitmq.password}")
    private String rabbitMqPassword;

    @Value("${spring.rabbitmq.virtual-host}")
    private String rabbitMqVirtualHost;


    @Value("${directexchange.yk}")
    private String directexchange;

    @Value("${topicexchange.yk}")
    private String topicexchange;

    @Value("${fanoutexchange.yk}")
    private String fanoutexchange;

    @Value("${directqueue.yk}")
    private String directqueue;

    @Value("${topicqueue.yk}")
    private String topicqueue;

    @Value("${fanoutqueue.yk}")
    private String fanoutqueue;

    @Value("${directrountingkey.yk}")
    private String directrountingkey;

    @Value("${topicrountingkey.yk}")
    private String topicrountingkey;


    //连接工厂
    @Bean("ykConnectionFactory")
    @Primary
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(rabbitMqAddress);
        connectionFactory.setUsername(rabbitMqUserName);
        connectionFactory.setPassword(rabbitMqPassword);
        connectionFactory.setVirtualHost(rabbitMqVirtualHost);

        //若使用confirm-callback或return-callback，必须要配置publisherConfirms或publisherReturns为true
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);

        return connectionFactory;
    }


    //创建交换机
    @Bean("directExchange")
    public DirectExchange directExchange(){
        return new DirectExchange(directexchange, true, false);
    }

    @Bean("topicExchange")
    public TopicExchange topicExchange(){
        return new TopicExchange(topicexchange, true, false);
    }

    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(fanoutexchange, true, false);
    }


    //创建队列  public Queue(String name){ this(name, true, false, false);}
    @Bean("directQueue")
    public Queue directQueue(){
        return new Queue(directqueue);
    }

    @Bean("topicQueue")
    public Queue topicQueue(){
        return new Queue(topicqueue);
    }

    @Bean("fanoutQueue")
    public Queue fanoutQueue(){
        return new Queue(fanoutqueue);
    }

    //定义绑定关系
    @Bean
    public Binding bindDirect(@Qualifier("directQueue") Queue queue, @Qualifier("directExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(directrountingkey);
    }

    @Bean
    public Binding bindTopic(@Qualifier("topicQueue") Queue queue, @Qualifier("topicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(topicrountingkey);
    }

    @Bean
    public Binding bindFanout(@Qualifier("fanoutQueue") Queue queue, @Qualifier("fanoutExchange") FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }


    /**
     * 在消费端转换JSON消息
     * 监听类都要加上containerFactory属性
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(@Qualifier("ykConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setAutoStartup(true);
        factory.setDefaultRequeueRejected(false);
        factory.setConsumerTagStrategy(queue -> queue + "_" + UUID.randomUUID().toString());
        return factory;
    }



    @Bean("ykRabbitTemplate")
    public RabbitTemplate rabbitTemplate(@Qualifier("ykConnectionFactory") ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        //若使用confirm-callback或return-callback，必须要配置publisherConfirms或publisherReturns为true
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("回发的消息：");
            System.out.println("replyCode: "+replyCode);
            System.out.println("replyText: "+replyText);
            System.out.println("exchange: "+exchange);
            System.out.println("routingKey: "+routingKey);
        });

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            String id = correlationData != null ? correlationData.getId() : "";
            if (ack) {
                System.out.println("消息确认成功, id: ="+  id);
            } else {
                System.out.println("消息未成功投递, id:{}, cause: " + id + cause);
            }
        });


        return rabbitTemplate;
    }


}
