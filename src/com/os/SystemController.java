package com.os;

import com.handle.BaseHandle;
import com.handle.HandleModule;
import com.handle.KeyBoardInput;
import com.hardware.CPU;

/**
 * @program: Process_Sheduling
 * @description: 系统总控制
 * @author: zach
 * @create: 2020-10-27-21:02
 **/
public class SystemController {
    public static SystemController systemController = new SystemController();

    public void SystemStart(){
        //文件管理 处理pcb
        FileOperator.fileOperator.ReadAllPCB(FileOperator.jobFileName);
        PCBPool pcbPool = new PCBPool();
        pcbPool.allPcbList = FileOperator.fileOperator.TmpPCBList;
        //系统cpu运行:时钟线程
        CPU.cpu.clock.start();
        //开始进程调度:进程调度线程
        Schedule.schedule.start();
        //处理功能模块运行:3个功能处理模块线程
        HandleModule.handleModule.run();
    }
}
