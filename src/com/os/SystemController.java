package com.os;

import com.hardware.Clock;

/**
 * @program: Process_Sheduling
 * @description: 系统总控制
 * @author: zach
 * @create: 2020-10-27-21:02
 **/
public class SystemController {

    FileOperator fileOperator = new FileOperator();
    public void SystemStart(){
        //系统运行
        //计时器运行
        Clock clock = new Clock();
        clock.startTimer();
        //初始化pcb，一次性读入
        fileOperator.ReadAllPCB(FileOperator.jobFileName);
        PCBPool pcbPool = new PCBPool();
        pcbPool.allPcbList = fileOperator.TmpPCBList;
    }
}
