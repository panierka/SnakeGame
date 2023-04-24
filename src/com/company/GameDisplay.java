package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameDisplay extends JPanel implements ActionListener {

    public static final int UNIT_PIXEL_SIZE = 25;
    public static final int borderSize = 100;
    public static final int fullPixelSize = GameLogic.BOARD_SIZE * UNIT_PIXEL_SIZE;

    public GameDisplay() {
        setPreferredSize(new Dimension(fullPixelSize, fullPixelSize + borderSize));
        setBackground(new Color(43, 43, 44));
    }

    private void update() {
        revalidate();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2d = (Graphics2D)g;

        var logic = GameLogic.getInstance();
        logic.getCurrentScreen().loop(g2d);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
}
