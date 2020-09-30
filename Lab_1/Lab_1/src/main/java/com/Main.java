package com;

import com.Service.KeyHandler;
import com.Service.PauseHandler;
import lombok.Getter;
import com.model.FuncResult;

import java.io.*;
import com.model.Results;
import lombok.Setter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {
    @Getter
    @Setter
    private static boolean Fdestroyed;
    @Getter
    @Setter
    private static boolean Gdestroyed;
    @Getter
    private static Process processF;
    @Getter
    private static Process processG;

    public static void main(String[] args) {
        String thisProjectDir = System.getProperty("user.dir");
        thisProjectDir = thisProjectDir.substring(0, thisProjectDir.length() - 5);
        String pathF = thisProjectDir + "Func_F\\target\\Func_F-1.0-SNAPSHOT.jar";
        String pathG = thisProjectDir + "Func_G\\target\\Func_G-1.0-SNAPSHOT.jar";
        String arg = "5";
        CountDownLatch latch = new CountDownLatch(1);
        ProcessBuilder processBuilderF = new ProcessBuilder("java", "-jar", pathF, arg);
        ProcessBuilder processBuilderG = new ProcessBuilder("java", "-jar", pathG, arg);
        new Thread(() -> {
            try {
                Process funcF = processBuilderF.start();
                processF = funcF;
                try {
                    if (funcF.waitFor(30, TimeUnit.SECONDS)) {
                        if(!Fdestroyed)
                        Results.res.add(new FuncResult("F", funcF.exitValue()));
                        else{
                            Results.res.add(new FuncResult("F", -1));
                        }
                    } else {
                        Results.res.add(new FuncResult("F", -1));
                    }
                    if(Results.res.size()==2){
                        latch.countDown();
                    }
                } catch (Exception e) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {try {
            Process funcG = processBuilderG.start();
            processG = funcG;
            try {
                if (funcG.waitFor(30, TimeUnit.SECONDS)) {
                    if(!Gdestroyed)
                    Results.res.add(new FuncResult("G", funcG.exitValue()));
                    else{
                        Results.res.add(new FuncResult("G", -1));
                    }
                } else {
                    Results.res.add(new FuncResult("G", -1));
                }
                if(Results.res.size()==2){
                    latch.countDown();
                }
            } catch (Exception e) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }).start();
        KeyHandler keyHandler=new KeyHandler();
        keyHandler.start();
        try{latch.await();}
        catch(Exception e){}
        Results.printResult();
        PauseHandler.Stop();
    }
}