package org.gameOfLife;

import org.gameOfLife.exceptions.StableStateReachedException;

public class Simulator {
    public static void main(String[] args) {
        new Simulator().start();
    }

    public void start(){
        InputOutput inputOutput = new InputOutput();
        Board board = inputOutput.takeInputAndInitializeBoard();
        int iteration = 0;
        inputOutput.print(board,iteration++);
        while(!board.allCellDead()){
            try {
                board.nextGeneration();
            } catch (StableStateReachedException e) {
                System.out.println("-------------" + e.getMessage() + "-------------");
                System.exit(0);
            }
            inputOutput.print(board, iteration++);
        }
    }

}