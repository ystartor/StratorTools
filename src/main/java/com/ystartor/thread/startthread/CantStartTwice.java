package com.ystartor.thread.startthread;

/**
 * @desc 演示不能两次调用star方法，否则会抛出异常 IllegalThreadStateException
 */
public class CantStartTwice {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };

        Thread thread = new Thread(runnable);
        thread.start();
        thread.start();

    }

}
