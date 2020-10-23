package com.os;

import com.queue.*;

import java.util.ArrayList;

//进程状态
enum ProcessStatus {
    Running, Ready, Block
}

/*
设计并发进程请求文件、进程进程控制块 PCB、进程程序段指令、进程表以及进程切换、
阻塞、唤醒等原语
*/

public class Process {
    int ProID;//值分别为 1,2,3,4,5,6，。。
    int Priority;//优先级
    int InTimes;//进程创建时间
    int StartTimes;//进程开始执行时间
    int EndTimes;//进程结束时间
    int InstrucNum;//进程包含的指令数目
    ProcessStatus psw;//进程状态
    private static class RunTimes {
        int StartTime;
        int EndTime;
    }
    ArrayList<RunTimes> RunQueue;//进程运行时间列表
    private static class TurnTimes {
        int InitTime;
        int EndTime;
    }
    ArrayList<TurnTimes> TurnQueue;//进程周转时间统计
    int PC;//程序计数器信息
    int IR;//指令寄存器信息
    ReadyQueue ReadyProcess;//就绪队列信息列表
    InputBlockQueue inputBlockQueue;// 键盘输入阻塞队列时间
    OutputBlockQueue outputBlockQueue;//进程进入显示器输出阻塞队列时间
    PVBlockQueue pvBlockQueue;//进程进入 PV操作控制阻塞队列时间

    public void setPriority(int priority) {
        Priority = priority;
    }

    public void setProID(int proID) {
        ProID = proID;
    }

    public void setInTimes(int inTimes) {
        InTimes = inTimes;
    }

    public void setInstrucNum(int instrucNum) {
        InstrucNum = instrucNum;
    }

    public void setEndTimes(int endTimes) {
        EndTimes = endTimes;
    }

    public void setStartTimes(int startTimes) {
        StartTimes = startTimes;
    }

}
