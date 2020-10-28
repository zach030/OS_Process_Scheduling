package com.hardware;

import com.constant.ConstantTime;
import com.status.ClockStatus;

/*设置 1 秒执行 1 条指令，也就是假设计算机 1 秒(s)发生一次时钟硬件中断*/
public class Clock extends Thread {
    private ClockStatus clockStatus;
    private int clockTime;

    public ClockStatus getClockStatus() {
        return clockStatus;
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
        while (true) {
            try {
                System.out.println("time start");
                sleep(ConstantTime.BREAKE_TIME);        //睡眠一定时间
                setClockStatus(ClockStatus.Interrupt);      //设置标志为中断状态
                setClockTime(getClockTime() + ConstantTime.BREAKE_TIME);         //CPU内时间自增
                System.out.println("clock time is:" + getClockTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
