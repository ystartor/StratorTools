package com.ystartor.thread.threadcoreknowledge;

/**
 * @desc 展示到了sleep的时候不释放synchronized的monitor，等sleep时间到了以后，正常结束后才释放锁
 */
public class SleepDontReleaseMonitor implements Runnable{

    public static void main(String[] args) {
        SleepDontReleaseMonitor sleepDontReleaseMonitor = new SleepDontReleaseMonitor();
        Thread thread = new Thread(sleepDontReleaseMonitor);
        Thread thread1 = new Thread(sleepDontReleaseMonitor);
        thread.start();
        thread1.start();
    }


    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        System.out.println("thread " + Thread.currentThread().getName() + "获取到了monitor");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread " + Thread.currentThread().getName() + "退出了monitor");
    }
}
