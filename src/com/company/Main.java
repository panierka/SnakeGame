package com.company;

public class Main {

    public static void main(String[] args)
    {
        var logic = GameLogic.getInstance();
        GameWindow.getInstance();

        logic.getCurrentScreen().enter();
    }
}
