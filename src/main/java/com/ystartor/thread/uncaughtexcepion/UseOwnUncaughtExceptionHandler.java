package com.ystartor.thread.uncaughtexcepion;

public class UseOwnUncaughtExceptionHandler implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));

        new Thread(new UseOwnUncaughtExceptionHandler()).start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler()).start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler()).start();
        Thread.sleep(300);
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }

}
