package com.company;

import java.awt.*;

public abstract class GameObject
{
    private Vector2 position;

    public GameObject(Vector2 position)
    {
        setPosition(position);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        var controller =  GameLogic.getInstance();
        var board = controller.getBoardHandler();

        if(this.position != null)
            board.writeToBoard(null, getPosition().x, getPosition().y);
        this.position = position;
        board.writeToBoard(this, getPosition().x, getPosition().y);
    }

    public abstract Shape getSprite(int x, int y);
    public abstract Color getColor();
    public abstract void collisionBehavior(Player player);
}
