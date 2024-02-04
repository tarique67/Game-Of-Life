package org.gameOfLife;

import java.util.ArrayList;
import java.util.List;

public class Neighbours {

    private final int[][] neighboursCoordinates;

    public Neighbours(int row, int column) {
        if(row<0 || column<0) {
            throw new IllegalArgumentException();
        }
        this.neighboursCoordinates = new int[8][2];
        this.neighboursCoordinates[0] = new int[]{row, column-1};
        this.neighboursCoordinates[1] = new int[]{row-1, column};
        this.neighboursCoordinates[2] = new int[]{row, column+1};
        this.neighboursCoordinates[3] = new int[]{row+1, column};
        this.neighboursCoordinates[4] = new int[]{row-1, column-1};
        this.neighboursCoordinates[5] = new int[]{row+1, column+1};
        this.neighboursCoordinates[6] = new int[]{row+1, column-1};
        this.neighboursCoordinates[7] = new int[]{row-1, column+1};
    }

    public List<int[]> validNeighboursCoordinates(int totalRows, int totalColumns){
        List<int[]> validNeighboursCoordinates = new ArrayList<>();
        for (int[] neighboursCoordinate : neighboursCoordinates) {
            int row = neighboursCoordinate[0];
            int column = neighboursCoordinate[1];
            if (row >= 0 && column >= 0 && row < totalRows && column < totalColumns)
                validNeighboursCoordinates.add(new int[]{row, column});
        }
        return validNeighboursCoordinates;
    }
}
