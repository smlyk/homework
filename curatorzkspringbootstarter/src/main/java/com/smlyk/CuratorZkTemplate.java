package com.smlyk;

import com.smlyk.autoconfiguration.CuratorFrameworkConfigProperties;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @author yekai
 */
public class CuratorZkTemplate {

    private CuratorFramework curatorFramework;

    public CuratorZkTemplate(CuratorFrameworkConfigProperties properties) {
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(properties.getConnectionstr()).sessionTimeoutMs(properties.getTimeout()).
                retryPolicy(new ExponentialBackoffRetry(1000,properties.getMaxretries())).build();
        curatorFramework.start();
    }

    public void create(String path, String data) throws Exception {
        curatorFramework.create()
                .creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                forPath(path,data.getBytes());
    }

    public void update(String path, String data) throws Exception {
        curatorFramework.setData().forPath(path,data.getBytes());
    }

    public void delete(String path) throws Exception {
        Stat stat=new Stat();
        String value=new String(curatorFramework.getData().storingStatIn(stat).forPath(path));
        System.out.println(value);
        curatorFramework.delete().withVersion(stat.getVersion()).forPath(path);
    }

}
