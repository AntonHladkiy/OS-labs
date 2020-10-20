package com.Service;
import com.Main;
import com.model.Results;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class PauseHandler{
    private static final int WAIT_TIME = 15000;

    public static void Stop() {
        List<Boolean> finished=Main.getFinished();
        List<Process> processes=Main.getProcesses();
        for(int i=0;i<finished.size();i++){
            finished.set(i,true);
            processes.get(i).destroy();
        }
    }

    public static void startPrompt() {
        Main.setPromptActive( true );
        AtomicBoolean continueProcess= new AtomicBoolean( false );
        System.out.println("Cancellation Prompt:");
        System.out.println("(1) stop");
        System.out.println("(2) continue");
        System.out.println("System will shut down automatically in 15 seconds");
        Scanner scanner = new Scanner(System.in);
        Thread thisThread=Thread.currentThread();
        new Thread(() ->{
            while (true) {
                String in = scanner.nextLine();
                if (in.equals("1")) {
                    thisThread.interrupt();
                    return;
                } else if (in.equals("2")) {
                    continueProcess.set( true );
                    thisThread.interrupt();
                    return;
                } else {
                    System.out.println("Wrong input");
                }
            }
        }).start();
       try{Thread.sleep(WAIT_TIME);}
       catch(InterruptedException e){}
        Main.setPromptActive( false );
       if(continueProcess.get()){
           return;
       }
       Results.printMainResult();
       Stop();
    }
}
