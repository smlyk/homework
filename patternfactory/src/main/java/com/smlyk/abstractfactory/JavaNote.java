package com.smlyk.abstractfactory;

/**
 * @author yekai
 */
public class JavaNote implements INote{
    @Override
    public void edit() {
        System.out.println("编写Java笔记");
    }
}
