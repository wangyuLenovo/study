package com.wy.shop.disruptor;

import com.lmax.disruptor.EventFactory;

public class Order {

    private Long orderId;
    private double amount;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public static final EventFactory<Order> EVENT_FACTOYR = new EventFactory<Order>() {

        public Order newInstance() {
            return new Order();
        }
    };
}