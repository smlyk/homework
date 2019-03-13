package com.smlyk.simple;

/**
 * @author yekai
 */
public class ProtoTypeTest {

    public static void main(String[] args) {

        // 创建一个具体的需要克隆的对象
        ConcretePrototypeA prototypeA = new ConcretePrototypeA();
        prototypeA.setName("张三");
        prototypeA.setAge(22);
        GirlFriend girlFriend = new GirlFriend("小红",20);
        prototypeA.setGirlFriend(girlFriend);
        System.out.println(prototypeA);

        ConcretePrototypeA clonePrototypeA = (ConcretePrototypeA) prototypeA.clone();
        System.out.println(clonePrototypeA);

        System.out.println("原对象中的引用类型地址值：" + prototypeA.getGirlFriend());
        System.out.println("克隆对象中的引用类型地址值：" + clonePrototypeA.getGirlFriend());

        //不同的原型共用一个女朋友？？？--->浅克隆
        System.out.println("对象地址比较："+(prototypeA.getGirlFriend() == clonePrototypeA.getGirlFriend()));

    }

}
