package com.smlyk.starterdemo.jmxdemo;

/**
 * @author yekai
 * 把需要发布出去的指标信息，通过MB来进行发布
 */
public interface MachineMBean {


    //属性、操作

    int getCpuCore();

    long getFreeMemory();

    void shutdown();

}
