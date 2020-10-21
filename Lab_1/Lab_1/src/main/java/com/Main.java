package com;

import com.Service.KeyHandler;
import com.Service.PauseHandler;
import lombok.Getter;
import com.model.FuncResult;

import java.io.*;
import com.model.Results;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    @Getter
    @Setter
    private static AtomicBoolean ResultsPrinted=new AtomicBoolean( false );
    @Getter
    @Setter
    private static List<Boolean> finished=new ArrayList<Boolean>();
    @Getter
    private static List<Process> processes=new ArrayList<Process>();
    @Getter
    private static KeyHandler keyHandler;
    public static void main(String[] args) {
        keyHandler = new KeyHandler( );
        keyHandler.start( );
        while (true) {
            ResultsPrinted.set( false );
            PauseHandler.setThread( null );
            finished=new ArrayList<Boolean>();
            Results.res=new ArrayList<FuncResult>();
            processes=new ArrayList<Process>();
            Scanner scanner=new Scanner(System.in);
            System.out.println("Write argument");
            String arg = scanner.nextLine();
            System.out.println("Write functions(example:F G F F F)");
            List<String> functions= Arrays.asList(scanner.nextLine().split( " " ));
            String thisProjectDir = System.getProperty( "user.dir" );
            thisProjectDir = thisProjectDir.substring( 0, thisProjectDir.length( ) - 5 );
            String path = thisProjectDir + "Func\\target\\Func-1.0-SNAPSHOT.jar";
            for(int i =0;i<functions.size();i++){
                try {
                    processes.add(new ProcessBuilder( "java", "-jar", path, arg,functions.get(i)).start());
                    finished.add(false);
                } catch (IOException e) {
                    e.printStackTrace( );
                }
            }
            for(int i=0;i<finished.size();i++){
                finished.set(i,false);
            }
            while (true) {
                for(int i =0;i<processes.size();i++){
                    if (!processes.get(i).isAlive( ) && !finished.get(i)) {
                        finished.set(i,true);
                        Results.res.add( new FuncResult(functions.get(i),processes.get(i).exitValue( )) );
                        if (processes.get(i).exitValue( ) == 0) {
                            System.out.println("Result of main function = 0");
                            ResultsPrinted.set(true);
                            break;
                        }
                    }
                }
                if (Results.res.size( ) == functions.size() ) {
                    Results.printMainResult();
                    break;
                }
                if(ResultsPrinted.get()){
                    break;
                }

            }
            PauseHandler.Stop();
            if(PauseHandler.getThread()!=null){
            try {
                PauseHandler.getThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace( );
            }}
        }
    }
}