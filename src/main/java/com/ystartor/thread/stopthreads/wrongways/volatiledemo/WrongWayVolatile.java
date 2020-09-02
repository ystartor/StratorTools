package com.ystartor.thread.stopthreads.wrongways.volatiledemo;

/**
 * @desc 演示用volatile的局限：part1 看似可行的部分
 */
public class WrongWayVolatile implements  Runnable{

    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num < 1000000 && !canceled){
                if (num % 100 == 0){
                    System.out.println("num:" + num + " is 100 倍数");
                }
                num++;
                Thread.sleep(1);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile wrongWayVolatile = new WrongWayVolatile();
        Thread thread = new Thread(wrongWayVolatile);
        thread.start();
        Thread.sleep(5000);
        wrongWayVolatile.canceled = true;
    }

}
