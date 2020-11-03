package com.moduleThread;

/**
 * @program: Process_Sheduling
 * @description: 输入输出及通信处理模块抽象类
 * @author: zach
 * @create: 2020-10-29-00:27
 **/
public abstract class BaseHandle extends Thread {
    public int BREAK_LENGTH;

    public BaseHandle(int length) {
        this.BREAK_LENGTH = length;
    }
}
