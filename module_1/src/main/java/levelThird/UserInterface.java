package levelThird;

import java.util.Scanner;

import static levelThird.GameOfLife.currentGame;

public class UserInterface {

    public static void printMainMenu() {
        System.out.println("\nMAIN MENU");
        System.out.println("1-Generate board");
        System.out.println("2-Print board");
        System.out.println("3-Step simulation by 1 frame");
        System.out.println("0-Finish the game");
        System.out.print("Your choice >> ");
    }

    public static void printBoardMenu() {
        System.out.println("\nGENERATE BOARD");
        System.out.println("1-Generate from a template");
        System.out.println("2-Generate a random board");
        System.out.print("Your choice >> ");
    }

    public static void printTemplateMenu() {
        System.out.println("\nGENERATE BOARD FROM TEMPLATE");
        System.out.println("1-Beehive (Still life)");
        System.out.println("2-Toad (Simple oscillator)");
        System.out.println("3-Glider (Simple spaceship)");
        System.out.print("Your choice >> ");
    }

    public static GameOfLife generateRandomBoard() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter board height: ");
        int height = in.nextInt();
        System.out.print("Enter board width: ");
        int width = in.nextInt();

        GameOfLife randomBoard = new GameOfLife(height, width);
        randomBoard.fillRandom();
        return randomBoard;
    }

    public static void checkInput(Scanner sc){
        if (!sc.hasNextInt()) {
            System.out.print("This is not a number! Try again >> ");
            sc.next();
        }
    }

    public static void generateBoard(Scanner sc) {
        printBoardMenu();
        checkInput(sc);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                printTemplateMenu();
                checkInput(sc);
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        currentGame = new GameOfLife(Templates.BEEHIVE);
                        System.out.println("You chose: Beehive");
                        break;
                    case 2:
                        currentGame = new GameOfLife(Templates.TOAD);
                        System.out.println("You chose: Toad");
                        break;
                    case 3:
                        currentGame = new GameOfLife(Templates.GLIDER);
                        System.out.println("You chose: Glider");
                        break;
                    default:
                        System.out.println("Invalid input!");
                        break;
                }
                break;
            case 2:
                currentGame = generateRandomBoard();
                System.out.println("Generated a random board");
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
    }

    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMainMenu();
            checkInput(sc);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    generateBoard(sc);
                    break;
                case 2:
                    if (currentGame == null) {
                        System.out.println("Impossible simulation!");
                        break;
                    }
                    currentGame.printBoard();
                    break;
                case 3:
                    if (currentGame == null) {
                        System.out.println("Impossible simulation!");
                        break;
                    }
                    currentGame.nextState();
                    currentGame.printBoard();
                    break;
                case 0:
                    System.out.println("Game is over.");
                    return;
                default:
                    System.out.println("Invalid input!\n");
                    break;
            }
        }
    }
}