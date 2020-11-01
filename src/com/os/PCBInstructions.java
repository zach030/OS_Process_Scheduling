package com.os;

import com.status.InstructionStatus;
import org.w3c.dom.ls.LSOutput;

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
        System.out.println("本指令编号：" + this.getInstructionID() + "，指令类型：" + this.getInstructionState());
    }
}