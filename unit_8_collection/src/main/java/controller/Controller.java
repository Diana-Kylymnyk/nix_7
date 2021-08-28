package controller;

import service.MathSet;

import java.util.Scanner;

public class Controller {

    private static final MathSet<Integer> numbers = new MathSet<>();
    private static final Scanner in = new Scanner(System.in);

    public void printMainMenu() {
        System.out.println();
        System.out.println("MAIN MENU:");
        System.out.println("1-Add numbers");
        System.out.println("2-Sort set of numbers");
        System.out.println("3-Find max/min value");
        System.out.println("4-Find average/median");
        System.out.println("5-Join/intersection");
        System.out.println("6-Cut");
        System.out.println("7-Clear");
        System.out.println("8-Print");
        System.out.println("0-Exit");
        System.out.print("Your choice >> ");
    }

    public void mainMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMainMenu();
            checkInput(sc);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addElements(numbers);
                    break;
                case 2:
                    sortMathSet(sc);
                    break;
                case 3:
                    findMinOrMaxValue(sc);
                    break;
                case 4:
                    findAverageOrMedian(sc);
                    break;
                case 5:
                    findJoinOrIntersection(sc);
                    break;
                case 6:
                    cutMathSet();
                    break;
                case 7:
                    clearMathSet();
                    break;
                case 8:
                    print();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid input!\n");
                    break;
            }
        }
    }

    private static void checkInput(Scanner sc) {
        if (!sc.hasNextInt()) {
            System.out.print("This is not a number! Try again >> ");
            sc.next();
        }
    }

    private static void findMinOrMaxValue(Scanner sc) {
        System.out.println("1-Max value");
        System.out.println("2-Min value");
        checkInput(sc);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Max value: " + numbers.getMax());
                break;
            case 2:
                System.out.println("Min value: " + numbers.getMin());
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
    }

    private static void findAverageOrMedian(Scanner sc) {
        System.out.println("1-Find average");
        System.out.println("2-Find median");
        checkInput(sc);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Average = " + numbers.getAverage());
                break;
            case 2:
                System.out.println("Median = " + numbers.getMedian());
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
    }

    private static void findJoinOrIntersection(Scanner sc) {
        System.out.println("1-Join");
        System.out.println("2-Intersection");
        checkInput(sc);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                joinMathSet();
                break;
            case 2:
                intersectionMathSet();
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
    }

    private static void sortMathSet(Scanner sc) {
        System.out.println("1-Descending sort");
        System.out.println("2-Ascending sort");
        checkInput(sc);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("1-Sort completely");
                System.out.println("2-Sort between two indexes");
                System.out.println("3-Sort from element");
                checkInput(sc);
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        sortDesc();
                        break;
                    case 2:
                        sortDescBetweenIndexes();
                        break;
                    case 3:
                        sortDescFromElement();
                        break;
                    default:
                        System.out.println("Invalid input!");
                        break;
                }
                break;
            case 2:
                System.out.println("1-Sort completely");
                System.out.println("2-Sort between two indexes");
                System.out.println("3-Sort from element");
                checkInput(sc);
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        sortAsc();
                        break;
                    case 2:
                        sortAscBetweenIndexes();
                        break;
                    case 3:
                        sortAscFromElement();
                        break;
                    default:
                        System.out.println("Invalid input!");
                        break;
                }
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
    }

    private static void addElements(MathSet<Integer> mathSet) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter count of numbers:");
            String count = sc.nextLine();
            for (int i = 0; i < Integer.parseInt(count); i++) {
                System.out.print("Enter the number:");
                String d = sc.nextLine();
                Integer entered = Integer.parseInt(d);
                mathSet.add(entered);
            }
            print();
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input");
        }
    }

    private static void print() {
        if (numbers.size() != 0) {
            System.out.println(numbers);
        } else {
            System.out.println("Set is empty!");
        }
    }

    private static void sortDesc() {
        System.out.println("Your set:");
        print();
        numbers.sortDesc();
        System.out.println("Sorted set:");
        print();
    }

    private static void sortDescBetweenIndexes() {
        System.out.println("Your set:");
        print();
        System.out.print("Enter the first index:");
        int first = in.nextInt();
        System.out.print("Enter the second index:");
        int second = in.nextInt();
        numbers.sortDesc(first, second);
        System.out.println("Sorted set:");
        print();
    }

    private static void sortDescFromElement() {
        System.out.println("Your set:");
        print();
        System.out.print("Enter the first element:");
        int element = in.nextInt();
        numbers.sortDesc(element);
        System.out.println("Sorted set:");
        print();
    }

    private static void sortAsc() {
        System.out.println("Your set:");
        print();
        numbers.sortAsc();
        System.out.println("Sorted set:");
        print();
    }

    private static void sortAscBetweenIndexes() {
        System.out.println("Your set:");
        print();
        System.out.print("Enter the first index:");
        int first = in.nextInt();
        System.out.print("Enter the second index:");
        int second = in.nextInt();
        numbers.sortAsc(first, second);
        System.out.println("Sorted set:");
        print();
    }

    private static void sortAscFromElement() {
        System.out.println("Your set:");
        print();
        System.out.print("Enter the first element:");
        int element = in.nextInt();
        numbers.sortAsc(element);
        System.out.println("Sorted set:");
        print();
    }

    private static void clearMathSet() {
        numbers.clear();
        print();
    }

    private static void joinMathSet() {
        System.out.println("Create new set");
        MathSet<Integer> ms = new MathSet<>();
        addElements(ms);
        numbers.join(new MathSet<Integer>(ms));
        print();
    }

    private static void intersectionMathSet() {
        System.out.println("Create new set");
        MathSet<Integer> ms = new MathSet<>();
        addElements(ms);
        numbers.intersection(new MathSet<Integer>(ms));
        print();
    }

    private static void cutMathSet() {
        while (true) {
            try {
                print();
                System.out.print("Enter the first index:");
                int first = in.nextInt();
                System.out.print("Enter the second index:");
                int second = in.nextInt();
                System.out.print("Cut set:");
                MathSet cutSet = numbers.cut(first, second);
                for (int i = 0; i < cutSet.size(); i++) {
                    System.out.print(" " + cutSet.get(i));
                }
                System.out.println();
                break;
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Incorrect value!");
            }
        }
    }
}