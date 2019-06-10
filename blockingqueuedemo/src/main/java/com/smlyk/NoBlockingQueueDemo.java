package com.smlyk;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用非阻塞队列会出现不同写线程处理同一个文件的情况
 * @author yekai
 */
public class NoBlockingQueueDemo {

    /**
     * 非阻塞队列
     */
    static final LinkedList<String> queue = new LinkedList<>();

    /**
     * 读个数
     */
    static final AtomicInteger readCount = new AtomicInteger();

    /**
     * 写个数
     */
    static final AtomicInteger writeCount = new AtomicInteger();

    static long randomTime(){
        return (long) (Math.random() * 1000);
    }

    private static void scanFile(String name){
        for (int i = 0; i<1000; i++){
            queue.offer("file" + i);
            int index = readCount.incrementAndGet();
            System.out.println(index);
        }
        //放置结束标志
        queue.offer("");
    }

    private static void writeFile(){
        while (true){
            try {
                Thread.sleep(randomTime());
                //返回的是新值（即加1后的值）
                int index = writeCount.incrementAndGet();
                String name = queue.poll();
                System.out.println(Thread.currentThread().getName() + "-->'" + name + "'写操作完成："+ index);

                //队列已经无对象
                if ("".equals(name)){
                    //再次添加"结束标志"，以让其他线程正常退出
                    queue.offer("");
                    break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        //线程池
        final ExecutorService executorService = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());

        //从线程池启一个读线程
        executorService.submit(()->scanFile("aaa"));

        //从线程池启四个写线程
        for (int i = 0; i<4; i++){
            executorService.submit(()->writeFile());
        }

        executorService.shutdown();
    }


}
