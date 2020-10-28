package com.os;


import com.status.PCBStatus;

import java.util.*;

//PCB管理池
public class PCBPool {
    public static PCBPool pcbPool = new PCBPool();
    //所有的PCB
    public ArrayList<PCB> allPcbList = new ArrayList<>();
    //PCB池状态MAP
    HashMap<PCB, PCBStatus> pcbStatusHashMap = new HashMap<>();
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

    //将某PCB从池中删除-->标记不可用
    public void deletePCBFromPool(PCB pcb) {
        pcbStatusHashMap.put(pcb, PCBStatus.UNUSABLE);
    }

    public void addPCB2Pool(PCB pcb){
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

}
