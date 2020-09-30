package com.model;

import com.Main;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Results {
    @Setter
    @Getter
    private static boolean FDone;
    @Setter
    @Getter
    private static boolean GDone;
    public static List<com.model.FuncResult> res=new ArrayList<com.model.FuncResult>();
    private static void printMainResult(){
        int min;
        min=res.get(0).getRes();
        for(int i=1;i<res.size();i++){
            if(res.get(i).getRes()<min&&res.get(i).getRes()!=-1){
                min=res.get(i).getRes();
            }
        }
        if(res.get(1).getRes()==-1&&min!=0){
            System.out.println("Result of function is undefined");
            return;
        }
        System.out.println("Result of main function = "+ min);
    }
    public static void printResult() {
        FDone = false;
        GDone = false;
        for (com.model.FuncResult results : res) {
            if (results.getFuncName() == "F" && results.getRes() != -1) {
                FDone = true;
            }
            if (results.getFuncName() == "G" && results.getRes() != -1) {
                GDone = true;
            }
        }
        if (FDone && GDone) {
            System.out.println(res.get(0).getFuncName() + " finishes with result: " + res.get(0).getRes());
            System.out.println(res.get(1).getFuncName() + " finishes with result: " + res.get(1).getRes());
            printMainResult();

        } else if(Main.getProcessF().isAlive()&&Main.getProcessF().isAlive()){
            if (FDone) {
                System.out.println(res.get(0).getFuncName() + " finishes with result: " + res.get(0).getRes());
                System.out.println("G hangs");
            } else {
                if (GDone) {
                    System.out.println(res.get(0).getFuncName() + " finishes with result: " + res.get(0).getRes());
                    System.out.println("F hangs");
                } else {
                    System.out.println("G hangs");
                    System.out.println("F hangs");
                }
            }

        printMainResult();}

    }
}
