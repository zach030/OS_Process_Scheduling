package com.os;

public class CPU {
    private class PC {

    }
}
/*
CPU 可抽象为一个类。关键寄存器可抽象为子类或类的属性，至少包括：程序计数器
（PC）、指令寄存器（IR）、状态寄存器（PSW）等，寄存器内容的表示方式自行设计。
CPU 寄存器现场保护、现场恢复操作可封装为进程调度类的方法，供进程切换、CPU
模式切换方法调用。
*/