package com.handle;

import com.os.PCB;
import com.os.PCBPool;

/**
 * @program: Process_Sheduling
 * @description: 屏幕显示模块
 * @author: zach
 * @create: 2020-10-29-00:29
 **/
public class ScreenOutput extends BaseHandle {
    public ScreenOutput(int length) {
        super(length);
    }

    public void run() {
        try {
            System.out.println("线程：---------屏幕输出线程开始运行");
            sleep(this.BREAK_LENGTH);
            //产生硬件终端信息号，阻塞队列 2 的队头节点出队，进入就绪队列
            if (PCBPool.pcbPool.isOutPutBlockQueueEmpty()) {
                System.out.println("正在将输出阻塞队列对头出列，加入就绪队列");
                PCB back2ready = PCBPool.pcbPool.getOutputBlockQueue().get(0);
                System.out.println("输出阻塞队列对头：" + back2ready.getProID());
                PCBPool.pcbPool.getOutputBlockQueue().remove(back2ready);
                PCBPool.pcbPool.AddProcess2ReadyQueue(back2ready);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
