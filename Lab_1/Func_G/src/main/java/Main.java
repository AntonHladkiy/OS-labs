import spos.lab1.demo.IntOps;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) {
        int num=0;
try {
     num = Integer.parseInt(args[0]);
  }
    catch (NumberFormatException nfe) {
 }
        int res=0;
        try{
            res =IntOps.funcG(num); }
        catch(InterruptedException e){
            e.printStackTrace();
            return;
        }
        System.exit(res);
    }
}
