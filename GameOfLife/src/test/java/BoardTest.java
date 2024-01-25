import org.gameOfLife.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {

    @Test
    public void expectExceptionForBoardWithLessThan1RowAndColumns(){
        assertThrows(IllegalArgumentException.class, () ->{
            new Board(0,0);
        });
    }

    @Test
    public void expectPopulation5For5x5BoardAnd20PercentSeedPopulation(){
        int expected = 5;
        assertEquals(expected, new Board(5,5).feedPopulation(20));
    }

    @Test
    public void expectExceptionWhenFeedingPopulation50ForGrid1x1(){
        assertThrows(IllegalArgumentException.class, () ->{
           new Board(1,1).feedPopulation(50);
        });
    }

    @Test
    public void expectExceptionWhenFeedingPopulation30ForGrid3x1(){
        assertThrows(IllegalArgumentException.class, () ->{
            new Board(1,1).feedPopulation(50);
        });
    }


}
