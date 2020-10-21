package com.Service;

import com.Main;
import com.model.Results;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends JComponent {
    @Getter
    JFrame frame;
    private JButton stopProcess;
    private JButton continueProcess;
    public void start() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(0, 0);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false);
        frame.addKeyListener(new KeyListener());
        frame.setFocusable(true);
    }
    public void reset() {
        frame.dispose();
        start();
    }
    public void prompt() {
        frame.setSize( 400, 300);
        frame.setLocation( 500,300 );
        JPanel panel = new JPanel( );
        frame.add( panel );
        JTextArea prompt = new JTextArea( );
        prompt.setBounds( 0, 0, 400, 100 );
        panel.add( prompt );
        panel.setLayout( null );
        prompt.setText( "Cancellation Prompt:" +
                "\r\n" + "(1) stop"+
                "\r\n" + "(2) continue"+
                "\r\n" + "System will shut down automatically in 15 seconds");
        stopProcess=new JButton( "Stop" );
        stopProcess.setBounds( 20,200, 100,40 );
        panel.add(stopProcess);
        stopProcess.addActionListener( new KeyListener() );
        continueProcess=new JButton( "Continue" );
        continueProcess.addActionListener(  new KeyListener()  );
        continueProcess.setBounds( 140,200, 100,40 );
        panel.add(continueProcess);
    }
    private class KeyListener   extends KeyAdapter implements ActionListener {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                PauseHandler.startPrompt();
            }
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                Results.printMainResult( );
                PauseHandler.Stop();
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource( ) == stopProcess) {
                PauseHandler.getEndPrompt( ).set( true );
            }
            if (e.getSource( ) == continueProcess) {
                PauseHandler.getEndPrompt( ).set( true );
                PauseHandler.getContinueProcess( ).set( true );
            }
        }

    }
}
