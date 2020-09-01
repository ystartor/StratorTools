package com.ystartor.thread.createthreads;

/**
 * @desc 同时使用Runnable和Thread方法实现
 */
public class BothRunnableThreadStyle {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable impl thread");
            }
        }){
            @Override
            public void run() {
                System.out.println("thread impl thread");
            }
        }.start();
    }

}
