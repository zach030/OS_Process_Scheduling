package com.queue;
import java.util.*;
//队列元素基类
public abstract class BaseQueue extends Thread{
    int num;//队列编号
    String date;//进入队列时间
    public void AddProcess(){};
}
