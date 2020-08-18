package com.ystartor.security;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EndodeString {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String plain = "吴琼";
        System.out.println(plain);
        plain = URLEncoder.encode(plain, "GBK");
        System.out.println(plain);
//        String ss = new String(plain.getBytes("UTF-8"),"GBK");
//        System.out.println(ss);
//        System.out.println(plain);
//        byte[] bytes = plain.getBytes("UTF-8");
//        byte[] gbks = new String(bytes, "UTF-8").getBytes("GBK");
//        String s = new String(gbks, "GBK");
//        System.out.println(s);
//        StringBuilder sb = new StringBuilder().append("jiji=1212").append(s);
//        System.out.println(sb);
//        byte[] gbks1 = s.getBytes("GBK");
//        String s2 = new String(gbks1, "GBK");
//        System.out.println(s2);
    }

}
