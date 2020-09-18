package com.ystartor.jihe;

import org.junit.Test;

import java.util.HashMap;

public class HashMapTest {

    @Test
    public void testInitMap(){
        System.out.println("test map init");
        HashMap<Object, Object> mapInit = new HashMap<>();
        System.out.println(mapInit);
    }

}
