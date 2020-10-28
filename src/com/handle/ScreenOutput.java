package com.handle;

/**
 * @program: Process_Sheduling
 * @description: 屏幕显示模块
 * @author: zach
 * @create: 2020-10-29-00:29
 **/
public class ScreenOutput extends BaseHandle {
    public static ScreenOutput screenOutput = new ScreenOutput(3000);

    public ScreenOutput(int length) {
        super(length);
    }

    public void run() {
        try {
            System.out.println("screen output thread start");
            sleep(this.BREAK_LENGTH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
