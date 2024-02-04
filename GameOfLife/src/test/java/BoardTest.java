import org.gameOfLife.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void expectExceptionForBoardWithLessThan1RowAndColumns(){
        assertThrows(IllegalArgumentException.class, () ->{
            new Board(0,0);
        });
    }

    @Test
    public void expectNoExceptionForBoardWithValidRowAndColumns(){
        assertDoesNotThrow( () ->{
            new Board(1,1);
        });
    }

    @Test
    public void expectPopulation5For5x5BoardAnd20PercentSeedPopulation(){
        int expected = 5;

        assertEquals(expected, new Board(5,5).feedPopulationCount(20));
    }

    @Test
    void expectFalseForAllDeadCellsWhenRandomPopulationSet10() {
        Board board = new Board(10,10);

        board.setRandomPopulation(10);

        assertFalse(board.allCellDead());
    }

    @Test
    void expectTrueForAllCellsDeadWithBoardOfAllDeadCells() {
        Board board = new Board(3,3);

        board.setRandomPopulation(0);

        assertTrue(board.allCellDead());
    }

    @Test
    void expectFalseForAllCellsDeadWithBoardOfSomeAliveCells() {
        Board board = new Board(3,3);

        board.setRandomPopulation(50);

        assertFalse(board.allCellDead());
    }

    @Test
    void expectAllCellDeadOnNextGenerationFor10x10GridWith1PercentPopulation() {
        Board board = new Board(10,10);
        board.setRandomPopulation(1);

        board.nextGeneration();

        assertTrue(board.allCellDead());
    }

    @Test
    void expectFalseForAllCellDeadOnNextGenerationFor10x10GridWith100PercentPopulation() {
        Board board = new Board(10,10);
        board.setRandomPopulation(100);

        board.nextGeneration();

        assertFalse(board.allCellDead());
    }
}
