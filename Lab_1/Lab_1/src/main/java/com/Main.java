package com;

import com.Service.KeyHandler;
import com.Service.PauseHandler;
import lombok.Getter;
import com.model.FuncResult;

import java.io.*;
import com.model.Results;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {
    @Getter
    @Setter
    private static boolean ResultsPrinted = false;
    @Getter
    @Setter
    private static boolean Ffinished = false;
    @Getter
    @Setter
    private static boolean Gfinished = false;
    @Getter
    @Setter
    private static boolean promptActive;
    @Getter
    private static Process processF;
    @Getter
    private static Process processG;

    public static void main(String[] args) {
        new Thread( ( ) -> {
            KeyHandler keyHandler = new KeyHandler( );
            keyHandler.start( );
        } ).start( );
        while (true) {
            Gfinished=false;
            Ffinished=false;
            ResultsPrinted=false;
            Results.res=new ArrayList<FuncResult>();
            System.out.println("Write argument");
            Scanner scanner=new Scanner(System.in);
            String arg = scanner.nextLine();
            String thisProjectDir = System.getProperty( "user.dir" );
            thisProjectDir = thisProjectDir.substring( 0, thisProjectDir.length( ) - 5 );
            String pathF = thisProjectDir + "Func_F\\target\\Func_F-1.0-SNAPSHOT.jar";
            String pathG = thisProjectDir + "Func_G\\target\\Func_G-1.0-SNAPSHOT.jar";
            ProcessBuilder processBuilderF = new ProcessBuilder( "java", "-jar", pathF, arg );
            ProcessBuilder processBuilderG = new ProcessBuilder( "java", "-jar", pathG, arg );
            try {
                Process funcF = processBuilderF.start( );
                processF = funcF;
            } catch (IOException e) {
                e.printStackTrace( );
            }

            try {
                Process funcG = processBuilderG.start( );
                processG = funcG;
            } catch (IOException e) {
                e.printStackTrace( );
            }
            while (true) {
                if (!processF.isAlive( ) && !Ffinished) {
                    Ffinished = true;
                    Results.res.add( new FuncResult( "F", processF.exitValue( ) ) );
                    if (processF.exitValue( ) == 0) {
                        Results.printMainResult( );
                        break;
                    }
                }
                if (!processG.isAlive( ) && !Gfinished) {
                    Gfinished = true;
                    Results.res.add( new FuncResult( "G", processG.exitValue( ) ) );
                    if (processG.exitValue( ) == 0) {
                        Results.printMainResult( );
                        break;
                    }
                }
                if (Results.res.size( ) == 2 ) {
                    Results.printMainResult( );
                    break;
                }
                if(ResultsPrinted){
                    break;
                }
            }
            PauseHandler.Stop( );
        }
    }
}