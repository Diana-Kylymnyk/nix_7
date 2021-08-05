import levelFirst.AreaOfTriangle;
import levelFirst.KnightMove;
import levelFirst.UniqueCharacters;
import levelSecond.StringCheck;
import levelThird.UserInterface;

import java.util.Scanner;

public class Module1Main {

    public static void printTasks() {
        System.out.println("\nModule_1(tasks):");
        System.out.println("1-Find count of unique symbols");
        System.out.println("2-Check the knight move");
        System.out.println("3-Find area of triangle");
        System.out.println("4-String validation");
        System.out.println("5-Game of life");
        System.out.println("0-Exit");
        System.out.print("Your choice >> ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printTasks();
            if (!sc.hasNextInt()) {
                System.out.print("This is not a number! Try again >> ");
                sc.next();
            }
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    UniqueCharacters.userInputAndRealization();
                    break;
                case 2:
                    KnightMove.userInputAndRealization();
                    break;
                case 3:
                    AreaOfTriangle.userInputAndRealization();
                    break;
                case 4:
                    StringCheck.userInputAndRealization();
                    break;
                case 5:
                    UserInterface.mainMenu();
                    break;
                case 0:
                    System.out.println("Exit.");
                    return;
                default:
                    System.out.println("Invalid input! Try again.");
            }
        }
    }
}
