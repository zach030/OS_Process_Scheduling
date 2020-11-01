package com.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: Process_Sheduling
 * @description: 调度算法测试
 * @author: zach
 * @create: 2020-11-01-16:47
 **/
public class RR {
    private int mProcessCount; //进程数
    private Queue<Process> mReadyQueue; //就绪队列，存放“待运行的进程
    private Queue<Process> mUnreachQueue; //存放“到达时间未到的进程”
    private int mTimeSlice; //时间片

    private Queue<Process> mExecutedQueue; //执行完毕的进程队列
    private double mTotalWholeTime = 0.0;
    private double mTotalWeightWholeTime = 0.0;

    private RR(int processCount, Queue<Process> processQueue, int timeSlice) {
        this.mProcessCount = processCount;
        this.mUnreachQueue = processQueue;
        mReadyQueue = new LinkedBlockingQueue<>();
        this.mTimeSlice = timeSlice;
        mExecutedQueue = new LinkedList<>();
    }

    /**
     * RR 算法实现
     */
    public void RRAlgorithm() {
        mReadyQueue.add(mUnreachQueue.poll());
        Process currProcess = mReadyQueue.poll();
        //第一个进程执行
        int currTime = executeProcess(currProcess, 0);

        while (!mReadyQueue.isEmpty() || !mUnreachQueue.isEmpty()) {
            //把所有“到达时间”达到的进程加入运行队列头部
            while (!mUnreachQueue.isEmpty()) {
                if (mUnreachQueue.peek().getArrivalTime() <= currTime) {
                    mReadyQueue.add(mUnreachQueue.poll());
                } else {
                    break;
                }
            }

            if (currProcess.getRemainServiceTime() > 0) mReadyQueue.add(currProcess);
            //运行队列不为空时
            if (!mReadyQueue.isEmpty()) {
                currProcess = mReadyQueue.poll();
                currTime = executeProcess(currProcess, currTime);
            } else {
                //当前没有进程执行，但还有进程为到达，时间直接跳转到到达时间
                currTime = mUnreachQueue.peek().getArrivalTime();
            }
        }
    }

    private int executeProcess(Process currProcess, int currTime) {
        if (currProcess.getRemainServiceTime() - mTimeSlice <= 0) {
            //当前进程在这个时间片内能执行完毕
            showExecuteMessage(currTime, currTime += currProcess.getRemainServiceTime(), currProcess.getName());
            currProcess.setFinishTime(currTime);
            currProcess.setRemainServiceTime(0);
            //对周转时间等进行计算
            calculeteWholeTime(currProcess);
            calculateWeightWholeTime(currProcess);
            mTotalWholeTime += currProcess.getWholeTime();
            mTotalWeightWholeTime += currProcess.getWeightWholeTime();
            mExecutedQueue.add(currProcess);
        } else {
            //不能执行完毕
            showExecuteMessage(currTime, currTime += mTimeSlice, currProcess.getName());
            currProcess.setRemainServiceTime(currProcess.getRemainServiceTime() - mTimeSlice);
        }
        return currTime;
    }

    /**
     * 计算周转时间
     *
     * @param process
     */
    private void calculeteWholeTime(Process process) {
        process.setWholeTime(process.getFinishTime() - process.getArrivalTime());
    }

    /**
     * 计算带权周转时间
     *
     * @param process
     */
    private void calculateWeightWholeTime(Process process) {
        process.setWeightWholeTime(process.getWholeTime() / (double) process.getServiceTime());
    }

    private void showExecuteMessage(int startTime, int endTime, String name) {
        System.out.println(startTime + "～" + endTime + "：【进程" + name + "】运行");
    }

    public void showResult() {
        System.out.print("进程名\t");
        System.out.print("周转时间\t");
        System.out.println("带权周转时间\t");
        Process process;
        while (!mExecutedQueue.isEmpty()) {
            process = mExecutedQueue.poll();
            System.out.print("进程" + process.getName() + "\t");
            System.out.print("\t" + process.getWholeTime() + "\t");
            System.out.println("\t" + process.getWeightWholeTime() + "\t");
        }
        System.out.println("平均周转时间：" + mTotalWholeTime / (double) mProcessCount);
        System.out.println("平均带权周转时间：" + mTotalWeightWholeTime / (double) mProcessCount);
    }

    public static void printAll(Queue<Process> queue) {
        System.out.print("进程名\t");
        System.out.print("到达时间\t");
        System.out.println("服务时间\t");
        Process process = null;
        while (!queue.isEmpty()) {
            process = queue.poll();
            System.out.print("进程" + process.getName() + "\t");
            System.out.print("\t" + process.getArrivalTime() + "\t");
            System.out.println("\t" + process.getServiceTime() + "\t");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入进程个数：");
        int processCount = scanner.nextInt();
        if (processCount < 1) return;
        Queue<Process> queue = new LinkedBlockingQueue<>();
        System.out.println("依次输入每个进程的到达时间（按到达顺序）：");
        for (int i = 0; i < processCount; i++) {
            Process process = new Process((char) (i + 65) + "");
            process.setArrivalTime(scanner.nextInt());
            queue.add(process);
        }
        System.out.println("依次输入每个进程的服务时间（按到达顺序）：");
        for (int i = 0; i < processCount; i++) {
            Process process = queue.poll();
            int time = scanner.nextInt();
            process.setServiceTime(time);
            process.setRemainServiceTime(time);
            queue.add(process);
        }

        System.out.println("输入时间片大小");
        int timeSlice = scanner.nextInt();

        RR rr = new RR(processCount, queue, timeSlice);

        System.err.println("*******************进程情况*****************");
        Thread.sleep(1000);
        printAll(new LinkedBlockingQueue<>(queue));
        Thread.sleep(1000);

        System.err.println("******************运行过程*****************");
        Thread.sleep(1000);
        rr.RRAlgorithm();
        Thread.sleep(1000);

        System.err.println("*******************运行结果*****************");
        Thread.sleep(1000);
        rr.showResult();
    }


}

//进程
class Process {
    private String name;
    private int arrivalTime; //到达时间
    private int serviceTime; //服务时间
    private int remainServiceTime; //还需要服务的时间
    private int finishTime; //完成时间
    private int wholeTime; //周转时间
    private double weightWholeTime; //带权周转时间

    public int getRemainServiceTime() {
        return remainServiceTime;
    }

    public void setRemainServiceTime(int remainServiceTime) {
        this.remainServiceTime = remainServiceTime;
    }

    public Process(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getWholeTime() {
        return wholeTime;
    }

    public void setWholeTime(int wholeTime) {
        this.wholeTime = wholeTime;
    }

    public double getWeightWholeTime() {
        return weightWholeTime;
    }

    public void setWeightWholeTime(double weightWholeTime) {
        this.weightWholeTime = weightWholeTime;
    }
}