package com.os;

import com.configs.InstructionStatus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: Process_Sheduling
 * @description: 文件控制管理
 * @author: zach
 * @create: 2020-10-27-21:36
 **/
public class FileOperator {
    //从文件中读取的临时pcb
    public static final String abstractFileName = "D:\\UniCourse\\OS\\周全-19318123-必修实验-申请成绩\\input\\";
    public static final String jobFileName = abstractFileName + "19318123-jobs-input.txt";
    public static String instrucName = "1.txt";
    public static String instructionFileName;
    ArrayList<PCB> TmpPCBList = new ArrayList<>();
    HashMap<PCB, String> PCBInstructionFile = new HashMap<>();

    public static void main(String[] args) {
        FileOperator fileOperator = new FileOperator();
        fileOperator.ReadAllPCB(jobFileName);
    }

    public void ReadAllPCB(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tmp = line.split(",");
                PCB pcb = new PCB();
                pcb.setProID(Integer.parseInt(tmp[0]));
                System.out.println("This is job: "+pcb.getProID());
                pcb.setPriority(Integer.parseInt(tmp[1]));
                pcb.setInTimes(Integer.parseInt(tmp[2]));
                pcb.setInstrucNum(Integer.parseInt(tmp[3]));
                TmpPCBList.add(pcb);
                char oldChar = instrucName.charAt(0);
                char newChar = tmp[0].charAt(0);
                instrucName = instrucName.replace(oldChar, newChar);
                instructionFileName = abstractFileName + instrucName;
                ReadPCBInstructions(instructionFileName);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReadPCBInstructions(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tmp = line.split(",");
                PCB pcb = new PCB();
                PCBInstructions pcbInstructions = new PCBInstructions();
                pcbInstructions.setInstructionID(Integer.parseInt(tmp[0]));
                pcbInstructions.setInstructionState(InstructionStatus.values()[Integer.parseInt(tmp[1])]);
                pcb.pcbInstructions.add(pcbInstructions);
                System.out.println("read pcb instructions id:  "+pcbInstructions.getInstructionID()+"" +
                        ",state:"+pcbInstructions.getInstructionState());
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
