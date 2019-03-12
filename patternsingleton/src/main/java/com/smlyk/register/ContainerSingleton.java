package com.smlyk.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yekai
 * //Spring中的做法，就是用这种注册式单例
 */
public class ContainerSingleton {

    private ContainerSingleton(){}

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    public static Object getInstance(String className){

        if (!ioc.containsKey(className)){
            try {
                Class<?> clazz = Class.forName(className);
                Object obj = clazz.newInstance();
                ioc.put(className, obj);
                return obj;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            return null;

        }else {
            return ioc.get(className);
        }

    }

}
