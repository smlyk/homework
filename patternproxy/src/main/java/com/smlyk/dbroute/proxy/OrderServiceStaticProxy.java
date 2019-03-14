package com.smlyk.dbroute.proxy;

import com.smlyk.dbroute.IOrderService;
import com.smlyk.dbroute.Order;
import com.smlyk.dbroute.db.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yekai
 */
public class OrderServiceStaticProxy implements IOrderService{
    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    private IOrderService orderService;

    public OrderServiceStaticProxy(IOrderService orderService){
        this.orderService = orderService;
    }

    @Override
    public int createOrder(Order order) {

        Long time = order.getCreateTime();
        Integer year = Integer.valueOf(yearFormat.format(new Date(time)));
        System.out.println("静态代理类自动分配到【DB_" +  year + "】数据源处理数据" );
        DynamicDataSourceEntity.set(year);

        this.orderService.createOrder(order);
        DynamicDataSourceEntity.restore();
        return 0;
    }
}
