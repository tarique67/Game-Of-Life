package org.gameOfLife;

import java.util.List;
import java.util.Objects;

public class Cell {

    private final Integer row;
    private final Integer column;
    private final State state;

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

    public int getAliveNeighbours(List<Cell> neighbours) {
        int count = 0;
        for(Cell neighbour : neighbours) {
            if(neighbour.state == State.ALIVE)
                count++;
        }
        return count;
    }

    public Cell evolve(List<Cell> neighbours) {
        int liveNeighbours = getAliveNeighbours(neighbours);
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
