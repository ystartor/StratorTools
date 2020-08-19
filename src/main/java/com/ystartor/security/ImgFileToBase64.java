package com.ystartor.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImgFileToBase64 {

    public static void main(String[] args) throws IOException {
        byte[] data = null;
        InputStream in = null;
        try {
            in = new FileInputStream("Y:\\1234.txt");
            data = new byte[in.available()];
            in.read(data);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ByteToFile(data);
//        GenerateImage(s, "X:\\1212.bak.jpg");
    }

    private static void ByteToFile(byte[] bt) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bt);
        BufferedImage bi1 = ImageIO.read(bais);
        try {
            File w2 = new File("X:\\12222.bak.jpg");//可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            bais.close();
        }
    }

    /*
       base64码转为图片,对字节数组字符串进行Base64解码并生成图片
    */
    public static boolean GenerateImage(String imgStr, String imgFilePath) {
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
