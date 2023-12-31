package com.mower.lawnmower.pojo;

public enum Direction {
    North("N", 0, 1),
    East("E", 1, 0),
    South("S", 0, -1),
    West("W", -1, 0);

    private final String firstLetter;
    private final int x;
    private final int y;

    Direction(String firstLetter, int x, int y) {
        this.firstLetter = firstLetter;
        this.x = x;
        this.y = y;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
