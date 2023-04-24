package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    public static final int BOARD_SIZE = 24;
    private final BoardHandler boardHandler;
    private Timer playerMoveTimer;

    private final static int MAX_SCORE_BONUS = 9;
    private int score = 0;
    private int nextScoreBonus = MAX_SCORE_BONUS;

    private Screen currentScreen;

    public GameLogic(){
        boardHandler = new BoardHandler(BOARD_SIZE);
        currentScreen = new GameScreen();
    }

    public void setPlayerMoveTimer(Timer playerMoveTimer) {
        this.playerMoveTimer = playerMoveTimer;
    }

    public Timer getPlayerMoveTimer() {
        return playerMoveTimer;
    }

    public BoardHandler getBoardHandler() {
        return boardHandler;
    }

    private static GameLogic instance;

    public static GameLogic getInstance() {
        if (instance == null){
            instance = new GameLogic();
        }
        return instance;
    }

    public int getScore(){
        return score;
    }

    public void addScore(){
        score += nextScoreBonus + 1;
        nextScoreBonus = MAX_SCORE_BONUS;
    }

    public void decayScoreBonus(){
        if(nextScoreBonus > 0){
            nextScoreBonus--;
        }
    }

    public Screen getCurrentScreen(){
        return currentScreen;
    }

    public void changeCurrentScreen(Screen newScreen){
        currentScreen.exit();
        currentScreen = newScreen;
        currentScreen.enter();
    }

    public void spawnFood(){
        var position = getRandomEmptyTile();
        new Food(position);
    }

    private Vector2 getRandomEmptyTile(){
        var list = new ArrayList<Vector2>();

        for(int y = 0; y < BOARD_SIZE; y++){
            collectEmptyTilesFromRow(list, y);
        }

        if(list.size() == 0){
            return new Vector2(0, 0);
        }

        var generator = new Random();
        int randomIndex = generator.nextInt(list.size());
        return list.get(randomIndex);
    }

    private void collectEmptyTilesFromRow(ArrayList<Vector2> list, int y) {
        for(int x = 0; x < BOARD_SIZE; x++){
            addTileIfEmpty(list, y, x);
        }
    }

    private void addTileIfEmpty(ArrayList<Vector2> list, int y, int x) {
        var tile = getBoardHandler().getFromBoard(x, y);
        if (tile == null){
            list.add(new Vector2(x, y));
        }
    }

    public void reset(){
        for(int x = 0; x < BOARD_SIZE; x++){
            clearRow(x);
        }

        score = 0;
        playerMoveTimer.stop();
        playerMoveTimer = null;
    }

    private void clearRow(int x) {
        for(int y = 0; y < BOARD_SIZE; y++){
            boardHandler.writeToBoard(null, x, y);
        }
    }
}
