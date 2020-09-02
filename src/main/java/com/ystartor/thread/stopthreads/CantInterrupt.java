package com.ystartor.thread.stopthreads;

/**
 * @desc 如果while里面放try/catch会导致中断失效
 */
public class CantInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 100000 && !Thread.currentThread().isInterrupted()){
                if (num % 100 == 0){
                    System.out.println(num + "is 100 倍数");
                }
                num++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }



}
