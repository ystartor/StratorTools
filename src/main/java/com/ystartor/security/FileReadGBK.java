package com.ystartor.security;

import java.io.*;

public class FileReadGBK {

    public static void main(String[] args) throws IOException {

        File f = new File("Y:\\公司ilink\\兴业\\lian\\now_run_log.out");
        InputStream ins = null;
        ins = new FileInputStream(f);
        StringBuilder content = new StringBuilder();
        int count = ins.available();
        while(count == 0){
            count  = ins.available();
        }
        byte[] bt = new byte[128];
        while(ins.read(bt) != -1){
            content.append(new String(bt,"GBK"));
            bt = new byte[1024];
        }
        String respContent = content.toString();
        System.out.println(respContent);
        respContent = respContent.replaceAll("\0", "");
        System.out.println(respContent);
//        int checkre = respContent.indexOf("checkre");
//        System.out.println("--------------");
//        String resu = respContent.substring(checkre, checkre + 700);
//        System.out.println(resu);
//        System.out.println(resu.indexOf(" "));
//        resu = resu.replaceAll("\\s*", "");
//        resu = resu.replaceAll("\0", "");
////        resu = resu.replaceAll(" +", "");
//        System.out.println(resu);
//        byte[] s = resu.getBytes("UTF-8");
//        int i = 0;
//        while (i < s.length-1){
//            System.out.println(s[i]);
//            i++;
//        }
//        respContent = respContent.replaceAll(" ", "");
//        respContent = respContent.replaceAll("\n","");
//        respContent = respContent.replaceAll("\t","");
//        System.out.println("-------------------------------------------------------");
////        System.out.println(respContent);

    }

}
