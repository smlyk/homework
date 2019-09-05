package com.smlyk;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.CountDownLatch;

/**
 * @author yekai
 */
public class AsyncProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {

        DefaultMQProducer producer = new DefaultMQProducer("second_producer_group");

        producer.setNamesrvAddr("47.101.129.30:9876");

        producer.start();

        producer.setRetryTimesWhenSendAsyncFailed(0);

        int messageCount = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        for (int i = 0; i<messageCount; i++){

            final int index = i;
            Message msg = new Message("second_topic", "second_tag", "test_keys",("Hello world Async"+i).getBytes());

            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {

                    countDownLatch.countDown();
                    System.out.println(index + " OK ,msgId = "+ sendResult.getMsgId());

                }

                @Override
                public void onException(Throwable e) {
                    countDownLatch.countDown();
                    System.out.println(index + " failed, exception : "+ e.getMessage());
                    e.printStackTrace();
                }
            });
        }

        countDownLatch.await();
        producer.shutdown();

    }

}
