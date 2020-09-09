package com.ystartor.thread.threadsunsafe;

/**
 * @desc 死锁
 */
public class MultiThreadsError2 implements Runnable{

    int flag = 1;
    static Object obj = new Object();
    static Object obj2 = new Object();

    public static void main(String[] args) {
        MultiThreadsError2 m1 = new MultiThreadsError2();
        MultiThreadsError2 m2 = new MultiThreadsError2();
        m1.flag = 1;
        m2.flag = 0;
        new Thread(m1).start();
        new Thread(m2).start();

    }

    @Override
    public void run() {
        System.out.println("s:"+flag);
        if (flag == 1){
            synchronized (obj){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2){
                    System.out.println("1");
                }
            }
        }
        if (flag == 0){
            synchronized (obj2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj){
                    System.out.println("0");
                }
            }
        }
    }
}
