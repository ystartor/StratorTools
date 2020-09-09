package com.ystartor.thread.uncaughtexcepion;

/**
 * @desc 直接使用try catch停止线程
 */
public class CantCatchDirectly implements Runnable{

    public static void main(String[] args) {
        try {
            new Thread(new CantCatchDirectly()).start();
            new Thread(new CantCatchDirectly()).start();
            new Thread(new CantCatchDirectly()).start();
            new Thread(new CantCatchDirectly()).start();
        }catch (RuntimeException e){
            System.out.println("exception");
        }

    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
