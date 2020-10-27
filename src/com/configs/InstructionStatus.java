package com.configs;
//指令类型
//0 表示用户态计算操作；1 表示键盘输入操作,2 表示 PV 操作；3 屏幕显示
public enum InstructionStatus {
    USERMODE_CALC, KEYBOARD_INPUT, PV_OPERATION, SCREEN_DISPLAY
}