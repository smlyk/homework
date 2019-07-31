package com.smlyk;

import com.alibaba.fastjson.JSON;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yekai
 */
public class CalTestApp {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext classPathXmlApplicationContext= new ClassPathXmlApplicationContext(new String[]{"application.xml"});

        ISaleOrderService saleOrderService =
                (ISaleOrderService) classPathXmlApplicationContext.getBean("saleOrderService");
        System.out.println(JSON.toJSONString(saleOrderService.getSaleOrderList()));
    }

}
