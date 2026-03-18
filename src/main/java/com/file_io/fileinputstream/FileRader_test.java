package com.file_io.fileinputstream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileRader_test {
    public static void main(String[] args) throws Exception{
        try(
                FileReader fr=new FileReader("src//main//java//com//file_io/1.txt");
                BufferedReader br=new BufferedReader(fr);
                ) {
            //了解字符输入流的 使用

//        char[] chars = new char[1024];
//        int len;
//        while ((len=fr.read(chars))!=-1) {
//            System.out.print(new String(chars,0,len));
//        }
            String line;
            while ((line=br.readLine())!=null){
                    System.out.println(line);
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
