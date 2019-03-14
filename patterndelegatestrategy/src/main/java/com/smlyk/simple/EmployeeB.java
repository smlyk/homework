package com.smlyk.simple;

/**
 * @author yekai
 */
public class EmployeeB implements IEmployee{
    @Override
    public void doing(String command) {
        System.out.println("我是员工B,我现在做" + command + "工作");
    }
}
