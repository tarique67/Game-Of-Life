package org.gameOfLife;

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
}
