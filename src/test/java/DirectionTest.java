import com.mower.lawnmower.pojo.Direction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    public void shouldHaveRightOrder() {

        Assertions.assertThat(Direction.values())
                .hasSize(4)
                .containsExactly(Direction.North, Direction.East, Direction.South, Direction.West);
    }

}
