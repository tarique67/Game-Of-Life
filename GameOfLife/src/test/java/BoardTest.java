import org.gameOfLife.Board;
import org.gameOfLife.exceptions.StableStateReachedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void expectExceptionForBoardWithLessThan1RowAndColumns(){
        assertThrows(IllegalArgumentException.class, () ->{
            new Board(0,0, 10);
        });
    }

    @Test
    public void expectNoExceptionForBoardWithValidRowAndColumns(){
        assertDoesNotThrow( () ->{
            new Board(1,1,0);
        });
    }

    @Test
    void expectFalseForAllDeadCellsWhenPercentageAliveCellsIs10() {
        Board board = new Board(10,10, 10);

        assertFalse(board.allCellDead());
    }

    @Test
    void expectTrueForAllCellsDeadWithBoardOfAllDeadCells() {
        Board board = new Board(3,3, 0);

        assertTrue(board.allCellDead());
    }

    @Test
    void expectFalseForAllCellsDeadWithBoardOfSomeAliveCells() {
        Board board = new Board(3,3, 50);

        assertFalse(board.allCellDead());
    }

    @Test
    void expectAllCellDeadOnNextGenerationFor10x10GridWith1PercentPopulation() throws StableStateReachedException {
        Board board = new Board(10,10, 1);

        board.nextGeneration();

        assertTrue(board.allCellDead());
    }

    @Test
    void expectFalseForAllCellDeadOnNextGenerationFor10x10GridWith100PercentPopulation() throws StableStateReachedException {
        Board board = new Board(10,10, 100);

        board.nextGeneration();

        assertFalse(board.allCellDead());
    }

    @Test
    void expectAllCellDeadOnNextGenerationFor2x2GridWith25PercentPopulation() throws StableStateReachedException {
        Board board = new Board(2,2, 25);

        board.nextGeneration();

        assertTrue(board.allCellDead());
    }

    @Test
    void expectExceptionWhenStableStateReached() {
        Board board = new Board(2,2, 100);

        assertThrows(StableStateReachedException.class, ()-> board.nextGeneration());
    }

    @Test
    void expectNoExceptionWhenStableStateNotReached() {
        Board board = new Board(2,2, 25);

        assertDoesNotThrow(()-> board.nextGeneration());
    }
}
