package com.ystartor.thread.threadcoreknowledge;

/**
 * @desc 两个线程交替打印0-100的奇偶数，用wait和nitify
 */
public class WaitNotifyPrintOddEveWait {

    private static int count = 0;
    private static final Object lock  = new Object();


    static class TurningRunner implements Runnable{
        @Override
        public void run() {
            while (count <= 100){
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName() + ": " +count++);
                    lock.notify();
                    if (count <= 100){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    //1. 拿到锁就打印
    //2. 打印完就休眠，唤醒其他线程

    public static void main(String[] args) {
        new Thread(new TurningRunner(), "偶数").start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new TurningRunner(),"奇数").start();
    }

}
