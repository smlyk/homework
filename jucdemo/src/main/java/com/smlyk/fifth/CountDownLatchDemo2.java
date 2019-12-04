package com.smlyk.fifth;

import java.util.concurrent.CountDownLatch;

/**
 * @author yekai
 */
public class CountDownLatchDemo2 extends Thread{

    //模拟高并发

    static CountDownLatch countDownLatch = new CountDownLatch(1);


    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ThreadName: "+ Thread.currentThread().getName());
    }

    public static void main(String[] args) {

        for (int i = 0; i<1000; i++){
            new CountDownLatchDemo2().start();
        }

        countDownLatch.countDown();
    }
}
