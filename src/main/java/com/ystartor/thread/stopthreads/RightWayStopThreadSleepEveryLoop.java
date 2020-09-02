package com.ystartor.thread.stopthreads;

/**
 * @desc 如果线程在每次迭代后都阻塞
 */
public class RightWayStopThreadSleepEveryLoop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 10000){
                    if (num % 100 == 0){
                        System.out.println(num + " is 100 倍数");
                    }
                    num++;
                    Thread.sleep(10);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }

}
