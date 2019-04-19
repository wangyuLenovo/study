package com.wy.shop.waitthennotify;

import com.wy.shop.util.WriterHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    static int count = 1200;

    public static void main(String[] args) throws Exception {


        Thread waitThread = new Thread(new Wait(), "WaitThread");
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        notifyThread.start();
        WriterHelper.writeObjInfo(count);

    }


    //猴子a
    static class Wait implements Runnable {
        public void run() {
            // 加锁，拥有lock的Monitor

            while (true) {
                // 当条件不满足时，继续wait，同时释放了lock的锁

                synchronized (lock) {

                    if (count - 3 >= 0) {
                        count -= 3;

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        WriterHelper.writeInfo(new SimpleDateFormat(" HH:mm:ss").format(new Date()) + "A猴子拿走苹果后还有" + count + "个");
                        lock.notifyAll();


                        try {
                            flag = false;
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        return;
                    }


                }


            }


        }
    }


    static class Notify implements Runnable {
        public void run() {

            while (true) {

                synchronized (lock) {
                    // 当条件不满足时，继续wait，同时释放了lock的锁
                    // 加锁，拥有lock的Monitor

                    if (count - 2 >= 0) {
                        count -= 2;
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        WriterHelper.writeInfo(new SimpleDateFormat(" HH:mm:ss").format(new Date()) + "B猴子拿走苹果后还有" + count + "个");
                        lock.notifyAll();


                        try {
                            flag = true;
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        return;
                    }


                }


            }
        }
    }
}