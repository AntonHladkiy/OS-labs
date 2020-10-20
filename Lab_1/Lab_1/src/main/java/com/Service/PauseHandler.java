package com.Service;
import com.Main;
import com.model.Results;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class PauseHandler{
    private static final int WAIT_TIME = 15000;

    public static void Stop() {
        Main.setFfinished( true );
        Main.setGfinished( true );
        Main.getProcessF().destroy();
        Main.getProcessG().destroy();
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
