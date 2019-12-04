package com.smlyk.second;

import java.util.concurrent.TimeUnit;

/**
 * @author yekai
 */
public class InterruptDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()){
                //默认情况下，isInterrupted()返回false,通过thread.interrupt可以变成true
                i++;
            }

            System.out.println("Num: "+ i);

        }, "Thread-interruptDemo");

        thread.start();

        TimeUnit.SECONDS.sleep(1);

        thread.interrupt();

        System.out.println(thread.isInterrupted());

    }

}
