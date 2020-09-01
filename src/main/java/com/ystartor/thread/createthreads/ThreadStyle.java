package com.ystartor.thread.createthreads;

/**
 * @desc 用thread方式实现线程
 */
public class ThreadStyle extends Thread{

    public static void main(String[] args) {
        ThreadStyle thread = new ThreadStyle();
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("use thread impl thread");
    }
}
