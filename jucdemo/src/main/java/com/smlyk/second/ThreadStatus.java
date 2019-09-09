package com.smlyk.second;

import java.util.concurrent.TimeUnit;

/**
 * @author yekai
 */
public class ThreadStatus {

    public static void main(String[] args) {

        //TIMED_WAITING
       /* new Thread(()->{

            while (true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }, "Time_Waiting_Thread").start();*/


       //WAITING
      /*  new Thread(()->{
            while (true){

                synchronized (ThreadStatus.class){
                    try {
                        ThreadStatus.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, "Wating_Thread").start();*/


        //BlockedDemo_01---TIMED_WAITING
        // BlockedDemo_02---BLOCKED
      new Thread(new BlockedDemo(),"BlockedDemo_01").start();
      new Thread(new BlockedDemo(),"BlockedDemo_02").start();


    }



    static class BlockedDemo extends Thread{

        @Override
        public void run() {
            synchronized (BlockedDemo.class){
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
