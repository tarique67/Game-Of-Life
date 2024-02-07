package org.gameOfLife;

import org.gameOfLife.exceptions.StableStateReachedException;

import java.util.*;

public class Board {

    private final int rows;
    private final int columns;
    private Cell[][] cellsGrid;

    public Board(int rows, int columns, double percentageAliveCells) {
        if(rows==0 || columns==0)
            throw new IllegalArgumentException("Rows and columns cannot be zero.");

        this.rows = rows;
        this.columns = columns;
        this.cellsGrid = new Cell[rows][columns];
        setRandomPopulation(percentageAliveCells);
    }

    private int feedPopulationCount(double percentageSeed){
        return (int) ((percentageSeed/100) * (rows*columns));
    }

    private void setRandomPopulation(double percentageSeed){
        int alivePopulationCount = feedPopulationCount(percentageSeed);
        Random random = new Random();
        while(alivePopulationCount>0){
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);
            if(cellsGrid[row][column]==null){
                cellsGrid[row][column] = new Cell(row,column,State.ALIVE);
                alivePopulationCount--;
            }
        }
        feedDeadCells();
    }

    private void feedDeadCells() {
        for(int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                if(cellsGrid[i][j]==null) {
                    cellsGrid[i][j] = new Cell(i, j, State.DEAD);
                }
            }
        }
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

    public void nextGeneration() throws StableStateReachedException {
        Cell[][] nextGrid = new Cell[rows][columns];
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                List<int[]> neighboursCoordinates = new Neighbours(i,j).validNeighboursCoordinates(rows, columns);
                List<Cell> neighbourCells = neighboursCoordinates.stream().map( coordinates ->cellsGrid[coordinates[0]][coordinates[1]]).toList();

                nextGrid[i][j] = cellsGrid[i][j].evolve(neighbourCells);
            }
        }
        if(Arrays.deepEquals(cellsGrid,nextGrid))
            throw new StableStateReachedException("Reached a stable state.");

        cellsGrid = nextGrid;
    }

    public void print(){
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                System.out.print(cellsGrid[i][j].isDead() ? State.DEAD.getStateString()+" " : State.ALIVE.getStateString()+" ");
            }
            System.out.println();
        }
    }
}
