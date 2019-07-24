package com.smyk;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkDataListener;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * @author yekai
 */
public class ZkDistributeLock extends BaseZkLock {

    //锁节点根路径
    private static final String LOCK_ROOT_PATH = "/zk_locks";

    //当前节点路径
    private String currentPath;

    //前一个节点的路径
    private String beforePath;

    private CountDownLatch countDownLatch;

    public ZkDistributeLock() {
        super();
        synchronized (ZkDistributeLock.class){
            //如果锁节点根路径不存在，则创建持久节点
            if (!zkClient.exists(LOCK_ROOT_PATH)){
                zkClient.createPersistent(LOCK_ROOT_PATH);
            }
        }
    }


    @Override
    public boolean tryLock() {
        //如果currentPath为空则为第一次尝试加锁，第一次加锁赋值currentPath
        if (StringUtils.isEmpty(currentPath)){
            //在path下创建一个临时的顺序节点
            currentPath = zkClient.createEphemeralSequential(LOCK_ROOT_PATH + "/", "lock");
        }

        //获取所有的临时节点，并排序
        List<String> childrens = zkClient.getChildren(LOCK_ROOT_PATH).stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("currentPath = " + currentPath + " childrens = " + JSON.toJSONString(childrens));
        if (currentPath.equals(LOCK_ROOT_PATH + "/" + childrens.get(0))){
            //当前节点是最小节点，则获得锁
            return true;
        }else {
            //如果当前节点不是最小节点，则获取它前面的节点名称，并赋值给beforePath
            //先得到当前节点(比如: /zk_locks/00001)的不带根节点(不包含/zk_locks/)的节点00001
            String curNodePath = currentPath.substring(LOCK_ROOT_PATH.length() + 1);
            //利用二分查找得到该节点在childrens中的位置
            int pos = Collections.binarySearch(childrens, curNodePath);
            //得到beforePath,beforePath位置为pos-1
            beforePath = LOCK_ROOT_PATH + "/" + childrens.get(pos -1);
        }
        return false;
    }

    @Override
    protected void waitLock() {
        IZkDataListener zkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
            }
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                if (null != countDownLatch){
                    //监听到前一个节点删除，计数器减一，继续尝试获取锁
                    countDownLatch.countDown();
                }
            }
        };

        //监听前一个节点的变化
        zkClient.subscribeDataChanges(beforePath, zkDataListener);
        if (zkClient.exists(beforePath)){
            countDownLatch = new CountDownLatch(1);
            try {
                //等待锁
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //运行到这说明已经监听到节点删除，计数器减一了，这时取消订阅监听
        zkClient.unsubscribeDataChanges(beforePath, zkDataListener);
    }

    @Override
    public void unlock() {
        if (null != zkClient){
            zkClient.delete(currentPath);
            zkClient.close();
        }
    }
}
