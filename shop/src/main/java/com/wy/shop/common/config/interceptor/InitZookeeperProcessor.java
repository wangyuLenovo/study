//package com.wy.shop.common.config.interceptor;
//
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.retry.ExponentialBackoffRetry;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Component;
//
///**
// * description:
// *
// * @author wangyu
// * @since 2018/10/25 18:17
// */
//@Component("initZookeeperProcessor")
//public class InitZookeeperProcessor implements InitializingBean {
//
//
//    private ThreadLocal<CuratorFramework> myCurator = new ThreadLocal<>();
//
//    @Override
//    public void afterPropertiesSet() {
//
//        try {
//            // 多个地址逗号隔开
//            CuratorFramework client = CuratorFrameworkFactory.builder().connectString("localhost:2181")
//                    .sessionTimeoutMs(1000)    // 连接超时时间
//                    .connectionTimeoutMs(1000) // 会话超时时间
//                    // 刚开始重试间隔为1秒，之后重试间隔逐渐增加，最多重试不超过三次
//                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
//                    .build();
//            client.start();
//
//
//            myCurator.set(client);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//}
