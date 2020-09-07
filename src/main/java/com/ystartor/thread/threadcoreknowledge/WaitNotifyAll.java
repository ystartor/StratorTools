package com.ystartor.thread.threadcoreknowledge;

/**
 * @desc 3个线程，线程1和线程2首先被阻塞，线程3唤醒它们 notify notifyAll
 *          start先执行不代表先启动
 */
public class WaitNotifyAll implements Runnable{

    private static final Object resourceA = new Object();

    @Override
    public void run() {
        synchronized (resourceA){
            System.out.println(Thread.currentThread().getName() + "get resourceA");
            try {
                System.out.println(Thread.currentThread().getName() + "waits to start");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + "waits to end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new WaitNotifyAll();
        Thread threadA = new Thread(runnable);
        Thread threadB = new Thread(runnable);
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    resourceA.notifyAll();
                    System.out.println(Thread.currentThread().getName() + "threadc notified");
                }
            }
        });
        threadA.start();
        threadB.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadC.start();
    }

}
