package com.os;

import com.queue.*;
import com.os.Clock.*;
import java.io.*;
import java.util.*;


public class SimulateTest {
    static void readLineVarFile(String fileName, int lineNumber) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); //使用缓冲区的方法将数据读入到缓冲区中
        String line = reader.readLine(); //定义行数
        if (lineNumber <= 0 || lineNumber > getTotalLines(fileName)) //确定输入的行数是否有内容
        {
            System.out.println("不在文件的行数范围之内。");
        }
        int num = 0;
        while (line != null)    //当行数不为空时，输出该行内容及字符数
        {
            if (lineNumber == ++num) {
                System.out.println("第" + lineNumber + "行: " + line + "     字符数为：" + line.length());
            }
            line = reader.readLine();
        }
        reader.close();
    }

    // 文件内容的总行数
    static int getTotalLines(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); //使用缓冲区的方法将数据读入到缓冲区中
        LineNumberReader reader = new LineNumberReader(br);
        String s = reader.readLine(); //定义行数
        int lines = 0;
        while (s != null) //确定行数
        {
            lines++;
            s = reader.readLine();
        }
        reader.close();
        br.close();
        return lines; //返回行数
    }

    public static void main(String[] args) throws IOException {
        //1、计时器线程启动
        Clock clock = new Clock();
        clock.startTimer();

        String filename = "D:\\UniCourse\\OS\\Process_Sheduling\\src\\com\\testdata\\19318123-jobs-input.txt";
        int totalNo = getTotalLines(filename);  // 获取文件的内容的总行数
        System.out.println("本文总共有：" + totalNo + "行");
        for (int i = 0; i < totalNo; i++) {
            readLineVarFile(filename, i); //读取指定行的内容
        }

    }
}
