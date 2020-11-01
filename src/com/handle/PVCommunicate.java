package com.handle;

import com.os.PCB;
import com.os.PCBPool;

/**
 * @program: Process_Sheduling
 * @description: PV通信模块
 * @author: zach
 * @create: 2020-10-29-00:31
 **/
public class PVCommunicate extends BaseHandle {

    public PVCommunicate(int length) {
        super(length);
    }

    public void run() {
        try {
            System.out.println("线程：---------PV通信线程开始运行");
            sleep(BREAK_LENGTH);
            //同时关中断，假设 2 秒以后产生唤醒信号，阻塞队列队 3 的头节点出队，进入就绪队列
            if (PCBPool.pcbPool.isPVBlockQueueEmpty()) {
                System.out.println("正在将pv阻塞队列对头出列，加入就绪队列");
                PCB back2ready = PCBPool.pcbPool.getPvBlockQueue().get(0);
                System.out.println("pv阻塞队列对头："+back2ready.getProID());
                PCBPool.pcbPool.getPvBlockQueue().remove(back2ready);
                PCBPool.pcbPool.AddProcess2ReadyQueue(back2ready);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
