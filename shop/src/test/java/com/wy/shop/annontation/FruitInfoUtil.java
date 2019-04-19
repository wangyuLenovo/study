package com.wy.shop.annontation;

import com.wy.shop.util.WriterHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 注解处理器
 */
public class FruitInfoUtil {


    public static void getFruitInfo(Class<?> clazz) {


        Field[] fields = clazz.getDeclaredFields();




        for (Field field : fields) {

            if (field.isAnnotationPresent(FruitName.class)) {
                FruitName fruitName = field.getAnnotation(FruitName.class);
                String fruName = fruitName.value();
                WriterHelper.writeInfo(fruName);

            } else if (field.isAnnotationPresent(FruitColor.class)) {
            } else if (field.isAnnotationPresent(FruitProvider.class)) {

            }


        }


    }

}
