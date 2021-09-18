package controller;

import tasks.levelFirst.DatesWithoutDelimiters;
import tasks.levelSecond.FindFirstUniqueName;
import tasks.levelThird.FindTheMostProfitableWay;

import java.util.Scanner;

public class Controller {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void mainMenu() {
        boolean flag = true;
        while (flag) {
            System.out.print("\nMenu:\n" +
                    "1-Return dates without delimiters\n" +
                    "2-Find first unique name from list\n" +
                    "3-Find the cheapest way\n" +
                    "0-Exit\n" +
                    "Your choice>>");

            String index = SCANNER.nextLine();
            switch (index) {
                case "1":
                    DatesWithoutDelimiters.realization();
                    break;
                case "2":
                    FindFirstUniqueName.realization();
                    break;
                case "3":
                    FindTheMostProfitableWay.realization();
                    break;
                case "0":
                    flag = false;
            }
        }
    }
}
