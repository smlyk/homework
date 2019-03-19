package com.smlyk.template.dodish;

/**
 * 抽象的做菜父类
 * @author yekai
 */
public abstract class DodishTemplate {

    /**
     * 具体的整个过程
     */
    protected void dodish(){

        this.preparation();
        this.doing();
        this.carriedDishes();

    }

    /**
     * 备料
     */
    public abstract void preparation();

    /**
     * 做菜
     */
    public abstract void doing();

    /**
     * 上菜
     */
    public abstract void carriedDishes();


}
