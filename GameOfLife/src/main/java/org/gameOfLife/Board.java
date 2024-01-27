package org.gameOfLife;

import java.util.Random;

public class Board {

    private final int rows;
    private final int columns;
    private Cell[][] cellsGrid;

    public Board(int rows, int columns) {
        if(rows==0 || columns==0)
            throw new IllegalArgumentException("Rows and columns cannot be zero.");

        this.rows = rows;
        this.columns = columns;
        this.cellsGrid = new Cell[rows][columns];
    }

    public int feedPopulationCount(double percentageSeed){
        int population = (int) Math.floor((percentageSeed/100 * (rows*columns)));
        if(population<1)
            throw new IllegalArgumentException("Population "+population+" for "+ percentageSeed+"% of rows and columns ");
        return population;
    }

    public int setRandomPopulation(double percentageSeed){
        int populationCount = feedPopulationCount(percentageSeed);
        int alivePopulationCountSet = 0;
        Random random = new Random(2);
        for(int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                if(random.nextInt(2) == 1 && populationCount-- > 0) {
                    cellsGrid[i][j] = new Cell(i,j,State.ALIVE);
                    alivePopulationCountSet++;
                } else {
                    cellsGrid[i][j] = new Cell(i, j, State.DEAD);
                }
            }
        }
        return alivePopulationCountSet;
    }

}
