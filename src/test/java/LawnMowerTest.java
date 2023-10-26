import com.mower.lawnmower.lawnmower.LawnMower;
import com.mower.lawnmower.pojo.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LawnMowerTest {

    @Test
    public void ShouldLawnMowerWork_WithOneMower_Test() {
        List<MowerAction> actionList = new ArrayList<>();
        actionList.add(MowerAction.Left);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Left);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Left);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Left);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Forward);

        Program program = new Program(5, 5,
                List.of(
                        new MowerProgram(new MowerState(new Position(1, 2), Direction.North), actionList)));

        List<MowerState> result = new LawnMower().start(program);

        Assertions.assertThat(result)
                .hasSize(program.mowersProgram().size())
                .containsExactly(new MowerState(new Position(1, 3), Direction.North));
    }

    @Test
    public void ShouldLawnMowerWork_With2Mower_Test() {
        List<MowerAction> actionList = new ArrayList<>();
        actionList.add(MowerAction.Left);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Left);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Left);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Left);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Forward);

        Program program = new Program(5, 5,
                List.of(
                        new MowerProgram(new MowerState(new Position(1, 2), Direction.North), actionList),
                        new MowerProgram(new MowerState(new Position(1, 1), Direction.North), actionList)
                ));

        List<MowerState> result = new LawnMower().start(program);

        Assertions.assertThat(result)
                .hasSize(program.mowersProgram().size())
                .containsExactly(
                        new MowerState(new Position(1, 3), Direction.North),
                        new MowerState(new Position(1, 2), Direction.North)
                );
    }

    @Test
    public void ShouldLawnMowerWork_WithOutsiteGrid_Test() {
        List<MowerAction> actionList = new ArrayList<>();
        actionList.add(MowerAction.Left);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Right);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Forward);

        Program program = new Program(5, 5,
                List.of(
                        new MowerProgram(new MowerState(new Position(4, 4), Direction.East), actionList)
                ));

        List<MowerState> result = new LawnMower().start(program);

        Assertions.assertThat(result)
                .hasSize(program.mowersProgram().size())
                .containsExactly(
                        new MowerState(new Position(5, 5), Direction.East)
                );
    }

    @Test
    public void ShouldLawnMowerWork_WithOutsiteGrid2_Test() {
        List<MowerAction> actionList = new ArrayList<>();
        actionList.add(MowerAction.Right);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Right);
        actionList.add(MowerAction.Forward);
        actionList.add(MowerAction.Forward);

        Program program = new Program(5, 5,
                List.of(
                        new MowerProgram(new MowerState(new Position(1, 1), Direction.East), actionList)
                ));

        List<MowerState> result = new LawnMower().start(program);

        Assertions.assertThat(result)
                .hasSize(program.mowersProgram().size())
                .containsExactly(
                        new MowerState(new Position(0, 0), Direction.West)
                );
    }

    @Test
    public void ShouldLawnMowerWork_WithConflictMower_Test() {
        List<MowerAction> actionList = new ArrayList<>();
        actionList.add(MowerAction.Left);
        actionList.add(MowerAction.Forward);

        List<MowerAction> actionList2 = new ArrayList<>();
        actionList2.add(MowerAction.Right);
        actionList2.add(MowerAction.Forward);

        Program program = new Program(5, 5,
                List.of(
                        new MowerProgram(new MowerState(new Position(3, 3), Direction.North), actionList),
                        new MowerProgram(new MowerState(new Position(2, 2), Direction.West), actionList2)
                ));

        List<MowerState> result = new LawnMower().start(program);

        Assertions.assertThat(result)
                .hasSize(program.mowersProgram().size())
                .containsExactly(
                        new MowerState(new Position(2, 3), Direction.West),
                        new MowerState(new Position(2, 2), Direction.North)
                );
    }


}
