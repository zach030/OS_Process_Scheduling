package com.os;

import com.handle.HandleModule;

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
        //同步pcb到池中
        PCBPool.pcbPool.setAllPcbList(FileOperator.fileOperator.TmpPCBList);

        //系统cpu运行:时钟线程
        //CPU.cpu.clock.start();
        //开始进程调度:进程调度线程
        //Schedule.schedule.start();
        //处理功能模块运行:3个功能处理模块线程
        HandleModule.handleModule.run();
    }

    public boolean isSystemStop(){
        //检查系统状态:如果所有队列都空,则系统结束运行
        if (!PCBPool.pcbPool.isInputBlockQueueEmpty())
            return false;
        if (!PCBPool.pcbPool.isOutPutBlockQueueEmpty())
            return false;
        if (!PCBPool.pcbPool.isPVBlockQueueEmpty())
            return false;
        return PCBPool.pcbPool.isReadyQueueEmpty();
    }
}
