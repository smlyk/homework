package com.smlyk.first;

import java.util.concurrent.*;

/**
 * @author yekai
 */
public class CallableDemo implements Callable<String>{


    @Override
    public String call() throws Exception {

        System.out.println("开始执行...");
        Thread.sleep(30000);

        return "执行结果： xxx";
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        CallableDemo callableDemo =new CallableDemo();
        Future<String> future = executorService.submit(callableDemo);

        System.out.println(future.get());
        System.out.println("调用成功...");

        executorService.shutdown();
    }

}
