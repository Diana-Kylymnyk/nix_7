import realizationClasses.*;

import java.util.Scanner;

public class Main {
    public static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1-Simple reverse");
        System.out.println("2-Substring reverse");
        System.out.println("3-Reverse by indexes");
        System.out.println("4-Reverse by symbols");
        System.out.println("5-Reverse by strings");
        System.out.println("0-Exit");
        System.out.print("Your choice >> ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            if (!sc.hasNextInt()) {
                System.out.print("This is not a number! Try again >> ");
                sc.next();
            }
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    SimpleReverse.realization();
                    break;
                case 2:
                    SubstringReverse.realization();
                    break;
                case 3:
                    ReverseByIndexes.realization();
                    break;
                case 4:
                    ReverseBySymbols.realization();
                    break;
                case 5:
                    ReverseByStrings.realization();
                    break;
                case 0:
                    System.out.println("Exit.");
                    return;
                default:
                    System.out.println("Incorrect value! Try again.");
            }
        }
    }
}
