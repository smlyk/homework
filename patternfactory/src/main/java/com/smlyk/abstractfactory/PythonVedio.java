package com.smlyk.abstractfactory;

/**
 * @author yekai
 */
public class PythonVedio implements IVideo {
    @Override
    public void record() {
        System.out.println("录制Python视频");
    }
}
