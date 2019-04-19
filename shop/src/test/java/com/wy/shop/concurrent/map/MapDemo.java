package com.wy.shop.concurrent.map;

import com.wy.shop.util.WriterHelper;

import java.util.concurrent.ConcurrentHashMap;

/**
 * describe:
 *
 * @author wangyu
 * @date 2019/4/19
 * @time 16:56
 */
public class MapDemo {


    public static void main(String[] args) {


        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            objectObjectConcurrentHashMap.put("WY"+i,"哈哈哈"+i);
        }
        WriterHelper.writeObjInfo(objectObjectConcurrentHashMap);
        WriterHelper.writeObjInfo("kkkkk");
    }





}