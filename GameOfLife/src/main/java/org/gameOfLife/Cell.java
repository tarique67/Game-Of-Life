package org.gameOfLife;

public class Cell {

    private Integer row;
    private Integer column;
    private State state;

    public Cell(Integer row, Integer column, State state) {
        this.row = row;
        this.column = column;
        this.state = state;
    }
}
