package com.wy.shop.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * describe:
 *
 * @author wangyu
 * @date 2019/3/31
 * @time 21:21
 */
public class Test {

    static   AtomicInteger  num=new AtomicInteger(9);
    static CyclicBarrier cb = new CyclicBarrier(2);
    public static void main(String[] args) {



        while (true){
            Thread   m1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cb.await();
                    } catch (InterruptedException e) {
                        throw  new RuntimeException("系统异常"+num.intValue());
                    } catch (BrokenBarrierException e) {
                        throw  new RuntimeException("系统异常"+num.intValue());
                    }
                    int i = num.get();
                    if(num.compareAndSet((i-3),num.intValue())){
                        throw  new RuntimeException("苹果不够了"+num.intValue());
                    }
                }
            });

            m1.start();


            Thread   m2 = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        cb.await();
                    } catch (InterruptedException e) {
                        throw  new RuntimeException("系统异常"+num.intValue());
                    } catch (BrokenBarrierException e) {
                        throw  new RuntimeException("系统异常"+num.intValue());
                    }
                    int i = num.get();
                    if(num.compareAndSet((i-2),num.intValue())){
                        throw  new RuntimeException("苹果不够了"+num.intValue());
                    }
                }
            });

            m2.start();
        }








    }


}
