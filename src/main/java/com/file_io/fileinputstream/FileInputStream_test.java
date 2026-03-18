package com.file_io.fileinputstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileInputStream_test {
    public static void main(String[] args) {
        //了解字节输入流实现类的使用
        try {
            FileInputStream fis =new FileInputStream("src//main//java//com//file_io/1.txt");
            //读取文件的一字节并输出
//            while (fis.available()>0){
//                System.out.print((char)fis.read());
//            }
            //读取文件的多个字节并输出
            byte[] bytes = new byte[1024];
            while (fis.available()>0){
                int len = fis.read(bytes);
                System.out.print(new String(bytes,0,len));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
