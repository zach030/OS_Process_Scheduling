package com;

import java.io.*;

import com.hardware.Clock;
import com.os.SystemController;

public class SimulateTest {
    public static void main(String[] args) throws IOException {
        //1、计时器线程启动
        SystemController.systemController.SystemStart();
//        ProcessSchedule processSchedule = new ProcessSchedule();
//        processSchedule.start();
        //2、读job,判断是否有进程
//        PCBPool pcbPool = new PCBPool();
//        pcbPool.readAllPCB();
//        pcbPool.displayAllPCB();
        //执行进程

    }
}

