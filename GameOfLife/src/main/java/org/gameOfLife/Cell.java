package org.gameOfLife;

import java.util.List;
import java.util.Objects;

public class Cell {

    private final Integer row;
    private final Integer column;
    private State state;

    public Cell(Integer row, Integer column, State state) {
        if(row < 0 || column < 0)
            throw new IllegalArgumentException("Row and column cannot be negative");
        this.row = row;
        this.column = column;
        this.state = state;
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

    public boolean isDead() {
        return this.state==State.DEAD;
    }
}
