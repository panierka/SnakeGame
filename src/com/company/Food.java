package com.company;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Food extends GameObject {


    public Food(Vector2 position) {
        super(position);
    }

    @Override
    public Shape getSprite(int x, int y) {
        int size = GameDisplay.UNIT_PIXEL_SIZE - 10;
        return new Rectangle2D.Double(x + 5, y + 5, size, size);
    }

    @Override
    public Color getColor() {
        return new Color(151, 106, 173);
    }

    @Override
    public void collisionBehavior(Player player) {
        player.addSegment(player.getTailPosition());

        var gameLogic = GameLogic.getInstance();

        gameLogic.getBoardHandler().writeToBoard(null, getPosition().x, getPosition().y);
        player.move();

        gameLogic.spawnFood();
        gameLogic.addScore();
    }
}
