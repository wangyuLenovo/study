package com.wy.shop.common.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * description:
 *
 * @author wangyu
 * @since 2018/10/26 14:18
 */
@Component("myListener")
public abstract class MyListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        handle();
        super.contextInitialized(event);
    }

    abstract String handle();
}
