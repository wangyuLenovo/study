package com.wy.shop.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class SendGoodConsumer2 implements EventHandler<Order>,WorkHandler<Order> {
    @Override
    public void onEvent(Order o) throws Exception {
        Thread.sleep(1000);
        System.out.println("派送货物司机B "+o.getOrderId());
    }

    @Override
    public void onEvent(Order order, long l, boolean b) throws Exception {
        Thread.sleep(1000);
        System.out.println("派送货物司机B "+order.getOrderId());
    }
}
