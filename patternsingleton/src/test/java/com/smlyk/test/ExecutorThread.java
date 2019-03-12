package com.smlyk.test;

import com.smlyk.lazy.LazySimpleSingleton;

/**
 * @author yekai
 */
public class ExecutorThread implements Runnable{
    @Override
    public void run() {

        LazySimpleSingleton instance = LazySimpleSingleton.getInstance();

        System.out.println(Thread.currentThread().getName() + ":" + instance);
    }
}
