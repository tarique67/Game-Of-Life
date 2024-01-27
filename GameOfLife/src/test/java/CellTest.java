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
    void expectTrueForConditionSatisfiedToLiveWith2LiveNeighbours() {
        List<Cell> neighbours = Arrays.asList(new Cell(0,0,State.ALIVE), new Cell(0,1,State.ALIVE), new Cell(1,1,State.DEAD));
        assertTrue(new Cell(1,0,State.ALIVE).conditionSatisfiedToLive(neighbours));
    }

    @Test
    void expectFalseForConditionSatisfiedToLiveWith1LiveNeighbour() {
        List<Cell> neighbours = Arrays.asList(new Cell(0,0,State.ALIVE), new Cell(0,1,State.DEAD), new Cell(1,1,State.DEAD));
        assertFalse(new Cell(1,0,State.ALIVE).conditionSatisfiedToLive(neighbours));
    }

    @Test
    void expectExceptionForConditionSatisfiedToLiveIfSameCellPassedInNeighboursList() {
        List<Cell> neighbours = Arrays.asList(new Cell(0,0,State.ALIVE), new Cell(0,1,State.ALIVE), new Cell(1,1,State.DEAD), new Cell(1,0,State.ALIVE));
        assertThrows(IllegalArgumentException.class ,() -> new Cell(1,0,State.ALIVE).conditionSatisfiedToLive(neighbours));
    }

    @Test
    void expectTrueForConditionSatisfiedToReLiveWith3LiveNeighbours() {
        List<Cell> neighbours = Arrays.asList(new Cell(0,0,State.ALIVE), new Cell(0,1,State.ALIVE), new Cell(1,1,State.ALIVE));
        assertTrue(new Cell(1,0,State.DEAD).conditionSatisfiedToReLive(neighbours));
    }

    @Test
    void expectFalseForConditionSatisfiedToReLiveWith2LiveNeighbours() {
        List<Cell> neighbours = Arrays.asList(new Cell(0,0,State.ALIVE), new Cell(0,1,State.ALIVE), new Cell(1,1,State.DEAD));
        assertFalse(new Cell(1,0,State.DEAD).conditionSatisfiedToReLive(neighbours));
    }

    @Test
    void expectExceptionForConditionSatisfiedToReLiveIfSameCellPassedInNeighboursList() {
        List<Cell> neighbours = Arrays.asList(new Cell(0,0,State.ALIVE), new Cell(0,1,State.ALIVE), new Cell(1,1,State.DEAD), new Cell(1,0,State.ALIVE));
        Exception exception = assertThrows(IllegalArgumentException.class ,() -> new Cell(1,0,State.DEAD).conditionSatisfiedToReLive(neighbours));
        assertEquals("A cell can not be it's own neighbour.", exception.getMessage() );
    }

    @Test
    void expectExceptionForConditionSatisfiedToReLiveIfCellAlreadyAlive() {
        List<Cell> neighbours = Arrays.asList(new Cell(0,0,State.ALIVE), new Cell(0,1,State.ALIVE), new Cell(1,1,State.DEAD), new Cell(1,0,State.ALIVE));
        Exception exception = assertThrows(IllegalArgumentException.class ,() -> new Cell(1,0,State.ALIVE).conditionSatisfiedToReLive(neighbours));
        assertEquals("Alive cells can not re-live.", exception.getMessage() );
    }

    @Test
    void expectTrueForSwitchStateOfDeadCellWith3LiveCellsPassedAsNeighbours() {
        List<Cell> neighbours = Arrays.asList(new Cell(0,0,State.ALIVE), new Cell(0,1,State.ALIVE), new Cell(1,1,State.ALIVE));
        assertTrue(new Cell(1,0, State.DEAD).switchState(neighbours));
    }

    @Test
    void expectTrueForSwitchStateOfAliveCellWith1AliveCellPassedInNeighbours() {
        List<Cell> neighbours = Arrays.asList(new Cell(0,0,State.ALIVE), new Cell(0,1,State.DEAD), new Cell(1,1,State.DEAD));
        assertTrue(new Cell(1,0, State.ALIVE).switchState(neighbours));
    }

    @Test
    void expectFalseForSwitchStateOfAliveCellWith2AliveCellPassedInNeighbours() {
        List<Cell> neighbours = Arrays.asList(new Cell(0,0,State.ALIVE), new Cell(0,1,State.ALIVE), new Cell(1,1,State.DEAD));
        assertFalse(new Cell(1,0, State.ALIVE).switchState(neighbours));
    }

    @Test
    void expectExceptionForSwitchStateOfAliveCellIfSameCellPassedInNeighboursList() {
        List<Cell> neighbours = Arrays.asList(new Cell(0,0,State.ALIVE), new Cell(0,1,State.ALIVE), new Cell(1,1,State.DEAD));
        assertThrows(IllegalArgumentException.class,() -> new Cell(0,0, State.ALIVE).switchState(neighbours));
    }
}
