package com.smlyk.threadlocal;

/**
 * @author yekai
 * ThreadLocal 不能保证其
创建的对象是全局唯一，但是能保证在单个线程中是唯一的，天生的线程安全。
 */
public class ThreadLocalSingleton {

    public static final ThreadLocal<ThreadLocalSingleton> threaLocalInstance = new ThreadLocal<ThreadLocalSingleton>(){
        @Override
        protected ThreadLocalSingleton initialValue() {
            return new ThreadLocalSingleton();
        }
    };

    private ThreadLocalSingleton(){}

    public static ThreadLocalSingleton getInstance(){
        return threaLocalInstance.get();
    }
}
