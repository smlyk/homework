package com.smlyk;

/**
 * @author yekai
 */
public class Demo {

    public static void main(String[] args) {

        YKBlockingQueue queue = new YKBlockingQueue();

      new Thread(() ->{
          for (int i = 0; i<1000; i++){
              try {
                  queue.put(i);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }).start();

      new Thread(() ->{
          for (int i = 0; i<1000; i++){
              try {
                  Object o = queue.take();
                  System.out.println(o);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }).start();

    }

}
