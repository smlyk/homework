package com.smlyk.test;

import com.smlyk.register.EnumSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author yekai
 */
public class EnumSingletonTest {

//    public static void main(String[] args) {
//
//        try {
//            EnumSingleton e1 = null;
//
//            EnumSingleton e2 = EnumSingleton.getInstance();
//            e2.setData(new Object());
//
//            FileOutputStream fos = new FileOutputStream("EnumSingleton.obj");
//            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//                oos.writeObject(e2);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            FileInputStream fis = new FileInputStream("EnumSingleton.obj");
//            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
//                e1 = (EnumSingleton) ois.readObject();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println(e1.getData());
//            System.out.println(e2.getData());
//
//            System.out.println(e1.getData() == e2.getData());
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//
//    }


    //尝试反射得到枚举实例会报错Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum object
    public static void main(String[] args) {

        try {
            Class<?> clazz = EnumSingleton.class;
            Constructor<?> c = clazz.getDeclaredConstructor(String.class,int.class);
            c.setAccessible(true);

            EnumSingleton e = (EnumSingleton) c.newInstance("smlyk", 666);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

}
