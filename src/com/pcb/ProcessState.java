package com.pcb;

import java.util.*;
import java.io.*;

public class ProcessState{

    private ArrayList<PCB> pcbList = new ArrayList<>();

    public static void main(String[] args){
        ProcessState ps = new ProcessState();
        ps.readPCB();

        ps.run();
    }

    //从文件in.txt读入pcb，放入pcb池子中
    public void readPCB(){
        try{
            FileReader fr = new FileReader("in.txt");
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            while((line = br.readLine()) != null){
                String[] tmp = line.split(" ");
                PCB pcb = new PCB();
                pcb.setPid(tmp[0]);
                pcb.setState(tmp[1]);

                pcbList.add(pcb);
            }

            br.close();
            fr.close();

        }catch(IOException e){
            e.printStackTrace();
        }

    }


    //通过PCB队列的组织管理来模拟进程就绪、运行、等待等状态之间的切换
    public void run(){
        //当前格局
        printCurrentState();

        Scanner scan = new Scanner(System.in);
        String choice = null;
        while(true){
            menu();
            System.out.print("请输入您的选择：");
            choice = scan.next();
            switch(choice){
                case "0":
                    System.out.println("您已经退出了模拟实验，欢迎再来！");
                    System.exit(0);
                case "1":
                    createProcess();
                    break;
                case "2":
                    destroyProcess();
                    break;
                case "3":
                    hangUpProcess();
                    break;
                case "4":
                    activateProcess();
                    break;
                case "5":
                    timeUp();
                    break;
                default:
                    System.out.println("您输入错误，请重新输入！");
                    break;

            }

            runProcess();//不管怎么选择，总得有个进程运行吧！

            //打印当前格局
            printCurrentState();

        }
    }

    //与用户交互菜单
    public void menu(){
        System.out.println("*************欢迎来模拟实验**************");
        System.out.println("1>>>>>>>>>>>>>>>>>>>创建进程");
        System.out.println("2>>>>>>>>>>>>>>>>>>>销毁进程");
        System.out.println("3>>>>>>>>>>>>>>>>>>>挂起进程");
        System.out.println("4>>>>>>>>>>>>>>>>>>>激活进程");
        System.out.println("5>>>>>>>>>>>>>>>>>>>时间片到");
        System.out.println("0>>>>>>>>>>>>>>>>>>>退出");
    }

    //打印当前格局
    public void printCurrentState(){

        System.out.println("----------------------------------------------");
        System.out.println("进程号	进程状态");
        System.out.println("----------------------------------------------");
        for(int i = 0; i < pcbList.size(); i++){
            System.out.println(pcbList.get(i).getPid()+"	"+pcbList.get(i).getState());
        }
        System.out.println("----------------------------------------------");
        System.out.println();
    }

    //运行进程
    public void runProcess(){
        //先判断有没有进程在运行
        boolean flag = false;
        for(int i = 0; i < pcbList.size(); i++){
            if(pcbList.get(i).getState().equals("运行")){
                flag = true;
                break;
            }
        }
        //如果当前没有进程在运行，就随便选一个进程运行（这是为了简化进程调度）
        if(flag == false){
            for(int i = 0; i < pcbList.size(); i++){
                if(pcbList.get(i).getState().equals("就绪")){
                    pcbList.get(i).setState("运行");
                    break;
                }
            }
        }
    }

    //创建进程
    public void createProcess(){

        for(int i = 0; i < pcbList.size(); i++){
            if(pcbList.get(i).getState().equals("待创建")){
                pcbList.get(i).setState("就绪");
                System.out.println("您创建的进程是"+pcbList.get(i).getPid());
                break;
            }
        }
    }

    //销毁进程
    public void destroyProcess(){
        for(int i = 0; i < pcbList.size(); i++){
            if(pcbList.get(i).getState().equals("运行")){
                pcbList.get(i).setState("销毁");
                System.out.println("您强制销毁了当前运行的进程"+pcbList.get(i).getPid());
                break;
            }
        }
    }

    //挂起进程
    public void hangUpProcess(){
        for(int i = 0; i < pcbList.size(); i++){
            if(pcbList.get(i).getState().equals("运行")){
                pcbList.get(i).setState("等待");
                System.out.println("您挂起了当前运行的进程"+pcbList.get(i).getPid());
                break;
            }
        }
    }

    //激活进程
    public void activateProcess(){
        for(int i = 0; i < pcbList.size(); i++){
            if(pcbList.get(i).getState().equals("等待")){
                pcbList.get(i).setState("就绪");
                System.out.println("您激活了等待进程"+pcbList.get(i).getPid());
                break;
            }
        }
    }

    //时间片到
    public void timeUp(){
        for(int i = 0; i < pcbList.size(); i++){
            if(pcbList.get(i).getState().equals("运行")){
                pcbList.get(i).setState("就绪");
                System.out.println("进程"+pcbList.get(i).getPid()+"的时间片到，转为就绪状态");
                break;
            }
        }
    }
}