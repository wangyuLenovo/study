package com.wy.shop.id;

import com.wy.shop.util.DateUtil;
import com.wy.shop.util.WriterHelper;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.*;

/**
 * describe:
 *
 * @author wangyu
 * @date 2019/4/11
 * @time 12:01
 */
public class UUIDDemo {




    public static void main(String[] args) {
        IdGenUtils idGenUtils=IdGenUtils.get();
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        CyclicBarrier barrier = new CyclicBarrier(200);
        List<String> ids = Collections.synchronizedList(new ArrayList<String>(200));
        for (int i = 0; i < 200; i++) {

            executorService.submit(new Runnable() {
                @Override
                public void run() {

                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                    String date = DateUtil.date2Str(DateUtil.DATE_FORMAT, new Date());
                    String id = idGenUtils.getStrCodingByPrefix(date);
                    if(ids.contains(id)){
                        Assert.isTrue(false,"id重复");
                    }

                    ids.add(id);

                }
            });

        }

        WriterHelper.writeObjInfo(ids);
      executorService.shutdown();
    }
}
