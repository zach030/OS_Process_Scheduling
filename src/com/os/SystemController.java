package com.os;

import com.PlatForm;
import com.moduleThread.KeyBoardInput;
import com.moduleThread.PVCommunicate;
import com.moduleThread.ScreenOutput;
import com.hardware.*;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * @program: Process_Sheduling
 * @description: 系统总控制
 * @author: zach
 * @create: 2020-10-27-21:02
 **/

public class SystemController {
    public static SystemController systemController = new SystemController();

    //初始化PCB池,线程启动
    public void SystemStart() throws FileNotFoundException {
        PlatForm.systemFlag=true;
        PrintStream ps = new PrintStream("D:\\UniCourse\\OS\\周全-19318123-必修实验-申请成绩\\output\\ProcessResults.txt");// 创建文件输出流
        System.setOut(ps);// 设置使用新的输出流
        //文件管理 处理pcb
        FileOperator.fileOperator.ReadAllPCB(FileOperator.jobFileName);
        //初次:读job文件,全部同步pcb到池中
        PCBPool.pcbPool.setAllPcbList(FileOperator.fileOperator.TmpPCBList);
        //系统cpu运行:时钟线程
        CPU.cpu.clock.start();
        //开始进程调度:进程调度线程
        Schedule.schedule.start();
        //仿真程序初始化时启动:input,output,pv三个操作线程
        KeyBoardInput.keyBoardInput.start();
        PVCommunicate.pvCommunicate.start();
        ScreenOutput.screenOutput.start();
    }

    public void SystemInterrupt() {
        //系统cpu运行:时钟线程
        CPU.cpu.clock.interrupt();
        //开始进程调度:进程调度线程
        Schedule.schedule.interrupt();
        //仿真程序初始化时启动:input,output,pv三个操作线程
        KeyBoardInput.keyBoardInput.interrupt();
        PVCommunicate.pvCommunicate.interrupt();
        ScreenOutput.screenOutput.interrupt();
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
