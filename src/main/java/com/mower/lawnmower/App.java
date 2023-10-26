package com.mower.lawnmower;

import com.mower.lawnmower.exceptions.InputException;
import com.mower.lawnmower.lawnmower.LawnMower;
import com.mower.lawnmower.parser.Parser;
import com.mower.lawnmower.pojo.MowerState;
import com.mower.lawnmower.pojo.Program;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class App {

    private final LawnMower lawnmower = new LawnMower();
    private final Parser parser = new Parser();

    public void start(String[] args) throws InputException {
        List<String> lines = readFile(args);
        Program program = parser.parse(lines);
        List<MowerState> outputPositionList = lawnmower.start(program);
        writeResult(outputPositionList, args);
    }

    private List<String> readFile(String[] args) throws InputException {
        try {
            return Files.readAllLines(Path.of(args[0]));
        } catch (IOException e) {
            throw new InputException(e.getMessage());
        }
    }

    private void writeResult(List<MowerState> outputPositionList, String[] args) throws InputException {
        List<String> linesToWrite = outputPositionList.stream().map(MowerState::print).toList();
        try {
            Path path = Path.of(args[1]);
            if (Files.exists(path)) Files.delete(path);
            Files.createFile(path);
            Files.write(path, linesToWrite);
        } catch (IOException e) {
            throw new InputException(e.getMessage());
        }
    }

}
