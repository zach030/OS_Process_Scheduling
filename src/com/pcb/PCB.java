package com.pcb;

public class PCB{

    private String pid;//进程号
    private String state;//进程状态：就绪，运行，等待，待创建

    public void setPid(String pid){this.pid = pid;}
    public String getPid(){return this.pid;}

    public void setState(String state){this.state = state;}
    public String getState(){return this.state;}
}