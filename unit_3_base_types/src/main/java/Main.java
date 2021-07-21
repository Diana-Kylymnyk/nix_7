import tasks.Task_1;
import tasks.Task_2;
import tasks.Task_3;

import java.util.Scanner;

public class Main {
    public static void printMenu() {
        System.out.println("Tasks:");
        System.out.println("1-Sum of numbers");
        System.out.println("2-Find count of letters");
        System.out.println("3-Count the end of the lesson");
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
                    Task_1.realization();
                    break;
                case 2:
                    Task_2.realization();
                    break;
                case 3:
                    Task_3.realization();
                    break;
                case 0:
                    System.out.println("Exit.");
                    return;
                default:
                    System.out.println("Incorrect value! Try again.\n");
            }
        }
    }
}
