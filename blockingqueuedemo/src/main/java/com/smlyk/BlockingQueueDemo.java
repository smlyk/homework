package com.smlyk;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞队列的使用（模拟有1000个文件需要处理，由一个读线程去将文件加载到容量为100的阻塞队列，由四个写线程去进行文件处理）
 * @author yekai
 */
public class BlockingQueueDemo {

    /**
     * 能容纳100个文件
     */
    static final BlockingQueue<String> queue = new LinkedBlockingQueue<>(100);

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

        try {
            for (int i = 0; i<1000; i++){
                queue.put("file" + i);
                int index = readCount.incrementAndGet();
                System.out.println(index);
            }
            //放置结束标志
            queue.put("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(){
        while (true){
            try {
                Thread.sleep(randomTime());
                //返回的是新值（即加1后的值）
                int index = writeCount.incrementAndGet();
                String name = queue.take();
                System.out.println(Thread.currentThread().getName() + "-->'" + name + "'写操作完成："+ index);

                //队列已经无对象
                if ("".equals(name)){
                    //再次添加"结束标志"，以让其他线程正常退出
                    queue.put("");
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
