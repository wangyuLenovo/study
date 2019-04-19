package com.wy.shop.concurrent;

import com.wy.shop.disruptor.Order;
import com.wy.shop.util.WriterHelper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Order> orderFutureTask = new FutureTask<>(new Task());


        Thread thread = new Thread(orderFutureTask);
        thread.start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (!orderFutureTask.isDone()) {
            WriterHelper.writeInfo("task is  not  done");
        }


        Order order = orderFutureTask.get();
        WriterHelper.writeObjInfo(order);


    }


    private static class Task implements Callable<Order> {
        @Override
        public Order call() throws Exception {
            WriterHelper.writeInfo("开始执行任务");
            Order order = new Order();
            Thread.sleep(5000);
            order.setOrderId(1l);
            order.setAmount(8.0);

            return order;
        }
    }


}
