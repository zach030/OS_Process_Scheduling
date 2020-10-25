package com.os;

import com.queue.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class PCBPool implements Runnable {
    ArrayList<PCB> pcList = new ArrayList<>();
    //从文件in.txt读入pcb，放入pcb池子中
    ReadyQueue readyQueue;
    InputBlockQueue inputBlockQueue;
    OutputBlockQueue outputBlockQueue;
    PVBlockQueue pvBlockQueue;

    public void displayProcess() {
        for (PCB p : pcList) {
            System.out.println("Process in pool now are:");
            System.out.println("id=" + p.ProID + ",priority=" + p.Priority + ",intime=" + p.InTimes + ",num is=" + p.InstrucNum);
        }
    }

    public void readPCB() {
        try {
            FileReader fr = new FileReader("./src/com/testdata/19318123-jobs-input.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tmp = line.split(",");
                PCB p = new PCB();
                p.setProID(Integer.parseInt(tmp[0]));
                p.setPriority(Integer.parseInt(tmp[1]));
                p.setInTimes(Integer.parseInt(tmp[2]));
                p.setInstrucNum(Integer.parseInt(tmp[3]));
                pcList.add(p);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        int now = calendar.get(Calendar.SECOND);
        System.out.println("now second is" + now);
        for (PCB p : pcList) {
            System.out.println("process in time:" + p.InTimes);
            if (p.InTimes == now) {

                System.out.println("进程:" + p.ProID + "进入就绪队列");
                readyQueue.AddProcess(p);
            }
        }
    }
}
