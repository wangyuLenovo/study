package com.wy.shop.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
@Deprecated
public class CountLatch {


    public static void main(String[] args) {

//        CountDownLatch countDownLatch = new CountDownLatch(4);
//
//
//        countDownLatch.countDown();
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Notify());

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                 @Override
                 public void run() {
                  System.out.println(Thread.currentThread().getName()+ "开始从宿舍出发");
                     try {
                         Thread.sleep(1000);
                         cyclicBarrier.await();
                         System.out.println(Thread.currentThread().getName()+ "开始楼下出发");
                         Thread.sleep(1000);
                         System.out.println(Thread.currentThread().getName()+ "到达操场");
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     } catch (BrokenBarrierException e) {
                         e.printStackTrace();
                     }
                 }
             }).start();
        }
    }


    private static class  Notify implements Runnable{
        @Override
        public void run() {
            System.out.println("宿舍10兄弟一起出发去球场");
        }
    }





}
