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
    void expectPopulationCountLessThan10ForSetRandomPopulationOfGrid10x10With10PercentPopulation() {
        int expected = 10;
        assertEquals(expected, new Board(10,10).setRandomPopulation(10));
    }

    @Test
    void expectNeighboursCount8ForCell1_1OfBoard10x10() {
        int expected = 8;
        assertEquals(expected, new Board(10,10).neighboursList(1,1).size());
    }

    @Test
    void expectNeighboursCount3ForCell0_0OfBoard10x10() {
        int expected = 3;
        assertEquals(expected, new Board(10,10).neighboursList(0,0).size());
    }

    @Test
    void expectNeighboursCount3ForCell9_9OfBoard10x10() {
        int expected = 3;
        assertEquals(expected, new Board(10,10).neighboursList(9,9).size());
    }

    @Test
    void expectExceptionForNegativeIndexPassedToGetNeighboursList() {
        assertThrows(IllegalArgumentException.class, ()-> new Board(10,10).neighboursList(-1,-1));
    }

    @Test
    void expectExceptionForIndexPassedToGetNeighboursListGreaterThanEqualToRowsColumns() {
        assertThrows(IllegalArgumentException.class, ()-> new Board(10,10).neighboursList(10,10));
    }

    @Test
    void expectNeighboursCount5ForCell6_9OfBoard10x10() {
        int expected = 5;
        assertEquals(expected, new Board(10,10).neighboursList(6,9).size());
    }

    @Test
    void expectNeighboursCount5ForCell0_6OfBoard10x10() {
        int expected = 5;
        assertEquals(expected, new Board(10,10).neighboursList(6,9).size());
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
    void expectBoardToBeTestedToBeEqualToBoardUnderTestWhenBothEvolve() {
        Board expectedBoard = new Board(2,2);
        expectedBoard.setRandomPopulation(30);
        Board boardUnderTest = new Board(2,2);
        boardUnderTest.setRandomPopulation(30);
        expectedBoard.evolve();
        boardUnderTest.evolve();
        assertEquals(expectedBoard, boardUnderTest);
    }
}
