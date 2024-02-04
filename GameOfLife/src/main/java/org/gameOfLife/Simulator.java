package org.gameOfLife;

public class Simulator {
    public static void main(String[] args) {
        new Simulator().start();
    }

    public void start(){
        InputOutput inputOutput = new InputOutput();
        Board board = inputOutput.takeInputAndInitializeBoard();
        int iteration = 0;
        inputOutput.printOutput(board,iteration++);
        while(!board.allCellDead()){
            board.nextGeneration();
            inputOutput.printOutput(board, iteration++);
        }
    }

}