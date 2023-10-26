package com.mower.lawnmower.pojo;

import java.util.List;

public record Program(
        int gridX,
        int gridY,
        List<MowerProgram> mowersProgram
) {
}
