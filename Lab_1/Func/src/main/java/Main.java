import spos.lab1.demo.IntOps;

    public class Main {
        public static void main(String[] args) {
            int num=0;
            try {
                num = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException nfe) {
            }
            int res=0;
            if(args[1].equals("F")) {
                try {
                    res = IntOps.funcF( num );
                } catch (InterruptedException e) {
                    e.printStackTrace( );
                    return;
                }
            }
            if(args[1].equals("G")) {
                try {
                    res = IntOps.funcG( num );
                } catch (InterruptedException e) {
                    e.printStackTrace( );
                    return;
                }
            }
            System.exit(res);
        }
    }

