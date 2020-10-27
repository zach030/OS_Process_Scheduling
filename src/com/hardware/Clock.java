package com.hardware;

import java.text.SimpleDateFormat;
import java.util.*;

/*设置 1 秒执行 1 条指令，也就是假设计算机 1 秒(s)发生一次时钟硬件中断*/
//线程 1：一秒执行一条指令，也就是假设计算机一秒发生一次时钟硬件中断。
//线程 2：5s 检查一次生成作业文件中是否有新请求。
//线程 3：5s 进行一次检查阻塞队列，并执行进程唤醒原语
public class Clock extends Thread {
    Timer timer;
    Date date;
    TimerTask TimeFunc;
    public static int BREAKE_TIME = 1;
    boolean ifInterrupt;//用来判断是否被中断

    public Clock() {
        timer = new Timer();
        date = new Date();
    }

    public void startTimer() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("本系统开始时间为:" + calendar.get(Calendar.SECOND));
        timer.schedule(TimeFunc = new TimerTask() {
            @Override
            public void run() {
                date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("当前系统时间为:" + df.format(date));
            }
        }, date, 1000);// 设定指定的时间time,此处为1000毫秒
    }
}