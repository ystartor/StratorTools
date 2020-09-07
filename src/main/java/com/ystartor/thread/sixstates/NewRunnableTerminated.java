package com.ystartor.thread.sixstates;

/**
 * @desc 展示线程的NEW RUNNABLE TERMINATED, 即使是正在运行也是Runnable
 */
public class NewRunnableTerminated implements Runnable{

    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        // New
        System.out.println(thread.getState());
        thread.start();
        // Runnable
        System.out.println(thread.getState());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Runnable
        System.out.println(thread.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Terminated
        System.out.println(thread.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
