package com.company;

public class Player
{
    private final SnakeSegment head;
    private SnakeSegment tail;
    private Vector2 direction;

    public Player()
    {
        head = new SnakeSegment(new Vector2(2, 0));
        tail = head;

        addSegment(new Vector2(1, 0));
        addSegment(new Vector2(0, 0));

        direction = new Vector2.Generator().right();
    }

    public void setMovingDirection(Vector2 direction){
        if(this.direction.equals(direction) || this.direction.equals(direction.reversed())){
            return;
        }

        this.direction = direction;
        move();

        var gameLogic = GameLogic.getInstance();

        var timer = gameLogic.getPlayerMoveTimer();
        if(timer != null)
            timer.restart();

        gameLogic.decayScoreBonus();
    }

    public void move(){
        var newPosition = head.getPosition().add(direction);

        var logic = GameLogic.getInstance();
        var board = logic.getBoardHandler();

        var elementOnTile = board.getFromBoard(newPosition.x, newPosition.y);
        if(elementOnTile == null){
            head.setPosition(newPosition);
            return;
        }

        elementOnTile.collisionBehavior(this);
    }

    public void addSegment(Vector2 lastTailPosition)
    {
        var segment = new SnakeSegment(lastTailPosition);
        tail.setNextSegment(segment);
        tail = segment;
    }

    public Vector2 getTailPosition(){
        return tail.getPosition();
    }
}
