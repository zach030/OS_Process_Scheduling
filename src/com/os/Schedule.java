package com.os;

import com.config.ConstantTime;
import com.hardware.CPU;
import com.PlatForm;
import com.config.ClockStatus;
import com.config.ScheduleStatus;

//进程调度
public class Schedule extends Thread {
    public static Schedule schedule = new Schedule();
    ScheduleStatus scheduleStatus;

    public Schedule() {

    }

    @Override
    public void run() {
        while (true) {
            try {
                if (SystemController.systemController.checkTime2InterruptThread()) {
                    //如果CPU处于核心态
                    this.scheduleStatus = ScheduleStatus.Wait;
                    System.err.println("进入CPU核心态，关闭进程调度中断");
                    try {
                        sleep(ConstantTime.PV_COMMUNICATE_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    this.scheduleStatus = ScheduleStatus.Wait;
                    sleep(ConstantTime.BREAK_TIME);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (CPU.cpu.clock.getClockStatus() == ClockStatus.Interrupt) {
                    this.scheduleStatus = ScheduleStatus.Running;
                    LowScheduling();
                }
            }
        }
    }

    public void LowScheduling() {
        System.out.println("*************************正在进行调度******************************");
        //对就绪队列根据进程优先级进行排序
        PCBPool.pcbPool.sortReadyQueueByPriority();
        PCBPool.pcbPool.displayReadyQueue();
        PlatForm.platForm.readyInfo.setText(PCBPool.pcbPool.concatList2String(PCBPool.pcbPool.getReadyQueue()));
        //1、检查是否有正在运行的进程
        if (!CPU.cpu.isCPUWork()) {
            System.out.println("==========当前无进程正在执行==========");
            PlatForm.platForm.runningInfo.setText("无进程执行");
            //进行调度:如果就绪队列为空，则return，否则就绪队列第一个pcb开始运行
            if (PCBPool.pcbPool.isReadyQueueEmpty()) {
                System.out.println("当前就绪队列为空，结束此次调度");
                return;
            }
            //就绪队列不为空
            PCB ready2Run = PCBPool.pcbPool.getReadyQueue().get(0);
            //判断进程是否已经运行结束
            if (ready2Run.isPCBOver()) {
                System.out.println("进程" + ready2Run.getProID() + "，已经运行结束");
                ready2Run.destroyProcess();
                return;
            }
            ready2Run.setStartTime(ConstantTime.getSystemTime());
            //从ready queue中删除
            PCBPool.pcbPool.getReadyQueue().remove(ready2Run);
            //加入running queue
            PCBPool.pcbPool.AddProcess2RunningQueue(ready2Run);
            //恢复现场
            CPU.cpu.Recovery(ready2Run);
        }
        //有正在运行
        PCB runningPCB = CPU.cpu.getRunningPCB();
        PlatForm.platForm.runningInfo.setText(Integer.toString(runningPCB.getProID()));
        //如果进程已执行完最后一条指令
        if (runningPCB.isPCBOver()) {
            System.out.println("进程" + runningPCB.getProID() + "，已经运行结束");
            runningPCB.destroyProcess();
            return;
        }
        CPU.cpu.updateCpuModeByInstruction();
        PlatForm.platForm.cpuState.setText(CPU.cpu.getCpuState().toString());
        //判断时间片是否耗尽
        if (runningPCB.isPCBRunOverInTimeSlice()) {
            System.out.println("本进程已运行完一个时间片，需要进行进程调度");
            //运行转就绪
            PCBPool.pcbPool.AddProcess2ReadyQueue(runningPCB);
            runningPCB.setTimeSliceLeft(ConstantTime.TIME_SLICE);
            CPU.cpu.DeleteRunningPCB();
            return;
        }
        System.out.println("正在运行进程：" + runningPCB.getProID() + ",正在运行指令：");
        //得到当前正在执行的指令
        runningPCB.getCurrentInstruction().displayInstruction();
        //2、开始运行进程
        CPU.cpu.execProcess();
    }
}

