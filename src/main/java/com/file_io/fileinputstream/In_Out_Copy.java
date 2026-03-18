package com.file_io.fileinputstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class In_Out_Copy {
    public static void main(String[] args) throws Exception {
        FileInputStream fis =new FileInputStream("src//main//java//com//file_io/1.png");
        FileOutputStream fos=new FileOutputStream("src//main//java//com//file_io/2.png");
        byte[] bytes = new byte[1024];
        while (fis.available()>0){
            int len = fis.read(bytes);
            fos.write(bytes,0,len);
            System.out.println("已复制");
        }
        }
    }
