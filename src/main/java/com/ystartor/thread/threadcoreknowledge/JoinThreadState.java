package com.ystartor.thread.threadcoreknowledge;

/**
 * @desc 先join再mainThread.getState  通过debug查看线程的状态
 */
public class JoinThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread0_finish = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println(mainThread.getState());
                    System.out.println("thread0 finish");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread0_finish.start();
        System.out.println("wait child finsih");
        thread0_finish.join();
        System.out.println("wait finish");
    }

}
