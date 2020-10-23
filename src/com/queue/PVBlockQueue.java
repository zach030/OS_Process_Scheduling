package com.queue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

//PV 操作控制阻塞队列
public class PVBlockQueue extends BaseQueue{
    ArrayList<Process> pvBlockQueue = new ArrayList<>();
    public void AddProcess(Process process) {
        pvBlockQueue.add(process);
        this.num = pvBlockQueue.indexOf(process);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = df.format(System.currentTimeMillis());
    }
}
