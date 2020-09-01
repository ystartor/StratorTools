package com.ystartor.thread.createthreads.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @desc 定时器创建线程
 */
public class DemoTimerTask {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }

}
