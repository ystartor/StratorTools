package com.ystartor.thread.threadcoreknowledge;

/**
 * @desc join期间被终端
 */
public class JoinInterrupt {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mainThread.interrupt();
                    Thread.sleep(5000);
                    System.out.println("thread1 finish");
                } catch (InterruptedException e) {
                    System.out.println("child thread interrupted");
                    e.printStackTrace();
                }
            }
        }
        );
        thread.start();

        System.out.println("wait child thread finish");
        try {
            thread.join();
            System.out.println(Thread.currentThread().getName() + " interrupted");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName());
            e.printStackTrace();
            thread.interrupt();
        }
        System.out.println("child finish");
    }

}
