package com.smlyk.third;

/**
 * @author yekai
 */
public class JoinDemo {

    static int x;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {

            System.out.println(x);
            x = 100;

        });


        x = 10;

        t1.start();


        t1.join();

        System.out.println(x);

    }

}
