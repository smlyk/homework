package com.smyk.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yekai
 */
public class ServiceDiscoveryWithZk implements IServiceDiscovery{

    private CuratorFramework curatorFramework;

    /**
     * 服务地址本的地缓存
     */
    List<String> serviceRepos=new ArrayList<>();

    {
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString("47.101.129.30:2181,47.101.129.30:2182,47.101.129.30:2183").sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3))
                .namespace("registry")
                .build();
        curatorFramework.start();
    }

    @Override
    public String discovery(String serviceName) {

        String path = "/" + serviceName;
        if (CollectionUtils.isEmpty(serviceRepos)){
            try {
                serviceRepos = curatorFramework.getChildren().forPath(path);
                registryWatch(path);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        //TODO 负载均衡
        return serviceRepos.get(0);
    }

    /**
     * 监听子节点变化，更新本地缓存地址
     * @param path
     * @throws Exception
     */
    private void registryWatch(String path) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, true);
        PathChildrenCacheListener pathChildrenCacheListener = (curatorFramework1, pathChildrenCacheEvent) -> {

            System.out.println("客户端收到节点变更的事件");
            //更新本地缓存地址
            serviceRepos = curatorFramework1.getChildren().forPath(path);
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start();
    }
}
