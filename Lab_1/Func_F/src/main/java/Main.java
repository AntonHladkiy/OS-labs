import spos.lab1.demo.*;

import java.io.InputStream;
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
        res =IntOps.funcF(num);}
        catch(Exception e){}
        System.exit(res);
    }
}
