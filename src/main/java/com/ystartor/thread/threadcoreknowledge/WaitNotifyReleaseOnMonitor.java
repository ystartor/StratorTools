package com.ystartor.thread.threadcoreknowledge;

/**
 * @desc 证明wait只释放当前的那把锁
 */
public class WaitNotifyReleaseOnMonitor {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread().getName() + "got resourceA lock");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread().getName() + "got resourceB lock");
                        try {
                            System.out.println(Thread.currentThread().getName() + "got resourceA release lock");
                            resourceA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread().getName() + "got resourceA lock");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread().getName() + "got resourceB lock");
                    }
                }
            }
        });
        thread.start();
        thread2.start();
    }


}
