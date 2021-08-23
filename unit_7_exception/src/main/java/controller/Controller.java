package controller;

import exceptions.DateException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                menu(position);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                menu(position);
            }
        } catch (IOException | DateException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("1-Difference between dates");
        System.out.println("2-Addition");
        System.out.println("3-Subtraction");
        System.out.println("4-Sorting");
        System.out.println("0-Exit");
        System.out.println();
    }

    private void menu(String position) throws DateException {
        switch (position) {
            case "1":
                DifferenceBetweenDates.difference();
                break;
            case "2":
                AdditionAndSubtraction.addition();
                break;
            case "3":
                AdditionAndSubtraction.subtraction();
                break;
            case "4":
                SortingListOfDates.sorting();
                break;
            case "0":
                System.exit(0);
        }
        runNavigation();
    }
}
