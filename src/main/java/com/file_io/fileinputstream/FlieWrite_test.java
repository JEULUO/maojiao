package com.file_io.fileinputstream;

import java.io.*;

public class FlieWrite_test {
    public static void main(String[] args) throws  Exception{
        //了解字符输出流的使用
        //创建字符输入流对象并包装到buffered字符输入流中
        try(  FileReader fr=new FileReader("src//main//java//com//file_io/csb.txt");
              BufferedReader br=new BufferedReader(fr);
              //创建字符输出流对象
              FileWriter fw=new FileWriter("src//main//java//com//file_io/csb_new.txt");
              //将字符输出流包装到buffered字符输出流中
              BufferedWriter bw=new BufferedWriter(fw);) {

            //通过buffered缓冲字符输出流copycsb.txt到csb_new.txt
            String line;
            while ((line=br.readLine())!=null){
                bw.write(line);
                bw.newLine();
            }
            System.out.println("复制完毕");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
