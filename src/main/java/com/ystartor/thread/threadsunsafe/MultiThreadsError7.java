package com.ystartor.thread.threadsunsafe;

/**
 * @desc 用工厂模式实现注册监听器出现的线程安全问题
 */
public class MultiThreadsError7 {

    int count;
    private EventListener listener;
    private MultiThreadsError7(MySource source){
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("\ni get data: " + count);
            }
        };
        for (int i = 0; i < 100000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static MultiThreadsError7 getInstance(MySource source){
        MultiThreadsError7 safe = new MultiThreadsError7(source);
        source.registerListener(safe.listener);
        return safe;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new Event() {
                });
            }
        }).start();
        MultiThreadsError7 m5 = new MultiThreadsError7(mySource);
    }

    static class MySource{
        private EventListener listener;
        void registerListener(EventListener eventListener){
            this.listener =eventListener;
        }
        void eventCome(Event e){
            if (listener != null){
                listener.onEvent(e);
            }
        }
    }
    interface EventListener{
        void onEvent(Event e);
    }

    interface Event{

    }


}
