package com.ystartor;

/**
 * @desc StringBuilder如果append(null)是个什么情况
 */
public class javabasic {

    public static void main(String[] args) {
        String s = null;
        s = s + "";
        System.out.println(s);
    }

}
