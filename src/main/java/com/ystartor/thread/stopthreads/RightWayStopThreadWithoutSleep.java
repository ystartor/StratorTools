package com.ystartor.thread.stopthreads;

/**
 * @desc  run方法内没有sleep或wait方法时，停止线程
 */
public class RightWayStopThreadWithoutSleep implements Runnable{

    @Override
    public void run() {
        int num  = 0;
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0){
                System.out.println(num + "is 10000 倍数");
            }
            num++;
        }
        System.out.println("run end");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        thread.sleep(1000);
        thread.interrupt();
    }

}
