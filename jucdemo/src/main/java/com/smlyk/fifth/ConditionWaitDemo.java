package com.smlyk.fifth;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author yekai
 */
public class ConditionWaitDemo implements Runnable{

    private Lock lock;

    private Condition condition;

    public ConditionWaitDemo(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {

        System.out.println("begin ConditionWaitDemo");

        try {
            lock.lock();
            condition.await();
            System.out.println("end ConditionWaitDemo");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
