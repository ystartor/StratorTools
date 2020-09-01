package com.ystartor.thread.createthreads;

/**
 * @desc 用runnable方式创建线程
 */
public class RunnableStyle implements Runnable{

    public static void main(String[] args) {
        //
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("use runnable method impl threads");
    }
}
