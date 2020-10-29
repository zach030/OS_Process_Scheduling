package com.os;

import com.constant.ConstantTime;
import com.hardware.CPU;
import com.hardware.Clock;
import com.status.ClockStatus;
import com.status.ScheduleStatus;

/*
进程调度模块用一个单独的线程或计时器实现。需要重点注意的是：不是每次发生时
钟中断都要进行进程调度。
◆实现时间片轮转算法。申请优秀的学生需实现时间片轮转+静态优先数调度算法。
◆时间片计算：每 2 秒为 1 个时间片，发生一次进程切换。
◆时间片轮转+静态优先数调度算法：采用非抢占式。优先数越小，优先级越高。
* */
//进程调度
public class Schedule extends Thread {
    public static Schedule schedule = new Schedule();

    ScheduleStatus scheduleStatus;
    int wait4Schedule = 0;
    public Schedule(){

    }



    @Override
    public void run() {
        //TODO 调度功能:
        //1 对时钟中断进行计数,5次则进行读job

        while (true) {
            try {
                System.out.println("schedule start");
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (CPU.cpu.clock.getClockStatus()== ClockStatus.Interrupt){
                //如果此时处于中断,则进入调度
                //1、保存处理机的现场信息。
                //2、按某种算法选取进程。
                //3、把处理器分配给进程。

            }
        }
    }

    public void Protect(PCB p) {
        //TODO 由pcb设置当前的psw，currenttime

    }

    ;//CPU寄存器现场保护

    public void Recovery(PCB p) {
        //TODO 将当前pcb赋值为p
        CPU.cpu.setRunningPCB(p);
        //将cpu的类变量都用pcb赋值，恢复
    }
    //时间片轮转算法
    public static void roundRobinSwitchProcess() {

    }

//    public void LowLevelScheduling()
//    {
//        //读PCB,将作业调入就绪队列
//        //低级调度
//        ConstantTime.constantTime.SystemAddTime();	 //系统时间自增
//        if(wait_time<=0)
//        {
//            if_wait=false;
//            if(!ProcessModule.process_module.waiting_queue.isEmpty())
//            {
//                PCB t=ProcessModule.process_module.waiting_queue.get(0);
//                MainUI.main_ui.run_info+="等待时间满，进程"+t.GetPid()+"退出等待！\n";
//                if(t.if_in_p==true)
//                {
//                    t.if_in_p=false;
//                    if(t.if_p_success)
//                    {
//                        t.AddCurrentInstructionNo();
//                        t.if_p_success=false;
//                    }
//                }
//                else
//                    t.AddCurrentInstructionNo();
//                t.ins_runtime=0;
//                if_wait=false;
//                t.SetRuntime(0); 		//刷新已运行时间
//                t.RefreshCounter(); 	//刷新时间片余额
//                ProcessModule.process_module.TransferProcessToReadyQueue(t,false);
//            }
//        }
//        //第一步，检测当前是否正处于等待态，如果处于，则CPU等待
//        if(scheduleStatus==ScheduleStatus.Wait)
//        {
//            //当前CPU处于等待态，继续等待，退出低级调度
//            PCB t=PCBPool.pcbPool.readyQueue.get(0);
//            t.AddRuntime(); 		//模拟已经运行过一次
//            t.AddTotalRuntime(); 	//总运行时间增加
//            t.ins_runtime+=kernel.INTERRUPTION_INTERVAL;
//            //处于等待态，则检查等待时间
//            return;
//        }
//        //第二步，刷新active和expired指针
//        ProcessModule.process_module.RefreshActiveExpired();
//        //第三步，查看当前运行队列是否还有进程
//        if(ProcessModule.process_module.IsRunningQueueEmpty()==true)
//        {
//            MainUI.main_ui.run_info+="运行队列中没有进程，进行重新调度--";
//            for(int i=0;i<140;i++)
//            {
//                if(ProcessModule.process_module.IsRunningQueueEmpty()==false)
//                    break;
//                if(!ProcessModule.process_module.ready_queue[ProcessModule.process_module.GetActivePoint()][i].isEmpty())
//                {
//                    PCB t=ProcessModule.process_module.ready_queue[ProcessModule.process_module.GetActivePoint()][i].get(0);
//                    ProcessModule.process_module.TransferProcessToRunningQueue(t);
//                    MainUI.main_ui.run_info+="调入进程"+t.GetPid()+"--";
//                }
//            }
//        }
//        else
//            MainUI.main_ui.run_info+="队列中有进程运行--";
//
//        //第四步，检测当前正准备执行的指令所在的页是否在内存中
//        if(ProcessModule.process_module.running_queue.isEmpty())
//        {
//            MainUI.main_ui.run_info+="当前没有可运行进程！\n";
//            return;
//        }
//        PCB run=ProcessModule.process_module.running_queue.get(0);
//        //CPU恢复现场
//        CPU.cpu.Recovery(run);
//        //检测是否发生缺页中断
//        MainUI.main_ui.run_info+="运行指令--";
//        if(ProcessModule.process_module.IfPageInMemory(run.page_table[run.GetCurrentInstructionNo()/(kernel.SINGLE_PAGE_SIZE/kernel.SINGLE_INSTRUCTION_SIZE)][1])==false)
//        {
//            //如果所需要的页面不在内存中，则发生缺页中断
//            //缺页中断的处理
//            MainUI.main_ui.run_info+="发生缺页中断--当前缺页号："+run.GetCurrentInstructionNo()/(kernel.SINGLE_PAGE_SIZE/kernel.SINGLE_INSTRUCTION_SIZE)+"-"+run.page_table[run.GetCurrentInstructionNo()/(kernel.SINGLE_PAGE_SIZE/kernel.SINGLE_INSTRUCTION_SIZE)][1]+"，";
//            ProcessModule.process_module.SolveMissingPage(run, run.page_table[run.GetCurrentInstructionNo()/(kernel.SINGLE_PAGE_SIZE/kernel.SINGLE_INSTRUCTION_SIZE)][1]);
//            MainUI.main_ui.run_info+="已进行调页处理--";
//            MainUI.main_ui.run_info+="当前页表项："+run.GetCurrentInstructionNo()/(kernel.SINGLE_PAGE_SIZE/kernel.SINGLE_INSTRUCTION_SIZE)+"-"+run.page_table[run.GetCurrentInstructionNo()/(kernel.SINGLE_PAGE_SIZE/kernel.SINGLE_INSTRUCTION_SIZE)][1]+"\n";
//            PageModule.page_module.LRUVisitOnePage(run.page_table[run.GetCurrentInstructionNo()/(kernel.SINGLE_PAGE_SIZE/kernel.SINGLE_INSTRUCTION_SIZE)][1]);
//        }
//        else
//        {
//            MainUI.main_ui.run_info+="没有发生缺页中断--当前访问页表："+run.GetCurrentInstructionNo()/(kernel.SINGLE_PAGE_SIZE/kernel.SINGLE_INSTRUCTION_SIZE)+"-"+run.page_table[run.GetCurrentInstructionNo()/(kernel.SINGLE_PAGE_SIZE/kernel.SINGLE_INSTRUCTION_SIZE)][1]+"\n";
//            PageModule.page_module.LRUVisitOnePage(run.page_table[run.GetCurrentInstructionNo()/(kernel.SINGLE_PAGE_SIZE/kernel.SINGLE_INSTRUCTION_SIZE)][1]);
//        }
//        //MMU的地址变换，取指令
//        MainUI.main_ui.run_info+="MMU进行地址变换--PC指针：0x"+String.format("%04x", CPU.cpu.GetPC()).toUpperCase()+
//                "H，转换后的实地址：0x"+String.format("%04x", CPU.cpu.mm.VirtualAddressToRealAddress(run, (short) CPU.cpu.GetPC())).toUpperCase()+"H\n";
//        //执行指令
//        if(run.getInstructions().size()<=run.GetCurrentInstructionNo())
//        {
//            MainUI.main_ui.run_info+="\n进程"+run.GetPid()+"运行完毕！\n";
//            run.ProcessCancel();		//调用进程撤销原语
//            return;
//        }
//        int current_ins_type=kernel.GetInstructionType(run.getInstructions().get(run.GetCurrentInstructionNo()));
//        MainUI.main_ui.run_info+="当前执行指令："+run.getInstructions().get(run.GetCurrentInstructionNo())+"--";
//        //每一种指令的不同运行过程
//        run.RefreshTimeslice(); 	//刷新时间片
//        run.SetCounter(0); 			//设置已用时间片
//        CPU.cpu.mm.ClearTLB(); 		//清空快表
//        switch(current_ins_type)
//        {
//            case 1:
//                MainUI.main_ui.run_info+="指令类型：系统调用--";
//                ProcessModule.process_module.RunType1(run);
//                break;
//            case 2:
//                MainUI.main_ui.run_info+="指令类型：P指令--";
//                ProcessModule.process_module.RunType2(run);
//                break;
//            case 3:
//                MainUI.main_ui.run_info+="指令类型：V指令--";
//                ProcessModule.process_module.RunType3(run);
//                break;
//            case 4:
//                MainUI.main_ui.run_info+="指令类型：申请资源--";
//                ProcessModule.process_module.RunType4(run);
//                break;
//            case 5:
//                MainUI.main_ui.run_info+="指令类型：释放资源--";
//                ProcessModule.process_module.RunType5(run);
//                break;
//            case 6:
//                MainUI.main_ui.run_info+="指令类型：普通指令--";
//                ProcessModule.process_module.RunType6(run);
//                break;
//        }
//        //检测是否运行完毕，如果运行完毕，调用撤销原语
//        if(ProcessModule.process_module.IfRunOver(run)==true)
//        {
//            MainUI.main_ui.run_info+="\n进程"+run.GetPid()+"运行完毕！\n";
//            run.ProcessCancel();		//调用进程撤销原语
//        }
//        //检测时间片是否用完，如果完毕，则进入就绪队列
//        if(ProcessModule.process_module.IfTimeSliceOver(run)==true&&this.if_wait!=true)
//        {
//            MainUI.main_ui.run_info+="\n进程"+run.GetPid()+"时间片到期！\n";
//            //设置counter为0
//            run.SetCounter(0);
//            run.SetRuntime(0);
//            //动态调整优先级
//            run.RefreshPriority();
//            //将该进程放入就绪队列
//            ProcessModule.process_module.TransferProcessToReadyQueue(run, false);
//        }
//        MainUI.main_ui.run_info+="\n";
//    }
}
