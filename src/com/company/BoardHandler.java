package com.company;

import java.util.Arrays;

public class BoardHandler
{
    private final int size;
    private final GameObject[][] board;

    public BoardHandler(int size)
    {
        this.size = size;
        board = new GameObject[this.size][this.size];
    }

    public GameObject getFromBoard(int x, int y)
    {
        x = Math.floorMod(x, size);
        y = Math.floorMod(y, size);

        return board[x][y];
    }

    public void writeToBoard(GameObject element, int x, int y)
    {
        x = Math.floorMod(x, size);
        y = Math.floorMod(y, size);

        board[x][y] = element;
    }

    public void printBoard()
    {
        for(var row : board)
        {
            var rowText = Arrays.toString(row);
            System.out.println(rowText);
        }
    }
}
