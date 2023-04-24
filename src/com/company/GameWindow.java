package com.company;

import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow()
    {
        super("SNAKE");

        var display = new GameDisplay();
        add(display);
        pack();

        var updateGraphicsTimer = new Timer(16, display);
        updateGraphicsTimer.setRepeats(true);
        updateGraphicsTimer.start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static GameWindow instance;

    public static GameWindow getInstance() {
        if(instance == null){
            instance = new GameWindow();
        }

        return instance;
    }
}
