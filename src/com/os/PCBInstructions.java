package com.os;

import com.status.InstructionStatus;

/**
 * @program: Process_Sheduling
 * @description: 进程指令
 * @author: zach
 * @create: 2020-10-27-23:11
 **/
public class PCBInstructions {
    private int instructionID;            //指令编号
    private InstructionStatus instructionState;        //指令类型

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
}