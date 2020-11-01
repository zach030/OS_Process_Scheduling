package com.constant;

/**
 * @program: Process_Sheduling
 * @description: 系统时间
 * @author: zach
 * @create: 2020-10-29-00:07
 **/
public class ConstantTime {
    public static ConstantTime constantTime = new ConstantTime();
    //系统时间
    public static int SYSTEM_TIME = 0;
    //中断间隔时间
    public static final int BREAK_TIME = 1000;
    //时间片长度
    public static final int TIME_SLICE = 2000;
    //键盘输入线程间隔时间
    public static final int KEYBOARD_INPUT_INTERVAL = 4000;
    //屏幕输出线程间隔时间
    public static final int SCREEN_OUTPUT_INTERVAL = 3000;
    //PV通信线程间隔时间
    public static final int PV_COMMUNICATE_INTERVAL = 2000;

    public static int getSystemTime() {
        return SYSTEM_TIME;
    }

    public void SystemAddTime() {
        SYSTEM_TIME += BREAK_TIME;
    }
}
