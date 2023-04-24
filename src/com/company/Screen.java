package com.company;

import java.awt.*;

public abstract class Screen
{
    public abstract void enter();
    public abstract void loop(Graphics2D g);
    public abstract void exit();
    public abstract int getMyID();
}
