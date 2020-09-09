package com.ystartor.thread.threadsunsafe;

import java.util.HashMap;
import java.util.Map;

public class MultiThreadsError6 {

    private Map<String, String> states;

    public MultiThreadsError6(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                states = new HashMap<>();
                states.put("1", "周1");
                states.put("2", "周2");
                states.put("3", "周3");
                states.put("4", "周4");
            }
        }).start();
    }

    public Map<String, String > getStates(){
        return states;
    }

    public static void main(String[] args) {
        MultiThreadsError6 multiThreadsError3 = new MultiThreadsError6();
        Map<String, String> states = multiThreadsError3.getStates();
        System.out.println(states.get("1"));
        states.remove("1");
        System.out.println(states.get("1"));
    }

}
