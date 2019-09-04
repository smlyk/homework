package com.smlyk;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author yekai
 */
public class MyPartitioner implements Partitioner{

    private Random random = new Random();

    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        //获取集群中指定topic的所有分区信息
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(s);
        int partitionNum = partitionInfos.size();
        int partitionIndex = 0;
        if (null == o){
            //key没有设置,随机指定分区
            partitionIndex = random.nextInt(partitionNum);
        }else {
            partitionIndex = Math.abs(o1.hashCode()) % partitionNum;
        }

        System.out.println("key-> " + o +", value-> " + o1 +"->send to partition: " + partitionIndex);

        return partitionIndex;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
