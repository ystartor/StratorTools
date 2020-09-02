package com.ystartor.thread.stopthreads;

/**
 * @desc  run方法内有sleep或wait方法时，停止线程
 */
public class RightWayStopThreadSleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 300 && !Thread.currentThread().isInterrupted()){
                    if (num % 100 == 0) {
                        System.out.println(num + "is 100 倍数");
                    }
                    num++;
                }
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("run end");
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.sleep(500);
        thread.interrupt();
    }


}
