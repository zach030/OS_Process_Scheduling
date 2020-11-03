package com.moduleThread;

import com.config.ConstantTime;
import com.hardware.CPU;
import com.os.PCB;
import com.os.PCBPool;
import com.config.InstructionStatus;

/**
 * @program: Process_Sheduling
 * @description: PV通信模块
 * @author: zach
 * @create: 2020-10-29-00:31
 **/
public class PVCommunicate extends BaseHandle {

    public static PVCommunicate pvCommunicate = new PVCommunicate(ConstantTime.PV_COMMUNICATE_INTERVAL);

    public PVCommunicate(int length) {
        super(length);
    }

    public void run() {
        System.out.println("线程：---------PV线程开始运行");
        while (true) {
            try {
                if (CPU.currentInstrucState == InstructionStatus.PV_OPERATION) {
                    if (PCBPool.pcbPool.isPVBlockQueueEmpty()) {
                        sleep(BREAK_LENGTH);
                        PCB back2ready = PCBPool.pcbPool.getPvBlockQueue().get(0);
                        System.out.println("正在将PV阻塞队列对头出列，加入就绪队列");
                        back2ready.wakeProcess(PCBPool.pcbPool.getPvBlockQueue());
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
