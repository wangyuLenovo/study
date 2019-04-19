package com.wy.shop.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * 参考文章
 * http://ziyue1987.github.io/pages/2013/09/22/disruptor-use-manual.html
 *  https://blog.csdn.net/vonzhoufz/article/details/82888508
 * 1.定位 : 工具类
 * 2.构造Disruptor  并初始化一些数据
 */
public class DisrupuorManager {


    // 消费者生产者协调器
    private static Disruptor<Order> disruptor;
    //线程池
    private static ExecutorService executorService;
    //
    private static RingBuffer<Order> ringBuffer;
    //   订单id
    private static AtomicLong count = new AtomicLong();


    /**
     * @param eventHandler
     */
    public static void init(EventHandler<Order> eventHandler) {

        executorService = Executors.newFixedThreadPool(5);
        disruptor = new Disruptor<Order>(new OrderEventFactory(), 1024 * 1024 * 8, executorService, ProducerType.MULTI, new BusySpinWaitStrategy());
        ringBuffer = disruptor.getRingBuffer();
        //设置消费者



        //模式一    消费者处于不同的组     多个消费者处理同一个订单号    可以类比kafka的消费组
//        EventHandlerGroup<Order> orderEventHandlerGroup = disruptor.handleEventsWith(eventHandler,new IntegralConsumerEventHandler());
        //orderEventHandlerGroup.then(new AmountConsumerEventHandler());   菱形处理流程


        //模式二    处于同一个消费组     多个消费者处理不同订单号       可以做到负载均衡
        /**
         * 在使用 WorkPool 时，我们提供的事件处理器最终会被封装为 WorkProcessor
         */
//        disruptor.handleEventsWithWorkerPool(new SendGoodConsumer(),new SendGoodConsumer2(),new SendGoodConsumer3());

        //模式 三   串行计算   c1->c2->c3
       /* disruptor.handleEventsWithWorkerPool(new SendGoodConsumer())
                .thenHandleEventsWithWorkerPool(new SendGoodConsumer2())
                .thenHandleEventsWithWorkerPool(new SendGoodConsumer3());*/

//        disruptor.handleEventsWith(new SendGoodConsumer())
//                  .then(new SendGoodConsumer2())
//                  .then(new SendGoodConsumer3());


        //模式 四   菱形
        /**
         * 派送货物司机B 72
          派送货物司机A 72
          派送货物司机B 73
          派送货物司机A 73         c的消费逻辑为啥后来没有了，第一次是有的
         */
       // disruptor.handleEventsWith(new SendGoodConsumer(),new SendGoodConsumer2()).then(new SendGoodConsumer3());
       //5.链式并行计算
        /**
         * 派送货物司机A 0
         派送货物司机B 0
         扣减余额1
         增加积分1
         扣减余额2
         增加积分2
         扣减余额3
         增加积分3
         扣减余额4
         增加积分4
         增加积分5
         扣减余额5
         扣减余额6
         增加积分6   司机a,b的消费逻辑又没了

         */
//        disruptor.handleEventsWith(new IntegralConsumerEventHandler()).then(new SendGoodConsumer());
//        disruptor.handleEventsWith(new AmountConsumerEventHandler()).then(new SendGoodConsumer2());


        //6. 并行 一个消费者多例模式
//        disruptor.handleEventsWithWorkerPool(new SendGoodConsumer(),new SendGoodConsumer());
//        disruptor.handleEventsWithWorkerPool(new SendGoodConsumer2(),new SendGoodConsumer2());


         //7. 串行  一个消费者多例模式
        disruptor.handleEventsWithWorkerPool(new SendGoodConsumer(),new SendGoodConsumer())
                 .then(new SendGoodConsumer2(),new SendGoodConsumer2());

        //消费者线程提交任务
        disruptor.start();
//        disruptor .handleEventsWith(new IntegralConsumerEventHandler());
//        disruptor .handleEventsWith(new AmountConsumerEventHandler());




//        System.out.println("消费者组"+ JSON.toJSONString(orderEventHandlerGroup));


        //  定时任务打印当前的订单号


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (count.get() < 100) {
                    System.out.println("生成的订单编号" + count);
                    System.out.println("剩余空间" + ringBuffer.remainingCapacity());
                }
            }
        }, new Date(), 1000);

    }

    public static void putDataToQueue(long num) {
        if (count.get() == Long.MAX_VALUE) {
            count.set(0l);
        }
        long seq = ringBuffer.next();
        try {
            Order order = ringBuffer.get(seq);
            order.setOrderId(num);
            count.incrementAndGet();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //通知消费者
             ringBuffer.publish(seq);
        }
    }
}
