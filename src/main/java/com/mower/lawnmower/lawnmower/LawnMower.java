package com.mower.lawnmower.lawnmower;


import com.mower.lawnmower.pojo.*;

import java.util.ArrayList;
import java.util.List;

public class LawnMower {

    public List<MowerState> start(Program program) {
        List<MowerState> newStates = new ArrayList<>();

        for (MowerProgram mowerProgram : program.mowersProgram()) {
            MowerState currentState = mowerProgram.mowerState();

            for (MowerAction mowerAction : mowerProgram.mowerActions()) {
                switch (mowerAction) {
                    case Forward -> currentState = tryToForward(currentState, program, newStates);
                    case Right -> currentState = changeOrientation(1, currentState);
                    case Left -> currentState = changeOrientation(-1, currentState);
                }
            }
            newStates.add(currentState);
        }

        return newStates;
    }

    private MowerState changeOrientation(int move, MowerState mowerState) {
        Direction[] directionsValues = Direction.values();
        int ordinal = (mowerState.direction().ordinal() + move) % directionsValues.length;
        ordinal = ordinal < 0 ? directionsValues.length + ordinal : ordinal;
        Direction direction = directionsValues[ordinal];
        return new MowerState(new Position(mowerState.position().x(), mowerState.position().y()), direction);
    }

    private MowerState tryToForward(MowerState currentPosition, Program program, List<MowerState> newPositions) {
        int newPosition_Y = currentPosition.position().y() + currentPosition.direction().getY();
        int newPosition_X = currentPosition.position().x() + currentPosition.direction().getX();

        if (newPositions.stream().anyMatch(x -> new Position(newPosition_X, newPosition_Y).equals(x.position())))
            return currentPosition;

        if (newPosition_X > program.gridX() ||
                newPosition_X < 0 ||
                newPosition_Y > program.gridY() ||
                newPosition_Y < 0) {
            return currentPosition;
        }
        return new MowerState(new Position(newPosition_X, newPosition_Y), currentPosition.direction());
    }


}
