package com.handle;

/**
 * @program: Process_Sheduling
 * @description: 处理方法模块
 * @author: zach
 * @create: 2020-10-29-00:46
 **/
public class HandleModule implements Runnable{
    public static HandleModule handleModule = new HandleModule();
    @Override
    public void run() {
        KeyBoardInput.keyBoardInput.start();
        PVCommunicate.pvCommunicate.start();
        ScreenOutput.screenOutput.start();
    }
}
