package com.smlyk.abstractfactory;

/**
 * @author yekai
 */
public class PythonNote implements INote{
    @Override
    public void edit() {
        System.out.println("编写Python笔记");
    }
}
