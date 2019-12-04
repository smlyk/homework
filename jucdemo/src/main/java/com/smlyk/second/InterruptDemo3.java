package com.smlyk.second;

import java.util.concurrent.TimeUnit;

/**
 * @author yekai
 */
public class InterruptDemo3 {

    private static int i;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()){

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }

            System.out.println("Num: " + i);

        },"thread-interruptDemo3");


        thread.start();

        TimeUnit.SECONDS.sleep(1);

        thread.interrupt();

        System.out.println(thread.isInterrupted());

    }

}
