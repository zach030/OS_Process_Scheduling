package com.os;

import com.config.InstructionStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @program: Process_Sheduling
 * @description: 文件控制管理
 * @author: zach
 * @create: 2020-10-27-21:36
 **/
public class FileOperator {

    static boolean joinNewJob = false;
    public static int currentJobSize = 5;
    public static FileOperator fileOperator = new FileOperator();
    public static final String outputAbstractFileName = "D:\\UniCourse\\OS\\周全-19318123-必修实验-申请成绩\\output\\";
    //从文件中读取的临时pcb
    public static final String inputAbstractFileName = "D:\\UniCourse\\OS\\周全-19318123-必修实验-申请成绩\\input\\";
    public static final String jobFileName = inputAbstractFileName + "19318123-jobs-input.txt";
    public static String instrucName = "1.txt";
    public static String instructionFileName;
    ArrayList<PCB> TmpPCBList = new ArrayList<PCB>();
    HashMap<PCB, String> PCBInstructionFile = new HashMap<PCB,String>();

    public void ReadAllPCB(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tmp = line.split(",");
                PCB pcb = new PCB();
                pcb.setProID(Integer.parseInt(tmp[0]));
                //System.out.println("This is job: " + pcb.getProID());
                pcb.setPriority(Integer.parseInt(tmp[1]));
                pcb.setInTimes(Integer.parseInt(tmp[2]));
                pcb.setInstrucNum(Integer.parseInt(tmp[3]));
                TmpPCBList.add(pcb);
                char oldChar = instrucName.charAt(0);
                char newChar = tmp[0].charAt(0);
                instrucName = instrucName.replace(oldChar, newChar);
                instructionFileName = inputAbstractFileName + instrucName;
                ReadPCBInstructions(instructionFileName, pcb);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("读取5个初始job文件及其指令文件完毕");
    }

    public void ReadOneNewPCB() {
        if (joinNewJob) {
            int size = PCBPool.pcbPool.getAllPcbList().size();
            PCBPool.pcbPool.AddProcess2ReadyQueue(PCBPool.pcbPool.getAllPcbList().get(size - 1));
        }
        joinNewJob = false;
    }

    public void createNewJob() {
        PCB pcb = createNewPCB();
        createNewInstructions(pcb);
        PCBPool.pcbPool.addPCB2Pool(pcb);
    }

    public PCB createNewPCB() {
        Random rand = new Random();
        joinNewJob = true;
        currentJobSize++;
        int jobID = currentJobSize;
        int jobPriority = rand.nextInt(6);
        int jobInTime = rand.nextInt(61);
        int jobInstrucNum = rand.nextInt(41) % (41 - 20 + 1) + 20;//20-40条指令
        PCB pcb = new PCB();
        pcb.setProID(jobID);
        pcb.setPriority(jobPriority);
        pcb.setInTimes(jobInTime);
        pcb.setInstrucNum(jobInstrucNum);
        return pcb;
    }

    public void createNewInstructions(PCB pcb) {
        Random rand = new Random();
        ArrayList<PCBInstructions> pcbInstructions = new ArrayList<PCBInstructions>();
        for (int i = 1; i <= pcb.getInstrucNum(); i++) {
            PCBInstructions pcbInstruction = new PCBInstructions();
            int state = rand.nextInt(4);
            pcbInstruction.setInstructionID(i);
            pcbInstruction.setInstructionState(InstructionStatus.values()[state]);
            pcbInstructions.add(pcbInstruction);
        }
        pcb.setPcbInstructions(pcbInstructions);
    }

    public void ReadPCBInstructions(String filename, PCB pcb) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tmp = line.split(",");
                PCBInstructions pcbInstructions = new PCBInstructions();
                pcbInstructions.setInstructionID(Integer.parseInt(tmp[0]));
                pcbInstructions.setInstructionState(InstructionStatus.values()[Integer.parseInt(tmp[1])]);
                pcb.pcbInstructions.add(pcbInstructions);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
