package com.model;

import com.Main;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Results {
    public static List<com.model.FuncResult> res=new ArrayList<com.model.FuncResult>();
    public static void printMainResult(){
        if(Main.isResultsPrinted()){
            return;
        }
        if(Main.isPromptActive()){
            return;
        }
        int min=0;
        Main.setResultsPrinted( true );
        if(res.size()!=0){
            min=res.get(0).getRes();
        }
        for(int i=0;i<res.size();i++){
            if(res.get(i).getRes()==0){
                min=0;
                System.out.println("Result of main function = "+ min);
                return;
            }
        }
        if(res.size()!=2) {
            System.out.println( "There is no result of main function" );
            return;
        }
        for(int i=0;i<res.size();i++){
            if(res.get(i).getRes()<min){
                min=res.get(i).getRes();
            }
        }
        System.out.println("Result of main function = "+ min);
    }
}
