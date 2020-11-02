package com;

import java.io.*;

import com.os.SystemController;

public class SimulateTest {
    public static void main(String[] args) throws IOException {
        //system controller start
        SystemController.systemController.SystemStart();
        if (SystemController.systemController.isSystemStop()){
            System.out.println("当前系统无进程运行");
            //SystemController.systemController.SystemStop();
        }
        //系统运行

    }
}

