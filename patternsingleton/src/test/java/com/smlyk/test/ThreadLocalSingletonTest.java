package com.smlyk.test;

import com.smlyk.threadlocal.ThreadLocalSingleton;

/**
 * @author yekai
 */
public class ThreadLocalSingletonTest {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());

        Thread t1 = new Thread(new ExecutorThread2());
        Thread t2 = new Thread(new ExecutorThread2());
        t1.start();
        t2.start();

        System.out.println("end----");

    }

}
