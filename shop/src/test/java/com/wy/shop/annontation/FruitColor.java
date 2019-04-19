package com.wy.shop.annontation;


import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {

    /**
     * 颜色   枚举
     */
    public enum Color {
        RED, GREEN, YELLO
    }


    Color fruitColor() default Color.RED;
}
