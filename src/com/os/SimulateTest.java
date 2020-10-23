package com.os;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.queue.*;
import com.thread.*;

public class SimulateTest {
    public static void main(String[] args) throws IOException {
        ScheduledExecutorService pool = (ScheduledExecutorService) Executors.newScheduledThreadPool(5);
        pool.scheduleWithFixedDelay(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        },0,500,TimeUnit.MILLISECONDS);

        //1、计时器线程启动
        Clock clock = new Clock();
        clock.startTimer();
        //2、读job
        PCBPool pcbPool = new PCBPool();
        pcbPool.readPCB();
        pcbPool.displayProcess();
        //3、队列进程启动
        InputBlockQueue ibq = new InputBlockQueue();
        OutputBlockQueue obq = new OutputBlockQueue();
        PVBlockQueue pvq = new PVBlockQueue();
        ReadyQueue req = new ReadyQueue();
        //
        pool.schedule(pcbPool,1, TimeUnit.SECONDS);
    }
}

