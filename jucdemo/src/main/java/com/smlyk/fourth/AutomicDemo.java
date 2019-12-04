package com.smlyk.fourth;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yekai
 */
public class AutomicDemo {

    private static int count;

    static Lock lock = new ReentrantLock();

    public static void incr(){

        lock.lock();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ++count;

        lock.unlock();
    }


    public static void main(String[] args) {

        for (int i=0; i<1000; i++){
            new Thread(AutomicDemo::incr).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count);
    }

}
