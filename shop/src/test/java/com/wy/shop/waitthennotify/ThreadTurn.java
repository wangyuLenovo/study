package com.wy.shop.waitthennotify;

public class ThreadTurn {
    Object o = new Object();
    Boolean f = true; // True 时线程1执行

    class Thread1 extends Thread {

        public void run() {

            while (true) {
                synchronized (o) {
                    System.out.println("A线程--->");
                    o.notify();
                    if (f) {
                        f = false;
                        try {
                            o.wait();
                        } catch (Exception e) {
                            System.out.print(e);
                        }
                    }
                }
            }
        }


    }

    class Thread2 extends Thread {


        public void run() {

            while (true) {

                synchronized (o) {
                    System.out.println(" B线程--->");
                    o.notify();
                    if (!f) {
                        f = true;
                        try {
                            o.wait();
                        } catch (Exception e) {
                            System.out.print(e);
                        }
                    }
                }
            }

        }
    }

    public void start() {
        new Thread1().start();
        new Thread2().start();
    }

    public static void main(String args[]) {
        ThreadTurn t = new ThreadTurn();
        t.start();
    }

}
