package org.gameOfLife;

import java.util.List;

public class Cell {

    private Integer row;
    private Integer column;
    private State state;

    public Cell(Integer row, Integer column, State state) {
        if(row < 0 || column < 0)
            throw new IllegalArgumentException("Row and column cannot be negative");
        this.row = row;
        this.column = column;
        this.state = state;
    }

    public boolean conditionSatisfiedToLive(List<Cell> neighbours){
        int liveNeighbours = 0;
        for(Cell cell : neighbours){
            if(cell.state.equals(State.ALIVE))
                liveNeighbours++;
        }
        return liveNeighbours == 2 || liveNeighbours == 3;
    }
}
