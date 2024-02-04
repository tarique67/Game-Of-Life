import org.gameOfLife.Neighbours;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NeighboursTest {

    @Test
    void expectExceptionWhenNeighboursCreatedWithNegativeValues() {
        assertThrows(IllegalArgumentException.class, ()-> new Neighbours(-1, 0));
    }

    @Test
    void expectNoExceptionWhenNeighboursCreatedWithValidValues() {
        assertDoesNotThrow(()-> new Neighbours(2, 0));
    }

    @Test
    void expectValidNeighboursCoordinates() {
        Neighbours neighbours = new Neighbours(0,0);
        List<int[]> expected = new ArrayList<>();
        expected.add(new int[]{0,1});
        expected.add(new int[]{1,0});
        expected.add(new int[]{1,1});

        List<int[]> neighboursCoordinates = neighbours.validNeighboursCoordinates(3,3);

        for(int i=0; i<neighboursCoordinates.size(); i++){
            assertEquals(expected.get(i)[0], neighboursCoordinates.get(i)[0]);
            assertEquals(expected.get(i)[1], neighboursCoordinates.get(i)[1]);
        }
    }
}
