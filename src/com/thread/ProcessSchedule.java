package com.thread;

/*
进程调度模块用一个单独的线程或计时器实现。需要重点注意的是：不是每次发生时
钟中断都要进行进程调度。
◆实现时间片轮转算法。申请优秀的学生需实现时间片轮转+静态优先数调度算法。
◆时间片计算：每 2 秒为 1 个时间片，发生一次进程切换。
◆时间片轮转+静态优先数调度算法：采用非抢占式。优先数越小，优先级越高。
* */
//进程调度
public class ProcessSchedule implements Runnable{
    @Override
    public void run() {

    }
    //时间片轮转算法
    public static void roundRobinSwitchProcess(){

    }
}
