package com.ystartor.thread.createthreads.wrongways;

/**
 *  @desc lambda实现创建线程
 */
public class LambdaStyle {

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }).start();
    }

}
