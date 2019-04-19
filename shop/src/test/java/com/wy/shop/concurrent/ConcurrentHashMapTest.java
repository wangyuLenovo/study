package com.wy.shop.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMapTest {
    static Map<String, Integer> counter = new ConcurrentHashMap();
//    static AtomicInteger   num=new AtomicInteger(0);


    public static void main(String[] args) throws InterruptedException {
        counter.put("stock1", 0);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Integer  stock1=0;
                    synchronized (this){
                          stock1 = counter.get("stock1");
                          stock1++;
                    }
                    counter.put("stock1", stock1);
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("result is " + counter.get("stock1"));
    }
}