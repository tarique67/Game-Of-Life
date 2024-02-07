package org.gameOfLife;

import java.util.Scanner;

public class InputOutput {

    private final Scanner scanner;

    public InputOutput() {
        this.scanner = new Scanner(System.in);
    }

    public Board takeInputAndInitializeBoard(){
        while (true) {
            try{
                System.out.println("Enter no. of rows.");
                int rows = scanner.nextInt();
                System.out.println("Enter no. of columns.");
                int columns = scanner.nextInt();
                System.out.println("Enter population feed percentage.");
                double percentageFeed = scanner.nextDouble();
                if(percentageFeed>100 || percentageFeed<0){
                    System.out.println("Feed percentage should be between 0 and 100.");
                    continue;
                }
                scanner.nextLine();
                return new Board(rows,columns,percentageFeed);
            }catch (Exception exception){
                scanner.nextLine();
                System.out.println("Please enter valid row, column and seeding percentage. Only numbers allowed. ");
            }
        }
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
