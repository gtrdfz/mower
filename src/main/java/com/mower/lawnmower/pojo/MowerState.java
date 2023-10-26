package com.mower.lawnmower.pojo;

import java.util.StringJoiner;

public record MowerState(
        Position position,
        Direction direction) {

    public String print() {
        return new StringJoiner(" ")
                .add(Integer.toString(position.x()))
                .add(Integer.toString(position.y()))
                .add(direction.getFirstLetter())
                .toString();
    }

}
