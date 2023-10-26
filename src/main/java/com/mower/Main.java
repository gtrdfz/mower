package com.mower;

import com.mower.lawnmower.App;
import com.mower.lawnmower.exceptions.InputException;

public class Main {

    public static void main(String[] args) throws InputException {
        new App().start(args);
    }

}