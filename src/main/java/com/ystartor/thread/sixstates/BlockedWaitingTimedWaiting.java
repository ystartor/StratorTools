package com.ystartor.thread.sixstates;

/**
 * @desc 展示Blocked、Timed、Waiting三种状态
 */
public class BlockedWaitingTimedWaiting implements Runnable{

    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //1:TIMED_WAITING
        //2:BLOCKED
        System.out.println("1:" + thread1.getState());
        System.out.println("2:" + thread2.getState());
        try {
            Thread.sleep(13000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread1.getState());
    }

    @Override
    public void run() {
        sync();
    }

    private synchronized void sync(){
        try {
            Thread.sleep(10000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
