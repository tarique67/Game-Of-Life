package org.gameOfLife;

import java.util.List;
import java.util.Objects;

public class Cell {

    private final Integer row;
    private final Integer column;
    private final State state;
    private int[][] neighboursLocations;

    public Cell(Integer row, Integer column, State state) {
        if(row < 0 || column < 0)
            throw new IllegalArgumentException("Row and column cannot be negative");
        this.row = row;
        this.column = column;
        this.state = state;
        this.neighboursLocations = new int[8][2];
        this.neighboursLocations[0] = new int[]{row, column-1};
        this.neighboursLocations[1] = new int[]{row-1, column};
        this.neighboursLocations[2] = new int[]{row, column+1};
        this.neighboursLocations[3] = new int[]{row+1, column};
        this.neighboursLocations[4] = new int[]{row-1, column-1};
        this.neighboursLocations[5] = new int[]{row+1, column+1};
        this.neighboursLocations[6] = new int[]{row+1, column-1};
        this.neighboursLocations[7] = new int[]{row-1, column+1};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return Objects.equals(row, cell.row) && Objects.equals(column, cell.column) && state == cell.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, state);
    }

    public int getAliveNeighbours(Cell[][] grid) {
        int count = 0;
        for(int i=0; i<neighboursLocations.length; i++){
            int row = neighboursLocations[i][0];
            int column = neighboursLocations[i][1];
            if(row>=0 && column>=0 && row< grid.length && column<grid[0].length)
                if(grid[row][column].state == State.ALIVE)
                    count++;
        }
        return count;
    }

    public Cell evolve(int liveNeighbours) {
        if(state.equals(State.DEAD) && liveNeighbours==3){
            return new Cell(this.row, this.column, State.ALIVE);
        }
        if(state.equals(State.ALIVE) && liveNeighbours>=2 && liveNeighbours<=3) {
            return new Cell(this.row, this.column, State.ALIVE);
        }
        return new Cell(this.row, this.column, State.DEAD);
    }

    public boolean isDead() {
        return this.state==State.DEAD;
    }
}
