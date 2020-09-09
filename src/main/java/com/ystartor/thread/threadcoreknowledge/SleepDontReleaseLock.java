package com.ystartor.thread.threadcoreknowledge;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @desc 展示sleep不释放lock
 */
public class SleepDontReleaseLock implements Runnable{

    private static final Lock LOCK = new ReentrantLock();

    @Override
    public void run() {
        LOCK.lock();
        System.out.println(Thread.currentThread().getName() + "get lock");
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " suxing ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            LOCK.unlock();
        }
        System.out.println(Thread.currentThread().getName() + "release lock");
    }

    public static void main(String[] args) {
        SleepDontReleaseMonitor sleepDontReleaseMonitor = new SleepDontReleaseMonitor();
        new Thread(sleepDontReleaseMonitor).start();
        new Thread(sleepDontReleaseMonitor).start();
    }

}
