package com.smlyk.fifth;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yekai
 */
public class ConditionMain {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(new ConditionWaitDemo(lock, condition)).start();

        new Thread(new ConditionSignalDemo(lock, condition)).start();


    }

}
