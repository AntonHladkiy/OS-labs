package com.Service;
import com.Main;
import com.model.Results;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PauseHandler{
    private static final int WAIT_TIME = 15000;
    @Getter
    private static AtomicBoolean continueProcess= new AtomicBoolean( false );
    @Getter
    private static AtomicBoolean endPrompt= new AtomicBoolean( false );
    @Setter
    @Getter
    private static Thread thread=null;
    public static void Stop() {
        List<Boolean> finished=Main.getFinished();
        List<Process> processes=Main.getProcesses();
        for(int i=0;i<finished.size();i++){
            finished.set(i,true);
            processes.get(i).destroy();
        }
    }

    public static void startPrompt() {
        endPrompt.set(false);
        continueProcess.set(false);
        Main.getKeyHandler().prompt();
        thread=new Thread(() ->{
        long start=System.currentTimeMillis();
        long currentTime=System.currentTimeMillis();
       while(currentTime-start<=WAIT_TIME&&!Main.getResultsPrinted( ).get( )&&!endPrompt.get()) {

           currentTime = System.currentTimeMillis( );
       }
       Main.getKeyHandler().reset();
       if(continueProcess.get()){
           return;
       }
       Results.printMainResult();
       Stop();});
       thread.start();
    }
}
