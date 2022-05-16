package cinema;

import java.util.Scanner;

public class Cinema {

    public static int rows;
    public static int cols;
    public static char[][] myCinema;
    public static int ticketsSold = 0;
    public static int income;
    public static int totalIncome;

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        rows = getInput();
        System.out.println("Enter the number of seats in each row:");
        cols = getInput();

        myCinema = new char[rows + 1][cols + 1];
        buildCinema();

        printCinema();

        boolean quit = false;

        do {
            System.out.println("1. Show the seats\n2.Buy a ticket\n3. Statistics\n0. Exit");
            switch (getInput()) {
                case 1:
                    printCinema();
                    break;
                case 2:
                    buySeat();
                    break;
                case 3:
                    printStats();
                    break;
                case 0:
                    quit = true;
            }
        } while (!quit);

    }

    public static int getInput() {
        Scanner scanner = new Scanner((System.in));
        return scanner.nextInt();
    }

    public static void buildCinema() {
        myCinema[0][0] = ' ';

        for (int i = 1; i <= cols; i++) {
            myCinema[0][i] = (char) (i + 48);
        }

        for (int i = 1; i <= rows; i++) {
            myCinema[i][0] = (char) (i + 48);
        }

        for (int i = 1; i < myCinema.length; i++) {
            for (int j = 1; j < myCinema[0].length; j++) {
                myCinema[i][j] = 'S';
            }
        }

        if (rows * cols <= 60) {
            totalIncome = 10 * rows * cols;
        } else {
            totalIncome = (10 * cols *  (rows/2)) + (8 * cols * (rows - rows/2));
        }
    }

    public static void printCinema() {
        System.out.println("Cinema: ");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= cols; j++) {
                System.out.print(myCinema[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void buySeat() {

        boolean inputIsValid = false;
        int desiredRow;
        int desiredCol;
        do {
            System.out.println("Enter a row number:");
            desiredRow = getInput();
            System.out.println("Enter a seat number in that row:");
            desiredCol = getInput();

            if (desiredRow < 1 || desiredRow > rows || desiredCol < 1 || desiredCol > cols) {
                System.out.println("Wrong input!");
            } else if (myCinema[desiredRow][desiredCol] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                inputIsValid = true;
            }
        } while (!inputIsValid);
        int price = 0;

        if (rows * cols <= 60 || desiredRow <= (int) rows/2) {
            price = 10;
        } else {
            price = 8;
        }

        myCinema[desiredRow][desiredCol] = 'B';
        System.out.println("Ticket price: $" + price);
        ticketsSold++;
        income += price;
    }

    public static void printStats() {
        double percentage = 100 * ((double) ticketsSold/(rows*cols));
        System.out.println("Number of purchased tickets: " + ticketsSold);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalIncome);

    }

}