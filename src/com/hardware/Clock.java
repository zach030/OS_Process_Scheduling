package com.hardware;

import com.constant.ConstantTime;
import com.os.PCBPool;
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
                //设置标志为中断状态
                setClockStatus(ClockStatus.Interrupt);
                //CPU内时间自增
                clockTime += ConstantTime.BREAK_TIME;
                //System.out.println("CPU 当前时钟时间是:" + getClockTime() / 1000);
                //记录时钟中断次数
                this.clockInterruptTimes++;
                //检查pcb池,将到时间的进程创建加入就绪队列
                PCBPool.pcbPool.checkPCBInTime2GetReady();
                //时钟中断1s
                sleep(ConstantTime.BREAK_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
