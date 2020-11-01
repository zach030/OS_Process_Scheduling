package com.hardware;

import com.os.PCB;
import com.os.PCBInstructions;
import org.jetbrains.annotations.NotNull;

public class CPU {
    //实例CPU对象
    public static CPU cpu = new CPU();
    //clock对象,统一通过cpu来调用
    public Clock clock = Clock.clock;

    private int PC;//程序计数器，当前正在运行的进程序号
    private PCBInstructions IR;//指令寄存器,存放指令状态
    private int PSW;//状态寄存器，当前正在运行的指令
    private PCB runningPCB;//正在运行的进程控制块

    //CPU状态
    private enum CpuState {
        USERMODE, COREMODE
    }

    CpuState cpuState;                    //CPU的状态


    public CPU() {
    }

    public CpuState getCpuState() {
        return cpuState;
    }

    public void setCpuState(CpuState cpuState) {
        this.cpuState = cpuState;
    }

    public PCB getRunningPCB() {
        return runningPCB;
    }

    public void setRunningPCB(@NotNull PCB runningPCB) {
        System.out.println("进程：" + runningPCB.getProID() + "正在运行");
        this.runningPCB = runningPCB;
    }

    public int getPSW() {
        return PSW;
    }

    public void setPSW(int PSW) {
        this.PSW = PSW;
    }

    public PCBInstructions getIR() {
        return IR;
    }

    public void setIR(PCBInstructions IR) {
        this.IR = IR;
    }

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    //设置CPU为用户态
    public void setCpuUserMode() {
        this.cpuState = CpuState.USERMODE;
    }

    //设置CPU为核心态
    public void setCpuCoreMode() {
        this.cpuState = CpuState.COREMODE;
    }

    //根据指令更新CPU状态
    public void updateCpuModeByInstruction() {
        switch (this.runningPCB.getInstructionState()) {
            case USERMODE_CALC:
            case KEYBOARD_INPUT:
            case SCREEN_DISPLAY:
                this.cpuState = CpuState.USERMODE;
                break;
            case PV_OPERATION:
                this.cpuState = CpuState.COREMODE;
                break;
            default:
                break;
        }
    }

    public void DeleteRunningPCB() {
        //清空当前的PCB指针
        this.runningPCB = null;
    }

    public void Protect(PCB p) {
        //TODO 由pcb设置当前的psw，currenttime

    }

    ;//CPU寄存器现场保护

    public void Recovery(PCB p) {
        //TODO 将当前pcb赋值为p
        this.setRunningPCB(p);
        //将cpu的类变量都用pcb赋值，恢复
        this.setPC(p.getProID());
        //this.setIR();
    }
}
