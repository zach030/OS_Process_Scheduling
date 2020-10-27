package com.os;

import java.util.ArrayList;

//进程调度类
public class ProcessController {

    ArrayList<PCB> ReadyQueue = new ArrayList<>(); //就绪队列
    ArrayList<PCB> RunningQueue = new ArrayList<>(); //运行队列
    ArrayList<PCB> BlockQueue = new ArrayList<>(); //阻塞队列


    //TODO 调度函数
    //检查当前运行指令的类型
    //去PCBPOOL里面进行对应类型的操作
    //调用PCB里的进程原语
}
