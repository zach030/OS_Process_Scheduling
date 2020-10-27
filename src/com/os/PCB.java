package com.os;

import com.configs.*;
import com.hardware.CPU;

import java.util.*;

public class PCB {
    private int ProID;//值分别为 1,2,3,4,5,6，。。
    private int Priority;//优先级
    private int InTimes;//进程创建时间
    private int StartTime;//进程开始时间
    private int EndTimes;//进程结束时间
    private int InstructionsNum;//进程包含的指令数目
    private int IR;//正在执行的指令编号：Instruc_ID
    private int PC;//下一条将执行的指令编号：Instruc_ID
    private ProcessStatus psw;//进程状态：运行、阻塞、就绪
    public ArrayList<PCBInstructions> pcbInstructions = new ArrayList<>();//包含的指令list

    //进程在队列中的信息--抽象类
    private abstract class PCBInQueue {
        int BqNum;//编号
        int BqTime;//进入队列时间

        public int getBqNum() {
            return BqNum;
        }

        public int getBqTime() {
            return BqTime;
        }
    }

    private class PCBInReadyQueue extends PCBInQueue {
    } //就绪队列信息

    private class PCBInKeyBoardInputQueue extends PCBInQueue {
    } //键盘输入阻塞队列信息

    private class PCBInScreenOutputInputQueue extends PCBInQueue {
    } //屏幕输出阻塞队列信息

    private class PCBInPVOperationQueue extends PCBInQueue {
    } //PV操作阻塞队列信息


    public InstructionStatus getInstructionState() {
        return pcbInstructions.get(this.IR).getInstructionState();
    }

    public int getStartTime() {
        return StartTime;
    }

    public void setStartTime(int startTime) {
        StartTime = startTime;
    }

    public int getEndTimes() {
        return EndTimes;
    }

    public ArrayList<PCBInstructions> getPcbInstructions() {
        return pcbInstructions;
    }

    public void setPcbInstructions(ArrayList<PCBInstructions> pcbInstructions) {
        this.pcbInstructions = pcbInstructions;
    }

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public int getIR() {
        return IR;
    }

    public void setIR(int IR) {
        this.IR = IR;
    }

    public int getInstructionsNum() {
        return InstructionsNum;
    }

    public void setInstructionsNum(int instructionsNum) {
        InstructionsNum = instructionsNum;
    }

    public void setEndTimes(int endTimes) {
        EndTimes = endTimes;
    }

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
        InstructionsNum = instrucNum;
    }


    public int getInstrucNum() {
        return InstructionsNum;
    }

    public int getInTimes() {
        return InTimes;
    }

    public int getPriority() {
        return Priority;
    }

    public int getProID() {
        return ProID;
    }

    public ProcessStatus getPsw() {
        return psw;
    }

    public void setPsw(ProcessStatus psw) {
        this.psw = psw;
    }

    public void createProcess() {
        //进程原语：进程创建
        PCBPool.pcbPool.allPcbList.add(this);
        //将该进程设置为就绪态，加入到就绪队列
        this.setPsw(ProcessStatus.Ready);
        PCBPool.pcbPool.AddProcess2ReadyQueue(this);
    }//进程创建,不考虑作业调度，假设有足够内存。当有作业请求后自动创建

    public void destroyProcess() {
        //PCBPool.pcbPool.allPcbList.remove(this);
        PCBPool.pcbPool.pcbStatusHashMap.put(this,PCBStatus.UNUSABLE);

    }//进程撤销,执行完成的进程调用撤销函数；

    public void blockProcess(InstructionStatus instructionStatus) {
        //进程原语：进程阻塞
        //将进程加入到阻塞队列
        switch (instructionStatus){
            case PV_OPERATION:
                PCBPool.pcbPool.AddProcess2PVBlockQueue(this);
                break;
            case SCREEN_DISPLAY:
                PCBPool.pcbPool.AddProcess2OutputBlockQueue(this);
                break;
            case KEYBOARD_INPUT:
                PCBPool.pcbPool.AddProcess2InputBlockQueue(this);
                break;
            default:
                break;
        }
        this.setPsw(ProcessStatus.Block);
    }//进程阻塞,进程切换、CPU 模式切换时调用

    public void wakeProcess() {
        PCBPool.pcbPool.AddProcess2ReadyQueue(this);
        this.setPsw(ProcessStatus.Ready);
    }//进程唤醒
    //Instruc_State=1：4 秒唤醒，操作阻塞队列 1；
    //Instruc_State=3：3 秒唤醒，操作阻塞队列 2；
    //Instruc_State=2：2 秒唤醒，操作阻塞队列 3；
}
