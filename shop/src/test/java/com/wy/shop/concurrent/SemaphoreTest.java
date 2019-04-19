package com.wy.shop.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore s = new Semaphore(10);
    private static int count;
    public static void main(String[] args) {


        for (int i = 0; i < THREAD_COUNT; i++) {

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        count++;
                        System.out.println("save data");
//                        s.release();
                    } catch (InterruptedException e) {
                    }
                }
            });
        }
        System.out.println("count="+count);
        threadPool.shutdown();
    }
}