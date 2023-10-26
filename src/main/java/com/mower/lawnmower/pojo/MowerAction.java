package com.mower.lawnmower.pojo;

public enum MowerAction {
    Right("D"),
    Left("G"),
    Forward("A");

    private final  String frenchLetter;

    MowerAction(String frenchLetter) {
        this.frenchLetter = frenchLetter;
    }

    public String getFrenchLetter() {
        return frenchLetter;
    }
}
