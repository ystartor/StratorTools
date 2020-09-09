package com.ystartor.thread.threadcoreknowledge;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @desc 每隔一秒种，被中断，观察
 */
public class SleepInterrupted implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();
        Thread.sleep(6500);
        thread.interrupt();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "get interrupted");
                e.printStackTrace();
            }

        }
    }
}
