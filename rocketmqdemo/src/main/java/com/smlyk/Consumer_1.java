package com.smlyk;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * @author yekai
 */
public class Consumer_1 {

    public static void main(String[] args) throws MQClientException {

        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("first_consumer_group2");

        // Specify name server addresses.
        consumer.setNamesrvAddr("47.101.129.30:9876");

        // Subscribe one more more topics to consume.
        //*表示不过滤，可以通过tag来过滤，比如:”tagA”
        consumer.subscribe("first_topic","*");

        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, consumeConcurrentlyContext) -> {

//            System.out.printf("%s Receive New Message: %s %n", Thread.currentThread().getName(), msgs);

            msgs.stream()
                    .forEach(msg -> System.out.println("Receive New Message: "+ new String(msg.getBody())));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }

}
