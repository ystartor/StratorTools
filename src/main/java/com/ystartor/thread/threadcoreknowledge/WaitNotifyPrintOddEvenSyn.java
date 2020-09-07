package com.ystartor.thread.threadcoreknowledge;

/**
 * @desc 两个线程交替打印0-100奇偶数，用synchronized
 */
public class WaitNotifyPrintOddEvenSyn {

    private static int count;
    private static final Object lock = new Object();

    //1. 新建两个线程
        //1.1 1个只处理偶数，另一个只处理奇数
        //1.2 用synchronized来通信

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100){
                    while (count < 100){
                        synchronized (lock){
                            if ((count & 1) == 0){
                                System.out.println(Thread.currentThread().getName() + ": " + count++);
                            }
                        }
                    }
                }
            }
        }, "偶数").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100){
                    while (count < 100){
                        synchronized (lock){
                            if ((count & 1) != 0){
                                System.out.println(Thread.currentThread().getName() + ": " + count++);
                            }
                        }
                    }
                }
            }
        }, "奇数").start();
    }

}
