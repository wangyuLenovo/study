package com.wy.shop.lambda;

import com.google.common.collect.Lists;
import com.wy.shop.util.WriterHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * describe:
 *
 * @author wangyu
 * @date 2018/12/28
 * @time 9:07
 */
public class Demo {

    private static class City {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public City(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        List<City> strings = Lists.newArrayList();
        for (int i = 0; i < 100000000; i++) {
            City city = new City("BJ" + i);
            strings.add(city);
        }


        long start = System.currentTimeMillis();
        strings.parallelStream().forEach(s ->check(s));
        long end = System.currentTimeMillis();
        WriterHelper.writeInfo((end - start) + "");


        long start2 = System.currentTimeMillis();
        for (City string : strings) {
           check(string);
        }
        long end2 = System.currentTimeMillis();
        WriterHelper.writeInfo((end2 - start2) + "");
    }

    private static void check(City c) {
        if (c.getName().equals("wangyu")) {
            WriterHelper.writeInfo("找到了");
        }

    }


}
