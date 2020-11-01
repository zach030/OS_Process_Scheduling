package com.os;

import com.constant.ConstantTime;
import com.hardware.CPU;
import com.status.ClockStatus;
import com.status.ScheduleStatus;

/*
进程调度模块用一个单独的线程或计时器实现。需要重点注意的是：不是每次发生时
钟中断都要进行进程调度。
◆实现时间片轮转算法。申请优秀的学生需实现时间片轮转+静态优先数调度算法。
◆时间片计算：每 2 秒为 1 个时间片，发生一次进程切换。
◆时间片轮转+静态优先数调度算法：采用非抢占式。优先数越小，优先级越高。
* */
//进程调度
public class Schedule extends Thread {
    public static Schedule schedule = new Schedule();
    ScheduleStatus scheduleStatus;
    int wait4Schedule = 0;

    public Schedule() {

    }

    @Override
    public void run() {
        System.out.println("线程：---------进程调度线程开始执行");
        //TODO 1 比较pcb intimes,加入就绪队列
        while (true) {
            try {
                if (CPU.cpu.clock.getClockStatus() == ClockStatus.Interrupt) {
                    LowScheduling();
                }
                sleep(ConstantTime.BREAK_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //进行低级调度
            //LowLevelScheduling();
            //如果此时处于中断,则进入调度
            //1、保存处理机的现场信息。
            //2、按某种算法选取进程。
            //3、把处理器分配给进程。
        }
    }

    public void LowScheduling() {
        System.out.println("*************************正在进行一次调度******************************");
        ConstantTime.constantTime.SystemAddTime();
        //对就绪队列根据进程优先级进行排序
        PCBPool.pcbPool.sortReadyQueueByPriority();
        //1、检查是否有正在运行的进程
        //没有
        //PCBPool.pcbPool.displayReadyQueue();
        if (CPU.cpu.getRunningPCB() == null) {
            System.out.println("==========当前无进程正在执行==========");
            //进行调度:如果就绪队列为空，则return，否则就绪队列第一个pcb开始运行
            if (PCBPool.pcbPool.getReadyQueue().size() == 0) {
                System.out.println("当前就绪队列为空，结束此次调度");
                return;
            }
            //调度算法简单版本：就绪队列对头出列，进行执行
            //就绪队列不为空
            PCB ready2Run = PCBPool.pcbPool.getReadyQueue().get(0);

            ready2Run.setStartTime(ConstantTime.getSystemTime());
            //加入running queue
            PCBPool.pcbPool.AddProcess2RunningQueue(ready2Run);
            CPU.cpu.Recovery(ready2Run);
        } else {
            //有正在运行
            PCB runningPCB = CPU.cpu.getRunningPCB();
            System.out.println("正在运行进程：" + runningPCB.getProID() + ",正在运行指令：");
            //判断进程是否运行完
            if (runningPCB.isPCBRunOver()) {
                //已经运行完，销毁进程
                System.out.println("进程" + runningPCB.getProID() + "，已经运行结束");
                runningPCB.destroyProcess();
                return;
            }
            //得到当前正在执行的指令
            runningPCB.getCurrentInstruction().displayInstruction();
            //cpu更新模式
            CPU.cpu.updateCpuModeByInstruction();
            //CPU.cpu.displayCPUState();
            //2、开始运行进程
            switch (runningPCB.getInstructionState()) {
                case KEYBOARD_INPUT:
                    runningPCB.InputInstrucRun();
                    break;
                case SCREEN_DISPLAY:
                    runningPCB.OutPutInstrucRun();
                    break;
                case PV_OPERATION:
                    runningPCB.PVCommunicateInstrucRun();
                    break;
                case USERMODE_CALC:
                    runningPCB.NormalInstrucRun();
            }
            //没到时间片，指令+1
            runningPCB.AddCurrentIR();
        }

    }


}
