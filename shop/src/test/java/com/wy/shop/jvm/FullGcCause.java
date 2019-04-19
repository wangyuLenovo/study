package com.wy.shop.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * describe:
 *
 * @author wangyu
 * @date 2019/4/2
 * @time 14:04
 */
public class FullGcCause {

    public static void main(String[] args) {
        List<String>  str=new ArrayList<>(10);
//        long totalPage = Long.MAX_VALUE;
        long totalPage = 4296977257l;
        for(int i = 0 ;i<totalPage;i++){
            if(i<0){
                System.out.println(i);
            }
            str.add(i+"");
        }
    }




}
