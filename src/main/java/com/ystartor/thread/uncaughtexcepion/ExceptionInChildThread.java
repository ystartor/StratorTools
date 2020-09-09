package com.ystartor.thread.uncaughtexcepion;

/**
 * @desc catch exception在子线程中
 */
public class ExceptionInChildThread implements Runnable{


    public static void main(String[] args) {
            new Thread(new CantCatchDirectly()).start();
            new Thread(new CantCatchDirectly()).start();
            new Thread(new CantCatchDirectly()).start();
            new Thread(new CantCatchDirectly()).start();
    }

    @Override
    public void run() {
        try {
            throw new RuntimeException();
        }catch (RuntimeException e){
            System.out.println("exception");
        }

    }

}
