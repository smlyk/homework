package com.smlyk.dynamicproxy.jdkproxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yekai
 */
public class JDKProxyTest {

    public static void main(String[] args) {


        Object obj = new JDKMeipo().getInstance(new Girl());

        try {
            Method m = obj.getClass().getMethod("findLover", null);
            m.invoke(obj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //或
//        Person obj = (Person) new JDKMeipo().getInstance(new Girl());
//        obj.findLover();

        //$Proxy0
//        try {
//            byte [] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
//            FileOutputStream os = new FileOutputStream("patternproxy/$Proxy0.class");
//            os.write(bytes);
//            os.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}
