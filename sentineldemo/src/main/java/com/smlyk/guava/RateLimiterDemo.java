package com.smlyk.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author yekai
 */
public class RateLimiterDemo {

    /**
     * 创建一个限流器，参数代表每秒生成的令牌数
     */
    private RateLimiter rateLimiter = RateLimiter.create(10);

    /**
     * 获取令牌桶
     */
    public void get(){
        if (rateLimiter.tryAcquire()){
            System.out.println("允许访问");
        }else {
            System.out.println("被限流了");
        }
    }

    public static void main(String[] args) throws IOException {

        //模拟并发请求
        RateLimiterDemo rateLimiterDemo = new RateLimiterDemo();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Random random = new Random();

        for (int i = 0; i<20; i++){

            new Thread(()->{
                try {
                    countDownLatch.await();
                    Thread.sleep(random.nextInt(1000));
                    rateLimiterDemo.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        countDownLatch.countDown();

        System.in.read();
    }


}
