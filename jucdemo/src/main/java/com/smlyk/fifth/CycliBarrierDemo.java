package com.smlyk.fifth;

import java.util.concurrent.CyclicBarrier;

/**
 * @author yekai
 */
public class CycliBarrierDemo extends Thread{


    @Override
    public void run() {
        System.out.println("开始进行数据分析");
    }


    public static void main(String[] args) {

        CyclicBarrier cycliBarrier = new CyclicBarrier(3, new CycliBarrierDemo());


        new Thread(new DataImportThread(cycliBarrier, "file_1")).start();

        new Thread(new DataImportThread(cycliBarrier, "file_2")).start();

        new Thread(new DataImportThread(cycliBarrier, "file_3")).start();

    }
}
