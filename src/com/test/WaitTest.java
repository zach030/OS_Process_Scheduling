package com.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WaitTest {
    public static void main(String[] args) throws IOException {

        write("D:\\1.txt"); //运行主方法
    }

    public static void write(String path) throws IOException {
        //将写入转化为流的形式
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        //一次写一行
        String ss = "测试数据";
        bw.write(ss);
        bw.newLine();  //换行用

        //关闭流
        bw.close();
        System.out.println("写入成功");
    }


}