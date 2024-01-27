import org.gameOfLife.Cell;
import org.gameOfLife.State;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    void expectNoExceptionInCellCreationWithValidRowColumnAndState() {
        assertDoesNotThrow(()->{
            new Cell(0,0,State.DEAD);
        });
    }

    @Test
    void expectExceptionWhenCreatingCellWithNegativeRowOrColumn() {
        assertThrows(IllegalArgumentException.class, () -> new Cell(-1, 0, State.DEAD));
    }

    @Test
    void expectTrueForConditionSatisfiedToLiveWith3LiveNeighbours() {
        List<Cell> neighbours = Arrays.asList(new Cell(0,0,State.ALIVE), new Cell(0,1,State.ALIVE), new Cell(1,1,State.ALIVE));
        assertTrue(new Cell(1,0,State.ALIVE).conditionSatisfiedToLive(neighbours));
    }

    @Test
    void expectFalseForConditionSatisfiedToLiveWith2LiveNeighbours() {
        List<Cell> neighbours = Arrays.asList(new Cell(0,0,State.ALIVE), new Cell(0,1,State.ALIVE), new Cell(1,1,State.DEAD));
        assertTrue(new Cell(1,0,State.ALIVE).conditionSatisfiedToLive(neighbours));
    }
}
