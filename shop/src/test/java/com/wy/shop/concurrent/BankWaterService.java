package com.wy.shop.concurrent;

import java.util.concurrent.*;

/**
 * CyclicBarrier
 * 通知线程
 */
public class BankWaterService implements Runnable {

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4, this);

    //线程池
    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    //线程池
    private ExecutorService semaphorefixedThreadPool = Executors.newFixedThreadPool(30);

    //信号量控制并发  允许得到10个许可证
    private Semaphore s = new Semaphore(10);

    //保存每个线程的计算结果
    private ConcurrentHashMap<String, Integer> sheetHolder = new ConcurrentHashMap<>();


    private void count() {

        while (true){
            break;
        }

        String a="";






        for (int i = 0; i < 4; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    sheetHolder.put(Thread.currentThread().getName(), 1);
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }


    private void saveData() {

        for (int i = 0; i < 30; i++) {
            semaphorefixedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        //只允许有十个线程并发
                        s.acquire();
                        System.out.println(Thread.currentThread().getName()+"save  data");
//                        s.release();
                    } catch (InterruptedException e) {
                        System.err.println("保存失败");
                    }
                }
            });

        }

    }


    @Override
    public void run() {

        int sum = 0;

        //汇总其他四个线程 work 的工作
        for (Integer integer : sheetHolder.values()) {
            sum += integer;
        }
        sheetHolder.put("sum", sum);
        System.out.println("最终计算结果" + sum);
    }


    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.saveData();





    }


}
