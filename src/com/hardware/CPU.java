package com.hardware;

import com.configs.*;
import com.os.PCB;

public class CPU {
    public static CPU cpu = new CPU();
    private int PC;//程序计数器，当前正在运行的进程序号
    private InstructionStatus IR;//指令寄存器,存放指令状态
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

    public void setRunningPCB(PCB runningPCB) {
        this.runningPCB = runningPCB;
    }

    public int getPSW() {
        return PSW;
    }

    public void setPSW(int PSW) {
        this.PSW = PSW;
    }

    public InstructionStatus getIR() {
        return IR;
    }

    public void setIR(InstructionStatus IR) {
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

    public void Protect(PCB p) {
        //TODO 由pcb设置当前的psw，currenttime
    }

    ;//CPU寄存器现场保护

    public void Recovery(PCB p) {
        //TODO 将当前pcb赋值为p
        //将cpu的类变量都用pcb赋值，恢复
    }

    public void SetRunningPCB(PCB pcb) {
        //设置当前执行的PCB
        this.runningPCB = pcb;
    }

    public void DeleteRunningPCB() {
        //清空当前的PCB指针
        this.runningPCB = null;
    }

}
