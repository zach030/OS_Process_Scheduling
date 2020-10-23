package com.queue;


import java.text.SimpleDateFormat;
import java.util.ArrayList;

//键盘输入阻塞队列
public class InputBlockQueue extends BaseQueue {
    ArrayList<Process> inputBlockQueue = new ArrayList<>();

    public void AddProcess(Process process) {
        inputBlockQueue.add(process);
        this.num = inputBlockQueue.indexOf(process);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = df.format(System.currentTimeMillis());
    }
}
