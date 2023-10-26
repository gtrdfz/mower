package com.mower.lawnmower.pojo;

import java.util.List;

public record MowerProgram(
        MowerState mowerState,
        List<MowerAction> mowerActions
) {
}
