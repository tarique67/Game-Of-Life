package org.gameOfLife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        return (int) Math.floor((percentageSeed/100 * (rows*columns)));
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

    public List<Cell> neighboursList(int row, int column) {
        if(row>=10 || column>=10 || row<0 || column<0){
            throw new IllegalArgumentException("Indices can not be negative.");
        }
        List<Cell> neighbours = new ArrayList<>();
        if(row==0 && column == 0){
            neighbours.addAll(Arrays.asList(cellsGrid[row][column+1], cellsGrid[row+1][column], cellsGrid[row+1][column+1]));
        } else if(row==rows-1 && column==columns-1){
            neighbours.addAll(Arrays.asList(cellsGrid[row][column-1], cellsGrid[row-1][column], cellsGrid[row-1][column-1]));
        } else if(row==0 && column==columns-1){
            neighbours.addAll(Arrays.asList(cellsGrid[row][column-1], cellsGrid[row+1][column], cellsGrid[row+1][column-1]));
        } else if(row==rows-1 && column==0){
            neighbours.addAll(Arrays.asList(cellsGrid[row][column+1], cellsGrid[row-1][column], cellsGrid[row-1][column+1]));
        } else if(row==0 && column>0){
            neighbours.addAll(Arrays.asList(cellsGrid[row][column+1], cellsGrid[row][column-1], cellsGrid[row+1][column], cellsGrid[row+1][column+1], cellsGrid[row+1][column-1]));
        } else if(row==rows-1 && column>0){
            neighbours.addAll(Arrays.asList(cellsGrid[row][column+1], cellsGrid[row][column-1], cellsGrid[row-1][column], cellsGrid[row-1][column+1], cellsGrid[row-1][column-1]));
        } else if(row>0 && column==0){
            neighbours.addAll(Arrays.asList(cellsGrid[row+1][column], cellsGrid[row-1][column], cellsGrid[row][column+1], cellsGrid[row-1][column+1], cellsGrid[row+1][column+1]));
        } else if(row>0 && column==columns-1){
            neighbours.addAll(Arrays.asList(cellsGrid[row+1][column], cellsGrid[row-1][column], cellsGrid[row][column-1], cellsGrid[row-1][column-1], cellsGrid[row+1][column-1]));
        } else {
            neighbours.addAll(Arrays.asList(cellsGrid[row][column-1], cellsGrid[row-1][column], cellsGrid[row][column+1], cellsGrid[row+1][column],
                cellsGrid[row-1][column-1], cellsGrid[row+1][column+1], cellsGrid[row+1][column-1], cellsGrid[row-1][column+1]));
        }
        return neighbours;
    }

    public boolean allCellDead() {
        int deadCellCount = 0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                if(cellsGrid[i][j].equals(new Cell(i,j,State.DEAD))){
                    deadCellCount++;
                }
            }
        }
        return deadCellCount==rows*columns;
    }
}
