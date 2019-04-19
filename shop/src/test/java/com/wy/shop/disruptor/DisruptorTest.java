package com.wy.shop.disruptor;

import com.lmax.disruptor.*;


public class DisruptorTest {


    //消费者
    private static class OrderEventHandler implements EventHandler<Order> {
        @Override
        public void onEvent(Order order, long l, boolean b) throws Exception {
            Thread.sleep(1000);
            System.out.println("订单ID" + order.getOrderId());

        }
    }

    //生产者

    public static class OrderProducter {

        private RingBuffer<Order> ringBuffer = null;


        public OrderProducter(RingBuffer ringBuffer) {
            this.ringBuffer = ringBuffer;
        }


        public void publishOrder(Long num) {

            long seq = ringBuffer.next();


            Order order = ringBuffer.get(seq);
            order.setOrderId(num);

            //发布事件同通知消费者
            ringBuffer.publish(seq);


        }


    }


    public static void oneProducertoOneBatchEventProcessor() {
        //1.构造Ring BUffer    只有一个生产者
        RingBuffer<Order> ringBuffer = RingBuffer.createSingleProducer(Order.EVENT_FACTOYR, 1024, new YieldingWaitStrategy());
        //2.构造 消费者 BatchEventProcessor()
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();//计数器
        OrderEventHandler orderEventHandler = new OrderEventHandler();
        BatchEventProcessor<Order> orderBatchEventProcessor = new BatchEventProcessor<>(ringBuffer, sequenceBarrier, orderEventHandler);
        //3.构造方向依赖
        ringBuffer.addGatingSequences(orderBatchEventProcessor.getSequence());
        //4. 构造消费者 生成订单
        OrderProducter orderProducter = new OrderProducter(ringBuffer);
        for (long i = 0; i < 100; i++) {
            orderProducter.publishOrder(i);
        }


        Thread thread = new Thread(orderBatchEventProcessor);

        thread.start();

        //唤醒阻塞的线程
        orderBatchEventProcessor.halt();


    }

    public static void main(String[] args) throws InterruptedException {

        DisrupuorManager.init(new OrderConsumerEventHandler());

        for (long i = 0;i<100 ; i++) {
            Thread.sleep(10);
            DisrupuorManager.putDataToQueue(i);


        }
    }
}
