package com.Service;

import com.model.Results;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends JComponent {
    JFrame frame;

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

        }
    }
}