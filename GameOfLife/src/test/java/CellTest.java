import org.gameOfLife.Cell;
import org.gameOfLife.State;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    void expect2CellToBeEqualWithSameIndicesAndState() {
        Cell cell = new Cell(1,1, State.DEAD);

        assertEquals(cell, new Cell(1,1,State.DEAD));
    }

    @Test
    void expect2CellToNotBeEqualWithDifferentIndicesAndState() {
        Cell cell = new Cell(1,1, State.DEAD);

        assertNotEquals(cell, new Cell(0,0,State.ALIVE));
    }

    @Test
    void expect3LiveNeighboursWhenPassedListOf3AllLiveCells() {
        Cell cell = new Cell(0,0,State.DEAD);
        List<Cell> neighbours = new ArrayList<>();
        neighbours.add(new Cell(0,1,State.ALIVE));
        neighbours.add(new Cell(1,1,State.ALIVE));
        neighbours.add(new Cell(1,0,State.ALIVE));
        int expected = 3;

        assertEquals(expected, cell.getAliveNeighbours(neighbours));
    }

    @Test
    void expect0LiveNeighboursWhenPassed2x2GridOf1LiveA3DeadCells() {
        Cell cell = new Cell(0,0,State.DEAD);
        List<Cell> neighbours = new ArrayList<>();
        neighbours.add(new Cell(0,1,State.DEAD));
        neighbours.add(new Cell(1,1,State.DEAD));
        neighbours.add(new Cell(1,0,State.DEAD));
        int expected = 0;

        assertEquals(expected, cell.getAliveNeighbours(neighbours));
    }

    @Test
    void expectAliveCellWhenDeadCellEvolvedWith3LiveCellsNeighbours() {
        Cell cell = new Cell(0,0,State.DEAD);
        List<Cell> neighbours = new ArrayList<>();
        neighbours.add(new Cell(0,1,State.ALIVE));
        neighbours.add(new Cell(1,1,State.ALIVE));
        neighbours.add(new Cell(1,0,State.ALIVE));
        Cell expected = new Cell(0,0,State.ALIVE);

        assertEquals(expected, cell.evolve(neighbours));
    }

    @Test
    void expectDeadCellWhenAliveCellEvolvedWith1AliveCellPassedInNeighbours() {
        Cell cell = new Cell(0,0,State.ALIVE);
        List<Cell> neighbours = new ArrayList<>();
        neighbours.add(new Cell(0,1,State.ALIVE));
        neighbours.add(new Cell(1,1,State.DEAD));
        neighbours.add(new Cell(1,0,State.DEAD));
        Cell expected = new Cell(0,0,State.DEAD);

        assertEquals(expected, cell.evolve(neighbours));
    }

    @Test
    void expectAliveCellWhenAliveCellEvolvedWith2AliveCellPassedInNeighbours() {
        Cell cell = new Cell(0,0,State.ALIVE);
        List<Cell> neighbours = new ArrayList<>();
        neighbours.add(new Cell(0,1,State.ALIVE));
        neighbours.add(new Cell(1,1,State.ALIVE));
        neighbours.add(new Cell(1,0,State.DEAD));
        Cell expected = new Cell(0,0,State.ALIVE);

        assertEquals(expected, cell.evolve(neighbours));
    }

    @Test
    void expectTrueForIsDeadIfCellStateIsDead() {
        Cell cell = new Cell(2,5,State.DEAD);
        assertTrue(cell.isDead());
    }
}
