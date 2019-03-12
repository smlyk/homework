package com.smlyk.lazy;

/**
 * @author yekai
 */
public class LazyDoubleCheckSingleton {
    private volatile static LazyDoubleCheckSingleton instance = null;

    private LazyDoubleCheckSingleton(){}

    public static LazyDoubleCheckSingleton getInstance(){

        if (null == instance){

            synchronized (LazyDoubleCheckSingleton.class){

                if (null == instance){
                    instance = new LazyDoubleCheckSingleton();
                }
            }

        }

        return instance;
    }

}
