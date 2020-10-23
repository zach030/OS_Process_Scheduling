package com.queue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.os.*;
import com.os.Process;

//就绪队列
public class ReadyQueue extends BaseQueue {
    ArrayList<Process> readyQueue = new ArrayList<>();

    public void AddProcess(Process process) {
        readyQueue.add(process);
        this.num = readyQueue.indexOf(process);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = df.format(System.currentTimeMillis());
    }
}
