import com.mower.lawnmower.pojo.Direction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    public void shouldHaveRightOrder() {

        Assertions.assertThat(Direction.values())
                .hasSize(8)
                .containsExactly(Direction.North, Direction.NorthEast, Direction.East, Direction.SouthEast,
                        Direction.South, Direction.SouthWest, Direction.West, Direction.NorthWest);
    }

}
