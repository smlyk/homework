package com.smlyk.fourth;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yekai
 */
public class LockDemo {

    static Map<String, Object> cacheMap = new HashMap<>();

    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    static Lock readLock = reentrantReadWriteLock.readLock();

    static Lock writeLock = reentrantReadWriteLock.writeLock();

    public static final Object get(String key){

        //读锁
        readLock.lock();

        System.out.println("开始读取数据...");
        try {
            Thread.sleep(1000);
            return cacheMap.get(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            readLock.unlock();
        }
    }


    public static final Object put(String key, Object value){

        //写锁
        writeLock.lock();

        System.out.println("开始写数据...");
        try {
            Thread.sleep(1000);
            return cacheMap.put(key, value);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            writeLock.unlock();
        }
    }


    public static void main(String[] args) {

        for (int i = 0; i< 100; i++){

            int finalI = i;
            new Thread(() -> LockDemo.put("a", finalI)).start();
        }

        for (int i = 0; i< 1000; i++){

            new Thread(() -> LockDemo.get("a")).start();
        }



    }
}
