package com.ystartor.thread.threadsunsafe;

/**
 * @desc 注册监听器出现的线程安全问题
 */
public class MultiThreadsError5 {

    int count;
    public MultiThreadsError5(MySource source){
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("\ni get data: " + count);
            }
        });
        for (int i = 0; i < 100000; i++) {
            System.out.print(i);
        }
        count = 100;
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
        MultiThreadsError5 m5 = new MultiThreadsError5(mySource);
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
