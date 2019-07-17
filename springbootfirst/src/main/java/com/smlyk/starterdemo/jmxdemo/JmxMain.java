package com.smlyk.starterdemo.jmxdemo;

import javax.management.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * @author yekai
 */
public class JmxMain {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, IOException {

        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("com.smlyk.starterdemo.jmxdemo.Machine:type=machine");
        MachineMBean machineMBean =new Machine();
        beanServer.registerMBean(machineMBean, objectName);

        System.in.read();
    }

}
