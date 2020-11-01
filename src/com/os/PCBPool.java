package com.os;


import com.constant.ConstantTime;
import com.handle.KeyBoardInput;
import com.handle.PVCommunicate;
import com.handle.ScreenOutput;
import com.hardware.CPU;
import com.status.PCBStatus;
import org.jetbrains.annotations.NotNull;

import java.util.*;

//PCB管理池
public class PCBPool {
    //实例PCB池对象
    public static PCBPool pcbPool = new PCBPool();
    //所有的PCB队列
    private ArrayList<PCB> allPcbList = new ArrayList<>();
    //PCB池状态MAP
    HashMap<PCB, PCBStatus> pcbStatusHashMap = new HashMap<>();
    //运行PCB队列
    ArrayList<PCB> runningQueue = new ArrayList<>();
    //就绪队列
    ArrayList<PCB> readyQueue = new ArrayList<>();
    //键盘输入阻塞队列
    ArrayList<PCB> inputBlockQueue = new ArrayList<>();
    //屏幕输出阻塞队列
    ArrayList<PCB> outputBlockQueue = new ArrayList<>();
    //PV阻塞队列
    ArrayList<PCB> pvBlockQueue = new ArrayList<>();

    //统计可用PCB数
    public int getUsablePCBNum() {
        int count = 0;
        for (Map.Entry<PCB, PCBStatus> entry : pcbStatusHashMap.entrySet()) {
            if (entry.getValue() == PCBStatus.USABLE) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<PCB> getUsAblePCBList() {
        ArrayList<PCB> usAblePCBList = new ArrayList<>();
        for (Map.Entry<PCB, PCBStatus> entry : pcbStatusHashMap.entrySet()) {
            if (entry.getValue() == PCBStatus.USABLE) {
                usAblePCBList.add(entry.getKey());
            }
        }
        return usAblePCBList;
    }

    public ArrayList<PCB> getInputBlockQueue() {
        return inputBlockQueue;
    }

    public void setInputBlockQueue(ArrayList<PCB> inputBlockQueue) {
        this.inputBlockQueue = inputBlockQueue;
    }

    public ArrayList<PCB> getPvBlockQueue() {
        return pvBlockQueue;
    }

    public void setPvBlockQueue(ArrayList<PCB> pvBlockQueue) {
        this.pvBlockQueue = pvBlockQueue;
    }

    public ArrayList<PCB> getOutputBlockQueue() {
        return outputBlockQueue;
    }

    public void setOutputBlockQueue(ArrayList<PCB> outputBlockQueue) {
        this.outputBlockQueue = outputBlockQueue;
    }

    public ArrayList<PCB> getReadyQueue() {
        return readyQueue;
    }

    public void setReadyQueue(ArrayList<PCB> readyQueue) {
        this.readyQueue = readyQueue;
    }

    public ArrayList<PCB> getRunningQueue() {
        return runningQueue;
    }

    public void setRunningQueue(ArrayList<PCB> runningQueue) {
        this.runningQueue = runningQueue;
    }

    public boolean isReadyQueueEmpty() {
        return this.readyQueue.isEmpty();
    }

    public boolean isRunningQueueEmpty(){
        return this.runningQueue.isEmpty();
    }
    public boolean isInputBlockQueueEmpty() {
        return this.inputBlockQueue.isEmpty();
    }

    public boolean isOutPutBlockQueueEmpty() {
        return this.outputBlockQueue.isEmpty();
    }

    public boolean isPVBlockQueueEmpty() {
        return this.pvBlockQueue.isEmpty();
    }

    public ArrayList<PCB> getAllPcbList() {
        return allPcbList;
    }

    public void setAllPcbList(@NotNull ArrayList<PCB> allPcbList) {
        this.allPcbList = allPcbList;
        for (PCB pcb : allPcbList) {
            //初始化 PCB status map
            this.pcbStatusHashMap.put(pcb, PCBStatus.USABLE);
        }
    }

    //将某PCB从池中删除-->标记不可用
    public void deletePCBFromPool(PCB pcb) {
        pcbStatusHashMap.put(pcb, PCBStatus.UNUSABLE);
    }

    public void addPCB2Pool(PCB pcb) {
        allPcbList.add(pcb);
    }

    public void AddProcess2ReadyQueue(PCB pcb) {
        readyQueue.add(pcb);
    }

    public void AddProcess2InputBlockQueue(PCB pcb) {
        inputBlockQueue.add(pcb);
    }

    public void AddProcess2OutputBlockQueue(PCB pcb) {
        outputBlockQueue.add(pcb);
    }

    public void AddProcess2PVBlockQueue(PCB pcb) {
        pvBlockQueue.add(pcb);
    }

    public PCBPool() {
        //TODO 设置所有的PCB为可使用状态
    }

    public void displayAllPCB() {
        for (PCB p : allPcbList) {
            System.out.println("Process in pool now are:");
            //System.out.println("id=" + p.ProID + ",priority=" + p.Priority + ",intime=" + p.InTimes + ",num is=" + p.InstructionsNum);
        }
    }

    public void checkPCBInTime2GetReady() {
        ArrayList<PCB> usAblePCBList = getUsAblePCBList();
        int cpuTime = CPU.cpu.clock.getClockTime();
        for (PCB pcb : usAblePCBList) {
            if (pcb.getInClockTime() == cpuTime) {
                //System.out.println(pcb.getInClockTime());
                //如果到了进程的intime,则创建进程
                pcb.createProcess();
                System.out.println("发现PCB，进程ID为：" + pcb.getProID() + "，已加入就绪队列");
            }
        }
    }


    public void NormalInstrucRun() {
        System.out.println("进程正常调度");
        CPU.cpu.updateCpuModeByInstruction();
    }

    public void InputInstrucRun() {
        KeyBoardInput keyBoardInput = new KeyBoardInput(ConstantTime.KEYBOARD_INPUT_INTERVAL);
        keyBoardInput.start();
    }

    public void OutPutInstrucRun() {
        ScreenOutput screenOutput = new ScreenOutput(ConstantTime.SCREEN_OUTPUT_INTERVAL);
        screenOutput.start();
    }

    public void PVCommunicateInstrucRun() {
        PVCommunicate pvCommunicate = new PVCommunicate(ConstantTime.PV_COMMUNICATE_INTERVAL);
        pvCommunicate.start();
    }

}
