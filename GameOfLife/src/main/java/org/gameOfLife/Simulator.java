package org.gameOfLife;

import java.util.Scanner;

public class Simulator {
    public static void main(String[] args) {
        new Simulator().start();

    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        Board board = takeInputAndInitializeBoard(scanner);
        int iteration = 1;
        boolean continueSimulation = true;
        while(!board.allCellDead() && continueSimulation){
            System.out.println("Iteration "+iteration++);
            board.print();
            board.evolve();
            System.out.println();
            if(board.allCellDead())
                System.out.println("All cells died. Game ended.");
            else{
                System.out.println("Press enter to continue simulation \n" +
                        "Press N and enter to exit.");
                String input = scanner.nextLine();
                if(input.equalsIgnoreCase("N"))
                    continueSimulation = false;
            }

        }
    }

    public Board takeInputAndInitializeBoard(Scanner scanner){
        System.out.println("Enter no. of rows.");
        int rows = scanner.nextInt();
        System.out.println("Enter no. of columns.");
        int columns = scanner.nextInt();
        System.out.println("Enter population feed percentage.");
        double percentageFeed = scanner.nextDouble();
        scanner.nextLine();
        Board board = new Board(rows,columns);
        board.setRandomPopulation(percentageFeed);
        return board;
    }
}