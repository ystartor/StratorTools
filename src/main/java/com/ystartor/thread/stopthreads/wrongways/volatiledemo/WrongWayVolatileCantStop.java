package com.ystartor.thread.stopthreads.wrongways.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * @desc 演示用volatile的局限part2 陷入阻塞时，volatile是无法停止线程的，此例中，生产者的生产速度很快，消费者消费速度很慢,
 *          会出现阻塞队列满了以后，生产者会阻塞，等待消费者进一步消费
 */
public class WrongWayVolatileCantStop {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()){
            System.out.println(consumer.storage.take() + "被消费");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了");
        // 一旦消费者不需要更多数据了  应该让生产者停下来, 但是实际情况
        producer.canceled = true;
    }



}
class Producer implements Runnable{

    public volatile boolean canceled = false;

    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num < 1000000 && !canceled){
                if (num % 100 == 0){
                    System.out.println("num:" + num + " is 100 倍数");
                    storage.put(num);
                }
                num++;
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("生产者结束运行");
        }
    }
}
class Consumer {
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums(){
        if (Math.random() > 0.95){
            return false;
        }else {
            return true;
        }
    }
}