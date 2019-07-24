package com.smyk;

import java.util.concurrent.locks.Lock;

/**
 * @author yekai
 */
public class ZkDistributeLockTest {


    public static void main(String[] args) {


        for (int i = 0; i < 10; i++){

            new Thread(()->{
                Lock zkLock = new ZkDistributeLock();
                System.out.println(Thread.currentThread().getName() + "-> 尝试获取锁");
                zkLock.lock();

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                zkLock.unlock();
            }).start();

        }

    }

}
