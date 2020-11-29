// The main MemoryManagement program, created by Alexander Reeder, 2000 Nov 19

import java.applet.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class MemoryManagement 
{
  public static void main(String[] args) 
  {
    ControlPanel controlPanel;
    Kernel kernel;
//    if ( args.length < 1 || args.length > 2 )
//    {
//      System.out.println( "Usage: 'java MemoryManagement <COMMAND FILE> <PROPERTIES FILE>'" );
//      System.exit( -1 );
//    }

    File f = new File( "F:\\Source\\Repos\\OS-labs\\Lab_4\\src\\main\\resources\\memory.conf" );

    if ( ! ( f.exists() ) ) 
    {
      System.out.println( "MemoryM: error, file '" + f.getName() + "' does not exist." );
      System.exit( -1 );
    }
    if ( ! ( f.canRead() ) ) 
    {
      System.out.println( "MemoryM: error, read of " + f.getName() + " failed." );
      System.exit( -1 );
    }


      f = new File( "F:\\Source\\Repos\\OS-labs\\Lab_4\\src\\main\\resources\\commands.conf" );

      if ( ! ( f.exists() ) ) 
      {
        System.out.println( "MemoryM: error, file '" + f.getName() + "' does not exist." );
        System.exit( -1 );
      }
      if ( ! ( f.canRead() ) ) 
      {
        System.out.println( "MemoryM: error, read of " + f.getName() + " failed." );
        System.exit( -1 );
      } 


    kernel = new Kernel();
    controlPanel = new ControlPanel( "Memory Management" );
    controlPanel.init( kernel , "F:\\Source\\Repos\\OS-labs\\Lab_4\\src\\main\\resources\\commands.conf","F:\\Source\\Repos\\OS-labs\\Lab_4\\src\\main\\resources\\memory.conf" );

  }
}
