package com.wy.shop.cyclicdependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * describe:
 *
 * @author wangyu
 * @date 2019/4/12
 * @time 15:43
 */
@Component
public class Student {
    @Autowired
    private  Person  person;


}
