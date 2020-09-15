package com.ystartor.thread.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * @desc 演示重排序的现象
 *  直到某个条件才停止
 */
public class OutOfOrderExecution {

    private static int a = 0, b = 0;
    private static int x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {

        int i = 0;
        for (;;){
            CountDownLatch latch = new CountDownLatch(2);

            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;


            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("aaaaaaaaa");
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    };
                    System.out.println("aaaaaaaaaaaa++++++++++");
                    b = 1;
                    x = a;
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("bbbbbbbbbbbb");
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("bbbbbbbbbbbbbbb+");
                    a = 1;
                    y = b;
                }
            });
            thread1.start();
            thread2.start();
            latch.countDown();
            Thread.sleep(1000);
            thread1.join();
            thread2.join();
            System.out.println("i:" + i);
            if (x == 1 && y ==1){
                System.out.println("x = " + x + ", y = " + y);
                break;
            }else {
                System.out.println("x = " + x + ", y = " + y);
            }
        }
    }

}
