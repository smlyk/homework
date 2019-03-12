package com.smlyk.lazy;

/**
 * @author yekai
 */
public class LazySimpleSingleton {

    private static LazySimpleSingleton instance = null;

    private LazySimpleSingleton(){}

    public synchronized static LazySimpleSingleton getInstance(){

        if (null == instance){
            instance = new LazySimpleSingleton();
        }

        return instance;
    }

}
