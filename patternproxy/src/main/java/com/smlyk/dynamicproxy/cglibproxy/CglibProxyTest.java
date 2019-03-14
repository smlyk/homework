package com.smlyk.dynamicproxy.cglibproxy;

import org.springframework.cglib.core.DebuggingClassWriter;

import java.io.File;
import java.io.IOException;

/**
 * @author yekai
 */
public class CglibProxyTest {

    public static void main(String[] args) {

        //JDK是采用读取接口的信息
        //CGLib覆盖父类方法
        //目的：都是生成一个新的类，去实现增强代码逻辑的功能

        //JDK Proxy 对于用户而言，必须要有一个接口实现，目标类相对来说复杂
        //CGLib 可以代理任意一个普通的类，没有任何要求

        //CGLib 生成代理逻辑更复杂,调用效率更高，生成一个包含了所有的逻辑的FastClass，不再需要反射调用
        //JDK Proxy生成代理的逻辑简单，执行效率相对要低，每次都要反射动态调用

        //CGLib 有个坑，CGLib不能代理final的方法

        try {
            // 获取项目路径
            File directory = new File("patternproxy");
            String courseFile = directory.getCanonicalPath();
            System.out.println(courseFile);
            System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,courseFile);

            Customer obj = (Customer) new CglibMeipo().getIntance(Customer.class);
            System.out.println(obj);
            obj.findLover();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
