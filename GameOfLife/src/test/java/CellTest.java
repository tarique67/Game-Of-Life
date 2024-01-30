import org.gameOfLife.Cell;
import org.gameOfLife.State;
import org.junit.jupiter.api.Test;

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
    void expect3LiveNeighboursWhenPassed2x2GridOfAllLiveCells() {
        Cell[][] grid = new Cell[][]{{new Cell(0,0,State.ALIVE),new Cell(0,1,State.ALIVE)},
                {new Cell(1,0,State.ALIVE),new Cell(1,1,State.ALIVE)}};
        int expected = 3;
        assertEquals(expected, grid[0][0].getAliveNeighbours(grid));
    }

    @Test
    void expect0LiveNeighboursWhenPassed2x2GridOf1LiveA3DeadCells() {
        Cell[][] grid = new Cell[][]{{new Cell(0,0,State.ALIVE),new Cell(0,1,State.DEAD)},
                {new Cell(1,0,State.DEAD),new Cell(1,1,State.DEAD)}};
        int expected = 0;
        assertEquals(expected, grid[0][0].getAliveNeighbours(grid));
    }

    @Test
    void expectAliveCellWhenDeadCellEvolvedWith3LiveCellsNeighbours() {
        Cell[][] grid = new Cell[][]{{new Cell(0,0,State.DEAD),new Cell(0,1,State.ALIVE)},
                {new Cell(1,0,State.ALIVE),new Cell(1,1,State.ALIVE)}};
        int neighbours = grid[0][0].getAliveNeighbours(grid);
        Cell expected = new Cell(0,0, State.ALIVE);
        assertEquals(expected, grid[0][0].evolve(neighbours));
    }

    @Test
    void expectDeadCellWhenAliveCellEvolvedWith1AliveCellPassedInNeighbours() {
        Cell[][] grid = new Cell[][]{{new Cell(0,0,State.ALIVE),new Cell(0,1,State.DEAD)},
                {new Cell(1,0,State.DEAD),new Cell(1,1,State.DEAD)}};
        int neighbours = grid[0][0].getAliveNeighbours(grid);
        Cell expected = new Cell(0,0, State.DEAD);
        assertEquals(expected, grid[0][0].evolve(neighbours));
    }

    @Test
    void expectAliveCellWhenAliveCellEvolvedWith2AliveCellPassedInNeighbours() {
        Cell[][] grid = new Cell[][]{{new Cell(0,0,State.ALIVE),new Cell(0,1,State.ALIVE)},
                {new Cell(1,0,State.DEAD),new Cell(1,1,State.ALIVE)}};
        int neighbours = grid[0][0].getAliveNeighbours(grid);
        Cell expected = new Cell(0,0, State.ALIVE);
        assertEquals(expected, grid[0][0].evolve(neighbours));
    }

    @Test
    void expectTrueForIsDeadIfCellStateIsDead() {
        Cell cell = new Cell(2,5,State.DEAD);
        assertTrue(cell.isDead());
    }
}
