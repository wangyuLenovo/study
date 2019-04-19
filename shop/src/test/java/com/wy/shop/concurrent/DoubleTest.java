package com.wy.shop.concurrent;

import com.wy.shop.util.WriterHelper;

/**
 * describe:
 *
 * @author wangyu
 * @date 2019/3/29
 * @time 10:19
 */
public class DoubleTest {


    public static void main(String[] args) {
        Double a=0.1;
        Double b=0.8;

        Double c=a/b;
        if(c.doubleValue()>300){
            WriterHelper.writeInfo("重货逻辑");
        }else {
            WriterHelper.writeInfo("轻货逻辑");
        }

    }
}
