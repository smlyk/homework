package com.smyk;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author yekai
 */
public abstract class BaseZkLock implements Lock{

    //zk连接地址
    public static final String ZK_CONN_ADDR = "47.101.129.30:2181,47.101.129.30:2182,47.101.129.30:2183";

    //zk超时时间
    public static final int SESSION_TIMEOUT = 5000;

    //zkclient
    protected ZkClient zkClient;

    public BaseZkLock() {
        zkClient = new ZkClient(ZK_CONN_ADDR, SESSION_TIMEOUT);
    }

    @Override
    public void lock() {
        String threadName = Thread.currentThread().getName();
        if (tryLock()){
            System.out.println(threadName+"-获取锁成功");
        }else {
            System.out.println(threadName+"-获取锁失败，进行等待...");
            waitLock();

            //递归重新获取锁
            lock();
        }
    }

    //等待获取锁
    protected abstract void waitLock();

    //尝试获取锁
    @Override
    public abstract boolean tryLock();

    //释放锁
    @Override
    public abstract void unlock();



    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
