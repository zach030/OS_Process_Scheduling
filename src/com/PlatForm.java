package com;

/**
 * @program: Process_Scheduling
 * @description: 界面图形化设计
 * @author: zach
 * @create: 2020-11-01-18:41
 **/

import com.os.FileOperator;
import com.os.SystemController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class PlatForm {

    public static PlatForm platForm = new PlatForm();
    //面板
    private JPanel log = new JPanel();//日志输出
    private JPanel jobPool = new JPanel();//job队列面板
    private JPanel runningQueue = new JPanel();    //运行队列面板
    private JPanel readyQueue = new JPanel();    //就绪队列面板
    private JPanel inputBlockQueue = new JPanel();        //键盘输入阻塞队列面板
    private JPanel outputBlockQueue = new JPanel();    //屏幕输出阻塞队列面板
    private JPanel pvBlockQueue = new JPanel();    //pv阻塞队列面板
    //信息展示
    public JTextArea logInfo = new JTextArea();//日志
    public JTextArea jobInfo = new JTextArea();//job队列信息
    public JTextArea runningInfo = new JTextArea();        //运行队列信息
    public JTextArea readyInfo = new JTextArea();        //就绪队列信息
    public JTextArea inputBlockInfo = new JTextArea();        //键盘输入阻塞队列信息
    public JTextArea outputBlockInfo = new JTextArea();        //屏幕输出阻塞信息
    public JTextArea pvBlockInfo = new JTextArea();        //pv阻塞信息

    //系统label
    //cpu
    public JLabel cpuLabel = new JLabel();
    public JLabel cpuState = new JLabel();
    //时间
    public JLabel timeLabel = new JLabel();
    public JLabel systemTime = new JLabel();
    //日志

    //滚动
    private JScrollPane logSP = new JScrollPane(logInfo);
    private JScrollPane jobSP = new JScrollPane(jobInfo);
    private JScrollPane runningSP = new JScrollPane(runningInfo);
    private JScrollPane readySP = new JScrollPane(readyInfo);
    private JScrollPane inputSP = new JScrollPane(inputBlockInfo);
    private JScrollPane outputSP = new JScrollPane(outputBlockInfo);
    private JScrollPane pvSP = new JScrollPane(pvBlockInfo);
    //按钮
    private JButton systemStart = new JButton("启动");                //按钮-启动
    private JButton systemPause = new JButton("暂停");                //按钮-结束
    private JButton systemExit = new JButton("退出");                //按钮-退出
    private JButton createJob = new JButton("创建作业");                //按钮-创建作业

    JFrame frame = new JFrame("并发进程低级调度仿真平台");

    PlatForm() {
        log.setBorder(BorderFactory.createTitledBorder("运行日志"));
        log.add(logSP);
        log.setLayout(null);
        logSP.setBounds(20, 20, 310, 180);
        log.setBounds(30, 300, 350, 220);
        logInfo.setEditable(false);
        logInfo.setFont(new Font("黑体", Font.PLAIN, 16));
        frame.add(log);
        //运行队列面板
        runningQueue.setBorder(BorderFactory.createTitledBorder("运行进程"));
        runningQueue.add(runningSP);
        runningQueue.setLayout(null);
        runningSP.setBounds(20, 30, 310, 50);
        runningQueue.setBounds(30, 60, 350, 100);
        runningInfo.setEditable(false);
        runningInfo.setForeground(Color.RED);
        runningInfo.setFont(new Font("黑体", Font.PLAIN, 16));
        frame.add(runningQueue);

        //作业队列面板
        jobPool.setBorder(BorderFactory.createTitledBorder("作业队列"));
        jobPool.add(jobSP);
        jobPool.setLayout(null);
        jobSP.setBounds(20, 30, 310, 50);
        jobPool.setBounds(30, 180, 350, 100);
        jobInfo.setEditable(false);
        jobInfo.setForeground(Color.RED);
        jobInfo.setFont(new Font("黑体", Font.PLAIN, 16));
        frame.add(jobPool);

        //就绪队列面板
        readyQueue.setBorder(BorderFactory.createTitledBorder("就绪队列"));
        readyQueue.add(readySP);
        readyQueue.setLayout(null);
        readySP.setBounds(20, 30, 310, 50);
        readyQueue.setBounds(470, 60, 350, 100);
        readyInfo.setEditable(false);
        readyInfo.setForeground(Color.RED);
        readyInfo.setFont(new Font("黑体", Font.PLAIN, 16));
        frame.add(readyQueue);

        //键盘输入阻塞队列面板
        inputBlockQueue.setBorder(BorderFactory.createTitledBorder("键盘输入阻塞队列"));
        inputBlockQueue.add(inputSP);
        inputBlockQueue.setLayout(null);
        inputSP.setBounds(20, 30, 310, 50);
        inputBlockQueue.setBounds(470, 180, 350, 100);
        inputBlockInfo.setEditable(false);
        inputBlockInfo.setForeground(Color.RED);
        inputBlockInfo.setFont(new Font("黑体", Font.PLAIN, 16));
        frame.add(inputBlockQueue);

        //屏幕输出阻塞队列面板
        outputBlockQueue.setBorder(BorderFactory.createTitledBorder("屏幕输出阻塞队列"));
        outputBlockQueue.add(outputSP);
        outputBlockQueue.setLayout(null);
        outputSP.setBounds(20, 30, 310, 50);
        outputBlockQueue.setBounds(470, 300, 350, 100);
        outputBlockInfo.setEditable(false);
        outputBlockInfo.setForeground(Color.RED);
        outputBlockInfo.setFont(new Font("黑体", Font.PLAIN, 16));
        frame.add(outputBlockQueue);

        //PV阻塞队列面板
        pvBlockQueue.setBorder(BorderFactory.createTitledBorder("PV通信阻塞队列"));
        pvBlockQueue.add(pvSP);
        pvBlockQueue.setLayout(null);
        pvSP.setBounds(20, 30, 310, 50);
        pvBlockQueue.setBounds(470, 420, 350, 100);
        pvBlockInfo.setEditable(false);
        pvBlockInfo.setForeground(Color.RED);
        pvBlockInfo.setFont(new Font("黑体", Font.PLAIN, 16));
        frame.add(pvBlockQueue);


        //增加按钮
        frame.add(systemStart);
        frame.add(systemPause);
        frame.add(systemExit);
        frame.add(createJob);

        //增加时钟
        timeLabel.setText("系统时间:");
        timeLabel.setFont(new Font("黑体", Font.PLAIN, 16));
        timeLabel.setBounds(30, 10, 100, 50);
        frame.add(timeLabel);
        systemTime.setFont(new Font("黑体", Font.PLAIN, 16));
        systemTime.setBounds(170, 10, 100, 50);
        systemTime.setText("");
        systemTime.setForeground(Color.RED);
        frame.add(systemTime);

        //增加CPU状态
        cpuLabel.setText("CPU状态");
        cpuLabel.setFont(new Font("黑体", Font.PLAIN, 16));
        cpuLabel.setBounds(470, 10, 100, 50);
        frame.add(cpuLabel);

        cpuState.setFont(new Font("黑体", Font.PLAIN, 16));
        cpuState.setBounds(570, 10, 100, 50);
        cpuState.setText("CPU未运行");
        cpuState.setForeground(Color.RED);
        frame.add(cpuState);

        systemStart.setBounds(230, 600, 80, 30);
        //systemPause.setBounds(250, 600, 80, 30);
        systemExit.setBounds(380, 600, 80, 30);
        createJob.setBounds(530, 600, 150, 30);

        frame.setLayout(null);
        frame.setSize(1000, 800);

        ButtonActionListener();
    }

    public static boolean systemFlag = false;

    public void ButtonActionListener() {
        //开始按钮
        systemStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SystemController.systemController.SystemStart();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } finally {
                    if (SystemController.systemController.isSystemStop() && !systemFlag) {
                        System.exit(0);
                    }
                    systemFlag = false;
                }
            }
        });
//        //暂停系统
//        systemPause.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                SystemController.systemController.SystemInterrupt();
//            }
//        });
        //退出系统
        systemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        //创建作业
        createJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileOperator.fileOperator.createNewJob();
            }
        });
    }

    public void SetVisible(boolean visible) {
        this.frame.setVisible(visible);
    }
}