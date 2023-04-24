package com.company;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SnakeSegment extends GameObject{

    private SnakeSegment previousSegment;
    private SnakeSegment nextSegment;

    public SnakeSegment(Vector2 position) {
        super(position);
    }

    @Override
    public void setPosition(Vector2 position) {
        var oldPosition = getPosition();
        super.setPosition(position);

        if(nextSegment != null)
            nextSegment.setPosition(oldPosition);
    }

    @Override
    public Shape getSprite(int x, int y) {
        int size = GameDisplay.UNIT_PIXEL_SIZE;
        return new Rectangle2D.Double(x, y, size, size);
    }

    @Override
    public Color getColor() {
        return previousSegment == null ? new Color(242, 184, 82) : new Color(201, 106, 47);
    }

    @Override
    public void collisionBehavior(Player player) {
        GameLogic.getInstance().changeCurrentScreen(new GameOverScreen());
    }

    public void setNextSegment(SnakeSegment nextSegment) {
        this.nextSegment = nextSegment;
        this.nextSegment.previousSegment = this;
    }
}
