package com.smlyk.registry;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.StringUtils;

import static java.util.Objects.isNull;

/**
 * @author yekai
 */
public class RegistryCenterWithZk implements IRegistryCenter{

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString("47.101.129.30:2181,47.101.129.30:2182,47.101.129.30:2183").sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3))
                .namespace("registry")
                .build();
        curatorFramework.start();
    }


    @Override
    public void registry(String serviceName, String serviceAddress) {

        String servicePath = "/" + serviceName;
        try {
            if (!checkExsits(servicePath)){
                create(servicePath, null);
            }
            //serviceAddress: ip:port
            String addressPath = servicePath + "/" + serviceAddress;
            create(addressPath, null, CreateMode.EPHEMERAL);
            System.out.println("服务注册成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean checkExsits(String path) throws Exception {
        Stat stat = curatorFramework.checkExists().forPath(path);
        if (isNull(stat)){
            return false;
        }
        return true;
    }

    public void create(String path, String data) throws Exception {
        create(path, data, CreateMode.PERSISTENT);
    }

    public void create(String path, String data, CreateMode mode) throws Exception {
        if (StringUtils.isEmpty(data)){
            curatorFramework.create()
                    .creatingParentsIfNeeded().withMode(mode).
                    forPath(path);
            return;
        }
        curatorFramework.create()
                .creatingParentsIfNeeded().withMode(mode).
                forPath(path,data.getBytes());
    }
}
