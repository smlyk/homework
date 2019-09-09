package com.smlyk.first;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yekai
 */
public class PrintProcessor extends Thread implements RequestProcessor{


    private LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    private final RequestProcessor nextProcessor;

    public PrintProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    //处理请求
    @Override
    public void processProcessor(Request request) {

        requests.add(request);

    }


    @Override
    public void run() {

        while (true){

            try {
                Request request = requests.take();
                System.out.println("print processor 得到请求："+request);

                nextProcessor.processProcessor(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
