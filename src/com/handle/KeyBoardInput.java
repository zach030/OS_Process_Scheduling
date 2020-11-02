package com.handle;


import com.constant.ConstantTime;
import com.hardware.CPU;
import com.os.PCB;
import com.os.PCBPool;
import com.os.SystemController;
import com.status.InstructionStatus;
import com.status.ScheduleStatus;

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
            sleep(this.BREAK_LENGTH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程：---------键盘输入线程开始运行");
            if (PCBPool.pcbPool.isInputBlockQueueEmpty()) {
                PCB back2ready = PCBPool.pcbPool.getInputBlockQueue().get(0);
                System.out.println("正在将输入阻塞队列对头出列，加入就绪队列");
                back2ready.wakeProcess(PCBPool.pcbPool.getInputBlockQueue());
            }
        }
    }
}
