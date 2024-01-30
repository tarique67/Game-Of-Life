package org.gameOfLife;

import java.util.*;

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
        return (int) ((percentageSeed/100) * (rows*columns));
    }

    public int setRandomPopulation(double percentageSeed){
        int populationCount = feedPopulationCount(percentageSeed);
        int alivePopulationCountSet = 0;
        Random random = new Random();
        while(alivePopulationCountSet<populationCount){
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);
            if(cellsGrid[row][column]==null){
                cellsGrid[row][column] = new Cell(row,column,State.ALIVE);
                alivePopulationCountSet++;
            }
        }
        for(int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                if(cellsGrid[i][j]==null) {
                    cellsGrid[i][j] = new Cell(i, j, State.DEAD);
                }
            }
        }
        return alivePopulationCountSet;
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

    public void nextGeneration() {
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                cellsGrid[i][j] = cellsGrid[i][j].evolve(cellsGrid);
            }
        }
    }

    public void print(){
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                System.out.print(cellsGrid[i][j].isDead() ? State.DEAD.getSateString()+" " : State.ALIVE.getSateString()+" ");
            }
            System.out.println();
        }
    }
}
