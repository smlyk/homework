package com.smlyk;

import com.smlyk.autoconfiguration.CuratorFrameworkConfigProperties;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.StringUtils;

import static java.util.Objects.isNull;

/**
 * @author yekai
 */
public class CuratorZkTemplate {

    public CuratorFramework curatorFramework;

    public CuratorFrameworkConfigProperties properties;

    public CuratorZkTemplate(CuratorFrameworkConfigProperties properties) throws Exception {
        this.properties = properties;
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(properties.getConnectionstr()).sessionTimeoutMs(properties.getTimeout()).
                retryPolicy(new ExponentialBackoffRetry(1000,properties.getMaxretries()))
                .namespace(properties.getNamespace())
                .build();
        curatorFramework.start();

        if (properties.isNeedwatch()){
            addListenerWithNode(properties);
            addListenerWithChild(properties);
        }
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

    public void update(String path, String data) throws Exception {
        curatorFramework.setData().forPath(path,data.getBytes());
    }

    public void delete(String path) throws Exception {
        Stat stat=new Stat();
        String value=new String(curatorFramework.getData().storingStatIn(stat).forPath(path));
        System.out.println(value);
        curatorFramework.delete().withVersion(stat.getVersion()).forPath(path);
    }

    public boolean checkExsits(String path) throws Exception {
        Stat stat = curatorFramework.checkExists().forPath(path);
        if (isNull(stat)){
            return false;
        }
        return true;
    }

    //配置中心
    //创建、修改、删除节点
    private void addListenerWithNode(CuratorFrameworkConfigProperties properties) throws Exception {

        NodeCache nodeCache = new NodeCache(curatorFramework, properties.getWatchpath(), false);
        NodeCacheListener nodeCacheListener = () -> {
            System.out.println("receive Node Changed");
            System.out.println(nodeCache.getCurrentData().getPath()+"---"+new String(nodeCache.getCurrentData().getData()));
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }

    ////实现服务注册中心的时候，可以针对服务做动态感知
    private void addListenerWithChild(CuratorFrameworkConfigProperties properties) throws Exception {

        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, properties.getWatchpath(), true);
        PathChildrenCacheListener nodeCacheListener= (curatorFramework1, pathChildrenCacheEvent) -> {
            System.out.println(pathChildrenCacheEvent.getType()+"->"+new String(pathChildrenCacheEvent.getData().getData()));
        };
        pathChildrenCache.getListenable().addListener(nodeCacheListener);
        pathChildrenCache.start(PathChildrenCache.StartMode.NORMAL);
    }


}
