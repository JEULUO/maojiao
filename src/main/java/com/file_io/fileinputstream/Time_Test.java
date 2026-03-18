package com.file_io.fileinputstream;

import java.io.*;
import java.nio.file.Path;

public class Time_Test {
    private static final String SrcPath="D:\\浏览器安装包\\7、Redis入门到实战教程\\Redis-视频\\01-实用篇视频\\01-实用篇视频\\01.Redis入门\\11.Redis命令-Hash类型.mp4";
    private static final String DestPath="src//main//java//com//file_io//";
    public static void main(String[] args) {
        //字节流，缓冲输入流，缓冲输出流，拷贝视频，时间测试
//        copyFile(SrcPath,DestPath);
        //低级字节流字节数组输入流字节数组输出流拷贝视频
        copyFile2(SrcPath,DestPath);
        //使用高级缓冲流缓冲输入流缓冲输出流拷贝视频
        copyFile3(SrcPath,DestPath);
        //使用高级缓存流按照字节数读取
//        copyFile4(SrcPath,DestPath);

    }




    private static void copyFile4(String srcPath, String destPath) {
        try(
                FileInputStream fis=new FileInputStream(srcPath);
                FileOutputStream fos=new FileOutputStream(destPath+"4.mp4");
                ) {
            long l = System.currentTimeMillis();
            //高级字节流
            BufferedInputStream bis=new BufferedInputStream(fis);
            BufferedOutputStream bos=new BufferedOutputStream(fos);
            while (bis.available()>0){
                //高级缓存流按照字节一个一个读写
                bos.write(bis.read());
            }
            System.out.println("按照字节数复制完毕");
            System.out.println("按照高级字节数复制耗时："+(System.currentTimeMillis()-l)+"毫秒");
                }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void copyFile3(String srcPath, String destPath) {
        try(
                FileInputStream fis=new FileInputStream(srcPath);
                FileOutputStream fos=new FileOutputStream(destPath+"3.mp4");
                ) {
            long l = System.currentTimeMillis();
            System.out.println("高级缓冲流复制开始");
            //使用缓冲流
            BufferedInputStream bis=new BufferedInputStream(fis);
            BufferedOutputStream bos=new BufferedOutputStream(fos);
            byte[] bytes = new byte[1024];
            while (bis.available()>0){
                //缓冲流边读边写
                int len = bis.read(bytes);
                bos.write(bytes,0,len);
            }
//            System.out.println("高级缓冲流复制完毕");
            System.out.println("高级缓冲流字节数组复制耗时："+ (System.currentTimeMillis()-l)+"毫秒");
                }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void copyFile2(String srcPath, String destPath) {
        try(
                FileInputStream fis=new FileInputStream(srcPath);
                FileOutputStream fos=new FileOutputStream(destPath+"2.mp4");
                ) {
            long l = System.currentTimeMillis();
            //字节数组复制
            byte[] bytes = new byte[1024];
            while (fis.available()>0){
                int len = fis.read(bytes);
                fos.write(bytes,0,len);
            }
            System.out.println("字节数组复制完毕");
            System.out.println("低级字节流字节数组复制耗时："+(System.currentTimeMillis()-l)+"毫秒");
                }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void copyFile(String srcPath,String destPath){
        try(
                FileInputStream fis=new FileInputStream(srcPath);
                FileOutputStream fos=new FileOutputStream(destPath+"1.mp4");
                ) {
            long l = System.currentTimeMillis();
            //单个字节复制
            while (fis.available()>0){
                fos.write(fis.read());
            }
            System.out.println("复制完毕");
            System.out.println("低级字节字节复制耗时："+(System.currentTimeMillis()-l)+"毫秒");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
