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

    public boolean conditionSatisfiedToLive(List<Cell> neighbours) {
        int liveNeighbours = 0;
        for(Cell cell : neighbours){
            if(cell.row==row && cell.column==column)
                throw new IllegalArgumentException("A cell can not be it's own neighbour.");
            if(cell.state.equals(State.ALIVE))
                liveNeighbours++;
        }
        return liveNeighbours == 2 || liveNeighbours == 3;
    }

    public boolean conditionSatisfiedToReLive(List<Cell> neighbours) {
        if(state.equals(State.ALIVE))
            throw new IllegalArgumentException("Alive cells can not re-live.");
        int liveNeighbours = 0;
        for(Cell cell : neighbours){
            if(cell.row==row && cell.column==column)
                throw new IllegalArgumentException("A cell can not be it's own neighbour.");
            if(cell.state.equals(State.ALIVE))
                liveNeighbours++;
        }
        return liveNeighbours == 3;
    }

    public boolean switchState(List<Cell> neighbours) {
        if(state.equals(State.DEAD) && conditionSatisfiedToReLive(neighbours)){
            state = State.ALIVE;
            return true;
        }
        if(state.equals(State.ALIVE) && !conditionSatisfiedToLive(neighbours)) {
            state = State.DEAD;
            return true;
        }
        return false;
    }
}
