package com.ystartor.jihe;

import org.junit.Test;

public class ObjectHashCodeTest {

    @Test
    public void testGetObjectHashCode(){
        // zhi
        String some = "1";
        // zhi end
        Object key = some;
        int i = key.hashCode();
        System.out.println(i);
        String s = Integer.toBinaryString(i);
        System.out.println(s);
        int i1 = i >>> 16;
        System.out.println(i1);
        String s1 = Integer.toBinaryString(i1);
        System.out.println(s1);
    }


}
