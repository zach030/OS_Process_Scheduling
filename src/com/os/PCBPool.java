package com.os;


import com.configs.PCBStatus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

//PCB管理池
public class PCBPool{
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

    public void readAllPCB() {

    }

}
