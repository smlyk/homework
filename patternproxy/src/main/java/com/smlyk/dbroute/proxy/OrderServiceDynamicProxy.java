package com.smlyk.dbroute.proxy;

import com.smlyk.dbroute.Order;
import com.smlyk.dbroute.db.DynamicDataSourceEntity;
import com.smlyk.dynamicproxy.ykproxy.YKClassLoader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yekai
 */
public class OrderServiceDynamicProxy implements InvocationHandler{

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    public Object object;

    public Object getInstance(Object source){
        this.object = source;
        Class<?> clazz = object.getClass();
        return Proxy.newProxyInstance(new YKClassLoader(),clazz.getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before(args);

        Object obj = method.invoke(object, args);

        after();
        return obj;
    }

    private void after() {
        System.out.println("Proxy after method");
        //还原成默认的数据源
        DynamicDataSourceEntity.restore();
    }

    private void before(Object[] args) {

        try {
            //进行数据源的切换
            System.out.println("Proxy before method");

            Order target = (Order) args[0];
            //约定优于配置
            Long time = (Long) target.getClass().getMethod("getCreateTime").invoke(target);
            Integer year = Integer.valueOf(yearFormat.format(new Date(time)));
            System.out.println("动态代理类自动分配到【DB_" + year + "】数据源处理数据");
            DynamicDataSourceEntity.set(year);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
