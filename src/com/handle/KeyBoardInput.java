package com.handle;


/**
 * @program: Process_Sheduling
 * @description: 键盘输入模块
 * @author: zach
 * @create: 2020-10-29-00:28
 **/
public class KeyBoardInput extends BaseHandle {
    public static KeyBoardInput keyBoardInput = new KeyBoardInput(4000);

    public KeyBoardInput(int length) {
        super(length);
    }

    public void run() {
        try {
            System.out.println("keyboard input thread start");
            sleep(this.BREAK_LENGTH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
