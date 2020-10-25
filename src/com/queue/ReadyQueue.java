package com.queue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.os.PCB;

//就绪队列
public class ReadyQueue extends BaseQueue {
    ArrayList<PCB> readyQueue = new ArrayList<>();

    public void AddProcess(PCB PCB) {
        readyQueue.add(PCB);
        this.num = readyQueue.indexOf(PCB);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = df.format(System.currentTimeMillis());
    }
}
