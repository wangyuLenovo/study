//package com.wy.shop.common.config.job;
//
//import com.alibaba.fastjson.JSON;
//import com.dangdang.ddframe.job.api.ShardingContext;
//import com.dangdang.ddframe.job.api.simple.SimpleJob;
//
///**
// * description:  任务机
// *
// * @author wangyu
// * @since 2018/10/25 17:03
// */
//public class PayJob implements SimpleJob {
//    @Override
//    public void execute(ShardingContext shardingContext) {
//        System.out.println("shardingContext:" + JSON.toJSONString(shardingContext));
//
//        /*比如你的定时器是批量更新3万条数据
//        那就用limit 控制咯
//        像分页查询一样
//         一台服务器只更新一万条数据*/
//
//        //多台服务器并发执行
//        switch (shardingContext.getShardingItem()) {
//            case 0:
//                 //处理前1-10000 条数据
//                //limit分页查询数据
//                // 业务处理
//                break;
//            case 1:
//                //10000-20000
//                break;
//            case 2:
//                // 20000-30000
//                break;
//            // case n: ...
//        }
//    }
//}
