package com.smlyk.third;

/**
 * @author yekai
 */
public class VolatileDemo {

    public volatile static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {

            int i = 0;
            while (!stop){
                i++;
            }
            System.out.println(i);
        },"thread-volatileDemo");

        thread.start();
        System.out.println("begin start thred");
        Thread.sleep(1000);
        stop = true;

    }

}
