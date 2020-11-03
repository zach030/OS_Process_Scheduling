package com.moduleThread;

import com.config.ConstantTime;
import com.hardware.CPU;
import com.os.PCB;
import com.os.PCBPool;
import com.config.InstructionStatus;

/**
 * @program: Process_Sheduling
 * @description: 键盘输入模块
 * @author: zach
 * @create: 2020-10-29-00:28
 **/
public class KeyBoardInput extends BaseHandle {

    public static KeyBoardInput keyBoardInput = new KeyBoardInput(ConstantTime.KEYBOARD_INPUT_INTERVAL);

    public KeyBoardInput(int length) {
        super(length);
    }

    public void run() {
        System.out.println("线程：---------INPUT线程开始运行");
        while (true) {
            try {
                if (CPU.currentInstrucState == InstructionStatus.KEYBOARD_INPUT) {
                    if (PCBPool.pcbPool.isInputBlockQueueEmpty()) {
                        sleep(this.BREAK_LENGTH);
                        PCB back2ready = PCBPool.pcbPool.getInputBlockQueue().get(0);
                        System.out.println("正在将输入阻塞队列对头出列，加入就绪队列");
                        back2ready.wakeProcess(PCBPool.pcbPool.getInputBlockQueue());
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
