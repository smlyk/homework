package com.smlyk.eighth;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yekai
 */
public class Test implements Runnable{


    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程" + Thread.currentThread().getName() + "执行");
    }


    static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {

        for (int i=0; i<100; i++){
            executorService.execute(new Test());
        }

        executorService.shutdown();
    }


}
