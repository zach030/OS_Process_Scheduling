package com.handle;

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
            if (!PCBPool.pcbPool.isOutPutBlockQueueEmpty()) {
                PCBPool.pcbPool.AddProcess2ReadyQueue(PCBPool.pcbPool.getOutputBlockQueue().get(0));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
