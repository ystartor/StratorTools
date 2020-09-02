package com.ystartor.thread.stopthreads.wrongways.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @desc 用终端来修复刚才的无尽等待的问题
 */
public class WrongWayVolatileFix {


    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        WrongWayVolatileFix wrongWayVolatileFix = new WrongWayVolatileFix();
        Producer producer = wrongWayVolatileFix.new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = wrongWayVolatileFix.new Consumer(storage);
        while (consumer.needMoreNums()){
            System.out.println(consumer.storage.take() + "被消费");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了");
        // 一旦消费者不需要更多数据了  应该让生产者停下来, 但是实际情况
        producerThread.interrupt();
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
                while (!Thread.currentThread().isInterrupted() && num < 1000000){
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



}
