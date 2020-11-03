package com.os;

import com.hardware.CPU;
import com.PlatForm;
import com.config.InstructionStatus;

/**
 * @program: Process_Sheduling
 * @description: 进程指令
 * @author: zach
 * @create: 2020-10-27-23:11
 **/
public class PCBInstructions {
    //指令编号
    private int instructionID;
    //指令类型
    private InstructionStatus instructionState;


    public InstructionStatus getInstructionState() {
        return instructionState;
    }

    public void setInstructionState(InstructionStatus instructionState) {
        this.instructionState = instructionState;
    }

    public int getInstructionID() {
        return instructionID;
    }

    public void setInstructionID(int instructionID) {
        this.instructionID = instructionID;
    }

    public void displayInstruction() {
        String outStr = "进程:" + CPU.cpu.getRunningPCB().getProID() + ",指令:" + this.getInstructionID() + ",类型:" + this.getInstructionState() + "\n";
        PlatForm.platForm.logInfo.append(outStr);
        System.out.println(outStr);
    }
}