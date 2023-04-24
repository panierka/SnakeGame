package com.company;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GameOverScreen extends Screen{

    private int score;
    private InputHandler input;

    @Override
    public void enter() {
        var g = GameLogic.getInstance();
        score = g.getScore();
        g.reset();

        input = new InputHandler(null);
        GameWindow.getInstance().addKeyListener(input);
    }

    @Override
    public void loop(Graphics2D g2d) {
        var rect = new Rectangle2D.Double(0, GameDisplay.fullPixelSize, GameDisplay.fullPixelSize, GameDisplay.borderSize);
        g2d.setColor(new Color(201, 106, 47));
        g2d.fill(rect);

        String text = String.format("Game Over. Score: %d", score);

        g2d.setColor(Color.white);
        var font = new Font("Consolas", Font.ITALIC, 26);
        g2d.setFont(font);

        int offsetX = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth() / 2;
        int offsetY = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getHeight() / 2;

        g2d.drawString(text,
                GameDisplay.fullPixelSize / 2 - offsetX,
                GameDisplay.fullPixelSize / 2 - offsetY);

        String restartTip = "Press [R] to restart";

        font = new Font("Consolas", Font.ITALIC, 18);
        g2d.setFont(font);
        offsetX = (int)g2d.getFontMetrics().getStringBounds(restartTip, g2d).getWidth() / 2;
        offsetY = (int)g2d.getFontMetrics().getStringBounds(restartTip, g2d).getHeight() / 2;

        g2d.setColor(new Color(84, 84, 84));

        g2d.drawString(restartTip,
                GameDisplay.fullPixelSize / 2 - offsetX,
                GameDisplay.fullPixelSize / 2 - offsetY + 80);
    }

    @Override
    public void exit() {
        GameWindow.getInstance().removeKeyListener(input);
    }

    @Override
    public int getMyID() {
        return 1;
    }
}
