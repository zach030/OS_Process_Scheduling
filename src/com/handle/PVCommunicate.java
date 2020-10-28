package com.handle;

/**
 * @program: Process_Sheduling
 * @description: PV通信模块
 * @author: zach
 * @create: 2020-10-29-00:31
 **/
public class PVCommunicate extends BaseHandle{
    public static PVCommunicate pvCommunicate = new PVCommunicate(2000);

    public PVCommunicate(int length) {
        super(length);
    }

    public void run() {
        try {
            System.out.println("pv communicate thread start");
            sleep(this.BREAK_LENGTH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
