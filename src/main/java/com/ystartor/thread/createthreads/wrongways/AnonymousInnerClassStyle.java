package com.ystartor.thread.createthreads.wrongways;

/**
 * @desc 匿名内部类创建线程
 */
public class AnonymousInnerClassStyle {

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }

}
