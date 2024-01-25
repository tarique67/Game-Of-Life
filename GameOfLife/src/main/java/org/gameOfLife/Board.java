package org.gameOfLife;

import java.util.Arrays;
import java.util.Objects;

public class Board {

    private final int rows;
    private final int columns;
    private State[][] states;

    public Board(int rows, int columns) {
        if(rows==0 || columns==0)
            throw new IllegalArgumentException("Rows and columns cannot be zero.");

        this.rows = rows;
        this.columns = columns;
        this.states = new State[rows][columns];
    }

    public int feedPopulation(double percentageSeed){
        int population = (int) Math.floor((percentageSeed/100 * (rows*columns)));
        if(population<1)
            throw new IllegalArgumentException("Population "+population+" for "+ percentageSeed+"% of rows and columns ");
        return population;
    }



}
