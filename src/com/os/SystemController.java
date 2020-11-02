package com.os;

import com.constant.ConstantTime;
import com.handle.KeyBoardInput;
import com.handle.PVCommunicate;
import com.handle.ScreenOutput;
import com.hardware.*;

import java.net.SecureCacheResponse;

/**
 * @program: Process_Sheduling
 * @description: 系统总控制
 * @author: zach
 * @create: 2020-10-27-21:02
 **/
public class SystemController {
    public static SystemController systemController = new SystemController();

    //初始化PCB池,线程启动
    public void SystemStart() {
        //文件管理 处理pcb
        FileOperator.fileOperator.ReadAllPCB(FileOperator.jobFileName);
        //初次:读job文件,全部同步pcb到池中
        PCBPool.pcbPool.setAllPcbList(FileOperator.fileOperator.TmpPCBList);
        //系统cpu运行:时钟线程
        CPU.cpu.clock.start();
        //开始进程调度:进程调度线程
        Schedule.schedule.start();
        //仿真程序初始化时启动:input,output,pv三个操作线程

    }

    public void SystemStop() {
        //系统cpu运行:时钟线程
        CPU.cpu.clock.interrupt();
        //开始进程调度:进程调度线程
        Schedule.schedule.interrupt();
        //仿真程序初始化时启动:input,output,pv三个操作线程
    }

    public boolean isSystemStop() {
        //检查系统状态:如果所有队列都空,则系统结束运行
        if (PCBPool.pcbPool.isInputBlockQueueEmpty())
            return false;
        if (PCBPool.pcbPool.isOutPutBlockQueueEmpty())
            return false;
        if (PCBPool.pcbPool.isPVBlockQueueEmpty())
            return false;
        return PCBPool.pcbPool.isReadyQueueEmpty();
    }

    public boolean checkTime2InterruptThread() {
        return CPU.cpu.getCpuState() == CPU.CpuState.COREMODE;
    }

}
