package com.handle;


import com.os.PCBPool;

/**
 * @program: Process_Sheduling
 * @description: 键盘输入模块
 * @author: zach
 * @create: 2020-10-29-00:28
 **/
public class KeyBoardInput extends BaseHandle {

    public KeyBoardInput(int length) {
        super(length);
    }

    public void run() {
        try {
            System.out.println("线程：---------键盘输入线程开始运行");
            sleep(this.BREAK_LENGTH);
            //产生硬件终端信息号，阻塞队列 1 的队头节点出对，进入就绪队列
            if (!PCBPool.pcbPool.isInputBlockQueueEmpty()){
                PCBPool.pcbPool.AddProcess2ReadyQueue(PCBPool.pcbPool.getInputBlockQueue().get(0));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
