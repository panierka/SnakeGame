package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GameScreen extends Screen{

    private InputHandler input;

    @Override
    public void enter() {
        var logic = GameLogic.getInstance();
        var player = new Player();

        input = new InputHandler(player);
        GameWindow.getInstance().addKeyListener(input);

        var playerMoveTimer = new Timer(106, e -> player.move());
        playerMoveTimer.setRepeats(true);
        playerMoveTimer.start();
        logic.setPlayerMoveTimer(playerMoveTimer);

        logic.spawnFood();
    }

    @Override
    public void loop(Graphics2D g2d)
    {
        for(int y = 0; y < GameLogic.BOARD_SIZE; y++)
        {
            drawRow(g2d, y);
        }

        drawScore(g2d);
    }

    @Override
    public void exit() {
        GameWindow.getInstance().removeKeyListener(input);
    }

    @Override
    public int getMyID() {
        return 0;
    }

    private void drawScore(Graphics2D g2d) {
        var rect = new Rectangle2D.Double(0, GameDisplay.fullPixelSize, GameDisplay.fullPixelSize, GameDisplay.borderSize);
        g2d.setColor(new Color(49, 51, 54));
        g2d.fill(rect);

        int score = GameLogic.getInstance().getScore();
        String text = String.format(":%d:", score);

        g2d.setColor(Color.white);
        var font = new Font("Consolas", Font.BOLD, 46);
        g2d.setFont(font);

        int offset = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth() / 2;

        g2d.drawString(text, GameDisplay.fullPixelSize / 2 - offset,
                GameDisplay.borderSize / 2 + GameDisplay.fullPixelSize + 15);
    }

    private void drawRow(Graphics2D g2d, int y)
    {
        for(int x = 0; x < GameLogic.BOARD_SIZE; x++)
        {
            drawElement(g2d, x, y);
        }
    }

    private void drawElement(Graphics2D g2d, int x, int y)
    {
        var gameController = GameLogic.getInstance();
        var board = gameController.getBoardHandler();
        var element = board.getFromBoard(x, y);

        if(element == null)
        {
            return;
        }

        int worldX = x * GameDisplay.UNIT_PIXEL_SIZE;
        int worldY = y * GameDisplay.UNIT_PIXEL_SIZE;

        var shape = element.getSprite(worldX, worldY);
        g2d.setColor(element.getColor());
        g2d.fill(shape);
    }
}
