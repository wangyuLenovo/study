package com.wy.shop.concurrent;

import java.util.concurrent.atomic.LongAdder;

/**
 * describe:
 *
 * @author wangyu
 * @date 2019/3/26
 * @time 10:38
 */
public class LongAdapderDemo {

    public static void main(String[] args) {
        LongAdder num=new LongAdder();
        num.add(1);
    }


}
