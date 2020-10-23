package com.os;

import java.text.SimpleDateFormat;
import java.util.*;

public class Clock extends Thread {
    Timer timer;
    Date date;
    TimerTask TimeFunc;

    public Clock() {
        timer = new Timer();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeFunc = new TimerTask() {
            @Override
            public void run() {
                System.out.println("当前系统时间为:" + date);
            }
        };
    }

    public void startTimer() {
        date = new Date();
        System.out.println("start time:" + date);
        timer.schedule(TimeFunc, date, 1000);// 设定指定的时间time,此处为2000毫秒
    }
}
