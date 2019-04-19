package com.wy.shop.util;

import com.alibaba.fastjson.JSON;

public class WriterHelper {

    public static void writeInfo(String msg) {
        System.out.println("线程"+Thread.currentThread().getName() +"\t\t" +msg);
    }

    public static void writeObjInfo(Object msg) {
        System.out.println(msg.getClass()+":"+JSON.toJSONString(msg));
    }
}
