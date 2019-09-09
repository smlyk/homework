package com.smlyk.first;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yekai
 */
public class SaveProcessor extends Thread implements RequestProcessor{

    private LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<>();


    @Override
    public void processProcessor(Request request) {

        requests.add(request);
    }

    @Override
    public void run() {

        while (true){

            try {
                Request request = requests.take();
                System.out.println("begin save request: "+ request);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
