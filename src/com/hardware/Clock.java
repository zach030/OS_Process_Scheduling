package com.hardware;

import com.constant.ConstantTime;
import com.os.FileOperator;
import com.os.PCBPool;
import com.os.SystemController;
import com.status.ClockStatus;

/*设置 1 秒执行 1 条指令，也就是假设计算机 1 秒(s)发生一次时钟硬件中断*/
public class Clock extends Thread {
    public static Clock clock = new Clock();

    private ClockStatus clockStatus;
    private int clockTime;
    private int clockInterruptTimes = 0;

    public ClockStatus getClockStatus() {
        return clockStatus;
    }

    public int getClockInterruptTimes() {
        return clockInterruptTimes;
    }

    public void setClockStatus(ClockStatus clockStatus) {
        this.clockStatus = clockStatus;
    }

    public Clock() {
        clockStatus = ClockStatus.Running;
        clockTime = -1000;
    }

    public int getClockTime() {
        return clockTime;
    }

    public void setClockTime(int clockTime) {
        this.clockTime = clockTime;
    }

    public void run() {
        System.out.println("线程：---------CPU仿真时钟线程开始运行");
        while (true) {
            try {
                if (SystemController.systemController.checkTime2InterruptThread()) {
                    //如果CPU处于核心态：PV操作
                    System.err.println("进入CPU核心态，关闭时钟中断");
                    try {
                        sleep(ConstantTime.PV_COMMUNICATE_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //系统时间自增
                        ConstantTime.SYSTEM_TIME += ConstantTime.PV_COMMUNICATE_INTERVAL;
                    }
                } else {
                    //CPU处于用户态，时钟正常中断
                    //时钟中断1s
                    sleep(ConstantTime.BREAK_TIME);
                    ConstantTime.constantTime.SystemAddTime();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (isTime2ReadJob()) {
                    //System.out.println("线程：---------已经过5次时钟中断，正在检查是否有新的作业请求");
                    //TODO 5次间隔 已做好 待完善 读pcb函数
                    FileOperator.fileOperator.ReadOneNewPCB();
                }
                //设置标志为中断状态
                setClockStatus(ClockStatus.Interrupt);
                //CPU内时间自增
                clockTime += ConstantTime.BREAK_TIME;
                //System.out.println("CPU 当前时钟时间是:" + getClockTime() / 1000);
                //记录时钟中断次数
                this.clockInterruptTimes++;
                //检查pcb池,将到时间的进程创建加入就绪队列
                PCBPool.pcbPool.checkPCBInTime2GetReady();
                System.err.println("######## 当前系统时间：" + ConstantTime.getSystemTime() / 1000 + "  #########");
            }
        }
    }

    public boolean isTime2ReadJob() {
        return (CPU.cpu.clock.getClockInterruptTimes() % 5 == 0) && (CPU.cpu.clock.getClockInterruptTimes() != 0);
    }
}
