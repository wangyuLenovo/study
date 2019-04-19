package com.wy.shop.disruptor;

import com.lmax.disruptor.EventFactory;



public class OrderEventFactory implements EventFactory<Order> {

    @Override
    public Order newInstance() {
        return new Order();
    }


}
