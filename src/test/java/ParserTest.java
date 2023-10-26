import com.mower.lawnmower.exceptions.InputException;
import com.mower.lawnmower.parser.Parser;
import com.mower.lawnmower.pojo.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class ParserTest {

    @Test
    public void shouldCreateProgramWhenScannerHaveRightInput_Test() throws InputException {
        List<String> lines = List.of(
                "5 5",
                "1 2 N",
                "GA",
                "3 3 E",
                "AD");

        Program program = new Parser().parse(lines);

        Assertions.assertThat(program).isEqualTo(
                new Program(5, 5,
                        List.of(
                                new MowerProgram(new MowerState(new Position(1, 2), Direction.North), List.of(MowerAction.Left, MowerAction.Forward)),
                                new MowerProgram(new MowerState(new Position(3, 3), Direction.East), List.of(MowerAction.Forward, MowerAction.Right))
                        ))
        );

    }

    @Test
    public void shouldRaiseExceptionWithWrongInput_OutOfGrid_Test() {
        List<String> lines = List.of(
                "5 5",
                "6 2 N",
                "GA");
        Assertions.assertThatThrownBy(() -> {
                    new Parser().parse(lines);
                }).isInstanceOf(InputException.class)
                .hasMessageContaining("out of grid");
    }

    @Test
    public void shouldRaiseExceptionWithWrongInput_OddLine_Test() {
        List<String> lines = List.of(
                "5 5",
                "6 2 N",
                "GA", "G");
        Assertions.assertThatThrownBy(() -> {
                    new Parser().parse(lines);
                }).isInstanceOf(InputException.class)
                .hasMessageContaining("odd");
    }

}
