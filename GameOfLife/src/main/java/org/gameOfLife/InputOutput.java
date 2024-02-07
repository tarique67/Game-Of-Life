package org.gameOfLife;

import java.util.Scanner;

public class InputOutput {

    private final Scanner scanner;

    public InputOutput() {
        this.scanner = new Scanner(System.in);
    }

    public Board takeInputAndInitializeBoard(){
        System.out.println("Enter no. of rows.");
        int rows = scanner.nextInt();
        System.out.println("Enter no. of columns.");
        int columns = scanner.nextInt();
        System.out.println("Enter population feed percentage.");
        double percentageFeed = scanner.nextDouble();
        scanner.nextLine();
        return new Board(rows,columns,percentageFeed);
    }

    public void print(Board board, int iteration) {
        System.out.println("Iteration " + iteration);
        board.print();
        if(board.allCellDead())
            System.out.println("All cells died. Game ended.");
        else{
            System.out.println("Press enter to continue simulation \n" +
                    "Press N and enter to exit.");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("N"))
                System.exit(0);
        }
    }
}
