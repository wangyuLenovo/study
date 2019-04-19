package com.wy.shop;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * description:
 *
 * @author wangyu
 * @since 2018/10/25 18:29
 */
public class Zookeeper {

    private static CuratorFramework myClient;

        public static void main(String[] args) {

            initClient();

            try {
                Stat stat=new Stat();
                myClient.getData().storingStatIn(stat).forPath("/mic/node1");

                myClient.setData().
                        withVersion(stat.getVersion()).forPath("/mic/node1","xx".getBytes());

                myClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    private static void initClient() {
        try {
            // 多个地址逗号隔开
            CuratorFramework client = CuratorFrameworkFactory.builder().connectString("140.143.230.153:2181")
                    .sessionTimeoutMs(1000)    // 连接超时时间
                    .connectionTimeoutMs(1000) // 会话超时时间
                    // 刚开始重试间隔为1秒，之后重试间隔逐渐增加，最多重试不超过三次
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .build();
            //开启会话
            client.start();

            myClient=client;







        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
