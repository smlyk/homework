package com.smlyk;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yekai
 */
public class YKBlockingQueue {

    final ReentrantLock lock = new ReentrantLock();

    final Condition notFull = lock.newCondition();

    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];

    int putIndex, takeIndex, count;

    public void put(Object o) throws InterruptedException {
        lock.lock();

        try {
            //当数组满了
            while (count == items.length){
                //释放锁，等待
                notFull.await();
            }

            //放入数据
            items[putIndex] = o;
            //如果到最后一个位置了，下标从头开始，防止下标越界
            if (++ putIndex == items.length){
                //从头开始
                putIndex = 0;
            }

            //count加1
            ++count;

            //通知take线程，可以取数据了，不必继续阻塞
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }


    public Object take() throws InterruptedException {
        lock.lock();
        try {
            //如果没有数据，则等待
            while (count == 0){
                notEmpty.await();
            }

            //取数据
            Object o = items[takeIndex];
            //如果到数组尽头了，就从头开始
            if (++takeIndex == items.length){
                //++takeIndex ：先加再比较
                //从头开始
                takeIndex = 0;
            }

            //将数量减1
            --count;

            //通知阻塞的put线程可以填装数据了
            notFull.signal();

            return o;
        } finally {
            lock.unlock();
        }
    }






}
