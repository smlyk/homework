package com.smlyk.simple;

/**
 * @author yekai
 */
public class EmployeeA implements IEmployee{
    @Override
    public void doing(String command) {
        System.out.println("我是员工A,我现在开始做" + command + "工作");
    }
}
