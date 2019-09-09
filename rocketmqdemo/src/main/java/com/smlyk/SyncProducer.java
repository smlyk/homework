package com.smlyk;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author yekai
 */
public class SyncProducer {


    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        /**
      * 生产者组，简单来说就是多个发送同一类消息的生产者称之为一个生产者组
         * rocketmq支持事务消息，在发送事务消息时，如果事务消息异常（producer挂了），broker端会来
         * 回查事务的状态，这个时候会根据group名称来查找对应的producer来执行相应的回查逻辑。相当于实现了
         * producer的高可用  
      */
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("first_producer_group");

        //指定namesrv服务地址，获取broker相关信息
        producer.setNamesrvAddr("47.101.129.30:9876");

        //Launch the instance.
        producer.start();

        for (int i = 0; i<20; i++){
            //创建一个消息实例，指定指定topic、tag、消息内容
            Message message = new Message("first_topic", "first_tag",("Hello RocketMQ "+ i).getBytes());

            //发送消息并且获取发送结果
            SendResult sendResult = producer.send(message, (list, message1, o) -> {

                int key = Math.abs(o.hashCode());
                int size = list.size();
                int index = key%size;
                return list.get(0);
            }, "key_" + i);
            System.out.println(sendResult);
        }

        //Shut down once the producer instance is not longer in use.
        producer.shutdown();

    }

}
