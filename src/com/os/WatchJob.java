package com.os;

import com.constant.ConstantTime;
import com.hardware.CPU;

/**
 * @program: Process_Sheduling
 * @description: 监视读取新的job进程
 * @author: zach
 * @create: 2020-10-29-22:59
 **/
public class WatchJob extends Thread {
    public static WatchJob watchJob = new WatchJob();

    public void run() {
        while (true) {
            try {
                if (isTime2ReadJob()) {
                    System.out.println("线程：-----已经过5次时钟中断，正在检查是否有新的作业请求");
                    //TODO 5次间隔 已做好 待完善 读pcb函数
                    FileOperator.fileOperator.ReadOneNewPCB();
                }
                sleep(ConstantTime.BREAK_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean isTime2ReadJob() {
        return (CPU.cpu.clock.getClockInterruptTimes() % 5 == 0) && (CPU.cpu.clock.getClockInterruptTimes() != 0);
    }
}
