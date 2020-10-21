package com.model;

import com.Main;


import java.util.ArrayList;
import java.util.List;

public class Results {
    public static List<com.model.FuncResult> res=new ArrayList<com.model.FuncResult>();
    public static void printMainResult(){
        if(Main.getResultsPrinted().get()){
            return;
        }
        int min=0;

        if(res.size()!=0){
            min=res.get(0).getRes();
        }
        if(res.size()!=Main.getFinished().size()) {
            boolean Fcheck=false;
            boolean Gcheck=false;
            for(int i=0;i<res.size();i++){
                if(res.get(i).getFunc().equals( "F" )){
                    Fcheck=true;
                }
                if(res.get(i).getFunc().equals( "G" )){
                    Gcheck=true;
                }
            }
            System.out.println( "There is no result of main function" );
            if(!Fcheck){
                System.out.println( "Function F didnt return result" );
            }
            if(!Gcheck){
                System.out.println( "Function G didnt return result" );
            }
            Main.getResultsPrinted().set( true );
            return;
        }
        for(int i=0;i<res.size();i++){
            //min+=res.get(i).getRes();
            if(res.get(i).getRes()<min){
                min=res.get(i).getRes();
            }
        }
        System.out.println("Result of main function = "+ min);
        Main.getResultsPrinted().set( true );
    }
}
