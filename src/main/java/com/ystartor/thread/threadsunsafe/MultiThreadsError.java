package com.ystartor.thread.threadsunsafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @desc 运行结果出错，演示技术不准确
 */
public class MultiThreadsError implements Runnable{

    int index = 0;
    final boolean[] marked = new boolean[10000000];
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongCount = new AtomicInteger();
    static volatile CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    public static void main(String[] args) throws InterruptedException {
        MultiThreadsError instance = new MultiThreadsError();
        Thread thread = new Thread(instance);
        Thread thread1 = new Thread(instance);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(instance.index);
        System.out.println(instance.realIndex.get());
        System.out.println(instance.wrongCount.get());
    }

    @Override
    public void run() {
//        while (index < 1000000){
//            index++;
//        }
        for (int i = 0; i < 10000; i++) {
            try {
                cyclicBarrier1.reset();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (MultiThreadsError.class){

                if (marked[index] && marked[index - 1]){
                    System.out.println("index:" + index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;
            }
        }
    }
}
