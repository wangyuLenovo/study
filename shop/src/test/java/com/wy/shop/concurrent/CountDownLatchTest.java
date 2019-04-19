package com.wy.shop.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(7);

    public static void main(String[] args) throws InterruptedException {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(1);
//                c.countDown();
//                System.out.println(2);
//                c.countDown();
//            }
//        }).start();
//        c.await();
//        System.out.println("3");



        isWeight();
    }

   public  static boolean isWeight(){
        Double a=4.0;

       Double ration= a/0;
       return false;

   }

}