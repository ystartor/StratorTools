package com.ystartor.thread.stopthreads.wrongways;

/**
 * @desc interrupted() 当前线程
 */
public class RigthWayInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){

                }
            }
        });
        threadOne.start();
        threadOne.interrupt();
        System.out.println("isInterrupted:" + threadOne.isInterrupted());
        // chongzhi
        System.out.println("isInterrupted:" + threadOne.interrupted());
        //chongzhi
        System.out.println("isInterrupted:" + Thread.interrupted());

        System.out.println("isInterrupted:" + threadOne.isInterrupted());
        threadOne.join();
        System.out.println("Main is over");
    }

}
