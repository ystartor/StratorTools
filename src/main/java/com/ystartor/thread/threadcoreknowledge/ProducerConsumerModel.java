package com.ystartor.thread.threadcoreknowledge;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @desc 用wait/notify来实现
 */
public class ProducerConsumerModel {

    public static void main(String[] args) {
        EventStorage s = new EventStorage();
        Producer producer = new Producer(s);
        Consumer consumer = new Consumer(s);
        Thread thread = new Thread(producer);
        thread.start();
        Thread thread2 = new Thread(consumer);
        thread2.start();
    }

}
class Producer implements Runnable{

    private EventStorage storage;

    public Producer(EventStorage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.put();
        }
    }
}


class Consumer implements Runnable{

    private EventStorage storage;

    public Consumer(EventStorage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }
    }
}

class EventStorage{
    private int maxSize;
    private LinkedList<Date> storage;

    public EventStorage(){
        maxSize = 10;
        storage = new LinkedList<>();
    }

    public synchronized void put(){
        while (storage.size() == maxSize){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.println("has " + storage.size());
        notify();
    }

    public synchronized void  take(){
        while (storage.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("get " + storage.poll() + ",has " + storage.size());
        notify();
    }

}