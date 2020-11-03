package com.moduleThread;

import com.config.ConstantTime;
import com.hardware.CPU;
import com.os.PCB;
import com.os.PCBPool;
import com.config.InstructionStatus;

/**
 * @program: Process_Sheduling
 * @description: 屏幕显示模块
 * @author: zach
 * @create: 2020-10-29-00:29
 **/
public class ScreenOutput extends BaseHandle {

    public static ScreenOutput screenOutput = new ScreenOutput(ConstantTime.SCREEN_OUTPUT_INTERVAL);

    public ScreenOutput(int length) {
        super(length);
    }

    public void run() {
        System.out.println("线程：---------OUTPUT线程开始运行");
        while (true) {
            try {
                if (CPU.currentInstrucState == InstructionStatus.SCREEN_DISPLAY) {
                    //产生硬件终端信息号，阻塞队列 2 的队头节点出队，进入就绪队列
                    if (PCBPool.pcbPool.isOutPutBlockQueueEmpty()) {
                        sleep(this.BREAK_LENGTH);
                        PCB back2ready = PCBPool.pcbPool.getOutputBlockQueue().get(0);
                        System.out.println("正在将OutPut阻塞队列对头出列，加入就绪队列");
                        back2ready.wakeProcess(PCBPool.pcbPool.getOutputBlockQueue());
                    }
                    CPU.currentInstrucState = null;
                } else {
                    sleep(ConstantTime.BREAK_TIME);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
