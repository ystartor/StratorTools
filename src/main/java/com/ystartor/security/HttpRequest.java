package com.ystartor.security;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {

    public static void main(String[] args) throws Exception {
        executeRquest("http://10.8.206.233:30090/login", "username=jiangxinxin&password=e10adc3949ba59abbe56e057f20f883e", false, "BD2FE2CC51249E5439453EE601905AAC");

        executeRquest("http://10.8.206.233:30090/businessUser/getAllData", "", true, "");

    }

    public static void executeRquest(String urlStr,String params, boolean isGet,String JSESSIONID) throws Exception{
        urlStr += isGet?(params!=null?("?"+params):""):"";
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod(isGet?"GET":"POST");

        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Charset", "UTF-8");

        if(JSESSIONID != null &&  !"".equals(JSESSIONID)){
            conn.setRequestProperty("Cookie", "JSESSIONID="+JSESSIONID);
        }
        conn.connect();
        if(!isGet){
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            out.write(params);
            out.flush();
            out.close();
        }
        //接受请求返回的数据
        InputStream ins = conn.getInputStream();
        StringBuilder content = new StringBuilder();
        int count = ins.available();
        while(count == 0){
            count  = ins.available();
        }
        byte[] bt = new byte[128];

        while(ins.read(bt) != -1){
            content.append(new String(bt,"UTF-8")+"\n");
            bt = new byte[1024];
        }

        String mp = conn.getHeaderField("Set-Cookie");
        System.out.println("**JSESSIONID*********************");
        System.out.println(mp);
        System.out.println("*********************************");
        //in.close();
        ins.close();
        System.out.println("--------------------content begin-------------------------");
        System.out.println(content);
        System.out.println("--------------------content end-------------------------");
        conn.disconnect();
    }

}
