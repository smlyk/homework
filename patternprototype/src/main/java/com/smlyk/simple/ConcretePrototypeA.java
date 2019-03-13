package com.smlyk.simple;

/**
 * @author yekai
 */
public class ConcretePrototypeA implements Prototype{

    private String name;
    private int age;

    private GirlFriend girlFriend;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public GirlFriend getGirlFriend() {
        return girlFriend;
    }

    public void setGirlFriend(GirlFriend girlFriend) {
        this.girlFriend = girlFriend;
    }

    @Override
    public Prototype clone() {
        ConcretePrototypeA prototypeA = new ConcretePrototypeA();
        prototypeA.setName(this.name);
        prototypeA.setAge(this.age);
        prototypeA.setGirlFriend(this.girlFriend);
        return prototypeA;
    }
}
