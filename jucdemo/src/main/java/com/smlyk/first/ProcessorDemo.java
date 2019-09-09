package com.smlyk.first;

/**
 * @author yekai
 */
public class ProcessorDemo {

    private PrintProcessor printProcessor;

    public ProcessorDemo() {

        SaveProcessor saveProcessor = new SaveProcessor();
        saveProcessor.start();

        printProcessor = new PrintProcessor(saveProcessor);
        printProcessor.start();
    }

    private void doProcessor(Request request){
        printProcessor.processProcessor(request);
    }


    public static void main(String[] args) {

        Request request = new Request();
        request.setName("YK");

        new ProcessorDemo().doProcessor(request);

    }

}
