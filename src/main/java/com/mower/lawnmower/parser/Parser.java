package com.mower.lawnmower.parser;

import com.mower.lawnmower.exceptions.InputException;
import com.mower.lawnmower.pojo.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Parser {
    private static final Pattern sizeLawn = Pattern.compile("^[0-9]+\\s[0-9]+$");
    private static final Pattern mowerPosition = Pattern.compile("^[0-9]+\\s[0-9]+\\s(N|E|S|W)$");
    private static final Pattern mowerMove = Pattern.compile("^(A|G|D)+$");


    public Program parse(List<String> linesRead) throws InputException {
        linesRead = linesRead.stream().filter(l -> !l.isEmpty()).toList();
        if (linesRead.size() < 3) throw new InputException("Input must contain minimum 3 lines");
        if (linesRead.size() % 2 == 0) throw new InputException("Input must have odd line number");
        List<MowerProgram> mowerProgram = new ArrayList<>();

        Position position = readFirstLine(linesRead.get(0));

        for (int i = 1; i < linesRead.size(); i++) {
            String line = linesRead.get(i);
            check(mowerPosition, line);
            String nextLine = linesRead.get(++i);
            check(mowerMove, nextLine);
            mowerProgram.add(parse(line, nextLine, position));
        }

        return new Program(position.x(), position.y(), mowerProgram);
    }

    private Position readFirstLine(String line) throws InputException {
        check(sizeLawn, line);
        String[] lineSplited = line.split("\\s");
        return new Position(Integer.parseInt(lineSplited[0]), Integer.parseInt(lineSplited[1]));
    }

    private MowerProgram parse(String line, String nextLine, Position position) throws InputException {
        String[] lineSplited = line.split(" ");
        int x_mower = Integer.parseInt(lineSplited[0]);
        int y_mower = Integer.parseInt(lineSplited[1]);
        String directionLetter = lineSplited[2];

        if (x_mower > position.x() || y_mower > position.y() || x_mower < 0 || y_mower < 0)
            throw new InputException("Input out of grid");

        Direction direction = Arrays.stream(Direction.values()).filter(d -> d.getFirstLetter().equalsIgnoreCase(directionLetter)).findFirst().get();

        return new MowerProgram(new MowerState(new Position(x_mower, y_mower), direction), parse(nextLine));
    }

    List<MowerAction> parse(String line) {
        List<MowerAction> mowerActionsEnum = Arrays.stream(MowerAction.values()).toList();
        return Arrays.stream(line.split("")).map(m -> mowerActionsEnum.stream().filter(a -> a.getFrenchLetter().equalsIgnoreCase(m)).findFirst().get()).toList();
    }

    private void check(Pattern sizeLawn, String line) throws InputException {
        if (!sizeLawn.matcher(line).matches()) {
            throw new InputException("Invalid line : " + line);
        }
    }

}
