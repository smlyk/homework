package com.smlyk.fourth;

/**
 * @author yekai
 */
public class ReentrantLockDemo {


    public synchronized void demo(){
        System.out.println("begin demo");
        demo2();
    }

    public void demo2(){
        System.out.println("begin demo2");
        synchronized (this){
            System.out.println("demo2 doing...");
        }
    }


    public static void main(String[] args) {

        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();

        //new Thread(() -> reentrantLockDemo.demo()).start();
        new Thread(reentrantLockDemo::demo).start();

    }
}
