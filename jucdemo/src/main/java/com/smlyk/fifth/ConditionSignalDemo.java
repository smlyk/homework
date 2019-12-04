package com.smlyk.fifth;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author yekai
 */
public class ConditionSignalDemo implements Runnable{

    private Lock lock;

    private Condition condition;

    public ConditionSignalDemo(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {

        System.out.println("begin ConditionSignalDemo");

        try {
            lock.lock();
            condition.signal();

            System.out.println("end ConditionSignalDemo");
        } finally {
            lock.unlock();
        }


    }
}
