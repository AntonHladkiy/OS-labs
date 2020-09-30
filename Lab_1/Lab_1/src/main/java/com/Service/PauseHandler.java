package com.Service;
import com.Main;
import com.model.Results;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PauseHandler{
    private static final int WAIT_TIME = 15000;

    public static void Stop() {
        Main.setFdestroyed(true);
        Main.setGdestroyed(true);
        Main.getProcessF().destroy();
        Main.getProcessG().destroy();
        System.exit(0);
    }

    public static void startPrompt() {
        System.out.println("Cancellation Prompt:");
        System.out.println("(1) stop");
        System.out.println("(2) continue");
        Scanner scanner = new Scanner(System.in);
        long promptStart = System.currentTimeMillis();
        while (System.currentTimeMillis() - promptStart < WAIT_TIME) {
            String in = scanner.nextLine();
            if (in.equals("1")) {
                Stop();
            } else if (in.equals("2")) {
                return;
            } else {
                System.out.println("Wrong input");
            }
        }
        Stop();
    }
}
