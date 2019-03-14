package com.smlyk.dbroute;

import com.smlyk.dbroute.proxy.OrderServiceDynamicProxy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yekai
 */
public class DBRouteProxyTest {

    public static void main(String[] args) {

        try {
            Order order = new Order();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date date = sdf.parse("2019/03/14");
            order.setCreateTime(date.getTime());

            //静态代理
//           new OrderServiceStaticProxy(new OrderService()).createOrder(order);

            //动态代理
            IOrderService orderService = (IOrderService) new OrderServiceDynamicProxy().getInstance(new OrderService());
            orderService.createOrder(order);

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
