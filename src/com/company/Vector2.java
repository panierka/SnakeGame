package com.company;

import java.util.Objects;

public class Vector2 {
    public int x;
    public int y;

    public Vector2(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2 vector2 = (Vector2) o;
        return x == vector2.x && y == vector2.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    public Vector2 reversed(){
        return new Vector2(-x, -y);
    }

    public Vector2 add(Vector2 v){
        return new Vector2(x + v.x, y + v.y);
    }

    public static class Generator{
        public Vector2 right(){
            return new Vector2(1, 0);
        }

        public Vector2 left(){
            return new Vector2(-1, 0);
        }

        public Vector2 down(){
            return new Vector2(0, 1);
        }

        public Vector2 up(){
            return new Vector2(0, -1);
        }
    }
}
